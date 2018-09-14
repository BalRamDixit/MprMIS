package com.infotech.sgsy.userManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.common.UserIdentityVO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.SGSYConstants;

public class UserServiceImpl implements UserService{	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public List<UserIdentityVO> getStateList() throws Exception {
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		List<UserIdentityVO>  list = new ArrayList<UserIdentityVO>();
		list = userDAO.getStateList();
		return list;
	}
	@Override
	public List<UserIdentityVO> getStateNameByStateCode(String stateCode) throws Exception {
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		List<UserIdentityVO>  list = new ArrayList<UserIdentityVO>();
		list = userDAO.getStateNameByStateCode(stateCode);
		return list;
	}

	@Override
	public Map<String, Object> getDistrictList(String code) throws Exception {
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		return userDAO.getEntityList(code);
	}

	@Override
	public Map<String, Object> getBlockList(String code) throws Exception {
		UserDAO  userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		return userDAO.getEntityList(code);
	}

	@Override
	public Map<String, Object> getVillageList(String code) throws Exception {
		UserDAO  userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		return userDAO.getEntityList(code);
	}

	@Override
	public int addUser(UserVO userVO) throws Exception {
		log.info("inside addUser method");
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);		
		return userDAO.insert(userVO);
	}
	public int changeAdminPassword(UserVO userVO) throws Exception {
		log.info("inside addUser method");
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);		
		return userDAO.changeAdminPassword(userVO);
	}
	public int activateAccount(UserVO userVO) throws Exception {
		log.info("inside addUser method");
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);		
		return userDAO.activateAccount(userVO);
	}
	@Override
	public UserVO showUser(String entityCode, String loginId) throws Exception {
		log.info("inside addUser method");
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		return userDAO.showUser(entityCode, loginId);
	}

	@Override
	public int updateUser(UserVO userVO) throws Exception {
		log.info("inside updateQualification method");
		
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);	
		String password = userDAO.userNameCheck(userVO.getLoginId());

		if(password != null) {			 
				return	userDAO.update(userVO);
		}else{
			return Constants.NAME_FOUND ;
		}
		
		
	}
	
	@Override
	public int updateUserDetail(UserVO userVO) throws Exception {
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);				 
		return	userDAO.updateDetail(userVO);	
	}

	@Override
	public List<MasterVO> getLevelList(String levelCode) throws Exception {
		log.info("inside getAllLevelList method");
		List<MasterVO> list = new ArrayList<MasterVO>() ;		
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		list = userDAO.getLevelList(levelCode);
		return list;
	}
	@Override
	public List<MasterVO> getuserReportList() throws Exception {
		log.info("inside getAllLevelList method");
		List<MasterVO> list = new ArrayList<MasterVO>() ;		
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		list = userDAO.getuserReportList();
		return list;
	}

	@Override
	public List<UserIdentityVO> getDistrictListByStateCode(String code)	throws Exception {
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		List<UserIdentityVO>  list = new ArrayList<UserIdentityVO>();
		list = userDAO.getDistrictListByStateCode(code);
		return list;
	}

	@Override
	public List<UserIdentityVO> getBlockListByDistrictCode(String code)
			throws Exception {
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		List<UserIdentityVO>  list = new ArrayList<UserIdentityVO>();
		list = userDAO.getBlockListByDistrictCode(code);
		return list;
	}

	@Override
	public Map<String, Object> getBlockPanchayatListByDistrictCode(
			String userId, String code) throws Exception {
		UserDAO  userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		return userDAO.getBlockPanchayatListByDistrictCode(userId,code);
		
	}
	
	public Map<String, Object> getVillagePanchayatListByBlockCode(
			String userId, String code) throws Exception {
		UserDAO  userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);
		return userDAO.getVillagePanchayatListByBlockCode(userId,code);
		
	}

	@Override
	public boolean userNameCheck(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getMailId(UserVO userVO) throws Exception {
		log.info("inside addUser method");
		UserDAO userDAO = (UserDAO)MasterDAOFactory.getInstance(Constants.GET_USER);		
		return userDAO.getMailId(userVO);
	}
	
	
}
	

	
