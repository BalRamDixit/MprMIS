package com.infotech.sgsy.tcSetup.tcSetupDue;

import java.util.ArrayList;
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
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.stateSetup.hrTeam.HRTeamVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.util.DateUtil;

public class TcSetupDueAction extends DispatchAction {

	DateUtil dateUtil = new DateUtil();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project:sanctionProjectList){
			ids.add(project.getId());
		}
		List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
		ArrayList<String> ids1=new ArrayList<String>();
		for(TcSetupVO vo:tclist){
			ids1.add(vo.getId());
		}
		List<TcSetupDueVO> list=new ArrayList<TcSetupDueVO>();
		if(ids1.size()>0){
			list=(List<TcSetupDueVO>) AbsFinder.getListByProjectIds(TcSetupDueVO.class, "trainingCenterId.id", ids1);
		}
		
		String forward= "showPage";
 		try {  
 			//List<TcSetupDueVO> list=(List<TcSetupDueVO>) AbsFinder.getListByProjectIds(TcSetupDueVO.class, "trainingCenterId.project.id", ids);
			    request.setAttribute("ViewList", list);
			}catch(Exception e) {
		//	log.error("Exception come -- Add Employee Details " + e.getMessage());
		}		
		return mapping.findForward(forward);
	}
	
	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO ();
		TcSetupDueActionForm tcSetUpForm = (TcSetupDueActionForm) form;
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");	
		
		try {
			
			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			System.out.println("...project list..."+assignProjectList);
			request.setAttribute("projectList",assignProjectList);
			
			List<HRTeamVO> hList=(List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"state.stateId",loginVO.getEntityCode()});
			request.setAttribute("hrlist",hList);
		   
		   }
		catch(Exception e) {
			//     log.error("Exception come -- Add Employee Details " + e.getMessage());
		}		
		  return mapping.findForward("addDetail");
	}
	
//	id.districtId.districtName
	
	public ActionForward getTraining(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {    
		 TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO();
		 String projectId=request.getParameter("project_id");
		 List<TcSetupVO> tcDropDownList=(List<TcSetupVO>) AbsFinder.getListByCondition(TcSetupVO.class,new String[]{"project.id",projectId});
		// TcSetupVO tcSetupVO= (TcSetupVO) tcSetupDao.getById(TcSetupVO.class, request.getParameter("project_id"));
		 //System.out.println("..training list...."+tcSetupVO.getId());
		 StringBuilder trainningOption=new StringBuilder();
		 trainningOption.append("<option value='0'>--select--</option>");
		 for(int i=0;i<tcDropDownList.size();i++){
			 trainningOption.append("<option value='"+tcDropDownList.get(i).getId()+"'>"+tcDropDownList.get(i).getDistrict().getDistrictName()+"-"+tcDropDownList.get(i).getTcID()+"</option>");
		 }
		 List<HRTeamVO> hList=new ArrayList<HRTeamVO>();
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");	
		if ("ctsa".equalsIgnoreCase(loginVO.getRoleName())) {

		} else if ("pia".equalsIgnoreCase(loginVO.getRoleName()) || "srlm".equalsIgnoreCase(loginVO.getRoleName())) {
			hList = (List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[] { "state.stateId", tcDropDownList.get(0).getProject().getState().getStateId() });
		}
		StringBuilder hrlist1111 = new StringBuilder();
		hrlist1111.append("<option value='0'>--SELECT--</option>");
		for (HRTeamVO vo : hList) {
			hrlist1111.append("<option value='" + vo.getId() + "'>" + vo.getPersonName() + "-"
					+ vo.getDesignation().getDesignationName() + "</option>");
		}
		 
		 
		 
		 
			response.getWriter().write(trainningOption.toString()+"<~~~>"+hrlist1111.toString()); 
		//	response.setContentType("text/xml");
		//	response.setHeader("Cache-Control", "no-cache");
			
			
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
		//	response.getWriter().write(tradedd.toString());
		   
		    return null;
	    }
	public ActionForward getSrlm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {    
		   TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO();
		   LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		   ProjectDetailsVO projectDetailsVO=(ProjectDetailsVO)tcSetupDao.getById(ProjectDetailsVO.class,request.getParameter("projectid"));
		   UserMaster userMaster =(UserMaster)tcSetupDao.getById(UserMaster.class,projectDetailsVO.getCreatedBy());
		   List<HRTeamVO> hRTeamVO=( List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"state.stateId",loginVO.getEntityCode()});
		   
		   StringBuilder ss=new StringBuilder();
		   ss.append("[");
		   int i=0;
		   for(HRTeamVO vo:hRTeamVO){
			   if(i>0){
				   ss.append(",");
			   }
			   ss.append("{");
			   ss.append("\"id\":\""+vo.getId()+"\",\"name\":\""+vo.getPersonName()+"\",\"designation\":\""+vo.getDesignation().getDesignationName()+"\"");
			   ss.append("}");
			   i++;
			}
		   ss.append("]");
		   	  
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(ss.toString());
		    return null;
	   }	  
	  ///.......................................	
	  
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO ();
		TcSetupDueActionForm tcSetUpForm = (TcSetupDueActionForm) form;
	    TcSetupDueVO tcSetUpVO = new TcSetupDueVO();
	    System.out.println("aaaaaaaa");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO"); 
			String forward= "showPage";
		try {
		   
		    // ProjectDetailsVO projectDetailsVO= (ProjectDetailsVO) tcSetupDao.getById(ProjectDetailsVO.class,tcSetUpForm.getProjectID() );
		   //  tcSetUpVO.setProjectID(projectDetailsVO);
             TcSetupVO tc=new TcSetupVO();
             tc.setId(tcSetUpForm.getTrainingCenterId());
		     tcSetUpVO.setTrainingCenterId(tc);
		     tcSetUpVO.setRecieptDate(tcSetUpForm.getRecieptDate());
		     HRTeamVO hr=new HRTeamVO();
		     System.out.println("serlm person---->    "+tcSetUpForm.getSrlmPersonId());
		     hr.setId(tcSetUpForm.getSrlmPersonId());
		     if(!tcSetUpForm.getSrlmPersonId().equals("0")){
		    	 tcSetUpVO.setSrlmPersonId(hr);	 
		     }
		     
		     tcSetUpVO.setTcAppResidentCapacity(tcSetUpForm.getTcAppResidentCapacity());
		     tcSetUpVO.setVisitDate(tcSetUpForm.getVisitDate());
		     tcSetUpVO.setAppRejDate(tcSetUpForm.getAppRejDate());
		     tcSetUpVO.setStatusDueDil(tcSetUpForm.getStatusDueDil());
		     tcSetUpVO.setRemarksDueDili(tcSetUpForm.getRemarksDueDili());
		     tcSetUpVO.setTcAppCapacity(tcSetUpForm.getTcAppCapacity());
		     tcSetUpVO.setTcStatus(tcSetUpForm.getTcStatus());
		     tcSetUpVO.setRemarks(tcSetUpForm.getRemarks());
		     tcSetUpVO.setCreatedBy(loginVO.getUserid());        //--------------------------------------------------------------------------update after user module
	     	 tcSetUpVO.setCreatedOnDate(new Date());
	     	 
   	         tcSetupDao.save(tcSetUpVO);	
   	     
   	         
   	      List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
  		ArrayList<String> ids=new ArrayList<String>();
  		for(ProjectDetailsVO project:sanctionProjectList){
  			ids.add(project.getId());
  		}
  		List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
  		ArrayList<String> ids1=new ArrayList<String>();
  		for(TcSetupVO vo:tclist){
  			ids1.add(vo.getId());
  		}
  		List<TcSetupDueVO> list=new ArrayList<TcSetupDueVO>();
  		if(ids1.size()>0){
  			list=(List<TcSetupDueVO>) AbsFinder.getListByProjectIds(TcSetupDueVO.class, "trainingCenterId.id", ids1);
  		}
   	        request.setAttribute("ViewList", list);

              }   catch (Exception e) {
  	           e.printStackTrace();
             }
	          return mapping.findForward(forward);
          }
	
     public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
				
	    TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO ();
	    TcSetupDueActionForm tcSetUpForm = (TcSetupDueActionForm) form;
		TcSetupDueVO tcSetUpVO = new TcSetupDueVO();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
    
        String tradeTargetid = request.getParameter("id");
        tcSetUpVO = tcSetupDao.getTradeTargetDetails(tradeTargetid);
        TcSetupVO tt=(TcSetupVO) AbsFinder.getById(TcSetupVO.class, tcSetUpVO.getTrainingCenterId().getId());
    //  List<TcSetupVO> tc=(List<TcSetupVO>) AbsFinder.getListByCondition(TcSetupVO.class,new String[]{"project.id",tt.getProject().getId()});
        
		try{
			request.setAttribute("dueDeligenceTarget",tcSetUpVO);
//			request.setAttribute("abc",tc);
//			BeanUtils.copyProperties(tcSetUpForm, tcSetUpVO);
//			if ("ctsa".equalsIgnoreCase(loginVO.getRoleName())) {
//			} else if ("pia".equalsIgnoreCase(loginVO.getRoleName()) || "srlm".equalsIgnoreCase(loginVO.getRoleName())) {
				List<HRTeamVO> hList = (List<HRTeamVO>) tcSetupDao.getListByCondition(HRTeamVO.class,new String[]{"state.stateId", tt.getProject().getState().getStateId()});
				request.setAttribute("hrlist",hList);
//			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
    return mapping.findForward("editPage");
	}
	
	
	
 public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO ();
		TcSetupDueActionForm tcSetUpForm = (TcSetupDueActionForm) form;
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	
		String forward= "showPage";

	try {
		TcSetupDueVO tcSetUpVO =tcSetupDao.getTradeTargetDetails(tcSetUpForm.getId());
		   
	       tcSetUpVO.setRecieptDate(tcSetUpForm.getRecieptDate());
		   tcSetUpVO.setVisitDate(tcSetUpForm.getVisitDate());
		   HRTeamVO hr=new HRTeamVO();
		 
		     hr.setId(tcSetUpForm.getSrlmPersonId());
		     if(!tcSetUpForm.getSrlmPersonId().equals("0")){
		    	 tcSetUpVO.setSrlmPersonId(hr);	 
		     }
		   tcSetUpVO.setAppRejDate(tcSetUpForm.getAppRejDate());
		   tcSetUpVO.setStatusDueDil(tcSetUpForm.getStatusDueDil());
		   tcSetUpVO.setRemarksDueDili(tcSetUpForm.getRemarksDueDili());
		   tcSetUpVO.setTcAppCapacity(tcSetUpForm.getTcAppCapacity());
		   tcSetUpVO.setTcStatus(tcSetUpForm.getTcStatus());
		   tcSetUpVO.setRemarks(tcSetUpForm.getRemarks());
		   tcSetUpVO.setUpdatedBy(loginVO.getUserid());   //--------------------------------------------------------------------------update after user module
	       tcSetUpVO.setUpdatedOnDate(new Date());
	       tcSetUpVO.setTcAppResidentCapacity(tcSetUpForm.getTcAppResidentCapacity());
	   	   tcSetupDao.update(tcSetUpVO);	
	   	   
	   	List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project:sanctionProjectList){
			ids.add(project.getId());
		}
		List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
		ArrayList<String> ids1=new ArrayList<String>();
		for(TcSetupVO vo:tclist){
			ids1.add(vo.getId());
		}
		List<TcSetupDueVO> list=new ArrayList<TcSetupDueVO>();
		if(ids1.size()>0){
			list=(List<TcSetupDueVO>) AbsFinder.getListByProjectIds(TcSetupDueVO.class, "trainingCenterId.id", ids1);
		}
   	   	  
           request.setAttribute("ViewList", list);
	    } 
	       catch (Exception e) {
  	       e.printStackTrace();
        }
        	return mapping.findForward(forward);
}
	//...................................
	  
 public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		      LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			  TcSetUpDueDAO tcSetupDao = new TcSetUpDueDAO();
			  TcSetupDueActionForm tcSetUpForm = (TcSetupDueActionForm) form;
		
				String forward= "showPage";
		 	try {
		 		TcSetupDueVO tcSetUpVO =tcSetupDao.getTradeTargetDetails(request.getParameter("id"));
		 		   tcSetupDao.delete(tcSetUpVO);	
		 		   request.setAttribute("ViewList", tcSetupDao.view());
			    }   catch (Exception e) {
		    	  e.printStackTrace();
		         }
			   return mapping.findForward(forward);
		    }
	  
	  
}

