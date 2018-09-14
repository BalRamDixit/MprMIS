package com.infotech.sgsy.selfHelpGroup;



import com.infotech.sgsy.common.MasterVO;

public class MasterSHGMonthlyDetailVO extends MasterVO{
	  private String shgCode;
	  private String financialYear;
	  private String entryMonth;
	  private String entityCode;
	 
	  private String noOfMeetingHeld;
	  
	  private double savingAmountCollected;
	  private double savingAmountDepositedInBank;
	 
	  private int internalLoanNumber;
	  private double internalLoanAmount;
	  private int internalLoanOutstandingNo;
	  private double internalLoanOutstandingAmount;
	  private double internalLoanOutstandingPar;
	  
	  private double bankLoanAmountOutstandingCCL;
	  private double bankLoanAmountOutstandingTL;
	  
	  private String interestSubsidyEligible;
	  private String interestSubsidyReceiving;
	  private double interestSubsidyAmountReceived;
	  
	  private double cisCifAmountReceived;
	  private double cisCifAmountRepaid;
	  private double cisCifLoanAtRisk;
	  
	  
	public String getShgCode() {
		return shgCode;
	}
	public void setShgCode(String shgCode) {
		this.shgCode = shgCode;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getEntryMonth() {
		return entryMonth;
	}
	public void setEntryMonth(String entryMonth) {
		this.entryMonth = entryMonth;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getNoOfMeetingHeld() {
		return noOfMeetingHeld;
	}
	public void setNoOfMeetingHeld(String noOfMeetingHeld) {
		this.noOfMeetingHeld = noOfMeetingHeld;
	}
	public double getSavingAmountCollected() {
		return savingAmountCollected;
	}
	public void setSavingAmountCollected(double savingAmountCollected) {
		this.savingAmountCollected = savingAmountCollected;
	}
	public double getSavingAmountDepositedInBank() {
		return savingAmountDepositedInBank;
	}
	public void setSavingAmountDepositedInBank(double savingAmountDepositedInBank) {
		this.savingAmountDepositedInBank = savingAmountDepositedInBank;
	}
	public int getInternalLoanNumber() {
		return internalLoanNumber;
	}
	public void setInternalLoanNumber(int internalLoanNumber) {
		this.internalLoanNumber = internalLoanNumber;
	}
	public double getInternalLoanAmount() {
		return internalLoanAmount;
	}
	public void setInternalLoanAmount(double internalLoanAmount) {
		this.internalLoanAmount = internalLoanAmount;
	}
	public int getInternalLoanOutstandingNo() {
		return internalLoanOutstandingNo;
	}
	public void setInternalLoanOutstandingNo(int internalLoanOutstandingNo) {
		this.internalLoanOutstandingNo = internalLoanOutstandingNo;
	}
	public double getInternalLoanOutstandingAmount() {
		return internalLoanOutstandingAmount;
	}
	public void setInternalLoanOutstandingAmount(
			double internalLoanOutstandingAmount) {
		this.internalLoanOutstandingAmount = internalLoanOutstandingAmount;
	}
	public double getInternalLoanOutstandingPar() {
		return internalLoanOutstandingPar;
	}
	public void setInternalLoanOutstandingPar(double internalLoanOutstandingPar) {
		this.internalLoanOutstandingPar = internalLoanOutstandingPar;
	}
	public String getInterestSubsidyEligible() {
		return interestSubsidyEligible;
	}
	public void setInterestSubsidyEligible(String interestSubsidyEligible) {
		this.interestSubsidyEligible = interestSubsidyEligible;
	}
	public String getInterestSubsidyReceiving() {
		return interestSubsidyReceiving;
	}
	public void setInterestSubsidyReceiving(String interestSubsidyReceiving) {
		this.interestSubsidyReceiving = interestSubsidyReceiving;
	}
	public double getInterestSubsidyAmountReceived() {
		return interestSubsidyAmountReceived;
	}
	public void setInterestSubsidyAmountReceived(
			double interestSubsidyAmountReceived) {
		this.interestSubsidyAmountReceived = interestSubsidyAmountReceived;
	}
	public double getCisCifAmountReceived() {
		return cisCifAmountReceived;
	}
	public void setCisCifAmountReceived(double cisCifAmountReceived) {
		this.cisCifAmountReceived = cisCifAmountReceived;
	}
	public double getCisCifAmountRepaid() {
		return cisCifAmountRepaid;
	}
	public void setCisCifAmountRepaid(double cisCifAmountRepaid) {
		this.cisCifAmountRepaid = cisCifAmountRepaid;
	}
	public double getCisCifLoanAtRisk() {
		return cisCifLoanAtRisk;
	}
	public void setCisCifLoanAtRisk(double cisCifLoanAtRisk) {
		this.cisCifLoanAtRisk = cisCifLoanAtRisk;
	}
	public double getBankLoanAmountOutstandingCCL() {
		return bankLoanAmountOutstandingCCL;
	}
	public void setBankLoanAmountOutstandingCCL(double bankLoanAmountOutstandingCCL) {
		this.bankLoanAmountOutstandingCCL = bankLoanAmountOutstandingCCL;
	}
	public double getBankLoanAmountOutstandingTL() {
		return bankLoanAmountOutstandingTL;
	}
	public void setBankLoanAmountOutstandingTL(double bankLoanAmountOutstandingTL) {
		this.bankLoanAmountOutstandingTL = bankLoanAmountOutstandingTL;
	}
}
