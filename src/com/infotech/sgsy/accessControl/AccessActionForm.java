package com.infotech.sgsy.accessControl;

import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;

/**
 * THIS CLASS IMPLEMENTS AccessAction Form
 * @author 37595
 *  Date : 24.04.2009
 */
public class AccessActionForm extends MasterForm {

	private List accessList;
	private String roleName;
	private String roleDescription;
	private String levelSchemeName;
	private String levelRoleName;
	private String levelCode;
	
	private boolean isInformationDialog = false;
	private String informationDialogText = null;
	private String informationDialogHeader = null;
	
	public boolean isInformationDialog() {
		return isInformationDialog;
	}

	public void setInformationDialog(boolean isInformationDialog) {
		this.isInformationDialog = isInformationDialog;
	}

	public String getInformationDialogText() {
		return informationDialogText;
	}

	public void setInformationDialogText(String informationDialogText) {
		this.informationDialogText = informationDialogText;
	}

	public String getInformationDialogHeader() {
		return informationDialogHeader;
	}

	public void setInformationDialogHeader(String informationDialogHeader) {
		this.informationDialogHeader = informationDialogHeader;
	}

	public RoleMasterVO getIndex(int index) {
		return (RoleMasterVO) accessList.get(index);
		}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelRoleName() {
		return levelRoleName;
	}

	public void setLevelRoleName(String levelRoleName) {
		this.levelRoleName = levelRoleName;
	}

	public List getAccessList() {
		return accessList;
	}

	public void setAccessList(List accessList) {
		this.accessList = accessList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	public void reset(ActionMapping mapping,
			javax.servlet.http.HttpServletRequest request) {
		if (accessList != null) {
			Iterator itr = accessList.iterator();
			while (itr.hasNext()) {
				RoleMasterVO vo = (RoleMasterVO) itr.next();
				vo.setPermission(false);
				

			}
		}
	}
	
	public void resetFields() {
		
		roleName="";
		roleDescription="";
		levelRoleName="";
		levelCode="";
		levelSchemeName="";
	}

	public String getLevelSchemeName() {
		return levelSchemeName;
	}

	public void setLevelSchemeName(String levelSchemeName) {
		this.levelSchemeName = levelSchemeName;
	}

}
