package com.infotech.sgsy.tcSetup.tcSetupDue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;


public class TcSetUpDueDAO {
	

	
	//.1..to show the record present in user_district_target
	public List view() throws HibernateException, Exception {
		String SQL = null;
		List ViewList = new ArrayList();
		
		SQL = "select id,tc_id, reciept_date, hr_id, visit_date, app_rej_date, status_due_dil,"
				                           + " remarks_due_dili, tc_app_capacity,tc_status,remarks   from due_deligence";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			ViewList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return ViewList;
}
	
	
	public  Object  getById(Class <?> abc,String id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			
				 result= session.get(abc, id); 
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
	
		/*public List getSrlmPerson(String state_name) throws Exception {
			String sQL = null;
			List SrlmList = new ArrayList();
			System.err.println("state_name...for srlm"+state_name);
			sQL = "select id,person_name,designation from mst_hr_team where entity_code  =(select state_code from mst_state where upper(state_name)=upper('"+state_name+"'))";
		
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Query query = session.createSQLQuery(sQL);
				query.setParameter("sector_code", sectorId);
				SrlmList = query.list();
				System.out.println("..SrlmList..."+SrlmList);
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
					persister.closeSession();
			}
			return SrlmList;
		}*/
		//.5...to find that id is unique or not
		public TcSetupDueVO getTradeTargetDetails(String string){
			TcSetupDueVO  tradeTarget=new TcSetupDueVO();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(TcSetupDueVO.class)
	                    .add(Restrictions.eq("id", string));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	tradeTarget= (TcSetupDueVO) result;
	               
	            }
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return tradeTarget;
		}
		
					
				/*//.6....to get the state name absed on project id
				public String getStateName(String projectid) {
					
						Persister persister = PersisterImpl.getPersister();
						Session session = persister.getSession();
						Transaction tr = session.getTransaction();
						String statename="";
						List<String> entryStatusDetailList = new ArrayList<String>();
						String SQL_Query = null;
						SQL_Query = "select   state_name from project_detail_new  where projectid=:projectid";
						try {
							tr.begin();
							Query Query = session.createSQLQuery(SQL_Query);
							Query.setParameter("projectid", projectid);
							entryStatusDetailList = Query.list();
							tr.commit();
							statename =  entryStatusDetailList.get(0).toString();
							System.out.println("statename....."+statename);			
						} catch (Exception e) {
							e.printStackTrace();
						}
						return statename;
					}*/
				//to save the data
			     public boolean save(TcSetupDueVO tcSetupDueVO) throws Exception {

						boolean flag = false;
						Persister persister = PersisterImpl.getPersister();
						Session session = persister.getSession();
						Transaction tx = session.beginTransaction();
						try {
							session.persist(tcSetupDueVO);
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
					public boolean update(TcSetupDueVO tcSetupDueVO) throws Exception {

						boolean flag = false;
						Persister persister = PersisterImpl.getPersister();
						Session session = persister.getSession();
						Transaction tx = session.beginTransaction();
						try {
							session.update(tcSetupDueVO);
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
//...delete..
				public boolean delete(TcSetupDueVO projectVo) throws Exception {

					boolean flag = false;
					Persister persister = PersisterImpl.getPersister();
					Session session = persister.getSession();
					Transaction tx = session.beginTransaction();
					try {
						session.delete(projectVo);
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
				
				public   Object  getListByCondition(Class<?> abc,String [] parameter){
					Object result=null;
					Persister persister = PersisterImpl.getPersister();
					Session session = persister.getSession();
					Transaction tx = session.beginTransaction();
					try{
						 Criteria criteria = session.createCriteria(abc)
								 .add(Restrictions.eq(parameter[0], parameter[1])) 
								 .add(Restrictions.eq("eSopCertReq", "Yes"))
								 .add(Restrictions.eq("isActive", "Yes"));
						 		
						 
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
	


