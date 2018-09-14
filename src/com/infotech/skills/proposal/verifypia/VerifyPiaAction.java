package com.infotech.skills.proposal.verifypia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;





import com.infotech.sgsy.login.LoginVO;
import com.infotech.skills.dao.pia.PiaDao;
import com.infotech.skills.dao.pia.PiaDaoImpl;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;
import com.infotech.skills.registration.pia.RegistrationBusiness;
import com.infotech.skills.registration.pia.RegistrationDao;
import com.infotech.skills.registration.pia.RegistrationDaoImpl;
import com.infotech.skills.util.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class VerifyPiaAction extends DispatchAction{
	
	/**
	 * PDF CODES
	 */
	public static Font HEADER_FONT_TOP = FontFactory.getFont("georgia", 12, BaseColor.BLACK);
	public static Font SUB_HEADER_FONT = FontFactory.getFont("georgia", 8, BaseColor.BLUE);
	public static Font TABLE_HEADER_FONT = FontFactory.getFont("georgia", 8, BaseColor.BLACK);
	public static Font BODY_FONT = FontFactory.getFont("georgia", 8, BaseColor.BLACK);
	/*	public static BaseColor TABLE_HEADER_BACKGROUND = WebColors.getRGBColor("#CEEBFA");*/
	// END
	
	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();
	PiaDao piaDao = new PiaDaoImpl();
	RegistrationBusiness business = null;
	RegistrationDao registrationDao = new RegistrationDaoImpl();
	 
	
	public ActionForward showPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{
	 log.info("inside showPage at VerifyPiaAction page");
	 request.setAttribute("piaList", piaDao.getPiaListForFinalVerification());
	 return mapping.findForward("showPage");
	 
 }
	
	public ActionForward showPageForDocsCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{
	 log.info("inside showPageForDocsCheck at VerifyPiaAction page");
	 request.setAttribute("piaList", piaDao.getPiaListForDocsCheck());
	 return mapping.findForward("showPageforDocsCheck");
	 
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
            response.setContentLength((int) f.length());
            byte[] arBytes = new byte[(int)f.length()];  
            FileInputStream is = new FileInputStream(f);  
            is.read(arBytes);  
            ServletOutputStream op = response.getOutputStream();  
            op.write(arBytes);  
            op.flush();  
           }catch(IOException ioe)  
           {  
               ioe.printStackTrace();  
           }
        return null;
	 
 }

	public ActionForward getPiaDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String page = request.getParameter("page");
		String forword = null;
		try {
			VerifyPiaForm registrationForm= (VerifyPiaForm) form;
			registrationForm.setRemark("");
			registrationForm.setPiaApplicationStatus("");
			if (registrationForm.getPiaCode() != null && !registrationForm.getPiaCode().equals("")) {
				request.setAttribute("piaDetail", piaDao.getDetailofPia(registrationForm.getPiaCode()));
				request.setAttribute("piaMemberList", piaDao.getPiaMemberDetail(registrationForm.getPiaCode()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (page.equals("verify")) {
				this.showPage(mapping, form, request, response);
			} else if (page.equals("checkDocs")) {
				this.showPageForDocsCheck(mapping, form, request, response);
			}
			 else if (page.equals("reject")) {
					this.showRejectionPage(mapping, form, request, response);
				}
			 else if (page.equals("categoryChange")) {
				    request.setAttribute("piaCategory",piaDao.getCategory());
					this.showCategoryChangePage(mapping, form, request, response);
				}
			 else if (page.equals("rejectL1")) {
					this.showRejectIncompletePiaPage(mapping, form, request, response);
				}
		}
		
		if(page.equals("verify")){
			forword = "showPage";
		}else if(page.equals("checkDocs")){
			forword = "showPageforDocsCheck";
		}
		else if(page.equals("reject")){
			forword = "showRejectionPage";
		}
		else if(page.equals("categoryChange")){
			request.setAttribute("piaCategory",piaDao.getCategory());
			forword = "showCategoryChangePage";
		}
		else if(page.equals("rejectL1")){
			forword = "showIncomplePIARejectionPage";
		}
		return mapping.findForward(forword);
	}
	
	public ActionForward confirmPia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{
	 log.info("inside confirmPia at VerifyPiaAction page");
	 try {
			VerifyPiaForm piaForm = (VerifyPiaForm) form;
			LoginVO loginVo=(LoginVO) request.getSession().getAttribute("loginVO");
			PiaDetailVO piaDetailVO = new PiaDetailVO();
			BeanUtils.copyProperties(piaDetailVO, piaForm);
			boolean status = false;
			String notification=null;
			
			if(piaForm.getPiaApplicationStatus()!= null){
				 if(piaForm.getPiaApplicationStatus().equals("V")){
					status = piaDao.piaConfirmed(piaDetailVO,loginVo.getUserid());
					notification="Pia Verified successfully";
				}else if(piaForm.getPiaApplicationStatus().equals("R")){
					status = piaDao.piaRejected(piaDetailVO,loginVo.getUserid());
					notification="Pia Rejected successfully";
				}else if(piaForm.getPiaApplicationStatus().equals("S")){
					status = piaDao.piaReverted(piaDetailVO,loginVo.getUserid());
				}
				else{
					log.info("Pia documents checking page found some error.");
				}
			}			
			if(status){
				request.setAttribute("Notify", notification);
				log.info("Pia ("+piaForm.getPiaCode()+") updated successfully");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.showPage(mapping, form, request, response);
		}
		return mapping.findForward("showPage");
	}
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDocs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{
	 log.info("inside checkDocs at VerifyPiaAction page");
	 boolean status = false;
	 try {
			VerifyPiaForm piaForm = (VerifyPiaForm) form;
			PiaDetailVO piaDetailVO = new PiaDetailVO();
			BeanUtils.copyProperties(piaDetailVO, piaForm);
			piaDetailVO.setL1Remark(piaForm.getRemark());
			LoginVO loginVo=(LoginVO) request.getSession().getAttribute("loginVO");
			System.out.println("User ID is:"+loginVo.getUserid()+"and User Name is:"+loginVo.getUserName());
			
			if(piaForm.getPiaApplicationStatus()!= null){
				 if(piaForm.getPiaApplicationStatus().equals("C")){
					status = piaDao.piaDocumentsChecked(piaDetailVO,loginVo.getUserid());
				}else if(piaForm.getPiaApplicationStatus().equals("RR")){
					status = piaDao.piaDocumentsRecommendedForRejection(piaDetailVO,loginVo.getUserid());
				}else{
					log.info("Pia documents checking page found some error.");
				}
			}
			if(status){
				log.info("Pia ("+piaForm.getPiaCode()+") documents checked successfully");
			}else{
				log.info("Pia documents checking page found some error.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			form.reset(mapping, request);
			this.showPageForDocsCheck(mapping, form, request, response);
		}
		return mapping.findForward("showPageforDocsCheck");
	}
	

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createPdf(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		VerifyPiaForm registrationForm= (VerifyPiaForm) form;
		PiaDetailVO piaInfo =  piaDao.getDetailofPia(registrationForm.getPiaCode());
		List<PiaMemberDetailVO>  piaMemberInfo= piaDao.getPiaMemberDetail(registrationForm.getPiaCode());
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
		
		Document document = new Document();
		response.addHeader("content-disposition", "attachment; filename="+piaInfo.getPiaName().replaceAll("\\s+","_")+"_Detail.pdf");
		PdfWriter.getInstance(document,response.getOutputStream());
		PdfWriter.getInstance(document,new FileOutputStream(piaInfo.getPiaName()+" Detail"));
		document.open();
		
		String serverPath = getServlet().getServletContext().getRealPath("/images/Aajeevika.jpg");
	    Image aajeevikaLogo = Image.getInstance(serverPath);
	    aajeevikaLogo.scaleAbsolute(40, 30);
		/*document.add(aajeevikaLogo);*/
		
		PdfPTable headerTBL = new PdfPTable(3);
		headerTBL.setSpacingAfter(5.0f);        // Space After table starts 
		headerTBL.setWidths(new float[] {0.5f, 2.5f, 1f});
		headerTBL.setWidthPercentage(100);

		PdfPCell headerCell = new PdfPCell(aajeevikaLogo);
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerCell.setBorder(0);
		headerCell.setRowspan(2);
		headerTBL.addCell(headerCell);
		
		headerCell = new PdfPCell(new Paragraph("Aajeevika Skills", HEADER_FONT_TOP));
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setBorder(0);
	/*	headerCell.setRowspan(2);*/
		headerTBL.addCell(headerCell);
		
		headerCell = new PdfPCell(new Paragraph("Temporary \nReference Number", BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		headerCell.setBorder(0);
		headerTBL.addCell(headerCell);
		
		headerCell = new PdfPCell(new Paragraph("Project Implementation Agency (PIA) Registration Details", SUB_HEADER_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setBorder(0);
		headerTBL.addCell(headerCell);
		
		headerCell = new PdfPCell(new Paragraph(piaInfo.getPiaCode(), BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerTBL.addCell(headerCell);
		
		/*Paragraph Heading = new Paragraph("Aajeevika Skills", HEADER_FONT_TOP);
		Paragraph SUB_Heading = new Paragraph("Project Implementation Agency (PIA) Registration Details", SUB_HEADER_FONT);*/
		
		/*Heading.setAlignment(Element.ALIGN_CENTER);
		document.add(Heading);*/
		
		/*SUB_Heading.setAlignment(Element.ALIGN_CENTER);
		SUB_Heading.setSpacingAfter(8f);
		document.add(SUB_Heading);*/
		
	/*	PdfPTable piaTBL = new PdfPTable(1);		 		                   	 
		piaTBL.setSpacingBefore(5.0f);       // Space Before table starts
        table.setSpacingAfter(5.0f); 
        table.setWidthPercentage(100);*/	
		PdfPTable subHeaderTBL = new PdfPTable(2);
		subHeaderTBL.setWidths(new float[] {1f, 2.5f});
		subHeaderTBL.setSpacingAfter(8.0f);        // Space After table starts 
		subHeaderTBL.setWidthPercentage(100);
		
		headerCell = new PdfPCell (new Paragraph("Project Implementation Agency(PIA)\nName", BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		subHeaderTBL.addCell(headerCell);
		
		headerCell = new PdfPCell (new Paragraph(piaInfo.getPiaName(), BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		subHeaderTBL.addCell(headerCell);
		
		headerCell = new PdfPCell (new Paragraph("Category", BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		subHeaderTBL.addCell(headerCell);
		
		headerCell = new PdfPCell (new Paragraph(piaInfo.getCategoryName(), BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		subHeaderTBL.addCell(headerCell);
       
		String activityList[] = piaInfo.getActivityName().split(",");
		headerCell = new PdfPCell(new Paragraph("Type of Business /\nActivity of PIA", BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		subHeaderTBL.addCell(headerCell);
		
		String PiaActivityList = "";
		int index = 1;
		for(String activityName : activityList){
			PiaActivityList = PiaActivityList + index + ". " + activityName + "\n"; 
			index++;
		}
		headerCell = new PdfPCell(new Paragraph(PiaActivityList, BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);			
		subHeaderTBL.addCell (headerCell);
		
		
		// used to collect PIA detail
		PdfPTable piaDetailTBL = new PdfPTable(6);
        piaDetailTBL.setWidthPercentage(100);

		PdfPCell piaDetailCell = new PdfPCell(new Paragraph("Name", BODY_FONT));
		piaDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailCell.setBorder(0);
		piaDetailTBL.addCell(piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph( piaInfo.getPiaName(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaDetailCell.setBorder(0);
		piaDetailCell.setColspan(5);
		piaDetailTBL.addCell(piaDetailCell);
		    
		piaDetailCell = new PdfPCell(new Paragraph("Address ", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getAddress(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);	
		piaDetailCell.setColspan(3);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("Pin", BODY_FONT));
		piaDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getPin(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaDetailTBL.addCell (piaDetailCell);
 
		piaDetailCell = new PdfPCell(new Paragraph("State", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);	
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getStateName(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("District", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getDistrictName(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("Block", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getBlockName(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailTBL.addCell (piaDetailCell);
	
		piaDetailCell = new PdfPCell(new Paragraph("Email", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getEmail(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("Office Phone", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);	
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getOfficePhone(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);	
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("Office Fax No", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getOfficeFax(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);	
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("Website", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getWebsite(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);	
		piaDetailCell.setColspan(5);
		piaDetailTBL.addCell (piaDetailCell);
        
        piaDetailCell = new PdfPCell(new Paragraph("Registration Detail", BODY_FONT));
        piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
        piaDetailCell.setPaddingTop(6);
        piaDetailCell.setColspan(6);
        piaDetailCell.setBorder(0);
        piaDetailTBL.addCell (piaDetailCell);

        piaDetailCell = new PdfPCell(new Paragraph("State where registered", BODY_FONT));
        piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
        piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell(piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegistrationStateName(), BODY_FONT));
	    piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
	    piaDetailTBL.addCell(piaDetailCell);
	    
	    piaDetailCell = new PdfPCell(new Paragraph("PAN", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getPanNo(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setColspan(3);
		piaDetailTBL.addCell (piaDetailCell);
    
		piaDetailCell = new PdfPCell(new Paragraph("Registration number", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegistrationNumber(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph("TAN", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getTanNo(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setColspan(3);
		piaDetailTBL.addCell (piaDetailCell);
 
		piaDetailCell = new PdfPCell(new Paragraph("Date of registration", BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaDetailTBL.addCell (piaDetailCell);
		
		piaDetailCell = new PdfPCell(new Paragraph(piaInfo.getDateOfRegistration(), BODY_FONT));
		piaDetailCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		piaDetailCell.setColspan(5);
		piaDetailTBL.addCell (piaDetailCell);
			
	   
		// used to show the member detail of PIA
		PdfPTable memberTBL = new PdfPTable(7);	
		memberTBL.setSpacingBefore(5.0f);       // Space Before table starts
		memberTBL.setSpacingAfter(5.0f);        // Space After table starts 
		float[] columnWidthsOwnDetail = new float[] {0.7f, 1.5f, 0.7f, 0.7f, 1f, 0.7f, 0.8f};	
		memberTBL.setWidths(columnWidthsOwnDetail);      
        memberTBL.setWidthPercentage(100);
		
		PdfPCell mebmerCell = new PdfPCell(new Paragraph("Details of the Owners / Directors", BODY_FONT));
		mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mebmerCell.setBorder(0);
		mebmerCell.setColspan(7);
		memberTBL.addCell (mebmerCell);
	
		/*mebmerCell = new PdfPCell(new Paragraph("Authorised\nPerson", BODY_FONT));
		mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell(mebmerCell);
*/			
		mebmerCell = new PdfPCell(new Paragraph("Name", BODY_FONT));
		mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell(mebmerCell);
		  
		if(!(piaInfo.getCategoryCode().equals("3")) && !(piaInfo.getCategoryCode().equals("4")) )
		{
		mebmerCell = new PdfPCell(new Paragraph("Designation", BODY_FONT));
		mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
		}
		
		if(piaInfo.getCategoryCode().equals("3") || piaInfo.getCategoryCode().equals("4" ))
		{
		mebmerCell = new PdfPCell(new Paragraph("Liability", BODY_FONT));
		mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
		}
		mebmerCell = new PdfPCell(new Paragraph("Contact", BODY_FONT));
		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
			
		mebmerCell = new PdfPCell(new Paragraph("Email", BODY_FONT));
		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
			
		mebmerCell = new PdfPCell(new Paragraph("PAN", BODY_FONT));
		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
		
		mebmerCell = new PdfPCell(new Paragraph("Aadhaar No./\nVoter ID card No.", BODY_FONT));
		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
			
		mebmerCell = new PdfPCell(new Paragraph("Passport No./\nDriving Licence No.", BODY_FONT));
		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		mebmerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		memberTBL.addCell (mebmerCell);
 
	    for(PiaMemberDetailVO piaMember: piaMemberInfo){
	    	if((piaMember.getNriStatus() == null) && (piaMember.getAuthorizedPerson() == null)){
	    		/*mebmerCell = new PdfPCell(new Paragraph (piaMember.getAuthorizedPerson(), BODY_FONT));
	    		mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);			 
	    		memberTBL.addCell(mebmerCell);*/
				
	    		mebmerCell = new PdfPCell(new Paragraph (piaMember.getMemberName(), BODY_FONT));
	    		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
	    		memberTBL.addCell(mebmerCell);
				
	    		if(!(piaInfo.getCategoryCode().equals("3")) && !(piaInfo.getCategoryCode().equals("4")) ){
	    		mebmerCell = new PdfPCell(new Paragraph (piaMember.getDesignation(), BODY_FONT));
	    		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
	    		memberTBL.addCell(mebmerCell);
	    		}
	    		
	    		if(piaInfo.getCategoryCode().equals("3") || piaInfo.getCategoryCode().equals("4" )){
		    		mebmerCell = new PdfPCell(new Paragraph (piaMember.getLiability(), BODY_FONT));
		    		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		    		memberTBL.addCell(mebmerCell);
		    		}
				
	    		mebmerCell = new PdfPCell(new Paragraph (piaMember.getContact(), BODY_FONT));
	    		mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
			 	memberTBL.addCell(mebmerCell);
				
			 	mebmerCell = new PdfPCell (new Paragraph (piaMember.getEmail(), BODY_FONT));
			 	mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
				memberTBL.addCell(mebmerCell);
				
				mebmerCell = new PdfPCell(new Paragraph (piaMember.getPan(), BODY_FONT));
				mebmerCell.setHorizontalAlignment (Element.ALIGN_LEFT);
				memberTBL.addCell(mebmerCell);
				
				mebmerCell = new PdfPCell(new Paragraph (piaMember.getAadharVoterNo(), BODY_FONT));
				mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				memberTBL.addCell(mebmerCell);
				
				mebmerCell = new PdfPCell(new Paragraph (piaMember.getPassportDrivingNo(), BODY_FONT));
				mebmerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				memberTBL.addCell(mebmerCell);
	    	}
	    }
	    
	      PdfPTable authInfoTBL = new PdfPTable(4);	
	      authInfoTBL.setSpacingBefore(5.0f);       // Space Before table starts
	      authInfoTBL.setSpacingAfter(5.0f);        // Space After table starts         
	      authInfoTBL.setWidthPercentage(100);
	    
	      authInfoTBL.setWidths(new float[] {0.7f, 1.5f,1.5f,1.5f});
				
			for(PiaMemberDetailVO piaMember: piaMemberInfo){
				if(piaMember.getAuthorizedPerson() != null){
		    	if(piaMember.getAuthorizedPerson().equals("yes")){
		    		PdfPCell authInfoCell = new PdfPCell(new Paragraph("Authorized Person Details", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBorder(0);
					authInfoCell.setColspan(4);
					authInfoTBL.addCell (authInfoCell);
						
					authInfoCell = new PdfPCell(new Paragraph("Name", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					
		    		authInfoCell = new PdfPCell(new Paragraph (piaMember.getMemberName(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
							
					authInfoCell = new PdfPCell(new Paragraph("S/o,D/o,W/o", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getRelativeName(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Residence Address", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getAddress(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
							
					authInfoCell = new PdfPCell(new Paragraph("Age", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getAge(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Designation", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getDesignation(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
							
					authInfoCell = new PdfPCell(new Paragraph("Occupation", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);

					authInfoCell = new PdfPCell(new Paragraph (piaMember.getOccupation(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Contact", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);

					authInfoCell = new PdfPCell(new Paragraph (piaMember.getContact(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					
					authInfoCell = new PdfPCell(new Paragraph("Email", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
							
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getEmail(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("PAN No.", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);		    		
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getPan(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Aadhaar No./Voter ID card No.", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getAadharVoterNo(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Passport No./ Driving Licence No.", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
							
										
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getPassportDrivingNo(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("State", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getStateName(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Post Office", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);
										
					authInfoCell = new PdfPCell(new Paragraph (piaMember.getPostOffice(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
					
					authInfoCell = new PdfPCell(new Paragraph("Police Station", BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					authInfoTBL.addCell(authInfoCell);

					authInfoCell = new PdfPCell(new Paragraph (piaMember.getPoliceStation(), BODY_FONT));
					authInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					authInfoTBL.addCell(authInfoCell);
		
		    	}
			}
		}
		    
	    PdfPTable eduInfoTBL = new PdfPTable(2);	
	    eduInfoTBL.setSpacingBefore(5.0f);       // Space Before table starts
	    eduInfoTBL.setSpacingAfter(5.0f);        // Space After table starts         
	    eduInfoTBL.setWidthPercentage(100);

		if(piaInfo.getCategoryCode().equals("1")){
			eduInfoTBL.setWidths(new float[] {0.7f, 1.5f});
			    
			PdfPCell eduInfoCell = new PdfPCell(new Paragraph("Details of Land and Building owned by the Educational Institution", TABLE_HEADER_FONT));
			eduInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			eduInfoCell.setColspan(2);
			eduInfoTBL.addCell (eduInfoCell);
					
			eduInfoCell = new PdfPCell(new Paragraph("Address of Land / Building", TABLE_HEADER_FONT));
			eduInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			eduInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			eduInfoTBL.addCell(eduInfoCell);
					
			eduInfoCell = new PdfPCell(new Paragraph("Whether Freehold / mortgaged", TABLE_HEADER_FONT));
			eduInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			eduInfoCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			eduInfoTBL.addCell(eduInfoCell);
					
			eduInfoCell = new PdfPCell(new Paragraph (piaInfo.getAddressLandBuilding(), BODY_FONT));
			eduInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			eduInfoTBL.addCell(eduInfoCell);
					
			eduInfoCell = new PdfPCell(new Paragraph (piaInfo.getFreeholdMortgaged(), BODY_FONT));
			eduInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			eduInfoTBL.addCell(eduInfoCell);	
		}
		
        PdfPTable nriTBL = new PdfPTable(8);
		nriTBL.setSpacingBefore(5.0f);       // Space Before table starts
		nriTBL.setSpacingAfter(5.0f);        // Space After table starts     
		nriTBL.setWidthPercentage(100);

	   if(piaInfo.getCategoryCode().equals("3") || piaInfo.getCategoryCode().equals("4")){
		   nriTBL.setWidths(new float[] {0.7f, 1.5f,1f,1f,1f,1f,1f,1f});						
		   PdfPCell nriCell = new PdfPCell (new Paragraph("Not an Indian Citizen, details of such Partner", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		   nriCell.setColspan(8);
		   nriTBL.addCell (nriCell);
				
		   nriCell = new PdfPCell(new Paragraph("Name", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment (Element.ALIGN_LEFT);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   nriTBL.addCell(nriCell);
						
		   nriCell = new PdfPCell(new Paragraph("Country", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   nriTBL.addCell(nriCell);
						
		   nriCell = new PdfPCell(new Paragraph("Passport No", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment (Element.ALIGN_CENTER);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   nriTBL.addCell (nriCell);
									 
		   nriCell = new PdfPCell(new Paragraph("Valid till", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment (Element.ALIGN_CENTER);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   nriTBL.addCell (nriCell);
						
		   nriCell = new PdfPCell(new Paragraph("Visa valid till", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   nriTBL.addCell(nriCell);
						
		   nriCell = new PdfPCell(new Paragraph("Whether a valid work permit held in India", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   nriTBL.addCell(nriCell);
					
		   nriCell = new PdfPCell(new Paragraph("Work permit valid til", TABLE_HEADER_FONT));
		   nriCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		   nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			nriTBL.addCell(nriCell);
						
			nriCell = new PdfPCell(new Paragraph("Whether clearance from FRA, MHA obtained.", TABLE_HEADER_FONT));
			nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			nriCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			nriTBL.addCell(nriCell);
			 
	  
			for(PiaMemberDetailVO piaMember: piaMemberInfo){
				if(piaMember.getNriStatus() != null){
				if(piaMember.getNriStatus().equals("yes") ){
			  
					nriCell = new PdfPCell(new Paragraph (piaMember.getMemberName(), BODY_FONT));
					nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					nriTBL.addCell(nriCell);
							
					nriCell = new PdfPCell(new Paragraph (piaMember.getCountry(), BODY_FONT));
					nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					nriTBL.addCell(nriCell);
							
					nriCell = new PdfPCell(new Paragraph (piaMember.getPassportDrivingNo(), BODY_FONT));
					nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					nriTBL.addCell (nriCell);
							
					nriCell = new PdfPCell(new Paragraph (piaMember.getPassportValidDate(), BODY_FONT));
					nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);			 
					nriTBL.addCell(nriCell);
							
					nriCell = new PdfPCell(new Paragraph (piaMember.getVisaValidDate(), BODY_FONT));
					nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					nriTBL.addCell (nriCell);
							
					nriCell = new PdfPCell(new Paragraph (piaMember.getWorkPermit(), BODY_FONT));
					nriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					nriTBL.addCell(nriCell);
							
					nriCell = new PdfPCell (new Paragraph (piaMember.getWorkingPermitDate(), BODY_FONT));
					nriCell.setHorizontalAlignment (Element.ALIGN_LEFT);
					nriTBL.addCell (nriCell);
							
					nriCell = new PdfPCell (new Paragraph (piaMember.getClearanceFRAMHA(), BODY_FONT));
					nriCell.setHorizontalAlignment (Element.ALIGN_LEFT);
					nriTBL.addCell (nriCell);			 
				}
			}
	   }
	  }
	   
	   PdfPTable piaAdditionalDetailTBL = new PdfPTable(4);
		piaAdditionalDetailTBL.setWidths(new float[] {0.2f, 2.5f, 0.6f, 0.6f});
		piaAdditionalDetailTBL.setSpacingBefore(5.0f);       // Space Before table starts
        piaAdditionalDetailTBL.setSpacingAfter(5.0f);        // Space After table starts    
        piaAdditionalDetailTBL.setWidthPercentage(100);

		PdfPCell piaAdditionalDetailCell = new PdfPCell(new Paragraph("Additional Details", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailCell.setColspan(4);
		piaAdditionalDetailCell.setBorder(0);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
		
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("S.No", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph(" ", BODY_FONT));
	    piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    piaAdditionalDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("Registration Number", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				 
    	piaAdditionalDetailCell = new PdfPCell(new Paragraph("Registration Date", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("1", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("Details of registration under section 12A of Income Tax Act of 1956", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegNoSection12A(), BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegDateSection12A(), BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("2", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("Details of registration under section 80G of Income Tax Act of 1956", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegNoSection80G(), BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell (piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegDateSection80G(), BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
			
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("3", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph("Details of registration under FCRA", BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell (new Paragraph(piaInfo.getRegNoFCRA(), BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
				
		piaAdditionalDetailCell = new PdfPCell(new Paragraph(piaInfo.getRegDateFCRA(), BODY_FONT));
		piaAdditionalDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		piaAdditionalDetailTBL.addCell(piaAdditionalDetailCell);
				
		 
		PdfPTable verificationTBL = new PdfPTable(1);					
		verificationTBL.setSpacingAfter(10.0f);       // Space Before table starts, 
		verificationTBL.setWidthPercentage(100);
                                                            	
		for(PiaMemberDetailVO piaMember: piaMemberInfo){
			if(piaMember.getAuthorizedPerson() != null){
			if(piaMember.getAuthorizedPerson().equals("yes")){
				String text =" \n\n I,  "+ piaMember.getMemberName() +"   S/o/D/o/W/o "+ piaMember.getRelativeName()  +" age "+ piaMember.getAge() +"  occupation "+ piaMember.getOccupation()  +" residence address "+ piaMember.getAddress()  +" post office  "+ piaMember.getPostOffice() +
					           "police station "+ piaMember.getPoliceStation() +" state "+ piaMember.getStateName() +"  am the authorized person to file for registration and certify the information " +
				               "furnished above id complete and correct in all respects. In case any of the information in this registration application is found to be "+
					           "false or incorrect, then the registration may be cancelled and I may be liable for any action by the Government.\n\n" +
				    			" Place:           										Date:  "+  piaInfo.getModifyOn() +"\n\n" +
				    	        " 1. Application received on: " + piaInfo.getModifyOn()  +"\n"+
						        " 2. Temporary registration No. " + piaInfo.getPiaCode() + " (only for purpose of reference and correspondence with MoRD. This temporaryregistration number shall be valid till permanent number is given by the MoRD)";
					
			        	
				PdfPCell verificationCell = new PdfPCell(new Paragraph("Verification" + text, TABLE_HEADER_FONT));
				verificationCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				verificationCell.setBorder(0);
				verificationTBL.addCell(verificationCell);				
			}
			}
			}
	
		
		document.add(headerTBL);
		document.add(subHeaderTBL);
		document.add(piaDetailTBL);
		document.add(memberTBL);		
		if (piaInfo.getCategoryCode().equals("4") || piaInfo.getCategoryCode().equals("3") ) {
			document.add(nriTBL);
		}
		document.add(authInfoTBL);
		if (piaInfo.getCategoryCode().equals("1")) {
			document.add(eduInfoTBL);
		}
		document.add(piaAdditionalDetailTBL);
		document.add(verificationTBL);
		document.close();
		return null;
	 }
	
	
	/**
	 * PIA (WITHDRAWN-DEBARRED-BLACKLISTED)
	 */
	
	public ActionForward showRejectionPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		 	throws Exception{
			 log.info("inside showPage at VerifyPiaAction(Rejection Method) page");
			 request.setAttribute("piaList", piaDao.getPiaListForFinalRejection());
			 return mapping.findForward("showRejectionPage");
			 
		 }
	
	public ActionForward rejectPia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		 	throws Exception{
			 log.info("inside rejectPia at VerifyPiaAction Action");
			 try {
					VerifyPiaForm piaForm = (VerifyPiaForm) form;
					LoginVO loginVo=(LoginVO) request.getSession().getAttribute("loginVO");
					PiaDetailVO piaDetailVO = new PiaDetailVO();
					BeanUtils.copyProperties(piaDetailVO, piaForm);
					piaDetailVO.setPiaStatus(piaForm.getPiaApplicationStatus());
					boolean status = false;
					
					if(piaForm.getPiaApplicationStatus()!= null){
						 if(piaForm.getPiaApplicationStatus().equals("PW") ||
								 piaForm.getPiaApplicationStatus().equals("PD") ||
								 piaForm.getPiaApplicationStatus().equals("PB")){
							status = piaDao.piaReject(piaDetailVO,loginVo.getUserid());
						}						
						else{
							log.info("Pia documents rejection page found some error.");
						}
					}			
					if(status){
						log.info("Pia ("+piaForm.getPiaCode()+") rejected successfully");				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					//form.reset(mapping, request);
					//request.setAttribute("piaList", piaDao.getPiaListForFinalRejection());
					this.showRejectionPage(mapping, form, request, response);
				}
			     request.setAttribute("PIAStatusChanged", "Pia Rejection Reason has been succesfully inserted into records");
				return mapping.findForward("showRejectionPage");
			}
	
	/**
	 * For Changing Category of Verified PIAs at L3 Level (Temporary Screen)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCategoryChangePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		 	throws Exception{ 
			 log.info("inside showCategoryChangePage at VerifyPiaAction(Rejection Method) page");
			 request.setAttribute("piaList", piaDao.getPiaListForCategoryChange());
			 return mapping.findForward("showCategoryChangePage");			 
		 }
	
	/**
	 * Used to get Modified PRN Number
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getModifiedPRN(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("inside getModifiedPRN at VerifyPiaAction(Rejection Method) page");
		String CategoryShortName = null;
		String previousPrnNumber = null;
		String modifiedPrnNumber = null;
		System.out.println(request.getParameter("categoryCode"));
		if (request.getParameter("categoryCode") != null
				&& !request.getParameter("categoryCode").equals("")) {
			CategoryShortName = piaDao.getCategortShortName(request
					.getParameter("categoryCode"));
		}
		System.out.println(request.getParameter("trn"));
		if (request.getParameter("trn") != null
				&& !request.getParameter("trn").equals("")) {
			previousPrnNumber = piaDao
					.getPrnNumber(request.getParameter("trn"));
		}
		try {
			modifiedPrnNumber = previousPrnNumber.substring(0, 6)
					+ CategoryShortName + previousPrnNumber.substring(8, 12);
			System.out.println(modifiedPrnNumber);
			if (modifiedPrnNumber != null) {
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(modifiedPrnNumber);
			} else {
				// nothing to show
				response.setStatus(response.SC_NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return mapping.findForward(null);
	}
	
	public ActionForward modifyPiaCategoryAndPRN(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("inside getModifiedPRN at VerifyPiaAction(Rejection Method) page");
		LoginVO loginVo=(LoginVO) request.getSession().getAttribute("loginVO");
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		VerifyPiaForm piaForm = (VerifyPiaForm) form;
		BeanUtils.copyProperties(piaDetailVO, piaForm);
		
		String CategoryShortName = null;
		String previousPrnNumber = null;
		String modifiedPrnNumber = null;
		System.out.println(request.getParameter("categoryCode"));
		System.out.println("PIA Email::"+piaDetailVO.getEmail());
		if (request.getParameter("categoryCode") != null
				&& !request.getParameter("categoryCode").equals("")) {
			System.out.println(request.getParameter("categoryCode"));
			CategoryShortName = piaDao.getCategortShortName(request
					.getParameter("categoryCode"));
		}
		
		System.out.println(request.getParameter("trn"));
		if (request.getParameter("trn") != null
				&& !request.getParameter("trn").equals("")) {
			previousPrnNumber = piaDao
					.getPrnNumber(request.getParameter("trn"));
		}
		
		try {
			modifiedPrnNumber = previousPrnNumber.substring(0, 6)
					+ CategoryShortName + previousPrnNumber.substring(8, 12);
			System.out.println(modifiedPrnNumber);
			if (request.getParameter("categoryCode") != null
					&& !request.getParameter("categoryCode").equals("")) {
				if (piaDao.modifyPIACategoryAndNumber(piaDetailVO,request.getParameter("categoryCode"),modifiedPrnNumber,loginVo.getUserid(),request.getParameter("trn"))) {
					request.setAttribute("Notification",
							"Category and PRN Number Modified Successfully");
				} else {
					request.setAttribute("Notification",
							"Category and PRN Number Not Modified Successfully");
				}
			}
			else{
				System.out.println("No parameter");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		request.setAttribute("piaList", piaDao.getPiaListForCategoryChange());
		return mapping.findForward("showCategoryChangePage");
	}
	
	public ActionForward showRejectIncompletePiaPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		 	throws Exception{
			 log.info("inside showPage at showRejectionPageForIncompletePIA page");
			 request.setAttribute("piaList", piaDao.getPiaListForIncompletePIARejection());
			 return mapping.findForward("showIncomplePIARejectionPage");			 
		 }
	
	public ActionForward rejectIncompletePIA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		 	throws Exception{
			 log.info("inside rejectPia at VerifyPiaAction Action");
			 try {
					VerifyPiaForm piaForm = (VerifyPiaForm) form;
					LoginVO loginVo=(LoginVO) request.getSession().getAttribute("loginVO");
					PiaDetailVO piaDetailVO = new PiaDetailVO();
					BeanUtils.copyProperties(piaDetailVO, piaForm);
					piaDetailVO.setPiaStatus(piaForm.getPiaApplicationStatus());
					boolean status = false;
					
					if(piaForm.getPiaApplicationStatus()!= null){
						 if(piaForm.getPiaApplicationStatus().equals("R")){
							status = piaDao.incompletePIAReject(piaDetailVO,loginVo.getUserid());
						}						
						else{
							log.info("Pia documents rejection page found some error.");
						}
					}			
					if(status){
						log.info("Pia ("+piaForm.getPiaCode()+") rejected successfully");				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					this.showRejectIncompletePiaPage(mapping, form, request, response);
				}
			     request.setAttribute("PIAStatusChanged", "Pia Rejection Reason has been succesfully inserted into records");
				return mapping.findForward("showIncomplePIARejectionPage");
			}
}
