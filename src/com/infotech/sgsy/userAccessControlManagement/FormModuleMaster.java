package com.infotech.sgsy.userAccessControlManagement;

import java.util.Date;

public class FormModuleMaster {
	
	private String id;
	private String formName;
	private String moduleName;
	private String url;
	private int formOrderNo;
	private String help;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	@Override
	public String toString() {
		return "FormModuleMaster [id=" + id + ", formName=" + formName + ", moduleName=" + moduleName + ", url=" + url
				+ ", formOrderNo=" + formOrderNo + ", help=" + help + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFormOrderNo() {
		return formOrderNo;
	}
	public void setFormOrderNo(int formOrderNo) {
		this.formOrderNo = formOrderNo;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	private Date updatedDate;

}
