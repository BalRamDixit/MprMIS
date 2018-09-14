package com.infotech.sgsy.stateSanctionDetail;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.util.DateUtil;

public class StateSanctionVO   implements Serializable  {
	
	@Override
	public String toString() {
		return "StateSanctionVO [id=" + id + ", stateId=" + state.getStateId() + ", targetStartedYear=" + targetStartedYear
				+ ", targetEndYear=" + targetEndYear + ", sanctionedTrainingTarget=" + sanctionedTrainingTarget
				+ ", fundSanctioned=" + fundSanctioned + ", sanctionDate=" + sanctionDate + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	private String id;
	//private String stateId;
	private StateVO state;
 	private String targetStartedYear;
	private String targetEndYear;
	private long sanctionedTrainingTarget;
	private long fundSanctioned;
	private Date sanctionDate;	
	private String createdBy;
	private Date createdOn;
	private String updatedBy ;
	private Date updatedOn ;
	private long supportCost ; 
	//...new added feilds....
	
	private long skillGapAssessment ; 
	private long iEC ; 
	private long alumniSupport ; 
	private long capacityBuilding ; 
	private long monitoringEvaluation ; 
	private long staffBlockLevelBelow ; 
	private long jobMela ; 
	private long migrationSupportCentre ; 
	
	private String ifscCode;
	private String bankName;
	private String accountNo;
	private String agencyCode;
	private String pfms;
	
	//..ends here ....
	
	public String getCreatedBy() {
		return createdBy;
	}
	public long getSkillGapAssessment() {
		return skillGapAssessment;
	}
    public void setSkillGapAssessment(Long skillGapAssessment) {
    	if(skillGapAssessment==null){
    		skillGapAssessment=0L;
    	}
		this.skillGapAssessment = skillGapAssessment;
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

	public long getSupportCost() {
		return supportCost;
	}
    public void setSupportCost(Long supportCost) {
    	if(supportCost==null){
    		supportCost=0L;
    	}
		this.supportCost = supportCost;
	}

    public long getiEC() {
		return iEC;
	}
    public void setiEC(Long iEC) {
    	if(iEC==null){
    		iEC=0L;
    	}
		this.iEC = iEC;
	}
    public long getAlumniSupport() {
		return alumniSupport;
	}
    public void setAlumniSupport(Long alumniSupport) {
    	if(alumniSupport==null){
    		alumniSupport=0L;
    	}
		this.alumniSupport = alumniSupport;
	}
    public long getCapacityBuilding() {
		return capacityBuilding;
	}
    public void setCapacityBuilding(Long capacityBuilding) {
    	if(capacityBuilding==null){
    		capacityBuilding=0L;
    	}
		this.capacityBuilding = capacityBuilding;
	}
   
    public long getMonitoringEvaluation() {
		return monitoringEvaluation;
	}
	public void setMonitoringEvaluation(Long monitoringEvaluation) {
		if(monitoringEvaluation==null){
			monitoringEvaluation=0L;
		}
		this.monitoringEvaluation = monitoringEvaluation;
	}
	public long getStaffBlockLevelBelow() {
		return staffBlockLevelBelow;
	}
	public void setStaffBlockLevelBelow(Long staffBlockLevelBelow) {
		if(staffBlockLevelBelow==null){
			staffBlockLevelBelow=0L;
		}
		this.staffBlockLevelBelow = staffBlockLevelBelow;
	}
	public long getJobMela() {
		return jobMela;
	}
	public void setJobMela(Long jobMela) {
		if(jobMela==null){
			jobMela=0L;
		}
		this.jobMela = jobMela;
	}
	public long getMigrationSupportCentre() {
		return migrationSupportCentre;
	}
	public void setMigrationSupportCentre(Long migrationSupportCentre) {
		if(migrationSupportCentre==null){
			migrationSupportCentre=0L;
		}
		this.migrationSupportCentre = migrationSupportCentre;
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
	public String getTargetStartedYear() {
		return targetStartedYear;
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
	public void setSanctionedTrainingTarget(Long sanctionedTrainingTarget) {
		if(sanctionedTrainingTarget==null){
			sanctionedTrainingTarget=0L;
		}
		this.sanctionedTrainingTarget = sanctionedTrainingTarget;
	}
	public long getFundSanctioned() {
		return fundSanctioned;
	}
	public void setFundSanctioned(long fundSanctioned) {
		this.fundSanctioned = fundSanctioned;
	}
	public Date getSanctionDate() {
		return sanctionDate;
	}
	
	public void setSanctionDate(Object sanctionDate) {
		if(sanctionDate instanceof String){
			this.sanctionDate = new DateUtil().Correct(sanctionDate.toString());
		}
		else{
			this.sanctionDate =(Date) sanctionDate;
		}		
	}
	
	public StateVO getState() {
		return state;
	}
	public void setState(StateVO state) {
		this.state = state;
	}
	/*public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
	