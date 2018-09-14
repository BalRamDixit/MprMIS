package com.infotech.sgsy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 	@author NIC
 * 	@date 25 JAN 2013
 *  @method BELOW FUNCTION IS USED TO HELP GET THE DATE FUCNTION
 *  
 *  getCurrentMonthName() 			1. Current name month: JANUARY
 *  getCurrentMonthCode()			2. Current month: 1
 *  getPreviousMonthName() 			3. Previous name month: DECEMBER
 *  getPreviousMonthCode()			4. Previous month: 12
 *  getCurrentYear() 				5. Current year: 2013
 *  getPreviousYear()				6. Previous year: 2012
 *  getCurrentFinancialYear()		7. current financial year: 2012-2013
 *  getPreviousFinancialYear()		8. Previous financial year: 2011-2012
 *  getCurrentDate()				9. current date: 25-1-2013
 *  getCurrentDateTime()			10. Previous date time: 25-1-2013 21:46:27.18
 * 
 * 		log.error("1. Current month: " + dateUtil.getCurrentMonthName());
		log.error("2. Previous month: " + dateUtil.getCurrentMonthCode());
		log.error("3. Current month: " + dateUtil.getPreviousMonthName());
		log.error("4. Previous month: " + dateUtil.getPreviousMonthCode());
		log.error("5. Current year: " + dateUtil.getCurrentYear());
		log.error("6. Previous year: " + dateUtil.getPreviousYear());
		log.error("7. current financial year: " + dateUtil.getCurrentFinancialYear());
		log.error("8. Previous financial year: " + dateUtil.getPreviousFinancialYear());		
		log.error("9. current date: " + dateUtil.getCurrentDate());
		log.error("10. Previous date time: " + dateUtil.getCurrentDateTime());	
 *
 */
public class DateUtil {

	public static final String REPORT_TIME_STAMP = "ddMMyyHHmmssSSS";
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final String MONTH_NAME[] = { "JANUARY", "FEBURARY", "MARCH", "APRIL", "MAY", "JUNE", 
										   "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };  
	
	public String getCurrentDateAsString() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Date d1 = new Date();
		return sdf.format(d1);
	}

	public static synchronized String getCurrentDate(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static Date getPresentDate() throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				DATE_FORMAT);
		Date d1 = new Date();
		return sdf.parse(sdf.format(d1));
	}

	public Date getDate(String str) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				DATE_FORMAT);
		try {
			Date date = sdf.parse(str);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCurrentDateforCalander() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				DATE_FORMAT);
		Date d1 = new Date();
		return sdf.format(d1);
	}

	public String getFormatedDate(java.util.Date fromDate, String format) {
		String strDate = null;
		SimpleDateFormat sdf = null;
		try {
			if (format == null)
				format = "dd-MM-yyyy";
			if (fromDate == null)
				fromDate = new Date(System.currentTimeMillis());
			sdf = new SimpleDateFormat(format);
			strDate = sdf.format(fromDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	public String formatDate(String date) {
		return (date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date
				.substring(0, 4));
	}

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}


	public String replaceMonthNameWithMonthCode(String monthName) {
		String monthCode = null;
		if (monthName.equals("JANUARY")) {
			monthCode = "1";
		} else if (monthName.equals("FEBURARY")) {
			monthCode = "2";
		} else if (monthName.equals("MARCH")) {
			monthCode = "3";
		} else if (monthName.equals("APRIL")) {
			monthCode = "4";
		} else if (monthName.equals("MAY")) {
			monthCode = "5";
		} else if (monthName.equals("JUNE")) {
			monthCode = "6";
		} else if (monthName.equals("JULY")) {
			monthCode = "7";
		} else if (monthName.equals("AUGUST")) {
			monthCode = "8";
		} else if (monthName.equals("SEPTEMBER")) {
			monthCode = "9";
		} else if (monthName.equals("OCTOBER")) {
			monthCode = "10";
		} else if (monthName.equals("NOVEMBER")) {
			monthCode = "11";
		} else if (monthName.equals("DECEMBER")) {
			monthCode = "12";
		}
		return monthCode;
	}

	public String replaceCodeWithMonthName(String monthCode) {
		String monthName = null;
		if (monthCode.equals("1")) {
			monthName = "JANUARY";
		} else if (monthCode.equals("2")) {
			monthName = "FEBURARY";
		} else if (monthCode.equals("3")) {
			monthName = "MARCH";
		} else if (monthCode.equals("4")) {
			monthName = "APRIL";
		} else if (monthCode.equals("5")) {
			monthName = "MAY";
		} else if (monthCode.equals("6")) {
			monthName = "JUNE";
		} else if (monthCode.equals("7")) {
			monthName = "JULY";
		} else if (monthCode.equals("8")) {
			monthName = "AUGUST";
		} else if (monthCode.equals("9")) {
			monthName = "SEPTEMBER";
		} else if (monthCode.equals("10")) {
			monthName = "OCTOBER";
		} else if (monthCode.equals("11")) {
			monthName = "NOVEMBER";
		} else if (monthCode.equals("12")) {
			monthName = "DECEMBER";
		}
		return monthName;
	}

	public String getMonthForQuater() {
		String month = null;
		Calendar cal = Calendar.getInstance();
		int intMonth = cal.get(Calendar.MONTH);
		if (intMonth == 0 || intMonth == 1 || intMonth == 2) {
			month = "DECEMBER";
		} else if (intMonth == 3 || intMonth == 4 || intMonth == 5) {
			month = "MARCH";
		} else if (intMonth == 6 || intMonth == 7 || intMonth == 8) {
			month = "JUNE";
		} else if (intMonth == 9 || intMonth == 10 || intMonth == 11) {
			month = "SEPTEMBER";
		}
		return month;
	}

	public String getMonth() {
		String[] month = { "JANUARY", "FEBURARY", "MARCH", "APRIL", "MAY",
				"JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER",
				"DECEMBER" };
		Calendar cal = Calendar.getInstance();
		return month[cal.get(Calendar.MONTH)];
	}

	// --------------------------------------------- HIMANSHU MEHRA
	// -------------------------------------------
	// Function used to collect all previous month of financial year(April -
	// March)
	public List<String[]> getFinancialMonth() {
		int topMonth;
		int month = Integer.parseInt(getMonthCode(getMonth())) - 1;
		List<String[]> financialMonth = new ArrayList<String[]>();
		if (month < 4) {
			// for Mar to Jan
			topMonth = 1;
			while (month > topMonth) {
				financialMonth.add(new String[] { Integer.toString(topMonth),
						getMonthName(Integer.toString(topMonth)) });
				topMonth++;
			}
			// for April to Dec
			topMonth = 4;
			while (12 > topMonth) {
				financialMonth.add(new String[] { Integer.toString(topMonth),
						getMonthName(Integer.toString(topMonth)) });
				topMonth++;
			}

		} else {
			topMonth = 4;
			while (month >= topMonth) {
				financialMonth.add(new String[] { Integer.toString(topMonth),
						getMonthName(Integer.toString(topMonth)) });
				topMonth++;
			}
		}

		return financialMonth;
	}

	// Function used to collect the monthName of select code
	public String getMonthName(String monthCode) {
		String monthName = null;
		if (monthCode.equals("1")) {
			monthName = "JANUARY";
		} else if (monthCode.equals("2")) {
			monthName = "FEBURARY";
		} else if (monthCode.equals("3")) {
			monthName = "MARCH";
		} else if (monthCode.equals("4")) {
			monthName = "APRIL";
		} else if (monthCode.equals("5")) {
			monthName = "MAY";
		} else if (monthCode.equals("6")) {
			monthName = "JUNE";
		} else if (monthCode.equals("7")) {
			monthName = "JULY";
		} else if (monthCode.equals("8")) {
			monthName = "AUGUST";
		} else if (monthCode.equals("9")) {
			monthName = "SEPTEMBER";
		} else if (monthCode.equals("10")) {
			monthName = "OCTOBER";
		} else if (monthCode.equals("11")) {
			monthName = "NOVEMBER";
		} else if (monthCode.equals("12")) {
			monthName = "DECEMBER";
		}
		return monthName;
	}

	// function used to collect month code
	public String getMonthCode(String monthName) {
		String monthCode = null;
		if (monthName.equals("JANUARY")) {
			monthCode = "1";
		} else if (monthName.equals("FEBURARY")) {
			monthCode = "2";
		} else if (monthName.equals("MARCH")) {
			monthCode = "3";
		} else if (monthName.equals("APRIL")) {
			monthCode = "4";
		} else if (monthName.equals("MAY")) {
			monthCode = "5";
		} else if (monthName.equals("JUNE")) {
			monthCode = "6";
		} else if (monthName.equals("JULY")) {
			monthCode = "7";
		} else if (monthName.equals("AUGUST")) {
			monthCode = "8";
		} else if (monthName.equals("SEPTEMBER")) {
			monthCode = "9";
		} else if (monthName.equals("OCTOBER")) {
			monthCode = "10";
		} else if (monthName.equals("NOVEMBER")) {
			monthCode = "11";
		} else if (monthName.equals("DECEMBER")) {
			monthCode = "12";
		}
		return monthCode;
	}
	
	// USED TO GET THE CURRENT MONTH NAME
		public String getCurrentMonthName() {	             
			Calendar cal = Calendar.getInstance();
			return MONTH_NAME[cal.get(Calendar.MONTH)];
		}
			
		//	USED TO GET THE CURRENT MONTH CODE
		public String getCurrentMonthCode() {
			Calendar cal = Calendar.getInstance();
			return Integer.toString(cal.get(Calendar.MONTH) + 1);
		}
		
		// USED TO GET THE PREVIOUS MONTH NAME
		public String getPreviousMonthName() {
		             
			Calendar cal = Calendar.getInstance();
			cal.roll(Calendar.MONTH, -1);
			return MONTH_NAME[cal.get(Calendar.MONTH)];
		}
		
		//	USED TO GET THE PREVIOUS MONTH CODE
		public String getPreviousMonthCode() {
			Calendar cal = Calendar.getInstance();
			cal.roll(Calendar.MONTH, -1);
			return Integer.toString(cal.get(Calendar.MONTH) + 1);
		}

		// USED TO GET THE CURRENT YEAR
		public int getCurrentYear() {
			Calendar cal = Calendar.getInstance();
			return cal.get(Calendar.YEAR);
		}
		
		// USED TO GET THE PREIOUS YEAR
		public int getPreviousYear() {
		    Calendar prevYear = Calendar.getInstance();
		    prevYear.add(Calendar.YEAR, -1);
		    return prevYear.get(Calendar.YEAR);
		}
		
		// USED TO GET THE CURRENT FINANCIAL YEAR
		public String getCurrentFinancialYear() {
			String currentFinancialYear = "";
			
			Calendar cal = Calendar.getInstance();
			// CHECK CUURENT MONTH APRIL OR ABOVE
			if(cal.get(Calendar.MONTH) >= 3){
				currentFinancialYear = Integer.toString(cal.get(Calendar.YEAR));
				cal.add(Calendar.YEAR, 1);
				currentFinancialYear = currentFinancialYear.concat("-").concat(Integer.toString(cal.get(Calendar.YEAR)));
			}
			// CHECK CUURENT MONTH APRIL OR BELLOW
			if(cal.get(Calendar.MONTH) < 3){
				int currentYear = cal.get(Calendar.YEAR);
				cal.add(Calendar.YEAR, -1);
				currentFinancialYear = Integer.toString(cal.get(Calendar.YEAR)).concat("-").concat(Integer.toString(currentYear));
			}
		    return currentFinancialYear;
		}
		
		// USED TO GET THE PREVIOUS FINANCIAL YEAR
		public String getPreviousFinancialYear() {
			String previousFinancialYear = "";
			
			Calendar cal = Calendar.getInstance();
			// CHECK CUURENT MONTH APRIL OR ABOVE
			if(cal.get(Calendar.MONTH) >= 3){
				cal.add(Calendar.YEAR, -1);
				previousFinancialYear = Integer.toString(cal.get(Calendar.YEAR));
				cal.add(Calendar.YEAR, 1);
				previousFinancialYear = previousFinancialYear.concat("-").concat(Integer.toString(cal.get(Calendar.YEAR)));
			}
			// CHECK CUURENT MONTH APRIL OR BELLOW
			if(cal.get(Calendar.MONTH) < 3){
				cal.add(Calendar.YEAR, -2);
				previousFinancialYear = Integer.toString(cal.get(Calendar.YEAR));
				cal.add(Calendar.YEAR, 1);
				previousFinancialYear = previousFinancialYear.concat("-").concat(Integer.toString(cal.get(Calendar.YEAR)));
			}
		    return previousFinancialYear;
		}
		
		// USED TO GET THE CURRENT DATE
		public  String getCurrentDate(){
			Calendar cal = Calendar.getInstance();
			String CURRENT_DATE = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);		
			return CURRENT_DATE;
		}

		// USED TO GET THE CURRENT DATE TIME
		public  String getCurrentDateTime(){
			Calendar cal = Calendar.getInstance();
			String CURRENT_DATE_TIME = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR)
									  + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND)
									  + "." + cal.get(Calendar.MILLISECOND);		
			return CURRENT_DATE_TIME;
		}
		
		// USED TO COLLECT THE CURRENT FINANCIAL YEAR MONTH LIST
		public List<String[]> getMonthList(){
			List<String[]> monthList = new ArrayList<String[]>();
			
			Calendar cal = Calendar.getInstance();
			cal.roll(Calendar.MONTH, -1);
			int PRE_MONTH = cal.get(Calendar.MONTH) + 1;
			if(PRE_MONTH >= 4 ){			
				for( int S_MONTH = 4; S_MONTH <= PRE_MONTH; S_MONTH++){				
					int m = S_MONTH-1;				
					monthList.add(new String[]{Integer.toString(S_MONTH), MONTH_NAME[m]});		
				}
			}
			else{
				for( int S_MONTH = 4; S_MONTH <= 12; S_MONTH++){				
					int m = S_MONTH-1;				
					monthList.add(new String[]{Integer.toString(S_MONTH), MONTH_NAME[m]});		
				}
				for( int S_MONTH = 1; S_MONTH <= PRE_MONTH; S_MONTH++){
					int m = S_MONTH-1;
					monthList.add(new String[]{Integer.toString(S_MONTH), MONTH_NAME[m]});				
			}
			}
			return monthList;
		}
		
		// 20 July 2013
		// USED TO GET THE CURRENT DATE TIME	
		public List getMonthListReqular(){
			List monthList = new ArrayList();			
			Calendar cal = Calendar.getInstance();
			cal.roll(Calendar.MONTH, -1);
			
				for( int S_MONTH = 1; S_MONTH <= 12; S_MONTH++){				
					int m = S_MONTH-1;
					PropertyModel propertyModel = new PropertyModel();
					propertyModel.setPropertyCode(Integer.toString(S_MONTH));
					propertyModel.setPropertyValue(MONTH_NAME[m]);
					monthList.add(propertyModel);	
				}			
			return monthList;
		}
		
		// USED TO GET THE CURRENT DATE TIME
		public  List getYearList(){
			List YearList = new ArrayList();			
			Calendar cal = Calendar.getInstance();
			
			for(int i=0; i>=-1; i--){
				cal.add(Calendar.YEAR, i);
				PropertyModel propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(Integer.toString(cal.get(Calendar.YEAR)));
				propertyModel.setPropertyValue(Integer.toString(cal.get(Calendar.YEAR)));
				YearList.add(propertyModel);	
			}
		    return YearList;
		}
		
public Date Correct(String abc){
			

			SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
			Date date=null;
			try{if(abc!=null && abc!=""){
				date=format.parse(abc);
			}}
			catch (ParseException e){
			e.printStackTrace();	
			}
			 return date;
			
			
		}
		
		
		
	
}
