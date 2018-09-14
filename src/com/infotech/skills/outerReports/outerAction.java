package com.infotech.skills.outerReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.skills.dao.pia.MasterCommonDao;
import com.infotech.skills.dao.pia.MasterCommonDaoImpl;
import com.infotech.skills.dao.pia.PiaDao;
import com.infotech.skills.dao.pia.PiaDaoImpl;

import com.infotech.skills.hbm.piaprofile.PiaDetailVO;

import com.infotech.skills.util.Constants;


public class outerAction extends DispatchAction
{
	
//	PiaDao piaDao = new PiaDaoImpl();
	MasterCommonDao masterCommonDao = new MasterCommonDaoImpl();
	public ActionForward showIndex(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) 	throws Exception 
	{
//		request.setAttribute("outerReportCount", piaDao.outerReportCount());			
		return mapping.findForward("indexPage");	
	}	
	
	public ActionForward showResource(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{		
		return mapping.findForward("resourcePage");	
	}	


	
	public ActionForward showpiaRegStatusPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		String reportType = request.getParameter("reportType");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, -2); 
		Date output = sdf.parse(sdf.format(c.getTime()));
		
		if(reportType.equals("PSV")){
//			List<PiaDetailVO> piaListTemp = piaDao.getPiaListForIndexPage("PSV");
			List piaList = new ArrayList();
//			for(PiaDetailVO piaDetailVO: piaListTemp){
//				if(piaDetailVO.getModifyOn() == null || piaDetailVO.getModifyOn() == ""){
//					piaDetailVO.setModifyOn(piaDetailVO.getCreatedOn());
//				}
//				piaList.add(piaDetailVO);
//			}
			request.setAttribute("PSV", piaList);
			request.setAttribute("title", "Applications filed for PRN allotment");
		}
		
		else if(reportType.equals("V")){
//			request.setAttribute("V", piaDao.getPiaListForIndexPage("V"));
			request.setAttribute("title", "Applicants alloted PRN");
		}
		
		else if(reportType.equals("R")){
//			request.setAttribute("R", piaDao.getPiaListForIndexPage("R"));
			request.setAttribute("title", " Rejected Applications");
		}
		
		else if(reportType.equals("S")){
//			List<PiaDetailVO> piaListTemp = piaDao.getPiaListForIndexPage("S");
			List piaList1 = new ArrayList();
			List piaList2 = new ArrayList();
//			for(PiaDetailVO detailVO: piaListTemp){
//				if(sdf.parse(detailVO.getModifyOn()).compareTo(output) <  0){
//					detailVO.setPiaConfirmationDate("More than 2 Days");
//					piaList1.add(detailVO);
//				}else if(sdf.parse(detailVO.getModifyOn()).compareTo(output) >= 0){
//					detailVO.setPiaConfirmationDate("Less than 2 Days");
//					piaList2.add(detailVO);
//				}
//			}
			request.setAttribute("S", "true");
			request.setAttribute("piaList1", piaList1);
			request.setAttribute("piaList2", piaList2);
			request.setAttribute("title", "Applications pending allotment of PRN ");
		}
		
		else if(reportType.equals("PW")){
//			request.setAttribute("PW", piaDao.getPiaListForIndexPage("PW"));
			request.setAttribute("title", " Applications Withdrawn after Review");
		}
		
		else if(reportType.equals("PD")){
//			request.setAttribute("PD", piaDao.getPiaListForIndexPage("PD"));
			request.setAttribute("title", " Applications Debarred");
		}
		
		else if(reportType.equals("PB")){
//			request.setAttribute("PB", piaDao.getPiaListForIndexPage("PB"));
			request.setAttribute("title", " Applications Blacklisted");
		}
		
		
		return mapping.findForward("piaRegStatusReportPage");
	}
	
	/**
	 * used to show the aajeevika skills presentation
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showPresentation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {					
		return mapping.findForward("aajeevikaPresentaion");	
	}
	
	
	//outerPiaDetail
	
	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		outerReportForm piaReportForm = (outerReportForm) form;
		piaReportForm.setPiaReportStatus("");
		return mapping.findForward("indexPage");

	}
	

	public ActionForward piaRegStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		outerReportForm piaReportForm = (outerReportForm) form;
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		try {
			if (piaReportForm.getPiaReportStatus() != null
					&& !piaReportForm.getPiaReportStatus().equals("")) {
				piaDetailVO.setPiaStatus(piaReportForm.getPiaReportStatus());
//				request.setAttribute("piaList",
//						piaDao.getPiaListForLoginPage(piaDetailVO));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("outerPiaListPage");
	}

	public ActionForward searchPIAData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String piaSearchValue =	request.getParameter("piaSearch");	
		System.out.println("PIA VALUE"+piaSearchValue);
		
		
		outerReportForm piaReportForm = (outerReportForm) form;
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		try {
			
				
//				request.setAttribute("piaList",
//						piaDao.getPiaSearchList(piaSearchValue));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("outerPiaListPage");
	}

	public ActionForward getPiaDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// PiaDetailVO piaDetailVO = new PiaDetailVO();
		String PIA_CODE = request.getParameter("pid");
		try {

//			if (PIA_CODE != null && !PIA_CODE.trim().equals("")
//					&& !PIA_CODE.trim().equals("0")) {
//				request.setAttribute("piaDetail",
//						piaDao.getDetailofPia(PIA_CODE));
//				request.setAttribute("piaMemberList",
//						piaDao.getPiaMemberDetail(PIA_CODE));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("outerPiaCompleteDetailPage");
	}
	
	public ActionForward showFiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{
		
		log.info("inside showFiles at VerifyPiaAction page");
		String fileName = request.getParameter("file");		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
        String file = Constants.LIVE_SERVER_PATH+"/"+fileName;
		
        response.setContentType("application/x-download");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        try{  
            File f = new File(file);  
            byte[] arBytes = new byte[(int)f.length()];  
            FileInputStream is = new FileInputStream(f);  
            is.read(arBytes);  
            ServletOutputStream op = response.getOutputStream();  
            op.write(arBytes);  
            op.flush();  
           }catch(IOException ioe)  {  
               ioe.printStackTrace();  
           }
        return null;
	 
 }
	

/**
 * 	
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 */
	public ActionForward showContactDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		request.setAttribute("contactDetails", this.masterCommonDao.getContactDetail());
		return mapping.findForward("showContactDetail");
	}
	
	public ActionForward showKnowledgeBank(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
       
        return mapping.findForward("showKnowledgeBank");
    }
	
	public ActionForward showSGSYSplProj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {		
		return mapping.findForward("SGSYSplPage");	
	}
	
	public ActionForward showProjectProposalPending(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {					
		return mapping.findForward("projectProposalPending");	
	}
	
	public ActionForward showTender(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {					
		return mapping.findForward("tenders");
	
	}
}
