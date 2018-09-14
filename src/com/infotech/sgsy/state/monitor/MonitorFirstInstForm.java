package com.infotech.sgsy.state.monitor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;
public class MonitorFirstInstForm extends MasterForm {

	private String [] id;
	/*private String  entryYear;
	private String  entryMonth;*/
	private String stateName;
	private String[] projectId;
	
	private long [] relAmount;
	private String [] relDate;
	private String [] remarks;
	
	private String [] dateofIssuance;
	private String [] dateofRecommend;
	private String [] dateofReceipt;
	private long [] amountClaimed;
	private String [] statusofClaim;
	private String installmentMapping;
	private String[] utilizationPercent;
	
	
	private String createdOnDate;
	private String updatedOnDate;
	private Integer installmentNo;
	
	public String[] getProjectId() {
		return projectId;
	}
	public void setProjectId(String[] projectId) {
		this.projectId = projectId;
	}
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	

	
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	
	public String[] getRelDate() {
		return relDate;
	}
	public void setRelDate(String[] relDate) {
		this.relDate = relDate;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	/*public String getEntityCode() {
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
	public String[] getDateofIssuance() {
		return dateofIssuance;
	}
	public void setDateofIssuance(String[] dateofIssuance) {
		this.dateofIssuance = dateofIssuance;
	}
	public String[] getDateofRecommend() {
		return dateofRecommend;
	}
	public void setDateofRecommend(String[] dateofRecommend) {
		this.dateofRecommend = dateofRecommend;
	}
	public String[] getDateofReceipt() {
		return dateofReceipt;
	}
	public void setDateofReceipt(String[] dateofReceipt) {
		this.dateofReceipt = dateofReceipt;
	}
	public String[] getStatusofClaim() {
		return statusofClaim;
	}
	public void setStatusofClaim(String[] statusofClaim) {
		this.statusofClaim = statusofClaim;
	}
	
	public String getInstallmentMapping() {
		return installmentMapping;
	}
	public long[] getRelAmount() {
		return relAmount;
	}
	public void setRelAmount(long[] relAmount) {
		this.relAmount = relAmount;
	}
	public long[] getAmountClaimed() {
		return amountClaimed;
	}
	public void setAmountClaimed(long[] amountClaimed) {
		this.amountClaimed = amountClaimed;
	}
	public void setInstallmentMapping(String installmentMapping) {
		this.installmentMapping = installmentMapping;
	}
	
	public Integer getInstallmentNo() {
		return installmentNo;
	}
	public void setInstallmentNo(Integer installmentNo) {
		this.installmentNo = installmentNo;
	}
	
	public String[] getUtilizationPercent() {
		return utilizationPercent;
	}
	public void setUtilizationPercent(String[] utilizationPercent) {
		this.utilizationPercent = utilizationPercent;
	}
	
}