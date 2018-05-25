package edu.springproject.appcontact.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import edu.springproject.appcontact.model.User;

public interface UserService extends UserDetailsService {

	public UserDetails loadUserByUsername(String username);
	
	public User getByUsername(String username);
	
	public User getUserById(long id);
	
	public long createUser(User user);

	public void updateUser(User user);

	public void removeUser(long id);

	public List<User> getUsers();

	boolean isUserExistsByUsername(String username);

	public User getWithRolesByUsername(String username);
}
