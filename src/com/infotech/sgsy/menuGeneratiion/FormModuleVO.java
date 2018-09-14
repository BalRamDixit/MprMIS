package com.infotech.sgsy.menuGeneratiion;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class FormModuleVO extends MasterVO implements Serializable{
	
	private String modcd;
	private String formcd;
	private String formName;
	private String formURL;
	private String schemeCode;
	private String schemeName;
	
	public FormModuleVO() {
		
	}	

	public FormModuleVO(String modcd, String formcd, String formName,
			String formURL, String schemeCode, String schemeName) {
		super();
		this.modcd = modcd;
		this.formcd = formcd;
		this.formName = formName;
		this.formURL = formURL;
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


	public String getFormcd() {
		return formcd;
	}


	public void setFormcd(String formcd) {
		this.formcd = formcd;
	}


	public String getFormName() {
		return formName;
	}


	public void setFormName(String formName) {
		this.formName = formName;
	}


	public String getFormURL() {
		return formURL;
	}


	public void setFormURL(String formURL) {
		this.formURL = formURL;
	}

}
