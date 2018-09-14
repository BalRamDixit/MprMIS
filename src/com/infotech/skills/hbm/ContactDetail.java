package com.infotech.skills.hbm;

import java.io.Serializable;

public class ContactDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contactId;
	private String officerName;
	private String phOffice;
	private String faxOffice;
	private String phResidence;
	private String email;
	private String designation;
	private String entityCode;
	private String order;
	private String stateName;

	public ContactDetail() {
	}

	public ContactDetail(String contactId) {
		this.contactId = contactId;
	}

	public ContactDetail(String contactId, String officerName, String phOffice,
			String faxOffice, String phResidence, String email,
			String designation, String entityCode, String order,
			String stateName) {
		this.contactId = contactId;
		this.officerName = officerName;
		this.phOffice = phOffice;
		this.faxOffice = faxOffice;
		this.phResidence = phResidence;
		this.email = email;
		this.designation = designation;
		this.entityCode = entityCode;
		this.order = order;
		this.stateName = stateName;
	}

	public String getContactId() {
		return this.contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getOfficerName() {
		return this.officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getPhOffice() {
		return this.phOffice;
	}

	public void setPhOffice(String phOffice) {
		this.phOffice = phOffice;
	}

	public String getFaxOffice() {
		return this.faxOffice;
	}

	public void setFaxOffice(String faxOffice) {
		this.faxOffice = faxOffice;
	}

	public String getPhResidence() {
		return this.phResidence;
	}

	public void setPhResidence(String phResidence) {
		this.phResidence = phResidence;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEntityCode() {
		return this.entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
