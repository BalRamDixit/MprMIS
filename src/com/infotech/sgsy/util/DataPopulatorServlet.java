package com.infotech.sgsy.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.master.bank.BankDAO;
import com.infotech.sgsy.master.bankBranch.BankBranchDAO;
import com.infotech.sgsy.master.block.BlockDAO;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.master.financialYear.FinancialYearVo;
import com.infotech.sgsy.master.financialYear.FinancialYrDAO;
import com.infotech.sgsy.master.village.VillageDAO;
import com.infotech.sgsy.selfHelpGroup.SHGMasterDAO;
import com.infotech.sgsy.userManagement.UserDAOImpl;
import com.infotech.skills.master.MasterDaoImpl;


public class DataPopulatorServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		try{	
			
			String funcParam=req.getParameter("funcParam");
			String primaryCode=req.getParameter("primaryCode");
			String controlName=req.getParameter("controlName");		
			String subPrimaryCode=req.getParameter("subPrimaryCode");
			String actionParam=req.getParameter("actionParam");
			
			String fyr=req.getParameter("finyr");
			String majorhead=req.getParameter("majorhead");
			String majorsubhead=req.getParameter("majorsubhead");
			String subhead=req.getParameter("subhead");
			String detailhead=req.getParameter("detailhead");
			
			String outputText="";
			List ajaxDataCol=null;
			String amount_release="";
			List postNos= new ArrayList();
			CommonUtils commonUtils=new CommonUtils();
			LocationVO vo=null;
			
			HttpSession session = req.getSession();
			
			synchronized (session){
				vo=(LocationVO) session.getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;
			}
			
			// ----------------------------------------------------------------
			// ------	Used to check the Method call and implemented	-------
			// ------				Date: 29 November 2012				-------
			// ----------------------------------------------------------------
			if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETSTATELIST")){
				ajaxDataCol = (new MasterDaoImpl()).getStateListUsingAjax();				
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);			
			} else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETDISTRICTLIST")){
				ajaxDataCol = (new MasterDaoImpl()).getDistrictListUsingAjax(primaryCode);				
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);			
			} else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETBLOCKLIST")){
				ajaxDataCol = (new MasterDaoImpl()).getBlockListUsingAjax(primaryCode);				
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);			
			}else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETBRANCHFORLOAN")){				
				String entityCode=vo.getBlockCode();
				ajaxDataCol=(new BankBranchDAO()).getBranchByBankForDropdown(primaryCode,entityCode);				
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);			
			}			
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETBRANCHFORFUND")){					
				String entityCode=vo.getDistrictCode();
				ajaxDataCol=(new BankBranchDAO()).getBranchByBankForDropdown(primaryCode,entityCode);	
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);			
			}
			else if(funcParam !=null && funcParam.trim().equalsIgnoreCase("GETBRANCHFORMORD")){
				ajaxDataCol=(new BankDAO()).getBankBranchListForMord(primaryCode);
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);
			}
			
			/*
			 *  Used to get the SHG List -- Date:29 November 2012
			 */
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETSHGLIST")){	
				if(primaryCode != null || primaryCode != ""){
					ajaxDataCol=(new SHGMasterDAO()).getSHGListForDropdown(vo, primaryCode);
				}
				outputText = commonUtils.populateValueFor(controlName, ajaxDataCol,"",true);
			}else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETSTATESHORTNAME")){
				String stateCode=req.getParameter("stateCode");
				outputText = CommonUtils.getStateShortName(stateCode); 				
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETDISTRICTSBYSTATE")){				
				ajaxDataCol=(new DistrictDAO()).getDistrictListByState(primaryCode);
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETBLOCKBYDISTRICT")){	
				String stateCode=req.getParameter("stateCode");
				BlockMasterVO blockMasterVO = new BlockMasterVO();		
				List blockList = new ArrayList();
				List list = (new BlockDAO()).getBlocksListByStateAndDistrict(stateCode, primaryCode);
				for (int i = 0; i < list.size(); i++) {
					PropertyModel propertyModel = new PropertyModel();
					propertyModel.setPropertyCode(((BlockMasterVO) list.get(i)).getBlockCode());
					propertyModel.setPropertyValue(((BlockMasterVO) list.get(i)).getBlockName()); 
					blockList.add(propertyModel);
				}
				ajaxDataCol= blockList;
				outputText = commonUtils.populateValueFor(controlName,ajaxDataCol,"",true);
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETTTOTALLOANAMOUNTTOSANCTION")){	
				//	outputText=(new ManageLoanSanctionRejectDAOImpl()).getTotalAmount(primaryCode);				
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("OVERRIDECHECKUNIQUE")){				 
				outputText = ""+ commonUtils.checkUnique(req.getParameter("tableName"), req.getParameter("columnName1"), req.getParameter("checkValue1"), req.getParameter("columnName2"), req.getParameter("checkValue2"));					
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase(Constants.COLLECTION_GETBANKTYPES )){				 
				outputText = new BankDAO().getBankTypeByBankCode(primaryCode);
			}
			
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("CHECKUNIQUE")){
				outputText = ""+commonUtils.checkUnique(req.getParameter("tableName"), req.getParameter("columnName"), req.getParameter("checkValue"));
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase(Constants.COLLECTION_GETBANKTYPES )){

				outputText = new BankDAO().getBankTypeByBankCode(primaryCode);
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("getVillages")){				
				
				StringBuffer  primaryCodeList = new StringBuffer();
				String blockList = (String)req.getParameter("primaryCode");
				//ajaxDataCol = new BankDAO().getVillagesForBlocks(blockList);
				ajaxDataCol = VillageDAO.getVillageListForBank(blockList);
				outputText = commonUtils.populateValueFor(controlName, ajaxDataCol, "", true);
			}else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETYEARLIMIT" )){
				String year = req.getParameter("primaryCode");
				FinancialYearVo finvo=(new FinancialYrDAO()).getFinancialYearDate(year);
				if(finvo!=null)
				outputText = finvo.getFinYearFr();	
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETDISTRICTSHORTNAME")){ 
				String districtCode=req.getParameter("primaryCode");
				outputText = CommonUtils.getDistrictShortName(districtCode); 
			}
			else if(funcParam != null && funcParam.trim().equalsIgnoreCase("GETUSERS")){
				List userDetails  = new ArrayList();
				String entityCode = primaryCode;
				userDetails=(new UserDAOImpl()).viewUser(entityCode);			
				System.out.println("userDetails.size:"+userDetails.size());
				outputText = commonUtils.populateValueFor(controlName,userDetails,"",true);
			}
			
			PrintWriter out = res.getWriter();
			out.print(outputText);
			out.close();
			res.flushBuffer();

		}catch(Exception e){
			e.printStackTrace();
		}

	}
}



