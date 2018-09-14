package com.infotech.sgsy.userManagement;

import java.util.List;
import java.util.Map;

import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.common.UserIdentityVO;
				
public interface UserDAO extends MasterDAO{
	
	public abstract List<MasterVO> getLevelList(String levelCode)throws Exception;
	public abstract List<MasterVO> getuserReportList()throws Exception;
	
	public abstract List<UserIdentityVO> getStateList()throws Exception;
	public abstract List<UserIdentityVO> getStateNameByStateCode(String stateCode)throws Exception;
	
	public List<UserIdentityVO> getDistrictListByStateCode(String code)throws Exception;
	public List<UserIdentityVO> getBlockListByDistrictCode(String code)throws Exception;
	
	public abstract Map<String,Object> getEntityList(String code)throws Exception;	
	public abstract UserVO showUser(String entityCode,String loginId) throws Exception;
	
	public List<UserIdentityVO> getVillageList(String entitycode)throws Exception;
	
	public List<UserIdentityVO> getDistrictPanchayatListByStateCode(String userId,String code)throws Exception;
	public Map<String,Object> getBlockPanchayatListByDistrictCode(String userId,String code)throws Exception;
	public Map<String,Object> getVillagePanchayatListByBlockCode(String userId,String code)throws Exception;
	public List<UserIdentityVO> getBlockPanchayatList(String userId,String code)throws Exception;
	public List<UserIdentityVO> getBlockPanchList(String userId,String code)throws Exception;
	
	public String userNameCheck(String loginId) throws Exception;
	
	public abstract int changeAdminPassword(UserVO userVO) throws Exception ;
	public abstract int activateAccount(UserVO userVO) throws Exception ;
	public abstract String getMailId(UserVO userVO) throws Exception ;
	
	public abstract int updateDetail(MasterVO  masterVO )throws Exception;
	
}