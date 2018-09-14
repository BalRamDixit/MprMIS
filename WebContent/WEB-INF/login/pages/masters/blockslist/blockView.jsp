<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />

<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="javascript" src="javaScript/common.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/datetimepicker.js"></script>
<script language="javascript" src="javaScript/choosedate.js"></script>
<script language="javascript" src="javaScript/dateValidation.js"></script>
<link type="text/css" rel="stylesheet" href="css/calendar.css"></link>

<script type="text/javascript">


var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 

//window.onbeforeunload=confirmExit;
function confirmExit(){
		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" class="actionstxt"><bean:message key="title.viewblock"></bean:message> </td>
	</tr>
</table>

<br>
<html:form action="login/block" onsubmit="unload()">
		<c:set var="cnt" value="0"></c:set>
    
      <logic:present name="<%=Constants.COLLECTION_GETBLOCK %>" >
      
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<th align="center" class="formlabel"><bean:message key="label.srno"/></th>
					<th align="center" class="formlabel"><bean:message key="label.blockName"/></th>
				</tr>
				
				<logic:iterate  id="List"   name="<%=Constants.COLLECTION_GETBLOCK %>" >
				<c:set var="cnt" value="${cnt+1}"></c:set>
				<tr>
					<td align="center" class="formlabel">${cnt}</td>
					<td align="center" class="formlabel"><bean:write name="List"  property="blockName" /> </td>
				</tr>
				
				</logic:iterate> 
			</table>
        
        </logic:present>
         
        
</html:form>
