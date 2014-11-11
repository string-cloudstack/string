package com.pft.string.service.framework.core;

import java.util.HashMap;
import java.util.Map;

public class RequestContext 
{
	private static InheritableThreadLocal<Map<String, Object>> attributes = new InheritableThreadLocal<>();
	
	
    public static void initialize() 
    {
        attributes.set(new HashMap<String,Object>());
    }
    public static void cleanup() 
    {
        attributes.remove();
        
    }
    @SuppressWarnings("unchecked")
	public static <T> T getAttribute(String key) {
        return (T) attributes.get().get(key);
    }
    
    public static void setAttribute(String key, Object value) {
        attributes.get().put(key, value);
    }
    
    public static ApplicationContext getCurrentApplicationContext() {
        return (ApplicationContext) attributes.get().get(Constants.ApplicationContext);
    }
}