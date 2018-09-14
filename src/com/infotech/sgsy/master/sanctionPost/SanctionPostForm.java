package com.infotech.sgsy.master.sanctionPost;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.infotech.sgsy.master.designationMaster.DesignationMasterVo;
import com.infotech.sgsy.master.state.StateVO;

public class SanctionPostForm extends ActionForm{
	
    private String sanctionId;
	private String designationId;
	private String noofPost;
	private String isDeleted;
	private String stateId;

	
	private String editId;
	private String createdBy;
	private Date createdDate;	
	private String updatedBy;
	private Date updatedDate;

	
	private StateVO state;
	private DesignationMasterVo designationMaster;
	
	public SanctionPostForm(SanctionPostVO sanctionPostVO) {
		this.sanctionId=sanctionPostVO.getSanctionId();
		this.designationId=sanctionPostVO.getDesignationMaster().getDesignationId();
		this.noofPost=sanctionPostVO.getNoofPost();
		this.stateId=sanctionPostVO.getState().getStateId();
		this.state=sanctionPostVO.getState();
		this.designationMaster=sanctionPostVO.getDesignationMaster();
	}
	@Override
	public String toString() {
		return "SanctionPostForm [sanctionId=" + sanctionId + ", designationId=" + designationId + ", noofPost="
				+ noofPost + ", isDeleted=" + isDeleted + ", stateId=" + stateId + ", editId=" + editId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", state=" + state + ", designationMaster=" + designationMaster + "]";
	}
	public SanctionPostForm() {
		super();
	}
	public String getSanctionId() {
		return sanctionId;
	}
	public void setSanctionId(String sanctionId) {
		this.sanctionId = sanctionId;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getNoofPost() {
		return noofPost;
	}
	public void setNoofPost(String noofPost) {
		this.noofPost = noofPost;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}
	public StateVO getState() {
		return state;
	}
	public void setState(StateVO state) {
		this.state = state;
	}
	public DesignationMasterVo getDesignationMaster() {
		return designationMaster;
	}
	public void setDesignationMaster(DesignationMasterVo designationMaster) {
		this.designationMaster = designationMaster;
	}
	
}
