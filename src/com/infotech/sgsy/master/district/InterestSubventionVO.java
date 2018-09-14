package com.infotech.sgsy.master.district;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class InterestSubventionVO extends MasterVO implements Serializable{

	private int interestSubventionRate;
	private String entityCode;
	private String districtName;

	

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setInterestSubventionRate(int interestSubventionRate) {
		this.interestSubventionRate = interestSubventionRate;
	}

	public int getInterestSubventionRate() {
		return interestSubventionRate;
	}
	
	
	
	
	
}
