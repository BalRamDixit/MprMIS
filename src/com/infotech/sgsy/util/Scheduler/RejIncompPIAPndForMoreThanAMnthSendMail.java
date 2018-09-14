package com.infotech.sgsy.util.Scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.infotech.sgsy.util.mail.SendMail;
import com.infotech.skills.dao.pia.PiaDao;
import com.infotech.skills.dao.pia.PiaDaoImpl;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;

public class RejIncompPIAPndForMoreThanAMnthSendMail  implements Job {

	
	protected final Log log = LogFactory.getLog(getClass());
	PiaDao piaHelper;
	
	public RejIncompPIAPndForMoreThanAMnthSendMail(){
		piaHelper = new PiaDaoImpl();
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SendMail sendMail = new SendMail();
		try {
			List<PiaDetailVO> monthOldIncompletePIAList = new ArrayList<PiaDetailVO>();
			monthOldIncompletePIAList = piaHelper.getMonthOldIncompletePIAListForRejection();
			if (monthOldIncompletePIAList != null) {
				if (monthOldIncompletePIAList.size() > 0) {
					for (PiaDetailVO piaInfo : monthOldIncompletePIAList) {
						if(piaHelper.getMonthOldPIADeemed(piaInfo)){
						String body ="To,\n"
							        +piaInfo.getPianame()
							        + "\n\nSubject: Faulty in the Application of Permanent Registration Number (PRN)."
					                + "\n\nSir/Madam,"
							        + "\n\nKindly refer to your application with TRN "+piaInfo.getPiacode()+" .Your application is incomplete. It has been noticed that you have not completed the application process with in the mandatory 30 days’ time period for completion of application.  Hence, in terms of Notification No. 44/2015 dated 12th August, 2015 your application is deemed closed and your TRN is cancelled. In case you are interested in obtaining PRN you may apply afresh."
                                    + "\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tUnder Secretary to the Government of India"
                                    + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRural Skills Division"
                                    + "\n\nDISCLAIMER: This is an Auto Generated Mail and signature is not required.";
						sendMail.sendMail(
								piaInfo.getEmail(),
								"Subject: Faulty in the Application of Permanent Registration Number (PRN).",
								body);

						log.info("PIA Name: " + piaInfo.getPianame()
								+ " found incomplete on date:"
								+ new Date().toString());
					}
				}
			  }
			} else {
				log.info("No Project Implementation Agency (PIA) found incomplete on date :"
						+ new Date().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
