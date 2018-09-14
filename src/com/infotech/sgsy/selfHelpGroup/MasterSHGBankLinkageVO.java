package com.infotech.sgsy.selfHelpGroup;



import java.math.BigDecimal;

import com.infotech.sgsy.common.MasterVO;

public class MasterSHGBankLinkageVO extends MasterVO {

	private String shgBankLinkageCode;
	private String shgCode;
	private String entityCode;
	
	private String loanAccountNumber;
	private String loanType;
	private BigDecimal loanAmount;
	private BigDecimal rateOfInterest;
	private String loanIssueDate;
	private BigDecimal cashCreditLimit;
	private BigDecimal emi;
	private int numberOfInstallment;
	private BigDecimal outstanding;
	private BigDecimal systemDefinedOutstanding;
	private int irragularCounter;
	private int npaCounter;
	private int paymentCounter;
	private int paidInstallmentCounter;
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
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(BigDecimal rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public String getLoanIssueDate() {
		return loanIssueDate;
	}
	public void setLoanIssueDate(String loanIssueDate) {
		this.loanIssueDate = loanIssueDate;
	}
	public BigDecimal getCashCreditLimit() {
		return cashCreditLimit;
	}
	public void setCashCreditLimit(BigDecimal cashCreditLimit) {
		this.cashCreditLimit = cashCreditLimit;
	}
	public BigDecimal getEmi() {
		return emi;
	}
	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}
	public int getNumberOfInstallment() {
		return numberOfInstallment;
	}
	public void setNumberOfInstallment(int numberOfInstallment) {
		this.numberOfInstallment = numberOfInstallment;
	}
	public BigDecimal getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}
	
	public BigDecimal getSystemDefinedOutstanding() {
		return systemDefinedOutstanding;
	}
	public void setSystemDefinedOutstanding(BigDecimal systemDefinedOutstanding) {
		this.systemDefinedOutstanding = systemDefinedOutstanding;
	}
	public int getIrragularCounter() {
		return irragularCounter;
	}
	public void setIrragularCounter(int irragularCounter) {
		this.irragularCounter = irragularCounter;
	}
	public int getNpaCounter() {
		return npaCounter;
	}
	public void setNpaCounter(int npaCounter) {
		this.npaCounter = npaCounter;
	}
	public int getPaymentCounter() {
		return paymentCounter;
	}
	public void setPaymentCounter(int paymentCounter) {
		this.paymentCounter = paymentCounter;
	}
	public int getPaidInstallmentCounter() {
		return paidInstallmentCounter;
	}
	public void setPaidInstallmentCounter(int paidInstallmentCounter) {
		this.paidInstallmentCounter = paidInstallmentCounter;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	
	
}
