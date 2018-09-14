package com.infotech.sgsy.master.tsaMaster;

import java.util.Date;

public class TsaMasterVO {
	
	@Override
	public String toString() {
		return "TsaMasterVO [id=" + id + ", tsaName=" + tsaName + ", tsaCode=" + tsaCode + ", isdDeleted=" + isdDeleted
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdOnDate=" + createdOnDate
				+ ", updatedOnDate=" + updatedOnDate + "]";
	}
	private String id;
	private String tsaName;
	private String tsaCode;
	
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
	public String getTsaName() {
		return tsaName;
	}
	public void setTsaName(String tsaName) {
		this.tsaName = tsaName;
	}
	public String getTsaCode() {
		return tsaCode;
	}
	public void setTsaCode(String tsaCode) {
		this.tsaCode = tsaCode;
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
