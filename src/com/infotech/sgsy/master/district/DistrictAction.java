package com.infotech.sgsy.master.district;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class DistrictAction extends   MasterAction {
	
	protected final Log log = LogFactory.getLog(getClass());
// Used for District Identification for Interest Subvention
	
	public ActionForward showAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		log.error("::::::: Interest Subvention ::::::");
		String requestPage="DistISIdentification";
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		LocationVO locationVO=(LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
		DistrictForm districtform = (DistrictForm)form;
		List<DistrictForm> districtLists = getDistrictList(loginVO.getEntityCode());
		districtform.setDistrictList(districtLists);
		request.setAttribute("districtform", districtform);

		return mapping.findForward(requestPage);
	}
	
	public List<DistrictForm> getDistrictList(String stateCode) throws IllegalAccessException, InvocationTargetException{
		List<DistrictForm> districtLists = new ArrayList<DistrictForm>();
		List<InterestSubventionVO> districtList = new DistrictDAO().getDistrictForInterestSubvention(stateCode);			
		List<InterestSubventionVO> identifiedDistrictDetail = new DistrictDAO().getDetailInterestSubvention(stateCode);;
		boolean flag=false;
		if(identifiedDistrictDetail != null && identifiedDistrictDetail.size() > 0){
				for(InterestSubventionVO vo : districtList) {
					DistrictForm actionForm = new DistrictForm();
					for(InterestSubventionVO districtDetail : identifiedDistrictDetail){
						if(vo.getEntityCode().equalsIgnoreCase(districtDetail.getEntityCode())){
							districtDetail.setDistrictName(vo.getDistrictName());
							BeanUtils.copyProperties(actionForm, districtDetail);
							flag = true;
							break;
						}						
						}
					
					if(!flag)
						BeanUtils.copyProperties(actionForm, vo);
					districtLists.add(actionForm);
				}
		}else{
			for(InterestSubventionVO vo : districtList) {
				DistrictForm actionForm = new DistrictForm();
				BeanUtils.copyProperties(actionForm, vo);
				districtLists.add(actionForm);
				}
		}
		return districtLists;
	}

	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		log.error("::::::: Interest Subvention ::::::");
		String requestPage="DistISIdentification";
		try{
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");		
		DistrictForm districtform = (DistrictForm)form;
		List<InterestSubventionVO> interestSubventionList = new ArrayList<InterestSubventionVO>();
		for(DistrictForm dlkIdentified: districtform.getDistrictList()){
			InterestSubventionVO obj = new InterestSubventionVO();
			BeanUtils.copyProperties(obj, dlkIdentified);
			obj.setCreatedBy(loginVO.getUserid().toString());
			obj.setCreatedOn(new DateUtil().getPresentDate());
			interestSubventionList.add(obj);
		}
		boolean flag = new DistrictDAO().saveOrUpdateInterestSubvention(interestSubventionList);   
		if(flag == true) {	
			this.showAdd(mapping, districtform, request, response);		
		} else {
			request.setAttribute("status", "Unable to change Block(s) status");	
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward(requestPage);
	}

@Override
public ActionForward showView(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public ActionForward showDelete(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public ActionForward showModify(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public ActionForward delete(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ActionForward modify(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	return null;
}


}
