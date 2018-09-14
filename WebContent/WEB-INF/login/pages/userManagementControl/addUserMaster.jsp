<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<script type="text/javascript" src="javaScript/md5.js"></script> 
<script type="text/javascript">

$(document).ready(function(){
	$('#repassword,#password').bind("cut copy paste",function(e) {
		e.preventDefault();
	});
	var forEdit=$('#idForEdit').val()+"abc";
	if(forEdit!="abc"){
		$('#repassword').val($('#password').val());
		$('#loginId').prop('readonly',true);
		$('#password').prop('readonly',true);
		$('#repassword').prop('readonly',true);
		getAssignState();
	}
	$('#example').DataTable();

});


	function saveRecord(){
		var idForEdit=$('#idForEdit').val()+"abc"; 
		if(idForEdit!="abc"){
			var status=window.confirm("Do you realy want to Update this FormName and Description?");
			if(status==true){
				document.userMasterBean.action = "userMaster.do?methodName=updateUserMaster"+"&"+tokenParameter+"="+tokenValue;
				document.userMasterBean.submit();
			}
		}
		else{
			var loginId = document.userMasterBean.loginId.value;
			var url = "userMaster.do?methodName=checkRecord&formName="+loginId;
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponse;
			req.send(null);
		}
	}
	function handleHttpResponse(){
		if (req.readyState == 4){	
	    	var response = req.responseText; 
	    	if(response == 'true'){
		     	alert("The User you Are Trying to add is Already Exsist");
		     	return;
	    	}else{
		         document.userMasterBean.loginId.focus();
			     save();
	    	}
	  	}
	}
	function getAssignState(){
		var x=$('#roleId :selected').text();
		if(x!='SRLM ADMIN'){
			$('#stateId').prop('disabled',true);
		}
		else{
			$('#stateId').prop('disabled',false);
		}
		if(x!='CTSA'){
			$('#ctsaId').prop('disabled',true);
		}
		else{
			$('#ctsaId').prop('disabled',false);
		}
		
	}
	function save(){
		/*
		if(document.userMasterBean.roleName.value == "" ){
	  		var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.userMasterBean.roleName.focus();
	  		return false;
  		} 
  		var roleName=document.userMasterBean.roleName.value;
		alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
		if( !(alphnum.test(roleName)) ) {
			var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
			alert(mess);
			document.userMasterBean.roleName.value ="";
			document.userMasterBean.roleName.focus();
			return false;
		}
  		if( document.userMasterBean.roleDescription.value == "" ){
	  		var arg='<bean:message key="access.addRole.roleDesc.label" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.userMasterBean.roleDescription.focus();
	  		return false;
  		}*/
  		if (!((document.userMasterBean.password.value.search(/[a-z]+/) > -1) && (document.userMasterBean.password.value.search(/[A-Z]+/) > -1)&& (document.userMasterBean.password.value.search(/[0-9]+/) > -1) && (document.userMasterBean.password.value.search(/[!@#$%^&*()_]+/) > -1))) {
  			mess = "Password must contain at least one uppercase letter, one lowercase letter, one numeral and one special charecters.\n";
  	    	alert(mess);
  	    	document.password.focus();
  	    	return false;
  		}
  		if ($('#repassword').val()!=$('#password').val()) {
  			mess = "passowrd and Repassword Must be Same";
 	    	alert(mess);
 	    	document.password.focus();
 	    	return false;
 		}
  		
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
		if(status==true){
			var password = hex_md5($('#password').val());  	 
		  	$('#password').val(password);
		  	$('#repassword').val($('#password').val());
  			document.userMasterBean.action = "userMaster.do?methodName=saveUserMaster"+"&"+tokenParameter+"="+tokenValue;
			document.userMasterBean.submit();
  	 	}
	}
	function clearField(){
	
	}
	function editRecord(id){
		$('#IdForAction').val(id);
		document.userMasterBean.action = "userMaster.do?methodName=editUserMaster"+"&"+tokenParameter+"="+tokenValue;
		document.userMasterBean.submit();
	}
	function changePassword(id){
		$('#IdForAction').val(id);
		document.userMasterBean.action = "userMaster.do?methodName=editUserPassword"+"&"+tokenParameter+"="+tokenValue;
		document.userMasterBean.submit();
	}
	function deleteRecord(id){
		$('#IdForAction').val(id);
		var status=window.confirm("Do you realy want to delete this role?");
		if(status==true){
			document.userMasterBean.action = "userMaster.do?methodName=deleteUserMaster"+"&"+tokenParameter+"="+tokenValue;
			document.userMasterBean.submit();
		}
	}
	
</script>
<!--Main form section starts here-->
<html:form action="login/userMaster">
	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Add User</td>
	   </tr>
	</table>
	<table width="100%" >
		<tr>
			<td>
			<input type="hidden" name="id" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${userMasterBean.editId}"/>
			
			<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>Login Id<span class="text-error">*</span></th>
					<td><span class="text-error"><html:text property="loginId" styleId="loginId" size="31" maxlength="30" onblur="this.value=this.value.toUpperCase();"  value="${userMasterBean.loginId}"/></span></td>
				</tr>
				<tr>
					<th>Assign Role<span class="text-error">*</span></th>
					<td>
						<html:select property="roleId" styleId="roleId" value="${userMasterBean.roleId}" onchange="getAssignState()">
							<html:option value="0">Select Role</html:option>
	              				<logic:present name="roleMasterList">
								<logic:iterate id="project" name="roleMasterList">
									<html:option value="${project['id']}">${project['roleName']}</html:option>
								</logic:iterate>
							</logic:present>
						</html:select> 
					</td>
				</tr>
				<c:if test="${RoleId eq '5'}">
					<tr>
						<th>Assign State<span class="text-error">*</span></th>
						<td>
							<html:select property="state" styleId="stateId" value="${userMasterBean.state}">
								<html:option value="0">Select State</html:option>
		              				<logic:present name="stateList">
									<logic:iterate id="project" name="stateList">
										<html:option value="${project['stateId']}">${project['stateName']}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select> 
						</td>
					</tr>
					<tr>
						<th>Assign CTSA<span class="text-error">*</span></th>
						<td>
							<html:select property="ctsa" styleId="ctsaId" value="${userMasterBean.ctsa}">
								<html:option value="0">Select CTSA</html:option>
		              				<logic:present name="ctsaList">
									<logic:iterate id="project" name="ctsaList">
										<html:option value="${project['id']}">${project['ctsaCode']}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select> 
						</td>
					</tr>
				</c:if>
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
					<th>User Status<span class="text-error">*</span></th>
					<td><span class="text-error"> 
						<html:select property="loginStatus" styleId="loginStatusId" value="${userMasterBean.loginStatus}">
							<option value="0" selected="selected">Select Status</option>
	              			<option  value="Locked">Locked</option>
							<option value="Active">Active</option>
						</html:select>  
						</span>
					</td>
				</tr>
				<tr>
					<th>Mobile / Telephone No</th>
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
				<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
<%-- 				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/>  --%>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<table id="example" class="display">
			<thead>
			<tr>
			<th>Sr NO</th>
			<th>Login Id</th>
			<th>User Name</th>
			<th>Mobile No</th>
			<th>Email Id</th>
			<th>Edit</th>
			<th>Delete</th>
			<th>Change Password</th>
			</tr></thead>
			<tfoot>
			<tr>
			<th>Sr NO</th>
			<th>Login Id</th>
			<th>User Name</th>
			<th>Mobile No</th>
			<th>Email Id</th>
			<th>Edit</th>
			<th>Delete</th>
			<th>Change Password</th>
			</tr>
			</tfoot>
			<tbody>
				<logic:present name="userMastersList">
					<c:set var="indexCount" value="1" />
					<logic:iterate id="list" name="userMastersList"	indexId="index">
						<tr>
						
							 <td>${indexCount}</td>
							<td>${list['loginId']}</td>
							<td>${list['userName']}</td>
							<td>${list['mobileNo']}</td>
							<td>${list['emailId']}</td>
							<td><a href="#"
								onclick="editRecord(${list['id']})"><img
									src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
							<td><a href="#"
								onclick="deleteRecord(${list['id']})"><img
									src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
							<td><a href="#"
								onclick="changePassword(${list['id']})"><img
									src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>	 	
							<c:set var="indexCount" value="${indexCount + 1}" />
						</tr>
					</logic:iterate>
				</logic:present>
			</tbody>
			
			</table>
			
			</td>
		</tr>
	</table>
</html:form>
<!--Main form section ends here-->