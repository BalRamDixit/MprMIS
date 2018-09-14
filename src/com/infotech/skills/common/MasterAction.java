package com.infotech.skills.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


/**
 * @since August 2013
 * @author mehra
 *
 */
public  abstract class MasterAction extends DispatchAction {
	
	public abstract ActionForward showAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public abstract ActionForward showView(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public abstract ActionForward showDelete(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public abstract ActionForward showModify(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public abstract ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public abstract ActionForward save(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public abstract ActionForward modify(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}