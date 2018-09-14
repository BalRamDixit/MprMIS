<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@ page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<html>
<head>
<title>Delete Bank Record</title>
<script type="text/javascript">

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
function closeFunctionality()
{
	var status = window.confirm('<bean:message key="alert.close.form" />');
	if(status==true)
	{
		document.forms[0].action="login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	}
}

function deleteFunctionality()
{	
	var len=document.forms[0].checkBankCodes.length;
	var obj = document.forms[0].checkBankCodes ;
	var val=0;
	
	if(len == undefined)
	{
		if(document.getElementById('checkBankCodes').checked == false)
		{
			alert("<bean:message key="check.product.alert" />");
			return;	
		}
	} 

	for(i=0;i<len ; i++ )
	{
		if(obj[i].checked == true)
		{ 
		}else{ 
			val=val+1;
		}
	}

	if(val==len)
	{
		alert("<bean:message key="check.product.alert" />");
	}else{
//		document.forms[0].action = "product.do?methodName=removeProductDetails";
//		document.forms[0].submit();
		var status = window.confirm('<bean:message key="msg.deleteRecord" />');
		if(status==true)
		{
			document.forms[0].action="bank.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	} 

//--------------------------------------------------------
//	var len = document.forms[0].checkBankCodes.length;
//	alert(len);
//	var obj = document.forms[0].checkBankCodes ;
//	var val=0;

//	if(len == undefined)
//	{
//		if(document.getElementById('checkBankCodes').checked == false)
//		{
//			alert("<bean:message key="check.product.alert" />");
//			return;
//		}
//	} 

//	for(i=0; i<len ; i++ )
//	{
//		if(obj[i].checked == true)
//		{ 
//			val=val+1;
//		}
//	}
//	if(val==0)
//	{
//		alert('<bean:message key="alert.selectBankToDelete" />');
//		return;
//	}
	
	
}
function reset2()
	{
		 
			var status=window.confirm('<bean:message key="msg.clear" />');
			if(status==true)
		
			{ 
				document.forms[0].reset();
				 
			}	
	}

</script>
</head>
<body>
<html:form action="login/bank">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		 
		<tr>
			<td class="actionstxt" width="100%" align="center"><bean:message
				key="title.deleteBankDetails"></bean:message></td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="formlabel"><bean:message key="label.state" /><span>*</span></td>
			<td class="formlabel"><label><bean:write
				name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></label></td>
		</tr>
		<tr>
			<td class="formlabel"><bean:message key="label.district" /><span
				class="mandatory">*</span></td>
			<td class="formlabel"><label><bean:write
				name="<%=SGSYConstants.SGSY_LOCATIONVO%>" property="districtName"></bean:write></label>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<logic:present name="bankDetails">
			<tr class="formsubheading">
				<td class="formlabel" align="center"><b><bean:message
					key="label.bankname" /></b></td>
				<td class="formlabel"></td>
			</tr>
			<logic:iterate id="bankId" name="bankDetails">
				<tr class="row1">
					<td class="formlabel"><bean:write name="bankId"
						property="bankName" /></td>
					<td class="formlabel"><input type="checkbox" name="checkBankCodes"  
					value='<bean:write name="bankId" property="bankCode" />' /></td>
				</tr>
			</logic:iterate>
		<table width="100%" cellpadding="0" cellspacing="0">
			<br />
			<tr align="center">
				<td><html:button styleClass="button" onclick="deleteFunctionality();"
					property="">
					<bean:message key="button.delete" />
				</html:button> <html:button styleClass="button" onclick="closeFunctionality();" property="">
					<bean:message key="button.close" />
				</html:button>
				
					 <html:button styleClass="button" property="" onclick="reset2()"><bean:message  key="button.clear" /></html:button>
				
				
				</td>
			</tr>
		</table>
		</logic:present>
	</table>
</html:form>
</body>
</html>