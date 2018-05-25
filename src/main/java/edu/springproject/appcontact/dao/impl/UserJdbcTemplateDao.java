package edu.springproject.appcontact.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import edu.springproject.appcontact.dao.UserDao;
import edu.springproject.appcontact.model.Role;
import edu.springproject.appcontact.model.User;
import edu.springproject.appcontact.utils.UserMapper;
import edu.springproject.appcontact.utils.UserWithRoleMapper;

@Repository
public class UserJdbcTemplateDao implements UserDao {

	private static final String SQL_SELECT = 
			"SELECT user_id, username, password FROM \"user\"";
	
	private static final String SQL_SELECT_SINGLE_BY_ID = 
			"SELECT user_id, username, password FROM \"user\" WHERE user_id=?";
	
	private static final String SQL_SELECT_SINGLE_BY_NAME = 
			"SELECT user_id, username, password FROM \"user\" WHERE username=?";
	
	private static final String SQL_INSERT = 
			"INSERT INTO \"user\" (username, password) VALUES (?, ?)";
	
	private static final String SQL_UPDATE = 
			"UPDATE \"user\" SET username=?, password=? WHERE user_id=?";
	
	private static final String SQL_DELETE = 
			"DELETE FROM \"user\" WHERE user_id=?";
	
	private static final String SQL_COUNT = 
			"SELECT Count(*) FROM \"user\" WHERE username=?";

	private static final String SQL_INSERT_USER_ROLES = 
			"INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
	
	private static final String SQL_SELECT_WITH_ROLE_SINGLE_BY_NAME = 
			"SELECT u.user_id, u.username, u.password, r.role_id, r.role " + 
			"FROM user_role as ur " +
			"INNER JOIN \"user\" as u " +
				"ON ur.user_id=u.user_id " +
			"INNER JOIN role as r " +
				"ON ur.role_id=r.role_id " +
			"WHERE u.username=?";

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public User getByUsername(String username) {
		return jdbcTemplate.queryForObject(
				SQL_SELECT_SINGLE_BY_NAME, 
				new Object[] { username }, 
				new UserMapper());
	}
	
	@Override
	public User getWithRolesByUsername(String username) {
		return jdbcTemplate.query(
				SQL_SELECT_WITH_ROLE_SINGLE_BY_NAME, 
				new Object[] { username }, 
				new UserWithRoleMapper());
	}

	@Override
	public User getUserById(long userId) {
		return jdbcTemplate.queryForObject(
				SQL_SELECT_SINGLE_BY_ID, 
				new Object[] { userId }, 
				new UserMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public long createUser(User user) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		final PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection c)
					throws SQLException {

				final PreparedStatement ps = c.prepareStatement(SQL_INSERT,
						new String[] { "user_id" });
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				return ps;
			}
		};

		jdbcTemplate.update(psc, keyHolder);
		Long newUserId = keyHolder.getKey().longValue();
		
		this.setUserRoles(newUserId, (List<Role>) user.getAuthorities());
		
		return newUserId;
	}

	@Override
	public void updateUser(User user) {
		Long id = user.getId();
		String username = user.getUsername();
		String password = user.getPassword();

		jdbcTemplate.update(SQL_UPDATE, username, password, id);
	}

	@Override
	public void removeUser(long userId) {
		jdbcTemplate.update(SQL_DELETE, userId);
		
	}

	@Override
	public List<User> getUsers() {
		return jdbcTemplate.query(SQL_SELECT, new UserMapper());
	}

	@Override
	public boolean isUserExistsByUsername(String username) {
		Integer userCount = jdbcTemplate.queryForObject(SQL_COUNT, 
				Integer.class, username);
		return userCount != null && userCount > 0;
	}

	@Override
	public void setUserRoles(Long userId, List<Role> roles) {
		jdbcTemplate.batchUpdate(SQL_INSERT_USER_ROLES, 
			new BatchPreparedStatementSetter() {
		
				@Override
				public void setValues(PreparedStatement ps, int i) 
						throws SQLException { 
					
					System.out.println("setUserRoles------------>");
					System.out.println(userId);
					System.out.println(roles.get(i).getRoleAsEnum().getId());
					ps.setLong(1, userId); 
					ps.setLong(2, roles.get(i).getRoleAsEnum().getId()); 
				}
	
				@Override
				public int getBatchSize() { 
					return roles.size(); 
				}
			});
	}
}
