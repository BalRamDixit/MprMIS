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
import com.infotech.sgsy.login.LoginMasterDAOImpl;

public class FormModuleMasterDaoImpl {
	protected final Log log = LogFactory.getLog(getClass());
	public boolean checkRecord(String formName)throws Exception {
		boolean status=false;
		log.info("====checkRecord method Starts======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(FormModuleMaster.class)
                    .add(Restrictions.eq("formName", formName));

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
	public boolean save(FormModuleMaster formModuleMaster) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(formModuleMaster);
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
	public boolean saveOrUpdate(FormModuleMaster formModuleMaster) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(formModuleMaster);
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
	public List<FormModuleMasterBean> getAllRecords(){
		List<FormModuleMasterBean> list=new ArrayList<FormModuleMasterBean>();
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select a.id,a.form_name,b.form_name as module_name,a.url,a.order_no,a.help from form_module_master as a left join form_module_master as b on a.module_name=b.id order by COALESCE(b.order_no, 0)||''||a.order_no";
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
		System.out.println("List is --> "+list);
		return list;
	}
	public List<FormModuleMasterBean> getAllMasterModuleList(){
		log.info("====checkRecord method Starts======>");
		List<FormModuleMasterBean> list=new ArrayList<FormModuleMasterBean>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(FormModuleMaster.class)
                    .add(Restrictions.disjunction().add(Restrictions.isNull("moduleName")).add(Restrictions.eq("moduleName", "")).add(Restrictions.eq("moduleName", "0")));

//            Criteria criteria2= session.createCriteria(FormModuleMaster.class)
//            		.add(Restrictions.not(Restrictions.in("id", criteria.list())));
            
            for(Object o : criteria.list()) {
            	FormModuleMaster ob=(FormModuleMaster)o;
            	FormModuleMasterBean ob1=new FormModuleMasterBean();
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
	public FormModuleMaster getRecordFromId(String id)throws Exception {
		log.info("====checkRecord method Starts======>");
		FormModuleMaster formModuleMaster=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(FormModuleMaster.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	formModuleMaster= (FormModuleMaster)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return formModuleMaster;
	}
	public boolean deleteRecordFromId(FormModuleMaster formModuleMaster) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(formModuleMaster);
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
	public boolean checkPermissionForForm(String formId, AccessModuleListForUserAndMenuBean bean) {
		boolean status=false;
		log.info("====checkPermissionForForm Start======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			AssignRoleMaster ob1=null;
			AssignRoleMaster ob2=null;
            Criteria criteria = session.createCriteria(AssignRoleMaster.class)
                    .add(Restrictions.or(Restrictions.eq("roleId", bean.getRoleId()),Restrictions.eq("userId", bean.getUserId())))
                    .add(Restrictions.eq("formModuleId", formId));
            if(criteria!=null && criteria.list().size()>=1){
	            for(Object o : criteria.list()) {
	            	if(ob1==null){
	            		ob1=(AssignRoleMaster)o;
	            	}
	            	if(ob1!=null && ob2==null){
	            		ob2=(AssignRoleMaster)o;
	            	}
				}
	            
			}
            if(ob1!=null && ob2!=null ){
            	String permission=new LoginMasterDAOImpl().getPermission(ob1.getPermissionChar(), ob2.getPermissionChar());
            	if(permission.startsWith("11")||permission.startsWith("01")||permission.startsWith("10")){
            		status=true;
            	}
            }
            
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		log.info("====checkPermissionForForm End======>");
		return status;
	}
	public boolean checkPermissionForFormForInsert(String formId, AccessModuleListForUserAndMenuBean bean) {
		boolean status=false;
		log.info("====checkPermissionForForm Start======>");
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			AssignRoleMaster[] ob=new AssignRoleMaster[3];
			Criteria criteria = session.createCriteria(AssignRoleMaster.class)
                    .add(Restrictions.or(Restrictions.eq("roleId", bean.getRoleId()),Restrictions.eq("userId", bean.getUserId())))
                    .add(Restrictions.eq("formModuleId", formId));
            if(criteria!=null && criteria.list().size()>=1){
            	int i=0;
	            for(Object o : criteria.list()) {
	            	System.out.println("i=========> "+i);
	            	ob[i]=(AssignRoleMaster)o;
	            	i++;
				}
	            
			}
            
            if(ob[0]!=null && (ob[0].getPermissionChar().charAt(0)=='1')){
            	status=true;
            	if(ob[1]!=null && ob[1].getPermissionChar().charAt(0)!='1'){
            		status=false;
            	}
            }
            
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		log.info("====checkPermissionForForm End======>");
		return status;
	}
	public String getMasterModuleFromFormId(String formId) {
		FormModuleMaster formModuleMaster=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(FormModuleMaster.class)
                    .add(Restrictions.eq("id", formId));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	formModuleMaster= (FormModuleMaster)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return formModuleMaster.getModuleName();
	}
	
}
