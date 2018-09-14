<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/packtag.tld" prefix="pack"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- <pack:script><src>/javaScript/pykkka/common/selectRoleLayout.js</src></pack:script> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.infotech.sgsy.login.LoginVO"%>
<%@page import="com.infotech.sgsy.util.Constants"%>

<%String loggedFlag="";
if(request.getAttribute("logFlag")!=null){
	loggedFlag=(String)request.getAttribute("logFlag");
}
%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" ignore="true" /></title>

<!-- <link href="css/nrlmStyles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/yui/container.css" />
<link rel="stylesheet" type="text/css" href="css/yui/button/button.css"/> -->

<link  type="text/css" href="css/main_style.css" rel="stylesheet" />
<link type="text/css" href="css/dhtmlgoodies_calendar.css" rel="stylesheet" />
<link type="text/css" href="css/menu.css" rel="stylesheet" />

<!-- These java script for yahoo pop remark -->
<!-- <script language="javascript" src="javaScript/yui/yuiloader-min.js" ></script>
<script language="javascript" src="javaScript/yui/dom-min.js" ></script>
<script language="javascript" src="javaScript/yui/event-min.js" ></script>
<script language="javascript" src="javaScript/yui/container-min.js" ></script>
<script language="javascript" src="javaScript/yui/element-beta-min.js" ></script>
<script language="javascript" src="javaScript/yui/button-min.js" ></script> -->

<script language="javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 

function getTracker(){
	document.loginForm.action="login.do?methodName=logOut&Header="+tokenValue;
	document.loginForm.submit();
}
 
	
function openFile(VAL){
  var URL;
  if(VAL=='1')
	 URL="login.do?methodName=login&rol=R";
   else
	  URL="login.do?methodName=showChangePasswordPage";
	document.forms[0].action=URL+"&"+tokenParameter+"="+tokenValue+"&paging=page";
	//alert(document.forms[0].action)
	document.forms[0].submit();
}
</script>

</head>

<body>
<div id="main" >
<!-- MAIN PANEL DIV STARTS -->
<div>
     <tiles:insert attribute="header" ignore="true">
			<tiles:put name="title" beanName="title" beanScope="tile" />
	 </tiles:insert>
	
	<div id="nav" class="box" style="height: 38px;">
        <ul style="padding-top: 10px;">
        	<logic:present name="loginVO">
            	<li><bean:define id="user" name="loginVO" type="com.infotech.sgsy.login.LoginVO"></bean:define></li>
      		</logic:present>
            <li><b>WELCOME</b>&nbsp;&nbsp;</li>
            <%LoginVO loginvo= (LoginVO)request.getSession().getAttribute("loginVO");   
      		if(request.getSession().getAttribute("loginVO")!=null){  
     		%>
    		<%if(Constants.ENTITYTYPE_STATE.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
            
            <li>STATE : <span style="color: #4852D0;"><b><bean:write name="user" property="entityName" /></b></span></li>
            <%} %>
      		<%if(Constants.ENTITYTYPE_DISTRICT.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
            
            <li>DISTRICT : <span style="color: #4852D0;"><b><bean:write name="user" property="entityName" /></b></span></li>
             <%} %>
      		<%if(Constants.ENTITYTYPE_BLOCK.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
            
            <li>BLOCK : <span style="color: #4852D0;"><b><bean:write name="user" property="entityName" /></b></span></li>
            <%} %>
       		<%if(Constants.ENTITYTYPE_VILLAGE.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
            <li>VILLAGE : <span style="color: #4852D0;"><b><bean:write name="user" property="entityName" /></b></span></li>
            <%}
       		if("AD".equalsIgnoreCase(loginvo.getEntityType().trim())){
       		%>
     		<li><span style="color: #4852D0;"><b>MoRD</b></span></li>
     	  	<%} %>
     	  	<li>, &nbsp;USER :&nbsp;</li>
     	 	<%if(Constants.ENTITYTYPE_MYAS.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
     		<li ><span style="color: #4852D0;"><b>(MoRD)</b></span></li> 
      		<%} %>
      		<%if(Constants.ENTITYTYPE_STATE.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      		<li ><span style="color: #4852D0;"><b>(STATE)</b></span></li> 
      		<%} %>
      		<%if(Constants.ENTITYTYPE_DISTRICT.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      		<li ><span style="color: #4852D0;"><b>(DISTRICT)</b></span></li> 
      		<%} %>
      		<%if(Constants.ENTITYTYPE_BLOCK.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      		<li><span style="color: #4852D0;"><b>(BLOCK)</b></span></li> 
      		<%} %>
       		<%if(Constants.ENTITYTYPE_VILLAGE.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      		<li><span style="color: #4852D0;"><b>(VILLAGE)</b></span></li> 
      		<%} %>
       		<span style="color: #4852D0;"><b><bean:write name="user" property="userName" /></b></span>      
       		<%} %>
        </ul>
        <p id="feeds">
        	<a href="javascript:openFile('1')" >Change Role</a>&nbsp;&nbsp;&nbsp;
            <a href="javascript:openFile('2')" >Change Password</a> &nbsp;&nbsp;&nbsp;
           	<a href="helpFile/Help.htm" target="_blank">User Manual</a> &nbsp;&nbsp;&nbsp;
            <a href="javascript:getTracker()" >Logout</a>       
        </p> 
    </div>


 	<div style="margin-top: 20px;">
   		<tiles:insert attribute="body"/>
  	 	<!--Footer navigation starts here-->
 	 		<tiles:insert attribute="footer" ignore="true" />
		<!--Footer message section ends here-->
	</div>
</div>
<!-- MAIN PANEL DIV END -->
</div>
</body>
</html>
