 package com.infotech.sgsy.master.grampanchayat;

import java.util.HashSet;
import java.util.Set;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.village.VillageVO;


public class GramPanchayatVO extends MasterVO{
	
	private String gramPanchayatCode;
	private String gramPanchayatName;
	private String ruralUrbanArea;
	private BlockMasterVO block;
	private Set<VillageVO> villageSet = new HashSet<VillageVO>();
	
	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}
	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}
	public String getGramPanchayatCode() {
		return gramPanchayatCode;
	}
	public void setGramPanchayatCode(String gramPanchayatCode) {
		this.gramPanchayatCode = gramPanchayatCode;
	}
	public String getGramPanchayatName() {
		return gramPanchayatName;
	}
	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}
	public BlockMasterVO getBlock() {
		return block;
	}
	public void setBlock(BlockMasterVO block) {
		this.block = block;
	}
	public Set<VillageVO> getVillageSet() {
		return villageSet;
	}
	public void setVillageSet(Set<VillageVO> villageSet) {
		this.villageSet = villageSet;
	}
}
