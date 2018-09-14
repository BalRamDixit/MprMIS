package com.infotech.sgsy.userManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.common.MasterDAOFactory;

import com.infotech.sgsy.login.LoginVO;

import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.PopUpMessages;
import com.infotech.sgsy.util.PropertyModel;
import com.infotech.sgsy.util.SGSYConstants;
import com.infotech.sgsy.util.mail.SendMail;

/**
 * 
 * @author NIC
 *
 */
public class UserAction extends MasterAction {

	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();

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

		message.clear();
		log.info("inside modify method");			

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String modifyPage = Constants.MODIFY;
		String level = null;
		UserForm actionForm = (UserForm) form;
		UserVO userVO = new UserVO();
		actionForm.setLastModifedBy(loginVO.getUserid());

		UserDAO userDAO = (UserDAO) MasterDAOFactory.getInstance(Constants.GET_USER);
		BeanUtils.copyProperties(userVO, actionForm);
		userVO.setLastModifedBy(loginVO.getUserid());

		// RandomStringUtils.randomAscii(3);

		// char[] aa={'s','g'};

		// RandomStringUtils.random(1, 2, 3, true,true,aa, new
		// java.util.Random());

		// RandomStringUtils.randomAlphabetic(5);
		/*String randomAlphaNumeric = RandomStringUtils.randomAlphabetic(3) + "@"
				+ RandomStringUtils.randomNumeric(2) + "#"
				+ RandomStringUtils.randomAlphanumeric(5)
				+ RandomStringUtils.randomNumeric(2);
		actionForm.setPassword(randomAlphaNumeric);
		userVO.setPassword(actionForm.getPassword());*/

		String stateName = "", districtName = "", blockName = "", userLevel = "", userDef = "";
		UserDAOImpl userlist = new UserDAOImpl();

		if (actionForm.getStateCode() != null) {
			stateName = userlist.getStateNameByStateCode(actionForm.getStateCode()).get(0).getStateName();
			userLevel = " Level of User : State ";
			userDef = " User's State Name is : " + stateName;
		} else {
			/*
			 * stateName=userlist.getStateNameByStateCode(loginVO.getEntityCode()
			 * ).get(0).getStateName(); userLevel="Level of User : State ";
			 * userDef="User's State Name is : "+stateName;
			 */
			if (loginVO.getEntityCode() != null)
				if (loginVO.getEntityCode().length() > 1) {
					stateName = userlist.getStateNameByStateCode(loginVO.getEntityCode()).get(0).getStateName();
					userLevel = " Level of User : State ";
					userDef = " User's State Name is : " + stateName;

				}

		}

		if (actionForm.getDistrictCode() != null
				&& !actionForm.getDistrictCode().equals("")) {
			districtName = userlist.getDistrictNameByStateCode(actionForm.getDistrictCode()).get(0).getDistrictName();
			userLevel = " Level of User : District ";
			userDef = userDef + "\n" + "\n" + " User's District Name is : "+ districtName;
		}

		if (actionForm.getBlockCode() != null && !actionForm.getBlockCode().equals("")) {
			blockName = userlist.getBlockNameByDistrictCode(actionForm.getBlockCode()).get(0).getBlockName();
			userLevel = "Level of User : Block ";
			userDef = userDef + "\n" + "\n"

			+ " User's Block Name is : " + blockName;
		}

		if (stateName.equals("")) {
			userLevel = " Level of User : MoRD ";

		}
		/*String body = "Dear User, " 
		+ "\n\n Your UserID is being Update. " 
		+ "Following are your UserId details for logging into NRLM-MIS available at http://nrlm.gov.in are :"
		+ "\n \n" 
		+ userLevel + "\n"
		+ userDef + "\n" 
		+ " User Id is : " + userVO.getLoginId() + "\n" 
		+ " Password is : " + userVO.getPassword() + "\n" + "\n"
		+ " Administrator,\n NRLM-MIS";*/

		UserService userService = new UserServiceImpl();
		int i = userService.updateUserDetail(userVO);

		// userDAO.userNameCheck(userVO.getLoginId());

		if (i == Constants.UPDATE_SUCCESS) {
			actionForm.reset();
			actionForm.setInformationDialog(true);
			actionForm.setInformationDialogHeader(PopUpMessages.MODIFY_HEADER);
			actionForm.setInformationDialogText(PopUpMessages.MODIFY_TEXT);
			request.getSession().setAttribute("searchSuccess",Constants.EMPTY_STRING);
			request.getSession().setAttribute("userModified","true");
			/*if ((userVO.getActiveFlag()).equalsIgnoreCase("Y")) {
				SendMail sendMail = new SendMail();
				sendMail.sendMail(userVO.getEmailId(), "NRLM UserID Modified",body);
			}*/

		} else if (i == Constants.NAME_FOUND) {
			message.add(SGSYConstants.MSG, new ActionMessage("name.found",context.getAttribute("label.user.user").toString()));
			saveMessages(request, message);
		} else {
			message.add(SGSYConstants.MSG, new ActionMessage("update.fail.message", context .getAttribute("label.user.user").toString()));
			saveMessages(request, message);
		}

		// LoginVO loginVO = (LoginVO)
		// request.getSession().getAttribute("loginVO");
		level = loginVO.getLevelCode();
		actionForm.setEntityCode(loginVO.getEntityCode());
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			modifyPage = Constants.MODIFY;
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			modifyPage = "modifyPageState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			modifyPage = "modifyPageDistrict";
		}

		request.getSession().removeAttribute("state");
		request.getSession().removeAttribute("district");
		request.getSession().removeAttribute("block");
		request.getSession().removeAttribute("village");
		request.getSession().removeAttribute("stateList");
		request.getSession().removeAttribute("districtList");
		request.getSession().removeAttribute("blockList");
		request.getSession().removeAttribute("villageList");
		request.getSession().removeAttribute("userList");

		return mapping.findForward(modifyPage);

	}

	public ActionForward clear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("inside clear method");
		UserForm actionForm = (UserForm) form;
		actionForm.setUserName(null);
		actionForm.setUserDesignation(null);
		actionForm.setEmailId(null);
		actionForm.setActiveFlag("Y");
		String modifyPage = Constants.MODIFY;

		LoginVO loginVO = (LoginVO) request.getSession()
				.getAttribute("loginVO");
		String level = loginVO.getLevelCode();

		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			modifyPage = Constants.MODIFY;
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			modifyPage = "modifyPageState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			modifyPage = "modifyPageDistrict";
		}
		return mapping.findForward(modifyPage);
	}

	
	/**
	 * 
	 */
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("UserAction: inside save method... ");
		String savePage = Constants.SAVE;
		UserForm actionForm = (UserForm) form;
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

		String level = loginVO.getLevelCode();
		
		if ((loginVO.getLevelCode().trim()
				.equalsIgnoreCase(Constants.STATE_LEVEL) && actionForm
				.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL))
				|| (loginVO.getLevelCode().trim()
						.equalsIgnoreCase(Constants.DISTRICT_LEVEL) && actionForm
						.getLevelCode().trim()
						.equalsIgnoreCase(Constants.DISTRICT_LEVEL))) {

			actionForm.setEntityCode(loginVO.getEntityCode());

		} else if ((loginVO.getLevelCode().trim().equalsIgnoreCase("0") && actionForm
				.getLevelCode().trim().equalsIgnoreCase("0")))
			actionForm.setEntityCode(level);

		else {
			
			if (loginVO.getLevelCode().trim().equalsIgnoreCase("1")) {
				actionForm.setEntityCode("0");
			}
			if (loginVO.getLevelCode().trim().equalsIgnoreCase("0")) {
				actionForm.setEntityCode("0");
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.STATE_LEVEL)) {
				actionForm.setEntityCode(actionForm.getStateCode());
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
				actionForm.setEntityCode(actionForm.getDistrictCode());
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
				actionForm.setEntityCode(actionForm.getBlockCode());
			}
		}
		UserVO userVO = new UserVO();
		actionForm.setCreatedBy(loginVO.getUserid());

		userVO.setCreatedBy(actionForm.getCreatedBy());

		BeanUtils.copyProperties(userVO, actionForm);

		String randomAlphaNumeric = RandomStringUtils.randomAlphabetic(3) + "@"
				+ RandomStringUtils.randomNumeric(2) + "#"
				+ RandomStringUtils.randomAlphanumeric(5)
				+ RandomStringUtils.randomNumeric(2);
		actionForm.setPassword(randomAlphaNumeric);
		userVO.setPassword(actionForm.getPassword());

		String stateName = "", districtName = "", blockName = "", userLevel = "", userDef = "";
		UserDAOImpl userlist = new UserDAOImpl();
		if (actionForm.getStateCode() != null) {
			stateName = userlist
					.getStateNameByStateCode(actionForm.getStateCode()).get(0)
					.getStateName();
			userLevel = " Level of User : State ";
			userDef = " User's State Name is : " + stateName;
		} else {
			if (loginVO.getEntityCode() != null)
				if (loginVO.getEntityCode().length() > 1) {
					stateName = userlist
							.getStateNameByStateCode(loginVO.getEntityCode())
							.get(0).getStateName();
					userLevel = " Level of User : State ";
					userDef = " User's State Name is : " + stateName;
				}
		}

		if (actionForm.getDistrictCode() != null && !actionForm.getDistrictCode().equals("")) {
			districtName = userlist.getDistrictNameByStateCode(actionForm.getDistrictCode()).get(0).getDistrictName();
			userLevel = " Level of User : District ";
			userDef = userDef + " User's District Name is : "
					+ districtName;
		}

		if (actionForm.getBlockCode() != null
				&& !actionForm.getBlockCode().equals("")) {
			blockName = userlist.getBlockNameByDistrictCode(actionForm.getBlockCode()).get(0).getBlockName();
			userLevel = " Level of User : Block ";
			userDef = userDef  + " User's Block Name is : " + blockName;
		}

		if (stateName.equals("")) {
			userLevel = " Level of User : MoRD ";
		}
		String body = "Dear New User, \n" 
				+ " \n A new UserId is created for you, by System Administrator. " 
				+ " \n Following are your UserId details for logging into Deen Dayal Upadhyaya Grameen Kaushalya Yojana (DDU-GKY) available at http://ddugky.gov.in/prn/"
				+ " \n \n" + userLevel 
				+ " \n"+ userDef 
				+ " \n User Id is : " + userVO.getLoginId() + "\n"
				+ " Password is : " + userVO.getPassword() + " \n \n" + 
				" Administrator, \n DDU-GKY";
		UserService userService = new UserServiceImpl();
		int duplicateloginId = userService.addUser(userVO);
		if (duplicateloginId == 23505) {
			actionForm.setInformationDialog(false);
			actionForm.setInformationDialogHeader(null);
			actionForm.setInformationDialogText(null);
			ActionErrors errors = new ActionErrors();
			errors.add("loginId", new ActionError("errors.lookup.duplicate", "loginId"));
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
		}
		if (duplicateloginId != 23505) {
			actionForm.reset();
			actionForm.setInformationDialog(true);
			actionForm.setInformationDialogHeader(PopUpMessages.SAVE_HEADER);
			actionForm.setInformationDialogText(PopUpMessages.SAVE_TEXT);
			if ((userVO.getActiveFlag()).equalsIgnoreCase("Y")) {
				SendMail sendMail = new SendMail();
				sendMail.sendMail(userVO.getEmailId(), "DDU-GKY New User Created: "+ userVO.getUserName(), body);
			}
		}
		String entityCode = loginVO.getEntityCode();
		level = loginVO.getLevelCode();
		request.getSession().setAttribute("levelList", userService.getLevelList(level));
		request.getSession().removeAttribute("state");
		request.getSession().removeAttribute("district");
		request.getSession().removeAttribute("block");
		request.getSession().removeAttribute("village");
		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			request.getSession().removeAttribute("districtList");
			request.getSession().removeAttribute("blockList");
			request.getSession().removeAttribute("villageList");
			request.getSession().removeAttribute("userList");
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			request.getSession().removeAttribute("districtList");
			request.getSession().removeAttribute("blockList");
			request.getSession().removeAttribute("villageList");
			request.getSession().removeAttribute("userList");
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));
			request.getSession().removeAttribute("stateList");
			request.getSession().removeAttribute("blockList");
			request.getSession().removeAttribute("villageList");
			request.getSession().removeAttribute("userList");
		}
		if (level.equalsIgnoreCase("3")) {
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));
			request.getSession().removeAttribute("stateList");
			request.getSession().removeAttribute("districtList");
			request.getSession().removeAttribute("villageList");
			request.getSession().removeAttribute("userList");
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			savePage = Constants.SAVE;
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			savePage = "saveState";
		}
		if (level.equalsIgnoreCase("3")) {
			savePage = "saveDistrict";
		}
		return mapping.findForward(savePage);
	}

	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("new User creatation show method... ");
		String showPage = Constants.SHOW_PAGE;
		UserForm userForm = (UserForm) form;
		userForm.reset();
		
		UserService userService = new UserServiceImpl();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();

		request.getSession().setAttribute("levelList", userService.getLevelList(level));

		request.getSession().removeAttribute("state");
		request.getSession().removeAttribute("district");
		request.getSession().removeAttribute("block");
		request.getSession().removeAttribute("village");
		request.getSession().removeAttribute("stateList");
		request.getSession().removeAttribute("districtList");
		request.getSession().removeAttribute("blockList");
		request.getSession().removeAttribute("villageList");
		request.getSession().removeAttribute("userList");
		return mapping.findForward(showPage);
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

		String modifyPage = Constants.MODIFY;
		UserForm userForm = (UserForm) form;
		userForm.reset();
		log.info("inside showModify method");
		UserService userService = new UserServiceImpl();

		request.getSession().removeAttribute("userModified");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		request.getSession().setAttribute("levelList", userService.getLevelList(loginVO.getLevelCode()));

		request.getSession().removeAttribute("state");
		request.getSession().removeAttribute("district");
		request.getSession().removeAttribute("block");
		request.getSession().removeAttribute("village");
		request.getSession().removeAttribute("stateList");
		request.getSession().removeAttribute("districtList");
		request.getSession().removeAttribute("blockList");
		request.getSession().removeAttribute("villageList");
		request.getSession().removeAttribute("userList");

		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			modifyPage = Constants.MODIFY;
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			modifyPage = Constants.MODIFY;
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));
			modifyPage = "modifyPageState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));
			modifyPage = "modifyPageDistrict";
		}

		request.getSession().setAttribute("searchSuccess", Constants.EMPTY_STRING);
		return mapping.findForward(modifyPage);
	}

	/*
	 * this method is used to get User.
	 */
	public ActionForward searchUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String modifyPage = Constants.MODIFY;
		String entityCode = "";
		log.info("inside searchUser method");
		UserForm actionForm = (UserForm) form;

		UserVO userVO = new UserVO();
		UserService userService = new UserServiceImpl();

		LoginVO loginVO = (LoginVO) request.getSession()
				.getAttribute("loginVO");
		String level = loginVO.getLevelCode();

		if ((loginVO.getLevelCode().trim()
				.equalsIgnoreCase(Constants.MYSA_LEVEL) && actionForm
				.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL))
				|| (loginVO.getLevelCode().trim()
						.equalsIgnoreCase(Constants.STATE_LEVEL) && actionForm
						.getLevelCode().trim()
						.equalsIgnoreCase(Constants.STATE_LEVEL))
				|| (loginVO.getLevelCode().trim()
						.equalsIgnoreCase(Constants.DISTRICT_LEVEL) && actionForm
						.getLevelCode().trim()
						.equalsIgnoreCase(Constants.DISTRICT_LEVEL))
				|| (loginVO.getLevelCode().trim()
						.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL) && actionForm
						.getLevelCode().trim()
						.equalsIgnoreCase(Constants.MYSA_LEVEL))) {

			entityCode = loginVO.getEntityCode();

			if ((loginVO.getLevelCode().trim()
					.equalsIgnoreCase(Constants.MYSA_LEVEL) && actionForm
					.getLevelCode().trim()
					.equalsIgnoreCase(Constants.MYSA_LEVEL)))
				entityCode = level;
		}

		else {

			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.SAI_LEVEL)) {
				entityCode = Constants.SAI_ENTITY_CODE;
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.STATE_LEVEL)) {
				entityCode = actionForm.getStateCode();
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
				entityCode = actionForm.getDistrictCode();
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
				entityCode = actionForm.getBlockCode();
			}
			if (actionForm.getLevelCode().trim()
					.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
				entityCode = actionForm.getVillageCode();
			}
		}

		userVO = userService.showUser(entityCode, actionForm.getLoginId());

		if (userVO != null
				&& actionForm.getLevelCode().trim()
						.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			userVO.setStateCode(entityCode);

		}
		if (userVO != null
				&& actionForm.getLevelCode().trim()
						.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			userVO.setDistrictCode(entityCode);
		}
		if (userVO != null
				&& actionForm.getLevelCode().trim()
						.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
			userVO.setBlockCode(entityCode);
		}
		if (userVO != null
				&& actionForm.getLevelCode().trim()
						.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
			userVO.setVillageCode(entityCode);
		}

		if (userVO != null) {

			userVO.setHiddenLoginId(actionForm.getLoginId());
			userVO.setHiddenStateCode(actionForm.getStateCode());

			userVO.setHiddenDistrictCode(actionForm.getDistrictCode());
			userVO.setHiddenBlockCode(actionForm.getBlockCode());
			userVO.setHiddenVillageCode(actionForm.getVillageCode());
			userVO.setEntityCode(actionForm.getEntityCode());

			BeanUtils.copyProperties(actionForm, userVO);
			if (userVO.getActiveFlag() != null
					&& userVO.getActiveFlag().equals("Y")) {
				request.getSession().setAttribute("searchSuccess",
						Constants.SEARCH_SUCCESS_OPEN);
			} else if (userVO.getActiveFlag() != null
					&& userVO.getActiveFlag().equals("N")) {
				request.getSession().setAttribute("searchSuccess",
						Constants.SEARCH_SUCCESS_NOT_AUTHORISED);
			} else
				request.getSession().setAttribute("searchSuccess",
						Constants.SEARCH_SUCCESS);

			actionForm.setInformationDialog(false);
			actionForm.setInformationDialogHeader(null);
			actionForm.setInformationDialogText(null);
		} else {
			request.getSession().setAttribute("searchSuccess",
					Constants.SEARCH_FAIL);
		}

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			modifyPage = Constants.MODIFY;
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			modifyPage = Constants.MODIFY;
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			modifyPage = "modifyPageState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			modifyPage = "modifyPageDistrict";
		}
		return mapping.findForward(modifyPage);

	}

	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String modifyPage = Constants.VIEW;
		UserForm userForm = (UserForm) form;
		userForm.reset();
		log.info("inside showView method");
		UserService userService = new UserServiceImpl();

		LoginVO loginVO = (LoginVO) request.getSession()
				.getAttribute("loginVO");
		request.getSession().setAttribute("levelList",
				userService.getLevelList(loginVO.getLevelCode()));
		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();

		request.getSession().removeAttribute("state");
		request.getSession().removeAttribute("district");
		request.getSession().removeAttribute("block");
		request.getSession().removeAttribute("village");
		request.getSession().removeAttribute("stateList");
		request.getSession().removeAttribute("districtList");
		request.getSession().removeAttribute("blockList");
		request.getSession().removeAttribute("villageList");
		request.getSession().removeAttribute("userList");

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			request.getSession().setAttribute("stateList",
					userService.getStateList());
			modifyPage = Constants.VIEW;
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			request.getSession().setAttribute("stateList",
					userService.getStateList());
			modifyPage = Constants.VIEW;
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("districtList",
					userService.getDistrictListByStateCode(entityCode));
			modifyPage = "viewPageState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			userForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList",
					userService.getBlockListByDistrictCode(entityCode));
			modifyPage = "viewPageDistrict";
		}

		request.getSession().setAttribute("searchSuccess",
				Constants.EMPTY_STRING);

		return mapping.findForward(modifyPage);

	}

	/**
	 * @author 53601
	 * @author 54741
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward showUsrView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String modifyPage = Constants.VIEW;
		try {

			UserForm userForm = (UserForm) form;
			userForm.reset();
			log.info("inside showUsrView method");
			UserService userService = new UserServiceImpl();
			request.getSession().setAttribute("userReportList",
					userService.getuserReportList());
			// modifyPage = Constants.VIEW;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(modifyPage);

	}

	public ActionForward getUserListForReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String modifyPage = "";
		try {
			UserService userService = new UserServiceImpl();
			if (request.getParameter("form") != null) {
				if (request.getParameter("form").equals("1"))
					modifyPage = Constants.SHOW_PAGE;
				if (request.getParameter("form").equals("2"))
					modifyPage = Constants.MODIFY;
				if (request.getParameter("form").equals("4"))
					modifyPage = Constants.VIEW;
			}
			UserForm actionForm = (UserForm) form;
			String roleCode = request.getParameter("level");
			actionForm.setLevelCode(roleCode);
			request.getSession().setAttribute("levelList",
					userService.getLevelList(roleCode));
			if (roleCode.equals("2")) {
				roleCode = "ST";
				request.setAttribute("stateName",
						request.getParameter("stateName"));
			} else if (roleCode.equals("3")) {
				roleCode = "DT";
				request.setAttribute("stateName",
						request.getParameter("stateName"));
				request.setAttribute("districtName",
						request.getParameter("districtName"));
			} else if (roleCode.equals("4")) {
				roleCode = "BP";
				request.setAttribute("stateName",
						request.getParameter("stateName"));
				request.setAttribute("districtName",
						request.getParameter("districtName"));
				request.setAttribute("blockName",
						request.getParameter("blockName"));
			} else
				roleCode = "AD";

			UserVO userVO = new UserVO();
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String entityCode = "";

			// String stateShortName=null,districtShortName=null;
			if (request.getParameter("level").equalsIgnoreCase(
					Constants.MYSA_LEVEL)) {
				entityCode = request.getParameter("level");
				modifyPage = "viewUserList";
			} else {
				entityCode = request.getParameter("entityCode");
				modifyPage = "viewUserList";
			}
			request.setAttribute("entityCode", entityCode);
			// GET USER LIST
			List userList = null;
			// Map<String, Object> userMap = (new
			// UserDAOImpl()).getUserList(loginVO,entityCode,request.getParameter("level"));
			Map<String, Object> userMap = (new UserDAOImpl()).getUserListForReport(loginVO, entityCode, request.getParameter("level"));
			// userList=(new UserDAOImpl()).viewUser(entityCode);
			// userList=(new
			// ManageRoleDAOImpl()).getUserList(entityCode,request);

			if (userMap != null && userMap.size() != 0) {
				userList = new ArrayList();
				for (Iterator<String> it = userMap.keySet().iterator(); it.hasNext();) {
					PropertyModel propertyModel = new PropertyModel();
					String key = (String) it.next();
					String value = (String) userMap.get(key);
					propertyModel.setPropertyCode(key);
					propertyModel.setPropertyValue(key);
					propertyModel.setGenUser(value);
					userList.add(propertyModel);
				}
			}

			// SEARCH USER
			if (request.getParameter("loginId") != null) {
				userVO = userService.showUser(entityCode,request.getParameter("loginId"));
				if (userVO != null && actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL)) {
					userVO.setStateCode(entityCode);
				}
				if (userVO != null && actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
					userVO.setDistrictCode(entityCode);
				}
				if (userVO != null && actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
					userVO.setBlockCode(entityCode);
				}
				if (userVO != null) {
					BeanUtils.copyProperties(actionForm, userVO);
					modifyPage = "showUserList";
				} else {
					// actionForm.reset();
					request.getSession().setAttribute("searchSuccess", Constants.SEARCH_FAIL);
				}
			}
			if (userList != null && !userList.isEmpty()) {
				request.setAttribute("userList", userList);
			} else {
				if (request.getParameter("param") != null && request.getParameter("param").equals("1")) {
					request.setAttribute("userNotFound", "Login Id does not exist ");
				}
				request.setAttribute("userList", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(modifyPage);

	}

	public ActionForward getStateDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			message.clear();

			HttpSession session = request.getSession();

			LocationVO locationVO = ((LocationVO) session
					.getAttribute(SGSYConstants.SGSY_LOCATIONVO));

			UserDAOImpl userdao = new UserDAOImpl();

			List stateList = userdao.getStateDetails(locationVO);

			if (stateList != null && stateList.size() > 0) {

				request.setAttribute(Constants.STATE_LIST, stateList);
				request.setAttribute("state", stateList);

			} else {

				message.add(SGSYConstants.MSG, new ActionMessage(
						"error.details", "Details"));
				saveMessages(request, message);

			}

			// request.setAttribute(Constants.ALL_YEAR, yearList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showStateList");
	}

	public ActionForward getDistrictDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		try {

			message.clear();

			String stateCode = request.getParameter("stateCode");

			// String stateShortName = request.getParameter("stateShortName");

			UserDAOImpl userdao = new UserDAOImpl();
			List stateList = userdao.getStateDetails(null);

			if (stateList != null && stateList.size() > 0) {

				request.setAttribute(Constants.STATE_LIST, stateList);
				request.setAttribute("state", stateList);

			}
			List districtList = userdao.getDistrictDetails(stateCode);

			if (districtList != null && districtList.size() > 0) {

				request.setAttribute(Constants.DISTRICT_LIST, districtList);

				// reportForm.setStateName(request.getParameter("stateName"));

			} else {

				message.add(SGSYConstants.MSG, new ActionMessage(
						"error.details", "Details"));
				saveMessages(request, message);
			}

			forward = mapping.findForward("showStateForUserList");
			if (request.getParameter("flag") != null)
				forward = mapping.findForward("showDistrictForUserList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;

	}

	public ActionForward getBlockDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			message.clear();

			String stateCode = request.getParameter("stateCode");

			UserDAOImpl userdao = new UserDAOImpl();

			List stateList = userdao.getStateDetails(null);

			if (stateList != null && stateList.size() > 0) {

				request.setAttribute(Constants.STATE_LIST, stateList);
				request.setAttribute("state", stateList);

			}
			List districtList = userdao.getDistrictDetails(stateCode);

			if (districtList != null && districtList.size() > 0) {

				request.setAttribute(Constants.DISTRICT_LIST, districtList);

				// reportForm.setStateName(request.getParameter("stateName"));

			}

			String districtCode = request.getParameter("districtCode");

			List blockList = userdao.getBlockDetails(stateCode, districtCode);

			if (blockList != null && blockList.size() > 0) {

				request.setAttribute(Constants.BLOCKLIST, blockList);

			} else {

				message.add(SGSYConstants.MSG, new ActionMessage("error.details", "Details"));
				saveMessages(request, message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showDistrictForUserList");
	}

	public ActionForward getStateDetailsForUserReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		message.clear();
		ActionForward forward = new ActionForward();

		try {
			HttpSession session = request.getSession();
			UserForm actionForm = (UserForm) form;
			actionForm.setStateCode("");
			LocationVO locationVO = ((LocationVO) session
					.getAttribute(SGSYConstants.SGSY_LOCATIONVO));

			UserDAOImpl userdao = new UserDAOImpl();

			List stateList = userdao.getStateDetails(locationVO);

			if (stateList != null && stateList.size() > 0) {
				request.setAttribute(Constants.STATE_LIST, stateList);
				request.setAttribute("state", stateList);

			} else {

				message.add(SGSYConstants.MSG, new ActionMessage(
						"error.details", "Details"));
				saveMessages(request, message);

			}

			forward = mapping.findForward("showStateForUserList");
			if (request.getParameter("flag") != null)
				forward = mapping.findForward("showDistrictForUserList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

	public ActionForward getDataList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserService userService = new UserServiceImpl();
		request.getSession().removeAttribute("userModified");
		String modifyPage = "";
		try {
			if (request.getParameter("form") != null) {
				if (request.getParameter("form").equals("1"))
					modifyPage = Constants.SHOW_PAGE;

				if (request.getParameter("form").equals("2"))
					modifyPage = Constants.MODIFY;

				if (request.getParameter("form").equals("4"))
					modifyPage = Constants.VIEW;

			}
			UserForm actionForm = (UserForm) form;

			actionForm.setInformationDialog(false);
			actionForm.setInformationDialogHeader(null);
			actionForm.setInformationDialogText(null);

			String type = request.getParameter("type");
			UserDAOImpl dao = new UserDAOImpl();
			List listE = new ArrayList();

			PropertyModel prop = new PropertyModel();
			listE.add(prop);
			String roleCode = actionForm.getLevelCode();
			LoginVO dto = (LoginVO) request.getSession().getAttribute("loginVO");

			if (roleCode.equals("2"))
				roleCode = "ST";
			else if (roleCode.equals("3"))
				roleCode = "DT";
			else if (roleCode.equals("4"))
				roleCode = "BP";
			else
				roleCode = "AD";

			String stateCode = actionForm.getStateCode();
			String districtCode = actionForm.getDistrictCode();
			String blockCode = actionForm.getBlockCode();

			if (((SGSYConstants.ENTITYTYPE_MYAS).equalsIgnoreCase(dto.getEntityType()))
					|| ((dto.getEntityType()).equalsIgnoreCase("ADM"))) {
				if (roleCode.equals(SGSYConstants.ENTITYTYPE_STATE)) {
					// if(type.equals("N")) {
					List list = dao.getEntityList("ST",SGSYConstants.ENTITYTYPE_STATE, dto);
					request.getSession().setAttribute("stateList", list);
					request.getSession().setAttribute("state", "state");
					request.getSession().removeAttribute("district");
					request.getSession().removeAttribute("block");
					request.getSession().removeAttribute("village");
					request.getSession().removeAttribute("districtList");
					request.getSession().removeAttribute("blockList");
					request.getSession().removeAttribute("villageList");
					request.setAttribute("userList", null);
					// actionForm.setStateCode(null);

				} else if (roleCode.equals("DT")) {

					request.getSession().removeAttribute("stateList");
					request.getSession().removeAttribute("districtList");

					List stateList = dao.getEntityList("ST", SGSYConstants.ENTITYTYPE_STATE, dto);
					request.getSession().setAttribute("stateList", stateList);

					dto.setStateCode(actionForm.getStateCode());
					if (actionForm.getStateCode() != null) {
						List list = dao.getEntityList(roleCode, type, dto);
						request.getSession().setAttribute("districtList", list);
					} else {
						// actionForm.setStateCode(null);
					}
					request.getSession().removeAttribute("state");
					request.getSession().setAttribute("district", "district");
					request.getSession().removeAttribute("block");
					request.getSession().removeAttribute("village");
					request.getSession().removeAttribute("blockList");
					request.getSession().removeAttribute("villageList");
				} else if (roleCode.equals(SGSYConstants.RA_AGENCY_LEVEL_CODE)) {
					request.getSession().removeAttribute("state");
					request.getSession().removeAttribute("district");
					request.getSession().removeAttribute("block");
					request.getSession().removeAttribute("village");
					request.getSession().setAttribute("block", "block");
					request.getSession().removeAttribute("stateList");
					request.getSession().removeAttribute("districtList");
					request.getSession().removeAttribute("blockList");
					
					List list = dao.getEntityList("ST", SGSYConstants.ENTITYTYPE_STATE, dto);
					request.getSession().setAttribute("stateList", list);

					if (actionForm.getStateCode() != null) {
						dto.setStateCode(actionForm.getStateCode());
						listE = dao.getEntityList("DT", SGSYConstants.RA_AGENCY_LEVEL_CODE, dto);
						request.getSession().setAttribute("districtList", listE);
						// actionForm.setBlockCode(null);
					}

					if (actionForm.getDistrictCode() != null) {
						dto.setDistrictCode(actionForm.getDistrictCode());
						listE = dao.getEntityList(roleCode, SGSYConstants.RA_AGENCY_LEVEL_CODE, dto);
						request.getSession().setAttribute("blockList", listE);
					}
					request.getSession().removeAttribute("villageList");
				}
				else if (roleCode.equals("0") || roleCode.equals("MS")) {
					request.getSession().removeAttribute("state");
					request.getSession().removeAttribute("district");
					request.getSession().removeAttribute("block");
					request.getSession().removeAttribute("village");
				} else if (roleCode.equals(SGSYConstants.ENTITYTYPE_MYAS)) {
					actionForm.setStateCode(null);
					actionForm.setDistrictCode(null);
					actionForm.setBlockCode(null);
					request.getSession().removeAttribute("state");
					request.getSession().removeAttribute("district");
					request.getSession().removeAttribute("block");
					request.getSession().removeAttribute("village");
					actionForm.setEntityCode(dto.getEntityCode());
					request.setAttribute("myas", "myas");
				}
				// return mapping.findForward(modifyPage);
			}

			UserVO userVO = new UserVO();
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String level = loginVO.getLevelCode(), entityCode = "";
			String stateShortName = null, districtShortName = null;
			if ((loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL) 
					&& actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL))
					|| (loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL) && actionForm.getLevelCode().trim()
							.equalsIgnoreCase(Constants.STATE_LEVEL))
					|| (loginVO.getLevelCode().trim()
							.equalsIgnoreCase(Constants.DISTRICT_LEVEL) && actionForm
							.getLevelCode().trim()
							.equalsIgnoreCase(Constants.DISTRICT_LEVEL))
					|| (loginVO.getLevelCode().trim()
							.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL) && actionForm
							.getLevelCode().trim()
							.equalsIgnoreCase(Constants.MYSA_LEVEL))) {

				//entityCode = loginVO.getEntityCode();
				entityCode = "0";

				if ((loginVO.getLevelCode().trim()
						.equalsIgnoreCase(Constants.MYSA_LEVEL) && actionForm
						.getLevelCode().trim()
						.equalsIgnoreCase(Constants.MYSA_LEVEL)))
					entityCode = level;
			}

			else {

				if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.SAI_LEVEL)) {
					entityCode = Constants.SAI_ENTITY_CODE;
				}
				if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL)) {
					entityCode = stateCode;
					// stateShortName =
					// com.infotech.sp.manageTraining.TrainingDAO.getStateShortName(stateCode);
					request.setAttribute("stateShortName", stateShortName);
				}
				if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
					entityCode = districtCode;

					// stateShortName =
					// com.infotech.sp.manageTraining.TrainingDAO.getStateShortName(stateCode);
					districtShortName = CommonUtils.getDistrictShortName(districtCode);
					request.setAttribute("stateShortName", stateShortName);
					request.setAttribute("districtShortName", districtShortName);
				}
				if (actionForm.getLevelCode().trim()
						.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
					entityCode = actionForm.getBlockCode();
				}
				if (actionForm.getLevelCode().trim()
						.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
					entityCode = blockCode;
				}
			}

			// GET USER LIST

			List userList = null;

			Map<String, Object> userMap = (new UserDAOImpl()).getUserList(
					loginVO, entityCode, actionForm.getLevelCode());

			// userList=(new UserDAOImpl()).viewUser(entityCode);

			// userList=(new
			// ManageRoleDAOImpl()).getUserList(entityCode,request);

			if (userMap != null && userMap.size() != 0) {

				userList = new ArrayList();

				for (Iterator<String> it = userMap.keySet().iterator(); it
						.hasNext();) {

					PropertyModel propertyModel = new PropertyModel();

					String key = (String) it.next();
					String value = (String) userMap.get(key);

					propertyModel.setPropertyCode(key);
					propertyModel.setPropertyValue(value);
					userList.add(propertyModel);

				}

			}

			// SEARCH USER
			if (actionForm.getLoginId() != null) {

				userVO = userService.showUser(entityCode,
						actionForm.getLoginId());

				if (userVO != null
						&& actionForm.getLevelCode().trim()
								.equalsIgnoreCase(Constants.STATE_LEVEL)) {
					userVO.setStateCode(entityCode);

				}
				if (userVO != null
						&& actionForm.getLevelCode().trim()
								.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
					userVO.setDistrictCode(entityCode);
				}
				if (userVO != null
						&& actionForm.getLevelCode().trim()
								.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
					userVO.setBlockCode(entityCode);
				}
				if (userVO != null
						&& actionForm.getLevelCode().trim()
								.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
					userVO.setVillageCode(entityCode);
				}

				if (userVO != null) {

					BeanUtils.copyProperties(actionForm, userVO);

					actionForm.setStateCode(stateCode);
					actionForm.setDistrictCode(districtCode);
					actionForm.setBlockCode(blockCode);

					if (userVO.getActiveFlag() != null
							&& userVO.getActiveFlag().equals("Y")) {
						request.getSession().setAttribute("searchSuccess",
								Constants.SEARCH_SUCCESS_OPEN);
					} else if (userVO.getActiveFlag() != null
							&& userVO.getActiveFlag().equals("N")) {
						request.getSession().setAttribute("searchSuccess",
								Constants.SEARCH_SUCCESS_NOT_AUTHORISED);
					} else
						request.getSession().setAttribute("searchSuccess",
								Constants.SEARCH_SUCCESS);

					actionForm.setInformationDialog(false);
					actionForm.setInformationDialogHeader(null);
					actionForm.setInformationDialogText(null);
				} else {
					// actionForm.reset();
					request.getSession().setAttribute("searchSuccess",
							Constants.SEARCH_FAIL);
				}

			}

			if (userList != null && !userList.isEmpty()) {

				request.setAttribute("userList", userList);
			} else {

				if (request.getParameter("param") != null
						&& request.getParameter("param").equals("1")) {

					request.setAttribute("userNotFound",
							"Login Id does not exist ");

				}
				request.setAttribute("userList", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
		return mapping.findForward(modifyPage);

	}

	public ActionForward getDistrictByStateCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserHelper userHelper = new UserHelper();
		userHelper.getDistrictByStateCode(request, response);
		return null;
	}

	public ActionForward getBlockByDistrictCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserHelper userHelper = new UserHelper();
		userHelper.getBlockByDistrictCode(request, response);
		return null;
	}

	public ActionForward getVillageByBlockCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserHelper userHelper = new UserHelper();
		userHelper.getVillageByBlockCode(request, response);
		return null;
	}

	public ActionForward getBlockPanchayatListByDistrictCode(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserHelper userHelper = new UserHelper();
		userHelper.getBlockPanchayatListByDistrictCode(request, response);
		return null;
	}

	public ActionForward getVillagePanchayatListByBlockCode(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserHelper userHelper = new UserHelper();
		userHelper.getVillagePanchayatListByBlockCode(request, response);
		return null;
	}
}