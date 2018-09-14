 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
 <%@ page import="com.infotech.sgsy.util.Constants" %>
 <jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
 
<script language="Javascript1.1" src="javaScript/common.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
</head>
<script language="javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 


</script>
<body>
<html:form action="login/bank" >
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" class="actionstxt"><bean:message key="title.viewbank"></bean:message> </td>
	</tr>
</table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	   <logic:present name="stateName">
	    <tr> 
	     
				<td class="formlabel"><B><bean:message
					key="label.state" /></B><span class="mandatory">*</span></td>
				<td class="formlabel"><label>
				<B><bean:write	name="stateName"></bean:write></B></label>
				</td>
			</tr>
			</logic:present>
			<logic:present name="districtName">
	    <tr>
				<td class="formlabel"><B><bean:message
					key="label.district" /></B><span class="mandatory">*</span></td>
				<td class="formlabel"><label>
				<B><bean:write	name="districtName"></bean:write></B></label>
				</td>
			</tr>
		</logic:present>
			</table>
<br>
<%int cnt = 0; %>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
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
	
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="center">
	 		<html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message  key="button.close" /></html:button>  
			</td>
		</tr>
				
	</table>
	
</logic:present>	
</html:form>
</table>

</body>
</html:html>



