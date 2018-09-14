package com.infotech.skills.mpr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;



public class MPRAction extends DispatchAction {

	
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,		
			HttpServletRequest request, HttpServletResponse response)		
	throws Exception {
	
		return mapping.findForward("addMprPage");
	}
}
