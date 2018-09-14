package com.infotech.sgsy.master.specialArea;

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

public class SpecialAreaAction extends DispatchAction {

	private SpecialAreaDao specialAreaDaoImp = new SpecialAreaDao();

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{

		List<SpecialAreaVo> specialArealist = (List<SpecialAreaVo>) specialAreaDaoImp.getSpecialAreaList();

		request.setAttribute("specialAreaBean", new SpecialAreaVo());
		request.setAttribute("specialAreaList", specialArealist);
		return mapping.findForward("specialAreaMastershowPage");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		SpecialAreaForm specialareaForm=(SpecialAreaForm) form;
		SpecialAreaVo specialAreaVo=new SpecialAreaVo();
		BeanUtils.copyProperties(specialAreaVo,specialareaForm);
		specialAreaVo.setCreatedBy(loginVO.getUserid());
		specialAreaVo.setCreatedOn(new Date());
		specialAreaDaoImp.save(specialAreaVo);
		List<SpecialAreaVo> specialArealist = (List<SpecialAreaVo>) specialAreaDaoImp.getSpecialAreaList();
		request.setAttribute("specialAreaBean", new SpecialAreaVo());
		request.setAttribute("specialAreaList", specialArealist);

		return mapping.findForward("specialAreaMastershowPage");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("specialAreaId");
		SpecialAreaVo specialAreavo = (SpecialAreaVo) specialAreaDaoImp.getResultById(id);

		request.setAttribute("specialAreaBean", specialAreavo);
		List<SpecialAreaVo> specialArealist = (List<SpecialAreaVo>) specialAreaDaoImp.getSpecialAreaList();

		request.setAttribute("specialAreaList", specialArealist);
		return mapping.findForward("specialAreaMastershowPage");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		SpecialAreaForm specialareaForm=(SpecialAreaForm) form;
		
		specialAreaDaoImp.update(specialareaForm,loginVO);
		
		
		return mapping.findForward("specialAreaMastershowPage");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		 String message="delete succesfully";
		String id = request.getParameter("specialAreaId");
		try{
		specialAreaDaoImp.delete(id);
		}catch(Exception e){
			message= "unable to delete";
		}
		request.setAttribute("message",message );
		List<SpecialAreaVo> specialArealist = (List<SpecialAreaVo>) specialAreaDaoImp.getSpecialAreaList();
		request.setAttribute("specialAreaBean", new SpecialAreaVo());
		request.setAttribute("specialAreaList", specialArealist);
		return mapping.findForward("specialAreaMastershowPage");
	}

}
