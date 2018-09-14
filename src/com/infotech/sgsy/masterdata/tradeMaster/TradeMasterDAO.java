package com.infotech.sgsy.masterdata.tradeMaster;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
 


public class TradeMasterDAO {

	public boolean save(TradeMasterVO helperVo) throws Exception {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(helperVo);
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
	
	
	
	
	public Object getDropDownList(Class<?> abc,String[] parameter){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(abc)
			    .setProjection(Projections.projectionList()
			      .add(Projections.property(parameter[0]),parameter[0] )
			      .add(Projections.property(parameter[1]),parameter[1] ))
			    .setResultTransformer(Transformers.aliasToBean(abc));

		result=cr.list();
		
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
 
	public Object  getById(Class <?> abc, String id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			
				 result= session.get(abc,id); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		System.err.println("result-->"+result);
		return result;
		
	}
	
	
	
/*	public Object  getByABId(Class <?> abc,AssessmentBodyVO assessmentBodyVO){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			
				 result= session.get(abc, (Serializable) assessmentBodyVO); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	*/
	
	
 public TradeMasterVO getRecordFromId(String id)throws Exception {
		//log.info("====checkRecord method Starts======>");		
 		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(TradeMasterVO.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return (TradeMasterVO)result;
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
	
	
	public boolean deleteRecordFromId(TradeMasterVO helperVO) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(helperVO);
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
	
	 
	public List<TradeMasterVO> getAllRecords(){
		List<TradeMasterVO> list=new ArrayList<TradeMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(TradeMasterVO.class);
			
			for(Object o : query.list()) {
			    list.add((TradeMasterVO)o);
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
	
 	public boolean saveOrUpdate(TradeMasterVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVO);
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
