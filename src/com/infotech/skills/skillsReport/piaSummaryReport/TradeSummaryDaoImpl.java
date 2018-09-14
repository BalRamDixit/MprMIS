package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class TradeSummaryDaoImpl {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infotech.skills.skillsReport.piaSummaryReport.TrainingSummaryDao#
	 * getTrainingSummaryDetailComplete()
	 */
	public List getTradeSummary(TradeSummaryForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "SELECT * from rpt_tradesummary ";
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
				if (helperForm.getTrainingCenterName() != null
						&& !helperForm.getTrainingCenterName().equals("")) {
					QueryFilterLst
							.add(new String(
									" initcap(training_center_name) = initcap('"
											+ helperForm
													.getTrainingCenterName()
											+ "')"));
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
			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st
					+ " ORDER BY pia_name, project_name, training_center_name, sector_name, trade_name";
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
	public List getPiaNameLst(TradeSummaryForm helperForm) 
	{

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try 
		{
			tr.begin();
			// String sql_st =
			// "select initcap(pia_name) from rpt_tradesummary AS pname group by pia_name order by pia_name";
			String sql_st = "select initcap(pia_name) from rpt_tradesummary ";
			if (helperForm != null)
			{
				if (helperForm.getProjectName() != null
						&& !helperForm.getProjectName().equals("")) 
				{
					QueryFilterLst.add(new String(
							" initcap(project_name) = initcap('"
									+ helperForm.getProjectName() + "')"));
				}
				

			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) 
			{
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) 
				{
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st + " group by pia_name order by pia_name";

			Query query = session.createSQLQuery(sql_st);
			pia_NAME = query.list();
			tr.commit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
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
	public List getProjectNameLst(TradeSummaryForm helperForm)
	{
		List project_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try
		{
			tr.begin();
			String sql_st = "select initcap(project_name) from rpt_tradesummary ";
			if (helperForm != null)
			{
				if (helperForm.getPiaName() != null
						&& !helperForm.getPiaName().equals("")) 
				{
					QueryFilterLst.add(new String(
							" initcap(pia_name) = initcap('"
									+ helperForm.getPiaName() + "')"));
				}
				

			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1)
			{
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
	public List getTrainingCenterName(TradeSummaryForm helperForm) {
		List trainingCenterNameLst = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "select initcap(training_center_name) from rpt_tradesummary ";
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

	public List getSectorNameLst(TradeSummaryForm helperForm) {

		List pia_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			// String sql_st =
			// "select initcap(pia_name) from port_expendituresummary AS pname group by pia_name order by pia_name";
			String sql_st = "select initcap(sector_name) from rpt_tradesummary ";
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

	public List getTradeNameLst(TradeSummaryForm helperForm) {
		List project_NAME = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "select initcap(trade_name) from rpt_tradesummary ";
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
}
