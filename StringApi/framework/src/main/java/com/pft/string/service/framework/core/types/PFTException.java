package com.pft.string.service.framework.core.types;

public class PFTException extends Exception {

	private static final long serialVersionUID = 1L;

	public PFTException() {
		
	}
	
	public PFTException(long code, ErrorLevel level, String description) {
		this.code = code;
		this.level = level;
		this.description = description;
		//this.linkPath = linkPath;
	}

	private long code = -1;
	
	private ErrorLevel level = ErrorLevel.LOW; 
	
	private String description = "";
	
	private String localizedString = "";
	
	private String linkPath = "";
	
	/**
	 * @return the code
	 */
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
