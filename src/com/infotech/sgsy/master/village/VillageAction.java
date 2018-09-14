package com.infotech.sgsy.master.village;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import javax.servlet.http.HttpSession;

import com.infotech.sgsy.common.EntityMaster;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.block.BlockDAO;
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.master.village.VillageActionForm;
import com.infotech.sgsy.master.village.VillageDAO;
import com.infotech.sgsy.userManagement.UserService;
import com.infotech.sgsy.userManagement.UserServiceImpl;
import com.infotech.sgsy.util.SGSYConstants;

public class VillageAction extends DispatchAction{

	protected final Log log = LogFactory.getLog(getClass());

	ActionMessages message = new ActionMessages();
	/*public ActionForward showVillage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		DistrictDAO districtDAO = new DistrictDAO();
		BlockDAO blockDAO = new BlockDAO();
		actionforward=mapping.findForward("showVillage");
		VillageActionForm actionForm = (VillageActionForm)form;
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		//String stateCode=null,districtCode=null,blockCode=null;
		
		
		if(actionForm.getStateCode()!=null&&!actionForm.getStateCode().equals("0"))
			locationVO.setStateCode(actionForm.getStateCode());
		if(actionForm.getDistrictCode()!=null&&!actionForm.getDistrictCode().equals("0"))
			locationVO.setDistrictCode(actionForm.getDistrictCode());
		if(actionForm.getBlockCode()!=null&&!actionForm.getBlockCode().equals("0"))
			locationVO.setBlockCode(actionForm.getBlockCode());
		
		BeanUtils.copyProperties(locationVO, actionForm);
		
		if(stateCode!=null)
			locationVO.setStateCode(stateCode);
		if(districtCode!=null)
			locationVO.setDistrictCode(districtCode);
		if(blockCode!=null)
			locationVO.setBlockCode(blockCode);
		
		String roleCode=actionForm.getLevelCode();
		EntityMaster entitymaster=new EntityMaster();
		try{
			message.clear();			
			UserService userService = new UserServiceImpl();
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");		 
			String level = loginVO.getLevelCode();
			
		if(level.equals("0")){
			request.setAttribute("levelList",userService.getLevelList("4"));
			 if(roleCode.equals("3"))
					roleCode="DT";
				else if(roleCode.equals("4")||roleCode.equals("BP"))
				{
					roleCode="BP";
				}
			 if(actionForm.getStateCode()==null||actionForm.getStateCode().equals("0"))
					locationVO.setStateCode(actionForm.getStateCode());
			 if(actionForm.getDistrictCode()==null||actionForm.getDistrictCode().equals("0"))
					locationVO.setDistrictCode(actionForm.getDistrictCode());
				if(actionForm.getBlockCode()==null||actionForm.getBlockCode().equals("0"))
					locationVO.setBlockCode(actionForm.getBlockCode());
			 if(roleCode.equals("DT")||roleCode.equals("BP"))
				entitymaster.showListForStateDistrictBlock(roleCode, locationVO, request);
		}
		if(level.equals("2")){
			
				actionforward=mapping.findForward("showVillageForState");
				List districtList = districtDAO.getDistricts(locationVO.getStateCode());
				request.setAttribute("districtList", districtList);
			if(actionForm.getDistrictCode()!=null&&!locationVO.getDistrictCode().equals("")){
				List blockList = blockDAO.getBlocksList(locationVO);
				request.setAttribute("blockList", blockList);
			}
				
		}
		if(level.equals("3")){
			if(locationVO.getDistrictCode()!=null&&!locationVO.getDistrictCode().equals("")){
				List blockList = blockDAO.getBlocksList(locationVO);
				request.setAttribute("blockList", blockList);
			}
			actionforward=mapping.findForward("showVillageForDistrict");

		}
				request.getSession().removeAttribute("state");				
				request.getSession().removeAttribute("district");
				request.getSession().removeAttribute("block");
     		    request.getSession().removeAttribute("stateList");
				request.getSession().removeAttribute("districtList");
				request.getSession().removeAttribute("blockList");
			
		}
		catch(Exception e){e.printStackTrace();}
		return  actionforward;
	}
	@SuppressWarnings("static-access")
	public ActionForward showVillageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		VillageDAO villagedao=new VillageDAO();
		List villageList;
		String stateName=null,districtName=null,blockName=null,stateCode=null,districtCode=null,blockCode=null;
		actionforward=mapping.findForward("showVillageList");
		VillageActionForm actionForm = (VillageActionForm)form;
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		if(actionForm.getStateCode()!=null&&!actionForm.getStateCode().equals("0"))
			stateCode=actionForm.getStateCode();
		if(actionForm.getDistrictCode()!=null&&!actionForm.getDistrictCode().equals("0"))
			districtCode=actionForm.getDistrictCode();
		if(actionForm.getBlockCode()!=null&&!actionForm.getBlockCode().equals("0"))
			blockCode=actionForm.getBlockCode();
		
		//BeanUtils.copyProperties(locationVO, actionForm);
		
		if(stateCode!=null)
			locationVO.setStateCode(stateCode);
		if(districtCode!=null)
			locationVO.setDistrictCode(districtCode);
		if(blockCode!=null)
			locationVO.setBlockCode(blockCode);
		
		String entityCode="";
		
		if(request.getParameter("stateName")!=null){
		 stateName=request.getParameter("stateName");
		request.setAttribute("stateName", stateName);
		}
		
		if(request.getParameter("districtName")!=null){
		 districtName=request.getParameter("districtName");
		request.setAttribute("districtName", districtName);
		}
		
		if(request.getParameter("blockName")!=null){
		 blockName=request.getParameter("blockName");
		request.setAttribute("blockName", blockName);
		}
		if(locationVO.getStateName()!=null&&!locationVO.getStateName().equals("")){
			stateName=locationVO.getStateName();
			request.setAttribute("stateName", stateName);
		}
		if(locationVO.getDistrictName()!=null&&!locationVO.getDistrictName().equals("")){
			districtName=locationVO.getDistrictName();
			request.setAttribute("districtName", districtName);
		}
		if(locationVO.getBlockName()!=null&&!locationVO.getBlockName().equals("")){
			blockName=locationVO.getBlockName();
			request.setAttribute("blockName", blockName);
		}
		if(locationVO.getStateCode()!=null){	
		
			if(locationVO.getBlockCode()!=null){
				entityCode=locationVO.getBlockCode();
				
			}
			else
				entityCode=locationVO.getDistrictCode();	
		}
		villageList=villagedao.getVillageList(entityCode);
		
		request.setAttribute("villageList", villageList);
		return actionforward;
	}*/
}
