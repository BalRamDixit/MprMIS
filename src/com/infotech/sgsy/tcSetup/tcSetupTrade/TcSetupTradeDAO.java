package com.infotech.sgsy.tcSetup.tcSetupTrade;

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
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;

public class TcSetupTradeDAO {

	 public List getDetails(String entity_code,String status) throws HibernateException, Exception {
		String SQL = null;
		List DetailList = new ArrayList();
		
		//SQL = " select projectid,tcid,sectorcode,tradecode,apptradecapacity,id  from  tc_trade_new " ;
		
		/*if("pia".equals(status))
			SQL ="select  a.projectid,a.tcid,a.sectorcode,a.tradecode,a.apptradecapacity,a.id from tc_trade_new as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"' order by a.projectid asc";
			else
				SQL ="select  a.projectid,a.tcid,a.sectorcode,a.tradecode,a.apptradecapacity,a.id from tc_trade_new as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"' order by a.projectid asc";
		
		*/
		if("pia".equals(status))
			SQL="select  a.projectid,a.tcid,sum(a.apptradecapacity) from tc_trade_new as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"' group by a.projectid,a.tcid order by a.projectid asc";
				else
			SQL="select  a.projectid,a.tcid,sum(a.apptradecapacity) from tc_trade_new as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"' group by a.tcid,a.projectid order by a.projectid asc";

		
		
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
	}
	 
	
	 public List<TcSetupTradeActionVO> gettcsetUPTrade(String tcid){
		 List<TcSetupTradeActionVO>  tradeTarget=new ArrayList<TcSetupTradeActionVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(TcSetupTradeActionVO.class)
	                    .add(Restrictions.eq("tcId", tcid));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.list();
	           
	            	tradeTarget= (List<TcSetupTradeActionVO>) result;
	               
	            
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return tradeTarget;
		}
	 public TcSetupTradeActionVO gettcsetUPTradeById(int id){
		 TcSetupTradeActionVO  tradeTarget=new TcSetupTradeActionVO();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(TcSetupTradeActionVO.class)
	                    .add(Restrictions.eq("id", id));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	           
	            	tradeTarget= (TcSetupTradeActionVO) result;
	               
	            
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return tradeTarget;
		}
	 
	
	public boolean save(TcSetupTradeActionForm projectFormvo,String entity_code) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectFormvo.getSectorCode().length;i++){
				TcSetupTradeActionVO projectVo = new TcSetupTradeActionVO();
				/*projectVo.setProjectID(projectFormvo.getProjectID());
				projectVo.setTcId(projectFormvo.getTcId());
				projectVo.setSectorCode(projectFormvo.getSectorCode()[i]);
				projectVo.setTradeCode(projectFormvo.getTradeCode()[i]);*/
				projectVo.setAppTradeCapacity(projectFormvo.getAppTradeCapacity()[i]);
				projectVo.setCreatedBy(entity_code);
				projectVo.setCreatedOn(new Date());
				session.save(projectVo);
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
	
	public boolean update(TcSetupTradeActionForm tcSetupTradeActionForm,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<tcSetupTradeActionForm.getId().length;i++){
				TcSetupTradeActionVO tcSetupTradeActionVO =(TcSetupTradeActionVO) session.get(TcSetupTradeActionVO.class, tcSetupTradeActionForm.getId()[i]);
				if(tcSetupTradeActionVO==null){
					tcSetupTradeActionVO=new TcSetupTradeActionVO();
					TcSetupVO trainningCenter=new TcSetupVO();
					trainningCenter.setId(tcSetupTradeActionForm.getTcId());
					tcSetupTradeActionVO.setTrainningCenter(trainningCenter);
					tcSetupTradeActionVO.setCreatedBy(loginVO.getUserid());
					tcSetupTradeActionVO.setCreatedOn(new Date());
					
				}else{
					tcSetupTradeActionVO.setUpdatedBy(loginVO.getUserid());
					tcSetupTradeActionVO.setUpdatedOn(new Date());
				}
				SectorMasterVO sector=new SectorMasterVO();
				sector.setSectorId(tcSetupTradeActionForm.getSectorCode()[i]);
				TradeMasterVO trade=new TradeMasterVO();
				trade.setTradeId(tcSetupTradeActionForm.getTradeCode()[i]);
				tcSetupTradeActionVO.setSector(sector);
				tcSetupTradeActionVO.setTrade(trade);
				tcSetupTradeActionVO.setAppTradeCapacity(tcSetupTradeActionForm.getAppTradeCapacity()[i]);
				session.saveOrUpdate(tcSetupTradeActionVO);
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
	
	
	

	
	
	/*public List getSectorList(String projectId) throws HibernateException, Exception {
		String SQL = null;
		List SectorList = new ArrayList();
		
		SQL = "select b.sector_id ,b.sector_name from trade_target_new as a , mst_sector as b where  a.sector=b.sector_id and a.projectid ='"+projectId+"' group by b.sector_id";
		
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
	public List getProjectByEntitycode(String entity_code,String status) throws Exception {
		String sQL = null;
		List projectlist = new ArrayList();
		
		if("pia".equals(status)){
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
	}*/
	/*public List getTradebySector(String sectorId) throws Exception {
		String sQL = null;
		List TradeList = new ArrayList();
		
		sQL = "select a.trade_code,a.trade_name from mst_trade as a WHERE a.sector_code='"+sectorId+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			query.setParameter("sector_code", sectorId);
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
	}*/
	public boolean delete(String  tcid) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			 List<TcSetupTradeActionVO> list= gettcsetUPTrade(tcid);
			 for(TcSetupTradeActionVO helperVo:list){
			session.delete(helperVo);
			session.flush();
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
	public boolean deleteById(List<String> ids) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(String id:ids){
			TcSetupTradeActionVO helperVo=(TcSetupTradeActionVO) session.load(TcSetupTradeActionVO.class, id);
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
	public List getTrainingCenter(String projectId) throws Exception {
		String sQL = null;
		List TradeList = new ArrayList();
		
		sQL = "select a.tcID from tc_new as a WHERE a.projectid='"+projectId+"'";
		
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
	public Integer getTotalApprovedCapacity(String tcid) throws HibernateException, Exception {
		Integer santarget =0;
		/*PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select a.tcappcapacity,a.id from user_due_deligence a where a.trainingcenterid='R002HR201302029'";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			//query.setParameter("projectid", projectid);
			List list = query.list();
			//santarget=(Integer) list.get(0);
			//santarget=500;
		//	tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("Error while getting state name by code" + e.getMessage());
		} finally {
			if (session.isOpen())
 				session.close();
		}*/
		String sQL = "select a.tcappcapacity  from user_due_deligence as a WHERE a.trainingcenterid='"+tcid+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			/*query.setParameter("sector_code", sectorId);*/
			santarget = (Integer) query.uniqueResult();
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return santarget;
	}
	public static Object getSelected__sector__Of__Project(String projectId){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(TradeTargetVO.class,"trade")
				.createAlias("trade.sector", "sector")
				.add(Restrictions.eq("project.id",projectId ))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property("sector.sectorId"),"sectorId")
			    .add(Projections.property("sector.sectorName"),"sectorName")
			    .add(Projections.groupProperty("sector.sectorId")))
			    .addOrder(Order.asc("sectorName"))
			    .setResultTransformer(Transformers.aliasToBean(SectorMasterVO.class));

		result=cr.list();
		  tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		 /*.add(Projections.property(parameter[0]),parameter[0] )
		    .add(Projections.property(parameter[1]),parameter[1] )*/
		return result;
	}
	public static Object getSelected__Trade__Of__Project_By_sector(String projectId,String sectorId){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(TradeTargetVO.class,"tradeTarget")
				.createAlias("tradeTarget.trade", "trade")
				.add(Restrictions.eq("project.id",projectId ))
				.add(Restrictions.eq("sector.sectorId",sectorId ))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property("trade.tradeId"),"tradeId")
			    .add(Projections.property("trade.tradeName"),"tradeName")
			    .add(Projections.property("trade.tradeCode"),"tradeCode")
			    .add(Projections.groupProperty("trade.tradeId")))
			    .addOrder(Order.asc("tradeName"))
			    .setResultTransformer(Transformers.aliasToBean(TradeMasterVO.class));

		result=cr.list();
		  tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		 /*.add(Projections.property(parameter[0]),parameter[0] )
		    .add(Projections.property(parameter[1]),parameter[1] )*/
		return result;
	}
		
}
