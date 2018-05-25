package edu.springproject.appcontact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.springproject.appcontact.model.User;
import edu.springproject.appcontact.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserRestController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<User> getUsers() {
		return service.getUsers();
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable("userId") long userId) {
		return service.getUserById(userId);
	}

	@PostMapping("/users")
	public Long createUser(@RequestBody User user) {
		return service.createUser(user);
	}

	@PutMapping("/users/{userId}")
	public void updateUser(@RequestBody User user) {
		service.updateUser(user);
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable("userId") long userId) {
		service.removeUser(userId);
	}
}
