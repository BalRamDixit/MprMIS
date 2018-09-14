
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="Javascript1.1" src="javaScript/common.js"></script>

<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';
window.onbeforeunload = confirmExit;
	
function confirmExit(){
	var URL = "login.do?methodName=logOut&" + tokenParameter + "=" + tokenValue;
	if (window.event.clientY < 0) {
			var xmlhttp;
			if (window.ActiveXObject) { // code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				xmlhttp.open("GET", URL, false);
				xmlhttp.send();
			}
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
				xmlhttp.open("GET", URL, false);
				xmlhttp.send();
			}
		}
	}
	
	function reset2() {
		var status = window.confirm('<bean:message key="msg.clear" />');
		if (status == true){
			document.forms[0].bankCode = "";
			document.forms[0].bankName = "";
			document.forms[0].bankTypeCode = "";
			document.forms[0].bankShortName = "";
			document.forms[0].bankLevelCode = "";
		}
	}

	function saveBank(){
		if (document.forms[0].bankName.value == "") {
			alert("<bean:message key="msg.bankname" />");
			return false;
		}

		if (!isAlpha(document.forms[0].bankName, "<bean:message key="label.bankname" />")) {
			return false;
		}

		if (document.forms[0].bankShortName.value == "") {
			alert("<bean:message key="msg.bankabbreviation" />");
			return false;
		}

		if (!isAlpha(document.forms[0].bankShortName,
				"<bean:message key="label.bankabbreviation" />")) {
			return false;
		}

		if (document.forms[0].bankTypeCode.value == "") {
			alert("<bean:message key="msg.banktype" />");
			return false;
		}
		if (document.forms[0].bankLevelCode.value == "") {
			alert("<bean:message key="msg.banklevel" />");
			return false;
		}
		var status = window.confirm('<bean:message key="msg.saveForm" />');
		if (status == true) {
			submitForm("bank.do?methodName=save" + "&" + tokenParameter + "="
					+ tokenValue);
		}
	}
</script>

<html:form action="login/bank" onsubmit="return saveBank();">
	<table width="100%">
		<tr>
			<td class="pageHeader" align="center"><bean:message
					key="title.addbank"></bean:message>
			</td>
		</tr>
		<tr><th style="padding-left: 50px;" align="left"><font color="red">*</font> Are Mandatory Field</th></tr>
		<tr>
			<td>
				<table width="90%" align="center" class="inputTBL">
				
				<logic:equal name="BANK_ENTRY_LEVEL" value="STATE_BANK" >
					<tr>
						<th><bean:message key="label.state" /><span class="mandatory">*</span></th>
						<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></td>
					</tr>
				</logic:equal>
			
				<logic:equal name="BANK_ENTRY_LEVEL" value="DISTRICT_BANK" >
					<tr>
						<th><bean:message key="label.state" /><span class="mandatory">*</span></th>
						<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></td>
					</tr>
					<tr>
						<th><bean:message key="label.district" /><span class="mandatory">*</span></th>
						<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="districtName"></bean:write></td>
					</tr>
				</logic:equal>
				
					<tr>
						<th><bean:message key="label.bankname" /><span class="mandatory">*</span></th>
						<td><html:text property="bankName" onblur="this.value=this.value.toUpperCase();" maxlength="40"></html:text></td>
					</tr>
					<tr>
						<th><bean:message key="label.bankabbreviation" /><span class="mandatory">*</span></th>
						<td><html:text property="bankShortName" onblur="this.value=this.value.toUpperCase();" maxlength="12"></html:text></td>
					</tr>
					<tr>
						<th><bean:message key="label.banktype" /><span class="mandatory">*</span></th>
						<td><html:select property="bankTypeCode">
								<html:options collection="banktypes" property="propertyCode" labelProperty="propertyValue" />
							</html:select></td>
					</tr>
					<tr>
						<th><bean:message key="label.level" /><span class="mandatory">*</span></th>
						<td><html:select property="bankLevelCode">
								<html:options collection="banklevel" property="propertyCode" labelProperty="propertyValue" />
							</html:select></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
			<html:button styleClass="button" property="" onclick="saveBank()"><bean:message key="button.save" /></html:button> 
			<html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message key="button.close" /></html:button> 
			<%-- <html:button styleClass="button" property="" onclick="reset2()"><bean:message key="button.clear" /></html:button> --%>
			</td>
		</tr>
	</table>
</html:form>




