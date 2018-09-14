package com.infotech.sgsy.projectSetup.tradeTarget;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class TradeTargetVO  implements Serializable {

	
	private int id;	
    private ProjectDetailsVO project;
	private SectorMasterVO sector;
	private TradeMasterVO trade;
	private String otherTrade;
	private String totalTradeDuration;
	private String ojt;
	private int trainingTarget;
	private String nonResiFull;
	private String nonResiPart;
	private String nonResiWeekend;
	private String resiFull;
	private Integer saveFlag;       // value should be 1 or 0 (1 for all data saved || 0 for data not saved)
	
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	
	
	
	
	public ProjectDetailsVO getProject() {
		return project;
	}
	public void setProject(ProjectDetailsVO project) {
		this.project = project;
	}
	public SectorMasterVO getSector() {
		return sector;
	}
	public void setSector(SectorMasterVO sector) {
		this.sector = sector;
	}
	public TradeMasterVO getTrade() {
		return trade;
	}
	public void setTrade(TradeMasterVO trade) {
		this.trade = trade;
	}
	public String getOtherTrade() {
		return otherTrade;
	}
	public void setOtherTrade(String otherTrade) {
		this.otherTrade = otherTrade;
	}
	public String getTotalTradeDuration() {
		return totalTradeDuration;
	}
	public void setTotalTradeDuration(String totalTradeDuration) {
		this.totalTradeDuration = totalTradeDuration;
	}
	public String getOjt() {
		return ojt;
	}
	public void setOjt(String ojt) {
		this.ojt = ojt;
	}
	
	
	public int getTrainingTarget() {
		return trainingTarget;
	}
	public void setTrainingTarget(Integer trainingTarget) {
		if(trainingTarget==null){
			trainingTarget=0;
		}
		this.trainingTarget = trainingTarget;
	}
	public String getNonResiFull() {
		return nonResiFull;
	}
	public void setNonResiFull(String nonResiFull) {
		this.nonResiFull = nonResiFull;
	}
	public String getNonResiPart() {
		return nonResiPart;
	}
	public void setNonResiPart(String nonResiPart) {
		this.nonResiPart = nonResiPart;
	}
	public String getNonResiWeekend() {
		return nonResiWeekend;
	}
	public void setNonResiWeekend(String nonResiWeekend) {
		this.nonResiWeekend = nonResiWeekend;
	}
	public String getResiFull() {
		return resiFull;
	}
	public void setResiFull(String resiFull) {
		this.resiFull = resiFull;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Integer getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(Integer saveFlag) {
		this.saveFlag = saveFlag;
	}
	
	
}
