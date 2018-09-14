<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>

</head>
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


function save(){
	
	if(document.getElementById('levelCode').value==""){
	alert("Please Select Level of User");
	return;
	}
	
	if((document.getElementById('blockRow').className !="hide") && (document.getElementById('blockCode').value=="")){
	alert("Please Select Block");
	return;
	}
	if((document.getElementById('villageRow').className !="hide") && (document.getElementById('villageCode').value=="")){
	alert("Please Select Village");
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
	
	if((document.getElementById('blockRowRole').className !="hide") && (document.getElementById('blockCodeRole').value=="")){
	alert("Please Select block");
	return;
	}
	
	selectAll();
	if((document.getElementById('blockRowMultiple').className !="hide") && (document.getElementById('selectedBlockM').value=="")){
	alert("No Selected Block, Please Select Block");
	return;
	}
	
	if((document.getElementById('villageRowMultiple').className !="hide") && (document.getElementById('selectedVillageM').value=="")){
	alert("No Selected Village, Please Select Village");
	return;
	}		
		
	var roleCode = document.getElementById('roleCode').value;
	
    var status=window.confirm("Are you sure you want to submit ");
	if(status==true){
	document.manageRoleForm.action="manageRoleAction.do?methodName=save&"+tokenParameter+"="+tokenValue;	
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
   		getUserByCode();
   		
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

function getVillageByBlockCode(){
var block = document.getElementById('blockCode').value;
var blockCodeRole = document.getElementById('blockCodeRole').value;
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
	
	if(blockCodeRole != ""){
  	var url = "userAction.do?methodName=getVillageByBlockCode&block="+blockCodeRole;
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
   		var selectBoxRole = document.getElementById("villageCodes");
   		var lenRole = selectBoxRole.length;
   		var len = selectBox.length;
   		
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}
   		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   		}
   		var selectBoxRoleV = document.getElementById("selectedVillageM");
   		var lenRoleV = selectBoxRoleV.length;   
   		for(var kRoleV=lenRoleV-1;kRoleV>=0;kRoleV--){
     			selectBoxRoleV.remove(kRoleV);
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
    		var selectBoxRole = document.getElementById("villageCodes");
    		
    		var len = selectBox.options.length;
    		var lenRole = selectBoxRole.length;
   			for(var k=len-1;k>0;k--) {
     		selectBox.remove(k);
    		}
    		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   			}
   			var selectBoxRoleV = document.getElementById("selectedVillageM");
   			var lenRoleV = selectBoxRoleV.length;   
   			for(var kRoleV=lenRoleV-1;kRoleV>=0;kRoleV--){
     			selectBoxRoleV.remove(kRoleV);
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



function hideShowField(){

document.getElementById('roleCode').value="";
document.getElementById('loginId').value="";

document.getElementById('blockCode').value="";
document.getElementById('villageCode').value="";

document.getElementById('blockCodeRole').value="";

var lavel=document.getElementById('levelCode').value;

if(lavel=='4'){
	
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="hide";
}

if(lavel=='5'){

	
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="";
 }
 if(lavel=='3' || lavel==""){
	
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";
	
	document.getElementById('blockRowRole').className="hide";
	
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="hide";
	
 } 
getRoleByLevelCode();
}


function hideShowFieldRole(){

var lavel=document.getElementById('levelCode').value;
var lavelA=document.getElementById('levelCodeA').value;

if(lavelA==""){	
	
	document.getElementById('blockRowRole').className="hide";	
	
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="hide";
 }


if(lavel=='3' && lavelA== '5'){		
	document.getElementById('blockRowRole').className="";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="";
}

if(lavel=='4' && lavelA== '5'){		
	document.getElementById('blockRowRole').className="hide";
	document.getElementById('blockRowMultiple').className="hide";
	document.getElementById('villageRowMultiple').className="";
 }  
  
 if(lavel=='3' && lavelA== '4'){ 	
	document.getElementById('blockRowRole').className="hide";
	document.getElementById('blockRowMultiple').className="";
	document.getElementById('villageRowMultiple').className="hide";
 }
 
 if(lavelA=="" || lavelA=='3'){
	document.getElementById('blockRowRole').className="hide";
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
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="actionstxt"><span class="actionstxt">
					<bean:message key="lable.manageRole.assign"></bean:message></span><bean:message key="lable.manageRole.role"></bean:message></td>					
				</tr>
				<tr>
					<td height="20" class="formlabel"><span class="mandatory">*</span>
					<span class="normaltext"><bean:message key="lable.field.manadatory"></bean:message></span></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.levelofuser"></bean:message><span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="levelCode" styleId="levelCode" onchange="hideShowField()">
						<html:option value="">--Select Level--</html:option>
						<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
					</html:select></td>
				</tr>				
							
		
				<tr id="blockRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
						<html:option value="">--Select Block Panchayat--</html:option>
						<html:options collection="blockList" property="blockCode" labelProperty="blockName"/>
					</html:select></td>
				</tr>
				
				<tr id="villageRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.selectVillagePanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="villageCode" styleId="villageCode" onchange="getUserByCode()">
					<html:option value="">--Select Village Panchayat--</html:option>
					
					</html:select></td>
				</tr>
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.loginID"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="loginId" styleId="loginId">
					<html:option value="">--Select Login Id--</html:option>						
					</html:select></td>
				</tr>
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.levelofRoletobeassigned"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="levelCodeA" styleId="levelCodeA" onchange="getRole()">
					<html:option value="">--Select Level of Role--</html:option>											
					</html:select></td>
				</tr>

				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.roleName"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="roleCode" styleId="roleCode">
					<html:option value="">--Select Role--</html:option>											
					</html:select></td>
				</tr>
				
		
				<tr id="blockRowRole" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="blockCodeRole" styleId="blockCodeRole" onchange="getVillageByBlockCode()">
						<html:option value="">--Select Block Panchayat--</html:option>
						<html:options collection="blockList" property="blockCode" labelProperty="blockName"/>
					</html:select></td>
				</tr>				
				
				<tr id="blockRowMultiple" class="hide">
					<td colspan="2">
					<div align="center">
					<table border="0" width="49%">					
						
						<tr>							
							<td class="formlabel"><bean:message key="lable.user.selectBlocks"/><span class="mandatory">*</span>
							<html:select property="blockCodes" styleId="blockCodes" multiple="true" style="width:195px">							
							<html:options collection="blockList" property="blockCode" labelProperty="blockName"/>
							</html:select></td>
							<td>						
							<%String block = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].blockCodes,document.forms[0].selectedBlockM)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=block %>" onclick="addSelected(document.forms[0].selectedBlockM,document.forms[0].blockCodes)"></html:button>
							</td>

							<td class="formlabel"><bean:message key="lable.user.selectedBlocks"/>
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
							<td class="formlabel"><bean:message key="lable.user.selectVillages"/><span class="mandatory">*</span>
							<html:select property="villageCodes" styleId="villageCodes" multiple="true" style="width:195px">							
							</html:select></td>
							<td>						
							<%String village = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].villageCodes,document.forms[0].selectedVillageM)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=village %>" onclick="addSelected(document.forms[0].selectedVillageM,document.forms[0].villageCodes)"></html:button>
							</td>

							<td class="formlabel"><bean:message key="lable.user.selectedVillages"/>
							<html:select property="selectedVillageM" styleId="selectedVillageM" multiple="true" style="width:195px" >							
							</html:select>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>		
				
				
				<html:hidden property="entityCode" styleId="entityCode"></html:hidden>					
				<tr>
					<td height="40" colspan="2" align="center">
					<html:button styleClass="button" property="next" value="Assign Role" onclick="save()"></html:button>					
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					  <html:button styleClass="button" property="" onclick="clearForm()">
						 <bean:message  key="button.clear" /></html:button>
						 
					
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

