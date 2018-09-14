package com.infotech.sgsy.selfHelpGroup;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.infotech.sgsy.common.MasterForm;

public class SHGBankLinkageForm extends MasterForm{

	private String shgBankLinkageCode;
	private String shgCode;
	private String entityCode;
	private String loanAccountNumber;
	
	private String loanType;
	private double loanAmount;
	private double rateOfInterest;
	private String loanIssueDate;
	private double cashCreditLimit;
	private double emi;
	private Integer numberOfInstallment;
	private double outstanding;
	private String loanStatus;	
	
	public String getShgBankLinkageCode() {
		return shgBankLinkageCode;
	}
	public void setShgBankLinkageCode(String shgBankLinkageCode) {
		this.shgBankLinkageCode = shgBankLinkageCode;
	}
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
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public String getLoanIssueDate() {
		return loanIssueDate;
	}
	public void setLoanIssueDate(String loanIssueDate) {
		this.loanIssueDate = loanIssueDate;
	}
	public double getCashCreditLimit() {
		return cashCreditLimit;
	}
	public void setCashCreditLimit(double cashCreditLimit) {
		this.cashCreditLimit = cashCreditLimit;
	}
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public Integer getNumberOfInstallment() {
		return numberOfInstallment;
	}
	public void setNumberOfInstallment(Integer numberOfInstallment) {
		this.numberOfInstallment = numberOfInstallment;
	}
	public double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
@Override
public void reset(ActionMapping mapping, HttpServletRequest request) {
	this.shgBankLinkageCode = null;
	this.shgCode = null;
	this.entityCode = null;
	this.loanAccountNumber = null;
	
	this.loanType = null;
	this.loanAmount = 0.0;
	this.rateOfInterest = 0.0;
	this.loanIssueDate = null;
	this.cashCreditLimit=0.0;
	this.emi = 0.0;
	this.numberOfInstallment = null;
	this.outstanding = 0.0;
	this.loanStatus = null;
}
public void setLoanAccountNumber(String loanAccountNumber) {
	this.loanAccountNumber = loanAccountNumber;
}
public String getLoanAccountNumber() {
	return loanAccountNumber;
}


}
