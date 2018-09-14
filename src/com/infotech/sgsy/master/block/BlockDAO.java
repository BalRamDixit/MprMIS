package com.infotech.sgsy.master.block;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAO;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.PropertyModel;

public class BlockDAO implements MasterDAO {

	protected final Log log = LogFactory.getLog(getClass());
	
	public List getBlock(LocationVO locationVO) throws Exception {

		List results = null;
		String query = " from com.infotech.sgsy.master.block.BlockMasterVO";
		List finalList = new ArrayList();
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			results = (List) persister.getObjectsByQuery(query);
			for (int x = 0; x < results.size(); x++) {
				propertyModel = new PropertyModel();
				propertyModel.setPropertyCode(((BlockMasterVO) results.get(x)).getBlockName());
				finalList.add(propertyModel);
			}
			transaction.commit();
			
		} catch (Exception e) {
			log.error("error occured while getting block list" + e);
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

	public List getBlocksList(LocationVO locationVO) throws Exception {
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String result = null;
		BlockMasterVO propertyModel = new BlockMasterVO();
		List finalList = new ArrayList();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = " SELECT block_name, block_code FROM mst_block where district_code=?"
				+ " ORDER BY block_name";

			stmt = con.prepareStatement(query);
			stmt.setString(1, locationVO.getDistrictCode());
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new BlockMasterVO();
				propertyModel.setBlockName(rs.getString("block_name"));
				propertyModel.setBlockCode(rs.getString("block_code"));
				finalList.add(propertyModel);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close(); 
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}
		return finalList;
	}
	

	/**
	 * @author cvas2273
	 * @param locationVO
	 * @return blockList based on state and district
	 * @throws Exception
	 */
	/*
	 * public List getBlocksListByStateAndDistrict(String stateCode,String
	 * districtCode) throws Exception{
	 * 
	 * List results = null;
	 * 
	 * List blockList = new ArrayList();
	 * 
	 * Persister persister = PersisterImpl.getPersister();
	 * 
	 * Session session=null;
	 * 
	 * try{ session=persister.getSession();
	 * 
	 * Transaction transaction = session.getTransaction();
	 * 
	 * transaction.begin();
	 * 
	 * Criteria criteria=session.createCriteria(
	 * "com.infotech.sgsy.master.block.BlockMasterVO");
	 * 
	 * criteria.add(Restrictions.eq("stateCode", stateCode));
	 * 
	 * criteria.add(Restrictions.eq("districtCode", districtCode));
	 * 
	 * criteria.addOrder(Order.asc("blockName"));
	 * 
	 * results=criteria.list();
	 * 
	 * for(int x=0;x<results.size();x++){
	 * 
	 * PropertyModel propertyModel=new PropertyModel();
	 * 
	 * propertyModel.setPropertyCode(((BlockMasterVO)results.get(x)).getBlockCode
	 * ());
	 * 
	 * propertyModel.setPropertyValue(((BlockMasterVO)results.get(x)).getBlockName
	 * ());
	 * 
	 * blockList.add(propertyModel); }
	 * 
	 * session.flush();
	 * 
	 * transaction.commit();
	 * 
	 * 
	 * }catch (Exception e) {
	 * 
	 * log.info("Error while getting blockList :--> " + e.getMessage());
	 * 
	 * 
	 * }finally{ if(session.isOpen()) session.close(); } return blockList;
	 * 
	 * }
	 */

	public List getBlocksListByStateAndDistrict(String stateCode,
			String districtCode) throws Exception {
		
		Connection con = null;
		String query = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List result = null;
		BlockMasterVO propertyModel = new BlockMasterVO();
		List finalList = new ArrayList();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			query = " SELECT block_name,block_code FROM "
					+ " mst_block where state_code= ?" 
					+ " and district_code= ?" 
					+ " ORDER BY block_name";

			stmt = con.prepareStatement(query);
			stmt.setString(1, stateCode);
			stmt.setString(2, districtCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				propertyModel = new BlockMasterVO();
				propertyModel.setBlockName(rs.getString("block_name"));
				propertyModel.setBlockCode(rs.getString("block_code"));
				finalList.add(propertyModel);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			CommonUtils.closeDatabaseUtil(stmt, con, rs);
		}
		return finalList;
	}
	public List<BlockMasterVO> getBlockByDistrictCode(String districtCode) {
		List<BlockMasterVO> blockList = new ArrayList<BlockMasterVO>();
		String query_sql = "select block_name, block_code, COALESCE(intensive,'') from mst_block where district_code = :districtCode";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query= session.createSQLQuery(query_sql);
			query.setParameter("districtCode",districtCode);
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				BlockMasterVO block = new BlockMasterVO();
				block.setBlockName(arr[0].toString());
				block.setBlockCode(arr[1].toString());
				block.setIntensive(arr[2].toString());
				/*
				 * if(arr[2].toString().equalsIgnoreCase("true"))
				 * block.setIntensive(true); else block.setIntensive(false);
				 */
				blockList.add(block);
			}
			tr.commit();
		} catch (Exception e) {
			log.error(e.getMessage());
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return blockList;
	}

	// By Divesh
	public BlockMasterVO getBlock(String blockCode) {
		BlockMasterVO block = new BlockMasterVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		try {
			block = (BlockMasterVO) session.get(BlockMasterVO.class, blockCode);
			// session.close();
		} catch (Exception e) {
			log.error("Error in BlockDAO.getBLock(). " + e.getMessage());
		}
		return block;
	}


	public Long getMaxCode(String districtCode) {
		String query_sql = "select max(block.block_code) from mst_block as block where block.district_code= :districtCode";
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Long maxCode = 0L;
		try {
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("districtCode",districtCode);
			List list =query.list();
			if (list.size() > 0) {
				maxCode = new Long(list.get(0).toString());
			}
		} catch (Exception e) {
			log.error("Unable to get Max Block Code. " + e.getMessage());
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return maxCode;
	}

	public boolean checkUnique(String districtCode, String blockName) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		String query_sql = "select * from  mst_block as block where block.district_code = ? and upper(block.block_name)= ? ";
		boolean flag = false;
		try {
			Query query = session.createSQLQuery(query_sql);
			query.setParameter(0,districtCode);
			query.setParameter(1,blockName);
			List<BlockMasterVO> blockList = query.list();
			if (blockList.isEmpty()) {
				flag = true;
			}
		} catch (Exception e) {
			log.error("Error checking unique name for Block. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
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
			StateDAO stdao = new StateDAO();
			String stCode = Code.substring(0, 2);
			String stateShortName = stdao.getStateShortNameByCode(stCode);
			String query_sql = "select count(*) from "
					+ stateShortName.toLowerCase()
					+ "_shg_detail where substring(entity_code,1,7) = :Code ";
			tr.begin();
			Query query = session.createSQLQuery(query_sql);
			query.setParameter("Code",Code);
			List rowCount = query.list();
			if (rowCount != null && !rowCount.isEmpty()
					&& Integer.parseInt(rowCount.get(0).toString()) > 0) {
				flag = true;
			}
			tr.commit();
			
		} catch (Exception e) {
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

	/**
	 * @updateBlockStatusToIntensive(List<BlockMasterVO> blockMasterVO):Function
	 *                                                   collect the list of
	 *                                                   BlockMasterVO and pass
	 *                                                   to the
	 *                                                   updateBlockStatusToIntensive
	 *                                                   (BlockMasterVO
	 *                                                   blockMasterVO) function
	 *                                                   to update the record
	 * @param blockMasterVO
	 * @return
	 */
	public boolean updateBlockStatusToIntensive(List<BlockMasterVO> blockMasterVO) {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query ;
			for (BlockMasterVO blockVo : blockMasterVO) {
			 query = session.createSQLQuery("update mst_block set intensive = :intensive where block_code = :blockCode ");
				query.setParameter("intensive", blockVo.getIntensive());
				query.setParameter("blockCode", blockVo.getBlockCode());
				int status =query.executeUpdate();
				if (status == 0) {
					flag = false;
					break;
				}
			}
			tr.commit();
		} catch (Exception e) {
			log.error("Exception while updating block status. "
					+ e.getMessage());
		}finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		return flag;
	}

	/**
	 * @updateBlockStatusToIntensive(BlockMasterVO blockMasterVO) :Function used
	 *                                             to update the block detail
	 * @param blockMasterVO
	 * @return
	 */
	public boolean updateBlockStatusToIntensive(BlockMasterVO blockMasterVO) {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(blockMasterVO);
			tr.commit();
		} catch (Exception e) {
			log.error("Exception while changing bl. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		
		return flag;
	}

	
}



	

	