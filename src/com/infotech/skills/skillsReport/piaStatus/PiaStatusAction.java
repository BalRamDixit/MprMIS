package com.infotech.skills.skillsReport.piaStatus;

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
import com.infotech.sgsy.util.SGSYConstants;
import com.infotech.skills.dao.pia.PiaStatusDaoImpl;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.skillsReport.piaLog.PiaLogForm;

public class PiaStatusAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();
	PiaStatusDaoImpl helper = new PiaStatusDaoImpl();

	public ActionForward showPiaStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PiaStatusForm piaStatusForm = (PiaStatusForm) form;
		request.setAttribute("piaStatusList", helper.getPiaList(piaStatusForm));
		return mapping.findForward("showPiaStatus");
	
	}


	public void createExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		
		log.info("Inside  createExcel Method Log In By "+loginVO.getUserid()+" Of LevelCode "+loginVO.getLevelCode()+" Of State "+loginVO.getOldEntityName()+".");

		HttpSession httpSession = request.getSession();
		PiaStatusForm piaStatusForm=(PiaStatusForm) form;
		List<Object[]> piaList =helper.getPiaList(piaStatusForm);
		
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
		String[] titles = { "S.No", "PIA Code(temporary)", "PIA Status ", "PIA Confirmation Date","PIAApplicationSubmission Date", "PIA Name",
							"PIA Permanent Number(in case of verified PIAs)","Category of Applicant","Type of business/Activity","Address","Contact Detail","State where registered","Registration number","Date of registration","PAN","TAN","TIN",
							"Details of registration under section 12A","Details of registration under section 80G","Details of registration under FCRA","Address of Land/Building","Freehold/Mortgaged","PIA Application Rejection Reason"};
		String[] titles1 = new String[] { "Address","State","District","Block ","PIN","Office Phone ","Office Fax ","Email" ,"Website","Registration Number","Registration Date","Registration Number","Registration Date","Registration Number","Registration Date"};	
		
		boolean xlsx = false;
		Workbook hwb = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();
		
		Sheet sheet = hwb.createSheet("Project Implementation Agency (PIAs) Status");
		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("D2:D4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("E2:E4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("F2:F4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("G2:G4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("H2:H4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("I2:I4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("J2:N2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("J2:J3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("O2:R2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("O2:O3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("S2:S4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("T2:T4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("U2:U4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("V2:V4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("W2:W4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("X2:X4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Y2:Z2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Y2:Y3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AA2:AB2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AA2:AA3"));
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("AC2:AD2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AC2:AC3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AE2:AE4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AF2:AF4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AG2:BW4"));
		
		
		
		
		sheet.setFitToPage(true);
		sheet.setVerticallyCenter(true);
		sheet.setHorizontallyCenter(true);
		sheet.setDefaultRowHeight((short) 11);
		
		sheet.setColumnWidth(0, (5 * 256));
		sheet.setColumnWidth(1, (15 * 256));
		sheet.setColumnWidth(2, (15 * 256));
		sheet.setColumnWidth(3, (20 * 256));
		sheet.setColumnWidth(4, (20 * 256));
		sheet.setColumnWidth(5, (65 * 256));
		sheet.setColumnWidth(6, (15 * 256));
		sheet.setColumnWidth(7, (55 * 256));
		sheet.setColumnWidth(8, (140 * 256));
		sheet.setColumnWidth(9, (130 * 256));
		sheet.setColumnWidth(10, (20 * 256));
		sheet.setColumnWidth(11, (20 * 256));
		sheet.setColumnWidth(12, (20 * 256));
		sheet.setColumnWidth(13, (20 * 256));
		sheet.setColumnWidth(14, (20 * 256));
		sheet.setColumnWidth(15, (20 * 256));
		sheet.setColumnWidth(16, (35 * 256));
		sheet.setColumnWidth(17, (45 * 256));
		sheet.setColumnWidth(18, (20 * 256));
		sheet.setColumnWidth(19, (40 * 256));
		sheet.setColumnWidth(20, (20 * 256));
		sheet.setColumnWidth(21, (20 * 256));
		sheet.setColumnWidth(22, (20 * 256));
		sheet.setColumnWidth(23, (20 * 256));
		sheet.setColumnWidth(24, (30 * 256));
		sheet.setColumnWidth(25, (20 * 256));
		sheet.setColumnWidth(26, (30 * 256));
		sheet.setColumnWidth(27, (20 * 256));
		sheet.setColumnWidth(28, (20 * 256));
		sheet.setColumnWidth(29, (20 * 256));
		sheet.setColumnWidth(30, (40 * 256));
		sheet.setColumnWidth(31, (20 * 256));
		sheet.setColumnWidth(32, (60 * 256));
		
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
			cell.setCellValue("Project Implementation Agency (PIAs) Status");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$BW$1"));
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(25);
		
			cell=row.createCell(0);
			cell.setCellValue(titles[0]);
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(titles[1]);
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(titles[2]);
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(titles[3]);
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(titles[4]);
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(titles[5]);
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(titles[6]);
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue(titles[7]);
			cell.setCellStyle(style);
		
			cell = row.createCell(8);
			cell.setCellValue(titles[8]);
			cell.setCellStyle(style);
			
			cell = row.createCell(9);
			cell.setCellValue(titles[9]);
		
			cell.setCellStyle(style);
			
			cell = row.createCell(14);
			cell.setCellValue(titles[10]);
			cell.setCellStyle(style);
			
			cell = row.createCell(18);
			cell.setCellValue(titles[11]);
			cell.setCellStyle(style);
			
			cell = row.createCell(19);
			cell.setCellValue(titles[12]);
			cell.setCellStyle(style);
			
			cell = row.createCell(20);
			cell.setCellValue(titles[13]);
			cell.setCellStyle(style);
			
			cell = row.createCell(21);
			cell.setCellValue(titles[14]);
			cell.setCellStyle(style);
			cell = row.createCell(22);
			
			cell.setCellValue(titles[15]);
			cell.setCellStyle(style);
			cell = row.createCell(23);
			
			cell.setCellValue(titles[16]);
			cell.setCellStyle(style);
			cell = row.createCell(24);
			
			cell.setCellValue(titles[17]);
			cell.setCellStyle(style);
			cell = row.createCell(26);
			
			cell.setCellValue(titles[18]);
			cell.setCellStyle(style);
			cell = row.createCell(28);
			
			cell.setCellValue(titles[19]);
			cell.setCellStyle(style);
			cell = row.createCell(30);
			
			cell.setCellValue(titles[20]);
			cell.setCellStyle(style);
			cell = row.createCell(31);
			
			cell.setCellValue(titles[21]);
			cell.setCellStyle(style);
			cell = row.createCell(32);
			
			cell.setCellValue(titles[22]);
			cell.setCellStyle(style);
			//sub head
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(30);
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(30);

			cell = row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(2);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(3);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(4);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(6);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(8);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(9);
			cell.setCellValue(titles1[0]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(10);
			cell.setCellValue(titles1[1]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(11);
			cell.setCellValue(titles1[2]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(12);
			cell.setCellValue(titles1[3]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(13);
			cell.setCellValue(titles1[4]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(14);
			cell.setCellValue(titles1[5]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(15);
			cell.setCellValue(titles1[6]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(16);
			cell.setCellValue(titles1[7]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(17);
			cell.setCellValue(titles1[8]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(18);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(19);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(20);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(21);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(22);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(23);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(24);
			cell.setCellValue(titles1[9]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(25);
			cell.setCellValue(titles1[10]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(26);
			cell.setCellValue(titles1[11]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(27);
			cell.setCellValue(titles1[12]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(28);
			cell.setCellValue(titles1[13]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(29);
			cell.setCellValue(titles1[14]);
			cell.setCellStyle(styles.get("header"));
			cell = row.createCell(30);
			cell.setCellValue("");
			cell.setCellStyle(styles.get("header"));			
			cell = row.createCell(31);
			cell.setCellValue("");
			cell.setCellStyle(style);
			cell = row.createCell(32);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			int index = 1;
			for (Object[] detail : piaList) 
			{
				row = sheet.createRow(rownum++);
				
				cell = row.createCell(0);
				cell.setCellValue(index);

				cell = row.createCell(1);
				cell.setCellValue(detail[0].toString());

				cell = row.createCell(2);
				cell.setCellValue(detail[1].toString());
			

				cell = row.createCell(3);
				if(detail[2].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[2].toString());
				}
				
				

				cell = row.createCell(4);
				if(detail[3].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[3].toString());
				}
				
				

				cell = row.createCell(5);
				cell.setCellValue(detail[4].toString());
				

				cell = row.createCell(6);
				
				if(detail[5].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[5].toString());
				}
				

				cell = row.createCell(7);
				cell.setCellValue(detail[6].toString());
				

				cell = row.createCell(8);
				cell.setCellValue(detail[7].toString());
				

				cell = row.createCell(9);
				cell.setCellValue(detail[8].toString());
				

				cell = row.createCell(10);
				cell.setCellValue(detail[9].toString());
				

				cell = row.createCell(11);
				if(detail[10].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[10].toString());
				}

				cell = row.createCell(12);
				if(detail[11].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[11].toString());
				}

				cell = row.createCell(13);
				cell.setCellValue(detail[12].toString());
				

				cell = row.createCell(14);
				cell.setCellValue(detail[13].toString());
				

				cell = row.createCell(15);
				cell.setCellValue(detail[14].toString());
				

				cell = row.createCell(16);
				cell.setCellValue(detail[15].toString());
				

				cell = row.createCell(17);
				cell.setCellValue(detail[16].toString());
				

				cell = row.createCell(18);
				cell.setCellValue(detail[17].toString());
				

				cell = row.createCell(19);
				cell.setCellValue(detail[18].toString());
				

				cell = row.createCell(20);
				cell.setCellValue(detail[19].toString());
				

				cell = row.createCell(21);
				cell.setCellValue(detail[20].toString());
				

				cell = row.createCell(22);
				cell.setCellValue(detail[21].toString());
				

				cell = row.createCell(23);
				cell.setCellValue(detail[22].toString());
			

				cell = row.createCell(24);
				cell.setCellValue(detail[23].toString());
				

				cell = row.createCell(25);
				if(detail[24].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[24].toString());
				}
			
				cell = row.createCell(26);
				if(detail[25].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[25].toString());
				}

				cell = row.createCell(27);
				if(detail[26].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[26].toString());
				}
				
			
				cell = row.createCell(28);
				if(detail[27].toString().equals("-")){
					cell.setCellValue("");
				} else
				{
					cell.setCellValue(detail[27].toString());
				}
			
				
				cell = row.createCell(29);
				if(detail[28].toString().equals("-")){
					cell.setCellValue("-");
				} else
				{
					cell.setCellValue(detail[28].toString());
				}
				
			

				cell = row.createCell(30);
				if(detail[29].toString().equals("-")){
					cell.setCellValue("");
				} else
				{
					cell.setCellValue(detail[29].toString());
				}
				
				

				cell = row.createCell(31);
				if(detail[30].toString().equals("-")){
					cell.setCellValue("");
				} else
				{
					cell.setCellValue(detail[30].toString());
				}
				
				

				cell = row.createCell(32);
				if(detail[31].toString().equals("-")){
					cell.setCellValue("");
				} else
				{
					cell.setCellValue(detail[31].toString());
				}
				

				index++;
			}
			response.setHeader("Content-Disposition", "attachment; filename=piastatus.xls");
			ServletOutputStream out = response.getOutputStream();
			hwb.write(out);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			log.error(" Exception Creating PIA Status" + e.getMessage());
		}	
	}
	}
		
		

		



