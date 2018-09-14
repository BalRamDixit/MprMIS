package com.infotech.sgsy.common;

public class MasterBusinessFactory {
	
	static BusinessMaster obj = null ;
	
	private MasterBusinessFactory(){
		
	}

	public static BusinessMaster getInstance(String viewName) throws ClassNotFoundException{
		try {
			obj =(BusinessMaster)(Object)Class.forName(viewName).newInstance();
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
