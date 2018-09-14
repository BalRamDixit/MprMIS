package com.infotech.sgsy.selfHelpGroup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterBusinessFactory;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class SHGBankLinkageAction extends DispatchAction{
	
	SHGBusiness business = null;
	
	// USED TO SHOW ADD PAGE OF SHG MONTHY DETAIL IN BLOCK
	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.error("SHG bank linkage showPage Method..............");
		String forword="SHOW_SHG_BANK_LINKAGE";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
		
		SHGBankLinkageForm shgBankLinkage = (SHGBankLinkageForm) form;
		try{
			if(shgBankLinkage.getEntityCode()!= null){
				request.setAttribute("SHGList", new SHGMasterDAO().getSHGListForDropdown(locationVO, shgBankLinkage.getEntityCode()));
			}
			if(shgBankLinkage.getShgCode()!= null && !"".equals(shgBankLinkage.getShgCode())){
				MasterShgVO shgRegDetail = new SHGMasterDAO().getshgRegDetails(locationVO, shgBankLinkage.getShgCode());
				request.setAttribute("shgBankLinkageList", new SHGMasterDAO().shgBankLinkageList(locationVO.getStateShortName(), shgBankLinkage.getShgCode()));
				if(shgRegDetail != null){
					if(shgRegDetail.getBankAccountNo() == null || shgRegDetail.getBankAccountNo().equals("")){
						request.setAttribute("ERROR_NOTIFICATION","Self Help Group Bank Detail Not Found in registation form. Please fill it first to enter Bank Loan Infromation.");
					}
					/*shgBankLinkage.setDosageNumber(Integer.parseInt(shgRegDetail.getBankLinkageHappened()) + 1);*/
					}
			}
			request.setAttribute("VillageList", business.getVillageList(locationVO.getBlockCode()));
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("SHG MONTHLY REPORT CARD ADD :::" + e.getMessage());
		}
		return mapping.findForward(forword);
	}
	
	public ActionForward clearPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.error("SHG bank linkage showPage Method..............");
		String forword="SHOW_SHG_BANK_LINKAGE";
		SHGBankLinkageForm shgBankLinkage = (SHGBankLinkageForm) form;
		shgBankLinkage.reset(mapping, request);
		this.showPage(mapping, form, request, response);
		return mapping.findForward(forword);
	}
	
	// USED TO SHOW MODIFY PAGE OF SHG MONTHY DETAIL IN BLOCK
	/*public ActionForward modifyPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward="MODIFY_SHG_MONTHLY";	
		HttpSession session=request.getSession();	
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
		LocationVO  locationVO = (LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		SHGReportCardForm shgRPTCard = (SHGReportCardForm) form;
		try{	
			if(shgRPTCard.getEntityCode()!= null){
				request.setAttribute("SHGList", new SHGMasterDAO().getSHGListForDropdown(locationVO, shgRPTCard.getEntityCode()));
				this.getFinancialYearAndMonth(request); 
			}
			if(shgRPTCard.getShgCode()!= null && !"".equals(shgRPTCard.getShgCode())){
				MasterShgVO shgRegDetail = new SHGMasterDAO().getshgRegDetails(locationVO, shgRPTCard.getShgCode());
				MasterSHGMonthlyDetailVO SHGMonthDetailVO = new SHGMasterDAO().getSHGMonthlyDetail(locationVO, shgRPTCard.getShgCode(), shgRPTCard.getFinancialYear(), shgRPTCard.getEntryMonth());
				if(SHGMonthDetailVO != null){
					BeanUtils.copyProperties(shgRPTCard, SHGMonthDetailVO);					
				} else{
					request.setAttribute("SHG_MONTHLY_DETAIL_ENTERED", "( SELF HELP GROUP MONTHLY DETAIL ) NO RECORD FOUND OF SELECTED MONTH.");
					SHGReportCardForm TEMP_SHG_REPORT_DETAIL = new SHGReportCardForm();
					TEMP_SHG_REPORT_DETAIL.setEntityCode(shgRPTCard.getEntityCode());
					TEMP_SHG_REPORT_DETAIL.setShgCode(shgRPTCard.getShgCode());
					TEMP_SHG_REPORT_DETAIL.setFinancialYear(shgRPTCard.getFinancialYear());
					TEMP_SHG_REPORT_DETAIL.setEntryMonth(shgRPTCard.getEntryMonth());
					shgRPTCard = TEMP_SHG_REPORT_DETAIL;
				}				
				if(shgRegDetail != null)
					request.setAttribute("shgDetails", shgRegDetail);		
			}			
			request.setAttribute("VillageList",business.getVillageList(locationVO.getBlockCode()));
			request.setAttribute("SHG_MONTHLY_DETAIL", shgRPTCard);	
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("SHG MONTHLY REPORT CARD MODIFY :::" + e.getMessage());
		}
		return mapping.findForward(forward);
	}
*/

	 
	// USED TO VIEW THE SHG MONTHY DETAIL IN BLOCK	 
/*	public ActionForward viewPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "VIEW_SHG_MONTHLY";	
		HttpSession session=request.getSession();	
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
		LocationVO  locationVO = (LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		SHGReportCardForm shgRPTCard = (SHGReportCardForm) form;
		try{
			if(shgRPTCard.getEntityCode()!= null){
				request.setAttribute("SHGList", new SHGMasterDAO().getSHGListForDropdown(locationVO, shgRPTCard.getEntityCode()));
				this.getFinancialYearAndMonth(request); 
			}
			if(shgRPTCard.getShgCode()!= null && !"".equals(shgRPTCard.getShgCode())){
				MasterShgVO shgRegDetail = new SHGMasterDAO().getshgRegDetails(locationVO, shgRPTCard.getShgCode());
				MasterSHGMonthlyDetailVO SHGMonthDetailVO = new SHGMasterDAO().getSHGMonthlyDetail(locationVO, shgRPTCard.getShgCode(), shgRPTCard.getFinancialYear(), shgRPTCard.getEntryMonth());
				if(SHGMonthDetailVO != null){
					BeanUtils.copyProperties(shgRPTCard, SHGMonthDetailVO);
					request.setAttribute("SHG_MONTHLY_DETAIL", shgRPTCard);
				}
				else{
					request.setAttribute("SHG_MONTHLY_DETAIL_ENTERED", "( SELF HELP GROUP MONTHLY DETAIL ) NO RECORD FOUND OF SELECTED MONTH.");	
				}
				if(shgRegDetail != null)
					request.setAttribute("shgDetails", shgRegDetail);	
				
			}
			request.setAttribute("VillageList",business.getVillageList(locationVO.getBlockCode()));
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("SHG MONTHLY REPORT CARD VIEW :::" + e.getMessage());
		}
		return mapping.findForward(forward);
	}*/
	
	
	// USED TO SAVE THE MONTHLY DETAIL OF SHG	
	public ActionForward saveAndModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.error("SHG Bank Linkage Save Method call.................");
		String forword = "SHOW_SHG_BANK_LINKAGE";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;	
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
		
		try{
			
			SHGBankLinkageForm SHGBankLinkageForm = (SHGBankLinkageForm)form;
			SHGBankLinkageForm.setCreatedBy(loginVO.getUserName());
			
			if(new SHGMasterDAO().bankLinkageSave(SHGBankLinkageForm, locationVO.getStateShortName())){					
				log.error("SHG MONTHLY DETAIL SUCCESSFULLY.");
				request.setAttribute("NOTIFICATION","Record Save Successfully.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("::"+ e.getMessage());
		}
		this.clearPage(mapping, form, request, response);
		this.showPage(mapping, form, request, response);
		return mapping.findForward(forword);
	}
	
	// USED TO MODIFY THE SHG MONTHLY DETAIL IN BLOCK LEVEL
	/*public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		log.error("SHG Monthly Report Save or Update Method call.................");
		String forward="MODIFY_SHG_MONTHLY";	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;		// Used to get the loggers detail
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;	
			
		SHGReportCardForm SHGReportCardForm=(SHGReportCardForm)form;
		try{				
			if(new SHGMasterDAO().SaveAndUpdateSHGMonthyDetail(SHGReportCardForm, locationVO.getStateShortName())){
				request.setAttribute("NOTIFICATION","Record Modify Successfully.");
				this.modifyPage(mapping, SHGReportCardForm, request, response);
			}													
		}					
		catch(Exception e){
			e.printStackTrace();
			log.error(e);					
		}	
		return mapping.findForward(forward);
	}*/
	
	// GET FINANCILA YEAR
	/*@SuppressWarnings("unchecked")
	public void getSHGReportFinancialYearAndMonth(HttpServletRequest request, List ENTERED_MONTH_LIST){
		DateUtil dateUtil = new DateUtil();
		List FINANCIAL_YEAR_LIST = new ArrayList();		
		FINANCIAL_YEAR_LIST.add(dateUtil.getCurrentFinancialYear());
		//FINANCIAL_YEAR_LIST.add(dateUtil.getPreviousFinancialYear());
		request.setAttribute("FINANCIAL_YEAR_LIST", FINANCIAL_YEAR_LIST);
		
		// MONTH DETAIL
		List<String[]> MONTH_LIST = dateUtil.getMonthList();
		List<String[]> M_LIST = new ArrayList<String[]>();
		if(ENTERED_MONTH_LIST !=null && ENTERED_MONTH_LIST.size() > 0){
			for(String[] m : MONTH_LIST){
				boolean flag = true;
				for(int i=0; i < ENTERED_MONTH_LIST.size(); i++){
				if(m[0].equals(ENTERED_MONTH_LIST.get(i))){
					flag= false;	
					break;
				}
				}
				if(flag)
					M_LIST.add(m);
				}			
			request.setAttribute("MONTH_LIST", M_LIST);
			String LAST_MONTH = M_LIST.get(M_LIST.size()-1)[0];
			request.setAttribute("LAST_MONTH", LAST_MONTH);
		}
		else{
			request.setAttribute("MONTH_LIST", MONTH_LIST);
			String LAST_MONTH = MONTH_LIST.get(MONTH_LIST.size()-1)[0];			
			request.setAttribute("LAST_MONTH", LAST_MONTH);
		}		
	}
	
	public void getFinancialYearAndMonth(HttpServletRequest request){
		DateUtil dateUtil = new DateUtil();
		List FINANCIAL_YEAR_LIST = new ArrayList();		
		FINANCIAL_YEAR_LIST.add(dateUtil.getCurrentFinancialYear());
		//FINANCIAL_YEAR_LIST.add(dateUtil.getPreviousFinancialYear());
		request.setAttribute("FINANCIAL_YEAR_LIST", FINANCIAL_YEAR_LIST);
		
		// MONTH DETAIL
		List<String[]> MONTH_LIST = dateUtil.getMonthList();
		request.setAttribute("MONTH_LIST", MONTH_LIST);		
	}*/

}
