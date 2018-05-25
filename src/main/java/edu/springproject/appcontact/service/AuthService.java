package edu.springproject.appcontact.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import edu.springproject.appcontact.model.AccessToken;

public interface AuthService {
	
	public AccessToken authenticate(HttpServletRequest request, String username, String password);
	
	public void authenticateByToken(HttpServletRequest request);
	
	public Authentication getPrincipal();

	public Boolean logout(HttpServletRequest request, HttpServletResponse response);

	public AccessToken registration(HttpServletRequest request, String username, String password);
}
