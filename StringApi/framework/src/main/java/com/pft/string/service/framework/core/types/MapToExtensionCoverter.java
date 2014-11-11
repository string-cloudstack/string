package com.pft.string.service.framework.core.types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;



public class MapToExtensionCoverter
{	
	public static ExtensionDataCarrier[] getextensionDataFromMap(Map<String,Object> map)
	{
		ExtensionDataCarrier[] result=null;	
	   if(map != null)
	   {
		List<ExtensionDataCarrier > data = new  ArrayList<ExtensionDataCarrier>();
		for(String key :map.keySet())
		{
			if(map.get(key) != null)
			{
			ExtensionDataCarrier carrier  = new ExtensionDataCarrier();
			carrier.setPropertyName(key);			
					
			    switch(map.get(key).getClass().getName())
			    {
			    
			    case "java.lang.Integer" :  
			    	carrier.setPropertyType(PropertyType.Integer);
			    	carrier.setPropertyValue(map.get(key).toString());	
			        break;
			    case "java.lang.Long" :
			    	carrier.setPropertyType(PropertyType.Long);
			    	carrier.setPropertyValue(map.get(key).toString());	
		        break;
			    case "java.lang.Boolean" :
			    	carrier.setPropertyType(PropertyType.Boolean);
			    	carrier.setPropertyValue(map.get(key).toString());	
		        break;
			    case "java.util.Date" :			    		    	
			    	carrier.setPropertyType(PropertyType.Date);			    
			    	carrier.setPropertyValue(ConvertDateObjectToString(map.get(key)));
		        break;
			    case "java.sql.Timestamp" :	    		    	
			    	carrier.setPropertyType(PropertyType.Date);			    
			    	carrier.setPropertyValue(ConvertDateObjectToString(map.get(key)));
		        break;
			    case "java.sql.Time" :	    		    	
			    	carrier.setPropertyType(PropertyType.Date);			    
			    	carrier.setPropertyValue(ConvertDateObjectToString(map.get(key)));
		        break;
			    case "java.sql.Date" :	    		    	
			    	carrier.setPropertyType(PropertyType.Date);			    
			    	carrier.setPropertyValue(ConvertDateObjectToString(map.get(key)));
		        break;
		        default:
		        carrier.setPropertyType(PropertyType.String);
		        carrier.setPropertyValue(map.get(key).toString());
		        break;
			    }
			    data.add(carrier);
			}
			
		}
		 result = new ExtensionDataCarrier[data.size()];
		  result =  data.toArray(result);
	   }
	 
		return result;
		
	}
	
	private static String ConvertDateObjectToString(Object key)
	{
		DateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");	
		return dateFormat.format(key);
	}
	
	
	public static Map<String, Object> getextensionDataFromMap(
			ExtensionDataCarrier[] carriers) {
		Map<String, Object> result = null;

		if (carriers != null) {
			result = new HashMap<String, Object>();
			for (ExtensionDataCarrier carrier : carriers) {
				if (carrier.getPropertyName() != null) {

					switch (carrier.getPropertyType()) {
					case Integer:
						Integer intData = Integer.valueOf(carrier
								.getPropertyValue());
						if (intData >= 0 && intData != null)
							result.put(carrier.getPropertyName(), intData);
						break;
					case Long:
						Long longData = Long
								.valueOf(carrier.getPropertyValue());
						if (longData >= 0 && longData != null)
							result.put(carrier.getPropertyName(), longData);
						break;
					case Boolean:
						Boolean boolData = Boolean.valueOf(carrier
								.getPropertyValue());
						if (boolData != null)
							result.put(carrier.getPropertyName(), boolData);
						break;
					case Date:
						Date date = null;
						try {
							DateFormat dateFormat = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");

							date = dateFormat.parse(carrier.getPropertyValue());
							// date = new
							// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(carrier.getPropertyValue());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (date != null)
							result.put(carrier.getPropertyName(), date);
						break;
					default:
						if (StringUtils.isNotBlank(carrier.getPropertyValue()))
							result.put(carrier.getPropertyName(),
									carrier.getPropertyValue());
						break;
					}
				}
			}
		}
		return result;

	}
}
