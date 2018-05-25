package edu.springproject.appcontact.model;

public class AccessToken {

	private String accessToken;
	
	public AccessToken() {
		super();
	}
	
	public AccessToken(String accessToken) {
		this.setAccessToken(accessToken);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
