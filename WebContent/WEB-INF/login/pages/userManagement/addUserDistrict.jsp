<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>

</head>
<script language=javascript>

 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
function userForm_mask () { 

	 var arg1='<bean:message key="userForm.userName" />';  
	 var arg2='<bean:message key="userForm.userDesignation" />';  
	 var arg3='<bean:message key="lable.user.loginID" />';  
	
     this.a0 = new Array("userName", '<bean:message key="error.correctvalue"  arg0="'+arg1+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a1 = new Array("userDesignation", '<bean:message key="error.correctvalue"  arg0="'+arg2+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a3 = new Array("loginId", '<bean:message key="error.correctvalue"  arg0="'+arg3+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[a-zA-Z0-9]*$/;  return this[varName];"));
     
}

function saveUser(form){
	 if(!(validateMask(form))) 
   		 return;
	
	if(document.getElementById('userName').value==""){
	alert("<bean:message key="msg.enterUserName" />");
	return;
	}
	
	if(document.getElementById('userDesignation').value==""){
	alert("<bean:message key="msg.enterUserDesignation" />");
	return;
	}
	
	if(document.getElementById('levelCode').value==""){
	alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}
	if((document.getElementById('blockRow').className !="hide") && (document.getElementById('blockCode').value=="")){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	if((document.getElementById('villageRow').className !="hide") && (document.getElementById('villageCode').value=="")){
	alert("<bean:message key="msg.selectVillage" />");
	return;
	}
	
	if(document.getElementById('loginId').value==""){
		var arg='<bean:message key="lable.user.loginID" />';  
		var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
		alert(mess);			
		return;
	}     
    var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
	document.userForm.action="userAction.do?methodName=save&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	} 
}

function getBlockByDistrictCode(){
var district = document.getElementById('districtCode').value;
  if(district != ""){
  var url = "userAction.do?methodName=getBlockByDistrictCode&district="+district;
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseBlock;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("blockCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponseBlock(){
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("BlockList");
   	try{ 
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    		var selectBox = document.getElementById("blockCode");
    		var len = selectBox.options.length;
   			for(var k=len-1;k>0;k--) {
     		selectBox.remove(k);
    		}
   for(var i=1;i<validate[0].childNodes.length;i++){
           var oOption = document.createElement("OPTION");
			oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBox.options.add(oOption);
   		}
    }
    else {
   	alert("<bean:message key="msg.noBlockCodeAvailable" />");
    }
}
catch(err)
{
  	alert("<bean:message key="error.fetchingBlockForDist" />");
}
}
}

function getVillageByBlockCode(){
var block = document.getElementById('blockCode').value;
  if(block != ""){
  var url = "userAction.do?methodName=getVillageByBlockCode&block="+block;
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseVillage;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("villageCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponseVillage(){
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("VillageList");
   	try{ 
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    		var selectBox = document.getElementById("villageCode");
    		var len = selectBox.options.length;
   			for(var k=len-1;k>0;k--) {
     		selectBox.remove(k);
    		}
   for(var i=1;i<validate[0].childNodes.length;i++){
           var oOption = document.createElement("OPTION");
			oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBox.options.add(oOption);
   		}
    }
    else {
   alert("<bean:message key="msg.noVillageForBlock" />");
    }
}
catch(err)
{
  alert("<bean:message key="error.fetchingBlockForBlock" />");
}
}
}


function hideShowField(){
var selectBox = document.getElementById("blockCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}
   		alert();
document.getElementById('blockCode').value="";
document.getElementById('villageCode').value="";

	   		   		
var lavel=document.getElementById('levelCode').value;
if(lavel=='4'){		
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="hide";
}
if(lavel=='5'){		
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="";
 }
 if(lavel=='3' ||  lavel==""){	
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
 } 

}

function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("<bean:message key="error.invalidEmailId" />");
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    alert("<bean:message key="error.invalidEmailId" />");
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("<bean:message key="error.invalidEmailId" />");
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("<bean:message key="error.invalidEmailId" />");
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		     alert("<bean:message key="error.invalidEmailId" />");
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("<bean:message key="error.invalidEmailId" />");
		    return false
		 }
		
		 if (str.indexOf(" ")!=-1){
		     alert("<bean:message key="error.invalidEmailId" />");
		    return false
		 }

 		 return true					
	}
	
function closePage()
{
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.userForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	}
}

</script>
<body>
<html:form action="login/userAction">
	<div class="mainpane">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="actionstxt"><span class="actionstxt"><bean:message key="lable.add"></bean:message></span>
					<bean:message key="lable.user.user"></bean:message></td>
					<html:hidden property="informationDialog"></html:hidden>
					<html:hidden property="informationDialogText"></html:hidden>
					<html:hidden property="informationDialogHeader"></html:hidden>
				</tr>
				<tr>
					<td height="20" class="formlabel"><span class="mandatory">*</span><span
						class="normaltext"> <bean:message key="lable.field.manadatory"></bean:message> </span></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.userName"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="userName" onblur="this.value=this.value.toUpperCase();"/></td>
				</tr>
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.designation"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="userDesignation" onblur="this.value=this.value.toUpperCase();"/></td>
				</tr>
				<tr >
					<td width="50%" class="formlabel"><bean:message key="lable.user.levelofuser"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="levelCode" styleId="levelCode" onchange="hideShowField()">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
					</html:select></td>
				</tr>
				
				<tr id="blockRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					<html:options collection="blockList" property="blockCode" labelProperty="blockName"/>
					</html:select></td>
				</tr>
				
				<tr id="villageRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectVillagePanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="villageCode" styleId="villageCode">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					
					</html:select></td>
				</tr>				
				
			 
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.loginID"/><span	class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="loginId" onblur="this.value=this.value.toUpperCase();"/>
					<br/><font color="red" size="1"><html:errors property="loginId"/></font></td>
				</tr>
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.emailID"/><span class="mandatory"></span></td>
					<td class="formlabel"><html:text property="emailId" onblur="this.value=this.value.toUpperCase(),echeck(this.value);"/>
					<br/><font color="red" size="1"><html:errors property="emailId"/></font></td>
				</tr>
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.accountStatus"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="activeFlag" size="1">
						<html:option value="Y"><bean:message key="lable.open" /></html:option>
						<html:option value="N"><bean:message key="lable.blocked" /></html:option>
					</html:select></td>
				</tr>
				
				
				<tr>
					<td height="40" colspan="2" align="center">
					<html:button styleClass="button" property="next" value="Save" onclick="saveUser(this.form)"></html:button>
						 <input	name="Button" type="reset" class="button" value="Clear" /> 
						  <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	</div>
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<script>
hideShowField();
</script>
</html:html>
