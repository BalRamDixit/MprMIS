package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

public class InfrastructureVO implements Serializable{
	
    private int id;
	private String  exsitingCapDistricts;
	private String  exsitingCapActiveTrainingCenters;
	private String  exsitingCapTotalTraingSpace;
	private String  exsitingCapPracticalsLabs;
	private String  propsedCapDistricts ;
	private String  propsedCapActiveTrainingCenters;
	private String  propsedCapTotalTrainingSpace;
	private String  propsedCapPracticalLabs;
	private String  createdOn ;
	private String  createdBy ;
	private String  modifyOn ;
	private String  modifyBy;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setExsitingCapDistricts(String exsitingCapDistricts) {
		this.exsitingCapDistricts = exsitingCapDistricts;
	}
	public String getExsitingCapDistricts() {
		return exsitingCapDistricts;
	}
	public void setExsitingCapActiveTrainingCenters(
			String exsitingCapActiveTrainingCenters) {
		this.exsitingCapActiveTrainingCenters = exsitingCapActiveTrainingCenters;
	}
	public String getExsitingCapActiveTrainingCenters() {
		return exsitingCapActiveTrainingCenters;
	}
	public void setExsitingCapTotalTraingSpace(
			String exsitingCapTotalTraingSpace) {
		this.exsitingCapTotalTraingSpace = exsitingCapTotalTraingSpace;
	}
	public String getExsitingCapTotalTraingSpace() {
		return exsitingCapTotalTraingSpace;
	}
	public void setExsitingCapPracticalsLabs(String exsitingCapPracticalsLabs) {
		this.exsitingCapPracticalsLabs = exsitingCapPracticalsLabs;
	}
	public String getExsitingCapPracticalsLabs() {
		return exsitingCapPracticalsLabs;
	}
	public void setPropsedCapActiveTrainingCenters(
			String propsedCapActiveTrainingCenters) {
		this.propsedCapActiveTrainingCenters = propsedCapActiveTrainingCenters;
	}
	public String getPropsedCapActiveTrainingCenters() {
		return propsedCapActiveTrainingCenters;
	}
	public void setPropsedCapTotalTrainingSpace(
			String propsedCapTotalTrainingSpace) {
		this.propsedCapTotalTrainingSpace = propsedCapTotalTrainingSpace;
	}
	public String getPropsedCapTotalTrainingSpace() {
		return propsedCapTotalTrainingSpace;
	}
	public void setPropsedCapDistricts(String propsedCapDistricts) {
		this.propsedCapDistricts = propsedCapDistricts;
	}
	public String getPropsedCapDistricts() {
		return propsedCapDistricts;
	}
	public void setPropsedCapPracticalLabs(String propsedCapPracticalLabs) {
		this.propsedCapPracticalLabs = propsedCapPracticalLabs;
	}
	public String getPropsedCapPracticalLabs() {
		return propsedCapPracticalLabs;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	

}
