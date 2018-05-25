package edu.springproject.appcontact.dao;

import java.util.List;

import edu.springproject.appcontact.model.Role;
import edu.springproject.appcontact.model.User;

public interface UserDao {
	
	public User getByUsername(String username);
	
	public User getUserById(long id);
	
	public long createUser(User user);

	public void updateUser(User user);

	public void removeUser(long id);

	public List<User> getUsers();

	public boolean isUserExistsByUsername(String username);

	public User getWithRolesByUsername(String username);

	public void setUserRoles(Long userId, List<Role> roles);
}
