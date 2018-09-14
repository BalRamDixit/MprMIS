<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<script type="text/javascript">
	function saveRecord(){
		var idForEdit=$('#idForEdit').val()+"abc";
		if(idForEdit!="abc"){
			var status=window.confirm("Do you realy want to Update this Role Name and Description?");
			if(status==true){
				document.accessForm.action = "asignRole.do?methodName=updateRole"+"&"+tokenParameter+"="+tokenValue;
				document.accessForm.submit();
			}
		}
		else{
			var roleName = document.accessForm.roleName.value;
			var roleDesc = document.accessForm.roleDescription.value;
			var url = "asignRole.do?methodName=checkRecord&roleName="+roleName+"&roleDesc="+roleDesc;
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
		     	alert("<bean:message key="error.roleExist" />");
		     	return;
	    	}else{
		         document.accessForm.roleName.focus();
			     save();
	    	}
	  	}
	}
	function save(){
		if(document.accessForm.roleName.value == "" ){
	  		var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.accessForm.roleName.focus();
	  		return false;
  		} 
  		var roleName=document.accessForm.roleName.value;
		alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
		if( !(alphnum.test(roleName)) ) {
			var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
			alert(mess);
			document.accessForm.roleName.value ="";
			document.accessForm.roleName.focus();
			return false;
		}
  		if( document.accessForm.roleDescription.value == "" ){
	  		var arg='<bean:message key="access.addRole.roleDesc.label" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.accessForm.roleDescription.focus();
	  		return false;
  		}
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
		if(status==true){
  			document.accessForm.action = "asignRole.do?methodName=saveRole"+"&"+tokenParameter+"="+tokenValue;
			document.accessForm.submit();
  	 	}
}
function clearField()
{

}
function editRecord(id){
	$('#IdForAction').val(id);
	document.accessForm.action = "asignRole.do?methodName=editRole"+"&"+tokenParameter+"="+tokenValue;
	document.accessForm.submit();
}
function deleteRecord(id){
	$('#IdForAction').val(id);
	var status=window.confirm("Do you realy want to delete this role?");
	if(status==true){
		document.accessForm.action = "asignRole.do?methodName=deleteRole"+"&"+tokenParameter+"="+tokenValue;
		document.accessForm.submit();
	}
}
$(document).ready(function() {
	$('#example').DataTable();
});
</script>
<!--Main form section starts here-->
<html:form action="login/access">
<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Define Role</td>
			   </tr>
			</table>
	<table width="100%" >
		<tr>
			<td>
			<input type="hidden" name="id" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${roleMasterBean.id}"/>
			
			<table width="100%" align="center" class="inputTBL">
				<tr>
					<th><bean:message  key="access.addRole.roleName.label" /><span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="roleName" styleId="roleName" size="31" maxlength="30" onblur="this.value=this.value.toUpperCase();" value="${roleMasterBean.roleName}"/> </span></td>
				</tr>
				<tr>	
					<th><bean:message  key="access.addRole.roleDesc.label" /><span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="roleDescription" styleId="roleDescription" size="60" maxlength="60" onblur="this.value=this.value.toUpperCase();" value="${roleMasterBean.roleDescription}"/> </span></td>
				</tr>
				<tr>
					<td><font color="red" size="1">&nbsp;<html:errors property="roleName"/></font></td>
					<td><font color="red" size="1">&nbsp;<html:errors property="roleDescription"/></font></td>
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
			<table class="display" id="example">
					<thead>
						<tr>
							<th>Sr NO</th>
							<th>Role Name</th>
							<th>User Name</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr NO</th>
							<th>Role Name</th>
							<th>User Name</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</tfoot>
					<tbody>
						<logic:present name="roleList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="list" name="roleList"
								indexId="index">
								<tr>
									<td>${indexCount}</td>
									<td>${list['roleName']}</td>
									<td>${list['roleDescription']}</td>
									<td><a href="#"
										onclick="editRecord(${list['id']})"><img
											src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
									<td><a href="#"
										onclick="deleteRecord(${list['id']})"><img
											src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
									<c:set var="indexCount" value="${indexCount + 1}" />
								</tr>
							</logic:iterate>
						</logic:present>
					</tbody>
				</table>
				<%-- <display:table id="data" class="dataTable" name="requestScope.roleList" requestURI="/asignRole.do?methodName=addRole" pagesize="10" >
					<display:column sortable="false" title="Sr NO" style="8%"><span>${data_rowNum}</span> </display:column>
					<display:column property="roleName" title="Role Name" sortable="false" media="html" group="1" style="39%" />
					<display:column property="roleDescription" title="User Name" sortable="false" style="39%" />
					<display:column title="Edit" sortable="false" style="7%" ><a href="#" onclick="editRecord(${data.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></display:column>
					<display:column title="Delete" sortable="false" style="7%" ><a href="#" onclick="deleteRecord(${data.id})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></display:column>
				</display:table> --%>
			</td>
		</tr>
	</table>
</html:form>
<!--Main form section ends here-->