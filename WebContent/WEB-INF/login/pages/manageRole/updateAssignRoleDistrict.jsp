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


function update(){	
	
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

if(lavel=='4'){	
	
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="hide";	}

if(lavel=='5'){	
	document.getElementById('blockRow').className="";
	document.getElementById('villageRow').className="";
 	}
 if(lavel=='3' || lavel==""){ 	
	
	document.getElementById('blockRow').className="hide";
	document.getElementById('villageRow').className="hide";	
 	}
 if(lavel=='3' && lavelA=="5"){
	document.getElementById('blockRowRole').className="";
	document.getElementById('villageRowMultiple').className="";	
 	}
 if(lavel=='3' && lavelA=="4"){
	document.getElementById('blockRowMultiple').className="";	
 	}
 if(lavel=='4' && lavelA=="5"){
	document.getElementById('villageRowMultiple').className="";			
	getVillageByBlockCode();
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
	req.setRequestHeader(tokenParameter, tokenValue);
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="actionstxt"><span class="actionstxt"><bean:message key="label.manageRole.update"></bean:message>
					<bean:message key="label.manageRole.assign"></bean:message></span><bean:message key="label.manageRole.role"></bean:message></td>					
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
					<html:text property="levelCodeName" styleId="levelCodeName" readonly="true"></html:text>					
						
					</td>
				</tr>				 
				
		
				<tr id="blockRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.selectedBlockPanchayat"/></td>
					<td class="formlabel"><html:text property="blockName" styleId="blockName" readonly="true"></html:text>
						
					</td>
				</tr>
				
				<tr id="villageRow" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.selectedVillagePanchayat"/></td>
					<td class="formlabel"><html:text property="villageName" styleId="villageName" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.loginID"/></td>
					<td class="formlabel"><html:text property="loginId" styleId="loginId" readonly="true"></html:text>
										
					</td>
				</tr>
				
				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.levelofRole"/></td>
					<td class="formlabel"><html:text property="levelCodeAName" styleId="levelCodeAName" readonly="true"></html:text>
					</td>
				</tr>

				<tr>
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.roleName"/></td>
					<td class="formlabel"><html:text property="roleName" styleId="roleName" size="31" readonly="true"></html:text>															
					</td>
				</tr>
				
		
				<tr id="blockRowRole" class="hide">
					<td width="50%" class="formlabel"><bean:message key="lable.manageRole.selectBlockPanchayat"/><span class="mandatory">*</span></td>
					<td class="formlabel"><html:select property="blockCodeRole" styleId="blockCodeRole" onchange="getVillageByBlockCode()">
						<html:option value="">--Select Block Panchayat--</html:option>
						<html:options collection="blocksList" property="blockCode" labelProperty="blockName"/>
					</html:select></td>
				</tr>
				
				<tr id="blockRowMultiple" class="hide">
					<td colspan="2">
					<div align="center">
					<table border="0" width="49%">					
						
						<tr>							
							<td class="formlabel"><bean:message key="lable.user.selectBlocks"/>
							<html:select property="blockCodes" styleId="blockCodes" multiple="true" style="width:195px">							
							<html:options collection="blockssList" property="blockCode" labelProperty="blockName"/>
							</html:select></td>
							<td>						
							<%String block = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].blockCodes,document.forms[0].selectedBlockCode)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=block %>" onclick="addSelected(document.forms[0].selectedBlockCode,document.forms[0].blockCodes)"></html:button>
							</td>

							<td class="formlabel"><bean:message key="lable.user.selectedBlocks"/>
							<html:select property="selectedBlockCode" styleId="selectedBlockCode" multiple="true" style="width:195px" >							
							<html:options collection="blockselectedList" property="blockCode" labelProperty="blockName"/>
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
							<td class="formlabel"><bean:message key="lable.user.selectVillages"/>
							<html:select property="villageCodes" styleId="villageCodes" multiple="true" style="width:195px">							
							</html:select></td>
							<td>						
							<%String village = "<<";%> 
							<html:button property="next" value=">>"	styleClass="button" onclick="addSelected(document.forms[0].villageCodes,document.forms[0].selectedVillageCode)"></html:button><br>
							<html:button property="next" styleClass="button"
							value="<%=village %>" onclick="addSelected(document.forms[0].selectedVillageCode,document.forms[0].villageCodes)"></html:button>
							</td>

							<td class="formlabel"><bean:message key="lable.user.selectedVillages"/>
							<html:select property="selectedVillageCode" styleId="selectedVillageCode" multiple="true" style="width:195px" >							
							</html:select>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>							
						
						
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

