package com.infotech.skills.business.proposal;

import java.util.List;

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

public interface PiaProposalBusiness {
	
	public boolean insertOrUpdatePlacementDetails (PlacementVO plavo);

	
}
