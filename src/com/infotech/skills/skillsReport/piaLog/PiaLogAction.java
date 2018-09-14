package com.infotech.skills.skillsReport.piaLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.selfHelpGroup.SHGMasterDAO;
import com.infotech.sgsy.util.SGSYConstants;
import com.infotech.skills.dao.pia.PiaLogDaoImpl;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;

public class PiaLogAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();
	PiaLogDaoImpl helper = new PiaLogDaoImpl();
	
	

	public ActionForward showPiaLog(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PiaLogForm piaLogForm = (PiaLogForm) form;
		request.setAttribute("piaStatusList", helper.getPiaStatusList(piaLogForm));
		return mapping.findForward("showPiaLog");
	}

	public ActionForward getPiaAllLog(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PiaLogForm piaLogForm = (PiaLogForm) form;
		piaLogForm.setPiaCode(request.getParameter("pid"));
		if(piaLogForm != null){
			request.setAttribute("piaCurrentStatus", helper.getPiaCurrentStatus(piaLogForm.getPiaCode()));
			request.setAttribute("piaOldStatus", helper.getPiaAllLogList(piaLogForm.getPiaCode()));
			request.setAttribute("piaCode", request.getParameter("pid"));
		}
		//String piaCode = "3227";
		
		
		return mapping.findForward("showPiaAllLog");
	}

	
	public void createExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		log.info("Inside  createExcel Method Log In By "+loginVO.getUserid()+" Of LevelCode "+loginVO.getLevelCode()+" Of State "+loginVO.getOldEntityName()+".");

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));		
		
		boolean xlsx = false;
		Workbook hwb = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();
		Sheet sheet = hwb.createSheet("ProjectImplementationAgencyLogs");
		sheet.setFitToPage(true);
		sheet.setVerticallyCenter(true);
		sheet.setHorizontallyCenter(true);
		sheet.setDefaultRowHeight((short) 11);
		
		sheet.setColumnWidth(0, (25 * 256));
		sheet.setColumnWidth(1, (25 * 256));
		sheet.setColumnWidth(2, (200 * 256));
		
		PiaLogForm piaLogForm = (PiaLogForm) form;
		PiaDetailVO piaCurrentStatus = helper.getPiaCurrentStatus(piaLogForm.getPiaCode());
		List  piaOldStatus   = helper.getPiaAllLogList(piaLogForm.getPiaCode());
		
		int cellnum = 0;
		int rownum = 0;
	
		int var=0;
		try {
			CellStyle style;
			style = hwb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setWrapText(true);
		
			Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
			styles.put("header", style);

			Row row = sheet.createRow(rownum++);
			row.setHeightInPoints(40);
			
			Cell cell = row.createCell(0);
			cell.setCellValue("Project Implementation Agency (PIAs) Logs");
			cell.setCellStyle(styles.get("header"));
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("PIA Current Status");
			cell.setCellStyle(styles.get("header"));
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
				
			
			cell = row.createCell(0);
			cell.setCellValue("Status");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B4:E4"));
		
			cell = row.createCell(1);
			cell.setCellValue (piaCurrentStatus.getPiaStatus());

			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("Last Modification");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B5:E5"));
			
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getModifyOn());
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("PIA Name");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B6:E6"));
			
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getPiaName());
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("Temp No");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B7:E7"));
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getPiaCode());
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("PRN");
			cell.setCellStyle(styles.get("header"));
			
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getPiaRegistrationNo());
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("Verification Date");
			cell.setCellStyle(styles.get("header"));
			
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getPiaConfirmationDate());
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("Registration No");
			cell.setCellStyle(styles.get("header"));
			
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getRegistrationNumber());
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("PAN");
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getPanNo());
			

			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("TAN");
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(1);
			cell.setCellValue(piaCurrentStatus.getTanNo());
			

			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);

			
			//piaoldstatus
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
			cell = row.createCell(0);
			cell.setCellValue("PIA Old Status");
			cell.setCellStyle(styles.get("header"));
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
		
			
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			
		
			
			cell = row.createCell(0);
			cell.setCellValue("PIA");
			cell.setCellStyle(styles.get("header"));
		
			
			cell = row.createCell(1);
			cell.setCellValue("Status");
			cell.setCellStyle(styles.get("header"));
		
			
			cell = row.createCell(2);
			
			cell.setCellValue("Status Remark");
			cell.setCellStyle(styles.get("header"));
			
            cell = row.createCell(3);
			
			cell.setCellValue("Created By");
			cell.setCellStyle(styles.get("header"));
			
            cell = row.createCell(4);
			
			cell.setCellValue("Created On");
			cell.setCellStyle(styles.get("header"));
			
            cell = row.createCell(5);
			
			cell.setCellValue("Updated By");
			cell.setCellStyle(styles.get("header"));
			
            cell = row.createCell(6);
			
			cell.setCellValue("Updated On");
			cell.setCellStyle(styles.get("header"));
			
			
			for (int i = 0; i < piaOldStatus.size(); i++) {
	
				Object[] detail = (Object[])piaOldStatus.get(i);
				
				row = sheet.createRow(rownum++);
				var = rownum;
				
				cell = row.createCell(0);
				cell.setCellValue(detail[1].toString());
				cell = row.createCell(1);
				if(detail[2] != null){
				if(detail[2].toString().equals("V"))
				cell.setCellValue("Verified");
				if(detail[2].toString().equals("C"))
					cell.setCellValue("Checked");
				if(detail[2].toString().equals("S"))
					cell.setCellValue("Submitted");
				if(detail[2].toString().equals("P")||detail[2].toString().equals("I"))
					cell.setCellValue("Incomplete");
				if(detail[2].toString().equals("R"))
					cell.setCellValue("Rejected");
				}else{
				cell.setCellValue("");	
			    }
				cell = row.createCell(3);
				if(detail[3] != null){
					
					cell.setCellValue(detail[3].toString());
					}else{
						cell.setCellValue("");	
					}
				cell = row.createCell(4);
				if(detail[4] != null){
				cell.setCellValue(detail[4].toString());
				}
				else{
					cell.setCellValue("");	
				}
				cell = row.createCell(5);
				if(detail[5] != null){
					cell.setCellValue(detail[5].toString());
					}
					else{
						cell.setCellValue("");	
					}
				cell = row.createCell(6);
				if(detail[6] != null){
					cell.setCellValue(detail[6].toString());
					}
					else{
						cell.setCellValue("");	
					}
			}
			row.setHeightInPoints(17);



			response.setHeader("Content-Disposition", "attachment; filename=PIAAllLogs.xls");
			
			
			ServletOutputStream out = response.getOutputStream();
			hwb.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error("Exception Creating PIA Log"+ e.getMessage());
		}
	}
}
	



			
	

