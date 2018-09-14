package com.infotech.sgsy.exception;

/**
 * 
 *  @author 37595
 *  Date : 28.04.2009
 */

public class UserLoggedInException extends Exception{
		
	public UserLoggedInException(){
	}
	
	public UserLoggedInException(String message){
		super(message);
	}

}