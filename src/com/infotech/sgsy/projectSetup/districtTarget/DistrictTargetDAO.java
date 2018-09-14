package com.infotech.sgsy.projectSetup.districtTarget;

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
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.projectSanctionDetail.SanctionDetailVO;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;


public class DistrictTargetDAO {

	
	
	//...to show the record present in user_district_target
	public List view() throws HibernateException, Exception {
		String SQL = null;
		List ViewList = new ArrayList();
		
		SQL = "select projectid,projectid  from user_district_target group by projectid";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			ViewList = query.list();
			System.out.println("ViewList...."+ViewList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return ViewList;
      }
	
	public Object  getById(Class <?> abc,String id){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			
				 result= session.get(abc, id); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	
	public Object  getDistrictTargetList(){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
			 Criteria criteria = session.createCriteria(DistrictTargetVO.class);
	        result=criteria.list(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	
	
	
	
	
	
//.2..to get the project id	
	public List getProjectList(String levelCode) throws HibernateException, Exception {
		String SQL = null;
		List ProjectList = new ArrayList();
		if(levelCode.length()==2)
		{
			//SQL = " select state_name, projectid from project_detail_new   where entity_code='"+levelCode+"'";
			SQL = "select a.projectid,b.state_name from project_detail_new as a,mst_state as b where a.entity_code=b.state_code and a.entity_code='"+levelCode+"'";
		}
		
		else{
		//	SQL = " select state_name, projectid from project_detail_new   where piaprn='"+levelCode+"'";
		//	SQL = "select a.projectid,b.state_name from project_detail_new as a,mst_state as b where a.entity_code=b.state_code and a.piaprn='"+levelCode+"'";
		//	SQL ="select a.projectid,b.state_code  from project_detail_new as a,mst_state as b where a.entity_code=b.state_code and a.piaprn='"+levelCode+"'";
			SQL ="select a.projectid,b.state_name,b.state_code  from project_detail_new as a,mst_state as b where a.entity_code=b.state_code and a.piaprn='"+levelCode+"'";
		}
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			ProjectList = query.list();
			
			System.out.println("ProjectList...."+ProjectList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return ProjectList;
}
	
	//...for getting the district dropdown
	
	public List getDistrict(String project_Id) throws Exception {
		String sQL = null;
		List DistrictList = new ArrayList();
	//	sQL = "select a.district_name,a.district_code from district_master  as a,mst_state as b where a.state_code = b.state_code and upper(b.state_name)='"+state_name+"'";
	
		sQL ="select dist.district_id,dist.district_name from project_details as project,User_master as um,mst_district as dist where project.id='"+project_Id+"' and project.created_by=um.id and um.state_id=dist.state_id order by dist.district_name;";

		System.err.println("man"+sQL);
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			DistrictList = query.list();
			
			System.out.println("DistrictList..."+DistrictList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return DistrictList;
	}
	
	//..to get the district name
	public String getDistrictName(String district_code) {
		System.out.println("..charvi..."+district_code);
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			String districtName="";
			List<String> entryStatusDetailList = new ArrayList<String>();
			String SQL_Query = null;
			SQL_Query = "select district_name from mst_district  where district_code=:district_code";
		
			try {
				tr.begin();
				Query Query = session.createSQLQuery(SQL_Query);
				Query.setParameter("district_code", district_code);
				entryStatusDetailList = Query.list();
				tr.commit();
				districtName =  entryStatusDetailList.get(0).toString();
							
			} catch (Exception e) {
				e.printStackTrace();
			}
			return districtName;
		}
	//...to get state code
	public String getStateCode(String state_name) {
		System.out.println("..charvi..."+state_name);
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			String stateCode="";
			List<String> entryStatusDetailList = new ArrayList<String>();
			String SQL_Query = null;
			SQL_Query = "select state_code from mst_state  where state_name='"+state_name+"'";;
		
			try {
				tr.begin();
				Query Query = session.createSQLQuery(SQL_Query);
				/*Query.setParameter("state_Code", SQL_Query);*/
				entryStatusDetailList = Query.list();
				tr.commit();
											
			} catch (Exception e) {
				e.printStackTrace();
			}
			return stateCode;
		}
	
	
	
	//.1.and 5.code for getting the district name
	public List<String> getDistrictList( String entityCode) {

		List<String> districtNameList = new ArrayList<String>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
				
		try {
			tr.begin();
			String sql_STATEMENT = "select a.district_code,  a.district_name from mst_district as a,mst_state as b where a.state_code=b.state_code and upper(b.state_name)='"+entityCode+"' order by a.district_name ";
			
			Query query = session.createSQLQuery(sql_STATEMENT);
			/*query.setParameter("entityCode", entityCode);*/
			districtNameList = query.list();
			System.out.println("...............district names..."+districtNameList);
						
			tr.commit();
			} catch (Exception e) {
					e.printStackTrace();
			} finally {
					session.close();
			}
				return districtNameList;
			}
	public List getDistrictListByStateCode( String entityCode) {

		List districtNameList = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
				
		try {
			tr.begin();
			String sql_STATEMENT = "select a.district_code,  a.district_name from mst_district as a,mst_state as b where a.state_code=b.state_code and b.state_code='"+entityCode+"' order by a.district_name ";
			
			Query query = session.createSQLQuery(sql_STATEMENT);
			/*query.setParameter("entityCode", entityCode);*/
			districtNameList = query.list();
			System.out.println("...............district names..."+districtNameList);
						
			tr.commit();
			} catch (Exception e) {
					e.printStackTrace();
			} finally {
					session.close();
			}
				return districtNameList;
			}
	
	/*//.3.to get the state name
		public String getState(String stateCode) {
			
				Persister persister = PersisterImpl.getPersister();
				Session session = persister.getSession();
				Transaction tr = session.getTransaction();
				String statename="";
				List<String> entryStatusDetailList = new ArrayList<String>();
				String SQL_Query = null;
				SQL_Query = "select upper(state_name) as state_name from mst_state where state_code=:stateCode";
				try {
					tr.begin();
					Query Query = session.createSQLQuery(SQL_Query);
					Query.setParameter("stateCode", stateCode);
					entryStatusDetailList = Query.list();
					tr.commit();
					statename =  entryStatusDetailList.get(0).toString();
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				return statename;
			}
	*/
		
	
		public List<DistrictTargetVO> getTradeTargetDetails(String projectID){
			List<DistrictTargetVO>  tradeTarget=new ArrayList<DistrictTargetVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
	            Criteria criteria = session.createCriteria(DistrictTargetVO.class)
	                    .add(Restrictions.eq("projectId.id", projectID));

	            // Convenience method to return a single instance that matches the
	            // query, or null if the query returns no results.
	            Object result = criteria.list();
	            
	            	tradeTarget= (List<DistrictTargetVO>) result;
	           
	        }
			catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}finally {
	            session.close();
	        }
			return tradeTarget;
		}
			
	//..to get the is special feild
	public List getSpecial(String district_code) throws Exception {
		
		System.out.println("....district_name..."+district_code);
		String sQL = null;
		List IsSpecialList = new ArrayList();
		
		sQL = "select a.special_area from mst_district as a WHERE a.district_id='"+district_code+"'";
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			//query.setParameter("sector_code", sectorId);
			IsSpecialList = query.list();
			if(IsSpecialList==null||IsSpecialList.size()==0||IsSpecialList.get(0)==null){
				IsSpecialList.clear();
				IsSpecialList.add("NO");
			}
			System.out.println("...IsSpecialList.."+IsSpecialList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return IsSpecialList;
	}
	
public int getSanctionTarget(String project_id) throws Exception {
		
		System.out.println("....projectid..."+project_id);
		String sQL = null;
		int target=0;
		
		/*sQL = "select santarget from user_sanction_details WHERE projectid='"+project_id+"'";*/
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			ProjectDetailsVO project=new ProjectDetailsVO();
			project.setId(project_id);
			Criteria criteria = session.createCriteria(SanctionDetailVO.class).add(Restrictions.eq("projectId.id", project_id));
			
			List<SanctionDetailVO> objectList=(List<SanctionDetailVO>)criteria.list();
			System.out.println(objectList);
			if(objectList!=null&& objectList.size()>=1){
				SanctionDetailVO object=objectList.get(0);
				target=object.getSanTarget();
			}
			System.out.println("Target is --> "+target);
			/*Query query = session.createSQLQuery(sQL);
			//query.setParameter("sector_code", sectorId);
			Target = query.list();
			System.out.println("...Target.."+Target);*/
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return target;
	}


	public boolean save(DistrictTargetActionForm districtTargetActionForm, LoginVO loginVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			DistrictTargetVO districtTargetVO=new DistrictTargetVO();
			System.out.println("districtTargetActionForm.getProjectId() --> "+districtTargetActionForm.getProjectId());
			for(int i=0;i<districtTargetActionForm.getDistrict().length;i++)
			{
				
				districtTargetVO.setDistrict(districtTargetActionForm.getDistrict()[i]);
				districtTargetVO.setTrainingTargetDist(districtTargetActionForm.getTrainingTargetDist()[i]);
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(districtTargetActionForm.getProjectId());
				districtTargetVO.setProjectId(project);
				districtTargetVO.setCreatedBy(loginVO.getUserid());        //--------------------------------------------------------------------------update after user module
				districtTargetVO.setCreatedOnDate(new java.util.Date());
				System.out.println("Object To save --> "+districtTargetVO);
				session.save(districtTargetVO);	
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

	
	
	public boolean update(DistrictTargetActionForm helperForm) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<helperForm.getDistrict().length;i++)
			{
				DistrictTargetVO helperVo=(DistrictTargetVO) session.get(DistrictTargetVO.class, helperForm.getId()[i]);
				
				System.out.println("district id===>  "+helperForm.getId()[i]);
				if(helperVo==null){
					helperVo=new DistrictTargetVO();
					helperVo.setCreatedBy("");       
					helperVo.setCreatedOnDate(new java.util.Date());
				}else{
					helperVo.setUpdatedBy("");
					helperVo.setUpdatedOnDate(new java.util.Date());
				}
		//		helperVo.setStateName(helperForm.getStateName());
				//...for saving projectId ...
		//		helperVo.setEntityCode(helperForm.getEntityCode());
				ProjectDetailsVO project=new ProjectDetailsVO();
				project.setId(helperForm.getProjectId());
				helperVo.setProjectId(project);
				
				helperVo.setDistrict(helperForm.getDistrict()[i]);
			
				helperVo.setTrainingTargetDist(helperForm.getTrainingTargetDist()[i]);
		
				
				
			    session.saveOrUpdate(helperVo);
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
	public boolean delete(List<DistrictTargetVO> projectVo) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(DistrictTargetVO objectToDelete:projectVo){
				session.delete(objectToDelete);
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
	public List getSanctionCheck(String id, String target) throws HibernateException, Exception {
		
		
		String sQL = null;
	 
		List sanctionList = new ArrayList();
		
		//sQL = "select a.whether_spcial_area from mst_district as a WHERE a.district_code='"+district_code+"'";
		
		sQL=" select cast(usd.sanorder as integer)as total_sanction_target,cast(coalesce(sum(udt.trainingtargetdist ),0) as integer)as sanction_target from "
				+ "user_sanction_details usd left join  user_district_target udt on usd.projectid = udt.projectid and "
				+ "usd.projectid = '"+id+"' group by usd.sanorder";
				 
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(sQL);
			//query.setParameter("sector_code", sectorId);
			sanctionList = query.list();
			System.out.println("...IsSpecialList.."+sanctionList);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return sanctionList;
	}
	

		
	/*	public ArrayList getStateNameForUse(String projectid) throws Exception {
			
			System.out.println("....projectid..."+projectid);
			String sQL = null;
			ArrayList statenAME = new ArrayList();
			
			sQL = "	select state_name from user_district_target WHERE projectid='"+projectid+"'";
			
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Query query = session.createSQLQuery(sQL);
				//query.setParameter("sector_code", sectorId);
				statenAME = (ArrayList) query.list();
				System.out.println("...statenAME.."+statenAME);
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
					persister.closeSession();
			}
			return statenAME;
		}*/
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
	
	
	
	public boolean deleteById(List<String> ids) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for(String id:ids){
				DistrictTargetVO helperVo=(DistrictTargetVO) session.load(DistrictTargetVO.class, id);
			session.delete(helperVo);
			session.flush();
			}
			tx.commit();
			//
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	public int viewList( String projectid) throws HibernateException, Exception {
		int  tradeTarget=0;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(DistrictTargetVO.class)
                    .add(Restrictions.eq("projectId.id", projectid));
            tradeTarget= criteria.list().size();
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		System.out.println("Data count is --> "+tradeTarget);
		return tradeTarget;
}
	
	public Object getDropDownList(Class<?> abc){
		Object result=null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try{
		Criteria cr = session.createCriteria(abc);

		result=cr.list();
		
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return result;
	  }
	
	
	
	
	
	
	
	}
	
	

