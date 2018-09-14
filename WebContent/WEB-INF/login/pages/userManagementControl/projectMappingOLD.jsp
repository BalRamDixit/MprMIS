<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<style>
	.hideDiv{
		display:none;
	}
</style> 
<script type="text/javascript">
	var row;
	var rowCount=1;
	$(document).ready(function(){
		var idForEdit=$('#idForEdit').val()+"abc"; 
		if(idForEdit!="abc"){
			$('#selectedProjectId0').val($('#projectId').val()).change();
			$('#selectedUserId').val($('#userId').val());
			$('#addNewRowTr').addClass('hideDiv');
		}
		else{
			row=$('#tbody').html();
			$('#tbody').html("");
		}
		
			$('#example').DataTable();
		
	});
	function addNewRow(){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
			row=row.replace( new RegExp("row"+(rowCount-1), 'g'), "row"+rowCount ) ;
			row=row.replace( new RegExp("selectedProjectId"+(rowCount-1), 'g'), "selectedProjectId"+rowCount ) ;
			row=row.replace( "piaDetail"+(rowCount-1), "piaDetail"+rowCount ) ;
			$('#tbody').append(row);
			rowCount++;
		}
	}
	function deleteRow(x){
		var elem = document.getElementById(x);
	    return elem.parentNode.removeChild(elem);
	}
	function saveRecord(){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
			var idForEdit=$('#idForEdit').val()+"abc"; 
			if(idForEdit!="abc"){
				var status=window.confirm("Do you realy want to Update this FormName and Description?");
				if(status==true){
					$('#projectId').val($('#selectedProjectId0').val());
					$('#userId').val($('#selectedUserId').val());
					document.projectMappingBean.action = "projectMapping.do?methodName=updateProjectMapping"+"&"+tokenParameter+"="+tokenValue;
					document.projectMappingBean.submit();
				}
			}
			else{
				save();
			}
		}
		
	}
	function getProjectDetail(x){
		var projectId=document.getElementById(x).value;
		x=x.replace("selectedProjectId","");
		var url = "projectMapping.do?methodName=checkRecord&formName="+projectId;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("GET", url, true);
		req.setRequestHeader(tokenParameter, tokenValue);
		req.onreadystatechange=function(){
			if (req.readyState == 4){	
		    	var response = req.responseText; 
		    	$('#piaDetail'+x).html(response);
		    	
		  	}
		};
		req.send(null);
	}
	function save(){
		/*
		if(document.projectMappingBean.roleName.value == "" ){
	  		var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.projectMappingBean.roleName.focus();
	  		return false;
  		} 
  		var roleName=document.projectMappingBean.roleName.value;
		alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
		if( !(alphnum.test(roleName)) ) {
			var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
			alert(mess);
			document.projectMappingBean.roleName.value ="";
			document.projectMappingBean.roleName.focus();
			return false;
		}
  		if( document.projectMappingBean.roleDescription.value == "" ){
	  		var arg='<bean:message key="access.addRole.roleDesc.label" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.projectMappingBean.roleDescription.focus();
	  		return false;
  		}
  		if (!((document.projectMappingBean.password.value.search(/[a-z]+/) > -1) && (document.projectMappingBean.password.value.search(/[A-Z]+/) > -1)&& (document.projectMappingBean.password.value.search(/[0-9]+/) > -1) && (document.projectMappingBean.password.value.search(/[!@#$%^&*()_]+/) > -1))) {
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
			document.projectMappingBean.action = "projectMapping.do?methodName=saveProjectMapping"+"&"+tokenParameter+"="+tokenValue;
			document.projectMappingBean.submit();
  	 	}
	}
	function clearField(){
	
	}
	function editRecord(id){
		$('#IdForAction').val(id);
		document.projectMappingBean.action = "projectMapping.do?methodName=editProjectMapping"+"&"+tokenParameter+"="+tokenValue;
		document.projectMappingBean.submit();
	}
	function deleteRecord(id){
		$('#IdForAction').val(id);
		var status=window.confirm("Do you realy want to delete this role?");
		if(status==true){
			document.projectMappingBean.action = "projectMapping.do?methodName=deleteProjectMapping"+"&"+tokenParameter+"="+tokenValue;
			document.projectMappingBean.submit();
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
	
	
</script>
<!--Main form section starts here-->
<html:form action="login/projectMapping">
<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Define Project Mapping</td>
			   </tr>
			</table>
	<table width="100%" >
		<tr>
			<td>
			<input type="hidden" name="id" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${projectMappingBean.editId}"/>
			<input type="hidden" name="projectId" id="projectId" value="${projectMappingBean.projectId}"/>
			<input type="hidden" name="userId" id="userId" value="${projectMappingBean.userId}"/>
			
			<table width="100%" align="center" class="inputTBL">
				<thead style="color: gray;">
					<tr  style="color: gray;">
						<td colspan="4" style="text-align: right;color: gray;" id="addNewRowTr"><a style="color: gray;" class="checkPermissionForFormForInsert" href="#" onclick="addNewRow()">Add Row</a></td>
					</tr>
					<tr  style="color: gray;">
						<th style="color: gray;">Project Id<span class="text-error">*</span></th>
						<th style="color: gray;">PIA No<span class="text-error">*</span></th>
						<th style="color: gray;">User To Assign<span class="text-error">*</span></th>
						<th style="color: gray;">Action</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<tr id="row0">	
						<td>
							<span class="text-error">
								<select name="selectedProjectId" id="selectedProjectId0" onchange="getProjectDetail('selectedProjectId0')">
									<option value="0">Select Project</option>
									<logic:present name="projectDetailsList">
										<logic:iterate id="project" name="projectDetailsList">
											<option value="${project['id']}">${project['projectID']}</option>
										</logic:iterate>
									</logic:present>
								</select>
							</span>
						</td>
						<td>
							<span id="piaDetail0"></span>
						</td> 
						<td>
							<span class="text-error">
								<select name="selectedUserId" id="selectedUserId">
									<option value="0">Select User</option>
	                				<logic:present name="userMasterList">
										<logic:iterate id="project" name="userMasterList">
											<option value="${project['id']}">${project['loginId']}</option>
										</logic:iterate>
									</logic:present>
								</select> 
						 	</span>
						 </td>
						 <td>
						 	<a href="#" onclick="deleteRow('row0')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a>
						 </td>
					</tr>
				</tbody>
			</table>
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
			</div>
			</td>
		</tr>
		<tr>
			<td>
			
			<table id="example" class="display">
					<thead>
						<tr>
							<th>Sr NO</th>
							<th>Project Id</th>
							<th>Pia PRN</th>
							<th>Pia Name</th>
							<th>File no</th>
							<th>User Name</th>
							<th>Login ID</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr NO</th>
							<th>Project Id</th>
							<th>Pia PRN</th>
							<th>Pia Name</th>
							<th>File no</th>
							<th>User Name</th>
							<th>Login ID</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</tfoot>
					<tbody>
						<logic:present name="projectMappingsList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="list" name="projectMappingsList"	indexId="index">
								<tr>
									<td>${indexCount}</td>
									<td>${list['createdBy']}</td>
									<td>${list['updatedBy']}</td>
									<td>${list['tempId']}</td>
									<td>${list['projectId']}</td>
									<td>${list['userId']}</td>
									<td>${list['editId']}</td>
									<td><a href="#" onclick="editRecord(${list['id']})" class="checkPermissionForFormForInsert"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
									<td><a href="#" onclick="deleteRecord(${list['id']})"  class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
									<c:set var="indexCount" value="${indexCount + 1}" />
								</tr>
							</logic:iterate>
						</logic:present>
					</tbody>
			</table><%-- 
				<display:table id="data" class="dataTable" name="requestScope.projectMappingsList" requestURI="/projectMapping.do?methodName=addProjectMapping" pagesize="10" >
					<display:column sortable="false" title="Sr NO" style="5%"><span>${data_rowNum}</span> </display:column>
					<display:column property="createdBy" title="Project Id" sortable="false" style="20%" />
					<display:column property="updatedBy" title="Pia PRN" sortable="false" style="20%" />
					<display:column property="tempId" title="Pia Name" sortable="false" style="20%" />
					<display:column property="projectId" title="File no" sortable="false" style="25%" />
					<display:column property="userId" title="User Name" sortable="false" style="20%" />
					<display:column property="editId" title="Login ID" sortable="false" style="20%" />
					<display:column title="Edit" sortable="false" style="5%" ><a href="#" onclick="editRecord(${data.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></display:column>
					<display:column title="Delete" sortable="false" style="5%" ><a href="#" onclick="deleteRecord(${data.id})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></display:column>
				</display:table> --%>
				
			</td>
		</tr>
	</table>
</html:form>
<!--Main form section ends here-->