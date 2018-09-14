package com.infotech.sgsy.common;

import com.infotech.sgsy.selfHelpGroup.MasterSHGBankLinkageVO;

public class MasterSHGBankLinkageFactory {

	static MasterSHGBankLinkageVO obj = null ;
	
	private MasterSHGBankLinkageFactory(){
	}
	
	public static MasterSHGBankLinkageVO getInstance(String viewName) throws ClassNotFoundException{
		try {
			obj =(MasterSHGBankLinkageVO)(Object)Class.forName(viewName).newInstance();
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
