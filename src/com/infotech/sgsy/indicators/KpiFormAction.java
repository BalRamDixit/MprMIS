package com.infotech.sgsy.indicators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class KpiFormAction extends DispatchAction{
	
	 public ActionForward overAllIndicator(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
     {
		 KPIIndicatorForm helperForm=(KPIIndicatorForm)form;
	  
		 helperForm.setStateName("ANDHRA PRADESH"); 
        return mapping.findForward("indicator");
     }

}
