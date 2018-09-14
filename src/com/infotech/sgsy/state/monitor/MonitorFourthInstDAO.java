package com.infotech.sgsy.state.monitor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class MonitorFourthInstDAO {


	public List<String> getPiaListForProjectId() {

		List<String> projectIdList = new ArrayList<String>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
				String sql_STATEMENT = "select projectid , piaprn  from project_detail_new";
				Query query = session.createSQLQuery(sql_STATEMENT);
				projectIdList = query.list();
				System.out.println(".........projectzId..."+projectIdList);
				
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
			
	
	public List<String> getPiaForProjectIds() {

		List<String> projectIdList = new ArrayList<String>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
				String sql_STATEMENT = "select projectid  from project_detail_new";
				Query query = session.createSQLQuery(sql_STATEMENT);
			//	query.setParameter("entityCode", entityCode);
				projectIdList = query.list();
				System.out.println("...............aaaa..."+projectIdList);
				
				tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projectIdList;
	}

	public boolean saveFourthInstallment(MonitorFourthInstActionForm helperForm,String createdBy,String createdOn,String entityCode) throws ClassNotFoundException {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<MonitorFourthInstVO> monitorThirdList = new ArrayList();
			
			for (int i = 0; i < helperForm.getProjectId().length; i++) {
		
				MonitorFourthInstVO monitorDetails = MasterFourthInstallmentDetailsFactory.getInstance("com.infotech.sgsy.state.monitor.MonitorFourthInstVO");
				
				if (helperForm.getId()!= null ){
					monitorDetails.setId(helperForm.getId()[i]);
					}
			
				monitorDetails.setProjectId(helperForm.getProjectId()[i]);
			//	monitorDetails.setStateName(this.getState(entityCode));
				
				monitorDetails.setIssuanceDate(helperForm.getIssuanceDate()[i]);
				monitorDetails.setInstallmentRectoFd(helperForm.getInstallmentRectoFd()[i]);
				monitorDetails.setReceiptDatePiaClaim(helperForm.getReceiptDatePiaClaim()[i]);
				monitorDetails.setAmountClaimed(helperForm.getAmountClaimed()[i]);
				monitorDetails.setAmountReleased(helperForm.getAmountReleased()[i]);
				monitorDetails.setReleasedDate(helperForm.getReleasedDate()[i]);
				monitorDetails.setClaimStatus(helperForm.getClaimStatus()[i]);
				monitorDetails.setRemarks(helperForm.getRemarks()[i]);
				
			//	monitorDetails.setEntityCode(entityCode);
				monitorDetails.setCreatedBy(createdBy);
				monitorDetails.setCreatedOnDate(createdOn);
				
				monitorThirdList.add(monitorDetails);
		
			}
			for (int i = 0; i < monitorThirdList.size(); i++) {
				MonitorFourthInstVO monitorDetails = MasterFourthInstallmentDetailsFactory.getInstance("com.infotech.sgsy.state.monitor.MonitorFourthInstVO");
				monitorDetails = (MonitorFourthInstVO) monitorThirdList.get(i);
				session.save(monitorDetails);
			}
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.flush();
			session.close();
		}
		return flag;
	}
	
	


}
