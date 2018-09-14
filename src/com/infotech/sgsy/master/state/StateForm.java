package com.infotech.sgsy.master.state;

import org.apache.struts.action.ActionForm;

import com.infotech.sgsy.common.MasterForm;

public class StateForm extends ActionForm{
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateShortName() {
		return stateShortName;
	}
	public void setStateShortName(String stateShortName) {
		this.stateShortName = stateShortName;
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
	
	
	

}
