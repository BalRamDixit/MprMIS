
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
		<td width="80%" align="left">
		<img src="images/403errorpage.jpg" />
		</tr>
		
		<tr>		
			<td width="100%" align="center"> <font color="red">Request could not be processed properly.
			Please Try Again </font>
			</td>
		</tr>
		<tr><td>	<br><br/>
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
