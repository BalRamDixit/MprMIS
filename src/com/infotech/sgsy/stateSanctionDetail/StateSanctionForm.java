package com.infotech.sgsy.stateSanctionDetail;

import com.infotech.sgsy.common.MasterForm;

public class StateSanctionForm extends MasterForm {

	private String id;
	private String stateId;	 	
	private String targetStartedYear;
	private String targetEndYear;
	private long sanctionedTrainingTarget;
	private long fundSanctioned;
	private String sanctionDate;
	
	//...new added feilds....
	
	private long skillGapAssessment ; 
	private long iEC ; 
	private long alumniSupport ; 
	private long capacityBuilding ; 
	private long monitoringEvaluation ; 
	private long staffBlockLevelBelow ; 
	private long jobMela ; 
	private long migrationSupportCentre ; 
	private long supportCost ; 
	
	private String ifscCode;
	private String bankName;
	private String accountNo;
	private String agencyCode;
	private String pfms;
//	private String projectName;
	
	//..ends here ....
	 
 
	public long getSupportCost() {
		return supportCost;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public String getAgencyCode() {
		return agencyCode;
	}


	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}


	public String getPfms() {
		return pfms;
	}


	public void setPfms(String pfms) {
		this.pfms = pfms;
	}


	/*public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
*/

	public void setSupportCost(long supportCost) {
		this.supportCost = supportCost;
	}


	public String getTargetStartedYear() {
		return targetStartedYear;
	}
	
	
	public long getSkillGapAssessment() {
		return skillGapAssessment;
	}


	public void setSkillGapAssessment(long skillGapAssessment) {
		this.skillGapAssessment = skillGapAssessment;
	}


	public long getiEC() {
		return iEC;
	}


	public void setiEC(long iEC) {
		this.iEC = iEC;
	}


	public long getAlumniSupport() {
		return alumniSupport;
	}


	public void setAlumniSupport(long alumniSupport) {
		this.alumniSupport = alumniSupport;
	}


	public long getCapacityBuilding() {
		return capacityBuilding;
	}


	public void setCapacityBuilding(long capacityBuilding) {
		this.capacityBuilding = capacityBuilding;
	}


	public long getMonitoringEvaluation() {
		return monitoringEvaluation;
	}


	public void setMonitoringEvaluation(long monitoringEvaluation) {
		this.monitoringEvaluation = monitoringEvaluation;
	}


	public long getStaffBlockLevelBelow() {
		return staffBlockLevelBelow;
	}


	public void setStaffBlockLevelBelow(long staffBlockLevelBelow) {
		this.staffBlockLevelBelow = staffBlockLevelBelow;
	}


	public long getJobMela() {
		return jobMela;
	}


	public void setJobMela(long jobMela) {
		this.jobMela = jobMela;
	}


	public long getMigrationSupportCentre() {
		return migrationSupportCentre;
	}


	public void setMigrationSupportCentre(long migrationSupportCentre) {
		this.migrationSupportCentre = migrationSupportCentre;
	}


	public void setTargetStartedYear(String targetStartedYear) {
		this.targetStartedYear = targetStartedYear;
	}
	public String getTargetEndYear() {
		return targetEndYear;
	}
	public void setTargetEndYear(String targetEndYear) {
		this.targetEndYear = targetEndYear;
	}
	
	public long getSanctionedTrainingTarget() {
		return sanctionedTrainingTarget;
	}
	public void setSanctionedTrainingTarget(long sanctionedTrainingTarget) {
		this.sanctionedTrainingTarget = sanctionedTrainingTarget;
	}
	public long getFundSanctioned() {
		return fundSanctioned;
	}
	public void setFundSanctioned(long fundSanctioned) {
		this.fundSanctioned = fundSanctioned;
	}
	public String getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
