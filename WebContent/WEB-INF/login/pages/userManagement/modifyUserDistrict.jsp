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

function userForm_mask () { 

	 var arg1='<bean:message key="userForm.userName" />';  
	 var arg2='<bean:message key="userForm.userDesignation" />';  
	 
     this.a0 = new Array("userName", '<bean:message key="error.correctvalue"  arg0="'+arg1+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a1 = new Array("userDesignation", '<bean:message key="error.correctvalue"  arg0="'+arg2+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
   
}


function searchUser(){

	if(document.getElementById('levelCode').value==""){
	alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	
	if(lavel=='4'){	
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	}

	if(lavel=='5'){	
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	if(document.getElementById('villageCode').value==""){
	alert("<bean:message key="msg.selectVillage" />");
	return;
	}
 	} 	 	
	if(document.getElementById('loginId').value==""){
		var arg='<bean:message key="lable.user.loginID" />';  
		var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
		alert(mess);			
		return;
	}	
    var status=window.confirm("<bean:message key="msg.selectLevelOfUser" />");
	if(status==true){
	 
	document.userForm.action = "userAction.do?methodName=searchUser&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	
	}
}
function updateUser(form){
	if(! ( validateMask(form))) 
   		 return; 
	
	
	if(document.getElementById('levelCode').value==""){
	alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;		
	
	if(lavel=='4'){	
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	}
	if(lavel=='5'){
	
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	if(document.getElementById('villageCode').value==""){
	alert("<bean:message key="msg.selectVillage" />");
	return;
	}
 	}
 	if(document.getElementById('loginId').value==""){
	alert("<bean:message key="loanId.alert" />");
	return;
	} 
	if(document.getElementById('userName').value==""){
	alert("<bean:message key="msg.enterUserName" />");
	return;
	}
	if(document.getElementById('userDesignation').value==""){
	alert("<bean:message key="msg.enterUserDesignation" />");
	return;
	}	
    var status=window.confirm("<bean:message key="msg.update" />");
	if(status==true){
	document.userForm.action="userAction.do?methodName=modify"+"&"+tokenParameter+"="+tokenValue;;
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
   		
   		var blockUser = document.getElementById('blockCode').value;
   		if(blockUser != ""){
   		getUserByCode();
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
document.getElementById('loginId').value="";
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
 
   if(lavel=='3'){  	
 	getUserByCode(); 	
 }
 
 if(lavel==""){
		if(document.getElementById('roleListRow') != null){
		document.getElementById('roleListRow').className="hide";
		}
	}    

}

function clearUser(){ 

    var status=window.confirm("<bean:message key="msg.clear" />");
	if(status==true){
	document.userForm.action="userAction.do?methodName=clear"+"&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	} 
}
function closePage()
{
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.userForm.action = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	}
}

function getUserByCode(){

var level = document.getElementById('levelCode').value;
var block = document.getElementById('blockCode').value;
var village = document.getElementById('villageCode').value;
var entity = "";
	
	if(level == "3"){			
		entity = document.getElementById('entityCode').value;
			
	}
	if(level == "4"){
		entity = block;
	}
	if(level == "5"){
		entity = village;
	}

  if(entity != ""){
  var url = "manageRoleAction.do?methodName=getUserByCode&entity="+entity;
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseUser;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("loginId");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponseUser(){
var level = document.getElementById('levelCode').value;
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("UserList");
   	try{ 
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    		var selectBox = document.getElementById("loginId");
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
   		
   		if(level == "3"){
				if(document.getElementById('hiddenLoginId').value!=""){
				getUserByCodeOnLoad();
				}
		}		
   		
    }
    else {
    		if(document.getElementById("loginId")!= null){
   			var selectBoxL = document.getElementById("loginId");
    		var lenL = selectBoxL.options.length;
   			for(var kL=lenL-1;kL>0;kL--) {
     		selectBoxL.remove(kL);
    		}
    		}
    alert("<bean:message key="msg.noUserAvailable" />");
    }
}
catch(err)
{
    alert("<bean:message key="error.fetchingUser" />");
}
}
}


function getBlockByDistrictCodeOnLoad(){
var blockCode = document.getElementById('hiddenBlockCode').value;
if(blockCode!=""){
	document.getElementById('blockCode').value=document.getElementById('hiddenBlockCode').value;
	}
	
	if(document.getElementById('hiddenVillageCode').value!=""){
	getVillageByBlockCodeOnLoad();
	}
	if(document.getElementById('hiddenLoginId').value!=""){	
		getUserByCodeOnLoad();
		}
	
}

function getVillageByBlockCodeOnLoad(){
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
	req.onreadystatechange = handleHttpResponseVillageOnLoad;
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

function handleHttpResponseVillageOnLoad(){
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
   		document.getElementById('villageCode').value=document.getElementById('hiddenVillageCode').value;
   		if(document.getElementById('hiddenLoginId').value!=""){
			getUserByCodeOnLoad();
			} 
   		
    }
    else {
    alert(alert("<bean:message key="msg.noVillageForBlock" />");
    }
}
catch(err)
{
   alert("<bean:message key="error.fetchingBlockForBlock" />");
}
}
}


function getUserByCodeOnLoad(){
var level = document.getElementById('levelCode').value;
var block = document.getElementById('blockCode').value;
var village = document.getElementById('villageCode').value;
var entity = "";	
	if(level == "3"){
		entity = document.getElementById('entityCode').value;
	}
	if(level == "4"){
		entity = block;
	}
	if(level == "5"){
		entity = village;
	}

  if(entity != ""){
  var url = "manageRoleAction.do?methodName=getUserByCode&entity="+entity;
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseUserOnLoad;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("loginId");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponseUserOnLoad(){
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("UserList");
   	try{ 
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    		var selectBox = document.getElementById("loginId");
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
   		document.getElementById('loginId').value=document.getElementById('hiddenLoginId').value;
   		  		
   		
    }
    else {
   			 if(document.getElementById("loginId")!= null){
   			var selectBoxL = document.getElementById("loginId");
    		var lenL = selectBoxL.options.length;
   			for(var kL=lenL-1;kL>0;kL--) {
     		selectBoxL.remove(kL);
    		}
    		}
   alert("<bean:message key="msg.noUserAvailable" />");
    }
}
catch(err)
{
    alert("<bean:message key="error.fetchingUser" />");
}
}
}

function resetList(){
	if(document.getElementById('roleListRow') != null){	
	document.getElementById('roleListRow').className="hide";
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
					<td align="center" class="actionstxt"><span class="actionstxt"><bean:message key="lable.update"/></span>
					<bean:message key="lable.user.user"></bean:message></td>
					<html:hidden property="informationDialog"></html:hidden>
					<html:hidden property="informationDialogText"></html:hidden>
					<html:hidden property="informationDialogHeader"></html:hidden>
				</tr>
				<tr>
					<td height="20" class="formlabel"><span class="mandatory">*</span>
					<span class="normaltext"> <bean:message key="lable.field.manadatory"></bean:message></span></td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			
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
					<html:option value=""><bean:message key="label.selectBlock" /></html:option>
					<html:options collection="blockList" property="blockCode" labelProperty="blockName"/>
					</html:select></td>
				</tr>
				
				<tr id="villageRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectVillagePanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
					<html:option value=""><bean:message key="label.selectVillage" /></html:option>
					
					</html:select></td>
				</tr>		
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.loginID"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="loginId" styleId="loginId" onchange="resetList()">
					<html:option value=""><bean:message key="loanId.label" /></html:option>						
					</html:select></td>
				</tr>
				
				<tr>
					<td height="40" colspan="2" align="center">					
					<html:button styleClass="button" property="Search" value="Submit" onclick="searchUser()"/>
					<logic:notEqual name="searchSuccess" value="searchSuccess">
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					</logic:notEqual></td>
				</tr>
			</table>
			<logic:equal name="searchSuccess" value="searchSuccess">
				<table id="roleListRow" class="" width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					
					<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.userName"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="userName"  onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.designation"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="userDesignation" onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.emailID"/><span class="mandatory"></span></td>
					<td class="formlabel"><html:text property="emailId" onblur="this.value=this.value.toUpperCase(),echeck(this.value);"/></td>
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
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>					
						<html:button styleClass="button" property="Clear" value="Clear" onclick="clearUser()"/>
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
					</tr>
				</table>
			</logic:equal>
			 <logic:equal name="searchSuccess" value="searchFail">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td class="formlabel" align="center"><bean:message key="search.fail" /></td>
					</tr>
				</table>
			</logic:equal></td>
		</tr>
		<html:hidden property="entityCode"></html:hidden>		
		<html:hidden property="hiddenBlockCode"></html:hidden>
		<html:hidden property="hiddenVillageCode"></html:hidden>
		<html:hidden property="hiddenLoginId"></html:hidden>
	</table>
	</div>
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<script>
hideShowField();
if(document.getElementById('hiddenBlockCode').value!=""){
getBlockByDistrictCodeOnLoad();
}
</script>
</html:html>
