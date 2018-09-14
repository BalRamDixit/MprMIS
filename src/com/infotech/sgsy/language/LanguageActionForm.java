package com.infotech.sgsy.language;

import com.infotech.sgsy.common.MasterForm;

public class LanguageActionForm extends MasterForm{
	
	private String language;
	private String languageShortName;
	private String stateCode;
	private String writeAccess;
	
	public String getLanguageShortName() {
		return languageShortName;
	}

	public void setLanguageShortName(String languageShortName) {
		this.languageShortName = languageShortName;
	}

	public LanguageActionForm()
	{}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getWriteAccess() {
		return writeAccess;
	}

	public void setWriteAccess(String writeAccess) {
		this.writeAccess = writeAccess;
	}

	
}
