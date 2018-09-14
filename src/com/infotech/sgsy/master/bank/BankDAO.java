package com.infotech.sgsy.master.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.bankBranch.BankBranchVO;
import com.infotech.sgsy.master.village.VillageVO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.PropertyModel;

public class BankDAO implements MasterDAO {
	protected final Log log = LogFactory.getLog(getClass());

	public int save(BankVO bankVO) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(bankVO);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			log.error("error in  saveBanks " + e);
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return Constants.ADD_SUCCESS;
	}

	/*public boolean update(BankVO bankVO) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String QUERY_SQL = "";
		boolean flag = false;
		try {
		tr.begin();
		QUERY_SQL = " update mst_bank set bank_shortname= " +
					" ? , banklevel_code= " +
					" ? , lastmodifiedon= " +
					" ? , banktype_code= " +
					" ? , lastmodifedby= " +
					" ? where bank_code= ? ";
			
			Query query = session.createSQLQuery(QUERY_SQL);
			query.setParameter(0, bankVO.getBankShortName());
			query.setParameter(1, bankVO.getBankLevelCode());
			query.setParameter(2, bankVO.getLastModifiedOn());
			query.setParameter(3, bankVO.getBankTypeCode());
			query.setParameter(4, bankVO.getLastModifedBy());
			query.setParameter(5, bankVO.getBankCode());
			
			tr.commit();
			flag = true;
		} catch (Exception e) {
			tr.rollback();
			log.error("error in  saveBanks " + e);
			throw e;
		}finally {		
			if (session.isOpen())
				session.close();
		}return flag;
	}*/
	
	public boolean update(BankVO bankVO) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Transaction transaction = persister.getTransaction();
		String query = "";
		boolean flag = false;
		try {
			transaction.begin();
			query = "update mst_bank set bank_shortname='"
					+ bankVO.getBankShortName() + "', banklevel_code='"
					+ bankVO.getBankLevelCode() + "', lastmodifiedon='"
					+ bankVO.getLastModifiedOn() + "' , banktype_code='"
					+ bankVO.getBankTypeCode() + "',lastmodifedby='"
					+ bankVO.getLastModifedBy() + "' where bank_code='"
					+ bankVO.getBankCode() + "' ";
			persister.executeNativeQry(query);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			transaction.rollback();
			log.error("error in  saveBanks " + e);
			throw e;
		}
		return flag;
	}
	
	
	// This Method Action Class is BankBranchAction and method name is delete
	// which is already commented in action class
	/*public List getBanks(LocationVO locationVO) throws Exception {

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List QueryList = new ArrayList();
		String QUERY_SQL = "";
		List results = null;

		QUERY_SQL = " SELECT DISTINCT bank_code, bank_name, bank_shortname  "
				+ " FROM mst_bank x "
				+ " where (x.entity_code like ? "
				+ " or banklevel_code = '1') and length(banklevel_code)=1 order by bank_name ";

		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		try {
			Query query = session.createSQLQuery(QUERY_SQL);
			query.setParameter(0, locationVO.getStateCode() + "%");
			QueryList = query.list();

			for (int i = 0; i < QueryList.size(); i++) {
				Object obj1[] = (Object[]) QueryList.get(i);
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(obj1[0].toString());
				propertyModel.setPropertyValue(obj1[1].toString());
				finalList.add(propertyModel);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBanks(String ENTITY_CODE) FUNCTION: "
					+ e.getMessage());
		} finally {
			tr.commit();
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}*/

	// Not found in any Action class and Method where this method is used
	/*public List getBankListForDropdowns(String entityCode) throws Exception {

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		List QueryList = new ArrayList();
		String QUERY_SQL = null;
		List reults = new ArrayList();
		PropertyModel bankVo = new PropertyModel();
		bankVo.setPropertyCode("");
		bankVo.setPropertyValue("Select");
		reults.add(bankVo);
		try {
			tr.begin();
			if (entityCode != null) {
				if (entityCode.length() > 5)
					entityCode = entityCode.substring(0, 4);
			}
			QUERY_SQL = "select * from mst_bank where bank_code in "
					+ " (select distinct bank_code from mst_bank_branch "
					+ " where village_code like ? "
					+ " and length(banklevel_code)=1 ) order by bank_name ";

			Query query = session.createSQLQuery(QUERY_SQL);
			query.setParameter(0, entityCode + "%");
			QueryList = query.list();

			for (int i = 0; i < QueryList.size(); i++) {
				Object[] arr = (Object[]) QueryList.get(i);
				BankVO propertyModel = new BankVO();
				propertyModel.setBankCode(arr[0].toString());
				propertyModel.setBankName(arr[1].toString());
				reults.add(bankVo);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankListForBanchAdd FUNCTION: "
					+ e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return reults;
	}*/

	// DATE 19 JULY 2013 HARI SHANKER
	public List getBankList(String entityCode) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List reults = new ArrayList();
		List QueryList = new ArrayList();
		String QUERY_SQL = "";

		try {
			if (entityCode != null) {
				if (entityCode.length() > 5)
					entityCode = entityCode.substring(0, 4);
			}
			tr.begin();
			QUERY_SQL = "select * from mst_bank where bank_code in "
					+ " (select bank_code from mst_bank_branch "
					+ " where entity_code IN ('"+entityCode +"', '"+ entityCode.substring(0, 2) + "')) order by bank_name";

			Query query = session.createSQLQuery(QUERY_SQL);
			log.info("Passing value:: "+entityCode +","+ entityCode.substring(0, 2));
			QueryList = query.list();

			for (int i = 0; i < QueryList.size(); i++) {
				Object[] arr = (Object[]) QueryList.get(i);
				BankVO bankVo = new BankVO();
				bankVo.setBankCode(arr[0].toString());
				bankVo.setBankName(arr[1].toString());
				reults.add(bankVo);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankModificationList(String ENTITY_CODE) FUNCTION: "
					+ e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return reults;
	}

	// Not found in any Action class and Method where this method is used
	/*public List getBankListForSP(LocationVO vo) throws Exception {

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List reults = new ArrayList();
		String QUERY_SQL = null;
		List QueryList = new ArrayList();

		try {
			tr.begin();
			QUERY_SQL = "select bank_code,bank_name from mst_bank  where  "
					+ " district_code like ? order by bank_name";

			Query query = session.createSQLQuery(QUERY_SQL);
			query.setParameter(0, vo.getDistrictCode() + "%");
			QueryList = query.list();
			for (int i = 0; i < QueryList.size(); i++) {
				Object[] arr = (Object[]) QueryList.get(i);
				BankVO propertyModel = new BankVO();
				propertyModel.setBankCode(arr[0].toString());
				propertyModel.setBankName(arr[1].toString());
				reults.add(propertyModel);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankListForSP FUNCTION: " + e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return reults;
	}*/

	public List getTrustListForSP(LocationVO vo) throws Exception {
		List reults = new ArrayList();
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = "select * from mst_trust_list where  "
					+ "district_code = ? order by trust_name";
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getDistrictCode());
			rs = stmt.executeQuery();
			PropertyModel pm = null;
			while (rs.next()) {
				pm = new PropertyModel();
				pm.setPropertyCode(rs.getString("trust_code"));
				pm.setPropertyValue(rs.getString("trust_name"));
				reults.add(pm);
			}
			con.commit();
		} catch (Exception e) {
			// transaction.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}

		return reults;
	}

	// Function used to collect Bank Branch Name and Bank Branch Code 
	// Action Class Is SHGBusiness.java and Method name is getBankBranchList It is Used in SHGAction.java for bank branch List
	/*public List getBankBranch(String entityCode, String bankCode) {
		List results = new ArrayList();
		Transaction transaction = null;
		String query = "";
		Statement stmt = null;
		ResultSet rs = null;
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		transaction = session.getTransaction();
		try {
			// session=PersisterImpl.getSessionFactory().getCurrentSession();
			if (entityCode != null) {
				if (entityCode.length() > 5)
					entityCode = entityCode.substring(0, 4);
			}
			transaction.begin();
			query = "from com.infotech.sgsy.master.bankBranch.BankBranchVO as bb where "
					+ "bb.bankCode='" + bankCode + "' and bb.villageCode like '" + entityCode + "%' order by bb.bankBranchName";

			results = persister.getObjectsByQuery(query);
			for (int x = 0; x < results.size(); x++) {
				if (results.get(x) != null) {
					propertyModel = new PropertyModel();
					propertyModel.setPropertyCode(((BankBranchVO) results.get(x)).getBankBranchCode());
					propertyModel.setPropertyValue(((BankBranchVO) results.get(x)).getBankBranchName());
					finalList.add(propertyModel);
				}
			}
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.flush();
		session.close();

		return finalList;
	}*/

	// Not Found Where are use this Method in this project
	// Function use to collect the bank branch code and bank branch name with
	// branch address
	/*public List getBankBranchList(String entityCode, String bankCode) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		List results = new ArrayList();
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		String QUERY_SQL = null;

		try {
			if (entityCode != null) {
				if (entityCode.length() > 5)
					entityCode = entityCode.substring(0, 4);
			}
			tr.begin();
			QUERY_SQL = "from com.infotech.sgsy.master.bankBranch.BankBranchVO as bb where "
					+ "bb.bankCode = ? and bb.villageCode like ? order by bb.bankBranchName";

			Query query = session.createSQLQuery(QUERY_SQL);
			query.setParameter(0, bankCode);
			query.setParameter(1, entityCode + "%");
			results = query.list();
			for (int x = 0; x < results.size(); x++) {
				if (results.get(x) != null) {
					propertyModel = new PropertyModel();
					propertyModel.setPropertyCode(((BankBranchVO) results
							.get(x)).getBankBranchCode());
					propertyModel.setPropertyValue(((BankBranchVO) results
							.get(x)).getBankBranchName()
							+ "-"
							+ ((BankBranchVO) results.get(x)).getAddress());
					finalList.add(propertyModel);
				}
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankViewList FUNCTION: " + e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}*/

	public static List getBankById(String bankId) {
		Persister persister = PersisterImpl.getPersister();
		Session sess = persister.getSession();
		Transaction transaction = sess.getTransaction();
		transaction.begin();
		List result = null;
		Criteria criteria = null;
		try {
			criteria = sess.createCriteria(
					"com.infotech.sgsy.master.bank.BankVO").add(
					Restrictions.eq("bankCode", bankId));
			result = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (sess.isOpen()) {
				sess.close();
			}
		}
		return result;
	}

	@Override
	public int delete(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MasterVO masterVO) throws Exception {

		return 0;
	}

	public String getBankTypeByBankCode(String bankCode) throws Exception {
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = " select banktype_detail " + " from "
					+ " mst_bank x,mst_bank_type y " + " where "
					+ " x.banktype_code=y.banktype_code  and  x.bank_code= ? ";
			stmt = con.prepareStatement(query);
			stmt.setString(1, bankCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
			con.commit();
		} catch (Exception e) {
			// transaction.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}

		return result;
	}

	public List getBanksForView(LocationVO locationVO) throws Exception {
		// public List getBanksForView() throws Exception{
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String result = null;
		BankVO propertyModel = new BankVO();
		List finalList = new ArrayList();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			/*
			 * query=
			 * "select bank_code,bank_name,bank_shortname,banktype_detail,bank_level_name   "
			 * + "from " + "mst_bank x ,mst_bank_type y,mst_bank_level z " +
			 * "where" +
			 * " x.banktype_code = y.banktype_code and x.district_code like '"
			 * +locationVO.getStateCode()+"%' " +
			 * " and z.bank_level_code = x.banklevel_code "+ "  union " +
			 * "    select bank_code,bank_name,bank_shortname,banktype_detail,bank_level_name   "
			 * +
			 * " from mst_bank x ,mst_bank_type y,mst_bank_level z where x.banktype_code = y.banktype_code "
			 * + " and z.bank_level_code = x.banklevel_code "+
			 * "   and banklevel_code = '1' order by bank_name" ;
			 */
			query = "select bank_code,bank_name,bank_shortname,banktype_detail,bank_level_name   "
					+ "from "
					+ "mst_bank x ,mst_bank_type y,mst_bank_level z "
					+ "where"
					+ " x.banktype_code = y.banktype_code "
					+ " and z.bank_level_code = x.banklevel_code ";
			stmt = con.prepareStatement(query);

			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new BankVO();
				propertyModel.setBankCode(rs.getString("bank_code"));
				propertyModel.setBankName(rs.getString("bank_name"));
				propertyModel.setBankShortName(rs.getString("bank_shortname"));
				propertyModel.setBankTypeCode(rs.getString("banktype_detail"));
				propertyModel
						.setbanklLevelName(rs.getString("bank_level_name"));
				finalList.add(propertyModel);
			}
			con.commit();
		} catch (Exception e) {
			// transaction.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}

		return finalList;
	}

	// Not in Use This Method And Some problem in Query
	// DATE: 19 JULY 2013 HARI SHANKER BANK MODIFY METHOD Not in Use
	/*public List getBanksForBlock(LocationVO locationVO) throws Exception {
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String result = null;
		BankVO propertyModel = new BankVO();
		List finalList = new ArrayList();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = "SELECT bank_code, bank_name, bank_shortname, banktype_detail, bank_level_name   "
					+ "FROM "
					+ "mst_bank x, mst_bank_type y, mst_bank_level z "
					+ "WHERE"
					+ " x.banktype_code = y.banktype_code and x.district_code like '"
					+ locationVO.getStateCode()
					+ "%' "
					+ " and z.bank_level_code = x.banklevel_code"
					+ "  union "
					+ "    select bank_code,bank_name,bank_shortname,banktype_detail,bank_level_name   "
					+ " FROM mst_bank x ,mst_bank_type y,mst_bank_level z where x.banktype_code = y.banktype_code "
					+ " AND z.bank_level_code = x.banklevel_code "
					+ " AND banklevel_code = '1' ORDER BY bank_name";

			stmt = con.prepareStatement(query);

			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new BankVO();
				propertyModel.setBankCode(rs.getString("bank_code"));
				propertyModel.setBankName(rs.getString("bank_name"));
				propertyModel.setBankShortName(rs.getString("bank_shortname"));
				propertyModel.setBankTypeCode(rs.getString("banktype_detail"));
				propertyModel
						.setbanklLevelName(rs.getString("bank_level_name"));
				finalList.add(propertyModel);
			}
			con.commit();
		} catch (Exception e) {
			// transaction.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}
		return finalList;
	}*/

	public List getBanksForDistrict(LocationVO locationVO) throws Exception {
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String result = null;
		BankVO propertyModel = new BankVO();
		List finalList = new ArrayList();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = "select bank_shortname,banktype_detail,bank_level_name "
					+ "from mst_bank,mst_bank_type,mst_bank_level";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new BankVO();
				propertyModel.setBankShortName(rs.getString("bank_shortname"));
				// propertyModel.setbankTypeDetails(rs.getString("banktype_detail"));
				propertyModel
						.setbanklLevelName(rs.getString("bank_level_name"));
				finalList.add(propertyModel);
			}
			con.commit();
		} catch (Exception e) {
			// transaction.rollback();
			// shaily ends
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}
		return finalList;
	}

	public BankVO getBankDetailsBasedOnBankCode(String bankCode)
			throws SQLException {
		Connection connection = null;
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankVO bankVO = null;
		List bankDetailList = new ArrayList();
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			query = "select bank_name, bank_shortname, banktype_code, banklevel_code from mst_bank where bank_code = ?  order by bank_name";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankVO = new BankVO();
				bankVO.setBankName(rs.getString("bank_name"));
				bankVO.setBankShortName(rs.getString("bank_shortname"));
				bankVO.setBankTypeCode(rs.getString("banktype_code"));
				bankVO.setBankLevelCode(rs.getString("banklevel_code"));

			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankVO;
	}

	// Not in use this Code Action Class is Bank Action and Method Name is
	// delete
	/*public List getBankNames(LocationVO locationVO) throws SQLException {
		Connection connection = null;
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankVO bankVO = null;
		List bankList = new ArrayList();
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			// query=
			// "SELECT BANK_CODE, BANK_NAME FROM MST_BANK WHERE DISTRICT_CODE = ?";
			query = "SELECT BANK_CODE, BANK_NAME FROM MST_BANK WHERE Bank_Code in "
					+ " (SELECT BANK_CODE FROM MST_BANK_BRANCH WHERE village_code like '"
					+ locationVO.getDistrictCode() + "%' )  order by bank_name";
			stmt = connection.prepareStatement(query);
			// stmt.setString(1, locationVO.getDistrictCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankVO = new BankVO();
				bankVO.setBankCode(rs.getString("BANK_CODE"));
				bankVO.setBankName(rs.getString("BANK_NAME"));
				bankList.add(bankVO);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankList;
	}*/

	// Not in Use This method is used for Delete Bank Action Class is
	// BankAction.java and Method name is delete where this is Use
	/*public boolean delete(String[] bankCode) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();
		boolean flag = false;
		try {
			for (int i = 0; i < bankCode.length; i++) {
				String query = "DELETE FROM MST_BANK WHERE BANK_CODE='"
						+ bankCode[i].toString() + "'";

				transaction.begin();
				Query query2 = session.createSQLQuery(query);
				query2.executeUpdate();
				transaction.commit();
				flag = true;
			}
		} catch (Exception e) {
			transaction.rollback();
			log.error("Error while performing delete opeation in Bank Module. "
					+ e);
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return flag;
	}*/

	public boolean bankAbbreviationCheck(BankVO bankVO) throws Exception {
		boolean bankAbbreviationFound = false;
		Connection connection = null;
		String query = "SELECT BANK_SHORTNAME FROM MST_BANK WHERE bank_code != ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String bankAbbr = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankVO.getBankCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankAbbr = rs.getString(1);
				if (bankVO.getBankShortName().equalsIgnoreCase(bankAbbr)) {
					bankAbbreviationFound = true;
				}
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankAbbreviationFound;
	}

	public boolean bankBranchCheck(String[] bankCode) throws Exception {
		boolean bankBranchFound = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connection = PersisterImpl.getConnection();
		try {
			connection.setAutoCommit(false);
			String query = "SELECT 'X' FROM MST_BANK_BRANCH WHERE BANK_CODE = ?";
			for (int i = 0; i < bankCode.length; i++) {
				stmt = connection.prepareStatement(query);
				stmt.setString(1, bankCode[i]);
				rs = stmt.executeQuery();
				while (rs.next()) {
					bankBranchFound = true;
					break;
				}
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchFound;
	}

	// Not in Use this Method this is Declare in DataPoplatorServlet.java and
	// Method Name is getVillages but it is commented
	/*public List getVillagesForBlocks(String blockList) throws Exception {
		List finallist = new ArrayList();
		Connection connection = null;
		PreparedStatement statement = null;
		List results = new ArrayList();
		Session session = null;
		String query = "";
		Statement stmt = null;
		ResultSet rs = null;
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		Persister persister = PersisterImpl.getPersister();
		try {
			session = persister.getSession();
			query = "from com.infotech.sgsy.master.village.VillageVO as bb where bb.blockCode ='"
					+ blockList
					+ "' "
					+ " and  length(bb.villageCode)=13 order by bb.villageName  ";
			session.beginTransaction();
			results = session.createQuery(query).list();
			
			 * Query q= session.createQuery(query) .setParameter("blockList1",
			 * blockList); results= q.list();
			 
			for (int x = 0; x < results.size(); x++) {
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(((VillageVO) results.get(x))
						.getVillageCode());
				propertyModel.setPropertyValue(((VillageVO) results.get(x))
						.getVillageName());
				finalList.add(propertyModel);
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			 transaction.rollback(); 
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}*/

	// This code is used in BankBussiness.java
	public List getBankListForMord() throws Exception {
		List reults = new ArrayList();
		String query = "";
		Session session = null;

		PropertyModel propertyModel = new PropertyModel();
		List finalList = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		try {
			session = persister.getSession();

			query = "from com.infotech.sgsy.master.bank.BankVO as bb where bb.bankLevelCode='1' order by bb.bankName";
			session.beginTransaction();
			reults = session.createQuery(query).list();
			for (int x = 0; x < reults.size(); x++) {
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(((BankVO) reults.get(x)).getBankCode());
				propertyModel.setPropertyValue(((BankVO) reults.get(x)).getBankName());
				finalList.add(propertyModel);
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}

	// This code is used in BankBussiness.java
	public List getBankBranchListForMord(String bankCode) {
		List results = new ArrayList();
		Transaction transaction = null;
		String query_hql = "from com.infotech.sgsy.master.bankBranch.BankBranchVO as bb where bb.bankCode=:bankCode order by bb.bankBranchName";
		Statement stmt = null;
		ResultSet rs = null;
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		/*
		 * propertyModel.setPropertyCode("");
		 * propertyModel.setPropertyValue("Select");
		 * finalList.add(propertyModel);
		 */
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		transaction = session.getTransaction();
		try {
			// session=PersisterImpl.getSessionFactory().getCurrentSession();
			transaction.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("bankCode", bankCode);
			results = query.list();
			/*
			 * Query q= session.createQuery(query) .setParameter("bankCode1",
			 * bankCode); results= q.list();
			 */
			for (int x = 0; x < results.size(); x++) {
				if (results.get(x) != null) {
					propertyModel = new PropertyModel();
					propertyModel.setPropertyCode(((BankBranchVO) results
							.get(x)).getBankBranchCode());
					propertyModel.setPropertyValue(((BankBranchVO) results
							.get(x)).getBankBranchName());
					finalList.add(propertyModel);
				}
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally{
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}

	public String getBankShortName(String bankCode) throws SQLException {
		Connection connection = null;
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String bankShortName = "";
		// BankVO bankVO=null;
		// List bankDetailList = new ArrayList();
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			query = "select bank_shortname from mst_bank where bank_code = ? ";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bankShortName = rs.getString("bank_shortname");

			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankShortName;
	}

	// DATE: 19 JULY 2013 HARI SHANKER BANK MODIFY METHOD
	public List getBankModificationList(String ENTITY_CODE) throws Exception {

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname, banktype_detail, bank_level_name "
				+ " FROM mst_bank b, mst_bank_type bt, mst_bank_level bl "
				+ " WHERE b.banktype_code = bt.banktype_code AND b.entity_code = ? AND b.banklevel_code = bl.bank_level_code ";

		List<BankVO> finalList = new ArrayList<BankVO>();
		List QueryList = new ArrayList();
		try {
			tr.begin();
			Query query = session.createSQLQuery(QUERY_SQL);
			query.setParameter(0, ENTITY_CODE);
			QueryList = query.list();

			for (int i = 0; i < QueryList.size(); i++) {
				Object[] arr = (Object[]) QueryList.get(i);
				BankVO propertyModel = new BankVO();
				propertyModel.setBankCode(arr[0].toString());
				propertyModel.setBankName(arr[1].toString());
				propertyModel.setBankShortName(arr[2].toString());
				propertyModel.setBankTypeCode(arr[3].toString());
				propertyModel.setbanklLevelName(arr[4].toString());
				finalList.add(propertyModel);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankModificationList(String ENTITY_CODE) FUNCTION: "
					+ e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}

	// DATE: 19 JULY 2013 VIEW BANK LIST HARI SHANKER
	public List getBankViewList(String ENTITY_CODE) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String QUERY_SQL = null;

		if (ENTITY_CODE.length() == 1 && ENTITY_CODE.equals("0")) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname, banktype_detail, bank_level_name "
					+ " FROM mst_bank b ,mst_bank_type bt, mst_bank_level bl "
					+ " WHERE b.banktype_code = bt.banktype_code "
					+ " AND b.banklevel_code = bl.bank_level_code ORDER BY bank_name";
		} else if (ENTITY_CODE.length() == 2) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname, banktype_detail, bank_level_name "
					+ " FROM mst_bank b ,mst_bank_type bt, mst_bank_level bl "
					+ " WHERE b.banktype_code = bt.banktype_code "
					+ " AND b.banklevel_code = bl.bank_level_code AND ( b.entity_code='0' OR b.entity_code LIKE ? ) ORDER BY bank_name";

		} else if (ENTITY_CODE.length() == 4) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname, banktype_detail, bank_level_name "
					+ " FROM mst_bank b ,mst_bank_type bt, mst_bank_level bl "
					+ " WHERE b.banktype_code = bt.banktype_code "
					+ " AND b.banklevel_code = bl.bank_level_code AND ( b.entity_code = '0' OR b.entity_code = ? OR b.entity_code = ? ) ORDER BY bank_name";
		}
		List<BankVO> finalList = new ArrayList<BankVO>();
		List QueryList = new ArrayList();
		try {
			tr.begin();
			Query query = session.createSQLQuery(QUERY_SQL);

			if (ENTITY_CODE.length() == 2) {
				query.setParameter(0, ENTITY_CODE + "%");
			} else if (ENTITY_CODE.length() == 4) {
				query.setParameter(0, ENTITY_CODE.subSequence(0, 2));
				query.setParameter(1, ENTITY_CODE);
			}

			QueryList = query.list();
			for (int i = 0; i < QueryList.size(); i++) {
				Object[] arr = (Object[]) QueryList.get(i);
				BankVO propertyModel = new BankVO();
				propertyModel.setBankCode(arr[0].toString());
				propertyModel.setBankName(arr[1].toString());
				propertyModel.setBankShortName(arr[2].toString());
				propertyModel.setBankTypeCode(arr[3].toString());
				propertyModel.setbanklLevelName(arr[4].toString());
				finalList.add(propertyModel);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankViewList FUNCTION: " + e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}

	// DATE 19 JULY 2013 BANK BRANCH VIEW HARI SHANKER
	// FUNCTION USED TO GET THE BANK DETAIL FOR VIEW
	public List getBankListForDropDown(String ENTITY_CODE) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List QueryList = new ArrayList();
		String QUERY_SQL = null;

		if (ENTITY_CODE.length() == 1) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname "
					+ " FROM mst_bank b " + " ORDER BY bank_name";
		} else if (ENTITY_CODE.length() == 2) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname "
					+ " FROM mst_bank b "
					+ " WHERE b.entity_code LIKE ? OR b.entity_code='0' ORDER BY bank_name";
		} else if (ENTITY_CODE.length() == 4) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname "
					+ " FROM mst_bank b "
					+ " WHERE b.entity_code = ? OR b.entity_code= ? OR b.entity_code='0' ORDER BY bank_name";
		}
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("SELECT");
		finalList.add(propertyModel);

		try {
			tr.begin();
			Query query = session.createSQLQuery(QUERY_SQL);

			if (ENTITY_CODE.length() == 2) {
				query.setParameter(0, ENTITY_CODE);
			} else if (ENTITY_CODE.length() == 4) {
				query.setParameter(1, ENTITY_CODE + "%");
				query.setParameter(0, ENTITY_CODE.subSequence(0, 2));
			}

			QueryList = query.list();
			for (int x = 0; x < QueryList.size(); x++) {
				Object obj1[] = (Object[]) QueryList.get(x);
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(obj1[0].toString());
				propertyModel.setPropertyValue(obj1[1].toString());
				finalList.add(propertyModel);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankListForDropDown FUNCTION: "
					+ e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}

	// DATE 19 JULY 2013 BANK BRANCH ADD HARI SHANKER
	public List getBankListForBanchAdd(String ENTITY_CODE) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List QueryList = new ArrayList();
		String QUERY_SQL = null;

		if (ENTITY_CODE.length() == 1) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname "
					+ " FROM mst_bank b "
					+ " WHERE b.entity_code = ? ORDER BY bank_name";
		} else if (ENTITY_CODE.length() == 2) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname "
					+ " FROM mst_bank b "
					+ " WHERE b.entity_code = ? OR b.entity_code='0' ORDER BY bank_name";
		} else if (ENTITY_CODE.length() == 4) {
			QUERY_SQL = "SELECT bank_code, bank_name, bank_shortname "
					+ " FROM mst_bank b "
					+ " WHERE b.entity_code = ? OR b.entity_code= ? OR b.entity_code='0' ORDER BY bank_name";
		}
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("SELECT");
		finalList.add(propertyModel);
		try {
			tr.begin();
			Query query = session.createSQLQuery(QUERY_SQL);

			if (ENTITY_CODE.length() == 1) {
				query.setParameter(0, ENTITY_CODE);
			} else if (ENTITY_CODE.length() == 2) {
				query.setParameter(0, ENTITY_CODE);
			} else if (ENTITY_CODE.length() == 4) {
				query.setParameter(1, ENTITY_CODE);
				query.setParameter(0, ENTITY_CODE.subSequence(0, 2));
			}

			QueryList = query.list();
			for (int x = 0; x < QueryList.size(); x++) {
				Object obj1[] = (Object[]) QueryList.get(x);
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(obj1[0].toString());
				propertyModel.setPropertyValue(obj1[1].toString());
				finalList.add(propertyModel);
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Problem in getBankListForBanchAdd FUNCTION: "
					+ e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return finalList;
	}

	// Not in Use This Method in this Project
	// FUNCTION USED TO GET THE BANK DETAIL FOR BRANCH MODIFY
	/*public List getBankListForBanchModify(String ENTITY_CODE) throws Exception {
		List reults = new ArrayList();
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			if (ENTITY_CODE != null) {
				con = PersisterImpl.getConnection();
				con.setAutoCommit(false);
				query = "SELECT DISTINCT b.* FROM mst_bank b, mst_bank_branch br"
						+ " WHERE br.entity_code ='"
						+ ENTITY_CODE
						+ "' AND br.bank_code = b.bank_code"
						+ " ORDER BY b.bank_name";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				while (rs.next()) {
					BankVO bankVo = new BankVO();
					bankVo.setBankCode(rs.getString("bank_code"));
					bankVo.setBankName(rs.getString("bank_name"));
					reults.add(bankVo);
				}
				con.commit();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}
		return reults;
	}*/
}
