package com.infotech.sgsy.language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.SGSYConnectionUtil;

public class LanguageDAOImpl implements LanguageDAO {

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public ArrayList getAllLanguage(LanguageVO langVO) throws Exception {
		Connection con = PersisterImpl.getConnection();
		String lang = "";
		String SQL_SELECT_Lang = null;
		ArrayList list = new ArrayList();
		SQL_SELECT_Lang = "select language_id,language_name from language where language_id!='en' ";

		PreparedStatement stm = null;
		ResultSet rs = null;
		// List list = new ArrayList();

		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_Lang);

			rs = stm.executeQuery();

			while (rs.next()) {
				LanguageVO vo = new LanguageVO();
				vo.setLanguageshortname(rs.getString(1).trim());
				vo.setLanguage(rs.getString(2).trim());
				list.add(vo);

			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("LanguageDAOImpl: getLanguage method Ends======>");
		return list;
	}

	@Override
	public ArrayList getAllResources(String languageId) throws Exception {

		Connection con = PersisterImpl.getConnection();
		String SQL_SELECT_Lang = "", bundle_name = "";
		ArrayList list = new ArrayList();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);

			if (!languageId.equals("en")) {
				bundle_name = "resources_" + languageId;
				SQL_SELECT_Lang = "select m.resourceid,m.resourceDesc,r.resourceDesc from resources r,"
						+ bundle_name
						+ " m "
						+ " where m.resourceid=r.resourceid ORDER BY m.resourceDesc";
			} else {
				bundle_name = "resources";
				SQL_SELECT_Lang = "select resourceid,resourceDesc,resourceDesc from "
						+ bundle_name + " ORDER BY resourceDesc ";
			}

			stm = con.prepareStatement(SQL_SELECT_Lang);

			rs = stm.executeQuery();

			while (rs.next()) {
				LanguageVO vo = new LanguageVO();
				vo.setResourceid(rs.getString(1));
				vo.setResourcename(rs.getString(2));
				vo.setResourcenameenglish(rs.getString(3));
				vo.setResourceName1("<input type='hidden' name='resourceid1' value='"
						+ vo.getResourceid()
						+ "' /><input type='text' name='resourceName1' value='"
						+ vo.getResourcename() + "' size='50%' />");
				list.add(vo);

			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("LanguageDAOImpl: getLanguage method Ends======>");
		return list;
	}

	@Override
	public int saveResources(LanguageVO langVO) throws Exception {

		log.info("LanguageDAOImpl : saveResources method Starts ============>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int saveSucessfully = 1;
		String SQL_INSERT_LANG = "";

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			String bundle_name = "";
			if (langVO.getLanguageshortname() != null)
				if (!langVO.getLanguageshortname().equals("en")) {
					bundle_name = "resources_" + langVO.getLanguageshortname();
				} else {
					bundle_name = "resources";
				}

			for (int i = 0; i < langVO.getResourceidArr1().length; i++) {
				SQL_INSERT_LANG = "update " + bundle_name
						+ " set resourcedesc= ?  where resourceid= ? ";

				stm = con.prepareStatement(SQL_INSERT_LANG);
				stm.setString(1, langVO.getResourceNameArr1()[i]);
				stm.setString(2, langVO.getResourceidArr1()[i]);
				stm.executeUpdate();
			}
			con.commit();
			log.info("LanguageDAOImpl : saveResources method Ends ============>");

		} catch (SQLException e) {
			saveSucessfully = -1;
			log.error("Exception:" + e);
			throw e;

		} catch (Exception e) {
			log.error("Exception:" + e);
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return saveSucessfully;
	}

	@Override
	public int saveLanguage(LanguageVO langVO) throws Exception {

		log.info("LanguageDAOImpl : saveLanguage method Starts ============>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int saveSucessfully = 1;

		String SQL_INSERT_LANG = "insert into  language(language_id,language_name)"
				+ " values (?,?)";

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_INSERT_LANG);
			stm.setString(1, langVO.getLanguageshortname());
			stm.setString(2, langVO.getLanguage());
			stm.executeUpdate();

			String SQL_INSERT_RESOURCE = "insert into  resourcebundle(language_id,bundle_name)"
					+ " values (?,?)";

			stm = con.prepareStatement(SQL_INSERT_RESOURCE);
			stm.setString(1, langVO.getLanguageshortname());
			String bundle_name = langVO.getLanguageshortname();

			if (!bundle_name.equals("en"))
				bundle_name = "Resources_" + bundle_name;
			else
				bundle_name = "Resources";

			stm.setString(2, bundle_name);
			stm.executeUpdate();

			String sql_createResourceTab = "create table " + bundle_name
					+ "( resourceid character varying NOT NULL, "
					+ "resourcedesc character varying NOT NULL,CONSTRAINT "
					+ bundle_name + "_pkey PRIMARY KEY (resourceid) )";
			stm = con.prepareStatement(sql_createResourceTab);
			stm.executeUpdate();

			String SQL_INSERT_RESOURCE_TABLE = "insert into  " + bundle_name
					+ " select * from resources";
			stm = con.prepareStatement(SQL_INSERT_RESOURCE_TABLE);
			stm.executeUpdate();
			con.commit();
			log.info("LanguageDAOImpl : saveLanguage method Ends ============>");

		} catch (SQLException e) {
			saveSucessfully = -1;
			log.error("Exception:" + e);
			throw e;

		} catch (Exception e) {
			log.error("Exception:" + e);
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return saveSucessfully;
	}

	@Override
	public String getLanguage(LanguageVO langVO) throws Exception {
		Connection con = PersisterImpl.getConnection();

		String lang = "";
		String SQL_SELECT_Lang = null;

		SQL_SELECT_Lang = "select language_id,language_name from language";

		PreparedStatement stm = null;
		ResultSet rs = null;
		// List list = new ArrayList();

		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_Lang);

			rs = stm.executeQuery();

			while (rs.next()) {
				LanguageVO vo = new LanguageVO();
				vo.setLanguageshortname(rs.getString(1));

				lang = lang + "," + rs.getString(1);

				// vo.setLanguage(rs.getString(2));

				// list.add(vo);

			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("LanguageDAOImpl: getLanguage method Ends======>");
		return lang;

	}

	/**
	 * @author 53414
	 * @param languageId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List getAssignLanguage(String languageId) throws Exception {
		Connection con = PersisterImpl.getConnection();
		String SQL_SELECT_Lang = null;
		SQL_SELECT_Lang = "select language_id,state_code from language_assign where language_id= ? ";
		PreparedStatement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_Lang);
			stm.setString(1, languageId.trim());
			rs = stm.executeQuery();
			while (rs.next()) {
				LanguageVO vo = new LanguageVO();
				vo.setPropertyCode(rs.getString("language_id"));
				vo.setPropertyValue(rs.getString("state_code"));
				list.add(vo);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("LanguageDAOImpl: getLanguage method Ends======>");
		return list;

	}

	@Override
	public List getDetailsListByTitle(String titleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getFeedbackDetails(String stCode, String distCode,
			String entityLevel, String entityCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LanguageVO> getStatusList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LanguageVO> getTitleList(String param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LanguageVO> getTopicList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LanguageVO> getTopicListBYParam(String param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveFeedback(LanguageVO langVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int assignLanguagePack(LanguageVO langVO) throws Exception {
		int saveSucessfully = 1;
		log.info("LanguageDAOImpl : assignLanguagePack method Starts ============>");
		/*Connection con = null;
		PreparedStatement stm = null;
		
		String SQL_ASSIGN_LANG = "";
		String SQL_DELETE_LANG = "";

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			SQL_DELETE_LANG = "delete from language_assign where language_id = ?";
			stm = con.prepareStatement(SQL_DELETE_LANG);
			stm.setString(1, langVO.getLanguage().trim());
			stm.execute();
			for (int i = 0; i < langVO.getResourceidArr1().length; i++) {
				SQL_ASSIGN_LANG = "insert into language_assign (language_id,state_code) values ('"+ langVO.getLanguage()+ "','"
						+ langVO.getResourceidArr1()[i] + "')";
				stm = con.prepareStatement(SQL_ASSIGN_LANG);
				stm.execute();
			}
			con.commit();
			log.info("LanguageDAOImpl : assignLanguagePack method Ends ============>");

		} catch (Exception e) {
			log.error("assignLanguagePack Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, null);
		}*/
		return saveSucessfully;
	}

	@Override
	public List getLanguagesById(String stCode) throws Exception {
		List languageList = new ArrayList();
		Connection con = PersisterImpl.getConnection();
		String SQL_SELECT_Lang = null;
		SQL_SELECT_Lang = "select * from language where language_id in (select language_id from language_assign where state_code= ? )";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_Lang);
			stm.setString(1, stCode.trim());
			rs = stm.executeQuery();
			while (rs.next()) {
				LanguageVO vo = new LanguageVO();
				vo.setLanguageshortname(rs.getString(1).trim());
				vo.setLanguage(rs.getString(2).trim());
				languageList.add(vo);

			}
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in LanguageDAOImpl: getLanguagesById---" + e);
			throw e;
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		log.info("LanguageDAOImpl: getLanguagesById method Ends======>");
		return languageList;
	}

}
