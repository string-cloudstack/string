package com.pft.string.service.framework.data.repositories;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pft.string.service.framework.core.ApplicationContext;
import com.pft.string.service.framework.core.RequestContext;
import com.pft.string.service.framework.data.connection.DBServer;
import com.pft.string.service.framework.data.connection.DataSourcer;
import com.pft.string.service.framework.data.persistence.PersistencyService;
import com.pft.string.service.framework.data.types.Parameter;

public class SQLRepository
{
	private static Session getSession() {
		try {
			return PersistencyService.getCurrentSession();
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static DBServer GetDBType() throws Exception
	{
		ApplicationContext context = RequestContext.getCurrentApplicationContext();
		Long tenantId = context.getTenantId();
		return DataSourcer.GetDBType(tenantId);
	}
	
	public  List<HashMap<String,Object>> GetDataByStoredProc(String sqlQuery,List<Parameter> params) throws HibernateException, SQLException
	{
		SQLWork work = new SQLWork(sqlQuery,params);
		return convertResultSetToList(getSession().doReturningWork(work));	
				
	}
	
	public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    rs.close();
	    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

	    while (rs.next()) {
	        HashMap<String,Object> row = new HashMap<String, Object>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(md.getColumnName(i),rs.getObject(i));
	        }
	        list.add(row);
	    }
	    rs.close();
	    return list;
	}
	

}
