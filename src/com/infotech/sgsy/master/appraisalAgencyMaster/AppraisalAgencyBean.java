package com.infotech.sgsy.master.appraisalAgencyMaster;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class AppraisalAgencyBean extends ValidatorForm{
	
	private String id;
	private String appraisalName;
	private String appraisalCode;
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
	public String getAppraisalName() {
		return appraisalName;
	}
	public void setAppraisalName(String appraisalName) {
		this.appraisalName = appraisalName;
	}
	public String getAppraisalCode() {
		return appraisalCode;
	}
	public void setAppraisalCode(String appraisalCode) {
		this.appraisalCode = appraisalCode;
	}
	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
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
