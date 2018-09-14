package com.infotech.skills.hbm.piaproposal;
import java.io.Serializable;
public class RationaleVO implements Serializable {
	private String piaCode;
	private String proposalCode;
	private String strength;
	private String geographicExistence;
	private String marketScan;
	private String thirdPartyScan;
	private String others;
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
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getStrength() {
		return strength;
	}
	public void setGeographicExistence(String geographicExistence) {
		this.geographicExistence = geographicExistence;
	}
	public String getGeographicExistence() {
		return geographicExistence;
	}
	public void setMarketScan(String marketScan) {
		this.marketScan = marketScan;
	}
	public String getMarketScan() {
		return marketScan;
	}
	public void setThirdPartyScan(String thirdPartyScan) {
		this.thirdPartyScan = thirdPartyScan;
	}
	public String getThirdPartyScan() {
		return thirdPartyScan;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getOthers() {
		return others;
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
