package com.xms.entity;
public class AccessToken {
	private Integer id;
	private String access_token;
	private Integer expires_in;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccess_token() {
		return access_token;
	}	
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public Integer getExpires_in() {
		return expires_in;
	}
	
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
}
