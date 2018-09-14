package com.infotech.sgsy.StateWiseCumYearWiseProgress.FirstReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class StateWiseProgressAction extends DispatchAction {

	public ActionForward showReport(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		/*List<String> list=new ArrayList<>();*/
		request.setAttribute("progressReportList", new StateWiseProgressDao().getStateWiseCumYearWiseProgressData());
	  
		
		return mapping.findForward("showReport");	
	} 
	
	
	public ActionForward showReport2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		/*List<String> list=new ArrayList<>();*/
		request.setAttribute("progressReportList2", new StateWiseProgressDao().getStateWiseCumMonthWiseProgressData());
	
		return mapping.findForward("showReport2");	
	
	}
	
	public ActionForward showReport3(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		/*List<String> list=new ArrayList<>();*/
		request.setAttribute("progressReportList3", new StateWiseProgressDao().getDistrictWiseProgressEachStateData());
	
		return mapping.findForward("showReport3");	
	
	   }

	
	public ActionForward showReport4(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		/*List<String> list=new ArrayList<>();*/
		request.setAttribute("progressReportList4", new StateWiseProgressDao().getStateWiseCategoryWiseAchievementData());
	
		return mapping.findForward("showReport4");	
	   
	   }
	
	
	public ActionForward showReport7(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		/*List<String> list=new ArrayList<>();*/
		request.setAttribute("progressReportList", new StateWiseProgressDao().getProjectWiseCategoryWiseAchievementData());
	
		return mapping.findForward("showReport7");	
	   
	   }
	
}
