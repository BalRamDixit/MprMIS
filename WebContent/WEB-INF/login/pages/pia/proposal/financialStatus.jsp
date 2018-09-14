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
<th align="left">Financial Status</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Bank Account Details </th>
</tr>

<tr>
<th align="left">Name of bank</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="50"/></td>
<tr>

<tr>
<th align="left">Branch</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="50"/></td>
<tr>

<tr>
<th align="left">Branch code</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="50"/></td>
<tr>

<tr>
<th align="left">Account number</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="50"/></td>
<tr>

<tr>
<th align="left">IFSC code</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="50"/></td>
<tr>

<tr>
<th align="left">MICR code</th>
<td align="center"><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="50"/></td>
<tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Annual Turnover</th>
</tr>
<tr></tr>
<tr>
<th align="left">(Only for the previous three years for which audit is complete)</th>
</tr>
<tr></tr>
<tr> 
<th align="left">Attach audited financial statements as per sl. no. 33, 34 and 35 of the check list</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Year</th>
<th align="left">Annual Turnover (in Rs.)</th>
<th align="left">Supporting Document</th>
<th align="left">Remarks</th>
</tr>

<tr> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:button property="stateCode" value="Upload File"></html:button> </td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
</tr>
<tr> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:button property="stateCode" value="Upload File"></html:button> </td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
</tr>
<tr> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:button property="stateCode" value="Upload File"></html:button> </td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">Annual Net Worth of The Applicant (in Rs.)</th>
</tr>

<tr>
<th>(Only for the last financial year for which the audit is complete)</th>
</tr>

<tr>
<th align="left">Year</th>
<th align="left">Annual Turnover(in Rs.)</th>
<th align="left">Supporting Document</th>
<th align="left">Remarks</th>
</tr>

<tr> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
<td><html:button property="stateCode" value="Upload File"></html:button> </td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th>Financial Liabilities</th>
</tr>

<tr>
<th align="left">Particular</th>
<th align="left">Amount (in Rs.)</th>
<th align="left">Remarks</th>
</tr>

<tr>
<th>Total outstanding financial liabilities of the applicant</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="30"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="30"/></td> 
</tr>

<tr>
<th>Total defaults in financial liabilities by the applicant</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="30"/></td> 
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="30"/></td> 
</tr>

</table>

<table align="center">
<tr>
<html:button  styleClass="button" property="" onclick="closeReport()">Submit</html:button>
</tr>
</table>

</html:form>