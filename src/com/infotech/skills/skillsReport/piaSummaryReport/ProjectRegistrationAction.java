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

public class ProjectRegistrationAction extends DispatchAction {
	List piaNameLst = null;
	List projectNameLst = null;
	List stateNameLst = null;
	List projectSummayLst = null;

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	ProjectRegistrationDaoImpl helper = new ProjectRegistrationDaoImpl();
	protected final Log log = LogFactory.getLog(getClass());

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProjectRegistrationForm helperForm = (ProjectRegistrationForm) form;
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		projectSummayLst = helper.getProjectSummary(helperForm);
		request.setAttribute("projectSummayLst", projectSummayLst);
		return mapping.findForward("showProjectRegistrationPage");
	}

	public ActionForward showDropDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProjectRegistrationForm helperForm = (ProjectRegistrationForm) form;

		if (helperForm.getPiaName() != null
				&& !helperForm.getPiaName().equals("")) {
			projectNameLst = helper.getProjectNameLst(helperForm);
			request.setAttribute("projectNameLst", projectNameLst);
		}
		if (helperForm.getPiaName() == null
				|| helperForm.getPiaName().equals("")) {
			piaNameLst = helper.getPiaName();
			request.setAttribute("piaNameLst", piaNameLst);
			projectNameLst = helper.getProjectName();
			request.setAttribute("projectNameLst", projectNameLst);
		}
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		return mapping.findForward("showProjectRegistrationPage");
	}

	public ActionForward showDropDown1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProjectRegistrationForm helperForm = (ProjectRegistrationForm) form;

		if (helperForm.getProjectName() != null
				|| !"".equals(helperForm.getProjectName())) {
			helperForm.setPiaName(helper.getPiaNameLst(helperForm).get(0)
					.toString());
		}
		helperForm = new ProjectRegistrationForm();
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		return mapping.findForward("showProjectRegistrationPage");
	}

	public ActionForward showPage1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProjectRegistrationForm helperForm = (ProjectRegistrationForm) form;
		piaNameLst = helper.getPiaNameLst(helperForm);
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectNameLst(helperForm);
		request.setAttribute("projectNameLst", projectNameLst);
		projectSummayLst = helper.getProjectSummary(helperForm);
		request.setAttribute("projectSummayLst", projectSummayLst);
		return mapping.findForward("showProjectRegistrationPage");
	}

	public ActionForward resetDropDownFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProjectRegistrationForm helperForm = (ProjectRegistrationForm) form;
		piaNameLst = helper.getPiaName();
		request.setAttribute("piaNameLst", piaNameLst);
		projectNameLst = helper.getProjectName();
		request.setAttribute("projectNameLst", projectNameLst);
		return mapping.findForward("showProjectRegistrationPage");
	}
}