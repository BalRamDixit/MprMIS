package com.infotech.sgsy.util.Scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerForRejIncompPIAPndMoreThanAMnth {
  
	public SchedulerForRejIncompPIAPndMoreThanAMnth() throws Exception {

		try {
			// Grab the Scheduler instance from the Factory
			SchedulerFactory sf = new StdSchedulerFactory();
			
			// and start it off
			Scheduler sched = sf.getScheduler();

			JobDetail job = newJob(RejIncompPIAPndForMoreThanAMnthSendMail.class)
		    .withIdentity("myJob", "group2")
		    .build();

			CronTrigger  trigger = newTrigger()
		    .withIdentity("trigger2", "group2")
		    .withSchedule(cronSchedule("0 30 17 * * ?")
		    .withMisfireHandlingInstructionFireAndProceed())
		    .forJob("myJob", "group2")
		    .build();
		
			sched.scheduleJob(job, trigger);
			
			sched.start();
			
			//sched.shutdown(true);

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}

}
