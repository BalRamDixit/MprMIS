package com.infotech.sgsy.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.actions.DispatchAction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class ReportMappingDAO extends DispatchAction{

	public List<ReportMappingBean> getSocialGenderTrainingCompletion() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	
	public List<ReportMappingBean> getGenderTrainingCompletion() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getSocialGenderTrainingCompletionMinorityTrainingCompletion() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getPlaced() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getCommencement() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getCompletion() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getGeneral() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getKeyDates() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getTradeSanctioned() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getAlertsAdvisories() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getAchievement() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getFinancials() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getResidential() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
	
	public List<ReportMappingBean> getDistrictsCovered() {

		List<ReportMappingBean> result = new ArrayList<ReportMappingBean>();
		String query_hql = "select * from ";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			Query query = session.createSQLQuery(query_hql);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String[] listToSet = new String[arr.length];
				ReportMappingBean ReportMappingBean = new ReportMappingBean();
				for (int j = 0; j < arr.length; j++) {
					Object ob = arr[j];
					if (ob == null) {
						listToSet[j] = "0";
					} else {
						listToSet[j] = ob + "";
					}
				}
				ReportMappingBean.setSc(listToSet[0] + "");
				ReportMappingBean.setSc(listToSet[1] + "");
				ReportMappingBean.setSc(listToSet[2] + "");
				result.add(ReportMappingBean);

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
}
