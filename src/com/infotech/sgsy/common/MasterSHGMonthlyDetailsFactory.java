package com.infotech.sgsy.common;

import com.infotech.sgsy.selfHelpGroup.MasterSHGMonthlyDetailVO;


public class MasterSHGMonthlyDetailsFactory {

	static MasterSHGMonthlyDetailVO obj = null ;
	
	private MasterSHGMonthlyDetailsFactory(){
	}
	
	public static MasterSHGMonthlyDetailVO getInstance(String viewName) throws ClassNotFoundException{
		try {
			obj =(MasterSHGMonthlyDetailVO)(Object)Class.forName(viewName).newInstance();
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
