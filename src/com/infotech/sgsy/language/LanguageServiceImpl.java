package com.infotech.sgsy.language;

import java.util.ArrayList;
import java.util.List;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.util.Constants;


public class LanguageServiceImpl implements LanguageService {
	
	
	@Override
	public  int saveLanguage(LanguageVO langVO)throws Exception
	{
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.saveLanguage(langVO);
	}
	public String getLanguage(LanguageVO langVO)throws Exception {
		
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.getLanguage(langVO);
	}
	public ArrayList getAllLanguage(LanguageVO langVO)throws Exception {
		
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.getAllLanguage(langVO);
	} 
	public ArrayList getAllResources(String languageId)throws Exception {
		
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.getAllResources(languageId);
	}
	public  int saveResources(LanguageVO langVO)throws Exception
	{
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.saveResources(langVO);
	}
	@Override
	public List getState(LocationVO locationVO) throws Exception {
		// TODO Auto-generated method stub
		return (new StateDAO()).getStates(locationVO);
	}
	@Override
	public List getAssignLanguage(String languageId)throws Exception{
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.getAssignLanguage(languageId);
	}
	
	@Override
	public int assignLanguagePack(LanguageVO langVO) throws Exception {
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.assignLanguagePack(langVO);
	}
	@Override
	public  List getLanguagesById(String stCode) throws Exception{
		LanguageDAO languageDAO = (LanguageDAO)MasterDAOFactory.getInstance(Constants.GET_LANGUAGE);
		return languageDAO.getLanguagesById(stCode);
	}
	
	
}
