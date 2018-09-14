package com.infotech.sgsy.manageRole;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.UserIdentityVO;

public interface ManageRoleDAO extends MasterDAO{
	public abstract Map<String,Object> getRoleList(String levelCode)throws Exception;
	public abstract Map<String,Object> getUserList(String code,HttpServletRequest request)throws Exception;
	public abstract String getLevelOfCode(String roleCode)throws Exception;
	public abstract Map<String,Object> getRole(String levelCode)throws Exception;
	public abstract Map<String,Object> getRole(String levelCode, String type)throws Exception;
	public int assignRoleToUser(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception;
	public String checkMultipleRole(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception;	
	public List<ManageRoleVO> getRoleByLoginId(String loginId)throws Exception;
	public ManageRoleVO getNameByCode(ManageRoleVO mRoleVO)throws Exception;
	
	public Map<String,Object> getEntityList(String code,String loginId,String roleCode)throws Exception;
	public Map<String,Object> getSelectedEntityList(String code,String loginId,String roleCode)throws Exception;
	
	public List<UserIdentityVO> getStatesList(String code,String loginId,String roleCode)throws Exception;
	public List<UserIdentityVO> getSelectedStateList(String code,String loginId,String roleCode)throws Exception;
	
	public List<UserIdentityVO> getsDistrictList(String code,String loginId,String roleCode)throws Exception;
	public List<UserIdentityVO> getSelectedDistrictList(String code,String loginId,String roleCode)throws Exception;
	
	public List<UserIdentityVO> getsBlockList(String code,String loginId,String roleCode)throws Exception;
	public List<UserIdentityVO> getSelectedBlockList(String code,String loginId,String roleCode)throws Exception;
	
	public int  delete(String code,String loginId)throws Exception;
	public int  modify(String selectedValue [],Map<String, Object> selectedMap,List<UserIdentityVO> selectedStateValues,
			ManageRoleVO manageRoleVO,HttpServletRequest request)throws Exception;
}