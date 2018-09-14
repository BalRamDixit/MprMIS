package com.infotech.sgsy.projectSetup.tradeTarget;

import com.infotech.sgsy.common.MasterForm;

public class TradeTargetActionForm extends MasterForm {

	
	private Integer Id[];
	
	private String projectID;
	private String sector[];
	private String trade[];
	private String otherTrade[];
	private String totalTradeDuration[];
	private String ojt[];
	private int trainingTarget[];
	private String nonResiFull[];
	private String nonResiPart[];
	private String nonResiWeekend[];
	private String resiFull[];
	/*private int projectTotalTrainingTagget;*/
	
	
	
	public String[] getSector() {
		return sector;
	}
	public Integer[] getId() {
		return Id;
	}
	public void setId(Integer[] id) {
		Id = id;
	}
	public void setSector(String[] sector) {
		this.sector = sector;
	}
	public String[] getTrade() {
		return trade;
	}
	public void setTrade(String[] trade) {
		this.trade = trade;
	}
	public String[] getOtherTrade() {
		return otherTrade;
	}
	public void setOtherTrade(String[] otherTrade) {
		this.otherTrade = otherTrade;
	}
	public String[] getTotalTradeDuration() {
		return totalTradeDuration;
	}
	public void setTotalTradeDuration(String[] totalTradeDuration) {
		this.totalTradeDuration = totalTradeDuration;
	}
	public String[] getOjt() {
		return ojt;
	}
	public void setOjt(String[] ojt) {
		this.ojt = ojt;
	}
	
	public int[] getTrainingTarget() {
		return trainingTarget;
	}
	public void setTrainingTarget(int[] trainingTarget) {
		this.trainingTarget = trainingTarget;
	}
	public String[] getNonResiFull() {
		return nonResiFull;
	}
	public void setNonResiFull(String[] nonResiFull) {
		this.nonResiFull = nonResiFull;
	}
	public String[] getNonResiPart() {
		return nonResiPart;
	}
	public void setNonResiPart(String[] nonResiPart) {
		this.nonResiPart = nonResiPart;
	}
	public String[] getNonResiWeekend() {
		return nonResiWeekend;
	}
	public void setNonResiWeekend(String[] nonResiWeekend) {
		this.nonResiWeekend = nonResiWeekend;
	}
	public String[] getResiFull() {
		return resiFull;
	}
	public void setResiFull(String[] resiFull) {
		this.resiFull = resiFull;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
}

	