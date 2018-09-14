package com.infotech.skills.monitoringProjectCandidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.monitoringalerts.MonitoringAlertDAO;
import com.infotech.sgsy.monitoringalerts.MonitoringAlertForm;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;
import com.infotech.skills.skillsReport.piaLog.PiaLogForm;

import MonitoringAlertDAO.CandidateDataDAO;

public class ProjectCandidateDataAction extends DispatchAction{

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		CandidateDataDAO helperDao = new CandidateDataDAO();
		DateUtil dateUtil = new DateUtil();
		
		String forward= "monitoring";
		CandidateDataForm helperForm = (CandidateDataForm) form;
		
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
			LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;

            request.setAttribute("ProjectCandidateList", helperDao.getProjectCandidateList(loginVO.getEntityCode()));
   		 request.setAttribute("formName","PROJECT CANDIDATE DATA");		 

		   }catch(Exception e) {
	}
		return mapping.findForward(forward);
	}
	
}
