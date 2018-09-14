package com.infotech.sgsy.state.monitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;

public class MonitorPpwsTrainAction extends DispatchAction {

	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private String[] monthsNo = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MonitorPpwsTrainActionForm helperForm = (MonitorPpwsTrainActionForm) form;
		MonitorPpwsTrainVO helperVo = new MonitorPpwsTrainVO();
		MonitorPpwsTrainDAO helperDao = new MonitorPpwsTrainDAO();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

		String forward = "showPage";

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); // Help for getting
														// Project list
		//System.out.println("...project list..." + assignProjectList);

		String currentmonth = months[month];
		request.setAttribute("currentyear", year);
		request.setAttribute("currentmonth", month);
		request.setAttribute("projectList", assignProjectList);

		return mapping.findForward(forward);
	}

	public ActionForward getTCList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MonitorPpwsTrainDAO helperDao = new MonitorPpwsTrainDAO();

		String prID = request.getParameter("prID");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String tcId = request.getParameter("tcId");

		//System.out.println("prID...11111" + prID);
	//	System.out.println("month...22222" + month);
	//	System.out.println("year...333333" + year);
	//	System.out.println("tcId....444444" + tcId);

		StringBuilder ss = new StringBuilder();
		int kk = 0;
		ss.append("[");
		
		List<TcSetupTradeActionVO> listTcTrade = (List<TcSetupTradeActionVO>) AbsFinder.getListByCondition(TcSetupTradeActionVO.class, new String[] { "trainningCenter.id", tcId });
		ArrayList<String> ids = new ArrayList<String>();
		for (TcSetupTradeActionVO tctradevo : listTcTrade) {
			ids.add(tctradevo.getId());
			System.out.println("trade ids----->   " + tctradevo.getId());
		}

		List<BatchCreationVO> batchCreationVOs = (List<BatchCreationVO>) AbsFinder.getListByProjectIds(BatchCreationVO.class, "tcID.id", ids);
		
		for (int j = 0; j < batchCreationVOs.size(); j++) {
			MonitorPpwsTrainVO monitoringVo = helperDao.getdetailByBatchId(batchCreationVOs.get(j).getId(), month, year);
			if (kk > 0) {
				ss.append(",");
			}
			if (monitoringVo == null) {
				monitoringVo = new MonitorPpwsTrainVO();
				BatchCreationVO batch = new BatchCreationVO();
				batch.setId(batchCreationVOs.get(j).getId());
				batch.setBatchID(batchCreationVOs.get(j).getBatchID());

				monitoringVo.setBatchId(batch);
			}
			ss.append(monitoringVo.toString());
			kk++;
		}
		ss.append("]");

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(ss.toString());
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "showPage";
		MonitorPpwsTrainDAO helperDao = new MonitorPpwsTrainDAO();
		MonitorPpwsTrainActionForm helperForm = (MonitorPpwsTrainActionForm) form;
		MonitorPpwsTrainVO helperVo = new MonitorPpwsTrainVO();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

		try {
			String year = helperForm.getYear();
			String month = helperForm.getMonth();
			String userId = loginVO.getUserid();

			helperDao.save(helperForm, year, month, userId);
			
			List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); // Help for getting
			request.setAttribute("projectList", assignProjectList);
	
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	public ActionForward getValDone(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MonitorPpwsTrainDAO helperDao = new MonitorPpwsTrainDAO();

		//String projectId = request.getParameter("proj");
		String monthss = request.getParameter("month");
		String year = request.getParameter("year");
		String tcId = request.getParameter("train");

		 System.out.println("...all val send ..."+monthss+"..year..."+year+"tcId..."+tcId);
		 
		String month = getPreviousMonth(monthss);
		
       System.out.println("...last month..."+month);
       
		List<MonitorPpwsTrainVO> totalduration = helperDao.getChecked( month, year, tcId);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(String.valueOf(totalduration));
		return null;
	}

	private String getPreviousMonth(String month) {
		int index = 0;
		for (int i = 0; i < months.length; i++) {
			if (month.equalsIgnoreCase(monthsNo[i])) {
				index = i;
				break;
			}
		}
		index = index - 1;
		if (index == 0) {
			index = 12;
		}
		return monthsNo[index];
	}

	public ActionForward getListOfTCId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MonitorPpwsTrainDAO helperDao = new MonitorPpwsTrainDAO();
		
		List<TcSetupVO> tcList = (List<TcSetupVO>) AbsFinder.getDropDownListByCondotion(TcSetupVO.class,new String[] { "id", "tcID" }, new String[] { "project.id", request.getParameter("projectid") });

		StringBuffer disst = new StringBuffer();
		disst.append("<option value='0'> --SELECT-- </option>");
		if (tcList.size() > 0) {
			for (int i = 0; i < tcList.size(); i++) {
				disst.append("<option value='" + tcList.get(i).getId() + "'>" + tcList.get(i).getTcID() + "</option>");
			}
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");

		response.getWriter().write(disst.toString());

		return null;
	}
}
