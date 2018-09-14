package com.infotech.sgsy.userAccessControlManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import com.infotech.sgsy.util.DateUtil;

public class AssignRoleMasterDaoImpl {
	protected final Log log = LogFactory.getLog(getClass());
	public boolean checkRecord(String loginId)throws Exception {
		boolean status=false;
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(AssignRoleMaster.class)
                    .add(Restrictions.eq("loginId", loginId));

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
	public List<RoleMaster> getAllRoleMsters(){
		List<RoleMaster> list=new ArrayList<RoleMaster>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria criteria = session.createCriteria(AssignRoleMaster.class);
			List<String> rolelist=new ArrayList<String>();
			for(Object o : criteria.list()) {
				String s=((AssignRoleMaster)o).getRoleId();
				if(s!=null){
					rolelist.add(s);
				}
			}
			Criteria query = session.createCriteria(RoleMaster.class);
			if(rolelist.size()>=1){
				query.add(Restrictions.not(Restrictions.in("id", rolelist)));
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
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	public List<UserMasterBean> getAllUser(LoginVO loginVO){
		log.info("====checkRecord method Starts======>");
		List<UserMasterBean> list=new ArrayList<UserMasterBean>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
			if(role.getId().equalsIgnoreCase("5")){
				Criteria criteria = session.createCriteria(AssignRoleMaster.class);
				List<String> userlist=new ArrayList<String>();
				for(Object o : criteria.list()) {
					String s=((AssignRoleMaster)o).getUserId();
					if(s!=null){
						userlist.add(s);
					}
				}
	            Criteria criteria1 = session.createCriteria(UserMaster.class);
	            if(userlist.size()>=1){
	            	criteria1.add(Restrictions.not(Restrictions.in("id", userlist)));
				}
	            for(Object o : criteria1.list()) {
	            	UserMaster ob=(UserMaster)o;
	            	UserMasterBean ob1=new UserMasterBean();
	            	BeanUtils.copyProperties(ob1,ob);
	            	
				    list.add(ob1);
				}
			}else{
				Criteria criteria = session.createCriteria(AssignRoleMaster.class);
				List<String> userlist=new ArrayList<String>();
				for(Object o : criteria.list()) {
					String s=((AssignRoleMaster)o).getUserId();
					if(s!=null){
						userlist.add(s);
					}
				}
	            Criteria criteria1 = session.createCriteria(UserMaster.class).add(Restrictions.eq("createdBy", loginVO.getUserid()));
	            if(userlist.size()>=1){
	            	criteria1.add(Restrictions.not(Restrictions.in("id", userlist)));
				}
	            for(Object o : criteria1.list()) {
	            	UserMaster ob=(UserMaster)o;
	            	UserMasterBean ob1=new UserMasterBean();
	            	BeanUtils.copyProperties(ob1,ob);
				    list.add(ob1);
				}
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
	public Map<String, Set<FormModuleMasterBean>> getAllFormModules(LoginVO loginVO){
		Map<String, Set<FormModuleMasterBean>> list=new HashMap<String, Set<FormModuleMasterBean>>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		//--------------------------0------1----------2--------------------------3------4-------5------6-------------------7
		String query_hql = "select a.id,a.form_name,b.form_name as module_name,a.url,a.order_no,a.help,b.id as master_id,arm.permission from form_module_master "
				+ "as a left join form_module_master as b on a.module_name=b.id left join assign_role_master arm on arm.form_module_master_id=a.id "
				+ "where arm.role_id='"+loginVO.getRoleId()+"' or arm.user_id='"+loginVO.getUserid()+"' order by b.id desc,a.form_name||b.form_name";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			for(Object[] temp :(List<Object[]>) query.list()) {
				//System.out.println("-----"+temp[6]+"----");
				FormModuleMasterBean bean=new FormModuleMasterBean(temp[0]+"",temp[1]+"",temp[2]+"",temp[3]+"",temp[4]+"",temp[5]+"");
				if(temp[6]==null){
					Set<FormModuleMasterBean> moduleList=new TreeSet<FormModuleMasterBean>(
							new Comparator<FormModuleMasterBean>() {
								@Override
								public int compare(FormModuleMasterBean arg0, FormModuleMasterBean arg1) {
									if(arg0.getId().equalsIgnoreCase(arg1.getId())){
										return 0;
									}
									return 1;
								}
								
							});
					
					if(role.getId().equalsIgnoreCase("5")){
						moduleList.add(bean);
						list.put(temp[0]+"", moduleList);
					}else if((temp[7]+"").endsWith("1")){
						moduleList.add(bean);
						list.put(temp[0]+"", moduleList);
					}
					
					
				}
				else{
					if(list.containsKey(temp[6]+"")){
						Set<FormModuleMasterBean> moduleList=list.get(temp[6]+"");
						if(role.getId().equalsIgnoreCase("5")){
							moduleList.add(bean);
						}else if((temp[7]+"").endsWith("1")){
							moduleList.add(bean);
						}
					}
					else{
						Set<FormModuleMasterBean> moduleList=new TreeSet<FormModuleMasterBean>(new Comparator<FormModuleMasterBean>() {
							@Override
							public int compare(FormModuleMasterBean arg0, FormModuleMasterBean arg1) {
								if(arg0.getId().equalsIgnoreCase(arg1.getId())){
									return 0;
								}
								return 1;
							}
							
						});
						if(role.getId().equalsIgnoreCase("5")){
							moduleList.add(bean);
							list.put(temp[6]+"", moduleList);
						}else if((temp[7]+"").endsWith("1")){
							moduleList.add(bean);
							list.put(temp[6]+"", moduleList);
						}
						
					}
				}
			}
			System.out.println(list);
			
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
	public Map<String, Set<FormModuleMasterBean>> getAllFormModulesForEdit(AssignRoleMasterBean assignRoleMasterBean,LoginVO loginVO){
		Map<String, Set<FormModuleMasterBean>> list=new HashMap<String, Set<FormModuleMasterBean>>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		
		System.out.println("assignRoleMasterBean======> "+assignRoleMasterBean);
		if(assignRoleMasterBean.getId()!=null && assignRoleMasterBean.getId().startsWith("Role")){
			assignRoleMasterBean.setRoleId(assignRoleMasterBean.getId().replaceAll("Role", ""));
		}
		else if(assignRoleMasterBean.getId()!=null && assignRoleMasterBean.getId().startsWith("User")){
			assignRoleMasterBean.setUserId( assignRoleMasterBean.getId().replaceAll("User", ""));
		}
//		StringBuffer query_hql = new StringBuffer("select a.id,a.form_name,b.form_name as module_name,a.url,arm.id as armID,arm.permission,b.id as master_id from form_module_master as a left join form_module_master as b on a.module_name=b.id left join assign_role_master as arm on a.id=arm.form_module_master_id where arm.role_id is null and arm.user_Id is null or ");
		StringBuffer query_hql = new StringBuffer("select  a.id,a.form_name,a.module_name,a.url,b.armID,b.permission,a.master_id ");
		query_hql.append("from (select a.id,a.form_name,b.form_name as module_name,a.url,b.id as master_id ");
		query_hql.append( "from form_module_master as a left join form_module_master as b on a.module_name=b.id) as a ");
		query_hql.append( "left join (select arm.id as armID,arm.permission,arm.role_id,arm.user_Id,arm.form_module_master_id ");
		query_hql.append( "from assign_role_master as arm where (arm.role_id is null and arm.user_Id is null) or  (");
		
		if(assignRoleMasterBean.getRoleId()==null||assignRoleMasterBean.getRoleId().equalsIgnoreCase("")){
			query_hql.append(" arm.role_id is null");
		}
		else{
			query_hql.append(" arm.role_id ='"+assignRoleMasterBean.getRoleId()+"'");
		}
		if(assignRoleMasterBean.getUserId()==null||assignRoleMasterBean.getUserId().equalsIgnoreCase("")){
			query_hql.append(" and arm.user_Id is null");
		}
		else{
			query_hql.append(" and arm.user_Id ='"+assignRoleMasterBean.getUserId() +"'");
		}
		query_hql.append(" )) as b on a.id=b.form_module_master_id order by a.master_id desc,a.form_name||a.module_name");
		System.out.println("Query Is --> "+query_hql);
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql.toString());
			for(Object[] temp :(List<Object[]>) query.list()) {
				FormModuleMasterBean bean=new FormModuleMasterBean(temp[0]+"",temp[1]+"",temp[2]+"",temp[3]+"",temp[4]+"",temp[5]+"");
				if(temp[6]==null){
					Set<FormModuleMasterBean> moduleList=new TreeSet<FormModuleMasterBean>(new Comparator<FormModuleMasterBean>() {
						@Override
						public int compare(FormModuleMasterBean arg0, FormModuleMasterBean arg1) {
							if(arg0.getId().equalsIgnoreCase(arg1.getId())){
								return 0;
							}
							return 1;
						}
						
					});
					if(role.getId().equalsIgnoreCase("5")){
						moduleList.add(bean);
						list.put(temp[0]+"", moduleList);
					}else if(temp[4] !=null){
						moduleList.add(bean);
						list.put(temp[0]+"", moduleList);
					}
					
					
				}
				else{
					if(list.containsKey(temp[6]+"")){
						Set<FormModuleMasterBean> moduleList=list.get(temp[6]+"");
						if(role.getId().equalsIgnoreCase("5")){
							moduleList.add(bean);
						}else if(temp[4] !=null){
							moduleList.add(bean);
						}
					}
					else{
						Set<FormModuleMasterBean> moduleList=new TreeSet<FormModuleMasterBean>(new Comparator<FormModuleMasterBean>() {
							@Override
							public int compare(FormModuleMasterBean arg0, FormModuleMasterBean arg1) {
								if(arg0.getId().equalsIgnoreCase(arg1.getId())){
									return 0;
								}
								return 1;
							}
							
						});
						if(role.getId().equalsIgnoreCase("5")){
							moduleList.add(bean);
							list.put(temp[6]+"", moduleList);
						}else if(temp[4] !=null){
							moduleList.add(bean);
							list.put(temp[6]+"", moduleList);
						}
						
					}
				}
				System.out.println("List Is ===> "+list);
				
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
	public List<FormModuleMasterBean> getAllFormModulesOld(LoginVO loginVO){
		List<FormModuleMasterBean> list=new ArrayList<FormModuleMasterBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select a.id,a.form_name,b.form_name as module_name,a.url,a.order_no,a.help,b.id as master_id from form_module_master as a left join form_module_master as b on a.module_name=b.id order by b.id desc,a.form_name||b.form_name";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			for(Object[] temp :(List<Object[]>) query.list()) {
				FormModuleMasterBean bean=new FormModuleMasterBean(temp[0]+"",temp[1]+"",temp[2]+"",temp[3]+"",temp[4]+"",temp[5]+"");
            	list.add(bean);
			}
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		//System.out.println("List is --> "+list);
		getAllFormModules(loginVO);
		return list;
	}
	public List<FormModuleMasterBean> getAllFormModulesForEditOld(AssignRoleMasterBean assignRoleMasterBean,LoginVO loginVO){
		List<FormModuleMasterBean> list=new ArrayList<FormModuleMasterBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		StringBuffer query_hql = new StringBuffer("select a.id,a.form_name,b.form_name as module_name,a.url,arm.id as armID,arm.permission from form_module_master as a left join form_module_master as b on a.module_name=b.id left join assign_role_master as arm on a.id=arm.form_module_master_id where arm.role_id is null and arm.user_Id is null or ");
		if(assignRoleMasterBean.getRoleId()==null){
			query_hql.append(" arm.role_id is null");
		}
		else{
			query_hql.append(" arm.role_id ='"+assignRoleMasterBean.getRoleId()+"'");
		}
		if(assignRoleMasterBean.getUserId()==null){
			query_hql.append(" and arm.user_Id is null");
		}
		else{
			query_hql.append(" and arm.user_Id ='"+assignRoleMasterBean.getUserId() +"'");
		}
		System.out.println("Query Is --> "+query_hql);
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql.toString());
			for(Object[] temp :(List<Object[]>) query.list()) {
				FormModuleMasterBean bean=new FormModuleMasterBean(temp[0]+"",temp[1]+"",temp[2]+"",temp[3]+"",temp[4]+"",temp[5]+"");
				System.out.println(bean);
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
	public boolean save(AssignRoleMasterBean[] assignRoleMasterBean) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<assignRoleMasterBean.length;i++){
				AssignRoleMaster assignRoleMaster=new AssignRoleMaster();
				BeanUtils.copyProperties(assignRoleMaster,assignRoleMasterBean[i]);
				session.save(assignRoleMaster);
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
	public boolean saveOrUpdate(AssignRoleMaster[] assignRoleMaster) {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<assignRoleMaster.length;i++){
				session.saveOrUpdate(assignRoleMaster[i]);
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
	public List<AssignRoleMasterBean> getAllRecords(LoginVO loginVO){
		List<AssignRoleMasterBean> list=new ArrayList<AssignRoleMasterBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		/*
		 * Old Query For SQL:-
		 * StringBuffer query_hql = new StringBuffer("select arm.id,rm.role_name,um.login_id,(select rolem.role_name from role_master as rolem where rolem.id=um.role_id )as userRole,fmm.form_name,arm.permission from assign_role_master as arm left join role_master as rm on arm.role_id=rm.id left join user_master as um on arm.user_id=um.id left join form_module_master as fmm on arm.form_module_master_id=fmm.id");
		 */
		StringBuffer query_hql = new StringBuffer("select distinct rm.role_name,um.login_id,(select rolem.role_name from role_master as rolem where rolem.id=um.role_id )as userRole,rm.id as role_id,um.id as user_id from assign_role_master as arm left join role_master as rm on arm.role_id=rm.id left join user_master as um on arm.user_id=um.id left join form_module_master as fmm on arm.form_module_master_id=fmm.id");
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		if(role.getId().equalsIgnoreCase("45")){//For SRLM ADMIN
			query_hql.append(" where arm.user_id='"+loginVO.getUserid()+"'  or arm.created_by='"+loginVO.getUserid()+"' ");
		}else if(role.getId().equalsIgnoreCase("4")){//FOR PIA ADMIN
			query_hql.append(" where arm.user_id='"+loginVO.getUserid()+"'  or arm.created_by='"+loginVO.getUserid()+"' ");
		}
		else if(role.getId().equalsIgnoreCase("5001")){//FOR CTSA ADMIN
			query_hql.append(" where arm.user_id='"+loginVO.getUserid()+"'  or arm.created_by='"+loginVO.getUserid()+"' ");
		}
		
		System.out.println("Query for Assign Module Mapping --> "+query_hql.toString());
		
		
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql.toString());
			for(Object[] temp :(List<Object[]>) query.list()) {
				AssignRoleMasterBean bean=new AssignRoleMasterBean(temp[0]+"",temp[1]+"",temp[2]+"","Role"+temp[3],"User"+temp[4]);
				/*if(role.getId().equalsIgnoreCase("5")){
					list.add(bean);
				}
				else if(bean.getEditId()!=null && bean.getEditId().endsWith("1")){
					list.add(bean);
				}*/
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
	public AssignRoleMaster getRecordFromId(String id) {
		log.info("====checkRecord method Starts======>");
		AssignRoleMaster assignRoleMaster=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			System.out.println("Data Is ====> "+id);
			Criteria criteria = session.createCriteria(AssignRoleMaster.class)
					.add(Restrictions.eq("id", id));
    		/*if(id!=null && id.startsWith("Role")){
    			criteria.add(Restrictions.eq("roleId", id.replaceAll("Role", "")));
    		}
    		else if(id!=null && id.startsWith("User")){
    			criteria.add(Restrictions.eq("userId", id.replaceAll("User", "")));
    		}*/

            Object result = criteria.uniqueResult();
            if (result != null) {
            	assignRoleMaster= (AssignRoleMaster)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return assignRoleMaster;
	}
	public boolean deleteRecordFromId(String id) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(AssignRoleMaster.class);
    		if(id!=null && id.startsWith("Role")){
    			criteria.add(Restrictions.eq("roleId", id.replaceAll("Role", "")));
    		}
    		else if(id!=null && id.startsWith("User")){
    			criteria.add(Restrictions.eq("userId", id.replaceAll("User", "")));
    		}
			for(Object ob:criteria.list()){
				AssignRoleMaster bean=(AssignRoleMaster)ob;
				session.delete(bean);
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
	public List<FormModuleMasterBean> getAllFormModulesForDelete(String id) {
		List<FormModuleMasterBean> list=new ArrayList<FormModuleMasterBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		AssignRoleMaster assignRoleMasterBean= getRecordFromId(id);
		//String query_hql = "select a.id,a.form_name,b.form_name as module_name,a.url,arm.id as armID,arm.permission from form_module_master as a left join form_module_master as b on a.module_name=b.id join assign_role_master as arm on arm.form_module_master_id=a.id where arm.role_id='"+assignRoleMasterBean.getRoleId()+"' and arm.user_Id='"+assignRoleMasterBean.getUserId()+"'";
		String query_hql="select a.id,a.form_name,a.module_name,a.url,arm.armID,arm.permission from "
					+" (select a.id,a.form_name,b.form_name as module_name,a.url from form_module_master as a left join form_module_master as b on a.module_name=b.id ) as a"
					+" left join (select arm.id as armID,arm.permission,arm.role_id, arm.user_Id,arm.form_module_master_id from assign_role_master as arm where (arm.role_id is null and arm.user_Id is null) or (arm.role_id='"+assignRoleMasterBean.getRoleId()+"' and arm.user_Id='"+assignRoleMasterBean.getUserId()+"')) as arm"
					+" on a.id=arm.form_module_master_id";
		System.out.println("Query Is --> "+query_hql);
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_hql);
			for(Object[] temp :(List<Object[]>) query.list()) {
				FormModuleMasterBean bean=new FormModuleMasterBean(temp[0]+"",temp[1]+"",temp[2]+"",temp[3]+"",temp[4]+"",temp[5]+"");
				System.out.println(bean);
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
	public boolean saveOrUpdate2(AssignRoleMasterBean assignRoleMasterBean) {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			//-------------------------------------------------------------------------
			AssignRoleMaster[] assignRoleMasterArray=new AssignRoleMaster[assignRoleMasterBean.getFormModuleIdList().length];
			System.out.println(assignRoleMasterBean);
			
			for(int i=0;i<assignRoleMasterBean.getFormModuleIdList().length;i++){
				AssignRoleMaster assignRoleMaster=new AssignRoleMasterDaoImpl().getRecordFromId(assignRoleMasterBean.getAccessModuleIdList()[i]);
				if(assignRoleMaster==null){
					assignRoleMaster=new AssignRoleMaster();
					BeanUtils.copyProperties(assignRoleMaster,assignRoleMasterBean);
					assignRoleMaster.setId(null);
				}
				if(assignRoleMaster.getUserId()==null || assignRoleMaster.getUserId().equalsIgnoreCase("0")||assignRoleMaster.getUserId().equalsIgnoreCase("")){
					assignRoleMaster.setUserId(null);
				}
				if(assignRoleMaster.getRoleId()==null || assignRoleMaster.getRoleId().equalsIgnoreCase("0")||assignRoleMaster.getRoleId().equalsIgnoreCase("")){
					assignRoleMaster.setRoleId(null);
				}
				System.out.println("----------------------------------------------------------------\n\n\n\n\n\n\n\n\nBefore Update--> "+assignRoleMaster);
				assignRoleMaster.setUpdatedBy(assignRoleMasterBean.getUpdatedBy());
				assignRoleMaster.setUpdatedDate(DateUtil.getPresentDate());
				assignRoleMaster.setFormModuleId(assignRoleMasterBean.getFormModuleIdList()[i] );
				assignRoleMaster.setPermissionChar(assignRoleMasterBean.getPermissionCharList()[i]);
				session.saveOrUpdate(assignRoleMaster);
				System.out.println("After Update--> "+assignRoleMaster);
			}
			//-------------------------------------------------------------------------
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
