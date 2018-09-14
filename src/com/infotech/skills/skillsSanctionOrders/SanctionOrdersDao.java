package com.infotech.skills.skillsSanctionOrders;

import java.util.List;

import org.apache.struts.upload.FormFile;

import com.infotech.skills.hbm.piaprofile.PiaDetailVO;

public interface SanctionOrdersDao {
	public List<PiaDetailVO> getPiaDetail() throws Exception;
	public String getPRNumber( String piaCode) throws Exception;
	public boolean insert(SanctionOrdersVO sanctionvo) throws Exception;
	public String checkFileType(FormFile file) throws Exception;
	public int getSequenceCodeForSanctionFileUpload() throws Exception;
	public List getSanctionOrderDetail() throws Exception;
	public boolean getDuplicateSanctionOrderDetail(String sanctionOrderNo,String piaCode) throws Exception;
	public List<SanctionOrdersVO> getSanctionNumber(String piaCode);
	public List getSanctionDetail(String sanctionOrderNo);
	public boolean update(SanctionOrdersVO sanctionvo);
	public List getSanctionOrderDetailFromExcel() throws Exception;
}
