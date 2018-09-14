package com.infotech.sgsy.accessControl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.exception.PermissionNotSelectedException;
import com.infotech.sgsy.util.Constants;

public class AccessServiceImpl implements AccessService{
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public Map<String, Object> getLevelList(String levelCode) throws Exception {		
		
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		return accessMasterDAO.getLevelList(levelCode);
				
	}
	
	@Override
	public Map<String, Object> getSchemeList(String levelCode) throws Exception {		
		
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		return accessMasterDAO.getSchemeList(levelCode);
				
	}
	
	@Override
	public List getAllSubModule(AccessVO vo) throws Exception {		
		
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		List accessList = accessMasterDAO.selectAllSubModule(vo);
		return accessList;
				
	}
	
	@Override
	public boolean checkRecord(String roleName, String roleDesc)
			throws Exception {
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		boolean flag=accessMasterDAO.checkRecord(roleName,roleDesc);
		
		return flag;
	}
	
	public void addRole(AccessVO vo,List subModList) throws Exception {
		
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		Iterator itr=(Iterator)subModList.iterator();
		int count=0;
		while(itr.hasNext()){
			RoleMasterVO rVO =(RoleMasterVO)itr.next();
			if(rVO.isPermission()){
				
				count++;
			}
			
		}
		if(count==0) throw new PermissionNotSelectedException();
		accessMasterDAO.addRole(vo,subModList);
		
	}
	
	public Map<String, Object> getRoleNameList(AccessVO vo) throws Exception {		
		
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		
		return accessMasterDAO.getRoleNameList(vo);
				
	}
		
	public List getRoleDetailsList(AccessVO vo) throws Exception {
				
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		List roleDetailsList=accessMasterDAO.selectAllModuleSubModule(vo);
		List accessList = accessMasterDAO.getRoleDetailsList(vo);
		
		Iterator itr=roleDetailsList.iterator();
		while(itr.hasNext())  {
			RoleMasterVO roleVO =(RoleMasterVO)itr.next();
			Iterator roleItr= accessList.iterator();
			while(roleItr.hasNext()){
				
				RoleMasterVO rVO =(RoleMasterVO)roleItr.next();
				
				if(rVO.getFormcd().equalsIgnoreCase(roleVO.getFormcd()) ){
					
					roleVO.setPermission(true);
					
				}
				
					roleVO.setRoleName(rVO.getRoleName());
					roleVO.setRoleDescription(rVO.getRoleDescription());
					roleVO.setLevelRoleName(rVO.getLevelRoleName());
									
			}
		}
	
		return roleDetailsList;
				
	}
	
	public void modifyRole(AccessVO vo,List subModList) throws Exception {
	
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		Iterator itr=(Iterator)subModList.iterator();
		int count=0;
		while(itr.hasNext()){
			RoleMasterVO rVO =(RoleMasterVO)itr.next();
			if(rVO.isPermission()){
				
				count++;
			}
			
		}
		if(count==0) throw new PermissionNotSelectedException();
		accessMasterDAO.modifyRole(vo,subModList);
		
	}
	
	public List getSelectedRoleDetails(AccessVO vo) throws Exception {		
	
	AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
	List accessList = accessMasterDAO.getRoleDetailsList(vo);
	return accessList;
			
	}
	
	public void deleteRole(AccessVO vo,List subModList) throws Exception {
		
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		
		accessMasterDAO.deleteRole(vo,subModList);
		
	}
	
	public boolean checkRoleAssignedRecord(AccessVO vo)throws Exception {
		AccessMasterDAO accessMasterDAO = (AccessMasterDAO)MasterDAOFactory.getInstance(Constants.GET_ACCESS);
		boolean flag=accessMasterDAO.checkRoleAssignedRecord(vo);

		return flag;
	}

	


}
