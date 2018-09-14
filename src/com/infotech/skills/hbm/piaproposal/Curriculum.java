package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

import sun.security.util.BigInt;

public class Curriculum implements Serializable{
	
	private BigInt id;
	private String proposedTrade;
	private String proposedCourse;
	private String totalTraininDays;
	private String totalTraininHours;
	private String ojtDays;
	private String daysExcludRecreation;
	private String softSkillsTraining;
	private String computerTraining;
	private String practicalTraining;
	private String strategyDevelopment;
	private String certificationObtained;
	private String createdOn;
	
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	
	public Curriculum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curriculum(BigInt id,String proposedTrade, String proposedCourse,
			String totalTraininDays, String totalTraininHours, String ojtDays,
			String daysExcludRecreation, String softSkillsTraining,
			String computerTraining, String practicalTraining,
			String strategyDevelopment, String certificationObtained,
			String createdOn, String createdBy, String modifyOn, String modifyBy) {
		super();
		this.proposedTrade = proposedTrade;
		this.proposedCourse = proposedCourse;
		this.totalTraininDays = totalTraininDays;
		this.totalTraininHours = totalTraininHours;
		this.ojtDays = ojtDays;
		this.daysExcludRecreation = daysExcludRecreation;
		this.softSkillsTraining = softSkillsTraining;
		this.computerTraining = computerTraining;
		this.practicalTraining = practicalTraining;
		this.strategyDevelopment = strategyDevelopment;
		this.certificationObtained = certificationObtained;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifyOn = modifyOn;
		this.modifyBy = modifyBy;
	}

	public String getProposedTrade() {
		return proposedTrade;
	}

	public void setProposedTrade(String proposedTrade) {
		this.proposedTrade = proposedTrade;
	}

	public String getProposedCourse() {
		return proposedCourse;
	}

	public void setProposedCourse(String proposedCourse) {
		this.proposedCourse = proposedCourse;
	}

	public String getTotalTraininDays() {
		return totalTraininDays;
	}

	public void setTotalTraininDays(String totalTraininDays) {
		this.totalTraininDays = totalTraininDays;
	}

	public String getTotalTraininHours() {
		return totalTraininHours;
	}

	public void setTotalTraininHours(String totalTraininHours) {
		this.totalTraininHours = totalTraininHours;
	}

	public String getOjtDays() {
		return ojtDays;
	}

	public void setOjtDays(String ojtDays) {
		this.ojtDays = ojtDays;
	}

	public String getDaysExcludRecreation() {
		return daysExcludRecreation;
	}

	public void setDaysExcludRecreation(String daysExcludRecreation) {
		this.daysExcludRecreation = daysExcludRecreation;
	}

	public String getSoftSkillsTraining() {
		return softSkillsTraining;
	}

	public void setSoftSkillsTraining(String softSkillsTraining) {
		this.softSkillsTraining = softSkillsTraining;
	}

	public String getComputerTraining() {
		return computerTraining;
	}

	public void setComputerTraining(String computerTraining) {
		this.computerTraining = computerTraining;
	}

	public String getPracticalTraining() {
		return practicalTraining;
	}

	public void setPracticalTraining(String practicalTraining) {
		this.practicalTraining = practicalTraining;
	}

	public String getStrategyDevelopment() {
		return strategyDevelopment;
	}

	public void setStrategyDevelopment(String strategyDevelopment) {
		this.strategyDevelopment = strategyDevelopment;
	}

	public String getCertificationObtained() {
		return certificationObtained;
	}

	public void setCertificationObtained(String certificationObtained) {
		this.certificationObtained = certificationObtained;
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

	public void setId(BigInt id) {
		this.id = id;
	}

	public BigInt getId() {
		return id;
	}
	 
}
