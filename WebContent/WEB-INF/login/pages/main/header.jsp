<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
  
 <script language="javascript"> 
  var tokenParameter='reqtrack';
  var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 

  function changeLanguage(var1){
	document.forms[0].action = "login/language.do?methodName=changeLanguage&lang="+var1.value;
	document.forms[0].submit();
	}
	 
// disable the mouse right button   
   	var message="Function Disabled!";
	function clickIE4(){
		if (event.button==2){
			//alert(message);
			return false;
		}
	}

	function clickNS4(e){
		if (document.layers||document.getElementById&&!document.all){
			if (e.which == 2 || e.which == 3){
				//alert(message);
				return false;
			}
		}
	}

	if (document.layers){
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown=clickNS4;
	}
	else if (document.all&&!document.getElementById){
		document.onmousedown=clickIE4;
	}

	document.oncontextmenu=new Function("return false")
	
</script>

<head>
<script src="javaScript/stuHover.js" type="text/javascript"></script>
</head>

<body>

<html:form action="login/language">
   <!-- HEADER MENU DIV START -->
   <div id="nav" class="box">
<%-- 			<a href="login/login.do?methodName=showMainPage&reqtrack=<%=request.getSession().getAttribute("TRACKERID")%>">Login</a>                --%>
     </div>
    <hr class="noscreen" />
  </html:form>
</body>  
  