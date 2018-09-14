package com.infotech.skills.skillsReport.piaSummaryReport;

import org.apache.struts.validator.ValidatorForm;

public class TrainingInformationForm extends ValidatorForm
{
	private String piaName;
	private String projectName;
	private String stateName;
	private String districtName;
	private String trainingcode;
	private String trainingcentername;
	
	public String getTrainingcode()
	{
		return trainingcode;
	}
	public String getTrainingcentername() 
	{
		return trainingcentername;
	}
	public void setTrainingcentername(String trainingcentername) {
		this.trainingcentername = trainingcentername;
	}
	public void setTrainingcode(String trainingcode) {
		this.trainingcode = trainingcode;
	}
	public String getPiaName() {
		return piaName;
	}
	public void setPiaName(String piaName) {
		this.piaName = piaName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}
