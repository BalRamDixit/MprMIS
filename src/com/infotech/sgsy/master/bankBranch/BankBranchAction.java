package com.infotech.sgsy.master.bankBranch;

import java.io.PrintWriter;
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

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.common.MasterBusinessFactory;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.bank.BankDAO;
import com.infotech.sgsy.master.block.BlockDAO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class BankBranchAction extends MasterAction {

	BankBranchVO branchVO = null;
	BankBranchBusiness business = null;
	HttpSession session = null;
	protected final Log log = LogFactory.getLog(getClass());

	ActionMessages message = new ActionMessages();

	// USED TO SHOW ADD PAGE OF BANK BRANCH
	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BankBranchActionForm frm = (BankBranchActionForm) form;
		String BRANCH_ENTRY_LEVEL = null;
		frm.reset();

		if (loginVO.getEntityCode().length() == 1 && loginVO.getEntityCode().equals("0")) {
			BRANCH_ENTRY_LEVEL = "NATIONAL_BRANCH";
		} else if (loginVO.getEntityCode().length() == 2) {
			BRANCH_ENTRY_LEVEL = "STATE_BRANCH";
		} else if (loginVO.getEntityCode().length() == 4) {
			BRANCH_ENTRY_LEVEL = "DISTRICT_BRANCH";
		}

		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);

		request.setAttribute(Constants.COLLECTION_GETBANKS,bankDAO.getBankListForBanchAdd(loginVO.getEntityCode()));
		request.setAttribute("BRANCH_ENTRY_LEVEL", BRANCH_ENTRY_LEVEL);
		return mapping.findForward(Constants.SHOW_ADD_PAGE);
	}

	// USED TO SAVE THE BANK BRANCH INFORMATION
	// DATE 19 FEB 2013
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);

		BankBranchActionForm frm = (BankBranchActionForm) form;
		BankBranchVO bankBranchVO = new BankBranchVO();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();

		BeanUtils.copyProperties(bankBranchVO, frm);
		// VillageDAO villageDAO =(VillageDAO)
		// MasterDAOFactory.getInstance(Constants.GET_VILLAGE_DAO);

		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		BankBranchBusiness business = (BankBranchBusiness) MasterBusinessFactory.getInstance(Constants.GET_BANK_BRANCH_BUSI);
		bankBranchVO.setActiveFlag(SGSYConstants.ACTIVE);
		bankBranchVO.setCreatedBy(loginVO.getUserid());
		bankBranchVO.setCreatedOn(DateUtil.getPresentDate());
		bankBranchVO.setEntityCode(loginVO.getEntityCode());
		int i = business.save(bankBranchVO);

		message.clear();
		BlockDAO blockDAO = (BlockDAO) MasterDAOFactory.getInstance(Constants.GET_BLOCK_DAO);
		request.setAttribute(Constants.COLLECTION_GETBLOCK, blockDAO.getBlocksList(vo));
		if (i == Constants.ADD_SUCCESS) {
			message.add(SGSYConstants.MSG, new ActionMessage("added.success.message", context.getAttribute("label.branch").toString()));
			saveMessages(request, message);
		} else if (i == Constants.NAME_FOUND) {
			message.add(SGSYConstants.MSG, new ActionMessage("error.present", context.getAttribute("label.branchname").toString()));
			saveMessages(request, message);
		} else if (i == Constants.SHORTNAME_FOUND) {
			message.add(
					SGSYConstants.MSG,
					new ActionMessage("error.present", context.getAttribute("label.branchabbreviation").toString()));
			saveMessages(request, message);
		} else {
			message.add(SGSYConstants.MSG, new ActionMessage("error.add", context.getAttribute("label.branch").toString()));
			saveMessages(request, message);
		}

		// request.setAttribute( Constants.COLLECTION_GETVILLAGE ,
		// VillageDAO.getVillageListForBank(vo.getBlockCode()));
		this.showAdd(mapping, form, request, response);
		return mapping.findForward(Constants.SHOW_ADD_PAGE);
	}

	// FUNCTION USED TO SHOW MODIFY PAGE
	public ActionForward showModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		message.clear();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		String BRANCH_ENTRY_LEVEL = null;

		if (loginVO.getEntityCode().length() == 1 && loginVO.getEntityCode().equals("0")) {
			BRANCH_ENTRY_LEVEL = "NATIONAL_BRANCH";
		} else if (loginVO.getEntityCode().length() == 2) {
			BRANCH_ENTRY_LEVEL = "STATE_BRANCH";
		} else if (loginVO.getEntityCode().length() == 4) {
			BRANCH_ENTRY_LEVEL = "DISTRICT_BRANCH";
		}

		BankBranchActionForm bankBranchActionForm = (BankBranchActionForm) form;
		bankBranchActionForm.reset();
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);
		bankBranchActionForm.reset();

		List bankList = null;
		bankList = bankDAO.getBankList(loginVO.getEntityCode());
		if (bankList.isEmpty() && bankList.size() == 0) {
			request.setAttribute("bankList", null);
			message.add(
					SGSYConstants.MSG, new ActionMessage("error.notPresent", context.getAttribute("label.bankBranchName").toString()));
			saveMessages(request, message);
		} else {
			request.setAttribute("bankList", bankList);
		}
		request.setAttribute("BRANCH_ENTRY_LEVEL", BRANCH_ENTRY_LEVEL);
		return mapping.findForward(Constants.SHOW_MODIFY_PAGE);

	}
	
	// USED TO COLLECT THE BANK LIST
	/*public void getBankList(HttpServletRequest request, String ENTITY_CODE) throws Exception{
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		
		List bankList = null;
		bankList = bankDAO.getBankList(ENTITY_CODE);
		if (bankList.isEmpty() && bankList.size() == 0) {
			request.setAttribute("bankList", null);
			message.add(SGSYConstants.MSG, new ActionMessage("error.notPresent", context.getAttribute("label.bankBranchName").toString()));
			saveMessages(request, message);
		} else {
			request.setAttribute("bankList", bankList);
		}
	}
*/
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		message.clear();
		HttpSession session=request.getSession();
		ServletContext context=session.getServletContext();
		BankBranchActionForm bankBranchActionForm = (BankBranchActionForm) form;
		BankBranchVO bankBranchVO = new BankBranchVO();
		
		BeanUtils.copyProperties(bankBranchVO, bankBranchActionForm);
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);
		BankBranchBusiness business = (BankBranchBusiness) MasterBusinessFactory.getInstance(Constants.GET_BANK_BRANCH_BUSI);	
				
		int i= BankBranchBusiness.update(bankBranchVO);					
		bankBranchActionForm.reset();	
		
		if(i==Constants.UPDATE_SUCCESS){
			message.add(SGSYConstants.MSG, new ActionMessage("update.success.message",context.getAttribute("label.branch").toString()));
			saveMessages(request, message);					
			request.setAttribute("NOTIFICATION", "Record Modify Successfully.");
		}else{
			request.setAttribute("NOTIFICATION", "ERROR:: Please contact to site Administrator.");
		}
		
		
		this.showModify(mapping, bankBranchActionForm, request, response);
		return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
	}

	/*
	 *  USED TO SHOEW THE VIEW PAGE(non-Javadoc)
	 * @see com.infotech.sgsy.common.MasterAction#showView(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);
		BankBranchActionForm bankBranchForm = (BankBranchActionForm) form;
		ViewBankBranchVO viewBankBranchVO = new ViewBankBranchVO();
		
		String LOGIN_LEVEL = null;
		if(loginVO.getEntityCode().length() == 1)
			LOGIN_LEVEL = "M";
		else if(loginVO.getEntityCode().length() == 2)
			LOGIN_LEVEL = "S";
		else
			LOGIN_LEVEL = "D";
		
		// CHECK BANK LEVEL
		if(bankBranchForm.getBankLevel() == null || (bankBranchForm.getBankLevel().equals("0")|| bankBranchForm.getBankLevel().equals("")) )
			bankBranchForm.setBankLevel("0");
		else{
			viewBankBranchVO.setBankLevelCode(bankBranchForm.getBankLevel());
		}
		
		//	 CHECK BANK CODE
		if(bankBranchForm.getBankCode() != null){
			viewBankBranchVO.setBankCode(bankBranchForm.getBankCode());
		}
		request.setAttribute(Constants.COLLECTION_GETBANKS,bankDAO.getBankListForDropDown(loginVO.getEntityCode()));
		
		viewBankBranchVO.setEntityCode(loginVO.getEntityCode());
		request.setAttribute("VIEW_BRANCH_LIST", bankBranchDAO.getBranchList(viewBankBranchVO));
		request.setAttribute("LOGIN_LEVEL", LOGIN_LEVEL);
		return mapping.findForward(Constants.SHOW_VIEW_PAGE);

	}

	// USED TO GET THE BANK DETAIL
	public ActionForward getBankDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.showModify(mapping, form, request, response);
		BankBranchActionForm actionForm = (BankBranchActionForm) form;
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);

		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);

	/*	List bankList = null;
		bankList = bankDAO.getBankList(loginVO.getEntityCode());
		request.setAttribute("bankList", bankList);*/

		String bankCode = request.getParameter("bankCode");
		actionForm.setBankCode(bankCode);
		List bankBranchList = bankBranchDAO.getBankBranchForModification(bankCode, loginVO.getEntityCode());
		if (bankBranchList.isEmpty() && bankBranchList.size() == 0) {
			request.setAttribute("bankBranchList", null);
			message.add(SGSYConstants.MSG, new ActionMessage("error.bankBranchNotFound"));
			saveMessages(request, message);
		} else {
			request.setAttribute("bankBranchList", bankBranchList);
		}

		// request.setAttribute( Constants.COLLECTION_GETVILLAGE ,
		// VillageDAO.getVillageListForBank(locationVO.getBlockCode()));
		return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
	}

	// USED TO GET THE BANK BRANCH DETAIL
	public ActionForward getBranchDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.getBankDetails(mapping, form, request, response);
		BankBranchActionForm actionForm = (BankBranchActionForm) form;
		BankBranchVO bankBranchVO = new BankBranchVO();
		BeanUtils.copyProperties(bankBranchVO, actionForm);
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);

		String BRANCH_CODE = request.getParameter("bankBranchCode");
		actionForm.setBankBranchCode(BRANCH_CODE);
		bankBranchVO = bankBranchDAO.getBankBranchDetail(BRANCH_CODE, bankBranchVO, loginVO.getEntityCode());
		request.setAttribute("bankBranchVO", bankBranchVO);
		
		return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
	}

	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		message.clear();
		BankBranchActionForm actionForm = (BankBranchActionForm) form;
		actionForm.reset();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		LocationVO vo = (LocationVO) request.getSession().getAttribute(
				SGSYConstants.SGSY_LOCATIONVO);
		BankDAO bankDAO = (BankDAO) MasterDAOFactory
				.getInstance(Constants.GET_BANK_DAO);
		List bankList = null;
		bankList = bankDAO.getBankList(vo.getDistrictCode());
		if (bankList.isEmpty() && bankList.size() == 0) {
			request.setAttribute("bankList", null);
			message.add(
					SGSYConstants.MSG,
					new ActionMessage("error.notPresent", context.getAttribute(
							"label.bankBranchName").toString()));
			saveMessages(request, message);
		} else {
			request.setAttribute("bankList", bankList);
		}
		return mapping.findForward(Constants.SHOW_DELETE_PAGE);
	}

	public ActionForward getBankDetailsForDeleteModule(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		message.clear();
		LocationVO vo = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		List bankList = null;
		bankList = bankDAO.getBankList(loginVO.getEntityCode());
		request.setAttribute("bankList", bankList);
		String bankCode = request.getParameter("bankCode");
		
		List bankBranchList = bankBranchDAO.getBankBranchForModification(bankCode, loginVO.getEntityCode());
		/*List bankBranchList = bankBranchDAO.getBankBranch(bankCode, vo);*/
		if (bankBranchList.isEmpty() && bankBranchList.size() == 0) {
			request.setAttribute("bankBranchList", null);
			message.add(SGSYConstants.MSG, new ActionMessage("error.bankBranchNotFound"));
			saveMessages(request, message);
		} else {
			request.setAttribute("bankBranchList", bankBranchList);
		}
		return mapping.findForward(Constants.SHOW_DELETE_PAGE);
	}

	
	/*public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		message.clear();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		BankBranchActionForm bankBranchActionForm = (BankBranchActionForm) form;
		String[] bankBranchCode = request
				.getParameterValues("checkBankBranchCodes");
		BankDAO bankDAO = (BankDAO) MasterDAOFactory
				.getInstance(Constants.GET_BANK_DAO);
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(
				SGSYConstants.SGSY_LOCATIONVO);
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory
				.getInstance(Constants.GET_BANK_BRANCH_DAO);
		List bankBranchWithLoanDetails = bankBranchDAO
				.bankBranchLoanDetailsCheck(bankBranchCode, locationVO);
		List bankBranchWithServiceAreaDetails = bankBranchDAO
				.bankBranchServiceAreaDetailsCheck(bankBranchCode, locationVO);
		boolean flag = bankBranchDAO.delete(bankBranchCode,
				bankBranchWithLoanDetails, bankBranchWithServiceAreaDetails);

		if (flag) {
			if (bankBranchWithLoanDetails.size() == 0
					&& bankBranchWithServiceAreaDetails.size() == 0) {
				message.add(SGSYConstants.MSG, new ActionMessage(
						"delete.success.message", "Selected Bank Branch(s)"));
				saveMessages(request, message);
			} else {
				message.add(SGSYConstants.MSG, new ActionMessage(
						"delete.BranchWithLoan"));
				saveMessages(request, message);
			}
		} else {
			message.add(SGSYConstants.MSG, new ActionMessage(
					"error.bankBranchWithLoanDetails"));
			saveMessages(request, message);
		}

		LocationVO vo = (LocationVO) request.getSession().getAttribute(
				SGSYConstants.SGSY_LOCATIONVO);
		request.setAttribute("bankList", bankBranchDAO.getBanks(vo));
		String bankCode = request.getParameter("bankCode");

		List bankList = null;
		bankList = bankDAO.getBankList(vo.getDistrictCode());
		if (bankList.isEmpty() && bankList.size() == 0) {
			request.setAttribute("bankList", null);
			message.add(
					SGSYConstants.MSG,
					new ActionMessage("error.notPresent", context.getAttribute(
							"label.bankBranchName").toString()));
			saveMessages(request, message);
		} else {
			request.setAttribute("bankList", bankList);
		}
		bankBranchActionForm.reset();
		return mapping.findForward(Constants.SHOW_DELETE_PAGE);
	}*/

	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
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

	public ActionForward checkIfsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.error("::::::: filter method call-- Check Ifsc Team ::::::");
		BankBranchActionForm bankBranchForm = (BankBranchActionForm)form;
		try {
			PrintWriter out = response.getWriter();
			String ifscCode = request.getParameter("ifscCode");
			BankBranchDAO helper = new BankBranchDAO();
			boolean flag = helper.checkIfsc(ifscCode);
			if(flag == true) {
				out.write("true");
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return mapping.findForward(null);
	}
	
}
