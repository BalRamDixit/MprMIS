package com.infotech.sgsy.monitoringsystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.monitoringalerts.MonitoringAlertDAO;
import com.infotech.sgsy.monitoringalerts.MonitoringAlertForm;
import com.infotech.sgsy.monitoringalerts.MonitoringAlertVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;

public class MonitoringInspectionAction extends DispatchAction {
	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	MonitoringInspectionDAO monitoringInspectionDAO = new MonitoringInspectionDAO();

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
		List<ProjectDetailsVO> sanctionProjectList = new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
		request.setAttribute("projectList", sanctionProjectList);
		return mapping.findForward("showMonitoringInspection");
	}

	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
			
		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
		
		request.setAttribute("projectList",sanctionProjectList);	 		 
		return mapping.findForward("MonitoringInspection");
	}  
	
	
	
	public ActionForward getDateDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String TrainingId = request.getParameter("TrainingId");
		StringBuffer sb = new StringBuffer();
		try {
			int kk = 0;
			List<TcSetupTradeActionVO> listTcTrade=(List<TcSetupTradeActionVO>) AbsFinder.getListByCondition(TcSetupTradeActionVO.class,new String[]{"trainningCenter.id",TrainingId});
			ArrayList<String> ids=new ArrayList<String>();
			for(TcSetupTradeActionVO tctradevo:listTcTrade){
				ids.add(tctradevo.getId());
			}
			if(ids.size()>0){
				sb.append("[");
			List<BatchCreationVO> batchCreationVOs=(List<BatchCreationVO>)AbsFinder.getListByProjectIds(BatchCreationVO.class,"tcID.id",ids);
			for (int j = 0; j < batchCreationVOs.size(); j++) {
					MonitoringInspectionVO monitoringVo = (MonitoringInspectionVO) monitoringInspectionDAO.getBatchList(batchCreationVOs.get(j).getId());
					if (kk > 0) {
						sb.append(",");
					}
					if (monitoringVo != null) {
						sb.append(monitoringVo.toString());
					}
					kk++;
				}
			sb.append("]");
		}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(sb.toString());
		return null;
		
	}

	
	
	public ActionForward getBatchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String TrainingId = request.getParameter("TrainingId");
		/*String month = request.getParameter("month");
		String year = request.getParameter("year");*/

		StringBuffer sb = new StringBuffer();
		/*Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int currentyear = cal.get(Calendar.YEAR);
		int currentmonth = cal.get(Calendar.MONTH);*/
		
		try {
			int kk = 0;
			sb.append("[");
			List<TcSetupTradeActionVO> listTcTrade=(List<TcSetupTradeActionVO>) AbsFinder.getListByCondition(TcSetupTradeActionVO.class,new String[]{"trainningCenter.id",TrainingId});
			ArrayList<String> ids=new ArrayList<String>();
			for(TcSetupTradeActionVO tctradevo:listTcTrade){
				ids.add(tctradevo.getId());
			}
			
			List<BatchCreationVO> batchCreationVOs=(List<BatchCreationVO>)AbsFinder.getListByProjectIds(BatchCreationVO.class,"tcID.id" , ids);
			for (int j = 0; j < batchCreationVOs.size(); j++) {
				MonitoringInspectionVO monitoringVo = monitoringInspectionDAO.getdetailByBatchId(batchCreationVOs.get(j).getId());
				if (kk > 0) {
					sb.append(",");
				}
				if (monitoringVo == null) {
					monitoringVo = new MonitoringInspectionVO();
					BatchCreationVO batch = new BatchCreationVO();
					batch.setId(batchCreationVOs.get(j).getId());
					batch.setBatchID(batchCreationVOs.get(j).getBatchID());
					
					monitoringVo.setBatchId(batch);
					sb.append(monitoringVo.toString());
				}
				sb.append(monitoringVo.toString());
				kk++;
			}
			sb.append("]");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		/*
		int refForReadOnly = 0;
		if (month.equals(months[currentmonth]) && year.equals(String.valueOf(currentyear))) {
			refForReadOnly = 1;
		}*/
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(sb.toString() /*+ "<--->" + refForReadOnly*/);
		return null;
		
	}

	public ActionForward getTrain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TcSetupVO> list=(List<TcSetupVO>)AbsFinder.getListByCondition(TcSetupVO.class, new String[]{"project.id",request.getParameter("prId")});
		StringBuffer tradedd = new StringBuffer();
		if (list.size() > 0) {
			tradedd.append("<option value='0'>--SELECT--</option>");
			for (int i = 0; i < list.size(); i++) {
			
				tradedd.append("<option value='" +list.get(i).getId()+ "'>" +list.get(i).getTcID() +"-"+list.get(i).getDistrict().getDistrictName() +"</option>");
			}
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		if (list != null && list.size() >= 1) {
			response.getWriter().write(tradedd.toString());
		} else {
			response.getWriter().write("0");
		}
		return null;
	}

	public ActionForward editDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		String id = request.getParameter("id");
		MonitoringInspectionVO vo=(MonitoringInspectionVO) MonitoringInspectionDAO.getById(MonitoringInspectionVO.class, id);
		request.setAttribute("Bean",vo);	
		MonitoringInspectionForm helperForm = (MonitoringInspectionForm) form;
		List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>)request.getSession().getAttribute("assignProjectDetails");
		List<ProjectDetailsVO> sanctionProjectList = new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
		request.setAttribute("projectList", sanctionProjectList);
		request.setAttribute("TrainingList", monitoringInspectionDAO.getTrainId(helperForm.getPrId()));
		return mapping.findForward("MonitoringInspection");
	}	
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MonitoringInspectionForm helperForm = (MonitoringInspectionForm) form;
		List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>)request.getSession().getAttribute("assignProjectDetails");
		List<ProjectDetailsVO> sanctionProjectList = new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
		
		request.setAttribute("projectList", sanctionProjectList);
		request.setAttribute("TrainingList", monitoringInspectionDAO.getTrainId(helperForm.getPrId()));
		
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		try {
			monitoringInspectionDAO.saveorupdate(helperForm,loginVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showMonitoringInspection");
	}
	
	
}
