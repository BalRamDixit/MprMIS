package com.infotech.sgsy.report;

import org.apache.struts.validator.ValidatorForm;

public class ReportMappingBean extends ValidatorForm{
	
	private String sc;
	private String scTotal;
	private String scPercentage;
	private String st;
	private String stTotal;
	private String stPercentage;
	private String general;
	private String generalTotal;
	private String generalPercentage;
	private String scStGeneralTotal;
	private String scStGeneralTotalTarget;
	private String scStGeneralTotalPercentage;
	
	private String women;
	private String womenTotal;
	private String womenPercentage;
	private String men;
	private String menTotal;
	private String menPercentage;
	private String Gendermentotal;
	private String Genderwomentotal;
	private String GendertotalPercentage;
	
	private String minority;
	private String minoritytotal;
	private String minorityTarget;
	private String minorityTargettotal;
	private String minorityPercentage;
	private String minoritytotalPercentage;  
	
	private String monthCompletion;
	private String ppwsCompletion;
	private String achievedCompletion; 
	private String percentageCompletion; 
	
	private String monthCommencement; 
	private String ppwsCommencement; 
	private String achievedCommencement; 
	private String percentageCommencement;
	
	private String monthPlaced; 
	private String ppwsPlaced; 
	private String achievedPlaced; 
	private String percentagePlaced;
	
	private String fileNo; 
	private String ctsa; 
	private String totalTarget; 
	private String totalCost; 
	private String fundReleased; 
	
	private String residential; 
	private String inchargeName; 
	private String inchargemobile; 
	private String statusTC;
	
	private String projectSanction; 
	private String mouSig; 
	private String firstInstallment; 
	private String ProjectCommencement; 
	private String perApproval; 
	private String trainingCommencement; 
	
	private String days20; 
	private String days10; 
	private String days30; 
	private String days15; 
	private String days90;
	
	private String tradeSanctioned; 
	
	private String districtsCovered; 
	
	private String FundreleasedPfms; 
	private String Year; 
	private String Expenditure; 
	private String cumExp; 
	private String Expditure; 
	
	private String totalTrained ;
	private String totalPlaced ;
	
	private String advisoriesCTSA ;
	private String alertReason ;
	private String alertIssueDate; 
	private String alertType;
	

	public String getScStGeneralTotal() {
		return scStGeneralTotal;
	}
	public void setScStGeneralTotal(String scStGeneralTotal) {
		this.scStGeneralTotal = scStGeneralTotal;
	}
	public String getScStGeneralTotalTarget() {
		return scStGeneralTotalTarget;
	}
	public void setScStGeneralTotalTarget(String scStGeneralTotalTarget) {
		this.scStGeneralTotalTarget = scStGeneralTotalTarget;
	}
	public String getScStGeneralTotalPercentage() {
		return scStGeneralTotalPercentage;
	}
	public void setScStGeneralTotalPercentage(String scStGeneralTotalPercentage) {
		this.scStGeneralTotalPercentage = scStGeneralTotalPercentage;
	}
	public String getWomen() {
		return women;
	}
	public void setWomen(String women) {
		this.women = women;
	}
	public String getWomenTotal() {
		return womenTotal;
	}
	public void setWomenTotal(String womenTotal) {
		this.womenTotal = womenTotal;
	}
	public String getWomenPercentage() {
		return womenPercentage;
	}
	public void setWomenPercentage(String womenPercentage) {
		this.womenPercentage = womenPercentage;
	}
	public String getMen() {
		return men;
	}
	public void setMen(String men) {
		this.men = men;
	}
	public String getMenTotal() {
		return menTotal;
	}
	public void setMenTotal(String menTotal) {
		this.menTotal = menTotal;
	}
	public String getMenPercentage() {
		return menPercentage;
	}
	public void setMenPercentage(String menPercentage) {
		this.menPercentage = menPercentage;
	}
	public String getGendermentotal() {
		return Gendermentotal;
	}
	public void setGendermentotal(String gendermentotal) {
		Gendermentotal = gendermentotal;
	}
	public String getGenderwomentotal() {
		return Genderwomentotal;
	}
	public void setGenderwomentotal(String genderwomentotal) {
		Genderwomentotal = genderwomentotal;
	}
	public String getGendertotalPercentage() {
		return GendertotalPercentage;
	}
	public void setGendertotalPercentage(String gendertotalPercentage) {
		GendertotalPercentage = gendertotalPercentage;
	}
	public String getMinority() {
		return minority;
	}
	public void setMinority(String minority) {
		this.minority = minority;
	}
	public String getMinoritytotal() {
		return minoritytotal;
	}
	public void setMinoritytotal(String minoritytotal) {
		this.minoritytotal = minoritytotal;
	}
	public String getMinorityTarget() {
		return minorityTarget;
	}
	public void setMinorityTarget(String minorityTarget) {
		this.minorityTarget = minorityTarget;
	}
	public String getMinorityTargettotal() {
		return minorityTargettotal;
	}
	public void setMinorityTargettotal(String minorityTargettotal) {
		this.minorityTargettotal = minorityTargettotal;
	}
	public String getMinorityPercentage() {
		return minorityPercentage;
	}
	public void setMinorityPercentage(String minorityPercentage) {
		this.minorityPercentage = minorityPercentage;
	}
	public String getMinoritytotalPercentage() {
		return minoritytotalPercentage;
	}
	public void setMinoritytotalPercentage(String minoritytotalPercentage) {
		this.minoritytotalPercentage = minoritytotalPercentage;
	}
	public String getMonthCompletion() {
		return monthCompletion;
	}
	public void setMonthCompletion(String monthCompletion) {
		this.monthCompletion = monthCompletion;
	}
	public String getPpwsCompletion() {
		return ppwsCompletion;
	}
	public void setPpwsCompletion(String ppwsCompletion) {
		this.ppwsCompletion = ppwsCompletion;
	}
	public String getAchievedCompletion() {
		return achievedCompletion;
	}
	public void setAchievedCompletion(String achievedCompletion) {
		this.achievedCompletion = achievedCompletion;
	}
	public String getPercentageCompletion() {
		return percentageCompletion;
	}
	public void setPercentageCompletion(String percentageCompletion) {
		this.percentageCompletion = percentageCompletion;
	}
	public String getMonthCommencement() {
		return monthCommencement;
	}
	public void setMonthCommencement(String monthCommencement) {
		this.monthCommencement = monthCommencement;
	}
	public String getPpwsCommencement() {
		return ppwsCommencement;
	}
	public void setPpwsCommencement(String ppwsCommencement) {
		this.ppwsCommencement = ppwsCommencement;
	}
	public String getAchievedCommencement() {
		return achievedCommencement;
	}
	public void setAchievedCommencement(String achievedCommencement) {
		this.achievedCommencement = achievedCommencement;
	}
	public String getPercentageCommencement() {
		return percentageCommencement;
	}
	public void setPercentageCommencement(String percentageCommencement) {
		this.percentageCommencement = percentageCommencement;
	}
	public String getMonthPlaced() {
		return monthPlaced;
	}
	public void setMonthPlaced(String monthPlaced) {
		this.monthPlaced = monthPlaced;
	}
	public String getPpwsPlaced() {
		return ppwsPlaced;
	}
	public void setPpwsPlaced(String ppwsPlaced) {
		this.ppwsPlaced = ppwsPlaced;
	}
	public String getAchievedPlaced() {
		return achievedPlaced;
	}
	public void setAchievedPlaced(String achievedPlaced) {
		this.achievedPlaced = achievedPlaced;
	}
	public String getPercentagePlaced() {
		return percentagePlaced;
	}
	public void setPercentagePlaced(String percentagePlaced) {
		this.percentagePlaced = percentagePlaced;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getCtsa() {
		return ctsa;
	}
	public void setCtsa(String ctsa) {
		this.ctsa = ctsa;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getFundReleased() {
		return fundReleased;
	}
	public void setFundReleased(String fundReleased) {
		this.fundReleased = fundReleased;
	}
	public String getResidential() {
		return residential;
	}
	public void setResidential(String residential) {
		this.residential = residential;
	}
	public String getInchargeName() {
		return inchargeName;
	}
	public void setInchargeName(String inchargeName) {
		this.inchargeName = inchargeName;
	}
	public String getInchargemobile() {
		return inchargemobile;
	}
	public void setInchargemobile(String inchargemobile) {
		this.inchargemobile = inchargemobile;
	}
	public String getStatusTC() {
		return statusTC;
	}
	public void setStatusTC(String statusTC) {
		this.statusTC = statusTC;
	}
	public String getProjectSanction() {
		return projectSanction;
	}
	public void setProjectSanction(String projectSanction) {
		this.projectSanction = projectSanction;
	}
	public String getMouSig() {
		return mouSig;
	}
	public void setMouSig(String mouSig) {
		this.mouSig = mouSig;
	}
	public String getFirstInstallment() {
		return firstInstallment;
	}
	public void setFirstInstallment(String firstInstallment) {
		this.firstInstallment = firstInstallment;
	}
	public String getProjectCommencement() {
		return ProjectCommencement;
	}
	public void setProjectCommencement(String projectCommencement) {
		ProjectCommencement = projectCommencement;
	}
	public String getPerApproval() {
		return perApproval;
	}
	public void setPerApproval(String perApproval) {
		this.perApproval = perApproval;
	}
	public String getTrainingCommencement() {
		return trainingCommencement;
	}
	public void setTrainingCommencement(String trainingCommencement) {
		this.trainingCommencement = trainingCommencement;
	}
	public String getDays20() {
		return days20;
	}
	public void setDays20(String days20) {
		this.days20 = days20;
	}
	public String getDays10() {
		return days10;
	}
	public void setDays10(String days10) {
		this.days10 = days10;
	}
	public String getDays30() {
		return days30;
	}
	public void setDays30(String days30) {
		this.days30 = days30;
	}
	public String getDays15() {
		return days15;
	}
	public void setDays15(String days15) {
		this.days15 = days15;
	}
	public String getDays90() {
		return days90;
	}
	public void setDays90(String days90) {
		this.days90 = days90;
	}
	public String getTradeSanctioned() {
		return tradeSanctioned;
	}
	public void setTradeSanctioned(String tradeSanctioned) {
		this.tradeSanctioned = tradeSanctioned;
	}
	public String getDistrictsCovered() {
		return districtsCovered;
	}
	public void setDistrictsCovered(String districtsCovered) {
		this.districtsCovered = districtsCovered;
	}
	public String getFundreleasedPfms() {
		return FundreleasedPfms;
	}
	public void setFundreleasedPfms(String fundreleasedPfms) {
		FundreleasedPfms = fundreleasedPfms;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getExpenditure() {
		return Expenditure;
	}
	public void setExpenditure(String expenditure) {
		Expenditure = expenditure;
	}
	public String getCumExp() {
		return cumExp;
	}
	public void setCumExp(String cumExp) {
		this.cumExp = cumExp;
	}
	public String getExpditure() {
		return Expditure;
	}
	public void setExpditure(String expditure) {
		Expditure = expditure;
	}
	public String getTotalTrained() {
		return totalTrained;
	}
	public void setTotalTrained(String totalTrained) {
		this.totalTrained = totalTrained;
	}
	public String getTotalPlaced() {
		return totalPlaced;
	}
	public void setTotalPlaced(String totalPlaced) {
		this.totalPlaced = totalPlaced;
	}
	public String getAdvisoriesCTSA() {
		return advisoriesCTSA;
	}
	public void setAdvisoriesCTSA(String advisoriesCTSA) {
		this.advisoriesCTSA = advisoriesCTSA;
	}
	public String getAlertReason() {
		return alertReason;
	}
	public void setAlertReason(String alertReason) {
		this.alertReason = alertReason;
	}
	public String getAlertIssueDate() {
		return alertIssueDate;
	}
	public void setAlertIssueDate(String alertIssueDate) {
		this.alertIssueDate = alertIssueDate;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getScTotal() {
		return scTotal;
	}
	public void setScTotal(String scTotal) {
		this.scTotal = scTotal;
	}
	public String getScPercentage() {
		return scPercentage;
	}
	public void setScPercentage(String scPercentage) {
		this.scPercentage = scPercentage;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getStTotal() {
		return stTotal;
	}
	public void setStTotal(String stTotal) {
		this.stTotal = stTotal;
	}
	public String getStPercentage() {
		return stPercentage;
	}
	public void setStPercentage(String stPercentage) {
		this.stPercentage = stPercentage;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getGeneralTotal() {
		return generalTotal;
	}
	public void setGeneralTotal(String generalTotal) {
		this.generalTotal = generalTotal;
	}
	public String getGeneralPercentage() {
		return generalPercentage;
	}
	public void setGeneralPercentage(String generalPercentage) {
		this.generalPercentage = generalPercentage;
	}
	public String getTotalTarget() {
		return totalTarget;
	}
	public void setTotalTarget(String totalTarget) {
		this.totalTarget = totalTarget;
	}
}
