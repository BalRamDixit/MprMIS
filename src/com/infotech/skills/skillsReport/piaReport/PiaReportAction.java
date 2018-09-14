package com.infotech.skills.skillsReport.piaReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import com.infotech.skills.dao.pia.PiaDao;
import com.infotech.skills.dao.pia.PiaDaoImpl;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.registration.pia.RegistrationBusiness;
import com.infotech.skills.registration.pia.RegistrationDao;
import com.infotech.skills.registration.pia.RegistrationDaoImpl;
import com.infotech.skills.util.Constants;


public class PiaReportAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();
	PiaDao piaDao = new PiaDaoImpl();
	RegistrationBusiness business = null;
	RegistrationDao registrationDao = new RegistrationDaoImpl();

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PiaReportForm piaReportForm = (PiaReportForm) form;
		piaReportForm.setPiaReportStatus("");
		return mapping.findForward("showPage");

	}
	

	public ActionForward piaRegStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PiaReportForm piaReportForm = (PiaReportForm) form;
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		try {
			if (piaReportForm.getPiaReportStatus() != null
					&& !piaReportForm.getPiaReportStatus().equals("")) {
				piaDetailVO.setPiaStatus(piaReportForm.getPiaReportStatus());
				request.setAttribute("piaList",
						piaDao.getPiaListForLoginPage(piaDetailVO));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("piaListPage");
	}

	
	public ActionForward piaRegStatusInner(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PiaReportForm piaReportForm = (PiaReportForm) form;
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		try {
			if (piaReportForm.getPiaReportStatus() != null
					&& !piaReportForm.getPiaReportStatus().equals("")) {
				piaDetailVO.setPiaStatus(piaReportForm.getPiaReportStatus());
				request.setAttribute("piaList",
						piaDao.getPiaListForLoginPageInner(piaDetailVO));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("piaListPage");
	}
	
	public ActionForward getPiaDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// PiaDetailVO piaDetailVO = new PiaDetailVO();
		String PIA_CODE = request.getParameter("pid");
		try {

			if (PIA_CODE != null && !PIA_CODE.trim().equals("")
					&& !PIA_CODE.trim().equals("0")) {
				request.setAttribute("piaDetail",
						piaDao.getDetailofPia(PIA_CODE));
				request.setAttribute("piaMemberList",
						piaDao.getPiaMemberDetail(PIA_CODE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("piaCompleteDetailPage");
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

	public ActionForward showDownloadPIAExcelPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("showDownloadPIAsExcelPage");
	}
	

	/** 
	 * @author laxman
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downloadPIAsExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			String piaStatus = request.getParameter("dType");
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
			
			if(piaStatus.length() == 1){
				//user wants all files in zip format
				String file = piaDao.generatePIAExcelZipFile();
				this.sendFile("application/zip", "PIAInformation.zip",file, response);
			} else if(piaStatus.length() == 2){
				// user wants to download excel files
				String file = piaDao.generatePIAExcelFile(piaStatus);
				String excelFileName= null;
				if(piaStatus.contains("P")) {
					excelFileName = "PIAInformation.xls";
				} else {
					excelFileName = "PIAMembersInformation.xls";
				}
				this.sendFile("application/vnd.ms-excel", excelFileName,file, response);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Method for sending file with HTTP response
	 * @author laxman 
	 * @param contentType
	 * @param fileName
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	private void sendFile(String contentType, String fileName, String file, HttpServletResponse response) throws IOException {

		OutputStream os = null;
		FileInputStream fis = null;
		File zipFile = null;
		
		try {
			int BUFF_SIZE = 1024;
			byte[] buffer = new byte[BUFF_SIZE];
			zipFile = new File(file);
			fis = new FileInputStream(zipFile);
			response.setContentType(contentType);
			response.setHeader("Content-disposition", "attachment; filename="+fileName);
			Cookie cookie = new Cookie("fileDownload", "true");
			cookie.setMaxAge(2);
			response.addCookie(cookie);
			response.setContentLength((int) zipFile.length());
			os = response.getOutputStream();
			int byteCount = 0;
				do {
					byteCount = fis.read(buffer);
					if (byteCount == -1)
						break;
					os.write(buffer, 0, byteCount);
					os.flush();
				} while (true);
		} catch (Exception excp) {
			excp.printStackTrace();
		} finally {
			os.close();
			fis.close();
			zipFile.delete();
		}
	}

}
