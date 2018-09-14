package com.infotech.sgsy.language;

import java.util.ArrayList;
import java.util.List;

import com.infotech.sgsy.common.MasterDAO;


public interface LanguageDAO extends MasterDAO{
	
	public abstract List<LanguageVO> getTitleList(String param) throws Exception;
	public abstract List<LanguageVO> getTopicListBYParam(String param) throws Exception;
	public abstract List getDetailsListByTitle(String titleId) throws Exception;
	
	public abstract List<LanguageVO> getTopicList() throws Exception;
	public abstract List<LanguageVO> getStatusList() throws Exception;
	public abstract int saveFeedback(LanguageVO langVO)throws Exception;
	public abstract int saveLanguage(LanguageVO langVO)throws Exception;
	public abstract List getFeedbackDetails(String stCode,String distCode,String entityLevel,String entityCode) throws Exception;

	public abstract String getLanguage(LanguageVO langVO) throws Exception;
	public abstract ArrayList getAllLanguage(LanguageVO langVO) throws Exception;
	public abstract ArrayList getAllResources(String languageId) throws Exception;
	public abstract int saveResources(LanguageVO langVO)throws Exception;
	public abstract List<LanguageVO> getAssignLanguage(String param) throws Exception;
	public abstract int assignLanguagePack(LanguageVO langVO)throws Exception;
	public abstract List getLanguagesById(String stCode) throws Exception;
	

}
