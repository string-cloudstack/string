package com.pft.string.service.framework.data.persistence;



import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.pft.string.service.framework.core.ApplicationContext;
import com.pft.string.service.framework.core.Constants;
import com.pft.string.service.framework.core.RequestContext;
import com.pft.string.service.framework.data.connection.DataSourcer;
import com.pft.string.service.framework.data.connection.PFTDataSource;

public class PersistencyService 
{
	    private static Map<String,SessionFactory>  tenantFactory = new HashMap<String,SessionFactory>();
	    public static Session getCurrentSession() throws Exception 
	    {
	    	
	    	Session session = RequestContext.getAttribute(Constants.CurrentSessionKey);
	    	if(session == null)
	    	{
	    		throw new Exception("You are Not in UnitOfWork");
	    		
	    	}
	    	return session;
	    }
	    
	    public static SessionFactory getCurrentSessionFactory() 
	    {
	    	
	    	
	   		  InitialContext ctx;
	   		  SessionFactory factory=null;
	   		ApplicationContext context = RequestContext.getCurrentApplicationContext();
	   			try {
	   				ctx = new InitialContext();
	   				if(context.getFactoryKey() != null)
	   				{
	   					factory = (SessionFactory)ctx.lookup(context.getFactoryKey());
	   				}
	   				else
	   				{
	   				 factory = (SessionFactory)ctx.lookup(Constants.DefaultSessionFactoryJNDILookup);	
	   				}
	   				//getServletContext().setAttribute("sessionFactory", factory);
	   			} catch (NamingException e) {
	   				
	   				e.printStackTrace();
	   			}
	   			return factory;
	   	  
	    }

		public static SessionFactory getCurrentSessionFactory(String factoryKey) {
			InitialContext ctx;
	   		  SessionFactory factory=null;
	   			try {
	   				ctx = new InitialContext();
	   				 factory = (SessionFactory)ctx.lookup(factoryKey);				 
	   				//getServletContext().setAttribute("sessionFactory", factory);
	   			} catch (NamingException e) {
	   				
	   				e.printStackTrace();
	   			}
	   			return factory;
		}
	    
        public static SessionFactory buildSessionFactory(PFTDataSource dataSource)
        {
        	   File file = new File(Constants.ConfigDir+"mappings/"+dataSource.getTenantId().toString());
        	   if(file.exists())
        	   {
        	    Configuration cfg = new Configuration();      	
		    	
		    	cfg.addDirectory(file);	
		    	//cfg.setProperty("hibernate.hbm2ddl.auto","create");
		    	//cfg.setProperty("hibernate.default_entity_mode","dynamic-map");
		    	cfg.setProperty("hibernate.show_sql","true");
		    	cfg.setProperty(Environment.DEFAULT_ENTITY_MODE, EntityMode.MAP.toString());
		    	cfg.setProperty("hibernate.validator.apply_to_ddl","false");
		    	cfg.setProperty("hibernate.listeners.envers.autoRegister","false");
        	    cfg.setProperty("hibernate.connection.username", dataSource.getDbUserName());
        	    cfg.setProperty("hibernate.connection.password",dataSource.getPassWord());			   
        	    cfg.setProperty("hibernate.connection.url",dataSource.getJdbcURL() );        	   
			    switch( dataSource.getDbServer())
			    {
			    case  Sqlserver:
			    	cfg.setProperty("hibernate.dialect","org.hibernate.dialect.SQLServer2008Dialect");
			    	cfg.setProperty("hibernate.connection.driver_class","com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    	 break;
			    case  Mysql:
			    	cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
			    	cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
			    	 break;
			    case  Oracle:
			    	cfg.setProperty("hibernate.connection.driver_class","oracle.jdbc.driver.OracleDriver");
			    	cfg.setProperty("hibernate.dialect","org.hibernate.dialect.OracleDialect");
			    	 break;
			    }
			    
				//cfg.buildSessionFactory();
				
	        	ServiceRegistry	serviceRegistry = new ServiceRegistryBuilder().applySettings(
	        			cfg.getProperties()).buildServiceRegistry();
	        	SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	        	
				return sessionFactory;
        	   }
        	   else
        	   {
        		   return null;
        	   }
        }
        
        public static void rebuildSessionFactory(Long tenantId) throws Exception
        {
        	SessionFactory sessionFactory = tenantFactory.get(tenantId.toString());        	
        	PFTDataSource dataSource = DataSourcer.readDataSourceFromFileSystem(tenantId);
        	if(dataSource != null)
        	{
        		if(sessionFactory != null)
            	{   
        			sessionFactory.close();
            		sessionFactory = null;
            		tenantFactory.remove(tenantId.toString());
            		sessionFactory = buildSessionFactory(dataSource);
            		if(sessionFactory != null)
            		tenantFactory.put(dataSource.getTenantId().toString(), sessionFactory);
            	}
        	}
        	else
        	{
        		throw new Exception("Couldn't find datasource file for tenantId "+tenantId.toString());
        	}
        		
        }
        
        public static SessionFactory getExtensionSessionFactory()
        {
        	ApplicationContext context = RequestContext
    				.getCurrentApplicationContext();
        	SessionFactory sessionFactory = tenantFactory.get(context.getTenantId().toString());
			return sessionFactory;
        }
        
        public static void setTenantFactories()
        {
        	List<PFTDataSource> datasources = DataSourcer.readDataSourcesFromFileSystem();
    		for(PFTDataSource dataSource : datasources)
    		{
    			SessionFactory sessionFactory = buildSessionFactory(dataSource);
    			if(sessionFactory != null && tenantFactory != null)
    			tenantFactory.put(dataSource.getTenantId().toString(), sessionFactory);
    		}
        }
        
        public static void clearTenantFactories()
        {
        	if(tenantFactory != null)
        	{
	        	for(SessionFactory factory : tenantFactory.values())
	    		{
	    			factory.close();
	       		}
        	}
    		tenantFactory = null;
    		 tenantFactory = new HashMap<String,SessionFactory>();
        }
        
        
}
