package com.infotech.sgsy.master.district;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.infotech.sgsy.common.MasterForm;

public class DistrictForm extends MasterForm {
	private List<DistrictForm> districtList = new ArrayList<DistrictForm>();
	private int interestSubventionRate;
	private String districtName;
	private String entityCode;
	
	
	public List<DistrictForm> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictForm> districtList) {
		this.districtList = districtList;
	}

	public DistrictForm getDistrict(int index) {
		while (this.districtList.size() <= index) {
			districtList.add(new DistrictForm());
		}
		return ((DistrictForm) districtList.get(index));
	}

	@Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.setInterestSubventionRate(0);		
        super.reset(mapping, request);
    }

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setInterestSubventionRate(int interestSubventionRate) {
		this.interestSubventionRate = interestSubventionRate;
	}

	public int getInterestSubventionRate() {
		return interestSubventionRate;
	}

	
	
}
