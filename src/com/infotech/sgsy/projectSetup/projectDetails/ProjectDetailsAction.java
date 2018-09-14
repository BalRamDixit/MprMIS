package com.infotech.sgsy.projectSetup.projectDetails;

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
import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;

public class ProjectDetailsAction extends DispatchAction {
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String prn=request.getParameter("prn");
		String forward= "showPage";
		try {
				List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); 
				request.setAttribute("ViewList",assignProjectList);
			   
				request.setAttribute("formName","PROJECT DETAILS");	

 		}catch(Exception e) {
			log.error("Exception come -- Add Project Details " + e.getMessage());
		}		
		
		return mapping.findForward(forward);
		
	}
	
	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
 		
 		try {	
 		     request.setAttribute("srNo",(Integer) AbsFinder.getcountUsingcondition(ProjectDetailsVO.class,new String[]{"createdBy",loginVO.getUserid()}));
 		     //System.out.println("serial number ---->  "+request.getAttribute("srNo"));
 		     
 		     
 		    
 		    request.setAttribute("statedetail", AbsFinder.getdetailByCondition(StateVO.class, new String[]{"stateId",loginVO.getEntityCode()}));
			request.setAttribute("formName","PROJECT DETAILS");	
			request.setAttribute("ProjectTypeList",(List<CtsaMasterVO>) AbsFinder.getDropDownList(CtsaMasterVO.class, new String[]{"id","ctsaName"}));
		    request.setAttribute("formName","PROJECT DETAILS");	

				
		}catch(Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}		
		
		return mapping.findForward("addPage");
	}
	 public ActionForward getPia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 ProjectDetailsDAO helperDao = new ProjectDetailsDAO();
		 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		 String prn=request.getParameter("prn");
		 StringBuffer tradedd = new StringBuffer();
		 UserMaster userMaster=(UserMaster) AbsFinder.getdetailByCondition(UserMaster.class, new String[]{"loginId",prn});
		
		 if(userMaster!=null){
			 tradedd.append(userMaster.getUserName());	 
			 
		 }
		 
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(tradedd.toString());
		   
		    return null;
	    }
	 
	   public ActionForward getUniquesFileNumber(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
		   {   
			 ProjectDetailsDAO helperDao = new ProjectDetailsDAO();
			 String filenumber=request.getParameter("filenumber");
			   List list=  helperDao.getDetailAboutFileNumber(filenumber);
			   StringBuffer tradedd = new StringBuffer();
			   if(list.size()>0){
				  // tradedd.append("<option value='0'> --SELECT-- </option>");
				   tradedd.append("already exist");
				   
			   }else{
				  // tradedd.append("<option value='0'> --SELECT-- </option>");
				   tradedd.append("not already exist");
				   
			   }
			    response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(tradedd.toString());
			   
			    return null;
		    }
	 
	 public ActionForward deleteproject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception {
					
			ProjectDetailsDAO helperDao = new ProjectDetailsDAO();
			ProjectDetailsForm  helperForm = (ProjectDetailsForm) form; 
	 		ProjectDetailsVO projectDetailsVO = new ProjectDetailsVO();
	 		String id = request.getParameter("id");
			 System.err.println(id);
			 projectDetailsVO=(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class, id);
	     	 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	 		try{
	 			helperDao.delete(projectDetailsVO);
	 			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
	 			ProjectDetailsVO deletedproject=new ProjectDetailsVO();
	 			for(ProjectDetailsVO vo:assignProjectList){
		 			if(vo.getId().equalsIgnoreCase(projectDetailsVO.getId())){
		 				deletedproject=vo;
		 			}
		 		}
	 			assignProjectList.remove(deletedproject);
		 		request.getSession().setAttribute("assignProjectDetails",assignProjectList);
			}
			catch(Exception e){
				 
				e.printStackTrace();
			}
	 		
	 		
	 		
	 		/*List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
			assignProjectList.add(projectDetailsVO);
			request.getSession().setAttribute("assignProjectDetails",assignProjectList);*/
	 		  request.setAttribute("ViewList",(List<ProjectDetailsVO>) AbsFinder.getListByCondition(ProjectDetailsVO.class, new String[]{"createdBy",loginVO.getUserid()}));
	 		 request.setAttribute("formName","PROJECT DETAILS");	
			return mapping.findForward("showPage");
		}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		ProjectDetailsVO projectDetailsVO = new ProjectDetailsVO();
          String id = request.getParameter("id");
		  projectDetailsVO=(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class, id);
     	 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
     	
 		try{
 			request.setAttribute("id", projectDetailsVO.getId());
 			if(projectDetailsVO.getCtsaMaster()!=null){
 				request.setAttribute("projecttype", projectDetailsVO.getCtsaMaster().getId());
 			}
 			//System.out.println( projectDetailsVO.getProjectType().getProjectTypeId());
 			request.setAttribute("file_number", projectDetailsVO.getFileNumber());
 			request.setAttribute("piaprn", projectDetailsVO.getPiaPrn().getLoginId());
 			request.setAttribute("pianame", projectDetailsVO.getPiaPrn().getUserName());
 			request.setAttribute("projectscheme", projectDetailsVO.getProjectScheme());
 			request.setAttribute("projectid", projectDetailsVO.getProjectID());
 			request.setAttribute("totalprojectcost", projectDetailsVO.getTotalProjectCost());
 			request.setAttribute("centralshare", projectDetailsVO.getCentralShare());
 			request.setAttribute("stateshare", projectDetailsVO.getStateShare());
 			request.setAttribute("programcost", projectDetailsVO.getProgramCost());
 			//request.setAttribute("welfarecost", projectDetailsVO.getWelfareCost());
 			request.setAttribute("consortium", projectDetailsVO.getConsortium());
 			if(projectDetailsVO.getConsortium().equalsIgnoreCase("No")){
 				request.setAttribute("prnofconsortiumpartner", "");
 	 			request.setAttribute("prnconsortiumpartnername", "");
 			}else{
 				request.setAttribute("prnofconsortiumpartner", projectDetailsVO.getPrnOfConsortiumPartner().getLoginId());
 	 			request.setAttribute("prnconsortiumpartnername", projectDetailsVO.getPrnOfConsortiumPartner().getUserName());
 			}
 			
 			request.setAttribute("partnerapplicantpiasame", projectDetailsVO.getPartnerApplicantPiaSame());
 			//System.out.println("date===>  "+projectDetailsVO.getDateOfEc());
 			request.setAttribute("dateofec", projectDetailsVO.getDateOfEc());
 			request.setAttribute("sanctioned", projectDetailsVO.getSanctioned());
 			
 			
			//BeanUtils.copyProperties(projectDetailsForm, projectDetailsVO);
			/*projectDetailsForm.setPiaName(projectDetailsVO.getPiaPrn().getUserName());
			projectDetailsForm.setPrnConsortiumPartnerName(projectDetailsVO.getPrnOfConsortiumPartner().getUserName());*/
		}
		catch(Exception e){
			 
			e.printStackTrace();
		}
 		
		 if("mord".equalsIgnoreCase(loginVO.getRoleName())){
			 
	    request.setAttribute("statedetail",AbsFinder.getdetailByCondition(StateVO.class, new String[]{"stateId",projectDetailsVO.getState().getStateId()}));
		 }else{
			 request.setAttribute("statedetail",AbsFinder.getdetailByCondition(StateVO.class, new String[]{"stateId",loginVO.getEntityCode()}));
		 }
	    // request.setAttribute("ProjectTypeList",(List<ProjectTypeMasterVO>) AbsFinder.getDropDownList(ProjectTypeMasterVO.class, new String[]{"projectTypeId","projectTypeName"}));
	    request.setAttribute("ProjectTypeList",(List<CtsaMasterVO>) AbsFinder.getDropDownList(CtsaMasterVO.class, new String[]{"id","ctsaName"}));
	    return mapping.findForward("editPage");
	}
	
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
        	ProjectDetailsDAO projectDetailsDAO = new ProjectDetailsDAO();
		ProjectDetailsForm  projectDetailsForm = (ProjectDetailsForm) form; 
		String forward= "showPage";

 	try {
	
 		request.setAttribute("formName","PROJECT DETAILS");	

 		ProjectDetailsVO projectDetailsVO = new ProjectDetailsVO();
 		projectDetailsVO.setFileNumber(projectDetailsForm.getFileNumber());
 		UserMaster userMaster=(UserMaster) AbsFinder.getdetailByCondition(UserMaster.class, new String[]{"loginId",projectDetailsForm.getPiaPrn()});
 		projectDetailsVO.setPiaPrn(userMaster);
 		projectDetailsVO.setProjectScheme(projectDetailsForm.getProjectScheme());
 		CtsaMasterVO projectTypeMasterVO=new CtsaMasterVO();
 		projectTypeMasterVO.setId(projectDetailsForm.getProjectType());
 		projectDetailsVO.setCtsaMaster(projectTypeMasterVO);
 		projectDetailsVO.setProjectID(projectDetailsForm.getProjectID());
 		projectDetailsVO.setTotalProjectCost(projectDetailsForm.getTotalProjectCost());
 		projectDetailsVO.setCentralShare(projectDetailsForm.getCentralShare());
 		projectDetailsVO.setStateShare(projectDetailsForm.getStateShare());
 		
 		if("YP".equalsIgnoreCase(projectDetailsForm.getProjectScheme())){
 			projectDetailsVO.setProgramCost(projectDetailsForm.getProgramCost());
 		}else{
 			projectDetailsVO.setProgramCost(projectDetailsForm.getTotalProjectCost());
 			
 		}
 		
 		//projectDetailsVO.setWelfareCost(projectDetailsForm.getWelfareCost());
 		projectDetailsVO.setConsortium(projectDetailsForm.getConsortium());
 		UserMaster userMaster1=(UserMaster) AbsFinder.getdetailByCondition(UserMaster.class, new String[]{"loginId",projectDetailsForm.getPrnOfConsortiumPartner()});
 		projectDetailsVO.setPrnOfConsortiumPartner(userMaster1);
 		projectDetailsVO.setPartnerApplicantPiaSame(projectDetailsForm.getPartnerApplicantPiaSame());
 		projectDetailsVO.setDateOfEc(projectDetailsForm.getDateOfEc());
 		projectDetailsVO.setSanctioned(projectDetailsForm.getSanctioned());
 		
 		projectDetailsVO.setCreatedBy(loginVO.getUserid());  
 		StateVO state=new StateVO();
 		state.setStateId(loginVO.getEntityCode());
 		projectDetailsVO.setState(state);
 		projectDetailsVO.setCreatedOn(new Date());
 		projectDetailsDAO.save(projectDetailsVO);
 		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
		assignProjectList.add(projectDetailsVO);
		request.getSession().setAttribute("assignProjectDetails",assignProjectList);
 		
 		
      }   catch (Exception e) {
    	  e.printStackTrace();
     }
  	    request.setAttribute("ViewList",(List<ProjectDetailsVO>) AbsFinder.getListByCondition(ProjectDetailsVO.class, new String[]{"createdBy",loginVO.getUserid()}));
	    request.setAttribute("formName","PROJECT DETAILS");	
	return mapping.findForward(forward);
}
	public ActionForward updateProjectDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
    ProjectDetailsDAO projectDetailsDAO = new ProjectDetailsDAO();
	ProjectDetailsForm  projectDetailsForm = (ProjectDetailsForm) form; 
	String forward= "showPage";
	try {
		
 		//request.setAttribute("PIAList", helperDao.getProjectDetailsList());	
		//request.setAttribute("ProjectTypeList", helperDao.getProjectTypeList());
	    request.setAttribute("formName","PROJECT DETAILS");	

 		ProjectDetailsVO projectDetailsVO =(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class,projectDetailsForm.getId());
 		projectDetailsVO.setFileNumber(projectDetailsForm.getFileNumber());
 		UserMaster userMaster=(UserMaster) AbsFinder.getdetailByCondition(UserMaster.class, new String[]{"loginId",projectDetailsForm.getPiaPrn()});
 		//UserMaster userMaster=(UserMaster) AbsFinder.getById(UserMaster.class, "4");
 		//System.out.println("userid--->   "+userMaster.getId());
 		projectDetailsVO.setPiaPrn(userMaster);
 		projectDetailsVO.setProjectScheme(projectDetailsForm.getProjectScheme());
 		//ProjectTypeMasterVO projectTypeMasterVO=(ProjectTypeMasterVO)AbsFinder.getById(ProjectTypeMasterVO.class, projectDetailsForm.getProjectType());
 		CtsaMasterVO projectTypeMasterVO=new CtsaMasterVO();
 		projectTypeMasterVO.setId(projectDetailsForm.getProjectType());
 		projectDetailsVO.setCtsaMaster(projectTypeMasterVO);
 		projectDetailsVO.setProjectID(projectDetailsForm.getProjectID());
 		projectDetailsVO.setTotalProjectCost(projectDetailsForm.getTotalProjectCost());
 		projectDetailsVO.setCentralShare(projectDetailsForm.getCentralShare());
 		projectDetailsVO.setStateShare(projectDetailsForm.getStateShare());
 		if("YP".equalsIgnoreCase(projectDetailsForm.getProjectScheme())){
 			projectDetailsVO.setProgramCost(projectDetailsForm.getProgramCost());
 		}else{
 			projectDetailsVO.setProgramCost(projectDetailsForm.getTotalProjectCost());
 			
 		}
 		//projectDetailsVO.setWelfareCost(projectDetailsForm.getWelfareCost());
 		projectDetailsVO.setConsortium(projectDetailsForm.getConsortium());
 		UserMaster userMaster1=(UserMaster) AbsFinder.getdetailByCondition(UserMaster.class, new String[]{"loginId",projectDetailsForm.getPrnOfConsortiumPartner()});
 		//UserMaster userMaster1=(UserMaster)AbsFinder.getById(UserMaster.class, "4");
 	//	System.out.println("userid 1--->   "+userMaster1.getId());
 		projectDetailsVO.setPrnOfConsortiumPartner(userMaster1);
 		projectDetailsVO.setPartnerApplicantPiaSame(projectDetailsForm.getPartnerApplicantPiaSame());
 		projectDetailsVO.setDateOfEc(projectDetailsForm.getDateOfEc());
 		projectDetailsVO.setSanctioned(projectDetailsForm.getSanctioned());
 		
 		projectDetailsVO.setUpdatedBy(loginVO.getUserid());  
 		projectDetailsVO.setUpdatedOn(new Date());
 		projectDetailsDAO.update(projectDetailsVO);
 		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
 	
 		ProjectDetailsVO updateproject=new ProjectDetailsVO();
 		for(ProjectDetailsVO vo:assignProjectList){
 			if(vo.getId().equalsIgnoreCase(projectDetailsVO.getId())){
 				//assignProjectList.remove(vo);
 				updateproject=vo;
 			}
 		}
 		assignProjectList.remove(updateproject);    //remove project
		assignProjectList.add(projectDetailsVO);    // add update project detail
		
		
		
		request.getSession().setAttribute("assignProjectDetails",assignProjectList);
      	
      }   catch (Exception e) {
    	  e.printStackTrace();
     }
	request.setAttribute("ViewList",(List<ProjectDetailsVO>) AbsFinder.getListByCondition(ProjectDetailsVO.class, new String[]{"createdBy",loginVO.getUserid()}));
	request.setAttribute("formName","PROJECT DETAILS");	
    return mapping.findForward(forward);
}
	
	
}
