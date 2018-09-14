package com.infotech.sgsy.monitoringsystem;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;
import com.infotech.sgsy.tcSetup.tcSetupDue.TcSetupDueVO;
import com.infotech.sgsy.util.DateUtil;


public class MonitoringInspectionVO implements Serializable{
	
	private String id;
	private String state;
	private String year;
	private String month;
	private String prId;
	//private String batchId;
	private String tcId;
	private Date  conductedByQAdate;
	private Date conductedBySRLMdate;
	private Date conductedByCTSAdate;
	
	private String  remarksCtsa="";
	private String  remarksQa="";
	private String	remarksSRLM="";
	
	private String remarkstcQa="";
	private String remarkstcSRLM="";
	private String remarkstcCtsa="";
	
	private String advisoryRaisedBySrlm="";
	private String advisoryRaisedByCtsa="";
	private String advisoryClosedByCtsa="";
	private String advisoryClosedBySrlm="";

	private ProjectDetailsVO projectId;
	private TcSetupDueVO trainingCenterId;
	private BatchCreationVO batchId;

	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	public String getRemarkstcQa() {
		return remarkstcQa;
	}
	public void setRemarkstcQa(String remarkstcQa) {
		this.remarkstcQa = remarkstcQa;
	}
	public String getRemarkstcSRLM() {
		return remarkstcSRLM;
	}
	public void setRemarkstcSRLM(String remarkstcSRLM) {
		this.remarkstcSRLM = remarkstcSRLM;
	}
	public String getRemarkstcCtsa() {
		return remarkstcCtsa;
	}
	public void setRemarkstcCtsa(String remarkstcCtsa) {
		this.remarkstcCtsa = remarkstcCtsa;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	/*public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	*/
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getConductedByQAdate() {
		return conductedByQAdate;
	}
	public void setConductedByQAdate(Object qadate) {
		if(qadate instanceof String){
			this.conductedByQAdate = new DateUtil().Correct(qadate.toString());
		}
		else{
			this.conductedByQAdate =(Date) qadate;
		}
	}

	public String getRemarksQa() {
		return remarksQa;
	}
	public void setRemarksQa(String remarksQa) {
		this.remarksQa = remarksQa;
	}
	public Date getConductedBySRLMdate() {
		return conductedBySRLMdate;
	}
	public void setConductedBySRLMdate(Object srlmdate) {
		if(srlmdate instanceof String){
			this.conductedBySRLMdate = new DateUtil().Correct(srlmdate.toString());
		}
		else{
			this.conductedBySRLMdate =(Date) srlmdate;
		}
	}
	
	public Date getConductedByCTSAdate() {
		return conductedByCTSAdate;
	}
	public void setConductedByCTSAdate(Object ctsadate) {
		if(ctsadate instanceof String){
			this.conductedByCTSAdate = new DateUtil().Correct(ctsadate.toString());
		}
		else{
			this.conductedByCTSAdate =(Date) ctsadate;
		}
	}
	public String getRemarksSRLM() {
		return remarksSRLM;
	}
	public void setRemarksSRLM(String remarksSRLM) {
		this.remarksSRLM = remarksSRLM;
	}
	public String getAdvisoryRaisedBySrlm() {
		return advisoryRaisedBySrlm;
	}
	public void setAdvisoryRaisedBySrlm(String advisoryRaisedBySrlm) {
		this.advisoryRaisedBySrlm = advisoryRaisedBySrlm;
	}
	public String getRemarksCtsa() {
		return remarksCtsa;
	}
	public void setRemarksCtsa(String remarksCtsa) {
		this.remarksCtsa = remarksCtsa;
	}
	public String getAdvisoryRaisedByCtsa() {
		return advisoryRaisedByCtsa;
	}
	public void setAdvisoryRaisedByCtsa(String advisoryRaisedByCtsa) {
		this.advisoryRaisedByCtsa = advisoryRaisedByCtsa;
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

	public ProjectDetailsVO getProjectId() {
		return projectId;
	}
	public void setProjectId(ProjectDetailsVO projectId) {
		this.projectId = projectId;
	}
	public TcSetupDueVO getTrainingCenterId() {
		return trainingCenterId;
	}
	public void setTrainingCenterId(TcSetupDueVO trainingCenterId) {
		this.trainingCenterId = trainingCenterId;
	}
	public BatchCreationVO getBatchId() {
		return batchId;
	}
	public void setBatchId(BatchCreationVO batchId) {
		this.batchId = batchId;
	}
	public String getAdvisoryClosedBySrlm() {
		return advisoryClosedBySrlm;
	}
	public void setAdvisoryClosedBySrlm(String advisoryClosedBySrlm) {
		this.advisoryClosedBySrlm = advisoryClosedBySrlm;
	}
	public String getAdvisoryClosedByCtsa() {
		return advisoryClosedByCtsa;
	}
	public void setAdvisoryClosedByCtsa(String advisoryClosedByCtsa) {
		this.advisoryClosedByCtsa = advisoryClosedByCtsa;
	}

	
	public String toString(){
		String bb="{\"id\":"+this.id+",\"batchId\":\""+this.batchId.getBatchID()+"\",\"tcId\":\""+this.batchId.getId()+"\","
				+ "\"conductedByQAdate\":\""+this.conductedByQAdate+"\",\"remarksQa\":\""+this.remarksQa+"\","
				+ "\"conductedBySRLMdate\":\""+this.conductedBySRLMdate+"\",\"conductedByCTSAdate\":\""+this.conductedByCTSAdate+"\","
				+ "\"remarksSRLM\":\""+this.remarksSRLM+"\",\"advisoryClosedBySrlm\":\""+this.advisoryClosedBySrlm+"\",\"advisoryRaisedBySrlm\":\""+this.advisoryRaisedBySrlm+"\","
				+ "\"remarksCtsa\":\""+this.remarksCtsa+"\",\"advisoryClosedByCtsa\":\""+this.advisoryClosedByCtsa+"\",\"advisoryRaisedByCtsa\":\""+this.advisoryRaisedByCtsa+"\"}";
				
		return bb;
	}
	
}	
