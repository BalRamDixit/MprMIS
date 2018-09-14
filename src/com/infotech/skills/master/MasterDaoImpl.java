package com.infotech.skills.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.util.PropertyModel;

/**
 * @since August 2013
 */
public class MasterDaoImpl implements MasterDao {

private static Logger log = Logger.getRootLogger();

@SuppressWarnings("unchecked")
public List<StateVO> getStateList() throws Exception {
	List<StateVO> stateList = new ArrayList<StateVO>();
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();

	try {
		tr.begin();
		Criteria crit = session.createCriteria(StateVO.class)
		.addOrder( Order.asc("stateName") );
		stateList = crit.list();
		tr.commit();			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(session.isOpen())			
			session.close();
	}
	return stateList;
}

public List getStateListUsingAjax() throws Exception {
	List<StateVO> stateList = new ArrayList<StateVO>();
	PropertyModel propertyModel = null;
	ArrayList finalList = new ArrayList();
	try {
		if(stateList !=null || stateList.size() > 0)
		for(StateVO stateVOTemp : stateList) {
			propertyModel = new PropertyModel();
			propertyModel.setPropertyCode(stateVOTemp.getStateCode());
			propertyModel.setPropertyValue(stateVOTemp.getStateName());
			finalList.add(propertyModel);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return finalList;
}

@SuppressWarnings("unchecked")
public List<DistrictVO> getDistrictList(DistrictVO districtVO) throws Exception {
	List<DistrictVO> districtList = new ArrayList<DistrictVO>();
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();

	try {
		tr.begin();
		Criteria crit = session.createCriteria(DistrictVO.class);
		if(districtVO.getStateCode()!= null && !districtVO.getStateCode().trim().equals("")){
			crit.add( Restrictions.like("stateCode", districtVO.getStateCode()));
		}
		crit.addOrder( Order.asc("districtName") );
		districtList = crit.list();
		tr.commit();			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(session.isOpen())			
			session.close();
	}
	return districtList;
}

/**
 * 
 */
public List getDistrictListUsingAjax(String stateCode) throws Exception {
	List<DistrictVO> districtList = new ArrayList<DistrictVO>();
	PropertyModel propertyModel = null;
	ArrayList finalList = new ArrayList();
	try {
		DistrictVO districtVO = new DistrictVO(); 
		districtVO.setStateCode(stateCode);
		districtList = this.getDistrictList(districtVO);
		if(districtList !=null || districtList.size() > 0)
		for(DistrictVO districtVOTemp : districtList) {
			propertyModel = new PropertyModel();
			propertyModel.setPropertyCode(districtVOTemp.getDistrictCode());
			propertyModel.setPropertyValue(districtVOTemp.getDistrictName());
			finalList.add(propertyModel);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return finalList;
}


/**
 * 
 */
@SuppressWarnings("unchecked")
public List<BlockMasterVO> getBlockList(BlockMasterVO blockMasterVO)
		throws Exception {
	List<BlockMasterVO> blockList = new ArrayList<BlockMasterVO>();
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();

	try {
		tr.begin();
		Criteria crit = session.createCriteria(BlockMasterVO.class);
		if(blockMasterVO.getStateCode()!= null && !blockMasterVO.getStateCode().trim().equals("")){
			crit.add( Restrictions.like("stateCode", blockMasterVO.getStateCode()));
		}else if(blockMasterVO.getDistrictCode()!= null && !blockMasterVO.getDistrictCode().trim().equals("")){
			crit.add( Restrictions.like("districtCode", blockMasterVO.getDistrictCode()));
		}
		crit.addOrder( Order.asc("blockName") );
		blockList = crit.list();
		tr.commit();			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(session.isOpen())			
			session.close();
	}
	return blockList;
}

/**
 * 
 */
public List getBlockListUsingAjax(String districtCode)
throws Exception {
List<BlockMasterVO> blockList = new ArrayList<BlockMasterVO>();
PropertyModel propertyModel = null;
ArrayList finalList = new ArrayList();
try {
	BlockMasterVO blockMasterVO = new BlockMasterVO(); 
	blockMasterVO.setDistrictCode(districtCode);
	blockList = this.getBlockList(blockMasterVO);
	if(blockList !=null || blockList.size() > 0)
	for(BlockMasterVO blockMasterVOTemp : blockList) {
		propertyModel = new PropertyModel();
		propertyModel.setPropertyCode(blockMasterVOTemp.getBlockCode());
		propertyModel.setPropertyValue(blockMasterVOTemp.getBlockName());
		finalList.add(propertyModel);
}
} catch (Exception e) {
	e.printStackTrace();
} 
return finalList;
}

@Override
public StateVO getStateInfo(StateVO stateVO) throws Exception {
	StateVO stateInfo = new StateVO();
	Persister persister = PersisterImpl.getPersister();
	Session session = persister.getSession();
	Transaction tr = session.getTransaction();

	try {
		tr.begin();
		Criteria crit = session.createCriteria(StateVO.class)
		 .add( Restrictions.eq("stateCode", stateVO.getStateCode()));
		stateInfo = (StateVO) crit.list();
		tr.commit();			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(session.isOpen())			
			session.close();
	}
	return stateInfo;
}
		
}


