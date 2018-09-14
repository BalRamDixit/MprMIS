<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function showData() {
	document.forms[0].action = "verifyPiaAction.do?methodName=getPiaDetail&page=reject&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}
function showFiles(fileType) {
	document.forms[0].action = "verifyPiaAction.do?methodName=showFiles&file="+fileType+"&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

function createPdf(){
	document.forms[0].action = "verifyPiaAction.do?methodName=createPdf&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}


function reject(){
	if(validate()){
		if (document.getElementById("piaApplicationStatus").value == "PW") {
			var status = window.confirm("Do You want to Withdraw this Applicant Organisation");
		}
		else if (document.getElementById("piaApplicationStatus").value == "PD") {
			var status = window.confirm("Do You want to Debarred this Applicant Organisation");
		}
		if (document.getElementById("piaApplicationStatus").value == "PB") {
			var status = window.confirm("Do You want to BlackList this Applicant Organisation");
		}		
		if(status){
			document.forms[0].action = "verifyPiaAction.do?methodName=rejectPia&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();	
		}
	}
}

function showRemark(){
	if ((document.getElementById("piaApplicationStatus").value == "PW") 
			||(document.getElementById("piaApplicationStatus").value == "PD") 
			||(document.getElementById("piaApplicationStatus").value == "PB") ){
		document.getElementById("remarkRow").style.display = "";		
	} else{
		document.getElementById("remarkRow").style.display = 'none';
	}
}

function validate(){
	var piaApplicationStatus = new LiveValidation(document.forms[0].piaApplicationStatus,{onlyOnSubmit:true});
	piaApplicationStatus.add( Validate.Presence );
	var areAllValid = LiveValidation.massValidate( [ piaApplicationStatus ] );
	if ((document.getElementById("piaApplicationStatus").value == "PW") 
			||(document.getElementById("piaApplicationStatus").value == "PD") 
			||(document.getElementById("piaApplicationStatus").value == "PB") ){
		if(document.getElementById("remark").value == ""){
			areAllValid = false;
			document.getElementById("remarkError").innerHTML = "Remarks cannot be empty.";
		}		
	} 
	return areAllValid;
}

function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.forms[0].action = "verifyPiaAction.do?methodName=showRejectionPage" + "&" + tokenParameter + "=" + tokenValue;;
	document.forms[0].submit();
	}
}

document.getElementById('realtxt').onkeyup = searchSel;
function searchSel() 
    {
      var input = document.getElementById('realtxt').value.toLowerCase();
       
          len = input.length;
          output = document.getElementById('piaCode').options;
      for(var i=0; i<output.length; i++)
          if (output[i].text.toLowerCase().indexOf(input) != -1 ){
          output[i].selected = true;
              break;
          }
      if (input == '')
        output[0].selected = true;
    }

function hideDetail() {
	 document.getElementById("detail").style.display="none";
}
</script>
<html:form action="/login/verifyPiaAction"> 
<div   class="outerTBL">
<table width="100%" align="center" style="padding-down: 7px;" class="piaLogTbl">
	<tr>
		<th colspan="2" align="center" class="pageHeader">APPLICANT ORGANISATION REJECTION (WITHDRAWN/DEBARRED/BLACKLISTED) CASES</th>
	</tr>
	<tr>
	<logic:empty name="piaList">
		<td><label class="label-info">NO NEW REGISTRATION OF APPLICANT ORGANISATION IS FOUND FOR REJECTION IN OUR DATA BASE</label></td>
	</logic:empty>
	<logic:notEmpty name="piaList">
	<th width="50%"><label class="label-info">Applicant Organisation List for Rejection</label></th>
	<td align="center">
	<b><label class="label-info"><font color="#fff">SEARCH</font></label> </b> <input type="text" id="realtxt" onkeyup="javascript:searchSel();" onkeydown="hideDetail();"/><br/>
		<html:select property="piaCode" name="verifyPiaForm" styleId="piaCode" onchange="hideDetail();">
			<html:option value="">Select</html:option>
			<logic:present name="piaList">
				<logic:iterate id="pia" name="piaList">
					<html:option value="${pia.piaCode}" >${pia.piaCode} - ${pia.piaName}</html:option>
				</logic:iterate>
			</logic:present>
		</html:select>
	</td>
	</logic:notEmpty>
	</tr>
	<tr>
		<td colspan="2" align="center">
 	<html:button  styleClass="mybtn" property="" onclick="showData();">Show</html:button>
 	</td>     
	</tr>
	<tr>
	<logic:present name="PIAStatusChanged"> 
 <div style="width: 70%; padding-left: 75px;border: solid #ccc 1px; -moz-border-radius: 6px; -webkit-border-radius: 6px; border-radius: 6px; -webkit-box-shadow: 0 1px 1px #ccc; -moz-box-shadow: 0 1px 1px #ccc; box-shadow: 0 1px 1px #ccc; margin: 10px 50px 5px 50px; padding: 15px 10px 15px 50px; background-repeat: no-repeat;  background-position: 10px center; color: #B22222; background-color: #9ACD32; text-transform: uppercase; background-image: url(../images/tick.png);size: 40px;">${PIAStatusChanged}
</div>
 </logic:present>
	</tr>
</table>
<%-- <logic:notPresent name="piaDetail">
<label class="label-warning">NO NEW REGISTRATION OF APPLICANT ORGANISATION IS FOUND FOR VERIFICATION IN OUR DATA BASE</label>
</logic:notPresent> --%>
<div id="detail">
<logic:present name="piaDetail" >

<table width="100%" class="inputTBL">
<tr style="padding-top: 7px;">
	<td width="40%" >DOWNLOAD APPLICANT ORGANISATION DETAIL </td>
 	<td><a href="javascript:createPdf()" title="Download as PDF"><img alt="PDF" src="images/pdf-icon.png" width="20px;" height="20px;"></a></td>
</tr>

<tr style="padding-top: 7px;">
	<td width="40%" ><label class="label-important">Applicant Organisation Temporary Reference Number</label></td>
 	<td><h3><span class="text-primary"><bean:write name="piaDetail" property="piaCode"/></span></h3></td>
</tr>
<tr style="padding-top: 7px;">
	<td width="40%" ><label class="label-important">Applicant Organisation Permanent Reference Number</label></td>
 	<td><h3><span class="text-primary"><bean:write name="piaDetail" property="piaRegistrationNo"/></span></h3></td>
</tr>
<tr>
	<td colspan="2" style="padding-top: 7px;"><label class="label-inverse">Category of Applicant</label></td>
</tr>
<tr>
	<td colspan="2"><span class="text-inverse"><bean:write name="piaDetail" property="categoryName"/></span></td>
</tr>
<tr>
	<td colspan="2" style="padding-top: 7px;"><label class="label-inverse">Type of business/activity of Applicant Organisation</label></td>
</tr>
<tr>
	<c:set var="activity" value="${fn:split(piaDetail.activityName,',')}" />
	<logic:iterate id="act" name="activity" indexId="index">
	<tr><td colspan="2">
	 ${index + 1})&emsp;${act}
	<td colspan="2"> </td>
	</logic:iterate>
</tr>
</table>
					
<table style="padding-top: 7px;" width="100%" class="inputTBL">
<tr>						
	<th>Name</th>
	<td colspan="3"><bean:write name="piaDetail" property="piaName"/></td>
</tr>
<tr>
	<td colspan="4" style="padding-top: 7px;"><label class="label-inverse">Address and Contact Detail</label></td>
</tr>
<tr>
	<th>Address</th>
	<td colspan="3"><bean:write name="piaDetail" property="address"/></td>
</tr>
<tr>
	<th>Pin</th>
	<td colspan="3"><bean:write name="piaDetail" property="pin"/></td>
</tr>
<tr>
	<th>State</th>
	<td colspan="3"><bean:write name="piaDetail" property="stateName"/></td>	
</tr>	
<tr>
	<th>District</th>
	<td colspan="3"><bean:write name="piaDetail" property="districtName"/></td>
</tr>	
<tr>
	<th>Block</th>
	<td colspan="3"><bean:write name="piaDetail" property="blockName"/></td>	
</tr>					
<tr>
	<th width="20%">Office Phone:</th><td><bean:write name="piaDetail" property="officePhone"/></td>
	<th width="20%">Office Fax No:</th><td><bean:write name="piaDetail" property="officeFax"/></td>
</tr>
<tr>
	<th>Email:</th><td colspan="3"><bean:write name="piaDetail" property="email"/></td>
</tr>					
<tr>
	<th>Website: </th><td colspan="3"><bean:write name="piaDetail" property="website"/></td>
</tr>
<tr>	
	<th>OfficePhoto:</th>
	<td><a href="javascript:showFiles('${piaDetail.officePhotoFileName}');">View</a></td>
</tr>					
<tr>
	<td colspan="4" style="padding-top: 7px;"><label class="label-inverse">Registration Detail</label></td>	
</tr>				
<tr>
	<th>State where registered:</th>
	<td colspan="3"><bean:write name="piaDetail" property="registrationStateName"/></td>
</tr>
<tr>
	<th>Registration number:</th>
	<td id="regFile" style="display:none;"><bean:write name="piaDetail" property="regFileName"/></td>	
	<td><bean:write name="piaDetail" property="registrationNumber"/>&emsp;<a href="javascript:showFiles('${piaDetail.regFileName }');" >View</a></td>
	<th>Date of registration:</th>
	<td><bean:write name="piaDetail" property="dateOfRegistration"/></td>
</tr>
<tr>
	<th>PAN:</th>		
	<td id="panFile" style="display:none;"><bean:write name="piaDetail" property="panFileName"/></td>	
	<td><bean:write name="piaDetail" property="panNo"/>&emsp;<a href="javascript:showFiles('${piaDetail.panFileName }');" >View</a></td>
	<th>TAN:</th>
	<td id="tanFile" style="display:none;"><bean:write name="piaDetail" property="tanFileName"/></td>	
	<td><bean:write name="piaDetail" property="tanNo"/>&emsp;<a href="javascript:showFiles('${piaDetail.tanFileName}');" >View</a></td>
	<th>Unique Id:</th>	
	<td>
		<c:if test="${piaDetail.enrolmentNumber==null}">Not Available</c:if>
		<c:if test="${piaDetail.enrolmentNumber!=null}">${piaDetail.enrolmentNumber}</c:if>
		&emsp;
		<c:if test="${piaDetail.enrolmentNumber!=null}"><a href="javascript:showFiles('${piaDetail.enrolmentFileName}');" >View</a></c:if>
	</td>
</tr>
</table>
				
<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="8"><label class="label-inverse">Details of the Owners/ Directors </label></td></tr>	
		<tr>
		<th>Name</th>
		<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
			<th>Liability</th>
		</c:if>
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
			<th>Designation</th>
		</c:if>
		<th>Contact</th>
		<th>Email</th>
		<th>PAN No.</th>
		<th>Aadhaar No./<br/>Voter ID card No.</th>
		<th>Passport No./<br/>Driving Licence No.</th>
		</tr>
		<logic:present name="piaMemberList">
	    <logic:iterate id="member" name="piaMemberList">
	   <c:if test="${(member.nriStatus != 'yes') && (member.authorizedPerson != 'yes')}">
	    <tr>
		<td><bean:write name="member" property="memberName"/></td>
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
	    <td><bean:write name="member" property="designation"/></td>
	    </c:if>
	    <c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
	    <td><bean:write name="member" property="liability"/></td>
	    </c:if>
		<td><bean:write name="member" property="contact"/></td>
		<td><bean:write name="member" property="email"/></td>
		<td><bean:write name="member" property="pan"/></td>
		<td><bean:write name="member" property="aadharVoterNo"/></td>
		<td><bean:write name="member" property="passportDrivingNo"/></td>
		</tr>	
		 </c:if> 
		</logic:iterate>
	</logic:present>
</table>
<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">	
<table width="100%"  align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="7"><label class="label-inverse">In case a Partner in the Firm is a not an Indian Citizen, details of such Partner</label></td></tr>
	<tr>
		<th>Name</th>				
		<th>Country</th>
		<th>Passport No</th>				
		<th>Valid till</th>
		<th>Visa valid till</th>
		<th>Whether<br/>a valid<br/>work permit<br/> held in<br/>India </th>				
		<th>If yes,<br/>Work permit<br/>valid till</th>
		<th>Whether<br/>clearance from<br/>FRA, MHA<br/>obtained.</th>
	</tr>
	<logic:present name="piaMemberList">
	<logic:iterate id="member" name="piaMemberList">
	<logic:equal value="yes" name="member" property="nriStatus">
	<tr>
		<td><bean:write name="member" property="memberName"/></td>
		<td><bean:write name="member" property="country"/></td>
		<td><bean:write name="member" property="passportDrivingNo"/></td>
		<td><bean:write name="member" property="passportValidDate"/></td>
		<td><bean:write name="member" property="visaValidDate"/></td>
		<td><bean:write name="member" property="workPermit"/></td>
		<td><bean:write name="member" property="workingPermitDate"/></td>
		<td><bean:write name="member" property="clearanceFRAMHA"/></td>	
	</tr>
	</logic:equal>
	</logic:iterate>
	</logic:present>
</table>
</c:if>	
<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="8"><label class="label-inverse">Authorized Person Details</label></td></tr>	
	<logic:present name="piaMemberList">
	<logic:iterate id="member" name="piaMemberList">
	<logic:equal value="yes" name="member" property="authorizedPerson">
	<tr>
		<th>Name</th>
		<td><bean:write name="member" property="memberName"/></td>
		<th>S/o,D/o,W/o</th>
		<td><bean:write name="member" property="relativeName"/></td>
	</tr>
	<tr>	
		<th>Residence Address</th>
		<td><bean:write name="member" property="address"/></td>
		<th>Age</th>
		<td><bean:write name="member" property="age"/></td>
	</tr>
	<tr>	
		<th>Designation</th>
		<td><bean:write name="member" property="designation"/></td>	
		<th>Occupation</th>
		<td><bean:write name="member" property="occupation"/></td>
	</tr>
	<tr>	
		<th>Contact</th>
		<td><bean:write name="member" property="contact"/></td>	
		<th>Email</th>
		<td><bean:write name="member" property="email"/></td>
	</tr>
	<tr>	
		<th>PAN No.</th>
		<td><bean:write name="member" property="pan"/></td>
		<th>Aadhaar No./Voter ID card No.</th>
		<td><bean:write name="member" property="aadharVoterNo"/></td>
	</tr>
	<tr>	
		<th>Passport No./<br/>Driving Licence No.</th>	
		<td><bean:write name="member" property="passportDrivingNo"/></td>	
	</tr>
	<tr>	
		<th>State</th>
		<td><bean:write name="member" property="stateName"/></td>	
	</tr>
	<tr>	
		<th>Post Office</th>
		<td><bean:write name="member" property="postOffice"/></td>	
		<th>Police Station</th>
		<td><bean:write name="member" property="policeStation"/></td>	
	</tr>	
	<tr>	
	<th>UploadCV:</th>
	<td><a href="javascript:showFiles('${member.cvFileName}');">View</a></td>
	<th>UploadPhoto:</th>
	<td><a href="javascript:showFiles('${member.photoFileName}');">View</a></td>
   </tr> 
	</logic:equal>
	</logic:iterate>
	</logic:present>
</table>				

			
<logic:equal value="1" name="piaDetail" property="categoryCode">				
<table width="100%"  align="center" class="inputTBL" style="padding-top: 7px;">
	<tr><td><label class="label-inverse">Details of Land and Building owned by the Educational Institution</label></td></tr>
	<tr>
		<th>Address of Land / Building</th>
		<th>Whether Freehold / mortgaged</th>
	</tr>
	<tr>
		<td><bean:write name="piaDetail" property="addressLandBuilding"/></td>
		<td><bean:write name="piaDetail" property="freeholdMortgaged"/></td>
	</tr>		
</table>
</logic:equal>
<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">
	<tr>
		<td colspan="4"><label class="label-inverse">Additional Details</label></td>
	</tr>
	<tr><th width="5%">S.No.</th>
		<th width="55%"></th>
		<th width="20%">Registration Number</th>
		<th width="20%">Registration Date</th>
	</tr>
	<tr><th>1</th>
		<th>Details of registration under section 12A of Income Tax Act of 1956</th>
		<td><bean:write name="piaDetail" property="regNoSection12A"/></td>
		<td><bean:write name="piaDetail" property="regDateSection12A"/></td>
	</tr>
	<tr><th>2</th>
		<th>Details of registration under section 80G of Income Tax Act of 1956</th>
		<td><bean:write name="piaDetail" property="regNoSection80G"/></td>
		<td><bean:write name="piaDetail" property="regDateSection80G"/></td>
		
	</tr>
	<tr><th>3</th>
		<th>Details of registration under FCRA</th>
		<td><bean:write name="piaDetail" property="regNoFCRA"/></td>
		<td><bean:write name="piaDetail" property="regDateFCRA"/></td>
	</tr>
</table>

<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">
<tr>
<th width="30%">Applicant Organisation Application Status</th>
	<td>
		<html:select property="piaApplicationStatus" styleId="piaApplicationStatus" onchange="javascript:showRemark();">
			<html:option value="">Select</html:option>
			<html:option value="PW">PERMANENT REGISTRATION NUMBER WITHDRAWN AFTER REVIEW</html:option>
			<html:option value="PD">APPLICANT ORGANISATION DEBARRED</html:option>	
			<html:option value="PB">APPLICANT ORGANISATION BLACKLISTED</html:option>
		</html:select>
	</td>
</tr>
<tr id="remarkRow" style="display: none">
	<th width="30%">Remark</th>
	<td align="center">
		<html:textarea property="remark" styleId="remark" cols="40" rows="4"/> 
		<br/><span id="remarkError" class="text-error"></span>
	</td>
</tr>
</table>
<div align="center" style="padding-top: 15px; padding-bottom: 10px;"> 
	<html:button  styleClass="primaryBtn" property="" onclick="reject()">Submit</html:button>
	<html:button  styleClass="defaultBtn" property="" onclick="closePage()">Close</html:button>
</div>
</logic:present>
</div>
</div>
</html:form>