package com.infotech.sgsy.master.grampanchayatCorrection;

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

/**
 * @author NIC 
 * @since July, 2013
 */
public class GrampanchayatCorrectionDAO {
	Log log = LogFactory.getLog(getClass());
	public List<GrampanchayatCorrectionVO> getGrampanchayatList(String blockCode) {
		String query_hql = "from com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionVO as vo " +
				" where vo.blockCode = :blockCode order by vo.gramPanchayatName";
		List<GrampanchayatCorrectionVO> list = new ArrayList<GrampanchayatCorrectionVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			Query query= session.createQuery(query_hql);
			query.setParameter("blockCode",blockCode);
			list = query.list();
			}
		catch(Exception e) {
			log.error("Exception in GrampanchayatCorrectionDAO.getGrampanchayatList(). " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();	
			}		
		}
		return list;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public boolean insert(GrampanchayatCorrectionVO vo) {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.saveOrUpdate(vo);
			tr.commit();
			flag = true;
		}
		catch(Exception e) {
			log.error("Exception in GrampanchayatCorrectionDAO.insert(). " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();	
			}	
		}
		return flag;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public Long getMaxCode(String blockCode) {
		String query_sql = "select max(grampanchayat.grampanchayat_code) from mst_grampanchayat_modification as grampanchayat " +
				" where grampanchayat.block_code=:blockCode";
		Persister persister = PersisterImpl.getPersister();
    	Session session = persister.getSession();
    	Long maxCode = 0L;
    	try {
    		Query query = session.createSQLQuery(query_sql);
    		query.setString("blockCode", blockCode);
    		maxCode = new Long((query).list().get(0).toString());  		
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
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public boolean checkUnique(String blockCode, String grampanchayatName) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		String query_sql = "select * from  mst_grampanchayat_modification as grampanchayat " +
				" where grampanchayat.block_code = ? and upper(grampanchayat.grampanchayat_name) =  ? ";
		boolean flag = false;
		try {
			Query query = session.createSQLQuery(query_sql);
			query.setParameter(0, blockCode);
			query.setParameter(1, grampanchayatName);
			List<BlockMasterVO> grampanchayatList =(query).list();
			if(grampanchayatList.isEmpty()) {
				flag = true;
			}
		}
		catch(Exception e) {
			log.error("Exception checking unique name for Block. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();	
			}			
		}
		return flag;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public boolean deleteGrampanchayatsUnderBlock(String blockCode) {
		log.info("Deleting grampanchayats under block-" + blockCode);
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String query_sql = "delete from mst_grampanchayat_modification as grampanchayat " +
				" where grampanchayat.block_code = :blockCode";
		try {
			tr.begin();
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("blockCode", blockCode);
			int row = query.executeUpdate();
			flag = true;
			tr.commit();
		}
		catch(Exception e) {
			log.error("Exception deleting Grampanchayats. " + e.getMessage());
		}
		finally{
			if(session.isOpen())
				session.close();	
		}
		return flag;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public boolean isGrampanchayatDeleted(String grampanchayatCode) {
		boolean flag = false;
		String query_hql = "from com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionVO as vo " +
				" where vo.gramPanchayatCode = :grampanchayatCode and flag = 'D'";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createQuery(query_hql);
			query.setParameter("grampanchayatCode",grampanchayatCode );
			List<GrampanchayatCorrectionVO> grampanchayatCorrectionList = (List<GrampanchayatCorrectionVO>)(query).list();
			if(!grampanchayatCorrectionList.isEmpty()) {
				flag = true;
			}
			tr.commit();
		}
		catch(Exception e) {
			log.error("Exception while checking if a grampanchayat has been deleted. " + e.getMessage());
		}
		finally{
			if(session.isOpen())
				session.close();						
		}
		return flag;
	}
}
