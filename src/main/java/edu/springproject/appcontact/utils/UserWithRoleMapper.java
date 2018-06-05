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
		
		if (!rs.isBeforeFirst() ) {    
		    return null;
		} 
		
//		rs.first();
		
		Long userId = rs.getLong("user_id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		System.out.println("<------------RS---------->");
//		if (!rs.isBeforeFirst() ) {    
//		    return null;
//		} 
		System.out.println(rs.getLong("user_id"));
		System.out.println(rs.getString("username"));
		System.out.println(rs.getString("password"));
		System.out.println("<------------RS---------->");
		Set<Role> roles = new HashSet<Role>();
		while (rs.next()) {
			System.out.println("<----------------------->");
			userId = rs.getLong("user_id");
			username = rs.getString("username");
			password = rs.getString("password");
			roles.add(new Role(rs.getString("role")));
			
			System.out.println(userId);
			System.out.println(username);
			System.out.println(password);
			System.out.println(new User(userId, username, password, roles));
			System.out.println("<----------------------->");
		}

		return new User(userId, username, password, roles);
	}

}
