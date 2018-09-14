<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link type="text/css" href="css/displayTag.css" rel="stylesheet"  />

<!--script for menu-->
<script type="text/javascript">

function deleteRecord(id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm('ARE YOU SURE YOU WANT TO DELETE ?');
	if(status==true){
	 document.forms[0].action="projectSanctionAction.do?methodName=delete&id="+id+'&reqtrack='+tokenValue;
	 document.forms[0].submit();
}
}
}

function editRecord(projectId){
	
	 document.forms[0].action="projectSanctionAction.do?methodName=edit&id="+projectId+'&reqtrack='+tokenValue;
	 document.forms[0].submit();
}

 	 
function addDetail(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	 document.forms[0].action="projectSanctionAction.do?methodName=addDetail"+"&"+tokenParameter+"="+tokenValue;;
	 document.forms[0].submit(); 
	}
}



function showRemark(str){
}
$(document).ready(function()
{
	showRemark("0");
	$('#example').DataTable();
});




function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="projectSanctionAction.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
	}
  
</script>

 <html:form action="login/projectSanctionAction">
	
			  		
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">SANCTION DETAIL</td>					
               </tr>
			</table>			
	<div align="right">
		  <input name="Button" type="button" class="button checkPermissionForFormForInsert" value="Add New" onclick="addDetail()"/> 
	</div>		
	
	<table width="100%"  class="display" id="example">
		<thead>
              <tr>
		 			<th>S.No </th>
               		<th>Project ID </th>
               		<th>Sanction Order No </th>
               		<th>Sanction Target </th>
               		<th>Project Duration (months) </th>
               		<th>Commencement Date </th>
               		<th>Status of the Project </th>
               		<th>SC St </th>
               		<th>GENERAL </th>
               		<th>MINORITY </th>
               		<th>WoMEN </th>
               		<th>Edit</th>
               		<th>Delete</th>
               </tr>
 		</thead>
 		<tfoot>
              <tr>
		 			<th>S.No </th>
               		<th>Project ID </th>
               		<th>Sanction Order No </th>
               		<th>Sanction Target </th>
               		<th>Project Duration (months) </th>
               		<th>Commencement Date </th>
               		<th>Status of the Project </th>
               		<th>SC St </th>
               		<th>GENERAL </th>
               		<th>MINORITY </th>
               		<th>WoMEN </th>
               		<th>Edit</th>
               		<th>Delete</th>
               </tr>
 		</tfoot>
        <tbody>
			<logic:present name="ViewList">
				<c:set var="indexCount" value="1" />
				<logic:iterate id="list" name="ViewList" indexId="index">
					<tr>
						<td>${indexCount}</td>
						<td>${list.projectId.projectID}</td>
						<td>${list['sanOrder']}</td>
						<td>${list['sanTarget']}</td> 
						<td>${list['projectDuration']}</td>
						<td>${list['commDate']}</td>
					    <td>${list['projectStatus']}</td>
					     <td>${list['sc_st']}</td>
					      <td>${list['general']}</td>
					       <td>${list['minority']}</td>
					        <td>${list['women']}</td>
					    
						<td><a href="#" onclick="editRecord(${list['id']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteRecord(${list['id']})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>	
				</table>
			  
			  
			  
			 
	
</html:form>
 