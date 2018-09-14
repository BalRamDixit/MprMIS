/*
 * Created on Mar 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.infotech.sgsy.util;

import com.infotech.sgsy.common.MasterVO;



/**
 * Model to access properties
 * 
 */
public class PropertyModel  extends MasterVO {

	String propertyCode ="";
	String propertyValue = "";
	/**
	 * @return Returns the propertyCode.
	 */
	public PropertyModel(String propertyCode, String propertyValue) {
		super();
		this.propertyCode = propertyCode;
		this.propertyValue = propertyValue;
	}
	
	public PropertyModel() {
		// TODO Auto-generated constructor stub
	}

	public String getPropertyCode() {
		return propertyCode;
	}
	/**
	 * @param propertyCode The propertyCode to set.
	 */
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	/**
	 * @return Returns the propertyValue.
	 */
	public String getPropertyValue() {
		return propertyValue;
	}
	/**
	 * @param propertyValue The propertyValue to set.
	 */
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
}
