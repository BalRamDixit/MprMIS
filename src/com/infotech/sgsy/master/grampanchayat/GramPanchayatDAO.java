package com.infotech.sgsy.master.grampanchayat;

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
import com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionVO;
import com.infotech.sgsy.master.state.StateDAO;

/**
 * @author NIC 
 * @since July, 2013
 */
public class GramPanchayatDAO {
	Log log = LogFactory.getLog(getClass());
	public List<GramPanchayatVO> getGramPanchayatList(String blockCode) {
		List<GramPanchayatVO> gramPanchayatList = new ArrayList<GramPanchayatVO>();
		String query_hql = "select vo.gramPanchayatCode, vo.gramPanchayatName from com.infotech.sgsy.master.grampanchayat.GramPanchayatVO as vo" +
				" where vo.blockCode = :blockCode order by vo.gramPanchayatName";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			Query query= session.createQuery(query_hql);
			query.setParameter("blockCode",blockCode);
			List<Object[]> list = query.list();
			for(Object[] o : list) {
				GramPanchayatVO vo = new GramPanchayatVO();
				vo.setGramPanchayatCode(o[0].toString());
				vo.setGramPanchayatName(o[1].toString());
				gramPanchayatList.add(vo);
				}
			}
		catch(Exception e) {
			log.error("Error getting GramPanchayat list. " + e.getMessage());
		}
		finally
		{
			if(session.isOpen())
				session.close();
		}
		return gramPanchayatList;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public GramPanchayatVO getGrampanchayat(String grampanchayatCode) {
		GramPanchayatVO grampanchayat = new GramPanchayatVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			grampanchayat = (GramPanchayatVO)session.get(GramPanchayatVO.class, grampanchayatCode);
		}
		catch(Exception e) {
			log.error("Error in GramPanchayatDAO.getGrampanchayat(). " + e.getMessage());
		}finally{		
		}
		return grampanchayat;
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
			session.save(vo);
			tr.commit();
			flag = true;
		}
		catch(Exception e) {
			log.error("Error in GramPanchayatDAO.insert(). " + e.getMessage());
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
	public Long getMaxCode(String blockCode) {
		String query_sql = "select max(grampanchayat.grampanchayat_code) from mst_grampanchayat as grampanchayat where grampanchayat.block_code= :blockCode ";
		Persister persister = PersisterImpl.getPersister();
    	Session session = persister.getSession();
    	Long maxCode = 0L;
    	try {
    		Query query= session.createSQLQuery(query_sql);
			query.setParameter("blockCode", blockCode);
	        maxCode = new Long((query).list().get(0).toString());
    		}
    	catch(Exception e) {
    		log.error("Unable to get Max Block Code. " + e.getMessage());
    	}
    	finally{
			if(session.isOpen())
				session.close();
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
		String query_sql = "select * from  mst_grampanchayat as grampanchayat where grampanchayat.block_code = ? and upper(grampanchayat.grampanchayat_name) =? ";
		boolean flag = false;
		try {
			Query query= session.createSQLQuery(query_sql);
			query.setParameter(0,blockCode);
			query.setParameter(1,grampanchayatName);
			List<GramPanchayatVO> grampanchayatList =query.list();
			if(grampanchayatList.isEmpty()) {
				flag = true;
			}
		}
		catch(Exception e) {
			log.error("Error checking unique name for Block. " + e.getMessage());
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
			String query_sql = "select count(*) from "+stateShortName.toLowerCase()+"_shg_detail where substring(entity_code,1,10) = :Code";
			tr.begin();
			Query query = session.createSQLQuery(query_sql);
			List rowCount = query.list();
			if (rowCount != null && !rowCount.isEmpty() && Integer.parseInt(rowCount.get(0).toString()) > 0) {
				flag = true;
			}
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
}
