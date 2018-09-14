package com.infotech.sgsy.language;

import java.util.ArrayList;
import java.util.List;

import com.infotech.sgsy.common.LocationVO;

public interface LanguageService {
	
	public abstract int saveLanguage(LanguageVO langVO)throws Exception;
	public abstract String getLanguage(LanguageVO langVO)throws Exception;
	public abstract ArrayList getAllLanguage(LanguageVO langVO) throws Exception;
	public abstract ArrayList getAllResources(String languageId) throws Exception;
	public abstract int saveResources(LanguageVO langVO)throws Exception;
	public abstract List getState(LocationVO locationVO) throws Exception;
	public abstract List<LanguageVO> getAssignLanguage(String param) throws Exception;
	public abstract int assignLanguagePack(LanguageVO langVO)throws Exception;
	public abstract List getLanguagesById(String stCode) throws Exception;

}
