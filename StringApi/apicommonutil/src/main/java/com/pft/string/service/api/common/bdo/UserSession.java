package com.pft.string.service.api.common.bdo;

import java.util.Date;

public class UserSession {

	private int id;
	private String userSession;
	private String username;
	private String userGuid;
	private Date sessionTime;

	public UserSession() {
	}

	public UserSession(int id, String userSession, String username, String userGuid, Date sessionTime) {
		
		this.id = id;
		this.username = username;
		this.userGuid = userGuid;
		this.userSession = userSession;
		this.sessionTime = sessionTime;
	}
	
	public String getUserSession() {
		return userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public Date getSessionTime() {
		return this.sessionTime;
	}

	public void setSessionTime(Date sessionTime) {
		this.sessionTime = sessionTime;
	}
	


}
