
<%@ page isErrorPage="true" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<body>
<form >

	<br/><br/>
	<table width="90%" align="center" >
		
		<tr>
		<td width="90%" align="center">
		<img src="./../images/403errorpage.jpg" />
		</tr>
		
	
		<tr><td align="center">
		<br/><br/>
			<a href='<%=request.getContextPath()%>' style=" text-decoration: none;"><span>GO TO HOME</span></a>
		</td></tr>
		</table>			
</form>
</body>
</html:html>

<script language="javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 

	function confirmExit(){		
	if(window.event.clientY < 0 ){
			var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			xmlhttp.open("GET","login.do?methodName=logOut&"+tokenParameter+"="+tokenValue,false);
			xmlhttp.send();
		}
		if (window.XMLHttpRequest){
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET","login.do?methodName=logOut&"+tokenParameter+"="+tokenValue,false);
			xmlhttp.send();
		}
	}		
} 
</script>
