<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<script type="text/javascript" src="javaScript/md5.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		$('#repassword,#password').bind("cut copy paste",function(e) {
			e.preventDefault();
		});
	});
	function save(){
		if (!((document.userMasterBean.password.value.search(/[a-z]+/) > -1) && (document.userMasterBean.password.value.search(/[A-Z]+/) > -1)&& (document.userMasterBean.password.value.search(/[0-9]+/) > -1) && (document.userMasterBean.password.value.search(/[!@#$%^&*()_]+/) > -1))) {
			mess = "Password must contain at least one uppercase letter, one lowercase letter, one numeric and one special charecters.\n";
  	    	alert(mess);
  	    	document.userMasterBean.password.focus();
  	    	return false;
  		}
  		if ($('#repassword').val()!= $('#password').val()) {
  			mess = "passowrd and Repassword Must be Same";
 	    	alert(mess);
 	    	document.userMasterBean.password.focus();
 	    	return false;
 		}
  		
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
		if(status==true){
			var password = hex_md5($('#password').val());  	 
		  	$('#password').val(password);
		  	$('#repassword').val($('#password').val());
  			document.userMasterBean.action = "userMaster.do?methodName=updateUserPassword"+"&"+tokenParameter+"="+tokenValue;
			document.userMasterBean.submit();
  	 	}
	}
	
</script>
<!--Main form section starts here-->
<html:form action="login/userMaster">
	<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Edit User Password</td>
			   </tr>
			</table>
	<table width="100%" >
		<tr>
			<td>
				<h2>Password must contain at least one uppercase letter, one lowercase letter, one numeric and one special charecters.</h2>
			</td>
		</tr>
		<tr>
			<td>
			<input type="hidden" name="id" id="IdForAction"/>
			
			<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>Login Id<span class="text-error">*</span></th>
					<td><span class="text-error"><html:text property="loginId" styleId="loginId" size="31" maxlength="30" onblur="this.value=this.value.toUpperCase();" value="${userMasterBean.loginId}"/></span></td>
				</tr>
				<tr>	
					<th>User Name<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="userName" styleId="userName" size="31" maxlength="30" onblur="this.value=this.value.toUpperCase();" value="${userMasterBean.userName}"/></span></td>
				</tr>
				<tr>	
					<th>Password<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:password property="password"  styleId="password" size="60" maxlength="60" value="${userMasterBean.password}"/> </span></td>
				</tr>
				<tr>
					<th>Re-Enter Password<span class="text-error">*</span></th>
					<td><span class="text-error"> <input type="password" id="repassword"  /> </span></td>
				</tr>
				<tr>
					<th>Mobile No</th>
					<td><span class="text-error"> <html:text property="mobileNo" styleId="mobileNo" size="60" maxlength="60" value="${userMasterBean.mobileNo}"/> </span></td>
				</tr>
				<tr>	
					<th>Email Id</th>
					<td><span class="text-error"> <html:text property="emailId" styleId="emailId" size="60" maxlength="60" value="${userMasterBean.emailId}"/> </span></td>
				</tr>
				<tr>
					<td><font color="red" size="1">&nbsp;<html:errors property="loginId"/></font></td>
					<td><font color="red" size="1">&nbsp;<html:errors property="userName"/></font></td>
					<td><font color="red" size="1">&nbsp;<html:errors property="password"/></font></td>
					<td><font color="red" size="1">&nbsp;<html:errors property="mobileNo"/></font></td>
					<td><font color="red" size="1">&nbsp;<html:errors property="emailId"/></font></td>
					<td></td>
				</tr>
			</table>
			<div align="center">
				<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="save()"/> 
<%-- 				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/>  --%>
			</div>
			</td>
		</tr>
		
	</table>
</html:form>
<!--Main form section ends here-->