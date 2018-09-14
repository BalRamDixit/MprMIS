package com.infotech.sgsy.userManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.block.BlockDAO;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.PropertyModel;
import com.infotech.sgsy.util.SGSYConstants;

				
public class UserDAOImpl implements UserDAO{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	public int delete(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(MasterVO masterVO) throws Exception {
		Connection con = null;
		int duplicateCount = 0;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_INSERT_USER = "insert into  mst_user(user_cd,user_name," +
			"level_cd,user_design,login_id,email_id,password,entity_code,active_flag,login_status,created_by,created_on,login_attempt,mobile) " +
		"values (nextval('user_master_code_seq'),?,?,?,?,?,md5(?),?,?,'Active',?,?,'0',?)";
		
		
		/*String SQL_INSERT_USER = "insert into  mst_user(user_cd,user_name," +
		
					"level_cd,user_design,login_id,email_id,password,entity_code,active_flag) " +
					"values (nextval('user_master_code_seq'),?,?,?,?,?,md5(?),?,?)";*/
		
		log.info("inside insert method");
		
		try{		
		con =PersisterImpl.getConnection();		
		con.setAutoCommit(false);
		UserVO userVO = (UserVO)masterVO;										
		stm = con.prepareStatement(SQL_INSERT_USER);			
		stm.setString(1,userVO.getUserName());
		stm.setString(2,userVO.getLevelCode());
		stm.setString(3,userVO.getUserDesignation());
		stm.setString(4,userVO.getLoginId());
		stm.setString(5,userVO.getEmailId());
		stm.setString(6,userVO.getPassword());
		stm.setString(7,userVO.getEntityCode());
		stm.setString(8,userVO.getActiveFlag());
		stm.setString(9,userVO.getCreatedBy());
		stm.setDate(10,new java.sql.Date(DateUtil.getPresentDate().getTime()));
		stm.setString(11,userVO.getMobile());
	    stm.executeUpdate();
	    con.commit();
		 	
		}catch(SQLException e){
			e.printStackTrace();
			log.info("DuplicateRecord:" + e);			
			if(e.getSQLState().equals(Constants.DUPLICATE_RECORD_ERROR ))				
				duplicateCount = Integer.parseInt(Constants.DUPLICATE_RECORD_ERROR );
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			log.error("Exception:" + e);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return duplicateCount;
		
	}

	
	public String userNameCheck(String loginId) throws Exception {	
		Connection connection=null;	
		String query= "SELECT password FROM MST_user WHERE login_id = ?";	
		PreparedStatement stmt=null;	
		ResultSet rs=null;	
		String password = null;
		
		try	{
			connection=PersisterImpl.getConnection();		
			connection.setAutoCommit(false);			
			stmt=connection.prepareStatement(query);		
			stmt.setString(1, loginId);		
			rs=stmt.executeQuery();
			
			while(rs.next()){
				password = rs.getString("password");			
			}			
			connection.commit();			
		}catch (Exception e) {			
			log.info("Error While getting password" + e.getMessage());		
		} finally {
			CommonUtils.closeDatabaseUtil(stmt, connection, rs);
		}
		return password;
	}
	 
	public int updateDetail(MasterVO masterVO) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		String sqlUpdateUser = "UPDATE mst_user SET user_design=?, email_id=?, last_modified_by=?, last_modified_on=?," +
				" login_attempt='0',login_status='Active', mobile=?, active_flag=? WHERE login_id=?";
		
		log.info("inside update method");
		
		try{		
				con =PersisterImpl.getConnection();		
				con.setAutoCommit(false);
				UserVO userVO = (UserVO)masterVO;										
				stm = con.prepareStatement(sqlUpdateUser);		
				stm.setString(1,userVO.getUserDesignation());
				stm.setString(2,userVO.getEmailId());
				stm.setString(3,userVO.getLastModifedBy());
				stm.setDate(4,new java.sql.Date(DateUtil.getPresentDate().getTime()));
				stm.setString(5,userVO.getMobile());
				stm.setString(6,userVO.getActiveFlag());
				stm.setString(7,userVO.getLoginId());			
				stm.executeUpdate();
				con.commit(); 	
		
		}catch(SQLException e){
				log.error("SQLException:" + e);
				//throw e;			
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return Constants.UPDATE_SUCCESS;
	}	
	
	public int update(MasterVO masterVO) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sqlUpdateUser = "UPDATE mst_user SET user_name=?, user_design=?, email_id=?, active_flag=?, last_modified_by=?, " +
				" last_modified_on=?, login_attempt='0', login_status='Active', password=md5(?) WHERE login_id=?";
		log.info("inside update method");

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			UserVO userVO = (UserVO) masterVO;
			stm = con.prepareStatement(sqlUpdateUser);
			stm.setString(1, userVO.getUserName());
			stm.setString(2, userVO.getUserDesignation());
			stm.setString(3, userVO.getEmailId());
			stm.setString(4, userVO.getActiveFlag());
			stm.setString(5, userVO.getLastModifedBy());
			stm.setDate(6, new java.sql.Date(DateUtil.getPresentDate().getTime()));
			stm.setString(7, userVO.getPassword());
			stm.setString(8, userVO.getLoginId());

			stm.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			log.error("SQLException:" + e);
			// throw e;
		} catch (Exception e) {
			log.error("Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return Constants.UPDATE_SUCCESS;
	}
	
	 
	public List<MasterVO> getuserReportList() throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
	///Sushil	String SQL_SELECT_ALL_LEVEL = "select * from mst_level where level_cd in('0','2','3','4','5') order by level_cd";
		String SQL_SELECT_ALL_LEVEL = "select (select count(*) from mst_user where entity_code ='0') as MORDUSR ," +
		"(select count(*) from mst_user where level_cd ='2') as STUSR,(select count(*) from mst_user where level_cd ='3') as DSTUSR," +
		"(select count(*) from mst_user where level_cd ='4') as BLKUSR";
		 
		
		List<MasterVO> levelList = new ArrayList<MasterVO>();
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ALL_LEVEL);			
			rs = stm.executeQuery();
			while (rs.next()) {		
				
				MasterVO masterVO = new MasterVO();
				masterVO.setMordUser(rs.getString("MORDUSR"));
				masterVO.setStateUser(rs.getString("STUSR"));
				masterVO.setDistrictUser(rs.getString("DSTUSR"));
				masterVO.setBlockUser(rs.getString("BLKUSR"));
				levelList.add(masterVO);
			}		
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return levelList;		
	}

	public List<MasterVO> getLevelList(String level) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		System.out.println("  - "+level);
		// select * from mst_level where level_cd in('0','2','3','4') order by level_cd
		String SQL_SELECT_ALL_LEVEL = ""; //"select * from mst_level where level_cd in('0','2','3') order by level_cd";
		if(level.equalsIgnoreCase("0") || level.equalsIgnoreCase("1") ){
			SQL_SELECT_ALL_LEVEL = "select * from mst_level where level_cd in('0','2','3') order by level_cd";
		}if(level.equalsIgnoreCase("2")){
			SQL_SELECT_ALL_LEVEL = "select * from mst_level where level_cd ='3' order by level_cd";
		}
		/*if(level.equalsIgnoreCase("3")){
			SQL_SELECT_ALL_LEVEL = "select * from mst_level where level_cd in('3','4') order by level_cd";
		}if(level.equalsIgnoreCase("4")){
			SQL_SELECT_ALL_LEVEL = "select * from mst_level where level_cd in('4') order by level_cd";
		}*/
				
		List<MasterVO> levelList = new ArrayList<MasterVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ALL_LEVEL);			
			rs = stm.executeQuery();
			while (rs.next()) {		
				MasterVO masterVO = new MasterVO();
				masterVO.setPropertyCode((rs.getString(1)));
				masterVO.setPropertyValue((rs.getString(2)));
				levelList.add(masterVO);
			}		
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return levelList;		
	}
	 
	public List<UserIdentityVO> getStateList() throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "select state_code,state_name from mst_state order by state_name";
		
		List<UserIdentityVO> stateList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			rs = stm.executeQuery();
			while (rs.next()) {		
				
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setStateCode((rs.getString("state_code")));
				userIdentityVO.setStateName((rs.getString("state_name")));
				stateList.add(userIdentityVO);
			}	
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return stateList;
	}
	public List<UserIdentityVO> getStateNameByStateCode(String stateCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "select state_code,state_name from mst_state where state_code= ? order by state_name";		
		List<UserIdentityVO> stateList = new ArrayList<UserIdentityVO>();
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1, stateCode);
			rs = stm.executeQuery();
			while (rs.next()) {					
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setStateCode((rs.getString("state_code")));
				userIdentityVO.setStateName((rs.getString("state_name")));
				stateList.add(userIdentityVO);
			}	
			con.commit();
		} catch(SQLException e){
			log.error("Exception:" + e.getMessage());
		}catch (Exception e) {
			log.error("Exception:" + e.getMessage());
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return stateList;
	}
	
	
	

	public Map<String, Object> getEntityList(String code) throws Exception {

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String SQL_SELECT_STATE = "";

		if (code != null && code.length() == 2)
			SQL_SELECT_STATE = "select district_code,district_name from mst_district where state_code =? order by district_name";
		if (code != null && code.length() == 4) {
			SQL_SELECT_STATE = "SELECT block_code, block_name FROM mst_block WHERE block_code in (SELECT entity_code " +
					" FROM mst_block_identification WHERE entity_code LIKE ?) ORDER BY block_name";
		}
		if (code != null && code.length() == 7)
			SQL_SELECT_STATE = "select village_code,village_name from mst_village  where block_code =? order by village_name";

		Map<String, Object> entityList = new LinkedHashMap<String, Object>();

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			if (code != null && code.length() != 4)
				stm.setString(1, code);
			if (code != null && code.length() == 4)
				stm.setString(1, code + "%");

			rs = stm.executeQuery();
			while (rs.next()) {
				entityList.put(rs.getString(1), rs.getString(2));
			}
			con.commit();
			System.out.println("JAI SHRI RAM");
		} catch (SQLException e) {
			log.error("Exception:" + e);
			// throw e;
		} catch (Exception e) {
			log.error("Exception:" + e);

		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return entityList;
	}
	
	public List<UserIdentityVO> getDistrictListByStateCode(String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_STATE = "select district_code,district_name " +
				" from mst_district where state_code =? order by district_name";
		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			rs = stm.executeQuery();
			while (rs.next()) {	
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setDistrictCode((rs.getString("district_code")));
				userIdentityVO.setDistrictName((rs.getString("district_name")));
				districtList.add(userIdentityVO);				
			}	
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return districtList;
	}
	public List<UserIdentityVO> getDistrictNameByStateCode(String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_STATE = "select district_code,district_name " +
				" from mst_district where district_code =? order by district_name";
		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			rs = stm.executeQuery();
			while (rs.next()) {	
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setDistrictCode((rs.getString("district_code")));
				userIdentityVO.setDistrictName((rs.getString("district_name")));
				districtList.add(userIdentityVO);				
			}	
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return districtList;
	}
	 
	// update Date : 5 August 2013
	public List<UserIdentityVO> getBlockListByDistrictCode(String code)
			throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_STATE = "select block_code,block_name from mst_block where district_code =? order by block_name";
		
		List<UserIdentityVO> blockList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1, code);
			rs = stm.executeQuery();
			while (rs.next()) {	
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setBlockCode(rs.getString("block_code"));
				userIdentityVO.setBlockName(rs.getString("block_name"));
				blockList.add(userIdentityVO);				
			}	
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return blockList;
	}
	
	public List<UserIdentityVO> getBlockNameByDistrictCode(String code)
	throws Exception {
		
		    Connection con = null;
			PreparedStatement stm = null ;
			ResultSet rs = null ;		
			String SQL_SELECT_STATE = "select block_code,block_name " +
					" from mst_block where block_code =? order by block_name";
			List<UserIdentityVO> blockList = new ArrayList<UserIdentityVO>();
			
			try {
				con =PersisterImpl.getConnection();
				con.setAutoCommit(false);
				stm = con.prepareStatement(SQL_SELECT_STATE);
				stm.setString(1,code);
				rs = stm.executeQuery();
				while (rs.next()) {	
					UserIdentityVO userIdentityVO = new UserIdentityVO();
					userIdentityVO.setBlockCode(rs.getString("block_code"));
					userIdentityVO.setBlockName(rs.getString("block_name"));
					blockList.add(userIdentityVO);				
				}	
				con.commit();
			
			} catch(SQLException e){
				log.error("Exception:" + e);
				//throw e;
				
			}catch (Exception e) {
				log.error("Exception:" + e);		
			} finally {
				CommonUtils.closeDatabaseUtil(stm, con, rs);
			}
			return blockList;
			}												

	public UserVO showUser(String entityCode, String loginId) throws Exception {	
		
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		UserVO userVO = null;
		String sqlQueryByLoginId = "select user_name, login_id, user_design, email_id, mobile, active_flag, level_cd " +
				" from mst_user where entity_code =? and login_id =?";	
		
		log.info("inside showUser method");		
		try{		
		con =PersisterImpl.getConnection();			
		con.setAutoCommit(false);
		stm = con.prepareStatement(sqlQueryByLoginId);
		stm.setString(1,entityCode);
		stm.setString(2,loginId);
	    rs = stm.executeQuery();
	    if(rs.next()){
	    	userVO = new UserVO();
	    	userVO.setUserName(rs.getString("user_name"));
	    	userVO.setLoginId(rs.getString("login_id"));
	    	userVO.setUserDesignation(rs.getString("user_design"));
	    	userVO.setEmailId(rs.getString("email_id"));
	    	userVO.setMobile(rs.getString("mobile"));
	    	userVO.setActiveFlag(rs.getString("active_flag"));
	    	userVO.setLevelCode(rs.getString("level_cd"));    		    	
	    }
	    con.commit(); 	
		}catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;		
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}	
		return userVO;		
	}

	public List viewUser(String entityCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List userDetails = new ArrayList();
		UserVO userVO = null;
		String sqlQueryByLoginId = "select user_name,login_id,level_dtl"
				+ " from mst_user mu,mst_level ml where mu.entity_code =? and mu.active_flag='N' and mu.level_cd=ml.level_cd";

		log.info("inside viewUser method");
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(sqlQueryByLoginId);
			stm.setString(1, entityCode);
			// stm.setString(2,loginId);
			rs = stm.executeQuery();

			if (rs.next()) {
				PropertyModel propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("login_id"));
				propertyModel.setPropertyValue(rs.getString("login_id"));
				// userVO.setLevelCode(rs.getString("level_dtl"));
				userDetails.add(propertyModel);
			}
			con.commit();
		} catch (SQLException e) {
			log.error("Exception:" + e);
			// throw e;
		} catch (Exception e) {
			log.error("Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return userDetails;
	}
    
	public List getStateDetails(LocationVO locationVO) throws Exception {
		
		StateDAO stateDAO = new StateDAO();
		Connection con =PersisterImpl.getConnection();	 
		List list = new ArrayList();		
    	List stateList = stateDAO.getStates(locationVO);
       try {			
			con.setAutoCommit(false);			 
			for (int i = 0; i < stateList.size(); i++) {		
				UserVO userReportVO = new UserVO();    		
	    		userReportVO.setStateCode(((StateVO) stateList.get(i)).getStateCode());    		
	    		userReportVO.setStateName(((StateVO) stateList.get(i)).getStateName());    		
	    		userReportVO.setStateShortName(((StateVO) stateList.get(i)).getStateShortName());    		
	    		UserVO reportVO =getNumberOfUsers(userReportVO.getStateCode(),con);   		
	    		userReportVO.setNoOfUsers(reportVO.getNoOfUsers());    		
	    		list.add(userReportVO);    					
			}
    	con.commit();
    	}
    	 catch (Exception e) {
				con.rollback();
			//e.printStackTrace();
		} finally {								
			CommonUtils.closeDatabaseUtil(null, con, null);
		}return list;
	}
	
	public List getDistrictDetails(String stateCode) throws Exception {		
		DistrictDAO districtDAO = new DistrictDAO();
		Connection con =PersisterImpl.getConnection();		 
		List list = new ArrayList();		
    	List districtList = districtDAO.getDistricts(stateCode);
    	
    	try {			
			con.setAutoCommit(false);			
			for (int i = 0; i < districtList.size(); i++) {	
    		UserVO userReportVO = new UserVO();	
    		userReportVO.setDistrictCode(((DistrictVO) districtList.get(i)).getDistrictCode());		
    		userReportVO.setDistrictName(((DistrictVO) districtList.get(i)).getDistrictName());    		 
    		UserVO reportVO = getNumberOfUsers(userReportVO.getDistrictCode(),con);    		 
    		userReportVO.setStateCode(stateCode);    		
    		//userReportVO.setStateShortName(stateShortName);    		    		
    		userReportVO.setNoOfUsers(reportVO.getNoOfUsers());    		
    		list.add(userReportVO);    							
			}
    	con.commit();
    	}
    	 catch (Exception e) {
				con.rollback();
			//e.printStackTrace();
		} finally {								
			CommonUtils.closeDatabaseUtil(null, con, null);
		}		
		return list;		
	}
	
	public List getBlockDetails(String stateCode,String districtCode) throws Exception {		
		BlockDAO blockDAO = new BlockDAO();
		Connection con =PersisterImpl.getConnection();		 
		List list = new ArrayList();		
    	List blockList = blockDAO.getBlocksListByStateAndDistrict(stateCode, districtCode);
        
    	try {			
			con.setAutoCommit(false);  	
			for (int i = 0; i < blockList.size(); i++) {  		
	    		UserVO userReportVO = new UserVO();	    		
	    		userReportVO.setBlockCode(((BlockMasterVO) blockList.get(i)).getBlockCode());	    		
	    		userReportVO.setBlockName(((BlockMasterVO) blockList.get(i)).getBlockName());    			    		
	    		UserVO reportVO = getNumberOfUsers(userReportVO.getBlockCode(),con);	    		 
	    		userReportVO.setNoOfUsers(reportVO.getNoOfUsers());	    		
	    		userReportVO.setStateCode(stateCode);
	    		userReportVO.setDistrictCode(districtCode);	    		 
	    		list.add(userReportVO);	   		
			}
    	con.commit();
     	}
     	 catch (Exception e) {
 				con.rollback();
 			//e.printStackTrace();

 		} finally {					 			
 			CommonUtils.closeDatabaseUtil(null, con, null);
 		}
		return list;		
	}
	
	public UserVO getNumberOfUsers(String entityCode, Connection connection)
			throws Exception {
		PreparedStatement statement = null;
		ResultSet rs = null;
		String noOfUsers = "";
		UserVO userReportVO = new UserVO();
		String activeGroup = "select count(*) as noOfUsers from mst_user where entity_code = ?";

		try {
			statement = connection.prepareStatement(activeGroup);
			statement.setString(0, entityCode);
			rs = statement.executeQuery();
			while (rs.next())
				noOfUsers = rs.getString(1);
			userReportVO.setNoOfUsers(noOfUsers);
			return userReportVO;
		} catch (Exception e) {
			log.error("Error While getting no of users==" + e.getMessage());
			throw e;
		} finally {
			CommonUtils.closeDatabaseUtil(statement, null, rs);
		}
	}

    public Map<String, Object> getUserList(LoginVO loginVO ,String code,String level1) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
     String SQL_SELECT_STATE = "select user_cd,login_id from mst_user where  entity_code =? order by login_id";
	 	
		
		String loginId = loginVO.getUserid();
		String level = loginVO.getLevelCode();
		String levelOfUser =level1;
		
		if(level.equalsIgnoreCase(Constants.MYSA_LEVEL) || ((levelOfUser != null) && levelOfUser.equalsIgnoreCase(Constants.SAI_LEVEL))){
			//SQL_SELECT_STATE = "select user_cd,login_id from mst_user where active_flag = 'Y' and entity_code =? and level_cd =? order by login_id";
			SQL_SELECT_STATE = "select user_cd,login_id from mst_user where  entity_code =? and level_cd =? order by login_id";
			
		}else{
			//SQL_SELECT_STATE = "select user_cd,login_id from mst_user where active_flag = 'Y' and entity_code =? and login_id !=? order by login_id";
			SQL_SELECT_STATE = "select user_cd,login_id from mst_user where  entity_code =? and login_id !=? order by login_id";			
		}	
		
		Map<String,Object> userList = new LinkedHashMap<String, Object>();	
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			
			if(level.equalsIgnoreCase(Constants.MYSA_LEVEL)){
			stm.setString(1,"0");
			stm.setString(2,levelOfUser);
			
			}else{
				stm.setString(1,code);
				stm.setString(2,loginId);
			}
			rs = stm.executeQuery();
			while (rs.next()) {	
				userList.put(rs.getString("login_id"),rs.getString("login_id"));				
			}			
			con.commit();
				
		} catch(SQLException e){
			log.error("Exception:" + e);
			throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return userList;
	}

	public Map<String, Object> getUserListForReport(LoginVO loginVO,
			String code, String level1) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String SQL_SELECT_STATE = " select user_cd,login_id," +

		" case when active_flag ='Y' then 'Open' "
				+ "    when active_flag ='N' then 'Not Authorised'"
				+ "    when active_flag ='B' then 'Blocked'"
				+ " end as LoginStatus "
				+ " from  mst_user where entity_code=? ";

		// "select user_cd,login_id from mst_user where entity_code =? order by login_id";

		String loginId = loginVO.getUserid();
		String level = loginVO.getLevelCode();
		String levelOfUser = level1;

		if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)
				|| ((levelOfUser != null) && levelOfUser
						.equalsIgnoreCase(Constants.SAI_LEVEL))) {
			SQL_SELECT_STATE = SQL_SELECT_STATE + " and level_cd =? ";
		} else {
			SQL_SELECT_STATE = SQL_SELECT_STATE + " and login_id !=? ";
		}
		SQL_SELECT_STATE = SQL_SELECT_STATE + " order by login_id";
		Map<String, Object> userList = new LinkedHashMap<String, Object>();

		// System.out.println("getUserListForReport---SQL_SELECT_STATE====>>>>>"+SQL_SELECT_STATE);

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			if (level.equalsIgnoreCase(Constants.MYSA_LEVEL)) {
				stm.setString(1, code);
				stm.setString(2, levelOfUser);
			} else {
				stm.setString(1, code);
				stm.setString(2, loginId);
			}
			// System.out.println("getUserListForReport code====>>>>>"+code+" loginId "+loginId+" levelOfUser "+levelOfUser);

			rs = stm.executeQuery();

			while (rs.next()) {
				userList.put(rs.getString("login_id"),
						rs.getString("LoginStatus"));
			}
			con.commit();
		} catch (SQLException e) {
			log.error("Exception:" + e);
			throw e;

		} catch (Exception e) {
			log.error("Exception:" + e);

		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return userList;
	}
   
	public List getEntityList(String entity,String subEntity,LoginVO dto)throws Exception {
		Connection con = PersisterImpl.getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		List list=new ArrayList();
		 	
		try {		
			con.setAutoCommit(false);		
			  if(((SGSYConstants.ENTITYTYPE_MYAS).equalsIgnoreCase(dto.getEntityType())) || ((dto.getEntityType()).equalsIgnoreCase("ADM")) ){
				if(entity.equals(SGSYConstants.ENTITYTYPE_STATE)  )  {
					//stm = con.prepareStatement("select  distinct em.state_code, em.state_name from mst_assign_role ar, mst_state em where em.state_code = ar.entity_code  order by em.state_name");
					stm = con.prepareStatement("select  distinct em.state_code, em.state_name from  mst_state em  order by em.state_name");			 
				} if(entity.equals("DT")  )  {
					//stm = con.prepareStatement("select  distinct em.district_code, em.district_name from mst_assign_role ar, mst_district em where em.district_code = ar.entity_code and state_code=?  order by em.district_name");	
					stm = con.prepareStatement("select  distinct em.district_code, em.district_name from  mst_district em where state_code=?  order by em.district_name");				
					stm.setString(1,dto.getStateCode());
				 } 
				/*else if(entity.equals(SGSYConstants.ENTITYTYPE_BLOCK) )  {
					//stm = con.prepareStatement("select  distinct em.block_code, em.block_name from mst_assign_role ar, mst_block em where em.block_code = ar.entity_code and state_code=?  and  district_code=?   order by em.block_name");		
					stm = con.prepareStatement("select  distinct em.block_code, em.block_name from mst_block em where em.state_code=?  and  em.district_code=?   order by em.block_name");
					stm.setString(1,dto.getStateCode());
					stm.setString(2,dto.getDistrictCode());
			 	}*/	 
			} 
			rs = stm.executeQuery();
			while (rs.next()) {
				PropertyModel dto1= new PropertyModel();
				dto1.setPropertyCode(rs.getString(1));
				dto1.setPropertyValue(rs.getString(2));
				list.add(dto1);
			}
			con.commit();
		} catch (SQLException e) {
			con.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return list;
	}

	public List<UserIdentityVO> getVillageList(String entitycode)
			throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String SQL_SELECT_STATE = "select entity_code,name from entity_master where parent_code=? order by name";
		List<UserIdentityVO> villageList = new ArrayList<UserIdentityVO>();

		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setInt(1, Integer.parseInt(entitycode));
			rs = stm.executeQuery();
			while (rs.next()) {
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setVillageCode((rs.getString("entity_code")));
				userIdentityVO.setVillageName((rs.getString("name")));
				villageList.add(userIdentityVO);
			}
			con.commit();
		} catch (SQLException e) {
			log.error("Exception:" + e);
			// throw e;

		} catch (Exception e) {
			log.error("Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return villageList;
	}

	public List<UserIdentityVO> getDistrictPanchayatListByStateCode(
			String userId, String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_DISTRICT = "select entityMst.entity_code,entityMst.name from entity_master entityMst,mst_assign_role mstAssign "+
		" where  entityMst.entity_code=mstAssign.entity_code and entityMst.parent_code=? and mstAssign.login_id=?";		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_DISTRICT);
			stm.setInt(1,Integer.parseInt(code));
			stm.setString(2, userId);
			rs = stm.executeQuery();
			while (rs.next()) {	
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setDistrictCode((rs.getString("entity_code")));
				userIdentityVO.setDistrictName((rs.getString("name")));
				districtList.add(userIdentityVO);				
			}			
			con.commit();
		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;			
		}catch (Exception e) {
			log.error("Exception:" + e);		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return districtList;
	}
 
	public Map<String, Object> getBlockPanchayatListByDistrictCode(
			String userId, String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_STATE = "select entityMst.entity_code,entityMst.name from entity_master entityMst,mst_assign_role mstAssign "+
		" where  entityMst.entity_code=mstAssign.entity_code and entityMst.parent_code=? and mstAssign.login_id=? ";
		
		Map<String,Object> blockList = new HashMap<String, Object>();
				
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setInt(1,Integer.parseInt(code));
			stm.setString(2, userId);
			rs = stm.executeQuery();
			rs = stm.executeQuery();
			while (rs.next()) {	
				blockList.put(rs.getString("entity_code"),rs.getString("name"));				
			}			
			con.commit();
		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return blockList;
	}
	
	public Map<String, Object> getVillagePanchayatListByBlockCode(
			String userId, String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_STATE = "select entityMst.entity_code,entityMst.name from entity_master entityMst,mst_assign_role mstAssign "+
		" where  entityMst.entity_code=mstAssign.entity_code and entityMst.parent_code=? and mstAssign.login_id=? order by entityMst.name";
		
		Map<String,Object> villageList = new LinkedHashMap<String, Object>();
				
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setInt(1,Integer.parseInt(code));
			stm.setString(2, userId);
			rs = stm.executeQuery();
			rs = stm.executeQuery();
			while (rs.next()) {	
				villageList.put(rs.getString("entity_code"),rs.getString("name"));				
			}		
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return villageList;
	}
	 
	public List<UserIdentityVO> getBlockPanchayatList(
			String userId, String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_BLOCK = "select distinct entityMst.entity_code,entityMst.name from entity_master entityMst,mst_assign_role mstAssign "+
		" where  entityMst.entity_code=mstAssign.entity_code and entityMst.parent_code=? and mstAssign.login_id=?";


		
		List<UserIdentityVO> blockPanchayatList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_BLOCK);
			stm.setInt(1,Integer.parseInt(code));
			stm.setString(2, userId);
			rs = stm.executeQuery();
			while (rs.next()) {	
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setBlockCode((rs.getString("entity_code")));
				userIdentityVO.setBlockName((rs.getString("name")));
				blockPanchayatList.add(userIdentityVO);				
			}
			con.commit();

		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return blockPanchayatList;
	}
	
	public List<UserIdentityVO> getBlockPanchList(String userId, String code) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_BLOCK = "select distinct em.parent_code, em1.name from mst_assign_role ar, entity_master em, entity_master em1 "+
		" where  ar.entity_code = em.entity_code and  em.entity_type = 'VP' and em.parent_code = em1.entity_code "+
		"  and em1.parent_code=? and login_id=? ";


		
		List<UserIdentityVO> blockPanchList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_BLOCK);
			stm.setInt(1,Integer.parseInt(code));
			stm.setString(2, userId);
			rs = stm.executeQuery();
			while (rs.next()) {	
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setBlockCode((rs.getString("parent_code")));
				userIdentityVO.setBlockName((rs.getString("name")));
				blockPanchList.add(userIdentityVO);				
			}			
			con.commit();
		} catch(SQLException e){
			log.error("Exception:" + e);
			//throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return blockPanchList;
	}
	public int changeAdminPassword(UserVO vo) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		String sqlUpdateUser =" UPDATE mst_user SET password=md5(?),last_modified_by=?,last_modified_on=? where login_id=? ";
		
		log.info("inside update method");
		
		try{		
		con =PersisterImpl.getConnection();		
		con.setAutoCommit(false);
					
		stm = con.prepareStatement(sqlUpdateUser);
		
		stm.setString(1,vo.getNewpassword());
		stm.setString(2,vo.getLastModifedBy());
		stm.setDate(3,new java.sql.Date(DateUtil.getPresentDate().getTime()));
		stm.setString(4,vo.getLoginId());
		stm.executeUpdate();
		con.commit(); 	
		
		}catch(SQLException e){
			log.error("SQLException:" + e);
			//throw e;			
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return Constants.UPDATE_SUCCESS;
	}	
	
	public String getMailId(UserVO vo) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String mailId=null;
		String sqlUpdateUser =" select email_id from  mst_user  where login_id=? ";
		
		log.info("inside getMailId");
		
		try{		
		con =PersisterImpl.getConnection();		
		con.setAutoCommit(false);
					
		stm = con.prepareStatement(sqlUpdateUser);
		
		stm.setString(1,vo.getLoginId());
		rs = stm.executeQuery();
		while (rs.next()) {	
			mailId = rs.getString("email_id");
		}
		con.commit(); 	
		
		}catch(SQLException e){
			log.error("SQLException:" + e);
		//	throw e;			
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return mailId;
	}	
	
	//	FUNCTION USED TO ACTIVATE THE USED ACCOUNT
	public int activateAccount(UserVO vo) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;

		String sqlUpdateUser =" UPDATE mst_user SET login_status= 'Active', login_attempt ='0', last_modified_by=?, last_modified_on=? where login_id=? ";
		try{		
		con =PersisterImpl.getConnection();		
		con.setAutoCommit(false);
					
		stm = con.prepareStatement(sqlUpdateUser);
		stm.setString(1,vo.getLastModifedBy());
		stm.setDate(2,new java.sql.Date(DateUtil.getPresentDate().getTime()));
		stm.setString(3,vo.getLoginId());
		stm.executeUpdate();
		con.commit(); 	
		
		}catch(SQLException e){
			log.error("SQLException:" + e);
			//throw e;			
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return Constants.UPDATE_SUCCESS;
	}

}