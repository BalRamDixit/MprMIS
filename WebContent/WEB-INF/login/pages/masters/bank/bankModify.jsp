<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@ page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />


<script language="Javascript1.1" src="javaScript/common.js"></script>
<script type="text/javascript">

var numb = '0123456789';
var lwr = 'abcdefghijklmnopqrstuvwxyz';
var upr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>' ; 
 

window.onbeforeunload=confirmExit;
function confirmExit(){
		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 
function isValid(parm,val)
{
	if (parm == "") 
	return true;

	for (i=0; i<parm.length; i++)
	{
		if (val.indexOf(parm.charAt(i),0) == -1) 
		return false;
	}
	return true;
}

function isNumber(parm)
{
	return isValid(parm,numb);
}

function isLower(parm)
{
	return isValid(parm,lwr);
}

function isUpper(parm)
{
	return isValid(parm,upr);
}

function isAlpha(parm)
{
	return isValid(parm,lwr+upr);
}

function isAlphanum(parm){
	return isValid(parm,lwr+upr+numb);
}

function openBankModifyPopUp(){
	var URL="bank.do?methodName=modifyDetails"+"&"+tokenParameter+"="+tokenValue+"&paging=page";
	window.open(URL,'mywindow','toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=690,height=550,left = 212,top = 84');
}

function getBankValue(){
	document.forms[0].action = "bank.do?methodName=getBankDetails"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}

function closePage(){
	var status = window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
		submitForm("login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue);
	}
}

function preLoad(){
	if(event.keyCode=='0')
	  return false;
	}

function modifyPage(){
	if(document.forms[0].value == ""){
		alert("<bean:message key="msg.bankname" />");
		return false;	
	}
	if(document.forms[0].bankShortName.value ==""){
		alert("<bean:message key="msg.bankabbreviation" />");
		return false;
	}
	
	if(document.forms[0].bankTypeCode.value ==""){
		alert("<bean:message key="msg.banktype" />");
		return false;
	}	
		
	if(document.forms[0].bankLevelCode.value ==""){
		alert("<bean:message key="msg.banklevel" />");
		return false;
	}
	
	if(!isAlphanum(document.forms[0].bankShortName.value)){
		alert("<bean:message key="error.bankAbbreviation" />");
		return false;
	}
	
	if(isNumber(document.forms[0].bankShortName.value)){	
		alert("<bean:message key="error.bankAbbreviation" />");
		return false;
	}
	
	if(!isAlpha(document.forms[0].bankShortName.value)){
		alert("<bean:message key="error.bankAbbreviation" />");
		return false;
	}
	
	var status = window.confirm('<bean:message key="msg.updateForm" />');
	 if(status==true){
	 	submitForm("bank.do?methodName=update"+"&"+tokenParameter+"="+tokenValue);
	 }
}

</script>


<html:form action="login/bank" onsubmit="return preLoad();">
<table width="100%">
<tr>
	<td class="pageHeader" width="100%" align="center"><bean:message key="title.modifybank"></bean:message></td>
</tr>
<tr><th style="padding-left: 50px;" align="left"><font color="red">*</font> Are Mandatory Field</th></tr>
<tr><td>
	<table width="90%" align="center" class="inputTBL">
	<logic:equal name="BANK_ENTRY_LEVEL" value="STATE_BANK" >
	<tr>
		<th width="350px"><bean:message key="label.state" /><span class="mandatory">*</span></th>
		<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></td>
	</tr>
	</logic:equal>
	
	<logic:equal name="BANK_ENTRY_LEVEL" value="DISTRICT_BANK" >
	<tr>
		<th width="350px"><bean:message key="label.state" /><span class="mandatory">*</span></th>
		<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></td>
	</tr>
	<tr>
		<th><bean:message key="label.district" /><span class="mandatory">*</span></th>
		<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="districtName"></bean:write></td>
	</tr>
	</logic:equal>
	<tr>
		<th><bean:message key="label.bankname" /><span class="mandatory">*</span></th>
			<td><html:select property="bankCode" styleId="bankCode" onchange="getBankValue()">
				<html:option value="">
					<bean:message key="label.select" />
				</html:option>
				<html:options collection="bankDetails" property="bankCode" labelProperty="bankName"></html:options>
				</html:select>
			</td>
		</tr>
		<logic:present name="<%= Constants.BANKDETAILS %>">
		<tr>
			<th><bean:message key="label.bankabbreviation" /><span class="mandatory">*</span></th>
			<td><html:text property="bankShortName" name="<%=Constants.BANKDETAILS %>" onblur="this.value=this.value.toUpperCase();"></html:text></td>
		</tr>
		<tr>
			<th><bean:message key="label.banktype" /><span class="mandatory">*</span></th>
			<td><html:select property="bankTypeCode" value="<%=request.getAttribute(Constants.BANK_TYPE).toString()%>">
				<html:options collection="banktypes" property="propertyCode" labelProperty="propertyValue" />
				</html:select>
			</td>
		</tr>
		<tr>
			<th><bean:message key="label.level" /><span class="mandatory">*</span></th>
			<td><html:select property="bankLevelCode" value="<%= request.getAttribute(Constants.BANK_LEVEL).toString()%>">
				<html:options collection="banklevel" property="propertyCode" labelProperty="propertyValue" />
				</html:select>
			</td>
		</tr>
		</logic:present>
	</table></td></tr>
<tr>
	<td align="center">
	<html:button styleClass="button" onclick="modifyPage();" property=""><bean:message key="button.modify" /></html:button> 
	<html:button styleClass="button" onclick="closePage()" property=""><bean:message key="button.close" /></html:button>
	</td>
</tr>						
</table>
</html:form>

