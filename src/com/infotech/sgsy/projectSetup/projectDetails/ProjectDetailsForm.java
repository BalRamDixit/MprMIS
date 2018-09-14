package com.infotech.sgsy.projectSetup.projectDetails;

import org.apache.struts.action.ActionForm;

public class ProjectDetailsForm extends ActionForm {	 
	
	public String id;

	public String fileNumber;
	public String piaPrn;
	public String piaName;
	public String projectScheme;
	/*this is use as CTSA*/
	public String projectType;
	public String projectID;
	public long totalProjectCost;
	public long centralShare;
	public long stateShare;
	public long programCost;
	public Integer welfareCost;
	public String consortium;
	public String prnOfConsortiumPartner;
	public String prnConsortiumPartnerName;
	public String partnerApplicantPiaSame;
	public String dateOfEc;	
	public String sanctioned;	
	
	
	
	
	
	
	 
	 
	 
	public String getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	public String getPiaPrn() {
		return piaPrn;
	}
	public void setPiaPrn(String piaPrn) {
		this.piaPrn = piaPrn;
	}
	public String getPiaName() {
		return piaName;
	}
	public void setPiaName(String piaName) {
		this.piaName = piaName;
	}
	public String getProjectScheme() {
		return projectScheme;
	}
	public void setProjectScheme(String projectScheme) {
		this.projectScheme = projectScheme;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	
	 
	
	public long getTotalProjectCost() {
		return totalProjectCost;
	}
	public void setTotalProjectCost(long totalProjectCost) {
		this.totalProjectCost = totalProjectCost;
	}
	public long getCentralShare() {
		return centralShare;
	}
	public void setCentralShare(long centralShare) {
		this.centralShare = centralShare;
	}
	public long getStateShare() {
		return stateShare;
	}
	public void setStateShare(long stateShare) {
		this.stateShare = stateShare;
	}
	public long getProgramCost() {
		return programCost;
	}
	public void setProgramCost(long programCost) {
		this.programCost = programCost;
	}
	public String getConsortium() {
		return consortium;
	}
	public void setConsortium(String consortium) {
		this.consortium = consortium;
	}
	public String getPrnOfConsortiumPartner() {
		return prnOfConsortiumPartner;
	}
	public void setPrnOfConsortiumPartner(String prnOfConsortiumPartner) {
		this.prnOfConsortiumPartner = prnOfConsortiumPartner;
	}
	public String getPrnConsortiumPartnerName() {
		return prnConsortiumPartnerName;
	}
	public void setPrnConsortiumPartnerName(String prnConsortiumPartnerName) {
		this.prnConsortiumPartnerName = prnConsortiumPartnerName;
	}
	public String getSanctioned() {
		return sanctioned;
	}
	public void setSanctioned(String sanctioned) {
		this.sanctioned = sanctioned;
	}
	public String getPartnerApplicantPiaSame() {
		return partnerApplicantPiaSame;
	}
	public void setPartnerApplicantPiaSame(String partnerApplicantPiaSame) {
		this.partnerApplicantPiaSame = partnerApplicantPiaSame;
	}
	public String getDateOfEc() {
		return dateOfEc;
	}
	public void setDateOfEc(String dateOfEc) {
		this.dateOfEc = dateOfEc;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getWelfareCost() {
		return welfareCost;
	}
	public void setWelfareCost(Integer welfareCost) {
		this.welfareCost = welfareCost;
	}
 
	
	
}
