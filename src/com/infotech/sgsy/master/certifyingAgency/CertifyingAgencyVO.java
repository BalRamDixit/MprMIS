package com.infotech.sgsy.master.certifyingAgency;

import java.util.Date;

public class CertifyingAgencyVO {

	@Override
	public String toString() {
		return "CertifyingAgencyVO [certifyingAgencyId=" + certifyingAgencyId + ", certifyingAgencyCode="
				+ certifyingAgencyCode + ", certifyingAgencyName=" + certifyingAgencyName + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	private String certifyingAgencyId;
	private String certifyingAgencyCode;
	private String certifyingAgencyName;
	
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	
	
	
	public String getCertifyingAgencyId() {
		return certifyingAgencyId;
	}
	public void setCertifyingAgencyId(String certifyingAgencyId) {
		this.certifyingAgencyId = certifyingAgencyId;
	}
	public String getCertifyingAgencyCode() {
		return certifyingAgencyCode;
	}
	public void setCertifyingAgencyCode(String certifyingAgencyCode) {
		this.certifyingAgencyCode = certifyingAgencyCode;
	}
	public String getCertifyingAgencyName() {
		return certifyingAgencyName;
	}
	public void setCertifyingAgencyName(String certifyingAgencyName) {
		this.certifyingAgencyName = certifyingAgencyName;
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

	
	
	
}
