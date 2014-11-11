package com.pft.string.service.api.common.resteasy;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;



/**
 * @author schand07
 *
 */
public class RestEasyClientResponse{
	
	public static <T> ClientResponse<String> get(String restURL,Map<String,String> HeaderMap) throws Exception
	{
		ClientResponse<String> clientResponse = getClientRequest(restURL,HeaderMap).get(String.class);
		return clientResponse;
	}
	
	public static <T> ClientResponse<String> post(String restURL,Map<String,String> HeaderMap,Object obj) throws Exception
	{
		String json;
		json = JacksonObjectParser.parseString(obj);
		ClientRequest clientRequest = getClientRequest(restURL,HeaderMap);
		clientRequest.body(MediaType.APPLICATION_JSON, json);
		ClientResponse<String> clientResponse =clientRequest.post(String.class);
		return clientResponse;
	}
	public static <T> ClientResponse<String> post(String restURL,Map<String,String> HeaderMap,String json) throws Exception
	{
		//String json;
		//json = JacksonObjectParser.parseString(obj);
		ClientRequest clientRequest = getClientRequest(restURL,HeaderMap);
		clientRequest.body(MediaType.APPLICATION_JSON, json);
		ClientResponse<String> clientResponse =clientRequest.post(String.class);
		return clientResponse;
	}
	public static <T> ClientResponse<String> post(String restURL,Map<String,String> HeaderMap,String obj,String mediaType,boolean jsonType) throws Exception
	{
		ClientRequest clientRequest = getClientRequest(restURL,HeaderMap);
		if(jsonType){
			clientRequest.body(mediaType, JacksonObjectParser.parseString(obj));
		}else{
			clientRequest.body(mediaType, obj);
		}
		ClientResponse<String> clientResponse =clientRequest.post(String.class);
		return clientResponse;
	}
	public static <T> ClientResponse<String> delete(String restURL,Map<String,String> HeaderMap, Object obj) throws Exception
	{
		//webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class)
		String json;
		json = JacksonObjectParser.parseString(obj);
	    ClientRequest clientRequest = getClientRequest(restURL,HeaderMap);
		clientRequest.body(MediaType.APPLICATION_JSON, json);
		ClientResponse<String> clientResponse =clientRequest.delete(String.class);
		return clientResponse;
	}
	
	public static <T> ClientResponse<String> put(String restURL, 
			Map<String,String> HeaderMap, Object obj) throws Exception
	{
		//webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class)
		ClientRequest req = getClientRequest(restURL,HeaderMap);
		
		String jsonStr = JacksonObjectParser.parseString(obj);
		req.body(MediaType.APPLICATION_JSON, jsonStr);
		ClientResponse<String> clientResponse = req.put();
		return clientResponse;
	}
	
	
	public static ClientRequest getClientRequest(String restURL,Map<String,String> HeaderMap)
	{
		 ClientRequest clientRequest = new ClientRequest(restURL);
		 for (Entry<String, String> entry : HeaderMap.entrySet()){
			 clientRequest.header(entry.getKey(),entry.getValue());
		 }
		 return clientRequest;
	}
	
}
