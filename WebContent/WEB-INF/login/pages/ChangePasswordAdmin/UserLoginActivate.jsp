<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>

<script language=javascript>
 
function searchUser(form){
	
	if(document.getElementById('levelCode').value==""){
		alert("<bean:message key="msg.selectLevelOfUser" />");
		return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='2'){
		if(document.getElementById('stateCode').value==""){
			alert("<bean:message key="msg.selectState" />");
			return;
		}
	}
	if(lavel=='3'){
		if(document.getElementById('stateCode').value==""){
			alert("<bean:message key="msg.selectState" />");
			return;
		}
		if(document.getElementById('districtCode').value==""){
			alert("<bean:message key="msg.selectDistrict" />");
			return;
		}
	}
	if(lavel=='4'){
		if(document.getElementById('stateCode').value==""){
			alert("<bean:message key="msg.selectState" />");
			return;
		}
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
		if(document.getElementById('stateCode').value==""){
			alert("<bean:message key="msg.selectState" />");
			return;
		}
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
    var status= true;
	if(status==true){
		document.userForm.action="adminChangePassword.do?methodName=activateDone"+"&"+tokenParameter+"="+tokenValue;
		document.userForm.submit();
	}
}


/* FUNCTION USED TO GET THE DISTRICT LIST */
function getDistrictByStateCode(){
	var state = document.getElementById('stateCode').value;
  	if(state != ""){
  		var districtCodeId=document.getElementById('districtCode').id;
  		var url = "manageRoleAction.do?methodName=getDistrictByStateCode&state="+state+"&districtCodeId="+districtCodeId;
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponse;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("districtCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponse(){
if (req.readyState == 4){	
   	try{ 
   	 	if(document.getElementById("districtCode")!=null){
    		var selectBox = document.getElementById("districtCode");
    		var len = selectBox.options.length;
   			for(var k=len-1;k>0;k--) {
     		selectBox.remove(k);
    		}
 		}
 		 
   	   if(req.responseText!=''){ 
   		  evalScript(req.responseText);
   		 var stateUser = document.getElementById('stateCode').value;	
   		if(stateUser != ""){
   				getUserByCode();
   		}
    }
    else {
   alert("<bean:message key="msg.noDistrictsAvailable" />");
    }
}
catch(err){
     alert("<bean:message key="error.fetchingDistrictForState" />");
	}
}
}

/* FUNCTION USED TO GET THE BLOCK LIST */
function getBlockByDistrictCode(){
	var district = document.getElementById('districtCode').value;
  	if(district != ""){
  		var url = "manageRoleAction.do?methodName=getBlockByDistrictCode&district="+district+"&blockCodeId="+document.getElementById("blockCode").id;
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
try{ 
     if(req.responseText !=''){ 		
      	var selectBoxRole = document.getElementById("blockCode");
      	var len = selectBoxRole.length; 
	   		for(var k=len-1;k>0;k--){
	     			selectBoxRole.remove(k);
	   		}	 
   		evalScript(req.responseText);   			
   	   var districtUser = document.getElementById('districtCode').value;
   		if(districtUser != ""){
   			getUserByCode();
   		}
     }  
    else {    
    		alert("There seems to be no block available for This District");    		
   			var selectBoxRole = document.getElementById("blockCode");
      		var len = selectBoxRole.length;   		
	   		for(var k=len-1;k>0;k--){
	     			selectBoxRole.remove(k);
	   		}    	  	
    }
}
	catch(err){ 
  	 alert("There seems to be an error fetching block for This District");   			
	}
}
}

/* function getVillageByBlockCode(){
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
       var blockUser = document.getElementById('blockCode').value;
   	   if(blockUser != ""){
   		  getUserByCode();
   		}
} */

function hideShowField(){
document.getElementById('loginId').value="";
document.getElementById('stateCode').value="";
document.getElementById('districtCode').value="";
document.getElementById('blockCode').value="";
document.getElementById('villageCode').value="";


var lavel=document.getElementById('levelCode').value;

if(lavel=='2'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="hide";
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
}
if(lavel=='3'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";

}
if(lavel=='4'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="hide";
}

if(lavel=='5'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="";
 }
 if(lavel=='0' || lavel=="1" || lavel==""){
 document.getElementById('stateRow').className="hide";
	document.getElementById('districtRow').className="hide";
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
 }
 if(lavel=='0' || lavel=='1'){ 	
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
	document.userForm.action="userAction.do?methodName=clear";
	document.userForm.submit();
	} 
}

function closePage(){
	var status=window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
	document.userForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.userForm.submit();
	}
}


function getUserByCode(){
	var level = document.getElementById('levelCode').value;
	var state = document.getElementById('stateCode').value;
	var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
var village = document.getElementById('villageCode').value;
var entity = "";
	
	if(level == "0"){
		entity = level;		
	}
	
	if(level == "1"){	
		entity = "9999999";
	}
	if(level == "2"){
		entity = state;
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
  var url = "manageRoleAction.do?methodName=getUserByCode&entity="+entity+"&level="+level;
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
   		if(level == "0" || level == "1"){
				if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!="")){
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
}}
catch(err)
{
    alert("<bean:message key="error.fetchingUser" />");
}
}
}


function getStateOnReloadOfPage(){
var stateCode = document.getElementById('hiddenStateCode').value;
if(stateCode!=""){
	document.getElementById('stateCode').value=document.getElementById('hiddenStateCode').value;
	}
	
	if((document.getElementById('hiddenDistrictCode')!= null) &&(document.getElementById('hiddenDistrictCode').value!="")){
	getDistrictOnReloadOfPage();
	}
	if((document.getElementById('hiddenLoginId')!= null)&&(document.getElementById('hiddenLoginId').value!="")){
			getUserByCodeOnLoad();
			}
	
}

function getDistrictOnReloadOfPage(){
var state = document.getElementById('stateCode').value;

  if(state != ""){
  var url = "userAction.do?methodName=getDistrictByStateCode&state="+state;
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseOnLoadState;
	req.send(null);
	}

	else{
   		var selectBox = document.getElementById("districtCode");
   		var len = selectBox.length;
   		
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}   		   			   		   		
	} 
}

function handleHttpResponseOnLoadState(){
if (req.readyState == 4){	
    //var xmlDoc = req.responseXML; 
    //var validate = xmlDoc.getElementsByTagName("DistrictList");
   	try{ 
   		//if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    		var selectBox = document.getElementById("districtCode");
    		var len = selectBox.options.length;
    		
    		
   			for(var k=len-1;k>0;k--) {
     		selectBox.remove(k);
    		}
    		/*
   			
   for(var i=1;i<validate[0].childNodes.length;i++){
           var oOption = document.createElement("OPTION");
			oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBox.options.add(oOption);	
			
   		}*/

   	   if(req.responseText!=''){
   		   evalScript(req.responseText);
   		   
   		document.getElementById('districtCode').value=document.getElementById('hiddenDistrictCode').value;
   		if((document.getElementById('hiddenBlockCode')!= null)&&(document.getElementById('hiddenBlockCode').value!="")){
			getBlockByDistrictCodeOnLoad();
			}
			if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!="")){
			getUserByCodeOnLoad();
			} 
   		
   		
    }
    else {
    alert("<bean:message key="msg.noDistrictsAvailable" />");
    }
}
catch(err)
{
   alert("<bean:message key="error.fetchingDistrictForState" />");
}
}
}


function getBlockByDistrictCodeOnLoad(){
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
   		if((document.getElementById('hiddenVillageCode')!=null )&& (document.getElementById('hiddenVillageCode').value!="")){
			getVillageByBlockCodeOnLoad();
			}
			if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!="")){
			getUserByCodeOnLoad();
			}	
    }
    else {
    alert("<bean:message key="msg.noBlockCodeAvailable" />");
    }
   	 }
}
catch(err){
     alert("<bean:message key="error.fetchingBlockForDist" />");
}
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
   		if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!="")){
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
var state = document.getElementById('stateCode').value;
var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
var village = document.getElementById('villageCode').value;
var entity = "";	
	if(level == "0"){
		entity = level;
	}
	if(level == "1"){		
		entity = "9999999";
	}
	if(level == "2"){
		entity = state;
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
  var url = "manageRoleAction.do?methodName=getUserByCode&entity="+entity+"&level="+level;
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



<html:form action="login/adminChangePassword">
<table width="100%">
<tr>
	<td align="center" class="pageHeader">Activate User Account
	<html:hidden property="informationDialog" />
	<html:hidden property="informationDialogText" />
	<html:hidden property="informationDialogHeader" />
	</td>				
</tr>
<tr>
	<td style="padding-left: 5%;"><span class="mandatory">*</span><bean:message key="lable.field.manadatory"></bean:message></td>
</tr>
<tr><td>
<table width="90%" align="center" class="inputTBL">
	<tr>
		<th width="30%"><bean:message key="lable.user.levelofuser" /><span class="mandatory">*</span></th>
		<td><html:select property="levelCode" styleId="levelCode" onchange="hideShowField()">
			<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
			<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
			</html:select>
		</td>
	</tr>
	<tr id="stateRow" class="hide">
		<th><bean:message key="lable.user.selectState" /><span class="mandatory">*</span></th>
		<td><html:select property="stateCode" styleId="stateCode" onchange="getDistrictByStateCode()">
			<html:option value="">
				<bean:message key="label.selectState" />
			</html:option>
			<html:options collection="stateList" property="stateCode" labelProperty="stateName" />
			</html:select></td>
	</tr>
	<tr id="districtRow" class="hide">
		<th><bean:message key="lable.user.selectDistrict" /><span class="mandatory">*</span></th>
		<td><html:select property="districtCode" styleId="districtCode" onchange="getBlockByDistrictCode()">
			<html:option value=""><bean:message key="label.selectDistrict" />
			</html:option>
			</html:select></td>
	</tr>
	<tr id="blockRow" class="hide">
		<th><bean:message key="lable.user.selectBlockPanchayat" /><span class="mandatory">*</span></th>
		<td><html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
			<html:option value=""><bean:message key="label.selectBlock" /></html:option>
			</html:select></td>
	</tr>
	<tr id="villageRow" class="hide">
		<th><bean:message key="lable.user.selectVillagePanchayat" /><span class="mandatory">*</span></th>
		<td><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
			<html:option value="">
			<bean:message key="label.selectVillage" />
			</html:option>
			</html:select></td>
	</tr>
	<tr>
		<th><bean:message key="lable.user.loginID" /><span class="mandatory">*</span></th>
		<td><html:select property="loginId" styleId="loginId">
			<html:option value=""><bean:message key="select.label" /></html:option>
			</html:select>
		</td>
	</tr>			
	</table>
</td></tr>
<tr><td align="center">
	<html:button styleClass="button" property="Search" value="Submit" onclick="searchUser(this.form)" />
	<logic:notEqual name="searchSuccess" value="searchSuccess">
	<input name="Button" type="button" class="button" value="Close" onclick="closePage()" />
	</logic:notEqual>
<html:hidden property="hiddenStateCode" />
<html:hidden property="hiddenDistrictCode" />
<html:hidden property="hiddenBlockCode" />
<html:hidden property="hiddenVillageCode" />
<html:hidden property="hiddenLoginId" />
</td></tr>
</table>
</html:form>


