package edu.springproject.appcontact.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import edu.springproject.appcontact.model.AccessToken;
import edu.springproject.appcontact.model.User;
import edu.springproject.appcontact.service.AccessTokenService;

@Service
public class AccessTokenRestService implements AccessTokenService {
	
	private String secretKey = "secretKey!";
	private Long expiration = 3600000L;
	private String tokenName = "Authorization";
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	@Override
	public AccessToken getAccessToken(User user) {
		final Date now = new Date();

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("USER_ID", String.valueOf(user.getId()));
        claims.put("AUTHORITIES", String.join(",", user.getAuthoritiesAsString()));
        claims.put("CREATED_AT", this.getCurrentDate());
        
        String token = Jwts.builder()
        		.setClaims(claims)
        		.setId(this.getId())
        		.setIssuedAt(now)
        		.setExpiration(this.getExpiration())
        		.signWith(SignatureAlgorithm.HS512, this.secretKey)
        		.compact();
        
        return new AccessToken(token);
	}	
	
	@Override
	public Boolean verifyAccessToken(String token, User user) {		
		
		if(token == null || user == null) {
			return false;
		}
		
		final String tokenUsername = this.getUsernameFromToken(token);
		final Date tokenExpiration = this.getExpirationFromToken(token);
		
		return (tokenUsername.equals(user.getUsername()) && 
				tokenExpiration.after(this.getCurrentDate()));
	}
	
	private Date getExpiration() {
        return new Date(new Date().getTime() + this.expiration);
    }
	
	private String getId() {
		return UUID.randomUUID().toString();
	}
	
	private Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }
	
	private Claims parseTokenBody(String token) {
		if(token == null) {
			return null;
		}
		try {
			return Jwts.parser().setSigningKey(this.secretKey)
					.parseClaimsJws(token).getBody();
		} catch(ExpiredJwtException ex) {
			// TODO catch expired token
			return null;
		}
		
	}
	
	public Date getExpirationFromToken(String token) {
		
		return this.parseTokenBody(token).getExpiration();
	}
	
	@Override
	public String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader(this.tokenName);
	}
	
	@Override
	public String getUsernameFromToken(String token) {
				
		Claims body;
		
		if((body = this.parseTokenBody(token)) == null) {
			return null;
		}
		return body.getSubject();
	}
	
	@Override
	public String getAuthoritiesFromToken(String token) {
		return (String) this.parseTokenBody(token).get("AUTHORITIES");
	}
	
	@Override
	public Long getUserIdFromToken(String token) {
		return Long.valueOf((String) this.parseTokenBody(token).get("USER_ID"));
	}
}
