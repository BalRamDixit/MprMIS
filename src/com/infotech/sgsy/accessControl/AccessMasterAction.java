package com.infotech.sgsy.accessControl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.PopUpMessages;
import com.infotech.sgsy.util.SGSYConstants;

/**
 * THIS CLASS IMPLEMENTS AccessMaster Action
 * @author 37595
 *  Date : 24.04.2009
 */
public class AccessMasterAction extends MasterAction {

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();

	/*
	 * This method is used to display the Access Add Role page 
	 * 
	 */
	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside showAdd method Starts======>");
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		actionForm.reset();
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		actionForm.setLevelSchemeName("");
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		//String level = loginVO.getLevelCode();
		Map<String,Object> levelList = access.getLevelList(loginVO.getLevelCode());
		Map<String,Object> schemeList = access.getSchemeList(loginVO.getLevelCode());
		HttpSession httpSession=request.getSession();
		httpSession.setAttribute("levelList",levelList);
		httpSession.setAttribute("schemeList",schemeList);
		actionForm.setInformationDialog(false); 
		log.info("AccessMasterAction : Inside showAdd method Ends======>");
		return mapping.findForward("addRole");
	}
	
	public ActionForward getFormDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws  Exception
	{
		log.info("AccessMasterAction : Inside getFormDetails method Starts======>"+request.getParameter("levelRoleName"));
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo=new AccessVO();
		actionForm.setInformationDialog(false); 
		//vo.setRoleName(request.getParameter("roleName"));
		//vo.setRoleDescription(request.getParameter("roleDescription"));
		vo.setSchemeCode(actionForm.getLevelSchemeName());
		vo.setLevelRoleName(request.getParameter("levelRoleName"));
		
		//BeanUtils.copyProperties(actionForm,vo);
		List subModList = access.getAllSubModule(vo);
		request.setAttribute("subModList",subModList);
		actionForm.setAccessList(subModList);
		log.info("AccessMasterAction : Inside getFormDetails method Ends======>"+subModList.size());
		return mapping.findForward("addRole");

	}	
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String roleName=request.getParameter("roleName");
		String roleDesc=request.getParameter("roleDesc");
		
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		AccessService access = new AccessServiceImpl();
				
		try{
			boolean flag=access.checkRecord(roleName,roleDesc);
			//System.out.println("====flag====>"+flag);
			if(flag==false){
				response.getWriter().write("error");
		}else{   
			s="true";
			response.getWriter().write(s);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		message.clear();
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		AccessVO vo = new AccessVO();
		
		BeanUtils.copyProperties(vo,actionForm);
		vo.setSchemeCode(actionForm.getLevelSchemeName());
		vo.setCreatedBy(loginVO.getUserid());
		vo.setCreatedOn(DateUtil.getPresentDate());
		List subModList=actionForm.getAccessList();
		access.addRole(vo, subModList);
		log.info("AccessMasterAction : Inside save method Ends======>");
		actionForm.setInformationDialog(true); 
		actionForm.setInformationDialogHeader(PopUpMessages.SAVE_HEADER);
		actionForm.setInformationDialogText(PopUpMessages.SAVE_TEXT);
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		return mapping.findForward(Constants.SAVE);
	}

	@Override
	public ActionForward showModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		message.clear();
		log.info("AccessMasterAction : Inside showModify method Starts======>");
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		actionForm.resetFields();
		HttpSession httpSession=request.getSession();
		
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		
		Map<String,Object> roleNameList = new HashMap<String, Object>();
		httpSession.setAttribute("roleNameList",roleNameList);
			
		//Map<String,Object> levelList = access.getLevelList();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		Map<String,Object> levelList = access.getLevelList(loginVO.getLevelCode());
		Map<String,Object> schemeList = access.getSchemeList(loginVO.getLevelCode());
		httpSession.setAttribute("roleLevelList",levelList);
		httpSession.setAttribute("schemeList",schemeList);
		actionForm.setInformationDialog(false); 
		log.info("AccessMasterAction : Inside showModify method Ends======>");
		return mapping.findForward("updateRole");
	}
	public ActionForward getRoleName(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws  Exception
	{
		message.clear();
		log.info("AccessMasterAction : Inside getRoleName method Starts======>"+request.getParameter("roleName"));
		HttpSession httpSession=request.getSession();
		ServletContext context=httpSession.getServletContext();
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo=new AccessVO();
		String jspFor=request.getParameter("jspFor");
		vo.setLevelRoleName(request.getParameter("roleLevel"));
		vo.setSchemeCode(actionForm.getLevelSchemeName());
		
		actionForm.setInformationDialog(false); 			
		Map<String,Object> roleNameList = access.getRoleNameList(vo);
		if(!roleNameList.isEmpty() && roleNameList.size()>0){
			httpSession.setAttribute("roleNameList",roleNameList);
		}else{
			message.add(SGSYConstants.MSG,new ActionMessage("notfound.error",context.getAttribute("label.roleName").toString()));
			saveMessages(request,message);		
		}
		
		request.setAttribute("fieldDisabled", "fieldDisabled");		
		log.info("AccessMasterAction : Inside getRoleName method Ends======>"+roleNameList.size());
		if(Constants.DELETE.equalsIgnoreCase(jspFor))
		{
			return mapping.findForward("deleteRole");
		}else if(Constants.VIEW.equalsIgnoreCase(jspFor))
		{
			return mapping.findForward("viewRole");
		}else
		{
		return mapping.findForward("updateRole");
		}

	}	
	
	public ActionForward getRoleDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws  Exception
	{
		message.clear();
		log.info("AccessMasterAction : Inside getRoleDetails method Starts======>"+request.getParameter("roleName"));
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo=new AccessVO();
		String jspFor=request.getParameter("jspFor");
		vo.setLevelRoleName(request.getParameter("roleLevel"));
		vo.setRoleName(request.getParameter("roleName"));
		vo.setLevelRoleName(request.getParameter("roleLevel"));
		vo.setSchemeCode(actionForm.getLevelSchemeName());
		actionForm.setInformationDialog(false); 			
		List roleDetailsList = access.getRoleDetailsList(vo);
		request.setAttribute("roleDetailsList",roleDetailsList);
		actionForm.setAccessList(roleDetailsList);
		
		Iterator itr=(Iterator)roleDetailsList.iterator();
		while(itr.hasNext()){
			
			RoleMasterVO rvo =(RoleMasterVO)itr.next();
			actionForm.setRoleDescription(rvo.getRoleDescription());
			actionForm.setLevelRoleName(rvo.getLevelRoleName());
			actionForm.setLevelRoleName(vo.getLevelRoleName());
		}
		request.setAttribute("fieldDisabled", "fieldDisabled");
		log.info("AccessMasterAction : Inside getRoleDetails method Ends======>"+roleDetailsList.size());
		if(Constants.DELETE.equalsIgnoreCase(jspFor)){
			return mapping.findForward("deleteRole");
		}else if(Constants.VIEW.equalsIgnoreCase(jspFor))
		{
			return mapping.findForward("viewRole");
		}else{
		return mapping.findForward("updateRole");
		}

	}	
	
	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AccessMasterAction : Inside modify method Starts======>");
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo = new AccessVO();
		message.clear();
		BeanUtils.copyProperties(vo,actionForm);
		
		System.out.println("===role name==="+vo.getRoleName());
		System.out.println("===role Description==="+vo.getRoleDescription());
		List subModList=actionForm.getAccessList();
		access.modifyRole(vo, subModList);
		log.info("AccessMasterAction : Inside modify method Ends======>");
		actionForm.setInformationDialog(true); 
		actionForm.setInformationDialogHeader(PopUpMessages.MODIFY_HEADER);
		actionForm.setInformationDialogText(PopUpMessages.MODIFY_TEXT);
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		return mapping.findForward(Constants.MODIFY);
	}
	
	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AccessMasterAction : Inside showDelete method Starts======>");
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		HttpSession httpSession=request.getSession();
		message.clear();
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		
		Map<String,Object> roleNameList = new HashMap<String, Object>();
		httpSession.setAttribute("roleNameList",roleNameList);
			
		//Map<String,Object> levelList = access.getLevelList();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		Map<String,Object> levelList = access.getLevelList(loginVO.getLevelCode());
		httpSession.setAttribute("roleLevelList",levelList);
				
		actionForm.setInformationDialog(false); 
		log.info("AccessMasterAction : Inside showDelete method Ends======>");
		return mapping.findForward("deleteRole");
	}
	/*
	public ActionForward getSelectedRoleDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws  Exception
	{
		log.info("AccessMasterAction : Inside getSelectedRoleDetails method Starts======>"+request.getParameter("roleName"));
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo=new AccessVO();
		actionForm.setInformationDialog(false); 
		vo.setRoleName(request.getParameter("roleName"));
					
		List roleDetailsList = access.getSelectedRoleDetails(vo);
		request.setAttribute("roleDetailsList",roleDetailsList);
		actionForm.setAccessList(roleDetailsList);
		
		Iterator itr=(Iterator)roleDetailsList.iterator();
		while(itr.hasNext()){
			
			RoleMasterVO rvo =(RoleMasterVO)itr.next();
			actionForm.setRoleDescription(rvo.getRoleDescription());
			actionForm.setLevelRoleName(rvo.getLevelRoleName());
			
		}	
		
		log.info("AccessMasterAction : Inside getSelectedRoleDetails method Ends======>"+roleDetailsList.size());
		return mapping.findForward("deleteRole");

	}	
	*/
	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AccessMasterAction : Inside delete method Starts======>");
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo = new AccessVO();
		message.clear();
		BeanUtils.copyProperties(vo,actionForm);
		//System.out.println("===role name==="+vo.getRoleName());
		List subModList=actionForm.getAccessList();
		boolean flag=access.checkRoleAssignedRecord(vo);
		if(flag){
			ActionErrors errors = new ActionErrors();
			errors.add("roleAssigned", new ActionError("errors.RoleAlreadyAssigned"));
			if ( !errors.isEmpty() ) {
		         saveErrors(request, errors);
		       }
			return mapping.findForward(Constants.DELETE);
		}else{
		access.deleteRole(vo, subModList);
		actionForm.setInformationDialog(true); 
		actionForm.setInformationDialogHeader(PopUpMessages.DELETE_HEADER);
		actionForm.setInformationDialogText(PopUpMessages.DELETE_TEXT);
		}
		log.info("AccessMasterAction : Inside delete method Ends======>");
		actionForm.resetFields();
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		//Map<String,Object> roleNameList = access.getRoleNameList();
		//HttpSession httpSession=request.getSession();
		//httpSession.setAttribute("roleNameList",roleNameList);
		return mapping.findForward(Constants.DELETE);
		
	}
	
	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AccessMasterAction : Inside showView method Starts======>");
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		HttpSession httpSession=request.getSession();
		
		actionForm.setRoleDescription(null);
		actionForm.setRoleName(null);
		actionForm.setLevelRoleName("");
		
		Map<String,Object> roleNameList = new HashMap<String, Object>();
		httpSession.setAttribute("roleNameList",roleNameList);
			
		//Map<String,Object> levelList = access.getLevelList();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		Map<String,Object> levelList = access.getLevelList(loginVO.getLevelCode());
		Map<String,Object> schemeList = access.getSchemeList(loginVO.getLevelCode());
		httpSession.setAttribute("roleLevelList",levelList);
		httpSession.setAttribute("schemeList",schemeList);
				
		actionForm.setInformationDialog(false); 
		log.info("AccessMasterAction : Inside showView method Ends======>");
		return mapping.findForward("viewRole");
	}
	/*
	public ActionForward viewRoleDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws  Exception
	{
		log.info("AccessMasterAction : Inside viewRoleDetails method Starts======>"+request.getParameter("roleName"));
		AccessService access = new AccessServiceImpl();
		AccessActionForm actionForm=(AccessActionForm)form;
		AccessVO vo=new AccessVO();
		actionForm.setInformationDialog(false); 
		vo.setRoleName(request.getParameter("roleName"));
					
		List roleDetailsList = access.getSelectedRoleDetails(vo);
		request.setAttribute("roleDetailsList",roleDetailsList);
		actionForm.setAccessList(roleDetailsList);
		
		Iterator itr=(Iterator)roleDetailsList.iterator();
		while(itr.hasNext()){
			
			RoleMasterVO rvo =(RoleMasterVO)itr.next();
			actionForm.setRoleDescription(rvo.getRoleDescription());
			actionForm.setLevelRoleName(rvo.getLevelRoleName());
			
		}	
		
		log.info("AccessMasterAction : Inside viewRoleDetails method Ends======>"+roleDetailsList.size());
		return mapping.findForward("viewRole");

	}	

	*/



	
}
