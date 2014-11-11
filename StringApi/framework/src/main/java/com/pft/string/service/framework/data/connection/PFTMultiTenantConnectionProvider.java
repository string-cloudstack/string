package com.pft.string.service.framework.data.connection;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.Session;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import com.pft.string.service.framework.core.Constants;
import com.pft.string.service.framework.core.ObjectSerializationToXML;
import com.pft.string.service.framework.core.RequestContext;

public class PFTMultiTenantConnectionProvider implements
		MultiTenantConnectionProvider {

	private static final long serialVersionUID = -403359210555925728L;

	private Map<String, DataSource> tenantDataSources;

	public PFTMultiTenantConnectionProvider() {

		//bindThis();

		this.tenantDataSources = new HashMap<String, DataSource>();

		//List<PFTDataSource> dataSources = readDataSourcesFromFileSystem();
		//tenantDataSources = DataSourcer.getTenantDataSources(dataSources);
		tenantDataSources = DataSourcer.getTenantDataSources();

		// DataSource s1 =
		// DataSourcer.createDataSource(DBServer.Sqlserver,"jdbc:sqlserver://localhost",
		// "sa", "data");
		// DataSource s2 =
		// DataSourcer.createDataSource(DBServer.Sqlserver,"jdbc:sqlserver://localhost",
		// "star", "star");
		// tenantDataSources.put("tenant1", s1);

		// tenantDataSources.put("star", s2);
		// tenantDataSources.put("zee", s2);
	}

	/*private void bindThis() {

		InitialContext ctx = null;
		PFTMultiTenantConnectionProvider connectionProvider = null;

		
			try{
				ctx = InitialContext.doLookup(Constants.MultiTenantConnectionProviderKey);	
				connectionProvider = (PFTMultiTenantConnectionProvider) ctx
						.lookup(Constants.MultiTenantConnectionProviderKey);

			} 
			catch(Exception e)
			{
				try {
					ctx = new InitialContext();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		if (connectionProvider == null) {

			try {
				ctx.bind(Constants.MultiTenantConnectionProviderKey, this);
			} catch (NamingException e) {
				e.printStackTrace();
			}

		} else {
			try {
				ctx.rebind(Constants.MultiTenantConnectionProviderKey, this);
			} catch (NamingException e) {
				e.printStackTrace();
			}
						
		}
	}
*/
	/*
	 * public TestMultiTenantConnectionProvider(Map<String, DataSource>
	 * tenantDataSources) { this.tenantDataSources = new HashMap<String,
	 * DataSource>(); DataSource s1 =
	 * DataSourcer.createDataSource("jdbc:sqlserver://localhost", "sa", "data");
	 * DataSource s2 =
	 * DataSourcer.createDataSource("jdbc:sqlserver://localhost", "star",
	 * "star"); tenantDataSources.put("tenant1", s1);
	 * tenantDataSources.put("star", s2); tenantDataSources.put("zee", s2); }
	 */

	

	public void addNewDatasource(PFTDataSource dataSource,
			String dataSourceFileName) {
		ObjectSerializationToXML serializer = new ObjectSerializationToXML();

		String dataSourceXlmAbsolutePath = com.pft.string.service.framework.core.Constants.DataSourcesDir
				+ "/" + dataSourceFileName;

		try {
			serializer.serializeObjectToXML(dataSourceXlmAbsolutePath,
					dataSource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DataSource source = DataSourcer.createDataSource(
				dataSource.getDbServer(), dataSource.getJdbcURL(),
				dataSource.getDbUserName(), dataSource.getPassWord());

		tenantDataSources.put(dataSource.getTenantId().toString(), source);

	}

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return tenantDataSources.get("1").getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier)
			throws SQLException {
		return tenantDataSources.get(tenantIdentifier).getConnection();
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection)
			throws SQLException {
		connection.close();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}
}
