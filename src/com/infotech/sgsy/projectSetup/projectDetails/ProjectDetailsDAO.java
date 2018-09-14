package com.infotech.sgsy.projectSetup.projectDetails;

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
import com.infotech.sgsy.master.state.StateVO;

public class ProjectDetailsDAO {

	
	
	public List getDetails() throws HibernateException, Exception {
		String SQL = null;
		List ViewList = new ArrayList();
		
		SQL = " select file_number, piaprn,pianame, projectscheme,projecttype,projectid from project_detail_new  ";
		
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
 	
	/*public ProjectDetailsVO editPage(String fileNumber) throws HibernateException, Exception {
		
		
		ProjectDetailsVO   dataVO = new ProjectDetailsVO();
		
		
		Connection con = null;
		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_GET = "";
		
		try{
			
			SQL_GET=" select id, file_number, piaprn,pianame, projectscheme,projecttype,projectid,totalprojectcost,"
					+ "centralshare,stateshare,programcost,welfarecost,consortium,prnofconsortiumpartner,prnconsortiumpartnername,"
					+ "partnerapplicantpiasame,dateofec,sanctioned   from project_detail_new  where file_number='"+fileNumber+"'";
				 
			con =PersisterImpl.getConnection();		
			con.setAutoCommit(false);													
			stm = con.prepareStatement(SQL_GET);			
			 rs=stm.executeQuery();		
			 if(rs.next()){
			        dataVO.setId(rs.getInt("id"));
				    dataVO.setFileNumber(rs.getString("file_number"));
				    dataVO.setPiaPrn(rs.getString("piaprn"));
				    dataVO.setPiaName(rs.getString("pianame"));
				    dataVO.setProjectScheme(rs.getString("projectscheme"));
				    dataVO.setProjectType(rs.getString("projecttype"));
				    dataVO.setProjectID(rs.getString("projectid"));
				    dataVO.setTotalProjectCost(rs.getInt("totalprojectcost"));
				    dataVO.setCentralShare(rs.getString("centralshare"));
 				    dataVO.setStateShare(rs.getString("stateshare"));
			        dataVO.setProgramCost(rs.getInt("programcost"));
			        dataVO.setWelfareCost(rs.getInt("welfarecost"));
			        dataVO.setConsortium(rs.getString("consortium"));
                    dataVO.setPrnOfConsortiumPartner(rs.getString("prnofconsortiumpartner"));
			        dataVO.setPrnConsortiumPartnerName(rs.getString("prnconsortiumpartnername"));
			        dataVO.setPartnerApplicantPiaSame(rs.getString("partnerapplicantpiasame"));
			        dataVO.setDateOfEc(rs.getString("dateofec"));
			        dataVO.setSanctioned(rs.getString("sanctioned"));
			       
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
	 
	
	public List getProjectDetailsList( ) throws HibernateException, Exception {
		String SQL = null;
		List PIAList = new ArrayList();
		
		SQL = " select pia_name, pia_registration_no from pia_detail";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			PIAList = query.list();
 			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return PIAList;
	}
	
	public List getProjectTypeList( ) throws HibernateException, Exception {
		String SQL = null;
		List ProjectTypeList = new ArrayList();
		
		SQL = " select project_type_code, project_type_name from project_type_master  ";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			ProjectTypeList = query.list();
 			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return ProjectTypeList;
	}
	
	
	public List getPRNList(String prn) throws HibernateException, Exception {
		String SQL = null;
		List PRNList = new ArrayList();
		
		SQL = " select pia_name, pia_registration_no from pia_detail where pia_registration_no='"+prn.toUpperCase()+"'";
		
		System.err.println("sql="+SQL);
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
 			PRNList = query.list();
 			System.err.println("query -"+PRNList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return PRNList;
	}
	
	
	
	public boolean save(ProjectDetailsVO projectVo) throws Exception {

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
	public boolean delete(ProjectDetailsVO projectVo) throws Exception {

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
	
	
	public boolean update(ProjectDetailsVO projectVo) throws Exception {

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
	public int getSrNumber() {
		int maxid =0;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select max(a.id) from com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO as a";
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
	
	public StateVO getState(String  stateCode) throws Exception {
		StateVO genre=new StateVO(); 
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(StateVO.class)
                    .add(Restrictions.eq("stateCode", stateCode));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.uniqueResult();
            if (result != null) {
            	genre = (StateVO) result;
                
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return genre;
	}
	
	public List getDetailAboutFileNumber(String fileNumber) throws Exception {
		List list = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		//String query_hql = "From HRTeamVO  WHERE email = :email ";

		try {	
			 Criteria criteria = session.createCriteria(ProjectDetailsVO.class)
	                    .add(Restrictions.eq("fileNumber", fileNumber));
			 list= criteria.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())
				session.close();
		}
		return list;
	}
}
