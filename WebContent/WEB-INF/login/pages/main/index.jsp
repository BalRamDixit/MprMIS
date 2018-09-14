<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%
String loggedFlag="";
if(request.getAttribute("logFlag")!=null){
	loggedFlag=(String)request.getAttribute("logFlag");
}
if(session.getAttribute("wait") != null){
	response.addHeader("Refresh", "0");
	//request.sendredirect
	session.removeAttribute("wait");
%>
<!-- Please Wait till the Page is loaded or you can Refresh ( F5 ) the Link again.
    It may happen due to Network Traffic ... -->
<%
}
else{
session.setAttribute("wait",new String());
%>

<html>
<head><title>Welcome to MPR System</title><head>
<body>

<tiles:insert page="/WEB-INF/login/pages/main/homeLayout.jsp" flush="true">
    <tiles:put name="title" type="string" value="Welcome to MPR System" />
    <tiles:put name="header" value="/WEB-INF/login/pages/main/header.jsp" />
    <tiles:put name="body" value="/WEB-INF/login/pages/main/mainPage.jsp"/>
    <tiles:put name="footer" value="/WEB-INF/login/pages/main/homePageFooter.jsp" />
</tiles:insert>

</body>
</html>
 
 <%} %>