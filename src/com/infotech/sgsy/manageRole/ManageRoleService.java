package com.infotech.sgsy.manageRole;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.infotech.sgsy.common.UserIdentityVO;

public interface ManageRoleService {

	public Map<String,Object> getLevelRoleList(String levelCode)throws Exception;
	public Map<String,Object> getRole(String levelCode)throws Exception;
	public Map<String,Object> getRole(String levelCode, String type)throws Exception;
	public Map<String,Object> getUserList(String code,HttpServletRequest request)throws Exception;
	public  String getLevelOfCode(String roleCode)throws Exception;	
	public int assignRoleToUser(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception;
	public String checkMultipleRole(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception;
	public List<ManageRoleVO> getRoleByLoginId(String loginId)throws Exception;
	public ManageRoleVO getNameByCode(ManageRoleVO mRoleVO)throws Exception;
	
	public Map<String,Object> getVillageList(String code,String loginId,String roleCode)throws Exception;
	public Map<String,Object> getSelectedVillageList(String code,String loginId,String roleCode)throws Exception;
	
	public Map<String,Object> getBlocksList(String code,String loginId,String roleCode)throws Exception;
	public Map<String,Object> getSelectedBlocksList(String code,String loginId,String roleCode)throws Exception;
	
	public Map<String,Object> getDistrictsList(String code,String loginId,String roleCode)throws Exception;
	public Map<String,Object> getSelectedDistrictsList(String code,String loginId,String roleCode)throws Exception;
	
	public List<UserIdentityVO> getStatesList(String code,String loginId,String roleCode)throws Exception;
	public List<UserIdentityVO> getSelectedStateList(String code,String loginId,String roleCode)throws Exception;
	
	public List<UserIdentityVO> getsDistrictList(String code,String loginId,String roleCode)throws Exception;
	public List<UserIdentityVO> getSelectedDistrictList(String code,String loginId,String roleCode)throws Exception;
	
	public List<UserIdentityVO> getsBlockList(String code,String loginId,String roleCode)throws Exception;
	public List<UserIdentityVO> getSelectedBlockList(String code,String loginId,String roleCode)throws Exception;
	
	public int  delete(String code,String loginId)throws Exception;
	public int  modify(String selectedValue [],Map<String, Object> selectedMap,List<UserIdentityVO> selectedStateValues,ManageRoleVO manageRoleVO,HttpServletRequest request)throws Exception;
	
	
}