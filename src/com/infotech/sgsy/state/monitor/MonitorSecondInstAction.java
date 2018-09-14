package com.infotech.sgsy.state.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;

public class MonitorSecondInstAction extends DispatchAction {

	MonitorSecondInstDAO helperDao = new MonitorSecondInstDAO();
	DateUtil dateHelper	= new DateUtil();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			
	   // LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
	    MonitorSecondInstActionForm helperForm=(MonitorSecondInstActionForm)form;
		 String abc=null;
		 request.setAttribute("projectList", helperDao.getPiaListForProjectId());
		 
	//	 helperForm.setStateCode(loginVO.getEntityCode());  //to set the state Code from login vo 
	//	 helperForm.setStateName(helperDao.getState(helperForm.getStateCode()));  //to set the state Name from login vo 
		
		 System.out.println(helperForm.getProjectId());
		 helperForm.reset(mapping,request);
		
	     String forward= "showPage";
		request.setAttribute("formName","MONITORING SECOND INSTALLMENT");		 
       return mapping.findForward(forward);
	}
	
	  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	     {
	     	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	     	MonitorSecondInstActionForm helperForm = (MonitorSecondInstActionForm) form;
			try {
				MonitorSecondInstVO helperVo = new MonitorSecondInstVO();
		    	List<String> projectIdList = new ArrayList<String>();
				projectIdList=helperDao.getPiaForProjectIds(loginVO.getEntityCode());
				String[] projectidarr=new String [projectIdList.size()];
				projectidarr=projectIdList.toArray(projectidarr);
				
				helperForm.setProjectId(projectidarr);
				
		     	helperDao.saveSecoundInstallment(helperForm,loginVO.getUserid(),dateHelper.getCurrentDate());
		     	request.setAttribute("NOTIFICATION", "Secound Installment Form is saved successfully.Please Click on continue for THIRD Installment");
		     	helperForm.reset(mapping, request);
			
				}  
           catch (Exception e)
           {
	    	  e.printStackTrace();
	       }
         
	    return mapping.findForward("showPage"); 
}
	 
}
