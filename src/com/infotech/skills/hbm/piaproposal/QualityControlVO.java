package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

public class QualityControlVO implements Serializable {

	private String piaCode;
	private String praposalCode;
	private String interConProcedures;
	private String documPolicy;
	private String orgPolicy;
	private String useOfSoftware;
	private String benchMarks;
	private String ControlProject ;
	private String accountPolicies;
	private String otherDetails;
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	
	public QualityControlVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QualityControlVO(String piaCode, String praposalCode,
			String interConProcedures, String documPolicy, String orgPolicy,
			String useOfSoftware, String benchMarks, String controlProject,
			String accountPolicies, String otherDetails, String createdOn,
			String createdBy, String modifyOn, String modifyBy) {
		super();
		this.piaCode = piaCode;
		this.praposalCode = praposalCode;
		this.interConProcedures = interConProcedures;
		this.documPolicy = documPolicy;
		this.orgPolicy = orgPolicy;
		this.useOfSoftware = useOfSoftware;
		this.benchMarks = benchMarks;
		ControlProject = controlProject;
		this.accountPolicies = accountPolicies;
		this.otherDetails = otherDetails;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifyOn = modifyOn;
		this.modifyBy = modifyBy;
	}

	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getPraposalCode() {
		return praposalCode;
	}

	public void setPraposalCode(String praposalCode) {
		this.praposalCode = praposalCode;
	}

	public String getInterConProcedures() {
		return interConProcedures;
	}

	public void setInterConProcedures(String interConProcedures) {
		this.interConProcedures = interConProcedures;
	}

	public String getDocumPolicy() {
		return documPolicy;
	}

	public void setDocumPolicy(String documPolicy) {
		this.documPolicy = documPolicy;
	}

	public String getOrgPolicy() {
		return orgPolicy;
	}

	public void setOrgPolicy(String orgPolicy) {
		this.orgPolicy = orgPolicy;
	}

	public String getUseOfSoftware() {
		return useOfSoftware;
	}

	public void setUseOfSoftware(String useOfSoftware) {
		this.useOfSoftware = useOfSoftware;
	}

	public String getBenchMarks() {
		return benchMarks;
	}

	public void setBenchMarks(String benchMarks) {
		this.benchMarks = benchMarks;
	}

	public String getControlProject() {
		return ControlProject;
	}

	public void setControlProject(String controlProject) {
		ControlProject = controlProject;
	}

	public String getAccountPolicies() {
		return accountPolicies;
	}

	public void setAccountPolicies(String accountPolicies) {
		this.accountPolicies = accountPolicies;
	}

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
}
