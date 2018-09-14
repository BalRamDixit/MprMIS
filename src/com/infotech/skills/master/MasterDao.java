package com.infotech.skills.master;

import java.util.List;


import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.district.DistrictVO;
import com.infotech.sgsy.master.state.StateVO;

/**
 * @since August 2013
 * @author mehra
 *
 */
public interface MasterDao {
	public List<StateVO> getStateList() throws Exception;
	public StateVO getStateInfo(StateVO stateVO) throws Exception;
	public List<DistrictVO> getDistrictList(DistrictVO districtVO) throws Exception;
	public List<BlockMasterVO> getBlockList(BlockMasterVO blockMasterVO) throws Exception;
	
	public List getStateListUsingAjax() throws Exception;
	public List getDistrictListUsingAjax(String stateCode) throws Exception;
	public List getBlockListUsingAjax(String districtCode) throws Exception;
	
}


