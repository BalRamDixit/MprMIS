package com.infotech.skills.hbm.piaprofile;
 
import java.io.Serializable;

import com.infotech.skills.master.MasterVO;


public class CategoryVO extends MasterVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * @since August 2013  
	 */
	private String categoryId; 
	private String categoryName;
	private String formType;
	private String categoryStatus;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
}
