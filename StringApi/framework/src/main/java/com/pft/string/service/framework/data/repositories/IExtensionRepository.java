package com.pft.string.service.framework.data.repositories;

import java.util.List;
import java.util.Map;

public interface IExtensionRepository
{
	void save(Map<String,Object> values);
	
	boolean IsExtented();
	
	Map<String,Object> getData(Long id);
	
	Map<String,Object> getData(Long id,List<String> propertyNames);

	Object GetAttribute(Long id, String propertyName);

	void updateAttribute(Long id, String propertyName, Object value);
}
