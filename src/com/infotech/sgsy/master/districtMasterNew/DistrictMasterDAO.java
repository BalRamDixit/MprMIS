package com.infotech.sgsy.master.districtMasterNew;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class DistrictMasterDAO {

	public Object  getDistrictList(){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(DistrictMasterVO.class);
	        result=criteria.list(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	public Object  getById(Class <?> abc,String id){
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
	public boolean save(DistrictMasterVO districtMasterVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(districtMasterVO);
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
	public boolean update(DistrictMasterVO districtMasterVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(districtMasterVO);
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
	public boolean delete(String stateId) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			DistrictMasterVO districtMasterVO=(DistrictMasterVO) session.load(DistrictMasterVO.class, (String) stateId); 
				session.delete(districtMasterVO);
			session.flush();
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return flag;
	}

}


