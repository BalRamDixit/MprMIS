package com.infotech.sgsy.manageRole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.userManagement.UserDAOImpl;
import com.infotech.sgsy.userManagement.UserForm;
import com.infotech.sgsy.userManagement.UserHelper;
import com.infotech.sgsy.userManagement.UserService;
import com.infotech.sgsy.userManagement.UserServiceImpl;
import com.infotech.sgsy.userManagement.UserVO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.PropertyModel;
import com.infotech.sgsy.util.SGSYConstants;

/**
 * 
 * @author NIC
 *
 */
public class ManageRoleAction extends MasterAction {

	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward assignRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String showPage = "";
		log.info("inside assignRole method");
		ManageRoleForm manageRoleForm = (ManageRoleForm) form;
		manageRoleForm.reset();
		UserService userService = new UserServiceImpl();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();

		request.getSession().setAttribute("levelList", userService.getLevelList(level));

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			showPage = Constants.SHOW_PAGE;
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			showPage = Constants.SHOW_PAGE;
		}
		/*if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			manageRoleForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));
			showPage = Constants.SHOW_PAGE_STATE;
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			manageRoleForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));
			showPage = Constants.SHOW_PAGE_DISTRICT;
		}*/
		return mapping.findForward(showPage);

	}

	public ActionForward getDataList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserService userService = new UserServiceImpl();

		String modifyPage = "";

		if (request.getParameter("form") != null) {

			if (request.getParameter("form").equals("1"))
				modifyPage = Constants.SHOW_PAGE;

			if (request.getParameter("form").equals("2"))
				modifyPage = Constants.MODIFY;

			if (request.getParameter("form").equals("4"))
				modifyPage = Constants.VIEW;

		}
		
		ManageRoleForm actionForm = (ManageRoleForm) form;

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

		List roleList = null;

		Map<String, Object> roleMap = (new ManageRoleDAOImpl()).getRoleList(actionForm.getLevelCode());
		if (roleMap != null && roleMap.size() != 0) {
			roleList = new ArrayList();

			for (Iterator<String> it = roleMap.keySet().iterator(); it.hasNext();) {
				PropertyModel propertyModel = new PropertyModel();
				String key = (String) it.next();
				String value = (String) roleMap.get(key);
				propertyModel.setPropertyCode(key);
				propertyModel.setPropertyValue(value);
				roleList.add(propertyModel);
			}
		}
		request.setAttribute("roleList", roleList);
		
		if(roleCode.equals("2"))
			roleCode="ST";
		else if(roleCode.equals("3"))
			roleCode="DT";
		else if(roleCode.equals("4"))
			roleCode="BP";
		else
			roleCode="AD";

		String stateCode = actionForm.getStateCode();
		String districtCode = actionForm.getDistrictCode();
		String blockCode = actionForm.getBlockCode();

		if (((SGSYConstants.ENTITYTYPE_MYAS).equalsIgnoreCase(dto.getEntityType())) || ((dto.getEntityType()).equalsIgnoreCase("ADM"))) {

			if (roleCode.equals("0") || roleCode.equals("MS")) {
				request.getSession().removeAttribute("state");
				request.getSession().removeAttribute("district");
				request.getSession().removeAttribute("block");
				request.getSession().removeAttribute("village");
			}
			else if(roleCode.equals(SGSYConstants.ENTITYTYPE_STATE) ) {
			         
					//	if(type.equals("N")) {	
				        	 
							List list=dao.getEntityList("ST", SGSYConstants.ENTITYTYPE_STATE, dto);
							
							request.getSession().setAttribute("stateList",list);
							 
							request.getSession().setAttribute("state","state");
							request.getSession().removeAttribute("district");
							request.getSession().removeAttribute("block");
							request.getSession().removeAttribute("village");
						 	request.getSession().removeAttribute("districtList");
							request.getSession().removeAttribute("blockList");
							request.getSession().removeAttribute("villageList");
							request.setAttribute("userList",null);	
							//actionForm.setStateCode(null);
							
							
						}
			else if (roleCode.equals(SGSYConstants.ENTITYTYPE_MYAS)) {

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
String  level = loginVO.getLevelCode(),entityCode="";
		
		String  stateShortName=null,districtShortName=null;
		 
		if ((loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL) && 
				actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL))
				|| (loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL) && 
				actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL))
				|| (loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL) &&
				actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL))
				|| (loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL) && 
				actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL))) {
			
			entityCode = loginVO.getEntityCode();		
			
			if ((loginVO.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL) && 
					actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.MYSA_LEVEL)))
				entityCode =level;
		}

		else {
			 
			if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.SAI_LEVEL)) {
				entityCode = Constants.SAI_ENTITY_CODE;						
			}
			if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.STATE_LEVEL)) {
				
				 entityCode = stateCode;		
				 
				// stateShortName = com.infotech.sp.manageTraining.TrainingDAO.getStateShortName(stateCode); 
				 
				 request.setAttribute("stateShortName",stateShortName);
			}
			if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
				entityCode = districtCode;
				
				 
			    //stateShortName = com.infotech.sp.manageTraining.TrainingDAO.getStateShortName(stateCode); 
			    districtShortName = CommonUtils.getDistrictShortName(districtCode); 
			    
			    request.setAttribute("stateShortName",stateShortName);
			    request.setAttribute("districtShortName",districtShortName);	
			}
			if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
				entityCode = actionForm.getBlockCode();
			}
			if (actionForm.getLevelCode().trim().equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
				entityCode = blockCode;
			}
		}
		

		// GET USER LIST
		List userList = null;
		Map<String, Object> userMap = (new UserDAOImpl()).getUserList(loginVO, entityCode, actionForm.getLevelCode());		

		if (userMap != null && userMap.size() != 0) {
			userList = new ArrayList();
			for (Iterator<String> it = userMap.keySet().iterator(); it.hasNext();) {
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
			userVO = userService.showUser(entityCode, actionForm.getLoginId());
			if (userVO != null) {
				BeanUtils.copyProperties(actionForm, userVO);
				actionForm.setStateCode(stateCode);
				actionForm.setDistrictCode(districtCode);
				actionForm.setBlockCode(blockCode);

				if (userVO.getActiveFlag() != null && userVO.getActiveFlag().equals("Y")) {
					request.getSession().setAttribute("searchSuccess", Constants.SEARCH_SUCCESS_OPEN);
				} else if (userVO.getActiveFlag() != null && userVO.getActiveFlag().equals("N")) {
					request.getSession().setAttribute("searchSuccess", Constants.SEARCH_SUCCESS_NOT_AUTHORISED);
				} else
					request.getSession().setAttribute("searchSuccess", Constants.SEARCH_SUCCESS);

				actionForm.setInformationDialog(false);
				actionForm.setInformationDialogHeader(null);
				actionForm.setInformationDialogText(null);
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
		return mapping.findForward(modifyPage);
	}

	
	
	
	
	public ActionForward getDistrictByStateCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		UserService userService = new UserServiceImpl();
		String stateCode = request.getParameter("state");
		if (stateCode.length() == 1) {
			stateCode = "0" + stateCode;
		}
		System.out.print("State Code by Murthy :" + stateCode);
		String districtCodeId = request.getParameter("districtCodeId");
		Map<String, Object> districtMap = null;

		String outputText = "";

		try {
			districtMap = userService.getDistrictList(stateCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (districtMap != null && districtMap.size() != 0) {

			for (Iterator<String> it = districtMap.keySet().iterator(); it.hasNext();) {

				String key = (String) it.next();
				String value = (String) districtMap.get(key);

				outputText += "O= document.createElement('option');";
				outputText += "O.text  = replaceSingleQuote(\"" + value.replaceAll("\"", "\\\\\"") + "\");";
				outputText += "O.value = '" + key + "';";
				outputText += "document.getElementsByName('" + districtCodeId + "')[0].options.add(O); ";

			}

			try {

				response.getWriter().write(outputText);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {

			try {
				response.getWriter().write(outputText);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}

	
	
	public ActionForward getBlockByDistrictCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		UserService userService = new UserServiceImpl();
		Map<String, Object> blockMap = null;

		String districtCode = request.getParameter("district");
		String blockCodeId = request.getParameter("blockCodeId"), outputText = "";
		try {
			blockMap = userService.getBlockList(districtCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (blockMap != null && blockMap.size() != 0) {

			for (Iterator<String> it = blockMap.keySet().iterator(); it.hasNext();) {

				String key = (String) it.next();
				String value = (String) blockMap.get(key);

				outputText += "O= document.createElement('option');";
				outputText += "O.text  = replaceSingleQuote(\"" + value.replaceAll("\"", "\\\\\"") + "\");";
				outputText += "O.value = '" + key + "';";
				outputText += "document.getElementsByName('" + blockCodeId + "')[0].options.add(O); ";

			}

			try {
				response.getWriter().write(outputText);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {

			try {
				response.getWriter().write(outputText);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}

	
	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleCode = request.getParameter("code");
		String loginId = request.getParameter("loginId");
		request.setAttribute("userId", loginId);

		ManageRoleService mRoleService = new ManageRoleServiceImpl();
		int count = mRoleService.delete(roleCode, loginId);
		return getRoleByLoginId(mapping, form, request, response);
	}

	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ManageRoleForm actionForm = (ManageRoleForm) form;
		ManageRoleVO manageRoleVO = new ManageRoleVO();
		BeanUtils.copyProperties(manageRoleVO, actionForm);
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		manageRoleVO.setCreatedBy(loginVO.getUserid());
		int updateRecord = 0;
		String selectedEntity = "";

		String roleLevel = request.getParameter("levelCodeA");
		// String roleCode = request.getParameter("roleCode");
		// String loginId = request.getParameter("loginId");
		String selectedValues[] = null;
		Map<String, Object> selectedMap = null;

		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

		String level = loginVO.getLevelCode();

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL) || level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			List<UserIdentityVO> selectedStateValues = new ArrayList<UserIdentityVO>();

			if (roleLevel.equalsIgnoreCase(Constants.STATE_LEVEL)) {

				selectedStateValues = (List<UserIdentityVO>) request.getSession().getAttribute("selectedStatesList");
				selectedValues = request.getParameterValues("selectedStateCode");

				selectedEntity = resourceBundle.getString("label.manageRole.selectedState").trim();

			}
			if (roleLevel.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
				selectedMap = (Map<String, Object>) request.getSession().getAttribute("selectedDMap");
				selectedValues = request.getParameterValues("selectedDistrictCode");

				selectedEntity = resourceBundle.getString("label.manageRole.selectedDistrict").trim();

			}
			if (roleLevel.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
				selectedMap = (Map<String, Object>) request.getSession().getAttribute("selectedBMap");
				selectedValues = request.getParameterValues("selectedBlockCode");

				selectedEntity = resourceBundle.getString("label.user.selectedBlocks").trim();

			}
			if (roleLevel.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
				selectedMap = (Map<String, Object>) request.getSession().getAttribute("selectedVMap");
				selectedValues = request.getParameterValues("selectedVillageCode");

				selectedEntity = resourceBundle.getString("lable.user.selectedVillages").trim();
			}

			ManageRoleService mRoleService = new ManageRoleServiceImpl();
			updateRecord = mRoleService.modify(selectedValues, selectedMap, selectedStateValues, manageRoleVO, request);
			request.getSession().removeAttribute("selectedStatesList");
			request.getSession().removeAttribute("selectedDMap");
			request.getSession().removeAttribute("selectedBMap");
			request.getSession().removeAttribute("selectedVMap");

		}

		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {

			List<UserIdentityVO> districtselectedList = new ArrayList<UserIdentityVO>();

			if (roleLevel.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
				districtselectedList = (List<UserIdentityVO>) request.getSession().getAttribute("districtselectedList");
				selectedValues = request.getParameterValues("selectedDistrictCode");

				selectedEntity = resourceBundle.getString("label.manageRole.selectedDistrict").trim();

			}

			if (roleLevel.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
				selectedMap = (Map<String, Object>) request.getSession().getAttribute("selectedBMap");
				selectedValues = request.getParameterValues("selectedBlockCode");
				selectedEntity = resourceBundle.getString("lable.user.selectedBlocks").trim();

			}
			if (roleLevel.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
				selectedMap = (Map<String, Object>) request.getSession().getAttribute("selectedVMap");
				selectedValues = request.getParameterValues("selectedVillageCode");
				selectedEntity = resourceBundle.getString("lable.user.selectedVillages").trim();
			}

			ManageRoleService mRoleService = new ManageRoleServiceImpl();
			updateRecord = mRoleService.modify(selectedValues, selectedMap, districtselectedList, manageRoleVO, request);
			request.getSession().removeAttribute("districtselectedList");
			request.getSession().removeAttribute("selectedBMap");
			request.getSession().removeAttribute("selectedVMap");

		}

		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {

			List<UserIdentityVO> blockselectedList = new ArrayList<UserIdentityVO>();

			if (roleLevel.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
				blockselectedList = (List<UserIdentityVO>) request.getSession().getAttribute("blockselectedList");
				selectedValues = request.getParameterValues("selectedBlockCode");
				selectedEntity = resourceBundle.getString("lable.user.selectedBlocks").trim();

			}

			if (roleLevel.equalsIgnoreCase(Constants.VILLAGE_LEVEL)) {
				selectedMap = (Map<String, Object>) request.getSession().getAttribute("selectedVMap");
				selectedValues = request.getParameterValues("selectedVillageCode");
				selectedEntity = resourceBundle.getString("lable.user.selectedVillages").trim();
			}

			ManageRoleService mRoleService = new ManageRoleServiceImpl();
			updateRecord = mRoleService.modify(selectedValues, selectedMap, blockselectedList, manageRoleVO, request);
			request.getSession().removeAttribute("blockselectedList");
			request.getSession().removeAttribute("selectedVMap");

		}

		String updateAssignRole = resourceBundle.getString("updateAssignRole").trim();
		if (updateRecord != 0) {
			request.setAttribute("updateAssignRole", updateAssignRole);
		} else {
			String NoRecd = resourceBundle.getString("NoRecd");
			String availableforModification = resourceBundle.getString("availableforModification");
			request.setAttribute("noUpdateAssignRole", NoRecd + " " + selectedEntity + " " + availableforModification);
		}
		return mapping.findForward("success");
	}

	/**
	 * 
	 */
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("inside save method");
		String savePage = Constants.SAVE;

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		String level = loginVO.getLevelCode();
		ManageRoleForm actionForm = (ManageRoleForm) form;

		ManageRoleVO manageRoleVO = new ManageRoleVO();
		BeanUtils.copyProperties(manageRoleVO, actionForm);

		ManageRoleService mRoleService = new ManageRoleServiceImpl();

		String entityName = mRoleService.checkMultipleRole(manageRoleVO, request);

		manageRoleVO.setCreatedBy(loginVO.getUserid());

		if (entityName != null && entityName != "") {
			String loginId = manageRoleVO.getLoginId();
			actionForm.reset();
			actionForm.setEntityCode(manageRoleVO.getEntityCode());
			ActionErrors errors = new ActionErrors();
			errors.add("loginId", new ActionError("errors.lookup.multipleRole", loginId, entityName));
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
			if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
				savePage = Constants.SAVE;
			}
			if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
				savePage = "saveState";
			}
			if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
				savePage = "saveDistrict";
			}
		} else {
			mRoleService.assignRoleToUser(manageRoleVO, request);
			actionForm.reset();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			String assignRole = resourceBundle.getString("assignRole").trim();
			request.setAttribute("assignRole", assignRole);
			savePage = "success";
		}
		return mapping.findForward(savePage);

	}

	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String showPage = "showUpdateAssignRole";
		ManageRoleForm actionForm = (ManageRoleForm) form;
		ManageRoleVO mRoleVO = new ManageRoleVO();
		BeanUtils.copyProperties(mRoleVO, actionForm);

		String roleCode = request.getParameter("roleCode");

		ManageRoleService mRoleService = new ManageRoleServiceImpl();
		ManageRoleVO manageRoleVO = mRoleService.getNameByCode(mRoleVO);
		actionForm.setStateName(manageRoleVO.getStateName());
		actionForm.setDistrictName(manageRoleVO.getDistrictName());
		actionForm.setBlockName(manageRoleVO.getBlockName());
		actionForm.setVillageName(manageRoleVO.getVillageName());
		actionForm.setLevelCodeName(manageRoleVO.getLevelCodeName());
		actionForm.setRoleName(manageRoleVO.getRoleName());
		actionForm.setLevelCodeAName(manageRoleVO.getLevelCodeAName());
		actionForm.setRoleCode(roleCode);

		String loginId = request.getParameter("loginId");
		String levelCode = request.getParameter("levelCode");
		String levelCodeA = request.getParameter("levelCodeA");

		UserService userService = new UserServiceImpl();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		String level = loginVO.getLevelCode();
		String entityCode = loginVO.getEntityCode();

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL) || level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			if ((levelCode.equalsIgnoreCase(Constants.MYSA_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.VILLAGE_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.MYSA_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.BLOCK_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.MYSA_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.DISTRICT_LEVEL))) {

				request.getSession().setAttribute("statesList", userService.getStateList());
				request.getSession().setAttribute("statessList", new ArrayList<UserIdentityVO>());
				request.getSession().setAttribute("selectedStatesList", new ArrayList<UserIdentityVO>());

			}

			if ((levelCode.equalsIgnoreCase(Constants.MYSA_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.STATE_LEVEL))) {

				request.getSession().setAttribute("statesList", new ArrayList<UserIdentityVO>());
				request.getSession().setAttribute("statessList", mRoleService.getStatesList(levelCode, loginId, roleCode));
				request.getSession().setAttribute("selectedStatesList", mRoleService.getSelectedStateList(levelCode, loginId, roleCode));
			}
			if ((levelCode.equalsIgnoreCase(Constants.STATE_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.VILLAGE_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.STATE_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.BLOCK_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.STATE_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.DISTRICT_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.DISTRICT_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.VILLAGE_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.BLOCK_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.VILLAGE_LEVEL))
					|| (levelCode.equalsIgnoreCase(Constants.DISTRICT_LEVEL) && levelCodeA.equalsIgnoreCase(Constants.BLOCK_LEVEL))) {

				request.getSession().setAttribute("statesList", new ArrayList<UserIdentityVO>());
				request.getSession().setAttribute("statessList", new ArrayList<UserIdentityVO>());
				request.getSession().setAttribute("selectedStatesList", new ArrayList<UserIdentityVO>());
			}
			showPage = "showUpdateAssignRole";
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			request.getSession().setAttribute("districtsList", userService.getDistrictListByStateCode(entityCode));
			request.getSession().setAttribute("districtssList", mRoleService.getsDistrictList(entityCode, loginId, roleCode));
			request.getSession().setAttribute("districtselectedList", mRoleService.getSelectedDistrictList(entityCode, loginId, roleCode));
			showPage = "showUpdateAssignRoleState";
		}

		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			request.getSession().setAttribute("blocksList", userService.getBlockListByDistrictCode(entityCode));
			request.getSession().setAttribute("blockssList", mRoleService.getsBlockList(entityCode, loginId, roleCode));
			request.getSession().setAttribute("blockselectedList", mRoleService.getSelectedBlockList(entityCode, loginId, roleCode));
			showPage = "showUpdateAssignRoleDistrict";

		}
		return mapping.findForward(showPage);
	}

	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionForward searchAssignRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String showPage = "searchAssignRole";
		log.info("inside assignRole method");

		ManageRoleForm manageRoleForm = (ManageRoleForm) form;
		manageRoleForm.reset();

		UserService userService = new UserServiceImpl();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

		String entityCode = loginVO.getEntityCode();
		String level = loginVO.getLevelCode();

		request.getSession().setAttribute("levelList", userService.getLevelList(level));

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			showPage = "searchAssignRole";
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			request.getSession().setAttribute("stateList", userService.getStateList());
			showPage = "searchAssignRole";
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			manageRoleForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("districtList", userService.getDistrictListByStateCode(entityCode));
			showPage = "searchAssignRoleState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			manageRoleForm.setEntityCode(loginVO.getEntityCode());
			request.getSession().setAttribute("blockList", userService.getBlockListByDistrictCode(entityCode));
			showPage = "searchAssignRoleDistrict";
		}
		request.getSession().setAttribute("roleList", null);
		request.getSession().setAttribute("searchSuccess", Constants.EMPTY_STRING);
		return mapping.findForward(showPage);

	}

	public ActionForward getRoleByLoginId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String showPage = "searchAssignRole";

		String loginId = request.getParameter("userId");
		if (loginId == null || loginId == "") {
			loginId = (String) request.getAttribute("userId");
		}

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		String level = loginVO.getLevelCode();

		ManageRoleService userService = new ManageRoleServiceImpl();
		List<ManageRoleVO> roleList = userService.getRoleByLoginId(loginId);
		ManageRoleForm manageRoleForm = (ManageRoleForm) form;

		manageRoleForm.setHiddenStateCode(manageRoleForm.getStateCode());
		manageRoleForm.setHiddenDistrictCode(manageRoleForm.getDistrictCode());
		manageRoleForm.setHiddenBlockCode(manageRoleForm.getBlockCode());
		manageRoleForm.setHiddenVillageCode(manageRoleForm.getVillageCode());
		manageRoleForm.setHiddenLoginId(manageRoleForm.getLoginId());
		manageRoleForm.setHiddenLevelCode(manageRoleForm.getLevelCode());

		if (roleList.isEmpty()) {
			request.getSession().setAttribute("searchSuccess", Constants.SEARCH_FAIL);
		} else {
			request.getSession().setAttribute("roleList", roleList);
			request.getSession().setAttribute("searchSuccess", Constants.SEARCH_SUCCESS);
		}

		if (level.equalsIgnoreCase(Constants.MYSA_ADMIN_LEVEL)) {
			showPage = "searchAssignRole";
		}
		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
			showPage = "searchAssignRole";
		}
		if (level.equalsIgnoreCase(Constants.STATE_LEVEL)) {
			showPage = "searchAssignRoleState";
		}
		if (level.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			showPage = "searchAssignRoleDistrict";
		}
		return mapping.findForward(showPage);

	}

	public ActionForward getUserByCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserHelper userHelper = new UserHelper();
		userHelper.getUserByCode(request, response);
		return null;
	}

	public ActionForward getRoleByCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserHelper userHelper = new UserHelper();
		userHelper.getLevelOfRole(request, response);
		return null;
	}

	public ActionForward getRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserHelper userHelper = new UserHelper();
		userHelper.getRole(request, response);
		return null;
	}

	public ActionForward getVillageByBlockCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getVillageByBlockCode(request, response);
		return null;
	}

	public ActionForward getSelectedVillageByBlockCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getSelectedVillageByBlockCode(request, response);
		return null;
	}

	public ActionForward getBlocksByDistrictCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getBlocksByDistrictCode(request, response);
		return null;
	}

	public ActionForward getBlockChosenByDistrictCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getBlockChosenByDistrictCode(request, response);
		return null;
	}

	public ActionForward getBlockSelectedByDistrictCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getBlockSelectedByDistrictCode(request, response);
		return null;
	}

	public ActionForward getDistrictsByStateCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getDistrictsByStateCode(request, response);
		return null;
	}

	public ActionForward getSelectedDistrictsByStateCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getSelectedDistrictsByStateCode(request, response);
		return null;
	}

	public ActionForward getSDistrictsByStateCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ManageRoleHelper mHelper = new ManageRoleHelper();
		mHelper.getSelectedDistrictsByStateCode(request, response);
		return null;
	}

}