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
	<tr><td align="center" class="pageHeader">Geographical Coverage</td></tr>
	<tr>
	<table width="99%"  align="center">
	
	<tr>
		<th>State in which project shall be undertaken</th>
		<th>Name of districts</th>
		<th>No of Districts Covered</th>
		<th>Duration of the project(Number of Years Only)</th>
		<th>Action</th>
	</tr>	
	
	<tr>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="Kerala">Kerala</html:option>
    <html:option value="Karnataka">Karnataka</html:option>
    <html:option value="TamilNadu">TamilNadu</html:option>
    <html:option value="Maharashtra">Maharashtra</html:option>
    <html:option value="Andhra Pradesh">Andhra Pradesh</html:option>	
	</html:select>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="Mysore">Mysore</html:option>
    <html:option value="Chitraguda">Chitraguda</html:option>
    <html:option value="Bellary">Bellary</html:option>
    <html:option value="Shimoga">Shimoga</html:option>
    <html:option value="Bijapur">Bijapur</html:option>	
	</html:select>
	</td>
	<td></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<html:button  property="" onclick="">AddMore</html:button>
	</tr>
	<tr>
	<th>Rationale Behind Selection of Districts</th>
	</tr>
	<tr>
	<td>Particular</td>
	<td>Reasoning</td>
	</tr>
	<tr>
	<td>Strength of the organisation</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Geographic existence of the organization</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Market scan conducted by the organization</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Market scan conducted by third party for the districts Proposed</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Any Other</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<th>Sector/Trade-wise Allocation of Beneficiaries for Training </th>
	</tr>
	<tr>
		<th>Sectors proposed</th>
		<th>Trades to be offered in the sector proposed</th>
		<th>Targeted beneficiaries</th>
		<th>Action</th>
	</tr>
	<tr>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="Sector 1">Sector 1</html:option>
    <html:option value="Sector 2">Sector 2</html:option>
    <html:option value="Sector 3">Sector 3</html:option>
    <html:option value="Sector 4">Sector 4</html:option>
    <html:option value="Sector 5">Sector 5</html:option>	
    <html:option value="Sector 6">Sector 6</html:option>
	</html:select>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="Trades 1">Trades 1</html:option>
    <html:option value="Trades 2">Trades 2</html:option>
    <html:option value="Trades 3">Trades 3</html:option>
    <html:option value="Trades 4">Trades 4</html:option>
    <html:option value="Trades 5">Trades 5</html:option>	
    <html:option value="Trades 6">Trades 6</html:option>	
	</html:select>
	</td>	
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td></td>
	</tr>
	<tr>
	<html:button styleClass="button" property="" onclick="">AddMore</html:button>
	</tr>
	
	<tr><html:button  styleClass="button" property="" onclick="closeReport()">Submit</html:button> </tr>
	</table>
	</tr>
    </table>
	</html:form>
