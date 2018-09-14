package com.infotech.sgsy.master.certifyingAgency;

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
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;


public class CertifyingAgencyDao {

	protected final Log log = LogFactory.getLog(getClass());
	public boolean save(CertifyingAgencyVO certifyingAgencyVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(certifyingAgencyVO);
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
	
	
	public boolean saveOrUpdate(CertifyingAgencyVO certifyingAgencyVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(certifyingAgencyVO);
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
	
	public List<CertifyingAgencyVO> getAllRecords(){
		List<CertifyingAgencyVO> list=new ArrayList<CertifyingAgencyVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(CertifyingAgencyVO.class);
			
			for(Object o : query.list()) {
			    list.add((CertifyingAgencyVO)o);
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
	
	public CertifyingAgencyVO getRecordFromId(String certifyingAgencyId)throws Exception {
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(CertifyingAgencyVO.class)
                    .add(Restrictions.eq("certifyingAgencyId", certifyingAgencyId));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return (CertifyingAgencyVO)result;
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
	
	public boolean deleteRecordFromId(CertifyingAgencyVO certifyingAgencyVO) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(certifyingAgencyVO);
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
	public boolean checkRecord(String certifyingAgencyName)throws Exception {
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(CertifyingAgencyVO.class)
             .add(Restrictions.eq("certifyingAgencyName", certifyingAgencyName));

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
	
}
