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
	document.forms[0].action = "verifyPiaAction.do?methodName=getPiaDetail&page=rejectL1&"+ tokenParameter + "=" + tokenValue;
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


function RejectIncompletePIA(){	
		var status = window.confirm("Do you want to submit.");
		if(status){
			document.forms[0].action = "verifyPiaAction.do?methodName=rejectIncompletePIA&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();	
		}
}

function showRemark(){
	if (document.getElementById("piaApplicationStatus").value == "R" ){
		document.getElementById("remarkRow").style.display = "";		
	} else{
		document.getElementById("remarkRow").style.display = 'none';
	}
}

function validate(){
	
	var piaApplicationStatus = new LiveValidation(document.forms[0].piaApplicationStatus,{onlyOnSubmit:true});
	piaApplicationStatus.add( Validate.Presence );
	var areAllValid = LiveValidation.massValidate( [ piaApplicationStatus ] );
	if (document.getElementById("piaApplicationStatus").value == "R" ){
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
	document.forms[0].action = "verifyPiaAction.do?methodName=showPage" + "&" + tokenParameter + "=" + tokenValue;;
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

<table width="100%" align="center" style="padding-down: 7px;" class="piaLogTbl">
	<tr>
		<th colspan="2" align="center" class="pageHeader">Incomplete Applicant Organisation Rejection</th>
	</tr>
	<tr>
	<logic:present name="PIAStatusChanged"> 
 <div style="width: 70%; padding-left: 75px;border: solid #ccc 1px; -moz-border-radius: 6px; -webkit-border-radius: 6px; border-radius: 6px; -webkit-box-shadow: 0 1px 1px #ccc; -moz-box-shadow: 0 1px 1px #ccc; box-shadow: 0 1px 1px #ccc; margin: 10px 50px 5px 50px; padding: 15px 10px 15px 50px; background-repeat: no-repeat;  background-position: 10px center; color: #B22222; background-color: #9ACD32; text-transform: uppercase; background-image: url(../images/tick.png);size: 40px;">${PIAStatusChanged}
</div>
 </logic:present>
	<logic:empty name="piaList">
		<td><label class="label-info">NO INCOMPLETE APPLICANT ORGANISATION FOR  REJECTION IS FOUND IN OUR DATA BASE</label></td>
	</logic:empty>
	<logic:notEmpty name="piaList">
	<th width="50%"><label class="label-info">Applicant Organisation List for Rejection</label></th>
	<td align="center">
	 <b><label class="label-info"><font color="#fff">SEARCH</font></label> </b> <input type="text" id="realtxt" onkeyup="javascript:searchSel();" onkeydown="hideDetail();"/><br/>
		<html:select property="piaCode" name="verifyPiaForm" styleId="piaCode" onchange="hideDetail();" >
			<html:option value="">Select</html:option>
			<logic:present name="piaList">
				<logic:iterate id="pia" name="piaList">
					<html:option value="${pia.piacode}" >${pia.piacode} - ${pia.pianame}</html:option>
				</logic:iterate>
			</logic:present>
		</html:select>
	</td>
	<tr>
		<td colspan="2" align="center">
 	<html:button  styleClass="mybtn" property="" onclick="showData();">Show</html:button>
 	</td>     
	</tr>
	</logic:notEmpty>
	</tr>
</table>

<div id="detail">
<logic:present name="piaDetail" >

<!-- ---------- NEW REPORT PART START ------------------------ -->
<div id="detail">
<table width="100%" align="center" style="padding-down: 7px;" class="bordered">
	<tr>
		<th align="center" class="pageHeader">
		<small><c:if test="${piaDetail.piaStatus != 'V'}" >
			Applicant Organisation Temporary Reference Number:&nbsp;
 			    <bean:write name="piaDetail" property="piaCode" /><br/>
  		</c:if>
	  	<c:if test="${piaDetail.piaStatus == 'V'}" >
	 	    	Applicant Organisation Permanent Number:&nbsp; 
	 		    <bean:write name="piaDetail" property="piaRegistrationNo" /><br/>
	  	</c:if></small>
	  	</th><th width="30%">
		<small>
		Download Applicant Organisation Registration Details:<br/>
		<a href="javascript:createPdf()" title="Download as PDF"><img alt="PDF" src="images/pdf-icon.png" width="20px;" height="20px;"></a>
		</small>
	 </th>
	</tr>
</table>
<table width="100%" align="center" style="padding-down: 7px;" class="bordered">	  	
	  	<tr><th>
		<bean:write name="piaDetail" property="piaName"/>
		<br/>(<c:if test="${piaDetail.piaStatus == 'P'}" ><span class="text-info">Pending</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'C'}" ><span class="text-primary">Submitted</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'S'}" ><span class="text-primary">Submitted</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'V'}" ><span class="text-success">Verified</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'R'}" ><span class="text-error">Rejected</span></c:if>)
		
		<br/><small>OfficePhoto:&nbsp;<a href="javascript:showFiles('${piaDetail.officePhotoFileName}');">View</a></small>
		<br/><small>Web Site:&nbsp;<a style="text-transform: lowercase" href="http://${piaDetail.website}" target="_blank">
		<bean:write name="piaDetail" property="website"/></a></small><br/>						
	  	</th>	  	
	</tr>
	</table>

<table width="100%" align="center" class="bordered">
	<tr>
		<th>Address</th>
		<th>Contact Detail</th>
	</tr>
	<tr>
	<td>
		<bean:write name="piaDetail" property="address"/><br/>	  		  	
	  	<c:if test="${piaDetail.blockName!=null}" >
	  	<strong>Block</strong>:&nbsp;<bean:write name="piaDetail" property="blockName"/><br/>
	  	</c:if>
	  	<c:if test="${piaDetail.districtName!=null}" >
	  	<strong>District</strong>:&nbsp;<bean:write name="piaDetail" property="districtName"/><br/>
	  	</c:if>
	  	<strong>State</strong>:&nbsp;<bean:write name="piaDetail" property="stateName"/><br/>
	  	<strong>Pin</strong>:&nbsp;<bean:write name="piaDetail" property="pin"/>
		</td>
		
		<td>
		    <strong>Office Phone</strong>:&nbsp;<bean:write name="piaDetail" property="officePhone"/><br/>
			<strong>Office Fax No</strong>:&nbsp;<bean:write name="piaDetail" property="officeFax"/><br/>
			<strong>Email</strong>:&nbsp;<bean:write name="piaDetail" property="email"/><br/>		
		</td>
	</tr>
</table>


<table width="100%" align="center" style="padding-down: 7px;" class="bordered">	 
<tr>
	<th>Category of Applicant</th>
	<td><span class="text-inverse"><bean:write name="piaDetail" property="categoryName"/></span></td>
</tr>
<tr>
	<th>Type of business/Activity of Applicant Organisation</th>
	<td><c:set var="activity" value="${fn:split(piaDetail.activityName,',')}" />
	<logic:iterate id="act" name="activity" indexId="index">
	  ${index + 1})&emsp;${act}<br/>
	</logic:iterate>
	</td>
</tr>
</table>
					
<table width="100%" class="bordered">
<tr><th colspan="2">Registration Detail</th></tr>
<tr>
<td>	
	<strong>State where registered</strong>:&nbsp;<bean:write name="piaDetail" property="registrationStateName"/><br/>
         <c:if test="${piaDetail.registrationNumber != null}" >
			<strong>Registration number</strong>:&nbsp;<bean:write name="piaDetail" property="registrationNumber"/>&emsp;
			<a href="javascript:showFiles('${piaDetail.regFileName }');" >View</a><br/>
         </c:if>
	<strong>Date of registration</strong>:&nbsp;<bean:write name="piaDetail" property="dateOfRegistration"/>
</td>
<td>
	<strong>PAN</strong>:&nbsp;<bean:write name="piaDetail" property="panNo"/>&emsp;
	<a href="javascript:showFiles('${piaDetail.panFileName }');" >View</a><br/>
	<strong>TAN</strong>:&nbsp;<bean:write name="piaDetail" property="tanNo"/>&emsp;
	<a href="javascript:showFiles('${piaDetail.tanFileName}');" >View</a></td>
</tr>
</table>

<table width="100%" align="center" class="bordered">	
	<tr><th colspan="3">Details of the Owners/ Directors </th></tr>	
	<tr>
		<td><strong>Name</strong></td>
		<td><strong>Contacts</strong></td>
		<td><strong>Other Details</strong></td>
		
	</tr>
	<logic:present name="piaMemberList">
	<logic:iterate id="member" name="piaMemberList">
	<c:if test="${(member.nriStatus != 'yes') && (member.authorizedPerson != 'yes')}">
	<tr>
		<td><bean:write name="member" property="memberName"/><br/>
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
	    	<strong>Designation</strong>:&nbsp;<bean:write name="member" property="designation"/>
	
	    </c:if>
	    <c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
	    	<strong>Liability</strong>:&nbsp;<bean:write name="member" property="liability"/>
	    </c:if>
	    </td>
		<td><strong>Contact</strong>:&nbsp;<bean:write name="member" property="contact"/><br/>
			<strong>Email-Id</strong>:&nbsp;<bean:write name="member" property="email"/></td>
		<td><strong>PAN</strong>:&nbsp;<bean:write name="member" property="pan"/><br/>
			<strong>Aadhaar No. <br>/ Voter ID card No.</strong>:&nbsp;<bean:write name="member" property="aadharVoterNo"/><br/>
			<strong>Passport No.<br>/ Driving Licence No.</strong>:&nbsp;<bean:write name="member" property="passportDrivingNo"/></td>
 	</tr>	
	</c:if> 
	</logic:iterate>
	</logic:present>
</table>

<table width="100%" align="center" class="bordered" >	
	<tr><th colspan="3">Authorized Person Details</th></tr>
	<logic:present name="piaMemberList">
	<logic:iterate id="member" name="piaMemberList">
	<logic:equal value="yes" name="member" property="authorizedPerson">
		
	    <tr>
	    	<td colspan="2"><strong>Personal Detail</strong></td>
	    	<td><strong>Communication / Other Details</strong></td>
	   		<!-- <td><strong>Photo and CV</strong></td> -->
	    </tr>
	    <tr>
	       <td>		
		  <%--   Uploaded Photo:<a href="javascript:showFiles('${member.photoFileName}');">View</a><br/> --%>
		   
		    Uploaded CV:<a href="javascript:showFiles('${member.cvFileName}');">View</a><br/><br/>	
		     <img src="<%=request.getContextPath()%>/Downloader?imageName=${member.photoFileName}" width="100" height="80" >
		        
	       </td>
	    	<td><strong>Name</strong>:&nbsp;<bean:write name="member" property="memberName"/><br/>
			    <strong>S/o, D/o, W/o</strong>:&nbsp;<bean:write name="member" property="relativeName"/><br/>
			    <strong>Age</strong>:&nbsp;<bean:write name="member" property="age"/><br/>
			    <strong>Designation</strong>:&nbsp;<bean:write name="member" property="designation"/><br/>	
				<strong>Occupation</strong>:&nbsp;<bean:write name="member" property="occupation"/>
			</td>	
		    <td>
		    <strong>Residence Address</strong><br/><bean:write name="member" property="address"/><br/>				
			<strong>Contact</strong>:&nbsp;<bean:write name="member" property="contact"/><br/>	
			<strong>Email</strong>:&nbsp;<bean:write name="member" property="email"/><br/>
			<strong>Passport No./Driving Licence No</strong>:&nbsp;<bean:write name="member" property="passportDrivingNo"/><br/>
			<strong>Aadhaar No./Voter ID card No</strong>:&nbsp;<bean:write name="member" property="aadharVoterNo"/><br/>
			
			<strong>PAN No:</strong><bean:write name="member" property="pan"/>
			<%-- <strong>State:</strong><bean:write name="member" property="stateName"/> --%>
			</td>
			
	      </tr>
	
	</logic:equal>
	</logic:iterate>
	</logic:present>  
</table>		

			
<logic:equal value="1" name="piaDetail" property="categoryCode">				
<table width="100%"  align="center" class="bordered" >
	<tr><th>Details of Land and Building owned by the Educational Institution</th></tr>
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
<table width="100%" align="center" class="bordered">
	<tr>
		<th colspan="4">Additional Details</th>
	</tr>
	<tr><td width="5%"><strong>S.No.</strong></td>
		<td width="50%"></td>
		<td width="30%"><strong>Registration Number</strong></td>
		<td width="20%"><strong>Registration Date</strong></td>
	</tr>
	<tr><td>1</td>
		<td><strong>Details of registration under section 12A of Income Tax Act of 1956</strong></td>
		<td><bean:write name="piaDetail" property="regNoSection12A"/></td>
		<td><bean:write name="piaDetail" property="regDateSection12A"/></td>
	</tr>
	<tr><td>2</td>
		<td><strong>Details of registration under section 80G of Income Tax Act of 1956</strong></td>
		<td><bean:write name="piaDetail" property="regNoSection80G"/></td>
		<td><bean:write name="piaDetail" property="regDateSection80G"/></td>
		
	</tr>
	<tr><td>3</td>
		<td><strong>Details of registration under FCRA</strong></td>
		<td><bean:write name="piaDetail" property="regNoFCRA"/></td>
		<td><bean:write name="piaDetail" property="regDateFCRA"/></td>
	</tr>
</table>

<!-- ------------- 	NEW REPORT PART END	------------------------>
 
<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">
<tr>
<th width="30%">Applicant Organisation Application Status</th>
	<td>
		<html:select property="piaApplicationStatus" styleId="piaApplicationStatus" onchange="javascript:showRemark();">
			<html:option value="">Select</html:option>
			<html:option value="R">Reject Incomplete Applicant Organisation</html:option>		
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
	<html:button  styleClass="primaryBtn" property="" onclick="RejectIncompletePIA()">Submit</html:button>
	<html:button  styleClass="defaultBtn" property="" onclick="closePage()">Close</html:button>
</div>
</div>
</logic:present>
</div>
</html:form>