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

import com.infotech.skills.skillsReport.piaReport.PiaReportForm;

public class TargetSummaryAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());

	List piaNameLst = null;
	List pia_list = null;
	List projectNameLst = null;
	List targetSummayLst = null;
	TargetSummaryDaoImpl helper = new TargetSummaryDaoImpl();

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TargetSummaryForm helperForm = (TargetSummaryForm) form;

		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);

		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);

		targetSummayLst = helper.getTragetSummary(helperForm);
		request.setAttribute("targetSummayLst", targetSummayLst);

		return mapping.findForward("showTargetPage");
	}

	public ActionForward resetFilters(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TargetSummaryForm helperForm = (TargetSummaryForm) form;

		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);

		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);

		targetSummayLst = helper.getTragetSummary(helperForm);
		request.setAttribute("targetSummayLst", targetSummayLst);

		return mapping.findForward("showTargetPage");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDropDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		TargetSummaryForm helperForm = (TargetSummaryForm) form;

		
		if (helperForm.getPiaName()!= null || !"".equals(helperForm.getPiaName())) 
		{
			
			projectNameLst = helper.getProjectNameLst(helperForm);
            request.setAttribute("projectNameLst", projectNameLst);
      }

		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);

		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);

		return mapping.findForward("showTargetPage");
	}

	public ActionForward showDropDown1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		TargetSummaryForm helperForm = (TargetSummaryForm) form;
		if(helperForm.getProjectName()!= null || !"".equals(helperForm.getProjectName()))
		{
			helperForm.setPiaName(helper.getPiaNameLst(helperForm).get(0).toString());
		} 
	    helperForm = new TargetSummaryForm();
		piaNameLst = helper.getPiaNameLst(helperForm);
	    request.setAttribute("piaNameLst", piaNameLst);
	    projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		return mapping.findForward("showTargetPage");
}
}