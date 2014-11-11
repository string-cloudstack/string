package com.pft.string.service.framework.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pft.string.service.framework.core.types.ErrorLevel;

@XmlRootElement(name = "FaultMessage")
public class FaultMessage {

	public FaultMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public FaultMessage(long code, ErrorLevel level, String description, 
			String localizedString, String linkPath) {
		this.code = code;
		this.level = level;
		this.description = description;
		this.localizedString = localizedString;
		this.linkPath = linkPath;
	}

	private long code = -1;
	
	private ErrorLevel level = ErrorLevel.LOW; 
	
	private String description = "";
	
	private String localizedString = "";
	
	private String linkPath = "";
	
	/**
	 * @return the code
	 */
	@XmlElement(name = "Code")
	public long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the localizedString
	 */
	@XmlElement(name = "LocalizedString")
	public String getLocalizedString() {
		return localizedString;
	}

	/**
	 * @param localizedString the localizedString to set
	 */
	public void setLocalizedString(String localizedString) {
		this.localizedString = localizedString;
	}

	/**
	 * @return the linkPath
	 */
	@XmlElement(name = "LinkPath")
	public String getLinkPath() {
		return linkPath;
	}

	/**
	 * @param linkPath the linkPath to set
	 */
	public void setLinkPath(String linkPath) {
		this.linkPath = linkPath;
	}

	/**
	 * @return the level
	 */
	@XmlElement(name = "Level")
	public ErrorLevel getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(ErrorLevel level) {
		this.level = level;
	}
}
