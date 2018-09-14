package com.infotech.skills.skillsSanctionOrders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.skills.util.Constants;

public class SanctionOrdersAction extends DispatchAction{
	SanctionOrdersDao sanctionDao = new SanctionOrdersDaoImpl();
	String filePath = Constants.LIVE_SERVER_PATH_SANCTION;
	
	public ActionForward showSanctionOrderUpload(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("inside show view of SanctionUpload");
	ActionForward fw = null;
	try{
		List piaDetail = sanctionDao.getPiaDetail();
		request.setAttribute("piaDetail",piaDetail);
		fw = mapping.findForward("showSanctionOrder");		
	}catch(Exception e){
		log.error("Exception in  inside show view of SanctionUpload. " + e.getMessage());
	}
	return(fw);
}
	
	public ActionForward getPrnNo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("inside getPrnNo of SanctionUpload");
		ActionForward fw = null;
	try{
		SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
		String prnNumber = sanctionDao.getPRNumber(sanctionform.getPiaCode());
		if(!prnNumber.equals("null")){
		request.setAttribute("prnIdValue",prnNumber);	
		}
		List piaDetail = sanctionDao.getPiaDetail();
		request.setAttribute("piaDetail",piaDetail);
		fw = mapping.findForward("showSanctionOrder");
		}catch(Exception e){
		log.error("Exception in  inside getPrnNo of SanctionUpload. " + e.getMessage());
	}
	return(fw);
}
	
	/*public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("inside save of SanctionUpload");
	ActionForward fw = null;
	FileOutputStream outputStreamSanctionFile;
	outputStreamSanctionFile = null;
	String modifiedDocSanctionFile = null;
	String checkFile = null;
	boolean flag = false;
	String errorMsg = null;
	
	try{
		SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
		String createdOn = new DateUtil().getCurrentDate();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	    String userId = loginVO.getUserid();
		FormFile docSanctionfile = (FormFile) sanctionform.getSanctionFile();
		String docSanctionFileName = docSanctionfile.getFileName();
		byte[] agendaFileDataDocSanction = docSanctionfile.getFileData();

		String docSanctionFilePath = filePath;
		log.info("Sanction File Path: " + docSanctionFilePath);

		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		int dotPosDocSanctionfile = docSanctionFileName.lastIndexOf(".");
		String extensionDocSanctionfile = docSanctionFileName.substring(dotPosDocSanctionfile);
		checkFile = sanctionDao.checkFileType(docSanctionfile);
		if (sanctionform.getSanctionFile().getFileName().length() > 0) {
			if (checkFile.equals("true")) {
				try {
					modifiedDocSanctionFile = sanctionDao.getSequenceCodeForSanctionFileUpload() + "_SanctionOrderDoc"+ extensionDocSanctionfile;
					String agendaPathDocSanctionfile = docSanctionFilePath+ System.getProperty("file.separator")+ modifiedDocSanctionFile;
					outputStreamSanctionFile = new FileOutputStream( new File(agendaPathDocSanctionfile));
					outputStreamSanctionFile.write(agendaFileDataDocSanction);
					flag = true;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					outputStreamSanctionFile.close();
				}
			} else {
				log.error(checkFile + " for  ("+ sanctionform.getSanctionFile().getFileName()+ ") in upload documents not supported.");
				request.setAttribute("docSanctionFileError", checkFile);
				errorMsg = checkFile + " for Sanction  Document";
			}
		}
		
		if (flag) {
			sanctionform.setSanctionFileName(modifiedDocSanctionFile);
			SanctionOrdersVO sanctionvo = new SanctionOrdersVO();					
			BeanUtils.copyProperties(sanctionvo, sanctionform);
			sanctionvo.setCreatedBy(userId);
			sanctionvo.setCreatedOn(createdOn);
			sanctionvo.setIpAddress(request.getRemoteAddr());
			sanctionvo.setSanctionFileName(modifiedDocSanctionFile);
		 if(sanctionDao.insert(sanctionvo) == true){
	    	log.info("save SuccessFully");
	    	form.reset(mapping, request);
	    	request.setAttribute("notification", "Save Successfully");
	    	List piaDetail = sanctionDao.getPiaDetail();
			request.setAttribute("piaDetail",piaDetail);
	    }
		fw = mapping.findForward("showSanctionOrder");
		}
		else{
			List piaDetail = sanctionDao.getPiaDetail();
			request.setAttribute("piaDetail",piaDetail);
			String prnIdValue = sanctionform.getPiaRegistrationNumber();
			request.setAttribute("prnIdValue",prnIdValue);
			fw = mapping.findForward("showSanctionOrder");	
		}
	}catch(Exception e){
		log.error("Exception in  inside save of SanctionUpload. " + e.getMessage());
	}
 	return(fw);
}*/
	

	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("inside save of SanctionUpload");
	ActionForward fw = null;
	FileOutputStream outputStreamSanctionFile;
	outputStreamSanctionFile = null;
	String modifiedDocSanctionFile = null;
	String checkFile = null;
	boolean flag = false;
	String errorMsg = null;
	
	try{
		SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
		String createdOn = new DateUtil().getCurrentDate();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	    String userId = loginVO.getUserid();
		FormFile docSanctionfile = (FormFile) sanctionform.getSanctionFile();
		String docSanctionFileName = docSanctionfile.getFileName();
		byte[] agendaFileDataDocSanction = docSanctionfile.getFileData();

		String docSanctionFilePath = filePath;
		log.info("Sanction File Path: " + docSanctionFilePath);

		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		int dotPosDocSanctionfile = docSanctionFileName.lastIndexOf(".");
		String extensionDocSanctionfile = docSanctionFileName.substring(dotPosDocSanctionfile);
		checkFile = sanctionDao.checkFileType(docSanctionfile);
		if (checkFile.equals("true")) {
			try{
					sanctionform.setSanctionFileName(modifiedDocSanctionFile);
					SanctionOrdersVO sanctionvo = new SanctionOrdersVO();					
					BeanUtils.copyProperties(sanctionvo, sanctionform);
					sanctionvo.setCreatedBy(userId);
					sanctionvo.setCreatedOn(createdOn);
					sanctionvo.setIpAddress(request.getRemoteAddr());
					if(sanctionDao.getDuplicateSanctionOrderDetail(sanctionform.getSanctionOrderNo(), sanctionform.getPiaCode())){
					modifiedDocSanctionFile = sanctionDao.getSequenceCodeForSanctionFileUpload() + "_SanctionOrderDoc"+ extensionDocSanctionfile;
				    String agendaPathDocSanctionfile = docSanctionFilePath+ System.getProperty("file.separator")+ modifiedDocSanctionFile;
					sanctionvo.setSanctionFileName(modifiedDocSanctionFile);
					if(sanctionDao.insert(sanctionvo)){
				    	log.info("save SuccessFully");
				    	outputStreamSanctionFile = new FileOutputStream( new File(agendaPathDocSanctionfile));
				    	outputStreamSanctionFile.write(agendaFileDataDocSanction);
				    	outputStreamSanctionFile.close();
				    	form.reset(mapping, request);
				    	request.setAttribute("notification", "Save Successfully");
				    	List piaDetail = sanctionDao.getPiaDetail();
						request.setAttribute("piaDetail",piaDetail);
				}
					}else{
					List piaDetail = sanctionDao.getPiaDetail();
					request.setAttribute("piaDetail",piaDetail);
					String prnIdValue = sanctionform.getPiaRegistrationNumber();
					request.setAttribute("prnIdValue",prnIdValue);
					request.setAttribute("sameSanctionNumber","Same Sanction Order has been already Assigned To This PIA");
				} 
		}catch(Exception e){
			log.error("Exception in  inside save of SanctionUpload. " + e.getMessage());
		}
		}else {
				log.error(checkFile + " for  ("+ sanctionform.getSanctionFile().getFileName()+ ") in upload documents not supported.");
				request.setAttribute("docSanctionFileError", checkFile);
				errorMsg = checkFile + " for Sanction  Document";
				List piaDetail = sanctionDao.getPiaDetail();
				request.setAttribute("piaDetail",piaDetail);
				String prnIdValue = sanctionform.getPiaRegistrationNumber();
				request.setAttribute("prnIdValue",prnIdValue);
			}
         fw = mapping.findForward("showSanctionOrder");
	}catch(Exception e){
		log.error("Exception in  inside save of SanctionUpload. " + e.getMessage());
	}
 	return(fw);
}	
	
	public ActionForward showSanctionUploadReport(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("inside show SanctionUploadReport");
	ActionForward fw = null;
	try{
		List sanctionDetail = sanctionDao.getSanctionOrderDetail();
		request.setAttribute("sanctionDetail",sanctionDetail);
		fw = mapping.findForward("showSanctionOrderReport");		
	}catch(Exception e){
		log.error("Exception in  inside show SanctionUploadReport. " + e.getMessage());
	}
	return(fw);
}
	
	public ActionForward showFiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{
		
		log.info("inside showFiles at Sanction page");
		String fileName = request.getParameter("file");		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
        String file = Constants.LIVE_SERVER_PATH_SANCTION+"/"+fileName;
		
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
	
	/*Modify Sanction Order
    Sandeep Tiwari
    23 March 2014*/
	
	public ActionForward modifySanctionOrderUpload(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("inside modify Sanction Order ");
	ActionForward fw = null;
	try{
		List piaDetail = sanctionDao.getPiaDetail();
		request.setAttribute("piaDetail",piaDetail);
		fw = mapping.findForward("modifySanctionOrder");		
	}catch(Exception e){
		e.printStackTrace();
		log.error("Exception in  inside show view of SanctionUpload. " + e.getMessage());
	}
	return(fw);
}
	
	
	public ActionForward getSecNo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("inside getSecNo of SanctionModify");
		ActionForward fw = null;
	try{
		SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
		List sanctionNumber = sanctionDao.getSanctionNumber(sanctionform.getPiaCode());		
		request.setAttribute("sanctionNumber",sanctionNumber);
		List piaDetail = sanctionDao.getPiaDetail();
		request.setAttribute("piaDetail",piaDetail);
		fw = mapping.findForward("modifySanctionOrder");
		}catch(Exception e){
		log.error("Exception in  inside getPrnNo of SanctionUpload. " + e.getMessage());
	}
	return(fw);
}
	
	
	public ActionForward getSanctionDetail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("inside getSanctionDetail of SanctionModify");
		ActionForward fw = null;
	try{
		SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
		List modifySanctionDetail = sanctionDao.getSanctionDetail(sanctionform.getSanctionOrderNo());		
		request.setAttribute("modifySanctionDetail",modifySanctionDetail);
		List sanctionNumber = sanctionDao.getSanctionNumber(sanctionform.getPiaCode());		
		request.setAttribute("sanctionNumber",sanctionNumber);
		List piaDetail = sanctionDao.getPiaDetail();
		request.setAttribute("piaDetail",piaDetail);
		fw = mapping.findForward("modifySanctionOrder");
		}catch(Exception e){
		log.error("Exception in  inside getPrnNo of SanctionUpload. " + e.getMessage());
	}
	return(fw);
}
	
	
	@SuppressWarnings("finally")
	public ActionForward modifySanctionOrder(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)			
throws Exception {	
	log.info("inside modify of SanctionModify");
	ActionForward fw = null;
	SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
	SanctionOrdersDao SanctionDaoImpl=null;
	SanctionDaoImpl= new SanctionOrdersDaoImpl();
	FileOutputStream outputStreamSanctionFile=null;
	FormFile formSanctionFile = null;
	String SanctionFileName = null;
	byte[] agendafileData = null;	
	String modifiedDocSanctionFileName = null;
	String SanctionFilePath = null;
	File file = null;
	String checkFile = null;
	String errorMsg = null;
	boolean flag = false, flag1 = false;

try {
		formSanctionFile = (FormFile) sanctionform.getSanctionFile();
		SanctionFileName = formSanctionFile.getFileName();
		agendafileData = formSanctionFile.getFileData();
		String modify_on = new DateUtil().getCurrentDate();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	    String userId = loginVO.getUserid();
			
	if(!SanctionFileName.equals("")){
		SanctionFilePath=filePath;
		log.info("Path to save Sanction files="+filePath);
		if (new File(filePath).isDirectory() == false) {
			new File(filePath).mkdirs();
		}
	}
	if(!SanctionFileName.equals("")){
		int dotPosSanction = SanctionFileName.lastIndexOf(".");
		String extensionSanction = SanctionFileName.substring(dotPosSanction);
		int position=sanctionform.getSanctionFileName().indexOf("_");
		String name=sanctionform.getSanctionFileName().substring(0, position);
		modifiedDocSanctionFileName = name  + "_SanctionOrderDoc"+extensionSanction;
		String agendapathSanction = SanctionFilePath+ System.getProperty("file.separator")+modifiedDocSanctionFileName;
		checkFile = SanctionDaoImpl.checkFileType(formSanctionFile);
		if (sanctionform.getSanctionFile().getFileName().length() > 0 && checkFile.equals("true")){
			try{
				outputStreamSanctionFile = new FileOutputStream(new File(agendapathSanction));
				outputStreamSanctionFile.write(agendafileData);
				file= new File(filePath+System.getProperty("file.separator")+sanctionform.getSanctionFileName().substring(0,2));
				file.delete();
				flag1 = true;
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				outputStreamSanctionFile.close();
			}
			
		}else{
			log.error(checkFile+" for sanction File ("+sanctionform.getSanctionFile().getFileName()+") in Modify Sanction File");
			request.setAttribute("docSanctionFileError", checkFile);
			errorMsg = checkFile+"for Sanction File";			
			List modifySanctionDetail = sanctionDao.getSanctionDetail(sanctionform.getSanctionOrderNo());
			SanctionOrdersVO sanctionOrder = (SanctionOrdersVO)modifySanctionDetail.get(0);
			sanctionOrder.setSanctionOrderDate(sanctionform.getSanctionOrderDate());
			request.setAttribute("modifySanctionDetail",modifySanctionDetail);
			List sanctionNumber = sanctionDao.getSanctionNumber(sanctionform.getPiaCode());		
			request.setAttribute("sanctionNumber",sanctionNumber);
			List piaDetail = sanctionDao.getPiaDetail();
			request.setAttribute("piaDetail",piaDetail);
			fw = mapping.findForward("modifySanctionOrder");
		}
	}else{
		flag1 = true;
	}	
	
	if(flag1){			
		
		SanctionOrdersVO sanctionvo = new SanctionOrdersVO();
		BeanUtils.copyProperties(sanctionvo, sanctionform);
		sanctionvo.setModifyBy(userId);
		sanctionvo.setModifyOn(modify_on);
		sanctionvo.setIpAddress(request.getRemoteAddr());
		
		if(modifiedDocSanctionFileName != null){
			sanctionvo.setSanctionFileName(modifiedDocSanctionFileName);
			sanctionform.setSanctionFileName(modifiedDocSanctionFileName);
		}else{
			sanctionvo.setSanctionFileName(sanctionform.getSanctionFileName());
		}
		
		 flag = SanctionDaoImpl.update(sanctionvo);
		if (flag) {
			form.reset(mapping, request);
	    	request.setAttribute("notification", "Sanction Order Modification has been done successfully.");
			
		} else {
			log.error(checkFile + " for  ("+ sanctionform.getSanctionFile().getFileName()+ ") in upload documents not supported.");
			request.setAttribute("docSanctionFileError", checkFile);
			errorMsg = checkFile + " for Sanction  Document";
			log.error("Sanction Order Modification has been not done successfully.");
		}
		fw = mapping.findForward("modifySanctionOrder");
	}
} catch (Exception e) {
	log.error("RegistrationAction method = updatePartA()" + e);
} finally {
	List piaDetail = sanctionDao.getPiaDetail();
	request.setAttribute("piaDetail",piaDetail);
	List sanctionNumber = sanctionDao.getSanctionNumber(sanctionform.getPiaCode());		
	request.setAttribute("sanctionNumber",sanctionNumber);
	return(fw);
}

}
	
	
	public ActionForward showSanctionReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ActionForward fw = null;
		List DetailList = null;
		String code = request.getParameter("encd");
		System.out.println(code+ "In Action");
		SanctionOrdersForm sanctionform = (SanctionOrdersForm)form;
		SanctionOrdersDaoImpl sanctionDao = new SanctionOrdersDaoImpl();
		try {
			/** Check if page is being loaded for first time then show data accordingly **/
			if(code!=null && !code.equals("")){
			DetailList = sanctionDao.getSanctionOrderDetails(code);
					if(code.length()==1){
						request.setAttribute("stateList", DetailList);
					}
					else if(code.length()==2){
						request.setAttribute("districtList", DetailList);
					}
					else if(code.length()==4){
						request.setAttribute("approvedPIAList", DetailList);
					}
			}
			else
			{
				System.out.println("error in action");
			}
				
			fw = mapping.findForward("showSanctionOrderReportNew");
		} catch (Exception e) {
			log.error("Exception in  inside show SanctionUploadReport. " );
			e.printStackTrace();
		}
		return (fw);
	}
	
	

}
