package com.infotech.skills.skillsReport.piaSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class SectorSummaryDaoImpl {
	public List getSectorSummaryLst(SectorSummaryForm helperForm) {

		List pia_list = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			String sql_st = "SELECT * from rpt_sectorsummary ";
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
				if (helperForm.getFinancialYear() != null
						&& !helperForm.getFinancialYear().equals("")) {
					QueryFilterLst.add(new String(" financial_year = '"
							+ helperForm.getFinancialYear() + "'"));
				}

			}

			if (QueryFilterLst != null && QueryFilterLst.size() >= 1) {
				sql_st = sql_st + " WHERE " + QueryFilterLst.get(0);
				for (int i = 1; i < QueryFilterLst.size(); i++) {
					sql_st = sql_st + " AND " + QueryFilterLst.get(i);
				}
			}
			sql_st = sql_st
					+ " ORDER BY pia_name, project_name, sector_name, trade_name, financial_year";
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

	public List getPiaNameLst(SectorSummaryForm helperForm) {
		List piaNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = "SELECT initcap(p.pia_name) as piaName from rpt_sectorsummary as p ";
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

	public List getProjectNameLst(SectorSummaryForm helperForm) {
		List projectNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(project_name) as projectName from rpt_sectorsummary as p ";
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

	public List getSectorNameLst(SectorSummaryForm helperForm) {
		List sectorNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = "SELECT initcap(p.sector_name) as sectorName from rpt_sectorsummary as p ";
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
			sectorNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return sectorNameLst;
	}

	public List getTradeNameLst(SectorSummaryForm helperForm) {
		List tradeNameLst = null;
		String sql_st = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		List<String> QueryFilterLst = new ArrayList<String>();
		try {
			tr.begin();
			sql_st = " SELECT initcap(trade_name) as tradeName from rpt_sectorsummary as p ";
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
			tradeNameLst = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return tradeNameLst;
	}

	public List getFinancialYear() {
		List financialYear = null;
		String SQL_Query = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			SQL_Query = " SELECT financial_year from rpt_sectorsummary as p group by financial_year order by p.financial_year ";
			Query query = session.createSQLQuery(SQL_Query);
			financialYear = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return financialYear;
	}

}
