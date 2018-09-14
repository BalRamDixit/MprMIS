package com.infotech.sgsy.util;

import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.userManagement.UserDAO;
import com.infotech.sgsy.userManagement.UserDAOImpl;


/**
 * @author NIC 
 * @since July, 2013 
 */ 
public class SGSYDAOFactory {

	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	private SGSYDAOFactory() {
	}

	static MasterDAO masterDAO = null;

	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public static MasterDAO getInstance(String view) {

		if (view.equalsIgnoreCase(Constants.GET_USER)) {
			masterDAO = new UserDAOImpl();
		}
		return masterDAO;
	}

}
