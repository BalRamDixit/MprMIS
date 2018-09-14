package com.infotech.sgsy.master.specialArea;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.state.StateVO;

public class SpecialAreaDao {

	public Object  getSpecialAreaList(){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(SpecialAreaVo.class);
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
	public Object  getResultById(String id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			
				 result= session.get(SpecialAreaVo.class, id); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	public boolean delete(String id) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			SpecialAreaVo specialAreaVo=(SpecialAreaVo) session.get(SpecialAreaVo.class,id); 
			session.delete(specialAreaVo);
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
	public boolean save(SpecialAreaVo specialAreaVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(specialAreaVo);
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
	public boolean update(SpecialAreaForm specialAreaForm,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			SpecialAreaVo specialAreaVo=(SpecialAreaVo) session.get(SpecialAreaVo.class, specialAreaForm.getSpecialAreaId());
			if(specialAreaVo!=null){
				specialAreaVo.setSpecialAreaCode(specialAreaForm.getSpecialAreaCode());
				specialAreaVo.setSpecialAreaName(specialAreaForm.getSpecialAreaName());
				specialAreaVo.setUpdateBy(loginVO.getUserid());
				specialAreaVo.setUpdatedOn(new Date());
				session.update(specialAreaVo);
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
	
	

}
