package com.infotech.sgsy.master.parliamentaryConstituencyMaster;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class ParliamentaryConstituencyMasterBean extends ValidatorForm{
	
	
	private String id;
	private String name;
	private String code;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
