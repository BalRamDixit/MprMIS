<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:form action="/mmuMasterReport"> 

<table width="90%" align="Center">

<tr align="right">
<th> Registration Number : S20072013GH34521</th>
</tr>

<tr>
<th align="left">PIA Credentials</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Accreditation/Certifications received</th>
<th align="left">Awarding Authority</th>
<th align="left">Date of Receiving</th>
<th align="left">Validity Date</th>
<th align="left">Supporting Document</th>
<th align="left">Actions</th>
</tr>

<tr> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>  
<td><html:button property="stateCode" value="Upload File"></html:button> </td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
</tr>

</table>

<table align="center">
<tr>
<html:button  styleClass="button" property="" onclick="closeReport()">Close</html:button>
</tr>
</table>

</html:form>