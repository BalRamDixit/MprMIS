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

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;

/**
 * THIS CLASS IMPLEMENTS AccessMaster Action
 * @author 37595
 *  Date : 24.04.2009
 */
public class RoleMasterAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();

	/*
	 * This method is used to display the Access Add Role page 
	 * 
	 */
	public ActionForward addRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("roleList", new RoleMasterDaoImpl().getAllRecords());
		RoleMasterBean roleMasterBean=new RoleMasterBean();
		request.setAttribute("roleMasterBean", roleMasterBean);
		return mapping.findForward("addRole");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String roleName=request.getParameter("roleName");
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		RoleMasterDaoImpl roleMasterDaoImpl=new RoleMasterDaoImpl();
		try{
			boolean flag=roleMasterDaoImpl.checkRecord(roleName);
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
	public ActionForward saveRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		RoleMasterBean roleMasterBean=(RoleMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		RoleMaster roleMaster=new RoleMaster();
		BeanUtils.copyProperties(roleMaster,roleMasterBean);
		roleMaster.setCreatedBy(loginVO.getUserid());
		roleMaster.setCreatedDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is --> "+roleMaster);
		new RoleMasterDaoImpl().save(roleMaster);
		log.info("AccessMasterAction : Inside save method Ends======>");
		return mapping.findForward("saveRole");
	}
	public ActionForward editRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside Edit method Starts======>");
		RoleMasterBean roleMasterBean=(RoleMasterBean)form;
		request.setAttribute("roleList", new RoleMasterDaoImpl().getAllRecords());
		System.out.println("role Master Bean Id--> "+roleMasterBean.getId());
		RoleMaster roleMaster=new RoleMasterDaoImpl().getRecordFromId(roleMasterBean.getId());
		if(roleMaster!=null){
			BeanUtils.copyProperties(roleMasterBean,roleMaster);
		}
		System.out.println(roleMaster);
		System.out.println(roleMasterBean);
		roleMasterBean.setEditId(roleMasterBean.getId());
		request.setAttribute("roleMasterBean", roleMasterBean);
		return mapping.findForward("editRole");
	}
	public ActionForward deleteRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside deleteRole method Starts======>");
		RoleMasterBean roleMasterBean=(RoleMasterBean)form;
		RoleMaster roleMaster=new RoleMaster();
		System.out.println("role Master Bean Id--> "+roleMasterBean.getId());
		BeanUtils.copyProperties(roleMaster,roleMasterBean);
		System.out.println(roleMaster);
		System.out.println(roleMasterBean);
		boolean status=new RoleMasterDaoImpl().deleteRecordFromId(roleMaster);
		System.out.println("status Is --> "+status);
		request.setAttribute("roleList", new RoleMasterDaoImpl().getAllRecords());
		return mapping.findForward("addRole");
	}
	
	public ActionForward updateRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside updateRole method Starts======>");
		RoleMasterBean roleMasterBean=(RoleMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		System.out.println("role Master Bean edit Id--> "+roleMasterBean.getEditId());
		System.out.println(roleMasterBean);
		if(roleMasterBean.getEditId()!=null){
			RoleMaster roleMaster=new RoleMasterDaoImpl().getRecordFromId(roleMasterBean.getEditId());
			roleMaster.setRoleName(roleMasterBean.getRoleName());
			roleMaster.setRoleDescription(roleMasterBean.getRoleDescription());
			roleMaster.setUpdatedBy(loginVO.getUserid());
			roleMaster.setUpdatedDate(DateUtil.getPresentDate());
			System.out.println("Object To Save Is --> "+roleMaster);
			new RoleMasterDaoImpl().saveOrUpdate(roleMaster);
		}
		request.setAttribute("roleMasterBean",new RoleMasterBean());
		request.setAttribute("roleList", new RoleMasterDaoImpl().getAllRecords());
		return mapping.findForward("updateRole");
	}
}
