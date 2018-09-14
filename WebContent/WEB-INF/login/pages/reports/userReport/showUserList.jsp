<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
<script language=javascript>
 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';
 var levelName;

	function closePage() {
		var level = document.forms[0].levelCode.value;
		var status = window.confirm('<bean:message key="alert.close.form" />');
		if (status == true) {
			var entityCode = document.forms[0].entityCode.value;

			var level = '', stateName = '', districtName = '', blockName = '';

			level = document.forms[0].levelCode.value;

			if (document.forms[0].stateName != null)
				stateName = document.forms[0].stateName.value;
			if (document.forms[0].districtName != null)
				districtName = document.forms[0].districtName.value;
			if (document.forms[0].blockName != null)
				blockName = document.forms[0].blockName.value;

			if (document.forms[0].blockName != null) {
				document.userForm.action = "userAction1.do?methodName=getUserListForReport&level="
						+ level
						+ "&"
						+ tokenParameter
						+ "="
						+ tokenValue
						+ "&entityCode="
						+ entityCode
						+ "&stateName="
						+ stateName
						+ "&districtName="
						+ districtName
						+ "&blockName=" + blockName;
				document.userForm.submit();

			} else {

				document.userForm.action = "userAction1.do?methodName=getUserListForReport&level="
						+ level + "&" + tokenParameter + "=" + tokenValue;
				document.userForm.submit();
			}

		}
	}
</script>


<body>
<html:form action="login/userAction1">
<div class="mainpane">
<table width="100%">
<tr>
	<td align="center" class="pageHeader">
		<bean:message key="lable.view" />
		<bean:message key="lable.user.user"></bean:message>
	</td>
</tr>
	<tr>
	<td style="padding-left:5%"><span class="mandatory">*</span><bean:message
											key="lable.field.manadatory"></bean:message>
	</td>
	</tr>
<logic:present name="userNotFound">
	<tr>
		<td><script>alert('<bean:write name="userNotFound"/>');</script></td>
	</tr>
</logic:present>
					
<tr><td align="center">
	<table width="90%" class="inputTBL" align="center">
	<tr>
		<th><bean:message key="lable.user.levelofuser" /></th>
		<td><label id="levelCodeID"></label>
		<html:select property="levelCode" styleId="levelCode" style="display:none">
			<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
		<logic:present name="levelList">
			<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
		</logic:present>
		</html:select> <script language="javascript">
			document.getElementById("levelCodeID").innerHTML = document.forms[0].levelCode[document.forms[0].levelCode.selectedIndex].text;
		</script>
		</td>
	</tr>
	<html:hidden property="entityCode" value="${entityCode}" />
	<tr>
		<th><bean:message key="lable.user.loginID" /><span class="mandatory">*</span></th>
		<td>${userForm.loginId }</td>
	</tr>
	</table>
	
	<table id="roleListRow" class="inputTBL" width="90%" align="center">
	<logic:present name="stateName">
	<tr>
		<th> State Name</th>
		<td> <input type="hidden" name="stateName" value="<bean:write name="stateName"/>" />
			<bean:write name="stateName" />
		</td>
	</tr>
	</logic:present>
	<logic:present name="districtName">
	<tr>
		<th> District Name</th>
		<td> <input type="hidden" name="districtName" value="<bean:write name="districtName"/>" />
			<bean:write name="districtName" />
		</td>
	</tr>
	</logic:present>

	<logic:present name="blockName">
	<tr>
		<th> Block Name </th>
		<td><input type="hidden" name="blockName" value="<bean:write name="blockName"/>" />
			<bean:write name="blockName" />
		</td>
	</tr>
	</logic:present>
	<tr>
		<th><bean:message key="lable.user.userName" /><span class="mandatory">*</span></th>
		<td>${userForm.userName}</td>
	</tr>
	<tr>
		<th><bean:message key="lable.user.designation" /><span class="mandatory">*</span></th>
		<td>${userForm.userDesignation}</td>
	</tr>
	<tr>
		<th><bean:message key="lable.user.emailID" /><span class="mandatory"></span></th>
		<td>${userForm.emailId}</td>
	</tr>
	<tr>
		<th><bean:message key="lable.user.accountStatus" /></th>
		<td><label id="activeID"></label> <html:select property="activeFlag" size="1" style="display:none">
			<html:option value="Y"><bean:message key="lable.open" /></html:option>
			<html:option value="B"><bean:message key="lable.blocked" /></html:option>
			<html:option value="N"><bean:message key="lable.notauthorised" /></html:option>
			</html:select> <script language="javascript">
										//alert(document.getElementById("activeID").id);
										document.getElementById("activeID").innerHTML = document.forms[0].activeFlag[document.forms[0].activeFlag.selectedIndex].text;
									</script></td>
	</tr>
	</table>
</td></tr>
<tr>
	<td align="center"><input name="Button" type="button" class="button" value="Close" onclick="closePage()" /></td>
</tr>
</table>
</div>
</html:form>
</body>

