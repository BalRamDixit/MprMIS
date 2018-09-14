package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class TargetSummaryDaoImpl {

	public List getTragetSummary(TargetSummaryForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "SELECT * from rpt_targetsummary ";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infotech.skills.skillsReport.piaSummaryReport.TrainingSummaryDao#
	 * getPiaNameLst()
	 */
	public List getPiaNameLst(TargetSummaryForm helperForm) {
		List piaNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = "SELECT initcap(p.pia_name) as piaName from rpt_targetsummary as p ";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infotech.skills.skillsReport.piaSummaryReport.TrainingSummaryDao#
	 * getProjectName()
	 */
	public List getProjectNameLst(TargetSummaryForm helperForm) {
		List projectNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(project_name) as projectName from rpt_targetsummary as p ";
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

}
