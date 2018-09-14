<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:form action="/login/trainingAction"> 

<table width="90%" align="Center">

<tr align="right">
<th> Registration Number : S20072013GH34521</th>
</tr>

<tr> 
<th align="left">Training of Beneficiaries  </th>
</tr>

<tr align="left">
<th align="left">Activity </th>
<th align="left">Strategy</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
 <th align="left">Use of biometric machines</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">Trainer/ trainee ratio</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">Training schedule for the courses showing spread of core module, 
 IT skills and soft skills over the training period</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">Training methodology</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">On the job training of the beneficiaries</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>
<tr>
 <th align="left">Internal assessment methodology</th>
   <td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="50" /></td>
</tr>
</table>

<table align="center">
<tr>
<html:button  styleClass="button" property="" onclick="closeReport()">Submit</html:button>
</tr>
</table>
</html:form>