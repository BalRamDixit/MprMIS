package com.infotech.sgsy.indicators;

import com.infotech.sgsy.common.MasterForm;

public class KPIIndicatorForm  extends MasterForm{

	 private String apStatus;
     private String stateTsaEmpaneled;
     private String cooPosted;
     private String stateMis;
     private String stateName;
     private String month;
     private String year;
     
     
     
	public String getApStatus() {
		return apStatus;
	}
	public void setApStatus(String apStatus) {
		this.apStatus = apStatus;
	}
	public String getStateTsaEmpaneled() {
		return stateTsaEmpaneled;
	}
	public void setStateTsaEmpaneled(String stateTsaEmpaneled) {
		this.stateTsaEmpaneled = stateTsaEmpaneled;
	}
	public String getCooPosted() {
		return cooPosted;
	}
	public void setCooPosted(String cooPosted) {
		this.cooPosted = cooPosted;
	}
	public String getStateMis() {
		return stateMis;
	}
	public void setStateMis(String stateMis) {
		this.stateMis = stateMis;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
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
}
