<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function save(){
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
	document.forms[0].action = "registrationAction.do?methodName=save" + "&" + tokenParameter + "=" + tokenValue;;
	document.forms[0].submit();
	}
}

function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
		window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
}

function createPdf(){
	document.forms[0].action = "registrationAction.do?methodName=createPdf&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}
</script>

<html:form action="registrationAction" >
	<!-- Rahul -->
	<!-- <h3>registration.jsp</h3> -->
	<!-- To display the file name -->
	<%-- <h3><%= pageContext.getPage().getClass().getName().substring(pageContext.getPage().getClass().getName().lastIndexOf('.')+1).replace('_','.') %></h3> --%>


<div class="outerTBL">
<table width="100%">
<tr>
	<td class="pageHeader" align="center">Applicant Organisation <small>Registration Form</small></td>
</tr>
<tr>
<td align="center">
<logic:notPresent name="piaDetail">
	<label class="label-warning">Registration not done. Please contact to site administrator.</label>
</logic:notPresent>

<logic:present name="piaDetail">
	<label class="label-success"> registration submitted to the MoRD. Wait for the verification.</label>
<table width="100%" class="inputTBL">
<tr>
	<td width="30%"><label class="label-important"> Temporary Reference Number</label></td>
 	<td><h3><span class="text-primary"><bean:write name="piaDetail" property="piaCode"/></span></h3></td>
</tr>
<tr>
	<td colspan="2" style="padding-top: 7px;"><label class="label-inverse">Category of Applicant</label></td></tr>
<tr>
	<td colspan="2"><span class="text-inverse"><bean:write name="piaDetail" property="categoryName"/></span></td>
</tr>
<tr>
	<td colspan="2" style="padding-top: 7px;"><label class="label-inverse">Type of business/activity of Applicant Organisation</label></td>
</tr>
<c:set var="activity" value="${fn:split(piaDetail.activityName,',')}" />
<logic:iterate id="act" name="activity" indexId="index">
<tr><td colspan="2">
&emsp;${index + 1}.&nbsp;${act}
</td>
</tr>
</logic:iterate>

</table>
					
<table style="padding-top: 7px;" width="100%" class="inputTBL">
<tr>
	<td style="padding-top: 7px;"><label class="label-inverse">Applicant Organisation Name</label></td>
	<td><bean:write name="piaDetail" property="piaName"/></td>
</tr>
<tr>
	<td style="padding-top: 7px;"><label class="label-inverse">NITI Aayog Alloted Unique Id: </label></td>
	<td><bean:write name="piaDetail" property="enrolmentNumber"/></td>
</tr>
<tr>
	<td colspan="4" style="padding-top: 7px;"><label class="label-inverse">Address and Contact Detail</label></td>
</tr>
<tr>
	<th>Address</th>
	<td colspan="3"><bean:write name="piaDetail" property="address"/></td>
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
	<th width="20%">Email:</th><td><bean:write name="piaDetail" property="email"/></td>
	<th width="20%">Website: </th><td><bean:write name="piaDetail" property="website"/></td>
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
	<td><bean:write name="piaDetail" property="registrationNumber"/></td>
	<th>Date of registration:</th>
	<td><bean:write name="piaDetail" property="dateOfRegistration"/></td>
</tr>
<tr>
	<th>PAN:</th>
	<td><bean:write name="piaDetail" property="panNo"/></td>
	<th>TAN:</th>
	<td><bean:write name="piaDetail" property="tanNo"/></td>
</tr>
</table>
				
<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="8"><label class="label-inverse">Details of the Owners/ Directors </label></td></tr>	
	<tr>
		<th>Name</th>
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
		<th>Designation</th>
		</c:if>
		<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
		<th>Liability</th>
		</c:if>
		<th>Contact</th>
		<th>Email</th>
		<th>PAN No.</th>
		<th>Aadhaar No./<br/>Voter ID card No.</th>
		<th>Passport No./<br/>Driving Licence No.</th>		
	</tr>
	<logic:present name="piaMemberList">
	<logic:iterate id="member" name="piaMemberList">
	<c:if test="${(member.authorizedPerson == null) && (member.nriStatus == null)}">
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

<logic:present name="piaMemberList">
<logic:iterate id="member" name="piaMemberList">
<logic:equal value="yes" name="member" property="authorizedPerson">
<table width="100%"  align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="9"><label class="label-inverse">Authorized Person Details</label></td></tr>	
	<tr>
		<th width="20%">Name<span class="text-error">*</span></th>
		<td><bean:write name="member" property="memberName"/></td>
		<th width="20%">S/o, D/o, W/o<span class="text-error">*</span></th>
		<td><bean:write name="member" property="relativeName"/></td>
	</tr>
	<tr>
		<th>Residence Address<span class="text-error">*</span></th>
		<td><bean:write name="member" property="address"/></td>
		<th>Age<span class="text-error">*</span></th>
		<td><bean:write name="member" property="age"/></td>
	</tr>
	<tr>
		<th>Designation<span class="text-error">*</span></th>
		<td><bean:write name="member" property="designation"/></td>
		<th>Occupation<span class="text-error">*</span></th>
		<td><bean:write name="member" property="occupation"/></td>
	<tr>
		<th>Contact<span class="text-error">*</span></th>
		<td><bean:write name="member" property="contact"/></td>
		<th>Email<span class="text-error">*</span></th>
		<td><bean:write name="member" property="email"/></td>
	</tr>
	<tr>
		<th>Pan No<span class="text-error">*</span></th>
		<td><bean:write name="member" property="pan"/></td>
		<th>Aadhaar No./<br/>Voter-ID card No.<span class="text-error">*</span></th>
		<td><bean:write name="member" property="aadharVoterNo"/></td>
	</tr>	
	<tr>	
		<th>State<span class="text-error">*</span></th>
		<td><bean:write name="member" property="stateName"/></td>			
        <th>Passport No./<br/>Driving Licence No.<span class="text-error">*</span></th>
		<td><bean:write name="member" property="passportDrivingNo"/></td>
	</tr>	
	<tr>			
        <th>Post Office <span class="text-error">*</span></th>
		<td><bean:write name="member" property="postOffice"/></td>
		
		<th>Police Station<span class="text-error">*</span></th>
		<td><bean:write name="member" property="policeStation"/></td>		
	</tr>
		
</table>
</logic:equal>
</logic:iterate>
</logic:present>

<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">
	<tr>
		<td colspan="4"><label class="label-inverse">Additional Details</label></td>
	</tr>
	<tr><th width="5%">S.No.</th>
		<th width="55%">Applicant Organisation</th>
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
</logic:present>
<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">
<tr>
	<td align="center" style="padding-top: 14px;" >
		<logic:present name="piaMemberList">
		<logic:iterate id="member" name="piaMemberList">
		<logic:equal value="yes" name="member" property="authorizedPerson">
		<table width="97%" class="inputTBL">
		<tr>
			<td colspan="2" style="text-align: justify;">I, <span class="text-info"><strong>${member.memberName}</strong>
			</span> S/o/D/o/W/o <span class="text-info"><strong>${member.relativeName}</strong></span> 
			age <span class="text-info"><strong>${member.age}</strong></span> occupation <span class="text-info"><strong>${member.occupation}</strong></span>
			residence address <span class="text-info"><strong>${member.address}</strong></span> post office <span class="text-info"><strong>${member.postOffice}</strong></span>
			police station <span class="text-info"><strong>${member.policeStation}</strong></span> state <span class="text-info"><strong>${member.stateName}</strong></span>
			am the authorized person to file for registration and certify the information
			furnished above is complete and correct in all respects. In case any of the information in this registration application is found to be
			false or incorrect, then the registration may be cancelled and I may be liable for any action by the Government.
			</td>
		</tr>
		<tr >
			<td style="padding-top: 7px;" width="40%">Place: </td>
			<td>Date: <span class="text-info"><strong>${piaDetail.modifyOn}</strong></span></td>
		</tr>
		<tr >
		<td colspan="2" style="padding-top: 7px; text-align: justify;">1. Application received on: <span class="text-info"><strong>${piaDetail.modifyOn}</strong></span>
		<br/>
		2. Temporary reference No. <span class="text-info"><strong>${piaDetail.piaCode}</strong></span> (only for purpose of reference and correspondence with MoRD. This temporary
		registration number shall be valid till permanent number is given by the MoRD)
		</table>
		</logic:equal>
		</logic:iterate></logic:present>
	</td>
</tr>
</table>
</td>
</tr></table>
<div align="center" style="padding-top: 15px; padding-bottom: 15px;"> 
	<html:button styleClass="inverseBtn" property="" onclick="createPdf();" value="Download the Applicant Organisation registration details entered in pdf format" />
	<html:button styleClass="defaultBtn" property="" onclick="closePage();" value="Exit" />
</div>
</div>
</html:form>


