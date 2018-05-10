package edu.springproject.appcontact.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.springproject.appcontact.model.Contact;
import edu.springproject.appcontact.utils.ContactMapper;

@Repository
public class ContactJdbcTemplateDao implements ContactDao {
	
	private static final String SELECT = 
			"SELECT contact_id, first_name, last_name, phone, email FROM contact";
	private static final String SELECT_SINGLE = 
			"SELECT contact_id, first_name, last_name, phone, email FROM contact WHERE contact_id=?";
	private static final String INSERT = 
			"INSERT INTO contact (first_name, last_name, phone, email) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE contact SET first_name=?, last_name=?, phone=?, email=? WHERE contact_id=?";
	private static final String DELETE = 
			"DELETE FROM contact WHERE contact_id=?";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Contact> getContacts() {
		return jdbcTemplate.query(SELECT, new ContactMapper());
	}

	@Override
	public long createContact(Contact c) {
//		jdbcTemplate.update(INSERT, c.getFirstName(), c.getLastName(), c.getPhone(), c.getEmail());
		return jdbcTemplate.update(INSERT, c.getFirstName(), c.getLastName(), c.getPhone(), c.getEmail());
	}

	@Override
	public void updateContact(Contact c) {
		jdbcTemplate.update(UPDATE, c.getFirstName(), c.getLastName(), c.getPhone(), c.getEmail());	
	}

	@Override
	public void removeContact(long id) {
		jdbcTemplate.update(DELETE, id);
	}

	@Override
	public Contact getContact(long id) {
		Contact contact = jdbcTemplate.queryForObject(SELECT_SINGLE, new Object[]{id}, new ContactMapper());
		return contact;
	}
}
