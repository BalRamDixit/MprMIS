package com.infotech.skills.proposal.verifypia;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.skills.dao.pia.PiaDao;
import com.infotech.skills.dao.pia.PiaDaoImpl;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;
import com.infotech.skills.master.MasterDao;
import com.infotech.skills.master.MasterDaoImpl;
import com.infotech.skills.registration.pia.RegistrationBusiness;
import com.infotech.skills.registration.pia.RegistrationBusinessImpl;
import com.infotech.skills.proposal.verifypia.ModifyPiaForm;
import com.infotech.skills.util.Constants;

public class ModifyPiaDocsAction extends DispatchAction{
	
	protected final Log log = LogFactory.getLog(getClass());
	ActionMessages message = new ActionMessages();
	PiaDao piaDao = new PiaDaoImpl();
	RegistrationBusiness business = null;
	MasterDao masterDao = null;
	String filePath = Constants.LIVE_SERVER_PATH;
	
	/**
	 * Function help in modification of PIA detail BY Aajeevika Skills DS Login
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifyPIADetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
 	throws Exception{		
		log.info("modifyPIADetails function call for PIA Details modification");

		ModifyPiaForm modifyPiaForm = (ModifyPiaForm) form;
		List<PiaDetailVO> listOfVerifiedPIAs = new ArrayList<PiaDetailVO>();
		PiaDetailVO piaDetail;
		listOfVerifiedPIAs = piaDao.getVerifiedPIAList();
		masterDao = new MasterDaoImpl();
		try {
			if (modifyPiaForm.getPiaCode() != null
					&& !modifyPiaForm.getPiaCode().isEmpty()) {
				piaDetail = new PiaDetailVO();
				piaDetail = piaDao.getDetailofPia(modifyPiaForm.getPiaCode());
				request.setAttribute("piaCategory", piaDao.getCategory());
				request.setAttribute("piaActivity", piaDao.getActivity());
				request.setAttribute("stateList", masterDao.getStateList());
				request.setAttribute("piaDetail", piaDetail);
				request.setAttribute("piaMemberDetail", piaDao.getPiaMemberDetail(piaDetail.getPiaCode()));
				if (piaDetail.getDistrictCode() != null
						&& !piaDetail.getDistrictCode().equals("")){
					BlockMasterVO blockMasterVO = new BlockMasterVO(); 
					blockMasterVO.setDistrictCode(piaDetail.getDistrictCode());
					request.setAttribute("blockList", masterDao.getBlockList(blockMasterVO));
				}
				if (piaDetail.getStateCode() != null
						&& !piaDetail.getStateCode().equals("")){
					DistrictVO districtVO = new DistrictVO();
					districtVO.setStateCode(piaDetail.getStateCode());
					request.setAttribute("districtList", masterDao.getDistrictList(districtVO));
				}
				
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		} finally {
			request.setAttribute("listOfVerifiedPIAs", listOfVerifiedPIAs);
		}

		return mapping.findForward("PIA_MODIFICATION_LOGIN");
	}
	
	public ActionForward modifyPia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("modifyPia function call for PIA Details modification(in ModifyPiaDocsAction)");
		
		ModifyPiaForm modifyPiaForm = (ModifyPiaForm) form;
		PiaMemberDetailVO piaMemberDetailVO = new PiaMemberDetailVO();
		PiaDetailVO piaDetailVO = new PiaDetailVO();
		List<PiaMemberDetailVO> piaMemberList = new ArrayList<PiaMemberDetailVO>();
		business = new RegistrationBusinessImpl();
		masterDao = new MasterDaoImpl();
		LoginVO loginVo=(LoginVO) request.getSession().getAttribute("loginVO");
		
		FileOutputStream outputStreamCV,outputStreamPhoto, outputStreamPan,outputStreamTan,outputStreamReg,outputStreamOfficePhoto;
		outputStreamCV=outputStreamPhoto=outputStreamPan=outputStreamTan=outputStreamReg=outputStreamOfficePhoto= null;				
		boolean flag1= false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;
		String cvFileName = null, photoFileName = null, panFileName = null,registerFileName = null,officePhotoFileName = null,tanFileName = null;
		byte[] agendafileDataCV = null,agendafileDataPhoto = null, agendafileData = null,agendafileDataTan = null,agendafileDataRegister = null,agendafileDataOfficePhoto = null;
		String cvFilePath = null,photoFilePath = null, filePanPath = null,fileTanPath = null,fileRegPath = null,fileOfficePhotoPath = null;
		FormFile cvFile = null, photoFile = null, formPanFile = null,formTanFile = null,formRegisterFile = null,formOfficePhotoFile = null;
		String modifiedCVFileName = null,modifiedPhotoFileName = null, modifiedPanFileName = null,modifiedTanFileName = null,modifiedRegisterFileName = null,modifiedOfficePhotoFileName = null;
		File file = null;
		String checkFile = null;
		String errorMsg = null;
		try {
				formPanFile = (FormFile) modifyPiaForm.getPanFile();
				panFileName = formPanFile.getFileName();
				agendafileData = formPanFile.getFileData();
			
				formTanFile = (FormFile) modifyPiaForm.getTanFile();
				tanFileName = formTanFile.getFileName();
				agendafileDataTan = formTanFile.getFileData();
			
				formRegisterFile = (FormFile) modifyPiaForm.getRegFile();
				registerFileName = formRegisterFile.getFileName();
				agendafileDataRegister = formRegisterFile.getFileData();
		
				formOfficePhotoFile = (FormFile) modifyPiaForm.getOfficePhotoFile();
				officePhotoFileName = formOfficePhotoFile.getFileName();
				agendafileDataOfficePhoto = formOfficePhotoFile.getFileData();
				
				cvFile = (FormFile) modifyPiaForm.getCvFile();
				cvFileName = cvFile.getFileName();
				agendafileDataCV = cvFile.getFileData();
	
				photoFile = (FormFile) modifyPiaForm.getPhotoFile();
				photoFileName = photoFile.getFileName();
				agendafileDataPhoto = photoFile.getFileData();
			
			if(!cvFileName.equals("") || !photoFileName.equals("")  || !panFileName.equals("") || !tanFileName.equals("") || !registerFileName.equals("") || !officePhotoFileName.equals("")){
			
			cvFilePath=photoFilePath=filePanPath=fileRegPath=fileTanPath = fileOfficePhotoPath=filePath;
			//log.info("Path to save PIA files="+ filePath);
			
			if (new File(filePath).isDirectory() == false) {
				new File(filePath).mkdirs();
			}
			}
			String piaCode = modifyPiaForm.getPiaCode();
			if(!panFileName.equals("")){
				int dotPosPan = panFileName.lastIndexOf(".");
				String extensionPan = panFileName.substring(dotPosPan);
				modifiedPanFileName =  piaCode+ "pan"+extensionPan;
				String agendapathPan = filePanPath+ System.getProperty("file.separator")+modifiedPanFileName;
				checkFile = business.checkFileType(formPanFile);
				if (modifyPiaForm.getPanFile().getFileName().length() > 0 && checkFile.equals("true")){
					try{
						file= new File(filePath+System.getProperty("file.separator")+modifyPiaForm.getPanFileName());
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
					log.error(checkFile+" for Pan Card ("+modifyPiaForm.getPanFile().getFileName()+") in modifyPia");
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
				if (modifyPiaForm.getTanFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+modifyPiaForm.getTanFileName());
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
						log.error(checkFile+" for Tan Card ("+modifyPiaForm.getTanFile().getFileName()+") in modifyPia");
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
				if (modifyPiaForm.getRegFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+modifyPiaForm.getRegFileName());
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
						log.error(checkFile+" for Registration File: ("+modifyPiaForm.getRegFile().getFileName()+") in modifyPia");
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
				if (modifyPiaForm.getOfficePhotoFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+modifyPiaForm.getOfficePhotoFileName());
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
						log.error(checkFile+" for Office Photo ("+modifyPiaForm.getOfficePhotoFile().getFileName()+") in modifyPia");
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
				if (modifyPiaForm.getCvFile().getFileName().length() > 0){
					if(checkFile.equals("true")){
						try{
							file= new File(filePath+System.getProperty("file.separator")+modifyPiaForm.getAuthCVFileName());
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
						log.error(checkFile+" for authorized person CV file ("+modifyPiaForm.getCvFile().getFileName()+") in modifyPia");
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
					if (modifyPiaForm.getPhotoFile().getFileName().length() > 0){
						if(checkFile.equals("true")){
							try{
								file= new File(filePath+System.getProperty("file.separator")+modifyPiaForm.getAuthPhotoFileName());
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
							log.error(checkFile+" for authorized person photo file ("+modifyPiaForm.getPhotoFile().getFileName()+") in modifyPia");
							request.setAttribute("photoError", checkFile);
							errorMsg = checkFile+" for  Photo";
						}
					}
				}else{
					flag6 = true;
				}
				
			if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6){
				
				BeanUtils.copyProperties(piaDetailVO, modifyPiaForm);	
				
				piaMemberDetailVO = new PiaMemberDetailVO();
				piaMemberDetailVO.setAuthorizedPerson("yes");
				piaMemberDetailVO.setMemberCode(modifyPiaForm.getAuthPersonCode());
				piaMemberDetailVO.setMemberName(modifyPiaForm.getAuthPersonName());
				piaMemberDetailVO.setContact(modifyPiaForm.getAuthPersonContact());
				piaMemberDetailVO.setAddress(modifyPiaForm.getAuthAddress());
				piaMemberDetailVO.setEmail(modifyPiaForm.getAuthPersonEmail());
				piaMemberDetailVO.setPan(modifyPiaForm.getAuthPersonPan());
				piaMemberDetailVO.setAadharVoterNo(modifyPiaForm.getAuthPersonAdhaar());
				piaMemberDetailVO.setPassportDrivingNo(modifyPiaForm.getAuthPersonPassport());
				piaMemberDetailVO.setAge(modifyPiaForm.getAuthAge());
				piaMemberDetailVO.setDesignation(modifyPiaForm.getAuthDesignation());
				piaMemberDetailVO.setRelativeName(modifyPiaForm.getAuthRelativeName());
				piaMemberDetailVO.setAge(modifyPiaForm.getAuthAge());
				piaMemberDetailVO.setOccupation(modifyPiaForm.getAuthOccupation());
				piaMemberDetailVO.setPostOffice(modifyPiaForm.getAuthPostOffice());
				piaMemberDetailVO.setPoliceStation(modifyPiaForm.getAuthPoliceStation());
				piaMemberDetailVO.setStateCode(modifyPiaForm.getAuthStateCode());
				piaMemberDetailVO.setPlace(modifyPiaForm.getAuthPlace());
				// end authorized person
				
				if(modifiedPanFileName != null){
					piaDetailVO.setPanFileName(modifiedPanFileName);
					modifyPiaForm.setPanFileName(modifiedPanFileName);
				}else{
					piaDetailVO.setPanFileName(modifyPiaForm.getPanFileName());
				}
				if(modifiedTanFileName != null){
					piaDetailVO.setTanFileName(modifiedTanFileName);
					modifyPiaForm.setTanFileName(modifiedTanFileName);
				}else{
					piaDetailVO.setTanFileName(modifyPiaForm.getTanFileName());
				}
				if(modifiedRegisterFileName != null){
					piaDetailVO.setRegFileName(modifiedRegisterFileName);
					modifyPiaForm.setRegFileName(modifiedRegisterFileName);
				}else{
					piaDetailVO.setRegFileName(modifyPiaForm.getRegFileName());
				}
				if(modifiedOfficePhotoFileName != null){
					piaDetailVO.setOfficePhotoFileName(modifiedOfficePhotoFileName);
					modifyPiaForm.setOfficePhotoFileName(modifiedOfficePhotoFileName);
				}else{
					piaDetailVO.setOfficePhotoFileName(modifyPiaForm.getOfficePhotoFileName());
				}
				if(modifiedCVFileName != null){
					piaMemberDetailVO.setCvFileName(modifiedCVFileName);
					modifyPiaForm.setAuthCVFileName(modifiedCVFileName);
				}else{
					piaMemberDetailVO.setCvFileName(modifyPiaForm.getAuthCVFileName());
				}
				if(modifiedPhotoFileName != null){
					piaMemberDetailVO.setPhotoFileName(modifiedPhotoFileName);
					modifyPiaForm.setAuthPhotoFileName(modifiedPhotoFileName);
				}else{
					piaMemberDetailVO.setPhotoFileName(modifyPiaForm.getAuthPhotoFileName());
				}
				piaMemberList.add(piaMemberDetailVO);
		
				// used to get member detail
				for(int member = 0; member < modifyPiaForm.getMemberName().length; member++){
					piaMemberDetailVO = new PiaMemberDetailVO();
					if(!modifyPiaForm.getMemberCode()[member].equals("0"))
						piaMemberDetailVO.setMemberCode(modifyPiaForm.getMemberCode()[member]);
					
					if(piaDetailVO.getCategoryCode().equals("3") || piaDetailVO.getCategoryCode().equals("4")){
						piaMemberDetailVO.setLiability(modifyPiaForm.getMemberLiability()[member]);
					}else{
						piaMemberDetailVO.setDesignation(modifyPiaForm.getMemberDesignation()[member]);	
					}	
					piaMemberDetailVO.setMemberName(modifyPiaForm.getMemberName()[member]);
					piaMemberDetailVO.setContact(modifyPiaForm.getMemberContact()[member]);
					piaMemberDetailVO.setEmail(modifyPiaForm.getMemberEmail()[member]);
					piaMemberDetailVO.setPan(modifyPiaForm.getMemberPan()[member]);
					piaMemberDetailVO.setAadharVoterNo(modifyPiaForm.getMemberAadharVoterNo()[member]);
					piaMemberDetailVO.setPassportDrivingNo(modifyPiaForm.getMemberPassportDrivingNo()[member]);
					piaMemberList.add(piaMemberDetailVO);
				}
				// end to get member detail
				
				// used to get nri member detail
				/*if(modifyPiaForm.getCategoryCode().equals("4") || modifyPiaForm.getCategoryCode().equals("3")){
					for(int member = 0; member < modifyPiaForm.getNriName().length; member++){
						piaMemberDetailVO = new PiaMemberDetailVO();
						if(!modifyPiaForm.getNriCode()[member].equals("0"))
							piaMemberDetailVO.setMemberCode(modifyPiaForm.getNriCode()[member]);
							piaMemberDetailVO.setMemberName(modifyPiaForm.getNriName()[member]);
							piaMemberDetailVO.setCountry(modifyPiaForm.getNriCountry()[member]);
							piaMemberDetailVO.setPassportDrivingNo(modifyPiaForm.getNriPassportNo()[member]);
							piaMemberDetailVO.setPassportValidDate(modifyPiaForm.getNriPassportValidDate()[member]);
							piaMemberDetailVO.setVisaValidDate(modifyPiaForm.getNriVisaValidDate()[member]);
							piaMemberDetailVO.setWorkPermit(modifyPiaForm.getNriWorkPermit()[member]);
							piaMemberDetailVO.setWorkingPermitDate(modifyPiaForm.getNriWorkingPermitDate()[member]);
							piaMemberDetailVO.setClearanceFRAMHA(modifyPiaForm.getNriclearanceFRAMHA()[member]);
							piaMemberDetailVO.setNriStatus("yes");
							piaMemberList.add(piaMemberDetailVO);
					}				
				}*/
				// end to get nri member detail	
				//get Activity Mapping list
				List<PiaActivityMappingVO> piaActivityMappingVOList = new ArrayList<PiaActivityMappingVO>();
				for(String activityId : modifyPiaForm.getPiaActivity()){
					PiaActivityMappingVO piaActivityMappingVO = new PiaActivityMappingVO();
					piaActivityMappingVO.setActivityId(activityId);
					piaActivityMappingVOList.add(piaActivityMappingVO);
				}
				
			boolean flag = piaDao.modifyPia(piaDetailVO, piaMemberList, piaActivityMappingVOList,loginVo.getUserid());	
			if (flag) {	
				log.info("PIA ("+piaDetailVO.getPiaCode()+") modified successfully.");
				request.setAttribute("msg", "PIA ("+piaDetailVO.getPiaCode()+") modified successfully.");
			} else {	
				log.error("ModifyPiaDocsAction in modifyPia method while modifying "+piaDetailVO.getPiaCode()+"'s detail");	
			}
		}}
		 catch (Exception e) {
			log.error( "ModifyPiaDocsAction method = modifyPia()"+e);
			e.printStackTrace();
			errorMsg = "Found some error try again.";
		}
		return this.modifyPIADetails(mapping, modifyPiaForm, request, response);
	}

}
