<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!--script for menu-->
<script type="text/javascript">

function getXMLHttpRequest() {
	var xmlHttpReq = false;
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
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
};

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
    $( "#sanctionDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
   // $( "#pwsDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
    $( "#pcoDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
    $( "#mouSignedDateId" ).datepicker({ dateFormat: 'dd-mm-yy' });
  
}); 
 //...this code is for changing date and its reflection on other
 function checkOnDate(){
	 $("#commDateId").datepicker({
		 dateFormat: 'dd-mm-yy',
		 beforeShow: function(input, inst) {
			 var mindate = $('#sanctionDateId').datepicker('getDate');
		       $(this).datepicker('option', 'minDate', mindate);
		 }
	 });
	/*  $("#mouSignedDateId").datepicker({
		 dateFormat: 'dd-mm-yy',
		 beforeShow: function(input, inst) {
			 var mindate = $('#sanctionDateId').datepicker('getDate');
		       $(this).datepicker('option', 'minDate', mindate);
		 }
	 });  */
	 $("#pwsDateId").datepicker({
		 dateFormat: 'dd-mm-yy',
		 beforeShow: function(input, inst) {
			 var mindate = $('#sanctionDateId').datepicker('getDate');
		       $(this).datepicker('option', 'minDate', mindate);
		 }
	 }); 	 
}
 function setpcodate(){
	// alert("aa");
	 var mindate = $('#mouSignedDateId').datepicker('getDate');
	 $("#pcoDateId").datepicker('option', { minDate:mindate});
	/*  $("#pcoDateId").datepicker({
		 dateFormat: 'dd-mm-yy',
		 beforeShow: function(input, inst) {
			 var mindate = $('#mouSignedDateId').datepicker('getDate');
		       $(this).datepicker('option', 'minDate', mindate);
		 }
	 }); */
 }
 function checkDateData(){
	 $("#perDateId").datepicker({
         dateFormat: 'dd-mm-yy',
		 beforeShow: function(input, inst) {
		 var mindate = $('#pwsDateId').datepicker('getDate');
		 $(this).datepicker('option', 'minDate', mindate);
		}
	 });
}
//...ends...
function hidebodercolor(fieldid)
{
   document.getElementById(fieldid.id).style.border="";	
}
		
function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
		var sanctionDate=document.getElementById("sanctionDateId").value;
		if(sanctionDate==''){
			   alert("Please fill Sanction Date ");
			   document.getElementById("sanctionDateId").style.border="1px solid red";
			   document.getElementById("sanctionDateId").focus(); 
			   return;
		}
		var commDate=document.getElementById("commDateId").value;
		if(commDate==''){
			   alert("Please fill Commencement Date ");
			   document.getElementById("commDateId").style.border="1px solid red";
			   document.getElementById("commDateId").focus(); 
			   return;
		}
		//var pwsDate=document.getElementById("pwsDateId").value;
		 /* if(sanctionDate>pwsDate)
			{
			     alert("PWS approval Date cannot be earlier than Project sanction Date");
			     document.getElementById("pwsDateId").style.border="1px solid red";
			     document.getElementById("pwsDateId").focus(); 
			     return;
			}  */
		  var status=window.confirm('Are You Sure You Want to Save ?');
		 	if(status==true){
				document.forms[0].action="projectSanctionAction.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
			} 
	}
}
  
function back(){
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="projectSanctionAction.do?methodName=sanctionDetails"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
function getApprovalDate(projectIDId){
	var projectIDId= document.getElementById("projectIDId").value;

	var xmlHttpRequest = getXMLHttpRequest();
     xmlHttpRequest.onreadystatechange = function(){
	 
	  if (xmlHttpRequest.readyState == 4) {
		if (xmlHttpRequest.status == 200) {
			//alert("response....."+xmlHttpRequest.responseText);
			if (xmlHttpRequest.responseText != "") {
				var response=xmlHttpRequest.responseText.split("<~~~>");
				//alert(response[0]);
				 $("#sanctionDateId").datepicker('option', { minDate:new Date(response[0])});
				 document.getElementById("stateNameId").value=response[1];
				 
			} else{
			
			}
			} else {
				document.getElementById("dateOfapprovalId").innerHTML = '';
		}
		}
	}
xmlHttpRequest.open("POST","projectSanctionAction.do?methodName=getDate", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("project_id="+projectIDId);	
}
function changedate(){
	var projectduration=document.getElementById("projectDurationId").value;
	
	if(projectduration==""||projectduration==null){
	$("#pcoDateId").datepicker('setDate', null);	
	$("#commDateId").datepicker('setDate', null);	
	$("#endDateId").datepicker('setDate', null);	
	$("#placementcompletionId").datepicker('setDate', null);	
	$("#trainingcompletionId").datepicker('setDate', null);	
	return false;
	}
	var pcodate=document.getElementById("pcoDateId").value;
	if(pcodate==""||pcodate==null){
		$("#commDateId").datepicker('setDate', null);	
		$("#endDateId").datepicker('setDate', null);	
		$("#placementcompletionId").datepicker('setDate', null);	
		$("#trainingcompletionId").datepicker('setDate', null);	
		
	}else{
		getCommencementdate();
	}
	
	}
function getCommencementdate(){
	var projectduration=document.getElementById("projectDurationId").value;
	if(projectduration==null||projectduration==""){
		alert("Enter project duration");
		$("#pcoDateId").datepicker('setDate', null);	
		return false;
	}
	
	
	var pcodate=document.getElementById("pcoDateId").value;
	var pcodateor=new Date(pcodate.split("-")[2]+"-"+pcodate.split("-")[1]+"-"+pcodate.split("-")[0]);
	pcodateor.setMonth(pcodateor.getMonth() +parseInt("1"));
	  $("#commDateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', pcodateor)
	    $("#commDateId").datepicker('option', 'minDate', pcodateor);
	  $("#commDateId").datepicker('option', 'maxDate', pcodateor);
	 // $("#perDateId").datepicker('option', 'minDate', pcodateor);
	/*   $("#perDateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('minDate', pcodateor)
	   */
	   getEndDate();
	  $("#perDateId").datepicker({
	        dateFormat: 'dd-mm-yy',
			 beforeShow: function(input, inst) {
			// var mindate = $('#pwsDateId').datepicker('getDate');
			 $(this).datepicker('option', 'minDate', pcodateor);
			}
		 });
}

function setpwsapprovaldate(){
	//alert("aa");
	var pcodate=document.getElementById("perDateId").value;
	var pcodateor=new Date(pcodate.split("-")[2]+"-"+pcodate.split("-")[1]+"-"+pcodate.split("-")[0]);
	
	$("#pwsDateId").datepicker({
        dateFormat: 'dd-mm-yy',
		 beforeShow: function(input, inst) {
		
		 $(this).datepicker('option', 'minDate', pcodateor);
		}
	 });
}

function getEndDate(){
	//alert("aa");
	  var projectDuration=document.getElementById("projectDurationId").value;
	  var commDate=document.getElementById("commDateId").value;
	  
	   var CurrentDate = new Date(commDate.split("-")[2]+"-"+commDate.split("-")[1]+"-"+commDate.split("-")[0]);
		  CurrentDate.setMonth(CurrentDate.getMonth() +parseInt(projectDuration));
		  $("#placementcompletionId").datepicker({
		        dateFormat: 'dd-mm-yy'
		    }).datepicker('setDate', CurrentDate)
		  $("#placementcompletionId").datepicker('option', 'minDate', CurrentDate);
	  $("#placementcompletionId").datepicker('option', 'maxDate', CurrentDate);  
		    
		  CurrentDate.setMonth(CurrentDate.getMonth() -parseInt("4"));
		  $("#trainingcompletionId").datepicker({
		        dateFormat: 'dd-mm-yy'
		    }).datepicker('setDate', CurrentDate)
		  $("#trainingcompletionId").datepicker('option', 'minDate', CurrentDate);
	  $("#trainingcompletionId").datepicker('option', 'maxDate', CurrentDate);  
		    
		     CurrentDate.setMonth(CurrentDate.getMonth() + parseInt("18"));
		     $("#endDateId").datepicker({
		        dateFormat: 'dd-mm-yy'
		    }).datepicker('setDate', CurrentDate)
		    $("#endDateId").datepicker('option', 'minDate', CurrentDate);
			  $("#endDateId").datepicker('option', 'maxDate', CurrentDate);
		    
 }

$(document).ready(function()
{
	var str =  document.getElementById("stateNameId").value;
});

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
 return true;
}  
//...code to get the state name
function getcc(str){
	
	var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = function(){
		 
		  if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200) {
				if (xmlHttpRequest.responseText != "") {
					   document.getElementById("stateNameId").value = xmlHttpRequest.responseText;
				} else{
				}
				} else {
					document.getElementById("stateNameId").innerHTML = '';
			}
			}
		}
	xmlHttpRequest.open("POST","projectSanctionAction.do?methodName=getStateName", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("project_id="+str);	
}
function disfield(val){
	if(val=="Yes"){
		
		document.getElementById("agencyCodeId").disabled = false;
		document.getElementById("accountNumberId").disabled = false;
		document.getElementById("bankNameId").disabled = false;
		document.getElementById("ifscCodeId").disabled = false;
		document.getElementById("projectNameId").disabled = false;
		
		
	}else{
		document.getElementById("agencyCodeId").disabled = true;
		document.getElementById("accountNumberId").disabled = true;
		document.getElementById("bankNameId").disabled = true;
		document.getElementById("ifscCodeId").disabled = true;
		document.getElementById("projectNameId").disabled = true;
	}
}
function getTotal(){
	var santarget=document.getElementById("sanTargetId").value;
	var residential= document.getElementById("residentialId").value;
	if(santarget==null||santarget==""){
		alert("Please enter Sanction Target");
		document.getElementById("residentialId").value=0;
		changeTextToBack(document.getElementById("residentialId"),"maskedAmountresidentialId");
		document.getElementById("nonresidentialId").value=0;
		changeTextToBack(document.getElementById("nonresidentialId"),"maskedAmountnonresidentialId");
		return false;
	}
	if(residential==""||residential==null){
		residential=0;
	}
	if(parseInt(residential) >  parseInt(santarget)){
		alert("Residential Target not be greater than Sanction Target");
		document.getElementById("residentialId").value=0;
		changeTextToBack(document.getElementById("residentialId"),"maskedAmountresidentialId");
		return false;
	}
	//var nonresidential=	document.getElementById("nonresidentialId").value;
	
	/* if(nonresidential==""||nonresidential==null){
		nonresidential=0;
	} */
	document.getElementById("nonresidentialId").value=parseInt(santarget)-parseInt(residential);
	changeTextToBack(document.getElementById("nonresidentialId"),"maskedAmountnonresidentialId");
    
}
 function changeTextToBack(element,x){
		
		var getInput = $(element).val();
		
		$("#"+x).autoNumeric('set', getInput);
		
	}
</script>
<!--Main form section starts here-->
<html:form action="login/projectSanctionAction">
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">SANCTION DETAIL</td>					
               </tr>
			</table>			
			
	<%-- <table width="100%" align="center" class="inputTBL">
	
		   <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
		    <td>	<html:select property="projectId" styleId="projectIDId"   styleClass="form-control"  onchange="getApprovalDate(this.value);" >
					<html:option value="">-select-</html:option>
							<logic:present name="projectList">
								<logic:iterate id="project" name="projectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
								</logic:iterate>
							</logic:present>
					</html:select></td>
		   </tr>
						   
	 <tr>
            <th>State Name <span class="text-error"></span></th>
            <td><span class="text-error"><html:text   property="stateName" readonly="true" styleId="stateNameId"  /></span></td>
         </tr>	 	
			 </table> --%>
			
			 
		<div id="showTable">
		<input type="hidden" id="dateOfapprovalId" name="dateOfapproval"/> 
		<table width="100%" align="center" class="inputTBL">
		 
		 <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
		    <td>	<html:select property="projectId" styleId="projectIDId"   styleClass="form-control"  onchange="getApprovalDate(this.value);" >
					<html:option value="">-select-</html:option>
							<logic:present name="projectList">
								<logic:iterate id="project" name="projectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
								</logic:iterate>
							</logic:present>
					</html:select></td>
		   </tr>
						   
	 <tr>
            <th>State Name <span class="text-error"></span></th>
            <td><span class="text-error"><html:text   property="stateName" readonly="true" styleId="stateNameId"  /></span></td>
         </tr>	
		<tr>
		<th>Sanction Order No<span class="text-error"></span></th>
			 <td><span class="text-error"><html:text property="sanOrder"  styleId="sanOrderId"   onchange="hidebodercolor(this);"/>  </span></td>
		</tr>
			
		<tr>
		<th>Date of Sanction<span class="text-error"></span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="sanctionDate"  styleId="sanctionDateId"  
			         onchange="hidebodercolor(this);checkOnDate();"/>  </span></td>
		</tr>
		 <tr>	
		 <th>Sanction Target<span class="text-error"></span></th>
		 <td><span class="text-error"><html:text styleClass="amount"  property="sanTarget" readonly="true" 
		            styleId="sanTargetId"  onchange="hidebodercolor(this);"/>  </span></td>
		 </tr> 
			<tr>	
		 <th>Residential Target<span class="text-error"></span></th>
		 <td><span class="text-error"><html:text styleClass="amount"  property="residential" 
		            styleId="residentialId"  onchange="hidebodercolor(this);" onblur="getTotal();" />  </span></td> 
		 </tr>
		 <tr>	
		 <th>Non-Residential Target<span class="text-error"></span></th>
		 <td><span class="text-error"><html:text styleClass="amount"  property="nonresidential" 
		            styleId="nonresidentialId" readonly="true"  onchange="hidebodercolor(this);" />  </span></td><!-- onblur="getTotal();" -->
		 </tr>
		<tr>	
		  <th>Project Duration (months)<span class="text-error"></span></th>
		  <td><span class="text-error"><html:text  styleClass="amount" property="projectDuration" onkeypress="return isNumberKey(event)"
		            styleId="projectDurationId"  onblur="hidebodercolor(this);changedate();"/>  </span></td>
		</tr> 	
		<tr>
        <th>Whether MoU Signed<span class="text-error"></span></th>
            <td><span class="text-error">
               <html:select property="mouSigned" styleId="mouSignedId" onchange="hidebodercolor(this);" >
						<%-- 	<html:option value="">Select</html:option> --%>
							<html:option value="Yes">Yes</html:option>
							<html:option value="No">No</html:option>
		      </html:select></span>
			</td>
            </tr> 
            
         <tr>
		<th>MoU signed Date <span class="text-error"></span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="mouSignedDate"  styleId="mouSignedDateId" 
		             onchange="setpcodate();"/>  </span></td>
		</tr>
					   
		
		
		
		
		<!-- new added  -->
		
		<tr>	
		 <th>PCO Date<span class="text-error"></span></th>
		 <td><span class="text-error"><html:text  property="pcoDate" readonly="true" styleId="pcoDateId"  onchange="getCommencementdate();"/>  </span></td>
		</tr> 
		
		<!-- new ends here   -->
		
		<tr>
		<th>Commencement Date <span class="text-error">*</span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="commDate"  styleId="commDateId" 
			       onchange="hidebodercolor(this);"/>  </span></td>
		</tr>
		<tr>
		<th>Last date of Training completion<span class="text-error"></span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="trainingcompletion"  styleId="trainingcompletionId"  onkeypress="return false" />  </span></td>
		</tr>
		<tr>
		<th>Last date of placement completion<span class="text-error"></span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="placementcompletion"  styleId="placementcompletionId"  onkeypress="return false" />  </span></td>
		</tr>
		
		<tr>
		<th>Project End Date <span class="text-error"></span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="endDate"  styleId="endDateId" onkeypress="return false" />  </span></td>
		</tr> 
		
		<tr>
         <th>Whether Project Execution Readiness (PER) approved <span class="text-error"></span></th>
            <td><span class="text-error">
               <html:select property="perApproved" styleId="perApprovedId" onchange="hidebodercolor(this);" >
							<%-- <html:option value="">Select</html:option> --%>
							<html:option value="Yes">Yes</html:option>
							<html:option value="No">No</html:option>
		      </html:select></span>
		 </td>
          </tr>  
		 <tr>
		  <th>Date of PER <span class="text-error"></span></th>
		   <td><span class="text-error nndate"><html:text readonly="true" property="perDate"  styleId="perDateId" 
			          onchange="setpwsapprovaldate();"/>  </span></td>
		 </tr>
		
		<tr>
        <th>Whether Project Work Schedule (PWS) approved<span class="text-error"></span></th>
            <td><span class="text-error">
               <html:select property="pwsApproved" styleId="pwsApprovedId" onchange="hidebodercolor(this);" >
							<%-- <html:option value="">Select</html:option> --%>
							<html:option value="Yes">Yes</html:option>
							<html:option value="No">No</html:option>
		      </html:select></span>
			</td>
            </tr> 
		
	    <tr>
		<th>PWS approval Date <span class="text-error"></span></th>
		<td><span class="text-error nndate"><html:text readonly="true" property="pwsDate"  styleId="pwsDateId"  
		                 onchange="hidebodercolor(this);"/>  </span></td>
		</tr>
		
		  <tr>
            <th>Status of the Project<span class="text-error"></span></th>
            <td><span class="text-error">
               <html:select property="projectStatus" styleId="projectStatusId" onchange="hidebodercolor(this);" >
			                   <html:option value="Not Commenced">Not Commenced</html:option>
							    <html:option value="Ongoing">Ongoing</html:option>
							    <html:option value="Recommended for Cancellation">Recommended for Cancellation</html:option>
							    <html:option value="Cancelled">Cancelled</html:option>
							    <html:option value="Processing for Completion">Processing for Completion</html:option>
							    <html:option value="Completed">Completed</html:option>
							    <html:option value="Terminated">Terminated</html:option>
			    </html:select></span>
			</td>
            </tr>
            <tr>	
		  <th>Remarks<span class="text-error"></span></th>
		  <td><span class="text-error"><html:textarea property="remark"  styleId="remarkId" />  </span></td>
		</tr>   
			<tr>
				<th>Whether register with PFMS<span class="text-error"></span></th>
				<td><span class="text-error">
               <html:select property="registerToPfms" styleId="registerToPfmsId" onchange="disfield(this.value)" >
			                   <html:option value="Yes">Yes</html:option>
			                    <html:option value="No">No</html:option>
							   
			    </html:select></span></td>

			</tr>
			<tr>
				<th>Agency code<span class="text-error"></span></th>
				<td><span class="text-error"><html:text  property="agencyCode"  styleId="agencyCodeId" /></span></td>
			</tr>
			<tr>
				<th>Account Number<span class="text-error"></span></th>
				<td><span class="text-error"><html:text  property="accountNumber"  styleId="accountNumberId" /></span></td>
			</tr>
			<tr>
				<th>Bank Name<span class="text-error"></span></th>
				<td><span class="text-error"><html:text  property="bankName"  styleId="bankNameId" /></span></td>
			</tr>
			<tr>
				<th>IFSC Code<span class="text-error"></span></th>
				<td><span class="text-error"><html:text  property="ifscCode"  styleId="ifscCodeId" /></span></td>
			</tr>
<tr>
		<th>Project Name in PFMS<span class="text-error"></span></th>
			 <td><span class="text-error"><html:text property="projectName"  styleId="projectNameId"   onchange="hidebodercolor(this);"/>  </span></td>
		</tr>


			  
              
    
				
			
		
			
			</table> 
			
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			      <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>	
			</div>
	
</html:form>
 