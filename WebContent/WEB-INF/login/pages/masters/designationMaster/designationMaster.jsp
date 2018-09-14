<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>


<script type="text/javascript">
	function save() {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var designation_id = $("#designationIdId").val();
		//alert(state_id);
		var designationcode=$("#designationCodeid").val();
		var designationName=$("#designationNameid").val();
		if(designationcode=="" || designationcode==null){
			alert("please enter designation code");
			return false;
		}
		if(designationName=="" || designationName==null){
			alert("please enter designation name");
			return false;
		}
		
		
		if (designation_id == "" || designation_id == null
				|| designation_id == "null" || designation_id == "0") {
			var strconfirm = confirm("Are you sure you want to Save ?");
			if (strconfirm == true) {

				document.forms[0].action = "designatioMaster.do?methodName=save"
						+ "&" + tokenParameter + "=" + tokenValue;
				document.forms[0].submit();
			}
		} else {
			var strconfirm = confirm("Are you sure you want to Update ?");
			if (strconfirm == true) {

				document.forms[0].action = "designatioMaster.do?methodName=update"
						+ "&" + tokenParameter + "=" + tokenValue;
				document.forms[0].submit();
			}

		}
		}

	}
	function edit(Id) {
		//alert(stateId);
		document.forms[0].action = "designatioMaster.do?methodName=edit" + "&"
				+ tokenParameter + "=" + tokenValue + "&designationId=" + Id;
		document.forms[0].submit();
	}
	function deletespecialArea(Id) {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var strconfirm = confirm("want to delete ?");
		if (strconfirm == true) {

			document.forms[0].action = "designatioMaster.do?methodName=delete"
					+ "&" + tokenParameter + "=" + tokenValue
					+ "&designationId=" + Id;
			document.forms[0].submit();

		}
		}

	}
	$(document).ready(function() {

		$('#example').DataTable();
	});
</script>

<html:form action="login/designatioMaster">
	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Designation Master</td>
		</tr>
	</table>
	<%
		String value = (String) request.getAttribute("message");

			if (value == "" || value == null || value == "null") {

			} else {
				out.print(request.getAttribute("message"));
			}
	%>

	<table width="100%" align="center" class="inputTBL">

		<tr>
			<th>Designation Code</th>
			<td><html:text property="designationCode"
					value="${designationMasterVo.designationCode}" styleId="designationCodeid"/>
				<html:hidden property="designationId" styleId="designationIdId"
					value="${designationMasterVo.designationId}" /></td>


		</tr>
		<tr>
			<th>designation Name</th>
			<td><html:text property="designationName"
					value="${designationMasterVo.designationName}" styleId="designationNameid" /></td>
			
		</tr>
	</table>
	<div align="center">
	<html:button property="" styleClass="button checkPermissionForFormForInsert" onclick="save()" value="save"></html:button>
	</div>


	<table width="100%" class="display" id="example">
		<thead>
			<tr>
				<th>S. No.</th>
				<th>Designation Code</th>
				<th>Designation Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<logic:present name="designationMasterList">
				<c:set var="indexCount" value="1" />
				<logic:iterate name="designationMasterList"
					id="designationMaster" scope="request">
					<tr>
						<td>${indexCount}</td>
						<td>${designationMaster.designationCode}</td>
						<td>${designationMaster.designationName}</td>
						<td><a href="#"
							onclick="edit('${designationMaster.designationId}')"><img
								src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" class="checkPermissionForFormForInsert"
							onclick="deletespecialArea('${designationMaster.designationId}')"><img
								src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>

					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>
		<tfoot>
			<tr>
				<td>S. No.</td>
				<td>Designation Code</td>
				<td>Designation Name</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
		</tfoot>
	</table>


</html:form>