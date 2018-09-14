<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Update Role</title>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="Javascript1.1" src="javaScript/common.js"></script>
<!--script for menu-->
<script type="text/javascript">
	function getRoleName(var1) {

		var roleLevel = document.accessForm.levelRoleName.value;
		document.accessForm.action = "access.do?methodName=getRoleName&roleLevel="
				+ var1.value + "&" + tokenParameter + "=" + tokenValue;
		document.accessForm.submit();

	}

	function getRoleDetails(var1) {
		if (document.accessForm.levelSchemeName.value == "") {
			var arg = '<bean:message key="label.schemeName" />';
			var mess = '<bean:message key="error.select"  arg0="'+arg+ '" />';
			alert(mess);
			document.accessForm.levelRoleName.focus();
			return false;
		}
		if (document.accessForm.levelRoleName.value == "") {
			var arg = '<bean:message key="access.addRole.roleLevel.label" />';
			var mess = '<bean:message key="error.select"  arg0="'+arg+ '" />';
			alert(mess);
			document.accessForm.roleName.value = "";
			document.accessForm.levelRoleName.focus();
			return false;
		}
		var roleLevel = document.accessForm.levelRoleName.value;
		document.accessForm.action = "access.do?methodName=getRoleDetails&roleName="
				+ var1.value
				+ "&roleLevel="
				+ roleLevel
				+ "&"
				+ tokenParameter
				+ "=" + tokenValue;
		document.accessForm.submit();
	}

	function modify() {

		if (document.accessForm.levelRoleName.value == "") {
			var arg = '<bean:message key="access.addRole.roleLevel.label" />';
			var mess = '<bean:message key="error.select"  arg0="'+arg+ '" />';
			alert(mess);
			document.accessForm.levelRoleName.focus();
			return false;
		}
		if (document.accessForm.roleName.value == "") {
			var arg = '<bean:message key="label.roleName" />';
			var mess = '<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';
			alert(mess);
			document.accessForm.roleName.focus();
			return false;
		}
		if (document.accessForm.roleDescription.value == "") {
			var arg = '<bean:message key="access.addRole.roleDesc.label" />';
			var mess = '<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';
			alert(mess);
			document.accessForm.roleDescription.focus();
			return false;
		}

		if (!isAlpha(document.accessForm.roleDescription,
				"<bean:message key="access.addRole.roleDesc.label" />")) {
			return false;
		}

		/*var roleDescription=document.accessForm.roleDescription.value;
		if( !(alphnum.test(roleDescription)) ) {
		alert("Role Description is not valid, Please enter a valid data" );
		document.accessForm.roleDescription.value ="";
		document.accessForm.roleDescription.focus();
		return false;
		}*/

		if ((document.accessForm.levelRoleName.value != "")
				&& (document.accessForm.roleName.value != "")
				&& (document.accessForm.roleDescription.value != "")) {
			var ctr = 0;
			for (i = 0; i < document.accessForm.elements.length; i++) {
				if (document.accessForm.elements[i].type == 'checkbox') {
					if (document.accessForm.elements[i].checked)
						ctr = ctr + 1;
				}

			}
			if (ctr == 0) {
				alert("<bean:message key="msg.selectOne" />");
				return false;
			}
		}
		var status = window.confirm("<bean:message key="msg.update" />");
		if (status == true) {
			document.accessForm.action = "access.do?methodName=modify&"
					+ tokenParameter + "=" + tokenValue;
			document.accessForm.submit();
		}
	}

	function closePage() {
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if (status == true) {
			document.accessForm.action = "login.do?methodName=closePage&"
					+ tokenParameter + "=" + tokenValue;
			document.accessForm.submit();
		}
	}
	function clearField() {
		var status = window.confirm("<bean:message key="msg.clearForm"/>");
		if (status == true) {
			document.accessForm.action = "access.do?methodName=showModify&"
					+ tokenParameter + "=" + tokenValue;
			document.accessForm.submit();
		}

	}
</script>
<!--Main form section starts here-->
<body>
	<html:form action="login/access">

		<table width="100%" align="center">
			<tr class="mainpaneheading">
				<td align="center"><html:messages id="message"
						name="<%=SGSYConstants.MSG %>" message="true">
						<bean:write name="message" />
					</html:messages></td>
			</tr>
		</table>

		<table width="100%">
			<tr>
				<td>
					<table width="90%" align="center">
						<tr>
							<td align="center" class="pageHeader"><bean:message key="label.manageRole.update" />
							<bean:message key="login.selectRole.label" /></td>
							<html:hidden property="informationDialog"></html:hidden>
							<html:hidden property="informationDialogText"></html:hidden>
							<html:hidden property="informationDialogHeader"></html:hidden>
						</tr>
						<tr>
							<td height="20"><span class="text-error">*</span> <span class="normaltext">
							<bean:message key="lable.field.manadatory" /></span></td>
						</tr>
					</table>
					<table width="99%" align="center" class="inputTBL">
						<tr>
							<th><bean:message key="label.schemeName" /><span class="text-error">*</span></th>
							<logic:notPresent name="fieldDisabled">
							<td><span class="fieldDisabled"> 
								<html:select property="levelSchemeName" styleId="levelSchemeName">
									<html:option value="">
										<bean:message key="label.select" />
									</html:option>
									<html:options collection="schemeList" property="key" labelProperty="value" />
								</html:select> </span>
							</td>
							</logic:notPresent>
							
							<logic:present name="fieldDisabled">
							<td><span class="fieldDisabled"> 
								<html:select property="levelSchemeName" styleId="levelSchemeName">
									<html:option value="">
										<bean:message key="label.select" />
									</html:option>
								<html:options collection="schemeList" property="key" labelProperty="value" />
								</html:select></span>
							</td>
							</logic:present>
						</tr>
						<tr>
							<th><bean:message key="access.addRole.roleLevel.label" /></th>
							<logic:notPresent name="fieldDisabled">
							<td><span class="text-error"> 
									<html:select property="levelRoleName" styleId="levelRoleName" onchange="getRoleName(this)">
										<html:option value="">
											<bean:message key="label.select" />
										</html:option>
										<html:options collection="roleLevelList" property="key" labelProperty="value" />
										</html:select> </span><font color="red" size="1">&nbsp;
										<html:errors property="levelRoleName" /> </font>
							</td>
							</logic:notPresent>
							
							<logic:present name="fieldDisabled">
							<td><span class="text-error"> 
									<html:select property="levelRoleName" styleId="levelRoleName" onchange="getRoleName(this)" disabled="true">
										<html:option value="">
											<bean:message key="label.select" />
										</html:option>
									<html:options collection="roleLevelList" property="key" labelProperty="value" />
									</html:select> </span>
									<font color="red" size="1">&nbsp;<html:errors property="levelRoleName" /> </font>
							</td>
							</logic:present>
						</tr>	
						<tr>	
							<th><bean:message key="access.addRole.roleName.label" /><span class="text-error">*</span></th>
							<td><span class="text-error"> 
							<html:select property="roleName" styleId="roleName" onchange="getRoleDetails(this)">
								<html:option value="">
									<bean:message key="label.select" />
								</html:option>
								<html:options collection="roleNameList" property="key"	labelProperty="value" />
									</html:select> </span><font color="red" size="1">&nbsp;
								<html:errors property="roleName" /> </font>
							</td>
						</tr>
						<tr>	
							<logic:present name="roleDetailsList">
								<th><bean:message key="access.addRole.roleDesc.label" /></th>
								<td><span> 
									<html:text property="roleDescription" styleId="roleDescription" size="60" maxlength="60"
											onblur="this.value=this.value.toUpperCase();" /> </span> 
											<font color="red" size="1">&nbsp;
											<html:errors property="roleDescription" /></font>
								</td>
							</logic:present>
						</tr>
					</table> 
					
					<logic:present name="roleDetailsList">
						<table width="99%" align="center" class="inputTBL">
							<tr>
								<th><bean:message key="access.addRole.moduleForm.label" />
								</th>
								<th><bean:message key="access.addRole.permission.label" />
								</th>
							</tr>
							<logic:iterate name="accessForm" property="accessList" id="index">
								<logic:equal name="index" property="moduleStatus" value="change">
									<tr>
										<th colspan="2"><bean:write name="index" property="modName" /></th>
									</tr>
								</logic:equal>
								<tr>
									<td><bean:write name="index" property="formName" /></td>
									<td><html:checkbox name="index" property="permission" styleId="permission" indexed="true" /></td>
								</tr>
							</logic:iterate>
						</table>
						<div align="center">
							<html:button styleClass="button" property="next" value="Update"
								onclick="modify()"></html:button>
							<html:button styleClass="button" property="next" value="Clear"
								onclick="clearField()"></html:button>
							<html:button styleClass="button" property="next" value="Close"
								onclick="closePage()"></html:button>
						</div>
					</logic:present> <logic:notPresent name="roleDetailsList">
						<div align="center">
							<html:button styleClass="button" property="next"
								onclick="modify()">
								<bean:message key="button.modify" />
							</html:button>
							<html:button styleClass="button" property="next"
								onclick="clearField()">
								<bean:message key="button.clear" />
							</html:button>
							<html:button styleClass="button" property="next"
								onclick="closePage()">
								<bean:message key="button.close" />
							</html:button>
						</div>
					</logic:notPresent>
				</td>
			</tr>
		</table>

	</html:form>
	<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<!--Main form section ends here-->