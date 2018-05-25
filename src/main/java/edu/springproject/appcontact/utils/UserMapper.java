package edu.springproject.appcontact.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import edu.springproject.appcontact.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("user_id");
		String username = rs.getString("username");
		String password = rs.getString("password");

		return new User(id, username, password);
	}

}
