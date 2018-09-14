<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">

function deleteDetail(id) {
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm('ARE YOU SURE YOU WANT TO DELETE ?');
	if(status==true){
		document.forms[0].action = "tcSetupDue.do?methodName=delete&id=" + id+ '&reqtrack=' + tokenValue;
		document.forms[0].submit();
	}
	}
	}

function editDetail(projectId) {
		document.forms[0].action = "tcSetupDue.do?methodName=edit&id="+ projectId + '&reqtrack=' + tokenValue;
		document.forms[0].submit();
	}

function addDetail() {
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
		document.forms[0].action = "tcSetupDue.do?methodName=addDetail" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
		}
	}

function showRemark(str) {
  if (str != '0') {
			document.getElementById("showTable").style.display = "";
    	} else {
			document.getElementById("showTable").style.display = "none";
		}
	}
	
$(document).ready(function() {
		$('#example').DataTable();
		showRemark("0");
});

function save() {
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status = window.confirm('Are You Sure You Want tO Save ?');
	if (status == true) {
			document.forms[0].action = "tcSetupDue.do?methodName=save" + "&"+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
	}
	}
}
</script>

<html:form action="login/tcSetupDue">

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">TRAINING CENTER-DUE DILIGENCE</td>
		</tr>
	</table>
	<div align="right">
		<html:button styleClass="button checkPermissionForFormForInsert" property="" styleId="addB"	onclick="addDetail();">ADD NEW</html:button>
	</div>
	<table width="100%" class="display" id="example">
		<thead>
		<tr>
			<th>S.No</th>
			<th>Project ID</th>
			<th>Training Centre ID</th>
			<th>SRLM Person</th>
			<th>Due-Diligence Status</th>
			<th>Due-Diligence Remarks by SRLM</th>
			<th>Training Center Approved Capacity</th>
			<th>TC Status</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr></thead>
<tfoot>
		<tr>
			<th>S.No</th>
			<th>Project ID</th>
			<th>Training Centre ID</th>
			<th>SRLM Person</th>
			<th>Due-Diligence Status</th>
			<th>Due-Diligence Remarks by SRLM</th>
			<th>Training Center Approved Capacity</th>
			<th>TC Status</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr></tfoot><tbody>
		<logic:present name="ViewList">
			<c:set var="indexCount" value="1" />
			<logic:iterate id="list" name="ViewList" indexId="index">

				<tr>
					<td>${indexCount}</td>
					<td>${list.trainingCenterId.project.projectID} (${list.trainingCenterId.project.state.stateName}) - ${list.trainingCenterId.project.piaPrn.userName}</td>
					<td>${list.trainingCenterId.tcID} - ${list.trainingCenterId.district.districtName}</td>
					<td>${list.srlmPersonId.personName}</td>
					<td>${list.statusDueDil}</td>
					<td>${list.remarksDueDili}</td>
					<td>${list.tcAppCapacity}</td>
					<td>${list.tcStatus}</td>
					<td><a href="#" onclick=" editDetail('${list.id}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
					<td><a href="#" class="checkPermissionForFormForInsert"  onclick="deleteDetail('${list.id}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					
				</tr>

				<c:set var="indexCount" value="${indexCount + 1}" />
			</logic:iterate>
		</logic:present>
	</tbody>
	</table>


	


</html:form>
