package com.infotech.sgsy.stateSetupTarget;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class StateTargetDAO {
	
	
	//to get appraisal dropdown
	public Object getDropDownList(Class<?> abc, String[] parameter){
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
	
	//to get tsa dropdown
	public Object getDropDownListTsa(Class<?> abc, String[] parameter){
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
	//...this code is used to get state name by using state id .using hibernate///
	public  Object  getById(Class <?> abc,String id){
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
	
		
	//to save the data
     public boolean save(StateTargetVO stateVo) throws Exception {

			boolean flag = false;
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			try {
				session.persist(stateVo);
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
	
	//for updating the code
		public boolean update(StateTargetVO stateVo) throws Exception {

			boolean flag = false;
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			try {
				session.update(stateVo);
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

//for the if condition saving values here we are getting data on basic of entity code	
	public StateTargetVO  getDetails(String entityCode) throws Exception {
		/*List<StateTargetVO> list = new ArrayList<StateTargetVO>();*/
		StateTargetVO list = new StateTargetVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
	
		try {	
			 Criteria criteria = session.createCriteria(StateTargetVO.class)
	                    .add(Restrictions.eq("state.stateId", entityCode));
			 list= (StateTargetVO) criteria.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())
				session.close();
		}
		return list;
	}
	public  Object  getListByAscOrder(Class<?> abc,String orderBy){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc)
					           .addOrder(Order.asc(orderBy));
					           
	        result=criteria.list(); 
	        tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	

	
}

