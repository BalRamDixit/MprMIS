package com.infotech.sgsy.tcSetup.batchCreation;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings("serial")
public class BatchCreationForm extends ValidatorForm {
 
	private String id;	
	public String projectID;
	public String tcID;    
	public String batchID;
	public String sector;
	public String trade;
	public String batchStartDate;
	public String batchFreezeDate;
	public Integer batchSize;
	/*public String batchEndDate;	*/
	public String ojtStartDate;
	public String batchDuration;	 
	
	public String batchType;
/*	public String batchCommencedDate;	*/
	
	private String resiSize;	



	private Integer  commenced_Sc;
	private Integer  commenced_St;
    private Integer  commenced_Others;
    private Integer  commenced_Women;	
	private Integer  commenced_Minority;
	private Integer  commenced_Pwd;
	private Integer  commenced_Total;	

	
	
	
	
 
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getTcID() {
		return tcID;
	}
	public void setTcID(String tcID) {
		this.tcID = tcID;
	}
	public String getBatchID() {
		return batchID;
	}
	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
 
	public Integer getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	public String getBatchStartDate() {
		return batchStartDate;
	}
	public void setBatchStartDate(String batchStartDate) {
		this.batchStartDate = batchStartDate;
	}
	public String getBatchFreezeDate() {
		return batchFreezeDate;
	}
	public void setBatchFreezeDate(String batchFreezeDate) {
		this.batchFreezeDate = batchFreezeDate;
	}
/*	public String getBatchEndDate() {
		return batchEndDate;
	}
	public void setBatchEndDate(String batchEndDate) {
		this.batchEndDate = batchEndDate;
	}*/

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public String getOjtStartDate() {
		return ojtStartDate;
	}
	public void setOjtStartDate(String ojtStartDate) {
		this.ojtStartDate = ojtStartDate;
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
	/*public String getBatchCommencedDate() {
		return batchCommencedDate;
	}
	public void setBatchCommencedDate(String batchCommencedDate) {
		this.batchCommencedDate = batchCommencedDate;
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
	
	
	
}
