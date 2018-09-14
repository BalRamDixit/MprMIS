
package com.infotech.sgsy.monitoringsystem;

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
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;

public class MonitoringInspectionDAO {
	
	public List getTrainId(String prID) throws Exception {
		String sQL = null;
		List TrainingList = new ArrayList();
		sQL = " select tc_id from training_center_detail where project_id='" + prID + "'";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			TrainingList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
				
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return TrainingList;
	}
	
	public static Object  getById(Class <?> abc,String id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			
				 result= session.get(abc, id); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	public MonitoringInspectionVO getdetailByBatchId(String batchId) throws Exception {
		MonitoringInspectionVO moniobj = new MonitoringInspectionVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(MonitoringInspectionVO.class)
					.add(Restrictions.eq("batchId.id", batchId));
			moniobj = (MonitoringInspectionVO) query.uniqueResult();
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

	public List<MonitoringInspectionVO> getTrainingId(String projectid) throws Exception {
		List<MonitoringInspectionVO> list = new ArrayList<MonitoringInspectionVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(MonitoringInspectionVO.class)
					.add(Restrictions.eq("prId", projectid));
			if (query.list() != null && query.list().size() >= 1) {
				for (Object o : query.list()) {
					list.add((MonitoringInspectionVO) o);
				}
			}
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
		return list;
	}

	public boolean saveorupdate(MonitoringInspectionForm monForm, UserMaster loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < monForm.getBatchId().length; i++) {
				System.out.println("==========> "+monForm.getBatchId()[i]);
				MonitoringInspectionVO monitoringInspectionVO=(MonitoringInspectionVO) session.get(MonitoringInspectionVO.class, monForm.getId()[i]);
				if (monitoringInspectionVO == null) {
					monitoringInspectionVO = new MonitoringInspectionVO();
					monitoringInspectionVO.setCreatedBy(loginVO.getId());
					monitoringInspectionVO.setCreatedOn(new Date());
				}else{
					monitoringInspectionVO.setUpdatedBy(loginVO.getId());
					monitoringInspectionVO.setUpdatedOn(new Date());
				}
				monitoringInspectionVO.setState(monForm.getState());
				monitoringInspectionVO.setYear(monForm.getYear());
				monitoringInspectionVO.setMonth(monForm.getMonth());
				
				BatchCreationVO batch=new BatchCreationVO();
				batch.setId(monForm.getBatchId()[i]);
				monitoringInspectionVO.setBatchId(batch);
				
				monitoringInspectionVO.setConductedByQAdate(monForm.getConductedByQAdate());
				monitoringInspectionVO.setConductedBySRLMdate(monForm.getConductedBySRLMdate());
				monitoringInspectionVO.setConductedByCTSAdate(monForm.getConductedByCTSAdate());
				
				monitoringInspectionVO.setRemarksSRLM(monForm.getRemarksSRLM()[i]);
				monitoringInspectionVO.setRemarksQa(monForm.getRemarksQa()[i]);
				monitoringInspectionVO.setRemarksCtsa(monForm.getRemarksCtsa()[i]);
				
				monitoringInspectionVO.setRemarkstcCtsa(monForm.getRemarkstcCtsa());
				monitoringInspectionVO.setRemarkstcQa(monForm.getRemarkstcQa());
				monitoringInspectionVO.setRemarkstcSRLM(monForm.getRemarkstcSRLM());;
				
				monitoringInspectionVO.setAdvisoryRaisedBySrlm(monForm.getAdvisoryRaisedBySrlm());
				monitoringInspectionVO.setAdvisoryRaisedByCtsa(monForm.getAdvisoryRaisedByCtsa());
				monitoringInspectionVO.setAdvisoryClosedByCtsa(monForm.getAdvisoryClosedByCtsa());
				monitoringInspectionVO.setAdvisoryClosedBySrlm(monForm.getAdvisoryClosedBySrlm());
				
				session.saveOrUpdate(monitoringInspectionVO);
			}

			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

 	public MonitoringInspectionVO getBatchList( String batchId) {
			/*List<MonitoringInspectionVO> batchList=new ArrayList<MonitoringInspectionVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			Object tcid=trainingId;
			try{
 			    Criteria criteria1 = session.createCriteria(TcSetupVO.class).add(Restrictions.in("id", tcid));
 			    Criteria criteria2 = session.createCriteria(TcSetupTradeActionVO.class).add(Restrictions.in("trainningCenter", criteria1.list()));
				Criteria criteria = session.createCriteria(BatchCreationVO.class).add(Restrictions.in("tcID", criteria2.list()));;
				 for(Object o : criteria.list()) {
					 batchList.add((MonitoringInspectionVO)o);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
			System.out.println("man-alertList"+batchList);
			return batchList;*/
 		MonitoringInspectionVO moniobj = new MonitoringInspectionVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(MonitoringInspectionVO.class)
					.add(Restrictions.eq("batchId.id", batchId));
			moniobj = (MonitoringInspectionVO) query.uniqueResult();
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

}
