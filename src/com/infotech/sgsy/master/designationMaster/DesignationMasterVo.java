package com.infotech.sgsy.master.designationMaster;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.infotech.sgsy.stateSetup.hrTeam.HRTeamVO;

public class DesignationMasterVo {
	private String designationId;
	private String designationCode;
	private String designationName;
	/*private Set<HRTeamVO> hrteam=new HashSet<HRTeamVO>(0);*/
	
	private Date createdOn;
	private String createdBy;
	private String updateBy;
	@Override
	public String toString() {
		return "DesignationMasterVo [designationId=" + designationId + ", designationCode=" + designationCode
				+ ", designationName=" + designationName + ", createdOn=" + createdOn + ", createdBy=" + createdBy
				+ ", updateBy=" + updateBy + ", updatedOn=" + updatedOn + "]";
	}
	private Date updatedOn;
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	/*public Set<HRTeamVO> getHrteam() {
		return hrteam;
	}
	public void setHrteam(Set<HRTeamVO> hrteam) {
		this.hrteam = hrteam;
	}*/
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
