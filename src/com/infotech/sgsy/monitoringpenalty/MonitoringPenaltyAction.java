package com.infotech.sgsy.monitoringpenalty;

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
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO; 
 
 

public class MonitoringPenaltyAction extends DispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception { 	
		
		MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();	
 		String forward= "showPage"; 
		
 		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
 		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");  
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
  		try {		
			
		 
 				 request.setAttribute("projectlist",sanctionProjectList);
 			     request.setAttribute("DetailList",helperDao.getdetails());
 		      // request.setAttribute("srNo", helperDao.getSrNumber());
			      
 
				
		}catch(Exception e) {
			log.error("Exception come -- Add Penalty Details " + e.getMessage());
		}		
		
     return mapping.findForward(forward);

}
	
	
	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();	

		     LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		    // String entity_code =   loginVO.getEntityCode();
		     List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			 List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
	 		
  		try {
 		    
 	        // request.setAttribute("srNo", helperDao.getSrNumber());
 /*
	     	if(entity_code.length()==2)
		    {
  				String state_code="entity_code";
			    List srlm=  helperDao.getSrlmname(entity_code);	
			    request.setAttribute("entity","Srlm Name");
			    request.setAttribute("srlm",srlm.get(0));
			    request.setAttribute("DetailList",helperDao.getdetails(entity_code));
			    List projectlist=  helperDao.getprojectDetails(state_code,entity_code);
			    request.setAttribute("projectlist",projectlist);
		    }
		    
		    else if(entity_code.length()>2)
		    		{		
		    	String piaprn="piaprn";
 		    	List pia=  helperDao.getPia(entity_code);	
			    request.setAttribute("entity","PIA NAME");
			    request.setAttribute("srlm",pia.get(0));
			    request.setAttribute("DetailList",helperDao.getdetails(entity_code));
			    List projectlist=  helperDao.getprojectDetails(piaprn,entity_code);
			    request.setAttribute("projectlist",projectlist);
		    		}*/
  			 request.setAttribute("projectlist",sanctionProjectList);
			 request.setAttribute("DetailList",helperDao.getdetails()); 		}
  		catch(Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}	
				return mapping.findForward("addDetail");
	} 
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MonitoringPenaltyForm  Bean = (MonitoringPenaltyForm) form;
		MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();
		MonitoringPenaltyVO helperVo= new MonitoringPenaltyVO();
		
		
		 String id = request.getParameter("id"); 
		 System.out.println("The id is "+id);
		/* MonitoringPenaltyVO monitoringPenaltyVO = (MonitoringPenaltyVO) AbsFinder.getById(MonitoringPenaltyVO.class, id);
		 System.out.println("monitoring vo contains===>"+monitoringPenaltyVO);*/
		 
		 List<ProjectDetailsVO> project= (List<ProjectDetailsVO>) helperDao.getProjectDetails(id);
		 System.out.println("the project contains---->"+project);
		
		 String projectid="";
		  
 		try{ 
 			 helperVo = helperDao.getPenaltyDetails(id);  			
 			if(helperVo!=null){
 				//BeanUtils.copyProperties(Bean,helperVo);
 				 System.err.println("vv1"+helperVo.getProjectID().getProjectID());
 			}
 			 if(project.size()>0)
 			  {		    
 				 System.err.println("vv2"+helperVo.getProjectID().getId());
 				    projectid=project.get(0).getProjectID();
 				 			 
 				 
 			  }	
		}
		catch(Exception e){
			 
			e.printStackTrace();
		}   
/* 		request.setAttribute("monitoringPenaltyVO", monitoringPenaltyVO);*/
		request.setAttribute("projectid", projectid);
		request.setAttribute("Bean", helperVo);
		request.setAttribute("penaltyId",helperVo.getId());
		
		return mapping.findForward("editPage");
	}	
	
	
	/*public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {			
		MonitoringPenaltyForm  helperForm = (MonitoringPenaltyForm) form;
		MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();
		MonitoringPenaltyVO helperVO= new MonitoringPenaltyVO();
            String id = request.getParameter("id");
 		    //helperVO = helperDao.editPage(id);
		  
		    LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
 		    List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
 		 
			MonitoringPenaltyVO helperVo= new MonitoringPenaltyVO();
			ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
			projectDetailsVO.setId(helperForm.getProjectID());

 		try{
 			
  			 
  			
 			 
 			 helperVo.setProjectID(projectDetailsVO);
			 helperVo.setTypeOfPenalty(helperForm.getTypeOfPenalty());
			 helperVo.setReasonForPenalty(helperForm.getReasonForPenalty());
			 helperVo.setStatus(helperForm.getStatus());
			 helperVo.setDateofIssue(helperForm.getDateofIssue());
			 helperVo.setLastDateOfAppeal(helperForm.getLastDateOfAppeal());
			 helperVo.setActualDateOfAppeal(helperForm.getActualDateOfAppeal());
			 helperVo.setLastDateOfAppealDisposal(helperForm.getLastDateOfAppealDisposal());
			 helperVo.setActualDateOfAppealDisposal(helperForm.getActualDateOfAppealDisposal());
			 helperVo.setAppealDisposalResult(helperForm.getAppealDisposalResult());
		 
 			 
			    request.setAttribute("penalty",helperVo);

 			request.setAttribute("projectlist",sanctionProjectList);
		}
		catch(Exception e){
			 
			e.printStackTrace();
		}
 
		return mapping.findForward("editPage");
	}*/
	
	 public ActionForward deleteproject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
		     MonitoringPenaltyForm  helperForm = (MonitoringPenaltyForm) form;
			MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();
			MonitoringPenaltyVO helperVo= new MonitoringPenaltyVO(); 
	          String id = request.getParameter("id"); 
              LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	  	      
	 		try{
 	 			helperVo.setId(id);
	 			helperDao.delete(helperVo);	 						
  			    request.setAttribute("DetailList",helperDao.getdetails());

			}
			catch(Exception e){
				 
				e.printStackTrace();
			}
	 
			return mapping.findForward("showPage");
		} 
	 
	 
	 	public ActionForward updatePenalty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 		
			    MonitoringPenaltyForm  helperForm = (MonitoringPenaltyForm) form;
			    MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();
			    MonitoringPenaltyVO helperVo= new MonitoringPenaltyVO();																      	 
			    LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");  
				List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
				List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
 				String updatedby=loginVO.getUserid();	
 				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
 				//projectDetailsVO.setId(helperForm.getProjectID());
 				 String id = request.getParameter("id");
				try {
					
					 //BeanUtils.copyProperties(helperVo, helperForm);	 
				  
 				 helperVo = helperDao.getPenaltyDetails(id);
				
 				 String prid=helperVo.getProjectID().getId();
 				
 	 		 	 projectDetailsVO.setId(prid);
				 helperVo.setProjectID(projectDetailsVO);
				 helperVo.setTypeOfPenalty(helperForm.getTypeOfPenalty());
				 helperVo.setReasonForPenalty(helperForm.getReasonForPenalty());
				 helperVo.setStatus(helperForm.getStatus());
				 helperVo.setDateofIssue(helperForm.getDateofIssue());
				 helperVo.setLastDateOfAppeal(helperForm.getLastDateOfAppeal());
				 helperVo.setActualDateOfAppeal(helperForm.getActualDateOfAppeal());
				 helperVo.setLastDateOfAppealDisposal(helperForm.getLastDateOfAppealDisposal());
				 helperVo.setActualDateOfAppealDisposal(helperForm.getActualDateOfAppealDisposal());
				 helperVo.setAppealDisposalResult(helperForm.getAppealDisposalResult());
				 helperVo.setId(id);
					 
					helperVo.setUpdatedBy(updatedby);      
					helperVo.setUpdatedOn(new Date());
  					helperDao.update(helperVo);	
					helperForm.reset(mapping, request);
					
			 			
		 			request.setAttribute("projectlist",sanctionProjectList);
	 			    request.setAttribute("DetailList",helperDao.getdetails());
	 			    request.setAttribute("penalty",helperVo);


				 
				}   catch (Exception e) {
				  e.printStackTrace();
				}
				return mapping.findForward("showPage");
				}
					 
	 
	 
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		MonitoringPenaltyForm  helperForm = (MonitoringPenaltyForm) form;
		MonitoringPenaltyDAO helperDao = new MonitoringPenaltyDAO();
		MonitoringPenaltyVO helperVo= new MonitoringPenaltyVO();

 		 LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	     String entity_code =   loginVO.getEntityCode();
	     List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
			request.setAttribute("projectlist",sanctionProjectList);
			 ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
		     projectDetailsVO.setId(helperForm.getProjectID());
		     String createdBy=loginVO.getUserid();
 	try {
 		
	          //request.setAttribute("srNo", helperDao.getSrNumber());	     
	         // BeanUtils.copyProperties(helperVo, helperForm);
	     	 
	        helperVo.setProjectID(projectDetailsVO);
	        helperVo.setTypeOfPenalty(helperForm.getTypeOfPenalty());
	        helperVo.setReasonForPenalty(helperForm.getReasonForPenalty());
	        helperVo.setStatus(helperForm.getStatus());
	        helperVo.setAppealDisposalResult(helperForm.getAppealDisposalResult() );	       
	     	helperVo.setDateofIssue(helperForm.getDateofIssue());
	     	helperVo.setLastDateOfAppeal(helperForm.getLastDateOfAppeal());
	     	helperVo.setActualDateOfAppeal(helperForm.getActualDateOfAppeal());     	
	     	helperVo.setLastDateOfAppealDisposal(helperForm.getLastDateOfAppealDisposal());
	     	helperVo.setActualDateOfAppealDisposal(helperForm.getActualDateOfAppealDisposal()); 	     	
	     	helperVo.setCreatedBy(createdBy);   
	     	helperVo.setCreatedOn(new Date());
 	     	helperDao.save(helperVo); 
      	    helperForm.reset(mapping, request);
		    request.setAttribute("DetailList",helperDao.getdetails());      	
       }   catch (Exception e) {
    	  e.printStackTrace();
     }
	return mapping.findForward("showPage");
  }
	
	}
