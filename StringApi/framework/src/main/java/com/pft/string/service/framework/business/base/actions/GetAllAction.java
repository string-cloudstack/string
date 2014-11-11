package com.pft.string.service.framework.business.base.actions;

import java.util.List;

import com.pft.string.service.framework.business.annotations.Transaction;
import com.pft.string.service.framework.core.ApplicationContext;
import com.pft.string.service.framework.core.RequestContext;
import com.pft.string.service.framework.core.types.PagingCriteria;
import com.pft.string.service.framework.data.repositories.Repository;

@Transaction(Supports=false)
public abstract class GetAllAction<T> extends EntityAction<T>
{
	protected List<T> items;
	
	protected Long userId = RequestContext.getCurrentApplicationContext().getUserId();
	
	protected Long totalItemCount;
	
	private int firstResult;
	
	protected PagingCriteria pagingCriteria;
	 

	public GetAllAction(Class classInfo) {
		super(classInfo);
		
	}
	
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	
	

	public PagingCriteria getPagingCriteria() {
		return pagingCriteria;
	}

	public void setPagingCriteria(PagingCriteria pagingCriteria)
	{
		if(pagingCriteria  != null)
		{
			if (pagingCriteria.getMaxResults() == 0)		
        	pagingCriteria.setMaxResults(50);
        if (pagingCriteria.getPageNo() > 0)
        	pagingCriteria.setPageNo(pagingCriteria.getPageNo()-1);
		}
        
        
        
		this.pagingCriteria = pagingCriteria;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(Long totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getFirstResult() 
	{
		//Find first result.
		if(pagingCriteria != null)
        firstResult = (pagingCriteria.getMaxResults() * pagingCriteria.getPageNo());
		return firstResult;
	}	
	
	

}
