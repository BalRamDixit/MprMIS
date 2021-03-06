package com.infotech.sgsy.state.monitor;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;

public class MonitorPpwsPlaceVO implements Serializable {
	
    private String id;
	
//	private String projectId;
	private String tcId;
	private BatchCreationVO batchId;
	private int appointed;
//	private String year;
//	private String month;
	
	private int  commenced_Total;	
	private int  commenced_Sc;
	private int  commenced_St;
    private int  commenced_Others;
    private int  commenced_Women;	
	private int  commenced_Minority;
	private int  commenced_Pwd;
 
	private int candidate_Assessed;	
	private int candidate_Certified;
	
	private String createdBy;
	private String updatedBy;
	private Date createdOnDate;
	private Date updatedOnDate;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	
	public BatchCreationVO getBatchId() {
		return batchId;
	}
	public void setBatchId(BatchCreationVO batchId) {
		this.batchId = batchId;
	}
	public int getAppointed() {
		return appointed;
	}
	public void setAppointed(int appointed) {
		this.appointed = appointed;
	}

	/*public String getYear() {
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
	}*/
	public int getCommenced_Total() {
		return commenced_Total;
	}
	public void setCommenced_Total(int commenced_Total) {
		this.commenced_Total = commenced_Total;
	}
	public int getCommenced_Sc() {
		return commenced_Sc;
	}
	public void setCommenced_Sc(int commenced_Sc) {
		this.commenced_Sc = commenced_Sc;
	}
	public int getCommenced_St() {
		return commenced_St;
	}
	public void setCommenced_St(int commenced_St) {
		this.commenced_St = commenced_St;
	}
	public int getCommenced_Others() {
		return commenced_Others;
	}
	public void setCommenced_Others(int commenced_Others) {
		this.commenced_Others = commenced_Others;
	}
	public int getCommenced_Women() {
		return commenced_Women;
	}
	public void setCommenced_Women(int commenced_Women) {
		this.commenced_Women = commenced_Women;
	}
	public int getCommenced_Minority() {
		return commenced_Minority;
	}
	public void setCommenced_Minority(int commenced_Minority) {
		this.commenced_Minority = commenced_Minority;
	}
	public int getCommenced_Pwd() {
		return commenced_Pwd;
	}
	public void setCommenced_Pwd(int commenced_Pwd) {
		this.commenced_Pwd = commenced_Pwd;
	}
	public int getCandidate_Assessed() {
		return candidate_Assessed;
	}
	public void setCandidate_Assessed(int candidate_Assessed) {
		this.candidate_Assessed = candidate_Assessed;
	}
	public int getCandidate_Certified() {
		return candidate_Certified;
	}
	public void setCandidate_Certified(int candidate_Certified) {
		this.candidate_Certified = candidate_Certified;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}
	@Override
	public String toString(){
		String bb="{\"id\":"+this.id+",\"tcId\":\""+this.tcId+"\",\"bId\":\""+this.batchId.getId()+"\",\"batchId\":\""+this.batchId.getBatchID()+"\",\"appointed\":\""+this.appointed+"\",\"commenced_Total\":"+this.commenced_Total+",\"commenced_Sc\":"+this.commenced_Sc+""
				+ ",\"commenced_St\":"+this.commenced_St+",\"commenced_Others\":"+this.commenced_Others+",\"commenced_Women\":"+this.commenced_Women+""
				+",\"commenced_Minority\":"+this.commenced_Minority+",\"commenced_Pwd\":"+this.commenced_Pwd+",\"candidate_Assessed\":"+this.candidate_Assessed+""
				+",\"candidate_Certified\":"+this.candidate_Certified+""
				+ "}";
		return bb;
	}
	
}
