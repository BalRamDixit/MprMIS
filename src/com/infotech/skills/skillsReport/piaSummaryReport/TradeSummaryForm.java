package com.infotech.skills.skillsReport.piaSummaryReport;

import org.apache.struts.validator.ValidatorForm;

public class TradeSummaryForm extends ValidatorForm {
	
	private String piaName;
	private String projectName;
	private String trainingCenterName;
	private String sectorName;
	private String tradeName;
	
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
	public void setTrainingCenterName(String trainingCenterName) {
		this.trainingCenterName = trainingCenterName;
	}
	public String getTrainingCenterName() {
		return trainingCenterName;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	
}
