package com.pft.string.service.framework.data.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.jdbc.connections.internal.ConnectionProviderInitiator;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.Stoppable;

import com.mchange.v2.c3p0.DataSources;

public class C3P0ConnectionProvider implements ConnectionProvider,
		Configurable, Stoppable {
	private DataSource ds;

	private Integer isolation;

	private boolean autocommit;

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {

		return ConnectionProvider.class.equals(unwrapType) ||

		C3P0ConnectionProvider.class.isAssignableFrom(unwrapType) ||

		DataSource.class.isAssignableFrom(unwrapType);

	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void configure(Map props) {
		Properties dbProperties = new Properties();
		dbProperties=DBPropertyLoader.getInstance().getApiProperties();
		props.put("hibernate.connection.url",
				dbProperties.getProperty("url"));
		props.put("hibernate.connection.username",
				dbProperties.getProperty("username"));
		props.put("hibernate.connection.password",
				dbProperties.getProperty("password"));
		

		String jdbcDriverClass = (String) props.get(Environment.DRIVER);

		String jdbcUrl = (String) props.get(Environment.URL);

		Properties connectionProps = ConnectionProviderInitiator
				.getConnectionProperties(props);

		autocommit = ConfigurationHelper.getBoolean(Environment.AUTOCOMMIT,
				props);

		if (jdbcDriverClass == null) {
		}

		else {

			try {

				Class.forName(jdbcDriverClass);

			}

			catch (ClassNotFoundException cnfe) {

				try {

					ReflectHelper.classForName(jdbcDriverClass);

				}

				catch (ClassNotFoundException e) {

					String msg = "";

					throw new HibernateException(msg, e);

				}

			}

		}

		
		  try {
			DataSource unpooled=DataSources.unpooledDataSource(jdbcUrl,
					connectionProps);
		    ds = DataSources.pooledDataSource(unpooled, props);
		
		  }
		 
		  catch (Exception e) {
		  
		  throw new HibernateException("c3p0 connection provider exception: ", e);
		  
		  }
		
		String i = (String) props.get(Environment.ISOLATION);

		if (i == null)
			isolation = null;

		else {
			isolation = Integer.valueOf(i);
		}

	}

	public void close() {

		try {

			DataSources.destroy(ds);

		}

		catch (SQLException sqle) {

		}

	}



	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		// TODO Auto-generated method stub
		if (ConnectionProvider.class.equals(unwrapType)
				|| C3P0ConnectionProvider.class.isAssignableFrom(unwrapType)) {
			return (T) this;
		} else if (DataSource.class.isAssignableFrom(unwrapType)) {
			return (T) ds;
		} else {
			throw new UnknownUnwrapTypeException(unwrapType);
		}

	}

	@Override
	public void stop() {
		close();

	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {

		final Connection c = ds.getConnection();
		if (isolation != null) {
			c.setTransactionIsolation(isolation.intValue());
		}
		if (c.getAutoCommit() != autocommit) {
			c.setAutoCommit(autocommit);
		}
		return c;
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

}