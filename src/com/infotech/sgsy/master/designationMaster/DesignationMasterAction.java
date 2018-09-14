package com.infotech.sgsy.master.designationMaster;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;

public class DesignationMasterAction extends DispatchAction {
private DesignationMasterDao designationMasterDao=new DesignationMasterDao();
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) designationMasterDao.getDesignationList();
		System.out.println(designationMasterList);
		request.setAttribute("designationMasterList", designationMasterList);
		request.setAttribute("designationMasterVo", new DesignationMasterVo());
		return mapping.findForward("designationMastershowPage");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
		
		      DesignationMasterForm designationForm=(DesignationMasterForm) form;
		      
		      DesignationMasterVo desigantionMasterVo=new DesignationMasterVo();
		      desigantionMasterVo.setDesignationCode(designationForm.getDesignationCode());
		      desigantionMasterVo.setDesignationName(designationForm.getDesignationName());
		      desigantionMasterVo.setCreatedBy(loginVO.getUserid());
		      desigantionMasterVo.setCreatedOn(new Date());
		      designationMasterDao.save(desigantionMasterVo);
		List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) designationMasterDao.getDesignationList();
		request.setAttribute("designationMasterList", designationMasterList);
		request.setAttribute("designationMasterVo", new DesignationMasterVo());
		
		return mapping.findForward("designationMastershowPage");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//request.setAttribute("designationMasterVo", new DesignatioMasterVo());
		String designationId=request.getParameter("designationId");
		DesignationMasterVo designationMasterVo=(DesignationMasterVo) designationMasterDao.getById(DesignationMasterVo.class, designationId);
		request.setAttribute("designationMasterVo",designationMasterVo);
		
		
		List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) designationMasterDao.getDesignationList();
		request.setAttribute("designationMasterList", designationMasterList);
		
		return mapping.findForward("designationMastershowPage");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
		String message="update succesfully";
		try{
		DesignationMasterForm designationForm=(DesignationMasterForm) form;
		DesignationMasterVo designationMasterVo=(DesignationMasterVo) designationMasterDao.getById(DesignationMasterVo.class, designationForm.getDesignationId());
		designationMasterVo.setDesignationCode(designationForm.getDesignationCode());
		designationMasterVo.setDesignationName(designationForm.getDesignationName());
		designationMasterVo.setUpdateBy(loginVO.getUserid());
		designationMasterVo.setUpdatedOn(new Date());
		designationMasterDao.update(designationMasterVo);
		}catch(Exception e){
			
		}
		
		List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) designationMasterDao.getDesignationList();
		request.setAttribute("designationMasterList", designationMasterList);
		request.setAttribute("designationMasterVo", new DesignationMasterVo());
		return mapping.findForward("designationMastershowPage");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 String message="delete succesfully";
		 try{
		String designationid=request.getParameter("designationId");
		designationMasterDao.delete(designationid);
	} catch (Exception e) {
		message= "unable to delete";
	}
	    request.setAttribute("message",message );
		List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) designationMasterDao.getDesignationList();
		request.setAttribute("designationMasterList", designationMasterList);
		request.setAttribute("designationMasterVo", new DesignationMasterVo());
		return mapping.findForward("designationMastershowPage");
	}
	}

