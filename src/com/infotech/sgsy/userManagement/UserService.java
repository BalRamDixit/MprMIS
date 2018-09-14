package com.infotech.sgsy.userManagement;

import java.util.List;
import java.util.Map;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.common.UserIdentityVO;

public interface UserService{
	
	public List<MasterVO> getLevelList(String levelCode)throws Exception;
	public List<MasterVO> getuserReportList()throws Exception;
	
	public List<UserIdentityVO> getStateList()throws Exception;
	public abstract List<UserIdentityVO> getStateNameByStateCode(String stateCode)throws Exception;
	public List<UserIdentityVO> getDistrictListByStateCode(String code)throws Exception;
	public List<UserIdentityVO> getBlockListByDistrictCode(String code)throws Exception;
	
	public Map<String,Object> getDistrictList(String code)throws Exception;
	public Map<String,Object> getBlockList(String code)throws Exception;
	public Map<String,Object> getVillageList(String code)throws Exception;
	
	public int addUser(UserVO  userVO ) throws Exception;
	public int changeAdminPassword(UserVO  userVO ) throws Exception;
	public int activateAccount(UserVO  userVO ) throws Exception;
	
	public UserVO showUser(String entityCode,String loginId) throws Exception;
	public int updateUser(UserVO  userVO) throws Exception;
	
	public Map<String,Object> getBlockPanchayatListByDistrictCode(String userId,String code)throws Exception;
	public Map<String,Object> getVillagePanchayatListByBlockCode(String userId,String code)throws Exception;
	
	public boolean userNameCheck(UserVO userVO) throws Exception;
	public String getMailId(UserVO userVO) throws Exception;
	int updateUserDetail(UserVO userVO) throws Exception;
	
}