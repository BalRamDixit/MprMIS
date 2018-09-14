package com.infotech.sgsy.masterdata.tradeMaster;

import java.util.Date;

import com.infotech.sgsy.master.assessmentBodyMaster.AssessmentBodyVO;
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterAction;
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;

public class TradeMasterVO {
	private String tradeId;	
	private String editId;
	private String tradeCode;
	private String tradeName;	 
	private Integer courseDuration;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;	
    private SectorMasterVO sectorId ;	
	private AssessmentBodyVO assessmentBodyId ;	
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
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
	public Integer getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}
	public SectorMasterVO getSectorId() {
		return sectorId;
	}
	public void setSectorId(SectorMasterVO sectorId) {
		this.sectorId = sectorId;
	}
	public AssessmentBodyVO getAssessmentBodyId() {
		return assessmentBodyId;
	}
	public void setAssessmentBodyId(AssessmentBodyVO assessmentBodyId) {
		this.assessmentBodyId = assessmentBodyId;
	}
	
	 
	
}
