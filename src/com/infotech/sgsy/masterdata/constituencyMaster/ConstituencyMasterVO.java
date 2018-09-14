package com.infotech.sgsy.masterdata.constituencyMaster;

import java.util.Date;

import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.parliamentaryConstituencyMaster.ParliamentaryConstituencyMasterVO;
import com.infotech.sgsy.master.state.StateVO;

public class ConstituencyMasterVO {


	@Override
	public String toString() {
		return "ConstituencyMasterVO [constituencyId=" + constituencyId + ", editId=" + editId
				+ ", assemblyConstituencyCode=" + assemblyConstituencyCode + ", parliamentaryConstituencyName="
				+ parliamentaryConstituencyName + ", assemblyConstituencyName=" + assemblyConstituencyName
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + ", district=" + district + "]";
	}
	private String constituencyId;	
	private String editId;
	private String assemblyConstituencyCode;
	
	private ParliamentaryConstituencyMasterVO parliamentaryConstituencyName;
	private String assemblyConstituencyName;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private DistrictMasterVO district;
	
	
	
	public ParliamentaryConstituencyMasterVO getParliamentaryConstituencyName() {
		return parliamentaryConstituencyName;
	}
	public void setParliamentaryConstituencyName(ParliamentaryConstituencyMasterVO parliamentaryConstituencyName) {
		this.parliamentaryConstituencyName = parliamentaryConstituencyName;
	}
	public String getAssemblyConstituencyCode() {
		return assemblyConstituencyCode;
	}
	public void setAssemblyConstituencyCode(String assemblyConstituencyCode) {
		this.assemblyConstituencyCode = assemblyConstituencyCode;
	}
	
	public String getAssemblyConstituencyName() {
		return assemblyConstituencyName;
	}
	public void setAssemblyConstituencyName(String assemblyConstituencyName) {
		this.assemblyConstituencyName = assemblyConstituencyName;
	}
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
	public DistrictMasterVO getDistrict() {
		return district;
	}
	public void setDistrict(DistrictMasterVO district) {
		this.district = district;
	}
	
	
	
	
}
