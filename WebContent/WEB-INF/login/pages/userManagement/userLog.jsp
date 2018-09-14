<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>

<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="Javascript1.1" src="javaScript/common.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="javascript" src="javaScript/dateValidation.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<link type="text/css" rel="stylesheet" href="css/calendar.css"></link>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css"></link>

<body  >
<html:form action="login/userLog" onsubmit="unload()">
<input type="hidden" name="reqtrack" value="<%=request.getSession().getAttribute("TRACKERID") %>"  />
<table width="99%" align="center">
<tr>
   <td align="center" class="pageHeader"><bean:message key="userlog.title" ></bean:message></td>
</tr>
<tr><td>
<table width="99%" align="center" class="inputTBL">
<tr>		
	<th width="30%"><bean:message  key="userlog.label.user"/></th> 
	<td> <html:text onblur="this.value=this.value.toUpperCase();" property="username"></html:text>
</tr>	
<tr>		
	<th><bean:message  key="userlog.label.fromDate"/></th>
	<td><html:text property="fromDate" styleId="fromDate" readonly="true" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].fromDate,'dd-mm-yyyy',this)"/>
	</td>
</tr>	
<tr>		
	<th><bean:message key="userlog.label.toDate"/></th>
	<td><html:text property="toDate" styleId="toDate" readonly="true" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].toDate,'dd-mm-yyyy',this)"/>
 	
 	</td>
</tr>	
</table>
</td></tr>
<tr>
	<td align="center">
		<html:button property="next" styleClass="primaryBtn"  onclick="getReport()">
		<bean:message key="button.submit"/></html:button>
	</td>	
</tr>
				
	 
<logic:present name="LOGLIST"> 
<tr>
	<td>
	<html:button property="next" styleClass="successBtn"  onclick="window.print()"><bean:message key="button.print"/></html:button>
	<html:button property="next" styleClass="successBtn"  onclick="closePage()"><bean:message key="button.close"/></html:button>
	</td>
</tr>
<tr><td>
 	<%int cnt = 0; %>
 	<table width="99%" align="center" class="inputTBL">
 	   <tr>
 	    <th width="15px" align="center">Sl.No.</th>
		<th align="center"><bean:message key="us.lb.userId"/></th>
		<th align="center"><bean:message key="userlog.label.time"/></th>
		<th align="center"><bean:message key="userlog.label.IP"/>1</th>
		<th align="center"><bean:message key="userlog.label.status"/></th>
	</tr>
 	     
 	<logic:iterate  id="List" name="LOGLIST" indexId="index" >
 	<tr>
 		<td>${index + 1}
		<td align="center"><bean:write name="List"  property="username" /> </td>
		<td align="center"><bean:write name="List"  property="timestamp" /> </td>
		<td align="center"><bean:write name="List"  property="ip" /> </td>
		<td align="center"><bean:write name="List"  property="login_status" /> </td>
	</tr> 	  
 	</logic:iterate>	
 						
 </table>
 </td></tr>					
 </logic:present>
 </table> 
</html:form>	
	
<script type="text/javascript"> 
function getReport(){ 
    document.forms[0].action = "userLog.do?method=showReportResult"+"&"+tokenParameter+"="+tokenValue+"&paging=page";
	document.forms[0].submit();  
}  

function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form"/>");	
	if(status==true){	
		document.logForm.action = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
			document.logForm.submit();
		}
}
 </script> 