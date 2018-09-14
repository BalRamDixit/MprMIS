<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:getAsString name="title" ignore="true" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link  type="text/css" href="css/main_style.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css" media="screen" ></link>

<script language="javascript" src="javaScript/dhtmlgoodies_calendar.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script type="text/javascript" src="javaScript/jquery.js" ></script>
<script type="text/javascript" src="menuJquery/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="javaScript/common.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/livevalidation_standalone.js"></script>
</head>
<body>

<!-- MAIN PANEL DIV STARTS -->
<div id="main" >
<!-- HEADER DIV START -->
   <div style="background-color: #51398D; height: 80px;">
        <img src="images/ddukgylogo.jpg" height="80px" alt="" />
    </div>
    <hr class="noscreen" />
    <!-- HEADER MENU DIV START -->
   <div id="nav" class="box">
        <ul id="navmenu">
            <li><a href="outerAction.do?methodName=showIndex">Home</a></li>
     	</ul>
        <p id="feeds">
            <a href="login/login.do?methodName=showMainPage&reqtrack=<%=request.getSession().getAttribute("TRACKERID")%>" class="ico-login">Login </a>    
        </p> 
       
    </div>
   	<div style="border-top: solid 2px gray; border-bottom: solid 2px gray;">
    <hr class="noscreen" />
 	<!-- HEADER MENU DIV END -->
     <hr class="noscreen" />
<!-- HEADER DIV END -->

<!-- HEADER-MENU DIV START -->
    <div>
		<tiles:insert attribute="header" ignore="true">
			<tiles:put name="title" beanName="title" beanScope="tile" />
		</tiles:insert>
	</div>
<!-- HEADER-MENU DIV END -->
	
<!-- CONTAINER DIV START -->
	<div ><tiles:insert attribute="body" /></div>
	</div>
	<tiles:insert attribute="footer" ignore="true" />
<!-- CONTAINER DIV END -->

</div>
<!-- MAIN PANEL DIV END -->

</body>
</html>
