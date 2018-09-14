package com.infotech.sgsy.exception;

public class PasswordNotValidException extends Exception{
		
	public PasswordNotValidException(){
	}
	
	public PasswordNotValidException(String message){
		super(message);
	}

}