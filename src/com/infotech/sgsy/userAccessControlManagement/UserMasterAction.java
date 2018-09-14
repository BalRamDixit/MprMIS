package com.infotech.sgsy.userAccessControlManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.util.DateUtil;

/**
 * THIS CLASS IMPLEMENTS AccessMaster Action
 * @author 37595
 *  Date : 24.04.2009
 */
public class UserMasterAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();

	/*
	 * This method is used to display the Access Add Role page 
	 * 
	 */
	public ActionForward addUserMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		request.setAttribute("roleMasterList", new UserMasterDaoImpl().getAllRoleMsters(loginVO));
		request.setAttribute("userMastersList", new UserMasterDaoImpl().getAllRecords(loginVO));
		request.setAttribute("stateList", new UserMasterDaoImpl().getAllState());
		request.setAttribute("ctsaList", new UserMasterDaoImpl().getAllCTSA());
		UserMasterBean userMasterBean=new UserMasterBean();
		request.setAttribute("userMasterBean", userMasterBean);
		return mapping.findForward("addUserMaster");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String formName=request.getParameter("formName");
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		UserMasterDaoImpl UserMasterDaoImpl=new UserMasterDaoImpl();
		try{
			boolean flag=UserMasterDaoImpl.checkRecord(formName);
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
	public ActionForward saveUserMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		UserMasterBean userMasterBean=(UserMasterBean)form;
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		UserMaster userMaster=new UserMaster();
		BeanUtils.copyProperties(userMaster,userMasterBean);
		if(userMasterBean.getState()!=null && !userMasterBean.getState().equalsIgnoreCase("0")){
			StateVO state=new StateVO();
			state.setStateId(userMasterBean.getState());
			userMaster.setStateId(state);
		}
		if(userMasterBean.getCtsa()!=null && !userMasterBean.getCtsa().equalsIgnoreCase("0")){
			CtsaMasterVO state=new CtsaMasterVO();
			state.setId(userMasterBean.getCtsa());
			userMaster.setCtsaId(state);
		}
		userMaster.setCreatedBy(loginVO.getId());
		userMaster.setCreatedDate(DateUtil.getPresentDate());
		userMaster.setActiveFlag("Active");
		userMaster.setLoginAttempt(0);
		
		System.out.println("Object To Save Is --> "+userMaster);
		new UserMasterDaoImpl().save(userMaster);
		log.info("AccessMasterAction : Inside save method Ends======>");
		return mapping.findForward("saveUserMaster");
	}
	
	public ActionForward deleteUserMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside deleteRole method Starts======>");
		UserMasterBean userMasterBean=(UserMasterBean)form;
		UserMaster userMaster=new UserMaster();
		System.out.println("role Master Bean Id--> "+userMasterBean.getId());
		BeanUtils.copyProperties(userMaster,userMasterBean);
		System.out.println(userMaster);
		System.out.println(userMasterBean);
		boolean status=new UserMasterDaoImpl().deleteRecordFromId(userMaster);
		System.out.println("status Is --> "+status);
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		request.setAttribute("userMastersList", new UserMasterDaoImpl().getAllRecords(loginVO));
		request.setAttribute("roleMasterList", new UserMasterDaoImpl().getAllRoleMsters(loginVO));
		request.setAttribute("stateList", new UserMasterDaoImpl().getAllState());
		return mapping.findForward("addUserMaster");
	}
	public ActionForward editUserMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside Edit method Starts======>");
		UserMasterBean userMasterBean=(UserMasterBean)form;
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		request.setAttribute("userMastersList", new UserMasterDaoImpl().getAllRecords(loginVO));
		
		request.setAttribute("roleMasterList", new UserMasterDaoImpl().getAllRoleMsters(loginVO));
		System.out.println("role Master Bean Id--> "+userMasterBean.getId());
		UserMaster userMaster=new UserMasterDaoImpl().getRecordFromId(userMasterBean.getId());
		if(userMaster!=null){
			BeanUtils.copyProperties(userMasterBean,userMaster);
			if(userMaster.getStateId()!=null){
				userMasterBean.setState(userMaster.getStateId().getStateId());
			}
			if(userMaster.getCtsaId()!=null){
				userMasterBean.setCtsa(userMaster.getCtsaId().getId());
			}
			
		}
		System.out.println(userMaster);
		System.out.println(userMasterBean);
		userMasterBean.setEditId(userMasterBean.getId());
		request.setAttribute("userMasterBean", userMasterBean);
		request.setAttribute("stateList", new UserMasterDaoImpl().getAllState());
		request.setAttribute("ctsaList", new UserMasterDaoImpl().getAllCTSA());
		return mapping.findForward("editUserMaster");
	}
	public ActionForward updateUserMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside updateRole method Starts======>");
		UserMasterBean userMasterBean=(UserMasterBean)form;
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		
		System.out.println("role Master Bean edit Id--> "+userMasterBean.getEditId());
		System.out.println(userMasterBean);
		if(userMasterBean.getEditId()!=null){
			UserMaster userMaster=new UserMasterDaoImpl().getRecordFromId(userMasterBean.getEditId());
			
			userMaster.setMobileNo(userMasterBean.getMobileNo());
			userMaster.setEmailId(userMasterBean.getEmailId());
			userMaster.setUserName(userMasterBean.getUserName());
			userMaster.setUpdatedBy(loginVO.getId());
			userMaster.setUpdatedDate(DateUtil.getPresentDate());
			userMaster.setRoleId(userMasterBean.getRoleId());
			userMaster.setLoginStatus(userMasterBean.getLoginStatus());
			userMaster.setLoginAttempt(0);
			if(userMasterBean.getState()!=null && !userMasterBean.getState().equalsIgnoreCase("0")){
				StateVO state=new StateVO();
				state.setStateId(userMasterBean.getState());
				userMaster.setStateId(state);
			}
			if(userMasterBean.getCtsa()!=null && !userMasterBean.getCtsa().equalsIgnoreCase("0")){
				CtsaMasterVO state=new CtsaMasterVO();
				state.setId(userMasterBean.getCtsa());
				userMaster.setCtsaId(state);
			}
			System.out.println("Object To Save Is --> "+userMaster);
			new UserMasterDaoImpl().saveOrUpdate(userMaster);
		}
		request.setAttribute("userMastersList", new UserMasterDaoImpl().getAllRecords(loginVO));
		return mapping.findForward("updateUserMaster");
	}
	public ActionForward editUserPassword(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside Edit method Starts======>");
		UserMasterBean userMasterBean=(UserMasterBean)form;
		
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		request.setAttribute("userMastersList", new UserMasterDaoImpl().getAllRecords(loginVO));
		request.setAttribute("roleMasterList", new UserMasterDaoImpl().getAllRoleMsters(loginVO));
		System.out.println("role Master Bean Id--> "+userMasterBean.getId());
		UserMaster userMaster=new UserMasterDaoImpl().getRecordFromId(userMasterBean.getId());
		if(userMaster!=null){
			BeanUtils.copyProperties(userMasterBean,userMaster);
		}
		System.out.println(userMaster);
		System.out.println(userMasterBean);
		userMasterBean.setEditId(userMasterBean.getId());
		userMasterBean.setPassword("");
		request.setAttribute("userMasterBean", userMasterBean);
		return mapping.findForward("editUserPassword");
	}
	public ActionForward updateUserPassword(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside updateRole method Starts======>");
		UserMasterBean userMasterBean=(UserMasterBean)form;
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		
		System.out.println("role Master Bean edit Id--> "+userMasterBean.getEditId());
		System.out.println(userMasterBean);
		if(userMasterBean.getEditId()!=null){
			UserMaster userMaster=new UserMasterDaoImpl().getRecordFromId(userMasterBean.getEditId());
			userMaster.setPassword(userMasterBean.getPassword());
			userMaster.setUpdatedBy(loginVO.getId());
			userMaster.setUpdatedDate(DateUtil.getPresentDate());
			System.out.println("Object To Save Is --> "+userMaster);
			new UserMasterDaoImpl().saveOrUpdate(userMaster);
		}
		request.setAttribute("userMastersList", new UserMasterDaoImpl().getAllRecords(loginVO));
		return mapping.findForward("updateUserPassword");
	}
}
