package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class DistrictSummaryDaoImpl {
	/***************************************** POPULATE DATA IN TABLE ******************************************/
	public List getDistrictSummary(DistrictSummaryForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = " SELECT * from rpt_districtsummary ";
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
				if (helperForm.getStateName() != null
						&& !helperForm.getStateName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(state_name) = initcap('"
									+ helperForm.getStateName() + "')"));
				}

				if (helperForm.getDistrictName() != null
						&& !helperForm.getDistrictName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(district_name) = initcap('"
									+ helperForm.getDistrictName() + "')"));
				}
			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st
					+ " ORDER BY pia_name, project_name, state_name, district_name";
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

	// get pianame name list in dropdown
	public List getPiaNameLst(DistrictSummaryForm helperForm) {
		List piaNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(p.pia_name) as piaName from rpt_districtsummary as p ";
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

	public List getProjectNameLst(DistrictSummaryForm helperForm) {
		List projectNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(project_name) as projectName from rpt_districtsummary  ";
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

	/****************************** STATENAMELIST ******************************/
	public List getStateNameLst(DistrictSummaryForm helperForm) {
		List stateNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(state_name) as stateName from rpt_districtsummary as p ";
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
			sql_st = sql_st + " group by state_name ORDER BY state_name";
			Query query = session.createSQLQuery(sql_st);
			stateNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return stateNameLst;
	}

	/****************************** DISTRICTNAMELIST ******************************/
	public List getDistrictNameLst(DistrictSummaryForm helperForm) {
		List districtNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(district_name) as districtName from rpt_districtsummary  ";
			if (helperForm != null) {
				if (helperForm.getPiaName() != null
						&& !helperForm.getPiaName().equals("")) {
					QueryFilterLst.add(new String(
							"initcap(state_name) = initcap('"
									+ helperForm.getStateName() + "')"));
				}
			}
			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " group by district_name ORDER BY district_name";
			Query query = session.createSQLQuery(sql_st);
			districtNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return districtNameLst;
	}

	// get pianame list in pianame dropdown
	public List getPiaName() {

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = " select initcap(pia_name) from rpt_districtsummary group by pia_name order by pia_name";
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

	// get project name list in dropdown
	public List getProjectName() {

		List project_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(project_name) from rpt_districtsummary group by project_name order by project_name";
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

	public List getStateName() {

		List state_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(pname.state_name) from rpt_districtsummary pname group by state_name order by state_name";
			Query query = session.createSQLQuery(sql_st);
			state_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return state_NAME;

	}

	// get districtname list in dropdown
	public List getDistrictName(String statename) {

		List district_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = "select initcap(district_name) from rpt_districtsummary WHERE initcap(state_name) =initcap('"
					+ statename
					+ "')  group by district_name  order by district_name";
			Query query = session.createSQLQuery(sql_st);
			district_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return district_NAME;

	}

	// get statename list in dropdown

}