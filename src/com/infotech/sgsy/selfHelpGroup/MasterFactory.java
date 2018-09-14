package com.infotech.sgsy.selfHelpGroup;

public class MasterFactory {

	static Object obj=null;
	private MasterFactory()
	{
		
	}
	public static Object getInstance(String viewName) throws ClassNotFoundException{
		
		try {
			obj =(Object)Class.forName(viewName).newInstance();
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
