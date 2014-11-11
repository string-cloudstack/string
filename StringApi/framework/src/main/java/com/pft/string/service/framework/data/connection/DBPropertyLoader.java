package com.pft.string.service.framework.data.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class DBPropertyLoader {
	private static DBPropertyLoader instance = null;
	 private Properties apiDBProperty = null ;
	 
	private DBPropertyLoader() {
		apiDBProperty = new Properties();
		InputStream settingsStream = null;

		settingsStream = DBPropertyLoader.class.getClassLoader().getResourceAsStream("apidatabase.properties");
		try {
			apiDBProperty.load(settingsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	} 

	public static DBPropertyLoader getInstance(){
		if(instance == null)
			instance = new DBPropertyLoader() ;
		
		return instance ;
	}
	
	public  Properties getApiProperties()  {
		return apiDBProperty;
	}
	
}
