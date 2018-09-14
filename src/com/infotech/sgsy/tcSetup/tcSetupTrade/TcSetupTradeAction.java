package com.infotech.sgsy.tcSetup.tcSetupTrade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetDAO;
import com.infotech.sgsy.tcSetup.tcSetupDue.TcSetupDueVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.util.DateUtil;

public class TcSetupTradeAction extends DispatchAction {

	DateUtil dateUtil = new DateUtil();

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "addDetail";
		//TcSetupTradeDAO tcSetupTradeDAO = new TcSetupTradeDAO();

		try {
			//LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	

			request.setAttribute("ProjectList", sanctionProjectList);
			

			
			request.setAttribute("formName", "TRAINING CENTER TRADE TARGET");

		} catch (Exception e) {
			log.error("Exception come --  " + e.getMessage());
		}

		return mapping.findForward(forward);

	}

	public ActionForward addDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TcSetupTradeDAO helperDao = new TcSetupTradeDAO();

		try {

			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

			String status = null;
			String entity_code = loginVO.getEntityCode();
			if (entity_code.length() > 3) {
				status = "pia";
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", entity_code);// new
																// TradeTargetDAO().getPIANameByCode(entity_code)
			} else {
				status = "srlm";
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}

		//	request.setAttribute("ProjectList", helperDao.getProjectByEntitycode(loginVO.getEntityCode(), status));

			request.setAttribute("formName", "TRADE TARGET");

		} catch (Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}

		return mapping.findForward("addDetail");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TcSetupTradeActionForm helperForm = (TcSetupTradeActionForm) form;
		TcSetupTradeDAO helperDao = new TcSetupTradeDAO();
		List<TcSetupTradeActionVO> helperVo = new ArrayList<TcSetupTradeActionVO>();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		String tcid = request.getParameter("tcId");
		String projectid = request.getParameter("projectid");

		helperVo = helperDao.gettcsetUPTrade(tcid);

		try {
			// request.setAttribute("tcsetuptrade",helperVo);
			// request.setAttribute("SectorList",
			// helperDao.getSectorList(helperVo.getProjectID()));
			// ==========change request.setAttribute("TradeList",
			// helperDao.getTradebySector(helperVo.getSectorCode()));
			// request.setAttribute("tranningcenterlist",
			// helperDao.getTrainingCenter(helperVo.getProjectID()));
			/*
			 * request.setAttribute("projectid", helperVo.getProjectID());
			 * request.setAttribute("tcid", helperVo.getTcId());
			 * request.setAttribute("sectorcode", helperVo.getSectorCode());
			 * request.setAttribute("tradecode", helperVo.getTradeCode());
			 * request.setAttribute("apptradecapacity",
			 * helperVo.getAppTradeCapacity());
			 */
			// BeanUtils.copyProperties(helperForm, helperVo);
			List sectorlist =null; //helperDao.getSectorList(projectid);
			StringBuilder table = new StringBuilder();
			if (helperVo.size() > 0) {
				for (int i = 0; i < helperVo.size(); i++) {
					table.append(
							"<tr><td><select class='sector' name='sectorCode' onchange='javascript:getTrade(this);'>");
					table.append("<option value='0'>--SELECT--</option>");
					for (int h = 0; h < sectorlist.size(); h++) {
						Object[] obj = (Object[]) sectorlist.get(h);

						if (helperVo.get(i).getSector().equals(obj[0])) {
							table.append("<option value='" + obj[0] + "' selected>" + obj[1] + "</option>");
						} else {
							table.append("<option value='" + obj[0] + "'>" + obj[1] + "</option>");
						}

					}
					table.append("<input type='hidden' name='id' value='" + helperVo.get(i).getId() + "'></td>");
					table.append(
							"</select></td><td><select name='tradeCode' class='trade' onchange='checkTradeTarget(this)'>");
					List tradelist = null;/*helperDao.getTradebySector(helperVo.get(i).getSector());*/
					table.append("<option value='0'>--SELECT--</option>");
					for (int j = 0; j < tradelist.size(); j++) {
						Object[] obj = (Object[]) tradelist.get(j);
						if (helperVo.get(i).getTrade().equals(obj[0])) {
							table.append("<option value='" + obj[0] + "' selected>" + obj[1] + "</option>");
						} else {
							table.append("<option value='" + obj[0] + "'>" + obj[1] + "</option>");
						}
					}
					table.append("</select></td>");

					table.append(
							"<td><input type='text' name='appTradeCapacity' class='apptrade' onblur='calculateApprovedCapacity('blur')' onkeypress='return isNumberKey(event)' value='"
									+ helperVo.get(i).getAppTradeCapacity() + "'></td>");
					table.append("<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove' onclick='removeRow(this)'/></td></tr>");
				}

			}
			request.setAttribute("tablerow", table + "");
			request.setAttribute("tradeApprovedCap", helperDao.getTotalApprovedCapacity(tcid));
			request.setAttribute("tcid", tcid);
			request.setAttribute("projectid", projectid);
			String entity_code = loginVO.getEntityCode();

			if (entity_code.length() > 3) {
				// status="pia";
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", new TradeTargetDAO().getPIANameByCode(entity_code));
			} else {
				// status="srlm";
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mapping.findForward("editPage");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TcSetupTradeActionForm helperForm = (TcSetupTradeActionForm) form;
		TcSetupTradeDAO helperDao = new TcSetupTradeDAO();
		String forward = "showPage";

		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String entity_code = loginVO.getEntityCode();
			request.setAttribute("formName", "TRAINING CENTER TRADE TARGET");
			TcSetupTradeActionVO helperVo = new TcSetupTradeActionVO();
			/*
			 * BeanUtils.copyProperties(helperVo, helperForm);
			 * helperVo.setCreatedBy(entity_code); helperVo.setCreatedOn(new
			 * Date());
			 */

			helperDao.save(helperForm, entity_code);

			if (entity_code.length() > 3) {
				request.setAttribute("DetailList", helperDao.getDetails(loginVO.getEntityCode(), "pia"));
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", new TradeTargetDAO().getPIANameByCode(entity_code));

			} else {
				request.setAttribute("DetailList", helperDao.getDetails(loginVO.getEntityCode(), "srlm"));
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}
			request.setAttribute("formName", "TRAINING CENTER TRADE TARGET");
			helperForm.reset(mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TcSetupTradeActionForm tcSetupTradeActionForm = (TcSetupTradeActionForm) form;
		TcSetupTradeDAO tcSetupTradeDAO = new TcSetupTradeDAO();
		String forward = "addDetail";

		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			
			request.setAttribute("formName", "TRAINING CENTER TRADE TARGET");
			//TcSetupTradeActionVO helperVo = new TcSetupTradeActionVO();
			
			List<TcSetupTradeActionVO> list =(List<TcSetupTradeActionVO>) AbsFinder.getListByCondition(TcSetupTradeActionVO.class, new String[]{"trainningCenter.id",tcSetupTradeActionForm.getTcId()});
			String first[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				first[i] =list.get(i).getId();
			}
			String[] second=new String[tcSetupTradeActionForm.getId().length];
			for(int i=0;i<tcSetupTradeActionForm.getId().length;i++){
				second[i]=tcSetupTradeActionForm.getId()[i];
			}
			
			List<String> missing = new ArrayList<String>(new HashSet<String>(Arrays.asList(first)));
			for (String num : second) {
				missing.remove(num);
			}
			tcSetupTradeDAO.deleteById(missing);

			tcSetupTradeDAO.update(tcSetupTradeActionForm,loginVO);

			
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));	

			request.setAttribute("ProjectList", sanctionProjectList);
			request.setAttribute("formName", "TRAINING CENTER TRADE TARGET");
			//helperForm.reset(mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	public ActionForward getTargetDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TcSetupTradeDAO tcSetupTradeDAO = new TcSetupTradeDAO();
		String projectId=request.getParameter("projectId");
		List<TradeMasterVO> tradelist =(List<TradeMasterVO> ) tcSetupTradeDAO.getSelected__Trade__Of__Project_By_sector(projectId, request.getParameter("sector")); //helperDao.getTradebySector(request.getParameter("sector"));
		StringBuffer tradedd = new StringBuffer();
		tradedd.append("<option value='0'> --SELECT-- </option>");
		if (tradelist.size() > 0) {
			for (int i = 0; i < tradelist.size(); i++) {
				//Object arr[] = (Object[]) list.get(i);
				tradedd.append("<option value='" + tradelist.get(i).getTradeId() + "'>"+tradelist.get(i).getTradeCode()+ "-" +tradelist.get(i).getTradeName()+ "</option>");
			}

		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(tradedd.toString());

		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TcSetupTradeDAO helperDao = new TcSetupTradeDAO();
		String forward = "showPage";

		try {
			/*
			 * request.setAttribute("ViewList", helperDao.view());
			 * request.setAttribute("SectorList", helperDao.getSectorList());
			 * request.setAttribute("TradeList", helperDao.getTradeList());
			 * request.setAttribute("ProjectList", helperDao.getProjectList());
			 */

			// TcSetupTradeActionVO helperVo
			// =helperDao.gettcsetUPTrade(Integer.parseInt(request.getParameter("projectId")));
			String tcid = request.getParameter("tcId");
			helperDao.delete(tcid);

			/*LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String entity_code = loginVO.getEntityCode();
			if (entity_code.length() > 3) {
				request.setAttribute("DetailList", helperDao.getDetails(loginVO.getEntityCode(), "pia"));
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", new TradeTargetDAO().getPIANameByCode(entity_code));

			} else {
				request.setAttribute("DetailList", helperDao.getDetails(loginVO.getEntityCode(), "srlm"));
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}*/
			request.setAttribute("formName", "TRAINING CENTER TRADE TARGET");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	public ActionForward getsector(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TcSetupTradeDAO tcSetupTradeDAO = new TcSetupTradeDAO();
		String projectid = request.getParameter("projectid");
		/*List list = helperDao.getSectorList(projectid);*/
		StringBuffer tradedd = new StringBuffer();
		/*tradedd.append("<option value='0'> --SELECT-- </option>");
		List<SectorMasterVO> sectorList=(List<SectorMasterVO>) tcSetupTradeDAO.getSelected__sector__Of__Project(projectid);
		for(SectorMasterVO vo:sectorList){
			tradedd.append("<option value='"+vo.getSectorId()+"'> "+vo.getSectorName()+"</option>");			
		}*/
		List<TcSetupVO> trainningCenterList=(List<TcSetupVO>)AbsFinder.getDropDownListByCondotion(TcSetupVO.class, new String[]{"id","tcID"}, new String[]{"project.id",projectid});
		tradedd.append("<~~~>");
		tradedd.append("<option value='0'> --SELECT-- </option>");
		if (trainningCenterList.size() > 0) {
			for (int i = 0; i < trainningCenterList.size(); i++) {
				tradedd.append("<option value='" + trainningCenterList.get(i).getId() + "'>" + trainningCenterList.get(i).getTcID() + "</option>");
			}
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(tradedd.toString());

		return null;
	}

	public ActionForward getApprovedCapacity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TcSetupTradeDAO tcSetupTradeDAO = new TcSetupTradeDAO();
		String tcid = request.getParameter("tcid");
		String projectid = request.getParameter("projectId");
		//TcSetupDueVO tcSetupDueVO=(TcSetupDueVO) AbsFinder.getdetailByCondition(TcSetupDueVO.class, new String[]{"",""});
		TcSetupDueVO tcSetupDueVO=(TcSetupDueVO) AbsFinder.getdetailByCondition(TcSetupDueVO.class, new String[]{"trainingCenterId.id",tcid});
		Integer approvedCapasity=0;
		if(tcSetupDueVO!=null){
			approvedCapasity=tcSetupDueVO.getTcAppCapacity();
		};
		/*if(tcSetupDueVO!=null){
			approvedCapasity=tcSetupDueVO.getTcAppCapacity();
		}*/
		// TcSetupTradeDAO helperDao = new TcSetupTradeDAO();
		
		// LoginVO loginVO =
		// (LoginVO)request.getSession().getAttribute("loginVO");
		List<TcSetupTradeActionVO> tcSetUptradeList=(List<TcSetupTradeActionVO>) AbsFinder.getListByConditionByAscOrder(TcSetupTradeActionVO.class, new String[]{"trainningCenter.id",tcid},"id");
		
		List<SectorMasterVO> sectorlist=(List<SectorMasterVO>) tcSetupTradeDAO.getSelected__sector__Of__Project(projectid);
		
		StringBuilder table = new StringBuilder();
		if (tcSetUptradeList.size() > 0) {
			for (int i = 0; i < tcSetUptradeList.size(); i++) {
				table.append("<tr><td><select class='sector' name='sectorCode' onchange='javascript:getTrade(this);'>");
				table.append("<option value='0'>--SELECT--</option>");
				for (int h = 0; h < sectorlist.size(); h++) {
					

					if (tcSetUptradeList.get(i).getSector().getSectorId().equalsIgnoreCase(sectorlist.get(h).getSectorId())) {
						table.append("<option value='" + sectorlist.get(h).getSectorId() + "' selected>" +sectorlist.get(h).getSectorName() + "</option>");
					} else {
						table.append("<option value='" + sectorlist.get(h).getSectorId() + "'>" +sectorlist.get(h).getSectorName() + "</option>");
					}

				}
				table.append("<input type='hidden' name='id' value='" + tcSetUptradeList.get(i).getId() + "'></td>");
				table.append(
						"</select></td><td><select name='tradeCode' class='trade' onchange='checkTradeTarget(this)'>");
				List<TradeMasterVO> tradelist =(List<TradeMasterVO> ) tcSetupTradeDAO.getSelected__Trade__Of__Project_By_sector(projectid, tcSetUptradeList.get(i).getSector().getSectorId());
				table.append("<option value='0'>--SELECT--</option>");
				for (int j = 0; j < tradelist.size(); j++) {
					
					if (tcSetUptradeList.get(i).getTrade().getTradeId().equalsIgnoreCase(tradelist.get(j).getTradeId())) {
						table.append("<option value='" + tradelist.get(j).getTradeId() + "' selected>"+tradelist.get(j).getTradeCode()+"-" +tradelist.get(j).getTradeName() + "</option>");
					} else {
						table.append("<option value='" + tradelist.get(j).getTradeId() + "'>"+tradelist.get(j).getTradeCode()+"-"  + tradelist.get(j).getTradeName() + "</option>");
					}
				}
				table.append("</select></td>");

				table.append(
						"<td><input type='text' name='appTradeCapacity' class='apptrade' onblur='calculateApprovedCapacity('blur')' onkeypress='return isNumberKey(event)' value='"
								+ tcSetUptradeList.get(i).getAppTradeCapacity() + "'></td>");
				table.append("<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove' onclick='removeRow(this)'/></td></tr>");
			}
		}else{
			table.append("<tr><td><select class='sector' name='sectorCode' onchange='javascript:getTrade(this);'>");
			table.append("<option value='0'>--SELECT--</option>");
			for (int h = 0; h < sectorlist.size(); h++) {
					table.append("<option value='" + sectorlist.get(h).getSectorId() + "'>" +sectorlist.get(h).getSectorName() + "</option>");
			}
			table.append("<input type='hidden' name='id' value='0'></td>");
			table.append("</select></td><td><select name='tradeCode' class='trade' onchange='checkTradeTarget(this)'>");
			//List<TradeMasterVO> tradelist =(List<TradeMasterVO> ) tcSetupTradeDAO.getSelected__Trade__Of__Project_By_sector(projectid, tcSetUptradeList.get(i).getSector().getSectorId());
			table.append("<option value='0'>--SELECT--</option>");
			
			table.append("</select></td>");

			table.append(
					"<td><input type='text' name='appTradeCapacity' class='apptrade' onblur='calculateApprovedCapacity('blur')' onkeypress='return isNumberKey(event)' value=''></td>");
			table.append("<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove' onclick='removeRow(this)'/></td></tr>");
			
			
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(table + "<--->" + approvedCapasity);

		return null;
	}
}
