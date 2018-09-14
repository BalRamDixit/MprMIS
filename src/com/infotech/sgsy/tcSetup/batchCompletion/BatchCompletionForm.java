package com.infotech.sgsy.tcSetup.batchCompletion;

import org.apache.struts.validator.ValidatorForm;

public class BatchCompletionForm extends ValidatorForm {

	private String id;
	private String projectID;
	private String tcID;    
	private String batchID;
	private String sector;
	private String trade;
	private String batchStartDate;
	private String batchFreezeDate;
	private Integer batchSize;
	private String batchEndDate;	 
	private String ojtStartDate;
	private String batchDuration; 	
	private String batchType;
 	public String batchCommencedDate;	 
	private String resiSize;
	private Integer  commenced_Sc;
	private Integer  commenced_St;
    private Integer  commenced_Others;
    private Integer  commenced_Women;	
	private Integer  commenced_Minority;
	private Integer  commenced_Pwd;
	//private Integer  commenced_Total;	

	private String batchStatus;

	
	private Integer  complet_Sc;
	private Integer  complet_St;
    private Integer  complet_Others;
    private Integer  complet_Women;	
	private Integer  complet_Minority;
	private Integer  complet_Pwd;
	private Integer  complet_Total;	

	 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	 
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
	 public String getBatchCommencedDate() {
		return batchCommencedDate;
	}
	public void setBatchCommencedDate(String batchCommencedDate) {
		this.batchCommencedDate = batchCommencedDate;
	} 
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
/*	public Integer getCommenced_Total() {
		return commenced_Total;
	}
	public void setCommenced_Total(Integer commenced_Total) {
		this.commenced_Total = commenced_Total;
	}*/
	public String getResiSize() {
		return resiSize;
	}
	public void setResiSize(String resiSize) {
		this.resiSize = resiSize;
	}

	public String getBatchEndDate() {
		return batchEndDate;
	}

	public void setBatchEndDate(String batchEndDate) {
		this.batchEndDate = batchEndDate;
	}

	public Integer getComplet_Sc() {
		return complet_Sc;
	}

	public void setComplet_Sc(Integer complet_Sc) {
		this.complet_Sc = complet_Sc;
	}

	public Integer getComplet_St() {
		return complet_St;
	}

	public void setComplet_St(Integer complet_St) {
		this.complet_St = complet_St;
	}

	public Integer getComplet_Others() {
		return complet_Others;
	}

	public void setComplet_Others(Integer complet_Others) {
		this.complet_Others = complet_Others;
	}

	public Integer getComplet_Women() {
		return complet_Women;
	}

	public void setComplet_Women(Integer complet_Women) {
		this.complet_Women = complet_Women;
	}

	public Integer getComplet_Minority() {
		return complet_Minority;
	}

	public void setComplet_Minority(Integer complet_Minority) {
		this.complet_Minority = complet_Minority;
	}

	public Integer getComplet_Pwd() {
		return complet_Pwd;
	}

	public void setComplet_Pwd(Integer complet_Pwd) {
		this.complet_Pwd = complet_Pwd;
	}

	public Integer getComplet_Total() {
		return complet_Total;
	}

	public void setComplet_Total(Integer complet_Total) {
		this.complet_Total = complet_Total;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	
	
	
	
}
