package com.infotech.sgsy.master.villageCorrection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.block.BlockMasterVO;

public class VillageCorrectionDAO {
	Log log = LogFactory.getLog(getClass());
	public List<VillageCorrectionVO> getVillageList(String grampanchayatCode) {
		String query_hql = "from com.infotech.sgsy.master.villageCorrection.VillageCorrectionVO as vo " +
				" where vo.gramPanchayatCode = :grampanchayatCode order by vo.villageName";
		List<VillageCorrectionVO> villageCorrectionList = new ArrayList<VillageCorrectionVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			Query query = session.createQuery(query_hql);
			query.setParameter("grampanchayatCode", "grampanchayatCode");
			villageCorrectionList = query.list();			
		}
		catch(Exception e) {
			log.error("Unable to retrieve village correction list. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return villageCorrectionList;
	}
	
	public boolean insert(VillageCorrectionVO vo) {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(vo);
			tr.commit();
			flag = true;
		}
		catch(Exception e) {
			log.error("Error in inserting records. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return flag;
	}
	
	public Long getMaxCode(String grampanchayatCode) {
		String query_sql = "select max(village.village_code) from mst_village_modification as village where village.grampanchayat_code= :grampanchayatCode";
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
    		log.error("Unable to get Max Block Code. " + e.getMessage());
    	}
    	finally{
			if(session.isOpen()){
				session.close();
			}
		}
    	return maxCode;
	}
	
	public boolean checkUnique(String grampanchayatCode, String villageName) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		String query_sql = "select * from  mst_village_modification as village " +
				" where village.grampanchayat_code = ? and upper(village.village_name) = ?";
		boolean flag = false;
		try {
			Query query = session.createSQLQuery(query_sql);
			query.setParameter(0, grampanchayatCode);
			query.setParameter(1, villageName);
			List<BlockMasterVO> villageList =(query).list();
			if(villageList.isEmpty()) {
				flag = true;
			}
		}
		catch(Exception e) {
			log.error("Error checking unique name for Block. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return flag;
	}
	
	public boolean deleteVillagesUnderGrampanchayat(String grampanchayatCode) {
		log.info("Deleting villages under grampanchayat-" + grampanchayatCode);
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String query_sql = "delete from mst_village_modification as village where village.grampanchayat_code= :grampanchayatCode";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("grampanchayatCode", grampanchayatCode);
		    int row = query.executeUpdate();
			flag = true;
			tr.commit();
		}
		catch(Exception e){
			log.error("Error deleteing villages for block. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return flag;
	}
	
	public boolean deleteVillagesUnderBlock(String blockCode) {
		log.info("Deleting villages under block-" + blockCode);
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String query_sql="delete from mst_village_modification as village where village.block_code= :blockCode";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("blockCode", blockCode);
			int row = query.executeUpdate();
			flag = true;
			tr.commit();
		}
		catch(Exception e){
			log.error("Error deleteing villages for block. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return flag;
	}
}
