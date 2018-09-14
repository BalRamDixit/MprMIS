<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">

$(document).ready(function()
		{
			$('#example').DataTable();
		});

	function addDetail() {
		 var x=checkPermissionForFormForInsert();
			if(x=='true'){
		document.forms[0].action = "projectDetails.do?methodName=addDetail"
				+ "&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
			}
	}

	function editRecord(id) {
// alert("aa");
		document.forms[0].action = "projectDetails.do?methodName=edit&id="
				+ id + '&reqtrack=' + tokenValue;
		document.forms[0].submit();

	}
	function deleteDetail(id) {
		 var x=checkPermissionForFormForInsert();
			if(x=='true'){
		var status = window.confirm('Are You Sure You Want to delete ?');
		if (status == true) {

			document.forms[0].action = "projectDetails.do?methodName=deleteproject&id="
					+ id + '&reqtrack=' + tokenValue;
			document.forms[0].submit();
		}
			}

	}
</script>
<!--Main form section starts here-->
<html:form action="login/projectDetails">



	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Project Details</td>

		</tr>

	</table>


	<%-- <table width="100%" align="center" class="inputTBL">
		<tr>
			<th>${name}</th>
			<td><b>${nameValue}</b></td>
		</tr>
	</table> --%>
	<br />
<div align="right">
		<html:button styleClass="CompareButton button checkPermissionForFormForInsert" property="" styleId="addB"
			onclick="addDetail();">ADD NEW</html:button>
	</div>
	<table width="100%" align="center" class="display" id="example">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>File Number</th>
				<th>PROJECT ID</th>
				<th>PROJECT SCHEME</th>
				<th>PIA PRN</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>S.No.</th>
				<th>File Number</th>
				<th>PROJECT ID</th>
				<th>PROJECT SCHEME</th>
				<th>PIA PRN</th>
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
						<td>${list.fileNumber}</td>
						<td>${list.projectID}</td>
						<td>${list.projectScheme}</td>
						<td title='${list.piaPrn.userName}'>${list.piaPrn.loginId}</td>
						<td><a href="#" onclick="editRecord(${list['id']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteDetail(${list['id']})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>
	</table>
</html:form>
