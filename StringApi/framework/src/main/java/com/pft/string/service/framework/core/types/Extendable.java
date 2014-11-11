package com.pft.string.service.framework.core.types;

import java.util.Map;

public interface Extendable
{
	void SaveExtensionData();
	Map<String,Object> GetExtensionData();
	void SetExtensionData(Map<String,Object> extensionData);
	void LoadExtensionData();
	void SetExtensionEntityName(String entityName);
	


}
