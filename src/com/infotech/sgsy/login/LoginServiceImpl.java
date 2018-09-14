package com.infotech.sgsy.login;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.infotech.sgsy.accessControl.RoleMasterVO;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.menuGeneratiion.FormModuleVO;
import com.infotech.sgsy.menuGeneratiion.ModuleMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.userAccessControlManagement.AccessModuleListForUserAndMenuBean;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.userManagement.UserDAO;
import com.infotech.sgsy.userManagement.UserVO;
import com.infotech.sgsy.util.Constants;

/**
 * 
 * @author NIC
 *
 */
public class LoginServiceImpl implements LoginService{

	/**
	 * 
	 */
	@Override
	public UserMaster login(String userId, String password,HttpServletRequest request,String changeRoleFor,String trackerId) throws Exception {
		
		LoginMasterDAO loginMasterDAO = (LoginMasterDAO)MasterDAOFactory.getInstance(Constants.GET_LOGIN);
		String ipAddress = request.getRemoteAddr();
		UserMaster loginVO = loginMasterDAO.login(userId,password,ipAddress,changeRoleFor,trackerId);
		
		
		return loginVO;
	}
		@Override
	public boolean checkOldPassword(String password, String userid,String trackeId)	throws Exception {
		LoginMasterDAO loginMasterDAO = (LoginMasterDAO)MasterDAOFactory.getInstance(Constants.GET_LOGIN);
		boolean flag=loginMasterDAO.checkOldPassword(password,userid,trackeId);	
		return flag;
	}

	/**
	 * 
	 */
	@Override
	public int changePassword(String newPassword, String oldPassword,String userid) throws Exception {	
		LoginMasterDAO loginMasterDAO = (LoginMasterDAO)MasterDAOFactory.getInstance(Constants.GET_LOGIN);
		int flag=loginMasterDAO.changePassword(newPassword,oldPassword,userid);
		return flag;
	}

	/**
	 * 
	 * @param list
	 * @param value
	 * @return
	 */
	public boolean checkValueInListForModule(List list,String value){
		Iterator itr=list.iterator();
		while(itr.hasNext()){
			ModuleMasterVO mVO=(ModuleMasterVO)itr.next();
			if(mVO.getModcd().equals(value))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param list
	 * @param value
	 * @return
	 */
	public boolean checkValueInListForFormModule(List list,String value){
		Iterator itr=list.iterator();
		while(itr.hasNext()){
			FormModuleVO fVO=(FormModuleVO)itr.next();
			if(fVO.getFormcd().equals(value))
				return true;
		}
		return false;
	}

	/**
	 * 
	 */
	
	public LoginVO count(String userId) throws Exception {
		LoginMasterDAO loginMasterDAO = (LoginMasterDAO)MasterDAOFactory.getInstance(Constants.GET_LOGIN);
		LoginVO loginVO1 = loginMasterDAO.count(userId);
		return loginVO1;
		
	}
	
	/**
	 * 
	 */
	public AccessModuleListForUserAndMenuBean getAccessModuleListForMenu(UserMaster user){
		LoginMasterDAO loginMasterDAO = new LoginMasterDAOImpl();
		return loginMasterDAO.getAccessModuleListForMenu(user);
	}
	
	@Override
	public List<ProjectDetailsVO> getAssignProjectList(UserMaster loginVO) {
		LoginMasterDAO loginMasterDAO = new LoginMasterDAOImpl();
		return loginMasterDAO.getAssignProjectList(loginVO);
	}

	

}
