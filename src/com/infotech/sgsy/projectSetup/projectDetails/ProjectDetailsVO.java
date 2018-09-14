package com.infotech.sgsy.projectSetup.projectDetails;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.util.DateUtil;

public class ProjectDetailsVO implements Serializable  {
	
	@Override
	public String toString() {
		return "ProjectDetailsVO [projectID=" + projectID + "]";
	}
	public String id;

	public String fileNumber;
	
	private UserMaster piaPrn;
	
	private String projectScheme;
	private CtsaMasterVO ctsaMaster;
	private String projectID;
	private long totalProjectCost;
	private long centralShare;
	private long stateShare;
	private long programCost;
	private Integer welfareCost;
	private String consortium;
	private UserMaster prnOfConsortiumPartner;
	private String partnerApplicantPiaSame;
	private Date dateOfEc;	
	private String sanctioned;	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	private StateVO state;
	
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
	public String getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	
	public String getProjectScheme() {
		return projectScheme;
	}
	public void setProjectScheme(String projectScheme) {
		this.projectScheme = projectScheme;
	}
	
	
	
	
	
	public long getTotalProjectCost() {
		return totalProjectCost;
	}
	public void setTotalProjectCost(Long totalProjectCost) {
		if(totalProjectCost==null){
			totalProjectCost=0L;
		}
		this.totalProjectCost = totalProjectCost;
	}
	public long getCentralShare() {
		return centralShare;
	}
	public void setCentralShare(Long centralShare) {
		if(centralShare==null){
			centralShare=0L;
		}
		this.centralShare = centralShare;
	}
	public long getStateShare() {
		return stateShare;
	}
	public void setStateShare(Long stateShare) {
		if(stateShare==null){
			stateShare=0L;
		}
		this.stateShare = stateShare;
	}
	public long getProgramCost() {
		return programCost;
	}
	public void setProgramCost(Long programCost) {
		if(programCost==null){
			programCost=0L;
		}
		this.programCost = programCost;
	}
	public CtsaMasterVO getCtsaMaster() {
		return ctsaMaster;
	}
	public void setCtsaMaster(CtsaMasterVO ctsaMaster) {
		this.ctsaMaster = ctsaMaster;
	}
	/*public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}*/
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	
	 
	
	
	
	public String getConsortium() {
		return consortium;
	}
	public void setConsortium(String consortium) {
		this.consortium = consortium;
	}
	
	public String getSanctioned() {
		return sanctioned;
	}
	public void setSanctioned(String sanctioned) {
		this.sanctioned = sanctioned;
	}
	public String getPartnerApplicantPiaSame() {
		return partnerApplicantPiaSame;
	}
	public void setPartnerApplicantPiaSame(String partnerApplicantPiaSame) {
		this.partnerApplicantPiaSame = partnerApplicantPiaSame;
	}
	
	public Date getDateOfEc() {
		return dateOfEc;
	}
	public void setDateOfEc(Object dateOfEc) {
		if(dateOfEc instanceof String){
			this.dateOfEc =new DateUtil().Correct(dateOfEc.toString());
			}else{
				this.dateOfEc=(Date) dateOfEc;
			}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getWelfareCost() {
		return welfareCost;
	}
	public void setWelfareCost(Integer welfareCost) {
		this.welfareCost = welfareCost;
	}
	public UserMaster getPiaPrn() {
		return piaPrn;
	}
	public void setPiaPrn(UserMaster piaPrn) {
		this.piaPrn = piaPrn;
	}
	public UserMaster getPrnOfConsortiumPartner() {
		return prnOfConsortiumPartner;
	}
	public void setPrnOfConsortiumPartner(UserMaster prnOfConsortiumPartner) {
		this.prnOfConsortiumPartner = prnOfConsortiumPartner;
	}
	public StateVO getState() {
		return state;
	}
	public void setState(StateVO state) {
		this.state = state;
	}
 
	

}
