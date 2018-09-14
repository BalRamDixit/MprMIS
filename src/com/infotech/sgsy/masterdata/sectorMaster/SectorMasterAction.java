package com.infotech.sgsy.masterdata.sectorMaster;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;

public class SectorMasterAction extends DispatchAction  {

	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {		 
 		
 		SectorMasterDAO helperDao = new SectorMasterDAO();
 		request.setAttribute("ViewList",helperDao.getAllRecords());
		return mapping.findForward("showSector");

					}
	
 	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
 		
		log.info("ProjectTypeMasterAction : Inside Edit method Starts======>");
		SectorMasterBean  Bean=(SectorMasterBean)form;
 		SectorMasterDAO helperDao = new SectorMasterDAO();
 		//request.setAttribute("projectTypeList", new SectorMasterDAO().getAllRecords());

		System.out.println("ProjectTypeMasterBean Id in edit --> "+Bean.getSectorId());
		SectorMasterVO helperVO=new SectorMasterDAO().getRecordFromId(Bean.getSectorId());
		if(helperVO!=null){
			BeanUtils.copyProperties(Bean,helperVO);
		}
  		Bean.setEditId(Bean.getSectorId());
		request.setAttribute("Bean", Bean);
 		request.setAttribute("ViewList",helperDao.getAllRecords());

		return mapping.findForward("editSector");
	}
	 public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	 		SectorMasterDAO helperDao = new SectorMasterDAO();

 		log.info("ProjectTypeMasterAction : Inside delet method Starts======>");
		SectorMasterBean  Bean = (SectorMasterBean) form; 
		SectorMasterVO helperVO=new SectorMasterVO();
 		BeanUtils.copyProperties(helperVO,Bean);
 		boolean status=new SectorMasterDAO().deleteRecordFromId(helperVO);
		System.out.println("status Is --> "+status);
 		request.setAttribute("ViewList",helperDao.getAllRecords());
			return mapping.findForward("showSector");
	} 
	 
	
	 
	 public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			log.info("ProjectTypeMasterAction : Inside update method Starts======>");
			SectorMasterBean  Bean=(SectorMasterBean)form;
	 		SectorMasterDAO helperDao = new SectorMasterDAO();

 			System.out.println(Bean);
			if(Bean.getEditId()!=null){
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				SectorMasterVO helperVO=new SectorMasterDAO().getRecordFromId(Bean.getEditId());
  				helperVO.setSectorCode(Bean.getSectorCode()); 
				helperVO.setSectorName(Bean.getSectorName()); 				
				helperVO.setUpdatedBy(loginVO.getUserid());
				helperVO.setUpdatedOn(DateUtil.getPresentDate());
				System.out.println("Object To Save Is --> "+helperVO);
				new SectorMasterDAO().saveOrUpdate(helperVO);
			}
			request.setAttribute("Bean",new SectorMasterBean());
	 		request.setAttribute("ViewList",helperDao.getAllRecords());
			return mapping.findForward("updateSector");
		} 
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		SectorMasterDAO helperDao = new SectorMasterDAO();
		SectorMasterBean  helperForm = (SectorMasterBean) form; 
 	 	try 
	 	{
	 		
 	 	SectorMasterVO helperVo = new SectorMasterVO();
     	BeanUtils.copyProperties(helperVo, helperForm);
     	helperVo.setCreatedBy("");   
     	helperVo.setCreatedOn(new Date());
      	helperDao.save(helperVo);	
      	helperForm.reset(mapping, request);
      	
       		    
	    
	      }   catch (Exception e) {
    	  e.printStackTrace();
     }
 		request.setAttribute("ViewList",helperDao.getAllRecords());
	   return mapping.findForward("showSector");
}
	
	
	
}
