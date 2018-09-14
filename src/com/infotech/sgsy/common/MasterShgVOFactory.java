package com.infotech.sgsy.common;

import com.infotech.sgsy.selfHelpGroup.MasterShgVO;

public class MasterShgVOFactory {

	static MasterShgVO obj = null ;
	
	private MasterShgVOFactory(){
		
	}
	
	public static MasterShgVO getInstance(String viewName) throws ClassNotFoundException{
		try {
			obj =(MasterShgVO)(Object)Class.forName(viewName).newInstance();
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
