package com.infotech.sgsy.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
 

public class ApplicationLevelExceptionAction extends DispatchAction {
	
	

	public ActionForward getApplicationException(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SystemFailureException, ConnectionFailedException,Exception{
		log.info("getApplicationException method Starts======>");
		 
			return mapping.findForward("applicationLevelException");
	}
	

}
