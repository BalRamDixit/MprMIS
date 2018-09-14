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

public class MonitorPpwsTrainDAO {
	

  ///...to get the training center id	...
	
	
	
	//...to get the batch id....
	public MonitorPpwsTrainVO getdetailByBatchId(String batchId, String month, String year) throws Exception {
		MonitorPpwsTrainVO moniobj = new MonitorPpwsTrainVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(MonitorPpwsTrainVO.class)
					.add(Restrictions.eq("batchId.id", batchId))
					.add(Restrictions.eq("month", month))
					.add(Restrictions.eq("year", year));
			moniobj = (MonitorPpwsTrainVO) query.uniqueResult();
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
	
	public MonitorPpwsTrainVO getListOfPPWS(String batchId,String month,String year,String tcId) throws Exception {
		
		MonitorPpwsTrainVO list = new MonitorPpwsTrainVO();
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
	
		try {	
			 Criteria criteria = session.createCriteria(MonitorPpwsTrainVO.class)
	                    .add(Restrictions.eq("batchId", batchId))
	                    .add(Restrictions.eq("month", month))
	                    .add(Restrictions.eq("year", year))
			            .add(Restrictions.eq("tcId", tcId));
			 
			 list=(MonitorPpwsTrainVO) criteria.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())
				session.close();
		}
		return list;
	}
  
	
	
	
	public boolean save(MonitorPpwsTrainActionForm monTrainForm, String month , String year, String userId) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
	
	
	try {
		for(int i=0;i<monTrainForm.getBatchId().length;i++){
			MonitorPpwsTrainVO	 helperVO=(MonitorPpwsTrainVO) session.get(MonitorPpwsTrainVO.class, monTrainForm.getId()[i]);
			
			if(helperVO==null){
				helperVO=new MonitorPpwsTrainVO();
				//helperVO.setProjectId(monTrainForm.getProjectId());
				helperVO.setMonth(month);
				helperVO.setYear(year);
				helperVO.setCreatedBy(userId);
				helperVO.setCreatedOnDate(new Date());
			}
			else{
				helperVO.setUpdatedBy(userId);	
				helperVO.setUpdatedOnDate(new Date());
			}
			
			helperVO.setTcId(monTrainForm.getTcId());
			helperVO.setYear(monTrainForm.getYear());
			helperVO.setMonth(monTrainForm.getMonth());
			
			BatchCreationVO batchCreationVO=new BatchCreationVO();
			batchCreationVO.setId(monTrainForm.getBatchId()[i]);
			helperVO.setBatchId(batchCreationVO);
			
			helperVO.setCommenced_Total(monTrainForm.getCommenced_Total()[i]);
			helperVO.setCommenced_Sc(monTrainForm.getCommenced_Sc()[i]);
			helperVO.setCommenced_St(monTrainForm.getCommenced_St()[i]);
			helperVO.setCommenced_Others(monTrainForm.getCommenced_Others()[i]);
			helperVO.setCommenced_Women(monTrainForm.getCommenced_Women()[i]);
			helperVO.setCommenced_Minority(monTrainForm.getCommenced_Minority()[i]);
			helperVO.setCommenced_Pwd(monTrainForm.getCommenced_Pwd()[i]);
			
			helperVO.setCompleted_Total(monTrainForm.getCompleted_Total()[i]);
			helperVO.setCompleted_Sc(monTrainForm.getCompleted_Sc()[i]);
			helperVO.setCompleted_St(monTrainForm.getCompleted_St()[i]);
			helperVO.setCompleted_Others(monTrainForm.getCompleted_Others()[i]);
			helperVO.setCompleted_Women(monTrainForm.getCompleted_Women()[i]);
			helperVO.setCompleted_Minority(monTrainForm.getCompleted_Minority()[i]);
			helperVO.setCompleted_Pwd(monTrainForm.getCompleted_Pwd()[i]);
			
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
	
	
//....workking ....
		public List getTrainCenter(String projectid) throws Exception {
			
			String sQL = null;
			List TargetTraining = new ArrayList();
			
			sQL = "select tc_id from training_center_detail where project_id='"+projectid+"'";
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Query query = session.createSQLQuery(sQL);
				//query.setParameter("sector_code", sectorId);
				TargetTraining = query.list();
				System.out.println("...TargetTraining.."+TargetTraining);
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
					persister.closeSession();
			}
			return TargetTraining;
		}
		//...this is to find out list on basic of projectd,year,previous month
		
		public List<MonitorPpwsTrainVO> getChecked( String month, String year, String tcId) throws Exception {
			
			List<MonitorPpwsTrainVO> list = new ArrayList<MonitorPpwsTrainVO>();
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
		
			try {	
				 Criteria criteria = session.createCriteria(MonitorPpwsTrainVO.class)
		                    .add(Restrictions.eq("month", month))
		                    .add(Restrictions.eq("year", year))
			                .add(Restrictions.eq("tcId", tcId));
				 
				 list= criteria.list();
				 System.out.println("list...charvi..."+list);
				
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				if(session.isOpen())
					session.close();
			}
			return list;
		}
		
		
}
