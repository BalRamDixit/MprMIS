package com.infotech.skills.candidateStatistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetVO;
import com.infotech.sgsy.state.monitor.MonitorPpwsTrainVO;

public class CandidateDAO {

	public List getSanctionProjectByEntitycode(String entity_code,String status) throws Exception {
		String sQL = null;
		List projectlist = new ArrayList();
		
		//sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		if("pia".equals(status)){
			sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"'";
		}else
			sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		
		
		
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
	public boolean SaveOrUpdate(CandidateForm projectform,String month,int year,String userId) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<projectform.getProjectId().length;i++){
				CandidateVO	 helpervo=(CandidateVO) session.get(CandidateVO.class, projectform.getId()[i]);
				
				System.out.println("project id===>  "+projectform.getProjectId()[i]);
				if(helpervo==null){
					helpervo=new CandidateVO();
					ProjectDetailsVO project=new ProjectDetailsVO();
					project.setId(projectform.getProjectId()[i]);
					helpervo.setProject(project);
					helpervo.setMonth(month);
					helpervo.setYear(year);
					helpervo.setCreatedBy(userId);
					helpervo.setCreatedOn(new Date());
				}
				else{
					helpervo.setUpdatedBy(userId);	
					helpervo.setUpdatedOn(new Date());
				}
				
				helpervo.setTotal_no_of_can(projectform.getTotal_no_of_can()[i]);
				helpervo.setTotal_no_of_can_with_Adhar_no(projectform.getTotal_no_of_can_with_Adhar_no()[i]);
				helpervo.setTotal_no_of_can_Bank_acc(projectform.getTotal_no_of_can_Bank_acc()[i]);
				helpervo.setTotal_no_of_can_Mobile_no(projectform.getTotal_no_of_can_Mobile_no()[i]);
				helpervo.setTotal_no_can_aadhar_lin_bank_acc(projectform.getTotal_no_can_aadhar_lin_bank_acc()[i]);
				helpervo.setNo_of_can_insurance(projectform.getNo_of_can_insurance()[i]);
				helpervo.setPro_life_trained_can(projectform.getPro_life_trained_can()[i]);
				helpervo.setForeign_placed(projectform.getForeign_placed()[i]);
				helpervo.setCan_place_earn_more(projectform.getCan_place_earn_more()[i]);
				helpervo.setCan_retained(projectform.getCan_retained()[i]);
				helpervo.setPlaced_documented_sub_upload(projectform.getPlaced_documented_sub_upload()[i]);
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
	
	/*public Integer getTotal_Can_Under_training_trained(String prjectId) throws Exception {
		String sQL = null;
		Integer tatalCandidate=null;
		
		sQL = "select a.column_name  from table_name as a WHERE a.projectId='"+prjectId+"'";
	
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			//Query query = session.createSQLQuery(sQL);
			
		//	tatalCandidate = (Integer) query.uniqueResult();
			tatalCandidate=500;
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return tatalCandidate;
	}*/
	public CandidateVO getCandidate_Stat_by_ProjectId(String projectId,String month,int year){
		CandidateVO  candidateStatlist=new CandidateVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(CandidateVO.class)
                    .add(Restrictions.eq("project.id", projectId))
                    .add(Restrictions.eq("month", month))
                    .add(Restrictions.eq("year", year));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.uniqueResult();
            
            candidateStatlist= (CandidateVO) result;
           
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return candidateStatlist;
	}//.setProjection(Projections.projectionList().add(Projection‌​s.sum("a3"));
	public static Object get(ArrayList<String> tcids,String year,String month){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(MonitorPpwsTrainVO.class)
				.add(Restrictions.in("tcId", tcids))
				.add(Restrictions.eq("year",year ))
				.add(Restrictions.eq("month",month ))
			    .setProjection(Projections.projectionList()
			    .add(Projections.sum("commenced_Total"))
			    .add(Projections.sum("completed_Total")))
			    .setResultTransformer(Transformers.aliasToBean(MonitorPpwsTrainVO.class));

		result=cr.uniqueResult();
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
