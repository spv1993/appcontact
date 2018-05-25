package edu.springproject.appcontact.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 370687271018568645L;
	
	private RoleStatus role = RoleStatus.USER;
	
	public enum RoleStatus {
        ADMIN(1L), USER(2L);
        
        private final Long id;
        
        RoleStatus(Long id) {
        	this.id = id;
        }
        
        public Long getId() {
        	return this.id;
        }
    }
	
	public Role() {
	}
	
	public Role(String roleString) {
		this.setRole(roleString);
	}

	public void setRole(String roleString) {
		this.role = RoleStatus.valueOf(roleString.trim().toUpperCase());
	}
	
	public RoleStatus getRoleAsEnum() {
		return this.role;
	}
	
	public String getRoleAsString() {
		return this.role.toString();
	}

	@Override
	public String getAuthority() {
		return getRoleAsString();
	}
}
