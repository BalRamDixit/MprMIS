<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
</head>
<script language=javascript>

 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 </script>
 
</head>
<body>
<html:form action="/login/userAction1">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
   
	
	<tr>
		<td class="actionstxt" width="100%" align="center" > 		
	  	User Details
	  	
	  	</td> 		 
	</tr>
	
</table>		
		
	<logic:present name="usrRptList">
		<tr class="formsubheading">		
			
			<td width="30%" class="formlabel" align="left">Mord User</td>
			<td width="30%" class="formlabel" align="left">State User</td>
			

	   </tr>
		<%int cnt = 0; %>	
		<logic:iterate  id="groupDetails"  name="usrRptList" type="com.infotech.sgsy.common.MasterVO">
			
		<tr class="row1">
				
			
			<td width="20%" class="formlabel" align="left">
			<a href="javascript:getGroupDetails('<bean:write name='groupDetails' property='mordUser'/>','<bean:write name="groupDetails" property="mordUser" />','<bean:write name="groupDetails" property="mordUser" />','<bean:write name='groupDetails' property='mordUser'/>','<bean:write name='shgReportForm' property='mordUser'/>','<bean:write property="mordUser" name="shgReportForm"/>','<bean:write property="mordUser" name="shgReportForm"/>')">
			<bean:write name="groupDetails"  property="groupName" />(<bean:write name='groupDetails' property='shgCode'/>)</a></td>
			
			<td width="20%" class="formlabel" align="left">			
			<bean:write name="groupDetails"  property="shgType" /></td>
			
			<td width="20%" class="formlabel" align="left">			
			<bean:write name="groupDetails"  property="status" /></td>
					 			
	   </tr>			
		</logic:iterate>	
		</table>	
		</logic:present>
	
	
  
	  <table border="0" width="100%" id="table1" style="border-width: 0px">			
			<tr>
			
				<td align="center">		
					
					<html:button styleClass="button" property="" onclick="closeReport()"><bean:message  key="button.close" /></html:button>
			</tr>	
		</table>
 
  
</html:form> 

</body>
 
</html:html>