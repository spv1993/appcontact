package edu.springproject.appcontact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import edu.springproject.appcontact.model.Contact;
import edu.springproject.appcontact.utils.ContactMapper;

@Repository
public class ContactJdbcTemplateDao implements ContactDao {

	private static final String SQL_SELECT = "SELECT contact_id, first_name, last_name, phone, email FROM contact";
	private static final String SQL_SELECT_SINGLE = "SELECT contact_id, first_name, last_name, phone, email FROM contact WHERE contact_id=?";
	private static final String SQL_INSERT = "INSERT INTO contact (first_name, last_name, phone, email) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE contact SET first_name=?, last_name=?, phone=?, email=? WHERE contact_id=?";
	private static final String SQL_DELETE = "DELETE FROM contact WHERE contact_id=?";
	private static final String SQL_COUNT = "SELECT Count(*) FROM contact WHERE contact_id=?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Contact> getContacts() {
		return jdbcTemplate.query(SQL_SELECT, new ContactMapper());
	}

	@Override
	public long createContact(Contact contact) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		final PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection c)
					throws SQLException {

				final PreparedStatement ps = c.prepareStatement(SQL_INSERT,
						new String[] { "contact_id" });
				ps.setString(1, contact.getFirstName());
				ps.setString(2, contact.getLastName());
				ps.setString(3, contact.getPhone());
				ps.setString(4, contact.getEmail());
				return ps;
			}
		};

		jdbcTemplate.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void updateContact(Contact contact) {
		Long id = contact.getId();
		String fname = contact.getFirstName();
		String lname = contact.getLastName();
		String phone = contact.getPhone();
		String email = contact.getEmail();

		jdbcTemplate.update(SQL_UPDATE, fname, lname, phone, email, id);
	}

	@Override
	public void removeContact(long contactId) {
		jdbcTemplate.update(SQL_DELETE, contactId);
	}

	@Override
	public Contact getContact(long contactId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_SINGLE, 
				new Object[] { contactId }, new ContactMapper());
	}

	@Override
	public boolean contactIsExists(long contactId) {
		Integer entryCount = jdbcTemplate.queryForObject(SQL_COUNT, 
				Integer.class, contactId);
		return entryCount != null && entryCount > 0;
	}
}
