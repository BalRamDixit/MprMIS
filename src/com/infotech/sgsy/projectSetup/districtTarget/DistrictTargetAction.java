package com.infotech.sgsy.projectSetup.districtTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import com.infotech.sgsy.util.DateUtil;
 

public class DistrictTargetAction extends DispatchAction {

	DateUtil dateUtil = new DateUtil();
	

	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DistrictTargetDAO districtTargetDAO = new DistrictTargetDAO();
		DistrictTargetActionForm districtTargetActionForm = (DistrictTargetActionForm) form;
		DistrictTargetVO districtTargetVO = new DistrictTargetVO();
		System.out.println("First Form Loaded");
		try {
				LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");	
				List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
				System.out.println("---**Project List---->"+assignProjectList);
				request.setAttribute("projectList", assignProjectList);
				districtTargetActionForm.setStateCode(loginVO.getEntityCode());
				districtTargetActionForm.setStateName("");
				request.setAttribute("formName","DISTRICT TARGET");	
				
		    }catch(Exception e) {
			     log.error("Exception come --  " + e.getMessage());
		  }		
		  return mapping.findForward("addDetail");
	     }
	
	
	public ActionForward checkForData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("projectidman");
		String target=request.getParameter("target");
		
		String extraValue=request.getParameter("extraValue");
		int extraValueData=0;
		if(extraValue!=null && !extraValue.equalsIgnoreCase("")){
			extraValueData=Integer.parseInt(extraValue);
		}
		DistrictTargetDAO helperDao = new DistrictTargetDAO();
		int c=Integer.parseInt(target);
		 String forward= "false";
         List list=helperDao.getSanctionCheck(id,target);
         if(list!=null && list.size()>0){
        	 Object aa[]=(Object[]) list.get(0);
        	 int a=  (int) aa[0] ;
        	 int b= (int) (aa[1] )+c-extraValueData;
        	 if(a>=b)
        	 {
        		 forward= "true";
        	 }
 		 }
         System.out.println("Forware is --> "+forward);
         response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(forward);
         return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DistrictTargetDAO districtTargetDAO = new DistrictTargetDAO();
		DistrictTargetActionForm districtTargetActionForm = (DistrictTargetActionForm) form;
		
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		DistrictTargetVO districtTargetVO = new DistrictTargetVO();
		 
		String forward= "addDetail";
		
		districtTargetActionForm.setEntityCode(loginVO.getEntityCode());
 		try {
 		 	districtTargetDAO.save(districtTargetActionForm,loginVO);	
 		 	List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
			System.out.println("---**Project List---->"+assignProjectList);
			request.setAttribute("projectList", assignProjectList);
			
	    }   
 		catch (Exception e) {
	    	e.printStackTrace();
	    }
 		
 		request.setAttribute("dis",new DistrictTargetVO());
 		return mapping.findForward(forward);
     } 
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		DistrictTargetDAO helperDao = new DistrictTargetDAO();
  		List<DistrictTargetVO> helperVo = new ArrayList<DistrictTargetVO>();
		String projectid = request.getParameter("id");
          System.out.println("project id --->"+projectid);
	      	helperVo = helperDao.getTradeTargetDetails(projectid);
	      	
	      	
	       List districtlist= helperDao.getDistrict(projectid);
 		try{
 			StringBuilder tab=new StringBuilder();
 			if(helperVo.size()>0){
 				for(int i=0;i<helperVo.size();i++){	
 					tab.append("<tr><td><select   name='district' id='districtId1' onchange='javascript:getSpecial(this,1)' class='form-control district'> ");
 					tab.append("<option value='0'>--SELECT--</option>");		
 					for(int p=0;p<districtlist.size();p++){
 						Object[] obj=(Object[]) districtlist.get(p);
						if(obj[0].equals(helperVo.get(i).getDistrict())){
							tab.append("<option value='"+obj[0]+"' selected>"+obj[1]+"</option>");
						}else{
							tab.append("<option value='"+obj[0]+"' >"+obj[1]+"</option>");
						}
						
					}
					tab.append("</select><input type='hidden' name='id' value='"+helperVo.get(i).getId()+"'></td>"+
	                "<td> <input type='text' name='isSpecial' id='isSpecialId1'  style='width: 95px;' readonly value='"+helperDao.getSpecial(helperVo.get(i).getDistrict()).get(0)+"'></td>"+
	                "<td> <input type='text' name='trainingTargetDist' class='trainingTargetDist' id='trainingTargetDistId' onkeypress='return isNumberKey(event)' value='"+helperVo.get(i).getTrainingTargetDist()+"' style='width: 95px;'></td>"+
	                   
	                "<td><input type='hidden' name='stateName' id='stateNameId' value='helperVo.get(i).getProjectId().getProjectId()'></td>"+
	                "<td><input name='Button' type='button'class='button checkPermissionForFormForInsert' value='Remove' onclick='removeRow(this)'></td></tr>");
 					
 			}
 				String updatebutton=" ";
 	 			request.setAttribute("updatebutton",updatebutton);
 			}
 			int target=helperDao.getSanctionTarget(projectid);
 			AbsFinder absFinder=new AbsFinder();
 			ProjectDetailsVO project=(ProjectDetailsVO)absFinder.getById(ProjectDetailsVO.class, projectid);
 			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
			System.out.println("---**Project List---->"+assignProjectList);
			request.setAttribute("projectList", assignProjectList);
 			request.setAttribute("projectID",projectid);
 			request.setAttribute("projectId",project.getProjectID());
 			request.setAttribute("rows",tab.toString());
 			request.setAttribute("projecttarget",target);
 		}
		catch(Exception e){
			 
			e.printStackTrace();
		}
 
		return mapping.findForward("editPage");
	}
	
   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
 		DistrictTargetActionForm  helperForm = (DistrictTargetActionForm) form; 
		DistrictTargetDAO helperDao = new DistrictTargetDAO();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		DistrictTargetVO helperVO = new DistrictTargetVO();
		String forward= "updateDetail";

 	try {
 		
 		List<DistrictTargetVO> list = helperDao.getTradeTargetDetails(helperForm.getProjectId());
		 String first[]=new String[list.size()];
		 for(int i=0;i<list.size();i++){
			 first[i]=list.get(i).getId(); 
		 }
		 String[] second=helperForm.getId();
		 List<String> missing = new ArrayList<String>(new HashSet<String>(Arrays.asList(first)));
		    for (String num : second) {
//		      missing.remove(num);
		      missing.remove(num);
		    }
		helperDao.deleteById(missing);
 		  helperDao.update(helperForm);	
       }   catch (Exception e) {
    	  e.printStackTrace();
     }
	return mapping.findForward(forward);
  }
	

	 public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		 DistrictTargetDAO helperDao = new DistrictTargetDAO();
		 DistrictTargetActionForm  helperForm = (DistrictTargetActionForm) form; 
		 
			String forward= "showPage";
	 	try {
	 		List<DistrictTargetVO> helperVo =helperDao.getTradeTargetDetails(request.getParameter("id"));
	 		
	 		   helperDao.delete(helperVo);	
	 		   helperForm.setEntityCode(loginVO.getEntityCode());
               request.setAttribute("ViewList", helperDao.view());

	      }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
		return mapping.findForward(forward);
	  }
	 //..for getting the district list
	 public ActionForward getDistrictDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	 {   
		 DistrictTargetDAO helperDao = new DistrictTargetDAO();
		 System.out.println(".....gggg"+request.getParameter("project_id"));
		   List list= helperDao.getDistrict(request.getParameter("project_id"));
		   StringBuffer disst = new StringBuffer();
		   if(list.size()>0){
			   disst.append("<option value='0'> --SELECT-- </option>");
			   for(int i=0;i<list.size();i++){
				 Object arr[]=  (Object[]) list.get(i);
				 disst.append("<option value='"+arr[0]+"'>"+arr[1]+"</option>");
				
			   }
			   
		   }
		    response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(disst.toString());
		   
		    return null;
	    }
//		ajax function for getting is special feild
		public ActionForward getSpecialDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
		   {   
			System.out.println("request.getParameter...."+request.getParameter("isSpecial"));
			
			DistrictTargetDAO helperDao = new DistrictTargetDAO();
			   List list= helperDao.getSpecial(request.getParameter("isSpecial"));
			    response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(list.get(0).toString());
			   
			    return null;
		    }
		public ActionForward getSanctionTarget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
		   {   
				System.out.println("projectid...."+request.getParameter("project_id"));
				DistrictTargetDAO helperDao = new DistrictTargetDAO();
				int target= helperDao.getSanctionTarget(request.getParameter("project_id"));
			
			    response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(target+"");
			    return null;
		    }
		public ActionForward getEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
		   {
				System.out.println("request.........."+request.getParameter("proj"));
				DistrictTargetDAO helperDao = new DistrictTargetDAO();
				int size= helperDao.viewList(request.getParameter("proj"));
				response.setContentType("text/xml");
				response.setHeader( "Pragma", "no-cache" );
				response.addHeader( "Cache-Control", "must-revalidate" );
				response.addHeader( "Cache-Control", "no-cache" );
				response.addHeader( "Cache-Control", "no-store" );
				response.setDateHeader("Expires", 0);
				response.getWriter().write(""+size);
				return null;		
		   }
	 ///...end
}
