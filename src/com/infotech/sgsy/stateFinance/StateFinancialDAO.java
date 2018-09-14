package com.infotech.sgsy.stateFinance;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.stateSanctionDetail.StateSanctionVO;

public class StateFinancialDAO {

	public boolean save(StateFinancialVO helperVo) throws Exception {

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
	
	public boolean update(StateFinancialVO helperVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVo);
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
	
	/*public List getfinancialList(int id) throws HibernateException, Exception {
		String SQL = null;
		List detailList = new ArrayList();
		
		SQL = "select  from user_state_financial  where id='"+id+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			detailList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return detailList;
}*/
	
	
	
	public StateSanctionVO getSanctionDetailsByStateId(String stateId) {
		StateSanctionVO  statefinVo=new StateSanctionVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(StateSanctionVO.class)
                    .add(Restrictions.eq("state.stateId", stateId));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.uniqueResult();
            if (result != null) {
            	statefinVo= (StateSanctionVO) result;
               
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return statefinVo;
	}
	
	
	
	
	
	
	//State Name
		@SuppressWarnings("unchecked")
		public String getState(String stateCode) {
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tr = session.getTransaction();
				String statename="";
				List<String> entryStatusDetailList = new ArrayList<String>();
				String SQL_Query = null;
				SQL_Query = "select upper(state_name) as state_name from mst_state where state_code=:stateCode";
				try {
					tr.begin();
					Query Query = session.createSQLQuery(SQL_Query);
					Query.setParameter("stateCode", stateCode);
					System.out.println("stateCode"+stateCode);
					entryStatusDetailList = Query.list();
					tr.commit();
					statename =  entryStatusDetailList.get(0).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return statename;
			}
		
		public StateFinancialVO getStateFinancialDetail(String id){
			StateFinancialVO  statefinVo=new StateFinancialVO();
			Persister persister = PersisterImpl.getPersister();
			StateSanctionVO stateSanction= getSanctionDetailsByStateId(id);
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(StateFinancialVO.class)
	                    .add(Restrictions.eq("sanctionDetailId.id", stateSanction.getId()));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	statefinVo= (StateFinancialVO) result;
	               
	            }
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return statefinVo;
		}
		public StateFinancialVO getStateFinancialDetailByid(int id){
			StateFinancialVO  statefinVo=new StateFinancialVO();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(StateFinancialVO.class)
	                    .add(Restrictions.eq("id", id));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	statefinVo= (StateFinancialVO) result;
	               
	            }
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return statefinVo;
		}
}
