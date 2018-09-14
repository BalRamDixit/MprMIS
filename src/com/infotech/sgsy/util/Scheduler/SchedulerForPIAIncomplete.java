package com.infotech.sgsy.util.Scheduler;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class SchedulerForPIAIncomplete {

	public SchedulerForPIAIncomplete() throws Exception {

		try {
			// Grab the Scheduler instance from the Factory
			SchedulerFactory sf = new StdSchedulerFactory();
			
			// and start it off
			Scheduler sched = sf.getScheduler();

			JobDetail job = newJob(IncompletePIASendMail.class)
		    .withIdentity("myJob", "group1")
		    .build();

			CronTrigger  trigger = newTrigger()
		    .withIdentity("trigger1", "group1")
		    .withSchedule(cronSchedule("0 0 20 * * ?")
		    .withMisfireHandlingInstructionFireAndProceed())
		    .forJob("myJob", "group1")
		    .build();
		
			sched.scheduleJob(job, trigger);
			
			sched.start();
			
			//sched.shutdown(true);

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
	 
}
