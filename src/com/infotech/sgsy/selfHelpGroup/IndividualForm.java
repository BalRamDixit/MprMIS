package com.infotech.sgsy.selfHelpGroup;

import com.infotech.sgsy.common.MasterForm;

public class IndividualForm extends MasterForm{

	
	private String shgCode;
	private String entityCode;
	private String finYr;
	private String groupName;
	private String localRegno;
	private String[] formationdate;
	private String[] allActivityCode  ;
	private String[] selectedActivityCode;
	private String[] bankCode;
	private String[] bankBranchCode;
	private String[] bankAccountNo;
	private String[] openingDateofAccount;;
	private String villageCode;
	private String villageC;
	private String addressOfVillage;
	private String contectNoOfGroup;
	private String[] familyCode;
	private String[] aplBpl;
	private String[] aplBplCode;
	private String[] memberName;
	private String[] designation;
	private String[] remark;
	
	
	public void reset() {	
		super.reset();	
		shgCode=null;
		entityCode=null;
		finYr=null;
		groupName=null;
		localRegno=null;
		formationdate=null;
		allActivityCode  =null;
		selectedActivityCode=null;
		bankCode=null;
		bankBranchCode=null;
		bankAccountNo=null;
		openingDateofAccount=null;;
		villageCode=null;
		addressOfVillage=null;
		contectNoOfGroup=null;
		familyCode=null;
		aplBpl=null;
		aplBplCode=null;
		memberName=null;
		designation=null;
		remark=null;
	}
	
	public String getShgCode() {
		return shgCode;
	}
	public void setShgCode(String shgCode) {
		this.shgCode = shgCode;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getFinYr() {
		return finYr;
	}
	public void setFinYr(String finYr) {
		this.finYr = finYr;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getLocalRegno() {
		return localRegno;
	}
	public void setLocalRegno(String localRegno) {
		this.localRegno = localRegno;
	}
	public String[] getFormationdate() {
		return formationdate;
	}
	public void setFormationdate(String[] formationdate) {
		this.formationdate = formationdate;
	}
	public String[] getAllActivityCode() {
		return allActivityCode;
	}
	public void setAllActivityCode(String[] allActivityCode) {
		this.allActivityCode = allActivityCode;
	}
	public String[] getSelectedActivityCode() {
		return selectedActivityCode;
	}
	public void setSelectedActivityCode(String[] selectedActivityCode) {
		this.selectedActivityCode = selectedActivityCode;
	}
	public String[] getBankCode() {
		return bankCode;
	}
	public void setBankCode(String[] bankCode) {
		this.bankCode = bankCode;
	}
	public String[] getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String[] bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String[] getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String[] bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String[] getOpeningDateofAccount() {
		return openingDateofAccount;
	}
	public void setOpeningDateofAccount(String[] openingDateofAccount) {
		this.openingDateofAccount = openingDateofAccount;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getAddressOfVillage() {
		return addressOfVillage;
	}
	public void setAddressOfVillage(String addressOfVillage) {
		this.addressOfVillage = addressOfVillage;
	}
	public String getContectNoOfGroup() {
		return contectNoOfGroup;
	}
	public void setContectNoOfGroup(String contectNoOfGroup) {
		this.contectNoOfGroup = contectNoOfGroup;
	}
	public String[] getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String[] familyCode) {
		this.familyCode = familyCode;
	}
	public String[] getAplBpl() {
		return aplBpl;
	}
	public void setAplBpl(String[] aplBpl) {
		this.aplBpl = aplBpl;
	}
	public String[] getAplBplCode() {
		return aplBplCode;
	}
	public void setAplBplCode(String[] aplBplCode) {
		this.aplBplCode = aplBplCode;
	}
	public String[] getMemberName() {
		return memberName;
	}
	public void setMemberName(String[] memberName) {
		this.memberName = memberName;
	}
	public String[] getDesignation() {
		return designation;
	}
	public void setDesignation(String[] designation) {
		this.designation = designation;
	}
	public String[] getRemark() {
		return remark;
	}
	public void setRemark(String[] remark) {
		this.remark = remark;
	}

	public String getVillageC() {
		return villageC;
	}

	public void setVillageC(String villageC) {
		this.villageC = villageC;
	}
}
