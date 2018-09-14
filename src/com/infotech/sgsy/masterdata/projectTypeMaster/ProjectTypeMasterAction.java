package com.infotech.sgsy.masterdata.projectTypeMaster;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationDAO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationForm;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;
import com.infotech.sgsy.userAccessControlManagement.ProjectMappingDaoImpl;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterBean;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;
import com.infotech.sgsy.util.DateUtil;

public class ProjectTypeMasterAction extends DispatchAction {
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 
 		
	/*	ProjectTypeMasterBean  helperForm = (ProjectTypeMasterBean) form; */
		
 		request.setAttribute("projectTypeList", new ProjectTypeMasterDAO().getAllRecords());

		return mapping.findForward("projectType");



					}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
 		
		log.info("ProjectTypeMasterAction : Inside Edit method Starts======>");
		ProjectTypeMasterBean  Bean=(ProjectTypeMasterBean)form;
		//request.setAttribute("roleList", new RoleMasterDaoImpl().getAllRecords());
 		request.setAttribute("projectTypeList", new ProjectTypeMasterDAO().getAllRecords());

		System.out.println("ProjectTypeMasterBean Id in edit --> "+Bean.getProjectTypeId());
		ProjectTypeMasterVO helperVO=new ProjectTypeMasterDAO().getRecordFromId(Bean.getProjectTypeId());
		if(helperVO!=null){
			BeanUtils.copyProperties(Bean,helperVO);
		}
  		Bean.setEditId(Bean.getProjectTypeId());
		request.setAttribute("Bean", Bean);
		return mapping.findForward("editRole");
	}
	 public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
 		log.info("ProjectTypeMasterAction : Inside delet method Starts======>");
		ProjectTypeMasterBean  Bean=(ProjectTypeMasterBean)form;
		ProjectTypeMasterVO helperVO=new ProjectTypeMasterVO();
 		BeanUtils.copyProperties(helperVO,Bean);
 		boolean status=new ProjectTypeMasterDAO().deleteRecordFromId(helperVO);
		System.out.println("status Is --> "+status);
      	request.setAttribute("projectTypeList", new ProjectTypeMasterDAO().getAllRecords());
			return mapping.findForward("projectType");
	}
	 
	
	 
	 public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			log.info("ProjectTypeMasterAction : Inside update method Starts======>");
			ProjectTypeMasterBean  Bean=(ProjectTypeMasterBean)form;
 			
 			System.out.println(Bean);
			if(Bean.getEditId()!=null){
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				ProjectTypeMasterVO helperVO=new ProjectTypeMasterDAO().getRecordFromId(Bean.getEditId());
				/*helperVO.setRoleName(Bean.getRoleName());
				helperVO.setRoleDescription(Bean.getRoleDescription());*/
				helperVO.setProjectTypCode(Bean.getProjectTypCode()); 
				helperVO.setProjectTypeName(Bean.getProjectTypeName()); 				
				helperVO.setUpdatedBy(loginVO.getUserid());
				helperVO.setUpdatedOn(DateUtil.getPresentDate());
				System.out.println("Object To Save Is --> "+helperVO);
				new ProjectTypeMasterDAO().saveOrUpdate(helperVO);
			}
			request.setAttribute("Bean",new ProjectTypeMasterBean());
	      	request.setAttribute("projectTypeList", new ProjectTypeMasterDAO().getAllRecords());
			return mapping.findForward("updateRole");
		}
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
  		ProjectTypeMasterDAO helperDao = new ProjectTypeMasterDAO();
		ProjectTypeMasterBean  helperForm = (ProjectTypeMasterBean) form; 
 	 	try 
	 	{
	 		
	 	ProjectTypeMasterVO helperVo = new ProjectTypeMasterVO();
     	BeanUtils.copyProperties(helperVo, helperForm);
     	helperVo.setCreatedBy("");   
     	helperVo.setCreatedOn(new Date());
      	helperDao.save(helperVo);	
      	helperForm.reset(mapping, request);
      	
       		    
	    
	      }   catch (Exception e) {
    	  e.printStackTrace();
     }
 		request.setAttribute("projectTypeList", new ProjectTypeMasterDAO().getAllRecords());
	   return mapping.findForward("projectType");
}
	
	
}
