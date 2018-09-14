package com.infotech.sgsy.ppws;

import java.util.ArrayList;
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
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetDAO;

public class PpwsSetupAction extends DispatchAction {


	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		    PpwsSetupActionDAO helperDao = new PpwsSetupActionDAO();
		    String forward= "showPage";		
		    //LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
		    
		   // request.setAttribute("ProjectList",(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		    List<ProjectDetailsVO> sslist=new ArrayList<ProjectDetailsVO>();
		  	try { 
		  		
				List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
				ArrayList<String> ids=new ArrayList<String>();
				for(ProjectDetailsVO project:sanctionProjectList){
					ids.add(project.getId());
				}
				List<SanctionDetailVO> sanction=new ArrayList<SanctionDetailVO>();
				if(ids.size()>0){
					sanction=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
				}
				for(SanctionDetailVO vo:sanction){
					ProjectDetailsVO project=new ProjectDetailsVO();
					try{
					project.setId(vo.getProjectId().getId());
					project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
					}catch (Exception e) {
						//e.printStackTrace();
						System.err.println("null pointer excption------- kamal");
					}
					sslist.add(project);
				}
				  			 	
		            }catch(Exception e) {
		            	e.printStackTrace();
				}
		  	request.setAttribute("ProjectList",sslist);
		    request.setAttribute("formName","PPWS SETUP");	
		    
 		return mapping.findForward(forward);
		
	}
	public ActionForward adddetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		    PpwsSetupActionDAO helperDao = new PpwsSetupActionDAO();
		    String forward= "addpage";		
		    LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
		    request.setAttribute("ProjectList", helperDao.getProjectByNotInPPWS());
		   
		    String entity_code=loginVO.getEntityCode();
			if(entity_code.length()>3){
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue",new TradeTargetDAO().getPIANameByCode(entity_code));
			}
			else 
				{
				
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
				}
		    request.setAttribute("formName","PPWS SETUP");	
 		return mapping.findForward(forward);
		
	}
	
			
	 public ActionForward getppwsDetailofProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		   String projectId=request.getParameter("projectId");
		   StringBuilder rr=new StringBuilder(); 
		    PpwsSetupActionDAO helperDao=new PpwsSetupActionDAO();
		    List<PpwsSetupActionVO> list=helperDao.getPPWSdetailbyProjectId(projectId);
		    rr.append("<table width='100%' class='inputTBL'> <tr ><th>Month<span class='text-error'></span></th><th>Year<span class='text-error'></span></th>"+
		    "<th>Expected Training Commencement<span class='text-error'></span></th><th>Expected Training Completion<span class='text-error'></span></th>"+
		    		"<th>Expected Placement<span class='text-error'></span></th> </tr>");
		   System.out.println("size of list=======>    "+list.size());
		   for(int i=0;i<list.size();i++){
			   rr.append("<tr>");
			   rr.append("<th>"+list.get(i).getEntryMonth()+"</th><th>"+list.get(i).getEntryYear()+"</th>"+
					   "<td>"+ list.get(i).getExpTrainComn()+"</td><td>"+ list.get(i).getExpTrainComp()+"</td>"+
					   "<td>"+ list.get(i).getPlaceExp()+"</td>");
			   rr.append("</tr>");
		   }
		    rr.append("</table>"); 
		    rr.append("<div align='center'><input name='Button' type='button' class='button checkPermissionForFormForInsert' id='edit' value='edit' onclick='javascript:editform()'/></div>");
		    response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(rr.toString());
		   
		    return null;
	    }		
	
	/*public ActionForward durationMonths(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));

		    PpwsSetupActionDAO helperDao = new PpwsSetupActionDAO();
		  	
		  String[] months=new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
		
		  StringBuilder rr=new StringBuilder(); 
		   String projectId =  request.getParameter("projectId");
		   List<PpwsSetupActionVO> list=helperDao.getPPWSdetailbyProjectId(projectId);
		   if(list.size()>0){
			   rr.append("<table width='100%' class='inputTBL'> <tr ><th>Entry-Month<span class='text-error'></span></th><th>Entry-Year<span class='text-error'></span></th>"+
					    "<th>Expected Training Commencement<span class='text-error'></span></th><th>Expected Training Completion<span class='text-error'></span></th>"+
					    		"<th>Expected Placement<span class='text-error'></span></th> </tr>");
					   System.out.println("size of list=======>    "+list.size());
					   for(int i=0;i<list.size();i++){
						   rr.append("<tr>");
						   rr.append("<th>"+list.get(i).getEntryMonth()+"<input type='hidden' data-test='"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' name='entryMonth' value='"+list.get(i).getEntryMonth()+"'><input type='hidden' name='id' value='"+list.get(i).getId()+"'/></th><th>"+list.get(i).getEntryYear()+"<input type='hidden' name='entryYear' value='"+list.get(i).getEntryYear()+"'></th>"+
								   "<td><input type='text' name='expTrainComn' id='expTrainComn"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+list.get(i).getExpTrainComn()+"'/></td><td><input type='text' name='expTrainComp'  onkeypress='return isNumberKey(event)' id='expTrainComp"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' value='"+ list.get(i).getExpTrainComp()+"'/></td>"+
								   "<td><input type='text' name='placeExp' id='placeExp"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+ list.get(i).getPlaceExp()+"' /></td>");
						   rr.append("</tr>");
					   }
					   rr.append("</table>");
					   rr.append("<div align='center'><div align='center' style='display: inline-block;'><input name='Button' type='button' class='button' id='update' value='update' onclick='javascript:editform()'/></div>");
					      rr.append("<div align='center' style='display: inline-block;'><input name='Button' type='button' class='button' id='back' value='back' onclick='javascript:backform()'/></div></div>");
					  
					   
		   }
		   else{
		   
		   
		   Object[] projectdetail=helperDao.getMonthDuration(projectId);
		   if(projectdetail==null){
			   
			   rr.append("Project is not sanction");
		   }
		   
		   else{
		  // System.out.println(projectdetail[0]+"------"+projectdetail[1]+"-----------"+projectdetail[2]);//2017-02-15
		   int month=(int) projectdetail[0];
		   int startmonth= Integer.parseInt(projectdetail[1].toString().substring(6, 7));
		   int endmonth=Integer.parseInt(projectdetail[2].toString().substring(6, 7));
		   int startyear=Integer.parseInt(projectdetail[1].toString().substring(0, 4));
		   int endyear=Integer.parseInt(projectdetail[2].toString().substring(0,4));
		   System.out.println("start month-->   "+startmonth+"   end month--->   "+endmonth+"   startyear-->   "+startyear+"    end year---->   "+endyear);
		
		   rr.append("<table width='100%' class='inputTBL'> <tr ><th>Entry-Month <span class='text-error'></span></th><th>Entry-Year<span class='text-error'></span></th>"+
				    "<th>Expected Training Commencement<span class='text-error'></span></th><th>Expected Training Completion<span class='text-error'></span></th>"+
				    		"<th>Expected Placement<span class='text-error'></span></th> </tr>");
		   for(int i=0;i<month;i++){
			   rr.append("<tr>");
			   startmonth++;
			   System.out.println("after increment--->    "+startmonth);
			   if(startmonth>12){
				   startmonth=Integer.parseInt("01");
				   startyear=startyear+1;
			   }
			   rr.append("<th>"+months[startmonth-1]+"<input type='hidden' data-test='"+months[startmonth-1]+""+startyear+"' name='entryMonth' value='"+months[startmonth-1]+"'></th>");
			   rr.append("<th>"+startyear+"<input type='hidden' name='entryYear' value='"+startyear+"'></th>");
			   rr.append("<td><span class='text-error'><input type='text' id='expTrainComn"+months[startmonth-1]+""+startyear+"' name='expTrainComn' onkeypress='return isNumberKey(event)' > </span></td>");
			   rr.append("<td><span class='text-error'><input type='text' id='expTrainComp"+months[startmonth-1]+""+startyear+"' name='expTrainComp'  onkeypress='return isNumberKey(event)' > </span></td>");
			   rr.append("<td><span class='text-error'><input type='text' id='placeExp"+months[startmonth-1]+""+startyear+"' name='placeExp'  onkeypress='return isNumberKey(event)' > </span></td>");
			   rr.append("</tr>");
		   }
		      rr.append("</table>");
		      rr.append("<div align='center'><div align='center' style='display: inline-block;'><input name='Button' type='button' class='button' id='save' value='save' onclick='javascript:saveform()'/></div>");
		      rr.append("<div align='center' style='display: inline-block;'><input name='Button' type='button' class='button' id='back' value='back' onclick='javascript:backform()'/></div></div>");
		   }
		   } 
		   response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(rr.toString());
		   
		    return null;		
	}*/
	 
	 
	 public ActionForward durationMonths(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			/*HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));*/

			    PpwsSetupActionDAO ppwsSetupActionDAO = new PpwsSetupActionDAO();
			   
			  String[] months=new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
			
			  StringBuilder rr=new StringBuilder(); 
			   String projectId =  request.getParameter("projectId");
			
			   List<PpwsSetupActionVO> list=(List<PpwsSetupActionVO>) AbsFinder.getListByConditionByAscOrder(PpwsSetupActionVO.class, new String[]{"project.id",projectId}, "id");
			   SanctionDetailVO sanctionDetailVO= (SanctionDetailVO) AbsFinder.getdetailByCondition(SanctionDetailVO.class, new String[]{"projectId.id",projectId});
			 //  Object[] projectdetail=ppwsSetupActionDAO.getMonthDuration(projectId);
			   if(sanctionDetailVO==null){
				   if(list.size()>0){
					  
					   ppwsSetupActionDAO.deleteByprojectId(projectId);
				   
				   }
				   rr.append("Project is not sanction"); 
				  
			   }else{
				//   boolean a=true;
				  
				   if(sanctionDetailVO.getProjectDuration()==0||sanctionDetailVO.getProjectDuration()==null){
					   rr.append("Project duration is null"); 
				   }
				   else if(sanctionDetailVO.getCommDate()==null){
					   rr.append("There is no Commencement Date"); 
				   }
				   else{
				   int month=sanctionDetailVO.getProjectDuration();
				   int startmonth=Integer.parseInt(sanctionDetailVO.getCommDate().toString().substring(6, 7));
				  // int endmonth=Integer.parseInt(sanctionDetailVO.getEndDate().toString().substring(6, 7));
				   int startyear=Integer.parseInt(sanctionDetailVO.getCommDate().toString().substring(0, 4));
				  // int endyear=Integer.parseInt(sanctionDetailVO.getEndDate().toString().substring(0,4));
				   
				//   int startmonth= Integer.parseInt(projectdetail[1].toString().substring(6, 7));
				  /* int endmonth=Integer.parseInt(projectdetail[2].toString().substring(6, 7));
				   int startyear=Integer.parseInt(projectdetail[1].toString().substring(0, 4));
				   int endyear=Integer.parseInt(projectdetail[2].toString().substring(0,4));*/
				  // System.out.println("start month-->   "+startmonth+"   end month--->   "+endmonth+"   startyear-->   "+startyear+"    end year---->   "+endyear);
				   rr.append("<table width='100%' class='inputTBL' id='tableid' > <tr ><th>Month <span class='text-error'></span></th><th>Year<span class='text-error'></span></th>"+
						    "<th>Expected Training Commencement<span class='text-error'></span></th><th>Expected Training Completion<span class='text-error'></span></th>"+
						    		"<th>Expected Placement<span class='text-error'></span></th> </tr>");
				   if(list.size()>0){
					   System.out.println(list.get(0).getEntryMonth()+"==="+months[startmonth]);
					   System.out.println( list.get(0).getEntryYear()+"======="+String.valueOf(startyear));
				   if(list.get(0).getEntryMonth().equals(months[startmonth]) && list.get(0).getEntryYear().equals(String.valueOf(startyear))){
					   if(month==list.size()){
						   for(int i=0;i<list.size();i++){
							   rr.append("<tr>");
							   rr.append("<th>"+list.get(i).getEntryMonth()+"<input type='hidden' data-test='"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' name='entryMonth' value='"+list.get(i).getEntryMonth()+"'><input type='hidden' name='id' value='"+list.get(i).getId()+"'/></th><th>"+list.get(i).getEntryYear()+"<input type='hidden' name='entryYear' value='"+list.get(i).getEntryYear()+"'></th>"+
									   "<td><input type='text' name='expTrainComn' id='expTrainComn"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+list.get(i).getExpTrainComn()+"'/></td><td><input type='text' name='expTrainComp'  onkeypress='return isNumberKey(event)' id='expTrainComp"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' value='"+ list.get(i).getExpTrainComp()+"'/></td>"+
									   "<td><input type='text' name='placeExp' id='placeExp"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+ list.get(i).getPlaceExp()+"' /></td>");
							   rr.append("</tr>");
						   } 
						  
					   }else if(month<list.size()){
						   int total_no_of_rows_for_deletion=list.size()-month;
						   ppwsSetupActionDAO.deleteLastRows(projectId,total_no_of_rows_for_deletion);
						   //delete last rows
						   
						   //List<PpwsSetupActionVO> newlist=ppwsSetupActionDAO.getPPWSdetailbyProjectId(projectId); 
						   List<PpwsSetupActionVO> newlist= (List<PpwsSetupActionVO>) AbsFinder.getListByConditionByAscOrder(PpwsSetupActionVO.class, new String[]{"project.id",projectId}, "id");
						   for(int i=0;i<newlist.size();i++){
							   rr.append("<tr>");
							   rr.append("<th>"+newlist.get(i).getEntryMonth()+"<input type='hidden' data-test='"+newlist.get(i).getEntryMonth()+""+newlist.get(i).getEntryYear()+"' name='entryMonth' value='"+newlist.get(i).getEntryMonth()+"'><input type='hidden' name='id' value='"+newlist.get(i).getId()+"'/></th><th>"+newlist.get(i).getEntryYear()+"<input type='hidden' name='entryYear' value='"+newlist.get(i).getEntryYear()+"'></th>"+
									   "<td><input type='text' name='expTrainComn' id='expTrainComn"+newlist.get(i).getEntryMonth()+""+newlist.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+newlist.get(i).getExpTrainComn()+"'/></td><td><input type='text' name='expTrainComp'  onkeypress='return isNumberKey(event)' id='expTrainComp"+newlist.get(i).getEntryMonth()+""+newlist.get(i).getEntryYear()+"' value='"+ newlist.get(i).getExpTrainComp()+"'/></td>"+
									   "<td><input type='text' name='placeExp' id='placeExp"+newlist.get(i).getEntryMonth()+""+newlist.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+newlist.get(i).getPlaceExp()+"' /></td>");
							   rr.append("</tr>");
						   } 
						   
					   }else{
						 int extra_row=month-list.size();  
						 for(int i=0;i<list.size();i++){
							   rr.append("<tr>");
							   rr.append("<th>"+list.get(i).getEntryMonth()+"<input type='hidden' data-test='"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' name='entryMonth' value='"+list.get(i).getEntryMonth()+"'><input type='hidden' name='id' value='"+list.get(i).getId()+"'/></th><th>"+list.get(i).getEntryYear()+"<input type='hidden' name='entryYear' value='"+list.get(i).getEntryYear()+"'></th>"+
									   "<td><input type='text' name='expTrainComn' id='expTrainComn"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+list.get(i).getExpTrainComn()+"'/></td><td><input type='text' name='expTrainComp'  onkeypress='return isNumberKey(event)' id='expTrainComp"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' value='"+ list.get(i).getExpTrainComp()+"'/></td>"+
									   "<td><input type='text' name='placeExp' id='placeExp"+list.get(i).getEntryMonth()+""+list.get(i).getEntryYear()+"' onkeypress='return isNumberKey(event)' value='"+ list.get(i).getPlaceExp()+"' /></td>");
							   rr.append("</tr>");
						   } 
						 int newStartMonth=0;
						 int newStartYear=Integer.parseInt(list.get(list.size()-1).getEntryYear());
						 for(int k=0;k<months.length;k++){
							 if(months[k].equals(list.get(list.size()-1).getEntryMonth())){
								 newStartMonth=k;
								 break;
							 }
						 }
						 newStartMonth++;
						 for(int j=0;j<extra_row;j++){
							 rr.append("<tr>");
							 newStartMonth++;
							   System.out.println("after increment new--->    "+newStartMonth);
							   if(startmonth>12){
								   newStartMonth=Integer.parseInt("01");
								   newStartYear=newStartYear+1;
							   }
							   rr.append("<th>"+months[newStartMonth-1]+"<input type='hidden' data-test='"+months[newStartMonth-1]+""+newStartYear+"' name='entryMonth' value='"+months[newStartMonth-1]+"'><input type='hidden' name='id' value='0'/></th>");
							   rr.append("<th>"+newStartYear+"<input type='hidden' name='entryYear' value='"+newStartYear+"'></th>");
							   rr.append("<td><span class='text-error'><input type='text' id='expTrainComn"+months[newStartMonth-1]+""+newStartYear+"' name='expTrainComn' onkeypress='return isNumberKey(event)' > </span></td>");
							   rr.append("<td><span class='text-error'><input type='text' id='expTrainComp"+months[newStartMonth-1]+""+newStartYear+"' name='expTrainComp'  onkeypress='return isNumberKey(event)' > </span></td>");
							   rr.append("<td><span class='text-error'><input type='text' id='placeExp"+months[newStartMonth-1]+""+newStartYear+"' name='placeExp'  onkeypress='return isNumberKey(event)' > </span></td>");
							  
							 rr.append("</tr>");
						 }  
					   }
					   rr.append("</table>");
					   rr.append("<div align='center'><div align='center' style='display: inline-block;'><input name='Button' type='button' class='button checkPermissionForFormForInsert' id='update' value='update' onclick='javascript:editform()'/></div>");
					   rr.append("<div align='center' style='display: inline-block;'><input name='Button' type='button' class='button' id='back' value='back' onclick='javascript:backform()'/></div></div>");
					   
				   }
				   else{
					   ppwsSetupActionDAO.deleteByprojectId(projectId);
					   //delete all rows from ppws details of selected project -----------------
				   }
			   }else{
				   for(int i=0;i<month;i++){
					   rr.append("<tr>");
					   startmonth++;
					  // System.out.println("after increment--->    "+startmonth);
					   if(startmonth>12){
						   startmonth=Integer.parseInt("01");
						   startyear=startyear+1;
					   }
					   rr.append("<th>"+months[startmonth-1]+"<input type='hidden' data-test='"+months[startmonth-1]+""+startyear+"' name='entryMonth' value='"+months[startmonth-1]+"'></th>");
					   rr.append("<th>"+startyear+"<input type='hidden' name='entryYear' value='"+startyear+"'></th>");
					   rr.append("<td><span class='text-error'><input type='text' id='expTrainComn"+months[startmonth-1]+""+startyear+"' name='expTrainComn' onkeypress='return isNumberKey(event)' > </span></td>");
					   rr.append("<td><span class='text-error'><input type='text' id='expTrainComp"+months[startmonth-1]+""+startyear+"' name='expTrainComp'  onkeypress='return isNumberKey(event)' > </span></td>");
					   rr.append("<td><span class='text-error'><input type='text' id='placeExp"+months[startmonth-1]+""+startyear+"' name='placeExp'  onkeypress='return isNumberKey(event)' > </span></td>");
					   rr.append("</tr>");
				   }
				      rr.append("</table>");
				      rr.append("<div align='center'><div align='center' style='display: inline-block;'><input name='Button' type='button' class='button checkPermissionForFormForInsert' id='save' value='save' onclick='javascript:saveform()'/></div>");
				      rr.append("<div align='center' style='display: inline-block;'><input name='Button' type='button' class='button' id='back' value='back' onclick='javascript:backform()'/></div></div>");
				 
			   }
				rr.append("^^^"+sanctionDetailVO.getCommDate()+"<--->"+sanctionDetailVO.getPlacementcompletion()+"<--->"+sanctionDetailVO.getSanTarget());   
			   }
			   }
			   
			   response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(rr.toString());
			   
			    return null;		
		}
	
	 public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
			String forward= "showPage";		
			PpwsSetupActionDAO ppwsSetupActionDAO = new PpwsSetupActionDAO();

         
         	PpwsSetupActionForm ppwsSetupActionForm = (PpwsSetupActionForm) form;
         	//System.out.println("Size of list =======>  "+helperForm.getEntryMonth()[0]);
	   
  	try {
	     List<PpwsSetupActionVO> list=new ArrayList<PpwsSetupActionVO>();
	     	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
	     	for(int i=0;i<ppwsSetupActionForm.getEntryMonth().length;i++){
				PpwsSetupActionVO ppwsSetupActionVO=new PpwsSetupActionVO();
				
				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
				projectDetailsVO.setId(ppwsSetupActionForm.getProjectId());
				ppwsSetupActionVO.setProject(projectDetailsVO);
				ppwsSetupActionVO.setEntryMonth(ppwsSetupActionForm.getEntryMonth()[i]);
				ppwsSetupActionVO.setEntryYear(ppwsSetupActionForm.getEntryYear()[i]);
				ppwsSetupActionVO.setExpTrainComn(ppwsSetupActionForm.getExpTrainComn()[i]);
				ppwsSetupActionVO.setExpTrainComp(ppwsSetupActionForm.getExpTrainComp()[i]);
				ppwsSetupActionVO.setPlaceExp(ppwsSetupActionForm.getPlaceExp()[i]);
				ppwsSetupActionVO.setCreatedBy(loginVO.getUserid());
				ppwsSetupActionVO.setCreatedOn(new Date());
				list.add(ppwsSetupActionVO);
				
			}
		   
	     	ppwsSetupActionDAO.saveOrUpdate(list);
	
       }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
  	//request.setAttribute("ProjectList", (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
  	List<ProjectDetailsVO> sslist=new ArrayList<ProjectDetailsVO>();
  	try { 
  		
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project:sanctionProjectList){
			ids.add(project.getId());
		}
		List<SanctionDetailVO> sanction=new ArrayList<SanctionDetailVO>();
		if(ids.size()>0){
			sanction=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
		}
		for(SanctionDetailVO vo:sanction){
			ProjectDetailsVO project=new ProjectDetailsVO();
			try{
			project.setId(vo.getProjectId().getId());
			project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
			}catch (Exception e) {
				//e.printStackTrace();
				System.err.println("null pointer excption------- kamal");
			}
			sslist.add(project);
		}
		  			 	
            }catch(Exception e) {
            	e.printStackTrace();
		}
  	request.setAttribute("ProjectList",sslist);
	return mapping.findForward(forward);
}
	 
	 public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
			String forward= "editpage";		
			PpwsSetupActionForm helperVo= (PpwsSetupActionForm) form;
			PpwsSetupActionDAO ppwsSetupActionDAO = new PpwsSetupActionDAO();
	try {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
		    List<PpwsSetupActionVO> list=ppwsSetupActionDAO.getPPWSdetailbyProjectId(helperVo.getProjectId());
		    request.setAttribute("ppwsdetail", list);
		    String entity_code=loginVO.getEntityCode();
			if(entity_code.length()>3){
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue",new TradeTargetDAO().getPIANameByCode(entity_code));
			}
			else 
				{
				
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
				}
		request.setAttribute("projectid",helperVo.getProjectId());
    }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
	return mapping.findForward(forward);
}
	 
	 public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
			String forward= "showPage";		
			PpwsSetupActionForm ppwsSetupActionForm= (PpwsSetupActionForm) form;
			PpwsSetupActionDAO ppwsSetupActionDAO = new PpwsSetupActionDAO();
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
	try {
		List<PpwsSetupActionVO> list =new ArrayList<PpwsSetupActionVO>();
		for(int i=0;i<ppwsSetupActionForm.getId().length;i++){
			System.out.println(ppwsSetupActionForm.getId());
			PpwsSetupActionVO ppwsSetupActionVO=(PpwsSetupActionVO) AbsFinder.getById(PpwsSetupActionVO.class, String.valueOf(ppwsSetupActionForm.getId()[i]));
			if(ppwsSetupActionVO==null){
				ppwsSetupActionVO=new PpwsSetupActionVO();
				//ProjectDetailsVO projectDetailsVO=(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class, ppwsSetupActionForm.getProjectId());
				ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
				projectDetailsVO.setId(ppwsSetupActionForm.getProjectId());
				ppwsSetupActionVO.setProject(projectDetailsVO);
				ppwsSetupActionVO.setCreatedBy(loginVO.getUserid());
				ppwsSetupActionVO.setCreatedOn(new Date());
			}else{
				ppwsSetupActionVO.setUpdatedBy(loginVO.getUserid());
				ppwsSetupActionVO.setUpdatedOn(new Date());
			}
			ppwsSetupActionVO.setEntryMonth(ppwsSetupActionForm.getEntryMonth()[i]);
			ppwsSetupActionVO.setEntryYear(ppwsSetupActionForm.getEntryYear()[i]);
			ppwsSetupActionVO.setExpTrainComn(ppwsSetupActionForm.getExpTrainComn()[i]);
			ppwsSetupActionVO.setExpTrainComp(ppwsSetupActionForm.getExpTrainComp()[i]);
			ppwsSetupActionVO.setPlaceExp(ppwsSetupActionForm.getPlaceExp()[i]);
		
			list.add(ppwsSetupActionVO);
		}
		ppwsSetupActionDAO.saveOrUpdate(list);
		

 }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
//	request.setAttribute("ProjectList", (List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
	List<ProjectDetailsVO> sslist=new ArrayList<ProjectDetailsVO>();
  	try { 
  		
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project:sanctionProjectList){
			ids.add(project.getId());
		}
		List<SanctionDetailVO> sanction=new ArrayList<SanctionDetailVO>();
		if(ids.size()>0){
			sanction=(List<SanctionDetailVO>) AbsFinder.getListByProjectIds(SanctionDetailVO.class,"projectId.id", ids);
		}
		for(SanctionDetailVO vo:sanction){
			ProjectDetailsVO project=new ProjectDetailsVO();
			try{
			project.setId(vo.getProjectId().getId());
			project.setProjectID(vo.getProjectId().getProjectID()+" ("+vo.getProjectId().getState().getStateName()+") - "+vo.getProjectId().getPiaPrn().getUserName());
			}catch (Exception e) {
				//e.printStackTrace();
				System.err.println("null pointer excption------- kamal");
			}
			sslist.add(project);
		}
		  			 	
            }catch(Exception e) {
            	e.printStackTrace();
		}
  	request.setAttribute("ProjectList",sslist);
	return mapping.findForward(forward);
}

	
	
}

