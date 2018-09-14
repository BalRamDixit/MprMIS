package com.infotech.sgsy.selfHelpGroup;

import java.util.List;

//import sun.security.jca.GetInstance;

import com.infotech.sgsy.common.BusinessMaster;
//import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.master.bank.BankDAO;
import com.infotech.sgsy.master.bankBranch.BankBranchDAO;
import com.infotech.sgsy.master.village.VillageDAO;
//import com.infotech.sgsy.selfHelpGroup.UP.SHGVO;
import com.infotech.sgsy.util.Constants;

public class SHGBusiness extends BusinessMaster{
	
	SHGMasterDAO masterDAO = null;
	BankDAO bankDAO = null;
	BankBranchDAO branchDAO = null;
	// Function used to save the detail of SHG and its member in the database
	public boolean Save(MasterShgVO shgDetails, List<MasterShgMemberDetails> shgMemberList, String shortName) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.Save(shgDetails, shgMemberList, shortName);
	}
	
	// Function used to save the detail of SHG and its member in the database
	/*public boolean SaveSHGMember(SHGActionForm shgForm, String entityCode, String shortName) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.Save(shgForm, shortName);
	}*/
	
	// Function used to get the bank list of selected entity code
	public List getBankList(String entityCode) throws Exception{
		bankDAO=(BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO) ;
		return bankDAO.getBankList(entityCode);
	}
	
	// Function used to get the bank branch list of the state
	/*public List getBankBranchList(String entityCode,String bankCode) throws Exception{
		bankDAO=(BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO) ;
		return bankDAO.getBankBranch(entityCode, bankCode);
	}*/
	
	// Function used to get the bank branch list of the state
	public List getBankBranchListNew(String bankCode, String entityCode) throws Exception{
		branchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO) ;
		return branchDAO.getBranchByBankForDropdown(bankCode, entityCode);
	}
	
	// Function used to get the village of selected block or GP
	public List getVillageList(String entityCode) throws Exception{
		VillageDAO  villageDAO=	(VillageDAO) MasterDAOFactory.getInstance(Constants.GET_VILLAGE_DAO) ;
		return villageDAO.getVillageList(entityCode);
	}
	
	// Function used to get the village of selected block or GP
	public List getVillageListwithoutSelect(String entityCode) throws Exception{
		VillageDAO  villageDAO=	(VillageDAO) MasterDAOFactory.getInstance(Constants.GET_VILLAGE_DAO) ;
		return villageDAO.getVillageListWithoutSelect(entityCode);
	}

	
	// -------------------------------------------  CODE NOT IN USED  ------------------------------------------
	
	/*public List getDesignation() throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getDesignation();
	}*/
	
	/*public List getFinancialYear() throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getFinancialYear();
	}*/
	/*public List getActivities(String entityCode) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getActivities(entityCode);
	}*/
	
/*	public boolean Modify(MasterShgVO entity,String shortName) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.Modify(entity, shortName);
	}*/
	
/*	public int delete(LocationVO location,MasterShgVO entity) throws Exception {
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.delete(location, entity);
	}*/
	
	/*public List getGroupDetail(LocationVO locationVO, String groupId) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getGroupDetail(locationVO, groupId);
	}*/
	
	/*public List getGroupActivities(LocationVO locationVO, String groupId) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getGroupActivities(locationVO, groupId);
	}*/
	
	/*public List getAllGroupMembers(LocationVO locationVO, String groupId) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getAllGroupMembers(locationVO, groupId);
	}*/
	
	/*public List getMembersforModify(LocationVO locationVO,String familyCode,String memberCode,String flag)  throws Exception{	
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getMembersforModify(locationVO, familyCode, memberCode,flag);
	}*/
	/*public List getMembers(LocationVO locationVO,String familyCode,String memberCode,String flag) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getMembers(locationVO, familyCode, memberCode,flag);
	}*/
	
	/*public List getModifiedMembers(LocationVO locationVO,String familyCode,String memberCode,String flag) throws Exception{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getMemberForModify(locationVO, familyCode, memberCode,flag);
	}*/
	
	/*public String getDuplicateMembers(LocationVO locationVO) throws Exception {
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getDuplicateMembers(locationVO);
	}*/
	/*public String getDuplicateFamilyCode(LocationVO locationVO) throws Exception 
	{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getDuplicateFamilyCode(locationVO);
	}*/
	/*public String getDuplicateGroupName(LocationVO locationVO) throws Exception 
	{
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.getDuplicateGroupName(locationVO);
	}*/
	/*public boolean checkLoanApplication(LocationVO locationVO,String groupCode,String finYr) throws Exception {
		masterDAO=	(SHGMasterDAO) MasterDAOFactory.getInstance(Constants.GET_SHG_DAO) ;
		return masterDAO.checkLoanApplication(locationVO, groupCode, finYr);
	}*/
	
}
