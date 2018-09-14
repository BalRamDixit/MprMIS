package com.infotech.sgsy.master.block;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.common.MasterBusinessFactory;
import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.manageRole.ManageRoleForm;
import com.infotech.sgsy.master.bankType.BankTypeDAO;
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class BlockAction extends MasterAction {

	protected final Log log = LogFactory.getLog(getClass());

	ActionMessages message = new ActionMessages();

	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public ActionForward save(ActionMapping mapping, ActionForm form,
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

/*	@Override
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

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.info("Inside BlockAction and showView method");
		BlockActionForm actionForm = (BlockActionForm)form;
		LocationVO vo=(LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
		BlockDAO blockDAO = (BlockDAO)MasterDAOFactory.getInstance(Constants.GET_BLOCK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBLOCK , blockDAO.getBlocksList(vo));
		request.setAttribute("paging","page");
		return  mapping.findForward(Constants.SHOW_VIEW_PAGE);
	}
	
	
	 * DEEPAK BISHT METHOD
	 * SHOW BLOCKS LIST AND DISTRICTS LIST 
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		
		log.info("Inside BlockAction and showView method");
		BlockActionForm actionForm = (BlockActionForm)form;
		LocationVO vo=(LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
		actionforward=mapping.findForward(Constants.SHOW_VIEW_PAGE);
	
		if(actionForm.getDistrictCode()!=null)
		{
				BeanUtils.copyProperties(vo, actionForm);
				LoginVO dto=(LoginVO)request.getSession().getAttribute("loginVO");
				LoginMasterDAOImpl dao=new LoginMasterDAOImpl();
				BlockDAO blockdao=new BlockDAO();
				dto.setEntityType("ST");
				String roleCode="DT";
				
				List districtList=dao.getEntityList(roleCode, SGSYConstants.ENTITYTYPE_DISTRICT, dto);
				request.setAttribute("districtList",districtList);
			    actionforward=mapping.findForward("blockForState");
		}
		
		BlockDAO blockDAO = (BlockDAO)MasterDAOFactory.getInstance(Constants.GET_BLOCK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBLOCK , blockDAO.getBlocksList(vo));
		request.setAttribute("paging","page");
		 
			
		 if(vo.getStateCode()=="ST")
			// actionforward=mapping.findForward("showView");
		 if(request.getParameter("type")!=null)
			 actionforward=mapping.findForward("blockForState");
		return  actionforward;
	}

 * DEEPAK BISHT METHOD
 * SHOW BLOCKS LIST  
	public ActionForward showViewForState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		
		log.info("Inside BlockAction and showView method");
		BlockActionForm actionForm = (BlockActionForm)form;
		LocationVO vo=(LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
		LoginVO dto=(LoginVO)request.getSession().getAttribute("loginVO");
		BlockDAO blockDAO = (BlockDAO)MasterDAOFactory.getInstance(Constants.GET_BLOCK_DAO);
		//request.setAttribute(Constants.COLLECTION_GETBLOCK , blockDAO.getBlocksList(Do));
		request.setAttribute("paging","page");
		//if(vo.getBlockCode()!=null){
			actionforward=mapping.findForward("blockForState");
		//}
		

			LoginMasterDAOImpl dao=new LoginMasterDAOImpl();
			BlockDAO blockdao=new BlockDAO();
			dto.setEntityType("ST");
			String roleCode="DT";
			List districtList=dao.getEntityList(roleCode, SGSYConstants.ENTITYTYPE_DISTRICT, dto);
			
			request.setAttribute("districtList",districtList);
			actionForm.setDistrictCode("");
		return  actionforward;
	}

 * DEEPAK BISHT METHOD
 * SHOW DEISTRICTS LIST IN THIS METHOD
	public ActionForward showViewForStateDistrict(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		
		log.info("Inside BlockAction and showView method");
		BlockActionForm actionForm = (BlockActionForm)form;
		LocationVO vo=(LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
		LoginVO dto=(LoginVO)request.getSession().getAttribute("loginVO");
		BlockDAO blockDAO = (BlockDAO)MasterDAOFactory.getInstance(Constants.GET_BLOCK_DAO);
		
		request.setAttribute("paging","page");
		
			actionforward=mapping.findForward("districtForState");
		

			LoginMasterDAOImpl dao=new LoginMasterDAOImpl();
			
			dto.setEntityType("ST");
			String roleCode="DT";
			List districtList=dao.getEntityList(roleCode, SGSYConstants.ENTITYTYPE_DISTRICT, dto);
			
			request.setAttribute("districtList",districtList);
			actionForm.setDistrictCode("");
		return  actionforward;
	}
	
	public ActionForward showUpdateBlockStatusPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		BlockActionForm blockForm = (BlockActionForm)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		LocationVO locationVO=(LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
		request.setAttribute("districtList", new DistrictDAO().getDistrictByState(locationVO.getStateCode()));
		if(loginVO.getLevelCode().equals("2") && blockForm.getDistrictCode() != null) {
			List<BlockMasterVO> blockList = new BlockDAO().getBlockByDistrictCode(blockForm.getDistrictCode());
			List<BlockActionForm> blockLists = new ArrayList<BlockActionForm>();
			for(BlockMasterVO vo : blockList) {
				BlockActionForm actionForm = new BlockActionForm();
				BeanUtils.copyProperties(actionForm, vo);
				blockLists.add(actionForm);
			}
			blockForm.setBlockList(blockLists);
		}
		return mapping.findForward("showUpdateBlockStatusPage");
	}
	
//	Used to Identify the Block	
	public ActionForward updateBlockStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		BlockActionForm blockForm = (BlockActionForm)form;
		ActionForward forward = new ActionForward();
	
		List<BlockMasterVO> blockList = new ArrayList<BlockMasterVO>();
		for(BlockActionForm actionForm : blockForm.getBlockList()) {
			BlockMasterVO vo = new BlockMasterVO();
			BeanUtils.copyProperties(vo, actionForm);
			blockList.add(vo);
		}
		boolean flag = new BlockDAO().updateBlockStatusToIntensive(blockList);
		if(flag == true) {
			request.setAttribute("status", "Block status changed successfully");
		} else {
			request.setAttribute("status", "Unable to change Block status");	
		}
		
		String tokenValue= request.getSession().getAttribute("TRACKERID").toString();
		forward.setPath("block.do?methodName=showUpdateBlockStatusPage&reqtrack="+tokenValue);
		return mapping.findForward("showUpdateBlockStatusPage");
	}*/
}
