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

public class TrainingInformationAction extends DispatchAction 
{

	protected final Log log = LogFactory.getLog(getClass());

	TrainingInformationDaoImpl helper = new TrainingInformationDaoImpl();

	List piaNameLst = null;
	List projectNameLst = null;
	List trainingCenterNameLst = null;
	List trainingCenterCodeLst = null;
	List trainingSummayLst = null;

	
	public ActionForward showPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
			{
		TrainingInformationForm helperForm = (TrainingInformationForm) form;
        piaNameLst = helper.getPiaName();
		request.setAttribute("piaNameLst", piaNameLst);
        projectNameLst = helper.getProjectName();
		request.setAttribute("projectNameLst", projectNameLst);
        trainingCenterNameLst = helper.getTrainingCenterName();
		request.setAttribute("trainingCenterNameLst", trainingCenterNameLst);
        trainingCenterCodeLst = helper.getTrainingCenterCode();
		request.setAttribute("trainingCenterCodeLst", trainingCenterCodeLst);
		   trainingSummayLst = helper.getTrainingSummary(helperForm);
			request.setAttribute("trainingSummayLst", trainingSummayLst);
			
        return mapping.findForward("showTrainingInformationPage");
	}

	public ActionForward showDropDown(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		TrainingInformationForm helperForm = (TrainingInformationForm) form;
        if(helperForm.getPiaName()!=null && !helperForm.getPiaName().equals(""))
     {
	  projectNameLst = helper.getProjectNameLst(helperForm);
	  request.setAttribute("projectNameLst", projectNameLst);
	}
        piaNameLst = helper.getPiaNameLst(helperForm);
        request.setAttribute("piaNameLst", piaNameLst);
        projectNameLst = helper.getProjectNameLst(helperForm);
 	    request.setAttribute("projectNameLst", projectNameLst);
 	    trainingCenterNameLst = helper.getTrainingCenterNameLst(helperForm);
        request.setAttribute("trainingCenterNameLst", trainingCenterNameLst);
         trainingCenterCodeLst = helper.getTrainingCenterCodeLst(helperForm);
         request.setAttribute("trainingCenterCodeLst", trainingCenterCodeLst);
         return mapping.findForward("showTrainingInformationPage");
}
	
	public ActionForward showDropDown1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		TrainingInformationForm helperForm = (TrainingInformationForm) form;
		
		if(helperForm.getProjectName()!= null || !"".equals(helperForm.getProjectName()))
		{
			helperForm.setPiaName(helper.getPiaNameLst(helperForm).get(0).toString());
		}
	    helperForm = new TrainingInformationForm();
		piaNameLst = helper.getPiaNameLst(helperForm);
	    request.setAttribute("piaNameLst", piaNameLst);
	    projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		trainingCenterNameLst = helper.getTrainingCenterNameLst(helperForm);
		request.setAttribute("trainingCenterNameLst", trainingCenterNameLst);
        trainingCenterCodeLst = helper.getTrainingCenterCodeLst(helperForm);
		request.setAttribute("trainingCenterCodeLst", trainingCenterCodeLst);
		return mapping.findForward("showTrainingInformationPage");
	}
	
	
	
	public ActionForward showDropDown2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		TrainingInformationForm helperForm = (TrainingInformationForm) form;
		if(helperForm.getTrainingcentername()!=null && !helperForm.getTrainingcentername().equals(""))
		{
			trainingCenterCodeLst = helper.getTrainingCenterCode(helperForm.getTrainingcentername());
			request.setAttribute("trainingCenterCodeLst", trainingCenterCodeLst);
		}
		  piaNameLst = helper.getPiaNameLst(helperForm);
	      request.setAttribute("piaNameLst", piaNameLst);
	      projectNameLst = helper.getProjectNameLst(helperForm);
	 	  request.setAttribute("projectNameLst", projectNameLst);
	 	  trainingCenterNameLst = helper.getTrainingCenterNameLst(helperForm);
		  request.setAttribute("trainingCenterNameLst", trainingCenterNameLst);
		return mapping.findForward("showTrainingInformationPage");	
	}
		  public ActionForward resetDropDownFilter(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
			{
	
	
			  TrainingInformationForm helperForm = (TrainingInformationForm) form;
	
	
			  piaNameLst = helper.getPiaName();
			     request.setAttribute("piaNameLst", piaNameLst);
			     
			     projectNameLst = helper.getProjectName();
			 	request.setAttribute("projectNameLst", projectNameLst);
			 	
			 	trainingCenterNameLst = helper.getTrainingCenterName();
				request.setAttribute("trainingCenterNameLst", trainingCenterNameLst);

	
				trainingCenterCodeLst = helper.getTrainingCenterCode();
				request.setAttribute("trainingCenterCodeLst", trainingCenterCodeLst);
	
	
				 return mapping.findForward("showTrainingInformationPage");	
	
	
	
			}
	
	public ActionForward showPage1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
			{
				TrainingInformationForm helperForm = (TrainingInformationForm) form;
				piaNameLst = helper.getPiaNameLst(helperForm);
				request.setAttribute("piaNameLst", piaNameLst);
				
				projectNameLst = helper.getProjectNameLst(helperForm);
				request.setAttribute("projectNameLst", projectNameLst);
				
				trainingCenterNameLst = helper.getTrainingCenterNameLst(helperForm);
				request.setAttribute("trainingCenterNameLst", trainingCenterNameLst);

	
				trainingCenterCodeLst = helper.getTrainingCenterCodeLst(helperForm);
				request.setAttribute("trainingCenterCodeLst", trainingCenterCodeLst);
	
	            trainingSummayLst = helper.getTrainingSummary(helperForm);
				request.setAttribute("trainingSummayLst", trainingSummayLst);
				
				 return mapping.findForward("showTrainingInformationPage");	
					
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}