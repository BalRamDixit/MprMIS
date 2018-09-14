package com.infotech.sgsy.master.ctsaMaster;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class CtsaMasterBean extends ValidatorForm{
	
	
	private String id;
	private String ctsaName;
	private String ctsaCode;
	private String editId;
	
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

	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}

}
