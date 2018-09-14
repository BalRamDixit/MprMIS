package com.infotech.sgsy.state.monitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
 
public class MonitorFirstInstDAO {

	public List<MonitorFirstInstVO> getPiaListForProjectIdEcode(String ecode) {

		List<MonitorFirstInstVO> projectIdList = new ArrayList<MonitorFirstInstVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sql_STATEMENT;
		try {
			tr.begin();
			//	String sql_STATEMENT = "select projectid, totalprojectcost  from project_detail_new where entity_code='"+ecode+"'";
			if (ecode.length()>3){
		      
				sql_STATEMENT="select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b where a.projectid=b.projectid and a.piaprn='"+ecode+"'";	
			    }
			else{
			    sql_STATEMENT = "select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b where a.projectid=b.projectid and a.entity_code='"+ecode+"'";
			     }
			    Query query = session.createSQLQuery(sql_STATEMENT);
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				for(Object[] temp :(List<Object[]>) query.list()) {
				MonitorFirstInstVO bean=new MonitorFirstInstVO(temp[0]+"",temp[1]+"",sdf.format(temp[2]));
				projectIdList.add(bean);
				}
				//projectIdList = query.list();
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projectIdList;
	}	
	public List<MonitorFirstInstVO> getPiaListForProjectIdEcodeForSecond(String ecode) {

		List<MonitorFirstInstVO> projectIdList = new ArrayList<MonitorFirstInstVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sql_STATEMENT;
		try {
			tr.begin();
			//	String sql_STATEMENT = "select projectid, totalprojectcost  from project_detail_new where entity_code='"+ecode+"'";
			if (ecode.length()>3){
		      
				sql_STATEMENT="select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b,user_monitoring_installment as c where a.projectid=b.projectid and c.project_id=a.projectid  and c.installment_no='1' and a.piaprn='"+ecode+"'";	
			    }
			else{
			    sql_STATEMENT = "select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b,user_monitoring_installment as c where a.projectid=b.projectid and c.project_id=a.projectid and c.installment_no='1' and a.entity_code='"+ecode+"'";
			     }
			    Query query = session.createSQLQuery(sql_STATEMENT);
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				for(Object[] temp :(List<Object[]>) query.list()) {
				MonitorFirstInstVO bean=new MonitorFirstInstVO(temp[0]+"",temp[1]+"",sdf.format(temp[2]));
				projectIdList.add(bean);
				}
				//projectIdList = query.list();
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projectIdList;
	}	
	public List<MonitorFirstInstVO> getPiaListForProjectIdEcodeForThird(String ecode) {

		List<MonitorFirstInstVO> projectIdList = new ArrayList<MonitorFirstInstVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sql_STATEMENT;
		try {
			tr.begin();
			//	String sql_STATEMENT = "select projectid, totalprojectcost  from project_detail_new where entity_code='"+ecode+"'";
			if (ecode.length()>3){
			      
				sql_STATEMENT="select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b,user_monitoring_installment as c where a.projectid=b.projectid and c.project_id=a.projectid  and c.installment_no='2' and a.piaprn='"+ecode+"'";	
			    }
			else{
			    sql_STATEMENT = "select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b,user_monitoring_installment as c where a.projectid=b.projectid and c.project_id=a.projectid and c.installment_no='2' and a.entity_code='"+ecode+"'";
			     }
			    Query query = session.createSQLQuery(sql_STATEMENT);
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				for(Object[] temp :(List<Object[]>) query.list()) {
				MonitorFirstInstVO bean=new MonitorFirstInstVO(temp[0]+"",temp[1]+"",sdf.format(temp[2]));
				projectIdList.add(bean);
				}
				//projectIdList = query.list();
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projectIdList;
	}	
	public List<MonitorFirstInstVO> getPiaListForProjectIdEcodeForForth(String ecode) {

		List<MonitorFirstInstVO> projectIdList = new ArrayList<MonitorFirstInstVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sql_STATEMENT;
		try {
			tr.begin();
			//	String sql_STATEMENT = "select projectid, totalprojectcost  from project_detail_new where entity_code='"+ecode+"'";
			if (ecode.length()>3){
			      
				sql_STATEMENT="select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b,user_monitoring_installment as c where a.projectid=b.projectid and c.project_id=a.projectid  and c.installment_no='3' and a.piaprn='"+ecode+"'";	
			    }
			else{
			    sql_STATEMENT = "select a.projectid,a.totalprojectcost,b.sanctiondate from project_detail_new  as a ,user_sanction_details as b,user_monitoring_installment as c where a.projectid=b.projectid and c.project_id=a.projectid and c.installment_no='3' and a.entity_code='"+ecode+"'";
			     }
			    Query query = session.createSQLQuery(sql_STATEMENT);
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				for(Object[] temp :(List<Object[]>) query.list()) {
				MonitorFirstInstVO bean=new MonitorFirstInstVO(temp[0]+"",temp[1]+"",sdf.format(temp[2]));
				projectIdList.add(bean);
				}
				//projectIdList = query.list();
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projectIdList;
	}	
	//State Name
		@SuppressWarnings("unchecked")
		public String getState(String stateCode) {
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tr = session.getTransaction();
				String statename="";
				List<String> entryStatusDetailList = new ArrayList<String>();
				String SQL_Query = null;
				SQL_Query = "select upper(state_name) as state_name from mst_state where state_code=:stateCode";
				try {
					tr.begin();
					Query Query = session.createSQLQuery(SQL_Query);
					Query.setParameter("stateCode", stateCode);
					System.out.println("stateCode"+stateCode);
					entryStatusDetailList = Query.list();
					tr.commit();
					statename =  entryStatusDetailList.get(0).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return statename;
			}
		
		
	
	/*public Object getPiaForProjectIds() {

		List<String> projectIdList = new ArrayList<String>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
				String sql_STATEMENT = "select projectid ,totalprojectcost from   project_detail_new";
				Query query = session.createSQLQuery(sql_STATEMENT);
				//query.setParameter("entityCode", entityCode);
				projectIdList = query.list();
				System.out.println("...............aaaa..."+projectIdList);
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projectIdList;
	}*/
	
	
	
	
	public MonitorFirstInstVO getPiaProjectsDetails(String projectId,String installmentNo ){
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		MonitorFirstInstVO monitorFirstInstVO=new MonitorFirstInstVO();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(MonitorFirstInstVO.class)
            .add(Restrictions.eq("projectId.id", projectId)).add(Restrictions.eq("installmentNo", installmentNo));
			monitorFirstInstVO= (MonitorFirstInstVO)criteria.uniqueResult();
			
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return monitorFirstInstVO;
		
	}
	public List<MonitorFirstInstVO> getPiaProjectsDetailsList(ArrayList<String> projectId,String installmentNo ){
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<MonitorFirstInstVO> monitorFirstInstVO=new ArrayList<MonitorFirstInstVO>();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(MonitorFirstInstVO.class)
            .add(Restrictions.in("projectId.id", projectId))
            .add(Restrictions.eq("installmentNo", installmentNo));
            monitorFirstInstVO = (List<MonitorFirstInstVO>)criteria.list();
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return monitorFirstInstVO;
		
	}
	public List<MonitorFirstInstVO> getPiaProjectsDetailsListFiltered(ArrayList<String> installmentId,String installmentNo ){
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<MonitorFirstInstVO> monitorFirstInstVO=new ArrayList<MonitorFirstInstVO>();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(MonitorFirstInstVO.class)
            .add(Restrictions.in("id", installmentId))
            .add(Restrictions.eq("installmentNo", installmentNo));
            monitorFirstInstVO = (List<MonitorFirstInstVO>)criteria.list();
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return monitorFirstInstVO;
		
	}
	
	
	public boolean save(MonitorFirstInstForm projectform,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectform.getProjectId().length;i++){
				if(projectform.getRelAmount()[i]>0){
					System.out.println(projectform.getRelAmount()[i]+" condition is "+(projectform.getRelAmount()[i]>0));
					MonitorFirstInstVO projectVo=(MonitorFirstInstVO) session.get(MonitorFirstInstVO.class, projectform.getId()[i]);
					if(projectVo==null){
						projectVo=new MonitorFirstInstVO();
						projectVo.setCreatedBy(loginVO.getUserid());
						projectVo.setCreatedOnDate(new Date());
						//System.out.println("hello.."+projectVo.getCreatedOn());
					}
					else{
						projectVo.setUpdatedBy(loginVO.getUserid());
						projectVo.setUpdatedOnDate(new Date());
					 }
					ProjectDetailsVO project=new ProjectDetailsVO();
					project.setId(projectform.getProjectId()[i]);
					projectVo.setProjectId(project);
					projectVo.setRelAmount(projectform.getRelAmount()[i]);
					projectVo.setRelDate(projectform.getRelDate()[i]);
					projectVo.setRemarks(projectform.getRemarks()[i]);
					projectVo.setUtilizationPercent(projectform.getUtilizationPercent()[i]);
					projectVo.setInstallmentNo("1");
			        session.saveOrUpdate(projectVo);
				}
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
	
/* -----------------------------         For Second Installments      ---------------------------------    */
	public boolean save2(MonitorFirstInstForm projectform,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			for(int i=0;i<projectform.getProjectId().length;i++){
				if(projectform.getRelAmount()[i]>0){
				MonitorFirstInstVO projectVo=(MonitorFirstInstVO) session.get(MonitorFirstInstVO.class, projectform.getId()[i]);
				if(projectVo==null){
					projectVo=new MonitorFirstInstVO();
					projectVo.setCreatedBy(loginVO.getUserid());
					projectVo.setCreatedOnDate(new Date());
				 }
				else{
					projectVo.setUpdatedBy(loginVO.getUserid());
					projectVo.setUpdatedOnDate(new Date());
				 }
				System.out.println("projectform.getId()[i] ---> "+projectform.getId()[i]);
				/*MonitorFirstInstVO projectVo=new MonitorFirstInstVO();*/
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(projectform.getProjectId()[i]);
				projectVo.setProjectId(project);
				projectVo.setRelAmount(projectform.getRelAmount()[i]);
				projectVo.setRelDate(projectform.getRelDate()[i]);
				projectVo.setRemarks(projectform.getRemarks()[i]);
				projectVo.setUtilizationPercent(projectform.getUtilizationPercent()[i]);
				projectVo.setAmountClaimed(projectform.getAmountClaimed()[i]);
				projectVo.setStatusofClaim(projectform.getStatusofClaim()[i]);
				projectVo.setDateofIssuance(projectform.getDateofIssuance()[i]);
				projectVo.setDateofRecommend(projectform.getDateofRecommend()[i]);
				projectVo.setDateofReceipt(projectform.getDateofReceipt()[i]);
				projectVo.setInstallmentNo("2");
				
				session.saveOrUpdate(projectVo);
			  }
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
	/*---------For Third Installment---------------*/

	public boolean save3(MonitorFirstInstForm projectform,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectform.getProjectId().length;i++){
				if(projectform.getRelAmount()[i]>0){
				MonitorFirstInstVO projectVo=(MonitorFirstInstVO) session.get(MonitorFirstInstVO.class, projectform.getId()[i]);
				if(projectVo==null){
					projectVo=new MonitorFirstInstVO();
					projectVo.setCreatedBy(loginVO.getUserid());
					projectVo.setCreatedOnDate(new Date());
				 }
				else{
					projectVo.setUpdatedBy(loginVO.getUserid());
					projectVo.setUpdatedOnDate(new Date());
				 }
				System.out.println("projectform.getId()[i] ---> "+projectform.getId()[i]);
				/*MonitorFirstInstVO projectVo=new MonitorFirstInstVO();*/
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(projectform.getProjectId()[i]);
				projectVo.setProjectId(project);
				projectVo.setRelAmount(projectform.getRelAmount()[i]);
				projectVo.setRelDate(projectform.getRelDate()[i]);
				projectVo.setRemarks(projectform.getRemarks()[i]);
				projectVo.setUtilizationPercent(projectform.getUtilizationPercent()[i]);
				projectVo.setAmountClaimed(projectform.getAmountClaimed()[i]);
				projectVo.setStatusofClaim(projectform.getStatusofClaim()[i]);
				projectVo.setDateofIssuance(projectform.getDateofIssuance()[i]);
				projectVo.setDateofRecommend(projectform.getDateofRecommend()[i]);
				projectVo.setDateofReceipt(projectform.getDateofReceipt()[i]);
				projectVo.setInstallmentNo("3");
				session.saveOrUpdate(projectVo);
			 } 
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
	
/* -----------------------For Fourth Installment --------------------------------- */
	public boolean save4(MonitorFirstInstForm projectform,LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectform.getProjectId().length;i++){
				if(projectform.getRelAmount()[i]>0){
				MonitorFirstInstVO projectVo=(MonitorFirstInstVO) session.get(MonitorFirstInstVO.class, projectform.getId()[i]);
				if(projectVo==null){
					projectVo=new MonitorFirstInstVO();
					projectVo.setCreatedBy(loginVO.getUserid());
					projectVo.setCreatedOnDate(new Date());
				}
				else{
					projectVo.setUpdatedBy(loginVO.getUserid());
					projectVo.setUpdatedOnDate(new Date());
				 }
				System.out.println("projectform.getId()[i] ---> "+projectform.getId()[i]);
			
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(projectform.getProjectId()[i]);
				projectVo.setProjectId(project);
				projectVo.setRelAmount(projectform.getRelAmount()[i]);
				projectVo.setRelDate(projectform.getRelDate()[i]);
				projectVo.setRemarks(projectform.getRemarks()[i]);
				projectVo.setUtilizationPercent(projectform.getUtilizationPercent()[i]);
				projectVo.setAmountClaimed(projectform.getAmountClaimed()[i]);
				projectVo.setStatusofClaim(projectform.getStatusofClaim()[i]);
				projectVo.setDateofIssuance(projectform.getDateofIssuance()[i]);
				projectVo.setDateofRecommend(projectform.getDateofRecommend()[i]);
				projectVo.setDateofReceipt(projectform.getDateofReceipt()[i]);
				projectVo.setInstallmentNo("4");
				session.saveOrUpdate(projectVo);
			  }
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
	public  Object  getdetailByCondition(Class<?> abc,String [] parameter,ArrayList<String> ids,String property){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(abc)
					 .add(Restrictions.eq(parameter[0], parameter[1]))
					 .add(Restrictions.in(property, ids));
	        result=criteria.list(); 
	      //  tx.commit();
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
