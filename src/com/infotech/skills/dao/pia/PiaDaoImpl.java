package com.infotech.skills.dao.pia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections.list.TransformedList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.mail.SendMail;
import com.infotech.skills.hbm.ContactDetail;
import com.infotech.skills.hbm.admin.AssignRoleVO;
import com.infotech.skills.hbm.admin.UserVO;
import com.infotech.skills.hbm.piaprofile.ActivityVO;
import com.infotech.skills.hbm.piaprofile.CategoryVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;
import com.infotech.skills.util.Constants;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;

public class PiaDaoImpl implements PiaDao {
	/**
	 * @since August 2013
	 */
	protected final Log log = LogFactory.getLog(getClass());
	List<String> filesListInDir = new ArrayList<String>();

	@Override
	public boolean piaConfirmed(PiaDetailVO piaDetailVO,String userId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String date = new DateUtil().getCurrentDate();		
		String piaCode = piaDetailVO.getPiaCode();
		String currentDate = new DateUtil().getCurrentDate();
		String query_sql = "SELECT (SELECT state_short_name from mst_state where state_code = :stateCode)"
				+ "|| (SELECT EXTRACT(YEAR FROM CURRENT_DATE)) || (select form_type from pia_category where category_id= :categoryId)";

		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));
			PiaDetailVO piaDetail = (PiaDetailVO) criteria.list().get(0);

			Query query = session.createSQLQuery(query_sql);
			query.setParameter("stateCode",
					piaDetail.getRegistrationStateCode());
			query.setParameter("categoryId", piaDetail.getCategoryCode());
			String piaRegistrationCode = query.list().iterator().next()
					.toString()
					+ piaCode;

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setPiaRegistrationNo(piaRegistrationCode);
				piaDetailVO.setModifyBy(userId);
				piaDetailVO.setModifyOn(currentDate);
				piaDetailVO.setPiaStatus(Constants.PIA_VERIFIED);
				piaDetailVO.setPiaConfirmationDate(date);
				/*
				 * piaDetailVO.setModifyBy("MANISH");
				 * piaDetailVO.setModifyOn(date);
				 */
			}
			session.update(piaDetailVO);

			UserVO userVO = new UserVO();
			userVO.setUserName(piaDetailVO.getPiaName());
			userVO.setLevelCd("4");
			userVO.setUserDesign(piaDetailVO.getPiaName());
			userVO.setLoginId(piaDetailVO.getPiaRegistrationNo());

			String password = RandomStringUtils.randomAlphabetic(3) + "@"
					+ RandomStringUtils.randomNumeric(2) + "#"
					+ RandomStringUtils.randomAlphanumeric(5)
					+ RandomStringUtils.randomNumeric(2);
			userVO.setPassword(md5(password));
			userVO.setEmailId(piaDetailVO.getEmail());
			userVO.setActiveFlag("Y");
			userVO.setEntityCode(piaDetailVO.getPiaCode());
			userVO.setLoginAttempt(0);
			userVO.setLoginStatus("Active");
			userVO.setCreatedBy(piaDetailVO.getPiaCode());
			userVO.setCreatedOn(currentDate);
			session.save(userVO);

			AssignRoleVO assignRoleVO = new AssignRoleVO();
			assignRoleVO.setLoginId(userVO.getLoginId());
			assignRoleVO.setRoleCd("226");
			assignRoleVO.setLevelCd("4");
			assignRoleVO.setLevelRole("4");
			assignRoleVO.setEntityCode(piaDetailVO.getPiaCode());
			assignRoleVO.setCreatedBy(piaDetailVO.getPiaCode());
			assignRoleVO.setCreatedOn(currentDate);
			session.save(assignRoleVO);
			tr.commit();
			flag = true;
			if (flag) {
				/*String body = "Dear "
						+ userVO.getUserDesign()
						+ "\n"
						+ " \n A new RegistrationId is created for you which can be used as UserId "
						+ " \n Following are your  RegistrationNo details for logging into Aajeevika Skills at http://aajeevikaskills.gov.in"
						+ " \n\n RegistrationNo  : "
						+ userVO.getLoginId()
						+ " \n"
						+ " Password  : "
						+ password
						+ " \n \n"
						+ " Here are the Details according to your Profile which you have been submitted during Registration \n"
						+ " Name : " + piaDetailVO.getPiaName() + "\n"
						+ " Email : " + userVO.getEmailId() + "\n"
						+ " PanNo : " + piaDetailVO.getPanNo() + "\n"
						+ " TanNo : " + piaDetailVO.getTanNo() + "\n\n\n"
						+ " DISCLAIMER : This is an AutoGenerated Mail.";
				SendMail sendMail = new SendMail();
				sendMail.sendMail(userVO.getEmailId(), "Aajeevika Skills ", body);*/
				
				// get authorized member details
				PiaMemberDetailVO authorizedPersonDetail = null;
				List<PiaMemberDetailVO> memeberList = getPiaMemberDetail(piaCode);
				for(PiaMemberDetailVO detailVO : memeberList) {
					if(detailVO.getAuthorizedPerson() != null && detailVO.getAuthorizedPerson().equals("yes")) {
						authorizedPersonDetail = detailVO;
						break;
					}
				}				
				
				String body = "\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDated:"+(new DateUtil().getCurrentDate())
					      +"\n\nTo,\n"						      
					      +userVO.getUserDesign()
					      + "\n\nSubject: Request for issue of Permanent Registration Number (PRN)."
			              + "\n\nSir/Madam,"
					      + "\n\nKindly refer to your application dated : "+piaDetailVO.getCreatedOn()+" for issue of Permanent Registration Number (PRN). Your application has been processed and following are your PRN details for Deen Dayal Upadhyaya Grameen Kaushalya Yojana (DDU-GKY):"
					      + "\n\nPRN Details:"
					      + "\n(1) Permanent Registration Number: " +userVO.getLoginId()						      
					      + "\n(2)PIA Name : " + piaDetailVO.getPiaName()
					      + "\n(3)Pan No : " + piaDetailVO.getPanNo() 
					      + "\n(4)Tan No : " + piaDetailVO.getTanNo() 
					      + "\n(5)Registration Number : " +piaDetailVO.getRegistrationNumber()
					      + "\n(6)Legal Status : " +piaDetailVO.getCategoryName()
					      + "\n(7)Address : " +piaDetailVO.getAddress()
					      + "\n(8)Email : " + userVO.getEmailId().toLowerCase() 
					      + "\n\nAuthorized Person Details:"
					      + "\n(1)Name : " + authorizedPersonDetail.getMemberName()
					      + "\n(2)Contact No :" + authorizedPersonDetail.getContact()
					      + "\n(3)Email-id : " + authorizedPersonDetail.getEmail().toLowerCase()
					      
					      + "\n\n You can log on to our website i.e. www.ddugky.gov.in to view PRN details by using below credentials."
					      +"\nUser Id:"+userVO.getLoginId()
					      +"\nPassword:"+password
					      
					      
					      + "\n\nIt is also advised to quote your PRN in all correspondence on any matter relating to DDU-GKY."
					     
						  + "\n\nImportant Note :"
						  + "\n i. If any discrepancies are noticed or you want to update the PRN details, a request for correction of such discrepancies/ details  may be sent to helpdeskprn@gmail.com"
					      + "\n ii. After activation of your account in ERP you will receive an email from no-reply@ruralskills.net . There after you will be able to log in to the http://ruralskills.in. Using your PRN as user ID and default password i.e. p326@MORD. (Note: Please change this Password on first login for future purpose.)"
					      + "\n iii. Before applying for new project, it is suggested to go through the Notification number 28/2015, 69/ 2015 and User manual, placed under resources section of the http://ddugky.gov.in  website. You can also find these by accessing below links."

						  + "\n\n Notification No. 28/2015:"+"\n\thttp://ddugky.gov.in/ddugky/DocumentsForDownload/Notifications/2015/Notification_No_28-2015.pdf"
						  +	"\n Notification No. 69/2015:"+ "\n\thttp://ddugky.gov.in/ddugky/DocumentsForDownload/Notifications/2015/Notification_No_69-2015.pdf"
						  +	"\n User Manual:"+ "\n\thttp://ddugky.gov.in/ddugky/DocumentsForDownload/Notifications/2015/Project_App_User_Manual.pdf "
					      
						  + "\n\n iv. Use of Helpdesk "
						  + "\na.) For issues related to the project applications and PFMS related please visit http://52.25.37.223:4040/trac/MrigsSSP to raise a Helpdesk ticket. (The detailed step by step process for raising the issue is provided in the user manual)"
						  + "\nb.) For issues related to the Helpdesk login, please write a mail to helpdesk@ruralskills.in with your PRN details i.e. Number, Name of Organisation and issues in detail."
					      
					      + "\n\n\n\t\t\t\t\t\t\t\t\t\t\tUnder Secretary to the Government of India"
					      + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRural Skills Division"
					      + "\n\nDISCLAIMER:  This is an Auto Generated Mail and signature is not required.  This PRN does not empanel you to sanction projects under DDU-GKY program. The sanction of project is subject to appraisal process  followed by DDU-GKY.";
				
				
				String body1 ="\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDated:"+(new DateUtil().getCurrentDate())
					      +"\n\nTo,\n"						      
					      +userVO.getUserDesign()
					      + "\n\nSubject: Request for issue of Permanent Registration Number (PRN)."
			              + "\n\nSir/Madam,"
					      + "\n\nKindly refer to your application dated : "+piaDetailVO.getCreatedOn()+" for issue of Permanent Registration Number (PRN). Your application has been processed and following are your PRN details for Deen Dayal Upadhyaya Grameen Kaushalya Yojana (DDU-GKY):"
					      + "\n\nPRN Details:"
					      + "\n(1) Permanent Registration Number: " +userVO.getLoginId()						      
					      + "\n(2)PIA Name : " + piaDetailVO.getPiaName()
					      + "\n(3)Pan No : " + piaDetailVO.getPanNo() 
					      + "\n(4)Tan No : " + piaDetailVO.getTanNo() 
					      + "\n(5)Registration Number : " +piaDetailVO.getRegistrationNumber()
					      + "\n(6)Legal Status : " +piaDetailVO.getCategoryName()
					      + "\n(7)Address : " +piaDetailVO.getAddress()
					      + "\n(8)Email : " + userVO.getEmailId().toLowerCase() 
					      + "\n\nAuthorized Person Details:"
					      + "\n(1)Name : " + authorizedPersonDetail.getMemberName()
					      + "\n(2)Contact No :" + authorizedPersonDetail.getContact()
					      + "\n(3)Email-id : " + authorizedPersonDetail.getEmail().toLowerCase()
					      
					      + "\n\n You can log on to our website i.e. www.ddugky.gov.in to view PRN details by using below credentials."
					      +"\nUser Id:"+userVO.getLoginId()
					      +"\nPassword:XXXXXXXXXX "
					      
					      
					      + "\n\nIt is also advised to quote your PRN in all correspondence on any matter relating to DDU-GKY."
					     
						  + "\n\nImportant Note :"
						  + "\n i. If any discrepancies are noticed or you want to update the PRN details, a request for correction of such discrepancies/ details  may be sent to helpdeskprn@gmail.com"
					      + "\n ii. After activation of your account in ERP you will receive an email from no-reply@ruralskills.net . There after you will be able to log in to the http://ruralskills.in. Using your PRN as user ID and default password i.e. p326@MORD. (Note: Please change this Password on first login for future purpose.)"
					      + "\n iii. Before applying for new project, it is suggested to go through the Notification number 28/2015, 69/ 2015 and User manual, placed under resources section of the http://ddugky.gov.in  website. You can also find these by accessing below links."

						  + "\n\n Notification No. 28/2015:"+"\n\thttp://ddugky.gov.in/ddugky/DocumentsForDownload/Notifications/2015/Notification_No_28-2015.pdf"
						  +	"\n Notification No. 69/2015:"+ "\n\thttp://ddugky.gov.in/ddugky/DocumentsForDownload/Notifications/2015/Notification_No_69-2015.pdf"
						  +	"\n User Manual:"+ "\n\thttp://ddugky.gov.in/ddugky/DocumentsForDownload/Notifications/2015/Project_App_User_Manual.pdf "
					      
						  + "\n\n iv. Use of Helpdesk "
						  + "\na.) For issues related to the project applications and PFMS related please visit http://52.25.37.223:4040/trac/MrigsSSP to raise a Helpdesk ticket. (The detailed step by step process for raising the issue is provided in the user manual)"
						  + "\nb.) For issues related to the Helpdesk login, please write a mail to helpdesk@ruralskills.in with your PRN details i.e. Number, Name of Organisation and issues in detail."
					      
					      + "\n\n\n\t\t\t\t\t\t\t\t\t\t\tUnder Secretary to the Government of India"
					      + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRural Skills Division"
					      + "\n\nDISCLAIMER:  This is an Auto Generated Mail and signature is not required.  This PRN does not empanel you to sanction projects under DDU-GKY program. The sanction of project is subject to appraisal process  followed by DDU-GKY.";
			
				
				SendMail sendMail = new SendMail();
				sendMail.sendMailWithOutPassword(userVO.getEmailId(), "Subject: Request for issue of Permanent Registration Number (PRN).", body);
				sendMail.sendMailWithOutPassword("prnddugky@gmail.com", "Subject: Request for issue of Permanent Registration Number (PRN).", body1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	public boolean piaRejected(PiaDetailVO piaDetailVO,String userId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String date = new DateUtil().getCurrentDate();
		String piaCode = piaDetailVO.getPiaCode();
		String remark = piaDetailVO.getRemark();
		String currentDate = new DateUtil().getCurrentDate();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setModifyBy(userId);
				piaDetailVO.setModifyOn(currentDate);
				piaDetailVO.setPiaStatus(Constants.PIA_REJECTED);
			}
			piaDetailVO.setRemark(remark);
			session.update(piaDetailVO);
			tr.commit();
			flag = true;
			if (flag) {
				/*String body = "Dear " + piaDetailVO.getPiaName() + "\n"
						+ " \n Your application is rejected because of" + " \n"
						+ piaDetailVO.getRemark()
						+ " \n\n DISCLAIMER : This is an AutoGenerated Mail.";*/
				String body = "To,\n"
					      +piaDetailVO.getPiaName()
					      + "\n\nSubject: Request for issue of Permanent Registration Number (PRN)."
			              + "\n\nSir/Madam,"
					      + "\n\nKindly refer to your application dated : "+piaDetailVO.getCreatedOn()+" for issue of Permanent Registration Number (PRN). Your application has been rejected for the following reason/(s):"
				          + "\n\n "+ piaDetailVO.getRemark()
				          + "\n\n	The Temporary Reference Number (TRN) allotted to you is closed. You may apply again for obtaining Permanent Registration Number (PRN) after ensuring necessary corrections. "
			              + "\n\n	In case of any grievance against this order, the applicant may send a request stating the grievance to DS (Skills), Rural Skills Division, Ministry of Rural Development at helpdeskprn@gmail.com." 
			              + "\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tUnder Secretary to the Government of India"
					      + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRural Skills Division"
					      + "\n\nDISCLAIMER: This is an Auto Generated Mail and signature is not required.";
			    SendMail sendMail = new SendMail();
				sendMail.sendMail(
						piaDetailVO.getEmail(),
						"Subject: Incomplete application of Permanent Registration Number (PRN).",
						body);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<PiaDetailVO> getPiaListForDocsCheck() {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.add(Restrictions.eq("piaStatus", Constants.PIA_SUBMIT));
			piaList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;

	}

	public PiaDetailVO getDetailofPia(String piaCode) {
		PiaDetailVO piaDetail = new PiaDetailVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class).add(
					Restrictions.eq("piaCode", piaCode));
			piaDetail = (PiaDetailVO) crit.list().get(0);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaDetail;

	}

	@Override
	public List<PiaMemberDetailVO> getPiaMemberDetail(String piaCode) {
		List<PiaMemberDetailVO> piaMemberDetailList = new ArrayList<PiaMemberDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaMemberDetailVO.class)
					.add(Restrictions.eq("piaCode", piaCode));
			piaMemberDetailList = (List<PiaMemberDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaMemberDetailList;
	}

	/**
	 * for genrating md5 password
	 * 
	 * @param input
	 * @return
	 */
	public static String md5(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());
			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public List outerReportCount() {
		List showList = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		/*String sql_STATEMENT = " select count(*) as filed_application,"
				+ " (select count(*) from pia_detail where pia_status = ? ) as PRNAlotted, "
				+ " (select count(*) from pia_detail where pia_status = ? OR pia_status = ? ) as waitForPRN, "
				+ " (select count(*) from pia_detail where pia_status = ? ) as incompleteApp, "
				+ " (select count(*) from pia_detail where pia_status = ? ) as rejectApp"
				+ " from pia_detail ";*/
		String sql_STATEMENT= " select distinct "
				            + " (select count(*) from pia_detail where pia_status in (?,?,?,?,?,?,?,?) )as filed_application, " 
				            + " (select count(*) from pia_detail where pia_status = ? ) as PRNAlotted, "
				            + " (select count(*) from pia_detail where pia_status = ? ) as rejectApp, "
				            + " (select count(*) from pia_detail where pia_status = ? ) as pendingApp, "
				            + " (select count(*) from pia_detail where pia_status = ? ) as withdrawnApp, "
				            + " (select count(*) from pia_detail where pia_status = ? ) as debarredApp, "
				            + " (select count(*) from pia_detail where pia_status = ? ) as blacklistedApp "
				            + " from pia_detail " ; 
		try {
			tr.begin();
			Query query = session.createSQLQuery(sql_STATEMENT);
			query.setParameter(0, Constants.PIA_VERIFIED);
			query.setParameter(1, Constants.PIA_SUBMIT);
			query.setParameter(2, Constants.PIA_DOCS_CHECKED);
			query.setParameter(3, Constants.PIA_REJECTED);
			query.setParameter(4, Constants.PIA_WITHDRAWN);
			query.setParameter(5, Constants.PIA_DEBARRED);
			query.setParameter(6, Constants.PIA_BLACKLISTED);
			query.setParameter(7, Constants.PIA_RECOMMENDED_FOR_REJECTION);
			
			query.setParameter(8, Constants.PIA_VERIFIED);
			query.setParameter(9, Constants.PIA_REJECTED);
			query.setParameter(10, Constants.PIA_SUBMIT);
			query.setParameter(11, Constants.PIA_WITHDRAWN);
			query.setParameter(12, Constants.PIA_DEBARRED);
			query.setParameter(13, Constants.PIA_BLACKLISTED);
			
			showList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return showList;
	}

	@Override
	public List<PiaDetailVO> getPiaListForFinalVerification() {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			/*crit.add(Restrictions.eq("piaStatus", Constants.PIA_DOCS_CHECKED));*/
			crit.add(Restrictions.or(Restrictions.eq("piaStatus", Constants.PIA_DOCS_CHECKED), Restrictions.eq("piaStatus", Constants.PIA_RECOMMENDED_FOR_REJECTION)));
			piaList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;

	}

	/**
	 * Used to 1st stage checked
	 */
	@Override
	public boolean piaDocumentsChecked(PiaDetailVO piaDetailVO,String UserId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String date = new DateUtil().getCurrentDate();
		String piaCode = piaDetailVO.getPiaCode();
		String currentDate = new DateUtil().getCurrentDate();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setModifyBy(UserId);
				piaDetailVO.setModifyOn(currentDate);
				piaDetailVO.setPiaStatus(Constants.PIA_DOCS_CHECKED);
			}
			session.update(piaDetailVO);
			tr.commit();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	/**
	 * Used to 1st stage rejection
	 */
	@Override
	public boolean piaDocumentsRecommendedForRejection(PiaDetailVO piaDetailVO,String userId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String date = new DateUtil().getCurrentDate();
		String piaCode = piaDetailVO.getPiaCode();
		String L1remark = piaDetailVO.getRemark();
		String currentDate = new DateUtil().getCurrentDate();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setModifyBy(userId);
				piaDetailVO.setModifyOn(currentDate);
				piaDetailVO.setPiaStatus(Constants.PIA_RECOMMENDED_FOR_REJECTION);
			}
			piaDetailVO.setL1Remark(L1remark);
			session.update(piaDetailVO);
			tr.commit();
			flag = true;
			
			/*if (flag) {
				String body = "Dear "
						+ piaDetailVO.getPiaName()
						+ "\n"
						+ " \n Your application was found incomplete during documentation checking. Please modify the document/data mentioned in the remark."
						+ " \n\n REMARK: " + piaDetailVO.getRemark()
						+ " \n\n DISCLAIMER : This is an AutoGenerated Mail.";
				SendMail sendMail = new SendMail();
				sendMail.sendMail(
						piaDetailVO.getEmail(),
						"PIA Registration regarding from Aajeevika Skills Division",
						body);
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public List<PiaDetailVO> getPiaListForIndexPage(String reportType) {

		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.addOrder(Order.asc("piaName"));
			if (reportType.equals("PSV")) {
				   /*crit.add( Restrictions.ne("piaStatus", Constants.PIA_PENDING));*/
				   crit.add(Restrictions.and((Restrictions.ne("piaStatus",
							Constants.PIA_PENDING)), (Restrictions.ne("piaStatus",
							Constants.PIA_DEEMED_REJECT))));
			}
			else if (reportType.equals("V")) {
				crit.add(Restrictions.eq("piaStatus", Constants.PIA_VERIFIED));
			}
			else if (reportType.equals("R")) {
				crit.add(Restrictions.eq("piaStatus", Constants.PIA_REJECTED));
			}
			else if (reportType.equals("S")) {
				crit.add(Restrictions.eq("piaStatus", Constants.PIA_SUBMIT));
				/*crit.add(Restrictions.or((Restrictions.eq("piaStatus",
						Constants.PIA_SUBMIT)), (Restrictions.eq("piaStatus",
						Constants.PIA_DOCS_CHECKED))));*/
			} else if (reportType.equals("PW")) {
				crit.add(Restrictions.eq("piaStatus", Constants.PIA_WITHDRAWN));
			}
			else if (reportType.equals("PD")) {
				crit.add(Restrictions.eq("piaStatus", Constants.PIA_DEBARRED));
			}
			else if (reportType.equals("PB")) {
				crit.add(Restrictions.eq("piaStatus", Constants.PIA_BLACKLISTED));
			}
			piaList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;
	}

	/*public List<PiaDetailVO> getPiaListForLoginPage(PiaDetailVO piaDetailVO) {

		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			if (piaDetailVO.getPiaStatus().equalsIgnoreCase("A")) {
				String sql_STATEMENT1 = "SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, pm.name, pm.contact, pm.email "
						+ " FROM pia_detail AS pd, pia_members_detail AS pm "
						+ " WHERE pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' "
						+ " order by pd.pia_name ";

				Query query = session.createSQLQuery(sql_STATEMENT1);
				piaList = query.list();
			} else {
				String sql_STATEMENT = "SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, pm.name, pm.contact, pm.email "
						+ " FROM pia_detail AS pd, pia_members_detail AS pm "
						+ " WHERE pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' AND pd.pia_status= ? "
						+ " order by pd.pia_name ";

				Query query = session.createSQLQuery(sql_STATEMENT);
				query.setParameter(0, piaDetailVO.getPiaStatus());
				piaList = query.list();
			}

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return piaList;
	}*/
	public List<PiaDetailVO> getPiaListForLoginPage(PiaDetailVO piaDetailVO) {

		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			if (piaDetailVO.getPiaStatus().equalsIgnoreCase("A")) {
				/*String sql_STATEMENT1 = "SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, pm.name, pm.contact, pm.email "
						+ " FROM pia_detail AS pd, pia_members_detail AS pm "
						+ " WHERE pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' "
						+ " order by pd.pia_name ";*/
				
				String sql_STATEMENT1 = " SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, "+
				                        " array_to_string((coalesce(array_agg((select  pm.name  "+
				                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
					                    " where authorized_person_status = 'yes' "+
					                    " group by  pia_code "+
					                    " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' ) "+
					                    " FROM pia_detail AS pd "+
					                    " group by pd.pia_code, pd.pia_name, pd.address, pd.office_phone, mail "+
					                    " order by pd.pia_name ";
				Query query = session.createSQLQuery(sql_STATEMENT1);
				piaList = query.list();
			}
			else if (piaDetailVO.getPiaStatus().equalsIgnoreCase("P"))
			{
				String sql_STATEMENT1 = " SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, "+
                                        " array_to_string((coalesce(array_agg((select  pm.name  "+
                                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
                                        " where authorized_person_status = 'yes' "+
                                        " group by  pia_code "+
                                        " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' )), null)),',' ) "+
                                        " FROM pia_detail AS pd "+
                                        " WHERE pd.pia_status= ? "+
                                        " group by pd.pia_code, pd.pia_name, pd.address, pd.office_phone, mail "+
                                        " order by pd.pia_name ";
                Query query = session.createSQLQuery(sql_STATEMENT1);
                query.setParameter(0, piaDetailVO.getPiaStatus());
				piaList = query.list();
			}		
			
			else {
				String sql_STATEMENT =   "SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, pm.name, pm.contact, pm.email "
						               + " FROM pia_detail AS pd, pia_members_detail AS pm "
						               + " WHERE pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' AND pd.pia_status= ? "
						               + " order by pd.pia_name ";

				Query query = session.createSQLQuery(sql_STATEMENT);
				query.setParameter(0, piaDetailVO.getPiaStatus());
				piaList = query.list();
			}

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return piaList;
	}
	public List<PiaDetailVO> getPiaListForLoginPageInner(PiaDetailVO piaDetailVO) {

		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			if (piaDetailVO.getPiaStatus().equalsIgnoreCase("A")) {
				/*String sql_STATEMENT1 = "SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, pm.name, pm.contact, pm.email "
						+ " FROM pia_detail AS pd, pia_members_detail AS pm "
						+ " WHERE pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' "
						+ " order by pd.pia_name ";*/
				
				String sql_STATEMENT1 = " SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, "+
				                        " array_to_string((coalesce(array_agg((select  pm.name  "+
				                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
					                    " where authorized_person_status = 'yes' "+
					                    " group by  pia_code "+
					                    " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' ) AS pname, "+
					                    " array_to_string((coalesce(array_agg((select  pm.contact  "+
				                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
					                    " where authorized_person_status = 'yes' "+
					                    " group by  pia_code "+
					                    " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' ) AS pcontact, "+
					                    " array_to_string((coalesce(array_agg((select  pm.email  "+
				                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
					                    " where authorized_person_status = 'yes' "+
					                    " group by  pia_code "+
					                    " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' )AS pemail "+
					                    " FROM pia_detail AS pd "+
					                    " group by pd.pia_code, pd.pia_name, pd.address, pd.office_phone, mail "+
					                    " order by pd.pia_name ";
				Query query = session.createSQLQuery(sql_STATEMENT1);
				piaList = query.list();
			}
			else if (piaDetailVO.getPiaStatus().equalsIgnoreCase("P"))
			{
				String sql_STATEMENT1 = " SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, "+
                                        " array_to_string((coalesce(array_agg((select  pm.name  "+
                                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
                                        " where authorized_person_status = 'yes' "+
                                        " group by  pia_code "+
                                        " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' )AS pname, "+
                                        " array_to_string((coalesce(array_agg((select  pm.contact  "+
                                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
                                        " where authorized_person_status = 'yes' "+
                                        " group by  pia_code "+
                                        " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' ) AS pcontact, "+
                                        " array_to_string((coalesce(array_agg((select  pm.email  "+
                                        " From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "+
                                        " where authorized_person_status = 'yes' "+
                                        " group by  pia_code "+
                                        " having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes')), null)),',' ) AS pemail "+
                                        " FROM pia_detail AS pd "+
                                        " WHERE pd.pia_status= ? "+
                                        " group by pd.pia_code, pd.pia_name, pd.address, pd.office_phone, mail "+
                                        " order by pd.pia_name ";
                Query query = session.createSQLQuery(sql_STATEMENT1);
                query.setParameter(0, piaDetailVO.getPiaStatus());
				piaList = query.list();
			}		
			
			else {
				String sql_STATEMENT = "SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail, pm.name, pm.contact, pm.email "
						+ " FROM pia_detail AS pd, pia_members_detail AS pm "
						+ " WHERE pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' AND pd.pia_status= ? "
						+ " order by pd.pia_name ";

				Query query = session.createSQLQuery(sql_STATEMENT);
				query.setParameter(0, piaDetailVO.getPiaStatus());
				piaList = query.list();
			}

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return piaList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PiaDetailVO> getIncompletePIAList() {
		List<PiaDetailVO> incompletePIAList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.addOrder(Order.asc("piaName"));
			crit.add(Restrictions.eq("piaStatus", Constants.PIA_PENDING));
			incompletePIAList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return incompletePIAList;
	}
	
	// get list of verified PIAs
	@SuppressWarnings("unchecked")
	@Override
	public List<PiaDetailVO> getVerifiedPIAList() {
		List<PiaDetailVO> incompletePIAList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.add(Restrictions.in("piaStatus", new String[] { Constants.PIA_VERIFIED, Constants.PIA_DOCS_CHECKED, Constants.PIA_SUBMIT, Constants.PIA_PENDING}));
			crit.addOrder(Order.asc("piaName"));			
			incompletePIAList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return incompletePIAList;
	}
	
	@Override
	public List<CategoryVO> getCategory() throws Exception {
		List<CategoryVO> piaCategoryList = new ArrayList<CategoryVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
	
		try {
			tr.begin();
			Criteria crit = session.createCriteria(CategoryVO.class);
			crit.add(Restrictions.eq("categoryStatus", "VALID")).addOrder(Order.asc("categoryId"));
			piaCategoryList = crit.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return piaCategoryList;
	}

	@Override
	public List<ActivityVO> getActivity() throws Exception {
		List<ActivityVO> piaActivityList = new ArrayList<ActivityVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
	
		try {
			tr.begin();
			Criteria crit = session.createCriteria(ActivityVO.class);
			piaActivityList = crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return piaActivityList;	
	}
	
	@Override
	public boolean modifyPia(PiaDetailVO piaDetailVO, List<PiaMemberDetailVO> piaMemberDetailsList, List<PiaActivityMappingVO> piaActivityMappingVOList ,String userId) throws Exception {
		
		log.info("updating "+piaDetailVO.getPiaCode()+"'s Detail");	
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();
		try {
			String currentDate = new DateUtil().getCurrentDate();
			transaction.begin();
			
			piaDetailVO.setModifyBy(userId);
			piaDetailVO.setModifyOn(currentDate);			
			session.update(piaDetailVO);
			
			for(PiaMemberDetailVO piaMemberDetailVO : piaMemberDetailsList){
				piaMemberDetailVO.setPiaCode(piaDetailVO.getPiaCode());
				piaMemberDetailVO.setModifyBy(userId);
				piaMemberDetailVO.setModifyOn(currentDate);
				if(piaMemberDetailVO.getMemberCode()==null || piaMemberDetailVO.getMemberCode().equals("")){
					session.save(piaMemberDetailVO);
				}else{
					session.update(piaMemberDetailVO);
				}
			}
			String Activity_Delete_query = "DELETE FROM pia_activity_mapping WHERE pia_code = :piaCode";
			Query query = session.createSQLQuery(Activity_Delete_query);
			query.setParameter("piaCode", piaDetailVO.getPiaCode()).executeUpdate();
			
			for(PiaActivityMappingVO piaActivityMappingVO : piaActivityMappingVOList){
				piaActivityMappingVO.setPiaCode(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setModifyBy(userId);
				piaActivityMappingVO.setModifyOn(currentDate);
				session.saveOrUpdate(piaActivityMappingVO);
			}
					
			transaction.commit();
		    flag = true;
		    
		} catch (HibernateException e) {
			log.error("PIA Modification Exception in modifyPia method" + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	/**
	 * Method for reverting back pia status(C to S)
	 */
	@Override
	public boolean piaReverted(PiaDetailVO piaDetailVO,String userId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String piaCode = piaDetailVO.getPiaCode();
		String currentDate = new DateUtil().getCurrentDate();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setModifyBy(userId);
				piaDetailVO.setModifyOn(currentDate);
				piaDetailVO.setPiaStatus(Constants.PIA_SUBMIT);
			}
			session.update(piaDetailVO);
			tr.commit();
			flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	
	public List<ContactDetail> getContactDetail() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		List<ContactDetail> contactDetailsList = new ArrayList<ContactDetail>();

		try {

			tr.begin();
			Criteria criteria = session.createCriteria(ContactDetail.class);			
			contactDetailsList = criteria.list();

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return contactDetailsList;
	}
	

	/**
	 * Method for generating zip files of PIAs
	 * @author laxman
	 */
	@Override
	public String generatePIAExcelZipFile() {
		
		log.info("generating zip file of PIAs >>> inside PiaDaoImpl at generatePIAExcelZipFile()");
		File zipDirName = null;
		File zipFolder = null;
		File tmpFolder = null;
		File piaExcelFile = null;
		File memberExcelFile = null;
		try {
			final long tmpNo = Math.round(Math.random() * 100000);
			//final String randomNo = String.valueOf((int)tmpNo);
			final String randomNo = tmpNo+"";
			//create a temp folder and store files in it
			tmpFolder = File.createTempFile("tmpFolder", randomNo);
			tmpFolder.delete();
			tmpFolder.mkdir();
		
			List<PiaDetailVO> piaFiles = this.getPIAsInformation();
			for(PiaDetailVO files: piaFiles){
				File panFile = new File(Constants.LIVE_SERVER_PATH+File.separator+files.getPanFileName());
				if(panFile.exists()){
					FileUtils.copyFileToDirectory(panFile, tmpFolder);
				}
				File tanFile = new File(Constants.LIVE_SERVER_PATH+File.separator+files.getTanFileName());
				if(tanFile.exists()){
					FileUtils.copyFileToDirectory(tanFile, tmpFolder);
				}
				File regFile = new File(Constants.LIVE_SERVER_PATH+File.separator+files.getRegFileName());
				if(regFile.exists()){
					FileUtils.copyFileToDirectory(regFile, tmpFolder);
				}
				File officeFile = new File(Constants.LIVE_SERVER_PATH+File.separator+files.getOfficePhotoFileName());
				if(officeFile.exists()){
					FileUtils.copyFileToDirectory(officeFile, tmpFolder);
				}
			}
			
			List<PiaMemberDetailVO> memberFileList = this.getPIAMembersInformation();
			for(PiaMemberDetailVO memberFiles : memberFileList) {
				File cvFile = new File(Constants.LIVE_SERVER_PATH+File.separator+memberFiles.getCvFileName());
				if(cvFile.exists()){
					FileUtils.copyFileToDirectory(cvFile, tmpFolder);
				}
				File photoFile = new File(Constants.LIVE_SERVER_PATH+File.separator+memberFiles.getPhotoFileName());
				if(photoFile.exists()) {
					FileUtils.copyFileToDirectory(photoFile, tmpFolder);
				}
			}

		
			//PIAs files copied 
			zipFolder = File.createTempFile("zipFolder", randomNo);
			zipFolder.delete();
			zipFolder.mkdir();
		
			//copy temp folder and excel file inside zipfolder 
			piaExcelFile = this.generateExcelForPIAs(false);  
			if(piaExcelFile.exists()){
				FileUtils.copyFileToDirectory(piaExcelFile, zipFolder);
			}
			
			memberExcelFile = this.generateExcelForPIAMembers(false);
			if(memberExcelFile.exists()){
				FileUtils.copyFileToDirectory(memberExcelFile, zipFolder);
			}
			//FileUtils.copyDirectory(tmpFolder, zipFolder);
			FileUtils.copyDirectoryToDirectory(tmpFolder, zipFolder);
		
			//rename temp folder and it's excel files
			for(File folder : zipFolder.listFiles()) {
				if(folder.isDirectory()){
					folder.renameTo(new File(zipFolder + File.separator + "files"));
				} else if(folder.isFile()){
					if(folder.getName().toLowerCase().contains("pia")){
						folder.renameTo(new File(zipFolder + File.separator + "PIAsInformation.xls"));
					} else if(folder.getName().toLowerCase().contains("member")) {
						folder.renameTo(new File(zipFolder + File.separator + "MemberInformation.xls"));
					}
				}
			}
			//delete excel file
			if(piaExcelFile != null){
				piaExcelFile.delete();
			}
			if(memberExcelFile != null) {
				memberExcelFile.delete();
			}
			//PIAs file copied <<<<<<<<<<<<<<<<<<<
			
			//now make zip
			zipDirName = File.createTempFile("PIA_"+randomNo,".zip");
         
			this.zipDirectory(zipFolder, zipDirName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//delete temp files
		try {
			FileUtils.deleteDirectory(tmpFolder);
			FileUtils.deleteDirectory(zipFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipDirName.getAbsolutePath();
	}
	
	 /**
     * This method zips the directory
     * @author laxman
     * @param dir
     * @param zipDirName
     */
    private void zipDirectory(File dir, File zipDirName) {
        try {
        	if(filesListInDir.size() > 0){
        		filesListInDir.clear();
        	}
            populateFilesList(dir);
            //now zip files one by one
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * This method populates all the files in a directory to a List
     * @author laxman
     * @param dir
     * @throws IOException
     */
    private void populateFilesList(File dir) throws IOException {
    	
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else populateFilesList(file);
        }
    }

	/**
	 * Method for generating verified PIAs excel file
	 * @author laxman
	 * @return
	 * @throws IOException 
	 */
	private File generateExcelForPIAs(boolean offlineFlag) throws IOException {

		String[] titles = { "S.No.","PIA Code\n(temporary)", "PIA Status" ,
				"PIA Confirmation Date\n(in case of verified PIAs)", 
				"PIA Application Submission Date","PIA Name", 
				"PIA Permanent Number\n(in case of verified PIAs)",
				"Category of Applicant", "Type of business/Activity",
				"Address", "Contact Detail", "Registration Detail",
				"Additional Details", "Related Documents", "Address of Land/Building", 
				"Freehold/Mortgaged", "PIA Application Rejection Reason"};
		String[] subTitles = { "Address", "State", "District", "Block", "PIN",
				"Office Phone", "Office Fax", "Email", "Website",
				"State where registered", "Registration number",
				"Date of registration", "PAN", "TAN",
				"Details of registration under section 12A",
				"Details of registration under section 80G",
				"Details of registration under FCRA", "PAN", "TAN",
				"Registration No", "Office Photo" };


		int linkType = 0;
		String path = null;
		if(offlineFlag){
			linkType = Hyperlink.LINK_URL;
			path = "http://aajeevikaskills.gov.in/docDownloader?fName=";
		} else {
			linkType = Hyperlink.LINK_FILE;
			path = "files"+File.separator;
		}
		
		long randomNo = Math.round(Math.random() * 100000);
		File excelFile = File.createTempFile("excelFilePia_"+randomNo, ".xls");
		excelFile.deleteOnExit();
		FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
		//boolean xlsx = false;
		Workbook hwb = new HSSFWorkbook();
		Sheet sheet = hwb.createSheet("PIA Details");

		CreationHelper createHelper = hwb.getCreationHelper();
		Hyperlink link;
	
		CellStyle cellStyle = hwb.createCellStyle();
		Font boldFont = hwb.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(boldFont);

		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		sheet.setColumnWidth(1, (15 * 256));
		sheet.setColumnWidth(2, (15 * 256));
		sheet.setColumnWidth(3, (20 * 256));
		sheet.setColumnWidth(4, (15 * 256));
		sheet.setColumnWidth(5, (81 * 256));
		sheet.setColumnWidth(6, (20 * 256));
		sheet.setColumnWidth(7, (79 * 256));
		sheet.setColumnWidth(8, (79 * 256));
		sheet.setColumnWidth(9, (79 * 256));
		sheet.setColumnWidth(10, (18 * 256));
		sheet.setColumnWidth(11, (18 * 256));
		sheet.setColumnWidth(12, (15 * 256));
		sheet.setColumnWidth(13, (15 * 256));
		sheet.setColumnWidth(14, (15 * 256)); /** office phone*/
		sheet.setColumnWidth(15, (12 * 256));
		sheet.setColumnWidth(16, (35 * 256)); /** Email */
		sheet.setColumnWidth(17, (40 * 256));
		sheet.setColumnWidth(18, (15 * 256)); /** state where registered*/
		sheet.setColumnWidth(19, (25 * 256));
		sheet.setColumnWidth(20, (12 * 256)); 
		sheet.setColumnWidth(21, (16 * 256)); /** pan*/
		sheet.setColumnWidth(22, (16 * 256)); /** tan*/
		sheet.setColumnWidth(23, (18 * 256));
		sheet.setColumnWidth(24, (12 * 256));
		sheet.setColumnWidth(25, (18 * 256));
		sheet.setColumnWidth(26, (12 * 256));
		sheet.setColumnWidth(27, (18 * 256));
		sheet.setColumnWidth(28, (12 * 256));
		sheet.setColumnWidth(29, (13 * 256));
		sheet.setColumnWidth(30, (13 * 256));
		sheet.setColumnWidth(31, (13 * 256));
		sheet.setColumnWidth(32, (13 * 256));
		sheet.setColumnWidth(33, (60 * 256));
		sheet.setColumnWidth(34, (20 * 256));
		sheet.setColumnWidth(35, (100 * 256));

		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("D2:D4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("E2:E4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("F2:F4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("G2:G4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("H2:H4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("I2:I4"));
		
		//ADDRESS SECTION
		sheet.addMergedRegion(CellRangeAddress.valueOf("J2:N2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("N3:N4"));
		
		//CONTACT DETAIL
		sheet.addMergedRegion(CellRangeAddress.valueOf("O2:R2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("O3:O4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("P3:P4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Q3:Q4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("R3:R4"));
		
		//REGISTRATION DETAIL
		sheet.addMergedRegion(CellRangeAddress.valueOf("S2:W2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("S3:S4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("T3:T4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("U3:U4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("V3:V4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("W3:W4"));
		
		//ADITIONAL DETAIL
		sheet.addMergedRegion(CellRangeAddress.valueOf("X2:AC2"));
		//SECOND SUB HEADER
		sheet.addMergedRegion(CellRangeAddress.valueOf("X3:Y3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Z3:AA3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AB3:AC3"));
		//THIRD SUB HEADER
		sheet.addMergedRegion(CellRangeAddress.valueOf("X4:X4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Y4:Y4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Z4:Z4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("A4:A4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AB4:AB4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AC4:AC4"));
		
		//RELATED DOCS
		sheet.addMergedRegion(CellRangeAddress.valueOf("AD2:AG2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AD3:AD4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AE3:AE4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AF3:AF4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AG3:AG4"));
		
		//ADDRESS OF LAND/BUILDING

		sheet.addMergedRegion(CellRangeAddress.valueOf("AH2:AH4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AH2:AH4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AI2:AI4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AJ2:AJ4"));

		int rownum = 0;
		try {
			CellStyle style;
			style = hwb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setWrapText(true);
			style.setFont(boldFont);
			Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
			styles.put("header", style);

			Row row = sheet.createRow(rownum++);
			row.setHeightInPoints(40);
			Cell cell = row.createCell(0);
			cell.setCellValue("PIAs INFORMATION");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AJ$1"));

			row = sheet.createRow(rownum++);
			row.setHeightInPoints(25);

			cell = row.createCell(0);
			cell.setCellValue(titles[0]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(1);
			cell.setCellValue(titles[1]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(2);
			cell.setCellValue(titles[2]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(3);
			cell.setCellValue(titles[3]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(4);
			cell.setCellValue(titles[4]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(5);
			cell.setCellValue(titles[5]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(6);
			cell.setCellValue(titles[6]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(7);
			cell.setCellValue(titles[7]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(8);
			cell.setCellValue(titles[8]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(9);
			cell.setCellValue(titles[9]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(14);
			cell.setCellValue(titles[10]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(15);
			cell.setCellValue(titles[11]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(23);
			cell.setCellValue(titles[12]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(29);
			cell.setCellValue(titles[13]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(33);
			cell.setCellValue(titles[14]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(34);
			cell.setCellValue(titles[15]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(35);
			cell.setCellValue(titles[16]);
			cell.setCellStyle(styles.get("header"));

			// second Sub Header
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(25);

			cell = row.createCell(9);
			cell.setCellValue(subTitles[0]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(10);
			cell.setCellValue(subTitles[1]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(11);
			cell.setCellValue(subTitles[2]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(12);
			cell.setCellValue(subTitles[3]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(13);
			cell.setCellValue(subTitles[4]);
			cell.setCellStyle(styles.get("header"));

			// contact details sub header
			cell = row.createCell(14);
			cell.setCellValue(subTitles[5]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(15);
			cell.setCellValue(subTitles[6]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(16);
			cell.setCellValue(subTitles[7]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(17);
			cell.setCellValue(subTitles[8]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(18);
			cell.setCellValue(subTitles[9]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(19);
			cell.setCellValue(subTitles[10]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(20);
			cell.setCellValue(subTitles[11]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(21);
			cell.setCellValue(subTitles[12]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(22);
			cell.setCellValue(subTitles[13]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(23);
			cell.setCellValue(subTitles[14]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(25);
			cell.setCellValue(subTitles[15]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(27);
			cell.setCellValue(subTitles[16]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(29);
			cell.setCellValue(subTitles[17]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(30);
			cell.setCellValue(subTitles[18]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(31);
			cell.setCellValue(subTitles[19]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(32);
			cell.setCellValue(subTitles[20]);
			cell.setCellStyle(styles.get("header"));

			// third row
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(25);

			cell = row.createCell(23);
			cell.setCellValue("Registration Number");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(24);
			cell.setCellValue("Registration Date");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(25);
			cell.setCellValue("Registration Number");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(26);
			cell.setCellValue("Registration Date");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(27);
			cell.setCellValue("Registration Number");
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(28);
			cell.setCellValue("Registration Date");
			cell.setCellStyle(styles.get("header"));

			List<PiaDetailVO> piaDetailList = this.getPIAsInformation();

			int count= 1;
			for (PiaDetailVO piaDetail : piaDetailList) {
				row = sheet.createRow(rownum++);

				cell = row.createCell(0);
				cell.setCellValue(count++);

				cell = row.createCell(1);
				cell.setCellValue(piaDetail.getPiaCode());
				
				cell = row.createCell(2);
				String status = null;
				if(piaDetail.getPiaStatus().equals("V")) {
					status = "Verified";
				} else if(piaDetail.getPiaStatus().equals("S")) {
					status = "Submitted";
				} else if (piaDetail.getPiaStatus().equals("C")) {
					status = "Checked";
				} else if(piaDetail.getPiaStatus().equals("R")) {
					status = "Rejected";
				} else if(piaDetail.getPiaStatus().equals("P")) {
					status = "Incomplete";
				}
				cell.setCellValue(status);
				
				cell = row.createCell(3);
				cell.setCellValue(piaDetail.getPiaConfirmationDate());
				
				cell = row.createCell(4);
				String date  = null;
				if(piaDetail.getCreatedOn() == null || piaDetail.getCreatedOn().equals("")) {
					date = piaDetail.getModifyOn();
				} else {
					date = piaDetail.getCreatedOn();
				}
				cell.setCellValue(date);
				
				cell = row.createCell(5);
				cell.setCellValue(piaDetail.getPiaName());

				cell = row.createCell(6);
				cell.setCellValue(piaDetail.getPiaRegistrationNo());

				cell = row.createCell(7);
				cell.setCellValue(piaDetail.getCategoryName());

				cell = row.createCell(8);
				cell.setCellValue(piaDetail.getActivityName());

				cell = row.createCell(9);
				cell.setCellValue(piaDetail.getAddress());

				cell = row.createCell(10);
				cell.setCellValue(piaDetail.getStateName());

				cell = row.createCell(11);
				cell.setCellValue(piaDetail.getDistrictName());

				cell = row.createCell(12);
				cell.setCellValue(piaDetail.getBlockName());

				cell = row.createCell(13);
				cell.setCellValue(piaDetail.getPin());

				cell = row.createCell(14);
				cell.setCellValue(piaDetail.getOfficePhone());

				cell = row.createCell(15);
				cell.setCellValue(piaDetail.getOfficeFax());

				cell = row.createCell(16);
				cell.setCellValue(piaDetail.getEmail());

				cell = row.createCell(17);
				cell.setCellValue(piaDetail.getWebsite());

				cell = row.createCell(18);
				cell.setCellValue(piaDetail.getRegistrationStateName());

				cell = row.createCell(19);
				cell.setCellValue(piaDetail.getRegistrationNumber());

				cell = row.createCell(20);
				cell.setCellValue(piaDetail.getDateOfRegistration());

				cell = row.createCell(21);
				cell.setCellValue(piaDetail.getPanNo());

				cell = row.createCell(22);
				cell.setCellValue(piaDetail.getTanNo());

				cell = row.createCell(23);
				cell.setCellValue(piaDetail.getRegNoSection12A());

				cell = row.createCell(24);
				cell.setCellValue(piaDetail.getRegDateSection12A());

				cell = row.createCell(25);
				cell.setCellValue(piaDetail.getRegNoSection80G());

				cell = row.createCell(26);
				cell.setCellValue(piaDetail.getRegDateSection80G());

				cell = row.createCell(27);
				cell.setCellValue(piaDetail.getRegNoFCRA());

				cell = row.createCell(28);
				cell.setCellValue(piaDetail.getRegDateFCRA());

				cell = row.createCell(29);
				if(piaDetail.getPanFileName().equals("")){
					cell.setCellValue("File Not Found");
				} else{
					link = createHelper.createHyperlink(linkType);
					link.setAddress(path+ piaDetail.getPanFileName());
					cell.setCellValue("View");
					cell.setHyperlink(link);
				}

				cell = row.createCell(30);
				if(piaDetail.getTanFileName().equals("")) {
					cell.setCellValue("File Not Found");
				} else {
					link = createHelper.createHyperlink(Hyperlink.LINK_FILE);
					link.setAddress(path+ piaDetail.getTanFileName());
					cell.setCellValue("View");
					cell.setHyperlink(link);
				}

				cell = row.createCell(31);
				if(piaDetail.getRegFileName().equals("")){
					cell.setCellValue("File Not Found");
				} else {
					link = createHelper.createHyperlink(linkType);
					link.setAddress(path+ piaDetail.getRegFileName());
					cell.setCellValue("View");
					cell.setHyperlink(link);
				}

				cell = row.createCell(32);
				if(piaDetail.getOfficePhotoFileName().equals("")) {
					cell.setCellValue("File Not Found");
				} else {
					link = createHelper.createHyperlink(linkType);
					link.setAddress(path+ piaDetail.getOfficePhotoFileName());
					cell.setCellValue("View");
					cell.setHyperlink(link);
				}
				
				cell = row.createCell(33);
				cell.setCellValue(piaDetail.getAddressLandBuilding());
				
				cell = row.createCell(34);
				cell.setCellValue(piaDetail.getFreeholdMortgaged());
				
				cell = row.createCell(35);
				if(piaDetail.getPiaStatus().equals("P") || piaDetail.getPiaStatus().equals("R")){
					cell.setCellValue(piaDetail.getRemark());
				}
				
			}
			hwb.write(fileOutputStream);
		} catch (Exception e) {
			log.error("While generating PIAs excel");
			e.printStackTrace();
		}
		return excelFile;
	}
	
	/**
	 * Method for generating Members excel
	 * @author laxman
	 * @param dType
	 * @return
	 * @throws IOException
	 */
	private File generateExcelForPIAMembers(boolean offlineFlag) throws IOException {

		String[] titles = { "Sr. No.", "PIA Code", "Member Name",
				"NRI Status", "Authorized Person","Designation",
				"Contact","Email-Id", "PAN No.", "Aadhar/Voter No.",
				"Passport/Driving License No.", "Country",
				"Passport Valid till","Visa Valid till",
				"Work Permit Valid till",
				"Whether a Valid Work Permit Held in India",
				"Whether clearance from FRA, MHA obtained",
				"Relative Name","Age","Occupation",
				"Address","Post-Office","Police Station","State Name",
				"Liability","CV","Photo"};

		//create tmp file for excel
		
		int linkType = 0;
		String path = null;
		if(offlineFlag){
			linkType = Hyperlink.LINK_URL;
			path = "http://aajeevikaskills.gov.in/docDownloader?fName=";
		} else {
			linkType = Hyperlink.LINK_FILE;
			path = "files"+File.separator;
		}
		
		long randomNo = Math.round(Math.random() * 100000);
		File excelFile = File.createTempFile("excelFileMember_"+randomNo, ".xls");
		excelFile.deleteOnExit();
		FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
		//boolean xlsx = false;
		Workbook hwb = new HSSFWorkbook();
		Sheet sheet = hwb.createSheet("PIA Members Details");

		CreationHelper createHelper = hwb.getCreationHelper();
		Hyperlink link;

		CellStyle cellStyle = hwb.createCellStyle();
		Font boldFont = hwb.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(boldFont);

		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		sheet.setColumnWidth(0, (7 * 256));
		sheet.setColumnWidth(1, (10 * 256));
		sheet.setColumnWidth(2, (30 * 256));
		sheet.setColumnWidth(3, (15 * 256));
		sheet.setColumnWidth(4, (12 * 256));
		sheet.setColumnWidth(5, (25 * 256));
		sheet.setColumnWidth(6, (15 * 256));
		sheet.setColumnWidth(7, (35 * 256));
		sheet.setColumnWidth(8, (15 * 256));
		sheet.setColumnWidth(9, (15 * 256));
		sheet.setColumnWidth(10, (25 * 256));
		sheet.setColumnWidth(11, (20 * 256));
		sheet.setColumnWidth(12, (15 * 256));
		sheet.setColumnWidth(13, (15 * 256));
		sheet.setColumnWidth(14, (15 * 256));
		sheet.setColumnWidth(15, (15 * 256));
		sheet.setColumnWidth(16, (18 * 256));
		sheet.setColumnWidth(17, (30 * 256));
		sheet.setColumnWidth(18, (5 * 256));
		sheet.setColumnWidth(19, (20 * 256));
		sheet.setColumnWidth(20, (90 * 256)); /** address */
		sheet.setColumnWidth(21, (20 * 256));
		sheet.setColumnWidth(22, (20 * 256));
		sheet.setColumnWidth(23, (15 * 256));
		sheet.setColumnWidth(24, (10 * 256));
		sheet.setColumnWidth(25, (10 * 256));
		sheet.setColumnWidth(26, (10 * 256));

		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("D2:D3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("E2:E3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("F2:F3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("G2:G3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("H2:H3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("I2:I3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("J2:J3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("K2:K3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("L2:L3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("M2:M3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("N2:N3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("O2:O3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("P2:P3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Q2:Q3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("R2:R3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("S2:S3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("T2:T3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("U2:U3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("V2:V3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("W2:W3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("X2:X3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Y2:Y3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Z2:Z3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AA2:AA3"));

		int rownum = 0;
		try {
			CellStyle style;
			style = hwb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setFont(boldFont);
			style.setWrapText(true);
			Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
			styles.put("header", style);

			Row row = sheet.createRow(rownum++);
			row.setHeightInPoints(30);
			Cell cell = row.createCell(0);
			cell.setCellValue("PIA MEMBERS INFORMATION");
			cell.setCellStyle(styles.get("header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AA$1"));

			row = sheet.createRow(rownum++);
			row.setHeightInPoints(32);

			cell = row.createCell(0);
			cell.setCellValue(titles[0]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(1);
			cell.setCellValue(titles[1]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(2);
			cell.setCellValue(titles[2]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(3);
			cell.setCellValue(titles[3]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(4);
			cell.setCellValue(titles[4]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(5);
			cell.setCellValue(titles[5]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(6);
			cell.setCellValue(titles[6]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(7);
			cell.setCellValue(titles[7]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(8);
			cell.setCellValue(titles[8]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(9);
			cell.setCellValue(titles[9]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(10);
			cell.setCellValue(titles[10]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(11);
			cell.setCellValue(titles[11]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(12);
			cell.setCellValue(titles[12]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(13);
			cell.setCellValue(titles[13]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(14);
			cell.setCellValue(titles[14]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(15);
			cell.setCellValue(titles[15]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(16);
			cell.setCellValue(titles[16]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(17);
			cell.setCellValue(titles[17]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(18);
			cell.setCellValue(titles[18]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(19);
			cell.setCellValue(titles[19]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(20);
			cell.setCellValue(titles[20]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(21);
			cell.setCellValue(titles[21]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(22);
			cell.setCellValue(titles[22]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(23);
			cell.setCellValue(titles[23]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(24);
			cell.setCellValue(titles[24]);
			cell.setCellStyle(styles.get("header"));

			cell = row.createCell(25);
			cell.setCellValue(titles[25]);
			cell.setCellStyle(styles.get("header"));
			
			cell = row.createCell(26);
			cell.setCellValue(titles[26]);
			cell.setCellStyle(styles.get("header"));

			List<PiaMemberDetailVO> memberDetailList = this.getPIAMembersInformation();
			
			HashMap<String, String> stateListMap = new HashMap<String, String>();
			List<StateVO> stateList = this.getStateListWithCodes();
			for(StateVO stateVO : stateList){
				stateListMap.put(stateVO.getStateCode(), stateVO.getStateName());
			}

			rownum++;
			int count= 1;
			for (PiaMemberDetailVO memberDetailVO  : memberDetailList) {
				row = sheet.createRow(rownum++);

				cell = row.createCell(0);
				cell.setCellValue(count++);

				cell = row.createCell(1);
				cell.setCellValue(memberDetailVO.getPiaCode());

				cell = row.createCell(2);
				cell.setCellValue(memberDetailVO.getMemberName());

				cell = row.createCell(3);
				cell.setCellValue(memberDetailVO.getNriStatus());
				
				cell = row.createCell(4);
				cell.setCellValue(memberDetailVO.getAuthorizedPerson());
				
				cell = row.createCell(5);
				cell.setCellValue(memberDetailVO.getDesignation());

				cell = row.createCell(6);
				cell.setCellValue(memberDetailVO.getContact());

				cell = row.createCell(7);
				cell.setCellValue(memberDetailVO.getEmail());

				cell = row.createCell(8);
				cell.setCellValue(memberDetailVO.getPan());

				cell = row.createCell(9);
				cell.setCellValue(memberDetailVO.getAadharVoterNo());

				cell = row.createCell(10);
				cell.setCellValue(memberDetailVO.getPassportDrivingNo());

				cell = row.createCell(11);
				cell.setCellValue(memberDetailVO.getCountry());

				cell = row.createCell(12);
				cell.setCellValue(memberDetailVO.getPassportValidDate());

				cell = row.createCell(13);
				cell.setCellValue(memberDetailVO.getVisaValidDate());

				cell = row.createCell(14);
				cell.setCellValue(memberDetailVO.getWorkingPermitDate());

				cell = row.createCell(15);
				cell.setCellValue(memberDetailVO.getWorkPermit());

				cell = row.createCell(16);
				cell.setCellValue(memberDetailVO.getClearanceFRAMHA());

				cell = row.createCell(17);
				cell.setCellValue(memberDetailVO.getRelativeName());

				cell = row.createCell(18);
				cell.setCellValue(memberDetailVO.getAge());

				cell = row.createCell(19);
				cell.setCellValue(memberDetailVO.getOccupation());

				cell = row.createCell(20);
				cell.setCellValue(memberDetailVO.getAddress());

				cell = row.createCell(21);
				cell.setCellValue(memberDetailVO.getPostOffice());

				cell = row.createCell(22);
				cell.setCellValue(memberDetailVO.getPoliceStation());
				
				cell = row.createCell(23);
				if(memberDetailVO.getStateCode() != null){
					cell.setCellValue(stateListMap.get(memberDetailVO.getStateCode()));
				}

				cell = row.createCell(24);
				cell.setCellValue(memberDetailVO.getLiability());

				cell = row.createCell(25);
				if(memberDetailVO.getCvFileName() != null){
					link = createHelper.createHyperlink(linkType);
					link.setAddress(path+ memberDetailVO.getCvFileName());
					cell.setCellValue("View");
					cell.setHyperlink(link);
				}

				cell = row.createCell(26);
				if(memberDetailVO.getPhotoFileName() != null) {
					link = createHelper.createHyperlink(linkType);
					link.setAddress(path+ memberDetailVO.getPhotoFileName());
					cell.setCellValue("View");
					cell.setHyperlink(link);
				}
	
			}
			hwb.write(fileOutputStream);
		} catch (Exception e) {
			log.error("While generating PIA Members excel");
			e.printStackTrace();
		}
		return excelFile;
	}
	

	
	/**
	 * Method for getting PIAs information
	 * @author laxman
	 * @return
	 */
	private List<PiaDetailVO> getPIAsInformation() {

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		//ArrayList<PiaDetailVO> fileList = new ArrayList<PiaDetailVO>();
		ArrayList<PiaDetailVO> masterPiaDetail = new ArrayList<PiaDetailVO>();

		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.addOrder(Order.desc("piaName"));
			masterPiaDetail = (ArrayList<PiaDetailVO>) criteria.list();
			tr.commit();
			/** set PIA status 'C' to 'S' */
			/*for(PiaDetailVO piaDetailDetail : masterPiaDetail){
				if(piaDetailDetail.getPiaStatus().equals("C")){
					piaDetailDetail.setPiaStatus("S");
				}
				fileList.add(piaDetailDetail);	
			}*/
			/** now sort this list */
			Collections.sort(masterPiaDetail, new Comparator<PiaDetailVO>() {
				@Override
				public int compare(PiaDetailVO pia1, PiaDetailVO pia2){
					return pia1.getPiaStatus().compareTo(pia2.getPiaStatus());
				}
			});
			/** reverse above list because in excel file we want to
			 * show PIA list in descending order of PIA status
			 */
			Collections.reverse(masterPiaDetail);
			

		} catch(Exception exception ){
			tr.rollback();
			log.error("While getting PIA files inside >>>> PiaDaoImpl at getPIAsFiles ");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return masterPiaDetail;
	}

	/**
	 * Method for getting PIA member detail
	 * @author laxman
	 * @param dType (A,S,V)
	 * @return
	 */
	private List<PiaMemberDetailVO> getPIAMembersInformation() {
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<PiaMemberDetailVO> memberList = null;
		
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaMemberDetailVO.class);
			criteria.addOrder(Order.asc("piaCode"));
			memberList = criteria.list();
			tr.commit();

		} catch(Exception exception ){
			tr.rollback();
			log.error("While getting PIA members info inside >>>> PiaDaoImpl at getPIAMembersInformation ");
			exception.printStackTrace();
		}
		finally {
			session.close();
		}
		return memberList;
	}
	
	private List<StateVO> getStateListWithCodes() {
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<StateVO> stateList = null;
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(StateVO.class);
			stateList = criteria.list();
			tr.commit();

		} catch(Exception exception ){
			tr.rollback();
			log.error("While getting state list inside >>>> PiaDaoImpl at getStateListWithCodes ");
			exception.printStackTrace();
		}
		finally {
			session.close();
		}
		return stateList;
		
	}

	/**
	 * Method for generating excel file
	 * @author laxman
	 * @return String >> path of excel file
	 */
	@Override
	public String generatePIAExcelFile(String piaStatus) {
		
		String excelFilePath = null;
		/** first identify that which file user wants to download */
		try {
			if (piaStatus.contains("PE")) {
				// user wants to download PIA's excel files
				excelFilePath = this.generateExcelForPIAs(true).getAbsolutePath();
			} else if(piaStatus.contains("ME")) {
				// user wants to download member's excel files
				excelFilePath = this.generateExcelForPIAMembers(true).getAbsolutePath();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return excelFilePath;
	}
	
	
	/**
	 * PIA(WITHDRAWN-DEBARRED-BLACKLISTED)
	 */
	
	@Override
	public List<PiaDetailVO> getPiaListForFinalRejection() {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			/*crit.add(Restrictions.eq("piaStatus", Constants.PIA_DOCS_CHECKED));*/
			crit.add(Restrictions.eq("piaStatus", Constants.PIA_VERIFIED));
			crit.addOrder(Order.asc("piaCode"));
			piaList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;

	}

	@Override
	public boolean piaReject(PiaDetailVO piaDetailVO,String userId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String date = new DateUtil().getCurrentDate();
		String piaCode = piaDetailVO.getPiaCode();
		String remark = piaDetailVO.getRemark();
		String status=piaDetailVO.getPiaStatus();
		String currentDate = new DateUtil().getCurrentDate();
		String query_sql = "update mst_user set active_flag='B',last_modified_by=:userId,last_modified_on=:date where login_id=:loginId " ;

		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				//System.out.println("get 0="+criteria.list().get(0));
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setModifyBy(userId);
				piaDetailVO.setModifyOn(currentDate);
				if(status.equals("PW")){
					piaDetailVO.setPiaStatus(Constants.PIA_WITHDRAWN);
				}
				else if(status.equals("PD")){
					piaDetailVO.setPiaStatus(Constants.PIA_DEBARRED);
				}
				else if(status.equals("PB")){
					piaDetailVO.setPiaStatus(Constants.PIA_BLACKLISTED);
				}
				
			}
			piaDetailVO.setRemark(remark);
			session.update(piaDetailVO);
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("userId", userId);
			query.setParameter("date", currentDate);
			query.setParameter("loginId", piaDetailVO.getPiaRegistrationNo()).executeUpdate();
			tr.commit();
			flag = true;
			if (flag) {	
				
				String msg=null;
				if(piaDetailVO.getPiaStatus().equals("PW")){
					msg="withdrawn after review" ;
				}
				if(piaDetailVO.getPiaStatus().equals("PD")){
					msg="debarred" ;
				}
				if(piaDetailVO.getPiaStatus().equals("PB")){
					msg="blacklisted" ;
				}
				String body = "Dear " + piaDetailVO.getPiaName() + "\n"
						+ " \n Your application is "+msg+" because of" + " \n"
						+ piaDetailVO.getRemark()
						+ " \n\n DISCLAIMER : This is an AutoGenerated Mail.";
				SendMail sendMail = new SendMail();
				sendMail.sendMail(piaDetailVO.getEmail(),"PIA Registration regarding from DDU-GKY Division",body);
			
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}
	
	
	//to get new category
	
	@Override
	public List<PiaDetailVO> getPiaListForCategoryChange() {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.add(Restrictions.eq("piaStatus", Constants.PIA_VERIFIED));
			crit.add(Restrictions.in("categoryCode",new String[]{"1","3","5","9","10","11"}));
			piaList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;

	}
	
	@Override
	public List<CategoryVO> getRegistrationCategory() throws Exception {
		List<CategoryVO> piaCategoryList = new ArrayList<CategoryVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(CategoryVO.class);
			crit.add(Restrictions.in("categoryId",new String[]{"2","4","6","7","8"}));
			crit.addOrder(Order.asc("categoryId"));
			piaCategoryList = crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaCategoryList;
	}


	@Override
	public String getCategortShortName(String parameter) {
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			String categoryShortName = null;
			String query = " select form_type from pia_category where category_id=:categoryId and category_status='VALID'  ";
			try {
				tr.begin();
				Query queryList = session.createSQLQuery(query);
				queryList.setParameter("categoryId", parameter);
				categoryShortName = (String) queryList.uniqueResult();
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				log.error("Exception while  fund_type_short_name. "
						+ e.getMessage());
			} finally {
				session.close();
			}
			return categoryShortName;		
	}

	@Override
	public String getPrnNumber(String parameter) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String prn = null;
		String query = " select pia_registration_no from pia_detail where pia_code=:piaCode ";
		try {
			tr.begin();
			Query queryList = session.createSQLQuery(query);
			queryList.setParameter("piaCode", parameter);
			prn = (String) queryList.uniqueResult();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			log.error("Exception while  fund_type_short_name. "
					+ e.getMessage());
		} finally {
			session.close();
		}
		return prn;
	}

	@Override
	public boolean modifyPIACategoryAndNumber(PiaDetailVO piaDetailVO,String category,
			String modifiedPrnNumber, String userId, String piaCode) {
			boolean flag = false;
			//String ModifiedCategoryName="";
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			String date = new DateUtil().getCurrentDate();
			String currentDate = new DateUtil().getCurrentDate();
			String SQL_Query = "update pia_detail set pia_registration_no=?, pia_category_code=?, modify_on=?, modify_by=? where pia_code=? and pia_status='V' ";
			try {
				tx.begin();
				Query query = session.createSQLQuery(SQL_Query);
				query.setParameter(0, modifiedPrnNumber);
				query.setParameter(1, category);
				query.setParameter(2, currentDate);
				query.setParameter(3, userId);
				query.setParameter(4, piaCode);
				query.executeUpdate();
				tx.commit();
				flag = true;
				if (flag) {
					/*String body = "Dear " + piaDetailVO.getPiaName() + "\n"
							+ " \n Your Permanent Registration Number has been changed due to category change. " 
							+ " \n New Permanent Registration Number is :"
							+ modifiedPrnNumber
							+ " \n Your New Permanent Registration Number is your userId and"
							+ " their will be no change in your password "
							+ " \n\n DISCLAIMER : This is an Auto Generated Mail. \n";*/
					String body ="To,\n"
							    + piaDetailVO.getPiaName()
					            + "\n\nSubject: Request for change of organisation details in Permanent Registration Number (PRN)."
			                    + "\n\nSir/Madam,"
					            + "\n\nKindly refer to your application for change of organisation details in Permanent Registration Number (PRN). Your application has been processed and following are your revised PRN details for Deen Dayal Upadhyaya Grameen Kaushalya Yojana (DDU-GKY):"
					            + "\n\nPlaced below are the details of applicant organisation submitted in the application form."
							    + "\n\nName : " + piaDetailVO.getPiaName() 
							    + "\nEmail : " + piaDetailVO.getEmail() 
							    + "\nPanNo : " + piaDetailVO.getPanNo() 
							    + "\nTanNo : " + piaDetailVO.getTanNo()
							    + "\nRegistration Number : " +piaDetailVO.getRegistrationNumber()
							    /*+ "\nLegal Status : " +piaDetailVO.getCategoryName()*/
							    + "\nAddress : " +piaDetailVO.getAddress()
							    + "\n\nYour new PRN shall be "+modifiedPrnNumber+" and existing password shall remain same. "
							    + "\n\n\nImportant Note: It is mandatory to quote your PRN while applying for a project under DDU-GKY, including Roshni and Himayat. Project applications will not be accepted if PRN is not quoted in the project application. It is also advised to quote your PRN in correspondence on any matter relating to DDU-GKY for faster processing. "
							    + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tUnder Secretary to the Government of India"
							    + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRural Skills Division"
							    + "\n\nDISCLAIMER: This is an Auto Generated Mail and signature is not required.  If any discrepancies are noticed, a request for correction of such discrepancies may be sent to helpdeskprn@gmail.com .";
				    SendMail sendMail = new SendMail();
					sendMail.sendMail(piaDetailVO.getEmail(),"Request for change of organisation details in Permanent Registration Number (PRN).",body);
				}

			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
			return flag;

	}

	@Override
	public List<PiaDetailVO> getPiaListForIncompletePIARejection() {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sql_STATEMENT = " with allData as ( "
                             + " select * from "
                             + " (select pia_code as piaCode,pia_name as piaName,pia_status, pia_category_code,case when modify_on is not null then modify_on else created_on  end as createdOn,modify_on from "
                             + " (select pia_code,pia_name,pia_status,pia_category_code ,case when char_length(created_on)=10 then to_date(created_on,'dd-mm-yyyy') "
		                     + " when char_length(created_on)=9 then to_date(('0'||created_on),'dd-mm-yyyy') "
		                     + " when char_length(created_on)=8 then to_date(('0'||substring(created_on,1,2)||'0'||substring(created_on,3,8)),'dd-mm-yyyy') end as created_on, "
		     				 + " case when char_length(modify_on)=10 then to_date(modify_on,'dd-mm-yyyy') "
		                     + " when char_length(modify_on)=9 then to_date(('0'||modify_on),'dd-mm-yyyy') "
		                     + " when char_length(modify_on)=8 then to_date(('0'||substring(modify_on,1,2)||'0'||substring(modify_on,3,8)),'dd-mm-yyyy') end as modify_on "
                             + " from pia_detail)s)s  ) "
 
                             + " select piaCode,piaName "
                             + " from allData where (pia_category_code  in ('1','3','5','9','10','11') and pia_status in ('P','S')) or "
                             + " (DATE_PART('day', cast(now() as timestamp) - cast(createdOn as timestamp)) >30 and pia_status in ('P') )  "
                             + " order by piaName ";

		try {
			tr.begin();
			Query query = session.createSQLQuery(sql_STATEMENT).setResultTransformer(Transformers.aliasToBean(PiaDetailVO.class));
			/*Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.add(Restrictions.eq("piaStatus", Constants.PIA_PENDING));
			crit.add(Restrictions.in("categoryCode",new String[]{"1","3","5","9","10","11"}));
			piaList = (List<PiaDetailVO>) crit.list();*/
			piaList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;
	}
	
	
	@Override
	public boolean incompletePIAReject(PiaDetailVO piaDetailVO,String userId) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String date = new DateUtil().getCurrentDate();
		String piaCode = piaDetailVO.getPiaCode();
		String remark = piaDetailVO.getRemark();
		String status=piaDetailVO.getPiaStatus();
		String currentDate = new DateUtil().getCurrentDate();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", piaCode));

			if (piaDetailVO.getPiaCode() != null || !piaCode.equals("")) {
				piaDetailVO = (PiaDetailVO) criteria.list().get(0);
				piaDetailVO.setModifyBy(userId);
				piaDetailVO.setModifyOn(currentDate);
				if(status.equals("R")){
					piaDetailVO.setPiaStatus(Constants.PIA_REJECTED);
				}				
			}
			piaDetailVO.setRemark(remark);
			session.update(piaDetailVO);
			tr.commit();
			flag = true;
			flag = true;
			if (flag) {
				/*String body = "Dear " + piaDetailVO.getPiaName() + "\n"
						+ " \n Your application is rejected because of" + " \n"
						+ piaDetailVO.getRemark()
						+ " \n\n DISCLAIMER : This is an AutoGenerated Mail.";
				SendMail sendMail = new SendMail();
				sendMail.sendMail(piaDetailVO.getEmail(),"PIA Registration regarding from Aajeevika Skills Division",body);*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public List<PiaDetailVO> getMonthOldIncompletePIAListForRejection() {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sql_STATEMENT = " with allData as (  "
				             + " select * from  "
				             + " (select pia_code as piaCode,pia_name as piaName,pia_status, pia_category_code,case when modify_on is not null then modify_on else created_on  end as createdOn,modify_on,email from " 
				             + " (select pia_code,pia_name,pia_status,pia_category_code,email ,case when char_length(created_on)=10 then to_date(created_on,'dd-mm-yyyy') "
				             + " when char_length(created_on)=9 then to_date(('0'||created_on),'dd-mm-yyyy') "
				             + " when char_length(created_on)=8 then to_date(('0'||substring(created_on,1,2)||'0'||substring(created_on,3,8)),'dd-mm-yyyy') end as created_on, "
				             + " case when char_length(modify_on)=10 then to_date(modify_on,'dd-mm-yyyy') "
				             + " when char_length(modify_on)=9 then to_date(('0'||modify_on),'dd-mm-yyyy') "
				             + " when char_length(modify_on)=8 then to_date(('0'||substring(modify_on,1,2)||'0'||substring(modify_on,3,8)),'dd-mm-yyyy') end as modify_on "
				             + " from pia_detail)s)s  )  "
				 
				             + " select piaCode,piaName,email "
				             + " from allData where  " 
				             + " (DATE_PART('day', cast(now() as timestamp) - cast(createdOn as timestamp)) >30 and pia_status in ('P') ) "
				             + " order by piaName  " ; 

		try {
			tr.begin();
			Query query = session.createSQLQuery(sql_STATEMENT).setResultTransformer(Transformers.aliasToBean(PiaDetailVO.class));
			piaList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;
	}

	@Override
	public boolean getMonthOldPIADeemed(PiaDetailVO piaDetailVO) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		Boolean flag = false;
		String piaCode = piaDetailVO.getPiacode();
		String userId=Constants.PIA_DEEMED_REJECT_BY;
		String piaStatus=Constants.PIA_DEEMED_REJECT;
		String remark=" Auto rejection due to pending for a month old incomplete PIAs";
		String currentDate = new DateUtil().getCurrentDate();
		String SQL_Query = "update pia_detail set pia_status=?, remark=?, modify_on=?, modify_by=? where pia_code=? and pia_status='P' ";
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL_Query);
			query.setParameter(0, piaStatus);
			query.setParameter(1, remark);
			query.setParameter(2, currentDate);
			query.setParameter(3, userId);
			query.setParameter(4, piaCode);
			query.executeUpdate();
			tr.commit();
			flag = true;		

		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return flag;

	}

	public List getPiaSearchList(String piaSearchValue) {

		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
		
				
				String sql_STATEMENT1 = " SELECT pd.pia_code, pd.pia_name, pd.address, pd.office_phone, pd.email AS mail,"
						+ "array_to_string((coalesce(array_agg((select  pm.name  "
						+ "From pia_members_detail AS pm WHERE pm.pia_code not in (SELECT pia_code FROM pia_members_detail as md "
						+ "where authorized_person_status = 'yes' "
						+ "group by  pia_code "
						+  "having count(md.pia_code)>1)and pd.pia_code = pm.pia_code AND pm.authorized_person_status='yes' )), null)),',' ) "
						
						+ "FROM pia_detail AS pd  where pd.pia_registration_no='"+piaSearchValue+"' or pd.pia_code='"+piaSearchValue+"' "
						+ "group by pd.pia_code, pd.pia_name, pd.address, pd.office_phone, mail";
						
				Query query = session.createSQLQuery(sql_STATEMENT1);
				piaList = query.list();		
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return piaList;
	}
	
	
	
}
