<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html>
<head><title>Welcome to MPR System</title><head>
<body>
 
	<tiles:insert page="/WEB-INF/login/pages/main/layout.jsp" flush="true">
    <tiles:put name="title" type="string" value="Skills" />
    <tiles:put name="body" value="/WEB-INF/login/pages/main/indexContent.jsp"/>
    <tiles:put name="footer" value="/WEB-INF/login/pages/main/footer.jsp" />
</tiles:insert> 

</body>
</html>
  