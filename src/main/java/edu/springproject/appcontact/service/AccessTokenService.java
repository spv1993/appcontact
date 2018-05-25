package edu.springproject.appcontact.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;

import edu.springproject.appcontact.exception.AccessTokenException;
import edu.springproject.appcontact.model.AccessToken;
import edu.springproject.appcontact.model.User;

public interface AccessTokenService {

	public AccessToken getAccessToken(User user) throws AccessTokenException;

	public Boolean verifyAccessToken(String token, User user) throws AccessTokenException;

	public String getTokenFromRequest(HttpServletRequest request);
	
	public String getUsernameFromToken(String token);

	public String getAuthoritiesFromToken(String token);

	public Long getUserIdFromToken(String token);
}
