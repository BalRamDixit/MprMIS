package com.infotech.skills.hbm.piaprofile;
 
import java.io.Serializable;

import com.infotech.skills.master.MasterVO;

public class MstIdentityVO extends MasterVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @since  August 2013  
	 */
	private int activityId;
	private String activityName;
	private String activityShortName;
	
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityShortName() {
		return activityShortName;
	}
	public void setActivityShortName(String activityShortName) {
		this.activityShortName = activityShortName;
	}
	 	 
}
