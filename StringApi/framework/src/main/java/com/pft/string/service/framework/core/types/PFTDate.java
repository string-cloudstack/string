package com.pft.string.service.framework.core.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "PFTDate")
public class PFTDate {

	private Date _pftDate = null;
	private String _pftDateStr = "";

	public PFTDate() {
		
	}

	
	public PFTDate(Date date) {
		_pftDate = date;
	}

	@XmlTransient
	// This will be ignored in JSON string
	public Date getValue() {
		return _pftDate;
	}

	public void setValue(Date value) {
		_pftDate = value;
	}

	//@XmlElement(name = "Carrier")
	public String getCarrier() {
		if (this._pftDate != null) {
			DateFormat dateFormat = new SimpleDateFormat(
					"MM/dd/yyyy HH:mm:ss a"); // Could be Tenant specific
			_pftDateStr = dateFormat.format(this._pftDate);
		}
		return _pftDateStr;

	}

	public void setCarrier(String value) {

		this._pftDateStr = value;
		try {
			DateFormat dateFormat = new SimpleDateFormat(
					"MM/dd/yyyy HH:mm:ss a");
			if(this._pftDateStr !=null || !this._pftDateStr.equals(""))
			{
			this._pftDate = dateFormat.parse(this._pftDateStr);
			}
			else
			{
				this._pftDate=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
