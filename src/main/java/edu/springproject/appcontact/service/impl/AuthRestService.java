package edu.springproject.appcontact.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import edu.springproject.appcontact.model.AccessToken;
import edu.springproject.appcontact.model.User;
import edu.springproject.appcontact.service.AccessTokenService;
import edu.springproject.appcontact.service.AuthService;
import edu.springproject.appcontact.service.UserService;

@Service
public class AuthRestService implements AuthService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccessTokenService tokenService;
	
	@Override
	public AccessToken authenticate(HttpServletRequest req, 
									String username, 
									String password
		) {
		
		System.out.println("authenticate");
		
		final User userDetails = (User) userService.loadUserByUsername(username);
		try {
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(username, 
							password, userDetails.getAuthorities());
			
			if (auth.isAuthenticated()) {
				auth.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(req)
				);        
		        
				SecurityContextHolder
					.getContext()
		        	.setAuthentication(authManager.authenticate(auth));
		        
				return tokenService.getAccessToken(userDetails);
			}
		} catch (AuthenticationException ex) { 
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void authenticateByToken(HttpServletRequest request) {
		
		String token = tokenService.getTokenFromRequest(request);
		String usernameFromToken;
		
		if(token == null) {
			return;
		}
		
		if((usernameFromToken = tokenService.getUsernameFromToken(token)) == null) {
			return;
		}
		
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = new User();
			user.setUsername(usernameFromToken);
			user.setAuthorities(tokenService.getAuthoritiesFromToken(token));
			user.setId(tokenService.getUserIdFromToken(token));
			
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
			
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
	}
	
	@Override
	public AccessToken registration(HttpServletRequest request, 
									String username, 
									String password
		) {

		if (!userService.isUserExistsByUsername(username)) {
			userService.createUser(new User(username, password));
			return authenticate(request, username, password);
	    } else {
	    	return null;
//	    	throw new CustomException("Username is already in use", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@Override
	public Authentication getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    return authentication;
		}
		return null;
	}
	
	@Override
	public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = this.getPrincipal();
	    if (auth != null){
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	        return true;
	    }
	    return false;
	}
}
