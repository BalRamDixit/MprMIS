package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

public class HumanResourceProposedVO implements Serializable {
	
	private String	piaCode ;
	private String	proposalCode ;
	private String employeeName;
	private String  employeeDesig ;
	private String createdOn ;
	private String createdBy ;
	private String modifyOn ;
	private String modifyBy ;
	
	public HumanResourceProposedVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HumanResourceProposedVO(String piaCode, String proposalCode,
			String employeeName, String employeeDesig, String createdOn,
			String createdBy, String modifyOn, String modifyBy) {
		super();
		this.piaCode = piaCode;
		this.proposalCode = proposalCode;
		this.employeeName = employeeName;
		this.employeeDesig = employeeDesig;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifyOn = modifyOn;
		this.modifyBy = modifyBy;
	}

	public String getPiaCode() {
		return piaCode;
	}

	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}

	public String getProposalCode() {
		return proposalCode;
	}

	public void setProposalCode(String proposalCode) {
		this.proposalCode = proposalCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDesig() {
		return employeeDesig;
	}

	public void setEmployeeDesig(String employeeDesig) {
		this.employeeDesig = employeeDesig;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
}