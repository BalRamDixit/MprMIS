package com.infotech.sgsy.master.parliamentaryConstituencyMaster;

import java.io.Serializable;
import java.util.Date;

public class ParliamentaryConstituencyMasterVO implements Serializable{
	
	@Override
	public String toString() {
		return "ParliamentaryConstituencyMasterVO [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
	private String id;
	private String name;
	private String code;
	
	private boolean isdDeleted;
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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isIsdDeleted() {
		return isdDeleted;
	}
	public void setIsdDeleted(boolean isdDeleted) {
		this.isdDeleted = isdDeleted;
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
	
	

}
