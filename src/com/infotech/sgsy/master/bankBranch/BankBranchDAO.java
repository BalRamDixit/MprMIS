package com.infotech.sgsy.master.bankBranch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.PropertyModel;

import com.infotech.sgsy.util.SGSYConstants;

public class BankBranchDAO implements MasterDAO {

	protected final Log log = LogFactory.getLog(getClass());

	public List getBranchByBankForDropdown(String bankCode, String entityCode) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankBranchVO bankBranchVO = null;
		List branchList = new ArrayList();
		String query = null;
		PropertyModel propertyModel = null;
		ArrayList finalList = new ArrayList();
		try {

			entityCode = entityCode.substring(0, 4);
			connection = PersisterImpl.getConnection();
			query = " select  bank_branch_code,bank_branch_name from mst_bank_branch where "
					+ " entity_code LIKE ? and bank_code= ? order by bank_branch_name";
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, entityCode + "%");
			stmt.setString(2, bankCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("bank_branch_code"));
				propertyModel.setPropertyValue(rs.getString("bank_branch_name"));
				finalList.add(propertyModel);
			}
			connection.commit();
			return finalList;

		} catch (Exception e) {
			log.error("error occured while getting bank branch list" + e);
			return null;
		} finally {
			try {
				CommonUtils.closeDatabaseUtil(stmt, connection, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	/*public List getBranchByBankForDropdown(LocationVO locationVO) {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankBranchVO bankBranchVO = null;
		List branchList = new ArrayList();
		String query = null;
		PropertyModel propertyModel = null;
		ArrayList finalList = new ArrayList();
		try {

			String entityCode = locationVO.getBlockCode().substring(0, 4);
			connection = PersisterImpl.getConnection();
			query = " select  bank_branch_code,bank_branch_name from mst_bank_branch where village_code LIKE '"
					+ entityCode + "%'  order by bank_branch_name";
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			// stmt.setString( 1, locationVO.getBlockCode() );

			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("bank_branch_code"));
				propertyModel
						.setPropertyValue(rs.getString("bank_branch_name"));
				finalList.add(propertyModel);
			}
			connection.commit();
			return finalList;

		} catch (Exception e) {
			log.error("error occured while getting bank branch list" + e);
			return null;
		} finally {
			try {
				CommonUtils.closeDatabaseUtil(stmt, connection, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}*/

	// USED TO SAVE THE BANK BRANCH DETAIL
	public int save(BankBranchVO bankBranchVO) throws Exception {
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = " select nextval('bank_branch_seq') ";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bankBranchVO.setBankBranchCode(rs.getString(1));
			}
			con.commit();

		} catch (Exception e) {
			// transaction.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(bankBranchVO);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			log.error("error in  saveBanks Branch " + e);
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}
		return Constants.ADD_SUCCESS;
	}

	// USED TO UPDATE THE BANK BRANCH DETAIL
	public int update(BankBranchVO branchVO) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
		
		try{
			session.update(branchVO);
			tr.commit();
		}
		catch (Exception e) {
			log.error("Bank Branch update Function: "+e.getMessage());
		}
		finally{
		session.close();
		}
		return Constants.UPDATE_SUCCESS;
	}
	
	/*public static List getBankBranckById(String bankId, String branchId) {
		Persister persister = PersisterImpl.getPersister();
		Session sess = persister.getSession();
		Transaction transaction = sess.getTransaction();
		;
		transaction.begin();
		List result = null;
		Criteria criteria = null;

		try {
			criteria = sess.createCriteria(
					"com.infotech.sgsy.master.bankBranch.BankBranchVO").add(
					Restrictions.eq("bankBranchCode", branchId));
			criteria = criteria.add(Restrictions.eq("bankCode", bankId));
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
	}*/

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

	// shaily

	/*public static List getBranchUpdate(LocationVO vo) throws Exception {

		Connection connection = null;
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankBranchVO bankBranchVO = null;
		List branchList = new ArrayList();
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			query = " update  mst_bank_branch set banktype_detail=?,bank_branch_name=?,"
					+ "bank_branch_short_name=?"
					+ " where  bank_name=? and village_code like ? ";

			stmt = connection.prepareStatement(query);
			bankBranchVO.setbankTypeDetails(rs.getString("banktype_detail"));
			bankBranchVO.setBankBranchName(rs.getString("bank_branch_name"));
			bankBranchVO.setIfscCode(rs
					.getString("bank_branch_short_name"));
			bankBranchVO.setBankName(rs.getString("bank_name"));
			bankBranchVO.setEntityCode(rs.getString("entity_code"));

			stmt = connection.prepareStatement(query);
			stmt.setString(1, "'" + vo.getDistrictCode() + "%'");

			rs = stmt.executeQuery();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return branchList;
	}*/

	/*public static int checkUnique(String tableName, String columnName1,
			String columnName2, String checkValue1, String checkValue2)
			throws SQLException {

		List results = null;

		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			String query = "select * from " + tableName + " where "
					+ columnName1 + " != '" + checkValue1.trim().toUpperCase()
					+ "' and " + columnName2 + " = '"
					+ checkValue2.trim().toUpperCase() + "'";

			// if(!columnName2.equals(""))
			// query=query+" and "+columnName2+" = '"+checkValue2.trim().toUpperCase()+"'";

			// query="select * from mst_bank_branch where bank_branch_name= 'ROYALL' and bank_branch_code!='106'";

			results = persister.executeNativeQuery(query);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (results == null || results.size() == 0) {
			return SGSYConstants.VALUE_ABSENT;
		} else {
			return SGSYConstants.VALUE_PRESENT;
		}

	}*/

	

	/*public boolean updateBankDBranchWithLoanDetails(BankBranchVO branchVO)
			throws Exception {

		Persister persister = PersisterImpl.getPersister();
		Transaction transaction = persister.getTransaction();
		String query = "";
		boolean flag = false;
		try {
			transaction.begin();
			query = "UPDATE MST_BANK_BRANCH SET ADDRESS = '"
					+ branchVO.getAddress() + "' WHERE BANK_BRANCH_CODE = '"
					+ branchVO.getBankBranchCode() + "' ";
			persister.executeNativeQry(query);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			transaction.rollback();
			log.error("Error while Updating Bank Branch Record." + e);
			throw e;
		}
		return flag;
	}*/

	/*public boolean branchShortNameCheck(BankBranchVO branchVO) throws Exception {
		boolean branchShortNameFound = false;
		Connection connection = null;
		String query = "SELECT 'X' FROM mst_bank_branch WHERE bank_branch_short_name = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, branchVO.getIfscCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				branchShortNameFound = true;
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return branchShortNameFound;
	}
*/

	/*public List getBranch(LocationVO locationVO) throws Exception {
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null, stmt1 = null, stmt2 = null;
		ResultSet rs = null, rs1 = null, rs2 = null;
		String result = null, BLOCKNAMEQUERY = "", blockCode = "";
		BankBranchVO propertyModel = new BankBranchVO();
		List<BankBranchVO> finalList = new ArrayList<BankBranchVO>();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			String entityCode = "", districtCode = "";
			if (locationVO.getBlockCode() != null
					&& locationVO.getBlockCode().trim() != ""
					&& locationVO.getBlockCode().length() == 7) {
				entityCode = locationVO.getBlockCode();
			} else if (locationVO.getDistrictCode() != null
					&& locationVO.getDistrictCode().trim() != ""
					&& locationVO.getDistrictCode().length() == 4) {
				entityCode = locationVO.getDistrictCode();
			} else {
				entityCode = locationVO.getStateCode();
			}
			if (entityCode.length() == 2) {
				query = " select "
						+ " bank_name,banktype_detail,bank_branch_name,bank_branch_short_name,levelflag,village_code "
						+ " from "
						+ " mst_bank x,mst_bank_branch y,mst_bank_type z "
						+ " where " + " x.bank_code = y.bank_code "
						+ " and  x.banktype_code = z.banktype_code "
						+ " and substring(y.village_code,1,2) like '"
						+ entityCode
						+ "' order by x.bank_name,y.bank_branch_name";
			} else if (entityCode.length() == 4) {
				query = " select "
						+ " bank_name,banktype_detail,bank_branch_name,bank_branch_short_name,levelflag,village_code "
						+ " from "
						+ " mst_bank x,mst_bank_branch y,mst_bank_type z "
						+ " where " + " x.bank_code = y.bank_code "
						+ " and  x.banktype_code = z.banktype_code "
						+ " and substring(y.village_code,1,4) like '"
						+ entityCode
						+ "' order by x.bank_name,y.bank_branch_name";
			} else if (entityCode.length() == 7) {
				query = " select "
						+ " bank_name,banktype_detail,bank_branch_name,bank_branch_short_name,levelflag,village_code "
						+ " from "
						+ " mst_bank x,mst_bank_branch y,mst_bank_type z "
						+ " where " + " x.bank_code = y.bank_code "
						+ " and  x.banktype_code = z.banktype_code "
						+ " and substring(y.village_code,1,4) like '"
						+ entityCode.substring(0, 4)
						+ "' order by x.bank_name,y.bank_branch_name";
			}

			stmt = con.prepareStatement(query);

			// stmt.setString(1, "'"+entityCode+"%'");

			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new BankBranchVO();
				// propertyModel.setBankCode(rs.getString("bank_code"));
				propertyModel.setBankName(rs.getString("bank_name"));
				propertyModel.setbankTypeDetails(rs
						.getString("banktype_detail"));
				propertyModel.setBankBranchName(rs
						.getString("bank_branch_name"));
				propertyModel.setIfscCode(rs
						.getString("bank_branch_short_name"));
				propertyModel.setLevelFlag(rs.getString("levelflag"));

				if (rs.getString("levelflag").equals("Y")) {

					if (rs.getString("village_code").length() > 4) {
						blockCode = rs.getString("village_code")
								.substring(0, 7);

						BLOCKNAMEQUERY = "select  block_name,block_code FROM  mst_block  WHERE  block_code = ?";
						stmt1 = con.prepareStatement(BLOCKNAMEQUERY);

						stmt1.setString(1, blockCode);

						rs1 = stmt1.executeQuery();

						if (rs1.next()) {
							propertyModel.setBlockCode(rs1
									.getString("block_code"));
							propertyModel.setBlockName(rs1
									.getString("block_name"));
						}
					}
					// districtCode=rs.getString("village_code").substring(0,4);
					districtCode = rs.getString("village_code");

					BLOCKNAMEQUERY = "select  district_name,district_code FROM  mst_district WHERE  district_code = ?";
					stmt2 = con.prepareStatement(BLOCKNAMEQUERY);

					stmt2.setString(1, districtCode);

					rs2 = stmt2.executeQuery();

					if (rs2.next()) {
						propertyModel.setDistrictCode(rs2
								.getString("district_code"));
						propertyModel.setDistrictName(rs2
								.getString("district_name"));
					}

				} else {

					propertyModel.setBlockCode(blockCode);
					propertyModel.setBlockName("");

					districtCode = rs.getString("village_code").substring(0, 4);

					BLOCKNAMEQUERY = "select  district_name,district_code FROM  mst_district WHERE  district_code = ?";
					stmt2 = con.prepareStatement(BLOCKNAMEQUERY);

					stmt2.setString(1, districtCode);

					rs2 = stmt2.executeQuery();

					if (rs2.next()) {
						propertyModel.setDistrictCode(rs2
								.getString("district_code"));
						propertyModel.setDistrictName(rs2
								.getString("district_name"));
					}

				}

				finalList.add(propertyModel);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}

		return finalList;
	}*/

	@Override
	public int update(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	/*public List getBanks(LocationVO locationVO) throws Exception {
		BankBranchVO bankBranchVO = null;
		Connection connection = null;
		String query = "SELECT BANK_CODE, BANK_NAME FROM MST_BANK WHERE DISTRICT_CODE = ? ORDER BY BANK_NAME";
		// String query =
		// "SELECT BANK_CODE, BANK_NAME FROM MST_BANK WHERE BANK_CODE IN (SELECT BANK_CODE FROM MST_BANK_BRANCH WHERE VILLAGE_CODE LIKE '"+locationVO.getDistrictCode()+"%')";
		// select * from mst_bank where bank_code in (select bank_code from
		// mst_bank_branch where village_code like '"+entityCode+"%')
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List bankBranchList = new ArrayList();

		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, locationVO.getDistrictCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankBranchVO = new BankBranchVO();
				bankBranchVO.setBankCode(rs.getString("BANK_CODE"));
				bankBranchVO.setBankName(rs.getString("BANK_NAME"));
				bankBranchList.add(bankBranchVO);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchList;
	}*/

	public List getBankBranch(String bankCode, LocationVO locationVO)
			throws Exception {
		BankBranchVO bankBranchVO = null;
		Connection connection = null;
		String query = "SELECT VILLAGE_CODE,levelflag,BANK_BRANCH_CODE, BANK_BRANCH_NAME FROM MST_BANK_BRANCH WHERE BANK_CODE = ? and village_code LIKE ? order by BANK_BRANCH_NAME";
		PreparedStatement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		List<BankBranchVO> bankBranchList = new ArrayList<BankBranchVO>();
		String BLOCKNAMEQUERY = "", blockCode = "";
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankCode);
			stmt.setString(2, locationVO.getDistrictCode() + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankBranchVO = new BankBranchVO();
				bankBranchVO.setBankCode(bankCode);
				bankBranchVO.setBankBranchCode(rs.getString("BANK_BRANCH_CODE"));
				bankBranchVO.setBankBranchName(rs.getString("BANK_BRANCH_NAME"));
				bankBranchVO.setLevelFlag(rs.getString("levelflag"));

				if (rs.getString("levelflag").equals("Y")) {
					blockCode = rs.getString("VILLAGE_CODE").substring(0, 7);

					BLOCKNAMEQUERY = "select  block_name,block_code FROM  mst_block  WHERE  block_code =? order by block_name ";
					stmt1 = connection.prepareStatement(BLOCKNAMEQUERY);
					stmt1.setString(1, blockCode);
					rs1 = stmt1.executeQuery();

					if (rs1.next()) {
						bankBranchVO.setBlockCode(rs1.getString("block_code"));
						bankBranchVO.setBlockName(rs1.getString("block_name"));

					}
				} else {
					bankBranchVO.setBlockCode(blockCode);
					bankBranchVO.setBlockName("");
				}

				bankBranchList.add(bankBranchVO);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchList;
	}

	/*public BankBranchVO getBankBranchDetails(String branchCode,
			BankBranchVO bankBranchVO, LocationVO locationVO) throws Exception {
		Connection connection = null;
		String query = "SELECT VILLAGE_CODE, BANK_BRANCH_NAME, BANK_BRANCH_SHORT_NAME, ADDRESS,levelflag FROM MST_BANK_BRANCH WHERE BANK_BRANCH_CODE = ? and village_code LIKE ? ";
		PreparedStatement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		String BLOCKNAMEQUERY = "", blockCode = "";
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, branchCode);
			// stmt.setString(2, locationVO.getDistrictCode());
			stmt.setString(2, "" + locationVO.getDistrictCode() + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {

				bankBranchVO.setBankBranchCode(branchCode);
				bankBranchVO
						.setBankBranchName(rs.getString("BANK_BRANCH_NAME"));
				bankBranchVO.setEntityCode(rs.getString("VILLAGE_CODE"));
				bankBranchVO.setIfscCode(rs
						.getString("BANK_BRANCH_SHORT_NAME"));
				bankBranchVO.setAddress(rs.getString("ADDRESS"));
				bankBranchVO.setLevelFlag(rs.getString("levelflag"));

				if (rs.getString("levelflag").equals("Y")) {
					blockCode = rs.getString("VILLAGE_CODE").substring(0, 7);

					BLOCKNAMEQUERY = "select  block_name,block_code FROM  mst_block  WHERE  block_code =? order by block_name";
					stmt1 = connection.prepareStatement(BLOCKNAMEQUERY);
					stmt1.setString(1, blockCode);
					rs1 = stmt1.executeQuery();

					if (rs1.next()) {
						bankBranchVO.setBlockCode(rs1.getString("block_code"));
						bankBranchVO.setBlockName(rs1.getString("block_name"));

					}
				} else {
					bankBranchVO.setBlockCode(blockCode);
					bankBranchVO.setBlockName("");
				}
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchVO;
	}*/

	/*public boolean branchAbbreviationCheck(BankBranchVO bankBranchVO)
			throws Exception {
		boolean branchAbbreviationFound = false;
		Connection connection = null;
		String query = "SELECT 'X' FROM MST_BANK_BRANCH WHERE BANK_BRANCH_SHORT_NAME = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankBranchVO.getIfscCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				branchAbbreviationFound = true;
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return branchAbbreviationFound;
	}*/

	/*public boolean bankBranchCodeCheck(BankBranchVO bankBranchVO)
			throws Exception {
		boolean bankBranchCodeFound = false;
		Connection connection = null;
		String query = "SELECT 'X' FROM MST_BANK_BRANCH WHERE BANK_BRANCH_CODE = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankBranchVO.getBankBranchCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankBranchCodeFound = true;
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchCodeFound;
	}*/

	/*public String branchAbbreviationChanged(String bankBranchCode)
			throws Exception {
		String branchAbbreviation = null;
		Connection connection = null;
		String query = "SELECT BANK_BRANCH_SHORT_NAME FROM MST_BANK_BRANCH WHERE BANK_BRANCH_CODE = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankBranchCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				branchAbbreviation = rs.getString("BANK_BRANCH_SHORT_NAME");
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return branchAbbreviation;
	}*/

	/*public boolean branchNameCheck(String bankBranchCode, String branchName)
			throws Exception {
		boolean branchNameFound = false;
		Connection connection = null;
		String query = "SELECT bank_branch_name FROM MST_BANK_BRANCH WHERE BANK_BRANCH_CODE != ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankBranchCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (branchName.equalsIgnoreCase(rs.getString(1))) {
					branchNameFound = true;
				}

			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return branchNameFound;
	}*/

	/*public String bankBranchLoanDetailsCheck(BankBranchVO bankBranchVO,
			LocationVO locationVO) throws Exception {
		String bankBranchLoanDetailsFound = null;
		Connection connection = null;
		String query = "SELECT 'X' FROM " + locationVO.getStateShortName()
				+ "_MANAGELOANDETAILS WHERE BANK = ? AND BRANCH = ? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankBranchVO.getBankCode());
			stmt.setString(2, bankBranchVO.getBankBranchCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankBranchLoanDetailsFound = Constants.BANK_BRANCH_LOAN_DETAILS;
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchLoanDetailsFound;
	}*/

	/*public List bankBranchLoanDetailsCheck(String[] bankBranchCode,
			LocationVO locationVO) throws Exception {
		String bankBranchLoanDetailsFound = null;
		Connection connection = null;
		String query = "SELECT DISTINCT(BRANCH) FROM "
				+ locationVO.getStateShortName()
				+ "_MANAGELOANDETAILS WHERE BRANCH = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List bankBranchWithLoanDetails = new ArrayList();
		for (int i = 0; i < bankBranchCode.length; i++) {
			try {
				connection = PersisterImpl.getConnection();
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(query);
				stmt.setString(1, bankBranchCode[i]);
				rs = stmt.executeQuery();
				while (rs.next()) {
					bankBranchWithLoanDetails.add(rs.getString("BRANCH"));
				}
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CommonUtils.closeDatabaseUtil(stmt, connection, rs);
			}
		}
		return bankBranchWithLoanDetails;
	}*/

	/*public List bankBranchServiceAreaDetailsCheck(String[] bankBranchCode,
			LocationVO locationVO) throws Exception {

		String bankBranchServiceAreaDetailsFound = null;
		Connection connection = null;
		String query = "SELECT DISTINCT(bank_branch_code) FROM mst_bank_branch_service_area WHERE bank_branch_code = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List bankBranchWithServiceDetails = new ArrayList();
		for (int i = 0; i < bankBranchCode.length; i++) {
			try {
				connection = PersisterImpl.getConnection();
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(query);
				stmt.setString(1, bankBranchCode[i]);
				rs = stmt.executeQuery();
				while (rs.next()) {
					bankBranchWithServiceDetails.add(rs
							.getString("bank_branch_code"));
				}
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CommonUtils.closeDatabaseUtil(stmt, connection, rs);
			}
		}
		return bankBranchWithServiceDetails;
	}*/

	/*public boolean delete(String[] bankBranchCode,
			List bankBranchWithLoanDetails, List bankBranchWithServiceDetails)
			throws Exception {

		Persister persister = PersisterImpl.getPersister();
		Transaction transaction = persister.getTransaction();
		int temp = 0;
		boolean matchFound = false;
		boolean matchFound1 = false;
		boolean flag = false;
		String bankBranchWithLoan = null;
		String bankBranchWithServiceArea = null;
		if (bankBranchWithLoanDetails.size() == 0
				&& bankBranchWithServiceDetails.size() == 0) {
			for (int i = 0; i < bankBranchCode.length; i++) {
				String query = "DELETE FROM MST_BANK_BRANCH WHERE BANK_BRANCH_CODE = '"
						+ bankBranchCode[i] + "'";
				try {
					transaction.begin();
					persister.executeNativeQry(query);
					transaction.commit();
					flag = true;
				} catch (Exception e) {
					transaction.rollback();
					log.error("Error while performing delete opeation in Bank Branch Module. "
							+ e);
					throw e;
				}
			}
		}
		for (int i = 0; i < bankBranchCode.length; i++) {
			matchFound = false;
			for (int j = 0; j < bankBranchWithLoanDetails.size(); j++) {
				bankBranchWithLoan = bankBranchWithLoanDetails.get(j)
						.toString();
				if (bankBranchCode[i].equalsIgnoreCase(bankBranchWithLoan)) {
					matchFound = true;
					break;
				} else {
					matchFound = false;
				}
			}
			for (int j = 0; j < bankBranchWithServiceDetails.size(); j++) {
				bankBranchWithServiceArea = bankBranchWithServiceDetails.get(j)
						.toString();
				if (bankBranchCode[i]
						.equalsIgnoreCase(bankBranchWithServiceArea)) {
					matchFound1 = true;
					break;
				} else {
					matchFound1 = false;
				}
			}
			if (!matchFound && !matchFound1) {
				String query = "DELETE FROM MST_BANK_BRANCH WHERE BANK_BRANCH_CODE = '"
						+ bankBranchCode[i] + "'";
				try {
					transaction.begin();
					persister.executeNativeQry(query);
					transaction.commit();
					flag = true;
				} catch (Exception e) {
					transaction.rollback();
					log.error("Error while performing delete opeation in Bank Branch Module. "
							+ e);
					throw e;
				}
			}
		}
		return flag;
	}*/

	/*public List getBranchByBankForFundReceived(String bankCode) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankBranchVO bankBranchVO = null;
		List branchList = new ArrayList();
		String query = null;
		PropertyModel propertyModel = null;
		ArrayList finalList = new ArrayList();
		try {

			connection = PersisterImpl.getConnection();
			query = " select  bank_branch_code,bank_branch_name from mst_bank_branch where bank_code=?  order by bank_branch_name";
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, bankCode);

			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("bank_branch_code"));
				propertyModel
						.setPropertyValue(rs.getString("bank_branch_name"));
				finalList.add(propertyModel);
			}
			connection.commit();
			return finalList;

		} catch (Exception e) {
			log.error("error occured while getting bank branch list" + e);
			return null;
		} finally {
			try {
				CommonUtils.closeDatabaseUtil(stmt, connection, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}*/
	
//----------------------------------------------------------------------------
// 					BANK BRANCH QUERY FOR NRLM
//					DATE: 19 FEB 2013
//----------------------------------------------------------------------------
	
// USED TO GET THE BRANCH FOR MODIFICATION
	public List getBankBranchForModification(String BANK_CODE, String ENTITY_CODE) throws Exception {
		BankBranchVO bankBranchVO = null;
		Connection connection = null;
		String query = "SELECT entity_code , bank_branch_code, bank_branch_name FROM mst_bank_branch WHERE bank_code = ? and entity_code = ? order by bank_branch_name";
		PreparedStatement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		List<BankBranchVO> bankBranchList = new ArrayList<BankBranchVO>();
		String BLOCKNAMEQUERY = "", blockCode = "";
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, BANK_CODE);
			stmt.setString(2, ENTITY_CODE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankBranchVO = new BankBranchVO();
				bankBranchVO.setBankCode(BANK_CODE);
				bankBranchVO.setBankBranchCode(rs.getString("bank_branch_code"));
				bankBranchVO.setBankBranchName(rs.getString("bank_branch_name"));
				bankBranchVO.setBlockCode(blockCode);
				bankBranchVO.setBlockName("");
				bankBranchList.add(bankBranchVO);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchList;
	}
	
	// USED TO GET THE BANK BRANCH DETAIL
	public BankBranchVO getBankBranchDetail(String BRANCH_CODE, BankBranchVO bankBranchVO, String ENTITY_CODE) throws Exception {
		Connection connection = null;
		String query = "SELECT entity_code, bank_branch_name, ifsc_code, address FROM mst_bank_branch WHERE bank_branch_code = ? and entity_code = ? ";
		PreparedStatement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		String BLOCKNAMEQUERY = "", blockCode = "";
		try {
			connection = PersisterImpl.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, BRANCH_CODE);
			stmt.setString(2, ENTITY_CODE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bankBranchVO.setBankBranchCode(BRANCH_CODE);
				bankBranchVO.setBankBranchName(rs.getString("bank_branch_name"));
				bankBranchVO.setEntityCode(rs.getString("entity_code"));
				bankBranchVO.setIfscCode(rs.getString("ifsc_code"));
				bankBranchVO.setAddress(rs.getString("address"));
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return bankBranchVO;
	}
	
	// USED TO GET THE BANK BRANCH LIST 
	public List<ViewBankBranchVO> getBranchList(ViewBankBranchVO viewBankBranchVO) throws Exception {
		List<ViewBankBranchVO> viewBranchList = new ArrayList<ViewBankBranchVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();		
		try{
			tr.begin();
			Criteria crit = session.createCriteria(ViewBankBranchVO.class);
			/*crit.setMaxResults(10);*/
			if( viewBankBranchVO.getEntityCode() != null && viewBankBranchVO.getEntityCode() != ""){
				if(viewBankBranchVO.getEntityCode().length() == 2){
					crit.add(Restrictions.like("entityCode", viewBankBranchVO.getEntityCode().concat("%")));
				}else if(viewBankBranchVO.getEntityCode().length() == 4){
					crit.add(Restrictions.or(
							Restrictions.eq("entityCode", viewBankBranchVO.getEntityCode()),
							Restrictions.eq("entityCode", viewBankBranchVO.getEntityCode().substring(0, 2))));
				}							
			}
			if( viewBankBranchVO.getBankCode() != null && viewBankBranchVO.getBankCode() != ""){
				crit.add(Restrictions.eq("bankCode", viewBankBranchVO.getBankCode()));
			}
			if( viewBankBranchVO.getBankLevelCode() != null && viewBankBranchVO.getBankLevelCode() != ""){
				crit.add(Restrictions.eq("bankLevelCode", viewBankBranchVO.getBankLevelCode()));
			}
			viewBranchList = crit.list();	
			tr.commit();
		}
		catch(Exception e) {
			log.error("getBranchList(ViewBankBranchVO viewBankBranchVO): " + e.getMessage());
		}
		finally{
			session.close();
		}
		return viewBranchList;
	}
	
	/**
	 * @since 8 August 2013
	 * @param IFSCcode
	 * @return
	 * @throws Exception
	 */
	public boolean checkIfsc(String IFSCcode) throws Exception {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List result = null;
		Criteria criteria = null;
		boolean flag=false;
		try {
			tr.begin();
			Criteria cr = session.createCriteria(BankBranchVO.class);
			cr.add(Restrictions.eq("ifscCode", IFSCcode));
			if(cr.list().size()>=1)
			{
				flag = true;
			}
			else 
			{
			flag = false;
			}
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return flag;
	}

}
