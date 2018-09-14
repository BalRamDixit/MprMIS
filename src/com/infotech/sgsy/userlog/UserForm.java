package com.infotech.sgsy.userlog; 

import org.apache.struts.action.ActionForm;

public class UserForm  extends ActionForm{

	 String username  ;
	  String fromDate ;  
	  String toDate;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	} 
	 
	public void reset()  {
		
		 username=null ;
		   fromDate=null ;  
		 toDate=null;
	}
	
	  

	
	
}
