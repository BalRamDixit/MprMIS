<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">
$(function() {
    $( "#joiningDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
    $( "#certificationDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
	$("#joiningDateId").datepicker('option', { maxDate:new Date()});
	$("#certificationDateId").datepicker('option', { maxDate:new Date()});
	
});
 


function closePage()
{
	document.getElementById("detail_table").style.display="";
	document.getElementById("newRec").style.display="none";
	document.getElementById("id").value="0";
	$('.CompareButton').show();
}
function clearField()
{
	document.getElementById("personNameId").value="";
	document.getElementById("emailId").value="";
	document.getElementById("joiningDateId").value="";
	document.getElementById("officeNoId").value="";
	document.getElementById("mobileNoId").value="";
	document.getElementById("certificationDateId").value="";
	//document.getElementById("personNameId").value="";
	//document.getElementById("personNameId").value="";
	
	document.getElementById("eSopCertReqId").value="Yes";
	document.getElementById("eSopCertLevelId").value="0";
	document.getElementById("eSOPCertificationNo").value="0";
	document.getElementById("districtCodeId").value="0";
	document.getElementById("locationId").value="State HQ";
	document.getElementById("designationId").value="0";
	document.getElementById("thematic").value="0";
	showDist('State HQ');
	
	//showCertDetail("Yes");
}

function hideShow(str){
	//alert(str);
	if(str=="HIDE"){
	    document.getElementById("newRec").style.display="none";
	   
	    $('.CompareButton').show();
	  /*   $('.detail_table').show(); */
	   document.getElementById("detail_table").style.display="";
	}else {
		document.getElementById("newRec").style.display="";	
		showDist('State HQ');
		$('.CompareButton').hide();
		/* $('.detail_table').hide(); */
	 document.getElementById("detail_table").style.display="none";
	}
}


function showDist(str){
	
	//alert(str);
	 if(str=="District HQ"){
		//alert("in show dist");
		getDistrictDetails();
		//alert("qq");
		document.getElementById("districtCodeId").disabled=false;	
	}else{
	    document.getElementById("districtCodeId").disabled=true;
	} 
	/*  (str=="State HQ") */
}

function showCertDetail(str){
	//clearField();
	//alert(str);
	if(str=="Yes"){
	    /* document.getElementById("esop1").style.display="";
	    document.getElementById("esop2").style.display=""; */
	    document.getElementById("eSopCertLevelId").disabled=false;
	    document.getElementById("certificationDateId").disabled=false;
	    document.getElementById("eSOPCertificationNo").disabled=false;
	}else {
		 document.getElementById("eSopCertLevelId").disabled=true;
		    document.getElementById("certificationDateId").disabled=true;
		    document.getElementById("eSOPCertificationNo").disabled=true;
		/* document.getElementById("esop1").style.display="none";	
		document.getElementById("esop2").style.display="none"; */
	}
}

$(document).ready(function () {
	//alert("aa");
	$('#example').DataTable();
	hideShow('HIDE');
	document.getElementById("id").value="0";
	changedateformat();
	dateOfLeavingDis("Yes");
	
});
function changedateformat(){
	$(".dateformate").each(function(){
		var text=$(this).text();
		var month=text.substring(5, 7);
		var year=text.substring(0,4);
		var date=text.substring(8,10);   
		$(this).text(date+"-"+month+"-"+year);
 });
}
function dateOfLeavingDis(str){
	if(str=="Yes"){
		document.getElementById("dateOfLeavingId").disabled=true;
	}else{
		document.getElementById("dateOfLeavingId").disabled=false;
		$("#dateOfLeavingId" ).datepicker({ dateFormat: 'dd-mm-yy' });
		$("#dateOfLeavingId").datepicker('option', { maxDate:new Date()});
	}
}


function addDetail(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
		hideShow('SHOW');
		showCertDetail('Yes');
		clearField();
		document.getElementById("saveandupdate").value="Save";
		document.getElementById("id").value=0;
		showDist('State HQ');
		dateOfLeavingDis("Yes");
	}
	
}

function editDetail(id,name, desig, joinD, loc, dist, email,  ofc, mob, esopR ,certN, esopL, certD ,isActiveId,dateofleaving ,thematic  ){
	//alert(email);
	document.getElementById("id").value=id;
	document.getElementById("personNameId").value=name;	
	document.getElementById("emailId").value=email;
	/* document.getElementById("joiningDateId").value=joinD; */
	
	
	if(joinD!=null && joinD!=""){
	var date2 = new Date(joinD);
	$("#joiningDateId").datepicker({
        dateFormat: 'dd-mm-yy'
    }).datepicker('setDate', date2)
	
	}
	
	
	document.getElementById("officeNoId").value=ofc;
	document.getElementById("mobileNoId").value=mob;
	
	//alert(esopR);
	document.getElementById("eSopCertReqId").value=esopR;
	showCertDetail(esopR);
	
	document.getElementById("eSOPCertificationNo").value=certN;
	
	
	document.getElementById("locationId").value=loc;
	
	showDist(loc);
	
		/* alert("thematic"+thematic); */
	document.getElementById("thematic").value=thematic;
	
	document.getElementById("designationId").value=desig;
	showCertDetail(esopR);
	
	document.getElementById("eSopCertLevelId").value=esopL;
	if(certD!=null && certD!=""){
		var date2 = new Date(certD);
		$("#certificationDateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
		
		}
	
	document.getElementById("isActiveId").value=isActiveId;
	if(isActiveId=="No"){
		
		document.getElementById("dateOfLeavingId").disabled=false;
		if(dateofleaving!=null && dateofleaving!=""){
			var date2 = new Date(dateofleaving);
			$("#dateOfLeavingId").datepicker({
		        dateFormat: 'dd-mm-yy'
		    }).datepicker('setDate', date2)
			
			}
		
	}else{
		dateOfLeavingDis("Yes");
		
	}
	 window.setTimeout(function ()
			    {
		 if(dist==null||dist==""){
			 dist=0;
		 }
		 document.getElementById("districtCodeId").value=dist;
			    }, 100);
	
	
	
	document.getElementById("saveandupdate").value="Update";
	
}

function deleteDetail(id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var strconfirm = confirm("Are you sure you want to delete the Record ?");
    if (strconfirm == true) {
    //	window.location = "stateHRTeam.do?methodName=delete"+"&id="id+"&"+tokenParameter+"="+tokenValue;
    	document.forms[0].action="stateHRTeam.do?methodName=deletehr"+"&"+tokenParameter+"="+tokenValue+"&id="+id;
		document.forms[0].submit();
     }
	}
}


function validation(){
	var i=0;
	var personNameId= document.getElementById("personNameId").value;
	if(personNameId==null || personNameId==""){
		document.getElementById("personNameId").style.border="1px solid red";
		// document.getElementById("personNameId").focus();
		 i++;
	}else{
		document.getElementById("personNameId").style.border ="none";
	}
	
	var designationId= document.getElementById("designationId").value ;
	if(designationId=="0"){
		document.getElementById("designationId").style.border="1px solid red";
		// document.getElementById("designationId").focus();
		 i++;
	}
	else{
		document.getElementById("designationId").style.border ="none";
	}
	
	
	var locationId= document.getElementById("locationId").value;  
	if(locationId!="State HQ"){
		
		var districtCodeId= document.getElementById("districtCodeId").value; 
		if(districtCodeId=="0"){
			document.getElementById("districtCodeId").style.border="1px solid red";
			// document.getElementById("districtCodeId").focus();
			 i++;
		}
		else{
			document.getElementById("districtCodeId").style.border ="none";
		}
	}
	/* var districtCodeId= document.getElementById("districtCodeId").value; 
	if(districtCodeId=="0"){
		document.getElementById("districtCodeId").style.border="1px solid red";
		
		 i++;
	}
	else{
		document.getElementById("districtCodeId").style.border ="none";
	} */
	
	
	 var emailId= document.getElementById("emailId").value; 
	 if (emailId.length<1)
	  {
		
		 document.getElementById("emailId").style.border="1px solid red";
		 i++;
		
	  }
	 
	 var thematic= document.getElementById("thematic").value ;
		if(thematic=="0"){
			document.getElementById("thematic").style.border="1px solid red";
			i++;
		}
		else{
			document.getElementById("thematic").style.border ="none";
		}
	 
	 
	var officeNoId= document.getElementById("officeNoId").value;
	var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	  if(officeNoId.match(phoneno))
	        {
		  document.getElementById("officeNoId").style.border ="none";
	        }
	      else
	        {
	    	  document.getElementById("officeNoId").style.border="1px solid red";
				// document.getElementById("officeNoId").focus();
				 i++;
	        }
	var mobileNoId= document.getElementById("mobileNoId").value;
	var phoneno = /^\(?([0-9]{3})\)?([0-9]{3})?([0-9]{4})$/;
	  if(mobileNoId.match(phoneno))
	        {
		  document.getElementById("mobileNoId").style.border ="none";
	        }
	      else
	        {
	    	  document.getElementById("mobileNoId").style.border="1px solid red";
		     // document.getElementById("mobileNoId").focus();
		      i++;
	        }
	  var eSopCertReqId= document.getElementById("eSopCertReqId").value;
	  if(eSopCertReqId="Yes"){
	var eSopCertReqId= document.getElementById("eSopCertLevelId").value; 
	var eSOPCertificationNo= document.getElementById("eSOPCertificationNo").value; 
	if(eSopCertLevelId=="0"){
		document.getElementById("eSopCertLevelId").style.border="1px solid red";
		// document.getElementById("eSopCertLevelId").focus();
		 i++;
	}
	else{
		document.getElementById("eSopCertLevelId").style.border ="none";
	}
	  }
	if(i>0){
		return false;
	}else{
		return true;
	}
	
	
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   

   return true;
}



function save(){
/* 	var validate=validation();
	if(!validate){
		alert("validation fail");
		return false;
	}
	 */
	 var x=checkPermissionForFormForInsert();
		if(x=='true'){
	var personNameId= document.getElementById("personNameId").value;
	if(personNameId==null || personNameId==""){
		
		alert("Please enter name");
		return false;
	}
	
	var designationId= document.getElementById("designationId").value ;
	if(designationId=="0"){
		alert("Please select designation");
		return false;
	}
	var locationId= document.getElementById("locationId").value;  
	//alert(locationId);
	if(locationId=="District HQ"){
		
		var districtCodeId= document.getElementById("districtCodeId").value; 
	//	alert(districtCodeId);
		if(districtCodeId=="0"){
			alert("Please select district");
			return false;
		}
	}
	
	var thematic= document.getElementById("thematic").value ;
	if(thematic=="0"){
		alert("Please select Thematic");
		return false;
	}
	
	/* var emailId= document.getElementById("emailId").value; 
	 if (emailId.length<1)
	  {
		 alert("Please enter email address");
			return false;
	  }
 */	 /* var officeNoId= document.getElementById("officeNoId").value;
		var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
		if(){
		  if(officeNoId.match(phoneno))
		        {
			 
		        }
		      else
		        {
		    	  alert("please enter correct mobile number");
		    	  return false;
		        }
		}
		  var mobileNoId= document.getElementById("mobileNoId").value;
			var phoneno = /^\(?([0-9]{3})\)?([0-9]{3})?([0-9]{4})$/;
			  if(mobileNoId.match(phoneno))
			        {
				  document.getElementById("mobileNoId").style.border ="none";
			        }
			      else
			        {
			    	  alert("please enter correct mobile number");
			    	  return false;
			        }
	 */
			  var eSopCertReqId= document.getElementById("eSopCertReqId").value;
			 // alert();
			  if(eSopCertReqId=="Yes"){
			var eSopCertLevelId= document.getElementById("eSopCertLevelId").value; 
			
			var eSOPCertificationNo= document.getElementById("eSOPCertificationNo").value; 
			if(eSopCertLevelId=="0"){
				alert("please select level of certification required");
				return false;
			}
			
			  }

			  var isActive= document.getElementById("isActiveId").value;
			  if(isActive=="No"){
				var dateofleaving= document.getElementById("dateOfLeavingId").value;
				//alert(dateofleaving.length);
				  if(dateofleaving.length<10){
					  alert("Please enter date of Leaving");
					  return false;
				  }else if(dateofleaving.length>10){
					  alert("Please enter date of Leaving");
					  return false;
				  }else{
					 var joiningdate =document.getElementById("joiningDateId").value;
					  
					 if(joiningdate.length<10){
						 alert(joiningdate.length);
						 alert("please enter joining date");
						 return false;
					 }
					    var month=joiningdate.substring(3, 5);
						var year=joiningdate.substring(6,10);
						var date=joiningdate.substring(0,2); 
						
						
						var month1=dateofleaving.substring(3, 5);
						var year1=dateofleaving.substring(6,10);
						var date1=dateofleaving.substring(0,2); 
					 
					 var joind=new Date(year+"-"+month+"-"+date);
					 var dateofle=new Date(year1+"-"+month1+"-"+date1);
					 if(+joind >= +dateofle){
						alert("date of leaving must be greate than date of joining..!!");
						document.getElementById("dateOfLeavingId").value="";
						return false;
					 }
				  }
				  
			  }
	
	
	
	
	 
	
	if(document.getElementById("id").value>0){
		var status=window.confirm('Are You Sure You Want to Update ?');
		if(status==true)
		{
			document.forms[0].action="stateHRTeam.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
		
	}
	else{
		var status=window.confirm('Are You Sure You Want to Save ?');
		if(status==true)
		{
			document.forms[0].action="stateHRTeam.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
		
		
	}
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
	
	function getDistrictDetails(){
		
		var xmlHttpRequest = getXMLHttpRequest();
		
		
			
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							
							document.getElementById("districtCodeId").innerHTML = xmlHttpRequest.responseText;
						} else{
							
							 document.getElementById("districtCodeId").innerHTML = '<option value="0"> --SELECT-- </option>'; 
					}
					} else {
						
						document.getElementById("districtCodeId").innerHTML = '<option value="0"> --SELECT-- </option>';
				}
				}
			}
		
	xmlHttpRequest.open("POST","stateHRTeam.do?methodName=getDistrictOfState", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send();	

	}
	
	function emailaddress(){
		
		
		 var email=document.getElementById("emailId").value; 
		 if(email.length<1){
			return false; 
		 } 
		 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
			
			 var xmlHttpRequest = getXMLHttpRequest();
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							
							
// 							if(xmlHttpRequest.responseText=="already exist")
// 							{
// 								alert(xmlHttpRequest.responseText);
// 								document.getElementById("emailId").style.border="1px solid red";
// 								 /* window.setTimeout(function ()
// 										    {
// 										        document.getElementById('emailId').focus();
// 										    }, 0); */
// 							}
// 							else
// 							{							
// 								document.getElementById("emailId").style.border ="none";
// 							}
							
							
							
						} else{
							
							 
					}
					} else {
						
						alert("error");
				}
				}
			}
		
	xmlHttpRequest.open("POST","stateHRTeam.do?methodName=getDetailEmail", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("email="+email);
	
	
	
	}
		 else{
			 
			   
			  document.getElementById("emailId").style.border="1px solid red";
			  alert("Email address is not valid");
			  window.setTimeout(function ()
					    {
					      document.getElementById('emailId').focus();
					     // alert()
					    }, 0);
			
				
		  }

	}
</script>
<!--Main form section starts here-->
<html:form action="login/stateHRTeam">
	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">HR Team</td>
		</tr>
	</table>
	<div align="right">
		<html:button styleClass="button checkPermissionForFormForInsert" property=""
			styleId="addB" onclick="addDetail();">ADD NEW</html:button>
	</div>
	<div id="detail_table">
	<table width="100%" class="TFtable display" id="example">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>Name</th>
				<th>Designation</th>
				<th>Joining Date</th>
				<th>Location</th>
				<th>Email ID</th>
				<th>Mobile</th>
				<th>eSOP certification<br /></th>
				<th>Is Active</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>S.No.</th>
				<th>Name</th>
				<th>Designation</th>
				<th>Joining Date</th>
				<th>Location</th>
				<th>Email ID</th>
				<th>Mobile</th>
				<th>eSOP certification<br /></th>
				<th>Is Active</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</tfoot>
		<tbody>
			<logic:present name="EmployeesList">
				<c:set var="indexCount" value="1" />
				<logic:iterate id="list" name="EmployeesList" indexId="index">

					<tr>
						<td>${indexCount}</td>
						<td>${list.personName}</td>
						<td>${list.designation.designationName}</td>
						<td class="dateformate">${list.joiningDate}</td>
						<td>${list.location}</td>
						<td>${list.email}</td>
						<td>${list.mobileNo}</td>
						<td>${list.eSopCertReq}</td>
						<td>${list.isActive}</td>
						<td><a href="#"  onclick="hideShow('SHOW');editDetail('${list.id}','${list.personName}','${list.designation.designationId}','${list.joiningDate}','${list.location}','${list.district.districtId}','${list.email}','${list.officeNo}','${list.mobileNo}','${list.eSopCertReq}','${list.certNo}','${list.eSopCertLevel}','${list.certificationDate}','${list.isActive}','${list.dateOfLeaving}','${list.thematic.projectTypeId}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" class="checkPermissionForFormForInsert" onclick="hideShow('HIDE');deleteDetail(${list.id})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>
	</table>
	</div>
	<div id="newRec">
		<table width="100%" align="center" class="inputTBL">

			<tr>
				<th>Name<span class="text-error">*</span></th>
				<td><span class="text-error"> <html:text
							property="personName" styleId="personNameId" />
				</span></td>
			</tr>

			<tr>
				<th>Designation<span class="text-error">*</span></th>
				<td><span class="text-error"> <html:select
							property="designation" styleId="designationId">
							<html:option value="0">-select-</html:option>
							<logic:present name="designationList">
								<logic:iterate id="designation" name="designationList">
									<html:option value="${designation.designationId}">${designation.designationName}</html:option>
								</logic:iterate>
							</logic:present>
						</html:select>
				</span></td>

			</tr>

			<tr>
				<th>Location <span class="text-error">*</span></th>
				<td><span class="text-error"><html:select
							property="location" onchange="showDist(this.value);"
							styleId="locationId">
							<html:option value="State HQ"> State HQ </html:option>
							<html:option value="District HQ"> District HQ </html:option>
							<html:option value="Block"> Block </html:option>
						</html:select> </span></td>
			</tr>

			<tr id="dist">
				<th>Name of District <span class="text-error">*</span></th>
				<td><span class="text-error"><html:select
							property="districtCode" styleId="districtCodeId">
							<html:option value="0">- Select -</html:option>
							<%--  <html:option value="EAST GODAVARI">EAST GODAVARI</html:option>
					       <html:option value="WEST GODAVARI">WEST GODAVARI</html:option> --%>

						</html:select> </span></td>
			</tr>
			
			<tr>
				<th> Thematic <span class="text-error">*</span></th>
				<td><span class="text-error"><html:select
							property="thematic" styleId="thematic">
							<html:option value="0">- Select -</html:option>
							<logic:present name="themeticRoleList">
								<logic:iterate id="thematic" name="themeticRoleList">
									<html:option value="${thematic.projectTypeId}">${thematic.projectTypeName}</html:option>
								</logic:iterate>
							</logic:present>
							
							</html:select> </span></td>
			</tr>
			

			<tr>
				<th>Date of Joining<span class="text-error"></span></th>
				<td><span class="text-error"> <html:text
							property="joiningDate" readonly="true" styleId="joiningDateId" />
				</span></td>
			</tr>

			<tr>
				<th>Email Address <span class="text-error"></span></th>
				<td><span class="text-error"> <html:text
							property="email" styleId="emailId" onblur="emailaddress()" />
				</span></td>
			</tr>

			<tr>
				<th>Office Number <span class="text-error"></span></th>
				<td><span class="text-error"> <html:text
							property="officeNo" styleId="officeNoId"
							onkeypress="return isNumberKey(event)" maxlength="10" />
				</span></td>
			</tr>

			<tr>
				<th>Mobile Number <span class="text-error"></span></th>
				<td><span class="text-error"> <html:text
							property="mobileNo" styleId="mobileNoId"
							onkeypress="return isNumberKey(event)" maxlength="10" />
				</span></td>
			</tr>
					
			<tr>
				<th>eSOP Certification Required <span class="text-error">*</span></th>
				<td><span class="text-error"> <html:select
							property="eSopCertReq" onchange="showCertDetail(this.value)"
							styleId="eSopCertReqId">
							<html:option value="Yes"> Yes </html:option>
							<html:option value="No"> No </html:option>
						</html:select>
				</span></td>
			</tr>
			<tr id="esop3">
			<th>eSOP Certification No <span class="text-error"></span></th>
				<td><span class="text-error"> <html:text property="certNo" styleId="eSOPCertificationNo" onkeypress="return isNumberKey(event)" maxlength="10" />
				</span></td>
			<tr id="esop1">
				<th>eSOP Level of Certification Required <span
					class="text-error">*</span></th>
				<td><span class="text-error"> <html:select property="eSopCertLevel" styleId="eSopCertLevelId">
							<html:option value="0">- Select -</html:option>
							<html:option value="Operation professional">Operation professional</html:option>
							<html:option value="Operation Master">Operation Master</html:option>
							<html:option value="Finance professional">Finance professional</html:option>
							<html:option value="Finance Master">Finance Master</html:option>
							<html:option value="Comprehensive professional">Comprehensive professional</html:option>
							<html:option value="Comprehensive Masters">Comprehensive Masters</html:option>
						</html:select>
				</span></td>
			</tr>

			<tr id="esop2">
				<th>Date of Certification <span class="text-error">*</span></th>
				<td><span class="text-error"> <html:text
							property="certificationDate" readonly="true" styleId="certificationDateId" />
				</span></td>
			</tr>

			<tr>
				<th>Is Active<span class="text-error">*</span></th>
				<td><span class="text-error"> <html:select
							property="isActive" styleId="isActiveId"
							onchange="dateOfLeavingDis(this.value)">
							<html:option value="Yes"> Yes </html:option>
							<html:option value="No"> No </html:option>
						</html:select>
				</span></td>
			</tr>
			<tr>
				<th>Date of Leaving<span class="text-error"></span></th>
				<td><span class="text-error"> <html:text
							property="dateOfLeaving" disabled="true" readonly="true"
							styleId="dateOfLeavingId" />
				</span></td>
			</tr>

		</table>
		<html:hidden property="id" styleId="id" value="0" />

		<div align="center">
			<input name="Button" type="button" class="button checkPermissionForFormForInsert" id="saveandupdate"
				value="save" onclick="save()" /> <input name="Button" type="button"
				class="button" value="<bean:message  key="button.close"/>"
				onclick="closePage()" />

		</div>

	</div>


</html:form>
</body>
<!--Main form section ends here-->