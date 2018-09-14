package com.infotech.sgsy.state.monitor;

import java.util.ArrayList;
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
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;


public class MonitorFirstInstAction extends DispatchAction {	

	MonitorFirstInstDAO helperDao = new MonitorFirstInstDAO();
	DateUtil dateHelper	= new DateUtil();
	
	/*First Installment Starts ------******************  Varun *********************************----------*/	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		MonitorFirstInstForm helperForm=(MonitorFirstInstForm)form;
		List<MonitorFirstInstVO> ll=new ArrayList<MonitorFirstInstVO>();
		List<ProjectDetailsVO> projectDetailslist = new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		for(int i=0;i<projectDetailslist.size();i++){
			String id=projectDetailslist.get(i).getId();
			SanctionDetailVO sanctionDetailVO=(SanctionDetailVO) AbsFinder.getdetailByCondition(SanctionDetailVO.class, new String[]{"projectId.id",id});
			
			String installNo="1";
			if(sanctionDetailVO!=null){
				MonitorFirstInstVO mm=helperDao.getPiaProjectsDetails(id,installNo);
				 if(mm==null){
					mm=new MonitorFirstInstVO();
				 }
				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
				projectDetailsVO.setId(id);
				projectDetailsVO.setProjectID(projectDetailslist.get(i).getProjectID());
				mm.setProjectId(projectDetailsVO);	
				mm.setRelAmountHidden(String.valueOf(projectDetailslist.get(i).getTotalProjectCost()));
				mm.setReldateHidden(sanctionDetailVO.getSanctionDate()+"");
				ll.add(mm);
			}
		}
		System.out.println(ll);
		request.setAttribute("mm", ll);
		
		helperForm.reset(mapping,request);
		String forward="showPage1";
		request.setAttribute("NOTIFICATION", "Please Click on continue for Second Installment");		 
	    return mapping.findForward(forward);
		
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
     	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
     	MonitorFirstInstForm helperForm = (MonitorFirstInstForm) form;
		try {
			helperDao.save(helperForm,loginVO);
		}  
		catch (Exception e)
		{
    	  e.printStackTrace();
		}
    return mapping.findForward("redirectTofirst"); 

	}
	
	
	/*First Installment Ends------------------------------------------------------------------------------------------------------------*/

	/*Second Installment Starts------------------------------------------------------------>*/
	//----------------har har mahadev-------------
	public ActionForward show2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		MonitorFirstInstForm helperForm=(MonitorFirstInstForm)form;
	
		List<MonitorFirstInstVO> ll=new ArrayList<MonitorFirstInstVO>();
		List<ProjectDetailsVO> projectDetailslist = new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO pr:projectDetailslist){
			ids.add(pr.getId());
		}
			List<MonitorFirstInstVO> firstInstallment=helperDao.getPiaProjectsDetailsList(ids,"1");
			List<MonitorFirstInstVO> secondInstallment=helperDao.getPiaProjectsDetailsList(ids,"2");
			List<MonitorFirstInstVO> firstInstallmentnew=new ArrayList<MonitorFirstInstVO>();
			for(MonitorFirstInstVO second:secondInstallment){
				for(MonitorFirstInstVO first:firstInstallment){
					if(second.getProjectId().getId().equalsIgnoreCase(first.getProjectId().getId())){
						
						firstInstallmentnew.add(first);
					}
				}
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(second.getProjectId().getId());
				project.setProjectID(second.getProjectId().getProjectID()+" ("+second.getProjectId().getState().getStateName()+") - "+second.getProjectId().getPiaPrn().getUserName());
				project.setTotalProjectCost(second.getProjectId().getTotalProjectCost());
				second.setProjectId(project);
			}
			firstInstallment.removeAll(firstInstallmentnew);
			ll.addAll(secondInstallment);
			for(MonitorFirstInstVO first:firstInstallment){
				MonitorFirstInstVO fi=new MonitorFirstInstVO();
				fi.setId("0");
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(first.getProjectId().getId());
				project.setProjectID(first.getProjectId().getProjectID()+" ("+first.getProjectId().getState().getStateName()+") - "+first.getProjectId().getPiaPrn().getUserName());
				project.setTotalProjectCost(first.getProjectId().getTotalProjectCost());
				fi.setProjectId(project);
				ll.add(fi);
			}
	      	request.setAttribute("mm", ll);
		    helperForm.reset(mapping,request);
		    request.setAttribute("NOTIFICATION", "Please Click on continue for Third Installment");
		    String forward="showPage2";
				 
	    return mapping.findForward(forward);
		}
	
    public ActionForward save2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
     	MonitorFirstInstForm helperForm =(MonitorFirstInstForm) form;
     	
		try {
			
			helperDao.save2(helperForm,loginVO);
			 
	
			}  
       catch (Exception e)
       {
    	  e.printStackTrace();
       }
     
    return mapping.findForward("redirectToSecond");
	}
	
	/* ----------------------------------    Third Installment Starts   ---------------------------------------------->*/
	public ActionForward show3(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		MonitorFirstInstForm helperForm=(MonitorFirstInstForm)form;
	
		//request.setAttribute("projectList", helperDao.getPiaListForProjectIdEcodeForThird(loginVO.getEntityCode()));
		
		
		/*List<MonitorFirstInstVO> ll=new ArrayList<MonitorFirstInstVO>();
		List<ProjectDetailsVO> projectDetailslist = new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		

		for(int i=0;i<projectDetailslist.size();i++){
			String projectId=projectDetailslist.get(i).getProjectId();
			String id=projectDetailslist.get(i).getId();
			SanctionDetailVO sanctionDetailVO=(SanctionDetailVO) AbsFinder.getdetailByCondition(SanctionDetailVO.class, new String[]{"projectId.id",id});
			
			String installNo="2";
			
			MonitorFirstInstVO mm=helperDao.getPiaProjectsDetails(id,installNo);

				
			if(mm==null){
				mm=new MonitorFirstInstVO();
				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
				projectDetailsVO.setId(id);
				projectDetailsVO.setProjectID(projectDetailslist.get(i).getProjectID());
				mm.setProjectId(projectDetailsVO);
			}
			mm.setRelAmountHidden(String.valueOf(projectDetailslist.get(i).getTotalProjectCost()));
			mm.setReldateHidden(sanctionDetailVO.getSanctionDate()+"");
			ll.add(mm);
		}
		request.setAttribute("mm", ll);*/
		
		List<MonitorFirstInstVO> ll=new ArrayList<MonitorFirstInstVO>();
		List<ProjectDetailsVO> projectDetailslist = new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO pr:projectDetailslist){
			ids.add(pr.getId());
		}
			List<MonitorFirstInstVO> firstInstallment=helperDao.getPiaProjectsDetailsList(ids,"2");
			List<MonitorFirstInstVO> secondInstallment=helperDao.getPiaProjectsDetailsList(ids,"3");
			List<MonitorFirstInstVO> firstInstallmentnew=new ArrayList<MonitorFirstInstVO>();
			for(MonitorFirstInstVO second:secondInstallment){
				for(MonitorFirstInstVO first:firstInstallment){
					if(second.getProjectId().getId().equalsIgnoreCase(first.getProjectId().getId())){
						firstInstallmentnew.add(first);
						}
					}
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(second.getProjectId().getId());
				project.setProjectID(second.getProjectId().getProjectID()+" ("+second.getProjectId().getState().getStateName()+") - "+second.getProjectId().getPiaPrn().getUserName());
				project.setTotalProjectCost(second.getProjectId().getTotalProjectCost());
				second.setProjectId(project);
			}
			firstInstallment.removeAll(firstInstallmentnew);
			ll.addAll(secondInstallment);
			for(MonitorFirstInstVO first:firstInstallment){
				MonitorFirstInstVO fi=new MonitorFirstInstVO();
				fi.setId("0");
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(first.getProjectId().getId());
				project.setProjectID(first.getProjectId().getProjectID()+" ("+first.getProjectId().getState().getStateName()+") - "+first.getProjectId().getPiaPrn().getUserName());
				project.setTotalProjectCost(first.getProjectId().getTotalProjectCost());
				fi.setProjectId(project);
				ll.add(fi);
			}
	      	request.setAttribute("mm", ll);
		
		helperForm.reset(mapping,request);
		
		request.setAttribute("NOTIFICATION", "Please Click on continue for Fourth Installment");
		String forward="showPage3";
				 
	    return mapping.findForward(forward);
		
	}
	
public ActionForward save3(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
     	MonitorFirstInstForm helperForm =(MonitorFirstInstForm) form;
     	
		try {
			
			helperDao.save3(helperForm,loginVO);
			 
	
			}  
       catch (Exception e)
       {
    	  e.printStackTrace();
       }
     
    return mapping.findForward("redirectToThird");
	}
	
	/*-------------------------     Fourth Installment Start   ------------------------------------------------>*/
	public ActionForward show4(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");		
		MonitorFirstInstForm helperForm=(MonitorFirstInstForm)form;
	
		//request.setAttribute("projectList", helperDao.getPiaListForProjectIdEcodeForForth(loginVO.getEntityCode()));
		
		/*List<MonitorFirstInstVO> ll=new ArrayList<MonitorFirstInstVO>();
		List<ProjectDetailsVO> projectDetailslist = new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		
				
		for(int i=0;i<projectDetailslist.size();i++){
			String projectId=projectDetailslist.get(i).getProjectId();
			String id=projectDetailslist.get(i).getId();
			SanctionDetailVO sanctionDetailVO=(SanctionDetailVO) AbsFinder.getdetailByCondition(SanctionDetailVO.class, new String[]{"projectId.id",id});
			
			
			String installNo="4";
			
			MonitorFirstInstVO mm=helperDao.getPiaProjectsDetails(id,installNo);
			
			
			if(mm==null){
				mm=new MonitorFirstInstVO();
				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
				projectDetailsVO.setId(id);
				projectDetailsVO.setProjectID(projectDetailslist.get(i).getProjectID());
				mm.setProjectId(projectDetailsVO);
			}
			mm.setRelAmountHidden(String.valueOf(projectDetailslist.get(i).getTotalProjectCost()));
			mm.setReldateHidden(sanctionDetailVO.getSanctionDate()+"");
			ll.add(mm);
		}
		request.setAttribute("mm", ll);*/
		
		List<MonitorFirstInstVO> ll=new ArrayList<MonitorFirstInstVO>();
		List<ProjectDetailsVO> projectDetailslist = new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO pr:projectDetailslist){
			ids.add(pr.getId());
		}
		List<MonitorFirstInstVO> firstInstallment=helperDao.getPiaProjectsDetailsList(ids,"3");
		List<MonitorFirstInstVO> secondInstallment=helperDao.getPiaProjectsDetailsList(ids,"4");
		List<MonitorFirstInstVO> firstInstallmentnew=new ArrayList<MonitorFirstInstVO>();
		for(MonitorFirstInstVO second:secondInstallment){
		for(MonitorFirstInstVO first:firstInstallment){
			if(second.getProjectId().getId().equalsIgnoreCase(first.getProjectId().getId())){
				firstInstallmentnew.add(first);
				}
			}
			ProjectDetailsVO project=new ProjectDetailsVO();
			project.setId(second.getProjectId().getId());
			project.setProjectID(second.getProjectId().getProjectID()+" ("+second.getProjectId().getState().getStateName()+") - "+second.getProjectId().getPiaPrn().getUserName());
			project.setTotalProjectCost(second.getProjectId().getTotalProjectCost());
			second.setProjectId(project);
		}
		firstInstallment.removeAll(firstInstallmentnew);
		ll.addAll(secondInstallment);
		for(MonitorFirstInstVO first:firstInstallment){
			MonitorFirstInstVO fi=new MonitorFirstInstVO();
			fi.setId("0");
			ProjectDetailsVO project=new ProjectDetailsVO();
			project.setId(first.getProjectId().getId());
			project.setProjectID(first.getProjectId().getProjectID()+" ("+first.getProjectId().getState().getStateName()+") - "+first.getProjectId().getPiaPrn().getUserName());
			project.setTotalProjectCost(first.getProjectId().getTotalProjectCost());
			fi.setProjectId(project);
			ll.add(fi);
		}
      	request.setAttribute("mm", ll);
		
		helperForm.reset(mapping,request);
		
		request.setAttribute("NOTIFICATION", "Fourth Installment Form is saved successfully.");
		String forward="showPage4";
				 
	    return mapping.findForward(forward);
		
	}

public ActionForward save4(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
     	MonitorFirstInstForm helperForm =(MonitorFirstInstForm) form;
     	
		try {
			
			helperDao.save4(helperForm,loginVO);
			 
	
			}  
       catch (Exception e)
       {
    	  e.printStackTrace();
       }
     
    return mapping.findForward("redirectToFourth");
	}


}

