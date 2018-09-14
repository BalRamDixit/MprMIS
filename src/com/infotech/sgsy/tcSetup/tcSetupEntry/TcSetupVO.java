package com.infotech.sgsy.tcSetup.tcSetupEntry;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.masterdata.constituencyMaster.ConstituencyMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class TcSetupVO implements Serializable {
	
	    @Override
	public String toString() {
		return "TcSetupVO [id=" + id + ", project=" + project + ", state=" + state + ", district=" + district
				+ ", address=" + address + ", pinCode=" + pinCode + ", assemblyCons=" + assemblyCons + ", tcLatitude="
				+ tcLatitude + ", tcLongitude=" + tcLongitude + ", resiStatus=" + resiStatus + ", inChargeName="
				+ inChargeName + ", inChargeMobile=" + inChargeMobile + ", inChargeAltMobile=" + inChargeAltMobile
				+ ", inChargeEmail=" + inChargeEmail + ", tcID=" + tcID + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
		public String id;
	    public ProjectDetailsVO project;
 		public StateVO state;
		public DistrictMasterVO district;
		public String address;
		public String pinCode;
		public ConstituencyMasterVO assemblyCons;
		public String parliament;

		public String tcLatitude;
		public String tcLongitude;
		public String resiStatus;
		public String inChargeName;
		//public String inChargeNo;
		public String inChargeMobile;
		public String inChargeAltMobile;
		public String inChargeEmail;
		public String tcID;
		public String tcName;

		
		private String createdBy;
		private Date createdOn;
		private String updatedBy;
		private Date updatedOn;
		
		
		
		public ConstituencyMasterVO getAssemblyCons() {
			return assemblyCons;
		}
		public void setAssemblyCons(ConstituencyMasterVO assemblyCons) {
			this.assemblyCons = assemblyCons;
		}
		public ProjectDetailsVO getProject() {
			return project;
		}
		public void setProject(ProjectDetailsVO project) {
			this.project = project;
		}
		public StateVO getState() {
			return state;
		}
		public void setState(StateVO state) {
			this.state = state;
		}
		public DistrictMasterVO getDistrict() {
			return district;
		}
		public void setDistrict(DistrictMasterVO district) {
			this.district = district;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPinCode() {
			return pinCode;
		}
		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}
		
		
		/*public Integer getTcLatitude() {
			return tcLatitude;
		}
		public void setTcLatitude(Integer tcLatitude) {
			this.tcLatitude = tcLatitude;
		}
		public Integer getTcLongitude() {
			return tcLongitude;
		}
		public void setTcLongitude(Integer tcLongitude) {
			this.tcLongitude = tcLongitude;
		}*/
		public String getResiStatus() {
			return resiStatus;
		}
		public void setResiStatus(String resiStatus) {
			this.resiStatus = resiStatus;
		}
		public String getInChargeName() {
			return inChargeName;
		}
		public void setInChargeName(String inChargeName) {
			this.inChargeName = inChargeName;
		}
		public String getInChargeMobile() {
			return inChargeMobile;
		}
		public void setInChargeMobile(String inChargeMobile) {
			this.inChargeMobile = inChargeMobile;
		}
		public String getInChargeAltMobile() {
			return inChargeAltMobile;
		}
		public void setInChargeAltMobile(String inChargeAltMobile) {
			this.inChargeAltMobile = inChargeAltMobile;
		}
		public String getInChargeEmail() {
			return inChargeEmail;
		}
		public void setInChargeEmail(String inChargeEmail) {
			this.inChargeEmail = inChargeEmail;
		}
		public String getTcID() {
			return tcID;
		}
		public void setTcID(String tcID) {
			this.tcID = tcID;
		}
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public String getTcLatitude() {
			return tcLatitude;
		}
		public void setTcLatitude(String tcLatitude) {
			this.tcLatitude = tcLatitude;
		}
		public String getTcLongitude() {
			return tcLongitude;
		}
		public void setTcLongitude(String tcLongitude) {
			this.tcLongitude = tcLongitude;
		}
		public String getParliament() {
			return parliament;
		}
		public void setParliament(String parliament) {
			this.parliament = parliament;
		}
		public String getTcName() {
			return tcName;
		}
		public void setTcName(String tcName) {
			this.tcName = tcName;
		}
		 
		 

}
