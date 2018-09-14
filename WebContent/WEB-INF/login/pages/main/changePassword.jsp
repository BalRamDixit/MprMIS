<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />

<tiles:insert page="/WEB-INF/login/pages/main/layout.jsp" flush="true">
    <tiles:put name="title" type="string" value="SGSY" />
    <tiles:put name="header" value="/WEB-INF/login/pages/main/header.jsp" />
    <tiles:put name="footer" value="/WEB-INF/login/pages/main/footer.jsp" />
    <tiles:put name="body"  value="/WEB-INF/login/pages/main/changePasswdContent.jsp"/>
</tiles:insert>


 
