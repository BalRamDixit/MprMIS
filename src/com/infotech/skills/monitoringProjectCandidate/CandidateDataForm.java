package com.infotech.skills.monitoringProjectCandidate;

import org.apache.struts.validator.ValidatorForm;

public class CandidateDataForm extends ValidatorForm{

	
	public String month;
	public String year;
	public int aadharNo;
	public int bankAccount ;
	public int mobileNo ;
	public int aadharlinkageBank;
	public int insurance;
	public int proLife;
	public int foreignPlaced;
	public int earningp;
	public int retainedForMonths;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(int aadharNo) {
		this.aadharNo = aadharNo;
	}
	public int getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getAadharlinkageBank() {
		return aadharlinkageBank;
	}
	public void setAadharlinkageBank(int aadharlinkageBank) {
		this.aadharlinkageBank = aadharlinkageBank;
	}
	public int getInsurance() {
		return insurance;
	}
	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}
	public int getProLife() {
		return proLife;
	}
	public void setProLife(int proLife) {
		this.proLife = proLife;
	}
	public int getForeignPlaced() {
		return foreignPlaced;
	}
	public void setForeignPlaced(int foreignPlaced) {
		this.foreignPlaced = foreignPlaced;
	}
	public int getEarningp() {
		return earningp;
	}
	public void setEarningp(int earningp) {
		this.earningp = earningp;
	}
	public int getRetainedForMonths() {
		return retainedForMonths;
	}
	public void setRetainedForMonths(int retainedForMonths) {
		this.retainedForMonths = retainedForMonths;
	}
	
	
}
