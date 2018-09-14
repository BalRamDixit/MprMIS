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


function update(){
	
	if((document.getElementById('districtRowRole').className !="hide") && (document.getElementById('districtCodeRole').value=="")){
	alert("Please Select District");
	return;
	}
	if((document.getElementById('blockRowRole').className !="hide") && (document.getElementById('blockCodeRole').value=="")){
	alert("Please Select block");
	return;
	}
	
    var status=window.confirm("Are you sure you want to update");
	if(status==true){
	document.manageRoleForm.action="manageRoleAction.do?methodName=modify&"+tokenParameter+"="+tokenValue;
	selectAll();
	document.manageRoleForm.submit();
	} 
}

function closePage(){
	var status=window.confirm("Are you sure you want to Close ");
	if(status==true){
	document.manageRoleForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.manageRoleForm.submit();
	}
}

function hideShowField(){

var lavel=document.getElementById('levelCode').value;
var lavelA=document.getElementById('levelCodeA').value;
	
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
 	}
 if(lavel=='2' && lavelA=="5"){ 	
	document.getElementById('districtRowRole').className="";
	document.getElementById('blockRowRole').className="";
	document.getElementById('villageRowMultiple').className="";	
 	}
 if(lavel=='2' && lavelA=="4"){ 	
	document.getElementById('districtRowRole').className="";	
	document.getElementById('blockRowMultiple').className="";	
 	} 	
 if(lavel=='2' && lavelA=="3"){ 		
	document.getElementById('districtRowMultiple').className="";		
 	}
 
 if(lavel=='3' && lavelA=="5"){ 	
	document.getElementById('blockRowRole').className="";
	document.getElementById('villageRowMultiple').className="";	
	getBlockByDistrictCode();		
		
 	}
 if(lavel=='3' && lavelA=="4"){
	document.getElementById('blockRowMultiple').className="";
	getBlocksByDistrictCode();				
		
 	}
 if(lavel=='4' && lavelA=="5"){
	document.getElementById('villageRowMultiple').className="";			
	getVillageByBlockCode();
 	}		
}

function getBlockByDistrictCode(){
var districtCode = document.getElementById('districtCode').value;
var districtCodes = document.getElementById('districtCodeRole').value;  
	if(districtCode != ""){
  	var url = "userAction.do?methodName=getBlockByDistrictCode&district="+districtCode;
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
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
   		var selectBoxRoleCode = document.getElementById("blockCodeRole");
   		var lenRoleCode = selectBoxRoleCode.length;   	
   		
   		for(var kRoleCode=lenRoleCode-1;kRoleCode>0;kRoleCode--){
     			selectBoxRoleCode.remove(kRoleCode);
   		}	   		   		
	} 
}    

function handleHttpResponseBlock(){

if (req.readyState == 4){	
    
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("BlockList");
    if(validate[0]!=null){
    
	   
	   	try{ 
	   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {    		
	    		var selectBoxRoleCode = document.getElementById("blockCodeRole");    	
	    		var lenRoleCode = selectBoxRoleCode.length;    		
	
	   			for(var kRoleCode=lenRoleCode-1;kRoleCode>0;kRoleCode--){
	     			selectBoxRoleCode.remove(kRoleCode);
	   			}
	   for(var i=1;i<validate[0].childNodes.length;i++){           
				
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
	catch(err)
	{
	    alert("There seems to be an error fetching block for This District");
	}
  }
 }
}


function getBlocksByDistrictCode(){

var loginId = document.getElementById('loginId').value;
var roleCode = document.getElementById('roleCode').value;
var districtCode = document.getElementById('districtCode').value;
var districtCodeRole = document.getElementById('districtCodeRole').value; 
 
	if(districtCode != ""){
	  	var url = "manageRoleAction.do?methodName=getBlocksByDistrictCode&district="+districtCode+"&loginId="+loginId+"&roleCode="+roleCode;   	 
	    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
		} 
		else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("GET", url, true);
		req.setRequestHeader(tokenParameter, tokenValue);
		req.onreadystatechange = handleHttpResponseBlocks;
		req.send(null);
	}
	if(districtCodeRole != ""){
	  	var url = "manageRoleAction.do?methodName=getBlocksByDistrictCode&district="+districtCodeRole+"&loginId="+loginId+"&roleCode="+roleCode;   	 
	    if (window.XMLHttpRequest) {
			req=new XMLHttpRequest();
		} 
		else if (window.ActiveXObject) {
			req=new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("GET", url, true);
		req.onreadystatechange = handleHttpResponseBlocks;
		req.send(null);
	}
	else{
	
   		var selectBoxRoleCode = document.getElementById("blockCodes");
   		var lenRoleCode = selectBoxRoleCode.length;   	
   		for(var kRoleCode=lenRoleCode-1;kRoleCode>=0;kRoleCode--){
     			selectBoxRoleCode.remove(kRoleCode);
   		}
   		
   		var selectBoxRoleB = document.getElementById("selectedBlockCode");
   			var lenRoleB = selectBoxRoleB.length;   
   			for(var kRoleB=lenRoleB-1;kRoleB>=0;kRoleB--){
     			selectBoxRoleB.remove(kRoleB);
   			}	   		   		
	} 
}    

function handleHttpResponseBlocks(){

if (req.readyState == 4){	
    
    var xmlDoc = req.responseXML; 
    
    var validate = xmlDoc.getElementsByTagName("BlockList");
    
    if(validate[0]!=null){
    	   	try{ 
		   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {    		
		    		var selectBoxRoleCode = document.getElementById("blockCodes");    	
		    		var lenRoleCode = selectBoxRoleCode.length;    		
		   			for(var kRoleCode=lenRoleCode-1;kRoleCode>=0;kRoleCode--){
		     			selectBoxRoleCode.remove(kRoleCode);
		   			}
		   for(var i=1;i<validate[0].childNodes.length;i++){           
					
					var oOption2 = document.createElement("OPTION");
					oOption2.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
					oOption2.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
					selectBoxRoleCode.options.add(oOption2);
		   		}   		
		    }
		    else {
		   			 alert("There seems to be no block available for This District");
		    }
		    getSlelectedBlocksByDistrictCode();
		}
		catch(err)
		{
		    alert("There seems to be an error fetching block for This District");
		}
   }
 }
}

function getSlelectedBlocksByDistrictCode(){
var loginId = document.getElementById('loginId').value;
var roleCode = document.getElementById('roleCode').value;

var districtCode = document.getElementById('districtCode').value;
var districtCodeRole = document.getElementById('districtCodeRole').value; 
 	
 	if(districtCode != ""){
  	var url = "manageRoleAction.do?methodName=getBlockChosenByDistrictCode&district="+districtCode+"&loginId="+loginId+"&roleCode="+roleCode;   	 
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseBlockSelected;
	req.send(null);
	}
	if(districtCodeRole != ""){
  	var url = "manageRoleAction.do?methodName=getBlockChosenByDistrictCode&district="+districtCodeRole+"&loginId="+loginId+"&roleCode="+roleCode;   	 
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseBlockSelected;
	req.send(null);
	}
	else{
   		var selectBoxRoleCode = document.getElementById("selectedBlockCode");
   		var lenRoleCode = selectBoxRoleCode.length;   	
   		
   		for(var kRoleCode=lenRoleCode-1;kRoleCode>=0;kRoleCode--){
     			selectBoxRoleCode.remove(kRoleCode);
   		}	   		   		
	} 
}    

function handleHttpResponseBlockSelected(){

if (req.readyState == 4){	
    
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("BlockList");
    
    if(validate[0]!=null){
    
		    
		   	try{ 
		   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {    		
		    		var selectBoxRoleCode = document.getElementById("selectedBlockCode");    	
		    		var lenRoleCode = selectBoxRoleCode.length;    		
		
		   			for(var kRoleCode=lenRoleCode-1;kRoleCode>=0;kRoleCode--){
		     			selectBoxRoleCode.remove(kRoleCode);
		   			}
		   for(var i=1;i<validate[0].childNodes.length;i++){           
					
					var oOption2 = document.createElement("OPTION");
					oOption2.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
					oOption2.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
					selectBoxRoleCode.options.add(oOption2);
					
		   		}   		
		   		
		    }
		    else {
		    		var selectBoxRoleB = document.getElementById("selectedBlockCode");
		   			var lenRoleB = selectBoxRoleB.length;   
		   			for(var kRoleB=lenRoleB-1;kRoleB>=0;kRoleB--){
		     			selectBoxRoleB.remove(kRoleB);
		   			}	
		    alert("There seems to be no selected block available for This District");
		    }
		}
		catch(err)
		{
		    alert("There seems to be an error fetching block for This District");
		}
   }
  }
}

function getVillageByBlockCode(){
var loginId = document.getElementById('loginId').value;
var roleCode = document.getElementById('roleCode').value;
var blockCodeRole = document.getElementById('blockCodeRole').value;
var blockCode = document.getElementById('blockCode').value;
  	
  	if(blockCode != ""){
  	var url = "manageRoleAction.do?methodName=getVillageByBlockCode&block="+blockCode+"&loginId="+loginId+"&roleCode="+roleCode;  	     
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
  	var url = "manageRoleAction.do?methodName=getVillageByBlockCode&block="+blockCodeRole+"&loginId="+loginId+"&roleCode="+roleCode;  	     
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
   		var selectBoxRole = document.getElementById("villageCodes");
   		var lenRole = selectBoxRole.length;   
   		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   		}
   		
   		var selectBoxRoleV = document.getElementById("selectedVillageCode");
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
   	
	   	if(validate[0]!=null){
	   	
	   	try{ 
	   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {    	
	    		var selectBoxRole = document.getElementById("villageCodes");
	    		var lenRole = selectBoxRole.length;   		
	    		for(var kRole=lenRole-1;kRole>=0;kRole--){
	     			selectBoxRole.remove(kRole);
	   			}
	   for(var i=1;i<validate[0].childNodes.length;i++){         
				
				var oOption1 = document.createElement("OPTION");
				oOption1.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
				oOption1.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
				selectBoxRole.options.add(oOption1);
				
	   		}   		
	    }
	    else {
	    alert("There seems to be no Village available for This Block");
	    }
	    getSelectedVillageByBlockCode();
	}
	catch(err)
	{
	    alert("There seems to be an error fetching Village for This Block");
	}
	}
}
}

function getSelectedVillageByBlockCode(){
var loginId = document.getElementById('loginId').value;
var roleCode = document.getElementById('roleCode').value;
var blockCodeRole = document.getElementById('blockCodeRole').value;
var blockCode = document.getElementById('blockCode').value;
  	
  	if(blockCode != ""){
  	var url = "manageRoleAction.do?methodName=getSelectedVillageByBlockCode&block="+blockCode+"&loginId="+loginId+"&roleCode="+roleCode;  	     
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.onreadystatechange = handleHttpResponseVillageSelected;
	req.send(null);
	}
	if(blockCodeRole != ""){
  	var url = "manageRoleAction.do?methodName=getSelectedVillageByBlockCode&block="+blockCodeRole+"&loginId="+loginId+"&roleCode="+roleCode;  	     
    if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) {
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponseVillageSelected;
	req.send(null);
	}
	else{   		
   		var selectBoxRole = document.getElementById("selectedVillageCode");
   		var lenRole = selectBoxRole.length;   
   		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   		}	   		   		
	} 
}    

function handleHttpResponseVillageSelected(){

if (req.readyState == 4){	
    
    var xmlDoc = req.responseXML; 
    var validate = xmlDoc.getElementsByTagName("VillageList");
    
    if(validate[0]!=null){
    
   	try{ 
   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {    	
    		var selectBoxRole = document.getElementById("selectedVillageCode");
    		var lenRole = selectBoxRole.length;   		
    		for(var kRole=lenRole-1;kRole>=0;kRole--){
     			selectBoxRole.remove(kRole);
   			}
   for(var i=1;i<validate[0].childNodes.length;i++){         
			
			var oOption1 = document.createElement("OPTION");
			oOption1.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
			oOption1.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
			selectBoxRole.options.add(oOption1);
			
   		}   		
    }
    else {
    		var selectBoxRoleV = document.getElementById("selectedVillageCode");
   			var lenRoleV = selectBoxRoleV.length;   
   			for(var kRoleV=lenRoleV-1;kRoleV>=0;kRoleV--){
     			selectBoxRoleV.remove(kRoleV);
   			}
    alert("There seems to be no selected village available for This Block");    
    }
}
catch(err)
{
    alert("There seems to be an error fetching Village for This Block");
}
}
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
	for(var i=0;i<document.forms[0].selectedDistrictCode.length;i++)
	{
		document.forms[0].selectedDistrictCode[i].selected=true;
	}
	}
	if(lavelA =='4'){
	for(var i=0;i<document.forms[0].selectedBlockCode.length;i++)
	{
		document.forms[0].selectedBlockCode[i].selected=true;
	}
	}
	
	if(lavelA =='5'){
	for(var i=0;i<document.forms[0].selectedVillageCode.length;i++)
	{
		document.forms[0].selectedVillageCode[i].selected=true;
	}
	}

}
</script>
<body>
<html:form action="login/manageRoleAction">

	<table width="100%">
		<tr>
			<td>
			<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" ><span class="actionstxt"><bean:message key="lable.manageRole.update"></bean:message>
					<bean:message key="lable.manageRole.assign"></bean:message></span><bean:message key="lable.manageRole.role"></bean:message></td>
				</tr>
				<tr>
					<td height="20"><span class="mandatory">*</span>
					<span class="normaltext"><bean:message key="lable.field.manadatory"></bean:message></span></td>
				</tr>
			</table>

			<table width="90%" align="center" class="inputTBL">
				<tr>
					<td width="50%"><bean:message key="lable.manageRole.levelofuser"></bean:message><span class="mandatory">*</span></td>
					<td>
					<html:text property="levelCodeName" styleId="levelCodeName" readonly="true"></html:text>					
						
					</td>
				</tr>
				 
				<tr id="districtRow" class="hide">
					<td width="50%"><bean:message key="lable.manageRole.selectedDistrict"/></td>
					<td><html:text property="districtName" styleId="districtName" readonly="true"></html:text>						
					</td>
				</tr>
		
				<tr id="blockRow" class="hide">
					<td width="50%"><bean:message key="lable.manageRole.selectedBlockPanchayat"/></td>
					<td><html:text property="blockName" styleId="blockName" readonly="true"></html:text>
						
					</td>
				</tr>
				
				<tr id="villageRow" class="hide">
					<td width="50%"><bean:message key="lable.manageRole.selectedVillagePanchayat"/></td>
					<td><html:text property="villageName" styleId="villageName" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr>
					<td width="50%"><bean:message key="lable.manageRole.loginID"/></td>
					<td><html:text property="loginId" styleId="loginId" readonly="true"></html:text>
										
					</td>
				</tr>
				
				<tr>
					<td width="50%"><bean:message key="lable.manageRole.levelofRole"/></td>
					<td><html:text property="levelCodeAName" styleId="levelCodeAName" readonly="true"></html:text>
					</td>
				</tr>

				<tr>
					<td width="50%"><bean:message key="lable.manageRole.roleName"/></td>
					<td><html:text property="roleName" styleId="roleName" size="31" readonly="true"></html:text>															
					</td>
				</tr>
				 
				 
				<tr id="districtRowRole" class="hide">					
					<td width="50%"><bean:message key="lable.manageRole.selectDistrict"/><span class="mandatory">*</span></td>
					<logic:notEqual name="manageRoleForm" property="levelCodeA" value="4">
					<td><html:select property="districtCodeRole" styleId="districtCodeRole" onchange="getBlockByDistrictCode()">					
					<html:option value="">--Select District--</html:option>
					<html:options collection="districtsList" property="districtCode" labelProperty="districtName" />
					</html:select></td></logic:notEqual>					
					
					<logic:equal name="manageRoleForm" property="levelCodeA" value="4">
					<td><html:select property="districtCodeRole" styleId="districtCodeRole" onchange="getBlocksByDistrictCode()">					
						<html:option value="">--Select District--</html:option>
						<html:options collection="districtsList" property="districtCode" labelProperty="districtName" />
					</html:select></td></logic:equal>
				</tr>
				
		
				<tr id="blockRowRole" class="hide">
					<td width="50%"><bean:message key="lable.manageRole.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td><html:select property="blockCodeRole" styleId="blockCodeRole" onchange="getVillageByBlockCode()">
						<html:option value="">--Select Block Panchayat--</html:option>
					</html:select></td>
				</tr>				
				
				
				<tr id="districtRowMultiple" class="hide">
					<td colspan="2">
					<div align="center">
					<table border="0" width="49%">					
						
						<tr>							
							<td>SELECT DISTRICT
							<html:select property="districtCodes" styleId="districtCodes" multiple="true" style="width:195px">							
							<html:options collection="districtssList" property="districtCode" labelProperty="districtName"/>
							</html:select></td>
							<td>						
							<%String district = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].districtCodes,document.forms[0].selectedDistrictCode)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=district %>" onclick="addSelected(document.forms[0].selectedDistrictCode,document.forms[0].districtCodes)"></html:button>
							</td>

							<td>SELECTED DISTRICT
							<html:select property="selectedDistrictCode" styleId="selectedDistrictCode" multiple="true" style="width:195px" >							
							<html:options collection="districtselectedList" property="districtCode" labelProperty="districtName"/>
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
							<td>SELECT BLOCK
							<html:select property="blockCodes" styleId="blockCodes" multiple="true" style="width:195px">							
							</html:select></td>
							<td>						
							<%String block = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].blockCodes,document.forms[0].selectedBlockCode)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=block %>" onclick="addSelected(document.forms[0].selectedBlockCode,document.forms[0].blockCodes)"></html:button>
							</td>

							<td>SELECTED BLOCK
							<html:select property="selectedBlockCode" styleId="selectedBlockCode" multiple="true" style="width:195px" >							
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
							<td><bean:message key="lable.user.selectVillages"/>
							<html:select property="villageCodes" styleId="villageCodes" multiple="true" style="width:195px">							
							</html:select></td>
							<td>						
							<%String village = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].villageCodes,document.forms[0].selectedVillageCode)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=village %>" onclick="addSelected(document.forms[0].selectedVillageCode,document.forms[0].villageCodes)"></html:button>
							</td>

							<td><bean:message key="lable.user.selectedVillages"/>
							<html:select property="selectedVillageCode" styleId="selectedVillageCode" multiple="true" style="width:195px" >							
							</html:select>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>							
						
						<html:hidden property="districtCode"></html:hidden>
						<html:hidden property="blockCode"></html:hidden>
						<html:hidden property="villageCode"></html:hidden>
						<html:hidden property="levelCode"></html:hidden>
						<html:hidden property="levelCodeA"></html:hidden>
						<html:hidden property="roleCode"></html:hidden>
					
				<tr>
					<td height="40" colspan="2" align="center">
					<html:button styleClass="button" property="next" value="Assign Role" onclick="update()"></html:button>					
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					<input type="button" class="button" value="Cancel" onClick="history.go(-1)"/></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
<script>
hideShowField();
</script>
</html:html>

