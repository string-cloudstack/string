package com.pft.string.service.framework.core.types;

import com.pft.string.service.framework.core.FaultMessage;

public class ServiceResponse {
	
	Object result;
	
	FaultMessage errorMessage;
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public FaultMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(FaultMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
}
