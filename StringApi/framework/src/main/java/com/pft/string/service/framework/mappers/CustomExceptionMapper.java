package com.pft.string.service.framework.mappers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;

import com.pft.string.service.framework.logproperties.LogFactory;
import com.pft.string.service.framework.audit.AuditlogFacade;
import com.pft.string.service.framework.audit.AuditlogFacade.UpdateAuditlogByRequestID;
import com.pft.string.service.framework.audit.Audit;
import com.pft.string.service.framework.core.FaultMessage;
import com.pft.string.service.framework.core.RequestContext;
import com.pft.string.service.framework.core.types.ErrorLevel;
import com.pft.string.service.framework.core.types.PFTException;
import com.pft.string.service.framework.core.types.ServiceResponse;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
	private static final Logger LOGGER = LogFactory
			.getLogger(CustomExceptionMapper.class);
	@Context
	HttpServletRequest servletRequest;

	public Response toResponse(Exception exception) {

		FaultMessage fault = new FaultMessage();

		if (exception instanceof PFTException) {

			PFTException customExp = (PFTException) exception;

			long errorCode = customExp.getCode();
			fault.setCode(errorCode);

			String desc = customExp.getDescription();
			fault.setDescription(desc);

			ErrorLevel level = customExp.getLevel();
			fault.setLevel(level);

			// Mechanism to pull Localised String and other info...
		} else {
			// Populate with generic error message structure
			System.out.println("updateAuditlogInputParameter : "+exception);
			fault.setCode(999);
			fault.setDescription("Problem while accessing the resource");
			fault.setLevel(ErrorLevel.HIGH);
		}
		Audit auditlog = new Audit();
		String requestID = RequestContext.getAttribute("AuditRequestID");
		auditlog.setRequestId(requestID);
		auditlog.setSuccessStatus(false);
		auditlog.setSeverity(fault.getLevel().getIdForErrorLevel());
		auditlog.setErrorMessage("Error Code :" +fault.getCode()+" Error Message :"+ fault.getDescription());
		updateAuditlog(auditlog);
		// TODO: need to be relooked
		exception.printStackTrace();

		// Set the fault message to the response body
		ServiceResponse sresponse =new ServiceResponse();
		sresponse.setErrorMessage(fault);
		Response retVal = Response.status(500).entity(sresponse).build();

		return retVal;

	}

	public void updateAuditlog(Audit auditlog) {
		// Insert into auditlog
		UpdateAuditlogByRequestID facade = new AuditlogFacade().new UpdateAuditlogByRequestID();
		facade.setErrorMessage(auditlog.getErrorMessage());

		facade.setOutTimeStamp(new Date());
		facade.setRequestId(auditlog.getRequestId());
		facade.setSeverity(auditlog.getSeverity());
		facade.setSuccessStatus(auditlog.getSuccessStatus());
		try {
			facade.Execute();
		} catch (Exception e) {
			LOGGER.debug("Unable to update Auditlog");
		}
	}

}
