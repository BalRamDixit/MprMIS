package com.infotech.sgsy.master.appraisalAgencyMaster;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;


public class AppraisalAgencyDaoImpl {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public boolean checkRecord(String appraisalName)throws Exception {
		
		log.info("====checkRecord method Starts===in dao Impl===>");
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
              Criteria criteria = session.createCriteria(AppraisalAgencyVO.class)
                    .add(Restrictions.eq("appraisalName", appraisalName));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return true;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return false;
	}
	
	public boolean save(AppraisalAgencyVO appraisalAgencyVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(appraisalAgencyVO);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		System.out.println("...................save ho gye hai....");
		return flag;
	}
	
	public List<AppraisalAgencyVO> getAllRecords(){
		List<AppraisalAgencyVO> list=new ArrayList<AppraisalAgencyVO>();
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(AppraisalAgencyVO.class);
			
			for(Object o : query.list()) {
			    list.add((AppraisalAgencyVO)o);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("List to Print is --> "+list);
		return list;
	}
	
	public boolean deleteRecordFromId(AppraisalAgencyVO appraisalAgencyVO) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(appraisalAgencyVO);
			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}
	
	public AppraisalAgencyVO getRecordFromId(String id)throws Exception {
		
		log.info("====checkRecord method Starts======>");
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(AppraisalAgencyVO.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return (AppraisalAgencyVO)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return null;
	}
	public boolean saveOrUpdate(AppraisalAgencyVO appraisalAgencyVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(appraisalAgencyVO);
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



}
