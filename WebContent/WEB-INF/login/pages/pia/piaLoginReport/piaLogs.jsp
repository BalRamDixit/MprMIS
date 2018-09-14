<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

 
function showData(){
	document.forms[0].action = "PiaLogAction.do?methodName=showPiaLog&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}


function showAllLog(pid){
	document.forms[0].action = "PiaLogAction.do?methodName=getPiaAllLog&pid="+pid+"&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}
</script>

<html:form action="/login/PiaLogAction"> 
<table width="100%" align="center" style="padding-down: 7px;" class="piaLogTbl">
	<tr>
		<th colspan="2" align="center" class="pageHeader">Project Implementation
		Agency (PIAs) Logs</th>
	</tr>
	<tr>
	<th><label class="label-info">Select Applicant Organisation Status </label></th>
	<th>
		<html:select property="piaReportStatus" styleId="piaReportStatus" onchange="javascript:showData();">			
			<html:option value="A">All</html:option>
			<html:option value="S">Submitted</html:option>
			<html:option value="C">Checked</html:option>
			<html:option value="V">Verified</html:option>
			<html:option value="R">Rejected</html:option>
			<html:option value="P">Incomplete</html:option>
			<html:option value="PW">Withdrawn</html:option>
			<html:option value="PD">Debarred</html:option>
			<html:option value="PB">Blacklisted</html:option>
		</html:select>
	</th>
	</tr>
<tr><td colspan="2">
<div style=" overflow:auto; max-width: 700px;max-height:400px; ">
<table width="100%" class="piaLogTbl" >
 
<logic:iterate id="piaLog" name="piaStatusList" indexId="index">
<tr>
<td align="center">${index+1}</td>
<td>
<table>

<tr><th>Current Status:</th><td>
<c:if test="${piaLog.piaStatus == 'V'}"><span class="text-error">Verified</span></c:if>
<c:if test="${piaLog.piaStatus == 'S'}"><span class="text-error">Submitted</span></c:if>
<c:if test="${piaLog.piaStatus == 'C'}"><span class="text-error">Checked</span></c:if>
<c:if test="${piaLog.piaStatus == 'P'}"><span class="text-error">Incomplete</span></c:if>
<c:if test="${piaLog.piaStatus == 'R'}"><span class="text-error">Rejected</span></c:if>
<c:if test="${piaLog.piaStatus == 'PW'}" ><span class="text-error">Withdrawn</span></c:if>
<c:if test="${piaLog.piaStatus == 'PD'}" ><span class="text-error">Debarred</span></c:if>
<c:if test="${piaLog.piaStatus == 'PB'}" ><span class="text-error">Blacklisted</span></c:if>
</td></tr>
<tr><th>Last Update</th><td>${piaLog.modifyOn} </td></tr>
<c:if test="${piaLog.piaStatus == 'R' || piaLog.piaStatus == 'PW' || piaLog.piaStatus == 'PD' || piaLog.piaStatus == 'PB'  }">
<tr><th>Remark</th><td>${piaLog.remark}</td></tr>
</c:if>
</table>
</td>



<td>
<table>
<tr><th>Applicant Organisation</th><td> ${piaLog.piaName}</td></tr>
<tr><th>Temp. Ref. No.</th><td> ${piaLog.piaCode} (<a href="javascript:showAllLog('${piaLog.piaCode}')"> View All Track History </a>)</td></tr>
<c:if test="${piaLog.piaStatus == 'V'}">
<tr><th>Permanent Registration Number</th><td> ${piaLog.piaRegistrationNo}</td></tr>
</c:if>
</table>
</td>

<td>
<table>
<tr><th>Registration Number</th><td> ${piaLog.registrationNumber}</td></tr>
<tr><th>PAN </th><td> ${piaLog.panNo}</td></tr>
<tr><th>TAN </th><td> ${piaLog.tanNo}</td></tr>
</table>
</td>
</tr>
</logic:iterate>
</table>
</div>
</td>
</tr>
</table>
 <div align="center" style="padding-top: 15px; padding-bottom: 10px;"> 
<html:button styleClass="mybtn" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message key="button.close" /></html:button>
</div> 
</html:form>