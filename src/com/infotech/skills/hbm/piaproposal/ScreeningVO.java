package com.infotech.skills.hbm.piaproposal;
import java.io.Serializable;

public class ScreeningVO implements Serializable {
	private String piaCode;
	private String proposalCode;
	private String methodology;
	private String criteria;
	private String tools;
	private String interview;
	private String discussion;
	private String hiring;
	private String anyOtherInform;
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
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}
	public String getMethodology() {
		return methodology;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public String getTools() {
		return tools;
	}
	public void setInterview(String interview) {
		this.interview = interview;
	}
	public String getInterview() {
		return interview;
	}
	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}
	public String getDiscussion() {
		return discussion;
	}
	public void setHiring(String hiring) {
		this.hiring = hiring;
	}
	public String getHiring() {
		return hiring;
	}
	public void setAnyOtherInform(String anyOtherInform) {
		this.anyOtherInform = anyOtherInform;
	}
	public String getAnyOtherInform() {
		return anyOtherInform;
	}

}
