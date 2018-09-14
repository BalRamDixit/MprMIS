package com.infotech.sgsy.state.monitor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;

public class MonitorThirdInstActionForm extends MasterForm {

	 private int [] id;
	 private String stateName;
	 private String [] projectId;
	
	/*private String entryYear;
	private String entryMonth;*/
	
	 private String [] issuanceDate;
	 private String [] installmentRectoFd;
	 private String [] receiptDatePiaClaim;
	 private int [] amountClaimed;
	 private String [] amountReleased;
	 private String [] releasedDate;
	 private String [] claimStatus;
	 private String [] remarks;
	    
//	 private String entityCode;
	 private String createdOnDate;
	 private String updatedOnDate;
	 
	 private String installmentMapping;
	 
	public int[] getId() {
		return id;
	}
	public void setId(int[] id) {
		this.id = id;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String[] getProjectId() {
		return projectId;
	}
	public void setProjectId(String[] projectId) {
		this.projectId = projectId;
	}
	public String[] getIssuanceDate() {
		return issuanceDate;
	}
	public void setIssuanceDate(String[] issuanceDate) {
		this.issuanceDate = issuanceDate;
	}
	public String[] getInstallmentRectoFd() {
		return installmentRectoFd;
	}
	public void setInstallmentRectoFd(String[] installmentRectoFd) {
		this.installmentRectoFd = installmentRectoFd;
	}
	public String[] getReceiptDatePiaClaim() {
		return receiptDatePiaClaim;
	}
	public void setReceiptDatePiaClaim(String[] receiptDatePiaClaim) {
		this.receiptDatePiaClaim = receiptDatePiaClaim;
	}
	public int[] getAmountClaimed() {
		return amountClaimed;
	}
	public void setAmountClaimed(int[] amountClaimed) {
		this.amountClaimed = amountClaimed;
	}
	public String[] getAmountReleased() {
		return amountReleased;
	}
	public void setAmountReleased(String[] amountReleased) {
		this.amountReleased = amountReleased;
	}
	public String[] getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(String[] releasedDate) {
		this.releasedDate = releasedDate;
	}
	public String[] getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String[] claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
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
	 
	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.setAmountClaimed(null);
		this.setAmountReleased(null);
		this.setClaimStatus(null);

	this.setIssuanceDate(null);
	this.setReceiptDatePiaClaim(null);
	this.setReleasedDate(null);
	this.setRemarks(null);
	this.setInstallmentRectoFd(null);
	}
	public String getInstallmentMapping() {
		return installmentMapping;
	}
	public void setInstallmentMapping(String installmentMapping) {
		this.installmentMapping = installmentMapping;
	}
	
	
    
   
}
