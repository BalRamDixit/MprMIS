package com.infotech.sgsy.master.month;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class MonthVO extends MasterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String monthCode;	
	private String monthName;
	
	public MonthVO() {
		super();		
	}
	public MonthVO(String monthCode, String monthName) {
		super();
		this.monthCode = monthCode;
		this.monthName = monthName;
	}
	public String getMonthCode() {
		return monthCode;
	}
	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	

}
