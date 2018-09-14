package com.infotech.sgsy.common;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;



/**
 * 
 * @author 51512
 * Date:
 */
public class MasterForm extends ValidatorForm{
	
	private String createdOn;
	private String lastModifiedOn;
	private String createdBy;
	private String lastModifedBy;
	private Date updatedOn;
	private String updatedBy;
	private String propertyCode;
	private String propertyValue;
	private String finYr ;
	private String finYrDisp ;
	private String stateCode;
	private String districtCode;
	
	//Added by CVAS2273
	private String yearLimit ;
	private String startYear ;
	
	private boolean  isInformationDialog = false;
	private String informationDialogText = null;
	private String informationDialogHeader = null;
	
	//*********//
	String uploadCircularCode;
	String uploadCircularName;
	String uploadCircularDate;
	String serverDate;
	FormFile UploadCircularFile=null;
	
	public String getUploadCircularCode() {
		return uploadCircularCode;
	}
	public void setUploadCircularCode(String uploadCircularCode) {
		this.uploadCircularCode = uploadCircularCode;
	}
	public String getUploadCircularName() {
		return uploadCircularName;
	}
	public void setUploadCircularName(String uploadCircularName) {
		this.uploadCircularName = uploadCircularName;
	}
	public String getUploadCircularDate() {
		return uploadCircularDate;
	}
	public void setUploadCircularDate(String uploadCircularDate) {
		this.uploadCircularDate = uploadCircularDate;
	}
	public String getServerDate() {
		return serverDate;
	}
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
	public FormFile getUploadCircularFile() {
		return UploadCircularFile;
	}
	public void setUploadCircularFile(FormFile uploadCircularFile) {
		UploadCircularFile = uploadCircularFile;
	}
	private String viewFor;
	
	
	public String getViewFor() {
		return viewFor;
	}
	public void setViewFor(String viewFor) {
		this.viewFor = viewFor;
	}
	public void reset() {		
		isInformationDialog = false;
		informationDialogHeader = null;
		informationDialogText = null;

	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastModifedBy() {
		return lastModifedBy;
	}
	public void setLastModifedBy(String lastModifedBy) {
		this.lastModifedBy = lastModifedBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(String lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public boolean isInformationDialog() {
		return isInformationDialog;
	}
	public void setInformationDialog(boolean isInformationDialog) {
		this.isInformationDialog = isInformationDialog;
	}
	public String getInformationDialogText() {
		return informationDialogText;
	}
	public void setInformationDialogText(String informationDialogText) {
		this.informationDialogText = informationDialogText;
	}
	public String getInformationDialogHeader() {
		return informationDialogHeader;
	}
	public void setInformationDialogHeader(String informationDialogHeader) {
		this.informationDialogHeader = informationDialogHeader;
	}
	public String getFinYr() {
		return finYr;
	}
	public void setFinYr(String finYr) {
		this.finYr = finYr;
	}
	public String getFinYrDisp() {
		return finYrDisp;
	}
	public void setFinYrDisp(String finYrDisp) {
		this.finYrDisp = finYrDisp;
	}
	public String getYearLimit() {
		return yearLimit;
	}
	public void setYearLimit(String yearLimit) {
		this.yearLimit = yearLimit;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	
	
}