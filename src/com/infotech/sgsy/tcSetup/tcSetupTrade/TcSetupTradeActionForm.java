package com.infotech.sgsy.tcSetup.tcSetupTrade;

import com.infotech.sgsy.common.MasterForm;

public class TcSetupTradeActionForm extends MasterForm {
 
	
	private String id[];
	private String projectID;
	private String tcId;
	private String sectorCode[];
	private String tradeCode[];
	private Integer appTradeCapacity[];
	
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	public String[] getSectorCode() {
		return sectorCode;
	}
	public void setSectorCode(String[] sectorCode) {
		this.sectorCode = sectorCode;
	}
	public String[] getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String[] tradeCode) {
		this.tradeCode = tradeCode;
	}
	public Integer[] getAppTradeCapacity() {
		return appTradeCapacity;
	}
	public void setAppTradeCapacity(Integer[] appTradeCapacity) {
		this.appTradeCapacity = appTradeCapacity;
	}
	 
	
	
	
	
	
	
	
}
