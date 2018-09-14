package com.infotech.skills.registration.pia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.mail.SendMail;
import com.infotech.skills.hbm.piaprofile.ActivityVO;
import com.infotech.skills.hbm.piaprofile.CategoryVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaprofile.PiaMemberDetailVO;
import com.infotech.skills.util.Constants;

public class RegistrationDaoImpl implements RegistrationDao {
	/**
	 * @since August 2013
	 */
	private static Logger log = Logger.getRootLogger();

	@SuppressWarnings("unchecked")
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
			if (session.isOpen())
				session.close();
		}
		return piaCategoryList;
	}

	@SuppressWarnings("unchecked")
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
			if (session.isOpen())
				session.close();
		}
		return piaActivityList;
	}

	/**
	 * 
	 */
	/*
	 * @Override public boolean save(PiaDetailVO piaDetailVO,
	 * List<PiaActivityMappingVO> piaActivityMappingVOList,
	 * List<PiaMemberDetailVO> piaMemberDetailsList)throws Exception {
	 * log.info("PIA Regisration save dao..."); boolean flag = false; Persister
	 * persister = PersisterImpl.getPersister(); Session session =
	 * persister.getSession(); Transaction transaction =
	 * session.getTransaction();
	 * 
	 * try { String currentDate = new DateUtil().getCurrentDate();
	 * transaction.begin(); piaDetailVO.setPiaStatus("false");
	 * piaDetailVO.setCreatedBy(piaDetailVO.getPiaCode());
	 * piaDetailVO.setCreatedOn(currentDate); session.save(piaDetailVO);
	 * for(PiaActivityMappingVO piaActivityMappingVO :
	 * piaActivityMappingVOList){
	 * piaActivityMappingVO.setPiaCode(piaDetailVO.getPiaCode());
	 * piaActivityMappingVO.setCreatedBy(piaDetailVO.getPiaCode());
	 * piaActivityMappingVO.setCreatedOn(currentDate);
	 * session.save(piaActivityMappingVO); }
	 * 
	 * for(PiaMemberDetailVO piaMemberDetailVO : piaMemberDetailsList){
	 * piaMemberDetailVO.setPiaCode(piaDetailVO.getPiaCode());
	 * piaMemberDetailVO.setCreatedBy(piaDetailVO.getPiaCode());
	 * piaMemberDetailVO.setCreatedOn(currentDate);
	 * session.save(piaMemberDetailVO); }
	 * 
	 * 
	 * transaction.commit(); flag = true;
	 * 
	 * String body = "Greetings from DDU-GKY, \n\n" +
	 * "This is to inform you that your request for "+
	 * piaDetailVO.getPiaName()+" Reference "+ piaDetailVO.getPiaCode()
	 * +", dated "
	 * +piaDetailVO.getCreatedOn()+" successfully received and in process.\n"+
	 * "Kindly Note that upon successful validation of your registration documents you will be allotted permanent registration number.\n"
	 * +
	 * "You can check the Status of your registration at any time by providing  Reference no: "
	 * + piaDetailVO.getPiaCode()
	 * +" to email ID prnddugky@gmail.com . \n\n"+ "Sincerely Yours,\n"+
	 * "DDU-GKY\n\n"+ "CONFIDENTIALITY / DISCLAIMER NOTICE:\n"+
	 * "This e-mail and any attachments may contain confidential and privileged information.\n\n"
	 * +
	 * "If you are not the intended recipient, please notify the sender immediately by return e-mail, delete this e-mail and destroy any copies.\n\n"
	 * +
	 * "Any dissemination or use of this information by a person other than the intended recipient is unauthorized and may be illegal. \n"
	 * +
	 * "The General Civil Aviation Authority or its employees are not responsible for any auto-generated spurious messages (spams) that you may receive from The General Civil Aviation Authority email addresses."
	 * ;
	 * 
	 * SendMail sendMail = new SendMail();
	 * sendMail.sendMail(piaDetailVO.getEmail(), "DDU-GKY ",body);
	 * 
	 * } catch (HibernateException e) {
	 * log.error("PIA Registration Exception in save menthod" + e.getMessage());
	 * throw e; } finally { if (session.isOpen()) session.close(); } return
	 * flag; }
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

	public int getSequenceCodeForFileUpload() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();
		int code = 0;
		String query_sql = "select nextval('pia_code_seq')";
		try {
			transaction.begin();
			Query query = session.createSQLQuery(query_sql);
			code = ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
		return code;
	}

	/**
	 * 
	 */
	@Override
	public PiaDetailVO getPiaDetail(PiaDetailVO piaDetailVO) throws Exception {
		PiaDetailVO piaDetail = new PiaDetailVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class).add(Restrictions.eq("piaCode", piaDetailVO.getPiaCode()));
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

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PiaMemberDetailVO> getPiaMemberDetail(PiaDetailVO piaDetailVO) throws Exception {
		List<PiaMemberDetailVO> piaMemberDetailList = new ArrayList<PiaMemberDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaMemberDetailVO.class).add(Restrictions.eq("piaCode", piaDetailVO.getPiaCode()));
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
	 * Date: 3 September 2013
	 */
	@Override
	public boolean savePartA(PiaDetailVO piaDetailVO, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception {
		log.info("RegistrationDao: savePartA method run...");
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();

		try {
			String currentDate = new DateUtil().getCurrentDate();
			transaction.begin();

			piaDetailVO.setCreatedBy(piaDetailVO.getPiaCode());
			piaDetailVO.setCreatedOn(currentDate);
			piaDetailVO.setPiaStatus(Constants.PIA_PENDING);
			session.save(piaDetailVO);
			for (PiaActivityMappingVO piaActivityMappingVO : piaActivityMappingVOList) {
				piaActivityMappingVO.setPiaCode(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setCreatedBy(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setCreatedOn(currentDate);
				session.save(piaActivityMappingVO);
			}

			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			log.error("RegistrationDao: savePartA method found error." + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePartA(PiaDetailVO piaDetailVO, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception {
		log.info("RegistrationDao: updatePartA method run...");
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();

		try {
			String currentDate = new DateUtil().getCurrentDate();
			transaction.begin();
			piaDetailVO.setCreatedBy(piaDetailVO.getPiaCode());
			piaDetailVO.setCreatedOn(currentDate);
			piaDetailVO.setModifyBy(piaDetailVO.getPiaCode());
			piaDetailVO.setModifyOn(currentDate);
			piaDetailVO.setPiaStatus(Constants.PIA_PENDING);
			/*
			 * PiaDetailVO PiaDetailtemp = null; PiaDetailtemp = (PiaDetailVO)
			 * session.get(PiaDetailVO.class);
			 */
			session.saveOrUpdate(piaDetailVO);

			String Activity_Delete_query = "DELETE FROM pia_activity_mapping WHERE pia_code = :piaCode";
			Query query = session.createSQLQuery(Activity_Delete_query);
			query.setParameter("piaCode", piaDetailVO.getPiaCode()).executeUpdate();

			for (PiaActivityMappingVO piaActivityMappingVO : piaActivityMappingVOList) {
				piaActivityMappingVO.setPiaCode(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setCreatedBy(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setCreatedOn(currentDate);
				session.saveOrUpdate(piaActivityMappingVO);
			}

			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			log.error("RegistrationDao: updatePartA method found error." + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public boolean savePartB(PiaDetailVO piaDetailVO, List<PiaMemberDetailVO> piaMemberDetailsList) throws Exception {
		log.info("PIA Regisration save dao...");
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();

		try {
			String currentDate = new DateUtil().getCurrentDate();
			transaction.begin();
			piaDetailVO.setCreatedBy(piaDetailVO.getPiaCode());
			piaDetailVO.setCreatedOn(currentDate);
			piaDetailVO.setModifyBy(piaDetailVO.getPiaCode());
			piaDetailVO.setModifyOn(currentDate);
			piaDetailVO.setPiaStatus(Constants.PIA_PENDING);
			session.update(piaDetailVO);

			for (PiaMemberDetailVO piaMemberDetailVO : piaMemberDetailsList) {
				piaMemberDetailVO.setPiaCode(piaDetailVO.getPiaCode());
				piaMemberDetailVO.setCreatedBy(piaDetailVO.getPiaCode());
				piaMemberDetailVO.setCreatedOn(currentDate);
				session.save(piaMemberDetailVO);
			}

			transaction.commit();
			flag = true;

			/*
			 * String body = "Greetings from DDU-GKY, \n\n" +
			 * "This is to inform you that your request for "+
			 * piaDetailVO.getPiaName()+" Reference "+ piaDetailVO.getPiaCode()
			 * +", dated "+piaDetailVO.getCreatedOn()+
			 * " successfully received and in process.\n"+
			 * "Kindly Note that upon successful validation of your registration documents you will be allotted permanent registration number.\n"
			 * +
			 * "You can check the Status of your registration at any time by providing  Reference no: "
			 * + piaDetailVO.getPiaCode()
			 * +" to email ID prnddugky@gmail.com . \n\n"+
			 * "Sincerely Yours,\n"+ "DDU-GKY\n\n"+
			 * "CONFIDENTIALITY / DISCLAIMER NOTICE:\n"+
			 * "This e-mail and any attachments may contain confidential and privileged information.\n\n"
			 * +
			 * "If you are not the intended recipient, please notify the sender immediately by return e-mail, delete this e-mail and destroy any copies.\n\n"
			 * +
			 * "Any dissemination or use of this information by a person other than the intended recipient is unauthorized and may be illegal. \n"
			 * +
			 * "The General Civil Aviation Authority or its employees are not responsible for any auto-generated spurious messages (spams) that you may receive from The General Civil Aviation Authority email addresses."
			 * ;
			 * 
			 * SendMail sendMail = new SendMail();
			 * sendMail.sendMail(piaDetailVO.getEmail(),
			 * "DDU-GKY ",body);
			 */

		} catch (HibernateException e) {
			log.error("PIA Registration Exception in save menthod" + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePartB(PiaDetailVO piaDetailVO, List<PiaMemberDetailVO> piaMemberDetailsList, List<PiaActivityMappingVO> piaActivityMappingVOList) throws Exception {
		log.info("PIA Regisration save dao...");
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();

		/* PiaDetailVO piaDetailVOForUpdate = new PiaDetailVO(); */

		try {
			String currentDate = new DateUtil().getCurrentDate();
			transaction.begin();
			/*
			 * Criteria criteria = session.createCriteria(PiaDetailVO.class)
			 * .add( Restrictions.eq("piaCode", piaDetailVO.getPiaCode()));
			 * piaDetailVOForUpdate = (PiaDetailVO) criteria.list().get(0);
			 * piaDetailVOForUpdate
			 * .setRegDateFCRA(piaDetailVO.getRegDateFCRA());
			 * piaDetailVOForUpdate.setRegNoFCRA(piaDetailVO.getRegNoFCRA());
			 * piaDetailVOForUpdate
			 * .setRegDateSection12A(piaDetailVO.getRegDateSection12A());
			 * piaDetailVOForUpdate
			 * .setRegNoSection12A(piaDetailVO.getRegNoSection12A());
			 * piaDetailVOForUpdate
			 * .setRegDateSection80G(piaDetailVO.getRegDateSection80G());
			 * piaDetailVOForUpdate
			 * .setRegNoSection80G(piaDetailVO.getRegNoSection80G());
			 */
			piaDetailVO.setModifyBy(piaDetailVO.getPiaCode());
			piaDetailVO.setModifyOn(currentDate);
			piaDetailVO.setPiaStatus(Constants.PIA_SUBMIT);
			session.update(piaDetailVO);

			for (PiaMemberDetailVO piaMemberDetailVO : piaMemberDetailsList) {
				piaMemberDetailVO.setPiaCode(piaDetailVO.getPiaCode());
				piaMemberDetailVO.setModifyBy(piaDetailVO.getPiaCode());
				piaMemberDetailVO.setModifyOn(currentDate);
				if (piaMemberDetailVO.getMemberCode() == null || piaMemberDetailVO.getMemberCode().equals("")) {
					session.save(piaMemberDetailVO);
				} else {
					session.update(piaMemberDetailVO);
				}

			}
			String Activity_Delete_query = "DELETE FROM pia_activity_mapping WHERE pia_code = :piaCode";
			Query query = session.createSQLQuery(Activity_Delete_query);
			query.setParameter("piaCode", piaDetailVO.getPiaCode()).executeUpdate();

			for (PiaActivityMappingVO piaActivityMappingVO : piaActivityMappingVOList) {
				piaActivityMappingVO.setPiaCode(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setModifyBy(piaDetailVO.getPiaCode());
				piaActivityMappingVO.setModifyOn(currentDate);
				session.saveOrUpdate(piaActivityMappingVO);
			}

			transaction.commit();
			flag = true;

			String body = "Dear Sir/Madam, \n\n"
					+ "This is to inform you that your request for "
					+ piaDetailVO.getPiaName()
					+ " with TRN (Temporary Reference Number) "
					+ piaDetailVO.getPiaCode()
					+ ", dated "
					+ piaDetailVO.getCreatedOn()
					+ " successfully received.\n"
					+ "Kindly note that upon successful validation of your registration details, you will be allotted PRN (Permanent Registration Number).\n"
					+ "You can check Application status online on our website ( http://ddugky.gov.in/prn/ ) under �PRN Application Status� with your Organization Details.\n"
					+ "For any further query/clarification, kindly write email to helpdeskprn@gmail.com.\n\n"
					+ "Sincerely Yours,\nDDU-GKY";

			SendMail sendMail = new SendMail();
			sendMail.sendMail(piaDetailVO.getEmail(), "Acknowledgment of submission of online application for PRN", body);
			System.out.println(body);
		} catch (HibernateException e) {
			log.error("PIA Registration Exception in save menthod" + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public boolean submit(PiaDetailVO piaDetailVO) throws Exception {
		log.info("PIA Regisration final Submit method...");
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();

		try {
			String currentDate = new DateUtil().getCurrentDate();
			transaction.begin();
			Criteria criteria = session.createCriteria(PiaDetailVO.class).add(Restrictions.eq("piaCode", piaDetailVO.getPiaCode()));
			piaDetailVO = (PiaDetailVO) criteria.list().get(0);

			piaDetailVO.setModifyBy(piaDetailVO.getPiaCode());
			piaDetailVO.setModifyOn(currentDate);
			piaDetailVO.setPiaStatus(Constants.PIA_SUBMIT);
			session.update(piaDetailVO);

			transaction.commit();
			flag = true;

			String body = "Dear Sir/Madam, \n\n"
					+ "This is to inform you that your request for "
					+ piaDetailVO.getPiaName()
					+ " with TRN (Temporary Reference Number) "
					+ piaDetailVO.getPiaCode()
					+ ", dated "
					+ piaDetailVO.getCreatedOn()
					+ " successfully received.\n"
					+ "Kindly note that upon successful validation of your registration details, you will be allotted PRN (Permanent Registration Number).\n"
					+ "You can check Application status online on our website ( http://ddugky.gov.in/prn/ ) under �PRN Application Status� with your Organization Details.\n"
					+ "For any further query/clarification, kindly write email to helpdeskprn@gmail.com.\n\n"
					+ "Sincerely Yours,\nDDU-GKY";
			
			SendMail sendMail = new SendMail();
			sendMail.sendMail(piaDetailVO.getEmail(), "Acknowledgment of submission of online application for PRN", body);

		} catch (HibernateException e) {
			log.error("PIA Registration Exception in save menthod" + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public PiaMemberDetailVO getPiaMemberDetailSelected(PiaDetailVO piaDetailVO) throws Exception {
		PiaMemberDetailVO piaMemberDetailList = new PiaMemberDetailVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaMemberDetailVO.class).add(Restrictions.eq("piaCode", piaDetailVO.getPiaCode()));
			crit.add(Restrictions.eq("authorizedPerson", "yes"));
			piaMemberDetailList = (PiaMemberDetailVO) crit.list().get(0);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaMemberDetailList;
	}

	public boolean verifyPIA(PiaDetailVO piaDetailVO) throws Exception {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			int count = 0;
			if (piaDetailVO.getPiaCode() != null && !piaDetailVO.getPiaCode().equals("")) {
				crit.add(Restrictions.eq("piaCode", piaDetailVO.getPiaCode()));
				count++;
			}
			if (piaDetailVO.getRegistrationNumber() != null && !piaDetailVO.getRegistrationNumber().equals("")) {
				crit.add(Restrictions.eq("registrationNumber", piaDetailVO.getRegistrationNumber()));
				count++;
			}
			if (piaDetailVO.getPanNo() != null && !piaDetailVO.getPanNo().equals("")) {
				crit.add(Restrictions.eq("panNo", piaDetailVO.getPanNo()));
				count++;
			}
			if (piaDetailVO.getTanNo() != null && !piaDetailVO.getTanNo().equals("")) {
				crit.add(Restrictions.eq("tanNo", piaDetailVO.getTanNo()));
				count++;
			}

			crit.add(Restrictions.eq("piaStatus", Constants.PIA_PENDING));

			if (crit.list().iterator().hasNext()) {
				if (count == 4)
					flag = true;
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	@Override
	public boolean validatePanTan(PiaDetailVO piaDetailVO) {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			/*
			 * Criterion PAN = null; Criterion TAN = null; int count = 0;
			 * 
			 * if(piaDetailVO.getPanNo()!=null &&
			 * !piaDetailVO.getPanNo().equals("")){ PAN =
			 * Restrictions.eq("panNo", piaDetailVO.getPanNo()); count++; }
			 * if(piaDetailVO.getTanNo()!=null &&
			 * !piaDetailVO.getTanNo().equals("")){ TAN =
			 * Restrictions.eq("tanNo", piaDetailVO.getTanNo()); count++; }
			 * LogicalExpression orExp = Restrictions.or(PAN, TAN); crit.add(
			 * orExp );
			 * 
			 * if(crit.list().iterator().hasNext()){ if(count == 2) flag = true;
			 * }
			 */

			if ((piaDetailVO.getPanNo() != null && !piaDetailVO.getPanNo().equals("")) || (piaDetailVO.getTanNo() != null && !piaDetailVO.getTanNo().equals(""))) {
				crit.add(Restrictions.or(Restrictions.eq("panNo", piaDetailVO.getPanNo()), Restrictions.eq("tanNo", piaDetailVO.getTanNo())));
				crit.add(Restrictions.ne("piaStatus", "R"));
				crit.add(Restrictions.ne("piaStatus", "DR"));
				if (!crit.list().isEmpty()) {
					flag = true;
				}
			}

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	
	//to be removed
	@SuppressWarnings("unchecked")
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
	public boolean deletePiaMembers(List<String> pia_mem_codes_for_delete){
		boolean flag= false;
		Session session = PersisterImpl.getPersister().getSession();
		try{
			session.beginTransaction();
			for(String obj:pia_mem_codes_for_delete){
				PiaMemberDetailVO pmdvo_to_delete = (PiaMemberDetailVO) session.get(PiaMemberDetailVO.class, obj);
				session.delete(pmdvo_to_delete);
			}
			session.getTransaction().commit();
			flag = true;
		}catch (Exception e) {	
			session.getTransaction().rollback();
			e.printStackTrace();	
		}finally {	session.close();	}
		return flag;
	}
	@SuppressWarnings("unchecked")
	public List<String> getPiaMemberCodes(String pia_code){
		List<String> pia_mem_codes = null;
		Session session = PersisterImpl.getPersister().getSession();
		try{
			Criteria criteria = session.createCriteria(PiaMemberDetailVO.class);
			criteria.add(Restrictions.eq("piaCode", pia_code));
			criteria.setProjection(Projections.property("memberCode"));
			pia_mem_codes=criteria.list();
		}catch (Exception e) {	e.printStackTrace();	
		}finally {	session.close();	}
		return pia_mem_codes;
	}
	public boolean addNewPiaMembers(List<PiaMemberDetailVO> pia_mem_list){
		boolean flag= false;
		Session session = PersisterImpl.getPersister().getSession();
		try{
			session.beginTransaction();
			for(PiaMemberDetailVO obj:pia_mem_list){
				session.save(obj);
			}
			session.getTransaction().commit();
			flag = true;
		}catch (Exception e) {	
			session.getTransaction().rollback();
			e.printStackTrace();	
		}finally {	session.close();	}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println(new RegistrationDaoImpl().getPiaMemberCodes("13413"));
	}
}
