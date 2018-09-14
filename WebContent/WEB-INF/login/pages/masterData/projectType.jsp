<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link type="text/css" href="css/displayTag.css" rel="stylesheet"  />
<style type="text/css">
 
</style>
<!--script for menu-->
<script type="text/javascript">

$(document).ready(function()
		{
 			$('#example').DataTable();
		});

function saveRecord(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var idForEdit=$('#idForEdit').val()+"abc";
  	if(idForEdit!="abc"){
		var status=window.confirm("Do you realy want to Update  ");
		if(status==true){
			
			document.forms[0].action = "projectType.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
	else{
		
		save();
	 
		}
	}
}
 


	function save() {
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
 		document.forms[0].action = "projectType.do?methodName=save" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	 }
	};
	
	function edit(id){		
 		$('#IdForAction').val(id);		 
		document.forms[0].action = "projectType.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	 
	}
	
	
	function deleteData(id){	
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		
  		$('#IdForAction').val(id);
		document.forms[0].action = "projectType.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}	 
	}
	function clearField()
	{

	}

	function back() {
		document.forms[0].action = "projectType.do?methodName=show" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	};
</script>
<!--Main form section starts here-->
<html:form action="login/projectType">

            <input type="hidden" name="projectTypeId" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${Bean.projectTypeId}"/>

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Project Type Master</td>
		</tr>
	</table>
 

	<table width="100%" align="center" class="inputTBL">
		<tr>
			<th>Project Type Code<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="projectTypCode"  value="${Bean.projectTypCode}" styleId="projectTypCodeId" />
			</span></td>
		</tr>

		<tr>
			<th>Project Type Name<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="projectTypeName"   value="${Bean.projectTypeName}" styleId="projectTypeNameId" />
			</span></td>
		</tr>

	</table>
	<div align="center">
		<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="saveRecord()" />
 	</div>
	 <br/>
<%-- 	<table>
	<display:table id="data" class="dataTable" name="requestScope.projectTypeList" requestURI="/projectType.do?methodName=show" pagesize="10" >
					 <display:column sortable="false" title="Sr NO" style="8%"><span>${data_rowNum}</span> </display:column> 
					<display:column property="projectTypCode" title="Project Type Code" sortable="false" media="html" group="1" style="39%" />
					<display:column property="projectTypeName" title=" Project Type Name" sortable="false" style="39%" />
					<display:column title="Edit" sortable="false" style="7%" ><a href="#" onclick="editRecord(${data.projectTypeId})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></display:column>
					<display:column title="Delete" sortable="false" style="7%" ><a href="#" onclick="deleteRecord(${data.projectTypeId})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></display:column>
				</display:table>
	 
</table> --%>
		
	<table width="100%"  class="display" id="example">
		<thead>
              <tr>
		 			<th>S.No </th>
               		<th>Project Type Code</th>
               		<th>Project Type Name </th>               		               		 
               		<th>Edit</th>
					<th>Delete</th>
               </tr>
 		</thead>
 		<tfoot>
             <tr>
		 			<th>S.No </th>
               		<th>Project Type Code</th>
               		<th>Project Type Name </th>               		               		 
               		<th>Edit</th>
					<th>Delete</th>
               </tr>
 		</tfoot>
        <tbody>
			<logic:present name="projectTypeList">
				<c:set var="indexCount" value="1" />
				<logic:iterate id="list" name="projectTypeList" indexId="index">
					<tr>
						<td>${indexCount}</td>
						<td>${list['projectTypCode']}</td>
						<td>${list['projectTypeName']}</td>		 
					    <td><a href="#" onclick="editRecord(${list['projectTypeId']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteRecord(${list['projectTypeId']})" class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>	
				</table>


</html:form>
