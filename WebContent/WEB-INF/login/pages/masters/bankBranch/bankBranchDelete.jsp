<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="Javascript1.1" src="javaScript/common.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>

<script language="javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 

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
</script>
<html:form action="login/bankBranch">
<table width="100%" >
<tr>
	<td class="pageHeader" align="center"><bean:message key="title.bankBranchDelete"></bean:message></td>
</tr>
<tr>
<td align="center">
	<table width="90%" class="inputTBL">
	<tr>
		<th><bean:message key="label.state" /><span class="mandatory">*</span></th>
		<td><label><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></label></td>
	</tr>
	<tr>
		<th><bean:message key="label.district" /><span class="mandatory">*</span></th>
		<td><label><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="districtName"></bean:write></label></td>
	</tr>
		
	<logic:notEmpty name="bankList">
	<tr>
		<th><bean:message key="label.bankname" /><span class="mandatory">*</span></th>
		<td><html:select property="bankCode" styleId="bankCode" onchange="getBankValue()">
			<html:option value=""> <bean:message key="label.select" /> </html:option>
			<html:options collection="bankList" property="bankCode" labelProperty="bankName"></html:options>
			</html:select>
		</td>
	</tr>
	</logic:notEmpty>
	</table>
	
	<table width="100%" class="inputTBL">
		<logic:present name="bankBranchList">
			<tr>
				<th align="center"><b><bean:message key="label.branchname"/></b></th>
				<th align="center"><bean:message key="label.level"/></th>
				<th align="center"><bean:message key="label.block"/></th>
			 	<th></th>
			</tr>
			<logic:iterate id="bankBranchId" name="bankBranchList">
				<tr id="" class="row1">
					<td align="center"><bean:write name="bankBranchId" property="bankBranchName" /></td>
					<td align="center">
						<logic:equal name="bankBranchId" value="<%=Constants.YES %>" property="levelFlag">
						       <bean:message key="label.block"/>
						</logic:equal>
						<logic:equal name="bankBranchId" value="<%=Constants.NO %>" property="levelFlag">
						       <bean:message key="label.district"/>
						</logic:equal>
					</th>
					<td align="center"><bean:write name="bankBranchId" property="blockName" /></th>
				 	<td align="center"><input type="checkbox"  name="checkBankBranchCodes"  
					value='<bean:write name="bankBranchId" property="bankBranchCode" />' /></td>
				</tr>
			</logic:iterate>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr align="center">
				<td><html:button styleClass="button" onclick="deleteFunctionality();"
					property="">
					<bean:message key="button.delete" />
				</html:button> <html:button styleClass="button" onclick="closePage()" property="">
					<bean:message key="button.close" />
				</html:button><html:button styleClass="button" onclick="resetall();" property=""><bean:message key="button.clear" /></html:button></td>
			</tr>
		</table>
		</logic:present>
	</table>
		
	 
	</table>
</html:form>

<script type="text/javascript">

function resetall(){

    
var status=window.confirm('<bean:message key="msg.clear" />');
	if(status==true)
		{
	


				document.forms[0].reset();
				 
		
	}	
   }

function getBankValue(){
	document.forms[0].action = "bankBranch.do?methodName=getBankDetailsForDeleteModule"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}

function closePage(){
	var status = window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
		submitForm("login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue);
	}
}



function getBranchDetails()
{
	document.forms[0].action = "bankBranch.do?methodName=getBranchDetails"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}

function deleteFunctionality(){	
	var len=document.forms[0].checkBankBranchCodes.length;
	var obj = document.forms[0].checkBankBranchCodes ;
	var val=0;
	
	if(len == undefined){
		if(document.getElementById('checkBankBranchCodes').checked == false){
			alert("<bean:message key="check.product.alert" />");
			return;	
		}
	} 

	for( var i=0; i<len; i++ ){
		if(obj[i].checked == true){ 
		}else{ 
			val=val+1;
		}
	}

	if(val==len){
		alert("<bean:message key="check.product.alert" />");
	}else{
		var status = window.confirm('<bean:message key="msg.deleteRecord" />');
		if(status==true){
			document.forms[0].action="bankBranch.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	} 
}

</script>

