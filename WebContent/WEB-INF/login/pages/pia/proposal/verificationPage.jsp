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
<th align="left">Verification Form</th>
</tr>

<tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr> <tr></tr><tr></tr> <tr></tr><tr></tr> <tr></tr>

<tr>
<th align="left">First Name</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Last Name</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">S/o / D/o / W/o </th>
<td><html:select property="stateCode">
<html:option value="">--Select--</html:option>
						<html:option value="S">S/o</html:option>
						<html:option value="D">D/o</html:option>
						<html:option value="W">W/o</html:option></html:select>
<html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Age</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Occupation</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Village / Town / City </th>
<td><html:select property="stateCode">
<html:option value="">--Select--</html:option>
						<html:option value="V">Village</html:option>
						<html:option value="T">Town</html:option>
						<html:option value="C">City</html:option></html:select>
<html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Police station </th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Post office </th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Taluka </th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">District</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">State </th>
<td><html:select property="stateCode">
<html:option value="">--Select--</html:option>
						<html:option value="D">Delhi</html:option>
						<html:option value="H">Haryana</html:option>
						<html:option value="G">Goa</html:option></html:select></td>
</tr>

<tr>
<th align="left">Place</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>  
<th>I am the authorized person to file this application and certify that the information furnished above is complete and correct in all respects. 
In case any of the information or documents attached to this application is found to be false or incorrect, 
then the application may be rejected and I may be liable for any action by the Government. </th>
</tr>

<tr>
<th align="left">Date</th>
<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="20"/></td> 
</tr>

<tr>
<th align="left">Digital Signature</th>
<td><html:button property="stateCode" value="Upload File"></html:button> </td>  
</tr>

</table>

<table align="center">
<tr>
<html:button  styleClass="button" property="" onclick="closeReport()">Submit</html:button>
</tr>
</table>

</html:form>