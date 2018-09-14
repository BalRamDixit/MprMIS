package com.infotech.sgsy.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.monitoringProgressReport.MonitoringProgressReportDAO;
import com.infotech.sgsy.monitoringProgressReport.MonitoringProgressReportForm;

public class ReportMappingAction extends DispatchAction {

	public ActionForward championEmployer(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("championEmployer");
	}
	
	public ActionForward appraisals(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("appraisals");
	}
	
	public ActionForward exceptionsNational(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("exceptionsNational");
	}
	
	public ActionForward exceptionsState(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("exceptionsState");
	}
	
	public ActionForward financialAchievement(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("financialAchievement");
	}
	
	public ActionForward installmentStatus(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("installmentStatus");
	}
	
	public ActionForward overallIndicators(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("overallIndicators");
	}
	
	public ActionForward physicalAchievement(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("physicalAchievement");
		
	}
	
	public ActionForward dashboard(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReportMappingDAO reportMappingDAO =new ReportMappingDAO();
		List<ReportMappingBean> details= reportMappingDAO.getSocialGenderTrainingCompletion();
		List<ReportMappingBean> details1= reportMappingDAO.getGenderTrainingCompletion();
		List<ReportMappingBean> details2= reportMappingDAO.getSocialGenderTrainingCompletionMinorityTrainingCompletion();
		List<ReportMappingBean> details3= reportMappingDAO.getPlaced();
		List<ReportMappingBean> details4= reportMappingDAO.getCommencement();
		List<ReportMappingBean> details5= reportMappingDAO.getCompletion();
		List<ReportMappingBean> details6= reportMappingDAO.getGeneral();
		List<ReportMappingBean> details7= reportMappingDAO.getKeyDates();
		List<ReportMappingBean> details8= reportMappingDAO.getTradeSanctioned();
		List<ReportMappingBean> details9= reportMappingDAO.getAlertsAdvisories();
		List<ReportMappingBean> details10= reportMappingDAO.getAchievement();
		List<ReportMappingBean> details11= reportMappingDAO.getFinancials();
		List<ReportMappingBean> details12= reportMappingDAO.getResidential();
		List<ReportMappingBean> details13= reportMappingDAO.getDistrictsCovered();
		
		request.setAttribute("formBean", details);
		request.setAttribute("formBean1", details1);
		request.setAttribute("formBean2", details2);
		request.setAttribute("formBean3", details3);
		request.setAttribute("formBean4", details4);
		request.setAttribute("formBean5", details5);
		request.setAttribute("formBean6", details6);
		request.setAttribute("formBean7", details7);
		request.setAttribute("formBean8", details8);
		request.setAttribute("formBean9", details9);
		request.setAttribute("formBean10", details10);
		request.setAttribute("formBean11", details11);
		request.setAttribute("formBean12", details12);
		request.setAttribute("formBean13", details13);
		
		return mapping.findForward("dashboard");
	}

}