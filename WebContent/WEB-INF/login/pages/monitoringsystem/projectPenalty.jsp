<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable();
	});

	function editDetail(id) {
  		document.forms[0].action = "projectPenalty.do?methodName=edit&id=" + id
				+ '&reqtrack=' + tokenValue;
		document.forms[0].submit();

	}

	function addDetail() {
		{
		document.forms[0].action = "projectPenalty.do?methodName=addDetail"
				+ "&" + tokenParameter + "=" + tokenValue;
		;
		document.forms[0].submit();
		}
	};
	function deleteDetail(id) {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
 		var status = window.confirm('Are You Sure You Want to Delete ?');
		if (status == true) {
			document.forms[0].action = "projectPenalty.do?methodName=deleteproject&id="
					+ id + '&reqtrack=' + tokenValue;
			document.forms[0].submit();
		}
		}
	};
</script>


<html:form action="login/projectPenalty">
<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">PROJECT PENALTIES</td>
		</tr>
	</table>

	<div align="right">
		<html:button styleClass="button checkPermissionForFormForInsert" property="" styleId="addB"
			onclick="addDetail();">ADD NEW</html:button>
	</div>

	<br />
	<table width="100%" class="display" id="example">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>Project ID</th>
				<th>Type OF Penalty</th>
				<th>Reason for Penalty</th>
				<th>Status</th>
				<th>Appeal Disposal Result</th>
				<th>Edit</th>
				<th>Delete</th>

			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>S.No.</th>
				<th>Project ID</th>
				<th>Type OF Penalty</th>
				<th>Reason for Penalty</th>
				<th>Status</th>
				<th>Appeal Disposal Result</th>
				<th>Edit</th>
				<th>Delete</th>

			</tr>
		</tfoot>
		<tbody>
			<logic:present name="DetailList">
				<c:set var="indexCount" value="1" />
				<logic:iterate id="list" name="DetailList" indexId="index">

					<tr>
						<td>${indexCount}</td>
						<td>${list.projectID.projectID}</td>
						<td>${list.typeOfPenalty}</td>
						<td>${list.reasonForPenalty}</td>
						<td>${list.status}</td>
						<td>${list.appealDisposalResult}</td>
						<td><a href="#" onclick="editDetail('${list.id}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteDetail('${list.id}')" class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
						 

					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>
	</table>
	<br />



</html:form>
