package com.pft.string.service.framework.data.connection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.pft.string.service.framework.core.Constants;
import com.pft.string.service.framework.core.ObjectSerializationToXML;

public class DataSourcer {
	
	public static DataSource createDataSource(DBServer serverType,String url, String userName, String password) {
		final String sqlServerDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		final String validationQueryForSQLServer = "SELECT 1";
		final String mysqlDriver = "com.mysql.jdbc.Driver";
		final String oracleDriver = "oracle.jdbc.driver.OracleDriver";

		final int minIdle = 3;
		final int maxIdle = 3;
		final int maxActive = 10;
		final long maxWait = 6000;
		final boolean removeAbandoned = true;
		final boolean logAbandoned = true;
		final boolean testOnBorrow = true;
		final boolean testOnReturn = false;
		final boolean testWhileIdle = false;
		final long timeBetweenEvictionRunsMillis = 30000;
		final long minEvictableIdleTimeMillis = 30000;
        
		BasicDataSource dataSource = new BasicDataSource();	
		
		if(serverType == DBServer.Sqlserver)
		{
			dataSource.setDriverClassName(sqlServerDriver);
			dataSource.setValidationQuery(validationQueryForSQLServer);	
		}
		if(serverType == DBServer.Mysql)
		{
			dataSource.setDriverClassName(mysqlDriver);				
		}
		if(serverType == DBServer.Oracle)
		{
			dataSource.setDriverClassName(oracleDriver);				
		}
		
		
		dataSource.setUsername(userName);
		dataSource.setPassword(password);		
			
		dataSource.setUrl(url);
		dataSource.setMaxIdle(minIdle);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxWait(maxWait);
		dataSource.setRemoveAbandoned(removeAbandoned);
		dataSource.setLogAbandoned(logAbandoned);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		return dataSource;
	}

	public static Map<String, DataSource> getTenantDataSources(
			List<PFTDataSource> dataSources) {
		Map<String, DataSource> tenantDataSources = new HashMap<String, DataSource>();
		for(PFTDataSource source : dataSources)
		{
			tenantDataSources.put(source.getTenantId().toString(),createDataSource(source.getDbServer(),source.getJdbcURL(),source.getDbUserName(),source.getPassWord()));			
		}
		return tenantDataSources;
	}
	
	public static Map<String, DataSource> getTenantDataSources()
	{
		List<PFTDataSource> dataSources =  readDataSourcesFromFileSystem();
		Map<String, DataSource> tenantDataSources = new HashMap<String, DataSource>();
		for(PFTDataSource source : dataSources)
		{
			tenantDataSources.put(source.getTenantId().toString(),createDataSource(source.getDbServer(),source.getJdbcURL(),source.getDbUserName(),source.getPassWord()));			
		}
		return tenantDataSources;
	}


	public static List<PFTDataSource> readDataSourcesFromFileSystem() {

		List<PFTDataSource> dataSources = null;
		try {

			/*
			 * Mani D. [13-Mar-2013] All configurations are kept under
			 * [jboss_installation_path]/configuration/clear folder.
			 * 
			 * Be it the deployment mode STANDALONE or DOMAIN,
			 * [jboss.server.base.dir] gives the valid path.
			 */
			/*File file = new File(Constants.DataSourcesDir);
			if (file != null) {
				dataSources = new ArrayList<PFTDataSource>();
			}*/
			String path=System.getProperty("jboss.server.base.dir") + "/configuration/connections/database.xml";
			/*File[] allFiles = file.listFiles();
			ObjectSerializationToXML serializer = new ObjectSerializationToXML();
			for (File confile : allFiles) {
				if (confile.isFile() && confile.getName().endsWith(".xml")) {
					try {
						PFTDataSource source = (PFTDataSource) serializer
								.deserializeXMLToObject(confile
										.getAbsolutePath());
						dataSources.add(source);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}*/
			ObjectSerializationToXML serializer = new ObjectSerializationToXML();
				try {
					PFTDataSource source = (PFTDataSource) serializer
							.deserializeXMLToObject(path);
					dataSources = new ArrayList<PFTDataSource>();
					if(source!=null)
					 dataSources.add(source);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dataSources;
	}
	public static DBServer GetDBType(Long tenantId) throws Exception
	{
		PFTDataSource dataSource = readDataSourceFromFileSystem(tenantId);
		if(dataSource != null)
		{
			return dataSource.getDbServer();
		}
		else
		{
		 throw new Exception("DataSource not found");
		}
	}
	public static PFTDataSource readDataSourceFromFileSystem(Long tenantId) 
	{
		PFTDataSource dataSource = null;
		try {			
			File file = new File(Constants.DataSourcesDir);
			if (file != null) {
				
			File[] allFiles = file.listFiles();
			ObjectSerializationToXML serializer = new ObjectSerializationToXML();
			for (File confile : allFiles) {
				if (confile.isFile() && confile.getName().endsWith(".xml")) {
					try {
						dataSource = (PFTDataSource) serializer
								.deserializeXMLToObject(confile
										.getAbsolutePath());
						if(dataSource.getTenantId().toString().equals(tenantId.toString()))
						{
							return dataSource;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		} 
			}
		}
			catch (Exception ex) {
			ex.printStackTrace();
		}

		return dataSource;
	
	}

}
