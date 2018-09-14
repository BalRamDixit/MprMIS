package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class ProjectRegistrationDaoImpl {

	public List getProjectSummary(ProjectRegistrationForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "SELECT * from rpt_projectregistration ";
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
			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " ORDER BY pia_name, project_name";
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

	public List getPiaNameLst(ProjectRegistrationForm helperForm) {
		List piaNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(p.pia_name) as piaName from rpt_projectregistration as p ";
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

	public List getProjectNameLst(ProjectRegistrationForm helperForm) {
		List projectNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(project_name) as projectName from rpt_projectregistration  ";
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

	public List getPiaName() {

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql_st = " select initcap(pia_name) from rpt_projectregistration group by pia_name order by pia_name";
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
			String sql_st = "select initcap(project_name) from rpt_projectregistration group by project_name order by project_name";
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
}
