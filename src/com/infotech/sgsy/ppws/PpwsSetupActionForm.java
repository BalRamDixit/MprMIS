package com.infotech.sgsy.ppws;

import com.infotech.sgsy.common.MasterForm;

public class PpwsSetupActionForm extends MasterForm{

	private String[] id;
	public String projectId;
 	public String[] entryMonth;
	public String[] entryYear;
	public String[] expTrainComn;
	public String[] expTrainComp;
	public String[] placeExp;
	
	
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String[] getEntryMonth() {
		return entryMonth;
	}
	public void setEntryMonth(String[] entryMonth) {
		this.entryMonth = entryMonth;
	}
	public String[] getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(String[] entryYear) {
		this.entryYear = entryYear;
	}
	public String[] getExpTrainComn() {
		return expTrainComn;
	}
	public void setExpTrainComn(String[] expTrainComn) {
		this.expTrainComn = expTrainComn;
	}
	public String[] getExpTrainComp() {
		return expTrainComp;
	}
	public void setExpTrainComp(String[] expTrainComp) {
		this.expTrainComp = expTrainComp;
	}
	public String[] getPlaceExp() {
		return placeExp;
	}
	public void setPlaceExp(String[] placeExp) {
		this.placeExp = placeExp;
	}
	
	  
	
	
	
	
}
