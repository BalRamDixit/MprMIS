package com.infotech.skills.skillsSanctionOrders;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class SanctionOrdersForm extends ValidatorForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sanctionOrderNo;
	private String sanctionOrderDate;
	private String piaRegistrationNumber;
	private FormFile sanctionFile;
	private String piaCode;
	private String sanctionFileName;
	private String piaName;
	private String  createdOn;
	private String  createdBy;
	
	
	public String getPiaName() {
		return piaName;
	}
	public void setPiaName(String piaName) {
		this.piaName = piaName;
	}
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getSanctionOrderNo() {
		return sanctionOrderNo;
	}
	public void setSanctionOrderNo(String sanctionOrderNo) {
		this.sanctionOrderNo = sanctionOrderNo;
	}
	public String getSanctionOrderDate() {
		return sanctionOrderDate;
	}
	public void setSanctionOrderDate(String sanctionOrderDate) {
		this.sanctionOrderDate = sanctionOrderDate;
	}
	public FormFile getSanctionFile() {
		return sanctionFile;
	}
	public void setSanctionFile(FormFile sanctionFile) {
		this.sanctionFile = sanctionFile;
	}
	public void setSanctionFileName(String sanctionFileName) {
		this.sanctionFileName = sanctionFileName;
	}
	public String getSanctionFileName() {
		return sanctionFileName;
	}
	
	public void reset(ActionMapping mapping, 
 			HttpServletRequest request) {

		this.setSanctionOrderNo(null);
		this.setSanctionOrderDate(null);
		this.setPiaRegistrationNumber(null);
		this.setSanctionFile(null);
		this.setPiaCode(null);
		this.setSanctionFileName(null);
	}
	public void setPiaRegistrationNumber(String piaRegistrationNumber) {
		this.piaRegistrationNumber = piaRegistrationNumber;
	}
	public String getPiaRegistrationNumber() {
		return piaRegistrationNumber;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	
}
