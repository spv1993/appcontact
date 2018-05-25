package edu.springproject.appcontact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import edu.springproject.appcontact.model.AccessToken;
import edu.springproject.appcontact.model.User;
import edu.springproject.appcontact.service.AccessTokenService;
import edu.springproject.appcontact.service.AuthService;
import edu.springproject.appcontact.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationRestController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/get")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AccessToken> signin(HttpServletRequest request, @RequestBody User user) throws AuthenticationException {
		return ResponseEntity.ok(authService.authenticate(request, user.getUsername(), user.getPassword()));
	}
	
	@GetMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		authService.logout(request, response);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AccessToken> signup(HttpServletRequest request, @RequestBody User user) {
		return ResponseEntity.ok(authService.registration(request, user.getUsername(), user.getPassword()));
	}
	

}
