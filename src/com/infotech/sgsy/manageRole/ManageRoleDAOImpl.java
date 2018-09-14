package com.infotech.sgsy.manageRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConnectionUtil;

/**
 * @author NIC 
 * @since July, 2013
 */
public class ManageRoleDAOImpl implements ManageRoleDAO{
	
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public Map<String, Object> getRoleList(String levelCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_ROLE = "";
		//SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('0') order by level_cd";
		
				
 		if (levelCode.equalsIgnoreCase("0")) {
			SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('0','2','3','4') order by level_cd";
		}
 		else if (levelCode.equalsIgnoreCase("1")) {
			SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('0','2','3','4') order by level_cd ";
		}
 		else if (levelCode.equalsIgnoreCase("2")) {

			SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('2','3','4') order by level_cd";
		
 		}else{
			
 			SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('3','4') order by level_cd";
		
 		}
		/*if (levelCode.equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {
			SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('3','4') order by level_cd";
		}
		if (levelCode.equalsIgnoreCase(Constants.BLOCK_LEVEL)) {
			SQL_SELECT_ROLE = "select level_cd,level_dtl from mst_level  where level_cd in('4') order by level_cd";			
		}*/
	
		Map<String,Object> roleList = new LinkedHashMap<String, Object>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ROLE);			
			rs = stm.executeQuery();
			while (rs.next()) {		
				roleList.put(rs.getString("level_cd"),rs.getString("level_dtl"));
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
		return roleList;
	}
	
	//This  Method Will Return the Level of Role Code.	 
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public String getLevelOfCode(String roleCode)throws Exception{
		
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String level = "";
		String SQL_SELECT_LEVEL = "select level_cd from mst_role where role_cd =?";
		
		try{
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_LEVEL);
			stm.setString(1,roleCode);
			rs  = stm.executeQuery();
			while(rs.next()){
				level = rs.getString("level_cd");			
			}
			con.commit();
		}
		catch(Exception ex){
			log.error("Exception:" + ex);
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return level;
	}

	@Override
	public int delete(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MasterVO masterVO) throws Exception {		
		return 0;
	}

	@Override
	public int update(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public Map<String, Object> getUserList(String code,HttpServletRequest request) throws Exception {
		/*if(code.length() == 1) {
			code = "0" + code;
		}*/
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "select user_cd,login_id from mst_user where entity_code =? order by login_id";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");		
		String loginId = loginVO.getUserid();
		String level = loginVO.getLevelCode();
		String levelOfUser = request.getParameter("level");
		
		if(level.equalsIgnoreCase(Constants.MYSA_LEVEL) || ((levelOfUser != null) && levelOfUser.equalsIgnoreCase(Constants.SAI_LEVEL))){
			SQL_SELECT_STATE = "select user_cd,login_id from mst_user where entity_code =? and level_cd =? order by login_id";
		}else{
			SQL_SELECT_STATE = "select user_cd,login_id from mst_user where entity_code =? and login_id !=? order by login_id";
		}		
		

		Map<String,Object> userList = new LinkedHashMap<String, Object>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			
			if(level.equalsIgnoreCase(Constants.MYSA_LEVEL)){
				stm.setString(1,code);
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

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public Map<String, Object> getRole(String levelCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_ROLE = "select role_cd,role_name,role_desc from mst_role where level_cd=? order by role_name";
		

		Map<String,Object> roleList = new LinkedHashMap<String, Object>();
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ROLE);
			stm.setString(1,levelCode);
			rs = stm.executeQuery();
			while (rs.next()) {		
				roleList.put(rs.getString("role_cd"),rs.getString("role_name")+" (" + rs.getString("role_desc")+ ")");
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
		return roleList;
	}
	
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public Map<String, Object> getRole(String levelCode, String Type) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_ROLE = "select role_cd,role_name,role_desc from mst_role where level_cd=? AND role_name not like '%ADMIN%' order by role_name";
		

		Map<String,Object> roleList = new LinkedHashMap<String, Object>();
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ROLE);
			stm.setString(1,levelCode);
			rs = stm.executeQuery();
			while (rs.next()) {		
				roleList.put(rs.getString("role_cd"),rs.getString("role_name")+" (" + rs.getString("role_desc")+ ")");
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
		return roleList;
	}

	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public int assignRoleToUser(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception {
		Connection con = null;		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		int duplicateRecord = 0;
		
		String SQL_ASSIGN_ROLE_USER = "insert into  mst_assign_role(login_id,role_cd," +
		"entity_code,level_cd,level_role,created_by,created_on)values (?,?,?,?,?,?,?)";
			
		log.info("inside assignRoleToUser method");		
		try{		
		con =PersisterImpl.getConnection();
		stm = con.prepareStatement(SQL_ASSIGN_ROLE_USER);
		con.setAutoCommit(false);
		
			if (manageRoleVO.getLevelCode().trim().equalsIgnoreCase(manageRoleVO.getLevelCodeA().trim())) {
				String entityCodeRole = "";
				LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
				String entityCodes = loginVO.getEntityCode();
				
				
				if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.MYSA_LEVEL)){
					entityCodeRole = manageRoleVO.getLevelCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}
				if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.SAI_LEVEL)){
						entityCodeRole = Constants.SAI_ENTITY_CODE;
				}
				if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.STATE_LEVEL)){
					entityCodeRole = manageRoleVO.getStateCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
					entityCodeRole = manageRoleVO.getDistrictCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.BLOCK_LEVEL)){
					entityCodeRole = manageRoleVO.getBlockCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.VILLAGE_LEVEL)){
					entityCodeRole = manageRoleVO.getVillageCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}
				stm.setString(1, manageRoleVO.getLoginId());
				stm.setString(2, manageRoleVO.getRoleCode());
				stm.setString(3, entityCodeRole);
				stm.setString(4, manageRoleVO.getLevelCode());
				stm.setString(5, manageRoleVO.getLevelCodeA());
				stm.setString(6, manageRoleVO.getCreatedBy());
				stm.setDate(7,new java.sql.Date(DateUtil.getPresentDate().getTime()));
				
				stm.executeUpdate();
				con.commit();

			}else{ 
				
				String []selectedList = null;		
				
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.VILLAGE_LEVEL)){
					selectedList = request.getParameterValues("selectedVillageM");
				}				
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.BLOCK_LEVEL)){
					selectedList = request.getParameterValues("selectedBlockM");
				}
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
					selectedList = request.getParameterValues("selectedDistrictM");
				}
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.STATE_LEVEL)){
					selectedList = request.getParameterValues("selectedStateM");
				}
				for (String selectValues : selectedList) {
					if (selectValues != "") {
					stm.setString(1, manageRoleVO.getLoginId());
					stm.setString(2, manageRoleVO.getRoleCode());
					stm.setString(3, selectValues);
					stm.setString(4, manageRoleVO.getLevelCode());
					stm.setString(5, manageRoleVO.getLevelCodeA());
					stm.setString(6, manageRoleVO.getCreatedBy());
					stm.setDate(7,new java.sql.Date(DateUtil.getPresentDate().getTime()));
					stm.executeUpdate();
					}					
				}
				con.commit();
			}							 	
		}catch(SQLException e){
			log.error("Exception:" + e);
			if(e.getSQLState().equals(Constants.DUPLICATE_RECORD_ERROR ))				
				duplicateRecord = Integer.parseInt(Constants.DUPLICATE_RECORD_ERROR );			
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			if(con!=null){
				con.rollback();
			}
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return duplicateRecord;
	}

	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public String checkMultipleRole(ManageRoleVO manageRoleVO,HttpServletRequest request) throws Exception {
		Connection con = null;		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String entityName = "";	
		String SQL_SELECT_ASSIGN_ROLE = "select login_id  from mst_assign_role where  login_id=? and entity_code=?";
		
		log.info("inside insert method");		
		try{		
		con =PersisterImpl.getConnection();
		con.setAutoCommit(false);
		stm = con.prepareStatement(SQL_SELECT_ASSIGN_ROLE);
		
			if (manageRoleVO.getLevelCode().trim().equalsIgnoreCase(manageRoleVO.getLevelCodeA().trim())) {
				String entityCodeRole = "";
				LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
				String entityCodes = loginVO.getEntityCode();
				
				if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.MYSA_LEVEL)){
					entityCodeRole = manageRoleVO.getLevelCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}
				if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.SAI_LEVEL)){
					entityCodeRole = Constants.SAI_ENTITY_CODE;
				}				
				if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.STATE_LEVEL)){
					entityCodeRole = manageRoleVO.getStateCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
					entityCodeRole = manageRoleVO.getDistrictCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
					
				}if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.BLOCK_LEVEL)){
					entityCodeRole = manageRoleVO.getBlockCode();
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}if(manageRoleVO.getLevelCode().equalsIgnoreCase(Constants.VILLAGE_LEVEL)){
					entityCodeRole = manageRoleVO.getVillageCode();	
					if(entityCodeRole == null || entityCodeRole =="" ){
						entityCodeRole = entityCodes;
					}
				}
				
				stm.setString(1, manageRoleVO.getLoginId());				
				stm.setString(2,entityCodeRole.trim());				
				rs = stm.executeQuery();
				if(rs.next()){
					entityName = getEntityName(entityCodeRole);
				}
				con.commit();
			}else{		
				String []selectedList = null;		
				
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.VILLAGE_LEVEL)){
					selectedList = request.getParameterValues("selectedVillageM");
				}				
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.BLOCK_LEVEL)){
					selectedList = request.getParameterValues("selectedBlockM");
				}
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.DISTRICT_LEVEL)){
					selectedList = request.getParameterValues("selectedDistrictM");
				}
				if(manageRoleVO.getLevelCodeA().trim().equalsIgnoreCase(Constants.STATE_LEVEL)){
					selectedList = request.getParameterValues("selectedStateM");
				}
								
				for (String selectValues : selectedList) {
					if (selectValues != "") {
						stm.setString(1, manageRoleVO.getLoginId());
						stm.setString(2, selectValues);

						rs = stm.executeQuery();
						if (rs.next()) {
							entityName = getEntityName(selectValues);
						}
					}
				}
				con.commit();
			}				
		}catch(SQLException e){
			log.error("Exception:" + e);
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{
			if(con!=null){
				con.rollback();
			}
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return entityName;
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public String getEntityName(String entityCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_NAME = "select state_name from mst_state where state_code=?";
		String  entityName = "";		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_NAME);
			stm.setString(1,entityCode);
			rs = stm.executeQuery();
			if (rs.next()) {		
				entityName = rs.getString("state_name");
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
		return entityName;
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<ManageRoleVO> getRoleByLoginId(String loginId) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		List<ManageRoleVO> roleList = new ArrayList<ManageRoleVO>();
		String SQL_SELECT_ROLE = "select distinct assign.login_id,role.role_cd,role.role_name," +
				"role.role_desc,role.level_cd,assign.level_cd  from  mst_role as role,mst_assign_role as assign " +
				"where role.role_cd=assign.role_cd	and assign.login_id=? ORDER BY role.level_cd";
		
			
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ROLE);
			stm.setString(1,loginId);
			rs = stm.executeQuery();
			while (rs.next()) {
				ManageRoleVO manageRoleVO = new ManageRoleVO();
				manageRoleVO.setLoginId(rs.getString(1));
				manageRoleVO.setRoleCode(rs.getString(2));
				manageRoleVO.setRoleName(rs.getString(3));
				manageRoleVO.setRoleDesc(rs.getString(4));
				manageRoleVO.setLevelCodeA(rs.getString(5));
				manageRoleVO.setLevelCode(rs.getString(6));
				roleList.add(manageRoleVO);
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
		return roleList;
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public ManageRoleVO getNameByCode(ManageRoleVO roleVO) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String StateName = "";
		String districtName = "";
		String blockName = "";
		String villageName = "";
		String levelName = "";
		String levelNameRole = "";
		String roleName = "";
		log.info("inside getNameByCode method");
		
		ManageRoleVO manageRoleVO = new ManageRoleVO();
		String sqlQueryForEntityName = "";
		String sqlQueryForLevelName = "select level_dtl from mst_level where level_cd=?";
		String sqlQueryForRoleName = "select role_name from mst_role where role_cd =?";
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			

			if (roleVO.getStateCode() != null && roleVO.getStateCode() != "") {
				sqlQueryForEntityName = "select state_name from mst_state where state_code=?";
				stm = con.prepareStatement(sqlQueryForEntityName);
				stm.setString(1, roleVO.getStateCode());
				rs = stm.executeQuery();
				if (rs.next()) {
					StateName = rs.getString(1);
				}
			}

			if (roleVO.getDistrictCode() != null
					&& roleVO.getDistrictCode() != "") {
				sqlQueryForEntityName = "select district_name from mst_district where district_code=?";
				stm = con.prepareStatement(sqlQueryForEntityName);
				stm.setString(1, roleVO.getDistrictCode());
				rs = stm.executeQuery();
				if (rs.next()) {
					districtName = rs.getString(1);
				}
			}

			if (roleVO.getBlockCode() != null && roleVO.getBlockCode() != "") {
				sqlQueryForEntityName = "select block_name from mst_block where block_code=?";
				stm = con.prepareStatement(sqlQueryForEntityName);
				stm.setString(1, roleVO.getBlockCode());
				rs = stm.executeQuery();
				if (rs.next()) {
					blockName = rs.getString(1);
				}
			}
			if (roleVO.getVillageCode() != null
					&& roleVO.getVillageCode() != "") {
				sqlQueryForEntityName = "select village_name from mst_village where village_code=?";
				stm = con.prepareStatement(sqlQueryForEntityName);
				stm.setString(1,roleVO.getVillageCode());
				rs = stm.executeQuery();
				if (rs.next()) {
					villageName = rs.getString(1);
				}
			}
			
			stm = con.prepareStatement(sqlQueryForLevelName);
			if (roleVO.getLevelCode() != null && roleVO.getLevelCode() != "") {
				stm.setString(1, roleVO.getLevelCode());
				rs = stm.executeQuery();
				if (rs.next()) {
					levelName = rs.getString(1);
				}
			}
			
			stm = con.prepareStatement(sqlQueryForLevelName);
			if (roleVO.getLevelCodeA() != null && roleVO.getLevelCodeA() != "") {
				stm.setString(1, roleVO.getLevelCodeA());
				rs = stm.executeQuery();
				if (rs.next()) {
					levelNameRole = rs.getString(1);
				}
			}
			
			stm = con.prepareStatement(sqlQueryForRoleName);
			if (roleVO.getRoleCode() != null && roleVO.getRoleCode() != "") {
				stm.setString(1, roleVO.getRoleCode());
				rs = stm.executeQuery();
				if (rs.next()) {
					roleName = rs.getString(1);
				}
			}
			con.commit();
			manageRoleVO.setStateName(StateName);
			manageRoleVO.setDistrictName(districtName);
			manageRoleVO.setBlockName(blockName);
			manageRoleVO.setVillageName(villageName);
			manageRoleVO.setLevelCodeName(levelName);
			manageRoleVO.setRoleName(roleName);
			manageRoleVO.setLevelCodeAName(levelNameRole);

		} catch (SQLException e) {
			log.error("Exception:" + e);
			throw e;

		} catch (Exception e) {
			log.error("Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return manageRoleVO;
	}

	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public Map<String, Object> getEntityList(String code, String loginId,String roleCode)throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;	
		String levelCode=code.length()+"";
		String SQL_SELECT_STATE = "( select em.state_code,em.state_name from mst_state as em " +
				"where em.parent_code =? order by em.name ) except ( select distinct em.state_code,em.state_name from " +
				"mst_state as em,mst_assign_role as ar where em.state_code=ar.entity_code and " +
				" em.state_code =? and ar.login_id=? and ar.role_cd=? order by em.state_name)";	
					if (levelCode.equalsIgnoreCase(Constants.MYSA_LEVEL)) {							
						SQL_SELECT_STATE = "select level_cd,level_dtl from mst_level  where level_cd in('0','2','3','4') order by level_cd";
					}
					if (levelCode.equalsIgnoreCase(Constants.SAI_LEVEL)) {						
						SQL_SELECT_STATE = "(select em.state_code,em.state_name from mst_state as em " +
						"where em.state_code =? order by em.state_name ) except ( select distinct em.state_code,em.state_name from " +
						"mst_state as em,mst_assign_role as ar where em.state_code=ar.entity_code and " +
						" em.state_code =? and ar.login_id=? and ar.role_cd=? order by em.state_name)";
					}
					if (levelCode.equalsIgnoreCase(Constants.STATE_LEVEL)) {		
						SQL_SELECT_STATE = "(select em.district_code,em.district_name from mst_district as em " +
						"where em.district_code like '"+code+"%' order by em.district_name ) except ( select distinct em.district_code,em.district_name from " +
						"mst_district as em,mst_assign_role as ar where em.district_code=ar.entity_code and " +
						" em.district_code like '"+code+"%' and ar.login_id=? and ar.role_cd=? order by em.district_name)";
					}
					if (levelCode.equalsIgnoreCase("4")) {		
						SQL_SELECT_STATE = "(select em.block_code,em.block_name from mst_block as em " +
						"where em.block_code like '"+code+"%' order by em.block_name ) except ( select distinct em.block_code,em.block_name from " +
						"mst_block as em,mst_assign_role as ar where em.block_code=ar.entity_code and " +
						" em.block_code like '"+code+"%'and ar.login_id=? and ar.role_cd=? order by em.block_name)";
					}
					if (levelCode.equalsIgnoreCase("7")) {	
						SQL_SELECT_STATE = "(select em.village_code,em.village_name from mst_village as em " +
						"where em.village_code like '"+code+"%' order by em.village_name ) except ( select distinct em.village_code,em.village_name from " +
						"mst_village as em,mst_assign_role as ar where em.village_code=ar.entity_code and " +
						" em.village_code like '"+code+"%' and ar.login_id=? and ar.role_cd=? order by em.village_name)";
					}

		Map<String,Object> entityList = new LinkedHashMap<String, Object>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,loginId);
			stm.setString(2,roleCode);
			rs = stm.executeQuery();
			while (rs.next()) {	
				entityList.put(rs.getString(1),rs.getString(2));				
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
		return entityList;
	}	
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public Map<String, Object> getSelectedEntityList(String code,String loginId,String roleCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String SQL_SELECT_STATE = "";	
		String levelCode=code.length()+"";
    	
		if (levelCode.equalsIgnoreCase(Constants.MYSA_LEVEL)) {	
			SQL_SELECT_STATE = "select level_cd,level_dtl from mst_level  where level_cd in('0','2','3','4') order by level_cd";
		}
		if (levelCode.equalsIgnoreCase(Constants.SAI_LEVEL)) {			
			SQL_SELECT_STATE = "";			
		}

		if (levelCode.equalsIgnoreCase(Constants.STATE_LEVEL)) {			
			SQL_SELECT_STATE = "(select distinct em.district_code,em.district_name from mst_district as em,"+
			"mst_assign_role as ar where em.district_code=ar.entity_code and  em.district_code like '"+code+"%' and"+
			" ar.login_id=? and ar.role_cd=? order by em.district_name)";
		}		
		if (levelCode.equalsIgnoreCase("4")) {
			SQL_SELECT_STATE = "select distinct em.block_code,em.block_name from mst_block as em," +
			"mst_assign_role as ar where em.block_code=ar.entity_code and  " +
			"em.block_code like '"+code+"%' and ar.login_id=? and ar.role_cd=? order by em.block_name";
		}
		if (levelCode.equalsIgnoreCase("7")) {
			SQL_SELECT_STATE = "select distinct em.village_code,em.village_name from mst_village as em," +
			"mst_assign_role as ar where em.village_code=ar.entity_code and  " +
			"em.village_code like '"+code+"%' and ar.login_id=? and ar.role_cd=? order by em.village_name";
		}
		Map<String,Object> entityList = new LinkedHashMap<String, Object>();
		try{

		con =PersisterImpl.getConnection();
		con.setAutoCommit(false);
		stm = con.prepareStatement(SQL_SELECT_STATE);
		//stm.setString(1,"'"+code+"%'");
		
		stm.setString(1,loginId);
		stm.setString(2,roleCode);
		 
		rs = stm.executeQuery();
		while (rs.next()) {	
			entityList.put(rs.getString(1),rs.getString(2));				
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
		return entityList;
	}
	

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<UserIdentityVO> getStatesList(String code, String loginId,String roleCode)throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		String SQL_SELECT_STATE = "(select em.state_code,em.state_name from mst_state as em " +
				" order by em.state_name )  except (select distinct em.state_code,em.state_name from " +
				" mst_state as em,mst_assign_role as ar where em.state_code=ar.entity_code  and  " +
				" ar.level_cd =? and ar.login_id=? and ar.role_cd=? order by em.state_name)";
		
		List<UserIdentityVO> stateList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			stm.setString(2,loginId);
			stm.setString(3,roleCode);
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
			throw e;
		}catch (Exception e) {
			log.error("Exception:" + e);
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return stateList;
	}	

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<UserIdentityVO> getSelectedStateList(String code, String loginId,String roleCode)throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		/*String SQL_SELECT_STATE = "select distinct em.entity_code,em.name from entity_master as em," +
		"mst_assign_role as ar where em.entity_code=ar.entity_code and  " +
		"em.parent_code =? and ar.login_id=? and ar.role_cd=? order by em.name";*/
		
		String SQL_SELECT_STATE = "select distinct em.state_code,em.state_name " +
				" from mst_state as em,mst_assign_role as ar where em.state_code=ar.entity_code " +
				" and  ar.level_cd =? and ar.login_id=? and ar.role_cd=? order by em.state_name";    
		List<UserIdentityVO> stateList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			stm.setString(2,loginId);
			stm.setString(3,roleCode);
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
			throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return stateList;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<UserIdentityVO> getSelectedDistrictList(String code,String loginId, String roleCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "select distinct em.district_code,em.district_name from mst_district as em," +
		"mst_assign_role as ar where em.district_code=ar.entity_code and  " +
		"em.state_code =? and ar.login_id=? and ar.role_cd=? order by em.district_name";
		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			stm.setString(2,loginId);
			stm.setString(3,roleCode);
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
			throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return districtList;
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<UserIdentityVO> getsDistrictList(String code, String loginId,String roleCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "(select em.district_code,em.district_name from mst_district as em " +
		"where em.state_code =? order by em.district_name ) except (select distinct em.district_code,em.district_name from " +
		"mst_district as em,mst_assign_role as ar where em.district_code=ar.entity_code and " +
		" em.state_code =? and ar.login_id=? and ar.role_cd=? order by em.district_name)";
		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			stm.setString(2,code);
			stm.setString(3,loginId);
			stm.setString(4,roleCode);
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
			throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return districtList;
	}
	
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<UserIdentityVO> getSelectedBlockList(String code,String loginId, String roleCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "select distinct em.block_code,em.block_name from mst_block as em," +
		"mst_assign_role as ar where em.block_code=ar.entity_code and  " +
		"em.district_code =? and ar.login_id=? and ar.role_cd=? order by em.block_name";
		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			stm.setString(2,loginId);
			stm.setString(3,roleCode);
			rs = stm.executeQuery();
			while (rs.next()) {		
				
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setBlockCode((rs.getString("block_code")));
				userIdentityVO.setBlockName((rs.getString("block_name")));
				districtList.add(userIdentityVO);
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
		return districtList;
	}

	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public List<UserIdentityVO> getsBlockList(String code, String loginId,String roleCode) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_SELECT_STATE = "(select em.block_code,em.block_name from mst_block as em " +
		"where em.state_code =? order by em.block_name ) except (select distinct em.block_code,em.block_name from " +
		"mst_block as em,mst_assign_role as ar where em.block_code=ar.entity_code and " +
		" em.district_code =? and ar.login_id=? and ar.role_cd=? order by em.block_name)";
		
		List<UserIdentityVO> districtList = new ArrayList<UserIdentityVO>();
		
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_STATE);
			stm.setString(1,code);
			stm.setString(2,code);
			stm.setString(3,loginId);
			stm.setString(4,roleCode);
			rs = stm.executeQuery();
			while (rs.next()) {		
				UserIdentityVO userIdentityVO = new UserIdentityVO();
				userIdentityVO.setBlockCode((rs.getString("block_code")));
				userIdentityVO.setBlockName((rs.getString("block_name")));
				districtList.add(userIdentityVO);
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
		return districtList;
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public int delete(String code, String loginId) throws Exception {
		Connection con = null;		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		int count = 0;	
		String SQL_ASSIGN_ROLE_USER = "delete from mst_assign_role where login_id=? AND role_cd=?";
		log.info("inside delete method");		
		
		try{		
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_ASSIGN_ROLE_USER);			
			stm.setString(1,loginId);
			stm.setString(2,code);			
			count = stm.executeUpdate();
			con.commit();
			
		}catch(SQLException e){
			log.error("Exception:" + e);				
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{			
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		return count;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	@Override
	public int modify(String selectedValue [],Map<String, Object> selectedMap,List<UserIdentityVO> selectedStateValues, ManageRoleVO manageRoleVO,HttpServletRequest request)	throws Exception {
		Connection con = null;		
		PreparedStatement stm = null ;
		PreparedStatement stmSelected = null ;
		ResultSet rs = null ;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		String level = loginVO.getLevelCode();
		int deleteRecord = 0;
		int insertRecord = 0;
		int returnRecord = 0;
					
		String SQL_DELETE_ROLE_USER = "delete from mst_assign_role where login_id=? AND role_cd =? and entity_code=?";
			
		String SQL_ASSIGN_ROLE_USER = "insert into  mst_assign_role(login_id,role_cd," +
		"entity_code,level_cd,level_role,created_by,created_on)values (?,?,?,?,?,?,?)";
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			if (level.equalsIgnoreCase(Constants.MYSA_LEVEL) && manageRoleVO.getLevelCodeA().equalsIgnoreCase(Constants.STATE_LEVEL)) {				
				Iterator itrMajor = (Iterator) selectedStateValues.iterator();
				while (itrMajor.hasNext()) {
					UserIdentityVO userIdentityVO = (UserIdentityVO) itrMajor.next();
					stm = con.prepareStatement(SQL_DELETE_ROLE_USER);
					stm.setString(1, manageRoleVO.getLoginId());
					stm.setString(2, manageRoleVO.getRoleCode());
					stm.setString(3, userIdentityVO.getStateCode());					
					deleteRecord = stm.executeUpdate();
				}
			}else  if (level.equalsIgnoreCase(Constants.STATE_LEVEL) && manageRoleVO.getLevelCodeA().equalsIgnoreCase(Constants.DISTRICT_LEVEL)) {				
				Iterator itrMajor = (Iterator) selectedStateValues.iterator();

				while (itrMajor.hasNext()) {
					UserIdentityVO userIdentityVO = (UserIdentityVO) itrMajor.next();
					stm = con.prepareStatement(SQL_DELETE_ROLE_USER);
					stm.setString(1, manageRoleVO.getLoginId());
					stm.setString(2, manageRoleVO.getRoleCode());
					stm.setString(3,userIdentityVO.getDistrictCode());
					deleteRecord = stm.executeUpdate();
				}

			}else if(level.equalsIgnoreCase(Constants.DISTRICT_LEVEL) && manageRoleVO.getLevelCodeA().equalsIgnoreCase(Constants.BLOCK_LEVEL)){
				Iterator itrMajor = (Iterator) selectedStateValues.iterator();

				while (itrMajor.hasNext()) {
					UserIdentityVO userIdentityVO = (UserIdentityVO) itrMajor.next();
					stm = con.prepareStatement(SQL_DELETE_ROLE_USER);
					stm.setString(1, manageRoleVO.getLoginId());
					stm.setString(2, manageRoleVO.getRoleCode());
					stm.setString(3, userIdentityVO.getBlockCode());
					deleteRecord = stm.executeUpdate();
				}
			}
			else {
				Set<String> keyValues= selectedMap.keySet();
				Object selected[] =keyValues.toArray();	
				for (Object value1 : selected) {
					stm = con.prepareStatement(SQL_DELETE_ROLE_USER);
					stm.setString(1, manageRoleVO.getLoginId());
					stm.setString(2, manageRoleVO.getRoleCode());
					stm.setString(3, value1.toString());
					deleteRecord = stm.executeUpdate();
				}
			}
			
			if(selectedValue != null){
			for (String value : selectedValue) {
				if (value != "") {
					stmSelected = con.prepareStatement(SQL_ASSIGN_ROLE_USER);
					stmSelected.setString(1, manageRoleVO.getLoginId());
					stmSelected.setString(2, manageRoleVO.getRoleCode());
					stmSelected.setString(3, value);
					stmSelected.setString(4, manageRoleVO.getLevelCode());
					stmSelected.setString(5, manageRoleVO.getLevelCodeA());
					stmSelected.setString(6, manageRoleVO.getCreatedBy());
					stmSelected.setDate(7,new java.sql.Date(DateUtil.getPresentDate().getTime()));
					insertRecord = stmSelected.executeUpdate();
				}
			}
			}
			con.commit();
		}catch(SQLException e){
			con.rollback();
			log.error("Exception:" + e);		
		}catch (Exception e) {
			log.error("Exception:" + e);
		}
		finally{			
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}		
		if(deleteRecord != 0){
			returnRecord = deleteRecord;
		}else if(insertRecord != 0){
			returnRecord = insertRecord;
		}
		return returnRecord;
	}
	
}