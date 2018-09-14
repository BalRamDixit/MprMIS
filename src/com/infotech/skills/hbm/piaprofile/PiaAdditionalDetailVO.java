package com.infotech.skills.hbm.piaprofile;
 
import java.io.Serializable;

import com.infotech.skills.master.MasterVO;

public class PiaAdditionalDetailVO extends MasterVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @since  August 2013  
	 */
	 private String piaCode;
     private String sec12aRegistrationNo;
     private String sec12aRegistrationDate;
     private String sec80gRegistrationNo;
     private String sec80gRegistrationDate;
     private String fcraRegistrationNo;
     private String fcraRegistrationDate;
	
	public String getPiaCode() {
		return piaCode;
	}

	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}

	public String getSec12aRegistrationNo() {
		return sec12aRegistrationNo;
	}

	public void setSec12aRegistrationNo(String sec12aRegistrationNo) {
		this.sec12aRegistrationNo = sec12aRegistrationNo;
	}

	public String getSec12aRegistrationDate() {
		return sec12aRegistrationDate;
	}

	public void setSec12aRegistrationDate(String sec12aRegistrationDate) {
		this.sec12aRegistrationDate = sec12aRegistrationDate;
	}

	public String getSec80gRegistrationNo() {
		return sec80gRegistrationNo;
	}

	public void setSec80gRegistrationNo(String sec80gRegistrationNo) {
		this.sec80gRegistrationNo = sec80gRegistrationNo;
	}

	public String getSec80gRegistrationDate() {
		return sec80gRegistrationDate;
	}

	public void setSec80gRegistrationDate(String sec80gRegistrationDate) {
		this.sec80gRegistrationDate = sec80gRegistrationDate;
	}

	public String getFcraRegistrationNo() {
		return fcraRegistrationNo;
	}

	public void setFcraRegistrationNo(String fcraRegistrationNo) {
		this.fcraRegistrationNo = fcraRegistrationNo;
	}

	public String getFcraRegistrationDate() {
		return fcraRegistrationDate;
	}

	public void setFcraRegistrationDate(String fcraRegistrationDate) {
		this.fcraRegistrationDate = fcraRegistrationDate;
	}
     
      	 
}
