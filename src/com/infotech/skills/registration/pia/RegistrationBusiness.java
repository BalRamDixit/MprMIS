package com.infotech.skills.registration.pia;

import java.util.List;

import org.apache.struts.upload.FormFile;

import com.infotech.skills.hbm.piaprofile.ActivityVO;
import com.infotech.skills.hbm.piaprofile.CategoryVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;

public interface RegistrationBusiness {
	
	public List<CategoryVO> getCategory() throws Exception;	
	public List<ActivityVO> getActivity() throws Exception;
	
	//public String save(RegistrationForm registrationForm) throws Exception;
	public boolean savePartA(PiaDetailVO piaDetailVO, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception;
	public boolean updatePartA(PiaDetailVO piaDetailVO, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception;
	
	public boolean savePartB(PiaDetailVO piaDetailVO, List<PiaMemberDetailVO> piaMemberDetailsList) throws Exception;
	public boolean updatePartB(PiaDetailVO piaDetailVO, List<PiaMemberDetailVO> piaMemberDetailsList, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception;
	
	public boolean submit(PiaDetailVO piaDetailVO) throws Exception;
	
	public boolean modify(PiaDetailVO piaDetailVO, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception;	
	public PiaDetailVO getPiaDetail(PiaDetailVO piaDetailVO) throws Exception;
	public List<PiaMemberDetailVO> getPiaMemberDetail(PiaDetailVO piaDetailVO) throws Exception;
	public PiaMemberDetailVO getPiaMemberDetailSelected(PiaDetailVO piaDetailVO) throws Exception;
	public int getSequenceCodeForFileUpload();
	public String checkFileType(FormFile file) throws Exception;
	
	public boolean verifyPIA(PiaDetailVO piaDetailVO) throws Exception;
	boolean validatePanTan(PiaDetailVO piaDetailVO) throws Exception; 
	public List<CategoryVO> getRegistrationCategory() throws Exception;	
			
}
