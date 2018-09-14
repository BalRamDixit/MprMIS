package com.infotech.sgsy.master.village;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.PropertyModel;

public class VillageDAO implements MasterDAO {
	Log log = LogFactory.getLog(getClass());
	
	
	/*
	 * Function used to collect the village list
	 */
	public static List getVillageList(String entityCode) throws Exception{
		List results=new ArrayList();
		Session session=null;
		String query="";
		 		
		List finalList =  new ArrayList();
		PropertyModel propertyModel=new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("SELECT");
		finalList.add(propertyModel);
		Persister persister=PersisterImpl.getPersister();
		
		Connection con = null;
	  	PreparedStatement stmt = null;
	  	ResultSet rs=null;
	  
		try{
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			
			session=persister.getSession();						
			query=" select  distinct v.village_code,trim(g.grampanchayat_name)||'--'||trim(v.village_name) as village_name  from mst_village v,mst_grampanchayat g where v.block_code=? and g.block_code=? "+ 
			"and  length(v.village_code)=13 and v.grampanchayat_code=g.grampanchayat_code order by  village_name";
		
			stmt = con.prepareStatement(query);
			stmt.setString(1, entityCode);
			stmt.setString(2, entityCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("village_code"));
				propertyModel.setPropertyValue(rs.getString("village_name"));				
				finalList.add(propertyModel);				
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
				CommonUtils.closeDatabaseUtil(stmt, con, rs);
			}

		return finalList;
	}
	
	public static List getVillageListWithoutSelect(String entityCode) throws Exception{
		List results=new ArrayList();
		Session session=null;
		String query="";
		 		
		List finalList =  new ArrayList();
		PropertyModel propertyModel=new PropertyModel();
		Persister persister=PersisterImpl.getPersister();
		
		Connection con = null;
	  	PreparedStatement stmt = null;
	  	ResultSet rs=null;
	  
		try{
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			
			session=persister.getSession();						
			query=" select  distinct v.village_code,trim(g.grampanchayat_name)||'--'||trim(v.village_name) as village_name  from mst_village v,mst_grampanchayat g where v.block_code=? and g.block_code=? "+ 
			"and  length(v.village_code)=13 and v.grampanchayat_code=g.grampanchayat_code order by  village_name";
		
			stmt = con.prepareStatement(query);
			stmt.setString(1, entityCode);
			stmt.setString(2, entityCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("village_code"));
				propertyModel.setPropertyValue(rs.getString("village_name"));				
				finalList.add(propertyModel);				
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
				CommonUtils.closeDatabaseUtil(stmt, con, rs);
			}

		return finalList;
	}
	
	public static List getVillageListForBank(String entityCode) throws Exception{
		List results=new ArrayList();
		Session session=null;
		String query="";
		 
		
		List finalList =  new ArrayList();
		PropertyModel propertyModel=new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		Persister persister=PersisterImpl.getPersister();
		
		Connection con = null;
	  	PreparedStatement stmt = null;
	  	ResultSet rs=null;
	  
		try
		{
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			
			session=persister.getSession();
			/*query=" select distinct bb from com.infotech.sgsy.master.village.VillageVO as bb where bb.blockCode='"+entityCode+"' " +
					" and  length(bb.villageCode)=13 order by bb.villageName  ";
			*/
			/*query=" select  distinct village_code,village_name  from mst_village where block_code=? " +
			" and  length(village_code)=13 order by  village_name ";
			*/
			
			query=" select  distinct v.village_code,trim(g.grampanchayat_name)||'--'||trim(v.village_name) as village_name  from mst_village v,mst_grampanchayat g where v.block_code=? and g.block_code=? "+ 
			"and  length(v.village_code)=13 and v.grampanchayat_code=g.grampanchayat_code order by  village_name";
			
			/*session.beginTransaction();
			results=session.createQuery(query).list();
			for(int x=0;x<results.size();x++){				
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(((VillageVO)results.get(x)).getVillageCode());
				propertyModel.setPropertyValue(((VillageVO)results.get(x)).getVillageName());				
				finalList.add(propertyModel);
			}	*/
			stmt = con.prepareStatement(query);
			stmt.setString(1, entityCode);
			stmt.setString(2, entityCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("village_code"));
				propertyModel.setPropertyValue(rs.getString("village_name"));				
				finalList.add(propertyModel);
				
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
				CommonUtils.closeDatabaseUtil(stmt, con, rs);
			}

		return finalList;
	}
	
	public int delete(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int update(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*public static List getVillageName(String villageId)
	{
		Persister persister=PersisterImpl.getPersister();
		Session sess=persister.getSession();
		Transaction transaction =sess.getTransaction();
		transaction.begin();
		List result=null;
		Criteria criteria = null;
	
		
		try{
			criteria=sess.createCriteria("com.infotech.sgsy.master.village.VillageVO").add(Restrictions.eq("villageCode", villageId));
			result=criteria.list();
			transaction.commit();
		}catch (Exception e) {
			transaction.rollback();
		}
		finally
		{
			if(sess.isOpen())
			{
				sess.close();
			}
		}


		return result;
	}*/
	public static List getVillageName(String villageId)
	{
		List results=new ArrayList();
		Session session=null;
		String query="";
		 
		
		List finalList =  new ArrayList();
		VillageVO propertyModel=new VillageVO();
		 
		Persister persister=PersisterImpl.getPersister();
		
		Connection con = null;
	  	PreparedStatement stmt = null;
	  	ResultSet rs=null;
	  
		try
		{
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			
			session=persister.getSession();
			/*query=" select distinct bb from com.infotech.sgsy.master.village.VillageVO as bb where bb.blockCode='"+entityCode+"' " +
					" and  length(bb.villageCode)=13 order by bb.villageName  ";
			*/
			/*query=" select  distinct village_code,village_name  from mst_village where block_code=? " +
			" and  length(village_code)=13 order by  village_name ";
			*/
			
			query=" select  distinct v.village_code,trim(g.grampanchayat_name)||'--'||trim(v.village_name) as village_name, v.state_Code as state_code, v.district_Code as district_code, v.block_Code as block_code, v.gramPanchayat_Code as gram_panchayat_code, v.rural_urban_area as rural_urban_area, v.created_on as created_On, v.created_By as created_by, v.modified_On as last_modified_on, v.Modifed_By as last_modifed_by  from mst_village v,mst_grampanchayat g where v.block_code=g.block_code  "+ 
			"and  length(v.village_code)=13 and v.village_code=? and v.grampanchayat_code=g.grampanchayat_code order by  village_name";
			 
			stmt = con.prepareStatement(query);
			System.out.println(query + villageId);
			stmt.setString(1, villageId);
	  
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel=new VillageVO();
				propertyModel.setVillageCode(rs.getString("village_code"));
				propertyModel.setVillageName(rs.getString("village_name"));
				System.out.println(propertyModel.getVillageName());
				propertyModel.setStateCode(rs.getString("state_code"));
				propertyModel.setDistrictCode(rs.getString("district_code"));
				propertyModel.setBlockCode(rs.getString("block_code"));
				propertyModel.setGramPanchayatCode(rs.getString("gram_panchayat_code"));
				propertyModel.setRuralUrbanArea(rs.getString("rural_urban_area"));
				propertyModel.setCreatedOn(rs.getDate("created_on"));
				propertyModel.setCreatedBy(rs.getString("created_by"));
				propertyModel.setLastModifedBy(rs.getString("last_modified_on"));
				propertyModel.setLastModifiedOn(rs.getDate("last_modifed_by"));
				finalList.add(propertyModel);
				
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
				try {
					CommonUtils.closeDatabaseUtil(stmt, con, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return finalList;
	}
	
	
	
	public static List getVillageListByBranch(String entityCode) throws Exception
	{
		List results=new ArrayList();
		Session session=null;
		String query="";
		 
		
		List finalList =  new ArrayList();
		PropertyModel propertyModel=new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		Persister persister=PersisterImpl.getPersister();
		
		Connection con = null;
	  	PreparedStatement stmt = null;
	  	ResultSet rs=null;
	  
		try
		{
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			
			session=persister.getSession();
			/*query=" select distinct bb from com.infotech.sgsy.master.village.VillageVO as bb where bb.blockCode='"+entityCode+"' " +
					" and  length(bb.villageCode)=13 order by bb.villageName  ";
			*/
			/*query=" select  distinct village_code,village_name  from mst_village where block_code=? " +
			" and  length(village_code)=13 order by  village_name ";
			*/
			
			query=" select  distinct v.village_code,trim(g.grampanchayat_name)||'--'||trim(v.village_name) as village_name  from mst_village v,mst_grampanchayat g where v.block_code=? and g.block_code=? "+ 
			"and  length(v.village_code)=13 and v.grampanchayat_code=g.grampanchayat_code order by  village_name";
			
			/*session.beginTransaction();
			results=session.createQuery(query).list();
			for(int x=0;x<results.size();x++){				
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(((VillageVO)results.get(x)).getVillageCode());
				propertyModel.setPropertyValue(((VillageVO)results.get(x)).getVillageName());				
				finalList.add(propertyModel);
			}	*/
			stmt = con.prepareStatement(query);
			stmt.setString(1, entityCode);
			stmt.setString(2, entityCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(rs.getString("village_code"));
				propertyModel.setPropertyValue(rs.getString("village_name"));				
				finalList.add(propertyModel);
				
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
				CommonUtils.closeDatabaseUtil(stmt, con, rs);
			}

		return finalList;
	}
	
	public List<VillageVO> getVillageListFromGrampanchayatCode(String grampanchayatCode) {
		List<VillageVO> villageList = new ArrayList<VillageVO>();
		String query_hql = "select vo.villageCode, vo.villageName from com.infotech.sgsy.master.village.VillageVO as vo where vo.gramPanchayatCode = :grampanchayatCode";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			Query query = session.createQuery(query_hql);
			query.setParameter("grampanchayatCode",grampanchayatCode);
			List<Object[]> list = query.list();
			for(Object[] o : list) {
				VillageVO vo = new VillageVO();
				vo.setVillageCode(o[0].toString());
				vo.setVillageName(o[1].toString());
				villageList.add(vo);
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return villageList;
	}
	
	public Long getMaxCode(String grampanchayatCode) {
		String query_sql = "select max(village.village_code) from mst_village as village where village.grampanchayat_code= :grampanchayatCode";
		Persister persister = PersisterImpl.getPersister();
    	Session session = persister.getSession();
    	Long maxCode = 0L;
    	try {
    		Query query = session.createSQLQuery(query_sql);
    		query.setParameter("grampanchayatCode", grampanchayatCode);
    		List list = (query).list();
    		if(list.size()>0) {
    			maxCode = new Long(list.get(0).toString());
    		}
    	}
    	catch(Exception e) {
    		log.error("Unable to get Max Village Code. " + e.getMessage());
    	}
    	finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
    	return maxCode;
	}
	
	public boolean checkUnique(String grampanchayatCode, String villageName) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		String query_sql = "select * from  mst_village as village where village.grampanchayat_code = ? and upper(village.village_name) = ? ";
		boolean flag = false;
		try {
			Query query = session.createSQLQuery(query_sql);
    		query.setParameter(0, grampanchayatCode);
    		query.setParameter(1, villageName);
			List<VillageVO> villageList = (query).list();
			if(villageList.isEmpty()) {
				flag = true;
			}
		}
		catch(Exception e) {
			log.error("Error checking unique name for Block. " + e.getMessage());
		}
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return flag;
	}

	public boolean shgDataExistance(String Code) {
		log.info("Checking whether SHG Data Existing against this code" + Code);
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			StateDAO stdao=new StateDAO();
			String stCode= Code.substring(0, 2);
			String stateShortName=stdao.getStateShortNameByCode(stCode);
			String query_sql = "select count(*) from "+stateShortName.toLowerCase()+"_shg_detail where entity_code = :Code";
			tr.begin();
			Query query= session.createSQLQuery(query_sql);
			query.setParameter("Code", Code);
			List rowCount = (query).list();
			if (rowCount != null && !rowCount.isEmpty() && Integer.parseInt(rowCount.get(0).toString()) > 0) {
				flag = true;
			}
			tr.commit();
		}
		catch(Exception e) {
			log.error("Exception deleting Grampanchayats. " + e.getMessage());
		}
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return flag;
	}

	public List<VillageVO> getVillageListForDistrictCode(String districtCode) {
		List<VillageVO> villageList = new ArrayList<VillageVO>();
		//String query = "select vo.block_code, vo.grampanchayat_code, vo.village_code, vo.village_name from mst_village as vo where vo.district_code = '"+districtCode+"' order by vo.block_code";
		String query_sql="select vo.block_code,b.block_name,vo.grampanchayat_code,g.grampanchayat_name,vo.village_code, vo.village_name from mst_village as vo,mst_block as b,mst_grampanchayat as g where vo.district_code = :districtCode and b.block_code=vo.block_code and g.grampanchayat_code=vo.grampanchayat_code order by b.block_name,g.grampanchayat_name";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("districtCode", districtCode);
			List<Object[]> list = (query).list();
			for(Object[] o : list) {
				VillageVO vo = new VillageVO();
				vo.setBlockCode(o[0].toString());
				vo.setBlockName(o[1].toString());
				vo.setGramPanchayatCode(o[2].toString());
				vo.setGramPanchayatName(o[3].toString());
				vo.setVillageCode(o[4].toString());
				vo.setVillageName(o[5].toString());
				villageList.add(vo);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return villageList;
	}
	

	
}
