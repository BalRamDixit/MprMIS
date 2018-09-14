package com.infotech.sgsy.stateSetupTarget;






import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * @author nic
 *
 */
public class StateTargetForm extends ActionForm{
	

    private String id;
	private String stateId;
	private String stateName;
	
	private String userId;
		
	private String scheme;
	private String annualPlan;
	private String ctsa;
	private String appraisalAgency;
	private String tsaName;
	private String stateMis;
	private String applicationName;
	private String sopCompliant;
	private String centralMis;
		
	private String createdBy;
	private String updatedBy;
	private Date createdOn;
	private Date updatedOn;
	
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public String getCtsa() {
		return ctsa;
	}
	public void setCtsa(String ctsa) {
		this.ctsa = ctsa;
	}
	public String getAppraisalAgency() {
		return appraisalAgency;
	}
	public void setAppraisalAgency(String appraisalAgency) {
		this.appraisalAgency = appraisalAgency;
	}
	public String getTsaName() {
		return tsaName;
	}
	public void setTsaName(String tsaName) {
		this.tsaName = tsaName;
	}
	public String getStateMis() {
		return stateMis;
	}
	public void setStateMis(String stateMis) {
		this.stateMis = stateMis;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getSopCompliant() {
		return sopCompliant;
	}
	public void setSopCompliant(String sopCompliant) {
		this.sopCompliant = sopCompliant;
	}
	public String getCentralMis() {
		return centralMis;
	}
	public void setCentralMis(String centralMis) {
		this.centralMis = centralMis;
	}
	public String getAnnualPlan() {
		return annualPlan;
	}
	public void setAnnualPlan(String annualPlan) {
		this.annualPlan = annualPlan;
	}
	
}
