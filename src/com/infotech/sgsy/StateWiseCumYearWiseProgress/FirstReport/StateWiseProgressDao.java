package com.infotech.sgsy.StateWiseCumYearWiseProgress.FirstReport;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class StateWiseProgressDao {

	
	/*  public static Object getStateWiseCumYearWiseProgressData(String projectId,String sectorId){
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
		return result;
	}*/
	

	public List getStateWiseCumYearWiseProgressData() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		List result=null;	

		try{
			/*String query ="select s.state_name,s.state_id,p.project_id,t.tc_id,p.date_of_ec from mst_state as s join "
					+ " project_details as p on s.state_id = p.state_id "
					+ " join training_center_detail as t on t.project_id = p.id "
					+ " join training_center_trade as tc on tc.tc_id = t.id where s.state_id = '21'"*/
			
			/*String query="select state.state_id as stateId,state.state_name as StateName,count(project_details.id) "
			+ "as total_projects from mst_state as state left join project_details as project_details on "
			+ "state.state_id=project_details.state_id group by state.state_id,state.state_name order by "
			+ "state.state_name";*/
			
			String query=" select state.state_id as stateId,state.state_name as StateName,count(project_details.id) as total_projects,state.state_id as stateId,"
					+ "count(project_details.id) as total_projects,state.state_id as stateId,count(project_details.id) as total_projects,state.state_id as stateId,"
					+ "count(project_details.id) as total_projects,state.state_id as stateId,count(project_details.id) as total_projects from mst_state as state left "
					+ "join project_details as project_details on "
					+ "state.state_id=project_details.state_id group by state.state_id,state.state_name order by state.state_name";

			
			/*String query="select s.state_id,s.state_name,p.project_id,t.tc_id,bcc.tc_trade_id,"
					+ "bcc.batch_start_date,bcc.batch_end_date from mst_state as s "
					+ "join project_details as p on s.state_id=p.state_id "
					+ "join training_center_detail as t on t.project_id=p.id "
					+ "join training_center_trade as tc on tc.tc_id =t.id "
					+ "join batch_creation as bcc on bcc.tc_trade_id=tc.id"
					+ "where s.state_id='21'";*/
			 /*Criteria cr = session.createCriteria(.class,"")	
			.createAlias("project_details", "p");
			.createAlias("mst_state", "s");
			.createAlias("training_center_detail", "t");
			.createAlias("training_center_trade", "tc");
			
			.add(Restrictions.eq("project.id",projectId)*/
			
			
	
			
			Query q = session.createSQLQuery(query);
		    result = q.list();
			
			  tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		return result; 
		
	 }
	
	public List getStateWiseCumMonthWiseProgressData() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		List result=null;	

		try{
			String query ="select s.state_name,s.state_id,p.project_id,t.tc_id,p.date_of_ec from mst_state as s join "
					+ " project_details as p on s.state_id = p.state_id "
					+ " join training_center_detail as t on t.project_id = p.id "
					+ " join training_center_trade as tc on tc.tc_id = t.id where s.state_id = '21'";
			
			
			Query q = session.createSQLQuery(query);
		    result = q.list();
			
			  tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		return result; 
		
	 }
	
	public List getStateWiseCategoryWiseAchievementData() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		List result=null;	

		try{
			String query ="select s.state_name,s.state_id,p.project_id,t.tc_id,p.date_of_ec from mst_state as s join "
					+ " project_details as p on s.state_id = p.state_id "
					+ " join training_center_detail as t on t.project_id = p.id "
					+ " join training_center_trade as tc on tc.tc_id = t.id where s.state_id = '21'";
		
			Query q = session.createSQLQuery(query);
		    result = q.list();
			
			  tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		return result; 
		
	 }
	
	public List getProjectWiseCategoryWiseAchievementData() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		List result=null;	

		try{
			String query ="select s.state_name,s.state_id,p.project_id,t.tc_id,p.date_of_ec from mst_state as s join "
					+ " project_details as p on s.state_id = p.state_id "
					+ " join training_center_detail as t on t.project_id = p.id "
					+ " join training_center_trade as tc on tc.tc_id = t.id where s.state_id = '21'";
			
		
	
			
			Query q = session.createSQLQuery(query);
		    result = q.list();
			
			  tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		return result; 
		
	 }
	
	
	public List getDistrictWiseProgressEachStateData() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		List result=null;	

		try{
			/*String query ="select district_name,district_code,coalesce(census_2011_district_code,'0') from district_master  "
			     + "order by district_name ";*/
			
			String query="select district_name,district_id,district_code from mst_district "
					    + "order by district_name";
		
			Query q = session.createSQLQuery(query);
		    result = q.list();
			
			  tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		return result; 
		
	 }
	
   }
