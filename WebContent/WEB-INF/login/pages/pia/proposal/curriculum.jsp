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
	
<html:form action="login/curriculamAction">
<table width="100%" align="center">
	<tr><td align="center" class="pageHeader">Curriculum</td></tr>
	<tr><th>The curriculum and course design course(s) in trade(s) proposed</th></tr>
	<tr>
	<td>
	<table width="99%"  align="center">
    <tr>
	<td>Proposed Trade</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Proposed Course</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Total Number of days of training (including OJT etc.)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Total number of hours of training(in hrs)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Number of OJT days</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Total number of hours/Day (excluding recreation time)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Hours of soft skills training during the program</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Hours of Computer training during the program </td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Hours of practical training</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Strategy for module development: In house team/ External Research Agency/ In collaboration with Industry (name the source)</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr>
	<td>Any Certification obtained for course work, if yes name the agency from which certification has been obtained</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	</tr>
	<tr><td align="center"><html:button  styleClass="button" property="" onclick="">Add Curriculum</html:button></td></tr>
	<tr>
	<td>
	<table>
	<tr>
	<th>Proposed Trade</th>
	<th>Proposed Course</th>
	<th>Training days</th>
	<th>Training Hours</th>
	<th>OJT days</th>
	<th>Training Hours / Day</th>
	<th>Soft Skill Training (in hrs.)</th>
	<th>Computer Training (in hrs.)</th>
	<th>Practical Training (in hrs.)</th>
	<th>Module Development Strategy</th>
	<th>Certifications</th>
	<th>Action</th>
	</tr>
	</table>
	</td>
	</tr>
 </table>
</html:form>
