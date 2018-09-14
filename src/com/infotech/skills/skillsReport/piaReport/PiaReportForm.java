package com.infotech.skills.skillsReport.piaReport;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;

public class PiaReportForm extends MasterForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String piaReportStatus;

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
