package edu.springproject.appcontact.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	private static final long serialVersionUID = -5327144159530603562L;
	
	private long id;
	private String username;
	private String password;
	private boolean enabled = true;
	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;
	private boolean accountNonLocked = true;
	private Collection<? extends GrantedAuthority> authorities;
	
	public User(){
		super();
	}
	
	public User(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(long id, String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public String getAuthoritiesAsString() {
		if (this.authorities == null) {
			return "";
		}
		return new ArrayList<GrantedAuthority>(this.authorities).stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(", "));
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public void setAuthorities(String authorities) {
		if(authorities == null) {
			this.authorities = null;
			return;
		}
		this.authorities = Arrays.asList(authorities.split(","))
			.stream()
			.map(new Function<String, Role>() {
				@Override
				public Role apply(String authority) {
					return new Role(authority);
				}
			})
			.collect(Collectors.toSet());
	}
	
	@Override
	public String toString() {
		return String.format("User{id='%d', username='%s', password='%s', Roles[%s]}", 
				id, username, password, this.getAuthoritiesAsString());
	}
}
