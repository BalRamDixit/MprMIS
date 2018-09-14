package com.infotech.sgsy.login;

import java.util.List;

import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.userAccessControlManagement.AccessModuleListForUserAndMenuBean;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.userManagement.UserVO;


public interface LoginMasterDAO extends MasterDAO{
	
	public abstract UserMaster login(String userId, String password,String ipAddress,String changeRoleFor,String trackerId)throws Exception;
	public boolean checkOldPassword(String password,String userid, String trackeId)throws Exception;
	public int changePassword(String oldPassword,String newPassword,String userid)throws Exception;
	public abstract LoginVO count(String userId)throws Exception;
	
	
	public abstract AccessModuleListForUserAndMenuBean getAccessModuleListForMenu(UserMaster user);
	public abstract List<ProjectDetailsVO> getAssignProjectList(UserMaster loginVO);
	

}
