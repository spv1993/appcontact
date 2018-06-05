package edu.springproject.appcontact.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.springproject.appcontact.service.AccessTokenService;
import edu.springproject.appcontact.service.AuthService;

public class AccessTokenFilter extends OncePerRequestFilter {

	@Autowired
	private AuthService authService;
	
	public AccessTokenFilter(AuthService authService) {
		this.authService = authService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, 
									FilterChain filterChain
	) throws ServletException, IOException {
		
		try {	    	
			authService.authenticateByToken(request);   	
	    } catch(Exception ex) {
	    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
	    			ex.getLocalizedMessage());
            return;
	    }
		
	    filterChain.doFilter(request, response);
	}

}
