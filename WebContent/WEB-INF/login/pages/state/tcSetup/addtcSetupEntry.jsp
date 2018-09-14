<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<script type="text/javascript">

function showRemark(str){
	//document.getElementById("newRec").style.display = "";	
	getDistrictDetails(str);
}
 

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var projectIDId=document.getElementById("projectIDId").value;
	if(projectIDId=="0"){
		alert("Please select a project..!");
		return false;
	}
	var stateNameId=document.getElementById("stateNameId").value;
	if(stateNameId==""||stateNameId==null||stateNameId.length<0){
		alert("State name cannot be null");
		return false;
	}
	var districtId=document.getElementById("districtId").value;
	if(districtId=="0"){
		alert("please select a district..!");
		return false;
	}
	var addressId=document.getElementById("addressId").value;
	if(addressId==""||addressId==null){
		alert("Please Enter address");
		return false;
	}
	var pinCodeId=document.getElementById("pinCodeId").value;
   if(pinCodeId==null||pinCodeId==""){
	   alert("Please enter PIN CODE");
	   return false;
	}
	if(pinCodeId.length<6){
		alert("Pin Code should be 6 digit");
		return false;
	}
	var assemblyConsId=document.getElementById("assemblyConsId").value;
	if(assemblyConsId=="0"){
		alert("Please Select assembly constituency");
		return false;
	}
	var resiStatusId=document.getElementById("resiStatusId").value;
	if(resiStatusId=="0"){
		alert("Please select residential");
		return false;
	}
	var inChargeNameId=document.getElementById("inChargeNameId").value;
	if(inChargeNameId==null||inChargeNameId==""){
		alert("Please enter incharge name");
		return false;
	}
	var inChargeNoId=document.getElementById("inChargeNoId").value;
	if(inChargeNoId==""||inChargeNoId==null){
		alert("Please enter incharge number");
		
		return false;
	}
	if(inChargeNoId.length<10){
		alert("Mobile Number should be in 10 digits");
		
		return false;
	}
	var inChargeAltMobileId=document.getElementById("inChargeAltMobileId").value;
	if(inChargeAltMobileId==""||inChargeAltMobileId==null){
		alert("Please enter incharge Alternate mobile number");
		
		return false;
	}
	if(inChargeNoId.length<10){
		alert("Alternate Mobile Number should be in 10 digits");
		
		return false;
	}
	var inChargeEmailId=document.getElementById("inChargeEmailId").value;
	 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(inChargeEmailId)){
		 
	}else{
		alert("Please enter correct Email Id");
		return false;
	}
	var tcIDId= document.getElementById("tcIDId").value;
	if(tcIDId==""||tcIDId==null){
		alert("Training Center Id cannot be null");
		return false;
		
	}
	var latitude=document.getElementById("tcLatitudeId").value;
	var longitude=document.getElementById('tcLongitudeId').value;
	 var lngVal = /^-?((1?[0-7]?|[0-9]?)[0-9]|180)\.[0-9]{1,6}$/;
	 var latVal = /^-?([0-8]?[0-9]|90)\.[0-9]{1,6}$/;
	 if(latitude.length > 0){
	    	 if (!latVal.test(latitude)) {
	       	alert("Please enter valid latitude..!!");
	    	return false;
	     } }
	 if(longitude.length > 0){
	if (!lngVal.test(longitude)) {
		alert("Please enter valid longitude..!!");
		return false;
	  }	
	}
	 
	 
	
	var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="tcSetupEntry.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
}
  
function checkLatitude(vv){
	//alert("vv");
	 var latVal = /^-?([0-8]?[0-9]|90)\.[0-9]{1,6}$/;
	 if(vv.value.length > 0){
  
		//alert("adssad");
    	 if (!latVal.test(vv.value)) {
       	 //alert("ss");
    		 window.setTimeout(function ()
   				    {
    	alert("Please enter valid latitude..!!");
    	document.getElementById("tcLatitudeId").focus();
   				 }, 0);
      	return false;
     } 
    
}
}

		function getpc(vv){
			 var cons=vv;
		
					var xmlHttpRequest = getXMLHttpRequest();
					
					
					
					  xmlHttpRequest.onreadystatechange = function(){
						 
						  
							if (xmlHttpRequest.readyState == 4) {
								
							if (xmlHttpRequest.status == 200) {
								
								if (xmlHttpRequest.responseText != "") {
									
									document.getElementById("parliamentId").innerHTML=xmlHttpRequest.responseText;
									/* var str=xmlHttpRequest.responseText.split("<~~~>");
									document.getElementById("districtId").innerHTML =str[0];
									var state=str[1].split("<!!!>");
									
									document.getElementById("stateNameId").value =state[0];
									document.getElementById("stateId").value =state[1];
									//document.getElementById("srno").value =str[2];
									
									 document.getElementById("tcIDId").value=""; */
									
								} 
							} else {
								
								document.getElementById("parliamentId").innerHTML = '<option value=""> --SELECT-- </option>';
						}
						}
					}
				
			xmlHttpRequest.open("POST","tcSetupEntry.do?methodName=getparliament", true);
			xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
			xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
			xmlHttpRequest.send("cons="+cons);	
				
				
				 
			}

function checkLongitude(vv){
	 var lngVal = /^-?((1?[0-7]?|[0-9]?)[0-9]|180)\.[0-9]{1,6}$/;
	//alert("ddd");
	if(vv.value.length > 0){
//  alert("bb");
		if (!lngVal.test(vv.value)) {
    //	alert("ff");
    	  window.setTimeout(function ()
				    {
    		         alert("Please enter valid longitude..!!");
				      document.getElementById('tcLongitudeId').focus();
				   
				    }, 0);
    	return false;
    }
	}
}

function back(){
	
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="tcSetupEntry.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}

		 
function getXMLHttpRequest() {
	var xmlHttpReq = false;
	// to create XMLHttpRequest object in non-Microsoft browsers
	
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	} else if (window.ActiveXObject) {
		try {
			// to create XMLHttpRequest object in later versions
			// of Internet Explorer
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				// to create XMLHttpRequest object in older versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}






	
	function getDistrictDetails(projectid){
		
		var xmlHttpRequest = getXMLHttpRequest();
		
		
			
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							var str=xmlHttpRequest.responseText.split("<~~~>");
							document.getElementById("districtId").innerHTML =str[0];
							var state=str[1].split("<!!!>");
							
							document.getElementById("stateNameId").value =state[0];
							document.getElementById("stateId").value =state[1];
							//document.getElementById("srno").value =str[2];
							
							 document.getElementById("tcIDId").value="";
							
						} 
					} else {
						
						document.getElementById("districtId").innerHTML = '<option value=""> --SELECT-- </option>';
				}
				}
			}
		
	xmlHttpRequest.open("POST","tcSetupEntry.do?methodName=getDistrictOfState", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectid="+projectid);	

	}
	function genratetcId(districtcode){
		//alert(districtcode);
		if(districtcode>0){
			getAssembly(districtcode);
			getpc(districtcode);
		}else{
			 document.getElementById("tcIDId").value="";
		}
	}
	function isNumberKey(evt)
	{
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if (charCode > 31 && (charCode < 48 || charCode > 57))
	      return false;
	   

	   return true;
	}
function getAssembly(discode){
		//alert("start");
		var xmlHttpRequest = getXMLHttpRequest();
		var	project=document.getElementById("projectIDId").value;
		
			
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							var response=xmlHttpRequest.responseText.split("<~~~>");
							document.getElementById("assemblyConsId").innerHTML=response[0];
							
							/* var	project=document.getElementById("projectIDId");
							var projectid=project.options[project.selectedIndex].text;
							//alert(projectid);
							var srno=document.getElementById("srno").value;
							var sr=parseInt(srno)+1;
							
							if(sr.length=1){
								sr="00"+sr;
							}else if(sr.length=2){
								sr="0"+sr;
							}
							
						    var tcid=projectid+""+response[1]+""+sr;*/
						    document.getElementById("tcIDId").value=response[1]; 
						} 
					} else {
						document.getElementById("tcIDId").value="";
						document.getElementById("assemblyConsId").innerHTML = '<option value=""> --SELECT-- </option>';
				}
				}
			}
		
	xmlHttpRequest.open("POST","tcSetupEntry.do?methodName=getAssembly", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("districtcode="+discode+"&&id="+project);	

	}
	
	
	
	

</script>
<!--Main form section starts here-->
<html:form action="login/tcSetupEntry">
 <input type="hidden" value="0" id="srno"/>
	<table width="100%" >
		<tr>
			<td>
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Training Center Detail</td>
					
               </tr>
			</table>
			<%-- <table width="100%" align="center" class="inputTBL">
				 
				 <tr>
					<th>Project ID<span class="text-error">*</span></th>
					<td><span class="text-error">  
					<html:select property="projectID" styleId="projectIDId"   onchange="javascript:showRemark(this.value);" >
								<html:option value="0">-select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select>
					</span></td>
				</tr>				
				</table> --%>
			 
				
				 <div id="newRec">
				<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>Project ID<span class="text-error">*</span></th>
					<td><span class="text-error">  
					<html:select property="projectID" styleId="projectIDId"   onchange="javascript:showRemark(this.value);" >
								<html:option value="0">-select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select>
					</span></td>
				</tr>	
					<tr>	
					<th>State Name<span class="text-error">*</span></th>
					<td><span class="text-error">
					<input type="text" readonly="true" id="stateNameId"/><html:hidden  property="state" value="4" styleId="stateId"/></span></td>
				</tr>
				
				<tr>	
					<th>Name of District <span class="text-error">*</span></th>
					<td><span class="text-error">
					<html:select property="district" styleId="districtId" onchange="javascript:genratetcId(this.value);">
					<html:option value="0">- Select -</html:option>
					
					
					</html:select>   </span></td>
				</tr>
				
				
				
				<tr>	
					<th style="vertical-align: top; text-align: left;">Address<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:textarea property="address" styleId="addressId"></html:textarea> </span></td>
				</tr>
			    <tr>
					<th>Pin code<span class="text-error">*</span></th>
					<td><span class="text-error">  <html:text property="pinCode"  styleId="pinCodeId" maxlength="6"  onkeypress="return isNumberKey(event)" /> </span></td>
				</tr>
				
				<tr>
				<th>Assembly Constituency</th>
				<td><span class="text-error">  
				<html:select property="assemblyCons" styleId="assemblyConsId"    styleClass="form-control" >
				<html:option value="0">- Select -</html:option>
				</html:select>
				</span></td>
				</tr>
				
				<tr>	
					<th>Name of Parliament Constituency<span class="text-error">*</span></th>
					<td><span class="text-error">
					<html:select property="parliament" styleId="parliamentId" >
					<html:option value="0">- Select -</html:option>					
					</html:select>   </span></td>
				</tr>
				
				
				<tr>
				<th>Latitude of TC (Format e.g. 12.234542)</th>
				<td><span class="text-error">  <html:text property="tcLatitude"  styleId="tcLatitudeId"  /> </span></td>
				</tr>
				
				<tr>
				<th>Longitude of TC(Format e.g. 12.234542)</th>
				<td><span class="text-error">  <html:text property="tcLongitude"   styleId="tcLongitudeId" /> </span></td>
				</tr>
				
				<tr>	
					<th>Residential/Non-residential<span class="text-error">*</span></th>
					<td><span class="text-error"> 
					<html:select property="resiStatus" styleId="resiStatusId">
					<html:option value="0">Select</html:option>
					<html:option value="Residential">Residential</html:option>
					<html:option value="Non Residential">Non-residential</html:option>
					<html:option value="Both">Both</html:option>
					</html:select>	
					 </span></td>
				</tr>
				
				<tr>
				<th>TC In-charge Name</th>
				<td><span class="text-error">  <html:text property="inChargeName"   styleId="inChargeNameId" /> </span></td>
				</tr>
				
				<tr>
				<th>TRAINING CENTER NAME</th>
				<td><span class="text-error">  <html:text property="tcName"   styleId="tcNameId" /> </span></td>
				</tr>
				
				
				<tr>
				<th>TC In-charge Mobile No</th>
				<td><span class="text-error">  <html:text property="inChargeMobile"   styleId="inChargeNoId" onkeypress="return isNumberKey(event)" maxlength="10"/> </span></td>
				</tr>
				
			 <tr>
				<th>TC Alternate Mobile Number</th>
				<td><span class="text-error">  <html:text property="inChargeAltMobile" styleId="inChargeAltMobileId"  onkeypress="return isNumberKey(event)" maxlength="10"/> </span></td>
				</tr>
				
				<tr>
				<th>TC In-charge Email ID</th>
				<td><span class="text-error">  <html:text property="inChargeEmail"  styleId="inChargeEmailId"  /> </span></td>
				</tr>
				
				<tr>
				<th>training Center ID</th>
				<td><span class="text-error">  <html:text readonly="true" property="tcID"  styleId="tcIDId"  /> </span></td>
				</tr>
			 
			</table>
			
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>	
 			
			</div>		
		 
			 
			</td>
		</tr>
	</table>
	
</html:form>