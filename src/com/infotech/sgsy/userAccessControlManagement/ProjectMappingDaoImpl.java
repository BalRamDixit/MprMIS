package com.infotech.sgsy.userAccessControlManagement;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsForm;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class ProjectMappingDaoImpl {
	protected final Log log = LogFactory.getLog(getClass());
	public String checkRecord(String projectId)throws Exception {
		String status="";
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class)
                    .add(Restrictions.eq("id", projectId));

            ProjectDetailsVO result = (ProjectDetailsVO)criteria.uniqueResult();
            if(result!=null){
            	status=result.getPiaPrn().getUserName();
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
	
	public List<UserMasterBean> getAllUser(LoginVO loginVO){
		log.info("====checkRecord method Starts======>");
		List<UserMasterBean> list=new ArrayList<UserMasterBean>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(UserMaster.class);
            RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
            if(!role.getId().equalsIgnoreCase("5")){//For MORD
            	criteria.add(Restrictions.eq("createdBy", loginVO.getUserid()));
			}
            List<ProjectMappingBean> listForTest=getAllRecords(loginVO);
            String[] idList=new String[listForTest.size()];
            for(int i=0;i<listForTest.size();i++){
            	ProjectMappingBean pmb=listForTest.get(i);
            	idList[i]=pmb.getProjectId();//Used as userId
            }
            System.out.println("User List are ==> "+Arrays.toString(idList));
            if(idList.length>0){
            	criteria.add(Restrictions.not(Restrictions.in("id", idList)));
            }
            for(Object o : criteria.list()) {
            	UserMaster ob=(UserMaster)o;
            	UserMasterBean ob1=new UserMasterBean();
            	BeanUtils.copyProperties(ob1,ob);
			    list.add(ob1);
			}
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	public List<ProjectDetailsForm> getAllProjectDetails(LoginVO loginVO){
		log.info("====checkRecord method Starts======>");
		List<ProjectDetailsForm> list=new ArrayList<ProjectDetailsForm>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class);
            RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
            if(role.getId().equalsIgnoreCase("45")){ // SRLM ADMIN
            	criteria.add(Restrictions.eq("state.stateId", loginVO.getEntityCode()));
			}
            for(Object o : criteria.list()) {
            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
            	ProjectDetailsForm ob1=new ProjectDetailsForm();
            	BeanUtils.copyProperties(ob1,ob);
			    list.add(ob1);
			}
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	public List<ProjectDetailsForm> getAllProjectDetailsWithoutCondition(){
		log.info("====checkRecord method Starts======>");
		List<ProjectDetailsForm> list=new ArrayList<ProjectDetailsForm>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectDetailsVO.class);
            for(Object o : criteria.list()) {
            	ProjectDetailsVO ob=(ProjectDetailsVO)o;
            	ProjectDetailsForm ob1=new ProjectDetailsForm();
            	BeanUtils.copyProperties(ob1,ob);
			    list.add(ob1);
			}
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	public boolean save(List<ProjectMapping> projectMappingList) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(ProjectMapping pm:projectMappingList){
				session.save(pm);
			}
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
	public boolean saveOrUpdate(ProjectMapping projectMapping) throws Exception {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(projectMapping);
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
	public List<ProjectMappingBean> getAllRecords(LoginVO loginVO){
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		List<ProjectMappingBean> list=new ArrayList<ProjectMappingBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select distinct um.User_name,um.login_id,um.id as userIdId from project_mapping as pm,project_details as pdn,user_master as um,role_master as rm where pm.project_id=pdn.id and um.id=pm.user_id and rm.id=um.role_id ";
		if(role.getId().equalsIgnoreCase("45")){	//SRLM ADMIN
			query_hql=query_hql+" and rm.id='49' and pdn.created_By='"+loginVO.getUserid()+"'";
		}
		else if(role.getId().equalsIgnoreCase("49")){  //SRLM USER
			query_hql=query_hql+" and rm.id='49' and pdn.created_By='"+ new UserMasterDaoImpl().getRecordFromId(loginVO.getUserid()).getCreatedBy()+"'";
		}
		else if(role.getId().equalsIgnoreCase("4")){   //PIA
			query_hql=query_hql+" and rm.id='10002' and pdn.pia_prn='"+loginVO.getUserid()+"'";
		}
		else if(role.getId().equalsIgnoreCase("10002")){   //PIA USER
			query_hql=query_hql+" and rm.id='10002' and pdn.pia_prn='"+ new UserMasterDaoImpl().getRecordFromId(loginVO.getUserid()).getCreatedBy()+"'";
		}
		else if(role.getId().equalsIgnoreCase("5001")){   //CTSA ADMIN
			query_hql=query_hql+" and rm.id='10001' and pdn.ctsa_type='"+new UserMasterDaoImpl().getRecordFromId(loginVO.getUserid()).getCtsaId().getId()+"'";
		}
		else if(role.getId().equalsIgnoreCase("10001")){   //CTSA USER
			query_hql=query_hql+" and rm.id='10001' and pdn.ctsa_type='"+ new UserMasterDaoImpl().getRecordFromId(loginVO.getUserid()).getCtsaId().getId()+"'";
		}
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			for(Object[] temp :(List<Object[]>) query.list()) {
				ProjectMappingBean bean=new ProjectMappingBean(temp[0]+"",temp[1]+"",temp[2]+"");
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
	public ProjectMapping getRecordFromId(String id)throws Exception {
		log.info("====checkRecord method Starts======>");
		ProjectMapping projectMapping=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ProjectMapping.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	projectMapping= (ProjectMapping)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return projectMapping;
	}
	public List<ProjectMapping> getRecordsFromUserId(String id)throws Exception {
		log.info("====checkRecord method Starts======>");
		List<ProjectMapping> list=new ArrayList<ProjectMapping>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			Criteria criteria = session.createCriteria(ProjectMapping.class).add(Restrictions.eq("userId", id));
			for(Object o : criteria.list()) {
				ProjectMapping ob=(ProjectMapping)o;
            	list.add(ob);
			}
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	public boolean deleteRecordFromId(ProjectMappingBean projectMapping) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			System.out.println("projectMapping.getId()==> "+projectMapping.getId());
			Criteria criteria = session.createCriteria(ProjectMapping.class).add(Restrictions.eq("userId", projectMapping.getId()));
			System.out.println("List Is ==> "+criteria.list().size());
            for(Object o : criteria.list()) {
            	ProjectMapping ob=(ProjectMapping)o;
            	System.out.println("Object To delete==> "+ob);
            	session.delete(ob);
			}
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

	public List<ProjectMappingBean> getProjectDetailsAssignList(String id) {
		List<ProjectMappingBean> list=new ArrayList<ProjectMappingBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select distinct pdn.id,pdn.project_id||' ('||(select state_name from mst_state where state_id=pdn.state_id)||') - '||(select user_name from user_Master where id=pdn.pia_prn) as project_id,um.id as userIdId from project_mapping as pm,project_details as pdn,user_master as um,role_master as rm where pm.project_id=pdn.id and um.id=pm.user_id and rm.id=um.role_id and pm.user_id='"+id+"'";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			for(Object[] temp :(List<Object[]>) query.list()) {
				ProjectMappingBean bean=new ProjectMappingBean(temp[0]+"",temp[1]+"",temp[2]+"");
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
	
}
