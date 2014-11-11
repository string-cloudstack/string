package com.pft.string.service.framework.data.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.ReturningWork;

import com.pft.string.service.framework.data.types.Parameter;

public class SQLWork implements ReturningWork<ResultSet>
{
	private List<Parameter> params = null;
	
	private  String sqlQuery = null;

	public SQLWork(String sqlQuery, List<Parameter> params) {
		this.params = params;
		this.sqlQuery = sqlQuery;
	}

	@Override
	public ResultSet execute(Connection conn) throws SQLException
	{
			 CallableStatement cstmt = conn.prepareCall(getSqlQuery());
			 if(getParams()!= null)
			 {
				 for(Parameter param : getParams())
				 {
					 cstmt.setObject(param.getName(),param.getValue());
				 }
			 }
	          
	            ResultSet rs;
	            try
	            {
	            
	                cstmt.execute();                        

	                rs = cstmt.getResultSet(); // First resultset
	                    
	            }
	            finally
	            {//cstmt.close();
	            	
	            }

	            return rs; // this should contain All rows or objects you need for further processing
		}

	public List<Parameter> getParams() {
		return params;
	}

	public void setParams(List<Parameter> params) {
		this.params = params;
	}

	private String getSqlQuery() {
		return sqlQuery;
	}

	private void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	

}
