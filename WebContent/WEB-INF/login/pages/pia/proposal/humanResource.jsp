<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;

function save(){
	window.location = "humanResourceAction.do?methodName=save&type=emp&"+tokenParameter+"="+tokenValue;
}

function closeReport(){
	 window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
	
	</script>
	
<html:form action="/login/humanResourceAction">
<table width="100%" align="center">
	<tr><td align="center" class="pageHeader">Human Resource</td></tr>
	<tr>
	<table width="99%"  align="center">
	<tr><th>Details of Employee already employed with the applicant</th></tr>
	<tr>
	<th>Name of the employee</th>
	<th>Designation</th>
	<th>Action</th>
	</tr>
	<tr>
	<td><html:text styleId="stateCode" property="employeeName" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="employeeDesig" maxlength="10"  size="10"/></td>
	<td></td>
	</tr>
	<tr>
	<html:button  styleClass="button" property="" onclick="">AddMore</html:button> </tr>
	
	<tr><th>Details of Employee proposed to be  employed by the applicant</th></tr>
	<tr>
	<th>Name of the employee</th>
	<th>Designation</th>
	<th>Action</th>
	</tr>
	<tr>
	<td><html:text styleId="stateCode" property="employeeName" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="employeeDesig" maxlength="10"  size="10"/></td>
	<td></td>
	</tr>
	<tr><html:button  styleClass="button" property="" onclick="">AddMore</html:button> </tr>
	</table></tr></table>
	<html:button property="submit" onclick="save()">Submit</html:button>
	</html:form>
	