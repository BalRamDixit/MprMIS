package com.infotech.sgsy.userAccessControlManagement;
public class FormModuleMenuBean{
	@Override
	public String toString() {
		return "FormModuleMenuBean [formName=" + formName + ", moduleName=" + moduleName + ", masterModule="
				+ masterModule + ", orderNo=" + orderNo + ", help=" + help + ", url=" + url + ", permission="
				+ permission + "]";
	}
	private String formName;
	private String moduleName;
	private String masterModule;
	private String orderNo;
	private String help;
	private String url;
	private String permission;
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getMasterModule() {
		return masterModule;
	}
	public void setMasterModule(String masterModule) {
		this.masterModule = masterModule;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	   
   }