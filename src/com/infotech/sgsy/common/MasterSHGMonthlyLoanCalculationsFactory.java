package com.infotech.sgsy.common;

import com.infotech.sgsy.selfHelpGroup.MasterSHGMonthlyLoanCalculationsVO;

public class MasterSHGMonthlyLoanCalculationsFactory {

	static MasterSHGMonthlyLoanCalculationsVO obj = null ;
	
	private MasterSHGMonthlyLoanCalculationsFactory(){
	}
	
	public static MasterSHGMonthlyLoanCalculationsVO getInstance(String viewName) throws ClassNotFoundException{
		try {
			obj =(MasterSHGMonthlyLoanCalculationsVO)(Object)Class.forName(viewName).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return obj;		
	}

}
