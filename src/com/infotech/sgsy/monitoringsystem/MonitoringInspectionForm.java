package com.infotech.sgsy.monitoringsystem;

import java.util.Arrays;
import java.util.Date;

import com.infotech.sgsy.common.MasterForm;

public class MonitoringInspectionForm extends MasterForm{
	@Override
	public String toString() {
		return "MonitoringInspectionForm [id=" + Arrays.toString(id) + ", state=" + state + ", year=" + year
				+ ", month=" + month + ", prId=" + prId + ", batchId=" + Arrays.toString(batchId) + ", tcId=" + tcId
				+ ", remarksQa=" + Arrays.toString(remarksQa) + ", remarksSRLM=" + Arrays.toString(remarksSRLM)
				+ ", remarksCtsa=" + Arrays.toString(remarksCtsa) + ", remarkstcQa=" + remarkstcQa + ", remarkstcSRLM="
				+ remarkstcSRLM + ", remarkstcCtsa=" + remarkstcCtsa + ", conductedByQAdate=" + conductedByQAdate
				+ ", conductedBySRLMdate=" + conductedBySRLMdate + ", conductedByCTSAdate=" + conductedByCTSAdate
				+ ", advisoryRaisedBySrlm=" + advisoryRaisedBySrlm + ", advisoryRaisedByCtsa=" + advisoryRaisedByCtsa
				+ ", advisoryClosedByCtsa=" + advisoryClosedByCtsa + ", advisoryClosedBySrlm=" + advisoryClosedBySrlm
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + "]";
	}
	private String[] id;
	private String state;
	private String year;
	private String month;
	private String prId;
	private String [] batchId;
	private String tcId;
	private String [] remarksQa;
	private String [] remarksSRLM;
	private String [] remarksCtsa;
	
	private String remarkstcQa;
	private String remarkstcSRLM;
	private String remarkstcCtsa;
	
	private String conductedByQAdate;
	private String conductedBySRLMdate;
	private String conductedByCTSAdate;
	private String advisoryRaisedBySrlm;
	private String advisoryRaisedByCtsa;
	private String advisoryClosedByCtsa;
	private String advisoryClosedBySrlm;
	
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
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
	public String[] getBatchId() {
		return batchId;
	}
	public void setBatchId(String[] batchId) {
		this.batchId = batchId;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	
	public String[] getRemarksQa() {
		return remarksQa;
	}
	public void setRemarksQa(String[] remarksQa) {
		this.remarksQa = remarksQa;
	}
	public String[] getRemarksSRLM() {
		return remarksSRLM;
	}
	public void setRemarksSRLM(String[] remarksSRLM) {
		this.remarksSRLM = remarksSRLM;
	}
	public String[] getRemarksCtsa() {
		return remarksCtsa;
	}
	public void setRemarksCtsa(String[] remarksCtsa) {
		this.remarksCtsa = remarksCtsa;
	}
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
	public String getConductedByQAdate() {
		return conductedByQAdate;
	}
	public void setConductedByQAdate(String conductedByQAdate) {
		this.conductedByQAdate = conductedByQAdate;
	}
	public String getConductedBySRLMdate() {
		return conductedBySRLMdate;
	}
	public void setConductedBySRLMdate(String conductedBySRLMdate) {
		this.conductedBySRLMdate = conductedBySRLMdate;
	}
	public String getConductedByCTSAdate() {
		return conductedByCTSAdate;
	}
	public void setConductedByCTSAdate(String conductedByCTSAdate) {
		this.conductedByCTSAdate = conductedByCTSAdate;
	}
	public String getAdvisoryRaisedBySrlm() {
		return advisoryRaisedBySrlm;
	}
	public void setAdvisoryRaisedBySrlm(String advisoryRaisedBySrlm) {
		this.advisoryRaisedBySrlm = advisoryRaisedBySrlm;
	}
	public String getAdvisoryRaisedByCtsa() {
		return advisoryRaisedByCtsa;
	}
	public void setAdvisoryRaisedByCtsa(String advisoryRaisedByCtsa) {
		this.advisoryRaisedByCtsa = advisoryRaisedByCtsa;
	}
	public String getAdvisoryClosedByCtsa() {
		return advisoryClosedByCtsa;
	}
	public void setAdvisoryClosedByCtsa(String advisoryClosedByCtsa) {
		this.advisoryClosedByCtsa = advisoryClosedByCtsa;
	}
	public String getAdvisoryClosedBySrlm() {
		return advisoryClosedBySrlm;
	}
	public void setAdvisoryClosedBySrlm(String advisoryClosedBySrlm) {
		this.advisoryClosedBySrlm = advisoryClosedBySrlm;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
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
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private Date updatedOn;
	

}
