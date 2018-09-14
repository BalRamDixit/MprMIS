package com.infotech.sgsy.state.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;

public class MonitorPpwsPlaceDAO {
	
	//...to get the batch id....
	public MonitorPpwsPlaceVO getdetailByBatchId(String batchId) throws Exception {
		MonitorPpwsPlaceVO moniobj = new MonitorPpwsPlaceVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(MonitorPpwsPlaceVO.class)
					.add(Restrictions.eq("batchId.id", batchId));
			moniobj = (MonitorPpwsPlaceVO) query.uniqueResult();
		tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				try {
					persister.closeSession();
				} catch (HibernateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return moniobj;
	}
	
	
	public boolean save(MonitorPpwsPlaceActionForm monPlaceForm, String userId) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
	
	
	try {
		for(int i=0;i<monPlaceForm.getBatchId().length;i++){
			MonitorPpwsPlaceVO	 helperVO=(MonitorPpwsPlaceVO) session.get(MonitorPpwsPlaceVO.class, monPlaceForm.getId()[i]);
			
			if(helperVO==null){
				helperVO=new MonitorPpwsPlaceVO();
				helperVO.setCreatedBy(userId);
				helperVO.setCreatedOnDate(new Date());
			}
			else{
				helperVO.setUpdatedBy(userId);	
				helperVO.setUpdatedOnDate(new Date());
			}
			
			helperVO.setTcId(monPlaceForm.getTcId());
			BatchCreationVO batchCreationVO=new BatchCreationVO();
			batchCreationVO.setId(monPlaceForm.getBatchId()[i]);
			helperVO.setBatchId(batchCreationVO);
			
			helperVO.setAppointed(monPlaceForm.getAppointed()[i]);
			helperVO.setCommenced_Total(monPlaceForm.getCommenced_Total()[i]);
			helperVO.setCommenced_Sc(monPlaceForm.getCommenced_Sc()[i]);
			helperVO.setCommenced_St(monPlaceForm.getCommenced_St()[i]);
			helperVO.setCommenced_Others(monPlaceForm.getCommenced_Others()[i]);
			helperVO.setCommenced_Women(monPlaceForm.getCommenced_Women()[i]);
			helperVO.setCommenced_Minority(monPlaceForm.getCommenced_Minority()[i]);
			helperVO.setCommenced_Pwd(monPlaceForm.getCommenced_Pwd()[i]);
			
			helperVO.setCandidate_Assessed(monPlaceForm.getCandidate_Assessed()[i]);
			helperVO.setCandidate_Certified(monPlaceForm.getCandidate_Certified()[i]);
			
		    session.saveOrUpdate(helperVO);
	}
		tx.commit();
		flag = true;
	}
	
catch (Exception e) {
	e.printStackTrace();
	tx.rollback();
} finally {
	session.close();
}
return flag;
}
	
		
public List getBCList(String tcID) throws Exception  {
		
		String sQL = null;
		List bcList = new ArrayList();
		
		sQL = "select batch_id from batch_creation where tc_trade_id='"+tcID+"'"; 			
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			bcList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		 System.err.println(" in dao BC_list");
		return bcList;
		
	}
	
//...for validation....from monitorppwsTrain
public MonitorPpwsTrainVO getListOfPPWS1(String batchId,String tcId) throws Exception {
	
MonitorPpwsTrainVO list = new MonitorPpwsTrainVO();
	
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();
	//System.out.println("..444. ---> "+batchId+"......."+tcId); 
	try {	
		 Criteria criteria = session.createCriteria(MonitorPpwsTrainVO.class)
                    .add(Restrictions.eq("batchId.id", batchId))
                    .add(Restrictions.eq("tcId", tcId));
		 
		 list=(MonitorPpwsTrainVO) criteria.uniqueResult();
//		System.out.println("...working...charvi....for monitor ka feild allana hai....validation"+list);
	} catch (HibernateException e) {
		e.printStackTrace();
	} finally {
		if(session.isOpen())
			session.close();
	}
	return list;
}

	

}
	
