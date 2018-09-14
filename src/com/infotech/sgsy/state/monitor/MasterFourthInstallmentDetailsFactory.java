package com.infotech.sgsy.state.monitor;

public class MasterFourthInstallmentDetailsFactory {

	static MonitorFourthInstVO obj = null ;
	private MasterFourthInstallmentDetailsFactory(){
		
	}
	public static MonitorFourthInstVO getInstance(String viewName) throws ClassNotFoundException{
		
		try {
			obj =(MonitorFourthInstVO)(Object)Class.forName(viewName).newInstance();
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
