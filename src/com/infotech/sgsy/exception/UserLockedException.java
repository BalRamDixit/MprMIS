package com.infotech.sgsy.exception;

public class UserLockedException extends Exception{
		
	public UserLockedException(){
	}
	
	public UserLockedException(String message){
		super(message);
	}

}