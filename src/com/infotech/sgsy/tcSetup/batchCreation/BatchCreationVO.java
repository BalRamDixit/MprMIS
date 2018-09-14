package com.infotech.sgsy.tcSetup.batchCreation;

import java.util.Date;

import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.util.DateUtil;

public class BatchCreationVO {
	
	private String id;	
	private String projectID;
	private TcSetupTradeActionVO tcID;    
	private String batchID;
	private TradeMasterVO trade;
	private Date batchStartDate;
	private Date batchFreezeDate;
	private Integer batchSize;
	/*private Date batchEndDate;	*/
	
	public Date ojtStartDate;
	public String batchDuration;	
	
	 
	private String resiSize;	

	
	public String batchType;
/*	public Date batchCommencedDate;	 */


    private Integer commenced_Sc;
	private Integer  commenced_St;
    private Integer  commenced_Others;
    private Integer  commenced_Women;	
	private Integer  commenced_Minority;
	private Integer  commenced_Pwd;
	private Integer  commenced_Total;
	
	
	
	
	
 	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	
	
	
	 
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	
	public String getBatchID() {
		return batchID;
	}
	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}
 
	public Integer getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	public Date getBatchStartDate() {
		return batchStartDate;
	}
	/*public void setBatchStartDate(Date batchStartDate) {
		this.batchStartDate = batchStartDate;
	}*/
	
	public void setBatchStartDate(Object batchStartDate) {
		if(batchStartDate instanceof String){
			this.batchStartDate = new DateUtil().Correct(batchStartDate.toString());
		}
		else{
			this.batchStartDate =(Date) batchStartDate;
		}
		
	}
	public Date getBatchFreezeDate() {
		return batchFreezeDate;
	}
	/*public void setBatchFreezeDate(Date batchFreezeDate) {
		this.batchFreezeDate = batchFreezeDate;
	}*/
	public void setBatchFreezeDate(Object batchFreezeDate) {
		if(batchFreezeDate instanceof String){
			this.batchFreezeDate = new DateUtil().Correct(batchFreezeDate.toString());
		}
		else{
			this.batchFreezeDate =(Date) batchFreezeDate;
		}
		
	}
	/*public Date getBatchEndDate() {
		return batchEndDate;
	}
	public void setBatchEndDate(Date batchEndDate) {
		this.batchEndDate = batchEndDate;
	}
	public void setBatchEndDate(Object batchEndDate) {
		if(batchEndDate instanceof String){
			this.batchEndDate = new DateUtil().Correct(batchEndDate.toString());
		}
		else{
			this.batchEndDate =(Date) batchEndDate;
		}
		
	}*/
 
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TcSetupTradeActionVO getTcID() {
		return tcID;
	}
	public void setTcID(TcSetupTradeActionVO tcID) {
		this.tcID = tcID;
	}
	public TradeMasterVO getTrade() {
		return trade;
	}
	public void setTrade(TradeMasterVO trade) {
		this.trade = trade;
	}
	public Date getOjtStartDate() {
		return ojtStartDate;
	}
	/*public void setOjtStartDate(Date ojtStartDate) {
		this.ojtStartDate = ojtStartDate;
	}*/
	public void setOjtStartDate(Object ojtStartDate) {
		if(ojtStartDate instanceof String){
			this.ojtStartDate = new DateUtil().Correct(ojtStartDate.toString());
		}
		else{
			this.ojtStartDate =(Date) ojtStartDate;
		}
		
	}	
	
	public String getBatchDuration() {
		return batchDuration;
	}
	public void setBatchDuration(String batchDuration) {
		this.batchDuration = batchDuration;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	/*public Date getBatchCommencedDate() {
		return batchCommencedDate;
	}
	
	public void setBatchCommencedDate(Date batchCommencedDate) {
		this.batchCommencedDate = batchCommencedDate;
	}
			
	public void setBatchCommencedDate(Object batchCommencedDate) {
		if(batchCommencedDate instanceof String){
			this.batchCommencedDate = new DateUtil().Correct(batchCommencedDate.toString());
		}
		else{
			this.batchCommencedDate =(Date) batchCommencedDate;
		}		
	}*/	
	public Integer getCommenced_Sc() {
		return commenced_Sc;
	}
	public void setCommenced_Sc(Integer commenced_Sc) {
		this.commenced_Sc = commenced_Sc;
 	}
	public Integer getCommenced_St() {
		return commenced_St;
	}
	public void setCommenced_St(Integer commenced_St) {
		this.commenced_St = commenced_St;
	}
	public Integer getCommenced_Others() {
		return commenced_Others;
	}
	public void setCommenced_Others(Integer commenced_Others) {
		this.commenced_Others = commenced_Others;
	}
	public Integer getCommenced_Women() {
		return commenced_Women;
	}
	public void setCommenced_Women(Integer commenced_Women) {
		this.commenced_Women = commenced_Women;
	}
	public Integer getCommenced_Minority() {
		return commenced_Minority;
	}
	public void setCommenced_Minority(Integer commenced_Minority) {
		this.commenced_Minority = commenced_Minority;
	}
	public Integer getCommenced_Pwd() {
		return commenced_Pwd;
	}
	public void setCommenced_Pwd(Integer commenced_Pwd) {
		this.commenced_Pwd = commenced_Pwd;
	}
	public Integer getCommenced_Total() {
		return commenced_Total;
	}
	public void setCommenced_Total(Integer commenced_Total) {
		this.commenced_Total = commenced_Total;
	}
	public String getResiSize() {
		return resiSize;
	}
	public void setResiSize(String resiSize) {
		this.resiSize = resiSize;
	}
	@Override
	public String toString() {
		return "{\"id\":\""+this.id+"\",\"tcid\":\""+this.tcID.getTrainningCenter().getTcID()+"\",\"batchStartDate\":\""+this.batchStartDate+"\","
				+ "\"batchFreezeDate\":\""+this.batchFreezeDate+"\",\"ojtStartDate\":\""+ojtStartDate+"\",\"batchSize\":\""+this.batchSize+"\","
				+ "\"batchDuration\":\""+this.batchDuration+"\",\"resiSize\":\""+this.resiSize+"\",\"batchType\":\""+this.batchType+"\","
				+ "\"commenced_Sc\":\""+this.commenced_Sc+"\",\"commenced_St\":\""+this.commenced_St+"\",\"commenced_Others\":\""+this.commenced_Others+"\","
				+ "\"commenced_Women\":\""+this.commenced_Women+"\",\"commenced_Minority\":\""+this.commenced_Minority+"\","
				+ "\"commenced_Pwd\":\""+this.commenced_Pwd+"\",\"commenced_Total\":\""+this.commenced_Total+"\",\"batchID\":\""+this.batchID+"\","
						+ "\"sector\":\""+this.tcID.getSector().getSectorName()+"\",\"trade\":\""+this.getTcID().getTrade().getTradeName()+"\","
								+ "\"projectid\":\""+this.tcID.getTrainningCenter().getProject().getProjectID()+"\"}";
	} 
	

}
