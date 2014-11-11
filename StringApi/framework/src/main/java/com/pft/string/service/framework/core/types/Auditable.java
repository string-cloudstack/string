package com.pft.string.service.framework.core.types;

import java.util.Date;

public interface Auditable
{
	public Long getCreatedBy();


	public void setCreatedBy(Long createdBy);


	public Date getCreatedOn() ;


	public void setCreatedOn(Date createdOn);
;

	public Long getModifiedBy() ;


	public void setModifiedBy(Long modifiedBy) ;


	public Date getModifiedOn() ;


	public void setModifiedOn(Date modifiedOn);


}
