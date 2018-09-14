package com.infotech.sgsy.master.loactionMasterStandardization;

import java.awt.Color;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.block.BlockDAO;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.blockCorrection.BlockCorrectionDAO;
import com.infotech.sgsy.master.blockCorrection.BlockCorrectionVO;
import com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO;
import com.infotech.sgsy.master.grampanchayat.GramPanchayatVO;
import com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO;
import com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionVO;
import com.infotech.sgsy.master.village.VillageDAO;
import com.infotech.sgsy.master.village.VillageVO;
import com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO;
import com.infotech.sgsy.master.villageCorrection.VillageCorrectionVO;
//import com.infotech.sp.newSPPrj.newSPPrjForm;

public class LocationMasterStandardizationUtil {
	Log log = LogFactory.getLog(getClass());
	public List<BlockMasterVO> getBlockList(String districtCode) {
		log.info("Getting Block List.");
		List<BlockMasterVO> blockList = new BlockDAO().getBlockByDistrictCode(districtCode);
		List<BlockCorrectionVO> blockCorrectionList = new BlockCorrectionDAO().getBlockList(districtCode);
		if(blockList.size() != 0){
			for(int i = 0; i< blockList.size(); i++) {
				BlockMasterVO block = blockList.get(i);
				for(int j = 0; j<blockCorrectionList.size(); j++) {
					BlockCorrectionVO blockCorrection = blockCorrectionList.get(j);
					if(blockCorrection.getFlag().equals("E") || blockCorrection.getFlag().equals("D") || blockCorrection.getFlag().equals("A")) {
						if(blockCorrection.getBlockCode().equals(block.getBlockCode())) {
							if(blockCorrection.getFlag().equals("E")) {
								block.setBlockName(blockCorrection.getBlockName());
							}
							block.setLastModifedBy(blockCorrection.getFlag());
							blockList.set(i, block);
							blockCorrectionList.remove(j);
							--j;
						}
						else if(!blockCorrection.getBlockCode().equals(block.getBlockCode()) && blockCorrection.getFlag().equals("A")) {
							BlockMasterVO newBlock = new BlockMasterVO();
							newBlock.setBlockCode(blockCorrection.getBlockCode());
							newBlock.setBlockName(blockCorrection.getBlockName());
							newBlock.setLastModifedBy(blockCorrection.getFlag());
							blockList.add(newBlock);
							blockCorrectionList.remove(j);
							--j;
						}
					}
				}
			}
		} 
		// If no block found in the selected district
		else {
			for(BlockCorrectionVO blockCorrection: blockCorrectionList) {
				BlockMasterVO newBlock = new BlockMasterVO();
				newBlock.setBlockCode(blockCorrection.getBlockCode());
				newBlock.setBlockName(blockCorrection.getBlockName());
				newBlock.setLastModifedBy(blockCorrection.getFlag());
				blockList.add(newBlock);
			}
		}
		return blockList;
	}
	
	public List<GramPanchayatVO> getGrampanchayatList(String blockCode) {
		log.info("Getting Grampanchayat List.");
		List<GramPanchayatVO> grampanchayatList = new GramPanchayatDAO().getGramPanchayatList(blockCode);
		List<GrampanchayatCorrectionVO> grampanchayatCorrectionList = new GrampanchayatCorrectionDAO().getGrampanchayatList(blockCode);
		for(int i = 0; i<grampanchayatList.size(); i++) {
			GramPanchayatVO grampanchayat = grampanchayatList.get(i);
			for(int j = 0; j<grampanchayatCorrectionList.size(); j++) {
				GrampanchayatCorrectionVO grampanchayatCorrection = grampanchayatCorrectionList.get(j);
				if(grampanchayatCorrection.getFlag().equals("E") || grampanchayatCorrection.getFlag().equals("D") || grampanchayatCorrection.getFlag().equals("A")) {
					if(grampanchayat.getGramPanchayatCode().equals(grampanchayatCorrection.getGramPanchayatCode())) {
						if(grampanchayatCorrection.getFlag().equals("E")) {
							grampanchayat.setGramPanchayatName(grampanchayatCorrection.getGramPanchayatName());
						}
						grampanchayat.setLastModifedBy(grampanchayatCorrection.getFlag());
						grampanchayatList.set(i, grampanchayat);
						grampanchayatCorrectionList.remove(j);
						--j;
					}
					else if(!grampanchayat.getGramPanchayatCode().equals(grampanchayatCorrection.getGramPanchayatCode()) && grampanchayatCorrection.getFlag().equals("A")) {
						GramPanchayatVO newGrampanchayat = new GramPanchayatVO();
						newGrampanchayat.setGramPanchayatCode(grampanchayatCorrection.getGramPanchayatCode());
						newGrampanchayat.setGramPanchayatName(grampanchayatCorrection.getGramPanchayatName());
						newGrampanchayat.setLastModifedBy(grampanchayatCorrection.getFlag());
						grampanchayatList.add(newGrampanchayat);
						grampanchayatCorrectionList.remove(j);
						--j;
					}
				}
			}
		}
		if(grampanchayatList.isEmpty() && !grampanchayatCorrectionList.isEmpty()) {
			grampanchayatList = new ArrayList<GramPanchayatVO>();
			for(int i = 0; i<grampanchayatCorrectionList.size(); i++) {
				GrampanchayatCorrectionVO grampanchayatCorrectionVO = grampanchayatCorrectionList.get(i);
				GramPanchayatVO grampanchayatVO = new GramPanchayatVO();
				grampanchayatVO.setGramPanchayatCode(grampanchayatCorrectionVO.getGramPanchayatCode());
				grampanchayatVO.setGramPanchayatName(grampanchayatCorrectionVO.getGramPanchayatName());
				grampanchayatVO.setLastModifedBy(grampanchayatCorrectionVO.getFlag());
				grampanchayatList.add(grampanchayatVO);
			}
		}
		return grampanchayatList;
	}
	
	public List<VillageVO> getVillageList(String grampanchayatCode) {
		log.info("Getting Village List.");
		List<VillageVO> villageList = new VillageDAO().getVillageListFromGrampanchayatCode(grampanchayatCode);
		List<VillageCorrectionVO> villageCorrectionList = new VillageCorrectionDAO().getVillageList(grampanchayatCode);
		for(int i = 0; i<villageList.size(); i++) {
			VillageVO villageVO = villageList.get(i);
			for(int j = 0; j<villageCorrectionList.size(); j++) {
				VillageCorrectionVO villageCorrectionVO = villageCorrectionList.get(j);
				if(villageCorrectionVO.getFlag().equals("E") || villageCorrectionVO.getFlag().equals("D") || villageCorrectionVO.getFlag().equals("A")) {
					if(villageCorrectionVO.getVillageCode().equals(villageVO.getVillageCode())) {
						if(villageCorrectionVO.getFlag().equals("E")) {
							villageVO.setVillageName(villageCorrectionVO.getVillageName());
						}
						villageVO.setLastModifedBy(villageCorrectionVO.getFlag());
						villageList.set(i, villageVO);
						villageCorrectionList.remove(j);
						--j;
					}
					else if(!villageCorrectionVO.getVillageCode().equals(villageVO.getVillageCode()) &&  villageCorrectionVO.getFlag().equals("A")) {
						VillageVO newVillage = new VillageVO();
						newVillage.setVillageCode(villageCorrectionVO.getVillageCode());
						newVillage.setVillageName(villageCorrectionVO.getVillageName());
						newVillage.setLastModifedBy(villageCorrectionVO.getFlag());
						villageList.add(newVillage);
						villageCorrectionList.remove(j);
						--j;
					}
				}
			}
		}
		if(villageList.isEmpty() && !villageCorrectionList.isEmpty()) {
			villageList = new ArrayList<VillageVO>();
			for(int i = 0; i<villageCorrectionList.size(); i++) {
				VillageCorrectionVO villageCorrectionVO = villageCorrectionList.get(i);
				VillageVO villageVO = new VillageVO();
				villageVO.setVillageCode(villageCorrectionVO.getVillageCode());
				villageVO.setVillageName(villageCorrectionVO.getVillageName());
				villageVO.setLastModifedBy(villageCorrectionVO.getFlag());
				villageList.add(villageVO);
			}
		}
		return villageList;
	}

	public String getCode(String daoClassName, String daoCorrectionClassName, String code) {
		Long maxCode;
		Long maxCodeCorrection;
		String newCode = "";
		try {
			Class<?> daoClass = Class.forName(daoClassName);
			Method maxCodeDAO = daoClass.getDeclaredMethod("getMaxCode", new Class[]{String.class});
			maxCode = (Long)maxCodeDAO.invoke(daoClass.newInstance(), code);
			Class<?> daoCorrectionClass = Class.forName(daoCorrectionClassName);
			Method maxCodeDAOCorrection = daoCorrectionClass.getDeclaredMethod("getMaxCode", String.class);
			maxCodeCorrection = (Long)maxCodeDAOCorrection.invoke(daoCorrectionClass.newInstance(), code);
			if(maxCode == 0 && maxCodeCorrection == 0) {
				code += "001";
				newCode = code;
			}
			else {
				newCode = (maxCode>maxCodeCorrection? ++maxCode : ++maxCodeCorrection).toString();
			}
		}
		catch(ClassNotFoundException e) {
			log.error("Error getting maximum code. " + e.getMessage());
		}
		catch(NoSuchMethodException e) {
			log.error("Error getting maximum code. " + e.getMessage());
		}
		catch(IllegalAccessException e) {
			log.error("Error getting maximum code. " + e.getMessage());
		}
		catch(InvocationTargetException e) {
			log.error("Error getting maximum code. " + e.getMessage());
		}
		catch(InstantiationException e) {
			log.error("Error getting maximum code. " + e.getMessage());
		}
		return newCode;
		
	}
	
	public boolean checkUnique(String daoClassName, String daoCorrectionClassName, String code, String name) {
		boolean flag = false;
		try {
			Class<?> daoClass = Class.forName(daoClassName);
			Method checkUnique = daoClass.getDeclaredMethod("checkUnique", new Class[]{String.class, String.class});
			flag = (Boolean)checkUnique.invoke(daoClass.newInstance(), code, name);
			
			if(flag == true) {
				Class<?> daoCorrectionClass = Class.forName(daoCorrectionClassName);
				Method checkUniqueCorrection = daoCorrectionClass.getDeclaredMethod("checkUnique", new Class[]{String.class, String.class});
				flag = (Boolean)checkUniqueCorrection.invoke(daoCorrectionClass.newInstance(), code, name);
			}
		}
		catch(ClassNotFoundException e) {
			log.error("Error while checking unique name. " + e.getMessage());
		}
		catch(NoSuchMethodException e) {
			log.error("Error while checking unique name. " + e.getMessage());
		}
		catch(IllegalAccessException e) {
			log.error("Error while checking unique name. " + e.getMessage());
		}
		catch(InvocationTargetException e) {
			log.error("Error while checking unique name. " + e.getMessage());
		}
		catch(InstantiationException e) {
			log.error("Error while checking unique name. " + e.getMessage());
		}
		return flag;
	}
	
	protected boolean addBlock(String stateCode, String districtCode, String name, HttpServletRequest request) {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		String blockCode = getCode("com.infotech.sgsy.master.block.BlockDAO", "com.infotech.sgsy.master.blockCorrection.BlockCorrectionDAO", districtCode);
		
		BlockCorrectionVO blockCorrectionVO = new BlockCorrectionVO();
		blockCorrectionVO.setStateCode(stateCode);
		blockCorrectionVO.setDistrictCode(districtCode);
		blockCorrectionVO.setBlockCode(blockCode);
		blockCorrectionVO.setBlockName(name);
		blockCorrectionVO.setFlag("A");
		blockCorrectionVO.setRuralUrbanArea("R");
		blockCorrectionVO.setCreatedBy(loginVO.getUserid());
		blockCorrectionVO.setCreatedOn(new Date());
		
		boolean flag = new BlockCorrectionDAO().insert(blockCorrectionVO);
		return flag;
	}
	
	protected boolean addGrampanchayat(String stateCode, String districtCode, String blockCode, String grampanchayatName, HttpServletRequest request) {
		boolean flag = false;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		String grampanchayatCode = getCode("com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO", "com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO", blockCode);
		
		GrampanchayatCorrectionVO grampanchayatCorrectionVO = new GrampanchayatCorrectionVO();
		grampanchayatCorrectionVO.setStateCode(stateCode);
		grampanchayatCorrectionVO.setDistrictCode(districtCode);
		grampanchayatCorrectionVO.setBlockCode(blockCode);
		grampanchayatCorrectionVO.setGramPanchayatName(grampanchayatName);
		grampanchayatCorrectionVO.setGramPanchayatCode(grampanchayatCode);
		grampanchayatCorrectionVO.setRuralUrbanArea("R");
		grampanchayatCorrectionVO.setFlag("A");
		grampanchayatCorrectionVO.setCreatedBy(loginVO.getUserid());
		grampanchayatCorrectionVO.setCreatedOn(new Date());
		
		flag = new GrampanchayatCorrectionDAO().insert(grampanchayatCorrectionVO);
		return flag;
	}
	
	protected boolean addVillage(String stateCode, String districtCode, String blockCode, String grampanchayatCode, String villageName, HttpServletRequest request) {
		String villageCode = getCode("com.infotech.sgsy.master.village.VillageDAO", "com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO", grampanchayatCode);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		VillageCorrectionVO villageCorrectionVO = new VillageCorrectionVO();
		villageCorrectionVO.setStateCode(stateCode);
		villageCorrectionVO.setDistrictCode(districtCode);
		villageCorrectionVO.setBlockCode(blockCode);
		villageCorrectionVO.setGramPanchayatCode(grampanchayatCode);
		villageCorrectionVO.setVillageCode(villageCode);
		villageCorrectionVO.setVillageName(villageName);
		villageCorrectionVO.setFlag("A");
		villageCorrectionVO.setCreatedBy(loginVO.getUserid());
		villageCorrectionVO.setCreatedOn(new Date());
		
		boolean flag = new VillageCorrectionDAO().insert(villageCorrectionVO);
		return flag;
	}

	public List<VillageVO> getAllVillageList(String districtCode) {
		log.info("Getting All Villages List.");
		List<VillageVO> villageList = new VillageDAO().getVillageListForDistrictCode(districtCode);
		return villageList;
	}
}

