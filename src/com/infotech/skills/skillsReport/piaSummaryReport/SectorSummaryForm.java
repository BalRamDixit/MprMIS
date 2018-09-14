package com.infotech.skills.skillsReport.piaSummaryReport;

import org.apache.struts.validator.ValidatorForm;



public class SectorSummaryForm extends ValidatorForm{
	private String piaName;
	private String projectName;
	private String sectorName;
	private String tradeName;
	private String financialYear;
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

	
	
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getSectorName() {
		return sectorName;
	}

}
