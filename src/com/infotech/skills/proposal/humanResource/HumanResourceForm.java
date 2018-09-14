package com.infotech.skills.proposal.humanResource;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class HumanResourceForm extends ActionForm{
	
	private String	piaCode ;
	private String	proposalCode;
	private String employeeName;
	private String  employeeDesig;
	private String createdOn ;
	private String createdBy ;
	private String modifyOn ;
	private String modifyBy ;
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
	
	public void reset(){
		this.piaCode = "";
		this.proposalCode = "";
		this.employeeName = "";
		this.employeeDesig = "";
		this.createdOn = "";
		this.createdBy = "";
		this.modifyOn = "";
		this.modifyBy = "";
	}

}
