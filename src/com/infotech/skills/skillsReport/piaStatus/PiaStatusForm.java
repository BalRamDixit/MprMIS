package com.infotech.skills.skillsReport.piaStatus;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.infotech.sgsy.common.MasterForm;

public class PiaStatusForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String piaReportStatus;
	private String piaCode;

	public String getPiaReportStatus() {
		return piaReportStatus;
	}

	public void setPiaReportStatus(String piaReportStatus) {
		this.piaReportStatus = piaReportStatus;
	}

	public String getPiaCode() {
		return piaCode;
	}

	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setPiaReportStatus("");
	}

}
