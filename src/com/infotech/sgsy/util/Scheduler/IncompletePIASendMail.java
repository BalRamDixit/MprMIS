package com.infotech.sgsy.util.Scheduler;

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

public class IncompletePIASendMail implements Job{
	
	protected final Log log = LogFactory.getLog(getClass());
	PiaDao piaHelper;
	
	public IncompletePIASendMail(){
		piaHelper = new PiaDaoImpl();
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SendMail sendMail = new SendMail();
		try {
			List<PiaDetailVO> incompletePIAList = new ArrayList<PiaDetailVO>();
			incompletePIAList = piaHelper.getIncompletePIAList();			if (incompletePIAList != null) {
				if (incompletePIAList.size() > 0) {
					for (PiaDetailVO piaInfo : incompletePIAList) {
						String body = "Temporary Reference Number: "
								+ piaInfo.getPiaCode()
								+ "\n"
								+ "PIA Name: "
								+ piaInfo.getPiaName()
								+ "\n\n"
								+ "Dear Applicant,\n\n"
								+ "Thank you for showing interest in DDU-GKY."
								+ "It is observed that you have still not completed the necessary documentation/filling of appliciation form for us"
								+ "to process the registration.\n\n"
								+ "It is requested to complete the application at the earlest using the link of \"Incomplete Registrations\""
								+ "For any further clarification, please send your query to:\n\n"
								+ "Contact Team DDU-GKY at \n"
								+ "Email-Id: helpdeskprn@gmail.com \n"
								+ "Contact No.: 011-23743625/26 \n"
								+ "Extension 243 and 204 \n\n"
								+ " Sincerely Yours,\n" + "DDU-GKY";

						sendMail.sendMail(
								piaInfo.getEmail(),
								"Subject: Your Application found incomplete",
								body);

						log.info("PIA Name: " + piaInfo.getPiaName()
								+ " found incomplete on date:"
								+ new Date().toString());
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
