package com.infotech.sgsy.master.district;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.PropertyModel;

public class DistrictDAO {

	protected final Log log = LogFactory.getLog(getClass());
	
	public List getDistricts(String stateCode) throws Exception {

		
		List results = null;
		
		//String query = "select dis from com.infotech.sgsy.master.district.DistrictVO dis where dis.stateCode='"+stateCode+"' order by dis.districtName";
		Session session=null;
		try{
			Persister persister = PersisterImpl.getPersister();	
			session=persister.getSession();
			Transaction transaction =session.getTransaction();
			transaction.begin();
			
			Criteria criteria=session.createCriteria("com.infotech.sgsy.master.district.DistrictVO");
			criteria.add(Restrictions.eq("stateCode", stateCode));	
			results=criteria.addOrder(Order.asc("districtName")).list();
			
			transaction.commit();
			return results;
		}catch (Exception e) {
			log.error("error in get Groups "+e);
			throw e;
		}finally{
			if(session.isOpen())
				session.close();
		}	

	}
	
	/** created By CVAS2273(20th - NOV -2009)
	 * @param stateCode
	 * @return districtList by stateCode
	 * @throws Exception
	 */
	public List getDistrictListByState(String stateCode) throws Exception {

		List results = null;
		List districtList = new ArrayList();		
		Persister persister = PersisterImpl.getPersister();			
		Session session=null;

		try{
			session=persister.getSession();
			Transaction transaction = session.getTransaction();;
			transaction.begin();
			
			Criteria criteria=session.createCriteria("com.infotech.sgsy.master.district.DistrictVO");			
			criteria.add(Restrictions.eq("stateCode", stateCode));	
			criteria.addOrder(Order.asc("districtName")); 
			
			results=criteria.list();//(List) persister.getObjectsByQuery(query);
			for(int x=0;x<results.size();x++){

				PropertyModel propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(((DistrictVO)results.get(x)).getDistrictCode());
				propertyModel.setPropertyValue(((DistrictVO)results.get(x)).getDistrictName());
				districtList.add(propertyModel);
			}
			
			session.flush();			
			transaction.commit();			
			//return districtList;
			
		}catch (Exception e) {			
		
			log.info("Error while getting districtList for DropDown :--> " + e.getMessage());
			
			
		}finally{
			if(session.isOpen())
			session.close();
		}
		return districtList;

	}
	
/*	// method added by pankaj
	public  String getDistrictFundReleaseFlag (String districtCode)throws Exception 
	{
		Connection con=null;
 	    PreparedStatement pstmt=null;
	 	ResultSet rs=null;
		
		String query="";
		String districtFundReleaseFlag = null;
		try 
		{
			con=PersisterImpl.getConnection();	
			query = "SELECT fundreleaseflag FROM mst_district WHERE district_code = ? ";				
			
			pstmt=con.prepareStatement(query);	
			pstmt.setString(1,districtCode);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				districtFundReleaseFlag = rs.getString("fundreleaseflag");				
			}			
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return districtFundReleaseFlag;
	}*/
	
	public String getDistrictNameByCode(String districtCode) {
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
	    String districtName = null;
		String query_hql = "select d.districtName from com.infotech.sgsy.master.district.DistrictVO d where d.districtCode = :districtCode";
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("districtCode",districtCode);
			districtName = (String)query.uniqueResult();
            tr.commit();
		} catch (Exception e) {
			log.error("Error while getting district name by code"
					+ e.getMessage());
		}
		finally{
			if(session.isOpen())
			session.close();
		}
		return districtName;
		
	}
	/*public List<DistrictVO> getDistrictByState(String stateCode) {
		List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		String query = "select d.districtName, d.districtCode from " +
				"com.infotech.sgsy.master.district.DistrictVO as d, com.infotech.sgsy.manageProposal.ProposalDistrictMasterVO as pd " +
				"where d.districtCode = pd.districtCode and d.stateCode='"+stateCode+"'";
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		try {
			tr.begin();
			List list = session.createQuery(query).list();
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
			log.error(e.getMessage());
		}
		return districtList;
	}*/
	
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
			log.error(e.getMessage());
		}
		finally{
			if(session.isOpen())
			session.close();
		}
		return districtList;
	}
	
	public List<DistrictVO> getDistrictByStateForLM(String stateCode) {
		List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		String query_hql = "select d.districtName, d.districtCode from " +
				"com.infotech.sgsy.master.district.DistrictVO as d where d.stateCode= :stateCode";
		PersisterImpl persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = persister.getTransaction();
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("stateCode", stateCode);
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
			log.error(e.getMessage());
		}
		finally{
			if(session.isOpen())
			session.close();
		}
		return districtList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DistrictVO> getDistrictListBYState(String stateCode) throws Exception {
		List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String query_hql = "From DistrictVO dt WHERE dt.stateCode = :stateCode order by dt.districtName";

		try {	
			Query query = session.createQuery(query_hql);
			query.setParameter("stateCode", stateCode);
			districtList = (List<DistrictVO>) query.list();
			tr.commit();
		} catch (HibernateException e) {
			log.error("error in get Groups " + e.getMessage());
		} finally {
			if(session.isOpen())
				session.close();
		}
		return districtList;
	}
	
	/**
	 * @since 8 August 2013
	 * @param InterestSubventionVO
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrUpdateInterestSubvention(
			List<InterestSubventionVO> InterestSubventionVO) throws Exception {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();

		try {
			for (InterestSubventionVO interestSubventionVo : InterestSubventionVO) {
				session.saveOrUpdate(interestSubventionVo);
			}
			tr.commit();
			flag = true;
		} catch (HibernateException e) {
			log.error("PROBLEM IN InterestSubvention insertinterestSubvention(interestSubvetion) FUNCTION: "
					+ e.getMessage());
		} finally{
			if(session.isOpen())
				session.close();
			}
		return flag;
	}

	/**
	 * @since 8 August 2013
	 * @param stateCode
	 * @return
	 */
	@SuppressWarnings("null")
	public List<InterestSubventionVO> getDistrictForInterestSubvention(
			String stateCode) {
		List<InterestSubventionVO> districtList = new ArrayList<InterestSubventionVO>();
		try {
			List<DistrictVO> DistrictLst = getDistrictByStateForLM(stateCode);
			if (DistrictLst != null || DistrictLst.size() != 0)
				for (DistrictVO districtvo : DistrictLst) {
					InterestSubventionVO district = new InterestSubventionVO();
					district.setDistrictName(districtvo.getDistrictName());
					district.setEntityCode(districtvo.getDistrictCode());
					districtList.add(district);
				}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
		}
		return districtList;
	}

	/**
	 * @since 8 August 2013
	 * @param stateCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InterestSubventionVO> getDetailInterestSubvention(
			String stateCode) {

		List<InterestSubventionVO> districtList = new ArrayList<InterestSubventionVO>();
		String query_hql = "FROM InterestSubventionVO as i WHERE i.entityCode LIKE(:stateCode)";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("stateCode", stateCode + "%");
			districtList = (List<InterestSubventionVO>) query.list();
			tr.commit();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally{
			if(session.isOpen())
				session.close();
			}
		return districtList;
	}

}


