<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>
<script type="text/javascript">
	$(function() {
		$('#checkForMasterModule').prop('checked',${formModuleMasterBean.checkForMasterModule});
		$('#example').DataTable();
	});
	function saveRecord(){
		var idForEdit=$('#idForEdit').val()+"abc"; 
		if(idForEdit!="abc"){
			var status=window.confirm("Do you realy want to Update this FormName and Description?");
			if(status==true){
				document.formModuleForm.action = "formModule.do?methodName=updateModule"+"&"+tokenParameter+"="+tokenValue;
				document.formModuleForm.submit();
			}
		}
		else{
			var formName = document.formModuleForm.formName.value;
			var url = "formModule.do?methodName=checkRecord&formName="+formName;
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
		         document.formModuleForm.formName.focus();
			     save();
	    	}
	  	}
	}
	function save(){
		<%--
		if(document.formModuleForm.roleName.value == "" ){
	  		var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.formModuleForm.roleName.focus();
	  		return false;
  		} 
  		var roleName=document.formModuleForm.roleName.value;
		alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
		if( !(alphnum.test(roleName)) ) {
			var arg='<bean:message key="label.roleName" />';  
			var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
			alert(mess);
			document.formModuleForm.roleName.value ="";
			document.formModuleForm.roleName.focus();
			return false;
		}
  		if( document.formModuleForm.roleDescription.value == "" ){
	  		var arg='<bean:message key="access.addRole.roleDesc.label" />';  
			var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
			alert(mess);
	  		document.formModuleForm.roleDescription.focus();
	  		return false;
  		}
  		--%>
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
		if(status==true){
  			document.formModuleForm.action = "formModule.do?methodName=saveModule"+"&"+tokenParameter+"="+tokenValue;
			document.formModuleForm.submit();
  	 	}
	}
	function clearField(){
	
	}
	function editRecord(id){
		$('#IdForAction').val(id);
		document.formModuleForm.action = "formModule.do?methodName=editModule"+"&"+tokenParameter+"="+tokenValue;
		document.formModuleForm.submit();
	}
	function deleteRecord(id){
		$('#IdForAction').val(id);
		var status=window.confirm("Do you realy want to delete this role?");
		if(status==true){
			document.formModuleForm.action = "formModule.do?methodName=deleteModule"+"&"+tokenParameter+"="+tokenValue;
			document.formModuleForm.submit();
		}
	}
	function checkForMasterModuleOption(){
	 	if($('#checkForMasterModule').is(":checked")){
	 		$('#moduleName').val("0");
			$('#moduleName').prop("disabled",true);
		}
	 	else{
	 		$('#moduleName').prop("disabled",false);
	 	}
		
	}
</script>
<!--Main form section starts here-->
<html:form action="login/formModule" name="formModuleForm"
	type="com.infotech.sgsy.userAccessControlManagement.FormModuleMasterBean">
	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">ADD Modules</td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td><input type="hidden" name="id" id="IdForAction" /> <input
				type="hidden" name="editId" id="idForEdit"
				value="${formModuleMasterBean.editId}" />

				<table width="100%" align="center" class="inputTBL">
					<tr>
						<th>Form Name<span class="text-error">*</span></th>
						<td><span class="text-error"> <html:text
									property="formName" styleId="formName" size="31" 
									value="${formModuleMasterBean.formName}" />
						</span><span><input type="checkbox" id="checkForMasterModule"
								name="checkForMasterModule"
								onclick="checkForMasterModuleOption();"
								value="${formModuleMasterBean.checkForMasterModule}" /> Is Form
								A Master Module</span></td>
					</tr>
					<tr>
						<th>Module Name<span class="text-error">*</span></th>
						<td><span class="text-error"> <html:select
									property="moduleName" styleId="moduleName"
									value="${formModuleMasterBean.moduleName}">
									<html:option value="0">Select Module</html:option>
									<logic:present name="masterModulesList">
										<logic:iterate id="project" name="masterModulesList">
											<html:option value="${project['id']}">${project['formName']}</html:option>
										</logic:iterate>
									</logic:present>
								</html:select>
						</span></td>
					</tr>
					<tr>
						<th>Url<span class="text-error">*</span></th>
						<td><span class="text-error"> <html:text
									property="url" styleId="url" size="60" 
									value="${formModuleMasterBean.url}" />
						</span></td>
					</tr>
					<tr>
						<th>Order Number<span class="text-error">*</span></th>
						<td><span class="text-error"> <html:text
									property="formOrderNo" styleId="formOrderNo" size="60"
									maxlength="60" value="${formModuleMasterBean.formOrderNo}" />
						</span></td>
					</tr>
					<tr>
						<th>Help</th>
						<td><html:textarea property="help" styleId="help" rows="10"
								cols="10" value="${formModuleMasterBean.help}"></html:textarea></td>
					</tr>
					<tr>
						<td><font color="red" size="1">&nbsp;<html:errors
									property="formName" /></font></td>
						<td><font color="red" size="1">&nbsp;<html:errors
									property="moduleName" /></font></td>
						<td><font color="red" size="1">&nbsp;<html:errors
									property="url" /></font></td>
						<td><font color="red" size="1">&nbsp;<html:errors
									property="formOrderNo" /></font></td>
						<td></td>
					</tr>
				</table>
				<div align="center">
					<input name="Button" type="button" class="button"
						value="<bean:message  key="button.save"/>" onclick="saveRecord()" />
<!-- 					<input name="Button" type="button" class="button" -->
<%-- 						value="<bean:message  key="button.clear"/>" onclick="clearField()" /> --%>
				</div></td>
		</tr>
		<tr>
			<td>
				<table id="example" class="display">
					<thead>
						<tr>
							<th>Sr NO</th>
							<th>Form Name</th>
							<th>Module Name</th>
							<th>URL</th>
							<th>formOrderNo</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr NO</th>
							<th>Form Name</th>
							<th>Module Name</th>
							<th>URL</th>
							<th>formOrderNo</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</tfoot>
					<tbody>
						<logic:present name="formModulesList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="list" name="formModulesList" indexId="index">
								<tr>
									<td>${indexCount}</td>
									<td>${list['formName']}</td>
									<td>${list['moduleName']}</td>
									<td>${list['url']}</td>
									<td>${list['formOrderNo']}</td>
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

				</table> <%-- <display:table id="data" class="dataTable" name="requestScope.formModulesList" requestURI="/formModule.do?methodName=addModule" pagesize="10" >
					<display:column sortable="false" title="Sr NO" style="5%"><span>${data_rowNum}</span> </display:column>
					<display:column property="formName" title="Form Name" sortable="false" media="html" group="1" style="20%" />
					<display:column property="moduleName" title="Module Name" sortable="false" style="20%" />
					<display:column property="url" title="URL" sortable="false" style="25%" />
					<display:column property="formOrderNo" title="formOrderNo" sortable="false" style="20%" />
					<display:column title="Edit" sortable="false" style="5%" ><a href="#" onclick="editRecord(${data.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></display:column>
					<display:column title="Delete" sortable="false" style="5%" ><a href="#" onclick="deleteRecord(${data.id})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></display:column>
				</display:table> --%>
			</td>
		</tr>
	</table>
</html:form>
<!--Main form section ends here-->