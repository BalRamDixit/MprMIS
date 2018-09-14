package com.infotech.sgsy.exception;

import org.hibernate.HibernateException;

/**
 * @author NIC 
 * @since July, 2013 
 */ 
public class UpdateFailedException extends Exception {

	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public UpdateFailedException() {
	}

	/**
	 * @author NIC 
	 * @since July, 2013 
	 */ 
	public UpdateFailedException(String message) {
		super(message);
	}

}
