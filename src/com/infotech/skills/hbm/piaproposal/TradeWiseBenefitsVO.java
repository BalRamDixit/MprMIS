package com.infotech.skills.hbm.piaproposal;
import java.io.Serializable;
public class TradeWiseBenefitsVO implements Serializable {
	private String piaCode;
	private String proposalCode;
	private String sector;
	private String trades;
	private String targetedBeneficiaries;
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getPiaCode() {
		return piaCode;
	}
	public void setProposalCode(String proposalCode) {
		this.proposalCode = proposalCode;
	}
	public String getProposalCode() {
		return proposalCode;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getSector() {
		return sector;
	}
	public void setTrades(String trades) {
		this.trades = trades;
	}
	public String getTrades() {
		return trades;
	}
	public void setTargetedBeneficiaries(String targetedBeneficiaries) {
		this.targetedBeneficiaries = targetedBeneficiaries;
	}
	public String getTargetedBeneficiaries() {
		return targetedBeneficiaries;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	
	

}
