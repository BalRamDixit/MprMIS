package com.infotech.sgsy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All the common methods which can be used by any module will be written here. Methods which need to
 * access the database should be written in UtilManager. No DB related stuff here.
 * @version 1.0.0   6 May 2008
 * 
 */

public class Util {
	
		public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy HH:mm:ss";
		public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";
		
		public static final String DATE_YYYY = "yyyy";
		
		public static final String DATE_MM = "MM";
		
		public static final String DATE_DD = "dd";
		
		public static final String DATE_MONTH = "MMMM";
		
		public static final String TIMEFORMATHH_mm = "HH:mm";	
	
		public static final String DATE_FORMAT_WITHOUT_TIME_DD_MM_YY = "dd-MM-yyyy";
		
		public static final String DATE_FORMAT_WITHOUT_TIME_DD_MONTH_YY = "dd-MMMM-yyyy";
		
		public static final String DATE_FORMAT_MM_YYYY = "MM-yyyy";
		
		public static final String DATE_FORMAT_MONTH_YEAR = "MMMM-yyyy";
	
		public static final String REPORT_TIME_STAMP = "ddMMyyHHmmssSSS";

		public static final String STATUS_ACTIVE = "A";
	
		public static final String STATUS_INACTIVE = "I";
		
		/*
		 *     The Following varible has to be change after complition of module.
		 */
		public static final int STATUS_ACTIVE_INT = 15;
		
		public static final int STATUS_INACTIVE_INT = 16;
	
		/*
		 *     The Following varible has to be change after complition of module.
		 */
		
		
		public static final String STATUS_TO_DISPLAY_ACTIVE = "Active";
	
		public static final String STATUS_TO_DISPLAY_INACTIVE = "Inactive";
	
		public static final String STATUS_APPROVED = "A";
	
		public static final String STATUS_PENDING = "P";
		
		public static final String DELIMITER = "@&!#@";
    
		//Nischal: To know if the master details in temp are for insertion into master table or for update
		public static final String TEMP_MASTER_STATUS_INSERT = "I";
    
		public static final String TEMP_MASTER_STATUS_UPDATE = "U";
    
		//For updating the status as Reconciled
		public static final String STATUS_RECONCILED = "1";	
    
		public static final String STATUS_UNRECONCILED = "2";
    
		public static final String STATUS_RECONCILE_REJECT = "4";
    
		public static final String STATUS_YET_TO_RECONCILE = "3";
    
		public static final String STATUS_DEPOSITED = "5";
		
		public static final String STATUS_NOT_DEPOSITED = "28";
		//Exam Status
		public static final String STATUS_EXAM_PRESENT = "P";
    
		public static final String STATUS_EXAM_ABSENT = "A";
		
		//Course Master
		
		public static final String CertificationType = "2";
		
		public static final String PERCENTAGE_DISCOUNT_TYPE ="1";
		public static final String FLAT_DISCOUNT_TYPE ="2";

		//Time list - hour or minute
		public static int HOUR_LIST = 1;
		
		public static int Minute_LIST = 2;
		
		// HELP DESK STATUS
		public static final Integer STATUS_INCIDENT_UNCLOSED =8;
		public static final Integer STATUS_INCIDENT_CLOSED =9;
		public static final Integer STATUS_INCIDENT_ASSIGNED =10;
		public static final Integer STATUS_INCIDENT_REOPEN=11;
		public static final Integer STATUS_INCIDENT_INACTIVE=16;
		
		 public static final String IncidentType_Query="1";
		 public static final String IncidentType_complaints="2";
		 public static final String ComplaintsCategory_other="5";
		 public static final String ComplaintsBy_KCOP="90";
		 public static final String ComplaintsBy_CenterManager="93";
		 public static final String ComplaintsBy_Candidate="92";
		 
		 //Reject, revert and pending status for all modules
		 public static final int AUTHORIZE_STATUS_REJECT = 12;
			
		 public static final int AUTHORIZE_STATUS_PENDING = 13;
			
		 public static final int AUTHORIZE_STATUS_REVERT = 14;
		 
		 
		 //These values will be used to display the various icons in the authorize pending approvals page
		 public static final String APPROVAL_TYPE_DELETE = "delete";
		 public static final String APPROVAL_TYPE_MODIFY = "modify";
		 public static final String APPROVAL_TYPE_NEW = "new";
		 
		 //Value for course management when weekly theory or labs is selected
		 public static final String matchValueToDisplayDaysOfTheWeek = "1";	
		 
		 //Value for the various class types
		 public static final String CLASS_TYPE_LAB = "1";
		 public static final String CLASS_TYPE_THEORY = "2";
		
	
		 //Role id's 
		 public static final String ROLEID_KCOP="90";
		 public static final String ROLEID_CENTERMANAGER="93";
		 public static final String ROLEID_CANDIDATE="92";
		 public static final String ROLEID_EMPLOYER="95";
		 public static final String RoleID_HELPDESKMANAGER="91";
		 public static final String ROLEID_TRAINER="97";
		 public static final String ROLEID_HELPDESKOPERATOR="94";
		 
		 //Candidate Registration
		 
		 public static final boolean CANDIDATE_REGISTRATION_ONLINE = true;
		 public static final boolean CANDIDATE_REGISTRATION_OFFLINE = false;
		 
		 //Certificate Approval Levels
		 
		 public static final int APPROVAL_BY_3I_PROJECTDIRECTOR = 19;
		 public static final int APPROVAL_BY_ITG_PROJECTDIRECTOR = 20;
		 public static final int REVERTED_TO_SECONDLEVEL = 21;
		 public static final int REJECTED_TO_FIRSTLEVEL = 22;
		 public static final int REJECTED_TO_SECONDLEVEL = 23;
		 public static final int REVERTED_TO_FIRSTLEVEL = 18;
		 
		 //Installment types
		 public static final int INSTALLMENT_TYPE_FIXED = 1;
		 public static final int INSTALLMENT_TYPE_VARIABLE = 2;
		 
		 //Course Class Type
		 public static final int COURSE_CLASS_TYPE_LAB = 1;
		 public static final int COURSE_CLASS_TYPE_THEORY = 2;
		 
		 //The following data will be displayed in the pdf report that will be generated for audit trail
		 public static final String LABEL_CURRENT_DATA = "Current Data";
		 public static final String LABEL_MODIFIED_ON = "Modified On :";
		 
		 
		 //The following static varible is used to document upload real path.
		 
		 public static final String UPLOADFOLDER="upload";
		 public static final String NEWSBULLETINUPLOADFOLDER="uploadedNewsDocument";
		 
		
		 
	
		 
		 
		 
		 //System Vaqriables
		 public static final String SYSTEM_CENTER_START_TIME = "CENTER_START_TIME";
		 public static final String SYSTEM_CENTER_END_TIME = "CENTER_END_TIME";
		 public static final String SYSTEM_GOVERNMENT_SHARE = "GOVT Share";
		 
		
		 /*
		  *  Value for  Knowledge center Belong to Urban or Not 
		  */
		 
		 public static final String CENTER_IS_URBAN="29";
		 public static final String CENTER_IS_NOT_URBAN="30";
		 
		 public static final String PAYMENT_MODE_CASH="1";
		 public static final String PAYMENT_MODE_CHEQUE="2";
		 public static final String PAYMENT_MODE_DD="3";
		 
		 //PAYMENT RECEIPT NUMBER
		 public static final int PAYMENT_RECEIPT_SIZE = 5;
		
		 // Software Down Time Report 
		 
		 public static final int noOfOperationalHrs = 8;
		 
		 public static final String SERVICE_TAX_REGISTRATION_NUMBER = "SERVICE_TAX_REG_NO";
		 
		 public static synchronized String getCurrentDate(String dateFormat) {
		  
			/*try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				return sdf.format(cal.getTime());

		}


	 /*
		 * Adds time, day, hour etc.  Note that you can use the same method for subtracting as well by
		 * sending a negative value of the appropriate amount to be subtracted in the 'amountToAdd' field.
		 * 
		 * @param calendarType Specify the type of data being sent. Whether it's minute, hour, day etc. Use 
		 * the static fields Calendar.MINUTE, Calendar.HOUR, Calendar.DATE etc.
		 * @param calendarValue The appropriate value on which the addition(or subtraction) operation is to be performed.
		 * @param amountToAdd The value that needs to be added (or subtracted from the 'calendarValue')
		 * @param calendarFormat The format of the 'calendarValue' . Example: 'HH' or 'HH-mm-yyyy' etc. 
		 *  
		 * 
		 * @return returns the candidate Id
		 */
	  private static String calendarAdd(int calendarType, String calendarValue, int amountToAdd, String calendarFormat ) throws ParseException
		{
		  	/* We first convert the 'calendarValue' from String to Date type using the 
		  	   SimpleDareFotmat's parse method */
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(calendarFormat);
			if(calendarValue!=null)
			date = sdf.parse(calendarValue);
			
			/* We get the calendar instance, set the time as the new date object that we just created */			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);  
			
			/* We add the value to the calendar */
			cal.add(calendarType, amountToAdd);
			
			return sdf.format(cal.getTime());
		}
	  
	  /*
	   * The following method returns the new date after adding the number of days supplied to it
	   */
	  public static String addDate(String dateToAdd, int numberOfDaysToAdd, String dateFormat) throws ParseException,Exception
	  {
		  return Util.calendarAdd(Calendar.DATE, dateToAdd, numberOfDaysToAdd, dateFormat);
		  /*Calendar cal = Calendar.getInstance(); Date d = new Date(); SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);	d = sdf.parse(dateToAdd); cal.setTime(d); cal.add(Calendar.DATE, numberOfDaysToAdd); return sdf.format(cal.getTime());*/
	  }
	  

	  /*
	   * Method adds minute 
	   */
	  public static String addMinute(String calendarValue, int amountToAdd, String calendarFormat ) throws ParseException 
	  {
		  return Util.calendarAdd(Calendar.MINUTE, calendarValue, amountToAdd, calendarFormat);
	  }
	  
	  
	  /*
	   * Method adds hour 
	   */
	  public static String addHour(String calendarValue, int amountToAdd, String calendarFormat ) throws ParseException 
	  {
		  return Util.calendarAdd(Calendar.HOUR, calendarValue, amountToAdd, calendarFormat);
	  }
	  
	  private static int calendarCompare(String compare, String compareWith, String format) throws ParseException
		{
		  
		  	Calendar calendar = Calendar.getInstance();
		 
		 	Calendar calendar2 = Calendar.getInstance();
		 
		 	Date date = new Date();
		 
		 	Date date2 = new Date();
		 
		    SimpleDateFormat sdf = new SimpleDateFormat(format);
			
		    if(compare!=null)
			date = sdf.parse(compare);
		    
		    if(compareWith!=null)
			date2 = sdf.parse(compareWith);
			
			calendar.setTime(date);  
			calendar2.setTime(date2);
		    
		    /*
		     * Calendar.compareTo(cal) method returns the following int values
		     * 
		     * 0 - if the value of the calendar is equal to the value of the argument
		     * value greater than 0 if the value of calendar is greater than the one in the argument
		     * value less than 0 if the value of calendar is less than the one in the argument 
		     */
		    
		 return calendar.compareTo(calendar2);
		  
		}
	  
	  public static boolean isHourGreater(String hour, String hourGreaterThan, String format) throws ParseException
	  {
		  if(calendarCompare(hour,hourGreaterThan,format)>0)
			  return true;
		  return false;
	  }
	  
	  
	  public static int numberOfDaysInMonth(String monthYear, String dateFormat) throws ParseException
		{

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = sdf.parse(monthYear);
			
			/* We get the calendar instance, set the time as the new date object that we just created */			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);  
			
			//System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			/* We add the value to the calendar */
			//cal.add(calendarType, amountToAdd);
			
			System.out.println(cal.getActualMaximum((Calendar.DAY_OF_MONTH)));
			
			return cal.getActualMaximum((Calendar.DAY_OF_MONTH));
		
		}
	  
	  
	  /*
		 * Calculates the number of specified days in a given month.
		 * 
		 * @param numberOfDaysInMonth The number of days in the month from which specific days have to be calculated
		 * @param startDay The day of the week on the 1st day of the month
		 * @param specificDayToCount The day to be counted in the month
		 * @param date format. 'EEEE' meaning Monday, Tuesday ect. 'EEE' meaning 'Tue' and so on.  
		 * 
		 * @return returns the candidate Id
		 */
	  private static int calculateSpecificNumberOfDayInMonth(int numberOfDaysInMonth, String startDay, String specificDayToCount, String dateFormat) throws ParseException
		{

		    int numberOfSpecificDays = numberOfDaysInMonth/7;
		    
	        int remainder = numberOfDaysInMonth%7;
	        
	        if (remainder==0)
	        	return 0;
	        
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = sdf.parse(startDay);
			
			/* We get the calendar instance, set the time as the new date object that we just created */			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);  
			
			
			boolean isSpecificDay = false;
			
			while(!isSpecificDay && remainder >0)
			{
				if(sdf.format(cal.getTime()).equalsIgnoreCase(specificDayToCount))
				{
					numberOfSpecificDays++;
					isSpecificDay = true;
				}
				else
				{
					cal.add(Calendar.DAY_OF_MONTH,1);
					remainder--;
				}
			}
			
			return numberOfSpecificDays;
		
		}
	  
	  
	  public static int calculateNumberOfSundays(String monthYear, String dateFormat) throws ParseException
	  {
		  
		  return calculateSpecificNumberOfDayInMonth(numberOfDaysInMonth(monthYear, dateFormat), startingDayOfWeekOfTheMonth(monthYear, dateFormat), "Sunday", "EEEE");
	  }
	  
	  public static int calculateNumberOfSundays(String currentDate, String currentDateFormat, String monthYear, String monthYearFormat) throws ParseException
	  {
		  
		  Date date = new Date();
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentDateFormat);
		  date = simpleDateFormat.parse(currentDate);
		  
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  
		  SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(Util.DATE_DD);
		  
		  return calculateSpecificNumberOfDayInMonth(Integer.parseInt(simpleDateFormat2.format(calendar.getTime())), startingDayOfWeekOfTheMonth(monthYear, monthYearFormat), "Sunday", "EEEE");
	  }
	  
	  
	  
	  public static String startingDayOfWeekOfTheMonth(String monthYear, String dateFormat) throws ParseException
		{

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-"+dateFormat);
			date = sdf.parse("01-"+monthYear);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);  
			
			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEEE");
			
			return dateFormat2.format(cal.getTime());
		
		}
	  
	  public static boolean isDateGreater(String date, String dateToCompare, String format) throws ParseException
	  {
		  if(calendarCompare(date,dateToCompare,format)>0)
			  return true;
		  	  return false;
	  }
	  
	  public static String getFormatedDate(java.util.Date fromDate, String format)
	  {
	  	String strDate = null;
	  	SimpleDateFormat sdf = null;
	  	try{
	  		if(format == null)
	  			format = "dd-MM-yyyy";
	  		if(fromDate == null)
	  			fromDate = new Date(System.currentTimeMillis());
	  		sdf = new SimpleDateFormat(format);
	  		strDate = sdf.format(fromDate);
	  	}
	  	catch(Exception e)
	  	{
	  		e.printStackTrace();
	  	}
	  	return strDate;
	  }
	  
	  public static String getFormatedDateWithoutCurrentDate(java.util.Date fromDate, String format)
	  {
		  
		  if(fromDate == null)
	  			return null;
	  	String strDate = null;
	  	SimpleDateFormat sdf = null;
	  	try{
	  		if(format == null)
	  			format = "dd-MM-yyyy";
	  		
	  		sdf = new SimpleDateFormat(format);
	  		strDate = sdf.format(fromDate);
	  	}
	  	catch(Exception e)
	  	{
	  		e.printStackTrace();
	  	}
	  	return strDate;
	  }
	  
	  
	  /*
	   *    Method Will Return The Date IN YYYY-MM-DD Formate from String
	   */
	
	  public static Date getDate(String str)  {
			
			String DATE_FORMAT = "dd-MM-yyyy";	
			
			java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(DATE_FORMAT);
			try  {
				Date date= sdf.parse(str);
				return date;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
	  public static Date getDate(String str,String format)  {
			
			if(format == null)
	  			format = "dd-MM-yyyy";
			java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(format);
			try  {
				Date date= sdf.parse(str);
				return date;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}	  
	
	
	/** Handle BlankSpace String
	 * @author CVAS2273
	 * @param moreBlankString
	 * @return  replace more than one blank space to one blank space  
	 */
	  
	public String removeBlankSpace(String moreBlankString)  {
		
		 Pattern pattern = Pattern.compile("\\s+");
		 Matcher matcher = pattern.matcher(moreBlankString);
		 String finalString =  matcher.replaceAll(" ");
		 
		 return finalString;
		}
	
	
	/**@author CVAS2273
	 * @param commaString
	 * @return this method return string i.e replace all comma with blank spaces 
	 */
	public String removeCommaSpace(String commaString){ 
		
		 // Create a pattern to match breaks(commas)
        Pattern pattern = Pattern.compile("[:\\s]+");
        
        Matcher matcher = pattern.matcher(commaString);
        // replace comma with space  
        String finalString =  matcher.replaceAll("");
      	return finalString;
	    }
	
	
	
	
	
	  
	}
