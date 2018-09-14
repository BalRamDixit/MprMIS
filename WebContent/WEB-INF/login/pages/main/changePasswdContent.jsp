<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javaScript/md5.js"></script> 
<script language=javascript>
function changePassword(){

	var password = document.loginForm.oldPassword.value;
	if(password=="" || password == null)
	{
		alert("Please Enter the Old Password");
	    return;
	}
	 password = hex_md5( document.loginForm.oldPassword.value);
	  	
	 document.loginForm.oldPassword.value =password ;
	  	
	var url = "login.do?methodName=checkOldPassword&oldPassword="+password;
		if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
		//req = new ActiveXObject("Msxml2.XMLHTTP");
		req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("GET", url, true);
		http_request.setRequestHeader(tokenParameter, tokenValue);
		req.onreadystatechange = handleHttpResponse;
		req.send(null);
	}
	function handleHttpResponse(){
	 if (req.readyState == 4) 
	  {	
	    var response = req.responseText; 
	  
	    if(response != 'true'){
		     alert("The Old Password must be matched");

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
	     	alert("Please Enter the Old Password");
	      	return;
	     }
	     if(newPWD=="" || newPWD == null ){
	     	alert("Please Enter the New Password");
	      	return;
	     }
	     if(newPWD.length <=7 ){
	      	alert("Password must be greater than 8 charcters");
	      	return;
	     }
	     if(newPWD.length >12 ){
	      	alert("Password must be less than 12 charcters");
	      	return;
	     }
	      if(newPWD == oldPWD){
	     	alert("New Password cannot be same as the old password.");
	     	return;
	     }       
	    if(newPWD!=confirmPWD){
	     	alert("New Password and Confirm Password does not match.");
	     	return;
	     }  
	    
	    if(!((newPWD.search(/[a-z]+/) > -1) && (newPWD.search(/[A-Z]+/) > -1)&& (newPWD.search(/[0-9]+/) > -1) && (newPWD.search(/[!@#$%^&*()_]+/) > -1))) 
	    {
	 		error ="New Password must contain at least one uppercase letter,one lowercase letter,one numeral and one special charecters.\n";
	    	alert(error);
	    	return;
	  	}
	  	
	  	
	    document.loginForm.oldPassword.value=hex_md5( document.loginForm.oldPassword.value);
	    document.loginForm.newPassword.value=hex_md5( document.loginForm.newPassword.value);
	   	document.loginForm.confirmPassword.value=hex_md5( document.loginForm.confirmPassword.value);
	   
	   
	      document.loginForm.action="login.do?methodName=changePassword&"+tokenParameter+"="+tokenValue;
	      document.loginForm.submit();
	    }
	    
	function closePage()
	{
		var status=window.confirm("Are you sure you want to Close ");
		if(status==true){
		document.loginForm.action = "login.do?methodName=showChangePasswordPage&pageClose=backToMainPage&"+tokenParameter+"="+tokenValue;
		document.loginForm.submit();
		}
	}

	</script>
</head>



 

<body onload="document.loginForm.oldPassword.focus();">
<!--Main form section starts here-->
<html:form action="login/login">
	<table width="310" cellpadding="0" cellspacing="0" id="datagrid" align="center">
							<tr>
								<th colspan="2" ><h2>Password must contain at least one uppercase letter, one lowercase letter, one numeral and one special charecters.</h2></th>
							</tr>
							<tr>
								<td width="87" class="userdetails"><bean:message  key="login.oldPassword.label" /></td>
								<td width="174"><html:password property="oldPassword" value="" />
								</td>
							</tr>

							<tr>
								<td class="userdetails"><bean:message  key="login.newPassword.label" /></td>
								<td><html:password property="newPassword" value="" /></td>
							</tr>
							
							<tr>
								<td class="userdetails"><bean:message  key="login.confirmPassword.label" /></td>
								<td><html:password property="confirmPassword" value="" onkeydown="Javascript: if (event.keyCode==13) changePassword()"/></td>
							</tr>
							

							<tr>
								<td >&nbsp;</td>
								<td><label><html:button styleClass="button" property="next" value="Save" onclick="changePassword()"></html:button> </label>
								&nbsp;<label><html:button styleClass="button" property="next" value="Cancel" onclick="closePage()"></html:button> </label>
								</td>
							</tr>
							
						</table>
</html:form>
</body>
</html>
<!--Main form section ends here-->
