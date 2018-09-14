
<%@ page isErrorPage="true" %>
<%@page import="java.util.Enumeration"%>
<%@page import="com.infotech.sgsy.login.LoginMasterAction"%>
<html:html>
<body>
<form >
	<br/>
	<table width="90%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="SubMenuHeader" align="center">&nbsp;
			</td>
		</tr>
	</table>
	<table width="90%" align="center" >
		<tr>  
			<td class="SubMenuHeader" align="center">&nbsp;</td>
		</tr>
	</table>
	<table width="90%" align="center" >
		<tr>
			<td class="SubMenuHeader" align="center">&nbsp;</td>
		</tr>
	</table>
	<table width="90%" align="center">
		<tr>
			<td class="SubMenuHeader" align="center">&nbsp;</td>
		</tr>
	</table>
	
	<%
	try{
		new LoginMasterAction().logOut(null,null,request,response);
	}catch(Exception e){
	}
	  %>
	
	<table width="90%" align="center"  >
		<tr>
		<td width="80%" align="left">
		<img src="images/404errorpage.jpg" />
		</tr>
		<tr>
			<td width="100%" align="center">		
			<a href='<%=request.getContextPath()%>'> Click Here To Login </a>
			</td>
		</tr>	
	</table>
	</form>
</body>

</html:html>

<script language="JavaScript"  type="text/javascript">
function globalCancel()
{
	
	document.forms[0].action = "login.do";
   	document.forms[0].submit();
}

</script>