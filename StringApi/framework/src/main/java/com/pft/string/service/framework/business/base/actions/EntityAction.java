package com.pft.string.service.framework.business.base.actions;

import com.pft.string.service.framework.business.annotations.Transaction;
import com.pft.string.service.framework.data.persistence.TransactionManager;

public abstract class EntityAction<T> 
{
	private boolean needsTransaction = false;
	
	private T entity= null;
	
	private final Class clazz;
	
	@SuppressWarnings("unused")
	private EntityAction()
	{
		clazz = null;
		
	}

	public EntityAction(Class classInfo)
	{
		clazz = classInfo;
		
		findTransactionSupport();
	}
	

	private void findTransactionSupport()
	{
		Transaction transaction = null;
		if(clazz.getAnnotation(Transaction.class) != null)
		{
			transaction = (Transaction) clazz.getAnnotation(Transaction.class);
		}
		if(transaction != null)
		needsTransaction = transaction.Supports();
	}


	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;		
	}
	
	 protected abstract void OnExecute() throws  Exception;
	 

     public  void Execute() throws Exception
     {
    	if(needsTransaction)
    	{
    		TransactionManager manager = new TransactionManager();
    		try
    		{
    			
    			manager.startNew();
    			OnExecute();
    			manager.commit();
    		}catch (Exception e) {
    			
    			e.printStackTrace();
    			manager.rollBack();
    			throw e;
    		}
    	}
    	else
    		{
	    		try
	    		{
	    		  	OnExecute();
	    			
	    		}
	    		catch (Exception e)
	    		{
	    			
	    			System.out.print(e.getMessage());
	    			e.printStackTrace();
	    			throw e;
	    		}
    		}
    		
    		
    	}

	protected void OnUpdateUsers() throws Exception {
		// TODO Auto-generated method stub
		
	}
    

}
