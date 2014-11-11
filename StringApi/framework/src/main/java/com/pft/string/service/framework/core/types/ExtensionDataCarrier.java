package com.pft.string.service.framework.core.types;

import javax.xml.bind.annotation.XmlElement;

public class ExtensionDataCarrier
{
  private String propertyName;
  
  private String propertyValue;
  
  private PropertyType propertyType;

@XmlElement(name = "propertyName")
public String getPropertyName() {
	return propertyName;
}

public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}

@XmlElement(name = "propertyValue")
public String getPropertyValue() {
	return propertyValue;
}

public void setPropertyValue(String propertyValue) {
	this.propertyValue = propertyValue;
}

@XmlElement(name = "propertyType")
public PropertyType getPropertyType() {
	return propertyType;
}

public void setPropertyType(PropertyType propertyType) {
	this.propertyType = propertyType;
}
	
}
