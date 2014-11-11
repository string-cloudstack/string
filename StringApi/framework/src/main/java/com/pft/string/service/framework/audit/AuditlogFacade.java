package com.pft.string.service.framework.audit;

import java.sql.Timestamp;
import java.util.Date;

import com.pft.string.service.framework.business.base.actions.CreateAction;
import com.pft.string.service.framework.business.base.actions.UpdateAction;

public class AuditlogFacade {
	
	public class CreateAudit extends CreateAction<Audit> {
		public CreateAudit() {
			super(CreateAudit.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}

	public class UpdateAuditlogByRequestID extends UpdateAction<Audit> {

		private Date outTimeStamp;
		private boolean successStatus;
		private String errorMessage;
		private String requestId;
		private int severity;

		public Date getOutTimeStamp() {
			return outTimeStamp;
		}

		public void setOutTimeStamp(Date outTimeStamp) {
			this.outTimeStamp = outTimeStamp;
		}

		public boolean isSuccessStatus() {
			return successStatus;
		}

		public void setSuccessStatus(boolean successStatus) {
			this.successStatus = successStatus;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		public int getSeverity() {
			return severity;
		}

		public void setSeverity(int severity) {
			this.severity = severity;
		}

		public UpdateAuditlogByRequestID() {
			super(UpdateAuditlogByRequestID.class);

		}

		@Override
		protected void OnExecute() throws Exception {
			Timestamp timestamp = new Timestamp(getOutTimeStamp().getTime());
			String hQuery = "update com.pft.string.service.framework.audit.Audit as da set da.outTimeStamp = '"
					+ timestamp
					+ "',da.successStatus = "
					+ isSuccessStatus()
					+ " ,da.errorMessage = '"
					+ getErrorMessage()
					+ "' ,da.severity = " + getSeverity();
			hQuery = hQuery + " where da.requestId = '" + getRequestId() + "'";
			new AuditlogRepository().update(hQuery, false);
		}

	}

	public class UpdateInputParameterByRequestID extends UpdateAction<Audit> {

		private String userSessionKey;
		private String parameters;
		private String requestId;

		public String getUserSessionKey() {
			return userSessionKey;
		}

		public void setUserSessionKey(String userSessionKey) {
			this.userSessionKey = userSessionKey;
		}

		public String getParameters() {
			return parameters;
		}

		public void setParameters(String parameters) {
			this.parameters = parameters;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		public UpdateInputParameterByRequestID() {
			super(UpdateInputParameterByRequestID.class);

		}

		@Override
		protected void OnExecute() throws Exception {

			//Todo getUserSessionKey by using this get the UserID and pass its in below method as parameter
			
			String hQuery = "update com.pft.string.service.framework.audit.Audit as da set da.userSessionKey = '"
					+ getUserSessionKey()
					+ "' ,da.parameters = '"
					+ getParameters() + "'";
			hQuery = hQuery + " where da.requestId = '" + getRequestId() + "'";
			new AuditlogRepository().update(hQuery, false);
		}

	}
}
