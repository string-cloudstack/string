package com.pft.string.service.api.commonutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class PropertyLoader {
	 private static PropertyLoader instance = null;
	 private Properties apiProperty = null ;
	 private Properties stbCommonProperty = null ;
	 private Properties stbAlfProperty = null ;
	 private Properties stbESProperty = null ;
	 
	private PropertyLoader() {
		apiProperty = new Properties();
		InputStream settingsStream = null;

		settingsStream = PropertyLoader.class.getClassLoader().getResourceAsStream("daxapiconfig.properties");
		try {
			apiProperty.load(settingsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stbCommonProperty = new Properties();
		InputStream stbSettingsStream = null;

		stbSettingsStream = PropertyLoader.class.getClassLoader().getResourceAsStream("stb_common_config.properties");
		try {
			stbCommonProperty.load(stbSettingsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		stbAlfProperty = new Properties();
		InputStream stbAlfSettingsStream = null;

		stbAlfSettingsStream = PropertyLoader.class.getClassLoader().getResourceAsStream("stb_alf_config.properties");
		try {
			stbAlfProperty.load(stbAlfSettingsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		stbESProperty = new Properties();
		InputStream stbESSettingsStream = null;

		stbESSettingsStream = PropertyLoader.class.getClassLoader().getResourceAsStream("stb_es_config.properties");
		try {
			stbESProperty.load(stbESSettingsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	} 

	public static PropertyLoader getInstance(){
		
			instance = new PropertyLoader() ;
		
		return instance ;
	}
	
	public  Properties getApiProperties()  {
		return apiProperty;
	}
	
	public Properties getSTBCommonProperties()  {
				return stbCommonProperty;
	}
	public Properties getSTBAlfProperties()  {
		return stbAlfProperty;
    }
	public Properties getSTBESProperties()  {
		return stbESProperty;
    }
	
	
}
