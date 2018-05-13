package edu.springproject.appcontact.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.springproject.appcontact.model.Contact;

public class ContactMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("contact_id");
		String fname = rs.getString("first_name");
		String lname = rs.getString("last_name");
		String phone = rs.getString("phone");
		String email = rs.getString("email");

		return new Contact(id, fname, lname, phone, email);
	}

}
