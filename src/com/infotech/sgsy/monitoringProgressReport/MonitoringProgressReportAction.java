package com.infotech.sgsy.monitoringProgressReport;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MonitoringProgressReportAction extends DispatchAction{
	
public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception { 	
     
//	MonitoringProgressReportDAO monitoringProgressReportDAO = new MonitoringProgressReportDAO();
	
//	List<MonitoringProgressReportForm> details=new MonitoringProgressReportDAO().getFieldData();
	Map<String, Map<String,MonitoringProgressReportForm>> details=new MonitoringProgressReportDAO().getFieldDataWithHshMap();
	request.setAttribute("formBean", details);
	
	
	return mapping.findForward("showPage");

	}

}
