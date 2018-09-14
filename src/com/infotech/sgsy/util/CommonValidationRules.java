

package com.infotech.sgsy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;

/**
 * All the common validation rules that can be used by any field in any module will 
 * be written here.
 * @version 1.0.0   24 May 2009
 * @author 	Tejendra Pathak
 */

/**
 * @author NIC 
 * @since July, 2013
 */
public class CommonValidationRules {

	
	public static boolean isDateLessThanCurrentDate(Object bean,ValidatorAction action,Field field,ActionErrors errors,
		    HttpServletRequest request)	{
	  
	 String inputDate = ValidatorUtil.getValueAsString(bean,field.getProperty());
	  System.out.println("------------------------ "+inputDate);
	  if(inputDate!=null && !inputDate.equals("")) {
	  Calendar enteredCal = Calendar.getInstance();
	  Calendar currentCal = Calendar.getInstance();
	 Date enteredDate = new Date();
	Date currentDate = new Date();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	try {
			enteredDate = sdf.parse(inputDate);
			currentDate = sdf.parse(Util.getCurrentDate(Util.DATE_FORMAT_WITHOUT_TIME_DD_MM_YY));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		enteredCal.setTime(enteredDate);  
		currentCal.setTime(currentDate);
	    
	    //System.out.println(" *************************** "+enteredCal.compareTo(currentCal));
	 
	    /*
	     * Calendar.compareTo(cal) method returns the following int values
	     * 
	     * 0 - if the value of the calendar is equal to the value of the argument
	     * value greater than 0 if the value of calendar is greater than the one in the argument
	     * value less than 0 if the value of calendar is less than the one in the argument 
	     */
	    
     //Greater than 0 meaning 	    
	 if(enteredCal.compareTo(currentCal) > 0){
		 errors.add(field.getKey(),Resources.getActionError(request, action, field));
		 return false;
	  }
	 
	 }
	  return true;
	  
	}
	
	public static boolean isDateLessThanDependentField(Object bean,ValidatorAction action,Field field,ActionErrors errors,
		    HttpServletRequest request){
	  
	 String input = ValidatorUtil.getValueAsString(bean,field.getProperty());
	 
	 String dependent = ValidatorUtil.getValueAsString(bean,field.getVarValue(("dependentDate")));

	 
	 if(input!=null && dependent!=null && !input.equals("")  && !dependent.equals(""))
	 {
	 
	 Calendar inputCal = Calendar.getInstance();
	 
	 Calendar dependentCal = Calendar.getInstance();
	 
	 Date enteredDate = new Date();
	 
	 Date dependentDate = new Date();
	 
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			enteredDate = sdf.parse(input);
			dependentDate = sdf.parse(dependent);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		inputCal.setTime(enteredDate);  
		dependentCal.setTime(dependentDate);
	    
	    //System.out.println(" *************************** "+enteredCal.compareTo(currentCal));
	 
	    /*
	     * Calendar.compareTo(cal) method returns the following int values
	     * 
	     * 0 - if the value of the calendar is equal to the value of the argument
	     * value greater than 0 if the value of calendar is greater than the one in the argument
	     * value less than 0 if the value of calendar is less than the one in the argument 
	     */
	    
     //Greater than 0 meaning 	    
	 if(inputCal.compareTo(dependentCal) > 0)
	 {
		 errors.add(field.getKey(),Resources.getActionError(request, action, field));
		 return false;
	 }
	 
	 }
	  return true;
	  
	}
	public static boolean isDateGreaterThanCurrentDate(Object bean,ValidatorAction action,Field field,ActionErrors errors,
		    HttpServletRequest request)
	{
	  
	 String inputDate = ValidatorUtil.getValueAsString(bean,field.getProperty());
	  
	 System.out.println("------------------------ "+inputDate);
	 
	 if(inputDate!=null && !inputDate.equals(""))
	 {
	 
	 Calendar enteredCal = Calendar.getInstance();
	 
	 Calendar currentCal = Calendar.getInstance();
	 
	 Date enteredDate = new Date();
	 
	 Date currentDate = new Date();
	 
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			enteredDate = sdf.parse(inputDate);
			currentDate = sdf.parse(Util.getCurrentDate(Util.DATE_FORMAT_WITHOUT_TIME_DD_MM_YY));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		enteredCal.setTime(enteredDate);  
		currentCal.setTime(currentDate);
	    
	    //System.out.println(" *************************** "+enteredCal.compareTo(currentCal));
	 
	    /*
	     * Calendar.compareTo(cal) method returns the following int values
	     * 
	     * 0 - if the value of the calendar is equal to the value of the argument
	     * value greater than 0 if the value of calendar is greater than the one in the argument
	     * value less than 0 if the value of calendar is less than the one in the argument 
	     */
	    
     //Greater than 0 meaning 	    
	 if(enteredCal.compareTo(currentCal) < 0)
	 {
		 errors.add(field.getKey(),Resources.getActionError(request, action, field));
		 return false;
	 }
	 
	 }
	  return true;
	  
	}
	//Date should not be less than or equal to current date
	public static boolean isDateGreaterThanOrEqualToCurrentDate(Object bean,ValidatorAction action,Field field,ActionErrors errors,
		    HttpServletRequest request)
	{
	  
	 String inputDate = ValidatorUtil.getValueAsString(bean,field.getProperty());
	  
	 System.out.println("------------------------ "+inputDate);
	 
	 if(inputDate!=null && !inputDate.equals(""))
	 {
	 
	 Calendar enteredCal = Calendar.getInstance();
	 
	 Calendar currentCal = Calendar.getInstance();
	 
	 Date enteredDate = new Date();
	 
	 Date currentDate = new Date();
	 
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			enteredDate = sdf.parse(inputDate);
			currentDate = sdf.parse(Util.getCurrentDate(Util.DATE_FORMAT_WITHOUT_TIME_DD_MM_YY));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		enteredCal.setTime(enteredDate);  
		currentCal.setTime(currentDate);
	    
	    //System.out.println(" *************************** "+enteredCal.compareTo(currentCal));
	 
	    /*
	     * Calendar.compareTo(cal) method returns the following int values
	     * 
	     * 0 - if the value of the calendar is equal to the value of the argument
	     * value greater than 0 if the value of calendar is greater than the one in the argument
	     * value less than 0 if the value of calendar is less than the one in the argument 
	     */
	    
     //Greater than 0 meaning 	    
	 if(enteredCal.compareTo(currentCal) <= 0)
	 {
		 errors.add(field.getKey(),Resources.getActionError(request, action, field));
		 return false;
	 }
	 
	 }
	  return true;
	  
	}
	
	public static boolean isDateGreaterThanDependantField(Object bean,ValidatorAction action,Field field,ActionErrors errors,
		    HttpServletRequest request)
	{
	  
	 String inputDate = ValidatorUtil.getValueAsString(bean,field.getProperty());
	  
	 System.out.println("------------------------ "+inputDate);
	 
	 if(inputDate!=null && !inputDate.equals(""))
	 {
	 
	 Calendar enteredCal = Calendar.getInstance();
	 
	 Calendar currentCal = Calendar.getInstance();
	 
	 Date enteredDate = new Date();
	 
	 Date currentDate = new Date();
	 
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			enteredDate = sdf.parse(inputDate);
			currentDate = sdf.parse(Util.getCurrentDate(Util.DATE_FORMAT_WITHOUT_TIME_DD_MM_YY));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		enteredCal.setTime(enteredDate);  
		currentCal.setTime(currentDate);
	    
	    //System.out.println(" *************************** "+enteredCal.compareTo(currentCal));
	 
	    /*
	     * Calendar.compareTo(cal) method returns the following int values
	     * 
	     * 0 - if the value of the calendar is equal to the value of the argument
	     * value greater than 0 if the value of calendar is greater than the one in the argument
	     * value less than 0 if the value of calendar is less than the one in the argument 
	     */
	    
     //Greater than 0 meaning 	    
	 if(enteredCal.compareTo(currentCal) < 0)
	 {
		 errors.add(field.getKey(),Resources.getActionError(request, action, field));
		 return false;
	 }
	 
	 }
	  return true;
	  
	}
	
	
 
	
}
