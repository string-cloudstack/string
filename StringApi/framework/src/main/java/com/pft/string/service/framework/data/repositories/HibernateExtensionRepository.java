package com.pft.string.service.framework.data.repositories;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.pft.string.service.framework.data.persistence.PersistencyService;

public class HibernateExtensionRepository implements IExtensionRepository
{
	private String entityName;

	public HibernateExtensionRepository(String entityName)
	{
		this.entityName = entityName;
	}
	
	@Override
	public void save(Map<String, Object> values)
	{
		Session session = PersistencyService.getExtensionSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
		session.saveOrUpdate(entityName, values);
		session.flush();
		transaction.commit();
		}
		catch(Exception ex)
		{
			transaction.rollback();
			ex.printStackTrace();
			throw ex;
		}
		
		session.close();
	}

	@Override
	public Map<String, Object> getData(Long id) {
		Session session = PersistencyService.getExtensionSessionFactory().openSession();
		Map<String, Object> values = (Map<String, Object>) session.get(entityName, id);
		session.close();
		return values;
	}

	@Override
	public Map<String, Object> getData(Long id, List<String> propertyNames)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public boolean IsExtented() 
	{
		SessionFactory sessionFactory = PersistencyService.getExtensionSessionFactory();
		boolean result = false;
		if(sessionFactory != null)
		{
			if(sessionFactory.getClassMetadata(entityName) != null)
				result = true;
		}
		return result;
	}
	

	@Override
	public Object GetAttribute(Long id,String propertyName) 
	{
		SessionFactory sessionFactory = PersistencyService.getExtensionSessionFactory();
		Session session = sessionFactory.openSession();
		Object result = null;
		if(sessionFactory != null)
		{
			if(sessionFactory.getClassMetadata(entityName) != null)
			{
				String[] propNames= sessionFactory.getClassMetadata(entityName).getPropertyNames();
				for(String prop : propNames)
				{
					if(prop.equals(propertyName))
					{
						Criteria crit =session.createCriteria(entityName);
						crit.add(Restrictions.eq("id", id));
						crit.setProjection(Projections.property(propertyName));
					     result = crit.uniqueResult();
						
					}
					
				}
			}
		}
		session.close();
		return result;
	}

	public void updateAttribute(Long id, String propertyName, Object value) {
		String hQuery = "update " + entityName + " set ";
		hQuery = hQuery + propertyName + " =  '" + value
				+ "' where id = " + id;
		SessionFactory sessionFactory = PersistencyService.getExtensionSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
		Query query=session.createQuery(hQuery);
		query.executeUpdate();		
		session.flush();
		transaction.commit();
		}
		catch(Exception ex)
		{
			transaction.rollback();
			ex.printStackTrace();
			throw ex;
		}
		
		session.close();
		
	}

	

}
