package com.infotech.skills.proposal.curriculam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.skills.master.MasterDao;
import com.infotech.skills.master.MasterDaoImpl;
import com.infotech.skills.util.Constants;
import com.infotech.sgsy.common.LocationVO;



public class CurriculamAction extends DispatchAction {
	

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();
	 
	 public ActionForward showPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	 	throws Exception{
		 log.info("inside show page at CurriculamAction page");
		 return mapping.findForward("showPage");
		 
	 }

	
}