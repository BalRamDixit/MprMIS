package com.infotech.skills.outerReports;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.skills.master.MasterForm;

public class outerReportForm extends MasterForm {
	
	private String piaCode;
	private String piaName;
	private String address;
	private String piaConfirmationDate;
	private String piaReportStatus;
	
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getPiaName() {
		return piaName;
	}
	public void setPiaName(String piaName) {
		this.piaName = piaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPiaConfirmationDate(String piaConfirmationDate) {
		this.piaConfirmationDate = piaConfirmationDate;
	}
	public String getPiaConfirmationDate() {
		return piaConfirmationDate;
	}
	
	public String getPiaReportStatus() {
		return piaReportStatus;
	}

	public void setPiaReportStatus(String piaReportStatus) {
		this.piaReportStatus = piaReportStatus;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setPiaReportStatus("");
	}
	
}
