package com.infotech.skills.skillsReport.piaSummaryReport;

import org.apache.struts.validator.ValidatorForm;

public class TrainingDurationForm extends ValidatorForm {
	
	private String piaName;
	private String projectName;
	private String sectorName;
	private String tradeName;
	private String certifyingAgencyName;
	
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
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public String getCertifyingAgencyName() {
		return certifyingAgencyName;
	}
	public void setCertifyingAgencyName(String certifyingAgencyName) {
		this.certifyingAgencyName = certifyingAgencyName;
	}
 
}
