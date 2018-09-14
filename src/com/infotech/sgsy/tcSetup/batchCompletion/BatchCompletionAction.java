package com.infotech.sgsy.tcSetup.batchCompletion;

import java.text.SimpleDateFormat;
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
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationDAO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;
import com.infotech.sgsy.tcSetup.tcSetupDue.TcSetupDueVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeDAO;

public class BatchCompletionAction extends DispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BatchCompletionDAO helperDao = new BatchCompletionDAO();
		String forward= "showPage";
		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
		request.setAttribute("ViewList",helperDao.getdetails(sanctionProjectList)); 
		return mapping.findForward(forward);
	}
	
	 
	 public ActionForward getBatchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 System.out.println("in a batch list");
		 BatchCompletionDAO helperDao = new BatchCompletionDAO();
		 String batchId= request.getParameter("batchId");	
		 System.out.println("batch id==> " + batchId);
		 List<BatchCreationVO> batchList=(List<BatchCreationVO>) helperDao.getBatchList(batchId);
		 
		   StringBuffer list = new StringBuffer();
		  
		   request.setAttribute("batchList",batchList); 
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(batchList.toString());		   
		    return null;
	    }	
	
	 public ActionForward showTCList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 BatchCompletionDAO helperDao = new BatchCompletionDAO();
		 String projectID= request.getParameter("projectid");		 
		 List<TcSetupTradeActionVO> tcList=(List<TcSetupTradeActionVO>) helperDao.getTC(projectID);
		 // List tcList= helperDao.getTCList(projectID);		 
		   StringBuffer list = new StringBuffer();
		   if(tcList.size()>0){
			   list.append("<option value='0'> --SELECT-- </option>");
			   for(int i=0;i<tcList.size();i++){
				// Object arr[]=  (Object[]) tcList.get(i);
				 list.append("<option value='"+tcList.get(i).getId()+"'>"+tcList.get(i).getTrainningCenter().getTcID()+"</option>");				
			   }			   
		   }
		    response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(list.toString());		   
		    return null;
	    }	 
	 
	 public ActionForward showStartDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 BatchCompletionDAO helperDao = new BatchCompletionDAO();
		 String projectid= request.getParameter("projectid"); 
		 List<TcSetupDueVO> startDate= helperDao.getStartDate(projectid);
		  if(startDate.size()>0)
		  {
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			String date=sdf.format(startDate.get(0).getVisitDate());
			response.getWriter().write(date+""); 
		  }
		  else
		  { 
			
			  	
		  }
		    return null;
	    }
	 
	 
	   public ActionForward showSectorList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
		   {   
			 
			 BatchCompletionDAO helperDao = new BatchCompletionDAO();
			 String tcid= request.getParameter("tcid");
			List<TcSetupTradeActionVO> sector= helperDao.getSelected__sector__Of__tc(tcid);
			 
	 		   StringBuffer list = new StringBuffer();
			   if(sector.size()>0){
				   list.append("<option value='0'> --SELECT-- </option>");
				   for(int i=0;i<sector.size();i++){
					// Object arr[]=  (Object[]) sectorList.get(i);
					 list.append("<option value='"+sector.get(i).getSector().getSectorId()+"'>"+sector.get(i).getSector().getSectorName()+"</option>");
					
				   }
				   
			   }
			    response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(list.toString());
			   
			    return null;
		    }
	   
	   public ActionForward showTradeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 BatchCompletionDAO helperDao = new BatchCompletionDAO();
		 String sector= request.getParameter("sector");
		 List<TradeMasterVO> trade=(List<TradeMasterVO>) helperDao.getSelected__Trade__Of__Project_By_sector(sector);
		 
		 // List tradeList= helperDao.getTradeList(sector);
		 
		 
		 
		   StringBuffer list = new StringBuffer();
		   if(trade.size()>0){
			   list.append("<option value='0'> --SELECT-- </option>");
			   for(int i=0;i<trade.size();i++){
				// Object arr[]=  (Object[]) tradeList.get(i);
				 list.append("<option value='"+trade.get(i).getTradeId()+"'>"+trade.get(i).getTradeName()+"</option>");
				
			   }
			   
		   }
		    response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(list.toString());
		   
		    return null;
	    }   
	   
	   
	   
	   public ActionForward showbatchId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
			 BatchCompletionDAO helperDao = new BatchCompletionDAO();
			 String sector= request.getParameter("sector");
			 String trade= request.getParameter("trade");
			 String tcid= request.getParameter("tcid");
			 String  mysector= "";
			 String myTrade= "";
			 String myTcid= "";
			 String myid="";
			 List<Object[]> batchList= helperDao.getbatchId(sector,trade,tcid); 
			 
				 for(Object[] temp :batchList) {
					 String tcidnew=temp[0]+"";	 
					 mysector= temp[0]+"";
					 myTrade= temp[1]+"";
					 myTcid= temp[2]+"";
				 }
				 myid=mysector+myTrade+myTcid ;
				 List ob=helperDao.getfinalId(myid);
				 
				 if(ob.size()==0 ){ 				 
				 myid=myid+"001";
				 }
				 else
				 {
					 if(ob!=null && ob.size()>=1 && ob.get(0)!=null){
						 String minebatch= ob.get(0).toString();					 
						 int length=minebatch.length();
						 String serialid=""+minebatch.charAt(length-2)+minebatch.charAt(length-1);
						 int mid=Integer.parseInt(serialid);
							 if(mid<9){ 			 
								myid=myid+"00"+(mid+1);				 				
							}
							else  
							{
								myid=myid+"0"+(mid+1);
							}	
					 }
				 } 
		 
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(myid);
		   
		    return null;
	    }  
	   
		public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			BatchCompletionDAO helperDao = new BatchCompletionDAO();
			BatchCompletionForm  helperForm = (BatchCompletionForm) form; 
			BatchCompletionVO helperVo = new BatchCompletionVO();
	 		 
	  			 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	  			
	  			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
				List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
				
				request.setAttribute("batchlist",helperDao.getCraetedBatchDetails(sanctionProjectList)); 
				
				request.setAttribute("batchCreationList",helperDao.getBatchDetails(sanctionProjectList)); 
				request.setAttribute("projectlist",sanctionProjectList);	 		 
			
				
			return mapping.findForward("addPage");
		}
	   
	   
		
		public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		 
			  BatchCompletionDAO helperDao = new BatchCompletionDAO();
			  BatchCompletionForm  helperForm = (BatchCompletionForm) form; 
			  BatchCompletionVO helperVo = new BatchCompletionVO();			
	          String id = request.getParameter("id"); 	
 	          
	         
 	     	  LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	  		  List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
	 		try{
			
		 		System.out.println("Id to Get Data IS==> "+id);
		 		BatchCompletionVO batchCompletionVO=new BatchCompletionDAO().getBatchCompletionById(id);
		 		System.out.println("Data to save is ====> "+batchCompletionVO);
		 		request.setAttribute("batchCompleteDetail", batchCompletionVO);
		 		BatchCreationVO batchCreationVO = helperDao.getBatchDetails(batchCompletionVO.getBatchID().getId());		
				BeanUtils.copyProperties(helperForm, batchCreationVO);

				String projectID = batchCreationVO.getProjectID(); 
				String man=helperForm.getProjectID(); 
				// List<TcSetupTradeActionVO> trList=  helperDao.getList(tcid);
				
				 
				 
			
				 String prid=batchCreationVO.getTcID().getTrainningCenter().getProject().getProjectID()+""
				 		+ "("+batchCreationVO.getTcID().getTrainningCenter().getState().getStateName()+")"+"-"+batchCreationVO.getTcID().getTrainningCenter().getProject().getPiaPrn().getUserName();
				 String tcID=batchCreationVO.getTcID().getTrainningCenter().getTcID()+"("+batchCreationVO.getTcID().getTrainningCenter().getDistrict().getDistrictName()+")";
				 String trade=batchCreationVO.getTcID().getTrade().getTradeName()+"("+batchCreationVO.getTcID().getTrade().getTradeCode()+")";
		 	 
				 				
				     request.setAttribute("prid",prid); 
				  
				     request.setAttribute("tcID",batchCreationVO.getTcID().getTrainningCenter().getTcID()); 
				     request.setAttribute("sector",batchCreationVO.getTcID().getSector().getSectorName()); 
				     request.setAttribute("trade",trade) ; 


				    request.setAttribute("projectlist",sanctionProjectList);		 		
			 		request.setAttribute("batchDetail",helperForm);
			 		request.setAttribute("ViewList", helperDao.getdetails(sanctionProjectList));
			 		request.setAttribute("projectID",projectID);
			}
			catch(Exception e){
				 
				e.printStackTrace();
			}
		   	 		

		    
			return mapping.findForward("editPage");
		}
			  
		 public ActionForward deleteproject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
					throws Exception {			 
			 	BatchCompletionDAO helperDao = new BatchCompletionDAO();
				BatchCompletionForm  Bean = (BatchCompletionForm) form; 
				BatchCompletionVO helperVo = new BatchCompletionVO();
				String id = request.getParameter("id");			 
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				String userID=  loginVO.getUserid(); 	
		 		try{
		 			helperVo.setId(id);
	 			 	//helperVo.setBatchEndDate(Bean.getBatchEndDate());
				 	helperVo.setBatchSize(Bean.getBatchSize());
			     	helperVo.setCreatedBy(userID);   
			     	helperVo.setCreatedOn(new Date());		  		
			 		boolean status=helperDao.deleteRecordFromId(helperVo);
			 		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
					  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
 				    request.setAttribute("ViewList",helperDao.getdetails(sanctionProjectList));				 
				}
				catch(Exception e){					 
					e.printStackTrace();
				}
		 		return mapping.findForward("showPage");
			}
		
		
		 	public ActionForward updateBatchDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
		 			
				    BatchCompletionDAO helperDao = new BatchCompletionDAO();
					BatchCompletionForm  BatchCompletionForm = (BatchCompletionForm) form; 
					
					String updateddBy=loginVO.getUserid();

			try {
				
				 	
		 		 String id=	request.getParameter("id");
		 		BatchCompletionVO helperVo =new BatchCompletionDAO().getBatchCompletionById(id);
 		 		helperVo.setBatchEndDate(BatchCompletionForm.getBatchEndDate());
		 		 
		 		helperVo.setComplet_Others(BatchCompletionForm.getComplet_Others());
		 		helperVo.setComplet_Minority(BatchCompletionForm.getComplet_Minority());
		 		helperVo.setComplet_Pwd(BatchCompletionForm.getComplet_Pwd());
		 		helperVo.setComplet_Sc(BatchCompletionForm.getComplet_Sc());
		 		helperVo.setComplet_St(BatchCompletionForm.getComplet_St());
		 		helperVo.setComplet_Total(BatchCompletionForm.getComplet_Total());
		 		helperVo.setComplet_Women(BatchCompletionForm.getComplet_Women());
		 		System.out.println("Object To Save is --> "+helperVo);
		     	
		     	
		     	
			 	
			 	helperVo.setUpdatedBy(updateddBy);      
			 	helperVo.setUpdatedOn(new Date());
	 		 	helperDao.update(helperVo);	
 			  	List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
				  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		    request.setAttribute("ViewList",helperDao.getdetails(sanctionProjectList));

		  }   catch (Exception e) {
			  e.printStackTrace();
		 }
		return mapping.findForward("showPage");
		}
	 	 
	 
		public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
			BatchCompletionDAO BatchCompletionDAO = new BatchCompletionDAO();
			
			BatchCompletionForm  BatchCompletionForm = (BatchCompletionForm) form; 
			String createdBy=loginVO.getUserid();
			 
	 	try {
	 		 
 	 		
	 		BatchCompletionVO helperVo = new BatchCompletionVO();
	 		helperVo.setBatchEndDate(BatchCompletionForm.getBatchEndDate());
	 		 
	 		helperVo.setComplet_Others(BatchCompletionForm.getComplet_Others());
	 		helperVo.setComplet_Minority(BatchCompletionForm.getComplet_Minority());
	 		helperVo.setComplet_Pwd(BatchCompletionForm.getComplet_Pwd());
	 		helperVo.setComplet_Sc(BatchCompletionForm.getComplet_Sc());
	 		helperVo.setComplet_St(BatchCompletionForm.getComplet_St());
	 		helperVo.setComplet_Total(BatchCompletionForm.getComplet_Total());
	 		helperVo.setComplet_Women(BatchCompletionForm.getComplet_Women());
	 		BatchCreationVO  batchcreationVo= new BatchCreationVO();
	 		batchcreationVo.setId(BatchCompletionForm.getBatchID());
	 		helperVo.setBatchID(batchcreationVo);
	 		helperVo.setBatchStatus("completed");
	     	helperVo.setCreatedBy(createdBy); 
 	     	helperVo.setCreatedOn(new Date());
 	     	System.out.println("===> "+helperVo);
	     	BatchCompletionDAO.save(helperVo);	
	     	BatchCompletionForm.reset(mapping, request);
 	     	List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		 	  request.setAttribute("ViewList",BatchCompletionDAO.getdetails(sanctionProjectList));
			 	
	     
	      }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
		return mapping.findForward("showPage");
	}
	
}
