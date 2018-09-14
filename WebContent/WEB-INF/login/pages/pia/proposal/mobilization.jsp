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
	<tr><td align="center" class="pageHeader">Mobilization</td></tr>
	<tr><td> Mobilization of the beneficiaries</td></tr>
	<tr>
	<table width="99%"  align="center">
    <tr>
	<td>Particular</td>
	<td>Target</td>
	</tr>
	<tr>
	<td>No of meetings to be held with CBOs/PRIs/NGOs </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>No of road shows </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>No of counseling session</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>No of other agencies hired for mobilization </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>DRDAs/ local level authorities</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>No of times advertising would be done/promotion material/Mode</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Any Other</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<th>Screening and Selection of Candidates </th>
	</tr>
	<tr>
	<td>Activity</td>
	<td>Strategy</td>
	</tr>
	<tr>
	<td>Screening methodology </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Screening criteria </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Screening tools and tests and may attach samples of test papers to be used for screening</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Personal interviews </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Group discussions</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Hiring of other agencies for screening of candidates and the extent of their role in the process</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Any other information</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr><html:button  styleClass="button" property="" onclick="closeReport()">Submit</html:button> </tr>
	</table>
	</tr>
    </table>
	</html:form>
