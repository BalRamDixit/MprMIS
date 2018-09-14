<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<!--script for menu-->
<script type="text/javascript" src="javaScript/md5.js"></script> 
<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;


//window.onbeforeunload=confirmExit;
function confirmExit(){
		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 



function changePassword(){

var password = document.loginForm.oldPassword.value;
if(password=="" || password == null){
	alert("<bean:message key="msg.enterOldPassword" />");
    return;
}
  var password = hex_md5( document.loginForm.oldPassword.value);
  
  if(document.loginForm.oldPassword.value == "" ){
  alert("Please Enter Password.");return ;
  }
	document.loginForm.oldPassword.value =password ;	
	
var url = "login.do?methodName=checkOldPassword&oldPassword="+password;
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	//req = new ActiveXObject("Msxml2.XMLHTTP");
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponse;
	req.send(null);
}
function handleHttpResponse(){


 if (req.readyState == 4){	


    var response = req.responseText; 
  
    if(response != 'true'){
	     alert("<bean:message key="msg.oldPasswordMatching" />");

	     return;
    }else{
         
         document.loginForm.newPassword.focus();
		
	     changePasswordOfUSer();
    }
  }
}
    
function changePasswordOfUSer(){


    var oldPWD = document.loginForm.oldPassword.value;
    var newPWD = document.loginForm.newPassword.value;
   	var confirmPWD = document.loginForm.confirmPassword.value;
     
      if(oldPWD=="" || oldPWD == null ){
     	alert("<bean:message key="msg.enterOldPassword" />");
      	return;
     }
     
     
     
     if(newPWD=="" || newPWD == null ){
     	alert("<bean:message key="msg.enterNewPassword" />");
      	return;
     }
     if(newPWD.length <=6 ){
      	alert("<bean:message key="msg.passwordGreaterThan" />");
      	return;
     }
      if(newPWD.length >16 ){
      	alert("<bean:message key="msg.passwordLessThan" />");
      	return;
     }
      if(newPWD == oldPWD){
     	alert("<bean:message key="msg.passwordCannotBeSame"/>");
     	return;
     }       
    if(newPWD!=confirmPWD){
     	alert("<bean:message key="msg.passwordnotmatching"/>");
     	return;
     } 
     
    if(!((newPWD.search(/[a-z]+/) > -1) && (newPWD.search(/[A-Z]+/) > -1)&& (newPWD.search(/[0-9]+/) > -1) && (newPWD.search(/[!@#$%^&*()_]+/) > -1))) 
    {
 		error = "New Password must contain at least one uppercase letter, one lowercase letter, one numeric and one special charecters.\n";
    	alert(error);
    	return;
  	}
     var status= true;
     if(status==true){
   

    document.loginForm.oldPassword.value=hex_md5( document.loginForm.oldPassword.value);
    document.loginForm.newPassword.value=hex_md5( document.loginForm.newPassword.value);
   	document.loginForm.confirmPassword.value=hex_md5( document.loginForm.confirmPassword.value);
   
      document.loginForm.action="login.do?methodName=changePassword&"+tokenParameter+"="+tokenValue;
      document.loginForm.submit();
      
      
      
      
    }
}
function closePage()
{
	var status=window.confirm("<bean:message key="alert.close.form"/>");
	if(status==true){
	document.loginForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
	document.loginForm.submit();
	}
}
function clearField()
{
	var status=window.confirm("<bean:message key="msg.clear"/>");
	if(status==true){
	document.loginForm.action = "login.do?methodName=showChangePasswordPage&"+tokenParameter+"="+tokenValue;
	document.loginForm.submit();
	}
	
}


// disable the backward-forward button of browser
       
     
    function disableKeys()
	{
		window.history.forward(); 
	}
   setTimeout("disableKeys()", 0);
   window.onunload=function(){null};



// disable the mouse right button
   
   	var message="Function Disabled!";

	function clickIE4()
	{
		if (event.button==2)
		{
			//alert(message);
			return false;
		}
	}

function clickNS4(e)
	{
		if (document.layers||document.getElementById&&!document.all)
		{
			if (e.which==2||e.which==3)
			{
				//alert(message);
				return false;
			}
		}
	}

	if (document.layers)
	{
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown=clickNS4;
	}
	else if (document.all&&!document.getElementById)
	{
		document.onmousedown=clickIE4;
	}

	document.oncontextmenu=new Function("return false")
	






</script>
<!--Main form section starts here-->
</head>
<body onload = disableKeys()>
<html:form action="login/login">
	<table class="pageHeaderTable" width="100%" >
				<tr>
                	<td class="pageHeader" align="center">Change Password</td>
              	</tr>
    </table>
	<table width="100%">
		<tr>
			<td>
		 		 <% if( request.getAttribute("passwordChanged") !=null &&  ( request.getAttribute("passwordChanged")).toString().trim().length() > 0  )  {  %> 
				
							<%=request.getAttribute("passwordChanged")%>
					 <% } %>
			<table align="center" width="100%" class="inputTBL">
                    <tr>
                      <th colspan="2" ><h2 style="text-transform: capitalize;">Password must contain at least one uppercase letter, one lowercase letter, one numeric and one special charecters.</h2></th>
                    </tr>
                    <tr>
                      <th width="200px;"><bean:message  key="login.user.label" /></th>
                      <bean:define id="user" name="loginVO" type="com.infotech.sgsy.login.LoginVO"  ></bean:define>
                      <td><span><bean:write name="user" property="userName" /></span></td>
                    </tr>
                    <tr>
                      <th><bean:message  key="login.oldPassword.label" /><span class="mandatory">*</span></th>
                      <td><html:password property="oldPassword" value="" /></td>
                    </tr>
                    <tr>
                      <th><bean:message  key="login.newPassword.label" /><span class="mandatory">*</span></th>
                      <td><html:password property="newPassword" value="" />
                      
                      </td>
                    </tr>
                    <tr>
                      <th><bean:message  key="login.confirmPassword.label" /><span class="mandatory">*</span></th>
                      <td><html:password property="confirmPassword" value="" onkeydown="Javascript: if (event.keyCode==13) changePassword()"/></td>
                    </tr>
			</table>
			
			</td>
		</tr>
		<tr>
                      <td height="40" colspan="2" align="center" >
                      <html:button styleClass="button" property="saveButton" value="Save" onclick="changePassword()"></html:button>
                      <html:button styleClass="button" property="clearButton" value="Clear" onclick="clearField()"></html:button>
                      <html:button styleClass="button" property="closeButton" value="Close" onclick="closePage(tokenParameter,tokenValue)" ></html:button>
                      </td>
                    </tr>
	</table>
	
</html:form>
</body>

<!--Main form section ends here-->