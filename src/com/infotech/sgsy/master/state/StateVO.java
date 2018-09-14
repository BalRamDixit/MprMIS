package com.infotech.sgsy.master.state;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;


public class StateVO extends MasterVO implements Serializable {
	
	

	@Override
	public String toString() {
		return "StateVO [stateId=" + stateId + ", stateCode=" + stateCode + ", stateName=" + stateName
				+ ", stateShortName=" + stateShortName + ", centerShare=" + centerShare + ", stateShare=" + stateShare
				+ ", sc_st=" + sc_st + ", miniority=" + miniority + ", women=" + women + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", updateBy=" + updateBy + ", updatedOn=" + updatedOn + "]";
	}

	private String stateId;
	private String stateCode;
	private String stateName;
	private String stateShortName ;
	private Integer centerShare ;
	private Integer stateShare ;
	
	
	/*new fields*/
	private Integer sc_st;
	private Integer miniority;
	private Integer women;
	
	/*private Set<DistrictMasterVO> district=new HashSet<DistrictMasterVO>(0);*/
	
	
	private Date createdOn;
	private String createdBy;
	private String updateBy;
	private Date updatedOn;
	
	
	
	
	

	

	

	

	/*public Set<DistrictMasterVO> getDistrict() {
		return district;
	}

	public void setDistrict(Set<DistrictMasterVO> district) {
		this.district = district;
	}*/

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateShortName() {
		return stateShortName;
	}

	public void setStateShortName(String stateShortName) {
		this.stateShortName = stateShortName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getCenterShare() {
		return centerShare;
	}

	public void setCenterShare(Integer centerShare) {
		this.centerShare = centerShare;
	}

	public Integer getStateShare() {
		return stateShare;
	}

	public void setStateShare(Integer stateShare) {
		this.stateShare = stateShare;
	}

	public Integer getSc_st() {
		return sc_st;
	}

	public void setSc_st(Integer sc_st) {
		this.sc_st = sc_st;
	}

	public Integer getMiniority() {
		return miniority;
	}

	public void setMiniority(Integer miniority) {
		this.miniority = miniority;
	}

	public Integer getWomen() {
		return women;
	}

	public void setWomen(Integer women) {
		this.women = women;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	

	



	

	

}
