package com.infotech.sgsy.stateSanctionDetail;

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

public class StateSanctionDAO {
	
//State Name
@SuppressWarnings("unchecked")
/*public String getState(String stateCode) {
				
   Persister persister = PersisterImpl.getPersister();
   Session session = persister.getSession();
   Transaction tr = session.getTransaction();
   String statename="";
	List<String> entryStatusDetailList = new ArrayList<String>();
	String SQL_Query = null;
	SQL_Query = "select upper(state_name) as state_name from mst_state where state_id='"+stateCode+"'";
	try {
		tr.begin();
		Query Query = session.createSQLQuery(SQL_Query);
	 	//Query.setParameter("stateCode", stateCode);
		entryStatusDetailList = Query.list();
		tr.commit();
		statename =  entryStatusDetailList.get(0).toString();
	   } 
	catch (Exception e) {
		e.printStackTrace();
		}
	 return statename;
}*/

	public boolean save(StateSanctionVO sanctionVo) throws Exception {

	  boolean flag = false;
	  Persister persister = PersisterImpl.getPersister();
	  Session session = persister.getSession();
	  Transaction tx = session.beginTransaction();
	  try {
		   session.save(sanctionVo);
		   tx.commit();
		   flag = true;
		  }
	  catch (Exception e) {
		  e.printStackTrace();
		  tx.rollback();
		} finally {
		  session.close();
		}
	 return flag;
	}
	
	public List  getState(String stateCode) throws HibernateException, Exception {
		String SQL = null;
		List state = new ArrayList();
		
		SQL = "select upper(state_name) as state_name from mst_state where state_id='"+stateCode+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			state = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return state;
	
	}
	
	
	//for getting the details based on entity code
	
	public List<StateSanctionVO> getDetails(String stateId) throws Exception  {
			
		List<StateSanctionVO> list = new ArrayList<StateSanctionVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
		
			try {	
				 Criteria criteria = session.createCriteria(StateSanctionVO.class)
		                    .add(Restrictions.eq("state.stateId", stateId));
				 list= criteria.list();
				
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				if(session.isOpen())
					session.close();
			}
			return list;
		}
			
	//for updating the code
		public boolean update(StateSanctionVO sanctionVo) throws Exception {

			boolean flag = false;
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			try {
				
				session.update(sanctionVo);
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
		
		public String getScheme(String entityCode) {
			
			   Persister persister = PersisterImpl.getPersister();
			   Session session = persister.getSession();
			   Transaction tr = session.getTransaction();
			   String scheme="";
				List<String> entryStatusDetailList = new ArrayList<String>();
				String SQL_Query = null;
				SQL_Query = "select scheme from state_data where entitycode=:entityCode";
				System.out.println("SQL_Query..."+SQL_Query);
				
				try {
					tr.begin();
					Query Query = session.createSQLQuery(SQL_Query);
					Query.setParameter("entityCode", entityCode);
					entryStatusDetailList = Query.list();
					tr.commit();
					scheme =  entryStatusDetailList.get(0).toString();
					System.out.println("scheme.."+scheme);
				   } 
				catch (Exception e) {
					e.printStackTrace();
					}
				
				 return scheme;
			}

}
