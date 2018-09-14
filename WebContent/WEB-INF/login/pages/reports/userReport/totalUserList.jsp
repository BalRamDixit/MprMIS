<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
</head>
<script language=javascript>
 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';
 var levelName;

 
	function getUserDetails(userID, entityCode) {

		var level = '', stateName = '', districtName = '', blockName = '';

		level = document.forms[0].levelCode.value;

		if (document.forms[0].stateName != null)
			stateName = document.forms[0].stateName.value;
		if (document.forms[0].districtName != null)
			districtName = document.forms[0].districtName.value;
		if (document.forms[0].blockName != null)
			blockName = document.forms[0].blockName.value;

		//alert(stateName+' '+districtName+' '+blockName)

		var status = true;
		if (status == true) {
			document.userForm.action = "userAction1.do?methodName=getUserListForReport&level="
					+ level
					+ "&"
					+ tokenParameter
					+ "="
					+ tokenValue
					+ "&loginId="
					+ userID
					+ "&entityCode="
					+ entityCode
					+ "&stateName="
					+ stateName
					+ "&districtName="
					+ districtName + "&blockName=" + blockName;
			document.userForm.submit();
		}
	}
	function closePage() {

		var stateName = '', districtName = '', blockName = '';

		var status = window.confirm('<bean:message key="alert.close.form" />');
		if (status == true) {

			// alert(document.forms[0].blockName)	 

			if (document.forms[0].blockName != null
					&& typeof (document.forms[0].blockName) != 'undefined') {

				document.userForm.action = "userAction1.do?methodName=getStateDetailsForUserReport&flag=block&level=4&"
						+ tokenParameter + "=" + tokenValue;
				document.userForm.submit();

			} else if (document.forms[0].districtName != null
					&& document.forms[0].blockName == null) {

				//document.forms[0].stateCode.value="";
				document.userForm.action = "userAction1.do?methodName=getStateDetailsForUserReport&level=3&"
						+ tokenParameter + "=" + tokenValue;
				document.userForm.submit();

			} else if (document.forms[0].stateName != null
					&& document.forms[0].districtName == null) {

				//document.forms[0].stateCode.value="";
				document.userForm.action = "userAction1.do?methodName=getStateDetails&level=2&"
						+ tokenParameter + "=" + tokenValue;
				document.userForm.submit();

			} else {

				// var status=window.confirm('<bean:message key="alert.close.form" />');
				// alert('sssss')	
				document.forms[0].action = "userAction1.do?methodName=showUsrView&"
						+ tokenParameter + "=" + tokenValue;
				document.forms[0].submit();
			}
		}
	}
</script>


<body>
<html:form action="login/userAction1">
<div class="mainpane">
<table width="100%">
	<tr><td align="center" class="pageHeader">Total User List</td></tr>
		
	<logic:present name="userNotFound">
	<tr>
		<td align="center"><script>alert('<bean:write name="userNotFound"/>');</script></td>
	</tr>
	</logic:present>
	<tr><td align="center">
	<table width="90%" class="inputTBL" align="center">
	<tr>
		<th width="30%"><bean:message key="lable.user.levelofuser" /></th>
		<td><label id="levelCodeID"></label><html:select property="levelCode" styleId="levelCode" style="display:none">
			<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
			<logic:present name="levelList">
				<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
			</logic:present>
			</html:select> <script language="javascript">
		//alert(document.getElementById("loginIDD").id);
			document.getElementById("levelCodeID").innerHTML = document.forms[0].levelCode[document.forms[0].levelCode.selectedIndex].text;
			</script></td>
	</tr>
	
	<logic:present name="stateName">
	<tr>
		<th>State Name</th>
		<td><input type="hidden" name="stateName" value="<bean:write name="stateName"/>" /> 
			<bean:write name="stateName" />
		</td>
	</tr>
	</logic:present>

	<logic:present name="districtName">
	<tr>
		<th>District Name</th>
		<td><input type="hidden" name="districtName" value="<bean:write name="districtName"/>" />
			 <bean:write name="districtName" />
		</td>
	</tr>
	</logic:present>

	<logic:present name="blockName">
	<tr>
		<th>Block Name</th>
		<td><input type="hidden" name="blockName" value="<bean:write name="blockName"/>" />
			<bean:write name="blockName" /></td>
	</tr>
	</logic:present>
							
	<logic:present name="myas">
		<html:hidden property="entityCode"></html:hidden>
	</logic:present>
	</table>
	
	<table width="90%" class="inputTBL" align="center">
	<tr>
		<th>Sl. No</th>
		<th><bean:message key="lable.user.loginID" /></th>
		<th><bean:message key="lable.user.accountStatus" /></th>
	</tr>
	
	<logic:present name="userList">
	<logic:iterate name="userList" id="userID" indexId="index">
	<tr>
		<td width="15px">${index +1}</td>
		<td><a href="javascript:getUserDetails('<bean:write name="userID"  property="propertyCode" />','<bean:write name="entityCode" />')">
			<bean:write name="userID" property="propertyValue" /> </a></td>
		<td><bean:write name="userID" property="genUser" /></td>
	</tr>
	</logic:iterate>
	</logic:present>
</table>
</td></tr>
<tr>
	<td align="center">
	<input name="Button" type="button" class="button" value="Close" onclick="closePage()" />
	</td>
</tr>
</table>
</div>
</html:form>
</body>

