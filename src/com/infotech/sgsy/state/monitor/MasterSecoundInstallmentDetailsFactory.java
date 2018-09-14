package com.infotech.sgsy.state.monitor;

public class MasterSecoundInstallmentDetailsFactory {
	
	static MonitorSecondInstVO obj = null ;
	private MasterSecoundInstallmentDetailsFactory(){
		
	}
	public static MonitorSecondInstVO getInstance(String viewName) throws ClassNotFoundException{
		
		try {
			obj =(MonitorSecondInstVO)(Object)Class.forName(viewName).newInstance();
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
