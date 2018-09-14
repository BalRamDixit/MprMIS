package com.infotech.sgsy.selfHelpGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterSHGBankLinkageFactory;
import com.infotech.sgsy.common.MasterSHGMonthlyDetailsFactory;
import com.infotech.sgsy.common.MasterSHGMonthlyLoanCalculationsFactory;
import com.infotech.sgsy.common.MasterShgVOFactory;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.PropertyModel;
import com.infotech.sgsy.util.SGSYConstants;

/**
 * @author NIC 
 * @since July, 2013 
 */ 
public class SHGMasterDAO {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	private List getUnique(List list1, List list2) {
		for (int i = 0; i < list1.size(); i++) {
			PropertyModel model1 = (PropertyModel) list1.get(i);
			for (int j = 0; j < list2.size(); j++) {
				PropertyModel model2 = (PropertyModel) list2.get(j);
				if (model1.getPropertyCode().equals(model2.getPropertyCode())) {
					list2.remove(j);
					j--;
				}
			}
		}
		return list2;
	}

	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public List getGroupsFinWiseBranchWise(LocationVO locationVO,
			String grpFlag, String barnchCode) throws Exception {
		List results = null;
		List finalList = new ArrayList();
		String finYr = locationVO.getFinYr();
		Persister persister = PersisterImpl.getPersister();
		Session session = null;
		String active = SGSYConstants.ACTIVE;

		try {
			session = persister.getSession();
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Criteria criteria = session.createCriteria("com.infotech.sgsy.selfHelpGroup."+ locationVO.getStateShortName() + ".SHGVO");
			criteria.add(Restrictions.eq("entityCode", locationVO.getBlockCode()));
			criteria.add(Restrictions.eq("flag", grpFlag));
			criteria.add(Restrictions.eq("bankBranchCode", barnchCode));
			criteria.add(Restrictions.eq("activeOrDefunct", active));

			if (!"AY".equalsIgnoreCase(finYr))
				criteria.add(Restrictions.eq("finYr", finYr));

			results = criteria.list();
			for (int x = 0; x < results.size(); x++) {
				PropertyModel propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(((MasterShgVO) results.get(x)).getShgCode());
				propertyModel.setPropertyValue(((MasterShgVO) results.get(x)).getGroupName()
						+ "("
						+ ((MasterShgVO) results.get(x)).getShgCode() + ")");
				finalList.add(propertyModel);
			}
			session.flush();
			transaction.commit();
			return finalList;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("error in get Groups " + e);
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	
	
	
	// Function used to save the details of SHG and its member 
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public boolean Save(MasterShgVO shgDetails,
			List<MasterShgMemberDetails> SHGMember, String shortName)
			throws ClassNotFoundException {
		// code for hibernate session
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(shgDetails);

			for (MasterShgMemberDetails sm : SHGMember) {
				MasterShgMemberDetails memberDetails = MasterMemberFactory.getInstance("com.infotech.sgsy.selfHelpGroup."+ shortName + ".ShgMemberDetails");
				memberDetails = (MasterShgMemberDetails) sm;
				memberDetails.setShgCode(shgDetails.getShgCode());
				session.save(memberDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (session.isOpen()) {
				tx.commit();
				session.flush();
				session.close();
			}
		}
		return false;
	}

	

	/**
	 * Function used to save the details of SHG and its member
	 * @author NIC 
	 * @since August, 2013 
	 */ 
	public boolean save(SHGActionForm shgForm, String shortName, String createdBy)
	throws ClassNotFoundException {
	// code for hibernate session
	boolean flag = false;
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tx = session.beginTransaction();
	try {
		String createdOn = new DateUtil().getCurrentDate();
		MasterShgVO entity = MasterShgVOFactory.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGVO");
		BeanUtils.copyProperties(entity, shgForm);
					
		List memberDetailList = new ArrayList();
		for (int i = 0; i < shgForm.getMemberName().length; i++) {
			MasterShgMemberDetails memberDetails = MasterMemberFactory.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".ShgMemberDetails");
							
			if(shgForm.getShgMemberCode()!=null && !shgForm.getShgMemberCode()[i].isEmpty())
				memberDetails.setShgMemberCode(shgForm.getShgMemberCode()[i]);
			memberDetails.setEntityCode(entity.getEntityCode());
			memberDetails.setMemberName(shgForm.getMemberName()[i]);
			memberDetails.setBelongingName(shgForm.getBelongingName()[i]);
			memberDetails.setGender(shgForm.getGender()[i]);
			memberDetails.setSocialCategory(shgForm.getSocialCategory()[i]);
			memberDetails.setDisability(shgForm.getDisability()[i]);
			memberDetails.setReligion(shgForm.getReligion()[i]);
			memberDetails.setAplbpl(shgForm.getAplBpl()[i]);
			memberDetails.setPipCategory(shgForm.getPipCategory()[i]);
			memberDetails.setLeader(shgForm.getLeader()[i]);
			memberDetails.setAadhaar(shgForm.getAadhaar()[i]);
			memberDetails.setSeccNo(shgForm.getSeccNo()[i]);
			memberDetails.setCreatedBy(createdBy);
			memberDetails.setCreatedOn(createdOn);
			memberDetailList.add(memberDetails);
			memberDetails.setMobile(shgForm.getMobile()[i]);
		}
		
		entity.setCreatedBy(createdBy);
		entity.setCreatedOn(createdOn);
		session.save(entity);
	
		for (int i = 0; i < memberDetailList.size(); i++) {
			MasterShgMemberDetails memberDetails = MasterMemberFactory.getInstance("com.infotech.sgsy.selfHelpGroup."+ shortName + ".ShgMemberDetails");
			memberDetails = (MasterShgMemberDetails) memberDetailList.get(i);
			memberDetails.setShgCode(entity.getShgCode());
			session.save(memberDetails);
		}
		tx.commit();
		flag = true;
	} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
	} finally {
		session.flush();
		session.close();
	}
	return flag;
	}
	
	/**
	 * Function used to save the details of SHG and its member
	 * @author NIC 
	 * @since August, 2013 
	 */ 
	public boolean updateOrInsert(SHGActionForm shgForm, String shortName, String updatedBy)
	throws ClassNotFoundException {
	// code for hibernate session
	boolean flag = false;
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tx = session.beginTransaction();
	try {
		String updatedOn = new DateUtil().getCurrentDate();
		MasterShgVO entity = MasterShgVOFactory.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGVO");
		BeanUtils.copyProperties(entity, shgForm);
					
		List memberDetailList = new ArrayList();
		for (int i = 0; i < shgForm.getMemberName().length; i++) {
			MasterShgMemberDetails memberDetails = MasterMemberFactory.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".ShgMemberDetails");
							
			if(shgForm.getShgMemberCode()!=null && !shgForm.getShgMemberCode()[i].isEmpty())
				memberDetails.setShgMemberCode(shgForm.getShgMemberCode()[i]);
			memberDetails.setEntityCode(entity.getEntityCode());
			memberDetails.setMemberName(shgForm.getMemberName()[i]);
			memberDetails.setBelongingName(shgForm.getBelongingName()[i]);
			memberDetails.setGender(shgForm.getGender()[i]);
			memberDetails.setSocialCategory(shgForm.getSocialCategory()[i]);
			memberDetails.setDisability(shgForm.getDisability()[i]);
			memberDetails.setReligion(shgForm.getReligion()[i]);
			memberDetails.setAplbpl(shgForm.getAplBpl()[i]);
			memberDetails.setPipCategory(shgForm.getPipCategory()[i]);
			memberDetails.setLeader(shgForm.getLeader()[i]);
			memberDetails.setAadhaar(shgForm.getAadhaar()[i]);
			memberDetails.setSeccNo(shgForm.getSeccNo()[i]);
			if(shgForm.getCreatedMembBy()[i].equals("") && shgForm.getCreatedMembOn()[i].equals("")){
				memberDetails.setCreatedBy(updatedBy);
				memberDetails.setCreatedOn(updatedOn);
			}else{
				memberDetails.setCreatedBy(shgForm.getCreatedMembBy()[i]);
				memberDetails.setCreatedOn(shgForm.getCreatedMembOn()[i]);
				memberDetails.setUpdatedBy(updatedBy);
				memberDetails.setUpdatedOn(updatedOn);	
			}
			memberDetailList.add(memberDetails);
			memberDetails.setMobile(shgForm.getMobile()[i]);
		}
		
		entity.setUpdatedBy(updatedBy);
		entity.setUpdatedOn(updatedOn);
		session.saveOrUpdate(entity);
	
		for (int i = 0; i < memberDetailList.size(); i++) {
			MasterShgMemberDetails memberDetails = MasterMemberFactory.getInstance("com.infotech.sgsy.selfHelpGroup."+ shortName + ".ShgMemberDetails");
			memberDetails = (MasterShgMemberDetails) memberDetailList.get(i);
			memberDetails.setShgCode(entity.getShgCode());
			session.saveOrUpdate(memberDetails);
		}
		tx.commit();
		flag = true;
	} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
	} finally {
		session.flush();
		session.close();
	}
	return flag;
	}
	/**
	 * __________ SHG MONTHLY REPORT CARD CODE _______________ DATE: 27 Nov 2012
	 **/

	// FUNCTION USED TO GET THE SHG LIST FOR DROP DOWN
	/*
	 * public List getSHGListForDropdown(LocationVO locationVO , String
	 * entityCode) throws Exception { List results = null; List finalList=new
	 * ArrayList(); Persister persister = PersisterImpl.getPersister(); Session
	 * session=null;
	 * 
	 * try{ session=persister.getSession(); Transaction transaction =
	 * session.getTransaction(); transaction.begin(); Criteria
	 * criteria=session.createCriteria
	 * ("com.infotech.sgsy.selfHelpGroup."+locationVO
	 * .getStateShortName()+".SHGVO");
	 * criteria.add(Restrictions.eq("entityCode", entityCode)) ;
	 * results=criteria.list(); for(int x=0;x<results.size();x++){ PropertyModel
	 * propertyModel=new PropertyModel();
	 * propertyModel.setPropertyCode(((MasterShgVO
	 * )results.get(x)).getShgCode());
	 * propertyModel.setPropertyValue(((MasterShgVO
	 * )results.get(x)).getGroupName()); finalList.add(propertyModel); }
	 * session.flush(); transaction.commit(); return finalList; }catch
	 * (Exception e) { e.printStackTrace(); log.error("error in get Groups "+e);
	 * throw e; }finally{ if(session.isOpen()) session.close(); }
	 * 
	 * }
	 */
	/**
	 * FUNCTION USED TO GET THE SHG LIST FOR DROP DOWN
	 * @author NIC 
	 * @since July, 2013 
	 */
	public List<String[]> getSHGListForDropdown(LocationVO locationVO,
			String entityCode) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String[]> SHGListFinal = new ArrayList<String[]>();
		String query_hql = "SELECT s.shgCode, s.groupName FROM com.infotech.sgsy.selfHelpGroup."
				+ locationVO.getStateShortName()
				+ ".SHGVO as s Where s.entityCode= :entityCode";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("entityCode", entityCode);
			List<Object[]> SHGList = (List<Object[]>) query.list();
			if (SHGList != null)
				for (Object[] shg : SHGList) {
					String[] lstVillage = new String[2];
					lstVillage[0] = shg[0].toString();
					lstVillage[1] = shg[1].toString();
					SHGListFinal.add(lstVillage);
				}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("error in get Groups " + e);
			throw e;
		} finally {
			session.close();
		}
		return SHGListFinal;
	}



	/**
	 * USED TO GET THE SHG DETAIL
	 * @author NIC 
	 * @since July, 2013 
	 */
	public MasterShgVO getshgRegDetails(LocationVO locationVO, String code)
			throws Exception {
		MasterShgVO results = null;
		Criteria criteria = null;
		Transaction transaction = null;
		Persister persister = PersisterImpl.getPersister();
		Session sess = persister.getSession();
		try {
			transaction = sess.getTransaction();
			transaction.begin();
			criteria = sess.createCriteria("com.infotech.sgsy.selfHelpGroup."
					+ locationVO.getStateShortName() + ".SHGVO");
			criteria = criteria.add(Restrictions.eq("shgCode", code));
			results = (MasterShgVO) criteria.list().iterator().next();
			transaction.commit();
			return results;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOGGER.error("getshgRegDetails Function :: " + e.getMessage());
			throw e;
		} finally {
				sess.close();
		}
	}
	
	
	/**
	 * USED TO GET THE SHG MEMBER DETAILS IN LIST
	 * @author NIC 
	 * @since July, 2013 
	 */
	public List<MasterShgMemberDetails> getSHGMemberDetails(
			LocationVO locationVO, String code) throws Exception {
		List<MasterShgMemberDetails> results = null;
		Criteria criteria = null;
		Transaction transaction = null;
		Persister persister = PersisterImpl.getPersister();
		Session sess = persister.getSession();
		try {
			transaction = sess.getTransaction();
			transaction.begin();
			criteria = sess.createCriteria("com.infotech.sgsy.selfHelpGroup."
					+ locationVO.getStateShortName() + ".ShgMemberDetails");
			criteria = criteria.add(Restrictions.eq("shgCode", code));
			results = criteria.list();
			transaction.commit();
			return results;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOGGER.error("error in get Groups " + e.getMessage());
			throw e;
		} finally {
				sess.close();
		} 
	}
	
	
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */
	public MasterShgVO getshgRegDetails(String Short_Name, String code)
			throws Exception {
		MasterShgVO results = null;
		Criteria criteria = null;
		Transaction transaction = null;
		Persister persister = PersisterImpl.getPersister();
		Session sess = persister.getSession();
		try {
			transaction = sess.getTransaction();
			transaction.begin();
			criteria = sess.createCriteria("com.infotech.sgsy.selfHelpGroup."
					+ Short_Name + ".SHGVO");
			criteria = criteria.add(Restrictions.eq("shgCode", code));
			results = (MasterShgVO) criteria.list().iterator().next();
			transaction.commit();
			return results;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOGGER.error("getshgRegDetails Function :: " + e.getMessage());
			throw e;
		} finally {
			sess.close();
		}
	}
	
	
	/**
	 * @author NIC
	 * @since July, 2013
	 */
	public List<MasterShgMemberDetails> getSHGMemberDetails(String Short_Name,
			String code) throws Exception {
		List<MasterShgMemberDetails> results = null;
		Criteria criteria = null;
		Transaction transaction = null;
		Persister persister = PersisterImpl.getPersister();
		Session sess = persister.getSession();
		try {
			transaction = sess.getTransaction();
			transaction.begin();
			criteria = sess.createCriteria("com.infotech.sgsy.selfHelpGroup."
					+ Short_Name + ".ShgMemberDetails");
			criteria = criteria.add(Restrictions.eq("shgCode", code));
			results = criteria.list();
			transaction.commit();
			return results;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			LOGGER.error("error in get Groups " + e.getMessage());
			throw e;
		} finally {
			sess.close();
		}
	}
	
	
	

	/**
	 * ----------------------------
	 * SHG MONTHLY STATUS FUNCTIONS
	 * ----------------------------- 
	 */

	/**
	 * USED TO SAVE THE MONTHLY SHG DETAIL
	 * @author NIC
	 * @since July, 2013
	 */
	public boolean SaveSHGMonthyDetail(SHGReportCardForm reportCardForm,
			String shortName, List<MasterSHGBankLinkageVO> SHGBankLinkageList, List<MasterSHGMonthlyLoanCalculationsVO> BankLoanMonthlyList)
	throws ClassNotFoundException {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			MasterSHGMonthlyDetailVO reportCard = MasterSHGMonthlyDetailsFactory
					.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGMonthlyDetailVO");
			BeanUtils.copyProperties(reportCard, reportCardForm);
			session.save(reportCard);
			for(MasterSHGMonthlyLoanCalculationsVO bankLoanMonthlyDetail: BankLoanMonthlyList){
				MasterSHGMonthlyLoanCalculationsVO loanCalulation =  MasterSHGMonthlyLoanCalculationsFactory
				.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGMonthlyLoanCalculationsVO");	
				
				loanCalulation.setEntityCode(bankLoanMonthlyDetail.getEntityCode());
				loanCalulation.setShgCode(bankLoanMonthlyDetail.getShgCode());
				loanCalulation.setFinancialYear(bankLoanMonthlyDetail.getFinancialYear());
				loanCalulation.setEntryMonth(bankLoanMonthlyDetail.getEntryMonth());
				
				loanCalulation.setPrincipalBeforeRepayment(bankLoanMonthlyDetail.getPrincipalBeforeRepayment());
				loanCalulation.setRateOfInterest(bankLoanMonthlyDetail.getRateOfInterest());
				loanCalulation.setInterestPaid(bankLoanMonthlyDetail.getInterestPaid());
				loanCalulation.setRepaidPrincipal(bankLoanMonthlyDetail.getRepaidPrincipal());
				loanCalulation.setInterestAmountAbove7per(bankLoanMonthlyDetail.getInterestAmountAbove7per());
								
				loanCalulation.setShgBankLinkageCode(bankLoanMonthlyDetail.getShgBankLinkageCode());
				loanCalulation.setCreatedBy(bankLoanMonthlyDetail.getCreatedBy());
				loanCalulation.setCreatedOn(bankLoanMonthlyDetail.getCreatedOn());
				session.save(loanCalulation);
			}
			
			for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
				MasterSHGBankLinkageVO bankLinkage = MasterSHGBankLinkageFactory
				.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGBankLinkageVO");
				bankLinkage = SHGBankLinkage;
				session.update(bankLinkage);
			}
					
			tx.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	// USED TO UPDATE THE MONTHLY SHG DETAIL
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public boolean UpdateSHGMonthyDetail(SHGReportCardForm reportCardForm, String shortName,
			List<MasterSHGBankLinkageVO> SHGBankLinkageList, List<MasterSHGMonthlyLoanCalculationsVO> BankLoanMonthlyList)
			throws ClassNotFoundException {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			MasterSHGMonthlyDetailVO reportCard = MasterSHGMonthlyDetailsFactory
					.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName
							+ ".SHGMonthlyDetailVO");
			BeanUtils.copyProperties(reportCard, reportCardForm);
			session.update(reportCard);
			
			for(MasterSHGMonthlyLoanCalculationsVO bankLoanMonthlyDetail: BankLoanMonthlyList){
				MasterSHGMonthlyLoanCalculationsVO loanCalulation =  MasterSHGMonthlyLoanCalculationsFactory
				.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGMonthlyLoanCalculationsVO");					
				String query_statement = "UPDATE " + shortName+"_monthly_loan_calculations SET repaid_principal= :repaid " +
						" WHERE shg_code= :code AND financial_year= :fnyear AND entry_month= :month AND shg_bank_linkage_code = :bankCode";
				Query query = session.createSQLQuery(query_statement);
				query.setBigDecimal("repaid",bankLoanMonthlyDetail.getRepaidPrincipal());
				query.setParameter("code",bankLoanMonthlyDetail.getShgCode());
				query.setParameter("fnyear", bankLoanMonthlyDetail.getFinancialYear());
				query.setParameter("month", bankLoanMonthlyDetail.getEntryMonth());
				query.setParameter("bankCode", bankLoanMonthlyDetail.getShgBankLinkageCode());
				query.executeUpdate();
				
			}
						
			for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
				MasterSHGBankLinkageVO bankLinkage = MasterSHGBankLinkageFactory
				.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGBankLinkageVO");
				bankLinkage = SHGBankLinkage;
				session.update(bankLinkage);
			}
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	/*public boolean UpdateSHGMonthyDetail(SHGReportCardForm reportCardForm, String shortName,
			List<MasterSHGBankLinkageVO> SHGBankLinkageList, List<MasterSHGMonthlyLoanCalculationsVO> BankLoanMonthlyList)
			throws ClassNotFoundException {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			MasterSHGMonthlyDetailVO reportCard = MasterSHGMonthlyDetailsFactory
					.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName
							+ ".SHGMonthlyDetailVO");
			BeanUtils.copyProperties(reportCard, reportCardForm);
			session.update(reportCard);
			
			for(MasterSHGMonthlyLoanCalculationsVO bankLoanMonthlyDetail: BankLoanMonthlyList){
				MasterSHGMonthlyLoanCalculationsVO loanCalulation =  MasterSHGMonthlyLoanCalculationsFactory
				.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGMonthlyLoanCalculationsVO");					
				String query_statement = "UPDATE " + shortName+"_monthly_loan_calculations SET repaid_principal=" 
										+ bankLoanMonthlyDetail.getRepaidPrincipal() 
										+ " WHERE shg_code='" + bankLoanMonthlyDetail.getShgCode()
										+ "' AND financial_year='" + bankLoanMonthlyDetail.getFinancialYear() 
										+ "' AND entry_month='" + bankLoanMonthlyDetail.getEntryMonth()
										+ "' AND shg_bank_linkage_code ='" + bankLoanMonthlyDetail.getShgBankLinkageCode() +"'"; 
				session.createSQLQuery(query_statement).executeUpdate();
			}
						
			for(MasterSHGBankLinkageVO SHGBankLinkage: SHGBankLinkageList){
				MasterSHGBankLinkageVO bankLinkage = MasterSHGBankLinkageFactory
				.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName + ".SHGBankLinkageVO");
				bankLinkage = SHGBankLinkage;
				session.update(bankLinkage);
			}
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}*/
	
	// USED TO GET THE SHG MONTHLY DETAIL
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public MasterSHGMonthlyDetailVO getSHGMonthlyDetail(LocationVO locationVO,
			String SHG_CODE, String FN_YEAR, String MONTH) throws Exception {
		MasterSHGMonthlyDetailVO SHG_DETAIL = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		String SQL_SELECT_STATEMENT = "FROM com.infotech.sgsy.selfHelpGroup."
				+ locationVO.getStateShortName()
				+ ".SHGMonthlyDetailVO as s Where s.shgCode= :shgCode and s.financialYear= :finYear and s.entryMonth= :entryMonth";
		try {
			tr.begin();
			Query query = session.createQuery(SQL_SELECT_STATEMENT);
			query.setParameter("shgCode", SHG_CODE);
			query.setParameter("finYear", FN_YEAR);
			query.setParameter("entryMonth", MONTH);
			List DETAIL = query.list();
			if (DETAIL != null && DETAIL.size() > 0)
				SHG_DETAIL = (MasterSHGMonthlyDetailVO) DETAIL.iterator()
						.next();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			LOGGER.error("getshgRegDetails Function :: " + e.getMessage());
			throw e;
		} finally {
			session.close();
		}
		return SHG_DETAIL;
	}

	// USED TO GET THE SHG MONTHLY DETAIL
	/*	public SHGReportCardForm getSHGMonthlyDetailForm(LocationVO locationVO, String SHG_CODE, String FN_YEAR, String MONTH)
				throws Exception {
			MasterSHGMonthlyDetailVO SHG_DETAIL = new MasterSHGMonthlyDetailVO();
			SHGReportCardForm SHG_REPORT_CARD_FROM = new SHGReportCardForm();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
		
			String SQL_SELECT_STATEMENT = "FROM com.infotech.sgsy.selfHelpGroup."
											+ locationVO.getStateShortName()
											+ ".SHGMonthlyDetailVO as s Where s.shgCode='"+ SHG_CODE +"' and s.financialYear='" + FN_YEAR
											+ "' and s.entryMonth='"+ MONTH +"'";
			try {
				tr.begin();
				SHG_DETAIL = (MasterSHGMonthlyDetailVO)session.createQuery(SQL_SELECT_STATEMENT).list().iterator().next();	
				BeanUtils.copyProperties(SHG_REPORT_CARD_FROM, SHG_DETAIL);
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
				LOGGER.error("getshgRegDetails Function :: " + e.getMessage());
				throw e;
			} finally {
				session.close();
			}
			return SHG_REPORT_CARD_FROM;
		}*/
	
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public List getSHGMonthlyDetailMonthList(LocationVO locationVO,
			String SHG_CODE, String FN_YEAR) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		String SQL_SELECT_STATEMENT = "SELECT s.entryMonth FROM com.infotech.sgsy.selfHelpGroup."
				+ locationVO.getStateShortName()
				+ ".SHGMonthlyDetailVO as s Where s.shgCode=:shgCode and s.financialYear= :finYear";
		List ENTERED_MONTH_LIST = null;
		try {
			tr.begin();
			Query query = session.createQuery(SQL_SELECT_STATEMENT);
			query.setParameter("shgCode", SHG_CODE);
			query.setParameter("finYear", FN_YEAR);
			ENTERED_MONTH_LIST = query.list();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			LOGGER.error("getshgRegDetails Function :: " + e.getMessage());
			throw e;
		} finally {
			session.close();
		}
		return ENTERED_MONTH_LIST;
	}


	
	// USED TO SAVE AND MODIFY THE BANK LINKAGE DETAIL
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public boolean bankLinkageSave(SHGBankLinkageForm bankLinkageForm,
			String shortName) throws ClassNotFoundException {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			MasterSHGBankLinkageVO SHGBankLinkage = MasterSHGBankLinkageFactory
					.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName
							+ ".SHGBankLinkageVO");
			BeanUtils.copyProperties(SHGBankLinkage, bankLinkageForm);
			if(SHGBankLinkage.getLoanType().equals("TL")){
				SHGBankLinkage.setCashCreditLimit(null);
			}else{
				SHGBankLinkage.setEmi(null);
				SHGBankLinkage.setNumberOfInstallment(0);
			}
			
			if(SHGBankLinkage.getShgBankLinkageCode().equals("")){
				SHGBankLinkage.setLoanStatus("R");
				SHGBankLinkage.setSystemDefinedOutstanding(SHGBankLinkage.getOutstanding());
				session.save(SHGBankLinkage);				
			}else{
				SHGBankLinkage.setSystemDefinedOutstanding(SHGBankLinkage.getOutstanding());
				session.update(SHGBankLinkage);
			}
			
			tx.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public boolean bankLinkageUpdate(SHGBankLinkageForm bankLinkageForm,
			String shortName) throws ClassNotFoundException {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			MasterSHGBankLinkageVO SHGBankLinkage = MasterSHGBankLinkageFactory
					.getInstance("com.infotech.sgsy.selfHelpGroup." + shortName
							+ ".SHGBankLinkageVO");
			BeanUtils.copyProperties(SHGBankLinkage, bankLinkageForm);
			session.update(SHGBankLinkage);
			tx.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public List<MasterSHGBankLinkageVO> shgBankLinkageList(String SHORT_NAME, String SHG_CODE) throws ClassNotFoundException {
		List<MasterSHGBankLinkageVO> SHG_BANKLINKAGE_LIST = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String HQL_SELECT_STATEMENT = "FROM com.infotech.sgsy.selfHelpGroup."+ SHORT_NAME
			+ ".SHGBankLinkageVO as s WHERE s.shgCode= :SHG_CODE AND s.loanStatus='R' ORDER BY s.loanType";
		try {
			tr.begin();
			Query query= session.createQuery(HQL_SELECT_STATEMENT);
			query.setParameter("SHG_CODE",SHG_CODE);
			SHG_BANKLINKAGE_LIST = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SHG_BANKLINKAGE_LIST;
	}

	
}