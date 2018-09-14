<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<script type="text/javascript" src="javaScript/md5.js"></script> 
<style>
	.hideDiv{
		display: none;
	}
	
	.tableRow tr:nth-child(even) {
		background-color: #dbdbdb
	}
	
</style>
<script type="text/javascript">
String.prototype.replaceAt=function(index, character) {
    return this.substr(0, index) + character + this.substr(index+character.length);
}
$(document).ready(function(){
// 	$('#repassword,#password').bind("cut copy paste",function(e) {
// 		e.preventDefault();
// 	});
// 	var forEdit=$('#idForEdit').val()+"abc";
// 	alert(forEdit) 
// 	if(forEdit!="abc"){
// 		$('#repassword').val($('#password').val());
// 		$('#loginId').prop('readonly',true);
// 		$('#password').prop('readonly',true);
// 		$('#repassword').prop('readonly',true);
// 	}
	$('#example').DataTable();
	var idForEdit=$('#idForEdit').val()+"abc"; 
	if(idForEdit!="abc"){
		$('#insertNewDataDiv').html('');
		if($('#roleId').val()!='0'){
			$('#selectedRoleId').val($('#roleId').val());
			$('#radioButton1').prop("checked", true);
		}
		else{
			$('#selectedUserId').val($('#userId').val());
			$('#radioButton2').prop("checked", true);
		}
		$('input[name=permissionCharList]').each(function(){
			selectCheckBox($(this).attr('id'),$(this).val());
		});
		
	}
	else{
		$('#editOldDataDiv').html('');
	}
	$('#selectedRoleId').prop('disabled', 'disabled');
	$('#selectedUserId').prop('disabled', 'disabled');
	
});

	function selectCheckBox(id,dataForSet){
		if(dataForSet.charAt(0)=='1'){
			$('#add'+id).prop("checked", true);
		}
		if(dataForSet.charAt(1)=='1'){
			$('#view'+id).prop("checked", true);
		}
		if(dataForSet.charAt(2)=='1'){
			$('#assign'+id).prop("checked", true);
		}
	}
	function saveRecord(){
		var idForEdit=$('#idForEdit').val()+"abc"; 
		if(idForEdit!="abc"){
			var status=window.confirm("Do you realy want to Update this FormName and Description?");
			if(status==true){
				document.assignRoleMasterBean.action = "assignRoleMaster.do?methodName=updateAssignRoleMaster"+"&"+tokenParameter+"="+tokenValue;
				document.assignRoleMasterBean.submit();
			}
		}
		else{
			/*var loginId = document.assignRoleMasterBean.loginId.value;
			var url = "assignRoleMaster.do?methodName=checkRecord&formName="+loginId;
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponse;
			req.send(null);
			*/
			save();
		}
	}
	function handleHttpResponse(){
		if (req.readyState == 4){	
	    	var response = req.responseText; 
	    	if(response == 'true'){
		     	alert("<bean:message key="error.roleExist" />");
		     	return;
	    	}else{
		         document.assignRoleMasterBean.loginId.focus();
			     save();
	    	}
	  	}
	}
	function save(){
		/*
		if(document.assignRoleMasterBean.roleName.value == "" ){
	  		var arg='<bean:message key="label.roleName" />';  
	 
			alert(mess);
	  		document.assignRoleMasterBean.roleName.focus();
	  		return false;
  		}
  		var roleName=document.assignRoleMasterBean.roleName.value;
		alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
		if( !(alphnum.test(roleName)) ) {
			var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
			alert(mess);
			document.assignRoleMasterBean.roleName.value ="";
			document.assignRoleMasterBean.roleName.focus();
			return false;
		}
  		if( document.assignRoleMasterBean.roleDescription.value == "" ){
	  		var arg='<bean:message key="access.addRole.roleDesc.label" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.assignRoleMasterBean.roleDescription.focus();
	  		return false;
  		}
  		if (!((document.assignRoleMasterBean.password.value.search(/[a-z]+/) > -1) && (document.assignRoleMasterBean.password.value.search(/[A-Z]+/) > -1)&& (document.assignRoleMasterBean.password.value.search(/[0-9]+/) > -1) && (document.assignRoleMasterBean.password.value.search(/[!@#$%^&*()_]+/) > -1))) {
  			mess = "Password must contain at least one uppercase letter, one lowercase letter, one numeral and one special charecters.\n";
  	    	alert(mess);
  	    	document.password.focus();
  	    	return false;
  		}
  		if ($('#repassword').val()!=('#password').val()) {
  			mess = "passowrd and Repassword Must be Same";
 	    	alert(mess);
 	    	document.password.focus();
 	    	return false;
 		}
  		*/
  		$('#roleId').val($('#selectedRoleId').val());
  		$('#userId').val($('#selectedUserId').val());
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
		if(status==true){
			document.assignRoleMasterBean.action = "assignRoleMaster.do?methodName=saveAssignRoleMaster"+"&"+tokenParameter+"="+tokenValue;
			document.assignRoleMasterBean.submit();
  	 	}
	}
	function clearField(){
	
	}
	function editRecord(id){
		$('#IdForAction').val(id);
		document.assignRoleMasterBean.action = "assignRoleMaster.do?methodName=editAssignRoleMaster"+"&"+tokenParameter+"="+tokenValue;
		document.assignRoleMasterBean.submit();
	}
	function deleteRecord(id){
		$('#IdForAction').val(id);
		var status=window.confirm("Do you realy want to delete this role?");
		if(status==true){
			document.assignRoleMasterBean.action = "assignRoleMaster.do?methodName=deleteAssignRoleMaster"+"&"+tokenParameter+"="+tokenValue;
			document.assignRoleMasterBean.submit();
		}
	}
	function changeSelect(x){
		if(x==1){
			$('#selectedRoleId').prop('disabled', false);
			$('#selectedUserId').find('option:nth-child(1)').prop('selected',true).change();
			$('#selectedUserId').prop('disabled', 'disabled');
			$('#selectedUserId').val("0");
		}
		else{
			$('#selectedUserId').prop('disabled', false);
			$('#selectedRoleId').find('option:nth-child(1)').prop('selected',true).change();
			$('#selectedRoleId').prop('disabled', 'disabled');
			$('#selectedRoleId').val("0");
		}
	}
	function add(x){
		var data=document.getElementById("Project"+x).value;
		var checkStatus=document.getElementById("addProject"+x).checked;
		if(checkStatus){
			document.getElementById("Project"+x).value=data.replaceAt(0,"1");
		}
		else{
			document.getElementById("Project"+x).value=data.replaceAt(0,"0");
		}
	}
	function view(x){
		var data=document.getElementById("Project"+x).value;
		var checkStatus=document.getElementById("viewProject"+x).checked;
		if(checkStatus){
			document.getElementById("Project"+x).value=data.replaceAt(1,"1");
		}
		else{
			document.getElementById("Project"+x).value=data.replaceAt(1,"0");
		}
	}
	function assign(x){
		var data=document.getElementById("Project"+x).value;
		var checkStatus=document.getElementById("assignProject"+x).checked;
		if(checkStatus){
			document.getElementById("Project"+x).value=data.replaceAt(2,"1");
		}
		else{
			document.getElementById("Project"+x).value=data.replaceAt(2,"0");
		}
	}
	function checkAll(element,x){
		var checkStatus=element.checked;
		var elementToSelect="[id^="+x+"]";
		if(checkStatus){
			$(elementToSelect).each(function(){
				if(!this.checked){
					this.click();
				}
				
			});
		}
		else{
			$(elementToSelect).each(function(){
				if(this.checked){
					this.click();
				}
				
			});
		}
	}
	
</script>
<!--Main form section starts here-->
<html:form action="login/assignRoleMaster">
	<table width="100%" class="pageHeaderTable" >
		<tr>
			<td align="center" class="pageHeader">Assign Form Modules</td>
	   </tr>
	</table>
	<table width="100%" >
		<tr>
			<td>
			<input type="hidden" name="id" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${assignRoleMasterBean.editId}"/>
			
			<div id="insertNewDataDiv">
				<table width="60%" >
					<tr>
						<th colspan="2">Select either Role or User to Assign Module permission</th>
				   </tr>
				   <tr>
				   		<td><input type="radio" name="selectCondition" onclick="changeSelect('1')" id="radioButton1"/>Select Role</td>
				   		<td><input type="radio" name="selectCondition" onclick="changeSelect('2')" id="radioButton2"/>Select User</td>
				   </tr>
					<tr>
						<th>Role Name<span class="text-error">*</span>
							<span class="text-error">
								<html:hidden property="roleId" styleId="roleId" value="${assignRoleMasterBean.roleId}"></html:hidden>
								<select name="selectedRoleId" id="selectedRoleId">
									<option value="0">Select Role</option>
		               				<logic:present name="roleMasterList">
										<logic:iterate id="project" name="roleMasterList">
											<option value="${project['id']}">${project['roleName']}</option>
										</logic:iterate>
									</logic:present>
								</select>
							</span>
						</th>
						<th>Login Id<span class="text-error">*</span>
							<span class="text-error">
							<html:hidden property="userId" styleId="userId" value="${assignRoleMasterBean.userId}"></html:hidden>
								<select name="selectedUserId" id="selectedUserId">
									<option value="0">Select User</option>
	                				<logic:present name="userMasterList">
										<logic:iterate id="project" name="userMasterList">
											<option value="${project['id']}">${project['loginId']}</option>
										</logic:iterate>
									</logic:present>
								</select> 
						 </span>
						</th>
					</tr>
					<tr>
						<td><font color="red" size="1">&nbsp;<html:errors property="roleId"/></font></td>
						<td><font color="red" size="1">&nbsp;<html:errors property="userId"/></font></td>
					</tr>
				</table>
				<table width="100%" style="text-align: center;border-style: solid;border-color: gray"  class="tableRow">
					<tr>
						<th width="70%" style="text-align: left;">Form Module Names </th>
						<th width="10%">Add/Edit/Delete</th>
						<th width="10%" style="text-align: center;">View</th>
						<th width="10%" style="text-align: center;">Assign Module To Other User</th>
				   </tr>
				   <tr style="background-color: gray;">
						<th width="70%" style="text-align: left;">Check All</th>
						<th width="10%" style="text-align: center;"><input type="checkbox" onclick="checkAll(this,'add')"/> </th>
						<th width="10%" style="text-align: center;"><input type="checkbox" onclick="checkAll(this,'view')"/> </th>
						<th width="10%" style="text-align: center;"><input type="checkbox" onclick="checkAll(this,'assign')"/> </th>
				   </tr>
				   <logic:present name="formModulesList">
						<logic:iterate id="project" name="formModulesList">
							<c:if test="${project.checkForMasterModule eq true}">
								<tr style="background-color: green">
							</c:if>
							<c:if test="${project.checkForMasterModule ne true}">
								<tr>
							</c:if>
								<html:hidden property="formModuleIdList" value="${project['id']}"></html:hidden>
								<html:hidden property="permissionCharList" styleId="Project${project['id']}" value="000"></html:hidden>
								<td style="text-align: left;">${project['formName']} ${project.checkForMasterModule}</td>
					   			<td><input type="checkbox" id="addProject${project['id']}" onclick="add('${project['id']}')"/></td>
					   			<td><input type="checkbox" id="viewProject${project['id']}" onclick="view('${project['id']}')"/> </td>
					   			<td><input type="checkbox" id="assignProject${project['id']}" onclick="assign('${project['id']}')"/> </td>
						   </tr>
						</logic:iterate>
					</logic:present>
				   
				</table>
				
				<div align="center">
					<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
					<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
				</div>
			</div>
			
			
			
			
			
			
			<div id="editOldDataDiv">
				<table width="60%" >
					<tr>
						<th colspan="2">Select either Role or User to Edit Module permission</th>
				   </tr>
				   <tr>
				   		<td><input type="radio" name="selectCondition"  id="radioButton1"/>Role</td>
				   		<td><input type="radio" name="selectCondition"  id="radioButton2"/>User</td>
				   </tr>
					<tr>
						<th>Role Name<span class="text-error">*</span>
							<span class="text-error">
								<html:hidden property="roleId" styleId="roleId" value="${assignRoleMasterBean.roleId}"></html:hidden>
								<select name="selectedRoleId" id="selectedRoleId">
									<option value="0">Select Role</option>
		               				<logic:present name="roleMasterList">
										<logic:iterate id="project" name="roleMasterList">
											<option value="${project['id']}">${project['roleName']}</option>
										</logic:iterate>
									</logic:present>
								</select>
							</span>
						</th>
						<th>Login Id<span class="text-error">*</span>
							<span class="text-error">
							<html:hidden property="userId" styleId="userId" value="${assignRoleMasterBean.userId}"></html:hidden>
								<select name="selectedUserId" id="selectedUserId">
									<option value="0">Select User</option>
	                				<logic:present name="userMasterList">
										<logic:iterate id="project" name="userMasterList">
											<option value="${project['id']}">${project['loginId']}</option>
										</logic:iterate>
									</logic:present>
								</select> 
							</span>
						</th>
					</tr>
					<tr>
						<td><font color="red" size="1">&nbsp;<html:errors property="roleId"/></font></td>
						<td><font color="red" size="1">&nbsp;<html:errors property="userId"/></font></td>
					</tr>
				</table>
				<table width="100%" style="text-align: center;border-style: solid;border-color: gray" class="tableRow">
					<tr>
						<th width="70%" style="text-align: left;">Form Module Names </th>
						<th width="10%" style="text-align: center;">Add/Edit/Delete</th>
						<th width="10%" style="text-align: center;">View</th>
						<th width="10%" style="text-align: center;">Assign Module To Other User</th>
				   </tr>
				   <tr style="background-color: gray;">
						<th width="70%" style="text-align: left;">Check All</th>
						<th width="10%" style="text-align: center;"><input type="checkbox" onclick="checkAll(this,'add')"/> </th>
						<th width="10%" style="text-align: center;"><input type="checkbox" onclick="checkAll(this,'view')"/> </th>
						<th width="10%" style="text-align: center;"><input type="checkbox" onclick="checkAll(this,'assign')"/> </th>
				   </tr>
				   <logic:present name="formModulesListForEdit">
						<logic:iterate id="project" name="formModulesListForEdit">
							<c:if test="${project.checkForMasterModule eq true}">
								<tr style="background-color: green">
							</c:if>
							<c:if test="${project.checkForMasterModule ne true}">
								<tr>
							</c:if>
								<html:hidden property="formModuleIdList" value="${project['id']}"></html:hidden>
								<html:hidden property="accessModuleIdList" value="${project['formOrderNo']}"></html:hidden>
								<html:hidden property="permissionCharList" styleId="Project${project['id']}" value="${project['help']}"></html:hidden>
								<td style="text-align: left;">${project['formName']}</td>
					   			<td><input type="checkbox" id="addProject${project['id']}" onclick="add('${project['id']}')"/></td>
					   			<td><input type="checkbox" id="viewProject${project['id']}" onclick="view('${project['id']}')"/> </td>
					   			<td><input type="checkbox" id="assignProject${project['id']}" onclick="assign('${project['id']}')"/> </td>
						   </tr>
						</logic:iterate>
					</logic:present>
				   
				</table>
				
				<div align="center">
					<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
					<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
				</div>
			</div>
			</td>
		</tr>
		<tr>
			<td>
				<table class="display" id="example">
				<thead>
					<tr>
						<th>Sr NO</th>
						<th>Role Name</th>
						<th>User Name</th>
						<th>User Role</th>
						<th>Form Module Name</th>
						<th>Permission Character</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Sr NO</th>
						<th>Role Name</th>
						<th>User Name</th>
						<th>User Role</th>
						<th>Form Module Name</th>
						<th>Permission Character</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</tfoot>
				<tbody>
				<logic:present name="assignRoleMastersList">
					<c:set var="indexCount" value="1" />
					<logic:iterate id="list" name="assignRoleMastersList" indexId="index">
					<tr>
						<td>${indexCount}</td>
						<td>${list['createdBy']}</td>
						<td>${list['updatedBy']}</td>
						<td>${list['roleId']}</td>
						<td>${list['userId']}</td>
						<td>${list['editId']}</td>
						<td><a href="#" onclick="editRecord(${list['id']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteRecord(${list['id']})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
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