package com.infotech.sgsy.master.bank;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


import com.infotech.sgsy.common.EntityMaster;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.common.MasterBusinessFactory;

import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.bankType.BankTypeDAO;
import com.infotech.sgsy.userManagement.UserService;
import com.infotech.sgsy.userManagement.UserServiceImpl;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class BankAction extends MasterAction {

	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();
	
	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BankActionForm frm = (BankActionForm) form;
		frm.reset();
		BankDAO bankDAO = (BankDAO) MasterDAOFactory
				.getInstance(Constants.GET_BANK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBANKSHORTNAME, bankDAO
				.getBanksForDistrict(null));
		request.setAttribute(Constants.COLLECTION_GETBANKLEVELS, bankDAO
				.getBanksForDistrict(null));
		request.setAttribute(Constants.COLLECTION_GETBANKTYPESDETAILS, bankDAO
				.getBanksForDistrict(null));
		return mapping.findForward(Constants.SHOW_UPDATE_PAGE);
	}

	
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BankActionForm frm = (BankActionForm) form;
		BankVO bankVO = new BankVO();
		BeanUtils.copyProperties(bankVO, frm);
		HttpSession session=request.getSession();
		ServletContext context=session.getServletContext();
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		bankVO.setEntityCode(loginVO.getEntityCode());
		bankVO.setActiveFlag(SGSYConstants.ACTIVE);
		bankVO.setCreatedBy(loginVO.getUserid());
		bankVO.setCreatedOn(DateUtil.getPresentDate());
		BankBusiness bankBusiness = (BankBusiness) MasterBusinessFactory.getInstance(Constants.GET_BANK_BUSI);
		BankTypeDAO bankTypeDAO = (BankTypeDAO) MasterDAOFactory.getInstance(Constants.GET_BANKTYPE_DAO);
		int i = bankBusiness.save(bankVO);
		message.clear();
		if (i == Constants.ADD_SUCCESS) {
			message.add(SGSYConstants.MSG, new ActionMessage("added.success.message", context.getAttribute("label.bank").toString()));
			saveMessages(request, message);
		} else if (i == Constants.NAME_FOUND) {
			message.add(SGSYConstants.MSG, new ActionMessage("error.present", context.getAttribute("label.bankName").toString()));
			saveMessages(request, message);
		} else if (i == Constants.SHORTNAME_FOUND) {
			message.add(SGSYConstants.MSG, new ActionMessage("error.present", context.getAttribute("label.bankabbreviation").toString()));
			saveMessages(request, message);
		} else {
			message.add(SGSYConstants.MSG, new ActionMessage("error.add", context.getAttribute("label.bank").toString()));
			saveMessages(request, message);
		}
		frm.reset();
		request.setAttribute(Constants.COLLECTION_GETBANKLEVELS, bankTypeDAO.getBankLevels(loginVO.getEntityCode()));
		request.setAttribute(Constants.COLLECTION_GETBANKTYPES, bankTypeDAO.getBankType(null));
		this.showAdd(mapping, form, request, response);
		return mapping.findForward(Constants.SHOW_ADD_PAGE);
	}

	// USED TO SHOW THE BANK ADD PAGE
	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		String BANK_ENTRY_LEVEL = null;
		BankActionForm frm = (BankActionForm) form;
		frm.reset();
		
		if(loginVO.getEntityCode().length() == 1 && loginVO.getEntityCode().equals("0")){
			BANK_ENTRY_LEVEL = "NATIONAL_BANK";
		}else if(loginVO.getEntityCode().length() == 2){
			BANK_ENTRY_LEVEL = "STATE_BANK";
		}else if(loginVO.getEntityCode().length() == 4){
			BANK_ENTRY_LEVEL = "DISTRICT_BANK";
		}
		BankTypeDAO bankTypeDAO = (BankTypeDAO) MasterDAOFactory.getInstance(Constants.GET_BANKTYPE_DAO);
		request.setAttribute(Constants.COLLECTION_GETBANKLEVELS, bankTypeDAO.getBankLevels(loginVO.getEntityCode()));
		request.setAttribute(Constants.COLLECTION_GETBANKTYPES, bankTypeDAO.getBankType(null));
		request.setAttribute("BANK_ENTRY_LEVEL", BANK_ENTRY_LEVEL);
		return mapping.findForward(Constants.SHOW_ADD_PAGE);
	}

	// USED TO SHOW THE BANK MODIFY PAGE
	@Override
	public ActionForward showModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		String BANK_ENTRY_LEVEL = null;
		BankActionForm frm = (BankActionForm)form;
		frm.reset();
		
		if(loginVO.getEntityCode().length() == 1 && loginVO.getEntityCode().equals("0")){
			BANK_ENTRY_LEVEL = "NATIONAL_BANK";
		}else if(loginVO.getEntityCode().length() == 2){
			BANK_ENTRY_LEVEL = "STATE_BANK";
		}else if(loginVO.getEntityCode().length() == 4){
			BANK_ENTRY_LEVEL = "DISTRICT_BANK";
		}
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		request.setAttribute("bankDetails", bankDAO.getBankModificationList(loginVO.getEntityCode()));
		request.setAttribute("BANK_ENTRY_LEVEL", BANK_ENTRY_LEVEL);
		return mapping.findForward(Constants.SHOW_UPDATE_PAGE);
	}

	// USED TO SHOW THE BANK VIEW PAGE
	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBANKS, bankDAO.getBankViewList(loginVO.getEntityCode()));
		return mapping.findForward(Constants.SHOW_VIEW_PAGE);
	}

	// USED TO COLLECT THE DETAIL OF BANK
	public ActionForward getBankDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");	
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		String BANK_ENTRY_LEVEL = null;
		
		if(loginVO.getEntityCode().length() == 1 && loginVO.getEntityCode().equals("0")){
			BANK_ENTRY_LEVEL = "NATIONAL_BANK";
		}else if(loginVO.getEntityCode().length() == 2){
			BANK_ENTRY_LEVEL = "STATE_BANK";
		}else if(loginVO.getEntityCode().length() == 4){
			BANK_ENTRY_LEVEL = "DISTRICT_BANK";
		}
		
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		request.setAttribute("bankDetails", bankDAO.getBankModificationList(loginVO.getEntityCode()));
		BankTypeDAO bankTypeDAO = (BankTypeDAO) MasterDAOFactory.getInstance(Constants.GET_BANKTYPE_DAO);
		request.setAttribute(Constants.COLLECTION_GETBANKLEVELS, bankTypeDAO.getBankLevels(loginVO.getEntityCode()));
		request.setAttribute(Constants.COLLECTION_GETBANKTYPES, bankTypeDAO.getBankType(null));
		String bankCode = request.getParameter("bankCode");
		BankVO bankDetailsVO = bankDAO.getBankDetailsBasedOnBankCode(bankCode);
		request.setAttribute(Constants.BANKDETAILS, bankDetailsVO);
		if(bankDetailsVO != null){
			request.setAttribute(Constants.BANK_TYPE, bankDetailsVO.getBankTypeCode());
			request.setAttribute(Constants.BANK_LEVEL, bankDetailsVO.getBankLevelCode());
		}
		request.setAttribute("BANK_ENTRY_LEVEL", BANK_ENTRY_LEVEL);
		return mapping.findForward(Constants.SHOW_UPDATE_PAGE);
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		message.clear();
		HttpSession session=request.getSession();
		ServletContext context=session.getServletContext();
		
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
/*		request.setAttribute("bankDetails", bankDAO.getBanksForBlock(vo));*/
		BankActionForm frm = (BankActionForm) form;
		BankVO bankVO = new BankVO();
		BeanUtils.copyProperties(bankVO, frm);
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		bankVO.setEntityCode(loginVO.getEntityCode());
		bankVO.setActiveFlag(SGSYConstants.ACTIVE);
		bankVO.setLastModifedBy(loginVO.getUserid());
		bankVO.setLastModifiedOn(DateUtil.getPresentDate());
		BankTypeDAO bankTypeDAO = (BankTypeDAO) MasterDAOFactory.getInstance(Constants.GET_BANKTYPE_DAO);
		boolean bankAbbreviationFound = bankDAO.bankAbbreviationCheck(bankVO);
		if(!bankAbbreviationFound){
			boolean flag = bankDAO.update(bankVO);
			if(flag){
				message.add(SGSYConstants.MSG, new ActionMessage("update.success.message", context.getAttribute("label.bank").toString()));
				saveMessages(request, message);
				frm.reset();
				this.showModify(mapping, form, request, response);
			}
		}else{
			message.add(SGSYConstants.MSG, new ActionMessage("error.bankAbbreviationFound"));
			saveMessages(request, message);
			getBankDetails(mapping, form, request, response);
		}
		
		return mapping.findForward(Constants.SHOW_UPDATE_PAGE);
	}
	
	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*message.clear();
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BankActionForm actionForm = (BankActionForm)form;
		actionForm.reset();
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		 
		//request.setAttribute("bankDetails", );
		
		List bankList =bankDAO.getBanksForBlock(vo);
			//bankDAO.getBankNames(vo);
		if(!bankList.isEmpty() && bankList.size()!=0){
			request.setAttribute("bankDetails", bankList);
		}else{
			request.setAttribute("bankDetails", null);
			message.add(SGSYConstants.MSG, new ActionMessage("error.bankRecordsNotFound"));
			saveMessages(request, message);
		}*/
		return mapping.findForward(Constants.SHOW_DELETE_PAGE);
	}
	
	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*message.clear();
		HttpSession session=request.getSession();
		ServletContext context=session.getServletContext();
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		String[] bankCode = request.getParameterValues("checkBankCodes");
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		boolean bankBranchFound = bankDAO.bankBranchCheck(bankCode);
		if(!bankBranchFound){
			boolean flag = bankDAO.delete(bankCode);
			if(flag){
				message.add(SGSYConstants.MSG, new ActionMessage(
						"delete.success.message", context.getAttribute("label.bank").toString()));
				saveMessages(request, message);
			}
		}else{
			message.add(SGSYConstants.MSG, new ActionMessage("error.bankBranchExitForBank"));
			saveMessages(request, message);
		}
		List bankList =bankDAO.getBanksForBlock(vo);
		//List bankList = bankDAO.getBankNames(vo);
		if(!bankList.isEmpty() && bankList.size()!=0){
			request.setAttribute("bankDetails", bankList);
		}else{
			request.setAttribute("bankDetails", null);
			message.add(SGSYConstants.MSG, new ActionMessage("error.bankRecordsNotFound"));
			saveMessages(request, message);
		}	*/
		return mapping.findForward(Constants.SHOW_DELETE_PAGE);
	}
	
	
	//ADD BY DEEPAK BISHT
	// SHOW BANKS LIST FOR STATE
	/*public ActionForward showViewForState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		
		LocationVO vo = (LocationVO) request.getSession().getAttribute(
				SGSYConstants.SGSY_LOCATIONVO);
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBANKS, bankDAO.getBanksForBlock(vo));
			actionforward=mapping.findForward("bankViewForState");
			LoginVO dto=(LoginVO)request.getSession().getAttribute("loginVO");
			LoginMasterDAOImpl dao=new LoginMasterDAOImpl();
			String roleCode="DT";
			List list=dao.getEntityList(roleCode, SGSYConstants.ENTITYTYPE_DISTRICT, dto);
			request.setAttribute("districtList",list);
			return  actionforward;
	}*/

	//DEEPAK BISHT 
	//GET LEVEL CODE AND SHOW BANK DETAILS FOR MORD
	
	public ActionForward showViewForMord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward actionforward=new ActionForward();
		actionforward=mapping.findForward("bankViewForMord");
		BankActionForm actionForm = (BankActionForm)form;
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BeanUtils.copyProperties(locationVO, actionForm);
		String roleCode=actionForm.getLevelCode();
		EntityMaster entitymaster=new EntityMaster();
		try{
			message.clear();
			
			UserService userService = new UserServiceImpl();
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		 
			String level = loginVO.getLevelCode();
			List levelList=userService.getLevelList("2");
			levelList.remove(2);
			request.setAttribute("levelList",levelList);
						
			if(roleCode.equals("2"))
				roleCode="ST";
			else if(roleCode.equals("3"))
				roleCode="DT";
			else if(roleCode.equals("4")||roleCode.equals("BP"))
				roleCode="BP";
			//else if(roleCode.equals("0")||roleCode.equals("1")||roleCode.equals(""))
			{				
                request.getSession().removeAttribute("state");				
				request.getSession().removeAttribute("district");
				request.getSession().removeAttribute("block");
        		request.getSession().removeAttribute("stateList");
				request.getSession().removeAttribute("districtList");
				request.getSession().removeAttribute("blockList");
			}
			entitymaster.showListForStateDistrictBlock(roleCode, locationVO, request);			
		}
		catch(Exception e){e.printStackTrace();}
		return  actionforward;
	}
	
	
	public ActionForward showViewBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LocationVO vo = (LocationVO) request.getSession().getAttribute(
				SGSYConstants.SGSY_LOCATIONVO);
		BankDAO bankDAO = (BankDAO) MasterDAOFactory
				.getInstance(Constants.GET_BANK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBANKS, bankDAO
				.getBanksForView(vo));
		
		if(request.getParameter("districtName")!=null&&!request.getParameter("districtName").equals("")&&!request.getParameter("districtName").equals("0")){
			request.setAttribute("districtName", request.getParameter("districtName"));
		}
		if(request.getParameter("stateName")!=null&&!request.getParameter("stateName").equals("")&&!request.getParameter("stateName").equals("0")){
			request.setAttribute("stateName", request.getParameter("stateName"));
		}
		else if(vo.getStateName()!=null&&!vo.getStateName().equals("")){
			request.setAttribute("stateName", vo.getStateName());
		}
		return mapping.findForward("showViewBank");
	}


	
}
