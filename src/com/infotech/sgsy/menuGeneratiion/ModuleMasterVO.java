package com.infotech.sgsy.menuGeneratiion;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class ModuleMasterVO extends MasterVO implements Serializable{
	
	private String modcd;
	private String modName;
	private String schemeCode;
	private String schemeName;
	
	public ModuleMasterVO() {
		
	}	
	
	public ModuleMasterVO(String modcd, String modName, String schemeCode,
			String schemeName) {
		super();
		this.modcd = modcd;
		this.modName = modName;
		this.schemeCode = schemeCode;
		this.schemeName = schemeName;
	}

	public String getSchemeCode() {
		return schemeCode;
	}


	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}


	public String getSchemeName() {
		return schemeName;
	}


	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	public String getModcd() {
		return modcd;
	}


	public void setModcd(String modcd) {
		this.modcd = modcd;
	}


	public String getModName() {
		return modName;
	}


	public void setModName(String modName) {
		this.modName = modName;
	}

}
