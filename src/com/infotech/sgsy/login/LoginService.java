package com.infotech.sgsy.login;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.userAccessControlManagement.AccessModuleListForUserAndMenuBean;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;

public interface LoginService {
	
	public abstract UserMaster login(String userId,String password ,HttpServletRequest request,String changeRoleFor,String trackerId) throws Exception;
	
	
	
	public boolean checkOldPassword(String password,String userid, String string)throws Exception;
	public int changePassword(String newPassword,String oldPassword,String userid)throws Exception;
		
	
	public abstract LoginVO count(String userId) throws Exception;
	public abstract AccessModuleListForUserAndMenuBean getAccessModuleListForMenu(UserMaster loginVO);
	public abstract List<ProjectDetailsVO> getAssignProjectList(UserMaster loginVO);
	
}
