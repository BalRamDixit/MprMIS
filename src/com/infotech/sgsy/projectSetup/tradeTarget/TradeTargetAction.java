package com.infotech.sgsy.projectSetup.tradeTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;

                                   /* change total training target after sanction will done in update method and getTotalTrainingTarget*/
public class TradeTargetAction extends DispatchAction {

	DateUtil dateUtil = new DateUtil();

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TradeTargetDAO helperDao = new TradeTargetDAO();

		String forward = "addDetail";
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

			
			List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
			
			request.setAttribute("ProjectList", sanctionProjectList);

			request.setAttribute("formName", "TRADE TARGET");

		} catch (Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}

		return mapping.findForward(forward);
	}

	public ActionForward addDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TradeTargetDAO helperDao = new TradeTargetDAO();

		try {

			// request.setAttribute("ViewList", helperDao.view());
			request.setAttribute("SectorList", helperDao.getSectorList());
			/*
			 * request.setAttribute("SectorList", helperDao.getSectorList());
			 * request.setAttribute("TradeList", helperDao.getTradeList());
			 */
			// request.setAttribute("ProjectList",
			// helperDao.getProjectByEntitycode(loginVO.getEntityCode()));

			// new
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String entity_code = loginVO.getEntityCode();
			if (entity_code.length() > 3) {
				request.setAttribute("ProjectList", helperDao.getProjectByEntitycode(loginVO.getEntityCode(), "pia"));
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", helperDao.getPIANameByCode(entity_code));

			} else {
				request.setAttribute("ProjectList", helperDao.getProjectByEntitycode(loginVO.getEntityCode(), "srlm"));
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}

			request.setAttribute("formName", "TRADE TARGET");

		} catch (Exception e) {
			log.error("Exception come -- Add Employee Details " + e.getMessage());
		}

		return mapping.findForward("addDetail");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TradeTargetDAO helperDao = new TradeTargetDAO();
		List<TradeTargetVO> tradeTargetVO = new ArrayList<TradeTargetVO>();
		//TradeTargetActionForm helperForm = (TradeTargetActionForm) form;

		String projectid = request.getParameter("id");
		System.err.println(projectid);
		tradeTargetVO = helperDao.getTradeTargetDetails(projectid);

		try {

			// request.setAttribute("tradeTarget",helperVo);
			// request.setAttribute("SectorList", helperDao.getSectorList());
			List sector = helperDao.getSectorList();
			// request.setAttribute("TradeList",
			// helperDao.getTradebySector(helperVo.getSector()));
			StringBuilder tab = new StringBuilder();
			if (tradeTargetVO.size() > 0) {
				for (int i = 0; i < tradeTargetVO.size(); i++) {
					tab.append("<tr>");
					tab.append(
							"<td><select name='sector' class='sector'  style='width: 95px;' onchange='javascript:getTrade(this);'>");
					tab.append("<option value='0' >--SELECT--</option>");
					for (int j = 0; j < sector.size(); j++) {
						Object[] ss = (Object[]) sector.get(j);

						if (tradeTargetVO.get(i).getSector().equals(ss[0])) {
							tab.append("<option value='" + ss[0] + "' selected>" + ss[1] + "</option>");
						} else {
							tab.append("<option value='" + ss[0] + "'>" + ss[1] + "</option>");
						}
					}
					tab.append("</select><input type='hidden' name='tradeTargetId' value='" + tradeTargetVO.get(i).getId()
							+ "'></td>");
					tab.append(
							"<td> <select name='trade' class='trade' onchange='javascript:addoption(this)' style='width: 95px;'>");
					List trade = null;/*helperDao.getTradebySector(tradeTargetVO.get(i).getSector());*/
					tab.append("<option value='0' >--SELECT--</option>");
					for (int j = 0; j < trade.size(); j++) {
						Object[] ss = (Object[]) trade.get(j);
						if (tradeTargetVO.get(i).getTrade().equals(ss[0])) {
							tab.append("<option value='" + ss[0] + "' selected>" + ss[1] + "</option>");
						} else {
							tab.append("<option value='" + ss[0] + "'>" + ss[1] + "</option>");
						}
					}
					tab.append("</select></td>");
					tab.append(
							"<td><select name='otherTrade' onchange='javascript:gettradeduration(this);' style='width: 95px;'>");
					tab.append("<option value='0'>--SELECT--</option>");
					if (tradeTargetVO.get(i).getOjt().equals("Yes")) {
						tab.append("<option value='Yes' selected>Yes</option>");
					} else {
						tab.append("<option value='No' selected>No</option>");
					}
					tab.append("</select></td>");
					tab.append("<td><input name='totalTradeDuration' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getTotalTradeDuration()
							+ "' readonly onkeypress='return isNumberKey(event)'></td>"
							+ "<td><input name='ojt' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getOjt() + "'  onkeypress='return isNumberKey(event)'></td>"
							+ "<td><input name='trainingTarget' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getTrainingTarget() + "' onblur='checktotaltrainingTarget('blur')' ></td>"
							/*+ "<td><input name='nonResiFull' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getNonResiFull() + "' onkeypress='return isNumberKey(event)' ></td>"*/
							/*+ "<td><input name='nonResiPart' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getNonResiPart() + "' onkeypress='return isNumberKey(event)' ></td>"
							+ "<td><input name='nonResiWeekend' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getNonResiWeekend() + "' onkeypress='return isNumberKey(event)' ></td>"*/
							/*+ "<td><input name='resiFull' type='text' style='width: 50px;' value='"
							+ tradeTargetVO.get(i).getResiFull() + "' onkeypress='return isNumberKey(event)' ></td>"*/
							+ "<td><input type='button' value='Remove Row'  onclick='removeRow(this)'></td>");
					tab.append("</tr>");
				}
				String updatebutton = "<input name='Button' type='button' class='button' value=Update' onclick='javascript:updateform()'/> ";
				request.setAttribute("updatebutton", updatebutton);
			}

			request.setAttribute("tranningTarget", helperDao.getTotalTrainingTarget(projectid));
			request.setAttribute("projectid", projectid);
			request.setAttribute("rows", tab.toString());
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String entity_code = loginVO.getEntityCode();
			if (entity_code.length() > 3) {
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", helperDao.getPIANameByCode(entity_code));

			} else {
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}

			// BeanUtils.copyProperties(helperForm, helperVo);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mapping.findForward("editPage");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TradeTargetActionForm helperForm = (TradeTargetActionForm) form;
		TradeTargetDAO helperDao = new TradeTargetDAO();
		String forward = "showPage";

		try {
			// request.setAttribute("ViewList", helperDao.view());
			// request.setAttribute("SectorList", helperDao.getSectorList());
			// request.setAttribute("TradeList", helperDao.getTradeList());
			// request.setAttribute("ProjectList", helperDao.getProjectList());
			// TradeTargetVO helperVo = new TradeTargetVO();
			// BeanUtils.copyProperties(helperVo, helperForm);
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			// helperVo.setCreatedBy(loginVO.getEntityCode());
			// helperVo.setCreatedOn(new Date());
			System.out.println(helperForm.getSector()[0]);
			helperDao.save(helperForm);

			String entity_code = loginVO.getEntityCode();
			if (entity_code.length() > 3) {
				// request.setAttribute("ProjectList",
				// helperDao.getProjectByEntitycode(loginVO.getEntityCode(),"pia"));
				request.setAttribute("ViewList", helperDao.view(entity_code, "pia"));
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", helperDao.getPIANameByCode(entity_code));

			} else {
				// request.setAttribute("ProjectList",
				// helperDao.getProjectByEntitycode(loginVO.getEntityCode(),"srlm"));
				request.setAttribute("ViewList", helperDao.view(entity_code, "srlm"));
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}

			helperForm.reset(mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TradeTargetActionForm tradeTargetActionForm = (TradeTargetActionForm) form;
		TradeTargetDAO tradeTargetDAO = new TradeTargetDAO();
		String forward = "addDetail";
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		SanctionDetailVO sanctionDetailVO= (SanctionDetailVO) AbsFinder.getdetailByCondition(SanctionDetailVO.class, new String[]{"projectId.id",tradeTargetActionForm.getProjectID()});
		int projectTotalTrainingTarget =sanctionDetailVO.getSanTarget();                                                           // change-------------------------------
		try {
			List<TradeTargetVO> list =(List<TradeTargetVO>) AbsFinder.getListByConditionByAscOrder(TradeTargetVO.class, new String[]{"project.id",tradeTargetActionForm.getProjectID()},"id");
			 /*= tradeTargetDAO.getTradeTargetDetails(tradeTargetActionForm.getProjectID());*/
			if(list.size()>0){
			Integer first[] = new Integer[list.size()];
			for (int i = 0; i < list.size(); i++) {
				first[i] = list.get(i).getId();
			}
			Integer[] second = tradeTargetActionForm.getId();
			List<Integer> missing = new ArrayList<Integer>(new HashSet<Integer>(Arrays.asList(first)));
			for (Integer num : second) {
				missing.remove(num);
			}
			tradeTargetDAO.deleteById(missing);
			}
			/*int total = 0;
			for (int p = 0; p < tradeTargetActionForm.getId().length; p++) {
				total = total + tradeTargetActionForm.getTrainingTarget()[p];

			}*/
		
			/*Integer saveFlag = 0;     // value should be 1 or 0 (1 for all data saved || 0 for data not saved)
			if (projectTotalTrainingTarget == total) {
				saveFlag = 1;
			}*/
		
          List<TradeTargetVO> tradeTargetSaveOrUpdateList=new ArrayList<TradeTargetVO>();
			for(int i=0;i<tradeTargetActionForm.getId().length;i++){
				TradeTargetVO tradeTargetVO=(TradeTargetVO) AbsFinder.getById(TradeTargetVO.class, tradeTargetActionForm.getId()[i]);
				if(tradeTargetVO==null){
					tradeTargetVO=new TradeTargetVO();
					//ProjectDetailsVO projectDetailsVO=(ProjectDetailsVO) AbsFinder.getById(ProjectDetailsVO.class, tradeTargetActionForm.getProjectID());
					ProjectDetailsVO projectDetailsVO=new ProjectDetailsVO();
					projectDetailsVO.setId(tradeTargetActionForm.getProjectID());
					tradeTargetVO.setProject(projectDetailsVO);
					tradeTargetVO.setCreatedBy(loginVO.getUserid());
					tradeTargetVO.setCreatedOn(new Date());
				}else{
					tradeTargetVO.setUpdatedBy(loginVO.getUserid());
					tradeTargetVO.setUpdatedOn(new Date());
				}
				//SectorMasterVO sector= (SectorMasterVO) AbsFinder.getById(SectorMasterVO.class, tradeTargetActionForm.getSector()[i]);
				//TradeMasterVO trade=(TradeMasterVO) AbsFinder.getById(TradeMasterVO.class, tradeTargetActionForm.getTrade()[i]);
				SectorMasterVO sector=new SectorMasterVO();
				TradeMasterVO trade=new TradeMasterVO();
				sector.setSectorId(tradeTargetActionForm.getSector()[i]);
				trade.setTradeId(tradeTargetActionForm.getTrade()[i]);
				
				tradeTargetVO.setSector(sector);
				tradeTargetVO.setTrade(trade);
				tradeTargetVO.setOtherTrade(tradeTargetActionForm.getOtherTrade()[i]);
				tradeTargetVO.setTotalTradeDuration(tradeTargetActionForm.getTotalTradeDuration()[i]);
				tradeTargetVO.setOjt(tradeTargetActionForm.getOjt()[i]);
				tradeTargetVO.setTrainingTarget(tradeTargetActionForm.getTrainingTarget()[i]);
				/*tradeTargetVO.setNonResiFull(tradeTargetActionForm.getNonResiFull()[i]);*/
				/*tradeTargetVO.setNonResiPart(tradeTargetActionForm.getNonResiPart()[i]);
				tradeTargetVO.setNonResiWeekend(tradeTargetActionForm.getNonResiWeekend()[i]);*/
				/*tradeTargetVO.setResiFull(tradeTargetActionForm.getResiFull()[i]);*/
				tradeTargetVO.setSaveFlag(1);
				tradeTargetSaveOrUpdateList.add(tradeTargetVO);
			}
			tradeTargetDAO.update(tradeTargetSaveOrUpdateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject((List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"));
		
		request.setAttribute("ProjectList", sanctionProjectList);

		request.setAttribute("formName", "TRADE TARGET");
		return mapping.findForward(forward);
	}

	public ActionForward getTargetDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TradeTargetDAO helperDao = new TradeTargetDAO();
		String sectorId=request.getParameter("sector");
		//List list = helperDao.getTradebySector(request.getParameter("sector"));
		List<TradeMasterVO> trade=(List<TradeMasterVO>) helperDao.getDropDownListByCondotion(TradeMasterVO.class, new String[]{"tradeId","tradeName","tradeCode"},new String[]{"sectorId.sectorId",sectorId});

		StringBuffer tradedd = new StringBuffer();
		if (trade.size() > 0) {
			tradedd.append("<option value='0'> --SELECT-- </option>");
			for (int i = 0; i < trade.size(); i++) {
				
				tradedd.append("<option value='" + trade.get(i).getTradeId() + "'>"+trade.get(i).getTradeCode()+"-"  + trade.get(i).getTradeName() + "</option>");
			}

		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(tradedd.toString());

		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TradeTargetDAO helperDao = new TradeTargetDAO();
		String forward = "showPage";

		try {
			String projectid = request.getParameter("id");
			// TradeTargetVO helperVo
			// =helperDao.getTradeTargetDetails(Integer.parseInt(request.getParameter("id")));

			helperDao.delete(projectid);

			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String entity_code = loginVO.getEntityCode();
			if (entity_code.length() > 3) {
				// request.setAttribute("ProjectList",
				// helperDao.getProjectByEntitycode(loginVO.getEntityCode(),"pia"));
				request.setAttribute("ViewList", helperDao.view(entity_code, "pia"));
				request.setAttribute("name", "PIA Name");
				request.setAttribute("nameValue", helperDao.getPIANameByCode(entity_code));

			} else {
				// request.setAttribute("ProjectList",
				// helperDao.getProjectByEntitycode(loginVO.getEntityCode(),"srlm"));
				request.setAttribute("ViewList", helperDao.view(entity_code, "srlm"));
				request.setAttribute("name", "SRLM Name");
				request.setAttribute("nameValue", new StateDAO().getStateNameByCode(entity_code));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	public ActionForward getTotalTrainingTarget(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TradeTargetDAO tradeTargetDAO = new TradeTargetDAO();
		String projectId = request.getParameter("projectId");
		SanctionDetailVO sanctionDetailVO = (SanctionDetailVO) AbsFinder.getdetailByCondition(SanctionDetailVO.class,
				new String[] { "projectId.id", projectId });
		int totaltrainingTarget = 0;
		if (sanctionDetailVO != null) {
			totaltrainingTarget = sanctionDetailVO.getSanTarget();
		}
		List<TradeTargetVO> listTradeTargetVO = new ArrayList<TradeTargetVO>();
		listTradeTargetVO = (List<TradeTargetVO>) AbsFinder.getListByConditionByAscOrder(TradeTargetVO.class,
				new String[] { "project.id", projectId }, "id");
		StringBuilder tab = new StringBuilder();
		List<SectorMasterVO> sector = (List<SectorMasterVO>) AbsFinder.getDropDownList(SectorMasterVO.class,
				new String[] { "sectorId", "sectorName" });
		if (listTradeTargetVO.size() > 0) {
			for (int i = 0; i < listTradeTargetVO.size(); i++) {
				tab.append("<tr>");
				tab.append(
						"<td><select name='sector' class='sector'  style='width: 95px;' onchange='javascript:getTrade(this);'>");
				tab.append("<option value='0' >--SELECT--</option>");
				for (int j = 0; j < sector.size(); j++) {
					String selectedsector=null;
					try{
						selectedsector=listTradeTargetVO.get(i).getSector().getSectorId();	
					}catch(Exception e){
						selectedsector="null";
					}
					
						if (sector.get(j).getSectorId().equalsIgnoreCase(selectedsector)) {
							tab.append("<option value='" + sector.get(j).getSectorId() + "' selected>"
									+ sector.get(j).getSectorName() + "</option>");
						} else {
							tab.append("<option value='" + sector.get(j).getSectorId() + "'>"
									+ sector.get(j).getSectorName() + "</option>");
						}
					}
				tab.append("</select><input type='hidden' name='id' value='" + listTradeTargetVO.get(i).getId()
						+ "'></td>");
				tab.append(
						"<td> <select name='trade' class='trade' onchange='javascript:addoption(this)' style='width: 95px;'>");
				List<TradeMasterVO> trade=new ArrayList<TradeMasterVO>();
				String ss=null;
				try{
				ss=listTradeTargetVO.get(i).getSector().getSectorId();	
				}catch(NullPointerException e){
					ss=null;
				}
				
				if( ss !=null){
					 trade = (List<TradeMasterVO>) tradeTargetDAO.getDropDownListByCondotion(
							TradeMasterVO.class, new String[] { "tradeId", "tradeName", "tradeCode" },
							new String[] { "sectorId.sectorId", listTradeTargetVO.get(i).getSector().getSectorId() });	
				}
				
				
				tab.append("<option value='0' >--SELECT--</option>");
				
				for (int j = 0; j < trade.size(); j++) {
					String selectedtrade=null;
					try{
						selectedtrade=listTradeTargetVO.get(i).getTrade().getTradeId();	
					}catch(Exception e){
						selectedtrade="null";
					}
					if (trade.get(j).getTradeId().equalsIgnoreCase(selectedtrade)) {
						tab.append("<option value='" + trade.get(j).getTradeId() + "' selected>"
								+ trade.get(j).getTradeCode() + "-" + trade.get(j).getTradeName() + "</option>");
					} else {
						tab.append("<option value='" + trade.get(j).getTradeId() + "'>" + trade.get(j).getTradeCode()
								+ "-" + trade.get(j).getTradeName() + "</option>");
					}
				
				}
				tab.append("</select></td>");
				
				
				tab.append(
						"<td><select name='otherTrade' onchange='javascript:gettradeduration(this);' style='width: 95px;'>");
				tab.append("<option value='0'>--SELECT--</option>");
				if (listTradeTargetVO.get(i).getOtherTrade() != null
						&& listTradeTargetVO.get(i).getOtherTrade().equalsIgnoreCase("No")) {
					tab.append("<option value='Yes'>Yes</option>");
					tab.append("<option value='No' selected>No</option>");

				} else {
					tab.append("<option value='Yes' selected>Yes</option>");
					tab.append("<option value='No'>No</option>");

				}
				tab.append("</select></td>");
				tab.append("<td><input name='totalTradeDuration' type='text' style='width: 50px;' value='"
						+ listTradeTargetVO.get(i).getTotalTradeDuration() + "' ");
				if (listTradeTargetVO.get(i).getOtherTrade() != null
						&&  "NO".equalsIgnoreCase(listTradeTargetVO.get(i).getOtherTrade())) {
					tab.append("readonly ");
				}
				tab.append("onkeypress='return isNumberKey(event)'></td>"
						+ "<td><input name='ojt' type='text' style='width: 50px;' value='"
						+ listTradeTargetVO.get(i).getOjt() + "'  onkeypress='return isNumberKey(event)'></td>"
						+ "<td><input name='trainingTarget' type='text' style='width: 50px;' value='"
						+ listTradeTargetVO.get(i).getTrainingTarget() + "' onblur='checktotaltrainingTarget('blur')' ></td>"
						/*+ "<td><input name='nonResiFull' type='text' style='width: 50px;' value='"
						+ listTradeTargetVO.get(i).getNonResiFull() + "' onkeypress='return isNumberKey(event)' ></td>"
						+ "<td><input name='resiFull' type='text' style='width: 50px;' value='"
						+ listTradeTargetVO.get(i).getResiFull() + "' onkeypress='return isNumberKey(event)' ></td>"*/
						+ "<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove Row'  onclick='removeRow(this)'></td>");
				tab.append("</tr>");
			}

		} else {
			tab.append("<tr>");
			tab.append(
					"<td><select name='sector' class='sector'  style='width: 95px;' onchange='javascript:getTrade(this);'>");
			tab.append("<option value='0' >--SELECT--</option>");
			for (int j = 0; j < sector.size(); j++) {
				tab.append("<option value='" + sector.get(j).getSectorId() + "'>" + sector.get(j).getSectorName()
						+ "</option>");

			}
			tab.append("</select><input type='hidden' name='id' value='0'></td>");
			tab.append(
					"<td> <select name='trade' class='trade' onchange='javascript:addoption(this)' style='width: 95px;'>");

			tab.append("<option value='0' >--SELECT--</option>");

			tab.append("</select></td>");
			tab.append(
					"<td><select name='otherTrade' onchange='javascript:gettradeduration(this);' style='width: 95px;'>");
			tab.append("<option value='0'>--SELECT--</option>");

			tab.append("</select></td>");
			tab.append(
					"<td><input name='totalTradeDuration' type='text' style='width: 50px;' value='' readonly onkeypress='return isNumberKey(event)'></td>"
							+ "<td><input name='ojt' type='text' style='width: 50px;' value=''  onkeypress='return isNumberKey(event)'></td>"
							+ "<td><input name='trainingTarget' type='text' style='width: 50px;' value='' onblur='checktotaltrainingTarget('blur')' ></td>"
							/*+ "<td><input name='nonResiFull' type='text' style='width: 50px;' value='' onkeypress='return isNumberKey(event)' ></td>"
							+ "<td><input name='resiFull' type='text' style='width: 50px;' value='' onkeypress='return isNumberKey(event)' ></td>"*/
							+ "<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove Row'  onclick='removeRow(this)'></td>");
			tab.append("</tr>");
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(tab + "<--->" + totaltrainingTarget);
		return null;
	}

	public ActionForward gettradeDuration(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TradeTargetDAO helperDao = new TradeTargetDAO();
		TradeMasterVO tradeMasterVO=(TradeMasterVO) AbsFinder.getById(TradeMasterVO.class, request.getParameter("tradeid"));
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(String.valueOf(tradeMasterVO.getCourseDuration()));

		return null;
	}
}
