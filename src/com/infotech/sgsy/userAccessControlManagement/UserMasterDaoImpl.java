package com.infotech.sgsy.userAccessControlManagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateDaoImp;
import com.infotech.sgsy.master.state.StateVO;

public class UserMasterDaoImpl {
	protected final Log log = LogFactory.getLog(getClass());
	public boolean checkRecord(String loginId)throws Exception {
		boolean status=false;
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(UserMaster.class)
                    .add(Restrictions.eq("loginId", loginId).ignoreCase());

            Object result = criteria.uniqueResult();
            if (result != null) {
            	status= true;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return status;
	}
	public List<RoleMaster> getAllRoleMsters(UserMaster loginVO){
		List<RoleMaster> list=new ArrayList<RoleMaster>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(RoleMaster.class);
			RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
			if(role.getId().equalsIgnoreCase("45")){
				query.add(Restrictions.eq("id", "49"));
			}
			if(role.getId().equalsIgnoreCase("5001")){
				query.add(Restrictions.eq("id", "10001"));
			}
			if(role.getId().equalsIgnoreCase("4")){
				query.add(Restrictions.eq("id", "10002"));
			}
			for(Object o : query.list()) {
			    list.add((RoleMaster)o);
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
		return list;
	}
	public boolean save(UserMaster userMaster) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userMaster);
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
	public boolean saveOrUpdate(UserMaster userMaster) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(userMaster);
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
	public List<UserMasterBean> getAllRecords(UserMaster loginVO){
		log.info("====checkRecord method Starts======>");
		List<UserMasterBean> list=new ArrayList<UserMasterBean>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(UserMaster.class);
            RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
			if(!role.getId().equalsIgnoreCase("5")){
				criteria.add(Restrictions.eq("createdBy", loginVO.getId()));
			}
            for(Object o : criteria.list()) {
            	UserMaster ob=(UserMaster)o;
            	UserMasterBean ob1=new UserMasterBean();
            	BeanUtils.copyProperties(ob1,ob);
			    list.add(ob1);
			}
            UserMasterBean ob1=new UserMasterBean();
        	BeanUtils.copyProperties(ob1,loginVO);
        	list.add(ob1);
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	public List<StateVO> getAllState(){
		List<StateVO> stateList=new ArrayList<StateVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(StateVO.class);
			 for(Object o : criteria.list()) {
				 stateList.add((StateVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return stateList;
		
	}
	public UserMaster getRecordFromId(String id) {
		log.info("====checkRecord method Starts======>");
		UserMaster userMaster=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(UserMaster.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	userMaster= (UserMaster)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return userMaster;
	}
	public boolean deleteRecordFromId(UserMaster userMaster) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(userMaster);
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
	public List<CtsaMasterVO> getAllCTSA() {
		List<CtsaMasterVO> ctsaMasterVO=new ArrayList<CtsaMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(CtsaMasterVO.class);
			 for(Object o : criteria.list()) {
				 ctsaMasterVO.add((CtsaMasterVO)o);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return ctsaMasterVO;
	}
	
}
