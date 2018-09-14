package com.infotech.skills.skillsSanctionOrders;

import java.io.Serializable;

public class SanctionOrdersVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sanctionOrderNo;
	private String sanctionOrderDate;
	private String  piaCode;
	private String  sanctionFileName;
	private String  piaRegistrationNumber;
	private String  createdOn;
	private String  createdBy;
	private String  modifyOn;
	private String  modifyBy;
	 private String ipAddress;
	
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
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getSanctionFileName() {
		return sanctionFileName;
	}
	public void setSanctionFileName(String sanctionFileName) {
		this.sanctionFileName = sanctionFileName;
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
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getPiaRegistrationNumber() {
		return piaRegistrationNumber;
	}
	public void setPiaRegistrationNumber(String piaRegistrationNumber) {
		this.piaRegistrationNumber = piaRegistrationNumber;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	

}
