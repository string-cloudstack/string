package com.pft.string.service.framework.business.base.entities;

import java.util.Map;

import com.pft.string.service.framework.core.types.DeleteMarker;
import com.pft.string.service.framework.core.types.Extendable;
import com.pft.string.service.framework.data.repositories.HibernateExtensionRepository;

public class EntityBase implements DeleteMarker,Extendable
{
	private long id ;

	private int objectVersion;
	
	private boolean isDeleted = false;    

	private String extEntityName;
	private Map<String, Object> extensionData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public int getObjectVersion() {
		return objectVersion;
	}


	public void setObjectVersion(int version) {
		this.objectVersion = version;
	}


	public boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	public void SaveExtensionData() {
		
		extensionData.put("id",id);
		if(extEntityName != null)
		{
			new HibernateExtensionRepository(extEntityName).save(extensionData);
			
		}else
		{
		new HibernateExtensionRepository(this.getClass().getSimpleName()+"EXTN").save(extensionData);
		}
	}

	
	public void LoadExtensionData() 
	{
		if(extEntityName != null)
		{
			this.extensionData = new HibernateExtensionRepository(extEntityName).getData(id);
			
		}
		else
		{
		if(new HibernateExtensionRepository(this.getClass().getSimpleName()+"EXTN").IsExtented())
		this.extensionData = new HibernateExtensionRepository(this.getClass().getSimpleName()+"EXTN").getData(id);
		}
	}

	
	public void SetExtensionData(Map<String, Object> extensionData) {
		this.extensionData = extensionData;
		
	}

	
	public Map<String, Object> GetExtensionData() {
		
		return extensionData;
	}

	
	public void SetExtensionEntityName(String entityName) {
		this.extEntityName=entityName;
		
	}



}
