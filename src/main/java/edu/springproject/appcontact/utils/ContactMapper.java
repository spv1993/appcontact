package edu.springproject.appcontact.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.springproject.appcontact.model.Contact;

public class ContactMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getLong("contact_id"));
		contact.setFirstName(rs.getString("first_name"));
		contact.setLastName(rs.getString("last_name"));
		contact.setPhone(rs.getString("phone"));
		contact.setEmail(rs.getString("email"));
		return contact;
	}

}
