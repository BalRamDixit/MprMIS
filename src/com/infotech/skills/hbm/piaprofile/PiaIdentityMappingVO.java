package com.infotech.skills.hbm.piaprofile;
 
import java.io.Serializable;

import com.infotech.skills.master.MasterVO;

public class PiaIdentityMappingVO extends MasterVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @since  August 2013  
	 */
	private long piaMemberId;
    private long identityId;
	
    public long getPiaMemberId() {
		return piaMemberId;
	}
	public void setPiaMemberId(long piaMemberId) {
		this.piaMemberId = piaMemberId;
	}
	public long getIdentityId() {
		return identityId;
	}
	public void setIdentityId(long identityId) {
		this.identityId = identityId;
	}
		 	 
}
