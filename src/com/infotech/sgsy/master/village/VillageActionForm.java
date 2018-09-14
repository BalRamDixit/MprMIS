package com.infotech.sgsy.master.village;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterForm;

public class VillageActionForm extends MasterForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String villageCode;
	private String villageName;
	private String levelCode;
	private String  blockCode ;
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	

	
	
}
