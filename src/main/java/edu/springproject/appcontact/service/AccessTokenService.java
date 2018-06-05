package edu.springproject.appcontact.service;

import javax.servlet.http.HttpServletRequest;

import edu.springproject.appcontact.model.AccessToken;
import edu.springproject.appcontact.model.User;

public interface AccessTokenService {

	public AccessToken getAccessToken(User user);

	public Boolean verifyAccessToken(String token, User user);

	public String getTokenFromRequest(HttpServletRequest request);
	
	public String getUsernameFromToken(String token);

	public String getAuthoritiesFromToken(String token);

	public Long getUserIdFromToken(String token);
}
