package com.infotech.sgsy.master.loactionMasterStandardization;

import org.apache.struts.action.ActionForm;

public class LocationMasterStandardizationForm extends ActionForm {
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String gramPanchayatCode;
	private String villageCode;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getGramPanchayatCode() {
		return gramPanchayatCode;
	}

	public void setGramPanchayatCode(String gramPanchayatCode) {
		this.gramPanchayatCode = gramPanchayatCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

}
