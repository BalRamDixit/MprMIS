package com.infotech.sgsy.master.sanctionPost;

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

import com.infotech.sgsy.master.designationMaster.DesignationMasterVo;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterForm;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.specialArea.SpecialAreaVo;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.util.DateUtil;

public class SanctionPostAction extends DispatchAction {
	
	

	private SanctionPostDao sanctionPostDao = new SanctionPostDao();
	
	
	public ActionForward addSanctionPost(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        List<StateVO> stateList =(List<StateVO>) sanctionPostDao.getDropDownList(StateVO.class);
        request.setAttribute("stateList", stateList);
        List<DesignationMasterVo> designationMasterList =(List<DesignationMasterVo>) sanctionPostDao.getDropDownList(DesignationMasterVo.class);
        request.setAttribute("designationMasterList", designationMasterList);
        List<SanctionPostVO> sanctionPostList=(List<SanctionPostVO>)sanctionPostDao.getSanctionPostList();
        request.setAttribute("sanctionPostList", sanctionPostList);
		return mapping.findForward("showPage");
	}
	
public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SanctionPostForm sanctionPostForm=(SanctionPostForm) form;
        LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
        StateVO stateVo=(StateVO) sanctionPostDao.getById(StateVO.class, sanctionPostForm.getStateId());
        DesignationMasterVo designationMasterVo=(DesignationMasterVo)sanctionPostDao.getById(DesignationMasterVo.class,sanctionPostForm.getDesignationId());
        
        SanctionPostVO sanctionPostVO =new SanctionPostVO();
        sanctionPostVO.setState(stateVo);
        sanctionPostVO.setDesignationMaster(designationMasterVo);
        sanctionPostVO.setNoofPost(sanctionPostForm.getNoofPost());
        sanctionPostVO.setCreatedBy(loginVO.getUserid());
        sanctionPostVO.setCreatedDate(new Date());
        sanctionPostVO.setIsDeleted("N");
        System.out.println("Object To save is --> "+sanctionPostVO);
        sanctionPostDao.save(sanctionPostVO);
        
        List<StateVO> stateList =(List<StateVO>) sanctionPostDao.getDropDownList(StateVO.class);
        List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) sanctionPostDao.getDropDownList(DesignationMasterVo.class);
        List<SanctionPostVO> sanctionPostList=(List<SanctionPostVO>)sanctionPostDao.getSanctionPostList();
        System.out.println(stateList);
        request.setAttribute("stateList", stateList);
        request.setAttribute("designationMasterList", designationMasterList);
        request.setAttribute("sanctionPostList", sanctionPostList);
       
		return mapping.findForward("showPage");
	}
public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	try{
		String id=request.getParameter("id");
		System.out.println("Sanction Id--->"+id);
		SanctionPostVO vo=(SanctionPostVO) sanctionPostDao.getById(SanctionPostVO.class, id);
		System.out.println(vo);
		SanctionPostForm bean=new SanctionPostForm(vo);
		List<StateVO> stateList =(List<StateVO>) sanctionPostDao.getDropDownList(StateVO.class);
		List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) sanctionPostDao.getDropDownList(DesignationMasterVo.class);
		List<SanctionPostVO> sanctionPostList=(List<SanctionPostVO>)sanctionPostDao.getSanctionPostList();
		 
		/*List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) distristMasterDao.getDistrictList();*/
		System.out.println(stateList);
	    request.setAttribute("stateList", stateList);
	    request.setAttribute("designationMasterList", designationMasterList);
	    request.setAttribute("sanctionPostList",sanctionPostList);
	    System.out.println(bean);
	    request.setAttribute("sanctionDeatil", bean);
	}catch(Exception e){
	  e.printStackTrace();
	}
	return mapping.findForward("showPage");
}
public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	try{
	
		/*String id=request.getParameter("districtId");*/
		String id=request.getParameter("sanctionId");
		
		 /*DistrictMasterForm districtForm=(DistrictMasterForm) form;*/
		 SanctionPostForm sanctionPostForm=(SanctionPostForm) form;
	     LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	     System.out.println(sanctionPostForm);
	     StateVO stateVo=(StateVO) sanctionPostDao.getById(StateVO.class, sanctionPostForm.getStateId());
	     System.out.println("designation Id ---->"+sanctionPostForm.getDesignationId());
	     DesignationMasterVo designationMasterVo=(DesignationMasterVo)sanctionPostDao.getById(DesignationMasterVo.class,sanctionPostForm.getDesignationId());
	     System.out.println("designation Id ---->"+sanctionPostForm.getDesignationId());
	     System.out.println("designation Id ---->"+sanctionPostForm.getSanctionId());
	     SanctionPostVO sanctionPostVo=(SanctionPostVO) sanctionPostDao.getById(SanctionPostVO.class, sanctionPostForm.getSanctionId());
	     
	     sanctionPostVo.setUpdatedBy(loginVO.getUserName());
	     sanctionPostVo.setUpdatedDate(new Date());
	     sanctionPostVo.setState(stateVo);
	     sanctionPostVo.setDesignationMaster(designationMasterVo);
	     sanctionPostVo.setNoofPost(sanctionPostForm.getNoofPost());
	     sanctionPostDao.update(sanctionPostVo);
	}catch(Exception e){
		e.printStackTrace();
	}
	List<StateVO> stateList =(List<StateVO>) sanctionPostDao.getDropDownList(StateVO.class);
	List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) sanctionPostDao.getDropDownList(DesignationMasterVo.class);
	List<SanctionPostVO> sanctionPostList=(List<SanctionPostVO>)sanctionPostDao.getSanctionPostList();
	 

    request.setAttribute("stateList", stateList);
    request.setAttribute("designationMasterList", designationMasterList);
    request.setAttribute("sanctionPostList",sanctionPostList);
    
   /* request.setAttribute("districtDeatil", new DistrictMasterVO());*/
    
	return mapping.findForward("showPage");
}
public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	try{
	String id=request.getParameter("id");	
	System.out.println("Id of ----->"+id);
	/*String id=request.getParameter("districtId");*/
	sanctionPostDao.delete(id);
    }catch(Exception e){
	System.out.println("error in district master in deletion -- unable to delete");
    }
	List<StateVO> stateList =(List<StateVO>) sanctionPostDao.getDropDownList(StateVO.class);
	List<DesignationMasterVo> designationMasterList=(List<DesignationMasterVo>) sanctionPostDao.getDropDownList(DesignationMasterVo.class);
	List<SanctionPostVO> sanctionPostList=(List<SanctionPostVO>)sanctionPostDao.getSanctionPostList();
	 
    request.setAttribute("stateList", stateList);
    request.setAttribute("designationMasterList", designationMasterList);
    request.setAttribute("sanctionPostList",sanctionPostList);
	return mapping.findForward("showPage");
}
}
