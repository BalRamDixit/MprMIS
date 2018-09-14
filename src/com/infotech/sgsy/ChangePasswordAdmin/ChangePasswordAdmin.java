package com.infotech.sgsy.ChangePasswordAdmin;

import javax.rmi.CORBA.UtilDelegate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.userManagement.UserDAOImpl;
import com.infotech.sgsy.userManagement.UserForm;
import com.infotech.sgsy.userManagement.UserService;
import com.infotech.sgsy.userManagement.UserServiceImpl;
import com.infotech.sgsy.userManagement.UserVO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;
import com.infotech.sgsy.util.mail.SendMail;

public class ChangePasswordAdmin extends MasterAction{
	
	ActionMessages messages=new ActionMessages();

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return null;
	}
	
	public ActionForward adminChangePassword(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String modifyPage = Constants.MODIFY;
		UserForm userForm = (UserForm) form;
		userForm.reset();
		log.info("inside showModify method");
		UserService userService = new UserServiceImpl();		

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		request.getSession().setAttribute("levelList",userService.getLevelList(loginVO.getLevelCode()));
		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();
		
		if(level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)){
			request.getSession().setAttribute("stateList", userService.getStateList());			
			modifyPage = Constants.MODIFY;
		}
		if(level.equalsIgnoreCase(Constants.MYSA_LEVEL)){
			request.getSession().setAttribute("stateList", userService.getStateList());			
			modifyPage = Constants.MODIFY;
		}
		if(level.equalsIgnoreCase(Constants.STATE_LEVEL)){
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));										
			modifyPage = "modifyPageState";
		}
		if(level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));									
			modifyPage = "modifyPageDistrict";
		}		
		
		request.getSession().setAttribute("searchSuccess",Constants.EMPTY_STRING);
			
			return mapping.findForward(modifyPage);
	}
	
	public ActionForward ChangePassword(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnpage = "";
		try {
			messages.clear();
			UserForm userForm = (UserForm) form;
			// userForm.reset();
			log.info("inside showModify method");
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			UserVO userVO = new UserVO();
			if (request.getParameter("loginId") != null) {
				userVO.setLoginId(request.getParameter("loginId"));
			}
			// userVO.setNewpassword(userForm.getNewpassword());
			// userVO.setNewpassword(Constants.DEFAULT_PASSWORD+RandomStringUtils.randomAlphanumeric(5));
			String randomAlphaNumeric = RandomStringUtils.randomAlphabetic(3)
					+ "@" + RandomStringUtils.randomNumeric(2) + "#"
					+ RandomStringUtils.randomAlphanumeric(5)
					+ RandomStringUtils.randomNumeric(2);
			/*
			 * userForm.setPassword(randomAlphaNumeric);
			 * userVO.setPassword(userForm.getPassword());
			 */
			userVO.setNewpassword(randomAlphaNumeric);
			String stateName = "", districtName = "", blockName = "", userLevel = "", userDef = "";
			UserDAOImpl userlist = new UserDAOImpl();
			String stCode = request.getParameter("stCode");
			if (stCode != null) {
				if (stCode.length() == 1) {
					stCode = "0" + stCode;
				}
				userForm.setStateCode(stCode);
				returnpage = "stnoPage";
			} else {
				returnpage = "success";
			}
			if (userForm.getStateCode() != null
					&& !userForm.getStateCode().equals("")) {
				stateName = userlist.getStateNameByStateCode(userForm.getStateCode()).get(0).getStateName();
				userLevel = " Level of User : State ";
				userDef = " User's State Name is : " + stateName;
			}

			if (userForm.getDistrictCode() != null
					&& !userForm.getDistrictCode().equals("")) {
				districtName = userlist.getDistrictNameByStateCode(userForm.getDistrictCode()).get(0).getDistrictName();
				userLevel = " Level of User : District ";
				userDef = userDef + " User's District Name is : " + districtName;
			}

			if (userForm.getBlockCode() != null && !userForm.getBlockCode().equals("")) {
				blockName = userlist.getBlockNameByDistrictCode(userForm.getBlockCode()).get(0).getBlockName();
				userLevel = " Level of User : Block ";
				userDef = userDef + " User's Block Name is : " + blockName;
			}

			if (stateName.equals("")) {
				userLevel = " Level of User : MoRD ";
			}

			String body = "Dear User, \n \n"
					+ " As per your request we have changed the password of your User Id."
					+ " \n \n Following are your UserId details for logging into NRLM-MIS available at http://nrlm.gov.in are \n \n "
					+ userLevel + "\n\n" 
					+ userDef 
					+ " \n User_Id: "+ userVO.getLoginId() 
					+ " \n User_Password : " + randomAlphaNumeric 
					+ "\n \n Administrator,\n NRLM-MIS ";

			userVO.setLastModifedBy(loginVO.getUserid());
			userVO.setLastModifiedOn(DateUtil.getPresentDate());
			UserService userService = new UserServiceImpl();
			int i = userService.changeAdminPassword(userVO);
			String Email = "", UserId = "";

			if (i == Constants.UPDATE_SUCCESS) {
				messages.add(SGSYConstants.MSG, new ActionMessage("passwordChanged"));
				String emailId = userService.getMailId(userVO);
				userVO.setEmailId(emailId);
				SendMail sendMail = new SendMail();

				UserId = userVO.getLoginId();

				// String body="Dear User, " + "\n"+"\n" +
				// "     Your login id for logging into SGSY is : "+userVO.getLoginId()+"."+"\n"+"\n"+"     Your new password is : "+userVO.getNewpassword()+"."+"\n"+"\n"+"Administrator, SGSY";
				sendMail.sendMail(userVO.getEmailId(), "User Id and New Password", body);

				userVO.setLoginId(loginVO.getUserid());

				emailId = userService.getMailId(userVO);
				userVO.setEmailId(emailId);

				// body="Dear Administrator, " + "\n"+"\n" +
				// "     You have changed the Password of : "+UserId+"."+"\n"+"\n"+"   as : "+userVO.getNewpassword()+"."+"\n"+"\n"+"This is the Auto Generated Mail, SGSY-MIS";

				body = "Dear Administrator, "
						+ "\n \n     You have changed the password of NRLM-MIS user \n \n" 
						+ userLevel + "\n\n"
						+ userDef + " \n\n"
						+ " UserId :"+ UserId + "\n"
						+ " Password: " + randomAlphaNumeric 
						+ "\n \n This is the Auto Generated Mail, NRLM-MIS";
				sendMail.sendMail(userVO.getEmailId(), "NRLM-MIS Password Reset: " + UserId, body);
			}
			userForm.reset();
		} catch (Exception e) {
			// messages.add(SGSYConstants.MSG,new
			// ActionMessage("errors.email"));
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		saveMessages(request, messages);
		return mapping.findForward(returnpage);		
	}
	
	public ActionForward showResetPassByStNodalOfficer(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//String modifyPage = Constants.MODIFY;
		UserForm userForm = (UserForm) form;
		userForm.reset();
		log.info("inside Changing Password by State Nodal Officer method");
		UserService userService = new UserServiceImpl();		

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		request.getSession().setAttribute("levelList",userService.getLevelList(loginVO.getLevelCode()));
		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();
		request.getSession().setAttribute("entityCode", entityCode);
//		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
//		request.getSession().setAttribute("stateCode", location.getStateCode());
		
//		if(level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)){
//			request.getSession().setAttribute("stateList", userService.getStateList());			
//			modifyPage = Constants.MODIFY;
//		}
		if(level.equalsIgnoreCase(Constants.MYSA_LEVEL)){
			request.getSession().setAttribute("stateList", userService.getStateList());			
			//modifyPage = Constants.MODIFY;
		}
		if(level.equalsIgnoreCase(Constants.STATE_LEVEL)){
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));
		}
		if(level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));									
			//modifyPage = "modifyPageDistrict";
		}		
		//request.getSession().setAttribute("searchSuccess",Constants.EMPTY_STRING);
		return mapping.findForward("stnoPage");
	}
	
	// FUNCTION USED TO ACTIVATE THE USER ACCOUNT
	public ActionForward activateAccount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ShowPage = "ACTIVATE_USER";
		UserForm userForm = (UserForm) form;
		userForm.reset();
		log.info("inside showModify method");
		UserService userService = new UserServiceImpl();		

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		request.getSession().setAttribute("levelList",userService.getLevelList(loginVO.getLevelCode()));
		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();
		
		/*if(level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)){
			request.getSession().setAttribute("stateList", userService.getStateList());			
			ShowPage ="ACTIVATE_USER";
		}*/
		if(level.equalsIgnoreCase(Constants.MYSA_LEVEL)){
			request.getSession().setAttribute("stateList", userService.getStateList());			
			ShowPage = "ACTIVATE_USER";
		}
		if(level.equalsIgnoreCase(Constants.STATE_LEVEL)){
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("stateList", userService.getStateNameByStateCode(loginVO.getEntityCode()));	
			/*request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));	*/									
			ShowPage = "ACTIVATE_USER";
		}
		/*if(level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));									
			ShowPage = "ACTIVATE_USER";
		}	*/	
		request.getSession().setAttribute("searchSuccess",Constants.EMPTY_STRING);
			return mapping.findForward(ShowPage);
	}
	
	// FUNCTION USED TO ACTIVATE THE ACCOUNT
	public ActionForward activateDone(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ShowPage = "ACTIVATE_USER";
		try {
			messages.clear();
			UserForm userForm = (UserForm) form;
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			UserVO userVO = new UserVO();
			
			if (request.getParameter("loginId") != null) {
				userVO.setLoginId(request.getParameter("loginId"));
			}
	
			userVO.setLastModifedBy(loginVO.getUserid());
			userVO.setLastModifiedOn(DateUtil.getPresentDate());
			UserService userService = new UserServiceImpl();
			int i = userService.activateAccount(userVO);
			if (i == Constants.UPDATE_SUCCESS) {
				messages.add(SGSYConstants.MSG, new ActionMessage("accountActivated"));
			}
			userForm.reset();
		} catch (Exception e) {
		
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		saveMessages(request, messages);
		return mapping.findForward(ShowPage);		
	}
	
}
