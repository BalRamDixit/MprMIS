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

public class DistrictSummaryAction extends DispatchAction 
{
	List piaNameLst = null;
	List projectNameLst = null;
	List stateNameLst = null;
	List districtNameLst = null;
	List districtSummayLst = null;
	DistrictSummaryDaoImpl helper = new DistrictSummaryDaoImpl();

	protected final Log log = LogFactory.getLog(getClass());


/**
 * 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
	public ActionForward showPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
			{
		DistrictSummaryForm helperForm = (DistrictSummaryForm) form;
        piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		stateNameLst = helper.getStateNameLst(helperForm);
		request.setAttribute("stateNameLst", stateNameLst);
		districtSummayLst = helper.getDistrictSummary(helperForm);
		request.setAttribute("districtSummayLst", districtSummayLst);
		return mapping.findForward("showDistrictSummaryPage");
	   }
	
	public ActionForward showPage1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		DistrictSummaryForm helperForm = (DistrictSummaryForm) form;
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		stateNameLst = helper.getStateNameLst(helperForm);
		request.setAttribute("stateNameLst", stateNameLst);
		districtNameLst = helper.getDistrictName(helperForm.getStateName());
		request.setAttribute("districtNameLst", districtNameLst);
		districtSummayLst = helper.getDistrictSummary(helperForm);
		request.setAttribute("districtSummayLst", districtSummayLst);
		return mapping.findForward("showDistrictSummaryPage");
	}
	
	/* ***********************************************To specify project names while selecting PIA name******************************/
	public ActionForward showDropDown(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
                 DistrictSummaryForm helperForm = (DistrictSummaryForm) form;
                if(helperForm.getPiaName()!=null && !helperForm.getPiaName().equals(""))
                {
	                  projectNameLst = helper.getProjectNameLst(helperForm);
	                  request.setAttribute("projectNameLst", projectNameLst);
                }
         piaNameLst = helper.getPiaNameLst(helperForm);
         request.setAttribute("piaNameLst", piaNameLst);
         projectNameLst = helper.getProjectNameLst(helperForm);
  	     request.setAttribute("projectNameLst", projectNameLst);
 	     stateNameLst = helper.getStateNameLst(helperForm);
         request.setAttribute("stateNameLst", stateNameLst);
         return mapping.findForward("showDistrictSummaryPage");
}
	
/* ***********************************************To specify pia names while selecting project name******************************/
	public ActionForward showDropDown1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		DistrictSummaryForm helperForm = (DistrictSummaryForm) form;
		if(helperForm.getProjectName()!= null || !"".equals(helperForm.getProjectName()))
		{
			helperForm.setPiaName(helper.getPiaNameLst(helperForm).get(0).toString());
		} 
	    helperForm = new DistrictSummaryForm();
		piaNameLst = helper.getPiaNameLst(helperForm);
	    request.setAttribute("piaNameLst", piaNameLst);
	    projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		stateNameLst = helper.getStateNameLst(helperForm);
		request.setAttribute("stateNameLst", stateNameLst);
		return mapping.findForward("showDistrictSummaryPage");
	}
	/* ***********************************************To specify district names while selecting state name******************************/
	public ActionForward showDropDown2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		DistrictSummaryForm helperForm = (DistrictSummaryForm) form;
		if(helperForm.getStateName()!=null && !helperForm.getStateName().equals(""))
		{
			districtNameLst = helper.getDistrictName(helperForm.getStateName());
			request.setAttribute("districtNameLst", districtNameLst);
		}
		 piaNameLst = helper.getPiaNameLst(helperForm);
	     request.setAttribute("piaNameLst", piaNameLst);
	     projectNameLst = helper.getProjectNameLst(helperForm);
	 	 request.setAttribute("projectNameLst", projectNameLst);
	 	 stateNameLst = helper.getStateNameLst(helperForm);
     	 request.setAttribute("stateNameLst", stateNameLst);
         return mapping.findForward("showDistrictSummaryPage");
	}
	/*****************************************************RESET FUNCTION*******************************************/
	public ActionForward resetDropDownFilter(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		DistrictSummaryForm helperForm =   (DistrictSummaryForm) form;
		piaNameLst = helper.getPiaName();
        request.setAttribute("piaNameLst", piaNameLst);
        projectNameLst = helper.getProjectName();
 	    request.setAttribute("projectNameLst", projectNameLst);
	    stateNameLst = helper.getStateName();
        request.setAttribute("stateNameLst", stateNameLst);
        return mapping.findForward("showDistrictSummaryPage");
	}
	
}
