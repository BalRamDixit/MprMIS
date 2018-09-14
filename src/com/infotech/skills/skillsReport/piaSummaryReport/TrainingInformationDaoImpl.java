package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class TrainingInformationDaoImpl {

	public List getTrainingSummary(TrainingInformationForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "SELECT * from rpt_trainingsummary ";
			if (helperForm != null) {
				if (helperForm.getPiaName() != null
						&& !helperForm.getPiaName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(pia_name) = initcap('"
									+ helperForm.getPiaName() + "')"));
				}
				if (helperForm.getProjectName() != null
						&& !helperForm.getProjectName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(project_name) = initcap('"
									+ helperForm.getProjectName() + "')"));
				}
				if (helperForm.getTrainingcentername() != null
						&& !helperForm.getTrainingcentername().equals("")) {
					QueryFilterLst
							.add(new String(
									" initcap(training_center_name) = initcap('"
											+ helperForm
													.getTrainingcentername()
											+ "')"));
				}
				if (helperForm.getTrainingcode() != null
						&& !helperForm.getTrainingcode().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(training_center_code) = initcap('"
									+ helperForm.getTrainingcode() + "')"));
				}
			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st
					+ " ORDER BY pia_name, project_name, training_center_name, training_center_code";
			Query query = session.createSQLQuery(sql_st);
			pia_list = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pia_list;

	}

	public List getPiaNameLst(TrainingInformationForm helperForm) {
		List piaNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(p.pia_name) as piaName from rpt_trainingsummary as p ";
			if (helperForm != null) {
				if (helperForm.getProjectName() != null
						&& !helperForm.getProjectName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(project_name) = initcap('"
									+ helperForm.getProjectName() + "')"));
				}
			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " group by pia_name order by pia_name";
			Query query = session.createSQLQuery(sql_st);
			piaNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return piaNameLst;
	}

	public List getProjectNameLst(TrainingInformationForm helperForm) {
		List projectNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(project_name) as projectName from rpt_trainingsummary  ";
			if (helperForm != null) {
				if (helperForm.getPiaName() != null
						&& !helperForm.getPiaName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(pia_name) = initcap('"
									+ helperForm.getPiaName() + "')"));
				}
			}
			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " group by project_name ORDER BY project_name";
			Query query = session.createSQLQuery(sql_st);
			projectNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return projectNameLst;
	}

	public List getTrainingCenterNameLst(TrainingInformationForm helperForm) {
		List trainingCenterNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(training_center_name) as training_center_name from rpt_trainingsummary  ";
			if (helperForm != null) {
				/*
				 * if (helperForm.getPiaName() != null &&
				 * !helperForm.getPiaName().equals("")) { QueryFilterLst.add(new
				 * String(" pia_name = '"+ helperForm.getPiaName() + "'")); }
				 */
			}
			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st
					+ " group by training_center_name ORDER BY training_center_name";
			Query query = session.createSQLQuery(sql_st);
			trainingCenterNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return trainingCenterNameLst;
	}

	public List getTrainingCenterCodeLst(TrainingInformationForm helperForm) {
		List trainingCenterCodeLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(training_center_code) as training_center_code from rpt_trainingsummary  ";
			if (helperForm != null) {
				if (helperForm.getPiaName() != null
						&& !helperForm.getPiaName().equals("")) {
					QueryFilterLst
							.add(new String(
									" initcap(training_center_name) = initcap('"
											+ helperForm
													.getTrainingcentername()
											+ "')"));
				}
			}
			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st
					+ " group by training_center_code ORDER BY training_center_code";
			Query query = session.createSQLQuery(sql_st);
			trainingCenterCodeLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return trainingCenterCodeLst;
	}

	public List getPiaName() {

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(pname.pia_name) from rpt_trainingsummary pname group by pname.pia_name order by pname.pia_name";
			Query query = session.createSQLQuery(sql_st);
			pia_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return pia_NAME;

	}

	public List getProjectName() {

		List project_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(pname.project_name) from rpt_trainingsummary AS pname group by pname.project_name order by pname.project_name";
			Query query = session.createSQLQuery(sql_st);
			project_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return project_NAME;

	}

	public List getTrainingCenterName() {

		List training_center_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(pname.training_center_name) from rpt_trainingsummary pname group by pname.training_center_name order by pname.training_center_name";
			Query query = session.createSQLQuery(sql_st);
			training_center_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return training_center_NAME;
	}

	public List getTrainingCenterCode(String TrainingCenterName) {
		List training_center_code = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(pname.training_center_code) from rpt_trainingsummary AS pname WHERE initcap(training_center_name)= initcap('"
					+ TrainingCenterName
					+ "')group by pname.training_center_code order by  pname.training_center_code";
			Query query = session.createSQLQuery(sql_st);
			training_center_code = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return training_center_code;

	}

	public List getTrainingCenterCode() {
		List training_center_code = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(pname.training_center_code) from rpt_trainingsummary AS pname group by pname.training_center_code order by  pname.training_center_code";
			Query query = session.createSQLQuery(sql_st);
			training_center_code = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return training_center_code;

	}

}
