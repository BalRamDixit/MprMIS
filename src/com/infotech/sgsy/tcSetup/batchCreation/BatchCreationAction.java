package com.infotech.sgsy.tcSetup.batchCreation;

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
import com.infotech.sgsy.tcSetup.tcSetupDue.TcSetupDueVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeDAO;
import com.infotech.sgsy.util.DateUtil;

public class BatchCreationAction extends DispatchAction  {

DateUtil dateUtil = new DateUtil();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BatchCreationDAO helperDao = new BatchCreationDAO();

		String forward= "showPage";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
			String entity_code=loginVO.getEntityCode();
			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
 			request.setAttribute("projectlist",sanctionProjectList);
 			
 			 request.setAttribute("ViewList",helperDao.getdetails(sanctionProjectList)); 
 	 	
		
		return mapping.findForward(forward);
	}
	
	 public ActionForward showTCList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 BatchCreationDAO helperDao = new BatchCreationDAO();
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
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(list.toString());		   
		    return null;
	    }	 
	 
	 public ActionForward showStartDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		 
		 BatchCreationDAO helperDao = new BatchCreationDAO();
		 String projectid= request.getParameter("projectid"); 
		 List<TcSetupDueVO> startDate= helperDao.getStartDate(projectid);
		  if(startDate.size()>0)
		  {
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
 			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
 			
 			String date=null;
 			try{
 			Date vi_date=startDate.get(0).getVisitDate();
 			date=sdf.format(vi_date);
 			}catch(NullPointerException e){
 				date="no";
 			}
 			
			response.getWriter().write(date+""); 
		  }
		  else
		  { 
			  System.err.println("list is empty");
			  	
		  }
		    return null;
	    }
	 
	 
	   public ActionForward showSectorList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
		   {   
			 
			 BatchCreationDAO helperDao = new BatchCreationDAO();
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
		 
		 BatchCreationDAO helperDao = new BatchCreationDAO();
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
		 
			 BatchCreationDAO helperDao = new BatchCreationDAO();
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
			BatchCreationDAO helperDao = new BatchCreationDAO();
			BatchCreationForm  helperForm = (BatchCreationForm) form; 
			BatchCreationVO helperVo = new BatchCreationVO();
	 		 
	  			 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
 	  			
	  			List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
				List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
				 request.setAttribute("projectlist",sanctionProjectList);	 		 
			
			return mapping.findForward("addPage");
		}
	   
	   
		
		public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		 
			  BatchCreationDAO helperDao = new BatchCreationDAO();
			  BatchCreationForm  helperForm = (BatchCreationForm) form; 
			  BatchCreationVO helperVo = new BatchCreationVO();			
	          String id = request.getParameter("id"); 	
	          String tcid = request.getParameter("tcid"); 	
	          
	          System.err.println("edit id and tr id"+id+"--"+tcid);
   	     	  LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
 	  		  List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);			
	 		try{
			helperVo = helperDao.getBatchDetails(id);		
			BeanUtils.copyProperties(helperForm, helperVo);
			String projectID = helperVo.getProjectID(); 
			String man=helperForm.getProjectID(); 
 			 List<TcSetupTradeActionVO> trList=  helperDao.getList(tcid);
 			
			 
			 
		
 			 String myTcId="";
             String sector="";
			    String trade="";
 	 		/*if(trList.size()>0)
			  {
 	 			 
				System.out.println("tc is --> "+trList.get(0).getTrainningCenter());
  		 		request.setAttribute("myTcId",trList.get(0).getTrainningCenter());
		 		request.setAttribute("mySector",trList.get(0).getSector());
		 		request.setAttribute("myTrade",trList.get(0).getTrade()); 		 		
			  } */
			   // String t=trList.get(0).getTrainningCenter().getId();
			    String t=trList.get(0).getTrainningCenter().getId();

			    List<TcSetupVO> trTcList=  helperDao.getTradeTCList(t);
			    String trainingId=trTcList.get(0).getId();
 			    String prid=trTcList.get(0).getProject().getId();
 			    String s=trList.get(0).getSector().getSectorId();
			    String tr=trList.get(0).getTrade().getTradeId();
 			    request.setAttribute("myTcId",helperVo.getTcID().getId());				
 			    request.setAttribute("prid",prid); 
			  

			    request.setAttribute("projectlist",sanctionProjectList);		 		
		 		request.setAttribute("batchDetail",helperVo);
		 		request.setAttribute("ViewList", helperDao.getdetails(sanctionProjectList));
		 		request.setAttribute("projectID",projectID);
/*		 		List<SectorMasterVO> sectorlist=(List<SectorMasterVO>) new TcSetupTradeDAO().getSelected__sector__Of__Project(prid);
				getSectorByTradeId*/
  			 	List<SectorMasterVO> sectorlist=(List<SectorMasterVO>) new TcSetupTradeDAO().getSelected__sector__Of__Project(prid);
		 		List<TradeMasterVO> tradelist=( List<TradeMasterVO>) new TcSetupTradeDAO().getSelected__Trade__Of__Project_By_sector(prid, s);
				 
		 		
		 		TcSetupTradeActionVO listfortcTrade=helperDao.getSectorByTradeId(tcid);
/*
		 		System.err.println("sector man --"+listfortcTrade.getSector().getSectorName());
		 		System.err.println("trade man--"+listfortcTrade.getTrade().getTradeName());
		 		System.err.println("trade id"+listfortcTrade.getTrade().getTradeId());
		 		System.err.println("sector id"+listfortcTrade.getSector().getSectorId());*/
				  request.setAttribute("listfortcTrade",listfortcTrade);

		 		
				  request.setAttribute("mySector",s);
				    request.setAttribute("myTrade",tr); 
				    System.err.println("sector and trade"+s+"-"+tr);
				 System.err.println("list project"+prid);
			 	
	 			 List<TcSetupTradeActionVO> sectorList= helperDao.getSelected__sector__Of__tc(tcid);
	 			 List<TcSetupTradeActionVO> tradeList=(List<TcSetupTradeActionVO>) helperDao.getSelected__trade__Of__tc(tcid);
	 			 request.setAttribute("sectorlist", sectorList);
				 request.setAttribute("tradelist", tradeList); 
			 	
			      List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getDropDownListByCondotion(TcSetupVO.class, new String[]{"id","tcID"},new String[]{"project.id",prid});
			    ArrayList<String> ids=new ArrayList<String>();
			      for(TcSetupVO vo:tclist){
			    	  ids.add(vo.getId());
			    }
			      @SuppressWarnings("unchecked")
				List<TcSetupTradeActionVO> tctradelist=(List<TcSetupTradeActionVO>) AbsFinder.getListByProjectIds(TcSetupTradeActionVO.class, "trainningCenter.id", ids); 
			      request.setAttribute("tcList", tctradelist);
			 		

				    
			}
			catch(Exception e){
				 
				e.printStackTrace();
			}
		   	 		

		    
			return mapping.findForward("editPage");
		}
			  
		 public ActionForward deleteproject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
					throws Exception {			 
 			 	BatchCreationDAO helperDao = new BatchCreationDAO();
				BatchCreationForm  Bean = (BatchCreationForm) form; 
				BatchCreationVO helperVo = new BatchCreationVO();
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
		 			 System.err.println("in action edit");
				    BatchCreationDAO helperDao = new BatchCreationDAO();
					BatchCreationForm  helperForm = (BatchCreationForm) form; 
					BatchCreationVO helperVo = new BatchCreationVO(); 
					/*SectorMasterVO sectorMasterVO=new SectorMasterVO();
		 			sectorMasterVO.setSectorId(helperForm.getSector());
		 			TradeMasterVO tradeMasterVO=new TradeMasterVO();
		 			tradeMasterVO.setTradeId(helperForm.getTrade());*/
		 			TcSetupTradeActionVO tcSetupTradeActionVO=new TcSetupTradeActionVO();
		 			tcSetupTradeActionVO.setId(helperForm.getTcID());
		 			String updateddBy=loginVO.getUserid();

			try {
				
				 	//BeanUtils.copyProperties(helperVo, helperForm);
				 	
		 		 	//String id = request.getParameter("id");
				 	//String id=Integer.toString(helperVo.getId());
				 	
		 		 String id=	request.getParameter("id");
		 		 
 				 helperVo = helperDao.getBatchDetails(id);		 	
   		 	 
			 //	helperVo.setProjectID(helperForm.getProjectID());
	 		 	/*sectorMasterVO.setSectorId(helperForm.getSector());	
			    tradeMasterVO.setTradeId(helperForm.getTrade());*/
	 		 	helperVo.setBatchStartDate(helperForm.getBatchStartDate());
			 	helperVo.setBatchFreezeDate(helperForm.getBatchFreezeDate());
			 	helperVo.setBatchSize(helperForm.getBatchSize());
			 	///helperVo.setBatchEndDate(helperForm.getBatchEndDate()); 
				helperVo.setBatchDuration(helperForm.getBatchDuration());
		     	helperVo.setOjtStartDate(helperForm.getOjtStartDate());
		      
		     	//NEW ENTRY
		     //	helperVo.setBatchCommencedDate(helperForm.getBatchCommencedDate());
		     	helperVo.setBatchType(helperForm.getBatchType());
		     	helperVo.setResiSize(helperForm.getResiSize());
		     	helperVo.setOjtStartDate(helperForm.getOjtStartDate());		     	
		        helperVo.setCommenced_Sc(helperForm.getCommenced_Sc());
		     	helperVo.setCommenced_St(helperForm.getCommenced_St());
		     	helperVo.setCommenced_Others(helperForm.getCommenced_Others());
		     	helperVo.setCommenced_Women(helperForm.getCommenced_Women());
		     	helperVo.setCommenced_Minority(helperForm.getCommenced_Minority());
		     	helperVo.setCommenced_Pwd(helperForm.getCommenced_Pwd());
		     	helperVo.setCommenced_Total(helperForm.getCommenced_Total());

		     	
		     	
		     	
			 	
			 	helperVo.setUpdatedBy(updateddBy);      
			 	helperVo.setUpdatedOn(new Date());
	 		 	helperDao.update(helperVo);	
			  	helperForm.reset(mapping, request);
			  	List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
				  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		    request.setAttribute("ViewList",helperDao.getdetails(sanctionProjectList));

		  }   catch (Exception e) {
			  e.printStackTrace();
		 }
 		return mapping.findForward("showPage");
		}
	 	 
	 
		public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			System.err.println("action save");
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	 
			BatchCreationDAO batchCreationDAO = new BatchCreationDAO();
			BatchCreationForm  batchCreationForm = (BatchCreationForm) form; 
 			String createdBy=loginVO.getUserid();
 			/*SectorMasterVO sectorMasterVO=new SectorMasterVO();
 			sectorMasterVO.setSectorId(batchCreationForm.getSector());
 			TradeMasterVO tradeMasterVO=new TradeMasterVO();
 			tradeMasterVO.setTradeId(batchCreationForm.getTrade());*/
 			//TcSetupVO tcSetupVO=new TcSetupVO();
 			/*TcSetupTradeActionVO tcsetuptrade= new TcSetupTradeActionVO();
 			tcsetuptrade.setId(batchCreationForm.getTcID());
*/
 			//tcSetupVO.setId(batchCreationForm.getTcID());
 	 	try {
 	 		System.out.println("object To save is --> "+batchCreationForm);
 	 	 //	TcSetupTradeActionVO tcSetupTradeActionVO= batchCreationDAO.getTcSetupTradeActionVO(tcSetupVO,sectorMasterVO,tradeMasterVO);
 	 		
	 		BatchCreationVO helperVo = new BatchCreationVO();
	     	helperVo.setBatchStartDate(batchCreationForm.getBatchStartDate());
	     	helperVo.setBatchFreezeDate(batchCreationForm.getBatchFreezeDate());
	     //	helperVo.setBatchEndDate(batchCreationForm.getBatchEndDate());
	     	helperVo.setBatchID(batchCreationForm.getBatchID());
	     	helperVo.setBatchSize(batchCreationForm.getBatchSize());
	     	helperVo.setBatchDuration(batchCreationForm.getBatchDuration());
	     	helperVo.setOjtStartDate(batchCreationForm.getOjtStartDate());
	     	//NEW ENTRY
	     	//helperVo.setBatchCommencedDate(batchCreationForm.getBatchCommencedDate());
	     	helperVo.setBatchType(batchCreationForm.getBatchType());
	     	helperVo.setResiSize(batchCreationForm.getResiSize());
 	        helperVo.setCommenced_Sc(batchCreationForm.getCommenced_Sc());
	     	helperVo.setCommenced_St(batchCreationForm.getCommenced_St());
	     	helperVo.setCommenced_Others(batchCreationForm.getCommenced_Others());
	     	helperVo.setCommenced_Women(batchCreationForm.getCommenced_Women());
	     	helperVo.setCommenced_Minority(batchCreationForm.getCommenced_Minority());
	     	helperVo.setCommenced_Pwd(batchCreationForm.getCommenced_Pwd());
	     	helperVo.setCommenced_Total(batchCreationForm.getCommenced_Total());

  
	     	
 	     	helperVo.setCreatedBy(createdBy);  
 	     //	System.err.println("tcsetuptrade.getTrainningCenter().getId()"+tcsetuptrade.getTrainningCenter().getId());
 	     	TcSetupTradeActionVO tcID=new TcSetupTradeActionVO();
 	     	tcID.setId(batchCreationForm.getTcID());
 	     	
	     	helperVo.setTcID(tcID);
	     	helperVo.setCreatedOn(new Date());
	     	batchCreationDAO.save(helperVo);	
	     	batchCreationForm.reset(mapping, request);
		     // request.setAttribute("srNo", batchCreationDAO.getSrNumber());	
	     	List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");    // Help for getting Project list
			  List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);	
		 	  request.setAttribute("ViewList",batchCreationDAO.getdetails(sanctionProjectList));
			 	
	     
 	      }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
		return mapping.findForward("showPage");
	}
}
