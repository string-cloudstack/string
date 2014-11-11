package com.pft.string.service.api.common.bdo;

public class QueryRegistryReq {

	private String userSession;
	private String assetUUID;

	public QueryRegistryReq()
	{

	}
	public QueryRegistryReq(String userSession,String assetUUID)
	{
		this.userSession=userSession;
		this.assetUUID=assetUUID;
	}

	public String getUserSession() {
		return userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	public String getAssetUUID() {
		return assetUUID;
	}

	public void setAssetUUID(String assetUUID) {
		this.assetUUID = assetUUID;
	}



}
