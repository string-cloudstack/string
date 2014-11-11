package com.pft.string.service.framework.data.repositories;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.ResultTransformer;

import com.pft.string.service.framework.core.DeepCopy;
import com.pft.string.service.framework.core.types.DeleteMarker;
import com.pft.string.service.framework.core.types.Extendable;
import com.pft.string.service.framework.data.persistence.PersistencyService;
import com.pft.string.service.framework.data.types.Parameter;

public class Repository<T> implements IRepository<T> {
	public Repository(Class classInfo) {
		clazz = classInfo;
	}

	private final Class clazz;

	private static Session getSession() {
		try {
			return PersistencyService.getCurrentSession();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public void attach(T entity) {
		getSession().update(entity);

	}

	@Override
	public void detach(T entity) {
		getSession().evict(entity);

	}

	@Override
	public void add(T entity) {
		getSession().save(entity);
		if (entity instanceof Extendable) {
			if (((Extendable) entity).GetExtensionData() != null) {
				((Extendable) entity).SaveExtensionData();
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEnity(Object id) {
		boolean addDeleteCriteria = false;

		try {
			clazz.asSubclass(DeleteMarker.class);
			addDeleteCriteria = true;
		} catch (ClassCastException ex) {

		}
		if (addDeleteCriteria)
			return findOne(Restrictions.and(Restrictions.eq("id", id),
					Restrictions.eq("isDeleted", false)));
		else
			return ((T) getSession().get(clazz, (Serializable) id));

	}

	@SuppressWarnings("unchecked")
	@Override
	public T getGlobalEnity(Object id) {
		return ((T) getSession().get(clazz, (Serializable) id));

	}

	@SuppressWarnings("unchecked")
	@Override
	public T loadEntity(Object id) {
		// TODO Auto-generated method stub
		return ((T) getSession().load(clazz, (Serializable) id));
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
		getSession().flush();
	}

	@Override
	public void save(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
		if (entity instanceof Extendable) {
			if (((Extendable) entity).GetExtensionData() != null) {
				((Extendable) entity).SaveExtensionData();
			}

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Order order, Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		crit.addOrder(order);
		return crit.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria criteria, Order... orders) {
		Criteria executableCriteria = getExecutableCriteria(criteria, orders);
		return executableCriteria.list();
	}

	@Override
	public List<T> findAll(DetachedCriteria criteria, Integer firstResult,
			Integer maxResults, Order... orders) {

		Criteria executableCriteria = getExecutableCriteria(criteria, orders);
		executableCriteria.setFirstResult(firstResult);
		executableCriteria.setMaxResults(maxResults);
		return executableCriteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Order[] orders, Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		for (Order order : orders) {
			crit.addOrder(order);
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Integer firstResult, Integer numberOfResults,
			Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		crit.setFirstResult(firstResult).setMaxResults(numberOfResults);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Integer firstResult, Integer numberOfResults,
			Order selectionOrder, Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		crit.setFirstResult(firstResult).setMaxResults(numberOfResults);
		crit.addOrder(selectionOrder);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Integer firstResult, Integer numberOfResults,
			Order[] selectionOrder, Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		crit.setFirstResult(firstResult).setMaxResults(numberOfResults);
		for (Order order : selectionOrder) {
			crit.addOrder(order);
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String namedQuery, Boolean isSQL,
			ResultTransformer resultTransformer, Parameter... parameters) {
		Query query = null;
		if (!isSQL) {
			query = createQuery(namedQuery, parameters, 0);

		} else {
			query = createNamedSqlQuery(namedQuery, parameters, 0);
		}
		if (resultTransformer != null) {
			query = query.setResultTransformer(resultTransformer);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Integer firstResult, Integer numberOfResults,
			String namedQuery, Parameter... parameters) {
		Query query = createQuery(namedQuery, parameters, 0);
		query.setFirstResult(firstResult).setMaxResults(numberOfResults);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String sqlQuery, Integer firstResult,
			Integer maxResults, ResultTransformer resultTransformer,
			Parameter... parameters) {
		SQLQuery query = createSQLQuery(sqlQuery, parameters);
		query.setResultTransformer(resultTransformer);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public T findOne(Criterion... criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		return (T) crit.uniqueResult();

	}

	@Override
	public T findOne(DetachedCriteria criteria) {
		Criteria executableCriteria = getExecutableCriteria(criteria, null);
		return (T) executableCriteria.uniqueResult();
	}

	@Override
	public T findOne(String namedQuery, Parameter... parameters) {
		Query query = null;
		try {
			query = createQuery(namedQuery, parameters, 0);
		} catch (MappingException ex) {
			query = createNamedSqlQuery(namedQuery, parameters, 0);
		}

		return (T) query.uniqueResult();
	}

	@Override
	public T findOne(String namedQuery, Integer timeOutPeriod,
			Parameter... parameters) {
		Query query = null;
		try {
			query = createQuery(namedQuery, parameters, timeOutPeriod);
		} catch (MappingException ex) {
			query = createNamedSqlQuery(namedQuery, parameters, timeOutPeriod);
		}

		return (T) query.uniqueResult();
	}

	@Override
	public T findFirst(DetachedCriteria criteria, Order... orders) {
		Criteria executableCriteria = getExecutableCriteria(criteria, orders);
		executableCriteria.setFirstResult(0);
		executableCriteria.setMaxResults(1);
		return (T) executableCriteria.uniqueResult();
	}

	@Override
	public void refresh(T entity) {
		getSession().refresh(entity);

	}

	@Override
	public Object getId(T entity) {
		return getSession().getIdentifier(entity);
	}

	@Override
	public Object getAttribute(DetachedCriteria criteria, String propertyName) {

		Criteria executableCriteria = getExecutableCriteria(criteria, null);
		criteria.setProjection(Projections.property(propertyName));
		Object result = executableCriteria.uniqueResult();
		return result;

	}

	@Override
	public Object getProjectedResult(DetachedCriteria criteria,
			Projection projection) {

		Criteria executableCriteria = getExecutableCriteria(criteria, null);
		criteria.setProjection(projection);
		Object result = executableCriteria.uniqueResult();
		return result;

	}

	@Override
	public Object getAttribute(Class classInfo, Object id, String propertyName) {
		Criteria crit = getSession().createCriteria(clazz);
		crit.add(Restrictions.eq("id", id));
		crit.setProjection(Projections.property(propertyName));
		Object result = crit.uniqueResult();
		return result;

	}

	@Override
	public Object GetAttribute(String extEntityName, Long id,
			String propertyName) {
		SessionFactory sessionFactory = PersistencyService
				.getCurrentSessionFactory();
		Object result = null;
		boolean found = false;
		if (sessionFactory != null) {
			if (sessionFactory.getClassMetadata(clazz.getName()) != null) {
				String[] propNames = sessionFactory.getClassMetadata(
						clazz.getName()).getPropertyNames();
				for (String prop : propNames) {
					if (prop.equals(propertyName)) {
						found = true;

					}

				}

				if (found) {
					Criteria crit = getSession().createCriteria(clazz);
					crit.add(Restrictions.eq("id", id));
					crit.setProjection(Projections.property(propertyName));
					result = crit.uniqueResult();

				} else {
					if (extEntityName != null) {
						result = new HibernateExtensionRepository(extEntityName)
								.GetAttribute(id, propertyName);
					} else {
						result = new HibernateExtensionRepository(
								clazz.getSimpleName() + "EXTN").GetAttribute(
								id, propertyName);
					}
				}

			}

		}
		return result;
	}

	@Override
	public void UpdateAttribute(String extEntityName, Long id,
			String propertyName, Object value) {
		SessionFactory sessionFactory = PersistencyService
				.getCurrentSessionFactory();
		boolean found = false;
		if (sessionFactory != null) {
			if (sessionFactory.getClassMetadata(clazz.getName()) != null) {
				String[] propNames = sessionFactory.getClassMetadata(
						clazz.getName()).getPropertyNames();
				for (String prop : propNames) {
					if (prop.equals(propertyName)) {
						found = true;

					}

				}

				if (found) {
					String hQuery = "update " + clazz.getName() + " set ";
					hQuery = hQuery + propertyName + " =  '" + value
							+ "' where id = " + id;
					update(hQuery, false);
				} else {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", id);
					map.put(propertyName, value);
					if (extEntityName != null) {

						new HibernateExtensionRepository(extEntityName)
								.updateAttribute(id, propertyName, value);

					} else {

						new HibernateExtensionRepository(clazz.getSimpleName()
								+ "EXTN").updateAttribute(id, propertyName,
								value);
					}
				}

			}

		}
	}

	@Override
	public Long getCount() {

		Criteria crit = getSession().createCriteria(clazz).setProjection(
				Projections.countDistinct("id"));
		Long result = Long.parseLong(crit.uniqueResult().toString());
		return result;
	}

	@Override
	public Long getCount(Criterion[] criteria) {
		Criteria crit = createCriteriaFromArray(criteria);
		// crit.SetProjection(Projections.RowCount());
		crit.setProjection(Projections.countDistinct("id"));
		Long result = Long.parseLong(crit.uniqueResult().toString());
		return result;
	}

	@Override
	public Long getCount(DetachedCriteria criteria) {
		DetachedCriteria criteriaCopy = null;
		try {
			criteriaCopy = DeepCopy.Copy(criteria);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Criteria executableCriteria = getExecutableCriteria(criteriaCopy, null);
		executableCriteria.setProjection(Projections.countDistinct("id"));
		Long result = Long.parseLong(executableCriteria.uniqueResult()
				.toString());
		return result;
	}

	@Override
	public Long getCount(DetachedCriteria criteria, boolean isDistinctRequired) {
		DetachedCriteria criteriaCopy = null;
		try {
			criteriaCopy = DeepCopy.Copy(criteria);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Criteria executableCriteria = getExecutableCriteria(criteriaCopy, null);
		if (isDistinctRequired)
			executableCriteria.setProjection(Projections.countDistinct("id"));
		else
			;
		executableCriteria.setProjection(Projections.count("id"));

		Long result = Long.parseLong(executableCriteria.uniqueResult()
				.toString());
		return result;
	}

	@Override
	public List<Long> getObjectIdArray(DetachedCriteria criteria) {
		Criteria executableCriteria = getExecutableCriteria(criteria, null);
		criteria.setProjection(Projections.id());
		List<Long> result = executableCriteria.list();
		return result;
	}

	@Override
	public void enableFilter(String filterName, String paramName,
			Object paramValue) {
		if (getSession().getEnabledFilter(filterName) == null) {
			getSession().enableFilter(filterName).setParameter(paramName,
					paramValue);
		}

	}

	@Override
	public void disableFilter(String filterName) {
		if (getSession().getEnabledFilter(filterName) != null) {
			getSession().disableFilter(filterName);
		}

	}

	@Override
	public List<T> findAll(DetachedCriteria criteria, Integer firstResult,
			Integer maxResults, ResultTransformer resultTransformer,
			Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		getSession().clear();

	}

	@Override
	public void purgeAll() {
		String entityType = clazz.getSimpleName();
		getSession().delete("select alias FROM " + entityType + " as alias");// HQL
																				// query

		getSession().flush();

	}

	@Override
	public Integer update(String query, boolean isSql, Parameter... parameters) {
		if (isSql) {
			SQLQuery sqlQuery = createSQLQuery(query, parameters);
			return sqlQuery.executeUpdate();
		}

		else {
			Query hqlQuery = createQuery(query, parameters, 0);
			return hqlQuery.executeUpdate();
		}
	}

	@Override
	public List getDatabySql(String query, boolean isSql,
			Parameter... parameters) {

		if (isSql) {
			SQLQuery sqlQuery = createSQLQuery(query, parameters);
			return sqlQuery.list();
		}

		else {
			Query hqlQuery = createQuery(query, parameters, 0);
			return hqlQuery.list();
		}
	}

	private Criteria createCriteriaFromArray(Criterion[] criteria) {
		Criteria crit = getSession().createCriteria(clazz);
		for (Criterion criterion : criteria) {
			// allow some fancy antics like returning possible return
			// or null to ignore the criteria
			if (criterion == null)
				continue;
			crit.add(criterion);
		}
		addCaching(crit);
		return crit;
	}

	private static Criteria getExecutableCriteria(DetachedCriteria criteria,
			Order[] orders) {
		Criteria executableCriteria = criteria
				.getExecutableCriteria(getSession());

		addCaching(executableCriteria);
		if (orders != null) {
			for (Order order : orders) {
				executableCriteria.addOrder(order);
			}
		}
		return executableCriteria;
	}

	private static Criteria getExecutableCriteria(DetachedCriteria criteria,
			Order[] orders, Session session) {
		Criteria executableCriteria = criteria.getExecutableCriteria(session);

		addCaching(executableCriteria);
		if (orders != null) {
			for (Order order : orders) {
				executableCriteria.addOrder(order);
			}
		}
		return executableCriteria;
	}

	private static void addCaching(Criteria crit) {

		crit.setCacheable(true);

	}

	private static SQLQuery createSQLQuery(String sqlQuery,
			Parameter[] parameters) {
		SQLQuery query = getSession().createSQLQuery(sqlQuery);
		if (parameters != null) {
			for (Parameter parameter : parameters) {
				if (parameter.getType() == null)
					query.setParameter(parameter.getName(),
							parameter.getValue());

				else
					query.setParameter(parameter.getName(), parameter,
							parameter.getType());
			}
		}
		addCaching(query);
		// query.setCacheable(true);
		return query;

	}

	// private static Query createQuery(String namedQuery, Parameter[]
	// parameters)
	// {
	// Query query = getSession().getNamedQuery(namedQuery);
	// if (parameters != null)
	// {
	// for(Parameter parameter : parameters)
	// {
	// //if (parameter.Type == null)
	// query.setParameter(parameter.getName(), parameter);
	// //else
	// // query.SetParameter(parameter.Name, parameter.Value, parameter.Type);
	// }
	// }
	//
	// addCaching(query);
	// return query;
	//
	//
	// }

	private static Query createQuery(String namedQuery, Parameter[] parameters,
			int timeOutPeriod) {
		// Query query = getSession().getNamedQuery(namedQuery);
		Query query = getSession().createQuery(namedQuery);

		if (timeOutPeriod != 0)
			query.setTimeout(timeOutPeriod);
		if (parameters != null) {
			for (Parameter parameter : parameters) {
				if (parameter.getType() == null)
					query.setParameter(parameter.getName(),
							parameter.getValue());

				else
					query.setParameter(parameter.getName(), parameter,
							parameter.getType());
			}
		}
		addCaching(query);
		return query;

	}

	// private static Query createNamedSqlQuery(String namedSqlQuery,
	// Parameter[] parameters)
	// {
	// Query query = ((SessionImpl)
	// getSession()).getNamedSQLQuery(namedSqlQuery);
	// for (Parameter parameter : parameters)
	// {
	// //if (parameter.Type == null)
	// query.setParameter(parameter.getName(), parameter);
	// // else
	// // query.SetParameter(parameter.Name, parameter.Value, parameter.Type);
	// }
	// addCaching(query);
	// return query;
	// }

	private static Query createNamedSqlQuery(String namedSqlQuery,
			Parameter[] parameters, int timeOutPeriod) {
		Query query = ((SessionImpl) getSession())
				.getNamedSQLQuery(namedSqlQuery);

		if (timeOutPeriod != 0)
			query.setTimeout(timeOutPeriod);

		for (Parameter parameter : parameters) {
			if (parameter.getType() == null)
				query.setParameter(parameter.getName(), parameter.getValue());

			else
				query.setParameter(parameter.getName(), parameter,
						parameter.getType());
		}
		addCaching(query);
		return query;
	}

	private static void addCaching(Query query) {
		query.setCacheable(true);

	}

}
