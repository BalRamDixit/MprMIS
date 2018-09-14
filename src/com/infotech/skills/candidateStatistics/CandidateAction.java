package com.infotech.skills.candidateStatistics;

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

import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetDAO;
import com.infotech.sgsy.state.monitor.MonitorPpwsTrainVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;

public class CandidateAction extends DispatchAction {
private String[] months=new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CandidateDAO candidateDAO=new CandidateDAO();
		Date date=new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int monthForPpws=month+1;
		//LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		List<ProjectDetailsVO> projectlist=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		
		List<CandidateVO> canlist=new ArrayList<CandidateVO>(); 
		for(int i=0;i<projectlist.size();i++){
			String projectId=projectlist.get(i).getId();
			CandidateVO  candidateStat=candidateDAO.getCandidate_Stat_by_ProjectId(projectId,months[month],year);
			
			if(candidateStat==null){
				candidateStat=new CandidateVO();
				candidateStat.setId("0");
				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
				projectDetailsVO.setId(projectId);
				projectDetailsVO.setProjectID(projectlist.get(i).getProjectID());
				candidateStat.setProject(projectDetailsVO);
				List<TcSetupVO> setupVO=(List<TcSetupVO>) AbsFinder.getListByCondition(TcSetupVO.class, new String[]{"project.id",projectId});
				ArrayList<String> tcids=new ArrayList<String>();
				for(TcSetupVO vo:setupVO){
					System.out.println("tc ids-->  "+vo.getTcID() );
					tcids.add(vo.getId());
				}
				int abc=0;
				try{
				MonitorPpwsTrainVO MonitorPpwsTrainVO=(MonitorPpwsTrainVO) candidateDAO.get(tcids, year+"", monthForPpws+"");
			    System.out.println("summmm--->...  "+MonitorPpwsTrainVO.getCommenced_Total()+"---------"+MonitorPpwsTrainVO.getCompleted_Total());
			    abc=MonitorPpwsTrainVO.getCommenced_Total()+MonitorPpwsTrainVO.getCompleted_Total();
				}catch(Exception e){
					System.out.println("error-- i candidate");
				}
			  
				//System.out.println("haha ----->   "+abc);
				candidateStat.setTotal_no_of_can(abc);
			}
			canlist.add(candidateStat);
		}
        String currentmonth=months[month];
		request.setAttribute("currentyear",year);
		request.setAttribute("candidateList", canlist);
	
		request.setAttribute("currentmonth",currentmonth);
		return mapping.findForward("showPage"); 
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		CandidateForm candidateForm = (CandidateForm) form; 
		CandidateDAO candidateDAO=new CandidateDAO();
		String forward= "redirectToShow";

 	try {
 		Date date=new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); 
     	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
     	candidateDAO.SaveOrUpdate(candidateForm,months[month],year,loginVO.getUserid());	
		candidateForm.reset(mapping, request);
      }   catch (Exception e) {
    	  e.printStackTrace();
     }
	return mapping.findForward(forward);
  }
	
	public ActionForward getDeatils(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CandidateDAO candidateDAO=new CandidateDAO();
		//List<CandidateVO> canlist=new ArrayList<CandidateVO>(); 
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		//String entity_code = loginVO.getEntityCode();
		List<ProjectDetailsVO> projectlist=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));

	
		String month=request.getParameter("month");
		int year=Integer.parseInt(request.getParameter("year"));
		StringBuilder json=new StringBuilder();
		json.append("[");
		for(int i=0;i<projectlist.size();i++){
			String projectId=projectlist.get(i).getId();
			CandidateVO  candidateStat=candidateDAO.getCandidate_Stat_by_ProjectId(projectId,month,year);
			
			if(candidateStat!=null){
			if(i>0){
				json.append(",");
			}
			json.append(candidateStat.toString());	
				
			}
			
		}
		
		json.append("]");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(json.toString());
		
		return null;
	}
		
}
