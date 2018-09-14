 package com.infotech.sgsy.master.block;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.infotech.sgsy.common.MasterForm;

public class BlockActionForm extends MasterForm implements Serializable {
	

	private String blockName;
	private String activeFlag;
	private String intensive;
	private String blockStatus;
	private List<BlockActionForm> blockList;
	private String blockCode;
	
	
	
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public void reset() {	
		super.reset();		
		blockName = "";
	}
	public String getIntensive() {
		return intensive;
	}
	public void setIntensive(String intensive) {
		this.intensive = intensive;
	}
	
	public String getBlockStatus() {
		return blockStatus;
	}
	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	
	public List<BlockActionForm> getBlockList() {
		return blockList;
	}
	public void setBlockList(List<BlockActionForm> blockList) {
		this.blockList = blockList;
	}
	
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getblockName() {
		return blockName;
	}
	public void setblockName(String blockName) {
		this.blockName = blockName;
	}
	
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public BlockActionForm getBlock(int index) {
		while (this.blockList.size() <= index) {
			blockList.add(new BlockActionForm());
		}
		return ((BlockActionForm) blockList.get(index));
	}

}
