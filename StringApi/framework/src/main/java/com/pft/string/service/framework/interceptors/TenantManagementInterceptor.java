package com.pft.string.service.framework.interceptors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import com.pft.string.service.framework.core.ApplicationContext;
import com.pft.string.service.framework.core.Constants;
import com.pft.string.service.framework.data.persistence.UnitOfWork;

public class TenantManagementInterceptor  implements PreProcessInterceptor,PostProcessInterceptor
{
	@Context
	HttpServletRequest servletRequest;
	
	@Context 
	ServletContext servletContext;
	@Override
	public ServerResponse preProcess(HttpRequest request,
			ResourceMethod resourceMethod) throws Failure,
			WebApplicationException {
		ServerResponse response = null;
		Long tenantId=null;
		Long userId=null;
		String token = null;
		try {
		 tenantId = Long.parseLong(request.getHttpHeaders().getRequestHeader("TenantId").get(0));
		}
		catch(Exception ex)
		{
			
		}
		if(tenantId == null)
		{
			response = new ServerResponse(
					"Invalid TenantId",
					400, new Headers<Object>());
			return response;
		}
		try {
			userId = Long.parseLong(request.getHttpHeaders().getRequestHeader("UserId").get(0));
			}
			catch(Exception ex)
			{
				
			}
			if(userId == null)
			{
				response = new ServerResponse(
						"Invalid UserId",
						400, new Headers<Object>());
				return response;
			}
			token = request.getHttpHeaders().getRequestHeader("Token").get(0);
			if(token == null)
			{
				response = new ServerResponse(
						"Token can't be null",
						400, new Headers<Object>());
				return response;
			}
			Boolean authenticated = vaildateToken(token);
			if(!authenticated)
			{
				response = new ServerResponse(
						"Login Expired,Kindly Login again",
						400, new Headers<Object>());
				return response;
			}
			
			String currentAppPath = servletRequest.getSession().getServletContext().getRealPath("/");
			Boolean multitenancy = null;
			String factoryKey = null;
			if(servletContext.getInitParameter(Constants.Multitenancy) != null)			
			 multitenancy = Boolean.valueOf(servletContext.getInitParameter(Constants.Multitenancy));
			
			if(servletContext.getInitParameter(Constants.SessionFactoryJNDILookupKey) != null)			
			 factoryKey = servletContext.getInitParameter(Constants.SessionFactoryJNDILookupKey);
			
			ApplicationContext appContext = new ApplicationContext(tenantId, userId, token, currentAppPath,multitenancy,factoryKey);
			try {
				UnitOfWork.start(appContext);
			} catch (Exception e) {
				//log
				response = new ServerResponse(
						"Unable to start the UnitOfWork,see the server log for more details",
						500, new Headers<Object>());
				return response;
			}
			
        
	
		return null;
	}

	private Boolean vaildateToken(String token) {

		return true;
	}

	
	@Override
	public void postProcess(ServerResponse arg0) {
		
		UnitOfWork.end();
	}

}
