
package com.infotech.sgsy.master.blockCorrection;

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

public class BlockCorrectionDAO {
	Log log = LogFactory.getLog(getClass());
	public List<BlockCorrectionVO> getBlockList(String districtCode) {
		String query_hql = "from com.infotech.sgsy.master.blockCorrection.BlockCorrectionVO as vo where vo.districtCode = :districtCode order by vo.blockName";
		List<BlockCorrectionVO> list = new ArrayList<BlockCorrectionVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			Query query= session.createQuery(query_hql);
			query.setParameter("districtCode",districtCode);
			list = query.list();
		}
		catch(Exception e) {
			log.error("Exception in BlockCorrectionDAO.getBlockList(). " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return list;
	}
	
	public boolean insert(BlockCorrectionVO vo) {
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
			log.error("Exception in BlockCorrectionDAO.insert(). " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return flag;
	}
	
	public Long getMaxCode(String districtCode) {
		String query_sql = "select max(block.block_code) from mst_block_modification as block where block.district_code=:districtCode ";
		Persister persister = PersisterImpl.getPersister();
    	Session session = persister.getSession();
    	Long maxCode = 0L;
    	try {
    		Query query= session.createSQLQuery(query_sql);
    		query.setParameter("districtCode", districtCode);
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
	
	public boolean checkUnique(String districtCode, String blockName) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		String query_sql = "select * from  mst_block_modification as block where block.district_code = ? and upper(block.block_name)= ? ";
		boolean flag = false;
		try {
			Query query = session.createSQLQuery(query_sql);
			query.setParameter(0, districtCode);
			query.setParameter(1, blockName);
			List<BlockCorrectionVO> blockList = (List<BlockCorrectionVO>)query.list();
			if(blockList.isEmpty()) {
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
	
	public boolean isBlockDeleted(String blockCode) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		String query_hql = "from com.infotech.sgsy.master.blockCorrection.BlockCorrectionVO as vo where vo.blockCode = :blockCode and vo.flag = 'D'";
		boolean flag = false;
		try {
			Query query = session.createQuery(query_hql);
			query.setParameter("blockCode", blockCode);
			List<BlockMasterVO> blockList =(query).list();
			if(!blockList.isEmpty()) {
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
}
