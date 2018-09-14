package com.infotech.skills.dao.proposal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.infotech.skills.hbm.piaproposal.HumanResourceEmpVO;
import com.infotech.skills.hbm.piaproposal.HumanResourceProposedVO;
import com.infotech.skills.hbm.piaproposal.PlacementTiaUpsVO;
import com.infotech.skills.hbm.piaproposal.PlacementVO;
import com.infotech.skills.hbm.piaproposal.ProjectDeliveryVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.skills.hbm.piaproposal.TrainingTargetsVO;

/**
 * @since August 2013
 */
public class PiaProposalDaoImpl implements PiaProposalDao {

	Log log = LogFactory.getLog(getClass()); // Used to check the logs
	
	// METHOD IS USED FOR SAVE THE DETAILS OF HUMAN RESOURCE EMPLOYED PAGE 
	public boolean saveEmp(HumanResourceEmpVO humanResourceEmpVO) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
			
		try
		{
			tr.begin();
			log.info("Inside PiaProposalDaoImpl saveEmp method");
			session.save(humanResourceEmpVO);
			tr.commit();
			flag=true;
		}
		catch (HibernateException e) {
			log.error(e.getMessage());
		}
		finally{
			if (session.isOpen())
				session.close();
		}
		return flag;
	}
	
	// METHOD IS USED FOR SAVE THE DETAILS OF HUMAN RESOURCE PROPOSED PAGE 
	public boolean saveProp(HumanResourceProposedVO humanResourceProposedVO) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
			
		try
		{
			tr.begin();
			log.info("Inside PiaProposalDaoImpl saveProp method");
			session.save(humanResourceProposedVO);
			tr.commit();
			flag=true;
		}
		catch (HibernateException e) {
			log.error(e.getMessage());
		}
		finally{
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	// METHOD IS USED FOR SAVE THE DETAILS OF PLACEMENT PAGE 
	public boolean save(PlacementVO placemnetDetails, PlacementTiaUpsVO placementTIA) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
			
		try
		{
			tr.begin();
			log.info(placemnetDetails.getFacilitationBeneficiaries());
			session.save(placemnetDetails);
			log.info(placementTIA.getEmpName());
			session.save(placementTIA);
			tr.commit();
			flag=true;
		}
		catch (HibernateException e) {
			log.error(e.getMessage());
		}
		finally{
			if (session.isOpen())
				session.close();
		}
		return flag;
	}

	// METHOD IS USED FOR SAVE THE DETAILS OF PROJECT DELIVERY PAGE 
	public boolean save(ProjectDeliveryVO projectDetails) {
		
		// TODO Auto-generated method stub
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
		try
		{
			tr.begin();
			log.info(projectDetails.getCandidateSocial());
			session.save(projectDetails);
			tr.commit();
			flag=true;
		}
		catch (HibernateException e) {
			log.error(e.getMessage());
		}
		finally{
			if (session.isOpen())
				session.close();
		}
		return flag;
	}
	
	public TrainingTargetsVO getTrainingTarget() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		TrainingTargetsVO trainingDetail=null;
		try {
			tr.begin();
			Query query = session.createQuery("from TrainingTargetsVO");
			trainingDetail=(TrainingTargetsVO) query.list().iterator().next();		    		   
			tr.commit();
		}
		catch(Exception e) {
			System.err.println("PROBLEM IN SRLM getMmuDetail(String entityCode) FUNCTION: " + e.getMessage());
		}
		finally{
			if(session.isOpen())
				session.close();
		}
		return trainingDetail;
	}
	
	public boolean insertTrainingTargetDetail(TrainingTargetsVO trainingTargetDetail) {
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
		try{
			tr.begin();
			session.save(trainingTargetDetail);
			flag=true;
			tr.commit();
		}catch (Exception e) {
			System.err.println("PROBLEM IN  insertTrainingTargetDetail(TrainingTargetsVO trainingTargetDetail) FUNCTION: "+e.getMessage());
		}
		finally{
		session.close();
		}
		return flag;
	}

	
	public List getMonthList(){
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		
		String monthList = "select * from mst_month";
			
		List monthListInfo = new ArrayList();
		try {
			tr.begin();
			monthListInfo = session.createSQLQuery(monthList).list();		    		   
			tr.commit();
		} catch(Exception e) {
			System.err.println(e);
		}finally {
			if (session.isOpen())
				session.close();
		}
		return monthListInfo;
	}

}






