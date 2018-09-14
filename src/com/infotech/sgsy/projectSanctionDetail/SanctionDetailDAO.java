package com.infotech.sgsy.projectSanctionDetail;

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
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;

public class SanctionDetailDAO {
	
	//.1..to show the record present in user_district_target
	public List<SanctionDetailVO> view(){
		List<SanctionDetailVO> list=new ArrayList<SanctionDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(SanctionDetailVO.class);
			
			for(Object o : query.list()) {
				SanctionDetailVO ob=(SanctionDetailVO)o;
				ob.setCreatedBy(ob.getProjectId().getProjectID());
			    list.add(ob);
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
		return list;
}
	//.2..to get the project id	
	/*public List getProjectList(String levelCode) throws HibernateException, Exception {
		String SQL = null;
		List ProjectList = new ArrayList();
		if(levelCode.length()==2)
		{
			SQL = " select  projectid from project_detail_new   where entity_code='"+levelCode+"'";
		}
		
		else{
			SQL = " select  projectid from project_detail_new   where piaprn='"+levelCode+"'";
			
		}
		if(levelCode.length()==2)
		{
			SQL = "select b.state_name, a.projectid from project_detail_new as a,mst_state as b  where a.entity_code=b.state_code and a.entity_code='"+levelCode+"'";
		}
		
		else{
			SQL = "select b.state_name, a.projectid from project_detail_new as a,mst_state as b  where a.entity_code=b.state_code and a.piaprn='"+levelCode+"'";
			
		}
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			
			ProjectList = query.list();
			
			System.out.println("ProjectList"+ProjectList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return ProjectList;
}*/
	
	
	
		//.5...to find that id is unique or not
		public SanctionDetailVO getTradeTargetDetails(String id){
			SanctionDetailVO  tradeTarget=new SanctionDetailVO();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(SanctionDetailVO.class)
	                    .add(Restrictions.eq("id", id));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	tradeTarget= (SanctionDetailVO) result;
	               
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
		
/*		public List getProjectDetailApprovalDate(String projectid) throws Exception {
			
			System.out.println("....projectid..."+projectid);
			String sQL = null;
			List DateOfEC = new ArrayList();
			
			sQL = "select dateofec from project_detail_new where projectid ='"+projectid+"'";
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Query query = session.createSQLQuery(sQL);
				
				DateOfEC = query.list();
				System.out.println("...DateOfEC.."+DateOfEC);
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
					persister.closeSession();
			}
			return DateOfEC;
		}*/
		//to save the data
	     public boolean save(SanctionDetailVO projectVo) throws Exception {

				boolean flag = false;
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tx = session.beginTransaction();
				try {
					session.persist(projectVo);
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
			public boolean update(SanctionDetailVO projectVo) throws Exception {

				boolean flag = false;
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tx = session.beginTransaction();
				try {
					session.update(projectVo);
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
				public boolean delete(SanctionDetailVO projectVo) throws Exception {

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
				
			//...for getting the state name by project id	
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
}
