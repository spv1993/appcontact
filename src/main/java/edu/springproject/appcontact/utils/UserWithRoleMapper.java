package edu.springproject.appcontact.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.springproject.appcontact.model.Role;
import edu.springproject.appcontact.model.User;

public class UserWithRoleMapper implements ResultSetExtractor<User> {
	
	@Override
	public User extractData(ResultSet rs) 
			throws SQLException, DataAccessException {
		
		Long userId = null;
		String username = null;
		String password = null;
		
		Set<Role> roles = new HashSet<Role>();
		while (rs.next()) {
			userId = rs.getLong("user_id");
			username = rs.getString("username");
			password = rs.getString("password");
			try {
				roles.add(new Role(rs.getString("role")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				roles.add(new Role());
			}
		}
		
		return new User(userId, username, password, roles);
	}

}
