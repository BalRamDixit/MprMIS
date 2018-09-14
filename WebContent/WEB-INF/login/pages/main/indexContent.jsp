<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
	String loggedFlag = "";
	if (request.getAttribute("logFlag") != null) {
		loggedFlag = (String) request.getAttribute("logFlag");
	}
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="javaScript/md5.js"></script> 
<style type="text/css">
p.single {line-height:0.75cm;}
</style>

<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>' ; 

function submitLogin(){
if((document.loginForm.userid.value == "") && (document.loginForm.password.value == "")){
	alert("Please Enter User Id and Password.");
  	document.loginForm.userid.focus();
  	return false;
} 
if( document.loginForm.userid.value == "" ){
  	alert("Please Enter User Id.");
  	document.loginForm.userid.focus();
  	return false;
  	} 
  	var userid=document.loginForm.userid.value;
	/* alphnum = /^[A-Za-z][A-Za-z0-9]*$/;
	if( !(alphnum.test(userid)) ) {
	alert("User Id is not valid, Please enter a valid User Id" );
	document.loginForm.userid.value ="";
	document.loginForm.userid.value.focus();
	return false;
	} */
	  	if( document.loginForm.password.value == "" )
	  	{
	  	alert("Please Enter Password.");
	  	document.loginForm.password.focus();
	  	return false;
	  	} 	 
  	var password = hex_md5( document.loginForm.password.value);  	 
  	password = hex_md5(password + tokenValue);	
  	document.loginForm.password.value =password ;		
	document.loginForm.action="login.do?methodName=login&"+tokenParameter+"="+tokenValue;
	document.loginForm.submit();	
}	

function register(){
	document.forms[0].action="registrationAction.do?methodName=registration&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}
function existingPIA(){
	document.forms[0].action="registrationAction.do?methodName=authenticatePIA&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
}

function blockAutoComplete(){
		document.getElementById('auto_off').setAttribute("autocomplete","off");
		document.getElementById("passID").setAttribute("autocomplete","off");
}
</script>
</head>

<!--Main form section starts here-->

<body onload="blockAutoComplete()">
	<!-- HEADER MENU DIV START -->
<!-- 	<div id="nav" class="box"> -->
<!-- 		<ul> -->
<!-- 			<li class=""><a -->
<!-- 				href="../outerAction.do?methodName=showIndex">Home -->
<!-- 					Page</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 		<p id="feeds"> -->
<!-- 		</p> -->
<!-- 	</div> -->
	<hr class="noscreen" />
	<!-- HEADER MENU DIV END -->
	<!-- class="mainpane" -->
	<html:form action="login/login">

		<div id="cols">
			<div id="cols-in" class="box">
				<div id="content">
					<h2 class="title-01">Welcome to MPR System</h2>					
																
				</div>
				
				
				
				<div id="aside">
					<h4 class="title-03">USER DETAILS</h4>
					<div class="in">
						<table>
							<tr>
								<td><bean:message key="login.userID.label" /></td>
								<td><html:text property="userid" value=""
										styleId="auto_off" /></td>
							</tr>
							<tr>
								<td><bean:message key="login.password.label" />
								</td>
								<td><html:password property="password" styleId="passID"
										onkeydown="Javascript: if (event.keyCode==13) submitLogin()"
										value="" />
								</td>
							</tr>
							
							 
							<tr>
								<!-- <td>&nbsp;</td> -->
								<td colspan="2"><html:button styleClass="button" property="next" value="Login" onclick="submitLogin()" />																
								</td>
							</tr>
							<tr>
								<td colspan="2"><p class="nomb">Please remember to
										logout when you have finished your session</p>
								</td>
							</tr>
						</table>
						<html:errors></html:errors>
						<%
							if (request.getAttribute("logoutMsg") != null
										&& (request.getAttribute("logoutMsg")).toString()
												.trim().length() > 0) {
						%>
						<%=request.getAttribute("logoutMsg")%>
						<%
							}
						%>
						<%
							if (request.getAttribute("passwordChanged") != null
										&& (request.getAttribute("passwordChanged")).toString()
												.trim().length() > 0) {
						%>
						<%=request.getAttribute("passwordChanged")%>
						<%
							}
						%>
						<logic:present name="failed" scope="session">
							<div
								style="color: #872657; font-family: Helvetica; font-weight: bold; font-size: 13px; text-align: center;">
								<bean:write name="failed" />
							</div>
						</logic:present>
					</div>
					<div><marquee><a href="files/DDUGKY_UserManual.pdf" target='_BLANK'>Click Here To Download User Manual For System</a> </marquee> </div>
					<div style="text-align: center;">
						Help Desk No <br>
						Mob 1:-8527638793<br>
						Mob 2:-8527636893
					</div>
				</div>
			</div>
		</div>
	</html:form>
</body>

</html>
<!--Main form section ends here-->
