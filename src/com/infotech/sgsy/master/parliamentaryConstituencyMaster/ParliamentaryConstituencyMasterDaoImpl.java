package com.infotech.sgsy.master.parliamentaryConstituencyMaster;

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



public class ParliamentaryConstituencyMasterDaoImpl {

	protected final Log log = LogFactory.getLog(getClass());
	
	public boolean checkRecord(String ctsaName)throws Exception {
		
		log.info("====checkRecord method Starts===in dao Impl===>");
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
              Criteria criteria = session.createCriteria(ParliamentaryConstituencyMasterVO.class)
                    .add(Restrictions.eq("name", ctsaName));

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

	public boolean save(ParliamentaryConstituencyMasterVO ctsaMasterVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(ctsaMasterVO);
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

	public List<ParliamentaryConstituencyMasterVO> getAllRecords(){
		List<ParliamentaryConstituencyMasterVO> list=new ArrayList<ParliamentaryConstituencyMasterVO>();
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(ParliamentaryConstituencyMasterVO.class);
			
			for(Object o : query.list()) {
			    list.add((ParliamentaryConstituencyMasterVO)o);
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
	
	
	
	public boolean deleteRecordFromId(ParliamentaryConstituencyMasterVO ctsaMasterVO) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(ctsaMasterVO);
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
	
	
public ParliamentaryConstituencyMasterVO getRecordFromId(String id)throws Exception {
		
		log.info("====checkRecord method Starts======>");
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ParliamentaryConstituencyMasterVO.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return (ParliamentaryConstituencyMasterVO)result;
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
	
	public boolean saveOrUpdate(ParliamentaryConstituencyMasterVO ctsaMasterVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(ctsaMasterVO);
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
