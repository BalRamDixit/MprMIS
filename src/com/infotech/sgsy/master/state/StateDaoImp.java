package com.infotech.sgsy.master.state;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetVO;

public class StateDaoImp {
	
	
	
	public boolean saveOrUpdateStateDetails(StateForm stateform,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {

			StateVO stateVo = (StateVO) session.get(StateVO.class, stateform.getStateId());
			if (stateVo == null) {
				stateVo = new StateVO();
				stateVo.setStateCode(stateform.getStateCode());
				stateVo.setStateName(stateform.getStateName());
				stateVo.setStateShortName(stateform.getStateShortName());
				stateVo.setCenterShare(stateform.getCenterShare());
				stateVo.setStateShare(stateform.getStateShare());
				stateVo.setSc_st(stateform.getSc_st());
				stateVo.setMiniority(stateform.getMiniority());
				stateVo.setWomen(stateform.getWomen());
				stateVo.setCreatedOn(new Date());
				stateVo.setCreatedBy(loginVO.getUserid());
			} else {
				stateVo.setStateCode(stateform.getStateCode());
				stateVo.setStateName(stateform.getStateName());
				stateVo.setStateShortName(stateform.getStateShortName());
				stateVo.setCenterShare(stateform.getCenterShare());
				stateVo.setStateShare(stateform.getStateShare());
				stateVo.setSc_st(stateform.getSc_st());
				stateVo.setMiniority(stateform.getMiniority());
				stateVo.setWomen(stateform.getWomen());
				stateVo.setUpdateBy(loginVO.getUserid());
				stateVo.setUpdatedOn(new Date());
				

			}

			session.saveOrUpdate(stateVo);

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
	public boolean save(StateVO stateVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
        session.save(stateVo);

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
	
	public Object  getList(Class<?> abc){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc);
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
	public Object  getResultById(Class<?> abc,Object id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 if(id instanceof Integer){
				 result= session.get(abc, (Integer) id); 
			 }else{
				 result= session.get(abc, (String) id);
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	public boolean delete(Class<?> abc,String stateId) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			StateVO statevo=(StateVO) session.get(abc, (String) stateId); 
				session.delete(statevo);
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
