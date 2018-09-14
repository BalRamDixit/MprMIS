package com.infotech.sgsy.tcSetup.tcSetupTrade;

import java.util.Date;

import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;

public class TcSetupTradeActionVO {
	
	@Override
	public String toString() {
		return "TcSetupTradeActionVO [id=" + id + ", trainningCenter=" + trainningCenter + ", sector=" + sector
				+ ", trade=" + trade + ", appTradeCapacity=" + appTradeCapacity + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	private String id;
	private TcSetupVO trainningCenter;
	private SectorMasterVO sector;
	private TradeMasterVO trade;
	private Integer appTradeCapacity;
	
	
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAppTradeCapacity() {
		return appTradeCapacity;
	}
	public void setAppTradeCapacity(Integer appTradeCapacity) {
		this.appTradeCapacity = appTradeCapacity;
	}
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
	public TcSetupVO getTrainningCenter() {
		return trainningCenter;
	}
	public void setTrainningCenter(TcSetupVO trainningCenter) {
		this.trainningCenter = trainningCenter;
	}
	public SectorMasterVO getSector() {
		return sector;
	}
	public void setSector(SectorMasterVO sector) {
		this.sector = sector;
	}
	public TradeMasterVO getTrade() {
		return trade;
	}
	public void setTrade(TradeMasterVO trade) {
		this.trade = trade;
	}
	 
	
	
	
}
