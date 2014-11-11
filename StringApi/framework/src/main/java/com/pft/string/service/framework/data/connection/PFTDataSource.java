package com.pft.string.service.framework.data.connection;

public class PFTDataSource
{
	private Integer tenantId;
	
	private DBServer dbServer;
	
	private String jdbcURL;
	
	private String dbUserName;
	
	private String  passWord;

	public DBServer getDbServer() {
		return dbServer;
	}

	public void setDbServer(DBServer dbServer) {
		this.dbServer = dbServer;
	}

	public String getJdbcURL() {
		return jdbcURL;
	}

	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
	
}
