package com.infotech.skills.dao.pia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.skills.hbm.ContactDetail;


public class MasterCommonDaoImpl implements MasterCommonDao {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public List<ContactDetail> getContactDetail() {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		List<ContactDetail> contactDetailsList = new ArrayList<ContactDetail>();

		try {

			tr.begin();
			Criteria criteria = session.createCriteria(ContactDetail.class);			
			contactDetailsList = criteria.addOrder(Order.asc("order")).list();

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
		return contactDetailsList;
	}
	
	
}
