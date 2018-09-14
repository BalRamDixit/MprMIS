package com.infotech.sgsy.selfHelpGroup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;

public class SHGReportCardForm extends MasterForm {

	private String shgCode;
	private String entityCode;
	private String financialYear;
	private String entryMonth;

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

	private String preOutstandingCCL;
	private String preOutstandingTL;
	
	public String cashCreditLimit;
	public String emi;

	public String getShgCode() {
		return shgCode;
	}
	public void setShgCode(String shgCode) {
		this.shgCode = shgCode;
	}

	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
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
	public void setSavingAmountDepositedInBank(
			double savingAmountDepositedInBank) {
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
	public String getPreOutstandingCCL() {
		return preOutstandingCCL;
	}
	public void setPreOutstandingCCL(String preOutstandingCCL) {
		this.preOutstandingCCL = preOutstandingCCL;
	}
	public String getPreOutstandingTL() {
		return preOutstandingTL;
	}
	public void setPreOutstandingTL(String preOutstandingTL) {
		this.preOutstandingTL = preOutstandingTL;
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
	public String getCashCreditLimit() {
		return cashCreditLimit;
	}
	public void setCashCreditLimit(String cashCreditLimit) {
		this.cashCreditLimit = cashCreditLimit;
	}
	public String getEmi() {
		return emi;
	}
	public void setEmi(String emi) {
		this.emi = emi;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.shgCode = null;
		this.entityCode = null;
		this.financialYear = null;
		this.entryMonth = null;

		this.noOfMeetingHeld = null;
		this.savingAmountCollected = 0.0;
		this.savingAmountDepositedInBank = 0.0;
		
		this.internalLoanNumber = 0;
		this.internalLoanAmount = 0.0;
		this.internalLoanOutstandingNo = 0;
		this.internalLoanOutstandingAmount = 0.0;
		this.internalLoanOutstandingPar = 0.0;
		
		this.bankLoanAmountOutstandingCCL = 0.0;
		this.bankLoanAmountOutstandingTL = 0.0;
 
		this.interestSubsidyEligible = null;
		this.interestSubsidyReceiving = null;
		this.interestSubsidyAmountReceived = 0.0;

		this.cisCifAmountReceived = 0.0;
		this.cisCifAmountRepaid = 0.0;
		this.cisCifLoanAtRisk = 0.0;
		this.preOutstandingCCL = null;
		this.preOutstandingTL = null;
		this.cashCreditLimit = null;
		this.emi = null;
		/* super.reset(mapping, request); */
	}

}
