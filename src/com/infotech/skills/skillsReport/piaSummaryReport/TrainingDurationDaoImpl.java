package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class TrainingDurationDaoImpl {

	public List getExpenditureSummary(TrainingDurationForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "SELECT * from rpt_expendituresummary ";
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
				if (helperForm.getSectorName() != null
						&& !helperForm.getSectorName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(sector_name) = initcap('"
									+ helperForm.getSectorName() + "')"));
				}
				if (helperForm.getTradeName() != null
						&& !helperForm.getTradeName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(trade_name) = initcap('"
									+ helperForm.getTradeName() + "')"));
				}
				if (helperForm.getCertifyingAgencyName() != null
						&& !helperForm.getCertifyingAgencyName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(certifying_agency) = initcap('"
									+ helperForm.getCertifyingAgencyName()
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
					+ " ORDER BY pia_name, project_name, sector_name, trade_name, certifying_agency";
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
	public List getPiaNameLst(TrainingDurationForm helperForm) {

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			// String sql_st =
			// "select initcap(pia_name) from rpt_expendituresummary AS pname group by pia_name order by pia_name";
			String sql_st = "select initcap(pia_name) from rpt_expendituresummary ";
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
			pia_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return pia_NAME;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infotech.skills.skillsReport.piaSummaryReport.TrainingSummaryDao#
	 * getProjectName()
	 */
	public List getProjectNameLst(TrainingDurationForm helperForm) {
		List project_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "select initcap(project_name) from rpt_expendituresummary ";
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
			project_NAME = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return project_NAME;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infotech.skills.skillsReport.piaSummaryReport.TrainingSummaryDao#
	 * getTrainingCenterName()
	 */
	public List getSectorNameLst(TrainingDurationForm helperForm) {

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			// String sql_st =
			// "select initcap(pia_name) from rpt_expendituresummary AS pname group by pia_name order by pia_name";
			String sql_st = "select initcap(sector_name) from rpt_expendituresummary ";
			if (helperForm != null) {
				if (helperForm.getTradeName() != null
						&& !helperForm.getTradeName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(trade_name) = initcap('"
									+ helperForm.getTradeName() + "')"));
				}
			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " group by sector_name order by sector_name";

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infotech.skills.skillsReport.piaSummaryReport.TrainingSummaryDao#
	 * getTrainingCenterName()
	 */
	public List getTradeNameLst(TrainingDurationForm helperForm) {
		List project_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "select initcap(trade_name) from rpt_expendituresummary ";
			if (helperForm != null) {
				if (helperForm.getSectorName() != null
						&& !helperForm.getSectorName().equals("")) {
					QueryFilterLst.add(new String(
							" initcap(sector_name) = initcap('"
									+ helperForm.getSectorName() + "')"));
				}

			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " group by trade_name ORDER BY trade_name";
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

	public List getCertifyingAgencyLst() {
		List certifyingAgencyLst = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "select initcap(certifying_agency) from rpt_expendituresummary ";
			sql_st = sql_st
					+ " group by certifying_agency ORDER BY certifying_agency";
			Query query = session.createSQLQuery(sql_st);
			certifyingAgencyLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return certifyingAgencyLst;

	}
}
