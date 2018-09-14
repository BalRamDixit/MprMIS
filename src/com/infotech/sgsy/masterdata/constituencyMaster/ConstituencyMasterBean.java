package com.infotech.sgsy.masterdata.constituencyMaster;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.state.StateVO;

public class ConstituencyMasterBean extends ValidatorForm {
	

	private String constituencyId;	
	private String editId;
	private String assemblyConstituencyCode;
	private String parliamentaryConstituencyId;
	private String assemblyConstituencyName;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private String state;
	private String districtid;
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}
	public String getAssemblyConstituencyCode() {
		return assemblyConstituencyCode;
	}
	public void setAssemblyConstituencyCode(String assemblyConstituencyCode) {
		this.assemblyConstituencyCode = assemblyConstituencyCode;
	}
	public String getParliamentaryConstituencyId() {
		return parliamentaryConstituencyId;
	}
	public void setParliamentaryConstituencyId(String parliamentaryConstituencyId) {
		this.parliamentaryConstituencyId = parliamentaryConstituencyId;
	}
	public String getAssemblyConstituencyName() {
		return assemblyConstituencyName;
	}
	public void setAssemblyConstituencyName(String assemblyConstituencyName) {
		this.assemblyConstituencyName = assemblyConstituencyName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrictid() {
		return districtid;
	}
	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}
	

}
