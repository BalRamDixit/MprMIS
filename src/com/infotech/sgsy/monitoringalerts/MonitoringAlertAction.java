package com.infotech.sgsy.monitoringalerts;

import java.text.SimpleDateFormat;
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
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;  

public class MonitoringAlertAction extends DispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			MonitoringAlertDAO helperDao = new MonitoringAlertDAO();
	    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		    String entity_code =   loginVO.getEntityCode();
		    
			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
 			request.setAttribute("projectlist",sanctionProjectList);
 			//request.setAttribute("AlertList",helperDao.getdetails());
 			 request.setAttribute("AlertList",helperDao.getdetails(sanctionProjectList));

 			
		try {
		/*	
			if(entity_code.length()==2)
		    {
			    List srlm=  helperDao.getSrlmname(entity_code);	
			    request.setAttribute("entity","Srlm Name");
			    request.setAttribute("srlm",srlm.get(0));
			    request.setAttribute("AlertList",helperDao.getdetails(entity_code));
		    }		    
		    else if(entity_code.length()>2)
		    		{		    	
 		    	List pia=  helperDao.getPia(entity_code);	
			    request.setAttribute("entity","PIA NAME");
			    request.setAttribute("srlm",pia.get(0));
			    request.setAttribute("AlertList",helperDao.getdetails(entity_code));
		    		}*/
			     	
			   //  request.setAttribute("AlertList",helperDao.getdetails(entity_code));
			    // request.setAttribute("srNo", helperDao.getSrNumber());
			      	
			 
		}catch(Exception e) {
			log.error("Exception come -- Add ALERT Details " + e.getMessage());
		}		
		
     return mapping.findForward("showPage");
} 
	
	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MonitoringAlertDAO helperDao = new MonitoringAlertDAO();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		   String entity_code =   loginVO.getEntityCode();		    		    
		  
		   List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
			request.setAttribute("projectlist",sanctionProjectList);
 		
  		try {
  			 
  			 request.setAttribute("AlertList",helperDao.getdetails(sanctionProjectList));
 				
		}catch(Exception e) {
			log.error("Exception come -- Add Project Alert " + e.getMessage());
		}			
		return mapping.findForward("addDetail");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		MonitoringAlertDAO helperDao = new MonitoringAlertDAO();
		MonitoringAlertForm  Bean = (MonitoringAlertForm) form;
		MonitoringAlertVO  helperVo= new MonitoringAlertVO();
		 String id = request.getParameter("id"); 
 		// List<ProjectDetailsVO> project= (List<ProjectDetailsVO>) helperDao.getProjectDetails(id);
		
		 String projectid="";
		  
 		try{ 
 			 helperVo = helperDao.getAlertDetails(id);  
 
 			if(helperVo!=null){
 				BeanUtils.copyProperties(Bean,helperVo);
  			}
 			String  prid= helperVo.getProjectID().getId().toString();
  			 List<ProjectDetailsVO> project= (List<ProjectDetailsVO>) helperDao.getProjectDetails(prid);
 			 if(project.size()>0)
 			  {		    
 				    projectid=project.get(0).getProjectID()+ "("+ project.get(0).getState().getStateName()+ ")"+"-"+project.get(0).getPiaPrn().getUserName();
 				 
  			  }	
		}
		catch(Exception e){
			 
			e.printStackTrace();
		}   
 		helperVo.setDateOfIssue(Bean.getDateOfIssue());
 		helperVo.setDateOfReplyFromPia(Bean.getDateOfReplyFromPia());
 		helperVo.setCommunicationToPiadate(Bean.getCommunicationToPiadate()); 
 		request.setAttribute("projectid", projectid);
		request.setAttribute("Bean",helperVo);	
		request.setAttribute("alertId",helperVo.getId());
		return mapping.findForward("editPage");
	}	
	 
	
	  public ActionForward showSanctionDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 MonitoringAlertDAO helperDao = new MonitoringAlertDAO();
		 String projectid= request.getParameter("projectid"); 
		 System.err.println("project id"+projectid);
		  
		   List<SanctionDetailVO>  sanction= helperDao.getSanctionDate(projectid);
		   
		   if(sanction.size()>0)
			  {
			    response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				System.out.println("Start Date is --> "+sanction.get(0).getCommDate()+"");
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				String date=sdf.format(sanction.get(0).getCommDate());
				response.getWriter().write(date+""); 
			  }
			  else
			  { 
				  System.err.println("list is empty");
				  	
			  }
			    return null;
		    }
		   
	     
 	

		 public ActionForward deleteproject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
					throws Exception {
						
			 	MonitoringAlertDAO helperDao = new MonitoringAlertDAO();
 				MonitoringAlertVO  helperVo= new MonitoringAlertVO(); 
		          String id = request.getParameter("id"); 				 
		         //helperVo = helperDao.editPage(id);
		     	 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		     	List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
				List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		      	 
		 		try{
		 			 helperVo.setId(id);
  		 			 helperDao.delete(helperVo);  		 			 
  		 			request.setAttribute("AlertList",helperDao.getdetails(sanctionProjectList));		 			 
				}
				catch(Exception e){
					 
					e.printStackTrace();
					}		 
				return mapping.findForward("showPage");
				} 	  
	  
		 public ActionForward updateAlert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
 				     MonitoringAlertDAO helperDao = new MonitoringAlertDAO();
					 MonitoringAlertForm  helperForm = (MonitoringAlertForm) form;
					 MonitoringAlertVO  helperVo= new MonitoringAlertVO();
  			      	 
 			      	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
 				  //  String entity_code =  loginVO.getEntityCode(); 	
 				   ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
 					// projectDetailsVO.setId(helperForm.getProjectID());
  					String updatedBY =   loginVO.getUserid();
 					 String id = request.getParameter("id"); 	
 			try {
				
 				List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
 				List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		 	// BeanUtils.copyProperties(helperVo, helperForm);	 
		 	 	 
		 	 helperVo = helperDao.getAlertDetails(id);
		 	 String prid=helperVo.getProjectID().getId();
 		 	 projectDetailsVO.setId(prid);
		 
		   //  helperVo.setProjectID(helperForm.getProjectID());
		    /* helperVo.setTypeOfAlert(helperForm.getTypeOfAlert());
		     helperVo.setDateOfIssue(helperForm.getDateOfIssue());
		     helperVo.setReasoncategory(helperForm.getReasoncategory());
		     helperVo.setReasonForIssue(helperForm.getReasonForIssue());
		     helperVo.setIssuingAgency(helperForm.getIssuingAgency());
		     helperVo.setDateOfReplyFromPia(helperForm.getDateOfReplyFromPia());
		     helperVo.setStatus(helperForm.getStatus());
		     helperVo.setCommunicationToPiadate(helperForm.getCommunicationToPiadate());
		     helperVo.setRemarks(helperForm.getRemarks());  */
		 	 
		 	helperVo.setId(id); 
		 	helperVo.setProjectID(projectDetailsVO);
			helperVo.setTypeOfAlert(helperForm.getTypeOfAlert());
			helperVo.setReasoncategory(helperForm.getReasoncategory());
			helperVo.setReasonForIssue(helperForm.getReasonForIssue());
			helperVo.setIssuingAgency(helperForm.getIssuingAgency());
			helperVo.setReplyFromPia(helperForm.getReplyFromPia());
			helperVo.setStatus(helperForm.getStatus());
			helperVo.setRemarks(helperForm.getRemarks());				
		 	helperVo.setDateOfIssue(helperForm.getDateOfIssue());
		 	helperVo.setDateOfReplyFromPia(helperForm.getDateOfReplyFromPia());
		 	helperVo.setCommunicationToPiadate(helperForm.getCommunicationToPiadate()); 
		 	 
		   	helperVo.setUpdatedBy(updatedBY);      
		 	helperVo.setUpdatedOn(new Date());
 		 	helperDao.update(helperVo);	
			 request.setAttribute("AlertList",helperDao.getdetails(sanctionProjectList));

		 	
		  	helperForm.reset(mapping, request); 
			 
		  }   catch (Exception e) {
			  e.printStackTrace();
		 }
		return mapping.findForward("showPage");
		}
		  
			public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	 
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
			MonitoringAlertDAO helperDao = new MonitoringAlertDAO();
			MonitoringAlertForm  helperForm = (MonitoringAlertForm) form;
			MonitoringAlertVO  helperVo= new MonitoringAlertVO();
			String createdBY =   loginVO.getUserid(); 
		    ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
			 projectDetailsVO.setId(helperForm.getProjectID());
			 System.err.println("helperForm.getProjectID()"+helperForm.getProjectID());
			try {
				
			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		
				
		 	//BeanUtils.copyProperties(helperVo, helperForm); 
			helperVo.setProjectID(projectDetailsVO);
			helperVo.setTypeOfAlert(helperForm.typeOfAlert);
			helperVo.setReasoncategory(helperForm.reasoncategory);
			helperVo.setReasonForIssue(helperForm.reasonForIssue);
			helperVo.setIssuingAgency(helperForm.issuingAgency);
			helperVo.setReplyFromPia(helperForm.replyFromPia);
			helperVo.setStatus(helperForm.status);
			helperVo.setRemarks(helperForm.remarks);				
		 	helperVo.setDateOfIssue(helperForm.getDateOfIssue());
		 	helperVo.setDateOfReplyFromPia(helperForm.getDateOfReplyFromPia());
		 	helperVo.setCommunicationToPiadate(helperForm.getCommunicationToPiadate());
		 	
		 	helperVo.setCreatedBy(createdBY);   
		 	helperVo.setCreatedOn(new Date());
		  	helperDao.save(helperVo);	 
 	
		  	request.setAttribute("AlertList",helperDao.getdetails(sanctionProjectList));
	  		helperForm.reset(mapping, request); 
	   	 
					}   catch (Exception e) {
				e.printStackTrace();
			}
			return mapping.findForward("showPage");
		} 
	 
}
