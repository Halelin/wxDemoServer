package com.xms.entity;

import java.sql.Timestamp;

public class UserWx {
	private String id;
	private String nickname;
	private Timestamp registedate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Timestamp getRegistedate() {
		return registedate;
	}
	public void setRegistedate(Timestamp registedate) {
		this.registedate = registedate;
	}
	
}
