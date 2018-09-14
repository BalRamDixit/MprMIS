<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@page import="com.infotech.sgsy.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>

</head>
<script language=javascript>
function searchUser(){

	var tokenParameter='reqtrack';
 	var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 

	if(document.getElementById('levelCode').value==""){
	alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='3'){	
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	}
	if(lavel=='4'){	
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if(document.getElementById('blockCode').value==""){
		alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	}

	if(lavel=='5'){	
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
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
    var status=window.confirm("<bean:message key="msg.search" />");
	if(status==true){
	//+"&"+tokenParameter+"="+tokenValue
	document.userForm.action="userAction.do?methodName=searchUser&"+tokenParameter+"="+tokenValue;
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
	if(lavel=='3'){	
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	}
	if(lavel=='4'){	
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	}

	if(lavel=='5'){	
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
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
	
	var emailID=document.getElementById('emailId')
	if (emailID.value != null){	
		if (echeck(emailID.value)==false){
		emailID.focus();
		return false;
	  }
	}
    var status=window.confirm("<bean:message key="msg.update" />");
	if(status==true){
	document.userForm.action="userAction.do?methodName=modify"+"&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	} 
}

function getBlockByDistrictCode(){

if(document.getElementById('roleListRow') != null){
									document.getElementById('roleListRow').className="hide";
								}
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
   	
   	if(validate[0]!=null){
   	
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
   		var districtUser = document.getElementById('districtCode').value;
   		if(districtUser != ""){
   		getUserByCode();
   		}
    }
    else {
   alert("<bean:message key="msg.noBlockCodeAvailable" />");
    }
    
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
   	
   	if(validate[0]!=null){
   	
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
}
catch(err)
{
    alert("<bean:message key="error.fetchingBlockForBlock" />");
}
}
}


function hideShowField(val){
//alert(1);
var lavel=document.getElementById('levelCode').value;
if(val=='2'){
	if(document.getElementById('roleListRow') != null){
		document.getElementById('roleListRow').className="hide";
	}
	
	var selectBox1 = document.getElementById("loginId");
   		var len1 = selectBox1.length;   		
   		for(var k=len1-1;k>0;k--){
     			selectBox1.remove(k);
   		}
   		
   		
   		   
}		
		
var selectBox = document.getElementById("blockCode");

   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}
   		
   		   		   
document.getElementById('loginId').value="";
document.getElementById('districtCode').value="";
document.getElementById('blockCode').value="";
document.getElementById('villageCode').value="";



if(lavel=='3'){	
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
	
}
if(lavel=='4'){	
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="hide";

}
if(lavel=='5'){	
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="";
 }
 if(lavel=='2' ||  lavel==""){
	document.getElementById('districtRow').className="hide";
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
 }
 
   if(lavel=='2'){ 
	
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
	document.userForm.action="userAction.do?methodName=showModify"+"&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
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

function userForm_mask () { 

	 var arg1='<bean:message key="userForm.userName" />';  
	 var arg2='<bean:message key="userForm.userDesignation" />';  
	
	 this.a0 = new Array("userName", '<bean:message key="error.correctvalue"  arg0="'+arg1+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a1 = new Array("userDesignation", '<bean:message key="error.correctvalue"  arg0="'+arg2+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
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
var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
var village = document.getElementById('villageCode').value;
var entity = "";

	if(level == "2"){		
		entity = document.getElementById('entityCode').value;				
	}
	if(level == "3"){
		entity = district;
	}
	if(level == "4"){
		entity = block;
	}
	if(level == "5"){
		entity = village;
	}

  if(entity != ""){
 //+"&"+tokenParameter+"="+tokenValue
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
   	
   	if(validate[0]!=null){
   	
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
   		
   		if(level == "2"){   				
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
}
catch(err)
{
    alert("<bean:message key="error.fetchingUser" />");
}
}
}



function getDistrictOnReloadOfPage(){

var districtCode = document.getElementById('hiddenDistrictCode').value;
if(districtCode!=""){
	document.getElementById('districtCode').value=document.getElementById('hiddenDistrictCode').value;
	
	}
	
	if(document.getElementById('hiddenBlockCode').value!=""){
	
	getBlockByDistrictCodeOnLoad();
	}
	if(document.getElementById('hiddenLoginId').value!=""){
		
		getUserByCodeOnLoad();
		}
	
}

function getBlockByDistrictCodeOnLoad(){

var district = document.getElementById('districtCode').value;
//+"&"+tokenParameter+"="+tokenValue
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
	req.onreadystatechange = handleHttpResponseBlockOnLoad;
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

function handleHttpResponseBlockOnLoad(){
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("BlockList");
   	try{ 
   	
   	if(validate[0]!=null){
   	
   	
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
   		document.getElementById('blockCode').value=document.getElementById('hiddenBlockCode').value;
   		if(document.getElementById('hiddenVillageCode').value!=""){
			getVillageByBlockCodeOnLoad();
			}
			if(document.getElementById('hiddenLoginId').value!=""){
			getUserByCodeOnLoad();
			}    		
   		
    }
    else {
   alert("<bean:message key="msg.noBlockCodeAvailable" />");
    }
    
    }
}
catch(err)
{
    alert("<bean:message key="error.fetchingBlockForDist" />");
}
}
}

function getVillageByBlockCodeOnLoad(){

//+"&"+tokenParameter+"="+tokenValue

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
   	
   	if(validate[0]!=null){
   	 
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
     alert("<bean:message key="msg.noVillageForBlock" />"); 
    }
    
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
var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
var village = document.getElementById('villageCode').value;
var entity = "";	
	if(level == "2"){		
		entity = document.getElementById('entityCode').value;
			
	}
	if(level == "3"){
		entity = district;
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
   	
   	
   
   	
   	if(validate[0]!=null){
   	
   	
   			var selectBox = document.getElementById("loginId");
   	
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    	
    	 	var len = selectBox.options.length;
   			 for(var k=len-1;k>0;k--) {
     		 	selectBox.remove(k);
    		 }
    		 
    		   //oOption.text ='<bean:message key="label.select"/>';
    		   //oOption.value ="";
    		   //selectBox.options.add(oOption);
    		   
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
}
catch(err)
{

	//alert('err==='+err)
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
					<span class="normaltext"> <bean:message key="lable.field.manadatory"></bean:message> </span></td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr >
					<td width="50%" class="formlabel"><bean:message key="lable.user.levelofuser"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="levelCode" styleId="levelCode" onchange="hideShowField('2')">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
					</html:select></td>
				</tr>
				  
				<tr id="districtRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectDistrict"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="districtCode" styleId="districtCode" onchange="getBlockByDistrictCode()">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					
					
					<logic:present name="districtList">
					<html:options collection="districtList" property="districtCode" labelProperty="districtName"/>
					</logic:present>
					
					
					
					</html:select></td>
				</tr>
				
				<tr id="blockRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					
					</html:select></td>
				</tr>
				<tr id="villageRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectVillagePanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					
					</html:select></td>
				</tr>
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.loginID"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="loginId" styleId="loginId" onchange="resetList()">
					<html:option value=""> <bean:message key="label.select"/> <bean:message key="lable.manageRole.loginID"/></html:option>						
					</html:select></td>
				</tr>
				
				<tr>
					<td height="40" colspan="2" align="center">				
					<html:button styleClass="button" property="Search" value="Submit" onclick="searchUser()"/>
					<logic:notEqual name="searchSuccess" value="searchSuccess">
					<html:button styleClass="button" property="Clear" value="Clear" onclick="clearUser()"/>
					   
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					</logic:notEqual></td>
				</tr>
			</table>
			  
	
			  
			  
			<logic:equal name="searchSuccess" value="searchSuccessNotAuthorised">
			
				<table id="roleListRow" class="" width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					
					<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.userName"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="userName" onblur="this.value=this.value.toUpperCase();"/></td>
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
							<html:option value="N"><bean:message key="lable.notauthorised" /></html:option>
						</html:select></td>
					</tr>

					<tr>
						<td height="40" colspan="2" align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>					
						<html:button styleClass="button" property="Clear" value="Clear" onclick="clearUser()"/>
					    <input	name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
					</tr>
				</table>
			</logic:equal>
			<logic:equal name="searchSuccess" value="searchSuccessOpen">
			
				<table id="roleListRow" class="" width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					
					<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.user.userName"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:text property="userName" onblur="this.value=this.value.toUpperCase();"/></td>
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
							<html:option value="N"><bean:message key="lable.notauthorised" /></html:option>
						</html:select></td>
					</tr>

					<tr>
						<td height="40" colspan="2" align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>					
						<html:button styleClass="button" property="Clear" value="Clear" onclick="clearUser()"/>
					    <input	name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
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
		<html:hidden property="hiddenDistrictCode"></html:hidden>
		<html:hidden property="hiddenBlockCode"></html:hidden>
		<html:hidden property="hiddenVillageCode"></html:hidden>
		<html:hidden property="hiddenLoginId"></html:hidden>
	</table>
	</div>
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<script>
hideShowField('1');
if(document.getElementById('hiddenDistrictCode').value!=""){

getDistrictOnReloadOfPage();
}
</script>
</html:html>
