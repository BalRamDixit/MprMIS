package com.infotech.sgsy.state.monitor;

import java.util.ArrayList;
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

public class MonitorPpwsPlaceAction extends DispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		MonitorPpwsPlaceDAO helperDao = new MonitorPpwsPlaceDAO ();
		MonitorPpwsPlaceVO helperVo = new MonitorPpwsPlaceVO();
		MonitorPpwsPlaceActionForm helperForm = (MonitorPpwsPlaceActionForm) form;
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");	
		
		String forward= "showPage";
		
		List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); // Help for getting
		request.setAttribute("projectList", assignProjectList);
		
	return mapping.findForward(forward);
	}
		
	//...for validation work..........
	 public ActionForward getValDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {   
		 
		 MonitorPpwsPlaceDAO helperDao = new MonitorPpwsPlaceDAO ();
	    	String trainingId=request.getParameter("train"); 
			System.out.println("trainingId..."+trainingId);	
			
	    List<TcSetupTradeActionVO> listTcTrade = (List<TcSetupTradeActionVO>) AbsFinder.getListByCondition(TcSetupTradeActionVO.class, new String[] { "trainningCenter.id", trainingId });
		ArrayList<String> ids = new ArrayList<String>();
		for (TcSetupTradeActionVO tctradevo : listTcTrade) {
			ids.add(tctradevo.getId());
		}
		
		List<BatchCreationVO> batchCreationVO =(List<BatchCreationVO>)AbsFinder.getListByProjectIds(BatchCreationVO.class, "tcID.id", ids);

		StringBuilder sskk=new StringBuilder();
		   int m=0;
		   sskk.append("[");
		 	   for(int l=0;l<batchCreationVO.size();l++){
				 
				   MonitorPpwsTrainVO instancesOfPPwsNew = helperDao.getListOfPPWS1((String) batchCreationVO.get(l).getId(),trainingId) ; 
				   if(m>0){
					   sskk.append(",");  
				   }
				   if(instancesOfPPwsNew!=null){
					   sskk.append("{\"batchId\":\""+instancesOfPPwsNew.getBatchId().getId()+"\",\"commencedTotal\":\""+instancesOfPPwsNew.getCommenced_Total()+"\"}");
				   }
				   m++;
				 }
		     sskk.append("]");
		    
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(sskk.toString());
		    return null;
	    }

		//...for getting batch Id...
	public ActionForward getTCList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			  
		MonitorPpwsPlaceDAO helperDao = new MonitorPpwsPlaceDAO ();
            String prID= request.getParameter("prID");
			String tcId=request.getParameter("tcId");
			
			StringBuilder ss = new StringBuilder();
			int kk = 0;
			ss.append("[");
			
			List<TcSetupTradeActionVO> listTcTrade = (List<TcSetupTradeActionVO>) AbsFinder.getListByCondition(TcSetupTradeActionVO.class, new String[] { "trainningCenter.id", tcId });
			ArrayList<String> ids = new ArrayList<String>();
			for (TcSetupTradeActionVO tctradevo : listTcTrade) {
				ids.add(tctradevo.getId());
				}

			List<BatchCreationVO> batchCreationVOs = (List<BatchCreationVO>) AbsFinder.getListByProjectIds(BatchCreationVO.class, "tcID.id", ids);
			
			for (int j = 0; j < batchCreationVOs.size(); j++) {
				MonitorPpwsPlaceVO monitoringVo = helperDao.getdetailByBatchId(batchCreationVOs.get(j).getId());
				if (kk > 0) {
					ss.append(",");
				}
				if (monitoringVo == null) {
					monitoringVo = new MonitorPpwsPlaceVO();
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
		
		 private MonitorPpwsTrainVO MonitorPpwsTrainVO() {
			// TODO Auto-generated method stub
			return null;
		}

		private Object sskk(int k1) {
			// TODO Auto-generated method stub
			return null;
		}

		public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 
				String forward= "showPage";		
				MonitorPpwsPlaceDAO helperDao = new MonitorPpwsPlaceDAO ();
				MonitorPpwsPlaceVO helperVo = new MonitorPpwsPlaceVO();
				MonitorPpwsPlaceActionForm helperForm = (MonitorPpwsPlaceActionForm) form;
				LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	       	
		 try {
		        String userId=loginVO.getUserid();
		    	helperDao.save(helperForm,userId);
		     	
		    	List<ProjectDetailsVO> assignProjectList = (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); // Help for getting
				request.setAttribute("projectList", assignProjectList);
		     }
		     catch (Exception e) {
		    	  e.printStackTrace();
		     }
		  return mapping.findForward(forward);
	}
		
		public ActionForward getListOfTCId(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
			MonitorPpwsTrainDAO helperDao = new MonitorPpwsTrainDAO();
			String projectID=request.getParameter("projectid") ;
			
			List<TcSetupVO> tcList = (List<TcSetupVO>) AbsFinder.getListByCondition(TcSetupVO.class, new String[] { "project.id", projectID});

			StringBuffer disst = new StringBuffer();
			disst.append("<option value='0'> --select-- </option>");
			if (tcList.size() > 0) {
				for (int i = 0; i < tcList.size(); i++) {
					disst.append("<option value='" + tcList.get(i).getId() + "'>"+tcList.get(i).getTcID() + "-" + tcList.get(i).getDistrict().getDistrictName()+" </option>");
				}
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(disst.toString());

			
			return null;
		}
	
	
}
