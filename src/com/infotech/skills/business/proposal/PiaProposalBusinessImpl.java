package com.infotech.skills.business.proposal;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.infotech.skills.hbm.piaprofile.ActivityVO;
import com.infotech.skills.hbm.piaprofile.CategoryVO;
import com.infotech.skills.hbm.piaprofile.PiaActivityMappingVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.hbm.piaproposal.PlacementVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.exception.InsertFailedException;

public class PiaProposalBusinessImpl implements PiaProposalBusiness {

	/**
	 * FUNCTION USED TO INSERT OR UPDATE THE PLACEMENT DETAIL INTO THE SYSTEM
	 * @return 
	 */
	Log log = LogFactory.getLog(getClass()); // Used to check the logs

	@Override
	public boolean insertOrUpdatePlacementDetails(PlacementVO placemnetDetails) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
		
		try
		{
			log.error(placemnetDetails.getFacilitationBeneficiaries());
			session.saveOrUpdate(placemnetDetails);
			flag=true;
			tr.commit();
		}
		catch (Exception e) {
			log.error("PROBLEM IN insertOrUpdatePlacementDetails(PlacementVO placemnetDetails) FUNCTION: "+e.getMessage());
		}
		finally{
		session.close();
		}
		return flag;
	}
		
}

