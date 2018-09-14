package com.infotech.sgsy.manageRole;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.util.Constants;

public class ManageRoleServiceImpl implements ManageRoleService{

	@Override
	public Map<String, Object> getLevelRoleList(String levelCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);		
		return manageRoleDAO.getRoleList(levelCode);		
	}

	@Override
	public Map<String, Object> getUserList(String code,HttpServletRequest request) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getUserList(code,request);		
	}

	@Override
	public String getLevelOfCode(String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getLevelOfCode(roleCode);	
	}

	@Override
	public Map<String, Object> getRole(String levelCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);		
		return manageRoleDAO.getRole(levelCode);	
	}
	
	@Override
	public Map<String, Object> getRole(String levelCode, String type)
			throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);		
		return manageRoleDAO.getRole(levelCode, type);
	}
	
	@Override
	public int assignRoleToUser(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.assignRoleToUser(manageRoleVO,request);		
	}

	@Override
	public String checkMultipleRole(ManageRoleVO manageRoleVO,	HttpServletRequest request) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.checkMultipleRole(manageRoleVO,request);	
	}

	@Override
	public List<ManageRoleVO> getRoleByLoginId(String loginId) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);		
		return manageRoleDAO.getRoleByLoginId(loginId);	
	}

	@Override
	public ManageRoleVO getNameByCode(ManageRoleVO roleVO) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getNameByCode(roleVO);	
	}

	@Override
	public Map<String, Object> getVillageList(String code, String loginId,String roleCode)throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getEntityList(code,loginId,roleCode);
	}

	@Override
	public Map<String, Object> getSelectedVillageList(String code,String loginId,String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getSelectedEntityList(code,loginId,roleCode);
	}

	@Override
	public Map<String, Object> getBlocksList(String code, String loginId,String roleCode)throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getEntityList(code,loginId,roleCode);
	}

	@Override
	public Map<String, Object> getSelectedBlocksList(String code, String loginId,String roleCode)throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getSelectedEntityList(code,loginId,roleCode);
	}

	@Override
	public Map<String, Object> getDistrictsList(String code, String loginId,String roleCode)throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getEntityList(code,loginId,roleCode);
	}

	@Override
	public Map<String, Object> getSelectedDistrictsList(String code,String loginId,String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getSelectedEntityList(code,loginId,roleCode);
	}

	@Override
	public List<UserIdentityVO> getSelectedStateList(String code, String loginId,String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getSelectedStateList(code, loginId,roleCode);
	}

	@Override
	public List<UserIdentityVO> getStatesList(String code, String loginId,String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getStatesList(code, loginId,roleCode);
	}

	@Override
	public int delete(String code, String loginId) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.delete(code,loginId);
	}

	@Override
	public int modify(String selectedValue [],Map<String, Object> selectedMap,List<UserIdentityVO> selectedStateValues,
			ManageRoleVO manageRoleVO,HttpServletRequest request)throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.modify(selectedValue,selectedMap,selectedStateValues,manageRoleVO,request);
	}

	@Override
	public List<UserIdentityVO> getSelectedDistrictList(String code,String loginId, String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getSelectedDistrictList(code, loginId,roleCode);
	}

	@Override
	public List<UserIdentityVO> getsDistrictList(String code, String loginId,String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getsDistrictList(code, loginId,roleCode);
	}

	@Override
	public List<UserIdentityVO> getSelectedBlockList(String code,String loginId, String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getSelectedBlockList(code, loginId,roleCode);
	}

	@Override
	public List<UserIdentityVO> getsBlockList(String code, String loginId,String roleCode) throws Exception {
		ManageRoleDAO manageRoleDAO = (ManageRoleDAO)MasterDAOFactory.getInstance(Constants.GET_ROLE);
		return manageRoleDAO.getsBlockList(code, loginId,roleCode);
	}

	
	
}