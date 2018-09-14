package com.infotech.sgsy.tcSetup.tcSetupEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.parliamentaryConstituencyMaster.ParliamentaryConstituencyMasterVO;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.masterdata.constituencyMaster.ConstituencyMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetDAO;
import com.infotech.sgsy.stateSetup.hrTeam.HRTeamDAO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.util.DateUtil;


public class TcSetupEntryAction extends DispatchAction {

	TcSetUpEntryDAO tcSetUpEntryDAO = new TcSetUpEntryDAO ();

	DateUtil dateUtil = new DateUtil ();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");		
	//	TcSetupEntryActionForm helperForm = (TcSetupEntryActionForm) form;

	//	helperForm.setStateName("ANDHRA PRADESH");    
		
		String forward= "showPage";
		try{
			
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
			ArrayList<String> ids=new ArrayList<String>();
			for(ProjectDetailsVO project:sanctionProjectList){
				ids.add(project.getId());
			}
			List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
			
			request.setAttribute("tcList",tclist);	
			
			request.setAttribute("formName","TRAINING CENTER DETAIL");	

		}
		catch(Exception e){
			log.error("Exception come -- Add T-C Details " + e.getMessage());
		}
		return mapping.findForward(forward);
	}
	
	
	
	public ActionForward addDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		    //  TcSetUpEntryDAO tcSetUpEntryDAO = new TcSetUpEntryDAO ();
 		try {
 			    LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
 			   
 			 
 			    
 			   List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
 			   request.setAttribute("ProjectList", sanctionProjectList);	
					
					
				
				request.setAttribute("formName","TRADE TARGET");	

				
		}catch(Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}		
		
		return mapping.findForward("addDetail");
	}

	
	
	
	
	
	
	

	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		TcSetUpEntryDAO tcSetUpEntryDAO = new TcSetUpEntryDAO();
		
 		TcSetupEntryActionForm tcSetupEntryActionForm = (TcSetupEntryActionForm) form;
 		
          String id = request.getParameter("id");
		//System.err.println(projectId);
          TcSetupVO tcSetupVO = (TcSetupVO) AbsFinder.getById(TcSetupVO.class, id);
		
 		try{
 			
 			request.setAttribute("tcentry",tcSetupVO);	
 			 List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) AbsFinder.getDropDownListByCondotion(DistrictMasterVO.class, new String[]{"districtId","districtName"}, new String[]{"state.stateId",tcSetupVO.getState().getStateId()});
 			
 			request.setAttribute("district", districtList);
		     List<ConstituencyMasterVO> conlist=(List<ConstituencyMasterVO>) AbsFinder.getDropDownList(ConstituencyMasterVO.class, new String[]{"constituencyId","assemblyConstituencyName"});

 			request.setAttribute("assembly", conlist);
 			//=====List list=helperDao.getDistrictByStateName(helperVO.getStateName());
 			//helperVO.setDistrict(disName);
 		//	request.setAttribute("tcentry",helperVO );	
 			//============request.setAttribute("district", list);
 			
 			//=============request.setAttribute("assembly",helperDao.getAssemblyConBydis(helperVO.getDistrict()));
 			/*request.setAttribute("projectid", helperVO.getProjectID());
 		    request.setAttribute("statename", helperVO.getStateName());
 		    request.setAttribute("district", helperVO.getDistrict());
 		    request.setAttribute("address", helperVO.getAddress());
 		    request.setAttribute("pincode", helperVO.getPinCode());
 		    request.setAttribute("assemblycons", helperVO.getAssemblyCons());
 		    request.setAttribute("tclatitude", helperVO.getTcLatitude());
 		    request.setAttribute("tclongitude", helperVO.getTcLongitude());
 		    request.setAttribute("resistatus", helperVO.getResiStatus());
 		    request.setAttribute("inchargename", helperVO.getInChargeName());
 		    request.setAttribute("inchargeno", helperVO.getInChargeNo());
 		    request.setAttribute("inchargemobile", helperVO.getInChargeMobile());
 		    request.setAttribute("inchargeemail", helperVO.getInChargeEmail());
 		    request.setAttribute("tcid", helperVO.getTcID());
		    
			BeanUtils.copyProperties(helperForm, helperVO);*/
		 
		}
		catch(Exception e){
			 
			e.printStackTrace();
		}
 
		return mapping.findForward("editPage");
	}
	
	
	
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forward= "showPage";

		TcSetupEntryActionForm tcSetupEntryActionForm = (TcSetupEntryActionForm) form;	
 	try {
		TcSetupVO tcSetupVO = new TcSetupVO();
     	//BeanUtils.copyProperties(helperVO, helperForm);
     	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
     	ProjectDetailsVO project=new ProjectDetailsVO();
     	project.setId(tcSetupEntryActionForm.getProjectID());
     	tcSetupVO.setProject(project);
     	StateVO state=new StateVO();
     	state.setStateId(tcSetupEntryActionForm.getState());
     	tcSetupVO.setState(state);
     	DistrictMasterVO district=new DistrictMasterVO();
     	district.setDistrictId(tcSetupEntryActionForm.getDistrict());
     	tcSetupVO.setDistrict(district);
     	tcSetupVO.setAddress(tcSetupEntryActionForm.getAddress());
     	tcSetupVO.setPinCode(tcSetupEntryActionForm.getPinCode());
     	ConstituencyMasterVO constituencyMasterVO=new ConstituencyMasterVO();
     	constituencyMasterVO.setConstituencyId(tcSetupEntryActionForm.getAssemblyCons());
     	tcSetupVO.setAssemblyCons(constituencyMasterVO);
     	tcSetupVO.setTcLatitude(tcSetupEntryActionForm.getTcLatitude());
     	tcSetupVO.setTcLongitude(tcSetupEntryActionForm.tcLongitude);
     	tcSetupVO.setResiStatus(tcSetupEntryActionForm.getResiStatus());
     	tcSetupVO.setInChargeName(tcSetupEntryActionForm.getInChargeName());
     	tcSetupVO.setTcName(tcSetupEntryActionForm.getTcName());
		//public String inChargeNo;
     	tcSetupVO.setInChargeMobile(tcSetupEntryActionForm.getInChargeMobile());
     	tcSetupVO.setInChargeAltMobile(tcSetupEntryActionForm.getInChargeAltMobile());
     	tcSetupVO.setInChargeEmail(tcSetupEntryActionForm.getInChargeEmail());
     	tcSetupVO.setTcID(tcSetupEntryActionForm.getTcID());
     	
     	
     	
     	
     	tcSetupVO.setCreatedBy(loginVO.getUserid());           //--------------------------------------------------------------------update when user module complete
     	tcSetupVO.setCreatedOn(new Date());
     	tcSetUpEntryDAO.save(tcSetupVO);	
	
     	List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project1:sanctionProjectList){
			ids.add(project1.getId());
		}
		List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
		
		request.setAttribute("tcList",tclist);	
		request.setAttribute("formName","TRAINING CENTER DETAIL");	
      }   catch (Exception e) {
    	  e.printStackTrace();
     }
	return mapping.findForward(forward);
  }
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forward= "showPage";

		TcSetupEntryActionForm tcSetupEntryActionForm = (TcSetupEntryActionForm) form;	
 	try {
 		
 		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
 		//System.out.println(tcSetupEntryActionForm.getId());/
 		TcSetupVO tcSetupVO =(TcSetupVO) AbsFinder.getById(TcSetupVO.class, tcSetupEntryActionForm.getId());
     	ProjectDetailsVO project=new ProjectDetailsVO();
     	project.setId(tcSetupEntryActionForm.getProjectID());
     	tcSetupVO.setProject(project);
     	StateVO state=new StateVO();
     	state.setStateId(tcSetupEntryActionForm.getState());
     	tcSetupVO.setState(state);
     	DistrictMasterVO district=new DistrictMasterVO();
     	district.setDistrictId(tcSetupEntryActionForm.getDistrict());
     	tcSetupVO.setDistrict(district);
     	tcSetupVO.setAddress(tcSetupEntryActionForm.getAddress());
     	tcSetupVO.setPinCode(tcSetupEntryActionForm.getPinCode());
     	ConstituencyMasterVO constituencyMasterVO=new ConstituencyMasterVO();
     	constituencyMasterVO.setConstituencyId(tcSetupEntryActionForm.getAssemblyCons());
     	tcSetupVO.setAssemblyCons(constituencyMasterVO);
     	tcSetupVO.setTcLatitude(tcSetupEntryActionForm.getTcLatitude());
     	tcSetupVO.setTcLongitude(tcSetupEntryActionForm.tcLongitude);
     	tcSetupVO.setResiStatus(tcSetupEntryActionForm.getResiStatus());
     	tcSetupVO.setInChargeName(tcSetupEntryActionForm.getInChargeName());
		//public String inChargeNo;
     	tcSetupVO.setInChargeMobile(tcSetupEntryActionForm.getInChargeMobile());
     	tcSetupVO.setInChargeAltMobile(tcSetupEntryActionForm.getInChargeAltMobile());
     	tcSetupVO.setInChargeEmail(tcSetupEntryActionForm.getInChargeEmail());
     	tcSetupVO.setTcID(tcSetupEntryActionForm.getTcID());
     	tcSetupVO.setTcName(tcSetupEntryActionForm.getTcName());

     	
     	
     	
     	tcSetupVO.setCreatedBy(loginVO.getUserid());           //--------------------------------------------------------------------update when user module complete
     	tcSetupVO.setCreatedOn(new Date());
		
		tcSetUpEntryDAO.update(tcSetupVO);	
     	
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
		ArrayList<String> ids=new ArrayList<String>();
		for(ProjectDetailsVO project1:sanctionProjectList){
			ids.add(project1.getId());
		}
		List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
		
		request.setAttribute("tcList",tclist);		
		request.setAttribute("formName","TRAINING CENTER DETAIL");	
	
      
      }   catch (Exception e) {
    	  e.printStackTrace();
     }
	return mapping.findForward(forward);
  }
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forward= "showPage";

		TcSetupEntryActionForm helperForm = (TcSetupEntryActionForm) form;	
 	try {
 		
	String id=request.getParameter("id");
		TcSetupVO tcSetupVO =(TcSetupVO) AbsFinder.getById(TcSetupVO.class, id);
		tcSetUpEntryDAO.delete(tcSetupVO);	
		
      	helperForm.reset(mapping, request);
      }   catch (Exception e) {
    	  e.printStackTrace();
     }
 	List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	
	ArrayList<String> ids=new ArrayList<String>();
	for(ProjectDetailsVO project1:sanctionProjectList){
		ids.add(project1.getId());
	}
	List<TcSetupVO> tclist=(List<TcSetupVO>) AbsFinder.getListByProjectIds(TcSetupVO.class,"project.id", ids);
	
	request.setAttribute("tcList",tclist);	
		
	request.setAttribute("formName","TRAINING CENTER DETAIL");	
	return mapping.findForward(forward);
  }
	 public ActionForward getDistrictOfState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		  // List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		   String projectid=request.getParameter("projectid");
		 // LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		 //  TcSetUpEntryDAO hrdao=new TcSetUpEntryDAO();
		   ProjectDetailsVO projectDetailsVO=(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class, projectid);
		   UserMaster userMaster=(UserMaster) AbsFinder.getById(UserMaster.class, projectDetailsVO.getCreatedBy());
		   
		   StateVO stateVO=userMaster.getStateId();
		   List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) AbsFinder.getDropDownListByCondotion(DistrictMasterVO.class, new String[]{"districtId","districtName"}, new String[]{"state.stateId",stateVO.getStateId()});
		
		   StringBuffer districtlist = new StringBuffer();
		   if(districtList.size()>0){
			 //  System.out.println("district list---->   "+districtList.size());
			   districtlist.append("<option value='0'>--Select--</option>");
				
				for(int i=0;i<districtList.size();i++){
					districtlist.append("<option value='"+districtList.get(i).getDistrictId()+"'>");
					districtlist.append(districtList.get(i).getDistrictName());
					districtlist.append("</option>");	
				}
			}else{
				
				System.out.println("district list---->   "+districtList.size());
				
				districtlist.append("<option value=''> --SELECT-- </option>");
			}
		     districtlist.append("<~~~>");
		     String statename=stateVO.getStateName()+"<!!!>"+stateVO.getStateId();
		     System.out.println("state name--->   "+statename); 
		     districtlist.append(statename);
		   //  long srNo=hrdao.getSrNumber(projectid);
		     Integer srNo= 0;
		     districtlist.append("<~~~>");
		     districtlist.append(srNo);
		     
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(districtlist.toString());
		   
		    return null;
	    }
	 
	 
	  
	 
	 
	 public ActionForward getparliament(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
			String cons=request.getParameter("cons");
			   
			System.out.println("=========> "+cons);   
			 
			Set<ParliamentaryConstituencyMasterVO> list=(Set<ParliamentaryConstituencyMasterVO>)new TcSetUpEntryDAO().getParliamentConByDistrict(cons);
			StringBuffer ammlist = new StringBuffer();
			ammlist.append("<option value='0'>- Select -</option>");
			for(ParliamentaryConstituencyMasterVO ob:list){
				ammlist.append("<option value='"+ob.getId()+"'>"+ob.getName()+"</option>");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(ammlist.toString());
			
			return null;
	    }
	 
	 
	 
	 
	 
	 
	 public ActionForward getAssembly(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		  
		     String discode=request.getParameter("districtcode");
		     String projectId=request.getParameter("id");
		     System.out.println("project id ---->>     "+projectId);
		    // TcSetUpEntryDAO hrdao=new TcSetUpEntryDAO();
		   // List list= hrdao.getAssemblyConBydis(discode);
		     List<ConstituencyMasterVO> list=(List<ConstituencyMasterVO>)new TcSetUpEntryDAO().getAssemblyConBydis(discode);
		    StringBuffer ammlist = new StringBuffer();
		    ammlist.append("<option value='0'>- Select -</option>");
		    for(int i=0;i<list.size();i++){
		    //	Object[] ll=(Object[]) list.get(i);
		    	ammlist.append("<option value='"+list.get(i).getConstituencyId()+"'>"+list.get(i).getAssemblyConstituencyName()+"("+list.get(i).getParliamentaryConstituencyName().getName()+")"+"</option>");
		    }
		    if(projectId!=null){
			    DistrictMasterVO districtMasterVO=(DistrictMasterVO) AbsFinder.getById(DistrictMasterVO.class, discode);
			    ProjectDetailsVO projectDetailsVO=(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class, projectId);
			    Integer srNo= (Integer) AbsFinder.getcountUsingcondition(TcSetupVO.class, new String[]{"project.id",projectId});
			    srNo=srNo+1;
			    String sr="";
			    if(String.valueOf(srNo).length()==1){
			    	sr="00"+srNo;
			    }else if(String.valueOf(srNo).length()==2){
			    	sr="0"+srNo;
				}
			    ammlist.append("<~~~>"+projectDetailsVO.getProjectID()+districtMasterVO.getState().getStateCode()+districtMasterVO.getDistrictCode()+sr);
		    }
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(ammlist.toString());
		   
		    return null;
	    }
	  
	
}
