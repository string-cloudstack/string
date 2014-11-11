package com.pft.string.service.framework.core.types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "PagingCriteria")
public class PagingCriteria 
{
	private int pageNo;
	
	private int maxResults;
	
	private ObjectState state;
	
	private boolean specificToUser;
	
	private boolean countNeeded;
	
	private PFTDate dateFrom;
	
	private PFTDate dateTo;

	//@XmlElement(name = "PageNo")
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	//@XmlElement(name = "MaxResults")
	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	
	//@XmlElement(name = "State")
	public ObjectState getState() {
		return state;
	}

	public void setState(ObjectState state) {
		this.state = state;
	}

	//@XmlElement(name = "SpecificToUser")
	public boolean getSpecificToUser() {
		return specificToUser;
	}

	public void setSpecificToUser(boolean specificToUser) {
		this.specificToUser = specificToUser;
	}

	//@XmlElement(name = "DateFrom")
	public PFTDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(PFTDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	//@XmlElement(name = "DateTo")
	public PFTDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(PFTDate dateTo) {
		this.dateTo = dateTo;
	}
	
	//@XmlElement(name = "IsCountNeeded")
	public boolean isCountNeeded() {
		return countNeeded;
	}

	public void setCountNeeded(boolean countNeeded) {
		this.countNeeded = countNeeded;
	}

}
