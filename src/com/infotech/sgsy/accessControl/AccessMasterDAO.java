package com.infotech.sgsy.accessControl;

import java.util.List;
import java.util.Map;

import com.infotech.sgsy.common.MasterDAO;

public interface AccessMasterDAO extends MasterDAO{
	
	public abstract Map<String, Object> getLevelList(String levelCode)throws Exception;
	public abstract Map<String, Object> getSchemeList(String levelCode)throws Exception;
	public abstract List selectAllSubModule(AccessVO vo)throws Exception;
	public boolean checkRecord(String roleName,String roleDesc)throws Exception;
	public void addRole(AccessVO vo,List subModList) throws Exception; 
	public abstract Map<String, Object> getRoleNameList(AccessVO vo)throws Exception;
	public abstract List getRoleDetailsList(AccessVO vo)throws Exception;
	public void modifyRole(AccessVO vo,List subModList) throws Exception; 
	public abstract List selectAllModuleSubModule(AccessVO vo)throws Exception;
	public void deleteRole(AccessVO vo,List subModList) throws Exception; 
	public boolean checkRoleAssignedRecord(AccessVO vo)throws Exception;

}
