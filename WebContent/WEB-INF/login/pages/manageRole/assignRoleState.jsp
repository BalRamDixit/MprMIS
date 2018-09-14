<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>

</head>
<script language=javascript>
 

//window.onbeforeunload=confirmExit;
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


function save(){
	
	if(document.getElementById('levelCode').value==""){
	alert("Please Select Level of User");
	return;
	}	
	
	if((document.getElementById('districtRow').className !="hide") && (document.getElementById('districtCode').value=="")){
	alert("Please Select District");
	return;
	}
	if((document.getElementById('blockRow').className !="hide") && (document.getElementById('blockCode').value=="")){
	alert("Please Select Block");
	return;
	}
	 
	if(document.getElementById('loginId').value==""){
	alert("Please Select loginId");
	return;
	}	
	if(document.getElementById('levelCodeA').value==""){
	alert("Please Select Level of  Role to be assigned");
	return;
	}	
	if(document.getElementById('roleCode').value==""){
	alert("Please Select Role Name");
	return;
	}
	
	if((document.getElementById('districtRowRole').className !="hide") && (document.getElementById('districtCodeRole').value=="")){
	alert("Please Select District");
	return;
	}
	if((document.getElementById('blockRowRole').className !="hide") && (document.getElementById('blockCodeRole').value=="")){
	alert("Please Select block");
	return;
	}
	
	selectAll();	
	if((document.getElementById('districtRowMultiple').className !="hide") && (document.getElementById('selectedDistrictM').value=="")){
	alert("No Selected District, Please Select District");
	return;
	}
	
	if((document.getElementById('blockRowMultiple').className !="hide") && (document.getElementById('selectedBlockM').value=="")){
	alert("No Selected Block, Please Select Block");
	return;
	}
	
	var roleCode = document.getElementById('roleCode').value;
	
    var status=window.confirm("Are you sure you want to submit ");
	if(status==true){
	document.manageRoleForm.action="manageRoleAction.do?methodName=save&"+tokenParameter+"="+tokenValue;
	selectAll();
	document.manageRoleForm.submit();
	} 
}

function getRoleByLevelCode(){
var levelCode = document.getElementById('levelCode').value;


  if(levelCode != ""){  
  var url = "manageRoleAction.do?methodName=getRoleByCode&level="+levelCode; 
   
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseRole;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("levelCodeA");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponseRole(){

if (req.readyState == 4){	
    
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("RoleList");
	try{
	   	
	   		if(validate[0]!=null){
	   	 
		   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
		    		var selectBox = document.getElementById("levelCodeA");
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
			    alert("There seems to be no User available");
			    }
	    }
	    getUserByCode();
}
catch(err)
{
    alert("There seems to be an error fetching User");
}
}

}

function getRole(){
var levelCodeA = document.getElementById('levelCodeA').value;

  if(levelCodeA != ""){  
  var url = "manageRoleAction.do?methodName=getRole&levelA="+levelCodeA; 
  
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
		req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseRoless;
	req.send(null);
	}
	else{
   		var selectBox = document.getElementById("roleCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	}
	hideShowFieldRole(); 
}    

function handleHttpResponseRoless(){
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("RolesList");
   	if(validate[0]!=null){
   	try{ 
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
    		var selectBox = document.getElementById("roleCode");
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
    alert("There seems to be no role available for This level");
    }
}
catch(err)
{
    alert("There seems to be an error fetching role for This Level");
}
}
}

}

function getUserByCode(){

var level = document.getElementById('levelCode').value;


var district = document.getElementById('districtCode').value;
var block = document.getElementById('blockCode').value;
 
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
if (req.readyState == 4){	
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("UserList");
    if(validate[0]!=null){
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
    }
    else {
    alert("There seems to be no User available");
    }
}
catch(err)
{
    alert("There seems to be an error fetching User");
}
}
}
}


function getBlockByDistrictCode(){

var district = document.getElementById('districtCode').value;
var districtCodes = document.getElementById('districtCodeRole').value;
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
	if(districtCodes != ""){
  var url = "userAction.do?methodName=getBlockByDistrictCode&district="+districtCodes;
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
   		var selectBoxRole = document.getElementById("blockCodes");
   		var selectBoxRoleCode = document.getElementById("blockCodeRole");
   		var lenRoleCode = selectBoxRoleCode.length;
   		var lenRole = selectBoxRole.length;
   		var len = selectBox.length;
   		
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}
   		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   		}
   		for(var kRoleCode=lenRoleCode-1;kRoleCode>0;kRoleCode--){
     			selectBoxRoleCode.remove(kRoleCode);
   		}
   		var selectBoxRoleB = document.getElementById("selectedBlockM");
   			var lenRoleB = selectBoxRoleB.length;   
   			for(var kRoleB=lenRoleB-1;kRoleB>=0;kRoleB--){
     			selectBoxRoleB.remove(kRoleB);
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
    		var selectBoxRole = document.getElementById("blockCodes");
    		var selectBoxRoleCode = document.getElementById("blockCodeRole");
    		var len = selectBox.options.length;
    		var lenRole = selectBoxRole.length;
    		var lenRoleCode = selectBoxRoleCode.length;
    		
   			for(var k=len-1;k>0;k--) {
     		selectBox.remove(k);
    		}
    		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   			}
   			for(var kRoleCode=lenRoleCode-1;kRoleCode>0;kRoleCode--){
     			selectBoxRoleCode.remove(kRoleCode);
   			}
   			var selectBoxRoleB = document.getElementById("selectedBlockM");
   			var lenRoleB = selectBoxRoleB.length;   
   			for(var kRoleB=lenRoleB-1;kRoleB>=0;kRoleB--){
     			selectBoxRoleB.remove(kRoleB);
   			}	
   for(var i=1;i<validate[0].childNodes.length;i++){
           var oOption = document.createElement("OPTION");
			oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBox.options.add(oOption);
						
			var oOption1 = document.createElement("OPTION");
			oOption1.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption1.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBoxRole.options.add(oOption1);
			
			var oOption2 = document.createElement("OPTION");
			oOption2.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption2.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBoxRoleCode.options.add(oOption2);
			
   		}
   		
   		
    }
    else {
    alert("There seems to be no block available for This District");
    }
    }
    var districtUser = document.getElementById('districtCode').value;
   		if(districtUser != ""){
   			getUserByCode();
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



function hideShowField(){
document.getElementById('levelCodeA').value="";
document.getElementById('roleCode').value="";
document.getElementById('loginId').value="";

document.getElementById('districtCode').value="";
document.getElementById('blockCode').value="";
document.getElementById('villageCode').value="";


document.getElementById('districtCodeRole').value="";
document.getElementById('blockCodeRole').value="";

var lavel=document.getElementById('levelCode').value;

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
 if(lavel=='2' || lavel==""){

	document.getElementById('districtRow').className="hide";
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
	

	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="hide";
	

	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="hide";
	
 } 
getRoleByLevelCode();
}

function hideShowFieldRole(){

var lavel=document.getElementById('levelCode').value;
var lavelA=document.getElementById('levelCodeA').value;

if(lavelA==""){
 	
	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="hide";	
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="hide";
 } 

if(lavel=='2' && lavelA== '5'){
	
	document.getElementById('districtRowRole').className="";
	document.getElementById('blockRowRole').className="";
	
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="";

}
if(lavel=='3' && lavelA== '5'){	
	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="";
	
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="";
}

if(lavel=='4' && lavelA== '5'){	
	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="hide";	
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="";
 }
  
 if(lavel=='2' && lavelA== '4'){ 	
	document.getElementById('districtRowRole').className="";
	document.getElementById('blockRowRole').className="hide";	
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="";
	document.getElementById('villageRowMultiple').className="hide";
 } 
 if(lavel=='3' && lavelA== '4'){ 	
	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="hide";
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="";
	document.getElementById('villageRowMultiple').className="hide";
 } 
 
 if(lavel=='2' && lavelA== '3'){
 	
	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="hide";	
	
	document.getElementById('districtRowMultiple').className="";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="hide";
 } 
 

 if(lavelA=="" || lavelA=='2'){
 	
	document.getElementById('districtRowRole').className="hide";
	document.getElementById('blockRowRole').className="hide";	
	
	document.getElementById('districtRowMultiple').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="hide";
 } 
}

function closePage(){
	var status=window.confirm("Are you sure you want to Close ");
	if(status==true){
	document.manageRoleForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.manageRoleForm.submit();
	}
}


function addSelected(selectbox1,selectbox2)
{
	var i;
	for(i=selectbox1.options.length-1;i>=0;i--)
	{
		if(selectbox1.options[i].selected)
		{
			var optn = document.createElement("OPTION");
			optn.text =  selectbox1.options[i].text;
			optn.value = selectbox1.options[i].value;

			selectbox2.options.add(optn);
			selectbox1.remove(i);

		}
		//selectbox2.(i);
	}
	
}



function selectAll()
{
var lavelA=document.getElementById('levelCodeA').value;	
	if(lavelA =='3'){
	for(var i=0;i<document.forms[0].selectedDistrictM.length;i++)
	{
		document.forms[0].selectedDistrictM[i].selected=true;
	}
	}
	
	if(lavelA =='4'){
	for(var i=0;i<document.forms[0].selectedBlockM.length;i++)
	{
		document.forms[0].selectedBlockM[i].selected=true;
	}
	}
	
	if(lavelA =='5'){
	for(var i=0;i<document.forms[0].selectedVillageM.length;i++)
	{
		document.forms[0].selectedVillageM[i].selected=true;
	}
	}

}

function clearForm(){
 
	    if(confirm("Do you want to clear the form ? "))
		{
		    document.forms[0].action="manageRoleAction.do?methodName=assignRole"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();	
		}
	}
</script>
<body>
<font color="red" size="2">
<html:errors/></font>
<html:form action="login/manageRoleAction">
<table width="100%">	
	<tr>
		<td align="center" class="pageHeader">
		<bean:message key="lable.manageRole.assign"></bean:message> &nbsp;<bean:message key="lable.manageRole.role"></bean:message></td>	
	</tr>
	<tr>
		<td style="padding-left: 40px;"><span class="mandatory">*</span>
		<span class="normaltext"><bean:message key="lable.field.manadatory"></bean:message></span></td>
	</tr>
	<tr>
	<td align="center">
	<table width="90%" class="inputTBL"> 
	<tr>
		<th><bean:message key="lable.manageRole.levelofuser"></bean:message><span class="mandatory">*</span></th>
		<td>
			<html:select property="levelCode" styleId="levelCode" onchange="hideShowField()">
						<html:option value="">--Select Level--</html:option>
						<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
			</html:select></td>
	</tr>			
	<tr id="districtRow" class="hide">
		<th><bean:message key="lable.user.selectDistrict"/><span class="mandatory">*</span></th>
		<td>
		<html:select property="districtCode" styleId="districtCode" onchange="getBlockByDistrictCode()">
					<html:option value="">--Select District--</html:option>
					<html:options collection="districtList" property="districtCode" labelProperty="districtName"/>					
		</html:select></td>
	</tr>				
	<tr id="blockRow" class="hide">
					<th><bean:message key="lable.manageRole.selectBlockPanchayat"/><span class="mandatory">*</span></th>
					<td><html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
						<html:option value="">--Select Block Panchayat--</html:option>
					</html:select></td>
	</tr>
	<tr id="villageRow" class="hide">
					<th ><bean:message key="lable.manageRole.selectVillagePanchayat"/><span class="mandatory">*</span></th>
					<td><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
					<html:option value="">--Select Village Panchayat--</html:option>				
					</html:select></td>
	</tr>
	<tr>
		<th><bean:message key="lable.manageRole.loginID"/><span class="mandatory">*</span></th>
		<td><html:select property="loginId" styleId="loginId">
			<html:option value="">--Select Login Id--</html:option>						
			</html:select></td>
	</tr>
	<tr>
		<th><bean:message key="lable.manageRole.levelofRoletobeassigned"/><span class="mandatory">*</span></th>
		<td><html:select property="levelCodeA" styleId="levelCodeA" onchange="getRole()">
					<html:option value="">--Select Level of Role--</html:option>											
			</html:select></td>
	</tr>
	<tr>
		<th ><bean:message key="lable.manageRole.roleName"/><span class="mandatory">*</span></th>
					<td><html:select property="roleCode" styleId="roleCode">
					<html:option value="">--Select Role--</html:option>											
					</html:select></td>
	</tr>				
	<tr id="districtRowRole" class="hide">
					<td ><bean:message key="lable.manageRole.selectDistrict"/><span class="mandatory">*</span></td>
					<td><html:select property="districtCodeRole" styleId="districtCodeRole" onchange="getBlockByDistrictCode()">
						<html:option value="">--Select District--</html:option>
						<html:options collection="districtList" property="districtCode" labelProperty="districtName"/>
					</html:select></td>
	</tr>	
	<tr id="blockRowRole" class="hide">
					<td ><bean:message key="lable.manageRole.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td><html:select property="blockCodeRole" styleId="blockCodeRole" onchange="getVillageByBlockCode()">
						<html:option value="">--Select Block Panchayat--</html:option>
					</html:select></td>
	</tr>
	<tr id="districtRowMultiple" class="hide">
					<td colspan="2">
					<div align="center">
		<table border="0" width="49%">											
		<tr>							
			<td>SELECT DISTRICT<span class="mandatory">*</span>
			<html:select property="districtCodes" styleId="districtCodes" multiple="true" style="width:195px">
			<html:options collection="districtList" property="districtCode" labelProperty="districtName"/>
			</html:select></td>
		<td>						
							<%String district = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].districtCodes,document.forms[0].selectedDistrictM)"></html:button><br />
							<html:button property="next" styleClass="button"
							value="<%=district %>" onclick="addSelected(document.forms[0].selectedDistrictM,document.forms[0].districtCodes)"></html:button>
		</td>
		<td>SELECTED DISTRICT
							<html:select property="selectedDistrictM" styleId="selectedDistrictM" multiple="true" style="width:195px" >													
							</html:select>
		</td>
			</tr>
			</table>
			</div>
			</td>
		</tr>			
		<tr id="blockRowMultiple" class="hide">
			<td colspan="2">
			<div align="center">
			<table border="0" width="49%">					
				<tr>							
				<td>SELECT BLOCK<span class="mandatory">*</span>
				<html:select property="blockCodes" styleId="blockCodes" multiple="true" style="width:195px">							
				</html:select></td>
				<td>						
				<%String block = "<<";%> 
				<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].blockCodes,document.forms[0].selectedBlockM)"></html:button><br />
				<html:button property="next" styleClass="button"
				value="<%=block %>" onclick="addSelected(document.forms[0].selectedBlockM,document.forms[0].blockCodes)"></html:button>
				</td>
				<td>SELECTED BLOCK
				<html:select property="selectedBlockM" styleId="selectedBlockM" multiple="true" style="width:195px" >							
				</html:select>
				</td>
			</tr>
			</table>
			</div>
			</td>
		</tr>
				
		<tr id="villageRowMultiple" class="hide">
			<td colspan="2">
			<div align="center">
		<table border="0" width="49%">						
		<tr>							
			<td><bean:message key="lable.user.selectVillages"/><span class="mandatory">*</span>
			<html:select property="villageCodes" styleId="villageCodes" multiple="true" style="width:195px">							
			</html:select></td>
			<td>						
			<%String village = "<<";%> 
			<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].villageCodes,document.forms[0].selectedVillageM)"></html:button><br/>
			<html:button property="next" styleClass="button"
			value="<%=village %>" onclick="addSelected(document.forms[0].selectedVillageM,document.forms[0].villageCodes)"></html:button>
			</td>

			<td><bean:message key="lable.user.selectedVillages"/>
			<html:select property="selectedVillageM" styleId="selectedVillageM" multiple="true" style="width:195px" >							
			</html:select>
			</td>
		</tr>
		</table>
		</div>
		</td>
	</tr>			
		<html:hidden property="entityCode" styleId="entityCode"></html:hidden>		
	</table>
	</td>
</tr>
<tr>
	<td align="center">
					<html:button styleClass="button" property="next" value="Assign Role" onclick="save()"></html:button>					
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
				    <html:button styleClass="button" property="" onclick="clearForm()">
		<bean:message  key="button.clear" /></html:button>		
	</td>
</tr>
</table>
</html:form>
</body>
</html:html>

