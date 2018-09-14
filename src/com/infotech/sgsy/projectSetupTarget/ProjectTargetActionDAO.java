package com.infotech.sgsy.projectSetupTarget;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
 
public class ProjectTargetActionDAO {

	
	public List getDetails(String entity_code,String login) throws HibernateException, Exception {
		String SQL = null;
		List DetailsList = new ArrayList();
		
		/*SQL = "select projectid, santarget, santarget *.5 as sc_st,"
				+ "santarget *.02 as general,santarget *.15 as minority,santarget *.33 as women from user_sanction_details ";*/
		
		//SQL="select projectid, santarget,sc_st,general,minority,women from user_sanction_details";
		
		/*SQL="select a.projectid,a.santarget,b.entity_code from user_sanction_details as a,project_detail_new as b WHERE a.projectid=b.projectid";*/
		if(login.equals("pia")){
		SQL="select a.projectid,a.santarget,a.sc_st,a.general,a.minority,a.women from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"'";
		}else
		SQL="select a.projectid,a.santarget,a.sc_st,a.general,a.minority,a.women from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			DetailsList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return DetailsList;
}
	
	
	
	/*public List getdetailFromState() throws HibernateException, Exception {
		String SQL = null;
		List DetailsList = new ArrayList();
		
		SQL = "select projectid, santarget, santarget *.5 as sc_st,"
				+ "santarget *.02 as general,santarget *.15 as minority,santarget *.33 as women from user_sanction_details ";
		
		SQL="select sc_st,minority,women from mst_sector";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			DetailsList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return DetailsList;
}*/
	public List getdetailofState(String entity_code) throws HibernateException, Exception {
		String SQL = null;
		List state = new ArrayList();
		
		/*SQL = "select projectid, santarget, santarget *.5 as sc_st,"
				+ "santarget *.02 as general,santarget *.15 as minority,santarget *.33 as women from user_sanction_details ";*/
		
		SQL="select sc_st,general,minority,women from mst_state where state_code='"+entity_code+"'";
		/*SQL="select a.projectid,a.santarget,b.entity_code from user_sanction_details as a,project_detail_new as b where a.projectid=b.projectid";*/
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			state = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return state;
}
	
	
	
	public boolean save(ProjectSetupTargetVO projectVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(projectVo);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	
	
}
