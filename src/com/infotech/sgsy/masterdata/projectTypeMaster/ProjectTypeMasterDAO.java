package com.infotech.sgsy.masterdata.projectTypeMaster;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
 
public class ProjectTypeMasterDAO {

	
	public boolean save(ProjectTypeMasterVO helperVo) throws Exception {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(helperVo);
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
	
	
/*	
	public List<ProjectTypeMasterBean> getAllRecords(){
		List<ProjectTypeMasterBean> list=new ArrayList<ProjectTypeMasterBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = " select project_type_id,project_type_code,project_type_name,created_by,last_modified_by from project_type_master";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			for(Object[] temp :(List<Object[]>) query.list()) {
				ProjectTypeMasterBean bean=new ProjectTypeMasterBean(temp[0]+"",temp[1]+"",temp[2]+"",temp[3]+"",temp[4]+"",temp[5]+"",temp[6]+"",temp[7]+"");
            	list.add(bean);
			}
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		System.out.println("List is --> "+list);
		return list;
	}
	*/
	
	
	public ProjectTypeMasterVO getRecordFromId(String id)throws Exception {
		//log.info("====checkRecord method Starts======>");		
 		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectTypeMasterVO.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return (ProjectTypeMasterVO)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return null;
	}
	
	
	public boolean deleteRecordFromId(ProjectTypeMasterVO helperVO) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(helperVO);
			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}
	
	
	public List<ProjectTypeMasterVO> getAllRecords(){
		List<ProjectTypeMasterVO> list=new ArrayList<ProjectTypeMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(ProjectTypeMasterVO.class);
			
			for(Object o : query.list()) {
			    list.add((ProjectTypeMasterVO)o);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				try {
					persister.closeSession();
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("List to Print is --> "+list);
		return list;
	}
	
	public boolean saveOrUpdate(ProjectTypeMasterVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVO);
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
