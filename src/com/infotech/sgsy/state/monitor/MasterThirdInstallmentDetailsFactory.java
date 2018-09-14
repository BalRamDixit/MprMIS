package com.infotech.sgsy.state.monitor;

public class MasterThirdInstallmentDetailsFactory {
	
	static MonitorThirdInstVO obj = null ;
	private MasterThirdInstallmentDetailsFactory(){
		
	}
	public static MonitorThirdInstVO getInstance(String viewName) throws ClassNotFoundException{
		
		try {
			obj =(MonitorThirdInstVO)(Object)Class.forName(viewName).newInstance();
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
