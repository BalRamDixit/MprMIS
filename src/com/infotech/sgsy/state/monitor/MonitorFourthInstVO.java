package com.infotech.sgsy.state.monitor;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class MonitorFourthInstVO extends MasterVO implements Serializable{
	
	 private int  id;
	 private String stateName;
	 private String projectId;
	
	/*private String entryYear;
	private String entryMonth;*/
	 
	private String  issuanceDate;
    private String  installmentRectoFd;
    private String  receiptDatePiaClaim;
    private int amountClaimed;
    private String amountReleased;
    private String releasedDate;
    private String  claimStatus;
    private String  remarks;
   
 //   private String entityCode;
    private String createdOnDate;
	private String updatedOnDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getIssuanceDate() {
		return issuanceDate;
	}
	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}
	public String getInstallmentRectoFd() {
		return installmentRectoFd;
	}
	public void setInstallmentRectoFd(String installmentRectoFd) {
		this.installmentRectoFd = installmentRectoFd;
	}
	public String getReceiptDatePiaClaim() {
		return receiptDatePiaClaim;
	}
	public void setReceiptDatePiaClaim(String receiptDatePiaClaim) {
		this.receiptDatePiaClaim = receiptDatePiaClaim;
	}
	public int getAmountClaimed() {
		return amountClaimed;
	}
	public void setAmountClaimed(int amountClaimed) {
		this.amountClaimed = amountClaimed;
	}
	public String getAmountReleased() {
		return amountReleased;
	}
	public void setAmountReleased(String amountReleased) {
		this.amountReleased = amountReleased;
	}
	public String getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
/*	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}*/
	public String getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public String getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(String updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

}
