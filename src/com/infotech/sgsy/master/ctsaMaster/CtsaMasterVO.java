package com.infotech.sgsy.master.ctsaMaster;

import java.io.Serializable;
import java.util.Date;

public class CtsaMasterVO implements Serializable{
	
	private String id;
	private String ctsaName;
	private String ctsaCode;
	
	private boolean isdDeleted;
	private String createdBy;
	private String updatedBy;
	private Date createdOnDate;
	private Date updatedOnDate;
	
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCtsaName() {
		return ctsaName;
	}
	public void setCtsaName(String ctsaName) {
		this.ctsaName = ctsaName;
	}
	public String getCtsaCode() {
		return ctsaCode;
	}
	public void setCtsaCode(String ctsaCode) {
		this.ctsaCode = ctsaCode;
	}
	
	public boolean isIsdDeleted() {
		return isdDeleted;
	}
	public void setIsdDeleted(boolean isdDeleted) {
		this.isdDeleted = isdDeleted;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}
	
	

}
