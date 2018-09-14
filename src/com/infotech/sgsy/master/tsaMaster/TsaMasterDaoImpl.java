package com.infotech.sgsy.master.tsaMaster;

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


public class TsaMasterDaoImpl {
			protected final Log log = LogFactory.getLog(getClass());
		
	 public boolean checkRecord(String tsaName)throws Exception {
			
			log.info("====checkRecord method Starts===in dao Impl===>");
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	              Criteria criteria = session.createCriteria(TsaMasterVO.class)
	                    .add(Restrictions.eq("tsaName", tsaName));

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

		public boolean save(TsaMasterVO tsaMasterVO) throws Exception {

			boolean flag = false;
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			try {
				session.save(tsaMasterVO);
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

		public List<TsaMasterVO> getAllRecords(){
			List<TsaMasterVO> list=new ArrayList<TsaMasterVO>();
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Criteria query = session.createCriteria(TsaMasterVO.class);
				
				for(Object o : query.list()) {
				    list.add((TsaMasterVO)o);
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
			return list;
		}
		
		
		
		public boolean deleteRecordFromId(TsaMasterVO tsaMasterVO) {
			boolean status=false;
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			try {
				session.delete(tsaMasterVO);
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
		
		
	public TsaMasterVO getRecordFromId(String id)throws Exception {
			
			log.info("====checkRecord method Starts======>");
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(TsaMasterVO.class)
	                    .add(Restrictions.eq("id", id));

	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	return (TsaMasterVO)result;
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
		
		public boolean saveOrUpdate(TsaMasterVO tsaMasterVO) throws Exception {
            
			boolean flag = false;
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			System.out.println(tsaMasterVO);
			try {
				session.update(tsaMasterVO);
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

	
