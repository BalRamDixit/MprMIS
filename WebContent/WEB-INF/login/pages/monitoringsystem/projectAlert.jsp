<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>



<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable();
	});

	function editDetail(id) {		 
		document.forms[0].action = "projectAlerts.do?methodName=edit&id=" + id
				+ '&reqtrack=' + tokenValue;
		document.forms[0].submit();
	};

	function addDetail() {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		document.forms[0].action = "projectAlerts.do?methodName=addDetail"
				+ "&" + tokenParameter + "=" + tokenValue;
		;
		document.forms[0].submit();
		}
	};
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	function deleteDetail(id) {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var status = window.confirm('Are You Sure You Want to Delete ?');
		if (status == true) {

			document.forms[0].action = "projectAlerts.do?methodName=deleteproject&id="
					+ id + '&reqtrack=' + tokenValue;
			document.forms[0].submit();
		}
		}
	}};
</script>



<html:form action="login/projectAlerts">



	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">PROJECT ALERTS</td>
		</tr>
	</table>
	<div align="right">
		<html:button styleClass="button checkPermissionForFormForInsert" property="" styleId="addB"
			onclick="addDetail();">ADD NEW</html:button>
	</div>
 

	<table width="100%" align="center" class="display" id="example">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>Project Id</th>
				<th>Type Of Alert/SCN</th>
				<th>Reason For Issuing Alert/Show Cause</th>
				<th>Issuing Agency</th>
				<th>Status</th>
				<th>EDIT</th>
				<th>DELETE</th>
				 
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>S.No.</th>
				<th>Project Id</th>
				<th>Type Of Alert/SCN</th>
				<th>Reason For Issuing Alert/Show Cause</th>
				<th>Issuing Agency</th>
				<th>Status</th>
				<th>EDIT</th>
				<th>DELETE</th>
				 
			</tr>
		</tfoot>

		<tbody>
			<logic:present name="AlertList">
				<c:set var="indexCount" value="1" />
				<logic:iterate id="list" name="AlertList" indexId="index">

					<tr>
						<td>${indexCount}</td>
						<td>${list.projectID.projectID}(${list.projectID.state.stateName}) - ${list.projectID.piaPrn.userName}</td>
						<td>${list.typeOfAlert}</td>
						<td>${list.reasonForIssue}</td>
						<td>${list.issuingAgency}</td>
						<td>${list.status}</td>
						<td><a href="#" onclick="editDetail(${list.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteDetail(${list.id})" class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>

					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>
	</table>
 

</html:form>
