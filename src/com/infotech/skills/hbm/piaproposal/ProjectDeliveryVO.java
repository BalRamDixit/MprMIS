package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

public class ProjectDeliveryVO implements Serializable{
	
	private String piaCode;
	private String praposalCode;
	private String candidateSocial;
	private String candidateSelection;
	private String hiringTrainers;
	private String trainingInfra;
	private String trainersTraining;
	private String traineesTraining ;
	private String traineesAssessment;
	private String traineesCertification;
	private String traineesPlacement;
	private String postTracking;
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	
	public ProjectDeliveryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public ProjectDeliveryVO(String piaCode, String praposalCode,
			String candidateSocial, String candidateSelection,
			String hiringTrainers, String trainingInfra,
			String trainersTraining, String traineesTraining,
			String traineesAssessment, String traineesCertification,
			String traineesPlacement, String postTracking, String createdOn,
			String createdBy, String modifyOn, String modifyBy) {
		super();
		this.piaCode = piaCode;
		this.praposalCode = praposalCode;
		this.candidateSocial = candidateSocial;
		this.candidateSelection = candidateSelection;
		this.hiringTrainers = hiringTrainers;
		this.trainingInfra = trainingInfra;
		this.trainersTraining = trainersTraining;
		this.traineesTraining = traineesTraining;
		this.traineesAssessment = traineesAssessment;
		this.traineesCertification = traineesCertification;
		this.traineesPlacement = traineesPlacement;
		this.postTracking = postTracking;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifyOn = modifyOn;
		this.modifyBy = modifyBy;
	}

	public String getPiaCode() {
		return piaCode;
	}

	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}

	public String getPraposalCode() {
		return praposalCode;
	}

	public void setPraposalCode(String praposalCode) {
		this.praposalCode = praposalCode;
	}

	public String getCandidateSocial() {
		return candidateSocial;
	}
	public void setCandidateSocial(String candidateSocial) {
		this.candidateSocial = candidateSocial;
	}
	public String getCandidateSelection() {
		return candidateSelection;
	}
	public void setCandidateSelection(String candidateSelection) {
		this.candidateSelection = candidateSelection;
	}
	public String getHiringTrainers() {
		return hiringTrainers;
	}
	public void setHiringTrainers(String hiringTrainers) {
		this.hiringTrainers = hiringTrainers;
	}
	public String getTrainingInfra() {
		return trainingInfra;
	}
	public void setTrainingInfra(String trainingInfra) {
		this.trainingInfra = trainingInfra;
	}
	public String getTrainersTraining() {
		return trainersTraining;
	}
	public void setTrainersTraining(String trainersTraining) {
		this.trainersTraining = trainersTraining;
	}
	public String getTraineesTraining() {
		return traineesTraining;
	}
	public void setTraineesTraining(String traineesTraining) {
		this.traineesTraining = traineesTraining;
	}
	public String getTraineesAssessment() {
		return traineesAssessment;
	}
	public void setTraineesAssessment(String traineesAssessment) {
		this.traineesAssessment = traineesAssessment;
	}
	public String getTraineesCertification() {
		return traineesCertification;
	}
	public void setTraineesCertification(String traineesCertification) {
		this.traineesCertification = traineesCertification;
	}
	public String getTraineesPlacement() {
		return traineesPlacement;
	}
	public void setTraineesPlacement(String traineesPlacement) {
		this.traineesPlacement = traineesPlacement;
	}
	public String getPostTracking() {
		return postTracking;
	}
	public void setPostTracking(String postTracking) {
		this.postTracking = postTracking;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
}
