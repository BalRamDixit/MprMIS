package com.infotech.sgsy.monitoringalerts;

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
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class MonitoringAlertDAO {

	
	/*
	public List getdetails(String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List AlertList = new ArrayList();
		
		SQL =" select id, project_id,type_of_alert,status,reason_for_issue,issuing_agency from project_alertNew where entity_code='"+entity_code+"'";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			AlertList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return AlertList;
	} 
	*/
	public MonitoringAlertVO getAlertDetails(int id){ 
		 MonitoringAlertVO  alert=new MonitoringAlertVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
 		
		try {
           Criteria criteria = session.createCriteria(MonitoringAlertVO.class)
                   .add(Restrictions.eq("id", id));

           // Convenience method to return a single instance that matches the
           // query, or null if the query returns no results.
           Object result = criteria.uniqueResult();
           if (result != null) {
           	alert= (MonitoringAlertVO) result;
            }
       }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
           session.close();
       }
		return alert;
	} 

	
	/*public List<MonitoringAlertVO> getdetails() throws HibernateException, Exception {
		List<MonitoringAlertVO> alertList=new ArrayList<MonitoringAlertVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(MonitoringAlertVO.class);
			 for(Object o : criteria.list()) {
				 alertList.add((MonitoringAlertVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		System.out.println(alertList);
		return alertList;
	}*/
	
	
	public List<MonitoringAlertVO> getdetails(List<ProjectDetailsVO> sanctionProjectList) throws HibernateException, Exception {
		List<MonitoringAlertVO> alertList=new ArrayList<MonitoringAlertVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
//			 Criteria criteria1 = session.createCriteria(ProjectDetailsVO.class).add(Restrictions.in("id", sanctionProjectList));
			
			 Criteria criteria = session.createCriteria(MonitoringAlertVO.class).add(Restrictions.in("projectID", sanctionProjectList));;
			 for(Object o : criteria.list()) {
				 MonitoringAlertVO ob=(MonitoringAlertVO)o;
				 alertList.add(ob);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		System.out.println("man-alertList"+alertList);
		return alertList;
	}
/*	
	public List<ProjectDetailsVO> getList(String tcid) throws HibernateException, Exception {
		 
		List<ProjectDetailsVO> tradeList=new ArrayList<ProjectDetailsVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 
			 Criteria criteria = session.createCriteria(ProjectDetailsVO.class)
					 .add(Restrictions.eq("id",tcid));
			 
			 for(Object o : criteria.list()) {
				 tradeList.add((TcSetupTradeActionVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
 		return tradeList;
	}*/
	
	
	
	
	
	public boolean update(MonitoringAlertVO helpervo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helpervo);
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
	
	public int getSrNumber() {
		int maxid =0;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select max(a.id) from com.infotech.sgsy.monitoringalerts.MonitoringAlertVO as a";
		
		
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			//query.setParameter("stateCode", stateCode);
 			maxid =  (int) query.uniqueResult();
			tr.commit();
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}

		return maxid;
	}
	
	
	
	
	
	/*public MonitoringAlertVO editPage(String id) throws HibernateException, Exception {
		
  		MonitoringAlertVO  dataVO= new MonitoringAlertVO();
		
 		Connection con = null;
		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_GET = "";
		
		try{
			
			SQL_GET="  select id,project_id,type_of_alert,date_of_issue,reason_category,reason_for_issue,issuing_agency,date_of_reply_from_pia,"
					+ "reply_from_pia,status,communication_to_pia_date,remarks  from project_alertnew where  id='"+id+"'";
					 
			con =PersisterImpl.getConnection();		
			con.setAutoCommit(false);													
			stm = con.prepareStatement(SQL_GET);				
			 rs=stm.executeQuery();
		
			 if(rs.next()){
				    // dataVO.setId(rs.getInt("id"));
					// dataVO.setProjectID(rs.getString("project_id"));
					 dataVO.setTypeOfAlert(rs.getString("type_of_alert"));
					 dataVO.setDateOfIssue(rs.getDate("date_of_issue"));
					 dataVO.setReasoncategory(rs.getString("reason_category"));
 					 dataVO.setReasonForIssue(rs.getString("reason_for_issue"));
			         dataVO.setIssuingAgency(rs.getString("issuing_agency"));
			         dataVO.setDateOfReplyFromPia(rs.getDate("date_of_reply_from_pia"));
			         dataVO.setReplyFromPia(rs.getString("reply_from_pia"));
			         dataVO.setStatus(rs.getString("status"));
			         dataVO.setCommunicationToPiadate(rs.getDate("communication_to_pia_date"));
			         dataVO.setRemarks(rs.getString("remarks"));
			 }
				 
				 con.commit();
			 }
			 
			 catch(SQLException e){
				System.err.println("ERROR  "+e);
			}
			catch(Exception e){
				System.err.println("ERROR  "+e);
			}finally{
				CommonUtils.closeDatabaseUtil(stm, con, rs);
			}
		
 		return dataVO;
	} */
	
 
	
	public boolean save(MonitoringAlertVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(helperVO);
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
	
	public MonitoringAlertVO getAlertDetails(String id){	
		 
		MonitoringAlertVO  alert=new MonitoringAlertVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(MonitoringAlertVO.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	alert= (MonitoringAlertVO) result;
             }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        } 
		return alert;
	} 
	
	public List getprojectDetails(String entity,String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List projectList = new ArrayList();
		
		SQL =" select projectid,projectid from  project_detail_new where "+entity+"='"+entity_code+"' and sanctioned='Yes'";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			projectList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return projectList;
	} 
	public List getSrlmname(String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List srlmList = new ArrayList<>();
		
		SQL =" select state_name from mst_state  where state_code='"+entity_code+"'";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			srlmList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
 		return srlmList;
	} 
	
	
	public List getPia(String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List piaList = new ArrayList<>();
		
		SQL ="select pianame from project_detail_new  where piaprn='"+entity_code+"'";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			piaList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
 		return piaList;
	} 
	
	
	
	
	public boolean delete(MonitoringAlertVO  helperVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(helperVo);
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
	
 

		public List<SanctionDetailVO> getSanctionDate(String id) throws HibernateException, Exception {
			 
			List<SanctionDetailVO> sanction=new ArrayList<SanctionDetailVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tx = session.beginTransaction();
			int id1= Integer.parseInt(id) ;
			try{				 
				 Criteria criteria = session.createCriteria(SanctionDetailVO.class)
						 .add(Restrictions.eq("id", id1));
				 for(Object o : criteria.list()) {
					 sanction.add((SanctionDetailVO)o);
				}
				 }
			catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
			System.out.println("sanction date"+sanction);
			return sanction;
		}			
		public List<ProjectDetailsVO> getProjectDetails(String id) throws HibernateException, Exception{	
			 
			List<ProjectDetailsVO>  project=new  ArrayList<ProjectDetailsVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(ProjectDetailsVO.class)
	                    .add(Restrictions.eq("id", id));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            for(Object o : criteria.list()) {
	            	project.add((ProjectDetailsVO)o);
				}
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return project;
		}
		
		
		
	}

