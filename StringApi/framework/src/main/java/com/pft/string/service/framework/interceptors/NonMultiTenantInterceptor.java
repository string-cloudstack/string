package com.pft.string.service.framework.interceptors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.resteasy.util.InputStreamToByteArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;

import com.pft.string.service.framework.audit.Audit;
import com.pft.string.service.framework.audit.AuditlogFacade;
import com.pft.string.service.framework.audit.AuditlogFacade.CreateAudit;
import com.pft.string.service.framework.audit.AuditlogFacade.UpdateAuditlogByRequestID;
import com.pft.string.service.framework.audit.AuditlogFacade.UpdateInputParameterByRequestID;
import com.pft.string.service.framework.core.RequestContext;
import com.pft.string.service.framework.data.persistence.UnitOfWork;
import com.pft.string.service.framework.logproperties.LogFactory;

@ServerInterceptor
public class NonMultiTenantInterceptor implements PreProcessInterceptor,
		PostProcessInterceptor, MessageBodyReaderInterceptor {
	private static final Logger LOGGER = LogFactory
			.getLogger(NonMultiTenantInterceptor.class);
	@Context
	HttpServletRequest servletRequest;
	@Context
	ServletContext servletContext;

	public NonMultiTenantInterceptor() {
	}
    @Override
	public ServerResponse preProcess(HttpRequest request,
			ResourceMethod resourceMethod) throws Failure,
			WebApplicationException {
		ServerResponse response = null;
		try {
			UnitOfWork.start();
		} catch (Exception e) {
			response = new ServerResponse(
					"Unable to start the UnitOfWork,see the server log for more details",
					500, new Headers());
			return response;
		}
		try {
			Audit auditlog = new Audit();
			String requestID = RequestContext.getAttribute("AuditRequestID");
			String additionalInfo = "";
			/*
			 * MultivaluedMap<String, String> str=clientRequest.getHeaders();
			 * str.getFirst("");
			 */
			if (servletRequest.getRemoteAddr() != null
					&& servletRequest.getRemoteAddr() != "")
				additionalInfo += " IP: " + servletRequest.getRemoteAddr();
			if (servletRequest.getRemotePort() != 0)
				additionalInfo += " Port:" + servletRequest.getRemotePort();
			if (servletRequest.getRemoteUser() != null
					&& servletRequest.getRemoteUser() != "")
				additionalInfo += " User:" + servletRequest.getRemoteUser();
			auditlog.setInTimeStamp(new Date());
			auditlog.setAction(resourceMethod.getMethod().getName());
			auditlog.setRequestId(requestID);
			auditlog.setAdditionalInfo(additionalInfo);
			createAuditlog(auditlog);
		} catch (Exception e) {
			// log
			LOGGER.debug("Unable to insert Auditlog");
			System.out.println("preProcess : "+e);
		}
		return null;
	}

    @Override
	public void postProcess(ServerResponse response) {
		try {
			Audit auditlog = new Audit();
			String requestID = RequestContext.getAttribute("AuditRequestID");
			auditlog.setRequestId(requestID);
			if (response.getStatus() == 200) {
				auditlog.setSuccessStatus(true);
				auditlog.setSeverity(4);
			} else {
				auditlog.setSuccessStatus(false);
				auditlog.setErrorMessage(response.getEntity().toString());
				auditlog.setSeverity(3);
			}
			updateAuditlog(auditlog);
		} catch (Exception e) {
			// log
			LOGGER.debug("Unable to update Auditlog");
			System.out.println("postProcess : "+e);
		}
		UnitOfWork.end();
	}

    @Override
	public Object read(MessageBodyReaderContext context) throws IOException,
			WebApplicationException {
		// TODO Auto-generated method stub
		InputStream inputStream = context.getInputStream();
		try {
			InputStreamToByteArray stream = new InputStreamToByteArray(
					inputStream);
			context.setInputStream(stream);
			Object proceed = context.proceed();
			byte[] body = stream.toByteArray();
			String inputParameter = new String(body);
			String userSessionKey = "NA";
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(inputParameter);
				JSONObject jsonObject = (JSONObject) obj;
				userSessionKey = (String) jsonObject.get("userSession");
				if (userSessionKey == null) {
					userSessionKey = (String) jsonObject.get("deviceSession");
					if (userSessionKey == null)
						userSessionKey = "NA";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("JSONParser error while auditlog");
				System.out.println("read : "+e);
			}
			Audit auditlog = new Audit();
			String requestID = RequestContext.getAttribute("AuditRequestID");
			auditlog.setUserSessionKey(userSessionKey);
			auditlog.setRequestId(requestID);
			auditlog.setParameters(inputParameter);
			//auditlog.setUserId(userId);
			updateAuditlogInputParameter(auditlog);
			
			return proceed;
		} finally {
			context.setInputStream(inputStream);
		}
	}

	public long createAuditlog(Audit auditlog) throws Exception {
		// Insert into Auditlog table
		CreateAudit facade = new AuditlogFacade().new CreateAudit();
		facade.setEntity(auditlog);
		facade.Execute();
		return facade.getEntity().getId();
	}

	public void updateAuditlog(Audit auditlog) throws Exception {
		// Insert into Auditlog table
		UpdateAuditlogByRequestID facade = new AuditlogFacade().new UpdateAuditlogByRequestID();
		facade.setErrorMessage(auditlog.getErrorMessage());
		facade.setOutTimeStamp(new Date());
		facade.setRequestId(auditlog.getRequestId());
		facade.setSeverity(auditlog.getSeverity());
		facade.setSuccessStatus(auditlog.getSuccessStatus());
		facade.Execute();

	}

	public void updateAuditlogInputParameter(Audit auditlog) {
		// Insert into Auditlog table
		UpdateInputParameterByRequestID facade = new AuditlogFacade().new UpdateInputParameterByRequestID();
		facade.setParameters(auditlog.getParameters());
		facade.setUserSessionKey(auditlog.getUserSessionKey());
		facade.setRequestId(auditlog.getRequestId());
		try {
			facade.Execute();
		} catch (Exception e) {
			LOGGER.debug("Unable to update Auditlog");
			System.out.println("updateAuditlogInputParameter : "+e);
		}

	}

}
