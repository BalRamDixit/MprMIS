package com.infotech.sgsy.projectSetup.tradeTarget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.stateFinance.StateFinancialVO;
import com.infotech.sgsy.stateSetup.hrTeam.HRTeamVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.util.CommonUtils;

public class TradeTargetDAO {

	
	
	
	public List view(String entity_code,String status) throws HibernateException, Exception {
		String SQL = null;
		List ViewList = new ArrayList();
		
	//	SQL = "select projectid,sector,trade,othertrade,totaltradeduration,ojt,id from trade_target_new";
		
		if(status.equals("pia")){
			SQL = "select a.projectid,SUM(a.trainingTarget) from trade_target_new  as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"' group by a.projectid";
		}else{
			SQL="select a.projectid,SUM(a.trainingTarget) from trade_target_new  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"' group by a.projectid";
			//SQL = "select a.projectid,a.sector,a.trade,a.othertrade,a.totaltradeduration,a.ojt,a.id from trade_target_new  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		}
		
		
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
	
	
	public List<TradeTargetVO> getTradeTargetDetails(String projectID){
		List<TradeTargetVO>  tradeTarget=new ArrayList<TradeTargetVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(TradeTargetVO.class)
                    .add(Restrictions.eq("projectID", projectID))
                    .addOrder(Order.asc("id"));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.list();
            
            	tradeTarget= (List<TradeTargetVO>) result;
           
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return tradeTarget;
	}
	
	
	public int getTotalTrainingTarget(String projectid) {
		int santarget = 0;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select a.sanTarget from com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO a where a.projectId='"+projectid+"'";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			//query.setParameter("projectid", projectid);
			santarget = (int) query.uniqueResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("Error while getting state name by code" + e.getMessage());
		} finally {
			if (session.isOpen())
 				session.close();
		}

		return santarget;
	}
	
	
	
	
	
	public String getTotalTradeDuration(String tradeid) throws Exception {
		String sQL = null;
		String totalduration=null;
		
		sQL = "select a.course_duration  from mst_trade as a WHERE a.trade_code='"+tradeid+"'";
	
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			/*query.setParameter("sector_code", sectorId);*/
			totalduration = (String) query.uniqueResult();
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return totalduration;
	}
	
	
	
	/*public TradeTargetVO editPage(String projectId) throws HibernateException, Exception {
		
		TradeTargetVO dataVO = new TradeTargetVO();
		Connection con = null;
		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_GET = "";
		
		try{
			
			SQL_GET="select projectid,sector,trade,othertrade,totaltradeduration,ojt,trainingtarget,nonresifull,nonresipart,nonresiweekend,resifull from trade_target_new where projectid='"+projectId+"'";
			con =PersisterImpl.getConnection();		
			con.setAutoCommit(false);													
			stm = con.prepareStatement(SQL_GET);
			
			 rs=stm.executeQuery();
		
			 if(rs.next()){
			
				  
					 
					 dataVO.setProjectID(rs.getString("projectid"));
					 dataVO.setSector(rs.getString("sector"));
					 dataVO.setTrade(rs.getString("trade"));
					 dataVO.setOtherTrade(rs.getString("othertrade"));
					 dataVO.setTotalTradeDuration(rs.getString("totaltradeduration"));
					 dataVO.setOjt(rs.getString("ojt"));
					 dataVO.setTrainingTarget(rs.getString("trainingtarget"));
 					 dataVO.setNonResiFull(rs.getString("nonresifull"));
				     dataVO.setNonResiPart(rs.getString("nonresipart"));
					 dataVO.setNonResiWeekend(rs.getString("nonresiweekend"));
					 dataVO.setResiFull(rs.getString("resifull"));
					 
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
	
	
	
	
	
	
	
	
	
	
	
	
	public List getSectorList() throws HibernateException, Exception {
		String SQL = null;
		List SectorList = new ArrayList();
		
		SQL = "select sector_id,sector_name from mst_sector ";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
 			SectorList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return SectorList;
}
	
	
	
	public List getTradebySector(String sectorId) throws Exception {
		String sQL = null;
		List TradeList = new ArrayList();
		
		sQL = "select a.trade_code,a.trade_name from mst_trade as a WHERE a.sector_code='"+sectorId+"'";
	
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			/*query.setParameter("sector_code", sectorId);*/
			TradeList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return TradeList;
	}
	
	
	
	public List getProjectByEntitycode(String entity_code,String status) throws Exception {
		String sQL = null;
		List projectlist = new ArrayList();
		
		//sQL = "select a.projectid from project_detail_new  as a WHERE a.entity_code='"+entity_code+"'";
		
		//sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"' and a.projectid not in (select projectid from trade_target_new )";

		
		//main query
		//sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		
		///new code for pia login
		
		if(status.equals("pia")){
			sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"'";
		}else{
			sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		}
		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			
			projectlist = query.list();
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
	 //currently PIAPRN is retrieving not name 
	public String getPIANameByCode(String entitycode) {
		String piaName = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select s.piaPrn from com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO s where s.piaPrn= :piaPrn";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("piaPrn", entitycode);
			List list= query.list();
			piaName=(String) list.get(0);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}

		return piaName;
	}
	
	
	public List getTradeList() throws HibernateException, Exception {
		String SQL = null;
		List TradeList = new ArrayList();
		
		SQL = "select trade_code,trade_name from mst_trade";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			TradeList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return TradeList;
}
	
	public List getProjectList() throws HibernateException, Exception {
		String SQL = null;
		List ProjectList = new ArrayList();
		
		SQL = "select state,project_id  from project_detail ";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
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
}
	
	
	
	public boolean save(TradeTargetActionForm projectform) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectform.getSector().length;i++){
				TradeTargetVO helpervo=new TradeTargetVO();
				
				/*helpervo.setProjectID(projectform.getProjectID());
				helpervo.setSector(projectform.getSector()[i]);
				helpervo.setTrade(projectform.getTrade()[i]);*/
				helpervo.setOtherTrade(projectform.getOtherTrade()[i]);
				helpervo.setTotalTradeDuration(projectform.getTotalTradeDuration()[i]);
				helpervo.setOjt(projectform.getOjt()[i]);
				helpervo.setTrainingTarget(projectform.getTrainingTarget()[i]);
				helpervo.setNonResiFull(projectform.getNonResiFull()[i]);
				helpervo.setNonResiPart(projectform.getNonResiPart()[i]);
				helpervo.setNonResiWeekend(projectform.getNonResiWeekend()[i]);
				helpervo.setResiFull(projectform.getResiFull()[i]);
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
	
	public boolean update(List<TradeTargetVO> list) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(TradeTargetVO tradeTargetVo :list){
			    session.saveOrUpdate(tradeTargetVo);
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
	public boolean delete(String projectid) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<TradeTargetVO> tradelist=getTradeTargetDetails(projectid);
			for(TradeTargetVO herpervo:tradelist){
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
	public boolean deleteById(List<Integer> ids) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int id:ids){
				TradeTargetVO helperVo=(TradeTargetVO) session.load(TradeTargetVO.class, id);
			session.delete(helperVo);
			session.flush();
			
			}
			tx.commit();
			//
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	public Object getDropDownListByCondotion(Class<?> abc,String[] parameter,String condition[]){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(abc)
				.add(Restrictions.eq(condition[0], condition[1]))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property(parameter[0]),parameter[0] )
			    .add(Projections.property(parameter[1]),parameter[1] )
			    .add(Projections.property(parameter[2]),parameter[2] ))
			    .addOrder(Order.asc(parameter[1]))
			    .setResultTransformer(Transformers.aliasToBean(abc));

		result=cr.list();
		  tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	//join in hibernate 
	/* 
	 * String sql = "select stud.firstname,stud.roll,sub.subjectname,cls.classname,exam.exam_name,exam.from_time,exam.to_time,exam.exam_date from Student stud,Subject sub,Classs cls,Exam exam where exam.classs=cls.id and exam.subject=sub.id and sub.classs=cls.id and stud.classs=cls.id and stud.roll= :roll";
     SQLQuery query = sessionHb.createSQLQuery(sql);
     query.setParameter("roll", a);
     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
     return query.list();
     
     */
	
}
