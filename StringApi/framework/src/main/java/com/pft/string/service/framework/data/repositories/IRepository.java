package com.pft.string.service.framework.data.repositories;


import java.util.List;



import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.transform.ResultTransformer;

import com.pft.string.service.framework.data.types.Parameter;

public interface IRepository<T> {
	void attach(T entity);
    void detach(T entity);

    void add(T entity);
   
    /// <summary>
    /// Get the entity from the persistance store, or return null
    /// if it doesn't exist.
    /// </summary>
    /// <param name="id">The entity's id</param>
    /// <returns>Either the entity that matches the id, or a null</returns>
    T getEnity(Object id);
    
  /// <summary>
    /// Get the entity from the persistance store, or return null
    /// if it doesn't exist.
    /// </summary>
    /// <param name="id">The entity's id</param>
    /// <returns>Either the entity that matches the id, or a null</returns>
    T getGlobalEnity(Object id);

    /// <summary>
    /// Load the entity from the persistance store
    /// Will throw an exception if there isn't an entity that matches
    /// the id.
    /// </summary>
    /// <param name="id">The entity's id</param>
    /// <returns>The entity that matches the id</returns>
    T loadEntity(Object id);

    /// <summary>
    /// Register the entity for deletion when the unit of work
    /// is completed. 
    /// </summary>
    /// <param name="entity">The entity to delete</param>
    void delete(T entity);

    /// <summary>
    /// Register te entity for save in the database when the unit of work
    /// is completed.
    /// </summary>
    /// <param name="entity">the entity to save</param>
    void save(T entity);

    /// <summary>
    /// Loads all the entities that match the criteria
    /// by order
    /// </summary>
    /// <param name="criteria">the criteria to look for</param>
    /// <returns>All the entities that match the criteria</returns>
    List<T> findAll(Order order,Criterion... criteria);

    /// <summary>
    /// Loads all the entities that match the criteria
    /// by order
    /// </summary>
    /// <param name="criteria">the criteria to look for</param>
    /// <param name="orders"> the order to load the entities</param>
    /// <returns>All the entities that match the criteria</returns>
    List<T> findAll(DetachedCriteria criteria,Order... orders);

    /// <summary>
    /// Loads all the entities that match the criteria
    /// by order
    /// </summary>
    /// <param name="criteria">the criteria to look for</param>
    /// <param name="orders"> the order to load the entities</param>
    /// <param name="firstResult">the first result to load</param>
    /// <param name="maxResults">the number of result to load</param>
    /// <returns>All the entities that match the criteria</returns>
    List<T> findAll(DetachedCriteria criteria,
                           Integer firstResult, Integer maxResults,
                           Order... orders);


    /// <summary>
    /// Loads all the entities that match the criteria
    /// by order
    /// </summary>
    /// <param name="criteria">the criteria to look for</param>
    /// <returns>All the entities that match the criteria</returns>
    List<T> findAll(Order[] orders,Criterion... criteria);

    /// <summary>
    /// Loads all the entities that match the criteria
    /// </summary>
    /// <param name="criteria">the criteria to look for</param>
    /// <returns>All the entities that match the criteria</returns>
    List<T> findAll(Criterion... criteria);    
  

    /// <summary>
    /// Loads all the entities that match the criteria, and allow paging.
    /// </summary>
    /// <param name="firstResult">The first result to load</param>
    /// <param name="numberOfResults">Total number of results to load</param>
    /// <param name="criteria">the cirteria to look for</param>
    /// <returns>number of Results of entities that match the criteria</returns>
    List<T> findAll(Integer firstResult, Integer numberOfResults, Criterion... criteria);

    /// <summary>
    /// Loads all the entities that match the criteria, with paging 
    /// and orderring by a single field.
    /// <param name="firstResult">The first result to load</param>
    /// <param name="numberOfResults">Total number of results to load</param>
    /// <param name="criteria">the cirteria to look for</param>
    /// <returns>number of Results of entities that match the criteria</returns>
    /// <param name="selectionOrder">The field the repository should order by</param>
    /// <returns>number of Results of entities that match the criteria</returns>
    List<T> findAll(Integer firstResult, Integer numberOfResults,
                           Order selectionOrder,
                           Criterion... criteria);

    /// <summary>
    /// Loads all the entities that match the criteria, with paging 
    /// and orderring by a multiply fields.
    /// </summary>
    /// <param name="firstResult">The first result to load</param>
    /// <param name="numberOfResults">Total number of results to load</param>
    /// <param name="criteria">the cirteria to look for</param>
    /// <returns>number of Results of entities that match the criteria</returns>
    /// <param name="selectionOrder">The fields the repository should order by</param>
    List<T> findAll(Integer firstResult, Integer numberOfResults,
                           Order[] selectionOrder,
                           Criterion... criteria);

    /// <summary>
    /// Execute the named query and return all the results
    /// </summary>
    /// <param name="namedQuery">The named query to execute</param>
    /// <param name="parameters">Parameters for the query</param>
    /// <returns>The results of the query</returns>
    List<T> findAll(String namedQuery,Boolean isSQL,ResultTransformer resultTransformer,Parameter... parameters);

    /// <summary>
    /// Execute the named query and return paged results
    /// </summary>
    /// <param name="parameters">Parameters for the query</param>
    /// <param name="namedQuery">the query to execute</param>
    /// <param name="firstResult">The first result to return</param>
    /// <param name="numberOfResults">number of records to return</param>
    /// <returns>Paged results of the query</returns>
    List<T> findAll(Integer firstResult, Integer numberOfResults, String namedQuery,Parameter... parameters);

    /// <summary>
    /// For Reports with direct sql query
    /// </summary>
    /// <param name="sqlQuery"></param>
    /// <param name="firstResult"></param>
    /// <param name="maxResults"></param>
    /// <param name="resultTransformer"></param>
    /// <param name="parameters"></param>
    /// <returns></returns>
    List<T> findAll(String sqlQuery, Integer firstResult, Integer maxResults,ResultTransformer resultTransformer, Parameter... parameters);

    /// <summary>
    /// Find a single entity based on a criteria.
    /// Thorws is there is more than one result.
    /// </summary>
    /// <param name="criteria">The criteria to look for</param>
    /// <returns>The entity or null</returns>
    T findOne(Criterion... criteria);

    /// <summary>
    /// Find a single entity based on a criteria.
    /// Thorws is there is more than one result.
    /// </summary>
    /// <param name="criteria">The criteria to look for</param>
    /// <returns>The entity or null</returns>
    T findOne(DetachedCriteria criteria);

    /// <summary>
    /// Find a single entity based on a named query.
    /// Thorws is there is more than one result.
    /// </summary>
    /// <param name="parameters">parameters for the query</param>
    /// <param name="namedQuery">the query to executre</param>
    /// <returns>The entity or null</returns>
    T findOne(String namedQuery, Parameter... parameters);

    /// <summary>
    /// Overloaded method takes timeOutPeriod 
    /// in seconds as addional parameter
    /// </summary>
    /// <param name="namedQuery"></param>
    /// <param name="timeOutPeriod"></param>
    /// <param name="parameters"></param>
    /// <returns></returns>
    T findOne(String namedQuery,Integer timeOutPeriod,Parameter... parameters);

    /// <summary>
    /// Find the entity based on a criteria.
    /// </summary>
    /// <param name="criteria">The criteria to look for</param>
    /// <param name="orders">Optional orderring</param>
    /// <returns>The entity or null</returns>
    T findFirst(DetachedCriteria criteria,  Order...orders);
   

    /// <summary>
    /// Refreshes the specified entity.
    /// </summary>
    /// <param name="entity">The entity.</param>
    void refresh(T entity);

    /// <summary>
    /// Gets the id.
    /// </summary>
    /// <param name="entity">The entity.</param>
    /// <returns></returns>
    Object getId(T entity);

    /// <summary>
    /// Gets Total no. of Rows
    /// </summary>
    /// <returns>Total No Of Rows </returns>
    Long getCount();

    /// <summary>
    /// Gets Total no. of Rows By Criterion
    /// </summary>
    /// <param name="criteria">Criteria to Check</param>
    /// <returns>No Of Rows Matches the Criteria</returns>
    Long getCount(Criterion[] criteria);

    /// <summary>
    /// Gets Total no. of Rows By Detached Criteria
    /// </summary>
    /// <param name="criteria">Detached Criteria to Check</param>
    /// <param name="orders">Order </param>
    /// <returns>No Of Rows Matches the Detached Criteria</returns>
    Long getCount(DetachedCriteria criteria);

    /// <summary>
    /// Gets Total no. of Rows By Detached Criteria
    /// </summary>
    /// <param name="criteria">Detached Criteria to Check</param>
    /// <param name="orders">Order </param>
    /// <returns>No Of Rows Matches the Detached Criteria</returns>
    Long getCount(DetachedCriteria criteria, boolean isDistinctRequired);

    /// <summary>
    /// Gets All Ids of objects that met's the Detached Criteria
    /// </summary>
    /// <param name="criteria">Criteia to be Searched</param>
    /// <returns>Collection of Identifiers of object that met the criteia</returns>
    List<Long> getObjectIdArray(DetachedCriteria criteria);


    /// <summary>
    /// Enable's the particular filter
    /// </summary>
    /// <param name="filterName">fileterName to be enabled</param>
    /// <param name="paramName">paramName in particular filter</param>
    /// <param name="paramValue">value of the parameter</param>
    void enableFilter(String filterName, String paramName, Object paramValue);


    /// <summary>
    /// Disable's the filter    
    /// </summary>
    /// <param name="filterName">name of the filter to be disbled</param>
    void disableFilter(String filterName);



    /// <summary>
    /// Loads all the entities that match the criteria, with paging 
    /// and orderring by a multiply fields.Translates the result to desired type
    /// </summary>
    /// <param name="firstResult">The first result to load</param>
    /// <param name="maxResults">Total number of results to load</param>
    /// <param name="criteria">the cirteria to look for</param>
    /// <param name="orders">The fields the repository should order by</param>
    /// <param name="resultTransformer">Transformer for result data</param>
    /// <returns>number of Results of entities that match the criteria</returns>
    List<T> findAll(DetachedCriteria criteria, Integer firstResult, Integer maxResults, ResultTransformer resultTransformer, Order... orders);

    
    /// <summary>
    /// Clear cache
    /// </summary>
    void clear();

    /// <summary>
    /// Purge Object Collection and return purge count
    /// </summary>
    void purgeAll();

    Integer update(String query,boolean isSql,Parameter... parameters);
	Object getAttribute(DetachedCriteria criteria, String propertyName);
	Object getAttribute(Class classInfo,Object id,String propertyName);
	Object getProjectedResult(DetachedCriteria criteria,Projection projection );
	List   getDatabySql(String query,boolean isSql,Parameter... parameters);
	Object GetAttribute(String entityName, Long id, String propertyName);
	void UpdateAttribute(String extEntityName, Long id, String propertyName,Object value);
	
    
         

}
