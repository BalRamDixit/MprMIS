package com.infotech.sgsy.util.Scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SchedulerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Stop scheduler");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			SchedulerForPIAIncomplete schedulerForPIAIncomplete = new SchedulerForPIAIncomplete();
			SchedulerForRejIncompPIAPndMoreThanAMnth schedulerForRejectingIncompletePIA = new SchedulerForRejIncompPIAPndMoreThanAMnth();

			System.out.println("initialize method call");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	

}
