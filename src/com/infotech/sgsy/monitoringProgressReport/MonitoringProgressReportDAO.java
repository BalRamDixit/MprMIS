package com.infotech.sgsy.monitoringProgressReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class MonitoringProgressReportDAO {

	public List<MonitoringProgressReportForm> getFieldData() {

		List<MonitoringProgressReportForm> result = new ArrayList<MonitoringProgressReportForm>();
		String query_hql = "select a.StateName as StateName,sum(a.total_projects) as total_projects,"
				+ "sum(a.total_projectSanctioned) as total_projectSanctioned,sum(a.editedProjectSanctioned) as "
				+ "editedProjectSanctioned,sum(a.totalTrade) as totalTrade,sum(a.total_district_target)as "
				+ "total_district_target,sum(a.totalPPWS) as totalPPWS,sum(a.totalTC) as totalTC,sum(a.FirstInstallment) "
				+ "as FirstInstallment,sum(a.secondInstallment) as secondInstallment,sum(a.thirdInstallment) as "
				+ "thirdInstallment,sum(a.fourthInstallment) as fourthInstallment,sum(a.due_deligence) as "
				+ "due_deligence,sum(a.training_center_trade) as training_center_trade,sum(a.batch_completion_before) "
				+ "as batch_completion_before,sum(a.batch_creation) as batch_creation,sum(a.batch_completion) as "
				+ "batch_completion,sum(a.monitoring_inspection) as monitoring_inspection,sum(a.PlacementMarked) as "
				+ "PlacementMarked,sum(a.totalCommenced) as totalCommenced,sum(a.totalComplete) as totalComplete,a.piaprn as "
				+ "piaprn,a.piaName as piaName,row_number() over() as rownumber from (select table1.stateId,table1.StateName,table1.id as projectID,"
				+ "table1.total_projects,table2.total_projectSanctioned,table3.editedProjectSanctioned,table4.totalTrade,"
				+ "table5.total_district_target,table6.totalPPWS,table7.totalTC,table8.FirstInstallment,table9.secondInstallment,"
				+ "table10.thirdInstallment,table11.fourthInstallment,table12.due_deligence,table13.training_center_trade,"
				+ "table14.batch_completion_before,table15.batch_creation,table16.batch_completion,table17.monitoring_inspection,"
				+ "table18.PlacementMarked,table19.totalCommenced,table20.totalComplete,table1.piaprn,table1.piaName "
				+ "from (select state.state_id as stateId,state.state_name as StateName,user_master.login_id as piaprn,"
				+ "user_master.user_name as piaName, project_details.id,count(project_details.project_id)as total_projects "
				+ "from project_details as project_details left join User_master as user_master on "
				+ "user_master.id=project_details.pia_prn left join mst_state as state on state.state_id=project_details.state_id "
				+ "group by user_master.login_id,user_master.user_name,project_details.pia_prn,state.state_id,state.state_name,"
				+ "project_details.id ) as table1 left join (select count(project_sanction_details.id) as total_projectSanctioned,"
				+ "project_sanction_details.project_id from project_sanction_details as project_sanction_details group by "
				+ "project_sanction_details.project_id) as table2 on table1.id=table2.project_id left join "
				+ "(select count(project_sanction_details.id) as editedProjectSanctioned,project_sanction_details.project_id "
				+ "from project_sanction_details as project_sanction_details where project_sanction_details.updated_on  is not "
				+ "null group by project_sanction_details.Project_id) as table3 on table1.ID=table3.project_id left join (select "
				+ "count(distinct(project_trade_target.project_id)) as totalTrade,project_trade_target.project_id from "
				+ "project_trade_target group by project_trade_target.project_id) as table4 on table1.Id=table4.project_id "
				+ "left join (select count(distinct(project_district_target.project_id)) as total_district_target, "
				+ "project_district_target.project_id from project_district_target group by project_district_target.project_id) "
				+ "as table5 on table1.Id= table5.project_id left join (select count(distinct(ppws_setup.project_id)) as totalPPWS,"
				+ "ppws_setup.project_id from ppws_setup group by project_id) as table6 on table1.Id=table6.project_id left join "
				+ "(select count(training_center_detail.id) as totalTC,training_center_detail.project_id from training_center_detail "
				+ "group by training_center_detail.project_id) as table7 on table1.Id=table7.project_id left join "
				+ "(select count(installment1.id) as FirstInstallment,installment1.project_id from monitoring_installment as "
				+ "installment1 where installment1.installment_no='1' and installment1.status_of_claim = 'Fund Released' group by installment1.project_id) as table8 on "
				+ "table1.Id=table8.project_id left join (select count(installment1.id) as secondInstallment,installment1.project_id "
				+ "from monitoring_installment as installment1 where installment1.installment_no='2' and installment1.status_of_claim = 'Fund Released' group by installment1.project_id) "
				+ "as table9 on table1.Id=table9.project_id left join (select count(installment1.id) as thirdInstallment,"
				+ "installment1.project_id from monitoring_installment as installment1 where installment1.installment_no='3' and installment1.status_of_claim = 'Fund Released' "
				+ "group by installment1.project_id) as table10 on table1.Id=table10.project_id left join (select count(installment1.id) "
				+ "as fourthInstallment,installment1.project_id from monitoring_installment as installment1 where "
				+ "installment1.installment_no='4' and installment1.status_of_claim = 'Fund Released' group by installment1.project_id) as table11 on table1.Id=table11.project_id "
				+ "left join (select count(distinct(due_deligence.tc_id)) as due_deligence,training_center_detail.Project_id from "
				+ "due_deligence left join training_center_detail as training_center_detail on training_center_detail.id=due_deligence.tc_id "
				+ "group by training_center_detail.project_id) as table12 on table1.Id=table12.project_id left join "
				+ "(select count(distinct(training_center_trade.tc_id)) as training_center_trade,training_center_detail.Project_id "
				+ "from training_center_trade left join training_center_detail as training_center_detail on training_center_detail.id=training_center_trade.tc_id "
				+ "group by training_center_detail.project_id) as table13 on table1.Id=table13.project_id left join (select count(batch_completion.id) as"
				+ " batch_completion_before,training_center.project_id from batch_completion left join batch_creation as batch_creation on "
				+ "batch_creation.id = batch_completion.batch_id left join training_center_trade as training_center_trade on training_center_trade.id=batch_creation.tc_trade_id "
				+ "left join training_center_detail as training_center on training_center.id=training_center_trade.id where batch_completion.batch_end_date <='31-MAR-2017' group by training_center.project_id) as table14 on "
				+ "table1.Id=table14.project_id left join (select count(batch_creation.id) as batch_creation,training_center_detail.project_id from batch_creation left join training_center_trade as training_center_trade on "
				+ "training_center_trade.id=batch_creation.tc_trade_id left join training_center_detail as training_center_detail on training_center_detail.id=training_center_trade.tc_id where batch_creation.created_on >'31-MAR-2017' "
				+ "group by training_center_detail.project_id) as table15 on table1.Id=table15.project_id left join (select count(batch_completion.id) as batch_completion,training_center.project_id from batch_completion "
				+ "left join batch_creation as batch_creation on batch_creation.id = batch_completion.batch_id left join training_center_trade as training_center_trade on training_center_trade.id=batch_creation.tc_trade_id left "
				+ "join training_center_detail as training_center on training_center.id=training_center_trade.id where batch_completion.batch_end_date >'31-MAR-2017' group by training_center.project_id) as table16 on table1.Id=table16.project_id "
				+ "left join (select count(distinct(training_center_detail.id,conductedby_QA_date,conductedby_SRLM_date,conductedby_CTSA_date)) as monitoring_inspection,training_center_detail.project_id from monitoring_inspection left join batch_creation "
				+ "on monitoring_inspection.batchid=batch_creation.id left join training_center_trade on batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id group by "
				+ "training_center_detail.project_id) as table17 on table1.Id=table17.project_id left join (select count(monitoring_achievement_placed.id) as PlacementMarked ,training_center_detail.project_id from monitoring_achievement_placed left join  "
				+ "batch_creation on batch_creation.id=monitoring_achievement_placed.batch_id left join training_center_trade on batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id "
				+ "group by training_center_detail.project_id) as table18 on table1.Id=table18.project_id left join (select sum(batch_creation.commenced_total) as totalCommenced,training_center_detail.project_id from  batch_creation left join training_center_trade on"
				+ " batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id group by training_center_detail.project_id) as table19 on table1.Id=table19.project_id left join "
				+ "(select sum(batch_completion.complet_total) as totalComplete ,training_center_detail.project_id from  batch_completion left outer join batch_creation on batch_creation.id = batch_completion.batch_id  left join training_center_trade on "
				+ "batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id group by training_center_detail.project_id) as table20 on table1.Id=table20.project_id  ) as a "
				+ "group by a.stateId,a.StateName,a.piaprn,a.piaName order by a.StateName, a.piaprn";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			MonitoringProgressReportForm monitoringProgressReportFormTotal = new MonitoringProgressReportForm();
			monitoringProgressReportFormTotal.setField("Total");
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				MonitoringProgressReportForm monitoringProgressReportForm = new MonitoringProgressReportForm();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				monitoringProgressReportForm.setField(listToSet[0] + "");
				monitoringProgressReportForm.setField2(listToSet[1] + "");
				monitoringProgressReportForm.setField3(listToSet[2] + "");
				monitoringProgressReportForm.setField4(listToSet[3] + "");
				monitoringProgressReportForm.setField5(listToSet[4] + "");
				monitoringProgressReportForm.setField6(listToSet[5] + "");
				monitoringProgressReportForm.setField7(listToSet[6] + "");
				monitoringProgressReportForm.setField8(listToSet[7] + "");
				monitoringProgressReportForm.setField9(listToSet[8] + "");
				monitoringProgressReportForm.setField10(listToSet[9] + "");
				monitoringProgressReportForm.setField11(listToSet[10] + "");
				monitoringProgressReportForm.setField12(listToSet[11] + "");
				monitoringProgressReportForm.setField13(listToSet[12] + "");
				monitoringProgressReportForm.setField14(listToSet[13] + "");
				monitoringProgressReportForm.setField15(listToSet[14] + "");
				monitoringProgressReportForm.setField16(listToSet[15] + "");
				monitoringProgressReportForm.setField17(listToSet[16] + "");
				monitoringProgressReportForm.setField18(listToSet[17] + "");
				monitoringProgressReportForm.setField19(listToSet[18] + "");
				monitoringProgressReportForm.setField20(listToSet[19] + "");
				monitoringProgressReportForm.setField21(listToSet[20] + "");
				monitoringProgressReportForm.setField22(listToSet[21] + "");
				monitoringProgressReportForm.setField23(listToSet[22] + "");
				result.add(monitoringProgressReportForm);

			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}

		return result;
	}
	public Map<String, Map<String,MonitoringProgressReportForm>> getFieldDataWithHshMap() {
		String query_hql = "select a.stateId as stateId,a.StateName as StateName,sum(a.total_projects) as total_projects,"
				+ "sum(a.total_projectSanctioned) as total_projectSanctioned,sum(a.editedProjectSanctioned) as "
				+ "editedProjectSanctioned,sum(a.totalTrade) as totalTrade,sum(a.total_district_target)as "
				+ "total_district_target,sum(a.totalPPWS) as totalPPWS,sum(a.totalTC) as totalTC,sum(a.FirstInstallment) "
				+ "as FirstInstallment,sum(a.secondInstallment) as secondInstallment,sum(a.thirdInstallment) as "
				+ "thirdInstallment,sum(a.fourthInstallment) as fourthInstallment,sum(a.due_deligence) as "
				+ "due_deligence,sum(a.training_center_trade) as training_center_trade,sum(a.batch_completion_before) "
				+ "as batch_completion_before,sum(a.batch_creation) as batch_creation,sum(a.batch_completion) as "
				+ "batch_completion,sum(a.monitoring_inspection) as monitoring_inspection,sum(a.PlacementMarked) as "
				+ "PlacementMarked,sum(a.totalCommenced) as totalCommenced,sum(a.totalComplete) as totalComplete,a.piaprn as "
				+ "piaprn,a.piaName as piaName,row_number() over() as rownumber from (select table1.stateId,table1.StateName,table1.id as projectID,"
				+ "table1.total_projects,table2.total_projectSanctioned,table3.editedProjectSanctioned,table4.totalTrade,"
				+ "table5.total_district_target,table6.totalPPWS,table7.totalTC,table8.FirstInstallment,table9.secondInstallment,"
				+ "table10.thirdInstallment,table11.fourthInstallment,table12.due_deligence,table13.training_center_trade,"
				+ "table14.batch_completion_before,table15.batch_creation,table16.batch_completion,table17.monitoring_inspection,"
				+ "table18.PlacementMarked,table19.totalCommenced,table20.totalComplete,table1.piaprn,table1.piaName "
				+ "from (select state.state_id as stateId,state.state_name as StateName,user_master.login_id as piaprn,"
				+ "user_master.user_name as piaName, project_details.id,count(project_details.project_id)as total_projects "
				+ "from project_details as project_details left join User_master as user_master on "
				+ "user_master.id=project_details.pia_prn left join mst_state as state on state.state_id=project_details.state_id "
				+ "group by user_master.login_id,user_master.user_name,project_details.pia_prn,state.state_id,state.state_name,"
				+ "project_details.id ) as table1 left join (select count(project_sanction_details.id) as total_projectSanctioned,"
				+ "project_sanction_details.project_id from project_sanction_details as project_sanction_details group by "
				+ "project_sanction_details.project_id) as table2 on table1.id=table2.project_id left join "
				+ "(select count(project_sanction_details.id) as editedProjectSanctioned,project_sanction_details.project_id "
				+ "from project_sanction_details as project_sanction_details where project_sanction_details.updated_on  is not "
				+ "null group by project_sanction_details.Project_id) as table3 on table1.ID=table3.project_id left join (select "
				+ "count(distinct(project_trade_target.project_id)) as totalTrade,project_trade_target.project_id from "
				+ "project_trade_target group by project_trade_target.project_id) as table4 on table1.Id=table4.project_id "
				+ "left join (select count(distinct(project_district_target.project_id)) as total_district_target, "
				+ "project_district_target.project_id from project_district_target group by project_district_target.project_id) "
				+ "as table5 on table1.Id= table5.project_id left join (select count(distinct(ppws_setup.project_id)) as totalPPWS,"
				+ "ppws_setup.project_id from ppws_setup group by project_id) as table6 on table1.Id=table6.project_id left join "
				+ "(select count(training_center_detail.id) as totalTC,training_center_detail.project_id from training_center_detail "
				+ "group by training_center_detail.project_id) as table7 on table1.Id=table7.project_id left join "
				+ "(select count(installment1.id) as FirstInstallment,installment1.project_id from monitoring_installment as "
				+ "installment1 where installment1.installment_no='1' and installment1.status_of_claim = 'Fund Released' group by installment1.project_id) as table8 on "
				+ "table1.Id=table8.project_id left join (select count(installment1.id) as secondInstallment,installment1.project_id "
				+ "from monitoring_installment as installment1 where installment1.installment_no='2' and installment1.status_of_claim = 'Fund Released' group by installment1.project_id) "
				+ "as table9 on table1.Id=table9.project_id left join (select count(installment1.id) as thirdInstallment,"
				+ "installment1.project_id from monitoring_installment as installment1 where installment1.installment_no='3' and installment1.status_of_claim = 'Fund Released' "
				+ "group by installment1.project_id) as table10 on table1.Id=table10.project_id left join (select count(installment1.id) "
				+ "as fourthInstallment,installment1.project_id from monitoring_installment as installment1 where "
				+ "installment1.installment_no='4' and installment1.status_of_claim = 'Fund Released' group by installment1.project_id) as table11 on table1.Id=table11.project_id "
				+ "left join (select count(distinct(due_deligence.tc_id)) as due_deligence,training_center_detail.Project_id from "
				+ "due_deligence left join training_center_detail as training_center_detail on training_center_detail.id=due_deligence.tc_id "
				+ "group by training_center_detail.project_id) as table12 on table1.Id=table12.project_id left join "
				+ "(select count(distinct(training_center_trade.tc_id)) as training_center_trade,training_center_detail.Project_id "
				+ "from training_center_trade left join training_center_detail as training_center_detail on training_center_detail.id=training_center_trade.tc_id "
				+ "group by training_center_detail.project_id) as table13 on table1.Id=table13.project_id left join (select count(batch_completion.id) as"
				+ " batch_completion_before,training_center.project_id from batch_completion left join batch_creation as batch_creation on "
				+ "batch_creation.id = batch_completion.batch_id left join training_center_trade as training_center_trade on training_center_trade.id=batch_creation.tc_trade_id "
				+ "left join training_center_detail as training_center on training_center.id=training_center_trade.id where batch_completion.batch_end_date <='31-MAR-2017' group by training_center.project_id) as table14 on "
				+ "table1.Id=table14.project_id left join (select count(batch_creation.id) as batch_creation,training_center_detail.project_id from batch_creation left join training_center_trade as training_center_trade on "
				+ "training_center_trade.id=batch_creation.tc_trade_id left join training_center_detail as training_center_detail on training_center_detail.id=training_center_trade.tc_id where batch_creation.created_on >'31-MAR-2017' "
				+ "group by training_center_detail.project_id) as table15 on table1.Id=table15.project_id left join (select count(batch_completion.id) as batch_completion,training_center.project_id from batch_completion "
				+ "left join batch_creation as batch_creation on batch_creation.id = batch_completion.batch_id left join training_center_trade as training_center_trade on training_center_trade.id=batch_creation.tc_trade_id left "
				+ "join training_center_detail as training_center on training_center.id=training_center_trade.id where batch_completion.batch_end_date >'31-MAR-2017' group by training_center.project_id) as table16 on table1.Id=table16.project_id "
				+ "left join (select count(distinct(training_center_detail.id,conductedby_QA_date,conductedby_SRLM_date,conductedby_CTSA_date)) as monitoring_inspection,training_center_detail.project_id from monitoring_inspection left join batch_creation "
				+ "on monitoring_inspection.batchid=batch_creation.id left join training_center_trade on batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id group by "
				+ "training_center_detail.project_id) as table17 on table1.Id=table17.project_id left join (select count(monitoring_achievement_placed.id) as PlacementMarked ,training_center_detail.project_id from monitoring_achievement_placed left join  "
				+ "batch_creation on batch_creation.id=monitoring_achievement_placed.batch_id left join training_center_trade on batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id "
				+ "group by training_center_detail.project_id) as table18 on table1.Id=table18.project_id left join (select sum(batch_creation.commenced_total) as totalCommenced,training_center_detail.project_id from  batch_creation left join training_center_trade on"
				+ " batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id group by training_center_detail.project_id) as table19 on table1.Id=table19.project_id left join "
				+ "(select sum(batch_completion.complet_total) as totalComplete ,training_center_detail.project_id from  batch_completion left outer join batch_creation on batch_creation.id = batch_completion.batch_id  left join training_center_trade on "
				+ "batch_creation.tc_trade_id=training_center_trade.id left join training_center_detail on training_center_detail.id=training_center_trade.tc_id group by training_center_detail.project_id) as table20 on table1.Id=table20.project_id  ) as a "
				+ "group by a.stateId,a.StateName,a.piaprn,a.piaName order by a.StateName,  a.piaName";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		Map<String, Map<String,MonitoringProgressReportForm>> result = new LinkedHashMap<String, Map<String,MonitoringProgressReportForm>>();
		try {
			tx.begin();
			
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				MonitoringProgressReportForm monitoringProgressReportForm = new MonitoringProgressReportForm();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				monitoringProgressReportForm.setField(listToSet[0] + "");//state Id  
				monitoringProgressReportForm.setField1(listToSet[1] + "");//State Name  
				monitoringProgressReportForm.setField2(listToSet[2] + "");//No of  Projects Detail
				monitoringProgressReportForm.setField3(listToSet[3] + "");//No of Project sanction Details
				monitoringProgressReportForm.setField4(listToSet[4] + "");//No of Project Sanction Modified
				monitoringProgressReportForm.setField5(listToSet[5] + "");//No of Project With Trade Target
				monitoringProgressReportForm.setField6(listToSet[6] + "");//No of Project with District Target
				monitoringProgressReportForm.setField7(listToSet[7] + "");//No of Project with PPWS
				monitoringProgressReportForm.setField8(listToSet[8] + "");//No of Training Center
				monitoringProgressReportForm.setField9(listToSet[9] + "");//No Of Project With 1st Installment
				monitoringProgressReportForm.setField10(listToSet[10] + "");//No Of Project With 2nd Installment
				monitoringProgressReportForm.setField11(listToSet[11] + "");//No Of Project With 3rd Installment
				monitoringProgressReportForm.setField12(listToSet[12] + "");//No Of Project With 4th Installment
				monitoringProgressReportForm.setField13(listToSet[13] + "");//
				monitoringProgressReportForm.setField14(listToSet[14] + "");//
				monitoringProgressReportForm.setField15(listToSet[15] + "");//
				monitoringProgressReportForm.setField16(listToSet[16] + "");//
				monitoringProgressReportForm.setField17(listToSet[17] + "");//
				monitoringProgressReportForm.setField18(listToSet[18] + "");//
				monitoringProgressReportForm.setField19(listToSet[19] + "");//
				monitoringProgressReportForm.setField20(listToSet[20] + "");//
				monitoringProgressReportForm.setField21(listToSet[21] + "");//
				monitoringProgressReportForm.setField22(listToSet[22] + "");//PIA PRN
				monitoringProgressReportForm.setField23(listToSet[23] + "");////PIA Name
				monitoringProgressReportForm.setField24(listToSet[24] + "");//rownumber
				
				Map<String,MonitoringProgressReportForm> item=null;
				if(result.containsKey(listToSet[0]+"")){
					item=result.get(listToSet[0]+"");
				}
				else{
					item=new HashMap<String,MonitoringProgressReportForm>();
				}
				MonitoringProgressReportForm total=null;
				if(item.containsKey("Total")){
					total=item.get("Total");
				}
				else{
					total= new MonitoringProgressReportForm();
					total.setField1(listToSet[1] + "");
				}
				total=add(total,monitoringProgressReportForm);
				item.put("Total", total);
				item.put(listToSet[24] + "", monitoringProgressReportForm);
				result.put(listToSet[0]+"", item);
				
				
				
				

			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		System.out.println("Result is ==> "+result);
		return result;
	}
	private MonitoringProgressReportForm add(MonitoringProgressReportForm total,MonitoringProgressReportForm monitoringProgressReportForm) {
		MonitoringProgressReportForm newTotal=new MonitoringProgressReportForm();
		newTotal.setField2(Integer.parseInt(total.getField2())+Integer.parseInt(monitoringProgressReportForm.getField2())+"");//No of  Projects Detail
		newTotal.setField3(Integer.parseInt(total.getField3())+Integer.parseInt(monitoringProgressReportForm.getField3())+"");
		newTotal.setField4(Integer.parseInt(total.getField4())+Integer.parseInt(monitoringProgressReportForm.getField4())+"");
		newTotal.setField5(Integer.parseInt(total.getField5())+Integer.parseInt(monitoringProgressReportForm.getField5())+"");
		newTotal.setField6(Integer.parseInt(total.getField6())+Integer.parseInt(monitoringProgressReportForm.getField6())+"");
		newTotal.setField7(Integer.parseInt(total.getField7())+Integer.parseInt(monitoringProgressReportForm.getField7())+"");
		newTotal.setField8(Integer.parseInt(total.getField8())+Integer.parseInt(monitoringProgressReportForm.getField8())+"");
		newTotal.setField9(Integer.parseInt(total.getField9())+Integer.parseInt(monitoringProgressReportForm.getField9())+"");
		newTotal.setField10(Integer.parseInt(total.getField10())+Integer.parseInt(monitoringProgressReportForm.getField10())+"");
		newTotal.setField11(Integer.parseInt(total.getField11())+Integer.parseInt(monitoringProgressReportForm.getField11())+"");
		newTotal.setField12(Integer.parseInt(total.getField12())+Integer.parseInt(monitoringProgressReportForm.getField12())+"");
		newTotal.setField13(Integer.parseInt(total.getField13())+Integer.parseInt(monitoringProgressReportForm.getField13())+"");
		newTotal.setField14(Integer.parseInt(total.getField14())+Integer.parseInt(monitoringProgressReportForm.getField14())+"");
		newTotal.setField15(Integer.parseInt(total.getField15())+Integer.parseInt(monitoringProgressReportForm.getField15())+"");
		newTotal.setField16(Integer.parseInt(total.getField16())+Integer.parseInt(monitoringProgressReportForm.getField16())+"");
		newTotal.setField17(Integer.parseInt(total.getField17())+Integer.parseInt(monitoringProgressReportForm.getField17())+"");
		newTotal.setField18(Integer.parseInt(total.getField18())+Integer.parseInt(monitoringProgressReportForm.getField18())+"");
		newTotal.setField19(Integer.parseInt(total.getField19())+Integer.parseInt(monitoringProgressReportForm.getField19())+"");
		newTotal.setField20(Integer.parseInt(total.getField20())+Integer.parseInt(monitoringProgressReportForm.getField20())+"");
		newTotal.setField21(Integer.parseInt(total.getField21())+Integer.parseInt(monitoringProgressReportForm.getField21())+"");
		newTotal.setField1(total.getField1());
		return newTotal;
	}
}
