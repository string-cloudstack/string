package com.pft.string.service.framework.data.persistence;

import org.hibernate.Transaction;

public class TransactionManager 
{
	private Transaction transaction = null;
	public void startNew() 
	{
	
	
	try {
		 transaction = PersistencyService.getCurrentSession().beginTransaction();
	} catch (Exception e) {
		
		e.printStackTrace();		
	}
	}
	
	public void commit() 
	{	
		if(!transaction.wasCommitted())
		 transaction.commit();
	
	}
	
	
	public void rollBack() 
	{	
		transaction.rollback();
	   
	}


}
