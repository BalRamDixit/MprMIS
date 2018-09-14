package com.infotech.sgsy.master.assessmentBodyMaster;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class AssessmentBodyBean extends ValidatorForm{
	
		private String id;
		private String assBodyName;
		private String assBodyCode;
		private String editId;
		
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
		public String getAssBodyName() {
			return assBodyName;
		}
		public void setAssBodyName(String assBodyName) {
			this.assBodyName = assBodyName;
		}
		public String getAssBodyCode() {
			return assBodyCode;
		}
		public void setAssBodyCode(String assBodyCode) {
			this.assBodyCode = assBodyCode;
		}
		public String getEditId() {
			return editId;
		}
		public void setEditId(String editId) {
			this.editId = editId;
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

