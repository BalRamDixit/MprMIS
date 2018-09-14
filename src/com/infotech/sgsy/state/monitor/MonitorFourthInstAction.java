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

public class MonitorFourthInstAction extends DispatchAction {

	MonitorFourthInstDAO helperDao = new MonitorFourthInstDAO();
	DateUtil dateHelper	= new DateUtil();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	//	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		MonitorFourthInstActionForm helperForm=(MonitorFourthInstActionForm)form;
		String abc=null;
		request.setAttribute("projectList", helperDao.getPiaListForProjectId());
			 
	//	helperForm.setStateCode(loginVO.getEntityCode());   
	//	helperForm.setStateName(helperDao.getState(helperForm.getStateCode())); 
		 helperForm.reset(mapping,request);
		 
		String forward= "showPage";
		request.setAttribute("formName","MONITORING FOURTH INSTALLMENT");		 
	    return mapping.findForward(forward);
	}
	
 public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
   {
	     	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	     	MonitorFourthInstActionForm helperForm = (MonitorFourthInstActionForm) form;
			try {
				MonitorFourthInstVO helperVo = new MonitorFourthInstVO();
		    	List<String> projectIdList = new ArrayList<String>();
				projectIdList=helperDao.getPiaForProjectIds();
				String[] projectidarr=new String [projectIdList.size()];
				projectidarr=projectIdList.toArray(projectidarr);
				
				helperForm.setProjectId(projectidarr);
				
		     	helperDao.saveFourthInstallment(helperForm,loginVO.getUserid(),dateHelper.getCurrentDate(),loginVO.getEntityCode());
		     	request.setAttribute("NOTIFICATION", "Fourth Installment Form is saved successfully.");
		     	helperForm.reset(mapping,request);
			}  
           catch (Exception e)
           {
	    	  e.printStackTrace();
	       }
        return mapping.findForward("showPage"); 
  }
	 
	
}