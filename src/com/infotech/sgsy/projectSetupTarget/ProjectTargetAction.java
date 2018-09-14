package com.infotech.sgsy.projectSetupTarget;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetDAO;
import com.infotech.sgsy.stateSetupTarget.StateTargetDAO;
import com.infotech.sgsy.stateSetupTarget.StateTargetForm;
import com.infotech.sgsy.stateSetupTarget.StateTargetVO;

public class ProjectTargetAction extends DispatchAction  {
	ProjectTargetActionDAO helperDao = new ProjectTargetActionDAO();

	
	 public ActionForward projectSetupTarget(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HibernateException, Exception
	   {
		 	ProjectSetupTargetForm helperform=(ProjectSetupTargetForm)form;
		 
		 	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
		 	String entity_code=loginVO.getEntityCode();
			if(entity_code.length()>3){
				request.setAttribute("DetailsList", helperDao.getDetails(loginVO.getEntityCode(),"pia"));	
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue",new TradeTargetDAO().getPIANameByCode(entity_code));
			}else{
				request.setAttribute("DetailsList", helperDao.getDetails(loginVO.getEntityCode(),"srlm"));	
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}
			request.setAttribute("formName","PROJECT TARGET");	

			return mapping.findForward("projectTarget");
	   }
	 
	 
/*	  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String forwards = "Error";
         	int count=0;
         	
         	System.err.println("man,,,,,,,,,,,,,,,");
         	ProjectSetupTargetForm helperForm = (ProjectSetupTargetForm) form;
       
   	try {
		
   		ProjectSetupTargetVO helperVo = new ProjectSetupTargetVO();
	     	BeanUtils.copyProperties(helperVo, helperForm);
	     	helperDao.save(helperVo);	
		
	      	helperForm.reset(mapping, request);
        }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
   return mapping.findForward("projectTarget");
}*/
	 
	   public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				 
	           	
	           	ProjectSetupTargetForm helperForm = (ProjectSetupTargetForm) form;
	           	
	           	
	           

	   		 
	           	
		   
	     	try {
	     		helperForm.setStateName("ANDHRA PRADESH");
	     		
	   		  //  request.setAttribute("DetailsList",helperDao.getDetails());	
	   		    request.setAttribute("formName","PROJECT TARGET");	
	     		
	     		ProjectSetupTargetVO helperVo = new ProjectSetupTargetVO();
		     	BeanUtils.copyProperties(helperVo, helperForm);
		     	helperDao.save(helperVo);	
			
		      	helperForm.reset(mapping, request);
	          }   catch (Exception e) {
		    	  e.printStackTrace();
		     }
		    return mapping.findForward("projectTarget"); 
	}
			 	 

}
