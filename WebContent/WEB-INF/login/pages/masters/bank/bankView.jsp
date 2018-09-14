 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
 <%@ page import="com.infotech.sgsy.util.Constants" %>
 <jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<script language="Javascript1.1" src="javaScript/common.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>


<script language="javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
</script>

<html:form action="login/bank" >
<table width="100%">
<tr>
	<td align="center" class="pageHeader"><bean:message key="title.viewbank"></bean:message> </td>
</tr>
<tr>
<td>
	<%int cnt = 0; %>
	<table width="90%" align="center" class="tempinputTBL">
		<tr>
			<th align="center" class="formlabel"><bean:message key="label.srno"/></th>
			<th align="center" class="formlabel"><bean:message key="label.bankname"/></th>
			<th align="center" class="formlabel"><bean:message key="label.bankabbreviation"/></th>
			<th align="center" class="formlabel"><bean:message key="label.banktype"/></th>	
			<th align="center" class="formlabel"><bean:message key="label.banklevel"/></th>
		</tr>
	
	<logic:present name="bankscoll" >
		<logic:iterate  id="List"   name="bankscoll" >
		<tr>
			<td align="center" class="formlabel" ><%=(cnt= cnt+ 1) %></td>
			<td align="center" class="formlabel"><bean:write name="List"  property="bankName" /> </td>
			<td align="center" class="formlabel">	<bean:write name="List"  property="bankShortName" /></td>
			<td align="center" class="formlabel">	<bean:write name="List"  property="bankTypeCode" /></td>
			<td align="center" class="formlabel">	<bean:write name="List"  property="bankLevelName" /></td>
		</tr>
		</logic:iterate>
	</logic:present>	
	</table>
</td></tr>	

<tr>
	<td align="center">
	 <html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message  key="button.close" /></html:button>  
	</td>
</tr>
</table>
</html:form>




