<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head></head>

<script language=javascript>


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


function closePage(){
	var status=window.confirm('<bean:message key="alert.close.form" />');
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
	
	if(lavel=='4'){	
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	}
	if(lavel=='5'){
	
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	if(document.getElementById('villageCode').value==""){
	alert("Please Select village");
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
	
	if(lavel=='4'){	
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	}
	if(lavel=='5'){
	
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	if(document.getElementById('villageCode').value==""){
	alert("Please Select village");
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
    alert("There seems to be no User available");
    }
}
catch(err)
{
    alert("There seems to be an error fetching User");
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
    alert("There seems to be no Village available for This Block");
    }
}
catch(err)
{
    alert("There seems to be an error fetching Village for This Block");
}
}
}


function getRoleByLoginId(){

	if(document.getElementById('levelCode').value==""){
	alert("Please Select Level of User");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;		
	
	if(lavel=='4'){	
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	}
	if(lavel=='5'){
	
	if(document.getElementById('blockCode').value==""){
	alert("Please Select block");
	return;
	}
	if(document.getElementById('villageCode').value==""){
	alert("Please Select village");
	return;
	}
 	}
 	if(document.getElementById('loginId').value==""){
	alert("Please Select loginId");
	return;
	} 	

	var loginId = document.getElementById('loginId').value;
	
	if(loginId != ""){  
 	document.manageRoleForm.action  = "manageRoleAction.do?methodName=getRoleByLoginId&userId="+loginId+"&"+tokenParameter+"="+tokenValue;  
	document.manageRoleForm.submit();
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
    alert("There seems to be no User available");
    }
}
catch(err)
{
    alert("There seems to be an error fetching User");
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
    alert("There seems to be no Village available for This Block");
    }
}
catch(err)
{
    alert("There seems to be an error fetching Village for This Block");
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
    	
    alert("There seems to be no User available");
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
<body>
<html:form action="login/manageRoleAction">
<table width="100%" >
  <tr>
      <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" class="actionstxt"><span class="actionstxt"><bean:message key="lable.manageRole.Search"></bean:message></span>
				<bean:message key="lable.manageRole.role"></bean:message></td>
			</tr>
			<tr>
				<td height="20" class="formlabel"><span class="mandatory">*</span><span
					class="normaltext"> <bean:message key="lable.field.manadatory"></bean:message> </span></td>
			</tr>
			</table>     
          
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
				<tr >
					<td width="50%" class="formlabel"><bean:message key="lable.user.levelofuser"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="levelCode" styleId="levelCode" onchange="hideShowField()">
					<html:option value="">--Select Level--</html:option>
					<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
					</html:select></td>
				</tr>
				
				<tr id="blockRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
					<html:option value="">--Select Block Panchayat--</html:option>
					<html:options collection="blockList" property="blockCode" labelProperty="blockName"/>
					
					</html:select></td>
				</tr>
				<tr id="villageRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.user.selectVillagePanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
					<html:option value="">--Select Village Panchayat--</html:option>
					
					</html:select></td>
				</tr>	
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.loginID"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="loginId" styleId="loginId" onchange="resetList()">
					<html:option value="">--Select Login Id--</html:option>						
					</html:select></td>
				</tr>
				
				<tr>
					<td height="40" colspan="2" align="center">					
					<html:button styleClass="button" property="Search" value="Search" onclick="getRoleByLoginId()"/>
					<logic:notEqual name="searchSuccess" value="searchSuccess">
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					</logic:notEqual></td>
				</tr>				
				
			</table>
		
		<logic:equal name="searchSuccess" value="searchSuccess">

			<table id="roleListRow" class="" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

				<tr class="formtitle">					
					<td colspan="3" class="formlabel"><bean:message key="lable.manageRole.Rolesassignedtotheuser"/> </td>
				</tr>

				<logic:iterate id="roleList" name="roleList">
					<tr>
						
						<td class="formlabel"><bean:write name="roleList" property="roleName" />&nbsp;&nbsp;&nbsp;&nbsp;( 
						<bean:write name="roleList" property="roleDesc" />)</td>
						
						<bean:define id="target">
						<bean:write name="roleList" property="levelCodeA" />
						</bean:define>
						<bean:define id="targetRole">
						<bean:write name="roleList" property="roleCode" />
						</bean:define>
						<bean:define id="targetL">
						<bean:write name="roleList" property="levelCode" />
						</bean:define>	
						
						<td class="formlabel">
						<logic:notEqual name="roleList" property="levelCode"  value="<%=target%>"> 
						<html:button styleClass="button" property="edit" value="Edit" onclick='<%="editRole("+targetRole+","+targetL+","+target+")" %>'/></logic:notEqual>
						</td>
						<td class="formlabel"><html:button styleClass="button" property="" value="delete" onclick='<%="deleteRole("+targetRole+")" %>'/>
						</td>			
                        
				   	</tr>
				</logic:iterate>
				
					<tr>
					<td height="40" colspan="3" align="center">										
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
					</tr>
				
					
			</table>
			
		</logic:equal>
		<logic:equal name="searchSuccess" value="searchFail">
			<table id="noRoleListRow" class="" width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="formlabel" align="center"><bean:message key="search.fail" /></font></td>
				</tr>
			</table>				
		</logic:equal>
			
		<html:hidden property="entityCode"></html:hidden>		
		<html:hidden property="hiddenBlockCode"></html:hidden>
		<html:hidden property="hiddenVillageCode"></html:hidden>
		<html:hidden property="hiddenLoginId"></html:hidden>
		
		
</td>
</tr>
</table>
</html:form>
</body>
<script>
hideShowField();
if(document.getElementById('hiddenBlockCode').value!=""){
getBlockByDistrictCodeOnLoad();
}

</script>
</html:html>

