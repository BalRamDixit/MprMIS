package com.infotech.sgsy.master;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class AbsFinder {
	
	public static Object  getById(Class <?> abc,Object id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			if(id instanceof String){
				 result= session.get(abc,(String) id); 
			}else{
				 result= session.get(abc,(Integer) id); 
			}
				
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
	public static Object getDropDownList(Class<?> abc,String[] parameter){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(abc)
			    .setProjection(Projections.projectionList()
			      .add(Projections.property(parameter[0]),parameter[0] )
			      .add(Projections.property(parameter[1]),parameter[1] ))
			    .addOrder(Order.asc(parameter[1]))
			    .setResultTransformer(Transformers.aliasToBean(abc));

		result=cr.list();
		  tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	public static Object getDropDownListByCondotion(Class<?> abc,String[] parameter,String condition[]){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(abc)
				.add(Restrictions.eq(condition[0], condition[1]))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property(parameter[0]),parameter[0] )
			    .add(Projections.property(parameter[1]),parameter[1] ))
			    .addOrder(Order.asc(parameter[1]))
			    .setResultTransformer(Transformers.aliasToBean(abc));

		result=cr.list();
		  tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	public static Object  getdetailByCondition(Class<?> abc,String [] parameter){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc)
					 .add(Restrictions.eq(parameter[0], parameter[1]));
	        result=criteria.uniqueResult(); 
	      //  tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	public static Object  getListByCondition(Class<?> abc,String [] parameter){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc)
					 .add(Restrictions.eq(parameter[0], parameter[1]));
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
	public static Object  getListByConditionByAscOrder(Class<?> abc,String [] parameter,String orderBy){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc)
					 .add(Restrictions.eq(parameter[0], parameter[1]))
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
	public static Object  getList(Class<?> abc){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc);
					           
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
	public static Object  getListByAscOrder(Class<?> abc,String orderBy){
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
	public static Object getcountUsingcondition(Class<?> abc,String[] parameter){
		
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria crit = session.createCriteria(abc)
		               .add(Restrictions.eq(parameter[0], parameter[1]))
		               .setProjection(Projections.rowCount());
		result= crit.uniqueResult();
		 tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
		//.add( Restrictions.isNotNull("birthDate"))
	}
	
public static Object getListByProjectIds(Class<?> abc,String columnName,ArrayList<String> ids){
		
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria crit = session.createCriteria(abc)
				.add(Restrictions.in(columnName, ids));
		result= crit.list();
		 tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
		//.add( Restrictions.isNotNull("birthDate"))
	}
/*public static Object join(String projectId){
	Object result=null;
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tx = session.beginTransaction();
	try{
	Criteria cr = session.createCriteria(TradeTargetVO.class,"trade")
			.createAlias("trade.sector", "sector")
			.add(Restrictions.eq("project.id",projectId ))
		    .setProjection(Projections.projectionList()
		    .add(Projections.property("sector.sectorId"),"sectorId")
		    .add(Projections.property("sector.sectorName"),"sectorName")
		    .add(Projections.groupProperty("sector.sectorId")))
		    .setResultTransformer(Transformers.aliasToBean(SectorMasterVO.class));

	result=cr.list();
	  tx.commit();
	}catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
	} finally {
		session.close();
	}
	 .add(Projections.property(parameter[0]),parameter[0] )
	    .add(Projections.property(parameter[1]),parameter[1] )
	return result;
}*/
	

}
