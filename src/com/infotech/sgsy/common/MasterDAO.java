package com.infotech.sgsy.common;

/**
 * @author NIC 
 * @since July, 2013
 */
public interface MasterDAO{
	public abstract int insert(MasterVO  masterVO )throws Exception;
	public abstract int delete(MasterVO  masterVO )throws Exception;
	public abstract int update(MasterVO  masterVO )throws Exception;
}