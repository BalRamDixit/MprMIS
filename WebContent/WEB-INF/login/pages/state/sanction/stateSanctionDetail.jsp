<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

$(document).ready(function () {
	$('#example').DataTable();
	$(".TdMaskedAmount").autoNumeric("init");
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
    var pfms = $('select[name="pfms"]').val()
	 if(pfms == "No")
	 {
		    document.getElementById("ifscCodeID").disabled = true; 
	        document.getElementById("ifscCodeID").value = "";
	        document.getElementById("ifscCodeID").style.backgroundColor = "#D3D3D3";
	        
	        document.getElementById("bankNameID").disabled = true; 
	        document.getElementById("bankNameID").value = "";
	        document.getElementById("bankNameID").style.backgroundColor = "#D3D3D3";
	        
	        document.getElementById("accountNoID").disabled = true; 
	        document.getElementById("accountNoID").value = "";
	        document.getElementById("accountNoID").style.backgroundColor = "#D3D3D3";
	        
	        document.getElementById("agencyCodeID").disabled = true; 
	        document.getElementById("agencyCodeID").value = "";
	        document.getElementById("agencyCodeID").style.backgroundColor = "#D3D3D3";
	      
	        /* document.getElementById("projectNameID").disabled = true; 
	        document.getElementById("projectNameID").value = "";
	        document.getElementById("projectNameID").style.backgroundColor = "#D3D3D3"; */
        }else{
        	
        	 document.getElementById("ifscCodeID").disabled=false; 
	      	 document.getElementById("ifscCodeID").style.backgroundColor = "white";
	      	 
	      	 document.getElementById("bankNameID").disabled=false; 
	      	 document.getElementById("bankNameID").style.backgroundColor = "white";
	      	 
	      	 document.getElementById("accountNoID").disabled=false; 
	      	 document.getElementById("accountNoID").style.backgroundColor = "white";
	      	 
	         document.getElementById("agencyCodeID").disabled=false; 
	      	 document.getElementById("agencyCodeID").style.backgroundColor = "white";
	      	 
	      	/*  document.getElementById("projectNameID").disabled=false; 
	      	 document.getElementById("projectNameID").style.backgroundColor = "white"; */
        }	
 
});
	
	        
	       

function hidebodercolor(fieldid)
{
	document.getElementById(fieldid.id).style.border="";	
}

$(function() {
	$( "#sanctionDateID" ).datepicker({ dateFormat: 'dd-mm-yy' });
	$("#sanctionDateID").datepicker('option', { maxDate:new Date()});
});


/* this is done to change the value when page is loaded */
$(document).ready(function () {
	
	$("#maskedAmountsumCatId").attr('readonly', 'readonly');
	var id=$("#idUU").val();
  	if(id<1){
		$("#button").val("save");
	}
  	else  
  		$("#button").val("Update");   
});


function changeTargetEndYear(){
	var selectedYear = document.getElementById("targetStartedYearId").value;	
	$('#targetEndYearId').find('option[value!="0"]').remove();
	 for (var i = 1; i <=6; i++ ) {
	     $("#targetEndYearId").append($("<option></option>").attr("value", selectedYear).text(selectedYear));
	        selectedYear++;
	    }
	}

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

/* function setEndTargetyaer(){
	 window.setTimeout(function(){ 
	 document.getElementById("targetEndYearId").value='${sanctiondetails.targetEndYear}';
	 }, 100);
 	 } */

//this is usd for actual saving of data
function save(){
	
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
		
		 var fundsanctioned =document.getElementById("fundSanctionedId").value;
		 var totalsupportcost=document.getElementById("sumCatId").value;
		if(fundsanctioned==null||fundsanctioned==""){
			fundsanctioned=0;
		}if(totalsupportcost==null||totalsupportcost==""){
			totalsupportcost=0;
		}
		if(parseInt(fundsanctioned) < parseInt(totalsupportcost)){
			alert("Total Support Cost Must be less than or equal to Fund sanctioned");
			return false;
		}
		 
		 
		 
		 
		
		var startYear=document.getElementById("targetStartedYearId").value;
		if(startYear=='')
		{
		  alert("Please fill Target Period Start Year");
		   document.getElementById("targetStartedYearId").style.border="1px solid red";
		   document.getElementById("targetStartedYearId").focus(); 
		   return;
		}
		var endYear=document.getElementById("targetEndYearId").value;
		if(endYear=='')
		{
		  alert("Please fill Target Period End Year");
		   document.getElementById("targetEndYearId").style.border="1px solid red";
		   document.getElementById("targetEndYearId").focus(); 
		   return;
		}
		
	  	var id=$("#idUU").val();
		if(id<1){
			var status=window.confirm('ARE YOU SURE YOU WANT TO SAVE ?');
			if(status==true){
			    document.forms[0].action="stateSanctionAction.do?methodName=save&id="+id+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
			  }
		}
		else {
		    var status=window.confirm('ARE YOU SURE YOU WANT TO UPDATE ?');
			if(status==true){ 
					document.forms[0].action="stateSanctionAction.do?methodName=update"+"&"+tokenParameter+"="+tokenValue; 
					document.forms[0].submit();
			}
		}
	}
}
  
 	function changePfms(){
 		var pfms = document.getElementById("pfmsID").value; 
 		   if(pfms == "No" )
 			 {  
 			        document.getElementById("ifscCodeID").disabled = true; 
 			        document.getElementById("ifscCodeID").value = "";
 			        document.getElementById("ifscCodeID").style.backgroundColor = "#D3D3D3";
 			        
 			        document.getElementById("bankNameID").disabled = true; 
			        document.getElementById("bankNameID").value = "";
			        document.getElementById("bankNameID").style.backgroundColor = "#D3D3D3";
			        
			        document.getElementById("accountNoID").disabled = true; 
 			        document.getElementById("accountNoID").value = "";
 			        document.getElementById("accountNoID").style.backgroundColor = "#D3D3D3";
 			        
 			        document.getElementById("agencyCodeID").disabled = true; 
			        document.getElementById("agencyCodeID").value = "";
			        document.getElementById("agencyCodeID").style.backgroundColor = "#D3D3D3";
			      
 			     /*    document.getElementById("projectNameID").disabled = true; 
			        document.getElementById("projectNameID").value = "";
			        document.getElementById("projectNameID").style.backgroundColor = "#D3D3D3"; */
			        
 			   }else{
 			      	 document.getElementById("ifscCodeID").disabled=false; 
 			      	 document.getElementById("ifscCodeID").style.backgroundColor = "white";
 			      	 
 			       	 document.getElementById("bankNameID").disabled=false; 
			      	 document.getElementById("bankNameID").style.backgroundColor = "white";
			      	 
			      	 document.getElementById("accountNoID").disabled=false; 
			      	 document.getElementById("accountNoID").style.backgroundColor = "white";
			      	 
			      	 document.getElementById("agencyCodeID").disabled=false; 
			      	 document.getElementById("agencyCodeID").style.backgroundColor = "white";
			      	 
			      /* 	document.getElementById("projectNameID").disabled=false; 
			      	 document.getElementById("projectNameID").style.backgroundColor = "white"; */
			      	 
 			      }
 	}     
 	
 	
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function calSuportCost(){
	
	  var skill= document.getElementById("skillGapAssessmentID").value;
	  var iec= document.getElementById("iECID").value;
	  var alumni= document.getElementById("alumniSupportID").value;
	  var capBuild= document.getElementById("capacityBuildingID").value;
	  var monitorEvc= document.getElementById("monitoringEvaluationID").value;
	  var staffLevel= document.getElementById("staffBlockLevelBelowID").value;
	  var jobMela= document.getElementById("jobMelaID").value;
	  var migSupp= document.getElementById("migrationSupportCentreID").value;
	  
	  if(skill==null || skill==''){
		  skill="0";
	  }
	  if(iec==null || iec==''){
		  iec="0";
	  }
	  if(alumni==null || alumni==''){
		  alumni="0";
	  }
	  if(capBuild==null || capBuild==''){
		  capBuild="0";
	  }
	  if(monitorEvc==null || monitorEvc==''){
		  monitorEvc="0";
	  }
	  if(staffLevel==null || staffLevel==''){
		  staffLevel="0";
	  }
	  if(jobMela==null || jobMela==''){
		  jobMela="0";
	  }
	  if(migSupp==null || migSupp==''){
		  migSupp="0";
	  }
	  
	  var sum=parseInt(skill) + parseInt(iec) + parseInt(alumni) + parseInt(capBuild) + parseInt(monitorEvc) + parseInt(staffLevel) + parseInt(jobMela) + parseInt(migSupp);
	  document.getElementById("sumCatId").value=sum;
	  changeTextToBack(document.getElementById("sumCatId"),"maskedAmountsumCatId");
 }
 


function changeTextToBack(element,x){
	
	var getInput = $(element).val();
	$("#"+x).autoNumeric('set', getInput);
	
}


</script>

<html>
<body>
	<html:form action="login/stateSanctionAction">



		<table width="100%" class="pageHeaderTable">
			<tr>
				<td align="center" width="100%" class="pageHeader">STATE ACTION PLAN DETAILS</td>
			</tr>
		</table>

		<c:set var="size" scope="request" value="${size}" />
		<c:choose>
			<c:when test="${size <= 0}">
				<table width="100%">
					<tr>
						<td>
							<table width="100%" align="center" class="inputTBL">
								<tr>
									<th>Action Plan Start Year <span class="text-error">*</span></th>
									<td><span class="text-error"> <html:select
												property="targetStartedYear" styleId="targetStartedYearId"
												onchange="javascript:changeTargetEndYear();hidebodercolor(this);"
												value="${sanctiondetails.targetStartedYear}">
												<html:option value="">Select</html:option>
													<logic:present name="year">
														<logic:iterate id="year" name="year">
														<html:option value="${year}">${year}</html:option>
																			</logic:iterate>
																			</logic:present>
											</html:select>
									</span></td>
								</tr>

								<tr>
									<th>Action Plan End Year <span class="text-error">*</span></th>
									<td><span class="text-error"> <html:select
												property="targetEndYear" styleId="targetEndYearId"
												onchange="hidebodercolor(this);"
												value="${sanctiondetails.targetEndYear}">
												<html:option value="${sanctiondetails.targetEndYear}">${sanctiondetails.targetEndYear}</html:option>
											</html:select></span></td>
									<%-- <tr><td> <input type="hidden"  id="endId" value="${sanctiondetails.targetEndYear}"></td></tr> --%>
								</tr>

								<tr>
									<th>Sanctioned/ Allocated Training Target <span
										class="text-error"></span></th>
									<td><span class="text-error"><html:text size="10"
												maxlength="10" styleClass="amount"
												property="sanctionedTrainingTarget"
												value="${sanctiondetails.sanctionedTrainingTarget}"
												onchange="hidebodercolor(this);"
												styleId="sanctionedTrainingTargetId"
												onkeypress="return isNumberKey(event)" /></span></td>
								</tr>



								<tr id="apShow">
									<th> Funds Sanctioned <span class="text-error">(Rs.) </span></th>
									<td><span class="text-error"><html:text size="10"
												maxlength="10" styleClass="amount" property="fundSanctioned"
												styleId="fundSanctionedId"
												value="${sanctiondetails.fundSanctioned}"
												onchange="hidebodercolor(this);" /></span></td>
								</tr>

								<tr id="apShow2">
									<th>Date of Sanction <span class="text-error"></span></th>
									<td><span class="text-error nndate"><html:text
												readonly="true" property="sanctionDate"
												styleId="sanctionDateID"
												value="${sanctiondetails.sanctionDate}"
												onchange="hidebodercolor(this);" /></span></td>
								</tr>
								<!-- new added feilds... -->
								
						<tr>
							<th> Skill Gap Assessment <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="skillGapAssessment"	styleId="skillGapAssessmentID"  
									value="${sanctiondetails.skillGapAssessment}" styleClass="amount"	onblur="calSuportCost();" onchange="hidebodercolor(this);" /></span></td>
						</tr>	
								
						<tr>
							<th>Information, Education and Communication (IEC) <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	property="iEC"	styleId="iECID"  
									value="${sanctiondetails.iEC}"	onblur="calSuportCost();" styleClass="amount" onchange="hidebodercolor(this);" /></span></td>
						</tr>				
						
						<tr>
							<th>Alumni Support <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	property="alumniSupport"	styleId="alumniSupportID"  
									value="${sanctiondetails.alumniSupport}" onblur="calSuportCost();" styleClass="amount"	onchange="hidebodercolor(this);" /></span></td>
						</tr>			
								
						<tr>
							<th>Capacity Building <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="capacityBuilding"	styleId="capacityBuildingID"  
									value="${sanctiondetails.capacityBuilding}"	onblur="calSuportCost();" styleClass="amount" onchange="hidebodercolor(this);" /></span></td>
						</tr>		
								
						<tr>
							<th>Monitoring and Evaluation <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="monitoringEvaluation"	styleId="monitoringEvaluationID"  
									value="${sanctiondetails.monitoringEvaluation}" onblur="calSuportCost();" styleClass="amount"	onchange="hidebodercolor(this);" /></span></td>
						</tr>			
								
						<tr>
							<th>Staff Block Level and below <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="staffBlockLevelBelow"	styleId="staffBlockLevelBelowID"  
									value="${sanctiondetails.staffBlockLevelBelow}"	 onblur="calSuportCost();" styleClass="amount" onchange="hidebodercolor(this);" /></span></td>
						</tr>		
								
						<tr>
							<th>Job Mela <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="jobMela"	styleId="jobMelaID"  
									value="${sanctiondetails.jobMela}" onblur="calSuportCost();" styleClass="amount"	onchange="hidebodercolor(this);" /></span></td>
						</tr>	
						
						<tr>
							<th>Migration Support Centre <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	property="migrationSupportCentre"	styleId="migrationSupportCentreID"  
									value="${sanctiondetails.migrationSupportCentre}" onblur="calSuportCost();" styleClass="amount"	onchange="hidebodercolor(this);" /></span></td>
						</tr>	
						
						<tr>
							<th>Total Support Cost <span class="text-error"></span></th>
							<td ><html:text  property="supportCost" readonly="true" styleId="sumCatId" styleClass="amount" value="${sanctiondetails.supportCost}"/><span class="text-error"></span></td>
						</tr>
								
						<tr>
							<th>Whether register with PFMS? <span class="text-error"></span></th>
							 <td><span class="text-error">
                              <html:select property="pfms" styleId="pfmsID"  onchange="hidebodercolor(this);changePfms();" value="${sanctiondetails.pfms}">
							    <html:option value="">Select</html:option>
							    <html:option value="Yes">Yes</html:option>
						    	<html:option value="No">No</html:option>
						    	</html:select></span>
		                	</td></tr>		
						
						<tr>
							<th>Agency Code <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="agencyCode"	styleId="agencyCodeID"  
									value="${sanctiondetails.agencyCode}" onchange="hidebodercolor(this);" /></span></td>
						</tr>		
						
						<tr>
							<th>Account Number<span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="accountNo"	styleId="accountNoID"  
									value="${sanctiondetails.accountNo}" 	onchange="hidebodercolor(this);" /></span></td>
						</tr>		
						
						<tr>
							<th>Bank Name <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="bankName"	styleId="bankNameID"  
									value="${sanctiondetails.bankName}" 	onchange="hidebodercolor(this);" /></span></td>
						</tr>		
						
						<tr>
							<th>IFSC Code <span class="text-error"></span></th>
						<td><span class="text-error"><html:text	 property="ifscCode"	styleId="ifscCodeID"  
									value="${sanctiondetails.ifscCode}" 	onchange="hidebodercolor(this);" /></span></td>
						</tr>		
						<%-- 
						<tr>
							<th>Project Name <span class="text-error"></span></th>
							<td><span class="text-error"><html:text	 property="projectName"	styleId="projectNameID"  
									value="${sanctiondetails.projectName}" 	onchange="hidebodercolor(this);" /></span></td>
						</tr>			 --%>
							<!-- ends here... -->	
								
								<tr>
									<td><html:hidden property="id" value="${id}"
											styleId="idUU" /></td>
								</tr>

							</table>

							<div align="center">
								<input name="Button" id="button" type="button"
									class="button checkPermissionForFormForInsert"
									value="<bean:message  key="button.save"/>" onclick="save()" />
								<html:button styleId="button" property=""
									styleClass="button checkPermissionForFormForInsert"
									onclick="closePage(tokenParameter,tokenValue)">
									<bean:message key="button.close" />
								</html:button>
							</div>

						</td>
					</tr>
				</table>

			</c:when>
			<c:when test="${size > 0}">
				<div style="width:100%; overflow-x: scroll;">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>S.No</th>
							<th>State Name</th>
							<th>Target Started Year</th>
							<th>Target End Year</th>
							<th>Sanctioned Training Target</th>
							<th>Fund Sanctioned</th>
							<th>Date of Sanction</th>
							
							<th>Skill Gap Assessment</th>
							<th>IEC</th>
							<th>Alumni Support</th>
							<th>Capacity Building</th>
							<th>Monitoring and Evaluation</th>
							<th>Staff Block Level and below </th>
							<th>Job Mela</th>
							<th>Migration Support Centre</th>
							<th>Total Support Cost</th>
							<th>Whether register with PFMS?</th>       
							<th>Agency Code</th>   
							<th>Account Number</th>
							<th>Bank Name</th>       
							<th>IFSC Code</th>    
						</tr>
					</thead>
					<tbody>
						<logic:present name="statesanctionList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="statesanctionList" name="statesanctionList">
								<tr>
									<td>${indexCount}</td>
									<td>${statesanctionList.state.stateName}</td>
									<td>${statesanctionList.targetStartedYear}</td>
									<td>${statesanctionList.targetEndYear}</td>
									<td class="TdMaskedAmount" data-d-group="2s" data-v-max="99999999999999" data-v-min="0" data-a-pad="false">${statesanctionList.sanctionedTrainingTarget}</td>
									<td class="TdMaskedAmount" data-d-group="2s" data-v-max="99999999999999" data-v-min="0" data-a-pad="false">${statesanctionList.fundSanctioned}</td>
									<td>${statesanctionList.sanctionDate}</td>
									<td>${statesanctionList.skillGapAssessment}</td>
									<td>${statesanctionList.iEC}</td>
									<td>${statesanctionList.alumniSupport}</td>
									<td>${statesanctionList.capacityBuilding}</td>
									<td>${statesanctionList.monitoringEvaluation}</td>
									<td>${statesanctionList.staffBlockLevelBelow}</td>
									<td>${statesanctionList.jobMela}</td>
									<td>${statesanctionList.migrationSupportCentre}</td>
									<td>${statesanctionList.supportCost}</td>
									<td>${statesanctionList.pfms}</td>
									<td>${statesanctionList.agencyCode}</td>
									<td>${statesanctionList.accountNo}</td>
									<td>${statesanctionList.bankName}</td>
									<td>${statesanctionList.ifscCode}</td>
								</tr>
								<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
					</tbody>
					<tfoot>
						<tr>
							<th>S.No</th>
							<th>State Name</th>
							<th>Target Started Year</th>
							<th>Target End Year</th>
							<th>Sanctioned Training Target</th>
							<th>Fund Sanctioned</th>
							<th>Sanction Date</th>
                                                         <!-- new added feilds -->
                            <th>Skill Gap Assessment</th>
							<th>IEC</th>
							<th>Alumni Support</th>
							<th>Capacity Building</th>
							<th>Monitoring and Evaluation</th>
							<th>Staff Block Level and below </th>
							<th>Job Mela</th>
							<th>Migration Support Centre</th>
							<th>Total Support Cost</th>
							<th>Whether register with PFMS?</th>       
							<th>Agency Code</th>   
							<th>Account Number</th>
							<th>Bank Name</th>       
							<th>IFSC Code</th>                             
                          
                                                            <!-- ends feilds -->
						</tr>


					</tfoot>
				</table>
			</div>




			</c:when>
			<c:otherwise>

			</c:otherwise>

		</c:choose>


	</html:form>
</body>
<!--Main form section ends here-->