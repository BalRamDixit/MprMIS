package com.infotech.sgsy.projectSanctionDetail;

public class MasterSanctionDetailsFactory {
 
	static SanctionDetailVO obj = null ;
	private MasterSanctionDetailsFactory(){
		
	}
	public static SanctionDetailVO getInstance(String viewName) throws ClassNotFoundException{
		
		try {
			obj =(SanctionDetailVO)(Object)Class.forName(viewName).newInstance();
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
