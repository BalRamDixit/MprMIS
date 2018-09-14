package com.infotech.sgsy.userlog; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
public class UserLog extends DispatchAction{
	
	
	public ActionForward showReportPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		 	
		UserForm frm  =(UserForm)form;
		frm.reset();
		return mapping.findForward("showReportPage");
	}

 
	public ActionForward showReportResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		UserForm frm  =(UserForm)form;
		req.setAttribute("LOGLIST",  new UserLogDAO().search(frm.getUsername(),frm.getFromDate(),frm.getToDate() ));
		 
		return mapping.findForward("showReportPage");
	}

}
