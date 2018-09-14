package com.infotech.sgsy.selfHelpGroup;

public class MasterMemberFactory {
 
	static MasterShgMemberDetails obj = null ;
	private MasterMemberFactory(){
		
	}
	public static MasterShgMemberDetails getInstance(String viewName) throws ClassNotFoundException{
		
		try {
			obj =(MasterShgMemberDetails)(Object)Class.forName(viewName).newInstance();
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
