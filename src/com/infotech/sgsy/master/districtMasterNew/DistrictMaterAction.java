package com.infotech.sgsy.master.districtMasterNew;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.specialArea.SpecialAreaVo;
import com.infotech.sgsy.master.state.StateVO;

public class DistrictMaterAction extends DispatchAction{
	
	
private DistrictMasterDAO distristMasterDao=new DistrictMasterDAO();
	
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		
		String[] parameter=new String[]{"stateId","stateName"};

		List<StateVO> stateList =(List<StateVO>) distristMasterDao.getDropDownList(StateVO.class,parameter);
		List<SpecialAreaVo> specialAreaList=(List<SpecialAreaVo>) distristMasterDao.getDropDownList(SpecialAreaVo.class,new String[]{"specialAreaId","specialAreaName"}); 
		List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) distristMasterDao.getDistrictList();
        request.setAttribute("statelist", stateList);
        request.setAttribute("specialAreaList", specialAreaList);
        request.setAttribute("districtList",districtList);
        request.setAttribute("districtDeatil", new DistrictMasterVO());
        
        
        
		return mapping.findForward("districtMastershowPage");
	}

public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
        DistrictMasterForm districtForm=(DistrictMasterForm) form;
        LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
        StateVO stateVo=(StateVO) distristMasterDao.getById(StateVO.class, districtForm.getState_id());
        SpecialAreaVo specialAreaVo=(SpecialAreaVo) distristMasterDao.getById(SpecialAreaVo.class, districtForm.getTypeOfSpecialArea());
        DistrictMasterVO districtMasterVO=new DistrictMasterVO();
        //BeanUtils.copyProperties(districtMasterVO,districtForm);
        districtMasterVO.setDistrictCode(districtForm.getDistrictCode());
        districtMasterVO.setDistrictName(districtForm.getDistrictName());
        districtMasterVO.setSpecialArea(districtForm.getSpecialArea());
        districtMasterVO.setState(stateVo);
        districtMasterVO.setTypeSpecialArea(specialAreaVo);
        districtMasterVO.setCreatedBy(loginVO.getUserid());
        districtMasterVO.setCreatedOn(new Date());
        /*stateVo.getDistrict().add(districtMasterVO);*/
       /* specialAreaVo.getDistrict().add(districtMasterVO);*/
        distristMasterDao.save(districtMasterVO);
        List<StateVO> stateList =(List<StateVO>) distristMasterDao.getDropDownList(StateVO.class,new String[]{"stateId","stateName"});
    	List<SpecialAreaVo> specialAreaList=(List<SpecialAreaVo>) distristMasterDao.getDropDownList(SpecialAreaVo.class,new String[]{"specialAreaId","specialAreaName"}); 
    	List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) distristMasterDao.getDistrictList();
        request.setAttribute("statelist", stateList);
        request.setAttribute("specialAreaList", specialAreaList);
        request.setAttribute("districtList",districtList);
        request.setAttribute("districtDeatil", new DistrictMasterVO());
		return mapping.findForward("districtMastershowPage");
	}
public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	try{
	String id=request.getParameter("districtId");
	DistrictMasterVO vo=(DistrictMasterVO) distristMasterDao.getById(DistrictMasterVO.class, id);
	List<StateVO> stateList =(List<StateVO>) distristMasterDao.getDropDownList(StateVO.class,new String[]{"stateId","stateName"});
	List<SpecialAreaVo> specialAreaList=(List<SpecialAreaVo>) distristMasterDao.getDropDownList(SpecialAreaVo.class,new String[]{"specialAreaId","specialAreaName"}); 
	List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) distristMasterDao.getDistrictList();
    request.setAttribute("statelist", stateList);
    request.setAttribute("specialAreaList", specialAreaList);
    request.setAttribute("districtList",districtList);
    request.setAttribute("districtDeatil", vo);
	}catch(Exception e){
		e.printStackTrace();
	}
	return mapping.findForward("districtMastershowPage");
}
public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	try{
	
		String id=request.getParameter("districtId");
	 DistrictMasterForm districtForm=(DistrictMasterForm) form;
     LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
     StateVO stateVo=(StateVO) distristMasterDao.getById(StateVO.class, districtForm.getState_id());
     SpecialAreaVo specialAreaVo=(SpecialAreaVo) distristMasterDao.getById(SpecialAreaVo.class, districtForm.getTypeOfSpecialArea());
     DistrictMasterVO districtMasterVO=(DistrictMasterVO) distristMasterDao.getById(DistrictMasterVO.class, districtForm.getDistrictId());
     //BeanUtils.copyProperties(districtMasterVO,districtForm);
     districtMasterVO.setDistrictCode(districtForm.getDistrictCode());
     districtMasterVO.setDistrictName(districtForm.getDistrictName());
     districtMasterVO.setSpecialArea(districtForm.getSpecialArea());
     districtMasterVO.setState(stateVo);
     districtMasterVO.setTypeSpecialArea(specialAreaVo);
     districtMasterVO.setUpdateBy(loginVO.getUserid());
     districtMasterVO.setUpdatedOn(new Date());
    /* stateVo.getDistrict().add(districtMasterVO);
     specialAreaVo.getDistrict().add(districtMasterVO);*/
     distristMasterDao.update(districtMasterVO);
	}catch(Exception e){
		e.printStackTrace();
	}
	//DistrictMasterVO vo=(DistrictMasterVO) distristMasterDao.getById(DistrictMasterVO.class, id);
	List<StateVO> stateList =(List<StateVO>) distristMasterDao.getDropDownList(StateVO.class,new String[]{"stateId","stateName"});
	List<SpecialAreaVo> specialAreaList=(List<SpecialAreaVo>) distristMasterDao.getDropDownList(SpecialAreaVo.class,new String[]{"specialAreaId","specialAreaName"}); 
	List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) distristMasterDao.getDistrictList();
    request.setAttribute("statelist", stateList);
    request.setAttribute("specialAreaList", specialAreaList);
    request.setAttribute("districtList",districtList);
    request.setAttribute("districtDeatil", new DistrictMasterVO());
    
	return mapping.findForward("districtMastershowPage");
}
public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	try{
	String id=request.getParameter("districtId");
	distristMasterDao.delete(id);
}catch(Exception e){
	System.out.println("error in district master in deletion -- unable to delete");
}
	//DistrictMasterVO vo=(DistrictMasterVO) distristMasterDao.getById(DistrictMasterVO.class, id);
	List<StateVO> stateList =(List<StateVO>) distristMasterDao.getDropDownList(StateVO.class,new String[]{"stateId","stateName"});
	List<SpecialAreaVo> specialAreaList=(List<SpecialAreaVo>) distristMasterDao.getDropDownList(SpecialAreaVo.class,new String[]{"specialAreaId","specialAreaName"}); 
	List<DistrictMasterVO> districtList=(List<DistrictMasterVO>) distristMasterDao.getDistrictList();
    request.setAttribute("statelist", stateList);
    request.setAttribute("specialAreaList", specialAreaList);
    request.setAttribute("districtList",districtList);
    request.setAttribute("districtDeatil", new DistrictMasterVO());
    
	return mapping.findForward("districtMastershowPage");
}


}
