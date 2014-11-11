package com.pft.string.service.framework.core;

public class Constants
{
	public static final String CurrentSessionKey = "CurrentSession";
	
	public static final String CurrentTenantIdKey = "CurrentTenantId";
	
	public static final String SessionFactoryJNDILookupKey = "SessionFactoryJNDILookupKey";
	public static final String DefaultSessionFactoryJNDILookup = "java:/hibernate/Factory";
	
	public static final String UserId = "UserId";

	public static final String ApplicationContext = "ApplicationContext";
	
	public static final String ConfigDir = System.getProperty("jboss.server.config.dir") + "/clear/";
	
	public static final String DataSourcesDir = System.getProperty("jboss.server.base.dir") + "/configuration/clear/connections";

	public static final String Multitenancy = "MultiTenancy";

	public static final String MultiTenantConnectionProviderKey = "java:/hibernate/MultiTenantConnectionProvider";
}
