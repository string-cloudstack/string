package com.pft.string.service.framework.core;

public class ApplicationContext
{
	
	public ApplicationContext(Long tenantId,Long userId,String token,String physicalPath, Boolean multitenancy, String factoryKey)
	{
		this.tenantId= tenantId;
		this.token = token;
		this.userId = userId;
		this.setPhysicalPath(physicalPath);
		this.multiTenancy = multitenancy;
		this.factoryKey = factoryKey;
	}
	public ApplicationContext(Long tenantId, Long userId, String token,
			String currentAppPath) {
		this.tenantId= tenantId;
		this.token = token;
		this.userId = userId;
		this.setPhysicalPath(physicalPath);
	}
	private Long tenantId;
	
	private Long userId;
	
	private String token;
	
	private String physicalPath;
	
	private Boolean multiTenancy;
	
	private String  factoryKey;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	public Boolean getMultiTenancy() {
		return multiTenancy;
	}

	public void setMultiTenancy(Boolean multiTenancy) {
		this.multiTenancy = multiTenancy;
	}

	public String getFactoryKey() {
		return factoryKey;
	}

	public void setFactoryKey(String factoryKey) {
		this.factoryKey = factoryKey;
	}
	

}
