package com.pft.string.service.api.common.bdo;


public class PasswordChange {

	private String oldPassword;
	private String newPassword;
	private String reEnterPassword;
	private String userSession;
	private String username;
	
	public PasswordChange() {
	}

	public PasswordChange(String oldPassword, String newPassword ,String userSession,String reEnterPassword, String username) {
		this.oldPassword = oldPassword;
		this.userSession = userSession;
		this.newPassword = newPassword;
		this.reEnterPassword = reEnterPassword;
		this.username=username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username=username;
	}

	
	public String getReEnterPassword() {
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserSession() {
		return userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
