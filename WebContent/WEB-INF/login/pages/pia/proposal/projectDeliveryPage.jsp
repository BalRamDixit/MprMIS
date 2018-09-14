<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<script language="Javascript1.1" src="javaScript/common.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script type="text/javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;

function closePage(){	
	var status = window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
	 	document.forms[0].action="login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
	 	document.forms[0].submit();
	 }
} 

function save(){	
	var status=window.confirm('<bean:message key="msg.saveForm" />');
    if(status==true){
		document.forms[0].action="projectDeliveryAction.do?methodName=save&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
}	

</script>

<html:form action="login/projectDeliveryAction"> 

<table width="90%" align="Center">

<tr align="center">
<th> Registration Number : S20072013GH34521</th>
</tr>

<tr>
<th align="left">Project Delivery</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th> Activity</th>
<th> Organization Will do this Activity on its Own</th>
<th> Organization Will Invoke Another Agency for this Activity</th>
</tr>

<tr>
<th align="left">Social mobilization of candidates from the community level</th>
<td align="center"><html:radio property="candidateSocial" styleId="candidateSocial" value="yes"></html:radio> </td>
<td align="center"><html:radio property="candidateSocial" styleId="candidateSocial" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Screening and selection of candidates</th>
<td align="center"><html:radio property="candidateSelection" styleId="candidateSelection" value="yes"></html:radio> </td>
<td align="center"><html:radio property="candidateSelection" styleId="candidateSelection" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Hiring of trainers ( whether on company payroll or hired by external consultancy)</th>
<td align="center"><html:radio property="hiringTrainers" styleId="hiringTrainers" value="yes"></html:radio> </td>
<td align="center"><html:radio property="hiringTrainers" styleId="hiringTrainers" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Training of trainers</th>
<td align="center"><html:radio property="trainersTraining" styleId="trainersTraining" value="yes"></html:radio> </td>
<td align="center"><html:radio property="trainersTraining" styleId="trainersTraining" value="no"></html:radio> </td>
<tr>


<tr>
<th align="left">Infrastructure for training activities( whether organization will own the training centers or rent the premises)</th>
<td align="center"><html:radio property="trainingInfra" styleId="trainingInfra" value="yes"></html:radio> </td>
<td align="center"><html:radio property="trainingInfra" styleId="trainingInfra" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Training of trainees</th>
<td align="center"><html:radio property="traineesTraining" styleId="traineesTraining" value="yes"></html:radio> </td>
<td align="center"><html:radio property="traineesTraining" styleId="traineesTraining" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Assessment of trainees</th>
<td align="center"><html:radio property="traineesAssessment" styleId="traineesAssessment" value="yes"></html:radio> </td>
<td align="center"><html:radio property="traineesAssessment" styleId="traineesAssessment" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Certification of trainees</th>
<td align="center"><html:radio property="traineesCertification" styleId="traineesCertification" value="yes"></html:radio> </td>
<td align="center"><html:radio property="traineesCertification" styleId="traineesCertification" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Placement of trainees</th>
<td align="center"><html:radio property="traineesPlacement" styleId="traineesPlacement" value="yes"></html:radio> </td>
<td align="center"><html:radio property="traineesPlacement" styleId="traineesPlacement" value="no"></html:radio> </td>
<tr>

<tr>
<th align="left">Post Placement tracking and follow up</th>
<td align="center"><html:radio property="postTracking" styleId="postTracking" value="yes"></html:radio> </td>
<td align="center"><html:radio property="postTracking" styleId="postTracking" value="no"></html:radio> </td>
<tr>

</table>

<table align="center">
<tr>
<html:button  styleClass="button" property="" onclick="save()">Submit</html:button>
<html:button styleClass="button" property="" onclick="closePage();"><bean:message  key="button.close" /></html:button>
</tr>
</table>

</html:form>