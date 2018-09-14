package com.infotech.sgsy.master.financialYear;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.PropertyModel;

public class FinancialYrDAO {

	protected final Log log = LogFactory.getLog(getClass());

	public List getFinancialYrs(LocationVO locationVO) throws Exception {
		List results = null;
		String query = "select fy from com.infotech.sgsy.master.financialYear.FinancialYearVo fy order by fy.finYearToDisplay";
		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();

			results = (List) persister.getObjectsByQuery(query);

			transaction.commit();
			return results;
		} catch (Exception e) {
			log.error("error in get Groups " + e);
			throw e;
		}

	}

	/**
	 * @author cvas2273
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public FinancialYearVo getFinancialYearDate(String year) throws Exception {

		String query = "select vo from com.infotech.sgsy.master.financialYear.FinancialYearVo vo "
				+ "where vo.finYr=:year1 ";
		FinancialYearVo financialYearVo = null;
		List results = null;
		try {
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction transaction = session.getTransaction();
			transaction.begin();

			// financialYearVo= (FinancialYearVo)
			// persister.getObjectByQuery(query);

			Query q = session.createQuery(query).setParameter("year1", year);

			results = q.list();

			// financialYearVo= (FinancialYearVo)q;
			// financialYearVo= (FinancialYearVo)results.get(0);

			for (int x = 0; x < results.size(); x++) {
				financialYearVo = (FinancialYearVo) results.get(x);

			}

			transaction.commit();
			session.flush();
			session.close();

			return financialYearVo;
		} catch (Exception e) {
			log.error("error in get Groups " + e);
			throw e;
		}

	}

	/**
	 * @author cvas2273
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public FinancialYearVo getFinancialYearDateForComponentAllocatedAmount(
			String year) throws Exception {

		String query = "select vo from com.infotech.sgsy.master.financialYear.FinancialYearVo vo "
				+ "where vo.finYearToDisplay=:year1 ";
		FinancialYearVo financialYearVo = null;
		List results = null;
		try {
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction transaction = session.getTransaction();
			transaction.begin();

			// financialYearVo= (FinancialYearVo)
			// persister.getObjectByQuery(query);

			Query q = session.createQuery(query).setParameter("year1", year);

			// financialYearVo= (FinancialYearVo)q;

			results = q.list();

			// financialYearVo= (FinancialYearVo)results.get(0);

			for (int x = 0; x < results.size(); x++) {
				financialYearVo = (FinancialYearVo) results.get(x);

			}
			transaction.commit();

			session.flush();

			session.close();

			return financialYearVo;
		} catch (Exception e) {
			log.error("error in get Groups " + e);
			throw e;
		}

	}

}
