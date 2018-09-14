package com.infotech.sgsy.stateFinance;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.stateSanctionDetail.StateSanctionVO;
import com.infotech.sgsy.util.DateUtil;

public class StateFinancialVO implements Serializable{

	private String id;
	private StateSanctionVO sanctionDetailId;
	
	private long centralProgramCost;
	private long centralSuportCost;
	private long centralCtsa;
	private long centralAdminCost;
	private long centralCapacityCost;
	
	private long stateProgramCost;
	private long stateSuportCost;
	private long stateCtsa;
	private long stateAdminCost;
	private long stateCapacityCost;
	
	private long centralReleasedProgramCost;
	private long centralReleasedSuportCost;
	private long centralReleasedCtsa;
	private long centralReleasedAdminCost;
	private long centralReleasedCapacityCost;
	
	private long centralOnlyProgramCost;
	private long centralOnlySuportCost;
	private long centralOnlyCtsa;
    private long centralOnlyAdminCost;
	private long centralOnlyCapacityCost;
	
	// date codes...
	private Date centralReleasedDateProgramCost;
	
	private Date centralReleasedDatSuportCost;
	private Date centralReleasedDatCtsa;
	private Date centralReleasedDatAdminCost;
	private Date centralReleasedDatCapacityCost;
    private Date stateReleasedDateProgramCost;
	private Date stateReleasedDateSuportCost;
	private Date stateReleasedDateCtsa;
	private Date stateReleasedDateAdminCost;
	private Date stateReleasedDateCapacityCost;
		//  date ends here
	private long stateOnlyProgramCost;
	private long stateOnlySuportCost;
	private long stateOnlyCtsa;
	private long stateOnlyAdminCost;
	private long stateOnlyCapacityCost;
	//new-added-columns
	private long central_totalprojectcost;
    private long central_otherprojectcost;
	private long central_totalcost;
	
	private long state_totalprojectcost;
    private long state_otherprojectcost;
	private long state_totalcost;
	
	private long centralreleased_totalprojectcost;
    private long centralreleased_otherprojectcost;
	private long centralreleased_totalcost;
	
	private long centralonly_totalprojectcost;
    private long centralonly_otherprojectcost;
	private long centralonly_totalcost;
	
	private long stateonly_totalprojectcost;
	private long stateonly_otherprojectcost;
	private long stateonly_totalcost;
		
 		
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
 
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
 
	
	
	public long getCentralProgramCost() {
		return centralProgramCost;
	}
	public void setCentralProgramCost(Long centralProgramCost) {
		if(centralProgramCost==null){
			centralProgramCost=0L;
		}
		this.centralProgramCost = centralProgramCost;
	}
	public long getCentralSuportCost() {
		return centralSuportCost;
	}
	public void setCentralSuportCost(Long centralSuportCost) {
		if(centralSuportCost==null){
			centralSuportCost=0L;
		}
		this.centralSuportCost = centralSuportCost;
	}
	public long getCentralCtsa() {
		return centralCtsa;
	}
	public void setCentralCtsa(Long centralCtsa) {
		if(centralCtsa==null){
			centralCtsa=0L;
		}
		this.centralCtsa = centralCtsa;
	}
	public long getCentralAdminCost() {
		return centralAdminCost;
	}
	public void setCentralAdminCost(Long centralAdminCost) {
		if(centralAdminCost==null){
			centralAdminCost=0L;
		}
		this.centralAdminCost = centralAdminCost;
	}
	public long getCentralCapacityCost() {
		return centralCapacityCost;
	}
	public void setCentralCapacityCost(Long centralCapacityCost) {
		if(centralCapacityCost==null){
			centralCapacityCost=0L;
		}
		this.centralCapacityCost = centralCapacityCost;
	}
	public long getStateProgramCost() {
		return stateProgramCost;
	}
	public void setStateProgramCost(Long stateProgramCost) {
		if(stateProgramCost==null){
			stateProgramCost=0L;
		}
		this.stateProgramCost = stateProgramCost;
	}
	public long getStateSuportCost() {
		return stateSuportCost;
	}
	public void setStateSuportCost(Long stateSuportCost) {
		if(stateSuportCost==null){
			stateSuportCost=0L;
		}
		this.stateSuportCost = stateSuportCost;
	}
	public long getStateCtsa() {
		return stateCtsa;
	}
	public void setStateCtsa(Long stateCtsa) {
		if(stateCtsa==null){
			stateCtsa=0L;
		}
		this.stateCtsa = stateCtsa;
	}
	public long getStateAdminCost() {
		return stateAdminCost;
	}
	public void setStateAdminCost(Long stateAdminCost) {
		if(stateAdminCost==null){
			stateAdminCost=0L;
		}
		this.stateAdminCost = stateAdminCost;
	}
	public long getStateCapacityCost() {
		return stateCapacityCost;
	}
	public void setStateCapacityCost(Long stateCapacityCost) {
		if(stateCapacityCost==null){
			stateCapacityCost=0L;
		}
		this.stateCapacityCost = stateCapacityCost;
	}
	public long getCentralReleasedProgramCost() {
		return centralReleasedProgramCost;
	}
	public void setCentralReleasedProgramCost(Long centralReleasedProgramCost) {
		if(centralReleasedProgramCost==null){
			centralReleasedProgramCost=0L;
		}
		this.centralReleasedProgramCost = centralReleasedProgramCost;
	}
	public long getCentralReleasedSuportCost() {
		return centralReleasedSuportCost;
	}
	public void setCentralReleasedSuportCost(Long centralReleasedSuportCost) {
		if(centralReleasedSuportCost==null){
			centralReleasedSuportCost=0L;
		}
		this.centralReleasedSuportCost = centralReleasedSuportCost;
	}
	public long getCentralReleasedCtsa() {
		return centralReleasedCtsa;
	}
	public void setCentralReleasedCtsa(Long centralReleasedCtsa) {
		if(centralReleasedCtsa==null){
			centralReleasedCtsa=0L;
		}
		this.centralReleasedCtsa = centralReleasedCtsa;
	}
	public long getCentralReleasedAdminCost() {
		return centralReleasedAdminCost;
	}
	public void setCentralReleasedAdminCost(Long centralReleasedAdminCost) {
		if(centralReleasedAdminCost==null){
			centralReleasedAdminCost=0L;
		}
		this.centralReleasedAdminCost = centralReleasedAdminCost;
	}
	public long getCentralReleasedCapacityCost() {
		return centralReleasedCapacityCost;
	}
	public void setCentralReleasedCapacityCost(Long centralReleasedCapacityCost) {
		if(centralReleasedCapacityCost==null){
			centralReleasedCapacityCost=0L;
		}
		this.centralReleasedCapacityCost = centralReleasedCapacityCost;
	}
	public long getCentralOnlyProgramCost() {
		return centralOnlyProgramCost;
	}
	public void setCentralOnlyProgramCost(Long centralOnlyProgramCost) {
		if(centralOnlyProgramCost==null){
			centralOnlyProgramCost=0L;
		}
		this.centralOnlyProgramCost = centralOnlyProgramCost;
	}
	public long getCentralOnlySuportCost() {
		return centralOnlySuportCost;
	}
	public void setCentralOnlySuportCost(Long centralOnlySuportCost) {
		if(centralOnlySuportCost==null){
			centralOnlySuportCost=0L;
		}
		this.centralOnlySuportCost = centralOnlySuportCost;
	}
	public long getCentralOnlyCtsa() {
		return centralOnlyCtsa;
	}
	public void setCentralOnlyCtsa(Long centralOnlyCtsa) {
		if(centralOnlyCtsa==null){
			centralOnlyCtsa=0L;
		}
		this.centralOnlyCtsa = centralOnlyCtsa;
	}
	public long getCentralOnlyAdminCost() {
		return centralOnlyAdminCost;
	}
	public void setCentralOnlyAdminCost(Long centralOnlyAdminCost) {
		if(centralOnlyAdminCost==null){
			centralOnlyAdminCost=0L;
		}
		this.centralOnlyAdminCost = centralOnlyAdminCost;
	}
	public long getCentralOnlyCapacityCost() {
		return centralOnlyCapacityCost;
	}
	public void setCentralOnlyCapacityCost(Long centralOnlyCapacityCost) {
		if(centralOnlyCapacityCost==null){
			centralOnlyCapacityCost=0L;
		}
		this.centralOnlyCapacityCost = centralOnlyCapacityCost;
	}
	public long getStateOnlyProgramCost() {
		return stateOnlyProgramCost;
	}
	public void setStateOnlyProgramCost(Long stateOnlyProgramCost) {
		if(stateOnlyProgramCost==null){
			stateOnlyProgramCost=0L;
		}
		this.stateOnlyProgramCost = stateOnlyProgramCost;
	}
	public long getStateOnlySuportCost() {
		return stateOnlySuportCost;
	}
	public void setStateOnlySuportCost(Long stateOnlySuportCost) {
		if(stateOnlySuportCost==null){
			stateOnlySuportCost=0L;
		}
		this.stateOnlySuportCost = stateOnlySuportCost;
	}
	public long getStateOnlyCtsa() {
		return stateOnlyCtsa;
	}
	public void setStateOnlyCtsa(Long stateOnlyCtsa) {
		if(stateOnlyCtsa==null){
			stateOnlyCtsa=0L;
		}
		this.stateOnlyCtsa = stateOnlyCtsa;
	}
	public long getStateOnlyAdminCost() {
		return stateOnlyAdminCost;
	}
	public void setStateOnlyAdminCost(Long stateOnlyAdminCost) {
		if(stateOnlyAdminCost==null){
			stateOnlyAdminCost=0L;
		}
		this.stateOnlyAdminCost = stateOnlyAdminCost;
	}
	public long getStateOnlyCapacityCost() {
		return stateOnlyCapacityCost;
	}
	public void setStateOnlyCapacityCost(Long stateOnlyCapacityCost) {
		if(stateOnlyCapacityCost==null){
			stateOnlyCapacityCost=0L;
		}
		this.stateOnlyCapacityCost = stateOnlyCapacityCost;
	}
	public long getCentral_totalprojectcost() {
		return central_totalprojectcost;
	}
	public void setCentral_totalprojectcost(Long central_totalprojectcost) {
		if(central_totalprojectcost==null){
			central_totalprojectcost=0L;
		}
		this.central_totalprojectcost = central_totalprojectcost;
	}
	public long getCentral_otherprojectcost() {
		return central_otherprojectcost;
	}
	public void setCentral_otherprojectcost(Long central_otherprojectcost) {
		if(central_otherprojectcost==null){
			central_otherprojectcost=0L;
		}
		this.central_otherprojectcost = central_otherprojectcost;
	}
	public long getCentral_totalcost() {
		return central_totalcost;
	}
	public void setCentral_totalcost(Long central_totalcost) {
		if(central_totalcost==null){
			central_totalcost=0L;
		}
		this.central_totalcost = central_totalcost;
	}
	public long getState_totalprojectcost() {
		return state_totalprojectcost;
	}
	public void setState_totalprojectcost(Long state_totalprojectcost) {
		if(state_totalprojectcost==null){
			state_totalprojectcost=0L;
		}
		this.state_totalprojectcost = state_totalprojectcost;
	}
	public long getState_otherprojectcost() {
		return state_otherprojectcost;
	}
	public void setState_otherprojectcost(Long state_otherprojectcost) {
		if(state_otherprojectcost==null){
			state_otherprojectcost=0L;
		}
		this.state_otherprojectcost = state_otherprojectcost;
	}
	public long getState_totalcost() {
		return state_totalcost;
	}
	public void setState_totalcost(Long state_totalcost) {
		if(state_totalcost==null){
			state_totalcost=0L;
		}
		this.state_totalcost = state_totalcost;
	}
	public long getCentralreleased_totalprojectcost() {
		return centralreleased_totalprojectcost;
	}
	public void setCentralreleased_totalprojectcost(Long centralreleased_totalprojectcost) {
		if(centralreleased_totalprojectcost==null){
			centralreleased_totalprojectcost=0L;
		}
		this.centralreleased_totalprojectcost = centralreleased_totalprojectcost;
	}
	public long getCentralreleased_otherprojectcost() {
		return centralreleased_otherprojectcost;
	}
	public void setCentralreleased_otherprojectcost(Long centralreleased_otherprojectcost) {
		if(centralreleased_otherprojectcost==null){
			centralreleased_otherprojectcost=0L;
		}
		this.centralreleased_otherprojectcost = centralreleased_otherprojectcost;
	}
	public long getCentralreleased_totalcost() {
		return centralreleased_totalcost;
	}
	public void setCentralreleased_totalcost(Long centralreleased_totalcost) {
		if(centralreleased_totalcost==null){
			centralreleased_totalcost=0L;
		}
		this.centralreleased_totalcost = centralreleased_totalcost;
	}
	public long getCentralonly_totalprojectcost() {
		return centralonly_totalprojectcost;
	}
	public void setCentralonly_totalprojectcost(Long centralonly_totalprojectcost) {
		if(centralonly_totalprojectcost==null){
			centralonly_totalprojectcost=0L;
		}
		this.centralonly_totalprojectcost = centralonly_totalprojectcost;
	}
	public long getCentralonly_otherprojectcost() {
		return centralonly_otherprojectcost;
	}
	public void setCentralonly_otherprojectcost(Long centralonly_otherprojectcost) {
		if(centralonly_otherprojectcost==null){
			centralonly_otherprojectcost=0L;
		}
		this.centralonly_otherprojectcost = centralonly_otherprojectcost;
	}
	public long getCentralonly_totalcost() {
		return centralonly_totalcost;
	}
	public void setCentralonly_totalcost(Long centralonly_totalcost) {
		if(centralonly_totalcost==null){
			centralonly_totalcost=0L;
		}
		this.centralonly_totalcost = centralonly_totalcost;
	}
	public long getStateonly_totalprojectcost() {
		return stateonly_totalprojectcost;
	}
	public void setStateonly_totalprojectcost(Long stateonly_totalprojectcost) {
		if(stateonly_totalprojectcost==null){
			stateonly_totalprojectcost=0L;
		}
		this.stateonly_totalprojectcost = stateonly_totalprojectcost;
	}
	public long getStateonly_otherprojectcost() {
		return stateonly_otherprojectcost;
	}
	public void setStateonly_otherprojectcost(Long stateonly_otherprojectcost) {
		if(stateonly_otherprojectcost==null){
			stateonly_otherprojectcost=0L;
		}
		this.stateonly_otherprojectcost = stateonly_otherprojectcost;
	}
	public long getStateonly_totalcost() {
		return stateonly_totalcost;
	}
	public void setStateonly_totalcost(Long stateonly_totalcost) {
		if(stateonly_totalcost==null){
			stateonly_totalcost=0L;
		}
		this.stateonly_totalcost = stateonly_totalcost;
	}
	
	
	
	
	
	
	
	
	
	public Date getCentralReleasedDateProgramCost() {
		return centralReleasedDateProgramCost;
	}

	
	public Date getCentralReleasedDatSuportCost() {
		return centralReleasedDatSuportCost;
	}

	public Date getCentralReleasedDatCtsa() {
		return centralReleasedDatCtsa;
	}
	
	public Date getCentralReleasedDatAdminCost() {
		return centralReleasedDatAdminCost;
	}
	
	public Date getCentralReleasedDatCapacityCost() {
		return centralReleasedDatCapacityCost;
	}
	
	
	public Date getStateReleasedDateProgramCost() {
		return stateReleasedDateProgramCost;
	}
	
	public Date getStateReleasedDateSuportCost() {
		return stateReleasedDateSuportCost;
	}
	
	public Date getStateReleasedDateCtsa() {
		return stateReleasedDateCtsa;
	}
	
	public Date getStateReleasedDateAdminCost() {
		return stateReleasedDateAdminCost;
	}
	
	public Date getStateReleasedDateCapacityCost() {
		return stateReleasedDateCapacityCost;
	}
	
 

	
	//......new edited dated value...

	public void setCentralReleasedDateProgramCost(Object centralReleasedDateProgramCost) {
		if(centralReleasedDateProgramCost instanceof String){
			this.centralReleasedDateProgramCost = new DateUtil().Correct(centralReleasedDateProgramCost.toString());
		}
		else{
			this.centralReleasedDateProgramCost =(Date) centralReleasedDateProgramCost;
		}
		
	}
	public void setCentralReleasedDatSuportCost(Object centralReleasedDatSuportCost) {
		if(centralReleasedDatSuportCost instanceof String){
			this.centralReleasedDatSuportCost = new DateUtil().Correct(centralReleasedDatSuportCost.toString());
		}
		else{
			this.centralReleasedDatSuportCost =(Date) centralReleasedDatSuportCost;
		}
		
	}
	public void setCentralReleasedDatCtsa(Object centralReleasedDatCtsa) {
		if(centralReleasedDatCtsa instanceof String){
			this.centralReleasedDatCtsa = new DateUtil().Correct(centralReleasedDatCtsa.toString());
		}
		else{
			this.centralReleasedDatCtsa =(Date) centralReleasedDatCtsa;
		}
		
	}
	
	public void setCentralReleasedDatAdminCost(Object centralReleasedDatAdminCost) {
	  if(centralReleasedDatAdminCost instanceof String){
		this.centralReleasedDatAdminCost = new DateUtil().Correct(centralReleasedDatAdminCost.toString());
	   }
	 else{
		this.centralReleasedDatAdminCost =(Date) centralReleasedDatAdminCost;
	  }
	
    }  
	public void setCentralReleasedDatCapacityCost(Object centralReleasedDatCapacityCost) {
		if(centralReleasedDatCapacityCost instanceof String){
			this.centralReleasedDatCapacityCost = new DateUtil().Correct(centralReleasedDatCapacityCost.toString());
		}
		else{
			this.centralReleasedDatCapacityCost =(Date) centralReleasedDatCapacityCost;
		}
		
	   } 
	public void setStateReleasedDateProgramCost(Object stateReleasedDateProgramCost) {
		if(stateReleasedDateProgramCost instanceof String){
			this.stateReleasedDateProgramCost = new DateUtil().Correct(stateReleasedDateProgramCost.toString());
		}
		else{
			this.stateReleasedDateProgramCost =(Date) stateReleasedDateProgramCost;
		}
		
	   } 
	public void setStateReleasedDateSuportCost(Object stateReleasedDateSuportCost) {
		if(stateReleasedDateSuportCost instanceof String){
			this.stateReleasedDateSuportCost = new DateUtil().Correct(stateReleasedDateSuportCost.toString());
		}
		else{
			this.stateReleasedDateSuportCost =(Date) stateReleasedDateSuportCost;
		}
		
	   } 
	public void setStateReleasedDateCtsa(Object stateReleasedDateCtsa) {
		if(stateReleasedDateCtsa instanceof String){
			this.stateReleasedDateCtsa = new DateUtil().Correct(stateReleasedDateCtsa.toString());
		}
		else{
			this.stateReleasedDateCtsa =(Date) stateReleasedDateCtsa;
		}
		
	   } 
	public void setStateReleasedDateAdminCost(Object stateReleasedDateAdminCost) {
		if(stateReleasedDateAdminCost instanceof String){
			this.stateReleasedDateAdminCost = new DateUtil().Correct(stateReleasedDateAdminCost.toString());
		}
		else{
			this.stateReleasedDateAdminCost =(Date) stateReleasedDateAdminCost;
		}
		
	   }  
	
		public void setStateReleasedDateCapacityCost(Object stateReleasedDateCapacityCost) {
	    if(stateReleasedDateCapacityCost instanceof String){
		   this.stateReleasedDateCapacityCost = new DateUtil().Correct(stateReleasedDateCapacityCost.toString());
	     }
	   else{
		this.stateReleasedDateCapacityCost =(Date) stateReleasedDateCapacityCost;
	   }
	
     }  
	//......end.......
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public StateSanctionVO getSanctionDetailId() {
			return sanctionDetailId;
		}
		public void setSanctionDetailId(StateSanctionVO sanctionDetailId) {
			this.sanctionDetailId = sanctionDetailId;
		}
		 
		
		
	 
}
