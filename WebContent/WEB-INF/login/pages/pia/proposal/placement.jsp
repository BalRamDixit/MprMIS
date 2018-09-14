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
			document.forms[0].action="placementAction.do?methodName=save&"+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
	}
}	

</script>

<html:form action="login/placementAction"> 

<table width="90%" align="Center">

<tr align="right">
<th> Registration Number : S20072013GH34521</th>
</tr>

<tr> 
<th align="left"> PrePlacement Activities </th>
</tr>

<tr>
<th align="left">Particular</th>
<th align="left">Strategy</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
 <th align="left">%age of beneficiaries that will be captively placed</th>
   <td align="center"><html:text styleId="captivelyBeneficiaries" property="captivelyBeneficiaries" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">%age of beneficiaries that will be placed in organized sector</th>
   <td align="center"><html:text styleId="organizedBeneficiaries" property="organizedBeneficiaries" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">Involvement of other agencies in placement of beneficiaries and the role and responsibilities of the third party</th>
   <td align="center"><html:text styleId="involvementAgencies" property="involvementAgencies" maxlength="50" /></td>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th>Name</th>
<th>Registration No.</th>
<th>PAN No.</th>
<th>TAN No.</th>
<th>Web site</th>
<th>Contact Person Name</th>
</tr>

<tr>
<td align="center"><html:text styleId="empName" property="empName" size="10"/></td>
<td align="center"><html:text styleId="registrationNo" property="registrationNo" size="10"/></td>
<td align="center"><html:text styleId="panNo" property="panNo" size="10"/></td>
<td align="center"><html:text styleId="tanNo" property="tanNo" size="10"/></td>
<td align="center"><html:text styleId="website" property="website" size="10"/></td>
<td align="center"><html:text styleId="nameContactPerson" property="nameContactPerson" size="10"/></td>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th>Contact No.</th>
<th>Email Id</th>
<th>No. Of Trainee</th>
<th>Confirmation Attached</th>
</tr>

<tr>
<td align="center"><html:text styleId="contactNo" property="contactNo" size="10"/></td>
<td align="center"><html:text styleId="emailId" property="emailId" size="10"/></td>
<td align="center"><html:text styleId="noOfTrainee" property="noOfTrainee" size="10"/></td>
<td align="center"><html:radio value="Yes" styleId="confirmationAttached" property="confirmationAttached" /></td>
</tr>

<tr></tr> <tr> </tr> <tr></tr> 
 
<tr> 
<td align="center"><html:button  styleClass="button" property="" onclick="closeReport()">Add Row</html:button></td> 
</tr>


<tr> 
<th align="left">Post-Placement Support </th> 
</tr>

<tr>
<th align="left">Activity</th>
<th align="left">Strategy</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
 <th align="left">Use of/Establishment of facilitation centers to support beneficiaries</th>
   <td align="center"><html:text styleId="facilitationBeneficiaries" property="facilitationBeneficiaries" maxlength="50"  /></td>
</tr>
<tr>
 <th align="left">Arrangement for re-employment of beneficiaries</th>
   <td align="center"><html:text styleId="reemploymentBeneficiaries" property="reemploymentBeneficiaries" maxlength="50" /></td>
</tr>

</table>

<table align="center">
<tr>
<td  align="center" colspan="2">
<html:button styleClass="button" property="" onclick="save();" >Submit</html:button> 
<html:button styleClass="button" property="" onclick="closePage();"><bean:message  key="button.close" /></html:button>
</tr>
</table>

</html:form>