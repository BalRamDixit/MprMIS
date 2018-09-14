package com.infotech.sgsy.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.exception.PasswordNotValidException;
import com.infotech.sgsy.exception.UserAlreadyLoggedIn;
import com.infotech.sgsy.exception.UserBlockedException;
import com.infotech.sgsy.exception.UserLockedException;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.userAccessControlManagement.AccessModuleListForUserAndMenuBean;
import com.infotech.sgsy.userAccessControlManagement.FormModuleMasterBean;
import com.infotech.sgsy.userAccessControlManagement.FormModuleMasterDaoImpl;
import com.infotech.sgsy.userAccessControlManagement.FormModuleMenuBean;
import com.infotech.sgsy.userAccessControlManagement.ProjectMapping;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.DateUtil;

public class LoginMasterDAOImpl implements LoginMasterDAO {

	private static Logger log = Logger.getRootLogger();
	DateUtil util = new DateUtil();
	private static final String AUDIT_INSERT = " INSERT INTO mst_user_loginlog(user_name,ip_address,login_status) VALUES (?,?,?) ";
	private static final String SQL_LOGIN_ATTEMPT = "SELECT login_status,login_attempt FROM user_master WHERE Upper(login_id)=? ";
	private static final String SQL_UPDATE_LOGIN_ATTEMPT = "UPDATE user_master SET login_attempt=?," + "login_status =? WHERE Upper(login_id)=?";
	
	/**
	 * 
	 */
	public UserMaster login(String userId, String password, String ipAddress,
			String changeRoleFor, String trackerId) throws Exception {
		Connection con = PersisterImpl.getConnection();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		password = password.replaceAll("0", "");
		UserMaster loginVO=null;
		try {
				System.out.println("User id is --> -----"+userId+"---------");
			 	Criteria criteria = session.createCriteria(UserMaster.class).add(Restrictions.eq("loginId", userId).ignoreCase());
	            Object result = criteria.uniqueResult();
	        
				if (result!=null) {
					loginVO = (UserMaster)result;
				} 
				else{
					throw new PasswordNotValidException(); 
				}									
				if (loginVO.getLoginId() != null) {
					System.out.println("Entered Password --> "+password);
					if (password.equals(checkEncriptedSaltedMD5Password( loginVO.getPassword(), trackerId))) {
						loginVO = getLoginDetails(loginVO, userId, loginVO.getPassword(), loginVO.getLoginStatus(), changeRoleFor, loginVO.getLoginAttempt(), ipAddress,con);
					} else {
						if (changeRoleFor == null)
							loginVO = auditUserAndLoginTrailTable(userId, loginVO.getLoginStatus(), loginVO.getLoginAttempt(), ipAddress, con);
						else
							loginVO = getLoginDetails(loginVO, userId, loginVO.getPassword(), loginVO.getLoginStatus(), changeRoleFor, loginVO.getLoginAttempt(), ipAddress, con);
	
						loginVO.setLoginStatus("0");
					}
				} 
				else{
					throw new PasswordNotValidException();
				}
			con.commit();
		} catch (UserAlreadyLoggedIn e) {
			con.rollback();
			throw new UserAlreadyLoggedIn();
		} 
		catch (UserLockedException e) {
			con.rollback();
			throw new UserLockedException();
		} 
		catch (UserBlockedException e) {
			con.rollback();
			throw new UserBlockedException();
		} 
		catch (PasswordNotValidException e) {
			con.rollback();
			throw new PasswordNotValidException();
		} 
		catch (Exception e) {
			con.rollback();
		} finally {
			 session.close();
		}
		return loginVO;
	}

	public UserMaster getLoginDetails(UserMaster loginVO, String userId,
			String existingPassword, String loginStatus, String changeRoleFor,
			int loginAttempt, String ipAddress, Connection con)
			throws Exception {

		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
				int attempt = loginVO.getLoginAttempt();
				if (changeRoleFor == null){
					auditLoginStatus(loginVO.getLoginId(), loginVO.getLoginStatus(), loginVO.getLoginAttempt(), attempt, ipAddress, loginVO.getActiveFlag());
				}
		} catch (UserAlreadyLoggedIn e) {
			throw new UserAlreadyLoggedIn();
		} 
		catch (UserLockedException e) {
			con.rollback();
			throw new UserLockedException();
		}
		catch (UserBlockedException e) {
			con.rollback();
			throw new UserBlockedException();
		}
		catch (PasswordNotValidException e) {
			con.rollback();
			throw new PasswordNotValidException();
		} 
		catch (Exception e) {
			
		} finally {
			CommonUtils.closeDatabaseUtil(stm, null, rs);
		}
		return loginVO;
	}

	/**
	 * @author cvas2273 THIS METHOD COUNT USER ATTEMPT BASED ON ATTEMPT USER
	 *         SHOULD BE LOCKED OR LOGGEDIN
	 * @param userId
	 * @param loginStatus
	 * @param loginAttempt
	 * @param attempt
	 * @param connection
	 * @param statement
	 * @throws Exception
	 */
	public void auditLoginStatus(String userId, String loginStatus,
			int loginAttempt, int attempt, String ipAddress, String ACTIVE_FLAG) throws UserLockedException, Exception {

 		Connection connection = PersisterImpl.getConnection();
		PreparedStatement statement = null;
		try {
			if (attempt <= 5 && !loginStatus.equals("Logged") && !loginStatus.equals("Locked") && !"B".equalsIgnoreCase(ACTIVE_FLAG)) {
				statement = connection.prepareStatement(SQL_UPDATE_LOGIN_ATTEMPT);
				statement.setInt(1, 1);
				statement.setString(2, "Logged");
				statement.setString(3, userId);
				statement.executeUpdate();
				statement = connection.prepareStatement(AUDIT_INSERT);
				statement.setString(1, userId);
				statement.setString(2, ipAddress);
				statement.setString(3, "Logged");
				statement.executeUpdate();
				connection.commit();
			}
			
			else if (loginStatus.equals("Locked")){
				 throw new UserLockedException();
			} else if (loginStatus.equals("Logged")){
				throw new UserAlreadyLoggedIn();
			} else if("B".equalsIgnoreCase(ACTIVE_FLAG)){
				throw new UserBlockedException();
			} else{
				throw new UserLockedException();
			}
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(statement, connection, null);
		}

	}

	/**
	 * 
	 * @param userId
	 * @param loginStatus
	 * @param loginAttempt
	 * @param ipAddress
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public UserMaster auditUserAndLoginTrailTable(String userId,
			String loginStatus, int loginAttempt, String ipAddress,
			Connection connection) throws Exception {

		PreparedStatement statement = null;
		ResultSet rs = null;
		UserMaster loginVO = new UserMaster();
		try {
			statement = connection.prepareStatement(SQL_LOGIN_ATTEMPT);
			statement.setString(1, userId);
			rs = statement.executeQuery();

			while (rs.next()) {
				loginStatus = rs.getString(1);
				loginAttempt = rs.getInt(2);
			}
			loginAttempt++;
			loginVO.setLoginAttempt(loginAttempt);
			if (loginAttempt < 6 && !loginStatus.equals("Locked")) {
				if (loginStatus.equals("Logged"))
					 throw new UserAlreadyLoggedIn();
				else if(loginStatus.equals("Locked")) {
					throw new UserLockedException();
				}
				else {
					statement = connection.prepareStatement(SQL_UPDATE_LOGIN_ATTEMPT);
					statement.setInt(1, loginAttempt);
					statement.setString(2, "Active");
					statement.setString(3, userId);
					statement.executeUpdate();
					statement = connection.prepareStatement(AUDIT_INSERT);
					statement.setString(1, userId);
					statement.setString(2, ipAddress);
					statement.setString(3, "Failed");
					statement.executeUpdate();
					connection.commit();
					throw new PasswordNotValidException();
				}
			} else {
				statement = connection.prepareStatement("update user_master set login_status =?, login_attempt=? where Upper(login_id)=? ");
				statement.setString(1, "Locked");
				statement.setInt(2, loginAttempt);
				statement.setString(3, userId);
				System.out.println("update user_master set login_status =Locked, login_attempt="+loginAttempt+" where Upper(login_id)="+userId );
				statement.executeUpdate();
				statement = connection.prepareStatement(AUDIT_INSERT);
				statement.setString(1, userId);
				statement.setString(2, ipAddress);
				statement.setString(3, "Locked");
				statement.executeUpdate();		
				loginVO.setLoginStatus("blocked");
				connection.commit();
				throw new UserLockedException("Your LoginId and Password is blocked");
			}
		}
		catch (PasswordNotValidException e) {
			log.error(e.getMessage());
			throw new PasswordNotValidException();
		} 
		catch (UserLockedException e) {
			log.error(e.getMessage());
			throw new UserLockedException();
		} 
		catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			CommonUtils.closeDatabaseUtil(statement, null, rs);
		}
		return loginVO;
	}

	/**
	 * @author cvas2273
	 * @param existingPassword
	 * @param sessionSalt
	 * @return salted password & match to existing password
	 * @throws Exception
	 */
	private String checkEncriptedSaltedMD5Password(String existingPassword,
			String sessionSalt) throws Exception {

		String saltedmd5Password = existingPassword + sessionSalt;
		byte[] defaultBytes = saltedmd5Password.getBytes();

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			String foo = messageDigest.toString();
			saltedmd5Password = hexString + "";
		} catch (NoSuchAlgorithmException nsae) {
			log.info("Erroe while salting pass : " + nsae.getMessage());
		}
		saltedmd5Password = saltedmd5Password.replaceAll("0", "");
		System.out.println("DataBase Password--> "+saltedmd5Password);
		return saltedmd5Password;
	}

	
		/**
	 * 
	 */
	@Override
	public boolean checkOldPassword(String password, String userid,String trackerId)
			throws Exception {
		
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
	        Criteria criteria = session.createCriteria(UserMaster.class)
	                .add(Restrictions.eq("id", userid));

	        Object result = criteria.uniqueResult();
	        if (result != null) {
	        	String oldPassword=((UserMaster)result).getPassword();
	        	System.out.println(password+" <-------------> "+oldPassword);
				if (oldPassword != null) {
					System.out.println("Entered Password --> "+oldPassword);
					if (oldPassword.equals(password)) {
						flag = true;
					}
				}
	        }
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return flag;
	}
	
	

	/**
	 * 
	 */
	public int changePassword(String newPassword, String oldPassword,
			String userid) throws Exception {
		int flag = 0;
		Connection con = PersisterImpl.getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			String query = "update user_master set password='"+newPassword+"',login_status='Active',login_attempt='0' where  id='"+userid+"'";
			System.out.println(query);
			stm = con.prepareStatement(query);
			flag = stm.executeUpdate();
			con.commit();

		} catch (Exception e) {
			con.rollback();
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return flag;
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

		public LoginVO count(String userId) throws Exception {
		Connection con = PersisterImpl.getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		LoginVO loginVO = null;
		String SQL_COUNT = "select count(distinct role_cd) from mst_assign_role where Upper(login_id)=?";
		try {
			con.setAutoCommit(false);
			stm = con.prepareStatement(SQL_COUNT);
			stm.setString(1, userId);

			rs = stm.executeQuery();
			if (rs.next()) {
				loginVO = new LoginVO();
				loginVO.setCount(rs.getInt(1));

			}
			con.commit();
		} catch (SQLException e) {

			con.rollback();

		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return loginVO;
	}

	
	public AccessModuleListForUserAndMenuBean getAccessModuleListForMenu(UserMaster user) {
		AccessModuleListForUserAndMenuBean loginBean = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select um.user_name,um.email_id,um.role_name,um.role_desc,fmm.form_name,fmm.form_name as module_name,"
							+ " fmm.module_name as master_module,fmm.order_no,fmm.help, fmm.url,arm.permission,arm.role_id,um.id,"
							+ " um.role_id as userRoleId,fmm.id as formId,um.state_id,COALESCE(fmm.master_order_no, 0)||''||fmm.order_no from assign_role_master as arm "
							+ " left join (select a.id,a.form_name,b.id as module_name,a.url,a.order_no,b.order_no as master_order_no,a.help,"
							+ " a.module_name as master_module from form_module_master as a "
							+ " left join form_module_master as b on a.module_name=b.id) as fmm "
							+ " on arm.form_module_master_id=fmm.id left join (select um.user_name,um.state_id,um.email_id,um.role_id,"
							+ " rm.role_name,rm.role_desc,um.id from user_master as um,role_master as rm "
							+ " where rm.id='"+user.getRoleId()+"' and um.id='"+user.getId()+"') as um on "
									+ " arm.role_id=um.role_id where um.role_id='"+user.getRoleId()+"' and um.id='"+user.getId()+"'  order by COALESCE(fmm.master_order_no, 0)||''||fmm.order_no";
		System.out.println("Query for get All Modules Dynamically--> "+query_hql);
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			loginBean=new AccessModuleListForUserAndMenuBean();
			Map<String, Map<String,FormModuleMenuBean>>  moduleList=new LinkedHashMap<String, Map<String,FormModuleMenuBean>>(
					/*new Comparator<T>() {
					};*/
					);
			for(Object[] temp :(List<Object[]>) query.list()) {
				if(loginBean.getUserId()==null||loginBean.getUserId().equalsIgnoreCase("")||loginBean.getUserId().equalsIgnoreCase("null")||loginBean.getUserId().equalsIgnoreCase("0")){
					loginBean.setUserId(temp[12]+"");
				}
				if(loginBean.getUserName()==null||loginBean.getUserName().equalsIgnoreCase("")||loginBean.getUserName().equalsIgnoreCase("null")||loginBean.getUserName().equalsIgnoreCase("0")){
					loginBean.setUserName(temp[0]+"");
				}
				if(loginBean.getEmailId()==null||loginBean.getEmailId().equalsIgnoreCase("")||loginBean.getEmailId().equalsIgnoreCase("null")||loginBean.getEmailId().equalsIgnoreCase("0")){
					loginBean.setEmailId(temp[1]+"");
				}
				if(loginBean.getRoleId() ==null||loginBean.getRoleId().equalsIgnoreCase("")||loginBean.getRoleId().equalsIgnoreCase("null")||loginBean.getRoleId().equalsIgnoreCase("0")){
					loginBean.setRoleId(temp[11]+"");
				}
				if(loginBean.getRoleDesc()==null||loginBean.getRoleDesc().equalsIgnoreCase("")||loginBean.getRoleDesc().equalsIgnoreCase("null")||loginBean.getRoleDesc().equalsIgnoreCase("0")){
					loginBean.setRoleDesc(temp[3]+"");
				}
				if(loginBean.getUserRoleId()==null||loginBean.getUserRoleId().equalsIgnoreCase("")||loginBean.getUserRoleId().equalsIgnoreCase("null")||loginBean.getUserRoleId().equalsIgnoreCase("0")){
					loginBean.setUserRoleId(temp[13]+"");
				}
				if(loginBean.getStateId()==null||loginBean.getStateId().equalsIgnoreCase("")||loginBean.getStateId().equalsIgnoreCase("null")||loginBean.getStateId().equalsIgnoreCase("0")){
					loginBean.setStateId(temp[15]+"");
				}
				
				LinkedHashMap<String, FormModuleMenuBean> moduleMap=null;
				FormModuleMenuBean module=null;
				module=new FormModuleMenuBean();
				module.setFormName((temp[4]+"").toLowerCase());
				module.setHelp(temp[8]+"");
				module.setMasterModule(temp[6]+"");
				module.setModuleName(temp[5]+"");
				module.setOrderNo(temp[7]+"");
				module.setPermission(temp[10]+"");
				module.setUrl(temp[9]+"");
				
				/*System.out.println("\n\n\n----------------------------------------\n--->"+temp[6]);
				System.out.println("Data Is --> "+module);
				System.out.println("Condition for ("+temp[6]+"!=null && !("+temp[6]+").equalsIgnoreCase('0')) is --> "+(temp[6]!=null && !(temp[6]+"").equalsIgnoreCase("0")));
				System.out.println("Condition for moduleList.containsKey("+temp[6]+") is ---> "+ moduleList.containsKey(temp[6]+""));*/
				if((temp[6]!=null && !(temp[6]+"").equalsIgnoreCase("0")) ){
//					System.out.println("Condition 1 True");
					if(moduleList.containsKey(temp[6]+"")){
//						System.out.println("Condition 2 True");
						moduleMap=(LinkedHashMap)moduleList.get(temp[6]+"");
						if(moduleMap.containsKey(temp[14]+"")){
							module=moduleMap.get(temp[14]+"");
							module.setPermission(getPermission(temp[10]+"",module.getPermission()));
						}
					}
					else{
//						System.out.println("Condition 2 False");
						moduleMap=new LinkedHashMap<String,FormModuleMenuBean>();
					}
					moduleMap.put(temp[14]+"", module);
					moduleList.put(temp[6]+"", moduleMap);
				}
				else{
//					System.out.println("Condition 1 False");
					if(moduleList.containsKey(temp[14]+"")){
//						System.out.println("Condition 3 True");
						moduleMap=(LinkedHashMap)moduleList.get(temp[14]+"");
					}
					else{
//						System.out.println("Condition 3 False");
						moduleMap=new LinkedHashMap<String,FormModuleMenuBean>();
					}
					moduleMap.put(temp[14]+"", module);
					moduleList.put(temp[14]+"", moduleMap);
					
				} 
//				System.out.println(moduleList);
			}
			moduleList=getFilteredModuleList(moduleList);
			loginBean.setModuleList(moduleList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return loginBean;
	}

	private Map<String, Map<String, FormModuleMenuBean>> getFilteredModuleList(Map<String, Map<String, FormModuleMenuBean>> moduleList) {
		List<FormModuleMasterBean> masterModules= new FormModuleMasterDaoImpl().getAllMasterModuleList();
		System.out.println("List is ===> "+masterModules);
		for(FormModuleMasterBean fBean:masterModules){
			if(moduleList.containsKey(fBean.getId())){
				Map<String, FormModuleMenuBean> moduleMap=moduleList.get(fBean.getId());
				System.out.println("Module Map Is ==> "+moduleMap);
				FormModuleMenuBean formModule=moduleMap.get(fBean.getId());
				if(formModule.getPermission().startsWith("00")){
						moduleList.remove(fBean.getId());
				}
			}
		}
		return moduleList;
	}

	@Override
	public List<ProjectDetailsVO> getAssignProjectList(UserMaster loginVO) {
		List<ProjectDetailsVO> list=null;
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		if(role.getId().equalsIgnoreCase("5")){//For MORD
			list=getAllProjects();
		}
		else if(role.getId().equalsIgnoreCase("45")){ 		//FOR SRLM ADMIN
			list=getAllProjectsCreatedBy(loginVO);
		}
		else if(role.getId().equalsIgnoreCase("4")){		// FOR PIA
			list=getAllProjectsForPIA(loginVO);
		}
		else if(role.getId().equalsIgnoreCase("5001")){		// FOR CTSA
			list=getAllProjectsForCTSA(loginVO);
		}
		else if(role.getId().equalsIgnoreCase("49")|| role.getId().equalsIgnoreCase("10002") || role.getId().equalsIgnoreCase("10001") || role.getId().equalsIgnoreCase("10003")){//FOR SRLM USER ,PIA USER , MORD USER AND CTSA USER
			list=getAllProjectsCreatedByAssignTo(loginVO);
		}
		return list;
	}
	
	private List<ProjectDetailsVO> getAllProjects() {
		List<ProjectDetailsVO> list=new ArrayList<ProjectDetailsVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class);
            if(criteria.list()!=null){
	            for(Object o : criteria.list()) {
	            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
	            	
	            	if(ob.getPiaPrn()==null || ob.getState()==null){
	            		ob.setProjectID(ob.getProjectID()+" () - ");
	            	}
	            	else{
	            		ob.setProjectID(ob.getProjectID()+" ("+ob.getState().getStateName()+") - "+ob.getPiaPrn().getUserName());
	            	}
				    list.add(ob);
				}
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}

	private List<ProjectDetailsVO> getAllProjectsCreatedBy(UserMaster loginVO) {
		List<ProjectDetailsVO> list=new ArrayList<ProjectDetailsVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class).add(Restrictions.eq("state", loginVO.getStateId()));
            if(criteria.list()!=null){
	            for(Object o : criteria.list()) {
	            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
	            	ob.setProjectID(ob.getProjectID()+" ("+ob.getState().getStateName()+") - "+ob.getPiaPrn().getUserName());
				    list.add(ob);
				}
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}

	private List<ProjectDetailsVO> getAllProjectsCreatedByAssignTo(UserMaster loginVO) {
		List<ProjectDetailsVO> list=new ArrayList<ProjectDetailsVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectMapping.class).add(Restrictions.eq("userId", loginVO.getId()));
            String[] idList=new String[criteria.list().size()];
            int i=0;
            for(Object o : criteria.list()) {
            	ProjectMapping ob=(ProjectMapping)o;
            	idList[i]=ob.getProjectId();
            	i++;
			}
            Criteria criteria1 = session.createCriteria(ProjectDetailsVO.class);
            if(idList.length >=1){
	            criteria1.add(Restrictions.in("id", idList));
	            if(criteria1.list()!=null){
		            for(Object o : criteria1.list()) {
		            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
		            	ob.setProjectID(ob.getProjectID()+" ("+ob.getState().getStateName()+") - "+ob.getPiaPrn().getUserName());
					    list.add(ob);
					}
	            }
            }
            
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}

	private List<ProjectDetailsVO> getAllProjectsForPIA(UserMaster loginVO) {
		List<ProjectDetailsVO> list=new ArrayList<ProjectDetailsVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class).add(Restrictions.eq("piaPrn", loginVO));
            if(criteria.list()!=null){
	            for(Object o : criteria.list()) {
	            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
	            	ob.setProjectID(ob.getProjectID()+" ("+ob.getState().getStateName()+") - "+ob.getPiaPrn().getUserName());
				    list.add(ob);
				}
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	private List<ProjectDetailsVO> getAllProjectsForCTSA(UserMaster loginVO) {
		List<ProjectDetailsVO> list=new ArrayList<ProjectDetailsVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class).add(Restrictions.eq("ctsaMaster", loginVO.getCtsaId()));
            if(criteria.list()!=null){
	            for(Object o : criteria.list()) {
	            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
	            	ob.setProjectID(ob.getProjectID()+" ("+ob.getState().getStateName()+") - "+ob.getPiaPrn().getUserName());
				    list.add(ob);
				}
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}

	public List<ProjectDetailsVO> filterAndGetAllSanctionProject(List<ProjectDetailsVO> list) {
		List<ProjectDetailsVO> newList=new ArrayList<ProjectDetailsVO>();
		for(ProjectDetailsVO project:list){
			if(project.getSanctioned()!=null && project.getSanctioned().equalsIgnoreCase("yes")){
				newList.add(project);
			}
		}
		return newList;
	}

	public String getPermission(String newPermission, String OldPermission) {
		String permission="";
//		System.out.println(newPermission+"----"+OldPermission);
		for(int i=0;i<newPermission.length();i++){
			if(newPermission.charAt(i)==OldPermission.charAt(i)){
				permission=permission+newPermission.charAt(i);
			}
			else{
				permission=permission+"0";
			}
		}
		return permission;
	}

	
	

	
	public static void logout(String userId, String ipAddress)
			throws SQLException {
		System.out.println("LogOut Method Executed");
		Connection connection = PersisterImpl.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int loginAttempt = 0;
		String loginStatus = null;
		try {
			connection.setAutoCommit(false);
			String query="select login_status,login_attempt from user_master  where Upper(login_id)='"+userId+"'";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				loginStatus = resultSet.getString("login_status");
				loginAttempt = resultSet.getInt("login_attempt");
			}
			String updateQuery="update user_master set login_attempt=0,login_status='Active' where Upper(login_id)= '"+userId+"'";
			statement = connection.prepareStatement(updateQuery);
			statement.executeUpdate();
			System.out.println(query+"\n"+updateQuery);
			if (loginStatus != "Logout" && loginAttempt != 0) {
				statement = connection.prepareStatement(AUDIT_INSERT);
				statement.setString(1, userId);
				statement.setString(2, ipAddress);
				statement.setString(3, "Logout");
				statement.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			log.info("Error while user trying to logout" + e.getMessage());
		} finally {
			CommonUtils.closeDatabaseUtil(statement, connection, resultSet);
		}
	}

	/**
	 * 
	 * @throws SQLException
	 */
	public static void logoutForServer() throws SQLException {
		Connection connection = PersisterImpl.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("update user_master set login_attempt=?,login_status=? ");
			statement.setInt(1, 0);
			statement.setString(2, "Active");
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			log.info("Error while user trying to logout" + e.getMessage());
		} finally {
			CommonUtils.closeDatabaseUtil(statement, connection, resultSet);
		}

	}

	/**
	 * 
	 * @throws SQLException
	 */
	public static void logoutForServerRestart() throws SQLException {
		Connection connection = PersisterImpl.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String loginStatus = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("select login_status,login_attempt from user_master  ");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				loginStatus = resultSet.getString("login_status");
				if (loginStatus.equals("Active")) {
					statement = connection.prepareStatement("update user_master set login_attempt=?,login_status=?  ");
					statement.setInt(1, 0);
					statement.setString(2, "Active");
					statement.executeUpdate();
				}
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			log.info("Error while user trying to logout" + e.getMessage());
		} finally {
			CommonUtils.closeDatabaseUtil(statement, connection, resultSet);
		}
	}


}
