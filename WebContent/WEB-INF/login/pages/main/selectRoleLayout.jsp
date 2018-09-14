<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/packtag.tld" prefix="pack"%>
<%-- <pack:script><src>/javaScript/pykkka/common/selectRoleLayout.js</src></pack:script> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

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
<link  rel="stylesheet" type="text/css" href="css/nrlmStyles.css" />
<link rel="stylesheet" type="text/css" href="css/yui/container.css" />
<link rel="stylesheet" type="text/css" href="css/yui/button/button.css"/>

<!-- These java script for yahoo pop remark -->
<script language="javascript" src="javaScript/yui/yuiloader-min.js" ></script>
<script language="javascript" src="javaScript/yui/dom-min.js" ></script>
<script language="javascript" src="javaScript/yui/event-min.js" ></script>
<script language="javascript" src="javaScript/yui/container-min.js" ></script>
<script language="javascript" src="javaScript/yui/element-beta-min.js" ></script>
<script language="javascript" src="javaScript/yui/button-min.js" ></script>
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
<!-- MAIN PANEL DIV STARTS -->
<div class="pagelayout" >
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="center">
     <tiles:insert attribute="header" ignore="true">
			<tiles:put name="title" beanName="title" beanScope="tile" />
	 </tiles:insert>
    </td>
  </tr>
</table>

<!-- Header Menu Start -->
<div align="center">
<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" class="globalnav">
    <tr>
      <td >
      <logic:present name="loginVO">
      	<bean:define id="user" name="loginVO" type="com.infotech.sgsy.login.LoginVO"  ></bean:define>
      </logic:present>
      </td>
    </tr>
    
    <tr>
      <td><span class="userlable">Welcome : </span> 
      <span class="userinfo">
      <%LoginVO loginvo= (LoginVO)request.getSession().getAttribute("loginVO");
      
      if(request.getSession().getAttribute("loginVO")!=null){
      
      %>
     
      <%if(Constants.ENTITYTYPE_STATE.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
      &nbsp;<span class="userlable">STATE :</span> 
        <bean:write name="user" property="entityName" />&nbsp;&nbsp;
      <%} %>
      <%if(Constants.ENTITYTYPE_DISTRICT.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
      &nbsp;<span class="userlable">DISTRICT :</span> 
        <bean:write name="user" property="entityName" />&nbsp;&nbsp;
      <%} %>
      <%if(Constants.ENTITYTYPE_BLOCK.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
      &nbsp;<span class="userlable">BLOCK :</span> 
        <bean:write name="user" property="entityName" />&nbsp;&nbsp;
      <%} %>
       <%if(Constants.ENTITYTYPE_VILLAGE.equalsIgnoreCase(loginvo.getEntityType().trim())){ %>
      &nbsp;<span class="userlable">VILLAGE :</span> 
        <bean:write name="user" property="entityName" />&nbsp;&nbsp;
      <%}
       if("AD".equalsIgnoreCase(loginvo.getEntityType().trim())){
       %>
     		MoRD
       <%} %>
      
      <span class="userlable">&nbsp;&brvbar;&nbsp; USER :&nbsp;</span>
      <%if(Constants.ENTITYTYPE_MYAS.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      &nbsp;<span class="userlable">(MoRD)</span> 
      <%} %>
      <%if(Constants.ENTITYTYPE_STATE.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      &nbsp;<span class="userlable">(STATE)</span> 
      <%} %>
      <%if(Constants.ENTITYTYPE_DISTRICT.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      &nbsp;<span class="userlable">(DISTRICT)</span> 
      <%} %>
      <%if(Constants.ENTITYTYPE_BLOCK.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      &nbsp;<span class="userlable">(BLOCK)</span> 
      <%} %>
       <%if(Constants.ENTITYTYPE_VILLAGE.equalsIgnoreCase(loginvo.getOldEntityType())){ %>
      &nbsp;<span class="userlable">(VILLAGE)</span> 
      <%} %>
       <bean:write name="user" property="userName" />
       
       <%} %>
      </span></td>
      <td width="380"><ul>
      
      
          <li><a href="javascript:openFile('1')" >Change Role</a></li>
        
          <li><span class="linksdivider">I</span></li>
       
          <li><a href="javascript:openFile('2')" >Change Password</a></li>
          <li><span class="linksdivider">I</span></li>
            <li><a href="javascript:window.location ='SGSYHelp.jsp'" target="_blank">Help</a> </li>
          <li><span class="linksdivider">I</span></li>
          <li><a href="javascript:getTracker()" >Logout</a> </li>
        </ul></td>
    </tr>
  </table>
  </div>
<!-- Header Menu End -->
  
  <!-- Menu Mart And Body Part -->
  <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr valign="top">
 
  <td  align="left" class="mainpanebgcolor">
  <table>
   <tiles:insert attribute="body"/>
  </table>
  </td></tr>
  </table>
  
  <table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td>
  <!--Footer navigation starts here-->
  <tiles:insert attribute="footer" ignore="true" />
  <!--Footer message section ends here-->
  </td>
  
  </tr>
  </table>
</div>
<!-- MAIN PANEL DIV END -->
</body>
</html>
