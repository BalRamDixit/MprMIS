package com.infotech.skills.hbm.piaprofile;
 
import java.io.Serializable;

import com.infotech.skills.master.MasterVO;

public class PiaActivityMappingVO extends MasterVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @since  August 2013  
	 */
	private String piaCode;
    private String activityId;
	
  
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
		 	 
}
