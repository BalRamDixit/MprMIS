package com.infotech.sgsy.state.monitor;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;

public class MonitorFirstInstVO implements Serializable  {
	
	@Override
	public String toString() {
		return "MonitorFirstInstVO [projectId=" + projectId + "]";
	}
	public MonitorFirstInstVO() {
		super();
	}
	public MonitorFirstInstVO(String projectId,String stateName,  String remarks) {
		super();
		this.stateName = stateName;
	//	this.projectId = projectId;
		this.remarks =   remarks;
	}
	private String id;
	
	private String stateName;
	private ProjectDetailsVO projectId;
	private long relAmount;
	private Date  relDate;
	private Date dateofIssuance;
	private Date dateofRecommend;
	private Date dateofReceipt;
	private String statusofClaim;
	private String  remarks;
	private String installmentNo;
	private Date createdOnDate;
	private long amountClaimed;
	private String utilizationPercent;
		
	
	private String relAmountHidden;
	private String reldateHidden;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	
	
	
	
	public String getRelAmountHidden() {
		return relAmountHidden;
	}
	public void setRelAmountHidden(String relAmountHidden) {
		this.relAmountHidden = relAmountHidden;
	}
	public String getReldateHidden() {
		return reldateHidden;
	}
	public void setReldateHidden(String reldateHidden) {
		this.reldateHidden = reldateHidden;
	}
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public ProjectDetailsVO getProjectId() {
		return projectId;
	}
	public void setProjectId(ProjectDetailsVO projectId) {
		this.projectId = projectId;
	}
	
	public long getRelAmount() {
		return relAmount;
	}
	public void setRelAmount(long relAmount) {
		this.relAmount = relAmount;
	}
	public Date getRelDate() {
		return relDate;
	}
	public void setRelDate(Object relDate) {
		if(relDate instanceof String){
			this.relDate = new DateUtil().Correct(relDate.toString());
		}else{
			this.relDate=(Date) relDate;
		}
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	private Date updatedOnDate;
	public Date getDateofIssuance() {
		return dateofIssuance;
	}
	public void setDateofIssuance(Object dateofIssuance) {
		if(dateofIssuance instanceof String){
			this.dateofIssuance = new DateUtil().Correct(dateofIssuance.toString());
		}else{
			this.dateofIssuance=(Date) dateofIssuance;
		}
	}
	public Date getDateofRecommend() {
		return dateofRecommend;
	}
	public void setDateofRecommend(Object dateofRecommend) {
		if(dateofRecommend instanceof String){
			this.dateofRecommend = new DateUtil().Correct(dateofRecommend.toString());
		}else{
			this.dateofRecommend=(Date) dateofRecommend;
		}
	}
	public Date getDateofReceipt() {
		return dateofReceipt;
	}
	public void setDateofReceipt(Object dateofReceipt) {
		if(dateofReceipt instanceof String){
			this.dateofReceipt = new DateUtil().Correct(dateofReceipt.toString());
		}else{
			this.dateofReceipt=(Date) dateofReceipt;
		}
	}
	public String getStatusofClaim() {
		return statusofClaim;
	}
	public void setStatusofClaim(String statusofClaim) {
		this.statusofClaim = statusofClaim;
	}
	public String getInstallmentNo() {
		return installmentNo;
	}
	public void setInstallmentNo(String installmentNo) {
		this.installmentNo = installmentNo;
	}
	
	public long getAmountClaimed() {
		return amountClaimed;
	}
	public void setAmountClaimed(long amountClaimed) {
		this.amountClaimed = amountClaimed;
	}
	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}
	public void setDateofIssuance(Date dateofIssuance) {
		this.dateofIssuance = dateofIssuance;
	}
	public void setDateofRecommend(Date dateofRecommend) {
		this.dateofRecommend = dateofRecommend;
	}
	public void setDateofReceipt(Date dateofReceipt) {
		this.dateofReceipt = dateofReceipt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUtilizationPercent() {
		return utilizationPercent;
	}
	public void setUtilizationPercent(String utilizationPercent) {
		this.utilizationPercent = utilizationPercent;
	}
	
	
}
