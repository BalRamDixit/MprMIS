package com.infotech.sgsy.tcSetup.batchCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.tcSetup.tcSetupDue.TcSetupDueVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.util.CommonUtils;
 
public class BatchCreationDAO {

	
/*	public List getdetails(String maxid) throws HibernateException, Exception {
		String SQL = null;
		List ViewList = new ArrayList();
		 
		SQL = "select id,project_id,tc_id,batch_id,sector_name,trade_name,batch_size from   (select * from "
				+ " (select id,project_id,tc_id,batch_id,sector,trade,batch_size from batch_creation where id='"+maxid+"')x	left join (select (sector_id) as sector, sector_name from mst_sector ) y "
				+ "using (sector))y left join (select (trade_code) as trade,(sector_id)as sector,trade_name from mst_trade)z using (trade) ";
		
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
}*/
	public List<BatchCreationVO> getdetails(List<ProjectDetailsVO> sanctionProjectList) throws HibernateException, Exception {
		List<BatchCreationVO> stateList=new ArrayList<BatchCreationVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 if(sanctionProjectList!=null && sanctionProjectList.size()>=1){
			 Criteria criteria1 = session.createCriteria(TcSetupVO.class).add(Restrictions.in("project", sanctionProjectList));
			 if(criteria1.list()!=null && criteria1.list().size()>=1){
			 Criteria criteria2 = session.createCriteria(TcSetupTradeActionVO.class).add(Restrictions.in("trainningCenter", criteria1.list()));
			 if(criteria2.list()!=null && criteria2.list().size()>=1){		 
			 Criteria criteria = session.createCriteria(BatchCreationVO.class).add(Restrictions.in("tcID", criteria2.list()));
			 for(Object o : criteria.list()) {
				 stateList.add((BatchCreationVO)o);
			}
			 }
			 }
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
 		return stateList;
	}
	
	
	
	
 	
	public List getTCList(String projectID) throws HibernateException, Exception {
		String SQL = null;
		List TCList = new ArrayList();
		
		SQL = " select tc_id,id  from training_center_detail where project_id='"+projectID+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			TCList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return TCList;
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
	/*public List getStartDate(String projectid) throws HibernateException, Exception {
		 
		String SQL = null;
		List dateList = new ArrayList<>();
		
 		SQL ="select visit_date from due_deligence where projectid='"+projectid+"'";

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			dateList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
 		return dateList;
	}*/
	public List<TcSetupDueVO> getStartDate(String projectid) throws HibernateException, Exception {
		 
		List<TcSetupDueVO> stateList=new ArrayList<TcSetupDueVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			/*Criteria criteria1 = session.createCriteria(SanctionDetailVO.class)
					 .add(Restrictions.eq("projectId.id", projectid));*/
			 Criteria criteria = session.createCriteria(TcSetupDueVO.class)
					                .add(Restrictions.eq("trainingCenterId.id", projectid));
			 
			 
			 
			 for(Object o : criteria.list()) {
				 stateList.add((TcSetupDueVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
 		return stateList;
	}
	
	
	public List getSectorList(String tc_id) throws HibernateException, Exception {
		String SQL = null;
		List SectorList = new ArrayList();
		
		SQL="select sector_name, sector_id   from (select tc_id,(sector_code) as sector_id from training_center_trade where tc_id='"+tc_id+"') x  "
				+ "	 left join  (select sector_id  ,sector_name from mst_sector )y  using (sector_id)";
		
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
	

	public List<TcSetupTradeActionVO> getList(String tcid) throws HibernateException, Exception {
		 
		List<TcSetupTradeActionVO> tradeList=new ArrayList<TcSetupTradeActionVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 
			 Criteria criteria = session.createCriteria(TcSetupTradeActionVO.class)
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
	}
	
	
	
	public List<TcSetupVO> getTradeTCList(String tcid) throws HibernateException, Exception {
		 
		List<TcSetupVO> tradeTCList=new ArrayList<TcSetupVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 
			 Criteria criteria = session.createCriteria(TcSetupVO.class)
					 .add(Restrictions.eq("id",tcid));
			 
			 for(Object o : criteria.list()) {
				 tradeTCList.add((TcSetupVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
  		return tradeTCList;
	}
	
	
	
	
	
	
	public List getTradeList(String sector) throws HibernateException, Exception {
		String SQL = null;
		List tradeList = new ArrayList();		
		 
		   SQL=" select trade_name,id from (select  sector_code,trade_code as id from training_center_trade where sector_code='"+sector+"')x "
		   		+ " left join 	 (select id, trade_name from mst_trade  )y using (id)";

		
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			tradeList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return tradeList;
}	
	
	public String getSrNumber() {
		String maxid = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select max(a.id) from com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO as a";
		
		
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
  			maxid =  (String) query.uniqueResult();
			tr.commit();
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}

		return maxid;
	}
	
	
	
	
	public List getProjectList(String entity,String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List ProjectList = new ArrayList();
		
 		SQL = "select projectid,projectid from  project_detail_new where "+entity+"='"+entity_code+"' and sanctioned='Yes'";		
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
	
}
	
	

	public List getbatchId(String sector, String trade, String tcid) throws HibernateException, Exception {
		String SQL = null;
		List batch = new ArrayList();
		
		SQL="select (select tc_id from training_center_detail where id ='"+tcid+"') as tc_id,(select sector_code from mst_sector  where sector_id ='"+sector+"') as sector_code , trade_code from mst_trade  where id='"+trade+"'";
 		
		
  		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			batch = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
 		return batch;
	
}
	
	
	public List getfinalId(String myid) throws HibernateException, Exception {
		String SQL = null;
		List batch = new ArrayList();
		
		SQL="select count(batch_id) from batch_creation  where batch_id like '"+myid+"%'";
 		//SQL = "  select batch_id from batch_creation where batch_id like '"+myid+"%'  order by batch_id  desc limit 1"; 
 				  	
		if(SQL.equalsIgnoreCase("0"))
		{
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Query query = session.createSQLQuery(SQL);
				batch = query.list();
 				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
					persister.closeSession();
			} 		 	
		}
		else 
		{
			SQL = "  select batch_id from batch_creation where batch_id like '"+myid+"%'  order by batch_id  desc limit 1"; 
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Query query = session.createSQLQuery(SQL);
				batch = query.list();
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
					persister.closeSession();
			} 
			 
		}
  		 
		return batch;
}
	
	
	
 	
	public BatchCreationVO getBatchDetails(String id){	
 		 
		BatchCreationVO  batch=new BatchCreationVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(BatchCreationVO.class)
                    .add(Restrictions.eq("id", id));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.uniqueResult();
            if (result != null) {
            	batch= (BatchCreationVO) result;
             }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
 		return batch;
	}
	
public BatchCreationVO editPage(String id) throws HibernateException, Exception {	
		
	BatchCreationVO   dataVO = new BatchCreationVO();
		Connection con = null;
		
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		String SQL_GET = "";
		
		try{
			
			SQL_GET=" select id,batch_id,sector,trade,batch_start_date,batch_freeze_date,batch_size,batch_end_date"
					+ " from batch_creation  where id='"+id+"'";
				 
			con =PersisterImpl.getConnection();		
			con.setAutoCommit(false);													
			stm = con.prepareStatement(SQL_GET);			
			 rs=stm.executeQuery();		
			 if(rs.next()){
			        dataVO.setId(rs.getString("id"));
			        //dataVO.setProjectID(rs.getString("project_id"));
			       // dataVO.setTcID(rs.getString("tc_id"));
			        dataVO.setBatchID(rs.getString("batch_id"));
			       /* dataVO.setSector(rs.getsec("sector"));
			        dataVO.setTrade(rs.getString("trade"));
			        dataVO.setBatchStartDate(rs.getString("batch_start_date"));*/
			        dataVO.setBatchFreezeDate(rs.getString("batch_freeze_date"));
			        dataVO.setBatchSize(rs.getInt("batch_size"));
			        //dataVO.setBatchEndDate(rs.getString("batch_end_date"));
			        
  			        
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
	}
	

public boolean deleteRecordFromId(BatchCreationVO helperVO) {
	boolean status=false;
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tx = session.beginTransaction();
	try {
		session.delete(helperVO);
		tx.commit();
		status = true;
	} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
	} finally {
		session.close();
	}
	return status;
}




/*public boolean delete(BatchCreationVO batchCreationVo) throws Exception {

	boolean flag = false;
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tx = session.beginTransaction();
	try {
		session.delete(batchCreationVo);
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


public BatchCreationVO getRecordFromId(String id)throws Exception {
	//log.info("====checkRecord method Starts======>");		
		Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();
	try {
        Criteria criteria = session.createCriteria(BatchCreationVO.class)
                .add(Restrictions.eq("id", id));

        Object result = criteria.uniqueResult();
        if (result != null) {
        	return (BatchCreationVO)result;
        }
    }
	catch (Exception e) {
		e.printStackTrace();
		tr.rollback();
	}finally {
        session.close();
    }
	return null;
}


 

public ProjectDetailsVO getmyProjectId(String id)throws Exception {
	//log.info("====checkRecord method Starts======>");		
		Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();
	try {
        Criteria criteria = session.createCriteria(ProjectDetailsVO.class)
                .add(Restrictions.eq("id", id));

        Object result = criteria.uniqueResult();
        if (result != null) {
        	return (ProjectDetailsVO)result;
        }
    }
	catch (Exception e) {
		e.printStackTrace();
		tr.rollback();
	}finally {
        session.close();
    }
	return null;
}




public TcSetupTradeActionVO getSectorByTradeId(String id)throws Exception {
	//log.info("====checkRecord method Starts======>");		
		Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();
	try {
        Criteria criteria = session.createCriteria(TcSetupTradeActionVO.class)
                .add(Restrictions.eq("id", id));

        Object result = criteria.uniqueResult();
        if (result != null) {
        	return (TcSetupTradeActionVO)result;
        }
    }
	catch (Exception e) {
		e.printStackTrace();
		tr.rollback();
	}finally {
        session.close();
    }
	return null;
}





public boolean update(BatchCreationVO batchCreationVo) throws Exception {

	boolean flag = false;
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tx = session.beginTransaction();
	try {
		session.update(batchCreationVo);
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
	public boolean save(BatchCreationVO batchCreationVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(batchCreationVo);
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
	
	public List<TcSetupTradeActionVO> getSelected__sector__Of__tc(String tcid){
		List<TcSetupTradeActionVO> result=new ArrayList<TcSetupTradeActionVO>();
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		/*Criteria cr = session.createCriteria(TcSetupTradeActionVO.class,"trade")
				.createAlias("trade.sector", "sector")
				.add(Restrictions.eq("trainningCenter.id",tcid ))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property("sector.sectorId"),"sectorId")
			    .add(Projections.property("sector.sectorName"),"sectorName")
			    .add(Projections.groupProperty("sector.sectorId")))
			    .setResultTransformer(Transformers.aliasToBean(SectorMasterVO.class));*/
 		        Criteria cr = session.createCriteria(TcSetupTradeActionVO.class)
				.add(Restrictions.eq("id", tcid));
				/*.setProjection(Projections.projectionList()
					    .add(Projections.distinct(Projections.property("sector.sectorId"))));*/
		for(Object tc:cr.list()){
			TcSetupTradeActionVO sector=(TcSetupTradeActionVO)tc;
			if(!result.contains(sector)){
				result.add(sector);
			}
			
		}
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
	
	
	public List<TcSetupTradeActionVO> getSelected__trade__Of__tc(String tcid){
		List<TcSetupTradeActionVO> result=new ArrayList<TcSetupTradeActionVO>();
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		/*Criteria cr = session.createCriteria(TcSetupTradeActionVO.class,"trade")
				.createAlias("trade.sector", "sector")
				.add(Restrictions.eq("trainningCenter.id",tcid ))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property("sector.sectorId"),"sectorId")
			    .add(Projections.property("sector.sectorName"),"sectorName")
			    .add(Projections.groupProperty("sector.sectorId")))
			    .setResultTransformer(Transformers.aliasToBean(SectorMasterVO.class));*/
 		        Criteria cr = session.createCriteria(TcSetupTradeActionVO.class)
				.add(Restrictions.eq("id", tcid));
				/*.setProjection(Projections.projectionList()
					    .add(Projections.distinct(Projections.property("sector.sectorId"))));*/
		for(Object tc:cr.list()){
			TcSetupTradeActionVO trade=(TcSetupTradeActionVO)tc;
			if(!result.contains(trade)){
				result.add(trade);
			}
			
		}
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
	
	
	
	
	
	
	
	public   Object getSelected__Trade__Of__Project_By_sector(String sectorId){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(TcSetupTradeActionVO.class,"tradeTarget")
				.createAlias("tradeTarget.trade", "trade")
 				.add(Restrictions.eq("sector.sectorId",sectorId ))
			    .setProjection(Projections.projectionList()
			    .add(Projections.property("trade.tradeId"),"tradeId")
			    .add(Projections.property("trade.tradeName"),"tradeName")
			    .add(Projections.groupProperty("trade.tradeId")))
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
	
			public   List<TcSetupTradeActionVO> getTC(String projectId){
 			 List<TcSetupTradeActionVO>  tradeTarget=new ArrayList<TcSetupTradeActionVO>();
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tr = session.getTransaction();
				try {
		            Criteria criteria = session.createCriteria(TcSetupVO.class)
		                    .add(Restrictions.eq("project.id", projectId));
		            List<TcSetupVO> list=(List<TcSetupVO>)criteria.list();
		            if(list!=null && list.size()>=1){
			            List<String> tcidList=new ArrayList<String>();
			            for(TcSetupVO ob:list){
			            	System.out.println("==>"+ ob);
			            	tcidList.add(ob.getId());
			            }
			            Criteria criteria1 = session.createCriteria(TcSetupTradeActionVO.class);
			            if(tcidList!=null && tcidList.size()>=1){		
			                    criteria1.add(Restrictions.in("trainningCenter.id", tcidList));
			            }
			            // Convenience method to return a single instance that matches the
			            // query, or null if the query returns no results.
			            Object result = criteria1.list();
		            	tradeTarget= (List<TcSetupTradeActionVO>) result;
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





			public TcSetupTradeActionVO getTcSetupTradeActionVO(TcSetupVO tcSetupVO, SectorMasterVO sectorMasterVO,	TradeMasterVO tradeMasterVO) {
				TcSetupTradeActionVO tradeTarget=new TcSetupTradeActionVO();
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tr = session.getTransaction();
				try {
		            Criteria criteria = session.createCriteria(TcSetupTradeActionVO.class)
		            		.add(Restrictions.eq("trainningCenter.id",tcSetupVO.getId()))
		            		.add(Restrictions.eq("sector.sectorId",sectorMasterVO.getSectorId()))
		            		.add(Restrictions.eq("trade.tradeId",tradeMasterVO.getTradeId()));

		            
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
	
	
}
