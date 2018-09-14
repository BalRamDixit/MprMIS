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

</script>


<html:form action="login/qualityControlAction"> 

<table width="90%" align="Center">

<tr align="center">
<th> Registration Number : S20072013GH34521</th>
</tr>

<tr>
<th align="left"> Monitoring Of Project</th>
</tr>

<tr>
<th align="left">Method of tracking beneficiaries post-placement </th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Manual </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">Software Based </html:radio> </td>
</tr>

<tr>
 <th align="left">Explain the process and documentation of tracking beneficiaries</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>

<tr>
 <th align="left">Date by which software shall be ready</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>

<tr>
 <th align="left">URL</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>

<tr>
 <th align="left">Username</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>

<tr>
 <th align="left">Password</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left"> Which of the following Mechanisms will be Used to Control Quality of the Project Implementation </th>
</tr>

<tr>
<th align="left">Particular</th>
<th align="left">Strategy</th>
</tr>

<tr>
<th align="left">Internal control procedures related to activities carried out in the project</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Documentation policy</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Organisation policy related to roles and definitions</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Use of software for internal control</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Bench marks</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Methods for controlling project over-runs or under-achievements</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Accounting policies of the Organisation</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

<tr>
<th align="left">Any other details</th>
<td align="left"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
</tr>

</table>

<table align="center">
<tr>
<html:button styleClass="button" property="" onclick="closePage();"><bean:message  key="button.close" /></html:button>
</tr>
</table>
</html:form>