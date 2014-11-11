package com.pft.string.service.api.commonutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	Utils() {
	}

	// To Check String is null or empty ---start
	public static boolean isStringEmpty(String input) {
		if (input != null && input.length() != 0 && input != "") {
			return true;
		}
		return false;
	}
	
	// To Check String is null or empty ---start
	public static String GetPathFromConfig(String key, String relativeConfigDir, String congiFileName)
	{
		final String ConfigDir = System.getProperty("jboss.server.base.dir") + relativeConfigDir;
	
		String	returnValue ="";
	 
       try
       {
       	Properties props = new Properties();       	
       	InputStream inputStream = new FileInputStream(ConfigDir+congiFileName);
       	/* load the xml file into properties format */
       	props.loadFromXML(inputStream);
    
       	returnValue = props.getProperty(key);
    
       	System.out.println("returnValue:"+returnValue);
       	
       	
       } catch (FileNotFoundException e)
       {
    	   e.printStackTrace();
       } catch (IOException e)
       {
    	   e.printStackTrace();
       }
       
       return returnValue;
	}	
}
