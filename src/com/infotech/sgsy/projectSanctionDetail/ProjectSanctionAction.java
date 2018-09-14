package com.infotech.sgsy.projectSanctionDetail;

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
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;

public class ProjectSanctionAction extends DispatchAction {
	
	public ActionForward sanctionDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		/*SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		SanctionDetailVO sanctionVo = new SanctionDetailVO();
		SanctionDetailsForm sanctionForm = (SanctionDetailsForm) form;*/
		List<SanctionDetailVO> tclist=new ArrayList<SanctionDetailVO>();
		String forward= "sanction";
		try { 
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project:sanctionProjectList){
			ids.add(project.getId());
		}
		if(ids.size()>0){
			 tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
		}
		for(SanctionDetailVO vo:tclist){
			ProjectDetailsVO project=new ProjectDetailsVO();
			try{
			project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
			}catch (Exception e) {
				//e.printStackTrace();
				System.err.println("null pointer excption------- kamal");
			}
			vo.setProjectId(project);
		}
		  			 	
            }catch(Exception e) {
            	e.printStackTrace();
		}
 		 request.setAttribute("ViewList",tclist);
		return mapping.findForward(forward);
	}
	
	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");	
 		
			List<ProjectDetailsVO> assignProjectList =(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
			ArrayList<String> ids=new ArrayList<String>();
			//System.out.println("...project list..."+assignProjectList);
			for(ProjectDetailsVO project:sanctionProjectList){
				ids.add(project.getId());
			}
			List<SanctionDetailVO> tclist=new ArrayList<SanctionDetailVO>();
			//List<SanctionDetailVO> tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			if(ids.size()>0){
				 tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			}
			// abhi mera dimaag khraab h .... :-).......... kamal
			List<ProjectDetailsVO> newProjectlist=new ArrayList<ProjectDetailsVO>();
				for(SanctionDetailVO sanvo:tclist){
					for(ProjectDetailsVO project:sanctionProjectList){
					if(project.getId().equalsIgnoreCase(sanvo.getProjectId().getId())){
						newProjectlist.add(project);
					}
				}
			}
			if(newProjectlist.size()>0){
				sanctionProjectList.removeAll(newProjectlist);
			}
			request.setAttribute("projectList",sanctionProjectList);
		}catch(Exception e) {
			     log.error("Exception come -- Add Employee Details " + e.getMessage());
		}		
		  return mapping.findForward("addDetail");
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		SanctionDetailVO sanctionVo = new SanctionDetailVO();
		SanctionDetailsForm sanctionForm = (SanctionDetailsForm) form;
		List<SanctionDetailVO> tclist=new ArrayList<SanctionDetailVO>();	 
		String forward= "sanction";
				
	 try {
		    ProjectDetailsVO projectDetailsVO= (ProjectDetailsVO) sanctionDao.getById(ProjectDetailsVO.class,sanctionForm.getProjectId() );
		     //ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
		    // projectDetailsVO.setId(sanctionForm.getProjectId());
		     sanctionVo.setProjectId(projectDetailsVO);
		     sanctionVo.setProjectName(sanctionForm.getProjectName());
		     sanctionVo.setSanOrder(sanctionForm.getSanOrder());
		     sanctionVo.setResidential(sanctionForm.getResidential());
		     sanctionVo.setNonresidential(sanctionForm.getNonresidential());
		    // Integer santarget=sanctionForm.getResidential()+sanctionForm.getNonresidential();
		     sanctionVo.setSanTarget(sanctionForm.getSanTarget());
		     sanctionVo.setSanctionDate(sanctionForm.getSanctionDate());
		     sanctionVo.setProjectDuration(sanctionForm.getProjectDuration());
		     sanctionVo.setPcoDate(sanctionForm.getPcoDate());
		     sanctionVo.setCommDate(sanctionForm.getCommDate());
		     sanctionVo.setEndDate(sanctionForm.getEndDate());
		     sanctionVo.setTrainingcompletion(sanctionForm.getTrainingcompletion());
		     sanctionVo.setPlacementcompletion(sanctionForm.getPlacementcompletion());
		     
		     sanctionVo.setMouSigned(sanctionForm.getMouSigned());
		     sanctionVo.setMouSignedDate(sanctionForm.getMouSignedDate());
		     sanctionVo.setPwsApproved(sanctionForm.getPwsApproved());
		     sanctionVo.setPwsDate(sanctionForm.getPwsDate());
		     sanctionVo.setPerApproved(sanctionForm.getPerApproved());
		     sanctionVo.setPerDate(sanctionForm.getPerDate());
		     sanctionVo.setProjectStatus(sanctionForm.getProjectStatus());
		     sanctionVo.setRemark(sanctionForm.getRemark());
		     int scst=sanctionForm.getSanTarget();
		     int general=100-projectDetailsVO.getState().getSc_st();
		     sanctionVo.setSc_st(Math.round(scst*projectDetailsVO.getState().getSc_st()/100)+"");
		     sanctionVo.setMinority(Math.round(scst*projectDetailsVO.getState().getMiniority()/100)+"");
		     sanctionVo.setWomen(Math.round(scst*projectDetailsVO.getState().getWomen()/100)+"");
		     sanctionVo.setGeneral(Math.round(scst*general/100)+"");
		     
		     sanctionVo.setRegisterToPfms(sanctionForm.getRegisterToPfms());
		     if("yes".equalsIgnoreCase(sanctionForm.getRegisterToPfms())){
		    	 sanctionVo.setAgencyCode(sanctionForm.getAgencyCode());
			     sanctionVo.setAccountNumber(sanctionForm.getAccountNumber());
			     sanctionVo.setBankName(sanctionForm.getBankName());
			     sanctionVo.setIfscCode(sanctionForm.getIfscCode());
		     }
		     
		     
		     
		     
        	 sanctionVo.setCreatedBy(loginVO.getUserid());        //--------------------------------------------------------------------------update after user module
	     	 sanctionVo.setCreatedOn(new Date());
	     	 sanctionDao.save(sanctionVo);	
   	     
	     	List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
			ArrayList<String> ids=new ArrayList<String>();
			for(ProjectDetailsVO project:sanctionProjectList){
				ids.add(project.getId());
			}
			//tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			if(ids.size()>0){
				 tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			}
			for(SanctionDetailVO vo:tclist){
				ProjectDetailsVO project=new ProjectDetailsVO();
				try{
				project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
				}catch (Exception e) {
					System.err.println("null pointer excption------- kamal");
				}
				vo.setProjectId(project);
			}
   	      

           sanctionForm.reset(mapping, request);
    }   catch (Exception e) {
  	  e.printStackTrace();
   }
	 request.setAttribute("ViewList", tclist);
	return mapping.findForward(forward);
}
	
public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
				
	SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	SanctionDetailVO sanctionVo = new SanctionDetailVO();
	SanctionDetailsForm sanctionForm = (SanctionDetailsForm) form;
		String forward="editPage";
        String tradeTargetid = request.getParameter("id");
       
		
		try{
			 sanctionVo = sanctionDao.getTradeTargetDetails(tradeTargetid);		
			 ProjectDetailsVO project =new ProjectDetailsVO();
			 project.setId(sanctionVo.getProjectId().getId());
			 project.setProjectID(sanctionVo.getProjectId().getProjectID()+" ("+sanctionVo.getProjectId().getState().getStateName()+")- "+sanctionVo.getProjectId().getPiaPrn().getUserName());
			 sanctionVo.setProjectId(project);
			 
			request.setAttribute("sanctionDetails",sanctionVo);
			forward="editPage";
		    BeanUtils.copyProperties(sanctionForm, sanctionVo);
		 
		}
		catch(Exception e){
			System.err.println("error in sanction edit method");
			request.setAttribute("sanctionDetails",sanctionVo);
			//e.printStackTrace();
		}
    return mapping.findForward(forward);
	}
	
	
	
 public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
	 LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	
	 SanctionDetailsForm sanctionForm = (SanctionDetailsForm) form;
		String forward= "sanction";
		List<SanctionDetailVO> tclist=new ArrayList<SanctionDetailVO>();
	try {
		SanctionDetailVO sanctionVo =sanctionDao.getTradeTargetDetails(sanctionForm.getId());
		   
		     sanctionVo.setSanOrder(sanctionForm.getSanOrder());
		     sanctionVo.setResidential(sanctionForm.getResidential());
		     sanctionVo.setNonresidential(sanctionForm.getNonresidential());
		    // Integer santarget=sanctionForm.getResidential()+sanctionForm.getNonresidential();
		     
		     sanctionVo.setSanTarget(sanctionForm.getSanTarget());
		     sanctionVo.setProjectName(sanctionForm.getProjectName());
		     sanctionVo.setSanctionDate(sanctionForm.getSanctionDate());
		     sanctionVo.setProjectDuration(sanctionForm.getProjectDuration());
		     sanctionVo.setPcoDate(sanctionForm.getPcoDate());
		     sanctionVo.setCommDate(sanctionForm.getCommDate());
		     sanctionVo.setEndDate(sanctionForm.getEndDate());
		     
		     sanctionVo.setTrainingcompletion(sanctionForm.getTrainingcompletion());
		     sanctionVo.setPlacementcompletion(sanctionForm.getPlacementcompletion());
		     
		     sanctionVo.setMouSigned(sanctionForm.getMouSigned());
		     sanctionVo.setMouSignedDate(sanctionForm.getMouSignedDate());
		     sanctionVo.setPwsApproved(sanctionForm.getPwsApproved());
		     sanctionVo.setPwsDate(sanctionForm.getPwsDate());
		     sanctionVo.setPerApproved(sanctionForm.getPerApproved());
		     sanctionVo.setPerDate(sanctionForm.getPerDate());
		     sanctionVo.setProjectStatus(sanctionForm.getProjectStatus());
		     sanctionVo.setRemark(sanctionForm.getRemark());
		     ProjectDetailsVO projectDetailsVO=sanctionVo.getProjectId();
		      /* int scst=sanctionForm.getSanTarget();*/
		     int scst=sanctionForm.getSanTarget();
		     int general=100-projectDetailsVO.getState().getSc_st();
		     sanctionVo.setSc_st(Math.round(scst*projectDetailsVO.getState().getSc_st()/100)+"");
		     sanctionVo.setMinority(Math.round(scst*projectDetailsVO.getState().getMiniority()/100)+"");
		     sanctionVo.setWomen(Math.round(scst*projectDetailsVO.getState().getWomen()/100)+"");
		     sanctionVo.setGeneral(Math.round(scst*general/100)+"");
		     
		    /* sanctionVo.setSc_st((scst*50/100)+"");
		     sanctionVo.setMinority((scst*15/100)+"");
		     sanctionVo.setWomen((scst*33/100)+"");
		     sanctionVo.setGeneral((scst*50/100)+"");*/
		     sanctionVo.setRegisterToPfms(sanctionForm.getRegisterToPfms());
		     if("yes".equalsIgnoreCase(sanctionForm.getRegisterToPfms())){
		    	 sanctionVo.setAgencyCode(sanctionForm.getAgencyCode());
			     sanctionVo.setAccountNumber(sanctionForm.getAccountNumber());
			     sanctionVo.setBankName(sanctionForm.getBankName());
			     sanctionVo.setIfscCode(sanctionForm.getIfscCode());
		     }else{
		    	 sanctionVo.setAgencyCode("");
			     sanctionVo.setAccountNumber("");
			     sanctionVo.setBankName("");
			     sanctionVo.setIfscCode("");
		     }
		     
		    
		     
		   
	         sanctionVo.setUpdatedBy(loginVO.getUserid());   //--------------------------------------------------------------------------update after user module
	         sanctionVo.setUpdatedOn(new Date());
	         sanctionDao.update(sanctionVo);
	         
   	   List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
	    	 ArrayList<String> ids=new ArrayList<String>();
	 	

		     for(ProjectDetailsVO project:sanctionProjectList){
			 ids.add(project.getId());
		    }
		// tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
		     if(ids.size()>0){
				 tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			}
		     
		     for(SanctionDetailVO vo:tclist){
			ProjectDetailsVO project=new ProjectDetailsVO();
			try{
			project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
			}catch (Exception e) {
				System.err.println("null pointer excption------- kamal");
			}
			vo.setProjectId(project);
		}
	      

     }   catch (Exception e) {
  	  e.printStackTrace();
   }
	 request.setAttribute("ViewList", tclist);
	return mapping.findForward(forward);
}
	//...................................
	 public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		  SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
		  LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		  SanctionDetailsForm sanctionForm = (SanctionDetailsForm) form;
			List<SanctionDetailVO> tclist=new ArrayList<SanctionDetailVO>();
		  String forward= "sanction";
		 try {
			 String id=request.getParameter("id");
		 		SanctionDetailVO sanctionVo =sanctionDao.getTradeTargetDetails(id);
		 		  sanctionDao.delete(sanctionVo);	
		 		   
		 	List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
			 ArrayList<String> ids=new ArrayList<String>();
			 for(ProjectDetailsVO project:sanctionProjectList){
				 ids.add(project.getId());
			    }
			 //tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			 if(ids.size()>0){
				 tclist=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
			}
			 for(SanctionDetailVO vo:tclist){
				ProjectDetailsVO project=new ProjectDetailsVO();
				try{
				project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
				}catch (Exception e) {
					System.err.println("null pointer excption------- kamal");
				}
				vo.setProjectId(project);
			}
			    
			    
			}   catch (Exception e) {
		    	  e.printStackTrace();
		  }
		 request.setAttribute("ViewList", tclist);
			return mapping.findForward(forward);
  }
	 
	 ///...code to get the date for validation from project detail new 
	 public ActionForward getDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
		
		ProjectDetailsVO projectDetailsVO= (ProjectDetailsVO) sanctionDao.getById(ProjectDetailsVO.class, request.getParameter("project_id"));
		response.getWriter().write(projectDetailsVO.getDateOfEc()+"<~~~>"+projectDetailsVO.getState().getStateName()); 
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		return null;
	    }
	 
	 //..this code is for writing the state name
	 public ActionForward getStateName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
	//	System.out.println("project_id......charvi"+request.getParameter("project_id"));
		 
		SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
				 
		  ProjectDetailsVO projectDetailsVO= (ProjectDetailsVO) sanctionDao.getById(ProjectDetailsVO.class,request.getParameter("project_id"));
		  /*UserMaster userMaster= (UserMaster) sanctionDao.getById(UserMaster.class,projectDetailsVO.getCreatedBy());
  		  StateVO stateVO= (StateVO) sanctionDao.getById(StateVO.class,userMaster.getStateId().getStateId());*/
		 
  		    response.getWriter().write(projectDetailsVO.getState().getStateName());
  			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
		    return null;
	    } 
	 
	 //..this code is for writing the state name for edit page..
	 public ActionForward getStateNameForEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		//System.out.println("project_id......charvi"+request.getParameter("project_id"));
		 
		SanctionDetailDAO sanctionDao = new SanctionDetailDAO();
			
		
		  ProjectDetailsVO projectDetailsVO= (ProjectDetailsVO) sanctionDao.getById(ProjectDetailsVO.class,request.getParameter("project_id"));
		//  System.out.println("1111"+projectDetailsVO.getId());
		  UserMaster userMaster= (UserMaster) sanctionDao.getById(UserMaster.class,projectDetailsVO.getCreatedBy());
		//  System.out.println("2222"+request.getParameter("project_id"));
  		  StateVO stateVO= (StateVO) sanctionDao.getById(StateVO.class,userMaster.getStateId().getStateId());
  		// System.out.println("3333"+request.getParameter("project_id"));
		 
  		    response.getWriter().write(stateVO.getStateName());
  			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
		    return null;
	    } 
}
