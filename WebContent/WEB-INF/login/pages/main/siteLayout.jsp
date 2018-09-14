<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/packtag.tld" prefix="pack"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.infotech.sgsy.login.LoginVO"%>
<%@page import="com.infotech.sgsy.util.Constants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" ignore="true" /></title>
<link type="text/css" href="css/main_style_sl.css" rel="stylesheet"  />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css" media="screen" ></link>
<%String loggedFlag="";
if(request.getAttribute("logFlag")!=null){
	loggedFlag=(String)request.getAttribute("logFlag");
}
%>
<!-- These java script for yahoo pop remark -->
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="css/displayTag.css" />

<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script language="javascript" src="javaScript/dhtmlgoodies_calendar.js"></script>
<script language="javascript" src="javaScript/common.js"></script>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/livevalidation_standalone.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/nrlm.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/AjaxScript.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/autoNumeric.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.blockUI.js"></script>


<script language="javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>';        

function getTracker(){
	//loginForm
	document.forms[0].action="login.do?methodName=logOut&Header="+tokenValue;
	document.forms[0].submit(); 
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
//function displaing a warning 
function displayWarning(){
	alert('Warning, your session has been expired. Please Login again.'); 
	getTracker();
} 

//let's call this function after 30 minutes of loading this page to the browser 
setTimeout("displayWarning()",30*60*1000);
</script>

</head>
<body>
<!-- MAIN PANEL DIV STARTS -->
<div id="main" >
	
<!--  Page Header Start -->
<div>
     <tiles:insert attribute="header" ignore="true">
			<tiles:put name="title" beanName="title" beanScope="tile" />
		</tiles:insert>
</div>
<!--  Page Header End -->
<div>	 
<div id="cols">
	<div id="cols-in" class="box">
		<div id="aside">
 			<tiles:insert attribute="menu"/>
 		</div>
		<div id="content" > 
		 <tiles:insert attribute="body"/>	   
	 	</div>
	 	
	  </div>
	  

<!--Footer navigation starts here-->
	<tiles:insert attribute="footer" ignore="true" /></div>
<!--Footer message section ends here-->
</div>
</div>
<script>
jQuery(function($) {
	$('.hasDatepicker').each(function(){
		$(this).attr({"style":"background-color:white"});
	});
});
</script>
</body>
</html>
