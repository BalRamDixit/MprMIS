package com.infotech.sgsy.master.financialYear;

import org.apache.struts.action.ActionForm;

public class FinancialYearForm extends ActionForm{
	private String finYr;
	private String finYearFr;
	private String finYrTo;
	private String finYearToDisplay;
	
	
	public String getFinYr() {
		return finYr;
	}
	public void setFinYr(String finYr) {
		this.finYr = finYr;
	}
	public String getFinYearFr() {
		return finYearFr;
	}
	public void setFinYearFr(String finYearFr) {
		this.finYearFr = finYearFr;
	}
	public String getFinYrTo() {
		return finYrTo;
	}
	public void setFinYrTo(String finYrTo) {
		this.finYrTo = finYrTo;
	}
	public String getFinYearToDisplay() {
		return finYearToDisplay;
	}
	public void setFinYearToDisplay(String finYearToDisplay) {
		this.finYearToDisplay = finYearToDisplay;
	}
}
