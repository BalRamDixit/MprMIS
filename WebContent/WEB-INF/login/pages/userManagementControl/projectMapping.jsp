<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE>
<link rel="stylesheet" href="css/bootstrap.css" />
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/multiselect.js"></script>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<style>
	.hideDiv{
		display:none;
	}
	input[type="text"], input[type="password"] {
	    height: 14px;
	    width: 394px;
	}
</style> 
<script type="text/javascript">
	var row;
	var rowCount=1;
	$(document).ready(function(){
	    $('#selectedProjectId').multiselect({
	        search: {
	            left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
	            right: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
	        },
	        fireSearch: function(value) {
	            return value.length > 1;
	        }
	    });
	    $('#selectedUserId').multiselect({
	        search: {
	            left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
	            right: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
	        },
	        fireSearch: function(value) {
	            return value.length > 1;
	        }
	    });
	    
		var idForEdit=$('#idForEdit').val()+"abc"; 
		if(idForEdit!="abc"){
			
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
					$('#selectedProjectId_to option').prop('selected', true);
					$('#selectedUserId_to option').prop('selected', true);
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
		$('#selectedProjectId_to option').prop('selected', true);
		$('#selectedUserId_to option').prop('selected', true);
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
			
			<table width="100%" align="center" class="inputTBL checkPermissionForFormForInsert">
				<tbody>
					<tr>
						<th style="color: gray;">User To Assign<span class="text-error">*</span></th>
					</tr>
					<tr>	
						
					 	<td>
							<div class="row">
							    <div class="col-xs-5">
							        <select name="selectedUserIdfrom" id="selectedUserId"  class="form-control" size="10" multiple="multiple">
							            <logic:present name="userMasterList">
											<logic:iterate id="project" name="userMasterList">
												<option value="${project['id']}">${project['loginId']}</option>
											</logic:iterate>
										</logic:present>
							        </select>
							    </div>
							    
							    <div class="col-sm-2">
									<button type="button" id="selectedUserId_undo" class="btn btn-primary btn-block">undo</button>
				                    <button type="button" id="selectedUserId_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
				                    <button type="button" id="selectedUserId_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
				                    <button type="button" id="selectedUserId_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
				                    <button type="button" id="selectedUserId_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
									<button type="button" id="selectedUserId_redo" class="btn btn-warning btn-block">redo</button>
				                </div>
							    
							    <div class="col-xs-5">
							        <select name="selectedUserId" id="selectedUserId_to" class="form-control" size="10" multiple="multiple">
							        	<logic:present name="userMasterAssign">
											<option value="${userMasterAssign.id}">${userMasterAssign.loginId}</option>
										</logic:present>
							        </select>
							        
							    </div>
							</div>
						</td>
					</tr>
					<tr>
						<th style="color: gray;">Project Id<span class="text-error">*</span></th>
					</tr>
					<tr>	
						
					 	<td>
							<div class="row">
							    <div class="col-xs-5">
							        <select name="selectedProjectIdfrom" id="selectedProjectId"  class="form-control" size="10" multiple="multiple">
							            <logic:present name="projectDetailsList">
											<logic:iterate id="project" name="projectDetailsList">
												<option value="${project['id']}">${project['projectID']}</option>
											</logic:iterate>
										</logic:present>
							        </select>
							    </div>
							    
							    <div class="col-sm-2">
									<button type="button" id="selectedProjectId_undo" class="btn btn-primary btn-block">undo</button>
				                    <button type="button" id="selectedProjectId_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
				                    <button type="button" id="selectedProjectId_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
				                    <button type="button" id="selectedProjectId_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
				                    <button type="button" id="selectedProjectId_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
									<button type="button" id="selectedProjectId_redo" class="btn btn-warning btn-block">redo</button>
				                </div>
							    
							    <div class="col-xs-5">
							        <select name="selectedProjectId" id="selectedProjectId_to" class="form-control" size="10" multiple="multiple">
							        	 <logic:present name="projectDetailsAssignList">
											<logic:iterate id="project" name="projectDetailsAssignList">
												<option value="${project['userId']}">${project['editId']}</option>
											</logic:iterate>
										</logic:present>
							        </select>
							    </div>
							</div>
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
							<th>User Name</th>
							<th>Login ID</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr NO</th>
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
									<td>${list['userId']}</td>
									<td>${list['editId']}</td>
									<td><a href="#" onclick="editRecord(${list['projectId']})" class="checkPermissionForFormForInsert"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
									<td><a href="#" onclick="deleteRecord(${list['projectId']})"  class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
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