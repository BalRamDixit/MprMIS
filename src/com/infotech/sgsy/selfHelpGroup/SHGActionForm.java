package com.infotech.sgsy.selfHelpGroup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;

public class SHGActionForm extends MasterForm{

	private String shgCode;	
	private String shgType;
	private String villageCode;	// Not in use
	private String entityCode;
	private String groupName;
	private String formationdate;
	private String promotedBy;
	private String promoterName;
	//Bank Details
	private String bankCode;
	private String bankName;
	private String bankBranchCode;
	private String bankBranchName;
	private String bankAccountNo;
	private String opDateofAccount;
	private String microPlanPrepared;
	private String basicShgTraining;
	private String standardBookkeepingPractices;
	private String bookkeeperIdentified;
	private String meetingFrequency;
	private String bankLinkageHappened;
	private int loanRunning;
	private int usualAmountOfSaving;
	
	// Member Details
	private String[] shgMemberCode;
	private String[] memberName;
	private String[] belongingName;
	private String[] gender;
	private String[] socialCategory;
	private String[] disability;
	private String[] religion;
	private String[] aplBpl;
	private String[] pipCategory;
	private String[] leader;
	private String[] aadhaar;
	private String[] seccNo;
	private String[] mobile;
	private String[] createdMembOn;
	private String[] createdMembBy;
	
	public String[] getShgMemberCode() {
		return shgMemberCode;
	}
	public void setShgMemberCode(String[] shgMemberCode) {
		this.shgMemberCode = shgMemberCode;
	}
	public String getShgCode() {
		return shgCode;
	}
	public void setShgCode(String shgCode) {
		this.shgCode = shgCode;
	}
	public String getShgType() {
		return shgType;
	}
	public void setShgType(String shgType) {
		this.shgType = shgType;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getFormationdate() {
		return formationdate;
	}
	public void setFormationdate(String formationdate) {
		this.formationdate = formationdate;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}	
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getPromotedBy() {
		return promotedBy;
	}
	public void setPromotedBy(String promotedBy) {
		this.promotedBy = promotedBy;
	}
	public String getPromoterName() {
		return promoterName;
	}
	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}
	public String getOpDateofAccount() {
		return opDateofAccount;
	}
	public void setOpDateofAccount(String opDateofAccount) {
		this.opDateofAccount = opDateofAccount;
	}
	public String getMicroPlanPrepared() {
		return microPlanPrepared;
	}
	public void setMicroPlanPrepared(String microPlanPrepared) {
		this.microPlanPrepared = microPlanPrepared;
	}
	public String getBasicShgTraining() {
		return basicShgTraining;
	}
	public void setBasicShgTraining(String basicShgTraining) {
		this.basicShgTraining = basicShgTraining;
	}
	public String getStandardBookkeepingPractices() {
		return standardBookkeepingPractices;
	}
	public void setStandardBookkeepingPractices(String standardBookkeepingPractices) {
		this.standardBookkeepingPractices = standardBookkeepingPractices;
	}
	public String getBookkeeperIdentified() {
		return bookkeeperIdentified;
	}
	public void setBookkeeperIdentified(String bookkeeperIdentified) {
		this.bookkeeperIdentified = bookkeeperIdentified;
	}
	public String getMeetingFrequency() {
		return meetingFrequency;
	}
	public void setMeetingFrequency(String meetingFrequency) {
		this.meetingFrequency = meetingFrequency;
	}
	public String getBankLinkageHappened() {
		return bankLinkageHappened;
	}
	public void setBankLinkageHappened(String bankLinkageHappened) {
		this.bankLinkageHappened = bankLinkageHappened;
	}
	public int getLoanRunning() {
		return loanRunning;
	}
	public void setLoanRunning(int loanRunning) {
		this.loanRunning = loanRunning;
	}
	public int getUsualAmountOfSaving() {
		return usualAmountOfSaving;
	}
	public void setUsualAmountOfSaving(int usualAmountOfSaving) {
		this.usualAmountOfSaving = usualAmountOfSaving;
	}
	
	public String[] getMemberName() {
		return memberName;
	}
	public void setMemberName(String[] memberName) {
		this.memberName = memberName;
	}
	public String[] getBelongingName() {
		return belongingName;
	}
	public void setBelongingName(String[] belongingName) {
		this.belongingName = belongingName;
	}
	public String[] getGender() {
		return gender;
	}
	public void setGender(String[] gender) {
		this.gender = gender;
	}
	public String[] getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(String[] socialCategory) {
		this.socialCategory = socialCategory;
	}
	public String[] getDisability() {
		return disability;
	}
	public void setDisability(String[] disability) {
		this.disability = disability;
	}
	public String[] getReligion() {
		return religion;
	}
	public void setReligion(String[] religion) {
		this.religion = religion;
	}
	public String[] getAplBpl() {
		return aplBpl;
	}
	public void setAplBpl(String[] aplBpl) {
		this.aplBpl = aplBpl;
	}
	public String[] getPipCategory() {
		return pipCategory;
	}
	public void setPipCategory(String[] pipCategory) {
		this.pipCategory = pipCategory;
	}
	public String[] getLeader() {
		return leader;
	}
	public void setLeader(String[] leader) {
		this.leader = leader;
	}
	public String[] getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String[] aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String[] getSeccNo() {
		return seccNo;
	}
	public void setSeccNo(String[] seccNo) {
		this.seccNo = seccNo;
	}
	public String[] getMobile() {
		return mobile;
	}
	public void setMobile(String[] mobile) {
		this.mobile = mobile;
	}
	public String[] getCreatedMembOn() {
		return createdMembOn;
	}
	public void setCreatedMembOn(String[] createdMembOn) {
		this.createdMembOn = createdMembOn;
	}
	public String[] getCreatedMembBy() {
		return createdMembBy;
	}
	public void setCreatedMembBy(String[] createdMembBy) {
		this.createdMembBy = createdMembBy;
	}
	// Used to reset all fields
	public void reset(ActionMapping mapping, 
 			HttpServletRequest request) {

		// Reset SHG Details
		this.setVillageCode(null);
		this.setEntityCode(null);
		this.setShgType(null);
		this.setGroupName(null);
		this.setFormationdate(null);
		this.setPromotedBy(null);
		this.setPromoterName(null);
		this.setBankCode(null);
		this.setBankBranchCode(null);
		this.setBankAccountNo(null);
		this.setOpDateofAccount(null);
		this.setMicroPlanPrepared(null);
		this.setBasicShgTraining(null);
		this.setStandardBookkeepingPractices(null);
		this.setBookkeeperIdentified(null);
		this.setMeetingFrequency(null);
		this.setBankLinkageHappened(null);
		this.setLoanRunning(0);
		this.setUsualAmountOfSaving(0);
		
		// Reset All SHG Member Details
		this.setMemberName(null);
		this.setBelongingName(null);
		this.setMobile(null);
		this.setSocialCategory(null);
		this.setDisability(null);
		this.setReligion(null);
		this.setAplBpl(null);
		this.setPipCategory(null);
		this.setLeader(null);
		this.setAadhaar(null);
		this.setSeccNo(null);
		this.setCreatedMembBy(null);
		this.setCreatedMembOn(null);
	}

	
}
