package com.infotech.sgsy.selfHelpGroup;

import java.math.BigDecimal;
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

public class SHGReportCardAction extends DispatchAction{
	
	SHGBusiness business = null;
	
	// USED TO SHOW ADD PAGE OF SHG MONTHY DETAIL IN BLOCK
	public ActionForward addPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.error("SHG Monthly Report Show add Method..............");
		String forword="ADD_SHG_MONTHLY";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
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
					request.setAttribute("SHG_MONTHLY_DETAIL_ENTERED", "SELF HELP GROUP DETAILS OF SELECTED MONTH ALREADY ENTERED.");				
				}				
				
				shgRPTCard.setPreOutstandingCCL("0.00");
				shgRPTCard.setPreOutstandingCCL("0.00");
				shgRPTCard.setBankLoanAmountOutstandingCCL(0.0);
				shgRPTCard.setBankLoanAmountOutstandingTL(0.0);
				List<MasterSHGBankLinkageVO> SHGBankLinkageList = new SHGMasterDAO().shgBankLinkageList(locationVO.getStateShortName(), shgRPTCard.getShgCode());
				for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
					if(SHGBankLinkage.getLoanType().equals("CCL")){
						shgRPTCard.setPreOutstandingCCL(SHGBankLinkage.getOutstanding().toString());
						shgRPTCard.setCashCreditLimit(SHGBankLinkage.getCashCreditLimit().toString());
					}
					if(SHGBankLinkage.getLoanType().equals("TL")){
						/*double INTEREST_AMOUNT = (SHGBankLinkage.getOutstanding().doubleValue() * 
								SHGBankLinkage.getRateOfInterest().doubleValue()) / 1200 ;
						double PREV_OUTSTANDING = INTEREST_AMOUNT + SHGBankLinkage.getOutstanding().doubleValue();*/
						shgRPTCard.setPreOutstandingTL(Double.toString(SHGBankLinkage.getOutstanding().doubleValue()));
						shgRPTCard.setEmi(SHGBankLinkage.getEmi().toString());
					}
				}				
				if(shgRegDetail != null)
					request.setAttribute("shgDetails", shgRegDetail);					
			}
			request.setAttribute("VillageList", business.getVillageList(locationVO.getBlockCode()));
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("SHG MONTHLY REPORT CARD ADD :::" + e.getMessage());
		}
		return mapping.findForward(forword);
	}
	/*public ActionForward addPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.error("SHG Monthly Report Show add Method..............");
		String forword="ADD_SHG_MONTHLY";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
		
		SHGReportCardForm shgRPTCard = (SHGReportCardForm) form;
		SHGMasterDAO helper = new SHGMasterDAO();
		try{
			if(shgRPTCard.getEntityCode()!= null){
				request.setAttribute("SHGList", helper.getSHGListForDropdown(locationVO, shgRPTCard.getEntityCode()));				
			}			
			if(shgRPTCard.getShgCode()!= null && !"".equals(shgRPTCard.getShgCode())){
				MasterShgVO shgRegDetail = new SHGMasterDAO().getshgRegDetails(locationVO, shgRPTCard.getShgCode());
				List ENTERED_MONTH = new SHGMasterDAO().getSHGMonthlyDetailMonthList(locationVO, shgRPTCard.getShgCode(), new DateUtil().getCurrentFinancialYear());
				if(shgRegDetail != null)
					request.setAttribute("shgDetails", shgRegDetail);	
				this.getSHGReportFinancialYearAndMonth(request, ENTERED_MONTH);
			}
			request.setAttribute("VillageList", business.getVillageList(locationVO.getBlockCode()));
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("SHG MONTHLY REPORT CARD ADD :::" + e.getMessage());
		}
		finally{
			
			request.setAttribute("MonthList", new DateUtil().getFinancialMonth());
			request.setAttribute("Month", new DateUtil().replaceMonthNameWithMonthCode(new DateUtil().getPreviousMonth()));
			request.setAttribute("Year", new FinanceUtil().getFinancialYear());
		}
		return mapping.findForward(forword);
	}*/
	
 
	// USED TO SHOW MODIFY PAGE OF SHG MONTHY DETAIL IN BLOCK
	public ActionForward modifyPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
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
					shgRPTCard.setPreOutstandingCCL("0.00");
					shgRPTCard.setPreOutstandingTL("0.00");
					/*shgRPTCard.setBankLoanAmountRepaidCCL(0.0);
					shgRPTCard.setBankLoanAmountRepaidTL(0.0);*/
					BeanUtils.copyProperties(shgRPTCard, SHGMonthDetailVO);					
					List<MasterSHGBankLinkageVO> SHGBankLinkageList = new SHGMasterDAO().shgBankLinkageList(locationVO.getStateShortName(), shgRPTCard.getShgCode());
					for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
						if(SHGBankLinkage.getLoanType().equals("CCL")){
							shgRPTCard.setPreOutstandingCCL(SHGBankLinkage.getOutstanding().toString());
						}
						if(SHGBankLinkage.getLoanType().equals("TL")){
							/*shgRPTCard.setBankLoanAmountRepaidTL(Double.parseDouble(SHGBankLinkage.getEmi().toString()));*/
							shgRPTCard.setPreOutstandingTL(SHGBankLinkage.getOutstanding().toString());
						}
					}
					
				} else{
					request.setAttribute("SHG_MONTHLY_DETAIL_ENTERED", "( SELF HELP GROUP MONTHLY DETAIL ) NO RECORD FOUND OF SELECTED MONTH.");
					SHGReportCardForm TEMP_SHG_REPORT_DETAIL = new SHGReportCardForm();
					TEMP_SHG_REPORT_DETAIL.setEntityCode(shgRPTCard.getEntityCode());
					TEMP_SHG_REPORT_DETAIL.setShgCode(shgRPTCard.getShgCode());
					TEMP_SHG_REPORT_DETAIL.setFinancialYear(shgRPTCard.getFinancialYear());
					TEMP_SHG_REPORT_DETAIL.setEntryMonth(shgRPTCard.getEntryMonth());
					shgRPTCard = TEMP_SHG_REPORT_DETAIL;
					
					shgRPTCard.setPreOutstandingCCL("0.00");
					shgRPTCard.setPreOutstandingTL("0.00");
					/*shgRPTCard.setBankLoanAmountRepaidCCL(0.0);
					shgRPTCard.setBankLoanAmountRepaidTL(0.0);*/
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


	 
	// USED TO VIEW THE SHG MONTHY DETAIL IN BLOCK	 
	public ActionForward viewPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
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
	}
	
	
	// USED TO SAVE THE MONTHLY DETAIL OF SHG	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.error("SHG Monthly Report Save Method call.................");
		String forword = "ADD_SHG_MONTHLY";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;	
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
		
		try{
			SHGReportCardForm SHGReportCardForm=(SHGReportCardForm)form;
			/*	
			 * 
			 *  Date 1 April'2013
			 * 	1. monthly_loan_calcaulation add current rate of interest and amount_outstanding  
			 * 	2. TempOutstaning value: Semi Master Outstanding + Calculate interest from semi-master outstanding amount
			 *  3. Amount Paid in the current month =  TempOutstaning - currentOutstanding
			 *  4. Set counter for irregular payment and NPA
			 *  5. replace semi master outstanding with the current outstanding 
			 *  6. calculating and storing the system define outstanding in the monthly loan calculations table
			 *  
			 */
			
			
			List<MasterSHGMonthlyLoanCalculationsVO> BankLoanMonthlyList = new ArrayList<MasterSHGMonthlyLoanCalculationsVO>();			
			List<MasterSHGBankLinkageVO> SHGBankLinkageList = new SHGMasterDAO().shgBankLinkageList(locationVO.getStateShortName(), SHGReportCardForm.getShgCode());
			
			for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
				MasterSHGMonthlyLoanCalculationsVO BankLoanMonthlyDetail = new MasterSHGMonthlyLoanCalculationsVO();
				
				if(SHGBankLinkage.getLoanType().equals("CCL")){
					BeanUtils.copyProperties(BankLoanMonthlyDetail, SHGReportCardForm);
					// USED TO CALCULATE THE LOAN INTEREST AMOUNT
					double INTEREST_AMOUNT = (SHGBankLinkage.getOutstanding().doubleValue() 
											* SHGBankLinkage.getRateOfInterest().doubleValue())/1200 ;
										
					// USED TO CALCULATE THE LOAN AMOUNT REPAID
					BigDecimal AMOUNT_REPAID = new BigDecimal( SHGBankLinkage.getOutstanding().doubleValue() 
							- SHGReportCardForm.getBankLoanAmountOutstandingCCL());
					 
					BankLoanMonthlyDetail.setRepaidPrincipal(AMOUNT_REPAID);
					BankLoanMonthlyDetail.setInterestPaid(new BigDecimal(INTEREST_AMOUNT));
					BankLoanMonthlyDetail.setShgBankLinkageCode(SHGBankLinkage.getShgBankLinkageCode());					
					BankLoanMonthlyDetail.setRateOfInterest(SHGBankLinkage.getRateOfInterest());
					BankLoanMonthlyDetail.setPrincipalBeforeRepayment(SHGBankLinkage.getOutstanding());
					SHGBankLinkage.setOutstanding(new BigDecimal(SHGReportCardForm.getBankLoanAmountOutstandingCCL()));
					if(SHGBankLinkage.getOutstanding().doubleValue() > SHGBankLinkage.getCashCreditLimit().doubleValue()){
						SHGBankLinkage.setIrragularCounter(SHGBankLinkage.getIrragularCounter() + 1);
					}
				}
				if(SHGBankLinkage.getLoanType().equals("TL")){
					BeanUtils.copyProperties(BankLoanMonthlyDetail, SHGReportCardForm);
					// USED TO CALCULATE THE LOAN INTEREST AMOUNT
					double INTEREST_AMOUNT = (SHGBankLinkage.getOutstanding().doubleValue() * 
												SHGBankLinkage.getRateOfInterest().doubleValue()) / 1200 ;
					double SYSTEM_INTEREST_AMOUNT = (SHGBankLinkage.getSystemDefinedOutstanding().doubleValue() * 
							SHGBankLinkage.getRateOfInterest().doubleValue()) / 1200 ;
					
					double INTEREST_AMOUNT_ABOVE7PER = (SHGBankLinkage.getOutstanding().doubleValue() * 7) / 1200 ;		
					BankLoanMonthlyDetail.setInterestAmountAbove7per(new BigDecimal(INTEREST_AMOUNT_ABOVE7PER));
					log.error("INTEREST_AMOUNT_ABOVE 7 PERCENT : "+INTEREST_AMOUNT_ABOVE7PER);
					
					// USED TO CALCULATE THE LOAN AMOUNT REPAID
					BigDecimal AMOUNT_REPAID = new BigDecimal(SHGBankLinkage.getOutstanding().doubleValue() 
							- SHGReportCardForm.getBankLoanAmountOutstandingTL());
					
					BankLoanMonthlyDetail.setRepaidPrincipal(AMOUNT_REPAID);
					BankLoanMonthlyDetail.setInterestPaid(new BigDecimal(INTEREST_AMOUNT));
					/*BankLoanMonthlyDetail.setShgBankLinkageCode(SHGBankLinkage.getShgBankLinkageCode());*/
					BankLoanMonthlyDetail.setRateOfInterest(SHGBankLinkage.getRateOfInterest());
					BankLoanMonthlyDetail.setPrincipalBeforeRepayment(SHGBankLinkage.getOutstanding());
					BankLoanMonthlyDetail.setShgBankLinkageCode(SHGBankLinkage.getShgBankLinkageCode());
					
					SHGBankLinkage.setSystemDefinedOutstanding(new BigDecimal((SHGBankLinkage.getSystemDefinedOutstanding().doubleValue()+SYSTEM_INTEREST_AMOUNT)
													-SHGBankLinkage.getEmi().doubleValue()));
					
					// FOR IRREGULAR ACCOUNT 
					if( AMOUNT_REPAID.doubleValue() + INTEREST_AMOUNT < SHGBankLinkage.getEmi().doubleValue()){
						SHGBankLinkage.setIrragularCounter(SHGBankLinkage.getIrragularCounter() + 1);
					}
					// FOR NPA ACCOUNT
					if( AMOUNT_REPAID.doubleValue() <=0 && 
							(SHGReportCardForm.getBankLoanAmountOutstandingTL()-INTEREST_AMOUNT)>=SHGBankLinkage.getOutstanding().doubleValue()){
						SHGBankLinkage.setNpaCounter(SHGBankLinkage.getNpaCounter() + 1);
					}else{
						SHGBankLinkage.setNpaCounter(0);
					}
					
					SHGBankLinkage.setOutstanding(new BigDecimal(SHGReportCardForm.getBankLoanAmountOutstandingTL()));
					// FOR RESET THE IRREGULAR COUNT AND NPA COUNT
					if(SHGReportCardForm.getBankLoanAmountOutstandingTL() <= SHGBankLinkage.getSystemDefinedOutstanding().doubleValue()){
						SHGBankLinkage.setIrragularCounter(0);
						SHGBankLinkage.setNpaCounter(0);
					}
					SHGBankLinkage.setPaymentCounter(SHGBankLinkage.getPaymentCounter() + 1);					
				}
				BankLoanMonthlyList.add(BankLoanMonthlyDetail);
			}
			
			if(new SHGMasterDAO().SaveSHGMonthyDetail(SHGReportCardForm, locationVO.getStateShortName(), SHGBankLinkageList, BankLoanMonthlyList)){						
				log.error("SHG MONTHLY DETAIL SUCCESSFULLY.");
				request.setAttribute("NOTIFICATION","Record Save Successfully.");
				form.reset(mapping, request);
				request.setAttribute("VillageList", business.getVillageList(locationVO.getBlockCode()));
			}	
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("::"+ e.getMessage());
		}	
		return mapping.findForward(forword);
	}

	
	// USED TO MODIFY THE SHG MONTHLY DETAIL IN BLOCK LEVEL
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		log.error("SHG Monthly Report Save or Update Method call.................");
		String forward="MODIFY_SHG_MONTHLY";	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;		// Used to get the loggers detail
		business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;	
			
		SHGReportCardForm SHGReportCardForm=(SHGReportCardForm)form;
		/*	
		 * 
		 *  Date 4 April'2013
		 * 	2. modify SHG_BANK_LINKAGE outstanding if found any change IRREGULAR_COUNTER and NPA_COUNTER
		 *  3. monthly_loan_calcaulation modify repaid account
		 *  
		 */
		try{	
			List<MasterSHGMonthlyLoanCalculationsVO> BankLoanMonthlyList = new ArrayList<MasterSHGMonthlyLoanCalculationsVO>();			
			List<MasterSHGBankLinkageVO> SHGBankLinkageList = new SHGMasterDAO().shgBankLinkageList(locationVO.getStateShortName(), SHGReportCardForm.getShgCode());
			
			for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
				MasterSHGMonthlyLoanCalculationsVO BankLoanMonthlyDetail = new MasterSHGMonthlyLoanCalculationsVO();
				
				if(SHGBankLinkage.getLoanType().equals("CCL")){
					if(SHGBankLinkage.getOutstanding().doubleValue() != SHGReportCardForm.getBankLoanAmountOutstandingCCL()){
						BeanUtils.copyProperties(BankLoanMonthlyDetail, SHGReportCardForm);						
						if(SHGBankLinkage.getOutstanding().doubleValue() > SHGReportCardForm.getBankLoanAmountOutstandingTL()){							
							BankLoanMonthlyDetail.setRepaidPrincipal(null);
						}else if(SHGBankLinkage.getOutstanding().doubleValue() < SHGReportCardForm.getBankLoanAmountOutstandingTL()){							
							BankLoanMonthlyDetail.setRepaidPrincipal(null);
						}
						BankLoanMonthlyDetail.setShgBankLinkageCode(SHGBankLinkage.getShgBankLinkageCode());
						SHGBankLinkage.setOutstanding(new BigDecimal(SHGReportCardForm.getBankLoanAmountOutstandingTL()));
					}
				}
				if(SHGBankLinkage.getLoanType().equals("TL")){					
					if(SHGBankLinkage.getOutstanding().doubleValue() != SHGReportCardForm.getBankLoanAmountOutstandingTL()){						
						// Either entered outstanding is greater of less than the data base outstanding
						BeanUtils.copyProperties(BankLoanMonthlyDetail, SHGReportCardForm);						
						if(SHGBankLinkage.getOutstanding().doubleValue() > SHGReportCardForm.getBankLoanAmountOutstandingTL()){							
							BankLoanMonthlyDetail.setRepaidPrincipal(null);
						}else if(SHGBankLinkage.getOutstanding().doubleValue() < SHGReportCardForm.getBankLoanAmountOutstandingTL()){							
							BankLoanMonthlyDetail.setRepaidPrincipal(null);
						}				
						BankLoanMonthlyDetail.setShgBankLinkageCode(SHGBankLinkage.getShgBankLinkageCode());
												
						SHGBankLinkage.setOutstanding(new BigDecimal(SHGReportCardForm.getBankLoanAmountOutstandingTL()));
						// FOR RESET THE IRREGULAR COUNT AND NPA COUNT
						if(SHGReportCardForm.getBankLoanAmountOutstandingTL() <= SHGBankLinkage.getSystemDefinedOutstanding().doubleValue()){
							SHGBankLinkage.setIrragularCounter(0);
							SHGBankLinkage.setNpaCounter(0);
						}						
					}
				}
				BankLoanMonthlyList.add(BankLoanMonthlyDetail);
			}
			
			if(new SHGMasterDAO().UpdateSHGMonthyDetail(SHGReportCardForm, locationVO.getStateShortName(), SHGBankLinkageList, BankLoanMonthlyList)){
				request.setAttribute("NOTIFICATION","Record Modify Successfully.");
				this.modifyPage(mapping, SHGReportCardForm, request, response);
			}													
		}					
		catch(Exception e){
			e.printStackTrace();
			log.error(e);					
		}	
		return mapping.findForward(forward);
	}
	
	// GET FINANCILA YEAR
	@SuppressWarnings("unchecked")
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
	}

}
