package com.pft.string.service.api.common.resteasy;

/**
 * @author schand07
 *
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JacksonObjectParser {
	
	@SuppressWarnings("unchecked")
	public static <T> T parseObject(final String json,Class<T> classType) throws Exception
	{
		if(json.isEmpty()==false)
		{
			T retVal = null;
			try {
					if(json.getClass().equals(classType))
						retVal = (T) json;
					else
						retVal = getObjectMapper().readValue(json, classType);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			return retVal;
		}
		else
		{
			throw new Exception("INVALID JSON");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T parseObjectUsingFile(String absoluteFilePath,Class<T> classType) throws Exception
	{
		T retVal = null;
		File inputFile = new File(absoluteFilePath);
		String json = FileUtils.readFileToString(inputFile);
		if(inputFile.exists())
		{
			if(json.isEmpty()==false)
			{
				retVal = classType.newInstance();
				try {
					if(json.getClass().equals(classType))
						retVal = (T) json;
					else
						retVal = getObjectMapper().readValue(json, classType);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				throw new Exception("INVALID JSON");
			}
		}
		else
			throw new Exception("FILE NOT FOUND");
		return retVal;
	}
	
	public static String parseString(Object obj) throws Exception
	{
		String retval;
		if(obj instanceof String)
			retval = (String) obj;
		else
			retval = getObjectMapper().writeValueAsString(obj);
		return retval;
	}
	
	public static ObjectMapper getObjectMapper()
	{
		AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(primary, secondary);

		ObjectMapper mapper = new ObjectMapper()
 			.configure(SerializationConfig.Feature.USE_ANNOTATIONS, true)
 			.configure(DeserializationConfig.Feature.USE_ANNOTATIONS, true)
 			.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
 
		mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
		mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
		return mapper;
	}

}
