package com.pft.string.service.framework.business.base.entities;

import java.util.Date;

import com.pft.string.service.framework.core.types.Auditable;
import com.pft.string.service.framework.core.types.DeleteMarker;

public class ValueObject extends ValueBase implements Auditable,DeleteMarker
{
	private boolean isDeleted = false;   
     
	private int objectVersion;
     
     private Long createdBy ;

     
     private Date createdOn ;

    
     private Long  modifiedBy ;

    
     private Date modifiedOn;     

     
     public Long getCreatedBy() {
 		return createdBy;
 	}


 	public void setCreatedBy(Long createdBy) {
 		this.createdBy = createdBy;
 	}


 	public Date getCreatedOn() {
 		return createdOn;
 	}


 	public void setCreatedOn(Date createdOn) {
 		this.createdOn = createdOn;
 	}


 	public Long getModifiedBy() {
 		return modifiedBy;
 	}


 	public void setModifiedBy(Long modifiedBy) {
 		this.modifiedBy = modifiedBy;
 	}


 	public Date getModifiedOn() {
 		return modifiedOn;
 	}


 	public void setModifiedOn(Date modifiedOn) {
 		this.modifiedOn = modifiedOn;
 	}

 	public boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public int getObjectVersion() {
		return objectVersion;
	}


	public void setObjectVersion(int objectVersion) {
		this.objectVersion = objectVersion;
	}
  

}
