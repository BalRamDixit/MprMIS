package com.infotech.sgsy.tcSetup.tcSetupEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.master.parliamentaryConstituencyMaster.ParliamentaryConstituencyMasterVO;
import com.infotech.sgsy.masterdata.constituencyMaster.ConstituencyMasterVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.projectSetup.tradeTarget.TradeTargetVO;
import com.infotech.sgsy.util.CommonUtils;
 

public class TcSetUpEntryDAO {

	 
	 	public List getdetails(String entity_code,String status) throws HibernateException, Exception {
		String SQL = null;
		List tcList = new ArrayList();
		//select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"'
		if("pia".equals(status))
		SQL ="select  a.projectid,a.address, a.pincode, c.assembly_name,a.resistatus,a.tcid from tc_new as a , project_detail_new as b,mst_assembly as c where b.projectid=a.projectid and a.assemblycons=c.assemby_id and b.piaprn='"+entity_code+"' order by a.projectid asc";
		else
			SQL ="select  a.projectid,a.address, a.pincode, c.assembly_name,a.resistatus,a.tcid from tc_new as a , project_detail_new as b,mst_assembly as c where b.projectid=a.projectid and a.assemblycons=c.assemby_id and b.entity_code='"+entity_code+"' order by a.projectid asc";

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			tcList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return tcList;
	} 
public TcSetupVO getdetailsbyTCId(String tcid) throws HibernateException, Exception {
			
			TcSetupVO dataVO = new TcSetupVO();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(TcSetupVO.class)
	                    .add(Restrictions.eq("tcID", tcid));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	dataVO= (TcSetupVO) result;
	               
	            }
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }

			return dataVO;
} 


		public TcSetupVO getdetailsbyId(int id) throws HibernateException, Exception {
			
			TcSetupVO dataVO = new TcSetupVO();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(TcSetupVO.class)
	                    .add(Restrictions.eq("id", id));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.uniqueResult();
	            if (result != null) {
	            	dataVO= (TcSetupVO) result;
	               
	            }
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }

			return dataVO;
		} 
		
				
	 	
	 	
	 	
	 	
	 
	public boolean save(TcSetupVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(helperVO);
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
	public boolean update(TcSetupVO helperVO) throws Exception {

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
	public boolean delete(TcSetupVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(helperVO);
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
	public List getProjectByEntitycode(String entity_code,String status) throws Exception {
		String sQL = null;
		List projectlist = new ArrayList();
		
		//sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		
		
		if("pia".equals(status))
			sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.piaprn='"+entity_code+"'";	
		else
			sQL = "select a.projectid from user_sanction_details  as a , project_detail_new as b where b.projectid=a.projectid and b.entity_code='"+entity_code+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			
			projectlist = query.list();
			System.out.println("list size-->   "+projectlist.size());
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return projectlist;
	}
	public List<DistrictVO> getDistrictByState(String stateCode) {
		List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		String query_hql = "select d.districtName, d.districtCode from " +
			"com.infotech.sgsy.master.district.DistrictVO as d" +
				" where d.stateCode= :stateCode ";
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		try {
			tr.begin();
			Query query= session.createQuery(query_hql);
			query.setParameter("stateCode",stateCode);
			List list = query.list();
			for(int i = 0; i<list.size(); i++) {
				Object[] arr = (Object[])list.get(i);
				DistrictVO district = new DistrictVO();
				district.setDistrictName(arr[0].toString());
				district.setDistrictCode(arr[1].toString());
				districtList.add(district);
			}
			tr.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session.isOpen())
			session.close();
		}
		return districtList;
	}
		public List getDistrictByStateName(String statename) throws Exception {
		String sQL = null;
		List list=new ArrayList();
		
		sQL = "select a.district_code,a.district_name from mst_district as a,mst_state as b where a.state_code=b.state_code and b.state_name='"+statename+"'";
	
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			/*query.setParameter("sector_code", sectorId);*/
			list=query.list();
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return list;
	}
	
	
	
	public long getSrNumber(String projectid) {
		long maxid =0;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		//String query_hql = "select max(a.id) from com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO as a";
		String query_hql = "select count(*) from com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO as a WHERE a.projectID='"+projectid+"'";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			//query.setParameter("stateCode", stateCode);
			maxid =  (long) query.uniqueResult();
			tr.commit();
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}

		return maxid;
	}
	public List<ConstituencyMasterVO> getAssemblyConBydis(String discode) throws Exception {
		List<ConstituencyMasterVO> list=new ArrayList<ConstituencyMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ConstituencyMasterVO.class)
                    .add(Restrictions.eq("district.districtId", discode));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
             list = criteria.list();
            
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return list;
	}
	public String getStateCodeByProjectId(String projectid) {
		String entity_code = null;
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		String query_hql = "select s.entityCode from com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO s where s.projectID= :projectID";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("projectID", projectid);
			entity_code = (String) query.uniqueResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}

		return entity_code;
	}
	//criteria.add(Restrictions.in("identifier", ids));
	public Set<ParliamentaryConstituencyMasterVO> getParliamentConByDistrict(String cons) {
		 Set<ParliamentaryConstituencyMasterVO> parliamentaryConstituencyList=new TreeSet<ParliamentaryConstituencyMasterVO>(
     		new Comparator<ParliamentaryConstituencyMasterVO>() {

			@Override
			public int compare(ParliamentaryConstituencyMasterVO arg0,ParliamentaryConstituencyMasterVO arg1) {
				System.out.println("Object 1==> "+arg0);
				System.out.println("Object 2==> "+arg1);
				if(arg0.getId().equalsIgnoreCase(arg1.getId())){
					return 0;
				}
				return 1;
			}
		});
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ConstituencyMasterVO.class)
                    .add(Restrictions.eq("district.districtId", cons)).addOrder(Order.asc("parliamentaryConstituencyName.id"));
           
            for(Object o :criteria.list()){
            	ConstituencyMasterVO ob=(ConstituencyMasterVO)o;
            	parliamentaryConstituencyList.add(ob.getParliamentaryConstituencyName());
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return parliamentaryConstituencyList;
	}

	
}
	


