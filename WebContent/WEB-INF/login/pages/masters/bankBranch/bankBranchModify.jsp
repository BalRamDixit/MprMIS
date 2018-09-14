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
 
///window.onbeforeunload=confirmExit;
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

function resetall(){  
	var status=window.confirm('<bean:message key="msg.clear" />');
	if(status==true){
	document.forms[0].bankCode.value="";
    document.forms[0].bankBranchCode.value="";
	document.forms[0].action = "bankBranch.do?methodName=showModify"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
	}	
   }
   
<%-- function showBlock(obj){
	var villageSection=document.getElementById("villageSection");
	var blockSection=document.getElementById("blockSection");
	if(obj=="<%=Constants.YES%>"){
		villageSection.style.display='';
		blockSection.style.display='';
		addOption(document.getElementById("blockCodes"),'<bean:message key="label.select"/>','');
		document.getElementById("entityCode").value=''; 
		document.getElementById("blockCodes").value='';
	}
	else{
		villageSection.style.display='none';
		blockSection.style.display='none';
	}
} --%>
 
/* var numb = '0123456789';
var lwr = 'abcdefghijklmnopqrstuvwxyz';
var upr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'; */

/* function isValid(parm,val)
{
	 var flag="0";
	if (parm == ""){
		//alert('fdfdf=='+param); 
		return true;
	}
	for (i=0; i<parm.length; i++)
	{
		 
		if (val.indexOf(parm.charAt(i),0) == -1) {
			flag="1";
			break;
	 	}
		
	}
	if(flag=="1"){
	  //alert('in if');
	  return false;
	 }
	 else{
	 	//alert('in else');
		 return true;}
} */

function isNumber(parm){
	return isValid(parm,numb);
}

function isLower(parm){
	return isValid(parm,lwr);
}

function isUpper(parm){
	return isValid(parm,upr);
}

function isAlpha(parm){
	return isValid(parm,lwr+upr);
}

function isAlphanum(parm){
	return isValid(parm,lwr+upr+numb);
}

function getBankValue(){
	document.forms[0].action = "bankBranch.do?methodName=getBankDetails"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}

function closePage(){
	var status = window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
		submitForm("login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue);
	}
}

function modifyPage(){
	if(validate()){
		var status=window.confirm('<bean:message key="msg.updateForm" />');
		if(status==true){
			document.forms[0].action = "bankBranch.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}	
	}
}

function validate(){
	var ifscCode = new LiveValidation('ifscCode',{onlyOnSubmit:true});	
		ifscCode.add( Validate.Presence );
		ifscCode.add( Validate.Length, { is: 11} );
		ifscCode.add( Validate.Format, { pattern: /^[a-z\d]+$/i, failureMessage: "Only AlphaNumericValue are Allowed."});
				
	var address = new LiveValidation('address',{onlyOnSubmit:true});
		address.add( Validate.Presence ); 
		address.add( Validate.Length, { maximum: 250 } );
		/* address.add( Validate.Format, { pattern: /^[a-z\d\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."}); */
		
	var areAllValid = LiveValidation.massValidate( [ifscCode, address ] );
	return areAllValid;
}  

function getBranchDetails(){
	document.forms[0].action = "bankBranch.do?methodName=getBranchDetails"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}
</script>

<html:form action="login/bankBranch">
<table width="100%">
<tr>
	<td class="pageHeader"align="center"><bean:message key="title.modifyBankBranch"></bean:message></td>
</tr>
<tr><th style="padding-left: 50px;" align="left"><font color="red">*</font> Are Mandatory Field</th></tr>
<logic:present name="NOTIFICATION">
<tr>
<td align="center"><font size="2" style="color: green;">${NOTIFICATION}</font></td>
</tr>
</logic:present>
<tr><td align="center">
	<table width="90%" align="center" class="inputTBL">
	<!-- FOR STATE LOGIN -->	
	<logic:equal name="BRANCH_ENTRY_LEVEL" value="STATE_BRANCH" >
		<tr>
			<th><bean:message key="label.state" /></th>
			<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></td>
		</tr>
	</logic:equal>
	<!-- FOR DISTRICT LOGIN -->
	<logic:equal name="BRANCH_ENTRY_LEVEL" value="DISTRICT_BRANCH" >	
		<tr>
			<th><bean:message key="label.state" /></th>
			<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></td>
		</tr>
		<tr>
			<th><bean:message key="label.district" /></th>
			<td><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="districtName"></bean:write></td>
		</tr>
	</logic:equal>	

		<tr>
			<th width="20%"><bean:message key="label.bankname" /><span class="mandatory">*</span></th>
			<td>
				<html:select property="bankCode" styleId="bankCode" onchange="getBankValue()">
				<html:option value=""> <bean:message key="label.select" /></html:option>
				<logic:notEmpty name="bankList">
			    <html:options collection="bankList" property="bankCode" labelProperty="bankName"></html:options>
			    </logic:notEmpty>
				</html:select>
				
			</td>
		</tr>

		<tr>
			<th><bean:message key="label.branchname" /><span class="mandatory">*</span></th>
			<td>
				<html:select property="bankBranchCode" styleId="bankBranchCode" onchange="getBranchDetails()">
				<html:option value=""><bean:message key="label.select" /></html:option>
				<logic:present name="bankBranchList">
				<html:options collection="bankBranchList" property="bankBranchCode" labelProperty="bankBranchName" ></html:options>
				</logic:present>
				</html:select>				
			</td>
		</tr>
		<logic:present name="bankBranchVO">	
		<tr>
			<th><bean:message key="label.branchabbreviation" /><span class="mandatory">*</span></th>
			<td ><html:text name="bankBranchVO" styleId="ifscCode" property="ifscCode" maxlength="11" size="11" onblur="this.value=this.value.toUpperCase();" ></html:text></td>
		</tr>
			
		<tr>
			<th><bean:message key="label.bankBranchCode" /></th>
			<td><html:text name="bankBranchVO" property="bankBranchCode" disabled="true"/>
				<html:hidden name="bankBranchVO" property="entityCode" />
				<html:hidden name="bankBranchVO" property="bankBranchName" />
			</td>
		</tr>	
		<tr>
			<th><bean:message key="bankForm.address" /><span class="mandatory">*</span></th>
			<td><html:textarea styleId="address" style="width:75%;" rows="3" name="bankBranchVO" property="address" onblur="this.value=this.value.toUpperCase();" /></td>
			</tr>
	</logic:present>
	</table>
	</td>
	</tr>
			
	<tr align="center"> 
		<td>
		<html:button styleClass="button" onclick="modifyPage();" property=""><bean:message key="button.modify" /></html:button>
		<html:button styleClass="button" onclick="closePage();" property=""><bean:message key="button.close" /></html:button>
		<html:button styleClass="button" onclick="resetall();" property=""><bean:message key="button.clear" /></html:button>
		</td>
	</tr>
</table>
</html:form>



