package com.infotech.sgsy.stateSetupTarget;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.appraisalAgencyMaster.AppraisalAgencyVO;
import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.master.tsaMaster.TsaMasterVO;
import com.infotech.sgsy.util.DateUtil;

public class StateTargetVO  implements Serializable {
	
	    private String id;
	   /* private String stateId;*/
	    private StateVO state;
		private String userId;
			
		private String scheme;
		private Date annualPlan;
		private CtsaMasterVO ctsa;
		private AppraisalAgencyVO appraisalAgency;
		private TsaMasterVO tsaName;
		private String stateMis;
		private String applicationName;
		private String sopCompliant;
		private String centralMis;
		private String dateformat;
		
		private String createdBy;
		private String updatedBy;
		private Date createdOn;
		private Date updatedOn;
		
		
		
		public String getDateformat() {
			return dateformat;
		}
		public void setDateformat(String dateformat) {
			this.dateformat = dateformat;
		}
		public StateVO getState() {
			return state;
		}
		public void setState(StateVO state) {
			this.state = state;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
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
		
		public Date getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}
		public Date getUpdatedOn() {
			return updatedOn;
		}
		public void setUpdatedOn(Date updatedOn) {
			this.updatedOn = updatedOn;
		}
		
		
		/*public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}*/
	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getScheme() {
			return scheme;
		}
		public void setScheme(String scheme) {
			this.scheme = scheme;
		}
		
		
		public CtsaMasterVO getCtsa() {
			return ctsa;
		}
		public void setCtsa(CtsaMasterVO ctsa) {
			this.ctsa = ctsa;
		}
		
		public AppraisalAgencyVO getAppraisalAgency() {
			return appraisalAgency;
		}
		public void setAppraisalAgency(AppraisalAgencyVO appraisalAgency) {
			this.appraisalAgency = appraisalAgency;
		}
		public TsaMasterVO getTsaName() {
			return tsaName;
		}
		public void setTsaName(TsaMasterVO tsaName) {
			this.tsaName = tsaName;
		}
		public String getStateMis() {
			return stateMis;
		}
		public void setStateMis(String stateMis) {
			this.stateMis = stateMis;
		}
		public String getApplicationName() {
			return applicationName;
		}
		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}
		public String getSopCompliant() {
			return sopCompliant;
		}
		public void setSopCompliant(String sopCompliant) {
			this.sopCompliant = sopCompliant;
		}
		public String getCentralMis() {
			return centralMis;
		}
		public void setCentralMis(String centralMis) {
			this.centralMis = centralMis;
		}
		public Date getAnnualPlan() {
			return annualPlan;
		}
		public void setAnnualPlan(Object annualPlan) {
			if(annualPlan instanceof String){
				this.annualPlan = new DateUtil().Correct(annualPlan.toString());
			}
			else{
				this.annualPlan =(Date) annualPlan;
			}
			
		}
	

}
