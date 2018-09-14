package com.infotech.sgsy.userAccessControlManagement;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class RoleMasterDaoImpl {
	protected final Log log = LogFactory.getLog(getClass());
	public boolean checkRecord(String roleName)throws Exception {
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		boolean status=false;
		try {
            Criteria criteria = session.createCriteria(RoleMaster.class)
                    .add(Restrictions.eq("roleName", roleName));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	status= true;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return status;
	}
	public boolean save(RoleMaster roleMaster) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(roleMaster);
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
	public boolean saveOrUpdate(RoleMaster roleMaster) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(roleMaster);
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
	public List<RoleMaster> getAllRecords(){
		List<RoleMaster> list=new ArrayList<RoleMaster>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(RoleMaster.class);
			
			for(Object o : query.list()) {
			    list.add((RoleMaster)o);
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
	public RoleMaster getRecordFromId(String id) {
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		RoleMaster rm=null;
		try {
            Criteria criteria = session.createCriteria(RoleMaster.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	rm= (RoleMaster)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return rm;
	}
	public boolean deleteRecordFromId(RoleMaster roleMaster) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(roleMaster);
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
	
}
