package com.infotech.sgsy.common;

import com.infotech.sgsy.accessControl.AccessMasterDAOImpl;
import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.manageRole.ManageRoleDAOImpl;
import com.infotech.sgsy.selfHelpGroup.SHGMasterDAO;
import com.infotech.sgsy.userManagement.UserDAOImpl;
import com.infotech.sgsy.util.Constants;

/**
 * @author NIC 
 * @since July, 2013
 */
public class MasterDAOFactory {

	private static MasterDAO masterDAO = null;

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	private MasterDAOFactory() {
	}

	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public static MasterDAO getInstance(String viewName) throws Exception {
		if (viewName.equalsIgnoreCase(Constants.GET_ACCESS)) {
			masterDAO = new AccessMasterDAOImpl();
		} else if (viewName.equalsIgnoreCase(Constants.GET_USER)) {
			masterDAO = new UserDAOImpl();
		} else if (viewName.equalsIgnoreCase(Constants.GET_LOGIN)) {
			masterDAO = new LoginMasterDAOImpl();
		} else if (viewName.equalsIgnoreCase(Constants.GET_ROLE)) {
			masterDAO = new ManageRoleDAOImpl();
		} else {
			masterDAO = (MasterDAO) (Object) Class.forName(viewName)
					.newInstance();
		}

		return masterDAO;

	}
}
