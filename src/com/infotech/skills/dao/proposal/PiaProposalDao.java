package com.infotech.skills.dao.proposal;

import com.infotech.skills.hbm.piaproposal.PlacementTiaUpsVO;
import com.infotech.skills.hbm.piaproposal.PlacementVO;
import com.infotech.skills.hbm.piaproposal.ProjectDeliveryVO;
import com.infotech.skills.hbm.piaproposal.TrainingTargetsVO;



/**
 * @since August 2013
 * @author mehra
 *
 */
public interface PiaProposalDao {

	public abstract TrainingTargetsVO getTrainingTarget();
	public abstract boolean insertTrainingTargetDetail(TrainingTargetsVO trainingTargetDetail);
	boolean save(PlacementVO placemnetDetails, PlacementTiaUpsVO plavoTIA);
	boolean save(ProjectDeliveryVO projectDetails);
	
}	

	

