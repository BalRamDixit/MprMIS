package com.infotech.sgsy.stateSetup.hrTeam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.masterdata.projectTypeMaster.ProjectTypeMasterVO;


public class HRTeamDAO {

	
	/*public List getHRTeamList(String entityCode) throws HibernateException, Exception {
		String SQL = null;
		List HRTeam = new ArrayList();
		SQL="SELECT  id, person_name, designation,  joining_date,  COALESCE( NULLIF(location,'') , '0' ) as location_name, COALESCE( NULLIF(district_code,'') , '0' ) "
				+ "as district , email, office_no, mobile_no, esop_cert_req, COALESCE( NULLIF(esop_cert_level,'') , '0' ) as esop_cert_level,"
				+ " certification_date,is_active,dateOfLeaving FROM mst_hr_team  WHERE entity_code ='"+entityCode+"' order by person_name;";
		
		
		SQL = "SELECT  id, p_name, designation, to_char(joining_date,'DD-MM-YYYY') as joining_date, "
				+ " COALESCE( NULLIF(location_name,'') , '0' ) as location_name, COALESCE( NULLIF(district,'') , '0' ) as district , email_id,"
				+ " office_no, mobile, esop_cert_req, COALESCE( NULLIF(esop_cert_level,'') , '0' ) as esop_cert_level,"
				+ " to_char(certifiaction_date,'DD-MM-YYYY') as certifiaction_date "
				+ " FROM hr_team WHERE srlm_code ='"+entityCode+"' order by p_name";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			HRTeam = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return HRTeam;
	}*/
	
	public boolean save(HRTeamVO helperVo) throws Exception {

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
	
	public boolean update(HRTeamVO helperVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVo);
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
	/*public boolean update(HRTeamVO helperVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVo);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}*/
	
	
	public DistrictVO getDistrict(String  discode) throws Exception {
		DistrictVO genre=new DistrictVO(); 
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(DistrictVO.class)
                    .add(Restrictions.eq("districtCode", discode));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.uniqueResult();
            if (result != null) {
            	genre = (DistrictVO) result;
                System.out.println("Genre = " + genre.getDistrictName());
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return genre;
	}
	
	public List<HRTeamVO> getDetailAboutEmail(String email) throws Exception {
		List<HRTeamVO> list = new ArrayList<HRTeamVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		//String query_hql = "From HRTeamVO  WHERE email = :email ";

		try {	
			 Criteria criteria = session.createCriteria(HRTeamVO.class)
	                    .add(Restrictions.eq("email", email));
			 list= criteria.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())
				session.close();
		}
		return list;
	}
		
	public List<ProjectTypeMasterVO> getDetailProjectTypeName(){
		List<ProjectTypeMasterVO> result=new ArrayList<ProjectTypeMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(ProjectTypeMasterVO.class);

		result=cr.list();
		
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public HRTeamVO getDetailAboutHr(String id) throws Exception {
		HRTeamVO list =new HRTeamVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		//String query_hql = "From HRTeamVO  WHERE email = :email ";

		try {	
			 Criteria criteria = session.createCriteria(HRTeamVO.class)
	                    .add(Restrictions.eq("id", id));
			 Object result = criteria.uniqueResult();
	            if (result != null) {
	            	list= (HRTeamVO) result;
	               
	            }
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())
				session.close();
		}
		return list;
	}
	
	
	public boolean deletehr(HRTeamVO helperVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(helperVo);
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
	
	/*public List getDesignation() throws HibernateException, Exception {
		String SQL = null;
		List ViewList = new ArrayList();
		
		SQL = "select designation_code,designation_name from mst_designation";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			ViewList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		System.out.println(ViewList.size());
		return ViewList;
}*/
	
	
}
