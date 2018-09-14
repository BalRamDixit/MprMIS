package com.infotech.skills.registration.pia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;
import com.infotech.skills.master.MasterDao;
import com.infotech.skills.master.MasterDaoImpl;
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

/**
 * 
 * @author NIC
 *
 */

public class RegistrationAction extends DispatchAction {

	ActionMessages message = new ActionMessages();
	RegistrationBusiness business = null;
	MasterDao masterDao = null;
	//This path is only for LIVE LINUX SERVER
	// for local please change it
	//String filePath = Constants.LOCAL_SERVER_PATH;
	String filePath = Constants.LIVE_SERVER_PATH;
	

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward registration(ActionMapping mapping, ActionForm form,		
			HttpServletRequest request, HttpServletResponse response)		
	throws Exception {
		
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		request.setAttribute("piaCategory",business.getCategory());
		request.setAttribute("piaActivity",business.getActivity());
		request.setAttribute("stateList",masterDao.getStateList());
		return mapping.findForward(Constants.SHOW_PIA_REGISTER);
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
	public ActionForward checkPiaDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("used to check the pia pan and tan validate");
		RegistrationForm registrationForm = (RegistrationForm) form;
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();		
		try {
			PrintWriter out = response.getWriter();
			PiaDetailVO piaDetailVO = new PiaDetailVO();
			piaDetailVO.setPanNo(request.getParameter("PAN"));
			piaDetailVO.setTanNo(request.getParameter("TAN"));		
			boolean flag = business.validatePanTan(piaDetailVO);
			if(flag == true) {
				out.write("true");
			}else{
				out.write("false");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return mapping.findForward(null);
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
	@SuppressWarnings("finally")
	public ActionForward savePartA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		RegistrationForm registrationForm = (RegistrationForm) form;
		registrationForm.setCaptchaResponse("");
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		
		log.info("RegistrationAction: save A part...");
		
		FileOutputStream outputStreamPan,
						 outputStreamTan,
						 outputStreamReg,
						 outputStreamEnrol,
						 outputStreamOfficePhoto;	
		String checkFile = null;
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5=false;
		String errorMsg = null;
		try {
			FormFile formRegisterFile = (FormFile) registrationForm.getRegFile();
			FormFile formPanFile = (FormFile) registrationForm.getPanFile();
			FormFile formTanFile = (FormFile) registrationForm.getTanFile();
			FormFile formEnrolFile = (FormFile) registrationForm.getEnrolmentFile();
			FormFile formOfficePhotoFile = (FormFile) registrationForm.getOfficePhotoFile();
			
			//Get file name of uploaded file.
			String panFileName = formPanFile.getFileName();
			String tanFileName = formTanFile.getFileName();
			String enrolmentFileName = formEnrolFile.getFileName();
			String registerFileName = formRegisterFile.getFileName();
			String officePhotoFileName = formOfficePhotoFile.getFileName();
			
			byte[] agendafileData = formPanFile.getFileData();
			byte[] agendafileDataTan = formTanFile.getFileData();
			byte[] agendafileDataEnrol = formEnrolFile.getFileData();
			byte[] agendafileDataRegister = formRegisterFile.getFileData();
			byte[] agendafileDataOfficePhoto = formOfficePhotoFile.getFileData();
			
			String filePanPath,
				   fileTanPath,
				   fileRegPath,
				   fileEnrolPath,
				   fileOfficePhotoPath;
			
			filePanPath = fileRegPath = fileTanPath = fileOfficePhotoPath = fileEnrolPath = filePath;
			
			log.info("PIA "+registrationForm.getPiaName()+" upload file path for");
			log.info("Registration File: "+ fileRegPath);
			log.info("PAN File: " + filePanPath);
			log.info("TAN File: " + fileTanPath);
			log.info("Enrol File: " + fileEnrolPath);
			
			//create the upload folder if not exists
			File folder = new File(filePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			int piaCodeSequence = business.getSequenceCodeForFileUpload();
			int dotPosPan = panFileName.lastIndexOf(".");
			int dotPosTan = tanFileName.lastIndexOf(".");
			
			int dotPosReg = registerFileName.lastIndexOf(".");
			int dotPosofficePhoto = officePhotoFileName.lastIndexOf(".");
			
			String extensionPan = panFileName.substring(dotPosPan);
			String extensionTan = tanFileName.substring(dotPosTan);
			
			String extensionReg = registerFileName.substring(dotPosReg);
			String extensionOfficePhoto = officePhotoFileName.substring(dotPosofficePhoto);
			
			String modifiedPanFileName =  piaCodeSequence+ "pan"+extensionPan;
			String modifiedTanFileName = piaCodeSequence +"tan"+extensionTan;
			
			String modifiedRegisterFileName = piaCodeSequence +"register"+extensionReg;
			String modifiedOfficePhotoFileName = piaCodeSequence +"OfficePhoto"+extensionOfficePhoto;
			
			String agendapathPan = filePanPath+ System.getProperty("file.separator")+modifiedPanFileName;
			String agendapathTan = fileTanPath+ System.getProperty("file.separator")+modifiedTanFileName;
			
			String agendapathReg = fileRegPath+ System.getProperty("file.separator")+modifiedRegisterFileName;
			String agendapathOfficePhoto = fileOfficePhotoPath+ System.getProperty("file.separator")+modifiedOfficePhotoFileName;
			
			
			// code by roop
			String agendapathEnrol="";
			String modifiedEnrolFileName="";
			if(!enrolmentFileName.equalsIgnoreCase(""))
			{
				int dotPosEnrol = enrolmentFileName.lastIndexOf(".");
				String extensionEnrol = enrolmentFileName.substring(dotPosEnrol);
				 modifiedEnrolFileName = piaCodeSequence +"enrol"+extensionEnrol;
				 agendapathEnrol = fileEnrolPath+ System.getProperty("file.separator")+modifiedEnrolFileName;
				
			}
			
			checkFile = business.checkFileType(formOfficePhotoFile);
			if(registrationForm.getOfficePhotoFile().getFileName().length() > 0 && checkFile.equals("true")){
				outputStreamOfficePhoto = new FileOutputStream(new File(agendapathOfficePhoto));
				try{
					outputStreamOfficePhoto.write(agendafileDataOfficePhoto);
					flag1 = true;
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					outputStreamOfficePhoto.close();
				}	
			}else{
				log.error(checkFile+" for Office Photo ("+registrationForm.getOfficePhotoFile().getFileName()+") in savePartA");
				request.setAttribute("officePhotoError", checkFile);
				errorMsg = checkFile+" for Office Photo";
				flag1 = false;
			}
			
			if (registrationForm.getRegFile().getFileName().length() > 0){
				checkFile = business.checkFileType(formRegisterFile);
				if(checkFile.equals("true")){
					outputStreamReg = new FileOutputStream(new File(agendapathReg));
					try{
						outputStreamReg.write(agendafileDataRegister);
						flag2 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamReg.close();
					}
				}else{
					log.error(checkFile+" for Registration File ("+registrationForm.getRegFile().getFileName()+") in savePartA");
					request.setAttribute("regError", checkFile);
					errorMsg = checkFile+"for Registration File";
					flag2 = false;
				}
			}
				
			
			if (registrationForm.getPanFile().getFileName().length() > 0){
				checkFile = business.checkFileType(formPanFile);
				if(checkFile.equals("true")){
					outputStreamPan = new FileOutputStream(new File(agendapathPan));
					try{
						outputStreamPan.write(agendafileData);
						flag3 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamPan.close();
					}
				}else{
					log.error(checkFile+" for Pan Card ("+registrationForm.getPanFile().getFileName()+") in savePartA");
					request.setAttribute("panError", checkFile);
					errorMsg = checkFile+"for Pan Card";
					flag3 = false;
				}
			}
				
			
			
			if (registrationForm.getTanFile().getFileName().length() > 0){
				checkFile = business.checkFileType(formTanFile);
				if(checkFile.equals("true")){
					outputStreamTan = new FileOutputStream(new File(agendapathTan));
					try{
						outputStreamTan.write(agendafileDataTan);
						flag4 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamTan.close();
					}
				}else{	
					log.error(checkFile+" for Tan Card ("+registrationForm.getTanFile().getFileName()+") in savePartA");
					request.setAttribute("tanError", checkFile);
					errorMsg = checkFile+"for Tan Card";
					flag4 = false;
				}
			}
				
			
			if (registrationForm.getEnrolmentFile().getFileName().length() > 0){
				checkFile = business.checkFileType(formEnrolFile);
				if(checkFile.equals("true")){
					outputStreamEnrol = new FileOutputStream(new File(agendapathEnrol));
					try{
						outputStreamEnrol.write(agendafileDataEnrol);
						flag5 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamEnrol.close();
					}
				}else{	
					log.error(checkFile+" for Enrol no. ("+registrationForm.getEnrolmentFile().getFileName()+") in savePartA");
					request.setAttribute("enError", checkFile);
					errorMsg = checkFile+"for Enrol no.";
					flag5 = false;
				}
			}
			
			if(flag5==false)
			{
				if(flag1 && flag2 && flag3 && flag4  ){	
					// BY laxman
					registrationForm.setPanFileName(modifiedPanFileName);
					registrationForm.setRegFileName(modifiedRegisterFileName);
					registrationForm.setTanFileName(modifiedTanFileName);
					registrationForm.setEnrolmentFileName(modifiedEnrolFileName);
					registrationForm.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					// laxman end
					registrationForm.setPiaCode(Integer.toString(piaCodeSequence));
					List<PiaActivityMappingVO> piaActivityMappingVOList = new ArrayList<PiaActivityMappingVO>();
					BeanUtils.copyProperties(piaDetailVO, registrationForm);
					piaDetailVO.setPanFileName(modifiedPanFileName);
					piaDetailVO.setTanFileName(modifiedTanFileName);
					piaDetailVO.setRegFileName(modifiedRegisterFileName);
					piaDetailVO.setEnrolmentFileName(modifiedEnrolFileName);
					piaDetailVO.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					for(String activityId : registrationForm.getPiaActivity()){
						PiaActivityMappingVO piaActivityMappingVO = new PiaActivityMappingVO();
						piaActivityMappingVO.setActivityId(activityId);
						piaActivityMappingVOList.add(piaActivityMappingVO);
					}
					
					boolean flag = business.savePartA(piaDetailVO, piaActivityMappingVOList);
					if (flag) {
						log.info("PIA Registration done successfully.");
					} else {
						log.error("PIA Registration not done successfully.");
					}
				}
			}else{
				
				if(flag1 && flag2 && flag3 && flag4 && flag5 ){	
					// BY laxman
					registrationForm.setPanFileName(modifiedPanFileName);
					registrationForm.setRegFileName(modifiedRegisterFileName);
					registrationForm.setTanFileName(modifiedTanFileName);
					registrationForm.setEnrolmentFileName(modifiedEnrolFileName);
					registrationForm.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					// laxman end
					registrationForm.setPiaCode(Integer.toString(piaCodeSequence));
					List<PiaActivityMappingVO> piaActivityMappingVOList = new ArrayList<PiaActivityMappingVO>();
					BeanUtils.copyProperties(piaDetailVO, registrationForm);
					piaDetailVO.setPanFileName(modifiedPanFileName);
					piaDetailVO.setTanFileName(modifiedTanFileName);
					piaDetailVO.setRegFileName(modifiedRegisterFileName);
					piaDetailVO.setEnrolmentFileName(modifiedEnrolFileName);
					piaDetailVO.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					for(String activityId : registrationForm.getPiaActivity()){
						PiaActivityMappingVO piaActivityMappingVO = new PiaActivityMappingVO();
						piaActivityMappingVO.setActivityId(activityId);
						piaActivityMappingVOList.add(piaActivityMappingVO);
					}
					
					boolean flag = business.savePartA(piaDetailVO, piaActivityMappingVOList);
					if (flag) {
						log.info("PIA Registration done successfully.");
					} else {
						log.error("PIA Registration not done successfully.");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("RegistrationAction method = save()" + e);
		} finally {
			request.setAttribute("piaCategory", business.getCategory());
			request.setAttribute("piaActivity", business.getActivity());
			request.setAttribute("stateList", masterDao.getStateList());
			request.setAttribute("piaDetail", piaDetailVO);
			
			if (registrationForm.getDistrictCode() != null
					&& !registrationForm.getDistrictCode().equals("")){
				BlockMasterVO blockMasterVO = new BlockMasterVO(); 
				blockMasterVO.setDistrictCode(registrationForm.getDistrictCode());
				request.setAttribute("blockList",masterDao.getBlockList(blockMasterVO));
			}
			if (registrationForm.getStateCode() != null
					&& !registrationForm.getStateCode().equals("")){
				DistrictVO districtVO = new DistrictVO();
				districtVO.setStateCode(registrationForm.getStateCode());
				request.setAttribute("districtList",masterDao.getDistrictList(districtVO));
			}		
			registrationForm.setCaptchaResponse("");
			String forward = null;
			if(errorMsg != null){
				forward = Constants.SHOW_PIA_REGISTER;
			}else{
				forward = Constants.SHOW_PIA_REGISTER_PARTA;
			}
			return mapping.findForward(forward);
		}
		
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
	@SuppressWarnings("finally")
	public ActionForward updatePartA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		System.err.println("HELLO");
		RegistrationForm registrationForm = (RegistrationForm) form;
		registrationForm.setCaptchaResponse("");
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		
		log.info("RegistrationAction: updatePartA part...");
		
		FileOutputStream outputStreamPan,outputStreamTan,outputStreamReg,outputStreamOfficePhoto,outputStreamEnrol;
		outputStreamPan=outputStreamTan=outputStreamReg=outputStreamOfficePhoto=outputStreamEnrol= null;		
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 =false, flag5= false;
		FormFile formPanFile = null,formTanFile = null,formRegisterFile = null,formOfficePhotoFile = null, formEnrolFile=null;;
		String panFileName = null,registerFileName = null,officePhotoFileName = null,tanFileName = null, enrolFileName=null;
		byte[] agendafileData = null,agendafileDataTan = null,agendafileDataRegister = null,agendafileDataOfficePhoto = null,agendafileDataEnrol=null;
		String filePanPath = null,fileTanPath = null,fileRegPath = null,fileOfficePhotoPath = null,fileEnrolPath=null;
		String modifiedPanFileName = null,modifiedTanFileName = null,modifiedRegisterFileName = null,modifiedOfficePhotoFileName = null,modifiedEnrolFileName=null;
		File file = null;
		String checkFile = null;
		String errorMsg = null;
		try {
				formPanFile = (FormFile) registrationForm.getPanFile();
				panFileName = formPanFile.getFileName();
				agendafileData = formPanFile.getFileData();
				
				/*for Enrolment file entry start*/
				formEnrolFile= (FormFile) registrationForm.getEnrolmentFile();
				enrolFileName =formEnrolFile.getFileName();
				agendafileDataEnrol =formEnrolFile.getFileData();
				/*for Enrolment file entry end*/
				
				formTanFile = (FormFile) registrationForm.getTanFile();
				tanFileName = formTanFile.getFileName();
				agendafileDataTan = formTanFile.getFileData();
				
				formRegisterFile = (FormFile) registrationForm.getRegFile();
				registerFileName = formRegisterFile.getFileName();
				agendafileDataRegister = formRegisterFile.getFileData();
			
				formOfficePhotoFile = (FormFile) registrationForm.getOfficePhotoFile();
				officePhotoFileName = formOfficePhotoFile.getFileName();
				agendafileDataOfficePhoto = formOfficePhotoFile.getFileData();
			
			if(!panFileName.equals("") || !tanFileName.equals("") || !registerFileName.equals("") || !officePhotoFileName.equals("") || !enrolFileName.equals("")){
				/*path = getServlet().getServletContext().getRealPath("/uploads/piaprofile/");*/
				filePanPath=fileRegPath=fileTanPath = fileOfficePhotoPath =fileEnrolPath= filePath;
				log.info("Path to save PIA files="+filePath);
				if (new File(filePath).isDirectory() == false) {
					new File(filePath).mkdirs();
				}
			}
			String piaCode = registrationForm.getPiaCode();
			if(!panFileName.equals("")){
				int dotPosPan = panFileName.lastIndexOf(".");
				String extensionPan = panFileName.substring(dotPosPan);
				modifiedPanFileName =  piaCode+ "pan"+extensionPan;
				String agendapathPan = filePanPath+ System.getProperty("file.separator")+modifiedPanFileName;
				checkFile = business.checkFileType(formPanFile);
				if (registrationForm.getPanFile().getFileName().length() > 0 && checkFile.equals("true")){
					try{
						outputStreamPan = new FileOutputStream(new File(agendapathPan));
						outputStreamPan.write(agendafileData);
						file= new File(filePath+System.getProperty("file.separator")+registrationForm.getPanFileName());
						file.delete();
						flag1 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamPan.close();
					}
					
				}else{
					log.error(checkFile+" for Pan Card ("+registrationForm.getPanFile().getFileName()+") in updatePartA");
					request.setAttribute("panError", checkFile);
					errorMsg = checkFile+"for Registration File";
				}
			}else{
				flag1 = true;
			}
			
			if(!tanFileName.equals("")){
				int dotPosTan = tanFileName.lastIndexOf(".");
				String extensionTan = tanFileName.substring(dotPosTan);
				modifiedTanFileName = piaCode +"tan"+extensionTan;
				String agendapathTan = fileTanPath+ System.getProperty("file.separator")+modifiedTanFileName;
				checkFile = business.checkFileType(formTanFile);
				if (registrationForm.getTanFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getTanFileName());
							file.delete();
							outputStreamTan = new FileOutputStream(new File(agendapathTan));
							outputStreamTan.write(agendafileDataTan);
							flag2=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamTan.close();
						}
					}
					else{
						log.error(checkFile+" for Tan Card ("+registrationForm.getTanFile().getFileName()+") in updatePartA");
						request.setAttribute("tanError", checkFile);
						errorMsg = checkFile+" for Tan Card";
					}
				}
			}else{
				flag2 = true;
			}
			if(!registerFileName.equals("")){
				int dotPosReg = registerFileName.lastIndexOf(".");
				String extensionReg = registerFileName.substring(dotPosReg);
				modifiedRegisterFileName = piaCode +"register"+extensionReg;
				String agendapathReg = fileRegPath+ System.getProperty("file.separator")+modifiedRegisterFileName;
				checkFile = business.checkFileType(formRegisterFile);
				if (registrationForm.getRegFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getRegFileName());
							file.delete();
							outputStreamReg = new FileOutputStream(new File(agendapathReg));
							outputStreamReg.write(agendafileDataRegister);
							flag3=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamReg.close();
						}
					}
					else{
						log.error(checkFile+" for Registration File: ("+registrationForm.getRegFile().getFileName()+") in updatePartA");
						request.setAttribute("regError", checkFile);
						errorMsg = checkFile+" for Registration Doc";
					}
				}
			}else{
				flag3 = true;
			}
			if(!officePhotoFileName.equals("")){
				int dotPosofficePhoto = officePhotoFileName.lastIndexOf(".");
				String extensionOfficePhoto = officePhotoFileName.substring(dotPosofficePhoto);
				modifiedOfficePhotoFileName = piaCode +"OfficePhoto"+extensionOfficePhoto;
				String agendapathOfficePhoto = fileOfficePhotoPath+ System.getProperty("file.separator")+modifiedOfficePhotoFileName;
				
				checkFile = business.checkFileType(formOfficePhotoFile);
				if (registrationForm.getOfficePhotoFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getOfficePhotoFileName());
							file.delete();
							outputStreamOfficePhoto = new FileOutputStream(new File(agendapathOfficePhoto));
							outputStreamOfficePhoto.write(agendafileDataOfficePhoto);
							flag4=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamOfficePhoto.close();
						}
					}
					else{
						log.error(checkFile+" for Office Photo ("+registrationForm.getOfficePhotoFile().getFileName()+") in updatePartA");
						request.setAttribute("officePhotoError", checkFile);
						errorMsg = checkFile+" for officePhoto Document";
					}
					
				}
			}else{
				flag4 = true;
			}
			
			if(!enrolFileName.equals("")){
				int dotPosEnrol = enrolFileName.lastIndexOf(".");
				String extensionEnrol = enrolFileName.substring(dotPosEnrol);
				modifiedEnrolFileName = piaCode +"enrol"+extensionEnrol;
				String agendapathEnrol = fileEnrolPath+ System.getProperty("file.separator")+modifiedEnrolFileName;
				checkFile = business.checkFileType(formEnrolFile);
				if (registrationForm.getTanFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getEnrolmentFileName());
							file.delete();
							outputStreamEnrol = new FileOutputStream(new File(agendapathEnrol));
							outputStreamEnrol.write(agendafileDataEnrol);
							flag5=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamEnrol.close();
						}
					}
					else{
						log.error(checkFile+" for Enrol No ("+registrationForm.getEnrolmentFile().getFileName()+") in updatePartA");
						request.setAttribute("enrolError", checkFile);
						errorMsg = checkFile+" for Enrol No";
					}
				}
			}else{
				flag5 = true;
			}
			
			if(flag1 && flag2 && flag3 && flag4 && flag5){			
				List<PiaActivityMappingVO> piaActivityMappingVOList = new ArrayList<PiaActivityMappingVO>();
				BeanUtils.copyProperties(piaDetailVO, registrationForm);
				
				if(modifiedPanFileName != null){
					piaDetailVO.setPanFileName(modifiedPanFileName);
					registrationForm.setPanFileName(modifiedPanFileName);
				}else{
					piaDetailVO.setPanFileName(registrationForm.getPanFileName());
				}
				if(modifiedTanFileName != null){
					piaDetailVO.setTanFileName(modifiedTanFileName);
					registrationForm.setTanFileName(modifiedTanFileName);
				}else{
					piaDetailVO.setTanFileName(registrationForm.getTanFileName());
				}
				if(modifiedEnrolFileName != null){
					piaDetailVO.setEnrolmentFileName(modifiedEnrolFileName);
					registrationForm.setEnrolmentFileName(modifiedEnrolFileName);
				}else{
					piaDetailVO.setEnrolmentFileName(registrationForm.getEnrolmentFileName());
				}
				if(modifiedRegisterFileName != null){
					piaDetailVO.setRegFileName(modifiedRegisterFileName);
					registrationForm.setRegFileName(modifiedRegisterFileName);
				}else{
					piaDetailVO.setOfficePhotoFileName(registrationForm.getRegFileName());
				}
				if(modifiedOfficePhotoFileName != null){
					piaDetailVO.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					registrationForm.setOfficePhotoFileName(modifiedOfficePhotoFileName);
				}else{
					piaDetailVO.setOfficePhotoFileName(registrationForm.getOfficePhotoFileName());
				}
				for(String activityId : registrationForm.getPiaActivity()){
					PiaActivityMappingVO piaActivityMappingVO = new PiaActivityMappingVO();
					piaActivityMappingVO.setActivityId(activityId);
					piaActivityMappingVOList.add(piaActivityMappingVO);
				}
				
				boolean flag = business.updatePartA(piaDetailVO, piaActivityMappingVOList);
				if (flag) {
					log.info("PIA Registration(updatePartA) done successfully.");
				} else {
					log.error("PIA Registration(updatePartA) not done successfully.");
				}
			}
		} catch (Exception e) {
			log.error("RegistrationAction method = updatePartA()" + e);
		} finally {
			request.setAttribute("piaCategory", business.getCategory());
			request.setAttribute("piaActivity", business.getActivity());
			request.setAttribute("stateList", masterDao.getStateList());
			request.setAttribute("piaDetail", piaDetailVO);
			
			if (registrationForm.getDistrictCode() != null
					&& !registrationForm.getDistrictCode().equals("")) {
				BlockMasterVO blockMasterVO = new BlockMasterVO(); 
				blockMasterVO.setDistrictCode(registrationForm.getDistrictCode());
				request.setAttribute("blockList",masterDao.getBlockList(blockMasterVO));
			}
			if (registrationForm.getStateCode() != null
					&& !registrationForm.getStateCode().equals("")) {
				DistrictVO districtVO = new DistrictVO();
				districtVO.setStateCode(registrationForm.getStateCode());
				request.setAttribute("districtList",masterDao.getDistrictList(districtVO));
			}
			String forward = null;
			if(errorMsg != null){
				forward = Constants.SHOW_PIA_REGISTER_PARTA;
			}else{
				forward = Constants.SHOW_PIA_REGISTER_PARTB;
			}
			return mapping.findForward(forward);
		}
		
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
	public ActionForward savePartB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)			
	throws Exception {
		log.info("RegistrationAction: Action save menthod...");
		RegistrationForm registrationForm = (RegistrationForm) form;
		PiaMemberDetailVO piaMemberDetailVO = new PiaMemberDetailVO();
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		List<PiaMemberDetailVO> piaMemberList = new ArrayList<PiaMemberDetailVO>();
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		
		
		FileOutputStream outputStreamCV,outputStreamPhoto;
		outputStreamCV=outputStreamPhoto= null;	
		boolean flag1= false, flag2 = false;
		String modifiedCVFileName = null, modifiedPhotoFileName = null;
		String checkFile = null;
		String errorMsg = null;
		try {
			FormFile cvFile = (FormFile) registrationForm.getCvFile();
			FormFile photoFile = (FormFile) registrationForm.getPhotoFile();
	
			String cvFileName = cvFile.getFileName();
			String photoFileName = photoFile.getFileName();
			
			byte[] agendafileDataCV = cvFile.getFileData();
			byte[] agendafileDataPhoto = photoFile.getFileData();
			
			String cvFilePath,photoFilePath;
			cvFilePath=photoFilePath= filePath;
			

			log.info("Path to save PIA files="+ cvFilePath+"\t"+photoFilePath);
			
			if (new File(filePath).isDirectory() == false) {
				new File(filePath).mkdirs();
			}
			
			String piaCode = registrationForm.getPiaCode();
			int dotPosCV = cvFileName.lastIndexOf(".");
			int dotPosPhoto = photoFileName.lastIndexOf(".");
			
			String extensionCV = cvFileName.substring(dotPosCV);
			String extensionPhoto = photoFileName.substring(dotPosPhoto);
			
			modifiedCVFileName =  piaCode+ "cv"+extensionCV;
			modifiedPhotoFileName = piaCode +"photo"+extensionPhoto;
		
			String agendapathCV = cvFilePath+ System.getProperty("file.separator")+modifiedCVFileName;
			String agendapathPhoto = photoFilePath+ System.getProperty("file.separator")+modifiedPhotoFileName;
			
			checkFile =  business.checkFileType(cvFile);
			if (registrationForm.getCvFile().getFileName().length() > 0){
				if(checkFile.equals("true")){
					try{
						outputStreamCV = new FileOutputStream(new File(agendapathCV));
						outputStreamCV.write(agendafileDataCV);
						flag1 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamCV.close();
					}
				}
				else{
					log.error(checkFile+" for authorized person CV File ("+registrationForm.getCvFile().getFileName()+") in savePartB");
					request.setAttribute("cvError", checkFile);
					errorMsg = checkFile+" for CV";
				}
			}
			
			checkFile = business.checkFileType(photoFile);
			if (registrationForm.getPhotoFile().getFileName().length() > 0){
				if(checkFile.equals("true")){
					try{
						outputStreamPhoto = new FileOutputStream(new File(agendapathPhoto));
						outputStreamPhoto.write(agendafileDataPhoto);
						flag2=true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamPhoto.close();
					}
				}
				else{
					log.error(checkFile+" for authorized person photo file ("+registrationForm.getPhotoFile().getFileName()+") in savePartB");
					request.setAttribute("photoError", checkFile);
					errorMsg = checkFile+" for  Photo";
				}
					
			}
			if(flag1 && flag2){
				registrationForm.setAuthCVFileName(modifiedCVFileName);
				registrationForm.setAuthPhotoFileName(modifiedPhotoFileName);
				//used to get the piaDetailVO
				BeanUtils.copyProperties(piaDetailVO, registrationForm);	
				// used to get the authorized person detail
				piaMemberDetailVO = new PiaMemberDetailVO();
				piaMemberDetailVO.setAuthorizedPerson("yes");
				piaMemberDetailVO.setMemberName(registrationForm.getAuthPersonName());
				piaMemberDetailVO.setContact(registrationForm.getAuthPersonContact());
				piaMemberDetailVO.setAddress(registrationForm.getAuthAddress());
				piaMemberDetailVO.setEmail(registrationForm.getAuthPersonEmail());
				piaMemberDetailVO.setPan(registrationForm.getAuthPersonPan());
				piaMemberDetailVO.setAadharVoterNo(registrationForm.getAuthPersonAdhaar());
				piaMemberDetailVO.setPassportDrivingNo(registrationForm.getAuthPersonPassport());
				piaMemberDetailVO.setAge(registrationForm.getAuthAge());
				piaMemberDetailVO.setRelativeName(registrationForm.getAuthRelativeName());
				piaMemberDetailVO.setAge(registrationForm.getAuthAge());
				piaMemberDetailVO.setDesignation(registrationForm.getAuthDesignation());
				piaMemberDetailVO.setOccupation(registrationForm.getAuthOccupation());
				piaMemberDetailVO.setPostOffice(registrationForm.getAuthPostOffice());
				piaMemberDetailVO.setPoliceStation(registrationForm.getAuthPoliceStation());
				piaMemberDetailVO.setCvFileName(modifiedCVFileName);
				piaMemberDetailVO.setPhotoFileName(modifiedPhotoFileName);
				piaMemberDetailVO.setStateCode(registrationForm.getAuthStateCode());
				piaMemberDetailVO.setPlace(registrationForm.getAuthPlace());
				piaMemberDetailVO.setIsNonProfitOrg(registrationForm.getIsNonProfitOrg());
				
				piaMemberList.add(piaMemberDetailVO);
				// end authorized person
		
				// used to get member detail
				for(int member = 0; member < registrationForm.getMemberName().length; member++){
					piaMemberDetailVO = new PiaMemberDetailVO();
					if(piaDetailVO.getCategoryCode().equals("3") || piaDetailVO.getCategoryCode().equals("4")){
						piaMemberDetailVO.setLiability(registrationForm.getMemberLiability()[member]);
					}else{
						piaMemberDetailVO.setDesignation(registrationForm.getMemberDesignation()[member]);	
					}					
					piaMemberDetailVO.setMemberName(registrationForm.getMemberName()[member]);
					piaMemberDetailVO.setContact(registrationForm.getMemberContact()[member]);
					piaMemberDetailVO.setEmail(registrationForm.getMemberEmail()[member]);
					piaMemberDetailVO.setPan(registrationForm.getMemberPan()[member]);
					piaMemberDetailVO.setAadharVoterNo(registrationForm.getMemberAadharVoterNo()[member]);
					piaMemberDetailVO.setPassportDrivingNo(registrationForm.getMemberPassportDrivingNo()[member]);
					piaMemberList.add(piaMemberDetailVO);
				}
				// end to get member detail
				
				// used to get nri member detail
				if(registrationForm.getCategoryCode().equals("4") || registrationForm.getCategoryCode().equals("3")){
					if( registrationForm.getNriName() != null ){
					for(int member = 0; member < registrationForm.getNriName().length; member++){
						piaMemberDetailVO = new PiaMemberDetailVO();	
						piaMemberDetailVO.setMemberName(registrationForm.getNriName()[member]);
						piaMemberDetailVO.setCountry(registrationForm.getNriCountry()[member]);
						piaMemberDetailVO.setPassportDrivingNo(registrationForm.getNriPassportNo()[member]);
						piaMemberDetailVO.setPassportValidDate(registrationForm.getNriPassportValidDate()[member]);
						piaMemberDetailVO.setVisaValidDate(registrationForm.getNriVisaValidDate()[member]);
						piaMemberDetailVO.setWorkPermit(registrationForm.getNriWorkPermit()[member]);
						piaMemberDetailVO.setWorkingPermitDate(registrationForm.getNriWorkingPermitDate()[member]);
						piaMemberDetailVO.setClearanceFRAMHA(registrationForm.getNriclearanceFRAMHA()[member]);
						piaMemberDetailVO.setNriStatus("yes");
						piaMemberList.add(piaMemberDetailVO);
					}
					}
				}
				piaDetailVO.setPanFileName(registrationForm.getPanFileName());
				piaDetailVO.setTanFileName(registrationForm.getTanFileName());
				piaDetailVO.setRegFileName(registrationForm.getRegFileName());
				piaDetailVO.setEnrolmentFileName(registrationForm.getEnrolmentFileName());
				piaDetailVO.setOfficePhotoFileName(registrationForm.getOfficePhotoFileName());
			boolean flag = business.savePartB(piaDetailVO, piaMemberList);	
			if (flag) {	
				log.info("PIA Registration partB done successfully.");
				request.setAttribute("piaMemberList", business.getPiaMemberDetail(piaDetailVO));	
				System.err.println("===="+business.getPiaMemberDetail(piaDetailVO).size());
				
			} else {	
				log.error("RegistrationAction in savePartB method");	
			}
		}
		}
		 catch (Exception e) {
			log.error( "RegistrationAction method = savePartB"+e.getMessage());
		}finally{
			request.setAttribute("stateList", masterDao.getStateList());
			request.setAttribute("cv", modifiedCVFileName);
			request.setAttribute("photo", modifiedPhotoFileName);
			registrationForm.setCaptchaResponse("");
		}
		this.checkAuthenticatePIA(mapping, registrationForm, request, response);
		//return mapping.findForward(Constants.SHOW_PIA_COMPLETE_REGISTRATION);
		/*return mapping.findForward(Constants.SHOW_PIA_REGISTER_PARTB_MODIFY);
		 * this.checkAuthenticatePIA(mapping, registrationForm, request, response);*/
		/*String forward = null;
		
		if(errorMsg != null){
			forward = Constants.SHOW_PIA_COMPLETE_REGISTRATION;
		}else{
			forward = Constants.SHOW_PIA_COMPLETE_REGISTRATION;
		}*/
		return mapping.findForward(Constants.SHOW_PIA_COMPLETE_REGISTRATION);
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
	public ActionForward updatePartB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)			
	throws Exception {
		log.info("RegistrationAction: Action updatePartB method...");
		RegistrationForm registrationForm = (RegistrationForm) form;
		registrationForm.setCaptchaResponse("");
		PiaMemberDetailVO piaMemberDetailVO = new PiaMemberDetailVO();
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		List<PiaMemberDetailVO> piaMemberList = new ArrayList<PiaMemberDetailVO>();
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		
		FileOutputStream outputStreamCV,outputStreamPhoto, outputStreamPan,outputStreamTan,outputStreamReg,outputStreamOfficePhoto,outputStreamEnrol;
		outputStreamCV=outputStreamPhoto=outputStreamPan=outputStreamTan=outputStreamReg=outputStreamOfficePhoto=outputStreamEnrol= null;				
		boolean flag1= false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false,flag7 = false;
		String cvFileName = null, photoFileName = null, panFileName = null,registerFileName = null,officePhotoFileName = null,tanFileName = null,enrolFileName = null;
		byte[] agendafileDataCV = null,agendafileDataPhoto = null, agendafileData = null,agendafileDataTan = null,agendafileDataRegister = null,agendafileDataOfficePhoto = null,agendafileDataEnrol = null;
		String cvFilePath = null,photoFilePath = null, filePanPath = null,fileTanPath = null,fileRegPath = null,fileOfficePhotoPath = null,fileEnrolPath = null;
		FormFile cvFile = null, photoFile = null, formPanFile = null,formTanFile = null,formRegisterFile = null,formOfficePhotoFile = null, formEnrolFile = null;
		String modifiedCVFileName = null,modifiedPhotoFileName = null, modifiedPanFileName = null,modifiedTanFileName = null,modifiedRegisterFileName = null,modifiedOfficePhotoFileName = null,modifiedEnrolFileName = null;
		File file = null;
		String checkFile = null;
		String errorMsg = null;
		try {
			
			
			
				formPanFile = (FormFile) registrationForm.getPanFile();
				panFileName = formPanFile.getFileName();
				agendafileData = formPanFile.getFileData();
			
				formTanFile = (FormFile) registrationForm.getTanFile();
				tanFileName = formTanFile.getFileName();
				agendafileDataTan = formTanFile.getFileData();			
				
				formEnrolFile= (FormFile) registrationForm.getEnrolmentFile();
				enrolFileName =formEnrolFile.getFileName();
				agendafileDataEnrol =formEnrolFile.getFileData();
				
				formRegisterFile = (FormFile) registrationForm.getRegFile();
				registerFileName = formRegisterFile.getFileName();
				agendafileDataRegister = formRegisterFile.getFileData();
		
				formOfficePhotoFile = (FormFile) registrationForm.getOfficePhotoFile();
				officePhotoFileName = formOfficePhotoFile.getFileName();
				agendafileDataOfficePhoto = formOfficePhotoFile.getFileData();
				
				cvFile = (FormFile) registrationForm.getCvFile();
				cvFileName = cvFile.getFileName();
				agendafileDataCV = cvFile.getFileData();
	
				photoFile = (FormFile) registrationForm.getPhotoFile();
				photoFileName = photoFile.getFileName();
				agendafileDataPhoto = photoFile.getFileData();
			
			if(!cvFileName.equals("") || !photoFileName.equals("")  || !panFileName.equals("") || !tanFileName.equals("") || !registerFileName.equals("") || !officePhotoFileName.equals("") || !enrolFileName.equals("")){
			 
			cvFilePath=photoFilePath=filePanPath=fileRegPath=fileTanPath = fileOfficePhotoPath= fileEnrolPath=filePath;
			log.info("Path to save PIA files="+ filePath);
			
			if (new File(filePath).isDirectory() == false) {
				new File(filePath).mkdirs();
			}
			}
			String piaCode = registrationForm.getPiaCode();
			if(!panFileName.equals("")){
				int dotPosPan = panFileName.lastIndexOf(".");
				String extensionPan = panFileName.substring(dotPosPan);
				modifiedPanFileName =  piaCode+ "pan"+extensionPan;
				String agendapathPan = filePanPath+ System.getProperty("file.separator")+modifiedPanFileName;
				checkFile = business.checkFileType(formPanFile);
				if (registrationForm.getPanFile().getFileName().length() > 0 && checkFile.equals("true")){
					try{
						file= new File(filePath+System.getProperty("file.separator")+registrationForm.getPanFileName());
						file.delete();
						outputStreamPan = new FileOutputStream(new File(agendapathPan));
						outputStreamPan.write(agendafileData);
						flag1 = true;
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						outputStreamPan.close();
					}
					
				}else{
					log.error(checkFile+" for Pan Card ("+registrationForm.getPanFile().getFileName()+") in updatePartB");
					request.setAttribute("panError", checkFile);
					errorMsg = checkFile+"for PAN Card";
				}
			}else{
				flag1 = true;
			}

			if(!tanFileName.equals("")){
				int dotPosTan = tanFileName.lastIndexOf(".");
				String extensionTan = tanFileName.substring(dotPosTan);
				modifiedTanFileName = piaCode +"tan"+extensionTan;
				String agendapathTan = fileTanPath+ System.getProperty("file.separator")+modifiedTanFileName;
				checkFile = business.checkFileType(formTanFile);
				if (registrationForm.getTanFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getTanFileName());
							file.delete();
							outputStreamTan = new FileOutputStream(new File(agendapathTan));
							outputStreamTan.write(agendafileDataTan);
							flag2=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamTan.close();
						}
					}
					else{
						log.error(checkFile+" for Tan Card ("+registrationForm.getTanFile().getFileName()+") in updatePartB");
						request.setAttribute("tanError", checkFile);
						errorMsg = checkFile+" for Tan Card";
					}
				}
			}else{
				flag2 = true;
			}
			if(!registerFileName.equals("")){
				int dotPosReg = registerFileName.lastIndexOf(".");
				String extensionReg = registerFileName.substring(dotPosReg);
				modifiedRegisterFileName = piaCode +"register"+extensionReg;
				String agendapathReg = fileRegPath+ System.getProperty("file.separator")+modifiedRegisterFileName;
				checkFile = business.checkFileType(formRegisterFile);
				if (registrationForm.getRegFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getRegFileName());
							file.delete();
							outputStreamReg = new FileOutputStream(new File(agendapathReg));
							outputStreamReg.write(agendafileDataRegister);
							flag3=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamReg.close();
						}
					}
					else{
						log.error(checkFile+" for Registration File: ("+registrationForm.getRegFile().getFileName()+") in updatePartB");
						request.setAttribute("regError", checkFile);
						errorMsg = checkFile+" for Registration Doc";
					}
				}
			}else{
				flag3 = true;
			}
			if(!officePhotoFileName.equals("")){
				int dotPosofficePhoto = officePhotoFileName.lastIndexOf(".");
				String extensionOfficePhoto = officePhotoFileName.substring(dotPosofficePhoto);
				modifiedOfficePhotoFileName = piaCode +"OfficePhoto"+extensionOfficePhoto;
				String agendapathOfficePhoto = fileOfficePhotoPath+ System.getProperty("file.separator")+modifiedOfficePhotoFileName;
				
				checkFile = business.checkFileType(formOfficePhotoFile);
				if (registrationForm.getOfficePhotoFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getOfficePhotoFileName());
							file.delete();
							outputStreamOfficePhoto = new FileOutputStream(new File(agendapathOfficePhoto));
							outputStreamOfficePhoto.write(agendafileDataOfficePhoto);
							flag4=true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamOfficePhoto.close();
						}
					}
					else{
						log.error(checkFile+" for Office Photo ("+registrationForm.getOfficePhotoFile().getFileName()+") in updatePartB");
						request.setAttribute("officePhotoError", checkFile);
						errorMsg = checkFile+" for officePhoto Document";
					}
					
				}
			}else{
				flag4 = true;
			}
			if(!cvFileName.equals("")){
				int dotPosCV = cvFileName.lastIndexOf(".");
				String extensionCV = cvFileName.substring(dotPosCV);
				modifiedCVFileName =  piaCode+ "cv"+extensionCV;
				String agendapathCV = cvFilePath+ System.getProperty("file.separator")+modifiedCVFileName;
				
				checkFile = business.checkFileType(cvFile);
				if (registrationForm.getCvFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+registrationForm.getAuthCVFileName());
							file.delete();
							outputStreamCV = new FileOutputStream(new File(agendapathCV));
							outputStreamCV.write(agendafileDataCV);
							flag5 = true;
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							outputStreamCV.close();
						}
					}
					else{
						log.error(checkFile+" for authorized person CV file ("+registrationForm.getCvFile().getFileName()+") in updatePartB");
						request.setAttribute("cvError", checkFile);
						errorMsg = checkFile+" for CV";
					}
				}
				}else{
					flag5=true;
				}
				if(!photoFileName.equals("")){
					int dotPosPhoto = photoFileName.lastIndexOf(".");
					String extensionPhoto = photoFileName.substring(dotPosPhoto);
					modifiedPhotoFileName = piaCode +"photo"+extensionPhoto;
					String agendapathPhoto = photoFilePath+ System.getProperty("file.separator")+modifiedPhotoFileName;
					
					checkFile = business.checkFileType(photoFile);
					if (registrationForm.getPhotoFile().getFileName().length() > 0){
						if(checkFile.equals("true")){
							try{
								file= new File(filePath+System.getProperty("file.separator")+registrationForm.getAuthPhotoFileName());
								file.delete();
								outputStreamPhoto = new FileOutputStream(new File(agendapathPhoto));
								outputStreamPhoto.write(agendafileDataPhoto);
								flag6=true;
							}catch (Exception e) {
								e.printStackTrace();
							}finally{
								outputStreamPhoto.close();
							}
							
						}
						else{
							log.error(checkFile+" for authorized person photo file ("+registrationForm.getPhotoFile().getFileName()+") in updatePartB");
							request.setAttribute("photoError", checkFile);
							errorMsg = checkFile+" for  Photo";
						}
					}
				}else{
					flag6 = true;
				}
				if(!enrolFileName.equals("")){
					int dotPosEnrol = enrolFileName.lastIndexOf(".");
					String extensionEnrol = enrolFileName.substring(dotPosEnrol);
					modifiedEnrolFileName = piaCode +"enrol"+extensionEnrol;
					String agendapathEnrol = fileEnrolPath+ System.getProperty("file.separator")+modifiedEnrolFileName;
					checkFile = business.checkFileType(formEnrolFile);
					if (registrationForm.getEnrolmentFile().getFileName().length() > 0){
						if(checkFile.equals("true")){
							try{
								file= new File(filePath+System.getProperty("file.separator")+registrationForm.getEnrolmentFileName());
								file.delete();
								outputStreamEnrol = new FileOutputStream(new File(agendapathEnrol));
								outputStreamEnrol.write(agendafileDataEnrol);
								flag7=true;
							}catch (Exception e) {
								e.printStackTrace();
							}finally{
								outputStreamEnrol.close();
							}
						}
						else{
							log.error(checkFile+" for Enrol No ("+registrationForm.getEnrolmentFile().getFileName()+") in updatePartA");
							request.setAttribute("enrolError", checkFile);
							errorMsg = checkFile+" for Enrol No";
						}
					}
				}else{
					flag7 = true;
				}
				
			if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7){
				
				BeanUtils.copyProperties(piaDetailVO, registrationForm);	
				// Need to be changed
				/*PiaMemberDetailVO piaMemberFiles = business.getPiaMemberDetailSelected(piaDetailVO);*/
				// used to get the piaDetailVO
				// used to get the authorized person detail
				piaMemberDetailVO = new PiaMemberDetailVO();
				piaMemberDetailVO.setAuthorizedPerson("yes");
				piaMemberDetailVO.setMemberCode(registrationForm.getAuthPersonCode());
				piaMemberDetailVO.setMemberName(registrationForm.getAuthPersonName());
				piaMemberDetailVO.setContact(registrationForm.getAuthPersonContact());
				piaMemberDetailVO.setAddress(registrationForm.getAuthAddress());
				piaMemberDetailVO.setEmail(registrationForm.getAuthPersonEmail());
				piaMemberDetailVO.setPan(registrationForm.getAuthPersonPan());
				piaMemberDetailVO.setAadharVoterNo(registrationForm.getAuthPersonAdhaar());
				piaMemberDetailVO.setPassportDrivingNo(registrationForm.getAuthPersonPassport());
				piaMemberDetailVO.setAge(registrationForm.getAuthAge());
				piaMemberDetailVO.setDesignation(registrationForm.getAuthDesignation());
				piaMemberDetailVO.setRelativeName(registrationForm.getAuthRelativeName());
				piaMemberDetailVO.setAge(registrationForm.getAuthAge());
				piaMemberDetailVO.setOccupation(registrationForm.getAuthOccupation());
				piaMemberDetailVO.setPostOffice(registrationForm.getAuthPostOffice());
				piaMemberDetailVO.setPoliceStation(registrationForm.getAuthPoliceStation());
				piaMemberDetailVO.setStateCode(registrationForm.getAuthStateCode());
				piaMemberDetailVO.setPlace(registrationForm.getAuthPlace());
				piaMemberDetailVO.setIsNonProfitOrg(registrationForm.getIsNonProfitOrg());

				if(modifiedPanFileName != null){
					piaDetailVO.setPanFileName(modifiedPanFileName);
					registrationForm.setPanFileName(modifiedPanFileName);
				}else{
					piaDetailVO.setPanFileName(registrationForm.getPanFileName());
				}
				if(modifiedTanFileName != null){
					piaDetailVO.setTanFileName(modifiedTanFileName);
					registrationForm.setTanFileName(modifiedTanFileName);
				}else{
					piaDetailVO.setTanFileName(registrationForm.getTanFileName());
				}
				if(modifiedEnrolFileName != null){
					piaDetailVO.setEnrolmentFileName(modifiedEnrolFileName);
					registrationForm.setEnrolmentFileName(modifiedEnrolFileName);
				}else{
					piaDetailVO.setEnrolmentFileName(registrationForm.getEnrolmentFileName());
				}
				if(modifiedRegisterFileName != null){
					piaDetailVO.setRegFileName(modifiedRegisterFileName);
					registrationForm.setRegFileName(modifiedRegisterFileName);
				}else{
					piaDetailVO.setRegFileName(registrationForm.getRegFileName());
				}
				if(modifiedOfficePhotoFileName != null){
					piaDetailVO.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					registrationForm.setOfficePhotoFileName(modifiedOfficePhotoFileName);
				}else{
					piaDetailVO.setOfficePhotoFileName(registrationForm.getOfficePhotoFileName());
				}
				if(modifiedCVFileName != null){
					piaMemberDetailVO.setCvFileName(modifiedCVFileName);
					registrationForm.setAuthCVFileName(modifiedCVFileName);
				}else{
					piaMemberDetailVO.setCvFileName(registrationForm.getAuthCVFileName());
				}
				if(modifiedPhotoFileName != null){
					piaMemberDetailVO.setPhotoFileName(modifiedPhotoFileName);
					registrationForm.setAuthPhotoFileName(modifiedPhotoFileName);
				}else{
					piaMemberDetailVO.setPhotoFileName(registrationForm.getAuthPhotoFileName());
				}
				piaMemberList.add(piaMemberDetailVO);
				// end authorized person
		
				//find deleted member here
				List<String> pia_mem_codes_on_page = new ArrayList<>();
				pia_mem_codes_on_page.add(piaMemberDetailVO.getMemberCode());
				List<PiaMemberDetailVO> newPiaMembers = new ArrayList<>();
				
				
				// used to get member detail
				for(int member = 0; member < registrationForm.getMemberName().length; member++){
					piaMemberDetailVO = new PiaMemberDetailVO();
					if(!registrationForm.getMemberCode()[member].equals("0"))
						piaMemberDetailVO.setMemberCode(registrationForm.getMemberCode()[member]);
					
					if(piaDetailVO.getCategoryCode().equals("3") || piaDetailVO.getCategoryCode().equals("4")){
						piaMemberDetailVO.setLiability(registrationForm.getMemberLiability()[member]);
					}else{
						piaMemberDetailVO.setDesignation(registrationForm.getMemberDesignation()[member]);	
					}	
					piaMemberDetailVO.setMemberName(registrationForm.getMemberName()[member]);
					piaMemberDetailVO.setContact(registrationForm.getMemberContact()[member]);
					piaMemberDetailVO.setEmail(registrationForm.getMemberEmail()[member]);
					piaMemberDetailVO.setPan(registrationForm.getMemberPan()[member]);
					piaMemberDetailVO.setAadharVoterNo(registrationForm.getMemberAadharVoterNo()[member]);
					piaMemberDetailVO.setPassportDrivingNo(registrationForm.getMemberPassportDrivingNo()[member]);
					
					piaMemberList.add(piaMemberDetailVO);
					
					if(piaMemberDetailVO.getMemberCode()!=null){
						pia_mem_codes_on_page.add(piaMemberDetailVO.getMemberCode());
					}
					if(piaMemberDetailVO.getMemberCode()==null){
						newPiaMembers.add(piaMemberDetailVO);						
					}
				}
				// end to get member detail
				
				// used to get nri member detail
				if(registrationForm.getCategoryCode().equals("4") || registrationForm.getCategoryCode().equals("3")){
					if(registrationForm.getNriName() != null){
						for(int member = 0; member < registrationForm.getNriName().length; member++){
							piaMemberDetailVO = new PiaMemberDetailVO();
							if(!registrationForm.getNriCode()[member].equals("0"))
								piaMemberDetailVO.setMemberCode(registrationForm.getNriCode()[member]);
								piaMemberDetailVO.setMemberName(registrationForm.getNriName()[member]);
								piaMemberDetailVO.setCountry(registrationForm.getNriCountry()[member]);
								piaMemberDetailVO.setPassportDrivingNo(registrationForm.getNriPassportNo()[member]);
								piaMemberDetailVO.setPassportValidDate(registrationForm.getNriPassportValidDate()[member]);
								piaMemberDetailVO.setVisaValidDate(registrationForm.getNriVisaValidDate()[member]);
								piaMemberDetailVO.setWorkPermit(registrationForm.getNriWorkPermit()[member]);
								piaMemberDetailVO.setWorkingPermitDate(registrationForm.getNriWorkingPermitDate()[member]);
								piaMemberDetailVO.setClearanceFRAMHA(registrationForm.getNriclearanceFRAMHA()[member]);
								piaMemberDetailVO.setNriStatus("yes");
								
								piaMemberList.add(piaMemberDetailVO);
								
								if(piaMemberDetailVO.getMemberCode()!=null){
									pia_mem_codes_on_page.add(piaMemberDetailVO.getMemberCode());
								}if(piaMemberDetailVO.getMemberCode()==null){
									newPiaMembers.add(piaMemberDetailVO);	
								}
						}				
					}
				}
				
				//get Activity Mapping list
				List<PiaActivityMappingVO> piaActivityMappingVOList = new ArrayList<PiaActivityMappingVO>();
				for(String activityId : registrationForm.getPiaActivity()){
					PiaActivityMappingVO piaActivityMappingVO = new PiaActivityMappingVO();
					piaActivityMappingVO.setActivityId(activityId);
					piaActivityMappingVOList.add(piaActivityMappingVO);
				}
				// end to get nri member detail	
				piaDetailVO.setPanFileName(registrationForm.getPanFileName());
				piaDetailVO.setTanFileName(registrationForm.getTanFileName());
				piaDetailVO.setRegFileName(registrationForm.getRegFileName());
				piaDetailVO.setEnrolmentFileName(registrationForm.getEnrolmentFileName());
				piaDetailVO.setOfficePhotoFileName(registrationForm.getOfficePhotoFileName());
				
			boolean flag = business.updatePartB(piaDetailVO, piaMemberList, piaActivityMappingVOList);	
			if (flag) {	
				List<String> pia_mem_codes_in_db= new RegistrationDaoImpl().getPiaMemberCodes(piaDetailVO.getPiaCode());
				if(pia_mem_codes_in_db.size() > pia_mem_codes_on_page.size()){
					pia_mem_codes_in_db.removeAll(pia_mem_codes_on_page);
					List<String> pia_mem_codes_remain = new ArrayList<>();
					pia_mem_codes_remain.addAll(pia_mem_codes_in_db);
					new RegistrationDaoImpl().deletePiaMembers(pia_mem_codes_remain);
				}
				if(newPiaMembers.size() > 1){
					new RegistrationDaoImpl().addNewPiaMembers(newPiaMembers);
				}
				/*
				else if(newPiaMembers.size() > 1){
					new RegistrationDaoImpl().addNewPiaMembers(newPiaMembers);
				}
				*/
				
				log.info("PIA Registration(updatePartB) done successfully.");
				//request.setAttribute("piaMemberList", business.getPiaMemberDetail(piaDetailVO));
				//System.err.println("--out side final-----"+business.getPiaMemberDetail(piaDetailVO).size());
			} else {	
				log.error("RegistrationAction in updatePartB method");	
			}
		}}
		 catch (Exception e) {
			log.error( "RegistrationAction method = updatePartB()"+e.getMessage());
			errorMsg = "Found some error try again.";
		}finally{
			request.setAttribute("piaMemberList", business.getPiaMemberDetail(piaDetailVO));			
			request.setAttribute("cv", modifiedCVFileName);
			request.setAttribute("photo", modifiedPhotoFileName);
			request.setAttribute("stateList", masterDao.getStateList());
			request.setAttribute("piaCategory", business.getCategory());
			request.setAttribute("piaActivity",business.getActivity());

			
			
			
		}
		String forward = null;
		//this.checkAuthenticatePIA(mapping, registrationForm, request, response);
		piaDetailVO.setPiaCode(registrationForm.getPiaCode());
		piaDetailVO = business.getPiaDetail(piaDetailVO);
		request.setAttribute("piaDetail", piaDetailVO);
		request.setAttribute("piaMemberDetail", business.getPiaMemberDetail(piaDetailVO));
		if(errorMsg != null){
			forward = Constants.SHOW_PIA_COMPLETE_REGISTRATION;
		}else{
			forward = Constants.PRINT_PIA_REGISTER;
		}
		return mapping.findForward(forward);
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
	public ActionForward continueWithPartB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)			
	throws Exception {
		RegistrationForm registrationForm = (RegistrationForm) form;
		registrationForm.setCaptchaResponse("");
		request.setAttribute("stateList", masterDao.getStateList());
		return mapping.findForward(Constants.SHOW_PIA_REGISTER_PARTB);
	}
	
	public ActionForward showFiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("inside showFiles at RegistrationAction page");
		String fileName = request.getParameter("file");
		request.setAttribute("piaFile", fileName);
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
           }catch(IOException ioe)  
           {  
               ioe.printStackTrace();  
           }
        return null;
	}
	
	/**
	 * PDF CODES
	 */
	public static Font HEADER_FONT_TOP = FontFactory.getFont("georgia", 12, BaseColor.BLACK);
	public static Font SUB_HEADER_FONT = FontFactory.getFont("georgia", 8, BaseColor.BLUE);
	public static Font TABLE_HEADER_FONT = FontFactory.getFont("georgia", 8, BaseColor.BLACK);
	public static Font BODY_FONT = FontFactory.getFont("georgia", 8, BaseColor.BLACK);
	
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
		RegistrationForm registrationForm = (RegistrationForm) form;
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		piaDetailVO.setPiaCode(registrationForm.getPiaCode());
		PiaDetailVO piaInfo = business.getPiaDetail(piaDetailVO);
		List<PiaMemberDetailVO>  piaMemberInfo= business.getPiaMemberDetail(piaDetailVO);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
	
		
		String test = piaInfo.getPiaName().replaceAll("\\s+", "_")+"_Detail.pdf";
		
		Document document = new Document();
		response.addHeader("content-disposition", "attachment; filename="+test);
		response.setContentType("application/pdf");
		PdfWriter.getInstance(document,response.getOutputStream());
		PdfWriter.getInstance(document,new FileOutputStream(piaInfo.getPiaName()+" Detail"));
		document.open();
		
		String serverPath = getServlet().getServletContext().getRealPath("/images/ddukgylogo.jpg");
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
		
		headerCell = new PdfPCell(new Paragraph("DDU-GKY", HEADER_FONT_TOP));
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
		
		headerCell = new PdfPCell(new Paragraph("NITI Aayog Alloted Unique Id:", BODY_FONT));
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		subHeaderTBL.addCell(headerCell);
		
		headerCell = new PdfPCell (new Paragraph(piaInfo.getEnrolmentNumber(), BODY_FONT));
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
		
		
		/////////////////////////////////////////////
		
		
		
		
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
					
					authInfoCell = new PdfPCell(new Paragraph("Passport No./Driving Licence No.", BODY_FONT));
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
				               "furnished above is complete and correct in all respects. In case any of the information in this registration application is found to be "+
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
		document.add(authInfoTBL);
		if (piaInfo.getCategoryCode().equals("1")) {
			document.add(eduInfoTBL);
		}
		if (piaInfo.getCategoryCode().equals("4") || piaInfo.getCategoryCode().equals("3")) {
			document.add(nriTBL);
		}
		document.add(piaAdditionalDetailTBL);
		document.add(verificationTBL);
		document.close();
		return null;
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
	public ActionForward completeRegistration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception {		
		RegistrationForm registrationForm = (RegistrationForm) form;
		return mapping.findForward(Constants.SHOW_PIA_COMPLETE_REGISTRATION);
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
	public ActionForward authenticatePIA(ActionMapping mapping, ActionForm form,		
			HttpServletRequest request, HttpServletResponse response)		
	throws Exception {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return mapping.findForward(Constants.SHOW_PIA_SEARCH);
	}
	
	public ActionForward checkAuthenticatePIA(ActionMapping mapping, ActionForm form,		
			HttpServletRequest request, HttpServletResponse response)		
	throws Exception {
		String forward = Constants.SHOW_PIA_SEARCH;
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		
		RegistrationForm registrationForm = (RegistrationForm) form;
		BeanUtils.copyProperties(piaDetailVO, registrationForm);
		
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		
		if(business.verifyPIA(piaDetailVO)){
			piaDetailVO = business.getPiaDetail(piaDetailVO);
			request.setAttribute("piaCategory", business.getCategory());
			request.setAttribute("piaActivity", business.getActivity());
			request.setAttribute("stateList", masterDao.getStateList());
			request.setAttribute("piaDetail", piaDetailVO);
			request.setAttribute("piaMemberDetail", business.getPiaMemberDetail(piaDetailVO));
			if (piaDetailVO.getDistrictCode() != null
					&& !piaDetailVO.getDistrictCode().equals("")){
				BlockMasterVO blockMasterVO = new BlockMasterVO(); 
				blockMasterVO.setDistrictCode(piaDetailVO.getDistrictCode());
				request.setAttribute("blockList",masterDao.getBlockList(blockMasterVO));
			}
			if (piaDetailVO.getStateCode() != null
					&& !piaDetailVO.getStateCode().equals("")){
				DistrictVO districtVO = new DistrictVO();
				districtVO.setStateCode(piaDetailVO.getStateCode());
				request.setAttribute("districtList",masterDao.getDistrictList(districtVO));
			}	
			forward = Constants.SHOW_PIA_COMPLETE_REGISTRATION;
		}	else{
			request.setAttribute("ErrorMsgPIA", "Information provided against one of 4 fields is incorrect. Please try again.");
		}
		return mapping.findForward(forward);
	}
}