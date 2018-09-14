package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class TrainingDurationAction extends DispatchAction {
	
protected final Log log = LogFactory.getLog(getClass());
List piaNameLst = null;
List projectNameLst = null;
List sectorNameLst = null;
List tradeNameLst = null;
List certifyingAgencyLst = null;
List trainingDurationLst = null;
public TrainingDurationDaoImpl helper = new TrainingDurationDaoImpl();
	
	
	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		TrainingDurationForm helperForm = (TrainingDurationForm) form;
		
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		
		sectorNameLst = helper.getSectorNameLst(helperForm);
		request.setAttribute("sectorNameLst", sectorNameLst);
		
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
		
		certifyingAgencyLst = helper.getCertifyingAgencyLst();
		request.setAttribute("certifyingAgencyLst", certifyingAgencyLst);
		
		trainingDurationLst = helper.getExpenditureSummary(helperForm);
		request.setAttribute("trainingDurationLst", trainingDurationLst);

		return mapping.findForward("showTrainingDurationPage");
	}
	
	public ActionForward resetFilters(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
			{
		TrainingDurationForm helperForm = (TrainingDurationForm) form;
		
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		
		sectorNameLst = helper.getSectorNameLst(helperForm);
		request.setAttribute("sectorNameLst", sectorNameLst);
		
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
		
		certifyingAgencyLst = helper.getCertifyingAgencyLst();
		request.setAttribute("certifyingAgencyLst", certifyingAgencyLst);
		
		trainingDurationLst = helper.getExpenditureSummary(helperForm);
		request.setAttribute("trainingDurationLst", trainingDurationLst);

		return mapping.findForward("showTrainingDurationPage");
	}
	
	
	public ActionForward showDropDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		TrainingDurationForm helperForm = (TrainingDurationForm) form;
		 if(helperForm.getPiaName()!=null && !helperForm.getPiaName().equals(""))
         {
			 
				projectNameLst = helper.getProjectNameLst(helperForm);
				request.setAttribute("projectNameLst", projectNameLst);
         }
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
         
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		
		sectorNameLst = helper.getSectorNameLst(helperForm);
		request.setAttribute("sectorNameLst", sectorNameLst);
		
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
		
		certifyingAgencyLst = helper.getCertifyingAgencyLst();
		request.setAttribute("certifyingAgencyLst", certifyingAgencyLst);
		
		return mapping.findForward("showTrainingDurationPage");
	}
	
	public ActionForward showDropDown1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		TrainingDurationForm helperForm = (TrainingDurationForm) form;
		if(helperForm.getProjectName()!= null || !"".equals(helperForm.getProjectName()))
		{
			helperForm.setPiaName(helper.getPiaNameLst(helperForm).get(0).toString());
		} 
	    helperForm = new TrainingDurationForm();
		piaNameLst = helper.getPiaNameLst(helperForm);
	    request.setAttribute("piaNameLst", piaNameLst);
	    projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		sectorNameLst = helper.getSectorNameLst(helperForm);
		request.setAttribute("sectorNameLst", sectorNameLst);
		
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
		
		certifyingAgencyLst = helper.getCertifyingAgencyLst();
		request.setAttribute("certifyingAgencyLst", certifyingAgencyLst);
		return mapping.findForward("showTrainingDurationPage");
	}
	public ActionForward showDropDown2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		TrainingDurationForm helperForm = (TrainingDurationForm) form;
		 if(helperForm.getSectorName()!=null && !helperForm.getSectorName().equals(""))
         {
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
         }
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);

		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		
		sectorNameLst = helper.getSectorNameLst(helperForm);
		request.setAttribute("sectorNameLst", sectorNameLst);
		
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
		
		certifyingAgencyLst = helper.getCertifyingAgencyLst();
		request.setAttribute("certifyingAgencyLst", certifyingAgencyLst);
		
		return mapping.findForward("showTrainingDurationPage");
	}

	
	public ActionForward showDropDown3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		TrainingDurationForm helperForm = (TrainingDurationForm) form;

		if(helperForm.getTradeName()!= null || !"".equals(helperForm.getTradeName()))
		{
			helperForm.setSectorName(helper.getSectorNameLst(helperForm).get(0).toString());
		}
		helperForm = new TrainingDurationForm();
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);

		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		
		sectorNameLst = helper.getSectorNameLst(helperForm);
		request.setAttribute("sectorNameLst", sectorNameLst);
		 
		tradeNameLst = helper.getTradeNameLst(helperForm);
		request.setAttribute("tradeNameLst", tradeNameLst);
       
		certifyingAgencyLst = helper.getCertifyingAgencyLst();
		request.setAttribute("certifyingAgencyLst", certifyingAgencyLst);
		
		return mapping.findForward("showTrainingDurationPage");
	}
	
}
