<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:form action="/mmuMasterReport"> 

<table width="90%" align="Center">

<tr align="center">
<th> Registration Number : S20072013GH34521</th>
</tr>

<tr>
<th align="left">Legal Details</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Has your Organization ever been convicted by a court in India for any of the following reasons. 
If so, give name of the court, case number, case status and relevant sections of Law</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Type of Cases </th>
<th>Status </th>
</tr>

<tr>
<th align="left">Criminal cases</th>
<td align="center"><html:radio property="stateCode" styleId="stateCode" value="yes"> Yes</html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no"> No</html:radio> </td>
<tr>

<tr>
<th align="left">Court injunctions</th>
<td align="center"><html:radio property="stateCode" styleId="stateCode" value="yes">Yes </html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
<tr>

<tr>
<th align="left">Blacklisted by state or central government</th>
<td align="center"><html:radio property="stateCode" styleId="stateCode" value="yes"> Yes</html:radio>
<html:radio property="stateCode" styleId="stateCode" value="no">No </html:radio> </td>
<tr>

<tr>
<th align="left">Any Other</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
<tr>

</table>

<table align="center">
<tr>
<html:button  styleClass="button" property="" onclick="closeReport()">Close</html:button>
</tr>
</table>

</html:form>