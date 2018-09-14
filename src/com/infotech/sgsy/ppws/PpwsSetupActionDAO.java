package com.infotech.sgsy.ppws;

import java.util.ArrayList;
import java.util.Date;
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
 
public class PpwsSetupActionDAO {

	public boolean save(PpwsSetupActionForm projectVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectVo.getEntryMonth().length;i++){
				PpwsSetupActionVO helpervo=new PpwsSetupActionVO();
				//System.out.println("project id---====>   "+projectVo.getProjectId());
			//	helpervo.setProjectId(projectVo.getProjectId());
				helpervo.setEntryMonth(projectVo.getEntryMonth()[i]);
				helpervo.setEntryYear(projectVo.getEntryYear()[i]);
				helpervo.setExpTrainComn(projectVo.getExpTrainComn()[i]);
				helpervo.setExpTrainComp(projectVo.getExpTrainComp()[i]);
				helpervo.setPlaceExp(projectVo.getPlaceExp()[i]);
				helpervo.setCreatedBy("");
				helpervo.setCreatedOn(new Date());
				session.save(helpervo);
				
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
	public boolean update(PpwsSetupActionForm projectVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectVo.getId().length;i++){
				PpwsSetupActionVO helpervo=(PpwsSetupActionVO) session.get(PpwsSetupActionVO.class, projectVo.getId()[i]);
				if(helpervo==null){
					helpervo=new PpwsSetupActionVO();
					//helpervo.setProjectId(projectVo.getProjectId());
					helpervo.setCreatedBy("");
					helpervo.setCreatedOn(new Date());
				}
				//System.out.println("project id---====>   "+projectVo.getProjectId());
				//helpervo.setId(projectVo.getId()[i]);
				//helpervo.setProjectId(projectVo.getProjectId());
				helpervo.setEntryMonth(projectVo.getEntryMonth()[i]);
				helpervo.setEntryYear(projectVo.getEntryYear()[i]);
				helpervo.setExpTrainComn(projectVo.getExpTrainComn()[i]);
				helpervo.setExpTrainComp(projectVo.getExpTrainComp()[i]);
				helpervo.setPlaceExp(projectVo.getPlaceExp()[i]);
				helpervo.setUpdatedBy("");
				helpervo.setUpdatedOn(new Date());
				session.saveOrUpdate(helpervo);
				
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
	public boolean saveOrUpdate(List<PpwsSetupActionVO> list) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(PpwsSetupActionVO ppwsSetupActionVO:list){
				session.saveOrUpdate(ppwsSetupActionVO);
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
	
	
	
	
	
	public List<PpwsSetupActionVO> getPPWSdetailbyProjectId(String projectId) throws Exception {
		List<PpwsSetupActionVO> list = new ArrayList<PpwsSetupActionVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		//String query_hql = "From HRTeamVO  WHERE email = :email ";

		try {	
			 Criteria criteria = session.createCriteria(PpwsSetupActionVO.class)
	                    .add(Restrictions.eq("projectId", projectId))
	                    .addOrder(Order.asc("id"));;
			 list= criteria.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())
				session.close();
		}
		return list;
	}
	
	
	
	/*public List getProjectList( ) throws HibernateException, Exception {
		String SQL = null;
		List ProjectList = new ArrayList();
		
		SQL = "select project_id,project_duration_month from sanction_detail ";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
 			ProjectList = query.list();
 			
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
	
	public List getProjectByEntitycode(String entity_code,String status) throws Exception {
		String sQL = null;
		List projectlist = new ArrayList();
		
		/*sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";*/
		/*if("pia".equals(status))
		sQL="select a.project_id from ppws_setup_new as a,project_detail_new as b where b.projectid=a.project_id and b.piaprn='"+entity_code+"' group by a.project_id";
		else 
			sQL="select a.project_id from ppws_setup_new as a,project_detail_new as b where b.projectid=a.project_id and b.entity_code='"+entity_code+"' group by a.project_id";*/
		
		
		if("pia".equals(status))
			sQL="select a.projectID from project_detail_new as a where  a.piaprn='"+entity_code+"' group by a.projectID";
			else 
				sQL="select a.projectID from project_detail_new as a where  a.entity_code='"+entity_code+"' group by a.projectID";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			
			projectlist = query.list();
			System.out.println("list size-->   "+projectlist.size());
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return projectlist;
	}
	public List getProjectByNotInPPWS() throws Exception {
		String sQL = null;
		List projectlist = new ArrayList();
		
		/*sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";*/
		
		sQL="select a.projectid from user_sanction_details as a where a.projectid not in (select project_id from ppws_setup_new)";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			
			projectlist = query.list();
			System.out.println("list size-->   "+projectlist.size());
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return projectlist;
	}
	
	public Object[] getMonthDuration(String projectid) throws HibernateException, Exception {
		String SQL = null;
		Object[] MonthDurationList =new String[3];
		
		SQL = "select projectduration,commdate,enddate from user_sanction_details where projectid='"+projectid+"'";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			MonthDurationList = (Object[]) query.uniqueResult();
 			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		/*System.out.println(Arrays.asList(MonthDurationList).size());*/
		
		return MonthDurationList;
	}
	/*public boolean delete(String projectid) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<PpwsSetupActionVO> ppwsdetail=getPPWSdetailbyProjectId(projectid);
			for(PpwsSetupActionVO herpervo:ppwsdetail){
				session.delete(herpervo);	
			}
			session.flush();
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
	public int deleteAllRowsByProjectId(String projectid) throws HibernateException, Exception {
		String SQL = null;
		int result=0;
		
		SQL = "delete from ppws_setup WHERE project_id='"+projectid+"'";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			result=query.executeUpdate();
 			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		/*System.out.println(Arrays.asList(MonthDurationList).size());*/
		
		return result;
	}
	
	public boolean deleteByprojectId(String projectid) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<PpwsSetupActionVO> tradelist=getPPWSdetailbyProjectId(projectid);
			for(PpwsSetupActionVO herpervo:tradelist){
				session.delete(herpervo);	
			}
			session.flush();
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
	
	
	//delete from ppws_setup_new where id in (select id from ppws_setup_new where project_id ='D002DL2013' order by id DESC limit 4 )
	public int deleteLastRows(String projectid, int numberOfRows) throws HibernateException, Exception {
		String SQL = null;
		int result=0;
		
		SQL = "delete from ppws_setup where id in (select id from ppws_setup where project_id ='"+projectid+"' order by id DESC limit "+numberOfRows+")";
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			result=query.executeUpdate();
 			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		/*System.out.println(Arrays.asList(MonthDurationList).size());*/
		
		return result;
	}
	
	
}
