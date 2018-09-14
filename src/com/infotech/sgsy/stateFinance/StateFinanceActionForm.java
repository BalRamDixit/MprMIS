package com.infotech.sgsy.stateFinance;

import com.infotech.sgsy.common.MasterForm;

	public class StateFinanceActionForm extends MasterForm {

		private String id; 				
		private String sanctionDetailId;		
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
			
        private long stateOnlyProgramCost;
		private long stateOnlySuportCost;
		private long stateOnlyCtsa;
		private long stateOnlyAdminCost;
		private long stateOnlyCapacityCost;
		
		// date  start...
		private String centralReleasedDateProgramCost;
		private String centralReleasedDatSuportCost;
		private String centralReleasedDatCtsa;
		private String centralReleasedDatAdminCost;
		private String centralReleasedDatCapacityCost;
		private String stateReleasedDateProgramCost;
		private String stateReleasedDateSuportCost;
		private String stateReleasedDateCtsa;
		private String stateReleasedDateAdminCost;
		private String stateReleasedDateCapacityCost;
		
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSanctionDetailId() {
			return sanctionDetailId;
		}
		public void setSanctionDetailId(String sanctionDetailId) {
			this.sanctionDetailId = sanctionDetailId;
		}
		public long getCentralProgramCost() {
			return centralProgramCost;
		}
		public void setCentralProgramCost(long centralProgramCost) {
			this.centralProgramCost = centralProgramCost;
		}
		public long getCentralSuportCost() {
			return centralSuportCost;
		}
		public void setCentralSuportCost(long centralSuportCost) {
			this.centralSuportCost = centralSuportCost;
		}
		public long getCentralCtsa() {
			return centralCtsa;
		}
		public void setCentralCtsa(long centralCtsa) {
			this.centralCtsa = centralCtsa;
		}
		public long getCentralAdminCost() {
			return centralAdminCost;
		}
		public void setCentralAdminCost(long centralAdminCost) {
			this.centralAdminCost = centralAdminCost;
		}
		public long getCentralCapacityCost() {
			return centralCapacityCost;
		}
		public void setCentralCapacityCost(long centralCapacityCost) {
			this.centralCapacityCost = centralCapacityCost;
		}
		public long getStateProgramCost() {
			return stateProgramCost;
		}
		public void setStateProgramCost(long stateProgramCost) {
			this.stateProgramCost = stateProgramCost;
		}
		public long getStateSuportCost() {
			return stateSuportCost;
		}
		public void setStateSuportCost(long stateSuportCost) {
			this.stateSuportCost = stateSuportCost;
		}
		public long getStateCtsa() {
			return stateCtsa;
		}
		public void setStateCtsa(long stateCtsa) {
			this.stateCtsa = stateCtsa;
		}
		public long getStateAdminCost() {
			return stateAdminCost;
		}
		public void setStateAdminCost(long stateAdminCost) {
			this.stateAdminCost = stateAdminCost;
		}
		public long getStateCapacityCost() {
			return stateCapacityCost;
		}
		public void setStateCapacityCost(long stateCapacityCost) {
			this.stateCapacityCost = stateCapacityCost;
		}
		public long getCentralReleasedProgramCost() {
			return centralReleasedProgramCost;
		}
		public void setCentralReleasedProgramCost(long centralReleasedProgramCost) {
			this.centralReleasedProgramCost = centralReleasedProgramCost;
		}
		public long getCentralReleasedSuportCost() {
			return centralReleasedSuportCost;
		}
		public void setCentralReleasedSuportCost(long centralReleasedSuportCost) {
			this.centralReleasedSuportCost = centralReleasedSuportCost;
		}
		public long getCentralReleasedCtsa() {
			return centralReleasedCtsa;
		}
		public void setCentralReleasedCtsa(long centralReleasedCtsa) {
			this.centralReleasedCtsa = centralReleasedCtsa;
		}
		public long getCentralReleasedAdminCost() {
			return centralReleasedAdminCost;
		}
		public void setCentralReleasedAdminCost(long centralReleasedAdminCost) {
			this.centralReleasedAdminCost = centralReleasedAdminCost;
		}
		public long getCentralReleasedCapacityCost() {
			return centralReleasedCapacityCost;
		}
		public void setCentralReleasedCapacityCost(long centralReleasedCapacityCost) {
			this.centralReleasedCapacityCost = centralReleasedCapacityCost;
		}
		public long getCentralOnlyProgramCost() {
			return centralOnlyProgramCost;
		}
		public void setCentralOnlyProgramCost(long centralOnlyProgramCost) {
			this.centralOnlyProgramCost = centralOnlyProgramCost;
		}
		public long getCentralOnlySuportCost() {
			return centralOnlySuportCost;
		}
		public void setCentralOnlySuportCost(long centralOnlySuportCost) {
			this.centralOnlySuportCost = centralOnlySuportCost;
		}
		public long getCentralOnlyCtsa() {
			return centralOnlyCtsa;
		}
		public void setCentralOnlyCtsa(long centralOnlyCtsa) {
			this.centralOnlyCtsa = centralOnlyCtsa;
		}
		public long getCentralOnlyAdminCost() {
			return centralOnlyAdminCost;
		}
		public void setCentralOnlyAdminCost(long centralOnlyAdminCost) {
			this.centralOnlyAdminCost = centralOnlyAdminCost;
		}
		public long getCentralOnlyCapacityCost() {
			return centralOnlyCapacityCost;
		}
		public void setCentralOnlyCapacityCost(long centralOnlyCapacityCost) {
			this.centralOnlyCapacityCost = centralOnlyCapacityCost;
		}
		public long getStateOnlyProgramCost() {
			return stateOnlyProgramCost;
		}
		public void setStateOnlyProgramCost(long stateOnlyProgramCost) {
			this.stateOnlyProgramCost = stateOnlyProgramCost;
		}
		public long getStateOnlySuportCost() {
			return stateOnlySuportCost;
		}
		public void setStateOnlySuportCost(long stateOnlySuportCost) {
			this.stateOnlySuportCost = stateOnlySuportCost;
		}
		public long getStateOnlyCtsa() {
			return stateOnlyCtsa;
		}
		public void setStateOnlyCtsa(long stateOnlyCtsa) {
			this.stateOnlyCtsa = stateOnlyCtsa;
		}
		public long getStateOnlyAdminCost() {
			return stateOnlyAdminCost;
		}
		public void setStateOnlyAdminCost(long stateOnlyAdminCost) {
			this.stateOnlyAdminCost = stateOnlyAdminCost;
		}
		public long getStateOnlyCapacityCost() {
			return stateOnlyCapacityCost;
		}
		public void setStateOnlyCapacityCost(long stateOnlyCapacityCost) {
			this.stateOnlyCapacityCost = stateOnlyCapacityCost;
		}
		public String getCentralReleasedDateProgramCost() {
			return centralReleasedDateProgramCost;
		}
		public void setCentralReleasedDateProgramCost(String centralReleasedDateProgramCost) {
			this.centralReleasedDateProgramCost = centralReleasedDateProgramCost;
		}
		public String getCentralReleasedDatSuportCost() {
			return centralReleasedDatSuportCost;
		}
		public void setCentralReleasedDatSuportCost(String centralReleasedDatSuportCost) {
			this.centralReleasedDatSuportCost = centralReleasedDatSuportCost;
		}
		public String getCentralReleasedDatCtsa() {
			return centralReleasedDatCtsa;
		}
		public void setCentralReleasedDatCtsa(String centralReleasedDatCtsa) {
			this.centralReleasedDatCtsa = centralReleasedDatCtsa;
		}
		public String getCentralReleasedDatAdminCost() {
			return centralReleasedDatAdminCost;
		}
		public void setCentralReleasedDatAdminCost(String centralReleasedDatAdminCost) {
			this.centralReleasedDatAdminCost = centralReleasedDatAdminCost;
		}
		public String getCentralReleasedDatCapacityCost() {
			return centralReleasedDatCapacityCost;
		}
		public void setCentralReleasedDatCapacityCost(String centralReleasedDatCapacityCost) {
			this.centralReleasedDatCapacityCost = centralReleasedDatCapacityCost;
		}
		public String getStateReleasedDateProgramCost() {
			return stateReleasedDateProgramCost;
		}
		public void setStateReleasedDateProgramCost(String stateReleasedDateProgramCost) {
			this.stateReleasedDateProgramCost = stateReleasedDateProgramCost;
		}
		public String getStateReleasedDateSuportCost() {
			return stateReleasedDateSuportCost;
		}
		public void setStateReleasedDateSuportCost(String stateReleasedDateSuportCost) {
			this.stateReleasedDateSuportCost = stateReleasedDateSuportCost;
		}
		public String getStateReleasedDateCtsa() {
			return stateReleasedDateCtsa;
		}
		public void setStateReleasedDateCtsa(String stateReleasedDateCtsa) {
			this.stateReleasedDateCtsa = stateReleasedDateCtsa;
		}
		public String getStateReleasedDateAdminCost() {
			return stateReleasedDateAdminCost;
		}
		public void setStateReleasedDateAdminCost(String stateReleasedDateAdminCost) {
			this.stateReleasedDateAdminCost = stateReleasedDateAdminCost;
		}
		public String getStateReleasedDateCapacityCost() {
			return stateReleasedDateCapacityCost;
		}
		public void setStateReleasedDateCapacityCost(String stateReleasedDateCapacityCost) {
			this.stateReleasedDateCapacityCost = stateReleasedDateCapacityCost;
		}
		public long getCentral_totalprojectcost() {
			return central_totalprojectcost;
		}
		public void setCentral_totalprojectcost(long central_totalprojectcost) {
			this.central_totalprojectcost = central_totalprojectcost;
		}
		public long getCentral_otherprojectcost() {
			return central_otherprojectcost;
		}
		public void setCentral_otherprojectcost(long central_otherprojectcost) {
			this.central_otherprojectcost = central_otherprojectcost;
		}
		public long getCentral_totalcost() {
			return central_totalcost;
		}
		public void setCentral_totalcost(long central_totalcost) {
			this.central_totalcost = central_totalcost;
		}
		public long getState_totalprojectcost() {
			return state_totalprojectcost;
		}
		public void setState_totalprojectcost(long state_totalprojectcost) {
			this.state_totalprojectcost = state_totalprojectcost;
		}
		public long getState_otherprojectcost() {
			return state_otherprojectcost;
		}
		public void setState_otherprojectcost(long state_otherprojectcost) {
			this.state_otherprojectcost = state_otherprojectcost;
		}
		public long getState_totalcost() {
			return state_totalcost;
		}
		public void setState_totalcost(long state_totalcost) {
			this.state_totalcost = state_totalcost;
		}
		public long getCentralreleased_totalprojectcost() {
			return centralreleased_totalprojectcost;
		}
		public void setCentralreleased_totalprojectcost(long centralreleased_totalprojectcost) {
			this.centralreleased_totalprojectcost = centralreleased_totalprojectcost;
		}
		public long getCentralreleased_otherprojectcost() {
			return centralreleased_otherprojectcost;
		}
		public void setCentralreleased_otherprojectcost(long centralreleased_otherprojectcost) {
			this.centralreleased_otherprojectcost = centralreleased_otherprojectcost;
		}
		public long getCentralreleased_totalcost() {
			return centralreleased_totalcost;
		}
		public void setCentralreleased_totalcost(long centralreleased_totalcost) {
			this.centralreleased_totalcost = centralreleased_totalcost;
		}
		public long getCentralonly_totalprojectcost() {
			return centralonly_totalprojectcost;
		}
		public void setCentralonly_totalprojectcost(long centralonly_totalprojectcost) {
			this.centralonly_totalprojectcost = centralonly_totalprojectcost;
		}
		public long getCentralonly_otherprojectcost() {
			return centralonly_otherprojectcost;
		}
		public void setCentralonly_otherprojectcost(long centralonly_otherprojectcost) {
			this.centralonly_otherprojectcost = centralonly_otherprojectcost;
		}
		public long getCentralonly_totalcost() {
			return centralonly_totalcost;
		}
		public void setCentralonly_totalcost(long centralonly_totalcost) {
			this.centralonly_totalcost = centralonly_totalcost;
		}
		public long getStateonly_totalprojectcost() {
			return stateonly_totalprojectcost;
		}
		public void setStateonly_totalprojectcost(long stateonly_totalprojectcost) {
			this.stateonly_totalprojectcost = stateonly_totalprojectcost;
		}
		public long getStateonly_otherprojectcost() {
			return stateonly_otherprojectcost;
		}
		public void setStateonly_otherprojectcost(long stateonly_otherprojectcost) {
			this.stateonly_otherprojectcost = stateonly_otherprojectcost;
		}
		public long getStateonly_totalcost() {
			return stateonly_totalcost;
		}
		public void setStateonly_totalcost(long stateonly_totalcost) {
			this.stateonly_totalcost = stateonly_totalcost;
		}
		
		 
		
		
		
	}
