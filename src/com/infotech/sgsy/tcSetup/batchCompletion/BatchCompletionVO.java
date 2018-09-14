package com.infotech.sgsy.tcSetup.batchCompletion;

import java.util.Date;

import com.infotech.sgsy.masterdata.tradeMaster.TradeMasterVO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;
import com.infotech.sgsy.tcSetup.tcSetupTrade.TcSetupTradeActionVO;
import com.infotech.sgsy.util.DateUtil;

public class BatchCompletionVO {

	@Override
	public String toString() {
		return "BatchCompletionVO [id=" + id + ", complet_Sc=" + complet_Sc + ", complet_St=" + complet_St
				+ ", complet_Others=" + complet_Others + ", complet_Women=" + complet_Women + ", complet_Minority="
				+ complet_Minority + ", complet_Pwd=" + complet_Pwd + ", complet_Total=" + complet_Total
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + "]";
	}

	private String id;
	private String projectID;
	private TcSetupTradeActionVO tcID;    
	private BatchCreationVO batchID;
	public BatchCreationVO getBatchID() {
		return batchID;
	}

	private TradeMasterVO trade;
	private Date batchStartDate;
	private Date batchFreezeDate;
	private Integer batchSize;
	private Date batchEndDate;	 
	
	public Date ojtStartDate;
	public String batchDuration;	
	
	 
	private String resiSize;
	
	private String batchStatus;
 	

	
	public String batchType;
 	public Date batchCommencedDate;	  


    private Integer commenced_Sc;
	private Integer  commenced_St;
    private Integer  commenced_Others;
    private Integer  commenced_Women;	
	private Integer  commenced_Minority;
	private Integer  commenced_Pwd;
	//private Integer  commenced_Total;
	
	
	
	private Integer  complet_Sc;
	private Integer  complet_St;
    private Integer  complet_Others;
    private Integer  complet_Women;	
	private Integer  complet_Minority;
	private Integer  complet_Pwd;
	private Integer  complet_Total;	

	
	
	
	
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
	

	 
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
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
	 public Date getBatchEndDate() {
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
	 public Date getBatchCommencedDate() {
		return batchCommencedDate;
	}
	
//	public void setBatchCommencedDate(Date batchCommencedDate) {
//		this.batchCommencedDate = batchCommencedDate;
//	}
			
	public void setBatchCommencedDate(Object batchCommencedDate) {
		if(batchCommencedDate instanceof String){
			this.batchCommencedDate = new DateUtil().Correct(batchCommencedDate.toString());
		}
		else{
			this.batchCommencedDate =(Date) batchCommencedDate;
		}		
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
	/*public Integer getCommenced_Total() {
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

	public void setBatchID(BatchCreationVO batchID) {
		this.batchID = batchID;
	} 

	
}
