package com.infotech.sgsy.stateSetup.hrTeam;

import java.util.ArrayList;
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
import com.infotech.sgsy.master.designationMaster.DesignationMasterVo;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.masterdata.projectTypeMaster.ProjectTypeMasterVO;
import com.infotech.sgsy.util.DateUtil;

public class HRTeamAction extends DispatchAction {

	HRTeamDAO helperDao = new HRTeamDAO();
	DateUtil dateUtil = new DateUtil();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		String forward= "showPage";
		HRTeamActionForm  helperForm = (HRTeamActionForm) form; 
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
			
			//LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
			List<HRTeamVO> list=new ArrayList<HRTeamVO>();
			//System.out.println("role name-->   "+loginVO.getRoleName());
			if("MORD".equalsIgnoreCase(loginVO.getRoleName())||"CTSA".equalsIgnoreCase(loginVO.getRoleName())){
				list=(List<HRTeamVO>) AbsFinder.getList(HRTeamVO.class);
				
			}else{
				list=(List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"state.stateId",loginVO.getEntityCode()});
			}
			
			request.setAttribute("EmployeesList",list);
			 request.setAttribute("designationList",AbsFinder.getDropDownList(DesignationMasterVo.class, new String[]{"designationId","designationName"}));
			 //request.setAttribute("name", "SRLM Name");
			 // request.setAttribute("nameValue", new StateDAO().getStateNameByCode(loginVO.getEntityCode()));
			/* request.setAttribute("nameValue", "AA");
			 */	
			 List<ProjectTypeMasterVO> themeticRoleList=new HRTeamDAO().getDetailProjectTypeName();
			 request.setAttribute("themeticRoleList", themeticRoleList);
			 request.setAttribute("formName","HR TEAM");
		    }
		catch(Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}		
		return mapping.findForward(forward);
	}
	
	   public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {
		  HRTeamActionForm helperForm = (HRTeamActionForm) form;
		   
	     try {
	    	 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
			    HRTeamVO helperVo = new HRTeamVO();
			    helperVo.setPersonName(helperForm.getPersonName());
			    helperVo.setLocation(helperForm.getLocation());
			    //DesignationMasterVo designationMasterVo=(DesignationMasterVo) AbsFinder.getById(DesignationMasterVo.class,helperForm.getDesignation() );
			    DesignationMasterVo designationMasterVo=new DesignationMasterVo();
			    designationMasterVo.setDesignationId(helperForm.getDesignation());
			    helperVo.setDesignation(designationMasterVo);
			    helperVo.setLocation(helperForm.getLocation());
			   
			    /*DistrictMasterVO districtMasterVO=new DistrictMasterVO();
			    districtMasterVO.setDistrictId(helperForm.getDistrictCode());
			    helperVo.setDistrict(districtMasterVO);*/
			    
			    if(helperForm.getDistrictCode()!=null){
		     		DistrictMasterVO districtMasterVO=new DistrictMasterVO();
		     		districtMasterVO.setDistrictId(helperForm.getDistrictCode());
					System.out.println("District Id is --> "+helperForm.getDistrictCode());
			    	helperVo.setDistrict(districtMasterVO);
		     	}
			   
			    ProjectTypeMasterVO thematicRole=new ProjectTypeMasterVO();
		     	thematicRole.setProjectTypeId(helperForm.getThematic());
		        helperVo.setThematic(thematicRole);
			    
			   
			    helperVo.setEmail(helperForm.getEmail());
			    helperVo.setOfficeNo(helperForm.getOfficeNo());
			    helperVo.setMobileNo(helperForm.getMobileNo());
			    helperVo.seteSopCertReq(helperForm.geteSopCertReq());
			    if("Yes".equals(helperForm.geteSopCertReq())){
			    	    helperVo.seteSopCertLevel(helperForm.geteSopCertLevel());
			    	    helperVo .setCertNo(helperForm.getCertNo());
			    		helperVo.setCertificationDate(helperForm.getCertificationDate());
			    }
			    helperVo.setIsActive(helperForm.getIsActive());
		     	helperVo.setJoiningDate(helperForm.getJoiningDate());
		     	if(helperForm.getIsActive()=="No"){
		     		helperVo.setDateOfLeaving(helperForm.getDateOfLeaving());	
		     	}
		     	helperVo.setCreatedBy(loginVO.getUserid());           //--------------------------------------------------------------------update when user module complete
		     	helperVo.setCreatedOn(new Date());
		     	//designationMasterVo.getHrteam().add(helperVo);
		     //	districtMasterVO.getHrteam().add(helperVo);
		     	StateVO state=new StateVO();
		     	state.setStateId(loginVO.getEntityCode());
		     	helperVo.setState(state);
		     	helperDao.save(helperVo);	
			
		      	helperForm.reset(mapping, request);
		      		
				//LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
				/*request.setAttribute("EmployeesList", helperDao.getHRTeamList(loginVO.getEntityCode()));*/
		      	List<HRTeamVO> list=new ArrayList<HRTeamVO>();
				//System.out.println("role name-->   "+loginVO.getRoleName());
		      	if("MORD".equalsIgnoreCase(loginVO.getRoleName())||"CTSA".equalsIgnoreCase(loginVO.getRoleName())){
					list=(List<HRTeamVO>) AbsFinder.getList(HRTeamVO.class);
					
				}else{
					list=(List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"state.stateId",loginVO.getEntityCode()});

					//list=(List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"createdBy",loginVO.getUserid()});
				}
				
				request.setAttribute("EmployeesList",list);
				request.setAttribute("designationList",AbsFinder.getDropDownList(DesignationMasterVo.class, new String[]{"designationId","designationName"}));
				/*request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(loginVO.getEntityCode()));*/
				 request.setAttribute("formName","HR TEAM");
				/* end */
	         } 
	     catch (Exception e) {
		    	  e.printStackTrace();
		     }
	     
		    return mapping.findForward("showPage"); 
	       }
	   
	   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {
		  HRTeamActionForm helperForm = (HRTeamActionForm) form;
		   
	     try {
	    	 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
			    HRTeamVO hrvo=helperDao.getDetailAboutHr(helperForm.getId());
			    hrvo.setJoiningDate(helperForm.getJoiningDate());
			    hrvo.setCertificationDate(helperForm.getCertificationDate());
			    DesignationMasterVo designationMasterVo=new DesignationMasterVo();
			    designationMasterVo.setDesignationId(helperForm.getDesignation());
			    hrvo.setDesignation(designationMasterVo);
		     	hrvo.setPersonName(helperForm.getPersonName());
		     	if(helperForm.getDistrictCode()!=null){
		     		DistrictMasterVO districtMasterVO=new DistrictMasterVO();
		     		districtMasterVO.setDistrictId(helperForm.getDistrictCode());
					System.out.println("District Id is --> "+helperForm.getDistrictCode());
			    	hrvo.setDistrict(districtMasterVO);
		     	}
		     	hrvo.setLocation(helperForm.getLocation());
		     	hrvo.setEmail(helperForm.getEmail());
		     	hrvo.setOfficeNo(helperForm.getOfficeNo());
		     	hrvo.setMobileNo(helperForm.getMobileNo());
		     	hrvo.seteSopCertLevel(helperForm.geteSopCertLevel()); 
		     	hrvo.seteSopCertReq(helperForm.geteSopCertReq());
		     	hrvo.setCertNo(helperForm.getCertNo());
		     	hrvo.setIsActive(helperForm.getIsActive());
		     	if(helperForm.getIsActive()=="No"){
		     		hrvo.setDateOfLeaving(helperForm.getDateOfLeaving());	
		     	}
		     	hrvo.setUpdatedBy(loginVO.getUserid());            //--------------------------------------------------------------------update when user module complete
		     	hrvo.setUpdatedOn(new Date());
		     	hrvo.setMobileNo(helperForm.getMobileNo());
		     	ProjectTypeMasterVO thematicRole=new ProjectTypeMasterVO();
		     	thematicRole.setProjectTypeId(helperForm.getThematic());
		     	hrvo.setThematic(thematicRole);
		     	hrvo.setId(helperForm.getId());
		     	System.err.println("hr id"+helperForm.getId());
		     	helperDao.update(hrvo);	
			
		      	helperForm.reset(mapping, request);
		      	List<HRTeamVO> list=new ArrayList<HRTeamVO>();
				if("MORD".equalsIgnoreCase(loginVO.getRoleName())||"CTSA".equalsIgnoreCase(loginVO.getRoleName())){
					list=(List<HRTeamVO>) AbsFinder.getList(HRTeamVO.class);
				}else{
					list=(List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"state.stateId",loginVO.getEntityCode()});
				}
				
				request.setAttribute("EmployeesList",list);
				request.setAttribute("designationList",AbsFinder.getDropDownList(DesignationMasterVo.class, new String[]{"designationId","designationName"}));
				request.setAttribute("formName","HR TEAM");
				 List<ProjectTypeMasterVO> themeticRoleList=new HRTeamDAO().getDetailProjectTypeName();
				 request.setAttribute("themeticRoleList", themeticRoleList);
	         } 
	     catch (Exception e) {
		    	  e.printStackTrace();
		     }
	     
		    return mapping.findForward("showPage"); 
	       }
	   
	   public ActionForward deletehr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {
		 String id=request.getParameter("id");
		 System.out.println(id);
		 try{
		 HRTeamVO helperVo =helperDao.getDetailAboutHr(id);
		
		 helperDao.deletehr(helperVo);
		 }catch(Exception e){
			 System.out.println("exception in hr team--> unable to delete");
		 }
		 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		 List<HRTeamVO> list=new ArrayList<HRTeamVO>();
			//System.out.println("role name-->   "+loginVO.getRoleName());
		 if("MORD".equalsIgnoreCase(loginVO.getRoleName())||"CTSA".equalsIgnoreCase(loginVO.getRoleName())){
				list=(List<HRTeamVO>) AbsFinder.getList(HRTeamVO.class);
				
			}else{
				list=(List<HRTeamVO>) AbsFinder.getListByCondition(HRTeamVO.class, new String[]{"createdBy",loginVO.getUserid()});
			}
			
			request.setAttribute("EmployeesList",list);
			request.setAttribute("designationList",AbsFinder.getDropDownList(DesignationMasterVo.class, new String[]{"designationId","designationName"}));
			 request.setAttribute("formName","HR TEAM");
		 
	     
		    return mapping.findForward("showPage"); 
	       }
	   public ActionForward getDistrictOfState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		   List<DistrictMasterVO> districtList = new ArrayList<DistrictMasterVO>();
		   LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		   HRTeamDAO hrdao=new HRTeamDAO();
		   System.out.println("=========>  "+loginVO.getEntityCode());
		   districtList=(List<DistrictMasterVO>) AbsFinder.getDropDownListByCondotion(DistrictMasterVO.class, new String[]{"districtId","districtName"}, new String[]{"state.stateId",loginVO.getEntityCode()});//loginVO.getUserid()
		   //DistrictDAO districtdao=new DistrictDAO();
		 //  districtList=hrdao.getDistrictByState(loginVO.getEntityCode());
		   StringBuffer districtlist = new StringBuffer();
		   if(districtList.size()>0){
			   System.out.println("district list---->   "+districtList.size());     
			   districtlist.append("<option value='0'>--Select--</option>");
				
				for(int i=0;i<districtList.size();i++){
					districtlist.append("<option value='" + districtList.get(i).getDistrictId() + "'>");
					districtlist.append(districtList.get(i).getDistrictName());
					districtlist.append("</option>");	
				}
			}else{
				
				System.out.println("district list---->   "+districtList.size());
				
				districtlist.append("<option value=''> --SELECT-- </option>");
			}
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(districtlist.toString());
		   
		    return null;
	    }
	   
	   public ActionForward getDetailEmail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		   List<HRTeamVO> list = new ArrayList<HRTeamVO>();
		  
		   HRTeamDAO hrteamdao=new HRTeamDAO();
		   list=hrteamdao.getDetailAboutEmail(request.getParameter("email"));
		 String rr=null;
		   if(list.size()>0){
			  rr="already exist";
		  }
		  else{
			 rr="not exist"; 
		  }
		   
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(rr);
		   
		    return null;
	    }
	   
	 /*  public ActionForward getDetailThematic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {   
		   List<HRTeamVO> list = new ArrayList<HRTeamVO>();
		  
		   HRTeamDAO hrteamdao=new HRTeamDAO();
		   list=hrteamdao.getDetailProjectTypeName(request.getParameter("thematic"));
		   StringBuffer projectNamelist = new StringBuffer();
		   if(list.size()>0){
			   System.out.println("Project Type Name list---->   "+list.size());     
			   projectNamelist.append("<option value='0'>--Select--</option>");
				
				for(int i=0;i<list.size();i++){
					
					projectNamelist.append(list.get(i).getThematic());
					projectNamelist.append("</option>");	
				}
			}else{
				
				System.out.println("district list---->   "+list.size());
				
				projectNamelist.append("<option value=''> --SELECT-- </option>");
			}
		    response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(projectNamelist.toString());
		   
		    return null;
	    }
	   */
	  /* <mapping class="com.websystique.hibernate.model.Student"/>*/
}
