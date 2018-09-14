package com.infotech.sgsy.selfHelpGroup;



import java.math.BigDecimal;

import com.infotech.sgsy.common.MasterVO;

public class MasterSHGMonthlyLoanCalculationsVO extends MasterVO {

	private String shgCode;
	private String financialYear;
	private String entryMonth;
	private String entityCode;
	private String shgBankLinkageCode;
	
	private BigDecimal principalBeforeRepayment;
	private BigDecimal rateOfInterest;
	private BigDecimal interestPaid;
	private BigDecimal repaidPrincipal;
	private BigDecimal interestAmountAbove7per;
	
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
	public String getShgBankLinkageCode() {
		return shgBankLinkageCode;
	}
	public void setShgBankLinkageCode(String shgBankLinkageCode) {
		this.shgBankLinkageCode = shgBankLinkageCode;
	}
	public BigDecimal getPrincipalBeforeRepayment() {
		return principalBeforeRepayment;
	}
	public void setPrincipalBeforeRepayment(BigDecimal principalBeforeRepayment) {
		this.principalBeforeRepayment = principalBeforeRepayment;
	}
	public BigDecimal getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(BigDecimal rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public BigDecimal getInterestPaid() {
		return interestPaid;
	}
	public void setInterestPaid(BigDecimal interestPaid) {
		this.interestPaid = interestPaid;
	}
	public BigDecimal getRepaidPrincipal() {
		return repaidPrincipal;
	}
	public void setRepaidPrincipal(BigDecimal repaidPrincipal) {
		this.repaidPrincipal = repaidPrincipal;
	}
	public void setInterestAmountAbove7per(BigDecimal interestAmountAbove7per) {
		this.interestAmountAbove7per = interestAmountAbove7per;
	}
	public BigDecimal getInterestAmountAbove7per() {
		return interestAmountAbove7per;
	}
	
}
