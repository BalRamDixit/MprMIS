package com.infotech.skills.registration.pia;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.infotech.skills.hbm.piaprofile.ActivityVO;
import com.infotech.skills.hbm.piaprofile.CategoryVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;


public class RegistrationBusinessImpl implements RegistrationBusiness {
	private static Logger log = Logger.getRootLogger();
	RegistrationDao registrationDao = null;
	
	@Override
	public List<CategoryVO> getCategory() throws Exception {
		registrationDao = new RegistrationDaoImpl();
		return registrationDao.getCategory();
	}

	@Override
	public List<ActivityVO> getActivity() throws Exception {
		registrationDao = new RegistrationDaoImpl();
		return registrationDao.getActivity();
	}

	@Override
	public boolean modify(PiaDetailVO piaDetailVO, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception {
		// TODO Auto-generated method stub
		return (Boolean) null;
	}


	
	public int getSequenceCodeForFileUpload(){
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.getSequenceCodeForFileUpload();
	}
	
	/**
	 * 
	 */
	public String checkFileType(FormFile file) throws Exception {
		String result = null;
		String contenttype = file.getContentType();
		String fileName = file.getFileName();
		// Getting the file extension
		String extension;
		if (!fileName.equals("")) {
			int dotPos = fileName.lastIndexOf(".");
			extension = fileName.substring(dotPos);
			if (extension.equalsIgnoreCase(".pdf") ||extension.equalsIgnoreCase(".png")
					|| contenttype.equalsIgnoreCase("image/jpeg") && (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))){
				if(file.getFileSize()/1024 > 300){
					result="File Size greater than 300KB";
				}
				else{
					result = "true";
				}
			}else{
				result = "File format not supported";
			}}
		
		return result;
	}

	@Override
	public PiaDetailVO getPiaDetail(PiaDetailVO piaDetailVO) throws Exception {
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.getPiaDetail(piaDetailVO);
	}
	
	@Override
	public List<PiaMemberDetailVO> getPiaMemberDetail(PiaDetailVO piaDetailVO)
			throws Exception {
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.getPiaMemberDetail(piaDetailVO);
	}

	/**
	 *  Date: 3 September 2013
	 */
	@Override
	public boolean savePartA(PiaDetailVO piaDetailVO,
			List<PiaActivityMappingVO> piaActivityMappingVOList)
			throws Exception {
		log.info("Registration: savePartA RegistrationBusiness... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.savePartA(piaDetailVO, piaActivityMappingVOList);
	}

	@Override
	public boolean updatePartA(PiaDetailVO piaDetailVO,
			List<PiaActivityMappingVO> piaActivityMappingVOList)
			throws Exception {
		log.info("Registration: updatePartA RegistrationBusiness... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.updatePartA(piaDetailVO, piaActivityMappingVOList);
	}

	@Override
	public boolean savePartB(PiaDetailVO piaDetailVO,
			List<PiaMemberDetailVO> piaMemberDetailsList) throws Exception {
		log.info("Registration: savePartB RegistrationBusiness... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.savePartB(piaDetailVO, piaMemberDetailsList);
	}

	@Override
	public boolean updatePartB(PiaDetailVO piaDetailVO,
			List<PiaMemberDetailVO> piaMemberDetailsList, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception {
		log.info("Registration: updatePartB RegistrationBusiness... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.updatePartB(piaDetailVO, piaMemberDetailsList, piaActivityMappingVOList);
	}

	@Override
	public boolean submit(PiaDetailVO piaDetailVO) throws Exception {
		log.info("Registration: submit RegistrationBusiness... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.submit(piaDetailVO);
	}
	/**
	 *  Date: 3 September 2013 END
	 */

	@Override
	public PiaMemberDetailVO getPiaMemberDetailSelected(PiaDetailVO piaDetailVO)
			throws Exception {
		log.info("Registration: getPiaMemberDetailSelected RegistrationBusiness... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.getPiaMemberDetailSelected(piaDetailVO);
	}
	
	public boolean verifyPIA(PiaDetailVO piaDetailVO) throws Exception{
		log.info("Registration: PIA Verification form... ");
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.verifyPIA(piaDetailVO);
	}
	
	@Override
	public boolean validatePanTan(PiaDetailVO piaDetailVO) throws Exception {
		registrationDao = new RegistrationDaoImpl(); 
		return registrationDao.validatePanTan(piaDetailVO);
	}

	@Override
	public List<CategoryVO> getRegistrationCategory() throws Exception {
		registrationDao = new RegistrationDaoImpl();
		return registrationDao.getRegistrationCategory();
	}
	
}
