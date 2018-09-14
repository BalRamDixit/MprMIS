package com.infotech.sgsy.state.monitor;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.tcSetup.batchCreation.BatchCreationVO;

public class MonitorPpwsTrainVO implements Serializable{

	private String id;
	private String tcId;
	private BatchCreationVO batchId;
    private String year;
	private String month;
	
	private int  commenced_Total;	
	private int commenced_Sc;
	private int commenced_St;
    private int commenced_Others;
    private int  commenced_Women;	
	private int commenced_Minority;
	private int commenced_Pwd;
 
	private int  completed_Total;	
	private int completed_Sc;
	private int completed_St;
    private int completed_Others;
    private int  completed_Women;	
	private int completed_Minority;
	private int completed_Pwd;
	

	private String createdBy;
	private String updatedBy;
	private Date createdOnDate;
	private Date updatedOnDate;
	
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
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BatchCreationVO getBatchId() {
		return batchId;
	}
	public void setBatchId(BatchCreationVO batchId) {
		this.batchId = batchId;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
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
	public int getCompleted_Total() {
		return completed_Total;
	}
	public void setCompleted_Total(int completed_Total) {
		this.completed_Total = completed_Total;
	}
	public int getCompleted_Sc() {
		return completed_Sc;
	}
	public void setCompleted_Sc(int completed_Sc) {
		this.completed_Sc = completed_Sc;
	}
	public int getCompleted_St() {
		return completed_St;
	}
	public void setCompleted_St(int completed_St) {
		this.completed_St = completed_St;
	}
	public int getCompleted_Others() {
		return completed_Others;
	}
	public void setCompleted_Others(int completed_Others) {
		this.completed_Others = completed_Others;
	}
	public int getCompleted_Women() {
		return completed_Women;
	}
	public void setCompleted_Women(int completed_Women) {
		this.completed_Women = completed_Women;
	}
	public int getCompleted_Minority() {
		return completed_Minority;
	}
	public void setCompleted_Minority(int completed_Minority) {
		this.completed_Minority = completed_Minority;
	}
	public int getCompleted_Pwd() {
		return completed_Pwd;
	}
	public void setCompleted_Pwd(int completed_Pwd) {
		this.completed_Pwd = completed_Pwd;
	}
	@Override
	public String toString(){
		String bb="{\"id\":"+this.id+",\"tcId\":\""+this.tcId+"\",\"bId\":\""+this.batchId.getId()+"\",\"batchId\":\""+this.batchId.getBatchID()+"\",\"commenced_Total\":"+this.commenced_Total+",\"commenced_Sc\":"+this.commenced_Sc+""
				+ ",\"commenced_St\":"+this.commenced_St+",\"commenced_Others\":"+this.commenced_Others+",\"commenced_Women\":"+this.commenced_Women+""
				+",\"commenced_Minority\":"+this.commenced_Minority+",\"commenced_Pwd\":"+this.commenced_Pwd+",\"completed_Total\":"+this.completed_Total+""
				+",\"completed_Sc\":"+this.completed_Sc+",\"completed_St\":"+this.completed_St+",\"completed_Others\":"+this.completed_Others+""
				+",\"completed_Women\":"+this.completed_Women+",\"completed_Minority\":"+this.completed_Minority+",\"completed_Pwd\":"+this.completed_Pwd+""
				+ "}";
		return bb;
	}
	
	
	/*id*/
}
