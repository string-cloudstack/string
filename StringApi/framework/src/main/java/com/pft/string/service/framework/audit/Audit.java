package com.pft.string.service.framework.audit;

import java.util.Date;

public class Audit implements java.io.Serializable {

	private long id;
	private Date inTimeStamp;
	private Date outTimeStamp;
	private String userSessionKey;
	private String action;
	private String parameters;
	private String preChangeValues;
	private boolean successStatus;
	private String errorMessage;
	private String requestId;
	private String additionalInfo;	
	private int severity;
	private int userId;

	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Audit() {
	}

	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getUserSessionKey() {
		return this.userSessionKey;
	}

	public void setUserSessionKey(String userSessionKey) {
		this.userSessionKey = userSessionKey;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParameters() {
		return this.parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getPreChangeValues() {
		return this.preChangeValues;
	}

	public void setPreChangeValues(String preChangeValues) {
		this.preChangeValues = preChangeValues;
	}

	public boolean getSuccessStatus() {
		return this.successStatus;
	}

	public void setSuccessStatus(boolean successStatus) {
		this.successStatus = successStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public Date getInTimeStamp() {
		return inTimeStamp;
	}

	public void setInTimeStamp(Date inTimeStamp) {
		this.inTimeStamp = inTimeStamp;
	}

	public Date getOutTimeStamp() {
		return outTimeStamp;
	}

	public void setOutTimeStamp(Date outTimeStamp) {
		this.outTimeStamp = outTimeStamp;
	}


	public String getAdditionalInfo() {
		return additionalInfo;
	}


	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}


}

