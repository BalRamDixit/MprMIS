<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<!--script for menu-->
<script type="text/javascript">
function getRoleName(var1){

 
var roleLevel=document.accessForm.levelRoleName.value;
document.accessForm.action = "access.do?methodName=getRoleName&jspFor=delete&roleLevel="+var1.value+"&"+tokenParameter+"="+tokenValue;
document.accessForm.submit();
}

function getRoleDetails(var1){
if( document.accessForm.levelRoleName.value == "" )
  	{
  	var arg='<bean:message key="access.addRole.roleLevel.label" />';  
	var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
	alert(mess);
  	document.accessForm.roleName.value = "";
  	document.accessForm.levelRoleName.focus();
  	return false;
  	} 
var roleLevel=document.accessForm.levelRoleName.value;
document.accessForm.action = "access.do?methodName=getRoleDetails&jspFor=delete&roleName="+var1.value+"&roleLevel="+roleLevel+"&"+tokenParameter+"="+tokenValue;
document.accessForm.submit();
}
/*
function getSelectedRoleDetails(var1){
document.accessForm.action = "access.do?methodName=getSelectedRoleDetails&roleName="+var1.value;
document.accessForm.submit();
}
*/
function deleteRecord()
{

 if( document.accessForm.levelRoleName.value == "" )
  	{
	  	var arg='<bean:message key="access.addRole.roleLevel.label" />';  
		var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
		alert(mess);
	  	document.accessForm.levelRoleName.focus();
	  	return false;
  	} 
  	 if( document.accessForm.roleName.value == "" )
  	{
	  	var arg='<bean:message key="label.roleName" />';  
		var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
		alert(mess);
	  	document.accessForm.roleName.focus();
	  	return false;
  	} 
  	
  	var status=window.confirm("<bean:message key="msg.delete.record" />");
	if(status==true){
  	document.accessForm.action = "access.do?methodName=delete&jspFor=delete&"+tokenParameter+"="+tokenValue;
	document.accessForm.submit();
	}
}

function closePage()
{
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.accessForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.accessForm.submit();
	}
}


function clearPage(){ 

    var status=window.confirm("<bean:message key="msg.clear" />");
	if(status==true){
		document.accessForm.action = "access.do?methodName=showDelete&"+tokenParameter+"="+tokenValue;
	  	document.accessForm.submit();
	} 
}

</script>
<!--Main form section starts here-->
<body >
<html:form action="login/access">

	<table width="100%" >
		<tr>
			<td>
			
			<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr align="center"><td><html:errors /></td></tr>
				<tr>
					<td align="center" class="pageHeader"><bean:message  key="label.delete" /><bean:message  key="login.selectRole.label" /></td>
					<html:hidden property="informationDialog"></html:hidden>
					<html:hidden property="informationDialogText"></html:hidden>
					<html:hidden property="informationDialogHeader"></html:hidden>
               </tr>
				<tr>
					<td height="20"><span class="mandatory">*</span>
					<span class="normaltext"><bean:message  key="lable.field.manadatory" /></span>
					</td>
				</tr>
				
			</table>
			<table width="90%" align="center" class="inputTBL">
			
				<tr >
				<th><bean:message  key="label.schemeName" /><span class="mandatory">*</span></th>
				<th><bean:message  key="access.addRole.roleLevel.label" /></th>
				<th><bean:message  key="access.addRole.roleName.label" /><span class="mandatory">*</span></th>
					<logic:present name="roleDetailsList">
					<th><bean:message  key="access.addRole.roleDesc.label" /></th>
					</logic:present>
				</tr>
				<tr >
				<logic:present name="fieldDisabled">
				<td><span class="mandatory"> 
						<html:select property="levelSchemeName" styleId="levelSchemeName">
							<html:option value=""><bean:message key="label.select"/></html:option>
							<html:options collection="schemeList" property="key" labelProperty="value"/>
						</html:select>
					</span></td>
				<td><span class="mandatory"> 
					<html:select property="levelRoleName" styleId="levelRoleName" onchange="getRoleName(this)" disabled="true">
					<html:option value=""><bean:message key="label.select"/></html:option>
					<html:options collection="roleLevelList" property="key" labelProperty="value"/>
					</html:select>
					</span><font color="red" size="1">&nbsp;<html:errors property="levelRoleName"/></font>
					</td>
				</logic:present>
				<logic:notPresent name="fieldDisabled">
				<td><span class="mandatory"> 
						<html:select property="levelSchemeName" styleId="levelSchemeName">
							<html:option value=""><bean:message key="label.select"/></html:option>
							<html:options collection="schemeList" property="key" labelProperty="value"/>
						</html:select>
					</span></td>
				<td><span class="mandatory"> 
					<html:select property="levelRoleName" styleId="levelRoleName" onchange="getRoleName(this)">
					<html:option value=""><bean:message key="label.select"/></html:option>
					<html:options collection="roleLevelList" property="key" labelProperty="value"/>
					</html:select>
					</span><font color="red" size="1">&nbsp;<html:errors property="levelRoleName"/></font>
					</td>
				</logic:notPresent>
				
				<td><span class="mandatory"> 
					<html:select property="roleName" styleId="roleName" onchange="getRoleDetails(this)">
					<html:option value=""><bean:message key="label.select"/></html:option>
					<html:options collection="roleNameList" property="key" labelProperty="value"/>
					</html:select>
					</span><font color="red" size="1">&nbsp;<html:errors property="roleName"/></font>
				</td>
										
					<logic:present name="roleDetailsList">
					<td><span > <html:text property="roleDescription" styleId="roleDescription"  size="60" maxlength="60" onblur="this.value=this.value.toUpperCase();" readonly="true"/> </span>
					<font color="red" size="1">&nbsp;<html:errors property="roleDescription"/></font>
					</td>
					</logic:present>
				</tr>
								
			</table>
			<!-- 
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr >
					<td><bean:message  key="access.addRole.roleName.label" /><span class="mandatory">*</span></td>
					<logic:present name="roleDetailsList">
					<td><bean:message  key="access.addRole.roleDesc.label" /></td>
					<td><bean:message  key="access.addRole.roleLevel.label" /></td>
					</logic:present>
				</tr>
				<tr >
					<td><span class="mandatory"> 
					<html:select property="roleName" styleId="roleName" onchange="getSelectedRoleDetails(this)">
					<html:option value="">--Select Role--</html:option>
					<html:options collection="roleNameList" property="key" labelProperty="value"/>
					</html:select>
					</span><font color="red" size="1">&nbsp;<html:errors property="roleName"/></font>
					</td>
					<logic:present name="roleDetailsList">
					<td><span > <html:text property="roleDescription" styleId="roleDescription"  size="60" maxlength="60" onblur="this.value=this.value.toUpperCase();" readonly="true" /> </span></td>
					<td><span > <html:text property="levelRoleName" styleId="levelRoleName"  size="31" maxlength="30" onblur="this.value=this.value.toUpperCase();" readonly="true"/> </span></td>
					</logic:present>
				</tr>
								
			</table>
			-->
			<table width="90%" align="center" class="inputTBL">
				<logic:present name="roleDetailsList">
				<tr >
					
					<th><bean:message  key="access.addRole.moduleForm.label" /></th>
					<th><bean:message  key="access.addRole.permission.label" /></th>
					
				</tr>
				
				<logic:iterate name="accessForm" property="accessList" id="index">
					<logic:equal name="index" property="moduleStatus" value="change">
						<tr>
							<th colspan="2"><b><bean:write name="index" property="modName" /></b></th>
						</tr>
					</logic:equal>

					<tr>
						<td>&nbsp;<bean:write name="index" property="formName" />&nbsp;</td>						
							<td ><html:checkbox name="index" property="permission" styleId="permission" indexed="true" disabled="true" /></td>							
					</tr>
				</logic:iterate>
				</logic:present>
				
			</table>
			<div align="center">
				<input name="Button" type="button" class="button" value="<bean:message  key="button.delete"/>" onclick="deleteRecord()"/> 
				<html:button styleClass="button" property="Clear" value="Clear" onclick="clearPage()"/>
				<input name="Button" type="button" class="button" value="<bean:message  key="button.close"/>" onclick="closePage()" />
			</div>
			
			</td>
		</tr>
	</table>
	
</html:form>

<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<!--Main form section ends here-->