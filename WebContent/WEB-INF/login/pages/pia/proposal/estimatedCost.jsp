<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;

function closeReport(){
	 window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
	
	</script>
	
<html:form action="/login/mmuMasterReport">
<table width="100%" align="center">
	<tr><td align="center" class="pageHeader">Estimated Cost</td></tr>
	<tr>
	<table width="99%"  align="center">
    <tr>
	<th>Component</th>
	<th>Cost</th>
	</tr>
	<tr>
	<td>Mobilization of beneficiaries </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Training of beneficiaries</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Cost of curriculum (including study material)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Training of trainers</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Utilities for training center (recurrent costs only)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Infrastructure costs of training (recurrent costs only) </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Equipment (recurrent costs only) </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Teaching aids (recurrent costs only)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Raw material (recurrent costs only) </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Boarding and lodging for beneficiaries or Transportation and food for 
beneficiaries (only one of the either costs may be claimed at a time) </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Assessment and certification </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Placement linkage </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>MIS (tracking and monitoring of project) </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Post-placement financial support given to beneficiary  </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Post placement tracking cost</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Counseling costs</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Cost of Market Scans for employer engagements</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Administrative costs of the PIA</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Total cost per candidate</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<th>Estimated Total Cost of The Project</th>
	</tr>
	<tr>
	<td>Total cost of the project</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
		<th>Funding Agent</th>
		<th>Share of Funding(in %)</th>
		<th>Funding (in Rs.)</th>
	</tr>
	<tr>
		<th>Funding Agent</th>
		<th>Share of Funding(in %)</th>
		<th>Funding (in Rs.)</th>
	</tr>
	<tr>
	<td>Central government</td>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="75">75%</html:option>
    <html:option value="90">90%</html:option>
    <html:option value="100">100%</html:option>	
	</html:select>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>	
	<tr>
	<td>State government/applicant</td>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value=""></html:option>
    <html:option value=""></html:option>
    <html:option value=""></html:option>	
	</html:select>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>	
		<tr><html:button  styleClass="button" property="" onclick="closeReport()">Submit</html:button> </tr>
	</table>
	</tr>
    </table>
	</html:form>
