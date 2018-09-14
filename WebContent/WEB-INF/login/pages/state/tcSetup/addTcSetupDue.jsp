<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<!--script for menu-->
<script type="text/javascript">

$(document).ready(function () {
    $(".nndate").each(function() {
    	$(this).find("input:text").each(function() {
    	var dbDate=$(this).val();
    	if(dbDate!=null && dbDate!=""){
    	var date2 = new Date(dbDate);
    	$(this).datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
    }
    });
    });
});

$(function() {
    $( "#recieptDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
    $("#recieptDateId").datepicker('option', { maxDate:new Date()});
    $( "#visitDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
    $( "#appRejDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
}); 

function getXMLHttpRequest() {
	var xmlHttpReq = false;
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	} else if (window.ActiveXObject) {
	try {
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
	try {
			  xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (exp2) {
				xmlHttpReq = false;
		}
	}
  }
	return xmlHttpReq;
}

function getStatus(str){
	var abc= document.getElementById("statusDueDilId").value;
	if(abc=="Approved")
	{
		var val= "<option value='Active'>Active</option>,<option value='In Active'>In Active</option>,<option value='Closed'>Closed</option>";
		document.getElementById("tcStatusId").innerHTML = val;
	}
	else{
		var val= "<option value='Due Deligence Pending'>Due Deligence Pending</option>";
		document.getElementById("tcStatusId").innerHTML = val;
	}
}

function getSrlmPerson(str){
	
   var xmlHttpRequest = getXMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function(){
		   
	 if (xmlHttpRequest.readyState == 4) {
	 if (xmlHttpRequest.status == 200) {
	 if (xmlHttpRequest.responseText != "")
	{
		var response=xmlHttpRequest.responseText;
		var aa=JSON.parse(response);
		var res="<option value='0'>--select--</option>";
		for(var i=0;i<aa.length;i++)
		{
			res+="<option value="+aa[i].id+">"+aa[i].name+"/"+aa[i].designation+"</option>";
		}	
			document.getElementById("srlmPersonNameId").innerHTML = res; 			
		} 
		else{
			document.getElementById("srlmPersonNameId").innerHTML = res; 
			}
		} else {
			document.getElementById("srlmPersonNameId").innerHTML = '<option value="0"> --SELECT-- </option>';
			}
		}
		}
xmlHttpRequest.open("POST","tcSetupDue.do?methodName=getSrlm", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("projectid="+str);		
} 

function abcdef(projectID){
			
	var xmlHttpRequest = getXMLHttpRequest();
		xmlHttpRequest.onreadystatechange = function(){
		 	if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					if (xmlHttpRequest.responseText != "") {
						var response=xmlHttpRequest.responseText.split("<~~~>");
									document.getElementById("trainingCenterIDADD").innerHTML = response[0];
									document.getElementById("srlmPersonNameId").innerHTML=response[1];
					   } else{
						            document.getElementById("trainingCenterIDADD").innerHTML = '<option value="0"> --SELECT-- </option>'; 
						     }
			        	} else {
									document.getElementById("trainingCenterIDADD").innerHTML = '<option value="0"> --SELECT-- </option>';
					}
			}
		}
xmlHttpRequest.open("POST","tcSetupDue.do?methodName=getTraining", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("project_id="+projectID);	
}

function checkDate(vv){
	var dateApproval=document.getElementById("visitDateId").value;
	var appRejDate=document.getElementById("appRejDateId").value;

	//alert("dateApproval"+dateApproval);
	//alert("appRejDate"+appRejDate);
	
   var  job_app_date = dateApproval.split('-');
   var job_visit_date = appRejDate.split('-');
//alert(job_app_date);
//alert(job_visit_date);
   var new_start_date = new Date(job_app_date[2],job_app_date[1]-1,job_app_date[0]);
 
   var new_end_date = new Date(job_visit_date[2],job_visit_date[1]-1,job_visit_date[0]);

//alert("new_start_date"+new_start_date);
//alert("new_end_date"+new_end_date);
   if(new_end_date < new_start_date) {
      alert("Actual Date of  DD visited by SRLM must be after the requested Date from Q team");
      document.getElementById("appRejDateId").value=""; 
	   return false;
   }
  } 
  
function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
	//var projId=document.getElementById("projectIDId").value;
	/* if(projId=='')
	{
	  alert("Please fill Project Id");
	   document.getElementById("projectIDId").style.border="1px solid red";
	   document.getElementById("projectIDId").focus(); 
	   return;
	} */
	
	/*  var trainingCenter=document.getElementById("trainingCenterIDADD").value;
	if(trainingCenter==''||trainingCenter=='0')
	{
	  alert("Please fill Training Center Id");
	   document.getElementById("trainingCenterIDADD").style.border="1px solid red";
	   document.getElementById("trainingCenterIDADD").focus(); 
	   return;
	}  */
	 var tcappcap=document.getElementById("tcAppCapacityId").value;
	var tcResicap= document.getElementById("tcAppResidentCapacityId").value;	
	if(tcappcap==null||tcappcap==""){
		tcappcap=0;
	}
	if(tcResicap==null||tcResicap==""){
		tcResicap=0;
	}
	if(parseInt(tcappcap)<parseInt(tcResicap)){
		alert("Training Center Approved Residential Capacity should be less than or equal to Training Center Approved Capacity ");
		return false;
	}
	
	
	var dateApproval=document.getElementById("recieptDateId").value;
	if(dateApproval=='')
	{
	  alert("Please fill Date of Receipt of Request from Q team");
	   document.getElementById("recieptDateId").style.border="1px solid red";
	   document.getElementById("recieptDateId").focus(); 
	   return;
	}
	var visitDate=document.getElementById("visitDateId").value;
	if(visitDate=='')
	{
	  alert("Please fill Actual Date ofDD visited by SRLM");
	   document.getElementById("visitDateId").style.border="1px solid red";
	   document.getElementById("visitDateId").focus(); 
	   return;
	}
	/* if(dateApproval>visitDate)
	{
	  alert("Actual Date of  DD visited by SRLM must be after the requested Date from Q team");
	   document.getElementById("visitDateId").style.border="1px solid red";
	   document.getElementById("visitDateId").focus(); 
	   return;
	} */
	/* var appRejDate=document.getElementById("appRejDateId").value;
		
	
	if(visitDate>appRejDate)
	{
	   alert("Date of Approval must be after the Date of DD visited by SRLM");
	   document.getElementById("appRejDateId").style.border="1px solid red";
	   document.getElementById("appRejDateId").focus(); 
	   return;
	} */
	 var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="tcSetupDue.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		} 
	}
}
function back(){
	var status=window.confirm('Are You Sure You Want to go back ?');
	if(status==true){
			document.forms[0].action="tcSetupDue.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	}
}
  
function hidebodercolor(fieldid)
{
   document.getElementById(fieldid.id).style.border="";	
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

</script>
<!--Main form section starts here-->
<html:form action="login/tcSetupDue">
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">TRAINING CENTER-DUE DILIGENCE</td>					
               </tr>
			</table>			
			
<!-- 	<div id="showTable">		 -->
	<table width="100%" align="center" class="inputTBL">

		<tr>
			<th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
			<!-- getSrlmPerson(this.value); -->
			<td><html:select property="projectID" styleId="projectIDId" styleClass="form-control" onchange="abcdef(this.value);">
					<html:option value="">-select-</html:option>
					<logic:present name="projectList">
						<logic:iterate id="project" name="projectList">
							<html:option value="${project.id}">${project.projectID}</html:option>
						</logic:iterate>
					</logic:present>
				</html:select></td>
		</tr>

		<tr>
			<th><span class="input-group-addon" id="basic-addon1">Training Centre ID</span><span class="text-error"> *</span></th>
			<td><html:select property="trainingCenterId" styleId="trainingCenterIDADD" styleClass="form-control"> </html:select></td>
		</tr>


		<tr>
			<th>Date of receipt of request from Q team for Due diligence<span
				class="text-error"> *</span></th>
			<td><span class="text-error nndate"><html:text readonly="true" property="recieptDate" styleId="recieptDateId"
						onchange="hidebodercolor(this);" /> </span></td>
		</tr>


		<tr>
			<th><span class="input-group-addon" id="basic-addon1">SRLM person who did DD (only eSOP certified person)</span><span class="text-error"> *</span></th>
			<td><html:select property="srlmPersonId" styleId="srlmPersonNameId" styleClass="form-control">
					<html:option value="">-select-</html:option>
					<logic:present name="hrlist">
						<logic:iterate id="hrlist" name="hrlist">
							<html:option value="${hrlist.id}">${hrlist.personName} ${hrlist.designation.designationName}</html:option>
						</logic:iterate>
					</logic:present>
				</html:select></td>
		</tr>


		<tr>
			<th>Actual date of DD visit by SRLM<span class="text-error"></span><span
				class="text-error"> *</span></th>
			<td><span class="text-error nndate"><html:text
						property="visitDate" readonly="true" styleId="visitDateId"
						onchange="hidebodercolor(this);" /> </span></td>
		</tr>


		<tr>
			<th>Date of Approval / Rejection by SRLM<span class="text-error"></span><span
				class="text-error"> *</span></th>
			<td><span class="text-error nndate"><html:text
						property="appRejDate" readonly="true" styleId="appRejDateId"
						onchange="hidebodercolor(this);checkDate(this);" /> </span></td>
		</tr>

		<tr>
			<th>Due-Diligence Status<span class="text-error"></span></th>
			<td><span class="text-error"> <html:select
						property="statusDueDil" styleId="statusDueDilId"
						onchange="hidebodercolor(this);javascript:getStatus(this.value);">
						<html:option value="">Select</html:option>
						<html:option value="Open">Open</html:option>
						<html:option value="Approved">Approved</html:option>
						<html:option value="Rejected">Rejected</html:option>
					</html:select></span></td>
		</tr>


		<tr>
			<th>Due-Diligence Remarks by SRLM<span class="text-error"></span></th>
			<td><span class="text-error"><html:textarea
						property="remarksDueDili" styleId="remarksDueDiliId" /> </span></td>
		</tr>

		<tr>
			<th>Training Center Approved Capacity <span class="text-error"></span></th>
			<td><span class="text-error"><html:text
						property="tcAppCapacity" onkeypress="return isNumberKey(event)"
						styleId="tcAppCapacityId" /> </span></td>
		</tr>
		<tr>
			<th>Training Center Approved Residential Capacity<span class="text-error"></span></th>
			<td><span class="text-error"><html:text
						property="tcAppResidentCapacity" onkeypress="return isNumberKey(event)"
						styleId="tcAppResidentCapacityId" /> </span></td>
		</tr>


		<tr>
			<th><span class="input-group-addon" id="basic-addon1">TC
					Status</span></th>
			<td><html:select property="tcStatus" styleId="tcStatusId"
					styleClass="form-control">

				</html:select></td>
		</tr>


		<tr>
			<th>Remarks<span class="text-error"></span></th>
			<td><span class="text-error"><html:textarea
						property="remarks" styleId="remarksId" /> </span></td>
		</tr>
		
			
	

	</table> 
	<div>
				<input name="Button" type="button"
					class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()" /> <input
					name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()" />
			</div>
<!-- 			</div> -->
	
</html:form>
 