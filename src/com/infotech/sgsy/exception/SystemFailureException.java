package com.infotech.sgsy.exception;

import java.io.IOException;

public class SystemFailureException extends IOException {

	public SystemFailureException() {
		
	}

	public SystemFailureException(String message) {
		
		super(message);		
	}

}
