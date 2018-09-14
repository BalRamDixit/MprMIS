<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
 function getUserDetails(level){
     
   //  alert(level)
    // alert(document.userForm.levelCode)
     //document.userForm.levelCode.value=level;
     
     if(level=='0')
     document.forms[0].action="userAction1.do?methodName=getUserListForReport&level="+level+"&"+tokenParameter+"="+tokenValue;
	 if(level=='2')
     document.forms[0].action="userAction1.do?methodName=getStateDetails&level="+level+"&"+tokenParameter+"="+tokenValue;
	 if(level=='3')
     document.forms[0].action="userAction1.do?methodName=getStateDetailsForUserReport&level="+level+"&"+tokenParameter+"="+tokenValue;
	 if(level=='4')
     document.forms[0].action="userAction1.do?methodName=getStateDetailsForUserReport&flag=block&level="+level+"&"+tokenParameter+"="+tokenValue;
	 document.forms[0].submit();
	 
	 
 }
function closePage()
{
	var status = window.confirm('<bean:message key="alert.close.form" />');
	if(status==true)
	{
		document.forms[0].action="login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	}
}
 
 </script>
<body>
<html:form action="/login/userAction1">

<table width="100%">
	<tr>
		<td class="pageHeader" align="center">User Details Report</td> 		 
	</tr>
	<tr><td align="center">
	<logic:present name="userReportList">
	<table width="90%" class="inputTBL" >
   
		<tr class="formsubheading">					
			<th width="20%">Total MoRD User</th>
			<th width="20%"> Total State User</th>
			<th width="20%">Total District User</th>
			<th width="20%">Total Block User</th>
	    </tr>
		 
		<c:set var="count" value="1"/>
		<logic:iterate  id="userDetails"  name="userReportList" type="com.infotech.sgsy.common.MasterVO">		
		<tr>							
			<td>
				<a href="javascript:getUserDetails('0')">
				<bean:write name="userDetails"  property="mordUser" /> 
				</a>
			</td>
			<td>
				<a href="javascript:getUserDetails('2')">
				<bean:write name="userDetails"  property="stateUser" /> 
				</a>
			</td>
			<td>
				<a href="javascript:getUserDetails('3')">
				<bean:write name="userDetails"  property="districtUser" /> 
				</a>
			</td>
			<td>
				<a href="javascript:getUserDetails('4')">
				<bean:write name="userDetails"  property="blockUser" /> 
				</a>
			</td>
	    </tr>			
		</logic:iterate>	
		</table>	
		</logic:present>  
	 	 <table border="0" width="100%" id="table1" style="border-width: 0px">			
			<tr>			
				<td align="center">							
					<html:button styleClass="button" property="" onclick="closePage()"><bean:message  key="button.close" /></html:button>
				</td>	
			</tr>	
		</table>
 </td></tr>
 </table> 
</html:form> 

</body>
 
</html:html>