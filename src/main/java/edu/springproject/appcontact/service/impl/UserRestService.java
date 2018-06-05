package edu.springproject.appcontact.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.springproject.appcontact.model.Role;
import edu.springproject.appcontact.model.User;
import edu.springproject.appcontact.dao.UserDao;
import edu.springproject.appcontact.service.UserService;

@Service
public class UserRestService implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = dao.getWithRolesByUsername(username);
		
		if (user == null) {
			// TODO add exception
            return null;
        }

		return new User(user.getId(), user.getUsername(), 
				user.getPassword(), user.getAuthorities());
	}

	@Override
	public User getByUsername(String username) {
		return dao.getByUsername(username);
	}

	@Override
	public User getUserById(long id) {
		return dao.getUserById(id);
	}

	@Override
	public boolean isUserExistsByUsername(String username) {
		return dao.isUserExistsByUsername(username);
	}
	
	@Override
	public long createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAuthorities(Arrays.asList(new Role()));
		return dao.createUser(user);
	}

	@Override
	public void updateUser(User user) {
		dao.updateUser(user);
		
	}

	@Override
	public void removeUser(long id) {
		dao.removeUser(id);
	}

	@Override
	public List<User> getUsers() {
		return dao.getUsers();
	}

	@Override
	public User getWithRolesByUsername(String username) {
		return dao.getWithRolesByUsername(username);
	}
}
