package com.infotech.sgsy.projectSetupTarget;

import com.infotech.sgsy.common.MasterForm;

public class ProjectSetupTargetForm extends MasterForm{

	/**
	 * 
	 */
	 private int id; 
	 private String stateName;
	 private String projectId;
	 private String totalTrainingTarget;
	 private String scSt;
 	 private String general;
	 private String minority;
	 private String women;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTotalTrainingTarget() {
		return totalTrainingTarget;
	}
	public void setTotalTrainingTarget(String totalTrainingTarget) {
		this.totalTrainingTarget = totalTrainingTarget;
	}
	 
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getMinority() {
		return minority;
	}
	public void setMinority(String minority) {
		this.minority = minority;
	}
	public String getWomen() {
		return women;
	}
	public void setWomen(String women) {
		this.women = women;
	}
	public String getScSt() {
		return scSt;
	}
	public void setScSt(String scSt) {
		this.scSt = scSt;
	}
	 
 
	 
	
}
