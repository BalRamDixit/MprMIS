package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

import sun.security.util.BigInt;

public class Training implements Serializable{
	
	private BigInt id;
	private int biometricMachines;
	private String trainerTraineeRatio;
	private String trainingSchedule;
	private String trainingMethodology;
	private String beneficiariesTraining;
	private String internalMethodology;
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Training(BigInt id, int biometricMachines,
			String trainerTraineeRatio, String trainingSchedule,
			String trainingMethodology, String beneficiariesTraining,
			String internalMethodology, String createdOn, String createdBy,
			String modifyOn, String modifyBy) {
		super();
		this.id = id;
		this.biometricMachines = biometricMachines;
		this.trainerTraineeRatio = trainerTraineeRatio;
		this.trainingSchedule = trainingSchedule;
		this.trainingMethodology = trainingMethodology;
		this.beneficiariesTraining = beneficiariesTraining;
		this.internalMethodology = internalMethodology;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifyOn = modifyOn;
		this.modifyBy = modifyBy;
	}

	public String getTrainingSchedule() {
		return trainingSchedule;
	}

	public void setTrainingSchedule(String trainingSchedule) {
		this.trainingSchedule = trainingSchedule;
	}
	
	public int getBiometricMachines() {
		return biometricMachines;
	}

	public void setBiometricMachines(int biometricMachines) {
		this.biometricMachines = biometricMachines;
	}

	public String getTrainerTraineeRatio() {
		return trainerTraineeRatio;
	}
	public void setTrainerTraineeRatio(String trainerTraineeRatio) {
		this.trainerTraineeRatio = trainerTraineeRatio;
	}
	public String getTrainingMethodology() {
		return trainingMethodology;
	}
	public void setTrainingMethodology(String trainingMethodology) {
		this.trainingMethodology = trainingMethodology;
	}
	public String getBeneficiariesTraining() {
		return beneficiariesTraining;
	}
	public void setBeneficiariesTraining(String beneficiariesTraining) {
		this.beneficiariesTraining = beneficiariesTraining;
	}
	public String getInternalMethodology() {
		return internalMethodology;
	}
	public void setInternalMethodology(String internalMethodology) {
		this.internalMethodology = internalMethodology;
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

	public void setId(BigInt id) {
		this.id = id;
	}

	public BigInt getId() {
		return id;
	}

}
