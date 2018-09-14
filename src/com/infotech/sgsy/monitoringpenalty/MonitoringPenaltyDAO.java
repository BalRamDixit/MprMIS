package com.infotech.sgsy.monitoringpenalty;

 
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
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.monitoringalerts.MonitoringAlertVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
 
public class MonitoringPenaltyDAO {

	
/*	public List getDetails(String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List DetailList = new ArrayList();
		
		SQL = "select id,project_id,type_of_penalty,reason_for_penalty,status,appeal_disposal_result from monitor_penaltynew where entity_code='"+entity_code+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			DetailList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return DetailList;
}*/
	
	public List<MonitoringPenaltyVO> getdetails() throws HibernateException, Exception {
		List<MonitoringPenaltyVO> penaltyList=new ArrayList<MonitoringPenaltyVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(MonitoringPenaltyVO.class);
			 for(Object o : criteria.list()) {
				 penaltyList.add((MonitoringPenaltyVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		System.out.println(penaltyList);
		return penaltyList;
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
	
	
 	public boolean delete(MonitoringPenaltyVO helperVo) throws Exception {

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
	
	
	public int getSrNumber() {
		int maxid =0;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select max(a.id) from com.infotech.sgsy.monitoringpenalty.MonitoringPenaltyVO as a";
		
		
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
	
	
	

	/*public MonitoringPenaltyVO editPage(String id) throws HibernateException, Exception {
		
		MonitoringPenaltyVO dataVO= new MonitoringPenaltyVO();
 		Connection con = null;  		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_GET = ""; 
		try{			
			SQL_GET=" select id,project_id,type_of_penalty,reason_for_penalty,status,date_of_issue,last_date_of_appeal,actual_date_of_appeal,"
					+ "last_date_of_appealdisposal,actual_date_of_appealdisposal,appeal_disposal_result from monitor_penaltynew where  id='"+id+"'";
 					 
			con =PersisterImpl.getConnection();		
			con.setAutoCommit(false);													
			stm = con.prepareStatement(SQL_GET);			
			 rs=stm.executeQuery();				 
			 if(rs.next()){	  		
				 
				     dataVO.setId(rs.getInt("id"));
 					 dataVO.setProjectID(rs.getString("project_id"));
					 dataVO.setTypeOfPenalty(rs.getString("type_of_penalty"));
					 dataVO.setReasonForPenalty(rs.getString("reason_for_penalty"));
					 dataVO.setStatus(rs.getString("status"));
					 dataVO.setDateofIssue(rs.getDate("date_of_issue"));
					 dataVO.setLastDateOfAppeal(rs.getDate("last_date_of_appeal"));
					 dataVO.setActualDateOfAppeal(rs.getDate("actual_date_of_appeal"));
					 dataVO.setLastDateOfAppealDisposal(rs.getDate("last_date_of_appealdisposal"));
					 dataVO.setActualDateOfAppealDisposal(rs.getDate("actual_date_of_appealdisposal"));
					 dataVO.setAppealDisposalResult(rs.getString("appeal_disposal_result")); 
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
	
	
	public MonitoringPenaltyVO getPenaltyDetails(String id){ 
		MonitoringPenaltyVO  penalty=new MonitoringPenaltyVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		
		
 		try {
          Criteria criteria = session.createCriteria(MonitoringPenaltyVO.class)
                  .add(Restrictions.eq("id", id));

          // Convenience method to return a single instance that matches the
          // query, or null if the query returns no results.
          Object result = criteria.uniqueResult();
          if (result != null) {
        	  penalty= (MonitoringPenaltyVO) result;
           }
      }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
          session.close();
      }
		return penalty;
	} 
	
	
	/*public boolean update(MonitoringPenaltyVO helperVo) throws Exception {

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
	}*/
	
	
	

 	public boolean update(MonitoringPenaltyVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVO);
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
	
	
	public boolean save(MonitoringPenaltyVO monitorVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(monitorVo);
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
