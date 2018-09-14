package com.infotech.sgsy.masterdata.constituencyMaster;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.parliamentaryConstituencyMaster.ParliamentaryConstituencyMasterVO;
import com.infotech.sgsy.util.DateUtil;

public class ConstituencyMasterAction extends DispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {		 
		ConstituencyMasterDAO constituencyMasterDAO = new ConstituencyMasterDAO();
 		request.setAttribute("ViewList",constituencyMasterDAO.getAllRecords());
 		request.setAttribute("stateList",constituencyMasterDAO.getAllState());
 		request.setAttribute("parliamentaryConstituencyList",constituencyMasterDAO.getAllParliamentaryConstituencyList());
		request.setAttribute("constituencyMasterBean", new ConstituencyMasterBean());
 		return mapping.findForward("showConstituency");
		

	}
	
	
public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
 		
		log.info("ProjectTypeMasterAction : Inside Edit method Starts======>");
		ConstituencyMasterBean  constituencyMasterBean=(ConstituencyMasterBean)form;
		ConstituencyMasterDAO constituencyMasterDAO = new ConstituencyMasterDAO();
 		request.setAttribute("ViewList", new ConstituencyMasterDAO().getAllRecords());

		System.out.println("ProjectTypeMasterBean Id in edit --> "+constituencyMasterBean.getConstituencyId());
		ConstituencyMasterVO constituencyMasterVO=new ConstituencyMasterDAO().getRecordFromId(constituencyMasterBean.getConstituencyId());
		if(constituencyMasterVO!=null){
			BeanUtils.copyProperties(constituencyMasterBean,constituencyMasterVO);
		}
		constituencyMasterBean.setEditId(constituencyMasterBean.getConstituencyId());
		constituencyMasterBean.setDistrictid(constituencyMasterVO.getDistrict().getDistrictId());
		request.setAttribute("constituencyMasterBean", constituencyMasterBean);
		request.setAttribute("ViewList",constituencyMasterDAO.getAllRecords());
		request.setAttribute("stateList",constituencyMasterDAO.getAllState());
		String state= constituencyMasterDAO.getDistrictById(constituencyMasterBean.getDistrictid());
		constituencyMasterBean.setState(state);
		List<DistrictMasterVO> list= constituencyMasterDAO.getDistrict(state);
		request.setAttribute("districtList",list);
				
		return mapping.findForward("editConstituency");
	}
	 public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConstituencyMasterDAO constituencyMasterDAO = new ConstituencyMasterDAO();
 		log.info("ProjectTypeMasterAction : Inside delet method Starts======>");
 		ConstituencyMasterBean  Bean = (ConstituencyMasterBean) form; 
 		ConstituencyMasterVO helperVO=new ConstituencyMasterVO();
 		BeanUtils.copyProperties(helperVO,Bean);
 		boolean status=new ConstituencyMasterDAO().deleteRecordFromId(helperVO);
		System.out.println("status Is --> "+status);
 		request.setAttribute("ViewList",constituencyMasterDAO.getAllRecords());
 		request.setAttribute("constituencyMasterBean", new ConstituencyMasterBean());
		return mapping.findForward("showConstituency");
	} 
	 public ActionForward getDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	 {   
		 System.out.println("request.getParameter...."+request.getParameter("state"));
		 ConstituencyMasterDAO constituencyMasterDAO = new ConstituencyMasterDAO();
		 List<DistrictMasterVO> list= constituencyMasterDAO.getDistrict(request.getParameter("state"));
		 response.setContentType("text/html");
		 response.setHeader("Cache-Control", "no-cache");
		 StringBuffer sb=new StringBuffer("<option value='0'> --SELECT-- </option>");
		 for(DistrictMasterVO ob:list){
			 sb.append("<option value='"+ob.getDistrictId()+"'>"+ob.getDistrictName()+"</option>");
		 }
		 response.getWriter().write(sb.toString());
		 return null;
	 } 
	
	 
	 public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			log.info("ProjectTypeMasterAction : Inside update method Starts======>");
			ConstituencyMasterBean  constituencyMasterBean=(ConstituencyMasterBean)form;
			ConstituencyMasterDAO constituencyMasterDAO = new ConstituencyMasterDAO();

 			System.out.println(constituencyMasterBean);
			if(constituencyMasterBean.getEditId()!=null){
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				ConstituencyMasterVO helperVO=new ConstituencyMasterDAO().getRecordFromId(constituencyMasterBean.getEditId());
				helperVO.setAssemblyConstituencyName(constituencyMasterBean.getAssemblyConstituencyName());
				helperVO.setAssemblyConstituencyCode(constituencyMasterBean.getAssemblyConstituencyCode());
				DistrictMasterVO districtMasterVO=new DistrictMasterVO();
		     	districtMasterVO.setDistrictId(constituencyMasterBean.getDistrictid());
		     	helperVO.setDistrict(districtMasterVO);
		     	
		     	ParliamentaryConstituencyMasterVO parliamentaryConstituencyMasterVO=new ParliamentaryConstituencyMasterVO();
		     	parliamentaryConstituencyMasterVO.setId(constituencyMasterBean.getParliamentaryConstituencyId());
		     	helperVO.setParliamentaryConstituencyName(parliamentaryConstituencyMasterVO);
				helperVO.setUpdatedBy(loginVO.getUserid());
				helperVO.setUpdatedOn(DateUtil.getPresentDate());
				System.out.println("Object To Save Is --> "+helperVO);
				new ConstituencyMasterDAO().saveOrUpdate(helperVO);
			}
			request.setAttribute("constituencyMasterBean", new ConstituencyMasterBean());
	 		request.setAttribute("ViewList",constituencyMasterDAO.getAllRecords());
	 		request.setAttribute("stateList",constituencyMasterDAO.getAllState());
			return mapping.findForward("updateConstituency");
		} 
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Save Process Started--> ");
		ConstituencyMasterDAO constituencyMasterDAO = new ConstituencyMasterDAO();
		ConstituencyMasterBean  constituencyMasterBean = (ConstituencyMasterBean) form; 
 	 	try 
	 	{
	 	 	ConstituencyMasterVO constituencyMasterVO = new ConstituencyMasterVO();
	 	 	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	     	BeanUtils.copyProperties(constituencyMasterVO, constituencyMasterBean);
	     	DistrictMasterVO districtMasterVO=new DistrictMasterVO();
	     	districtMasterVO.setDistrictId(constituencyMasterBean.getDistrictid());
	     	constituencyMasterVO.setDistrict(districtMasterVO);
	     	
	     	ParliamentaryConstituencyMasterVO parliamentaryConstituencyMasterVO=new ParliamentaryConstituencyMasterVO();
	     	parliamentaryConstituencyMasterVO.setId(constituencyMasterBean.getParliamentaryConstituencyId());
	     	constituencyMasterVO.setParliamentaryConstituencyName(parliamentaryConstituencyMasterVO);
	     	constituencyMasterVO.setCreatedBy(loginVO.getUserid());   
	     	constituencyMasterVO.setCreatedOn(new Date());
	     	System.out.println("Object To save is --> "+constituencyMasterVO);
	     	constituencyMasterDAO.save(constituencyMasterVO);	
	      	
	 	}catch (Exception e) {
	 		e.printStackTrace();
      	}
 	 	request.setAttribute("ViewList",constituencyMasterDAO.getAllRecords());
 	 	request.setAttribute("constituencyMasterBean", new ConstituencyMasterBean());
 	 	request.setAttribute("stateList",constituencyMasterDAO.getAllState());
 	 	return mapping.findForward("showConstituency");
	}
	
	
	
 }
