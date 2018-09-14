package com.infotech.skills.candidateStatistics;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class CandidateVO implements Serializable {

	private String id;
	private ProjectDetailsVO project;
	private String month;
	private int year;

	private Integer total_no_of_can;
	private Integer total_no_of_can_with_Adhar_no;
	private Integer total_no_of_can_Bank_acc;
	private Integer total_no_of_can_Mobile_no;
	private Integer total_no_can_aadhar_lin_bank_acc;
	private Integer no_of_can_insurance;
	private Integer pro_life_trained_can;
	private Integer foreign_placed;
	private Integer can_place_earn_more;
	private Integer can_retained;
	private Integer placed_documented_sub_upload;

	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjectDetailsVO getProject() {
		return project;
	}

	public void setProject(ProjectDetailsVO project) {
		this.project = project;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getTotal_no_of_can() {
		return total_no_of_can;
	}

	public void setTotal_no_of_can(Integer total_no_of_can) {
		this.total_no_of_can = total_no_of_can;
	}

	public Integer getTotal_no_of_can_with_Adhar_no() {
		return total_no_of_can_with_Adhar_no;
	}

	public void setTotal_no_of_can_with_Adhar_no(Integer total_no_of_can_with_Adhar_no) {
		this.total_no_of_can_with_Adhar_no = total_no_of_can_with_Adhar_no;
	}

	public Integer getTotal_no_of_can_Bank_acc() {
		return total_no_of_can_Bank_acc;
	}

	public void setTotal_no_of_can_Bank_acc(Integer total_no_of_can_Bank_acc) {
		this.total_no_of_can_Bank_acc = total_no_of_can_Bank_acc;
	}

	public Integer getTotal_no_of_can_Mobile_no() {
		return total_no_of_can_Mobile_no;
	}

	public void setTotal_no_of_can_Mobile_no(Integer total_no_of_can_Mobile_no) {
		this.total_no_of_can_Mobile_no = total_no_of_can_Mobile_no;
	}

	public Integer getTotal_no_can_aadhar_lin_bank_acc() {
		return total_no_can_aadhar_lin_bank_acc;
	}

	public void setTotal_no_can_aadhar_lin_bank_acc(Integer total_no_can_aadhar_lin_bank_acc) {
		this.total_no_can_aadhar_lin_bank_acc = total_no_can_aadhar_lin_bank_acc;
	}

	public Integer getNo_of_can_insurance() {
		return no_of_can_insurance;
	}

	public void setNo_of_can_insurance(Integer no_of_can_insurance) {
		this.no_of_can_insurance = no_of_can_insurance;
	}

	public Integer getPro_life_trained_can() {
		return pro_life_trained_can;
	}

	public void setPro_life_trained_can(Integer pro_life_trained_can) {
		this.pro_life_trained_can = pro_life_trained_can;
	}

	public Integer getForeign_placed() {
		return foreign_placed;
	}

	public void setForeign_placed(Integer foreign_placed) {
		this.foreign_placed = foreign_placed;
	}

	public Integer getCan_place_earn_more() {
		return can_place_earn_more;
	}

	public void setCan_place_earn_more(Integer can_place_earn_more) {
		this.can_place_earn_more = can_place_earn_more;
	}

	public Integer getCan_retained() {
		return can_retained;
	}

	public void setCan_retained(Integer can_retained) {
		this.can_retained = can_retained;
	}

	public Integer getPlaced_documented_sub_upload() {
		return placed_documented_sub_upload;
	}

	public void setPlaced_documented_sub_upload(Integer placed_documented_sub_upload) {
		this.placed_documented_sub_upload = placed_documented_sub_upload;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		String bb = "{\"projectId\":\"" + this.project.getProjectID() + "\",\"total_no_of_can\":" + this.total_no_of_can
				+ ",\"total_no_of_can_with_Adhar_no\":" + this.total_no_of_can_with_Adhar_no + ","
				+ "\"total_no_of_can_Bank_acc\":" + this.total_no_of_can_Bank_acc + ",\"total_no_of_can_Mobile_no\":"
				+ this.total_no_of_can_Mobile_no + "," + "\"total_no_can_aadhar_lin_bank_acc\":"
				+ this.total_no_can_aadhar_lin_bank_acc + ",\"no_of_can_insurance\":" + this.no_of_can_insurance + ","
				+ "\"pro_life_trained_can\":" + this.pro_life_trained_can + ",\"foreign_placed\":" + this.foreign_placed
				+ "," + "\"can_place_earn_more\":" + this.can_place_earn_more + ",\"can_retained\":" + this.can_retained
				+ "," + "\"placed_documented_sub_upload\":" + this.placed_documented_sub_upload + "}";

		return bb;
	}

}
