package com.infotech.skills.skillsReport.piaSummaryReport;

import org.apache.struts.validator.ValidatorForm;

public class TargetSummaryForm extends ValidatorForm{
	
	private String piaName;
	private String projectName;
	
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

}
