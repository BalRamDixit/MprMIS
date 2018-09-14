package com.infotech.sgsy.master.state;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class StateDAO {

	protected final Log log = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	public List<StateVO> getStateList() throws Exception {
		List<StateVO> StateList = new ArrayList<StateVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String query_hql = "select st from StateVO st WHERE st.stateCode NOT IN ('09','06') order by st.stateName";

		try {
			Query query = session.createQuery(query_hql);
			StateList = (List<StateVO>) query.list();
			tr.commit();
		} catch (HibernateException e) {
			log.error("error in get Groups " + e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return StateList;
	}

	public List getStates(LocationVO locationVO) throws Exception {
		List results = null;
		String query = "select st from com.infotech.sgsy.master.state.StateVO st order by st.stateName";
		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			results = (List) persister.getObjectsByQuery(query);
			transaction.commit();

		} catch (Exception e) {
			log.error("error in get Groups " + e);
			throw e;
		}
		return results;
	}

	public List getStatesWide() throws Exception {
		List results = null;
		String query = "select st from com.infotech.sgsy.master.state.StateVO st order by st.stateName";
		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			results = (List) persister.getObjectsByQuery(query);
			transaction.commit();
		} catch (Exception e) {
			log.error("error in get Groups " + e);
			throw e;
		}
		return results;
	}

	/**
	 * @author cvas2273
	 * @param locationVO
	 * @return
	 * @throws Exception
	 */
	public List getStatesByLevel(LocationVO locationVO) throws Exception {
		List results = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = persister.getTransaction();
		String query_hql = "select st from com.infotech.sgsy.master.state.StateVO st where st.stateCode= :stateCode order by st.stateName";
		try {
			transaction.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("stateCode", locationVO.getStateCode());
			results = (query).list();
			transaction.commit();
			return results;
		} catch (Exception e) {
			log.error("error while getting State By Level " + e.getMessage());
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	/**
	 * @author cvas2273
	 * @param locationVO
	 * @return
	 * @throws Exception
	 */
	public String getStateShortNameByCode(String stateCode) throws Exception {

		String stateShortName = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select st.stateShortName from com.infotech.sgsy.master.state.StateVO st where st.stateCode=:stateCode order by st.stateName";

		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("stateCode", stateCode);
			stateShortName = (String) query.uniqueResult();
			tr.commit();

		} catch (Exception e) {

			log.error("error while getting state short name By code "
					+ e.getMessage());

			throw e;
		} finally {
			if (session.isOpen())
				session.close();
		}

		return stateShortName;
	}

	public String getStateNameByCode(String stateCode) {
		String stateName = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select s.stateName from com.infotech.sgsy.master.state.StateVO s where s.stateCode= :stateCode";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("stateCode", stateCode);
			stateName = (String) query.uniqueResult();
			tr.commit();
		} catch (Exception e) {
			log.error("Error while getting state name by code" + e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}

		return stateName;
	}

	public List<StateVO> getStateListByProjectId(String projectId) {
		List<StateVO> stateList = new ArrayList<StateVO>();
		String query_sql = "select distinct s.state_name, p.state_code from mst_state as s, "
				+ "mst_proposal_district as p where p.state_code = s.state_code "
				+ "and p.project_id = :projectId";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		try {
			tr.begin();
			// List list = session.createQuery(query).list();
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("projectId", projectId);
			List list = (query).list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				StateVO state = new StateVO();
				state.setStateName(arr[0].toString());
				state.setStateCode(arr[1].toString());
				stateList.add(state);
			}
			tr.commit();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return stateList;
	}

	public List<StateVO> getStateDetail() throws Exception {
		List<StateVO> results = new ArrayList<StateVO>();
		String query = "from com.infotech.sgsy.master.state.StateVO st order by st.stateName";
		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			results = (List<StateVO>) persister.getObjectsByQuery(query);
			transaction.commit();
			persister.close();
		} catch (Exception e) {
			log.error("error in get Groups " + e);
			throw e;
		}
		return results;
	}
	
	                /*
	                 * starts from 28-03-2017
	                 * 
	                 * new methods 
	                 * 
	                 * @by kamal
	                 * 
	                 * */
	
	public boolean saveOrUpdate(StateForm stateform) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
				StateVO stateVo=(StateVO) session.get(StateVO.class,stateform.getStateCode());
				if(stateVo==null){
					stateVo=new StateVO();
					BeanUtils.copyProperties(stateVo,stateform);
					
				}else{
					
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

}
