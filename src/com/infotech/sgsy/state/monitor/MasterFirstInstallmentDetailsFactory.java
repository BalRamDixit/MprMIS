	package com.infotech.sgsy.state.monitor;

	public class MasterFirstInstallmentDetailsFactory {
		
		static MonitorFirstInstVO obj = null ;
		
		private MasterFirstInstallmentDetailsFactory(){
			
		}
		public static MonitorFirstInstVO getInstance(String viewName) throws ClassNotFoundException{
			
			try {
				obj =(MonitorFirstInstVO)(Object)Class.forName(viewName).newInstance();
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
