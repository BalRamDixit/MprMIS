package com.infotech.skills.dao.pia;

import java.util.List;

import com.infotech.skills.hbm.ContactDetail;
import com.infotech.skills.hbm.piaprofile.ActivityVO;
import com.infotech.skills.hbm.piaprofile.CategoryVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;

public interface PiaDao {

	
	public boolean piaConfirmed(PiaDetailVO piaDetailVO,String UserId);

	public boolean piaRejected(PiaDetailVO piaDetailVO,String UserId);

	public List<PiaDetailVO> getPiaListForDocsCheck();

	public PiaDetailVO getDetailofPia(String piaCode);

	public List<PiaMemberDetailVO> getPiaMemberDetail(String piaCode);

	public List outerReportCount();

	public List<PiaDetailVO> getPiaListForFinalVerification();

	public boolean piaDocumentsChecked(PiaDetailVO piaDetailVO,String UserId);

	public boolean piaDocumentsRecommendedForRejection(PiaDetailVO piaDetailVO,String UserId);

	public List<PiaDetailVO> getPiaListForIndexPage(String reportType);

	public List<PiaDetailVO> getPiaListForLoginPage(PiaDetailVO piaDetailVO);
	
	public List getPiaSearchList(String piaSearchValue);
	
	public List<PiaDetailVO> getPiaListForLoginPageInner(PiaDetailVO piaDetailVO);
	
	public List<PiaDetailVO> getIncompletePIAList();

	List<PiaDetailVO> getVerifiedPIAList();

	List<CategoryVO> getCategory() throws Exception;

	List<ActivityVO> getActivity() throws Exception;
	
	boolean modifyPia(PiaDetailVO piaDetailVO, List<PiaMemberDetailVO> piaMemberDetailsList, List<PiaActivityMappingVO> piaActivityMappingVOList, String string) throws Exception;

	public boolean piaReverted(PiaDetailVO piaDetailVO, String string);
	
	public List<ContactDetail> getContactDetail();
	
	public String generatePIAExcelZipFile();

	public String generatePIAExcelFile(String piaStatus);
	
	//
	public List<PiaDetailVO> getPiaListForFinalRejection();
	public boolean piaReject(PiaDetailVO piaDetailVO,String UserId);

	List<CategoryVO> getRegistrationCategory() throws Exception;

	public String getCategortShortName(String parameter);

	public String getPrnNumber(String parameter);

	public boolean modifyPIACategoryAndNumber(PiaDetailVO piaDetailVO, String parameter,
			String modifiedPrnNumber, String string, String string2);

	public List<PiaDetailVO> getPiaListForCategoryChange();
	public List<PiaDetailVO> getPiaListForIncompletePIARejection();
	
	public boolean incompletePIAReject(PiaDetailVO piaDetailVO,String UserId);
	
	public List<PiaDetailVO> getMonthOldIncompletePIAListForRejection();

	public boolean getMonthOldPIADeemed(PiaDetailVO piaDetailVO);


}
