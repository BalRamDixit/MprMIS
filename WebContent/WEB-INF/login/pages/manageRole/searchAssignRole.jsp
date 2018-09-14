<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language=javascript>
function closePage(){
	var status=window.confirm("Are you sure you want to Close ");
	if(status==true){
	document.manageRoleForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.manageRoleForm.submit();
	}
}

function deleteRole(roleCode){
	if(document.getElementById('levelCode').value==""){
	alert("Please Select Level of User");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='2'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	}
	if(lavel=='3'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	}
	if(lavel=='4'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	}

	if(lavel=='5'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	 
 	}
 	if(document.getElementById('loginId').value==""){
	alert("Please Select loginId");
	return;
	} 	
	var loginId = document.getElementById('loginId').value;
	var status=window.confirm("Are you sure you want to Delete ");
	if(status==true){
	document.manageRoleForm.action = "manageRoleAction.do?methodName=delete&code="+roleCode+"&loginId="+loginId+"&"+tokenParameter+"="+tokenValue;    
	document.manageRoleForm.submit();

}
}

function editRole(roleCode,levelCode,levelCodeA){
	if(document.getElementById('levelCode').value==""){
	alert("Please Select Level of User");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='2'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	}
	if(lavel=='3'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	}
	if(lavel=='4'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	}

	if(lavel=='5'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	 
 	}
 	if(document.getElementById('loginId').value==""){
	alert("Please Select loginId");
	return;
	} 
	var loginId = document.getElementById('loginId').value;

	document.manageRoleForm.action = "manageRoleAction.do?methodName=showModify&roleCode="+roleCode+"&loginId="+loginId+"&levelCode="+levelCode+"&levelCodeA="+levelCodeA+"&"+tokenParameter+"="+tokenValue+"&paging=page";	    
	document.manageRoleForm.submit();
}


function hideShowField(){
document.getElementById('loginId').value="";
document.getElementById('stateCode').value="";
document.getElementById('districtCode').value="";
document.getElementById('blockCode').value="";
 

var lavel=document.getElementById('levelCode').value;

if(lavel=='2'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="hide";
	document.getElementById('blockRow').className="hide";
 
}
if(lavel=='3'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="hide";
 

}
if(lavel=='4'){
	document.getElementById('stateRow').className="";
	document.getElementById('districtRow').className="";
	document.getElementById('blockRow').className="";
 
}

 
 if(lavel=='0' || lavel=="1" || lavel==""){

 	document.getElementById('stateRow').className="hide";
	document.getElementById('districtRow').className="hide";
	document.getElementById('blockRow').className="hide";
 
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

function getUserByCode(){

var level = document.getElementById('levelCode').value;
var state = document.getElementById('stateCode').value;
var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
 
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
   			alert("There seems to be no User available");
    }
    
   }
}
catch(err)
{
    alert("There seems to be an error fetching User");
}
}
}


function getDistrictByStateCode(){
	var state = document.getElementById('stateCode').value;
	if(state != ""){
		//var url = "userAction.do?methodName=getDistrictByStateCode&state="+state;  
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
	} else{
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


function getDistrictOnReloadOfPage(){
var state = document.getElementById('stateCode').value;
  if(state != ""){
	  // var url = "userAction.do?methodName=getDistrictByStateCode&state="+state;	  
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
		req.onreadystatechange = handleHttpResponseOnLoadState;
		req.send(null);
	}else{
   		var selectBox = document.getElementById("districtCode");
   		var len = selectBox.length;
   		
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}   		   			   		   		
	} 
}

function handleHttpResponseOnLoadState(){
	if(req.readyState == 4){	   
		// var xmlDoc = req.responseXML; 
		// var validate = xmlDoc.getElementsByTagName("DistrictList");
		try{   		
   		 if(req.responseText!=''){
   			var selectBox = document.getElementById("districtCode");
    		var len = selectBox.options.length;
    		for(var k=len-1;k>0;k--) {
     			selectBox.remove(k);
    		}
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
    		alert("There seems to be no District available for This State");
    }
} catch(err){
    alert("There seems to be an error fetching District for This State");
}
}
}




function getBlockByDistrictCode(){

var district = document.getElementById('districtCode').value;

  if(district != ""){
  //var url = "userAction.do?methodName=getBlockByDistrictCode&district="+district;
  
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
catch(err)
{ 
   alert("There seems to be an error fetching block for This District");
   			
}
  
}
}


function getVillageByBlockCode(){
		var blockUser = document.getElementById('blockCode').value;
   		if(blockUser != ""){
   		getUserByCode();
   		}
}    

 

function getRoleByLoginId(){
if(document.getElementById('levelCode').value==""){
	alert("Please Select Level of User");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='2'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	}
	if(lavel=='3'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	}
	if(lavel=='4'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	}

	if(lavel=='5'){
	if(document.getElementById('stateCode').value==""){
	alert("Please Select state");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("Please Select district");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	 
 	}
 	if(document.getElementById('loginId').value==""){
	alert("Please Select loginId");
	return;
	} 	
if(document.getElementById('loginId')!= null){
	var loginId = document.getElementById('loginId').value;
	if(loginId != ""){  
 	document.manageRoleForm.action  = "manageRoleAction.do?methodName=getRoleByLoginId&userId="+loginId+"&"+tokenParameter+"="+tokenValue;
	document.manageRoleForm.submit();
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


function getBlockByDistrictCodeOnLoad(){
var district = document.getElementById('districtCode').value;
  if(district != ""){
 // var url = "userAction.do?methodName=getBlockByDistrictCode&district="+district;
  var url = "manageRoleAction.do?methodName=getBlockByDistrictCode&district="+district+"&blockCodeId="+document.getElementById("blockCode").id;
  
  
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
     
   	try{ 
   	
   		 if(req.responseText !=''){
      	  
    		var selectBox = document.getElementById("blockCode");    		
    		var len = selectBox.options.length;    	
    		
   			for(var k=len-1;k>0;k--) {
     			selectBox.remove(k);
    		}
    		
    		evalScript(req.responseText);
            
            document.getElementById('blockCode').value=document.getElementById('hiddenBlockCode').value;
   		 
			if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!="")){
			getUserByCodeOnLoad();
			}    		
   		
	    }
	    else {
	   		 alert("There seems to be no block available for This District");
	    }
	}
	catch(err)
	{
	    alert("There seems to be an error fetching block for This District");
	}
  }
}

function getVillageByBlockCodeOnLoad(){


var block = document.getElementById('blockCode').value;

if(block != ""){


		if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!="")){
			getUserByCodeOnLoad();
		 } 
			
	}
	 
}    
 

function getUserByCodeOnLoad(){

var level = document.getElementById('levelCode').value;
var state = document.getElementById('stateCode').value;
var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
 
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
	   			alert("There seems to be no User available");
	    }
	    
    }
}
catch(err)
{
    alert("There seems to be an error fetching User");
}
}
}

function resetList(){
	if(document.getElementById('roleListRow') != null){	
	document.getElementById('roleListRow').className="hide";
	}
}
</script>
<html:form action="login/manageRoleAction">
<html:hidden property="hiddenStateCode" styleId="hiddenStateCode" />
<html:hidden property="hiddenDistrictCode" styleId="hiddenDistrictCode" />
<html:hidden property="hiddenBlockCode" styleId="hiddenBlockCode" /> 
<html:hidden property="hiddenVillageCode"  styleId="hiddenVillageCode"/>
<html:hidden property="hiddenLoginId" styleId="hiddenLoginId"/>
<table width="100%">
<tr>
	<td align="center" class="pageHeader"> 
	<bean:message key="lable.manageRole.Search" />
	<bean:message key="label.manageRole.update" />
	<bean:message key="lable.manageRole.role"></bean:message></td>
</tr>
<tr>
<td style="padding-left: 5%;"><span class="mandatory">*</span><span class="normaltext"> <bean:message key="lable.field.manadatory"></bean:message> </span></td>
</tr>
<tr>
<td align="center">
	<table width="90%" align="center" class="inputTBL">
		<tr>
			<th width="30%"><bean:message key="lable.manageRole.levelofuser"></bean:message><span class="mandatory">*</span></th>
			<td><html:select property="levelCode" styleId="levelCode" onchange="resetList();hideShowField()">
				<html:option value="">--Select Level--</html:option>
				<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
				</html:select>
			</td>
		</tr>
		<tr id="stateRow" class="hide">
			<th width="30%"><bean:message key="label.manageRole.selectState" /><span class="mandatory">*</span></th>
			<td><html:select property="stateCode" styleId="stateCode" onchange="resetList();getDistrictByStateCode()">
				<html:option value="">--Select State--</html:option>
				<html:options collection="stateList" property="stateCode" labelProperty="stateName" />
				</html:select>
			</td>
		</tr>
		<tr id="districtRow" class="hide">
			<th width="30%"><bean:message key="lable.manageRole.selectDistrict" /><span class="mandatory">*</span></th>
			<td><html:select property="districtCode" styleId="districtCode" onchange="resetList();getBlockByDistrictCode()">
				<html:option value="">--Select District--</html:option>
				</html:select></td>
		</tr>
		 <tr id="blockRow" class="hide">
			<th width="30%"><bean:message key="lable.manageRole.selectBlockPanchayat" /><span class="mandatory">*</span></th>
			<td><html:select property="blockCode" styleId="blockCode" onchange="resetList();getVillageByBlockCode()">
				<html:option value="">--Select Block Panchayat--</html:option>
				</html:select></td>
		</tr>
		<tr id="villageRow" class="hide">
			<th width="30%"><bean:message key="lable.manageRole.selectVillagePanchayat" /><span class="mandatory">*</span></th>
			<td><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
				<html:option value="">--Select Village Panchayat--</html:option>
				</html:select></td>
		</tr>
		<tr>
			<th width="30%"><bean:message key="lable.manageRole.loginID" /><span class="mandatory">*</span></th>
			<td><html:select property="loginId" styleId="loginId" onchange="resetList()">
				<html:option value="">--Select Login Id--</html:option>
				</html:select></td>
		</tr>
		</table>
		<div align="center">  
		<html:button styleClass="button" property="Search" value="Search" onclick="getRoleByLoginId()" />
		<input name="Button" type="button" class="button" value="Close" onclick="closePage()" />
		</div>
		
		<logic:equal name="searchSuccess" value="searchSuccess">
		<table id="roleListRow" class="inputTBL" width="90%" align="center">
		<tr>
			<th colspan="3"><bean:message key="lable.manageRole.Rolesassignedtotheuser" /></th>
		</tr>
		<logic:iterate id="roleList" name="roleList">
		<tr>
			<td><bean:write name="roleList" property="roleName" />&nbsp;&nbsp;&nbsp;&nbsp;(<bean:write name="roleList" property="roleDesc" />)</td>
			<bean:define id="target"><bean:write name="roleList" property="levelCodeA" /></bean:define>
			<bean:define id="targetRole">
			<bean:write name="roleList" property="roleCode" />
			</bean:define>
									
			<bean:define id="targetL">
				<bean:write name="roleList" property="levelCode" />
			</bean:define>

		<td><logic:notEqual name="roleList" property="levelCode" value="<%=target%>">
			<html:button styleClass="button" property="edit" value="Edit" onclick='<%="editRole("+targetRole+","+targetL+","+target+")" %>' />
			</logic:notEqual>
		</td>
		<td><html:button styleClass="button" property="" value="delete" onclick='<%="deleteRole("+targetRole+")" %>' /></td>
		</tr>
		</logic:iterate>
		</table>
		<!-- <div align="center">
			<input name="Button" type="button" class="button" value="Close" onclick="closePage()" />
		</div> -->
		</logic:equal> 
		
		<logic:equal name="searchSuccess" value="searchFail">
			<table id="noRoleListRow" class="inputTBL" width="90%" align="center">
				<tr>
					<td align="center"><bean:message key="search.fail" /></td>
				</tr>
		</table>
			</logic:equal> 
		</td>
	</tr>
	</table>
</html:form>

<script>
hideShowField();

if((document.getElementById("hiddenStateCode") != null) && (document.getElementById("hiddenStateCode").value!="")){
getStateOnReloadOfPage();
}
</script>


