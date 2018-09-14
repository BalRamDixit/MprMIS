package com.infotech.sgsy.accessControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.exception.ConnectionFailedException;
import com.infotech.sgsy.exception.DeleteFailedException;
import com.infotech.sgsy.exception.InsertFailedException;
import com.infotech.sgsy.exception.SystemFailureException;
import com.infotech.sgsy.exception.UpdateFailedException;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.SGSYConnectionUtil;


public class AccessMasterDAOImpl implements AccessMasterDAO {
	
	protected final Log log = LogFactory.getLog(getClass());
	//private static final String SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level order by level_cd";
	private static final String SQL_INSERT_ROLE = "insert into mst_role (role_cd,role_name,role_desc,level_cd,scheme_code) values(nextval('mst_role_ser_no_seq'),?,?,?,?)";
	private static final String SQL_INSERT_MODULES = "select fn_insert_roleDetail(?,?,?,?)";
	//private static final String SQL_SELECT_ROLE_NAME_LIST = "select role_cd,role_name from mst_role";
	private static final String SQL_SELECT_ROLE_NAME_LIST = "select role_cd,role_name from mst_role where level_cd=? and scheme_code=?";
	private static final String SQL_DELETE_ROLE_DETAILS = "delete from mst_role_detail where role_cd=?";
	private static final String SQL_UPDATE_MODULES = "select fn_insert_roleDetail(?,?,?,?)";
	private static final String SQL_DELETE_ROLE = "delete from mst_role where role_cd=?";
	private static final String SQL_UPDATE_ROLE_MASTER = "UPDATE mst_role SET  role_desc=? WHERE role_cd=?;";

	@Override
	public Map<String, Object> getLevelList(String levelCode) throws Exception  {
		log.info("====getLevelList method Starts======>");
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		//Sushil String SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('0','2','3','4','5') order by level_cd";
		
		String SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('0','2','3','4') order by level_cd";
		
		if(levelCode.equalsIgnoreCase("0")){
//Sushil			SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('0','1','2','3','4','5')order by level_cd";
			SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('0','1','2','3','4')order by level_cd";
		}if(levelCode.equalsIgnoreCase("2")){
//Sushil			SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('2','3','4','5') order by level_cd";
			SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('2','3','4') order by level_cd";
		}if(levelCode.equalsIgnoreCase("3")){
//Sushil			SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('3','4','5') order by level_cd";
			SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('3','4') order by level_cd";
		}
		
		Map<String,Object> list = new java.util.LinkedHashMap<String, Object> ();
		
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ALL_LEVEL);
			rs = stm.executeQuery();
			while (rs.next()) {
				list.put(rs.getString(1), rs.getString(2));
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
		log.info("====getLevelList method Ends======>");
		return list;

	}
	
	@Override
	public Map<String, Object> getSchemeList(String levelCode) throws Exception  {
		log.info("====getLevelList method Starts======>");
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		//Sushil String SQL_SELECT_ALL_LEVEL = "select level_cd,level_dtl from mst_level where level_cd in('0','2','3','4','5') order by level_cd";
		
		String SQL_SELECT_ALL_LEVEL = "select distinct scheme_cd,scheme_name from mst_module  order by scheme_cd";
		
		
		Map<String,Object> list = new java.util.LinkedHashMap<String, Object> ();
		
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ALL_LEVEL);
			rs = stm.executeQuery();
			while (rs.next()) {
				list.put(rs.getString(1), rs.getString(2));
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
		log.info("====getLevelList method Ends======>");
		return list;

	}
	
	public List selectAllSubModule(AccessVO vo) throws Exception  {
		log.info("====selectAllSubModule method Starts======>");
		Connection con = PersisterImpl.getConnection();
		String SQL_SELECT_MODULE_FORM = "SELECT MM.module_cd,MM.module_name,MFM.form_cd,form_name FROM mst_form_module MFM, mst_form_level MFL,mst_module MM WHERE MFM.form_cd = MFL.form_cd and MM.module_cd = MFM.module_cd AND MFL.level_cd=? and MM.scheme_cd=? order by MM.order,MFM.order";
		PreparedStatement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String status,Modcd="temp";
		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_MODULE_FORM);
			stm.setString(1,vo.getLevelRoleName());
			stm.setString(2,vo.getSchemeCode());
			rs = stm.executeQuery();

			while (rs.next()) {
				RoleMasterVO rvo = new RoleMasterVO();
				rvo.setModcd(rs.getString(1));
				if(Modcd.equals(rvo.getModcd()))
					status="same";
				else
					status="change";
				Modcd=rvo.getModcd();
				rvo.setModuleStatus(status);
				rvo.setModName(rs.getString(2));
				rvo.setFormcd(rs.getString(3));
				rvo.setFormName(rs.getString(4));
				rvo.setPermission(false);
				
				list.add(rvo);
			}
			con.commit();

		} catch(Exception e){
			e.printStackTrace();
			throw  e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("====selectAllSubModule method Ends======>");
		return list;

	}
	
	@Override
	public boolean checkRecord(String roleName, String roleDesc)
			throws Exception {
		log.info("====checkRecord method Starts======>");
		boolean flag=false;
		Connection con = PersisterImpl.getConnection();
		PreparedStatement stm=null;
		ResultSet rs=null;
		String rName=null;
		String rDesc=null;
		try{
			con.setAutoCommit(false);
			String query="SELECT role_name,role_desc FROM mst_role where role_name=? and role_desc=?";
			stm = con.prepareStatement(query);
			stm.setString(1,roleName);
			stm.setString(2,roleDesc);
		    rs= stm.executeQuery();
			
			//password=encrypt(password);
			if(rs.next()){
				rName=rs.getString("role_name");
				rDesc=rs.getString("role_desc");
			}
			con.commit();
			if(rName !=null && rDesc !=null){
				if(rName.equalsIgnoreCase(roleName) && rDesc.equalsIgnoreCase(roleDesc) ){
					
					flag=true;
					
				}	
			}
		log.info("====checkRecord method Ends======>");
		}catch(SQLException e){
			
			throw new SQLException();
		}
		finally{
				CommonUtils.closeDatabaseUtil(stm, con, rs);
			}
			return flag;
	}
	
	public  void addRole(AccessVO vo,List subModList) throws Exception {
		log.info("====addRole method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String roleId = "";
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			
			AccessMasterDAOImpl.insertRole(vo);
			
			String roleCode="select role_cd from mst_role where role_name=? and role_desc=? and level_cd=? and scheme_code=?";
			stm = con.prepareStatement(roleCode);
			
			stm.setString(1,vo.getRoleName());
			stm.setString(2,vo.getRoleDescription());
			stm.setString(3, vo.getLevelRoleName());
			stm.setString(4, vo.getSchemeCode());
			rs = stm.executeQuery();
			if(rs.next()){
				roleId = rs.getString("role_cd");
				
			}
			
			Iterator itr=(Iterator)subModList.iterator();
			while(itr.hasNext()){
				RoleMasterVO rvo =(RoleMasterVO)itr.next();
				if(rvo.isPermission()){
					
					AccessMasterDAOImpl.insertModule(rvo, roleId);
					
				}			
			}			
			con.commit();
			log.info("====addRole method Ends======>");
		}  catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new InsertFailedException();
			}

		} finally {
			CommonUtils.closeDatabaseUtil(null, con, null);
		}

	}
	
	public static void insertRole(AccessVO avo)	throws Exception {
		final  Log log = LogFactory.getLog(AccessMasterDAOImpl.class);
		log.info("====insertRole method Starts======>");
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
		try {
			if(con==null){
				con = PersisterImpl.getConnection();
				
			}
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_INSERT_ROLE);
			stm.setString(1,avo.getRoleName());
			stm.setString(2,avo.getRoleDescription());
			stm.setString(3, avo.getLevelRoleName());
			stm.setString(4, avo.getSchemeCode());
			stm.executeUpdate();
			con.commit();
			log.info("====insertRole method Ends======>");
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new UpdateFailedException();
		}finally {
			CommonUtils.closeDatabaseUtil(stm, con, null);
		}		
	}
	
	public static void insertModule(RoleMasterVO rvo,String roleId)	throws Exception,InsertFailedException, SystemFailureException, ConnectionFailedException {
		final  Log log = LogFactory.getLog(AccessMasterDAOImpl.class);
		log.info("====insertModule method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			if(con==null){
				con = PersisterImpl.getConnection();
				
			}
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_INSERT_MODULES);
			stm.setString(1,rvo.getModcd());
			stm.setString(2,rvo.getFormcd());			
			stm.setString(3,roleId);		
			stm.setBoolean(4,rvo.isPermission());		
			
			rs=stm.executeQuery();
			con.commit();
			log.info("====insertModule method Ends======>");
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new InsertFailedException();
		}finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}	
	}
	
	public Map<String, Object> getRoleNameList(AccessVO vo) throws Exception  {
		log.info("====getRoleNameList method Starts======>"+vo.getLevelRoleName());
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		Map<String,Object> list = new java.util.LinkedHashMap<String, Object> ();
		
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ROLE_NAME_LIST);
			stm.setString(1,vo.getLevelRoleName());
			stm.setString(2,vo.getSchemeCode());
			rs = stm.executeQuery();
			while (rs.next()) {
				list.put(rs.getString(1), rs.getString(2));
			}
			con.commit();
			log.info("====getRoleNameList method Ends======>");
		} catch(SQLException e){
			log.error("Exception:" + e);
			throw e;
			
		}catch (Exception e) {
			log.error("Exception:" + e);
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return list;

	}
	
	public List selectAllModuleSubModule(AccessVO vo) throws Exception  {
		log.info("====selectAllModuleSubModule method Starts======>"+vo.getLevelRoleName());
		Connection con = PersisterImpl.getConnection();
		String SQL_SELECT_MODULE_FORM = "select mstMod.module_cd,mstMod.module_name,mstFormMod.form_cd,mstFormMod.form_name,mstFormLevl.level_cd,mstMod.scheme_cd,mstMod.scheme_name"+
		" From mst_module mstMod,mst_form_module mstFormMod,mst_form_level mstFormLevl where mstMod.module_cd=mstFormMod.module_cd and mstFormMod.form_cd=mstFormLevl.form_cd "+
		" and mstFormLevl.level_cd=? and mstMod.scheme_cd=? order by mstMod.order,mstFormMod.order ";
		PreparedStatement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String status,Modcd="temp";
		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_MODULE_FORM);
			stm.setString(1,vo.getLevelRoleName());
			stm.setString(2,vo.getSchemeCode());
			rs = stm.executeQuery();

			while (rs.next()) {
				RoleMasterVO rvo = new RoleMasterVO();
				rvo.setModcd(rs.getString(1));
				if(Modcd.equals(rvo.getModcd()))
					status="same";
				else
					status="change";
				Modcd=rvo.getModcd();
				rvo.setModuleStatus(status);
				rvo.setModName(rs.getString(2));
				rvo.setFormcd(rs.getString(3));
				rvo.setFormName(rs.getString(4));
		 		rvo.setSchemeCode(rs.getString("scheme_cd"));
				rvo.setSchemeName(rs.getString("scheme_name"));
				rvo.setPermission(false);
				
				list.add(rvo);
			}
			con.commit();

		} catch(Exception e){
			e.printStackTrace();
			throw  e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("====selectAllModuleSubModule method Ends======>");
		return list;

	}
		
	
	public List getRoleDetailsList(AccessVO vo) throws Exception  {
		log.info("====getRoleDetailsList method Starts======>");
		Connection con = PersisterImpl.getConnection();
		/*String SQL_SELECT_ROLE_DETAILS = "select mstRole.role_cd as roleId,mstRole.role_name as roleName,mstRole.role_desc as roleDesc,mstMod.module_cd as modCode,mstMod.module_name as modName, "+
		" mstFormMod.form_cd as formCode,mstFormMod.form_name as formName,mstlvl.level_dtl as levelDtl from mst_role mstRole,mst_module mstMod,mst_form_module mstFormMod,mst_level mstlvl, "+
		" mst_form_level mstFormLvl,mst_role_detail mstRoleDtl where mstMod.module_cd=mstFormMod.module_cd and mstFormMod.form_cd=mstFormLvl.form_cd "+
		" and mstRoleDtl.module_cd=mstMod.module_cd and mstRoleDtl.form_cd=mstFormMod.form_cd and mstlvl.level_cd=mstFormLvl.level_cd and mstRole.role_cd=mstRoleDtl.role_cd "+
		" and mstRole.role_cd=? order by mstRoleDtl.module_cd ";
		*/
		String SQL_SELECT_ROLE_DETAILS =" select mstRoleDtl.module_cd as modCode,mstFormMod.form_cd as formCode,mstRole.role_name as roleName,mstRole.role_desc as roleDesc,mstMod.module_name as modName,mstFormMod.form_name as formName,mstlvl.level_dtl as levelDtl,mstMod.scheme_cd,mstMod.scheme_name "+
		" from mst_role mstRole,mst_module mstMod,mst_form_module mstFormMod,mst_level mstlvl, mst_form_level mstFormLvl,mst_role_detail mstRoleDtl "+
		" where mstMod.module_cd=mstFormMod.module_cd and mstFormMod.form_cd=mstFormLvl.form_cd and mstRoleDtl.module_cd=mstMod.module_cd and mstRoleDtl.form_cd=mstFormMod.form_cd "+
		" and mstlvl.level_cd=mstFormLvl.level_cd and mstFormLvl.level_cd =mstRole.level_cd and mstRole.role_cd=mstRoleDtl.role_cd "+
		" and mstRole.role_cd=? order by mstRoleDtl.module_cd";
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String status,Modcd="temp";
		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_SELECT_ROLE_DETAILS);
			stm.setString(1,vo.getRoleName());
			rs = stm.executeQuery();

			while (rs.next()) {
				RoleMasterVO rvo = new RoleMasterVO();
				rvo.setModcd(rs.getString("modCode"));
				if(Modcd.equals(rvo.getModcd()))
					status="same";
				else
					status="change";
				Modcd=rvo.getModcd();
				rvo.setModuleStatus(status);
				rvo.setModName(rs.getString("modName"));
				rvo.setFormcd(rs.getString("formCode"));
				rvo.setFormName(rs.getString("formName"));
				rvo.setRoleName(rs.getString("roleName"));
				rvo.setRoleDescription(rs.getString("roleDesc"));
				rvo.setLevelRoleName(rs.getString("levelDtl"));
				rvo.setSchemeCode(rs.getString("scheme_cd"));
				rvo.setSchemeName(rs.getString("scheme_name"));
				rvo.setPermission(true);
				
				list.add(rvo);
			}
			con.commit();
			
		} catch(Exception e){
			e.printStackTrace();
			throw  e;
		} finally {

			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		log.info("====getRoleDetailsList method Ends======>");
		return list;

	}
	
	@Override
	public void modifyRole(AccessVO vo, List subModList) throws Exception {
		log.info("====modifyRole method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String roleId = vo.getRoleName();
	
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			AccessMasterDAOImpl.updateRoleMaster(vo);
			AccessMasterDAOImpl.deleteModuleRoleDetails( roleId);			
			Iterator itr=(Iterator)subModList.iterator();
			while(itr.hasNext()){
				RoleMasterVO rvo =(RoleMasterVO)itr.next();
				if(rvo.isPermission()){
					//AccessMasterDAOImpl.updateModule(rvo, roleId);
					
					
					stm = con.prepareStatement(SQL_UPDATE_MODULES);
					stm.setString(1,rvo.getModcd());
					stm.setString(2,rvo.getFormcd());			
					stm.setString(3,roleId);		
					stm.setBoolean(4,rvo.isPermission());					
					rs=stm.executeQuery();
					
					
				}			
			}			
			con.commit();
			log.info("====modifyRole method Ends======>");
		}  catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new InsertFailedException();
			}

		} finally {
			CommonUtils.closeDatabaseUtil(null, con, null);
		}
	}
	
	public static void updateRoleMaster(AccessVO vo) throws  SystemFailureException, ConnectionFailedException, UpdateFailedException, DeleteFailedException, SQLException {
		final  Log log = LogFactory.getLog(AccessMasterDAOImpl.class);
		log.info("====updateRoleMaster method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String roleId = vo.getRoleName();
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_UPDATE_ROLE_MASTER);
			
			stm.setString(1,vo.getRoleDescription());
			System.out.println("==desc===>"+vo.getRoleDescription());
			stm.setString(2,roleId);
			System.out.println("==roleid===>"+roleId);
			//stm.setString(1,roleId);

			stm.executeUpdate();
			con.commit();
			log.info("====updateRoleMaster method Ends======>");
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new DeleteFailedException();
		}finally {
			
				CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
	}
	
	public static void deleteModuleRoleDetails(String roleId) throws  SystemFailureException, ConnectionFailedException, UpdateFailedException, DeleteFailedException, SQLException {
		final  Log log = LogFactory.getLog(AccessMasterDAOImpl.class);
		log.info("====deleteModuleRoleDetails method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_DELETE_ROLE_DETAILS);

			stm.setString(1,roleId);

			stm.executeUpdate();
			con.commit();
			log.info("====deleteModuleRoleDetails method Ends======>");
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new DeleteFailedException();
		}finally {
			
				CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
	}
	
	public static void updateModule(RoleMasterVO rvo,String roleId)	throws Exception,InsertFailedException, SystemFailureException, ConnectionFailedException {

		final  Log log = LogFactory.getLog(AccessMasterDAOImpl.class);
		log.info("====updateModule method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			if(con==null){
				con = PersisterImpl.getConnection();
				
			}
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_UPDATE_MODULES);
			stm.setString(1,rvo.getModcd());
			stm.setString(2,rvo.getFormcd());			
			stm.setString(3,roleId);		
			stm.setBoolean(4,rvo.isPermission());		
			
			rs=stm.executeQuery();
			con.commit();
			log.info("====updateModule method Ends======>");
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new InsertFailedException();
		}finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}	
	}
	
	public boolean checkRoleAssignedRecord(AccessVO vo)throws Exception {
		log.info("====checkRoleAssignedRecord method Starts======>");
		boolean flag=false;
		Connection con =PersisterImpl.getConnection();
		PreparedStatement stm=null;
		ResultSet rs=null;
		String roleCode=null;
		String roleId = vo.getRoleName();
		try{
			con.setAutoCommit(false);
			String query="select role_cd from mst_assign_role where role_cd=?";
			stm = con.prepareStatement(query);
			stm.setString(1,roleId);
			
			rs= stm.executeQuery();

			if(rs.next()){
				roleCode=rs.getString("role_cd");
				
			}
			con.commit();
			if(roleCode !=null ){
				if(roleCode.equalsIgnoreCase(roleId)){
	
					flag=true;
	
				}	
			}
			log.info("====checkRoleAssignedRecord method Ends======>");
		}catch(SQLException e){
	
			throw new SQLException();
		}
		finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return flag;
	}
	
	@Override
	public void deleteRole(AccessVO vo, List subModList) throws Exception {
		log.info("====deleteRole method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String roleId = vo.getRoleName();
		
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			AccessMasterDAOImpl.deleteModuleRoleDetails( roleId);	
			AccessMasterDAOImpl.deleteModuleRole( roleId);
					
			con.commit();
			log.info("====deleteRole method Ends======>");
		}  catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new InsertFailedException();
			}

		} finally {
			CommonUtils.closeDatabaseUtil(null, con, null);
		}
	}
	
	public static void deleteModuleRole(String roleId) throws  SystemFailureException, ConnectionFailedException, UpdateFailedException, DeleteFailedException, SQLException {
		final  Log log = LogFactory.getLog(AccessMasterDAOImpl.class);
		log.info("====deleteModuleRole method Starts======>");
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_DELETE_ROLE);

			stm.setString(1,roleId);

			stm.executeUpdate();
			con.commit();
			log.info("====deleteModuleRole method Ends======>");
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new DeleteFailedException();
		}finally {
			
				CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
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

	

	

	

}
