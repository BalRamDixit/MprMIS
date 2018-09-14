<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<!--script for menu-->
<script type="text/javascript">
	function showRemark(str) {
		if (str == "pro_1") {
			document.getElementById("newRec").style.display = "";
			//document.getElementById("newRec").style.display = "none";

		} else {
			document.getElementById("newRec").style.display = "none";
			//document.getElementById("newRec").style.display = "none";
		}
	}

	function save() {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var status = window.confirm('Are You Sure You Want tO Save ?');
		if (status == true) {
			document.forms[0].action = "tcSetupEntry.do?methodName=save" + "&"
					+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
		}
	}

	function editDetail(id) {
		
		document.forms[0].action = "tcSetupEntry.do?methodName=edit&id="
				+ id + '&reqtrack=' + tokenValue;
		document.forms[0].submit();
	

	}

	function addDetail() {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		document.forms[0].action = "tcSetupEntry.do?methodName=addDetail" + "&"
				+ tokenParameter + "=" + tokenValue;
		;
		document.forms[0].submit();
		}

	}
	function deleteDetail(id) {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		document.forms[0].action = "tcSetupEntry.do?methodName=delete&id="
				+ id + '&reqtrack=' + tokenValue;
		document.forms[0].submit();
		}

	}

	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>
<!--Main form section starts here-->
<html:form action="login/tcSetupEntry">

	<table width="100%">
		<tr>
			<td>
				<table width="100%" class="pageHeaderTable">
					<tr>
						<td align="center" class="pageHeader">Training Center Detail</td>
					</tr>
				</table>
				
				<div align="right">
					<html:button styleClass="CompareButton button checkPermissionForFormForInsert" property="" styleId="addB"
						onclick="addDetail();">ADD NEW</html:button>
				</div>


				<table width="100%" class="display" id="example">
					<thead><tr>
						<th>S.No.</th>
						<th>Project Id</th>
						<th>TC Id</th>
						<th>Address</th>
						<th>Pin code</th>
						<th>Residential Status</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr></thead><tfoot><tr>
						<th>S.No.</th>
						<th>Project Id</th>
						<th>TC Id</th>
						<th>Address</th>
						<th>Pin code</th>
						<th>Residential Status</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr></tfoot>
					<tbody>
					<logic:present name="tcList">
						<c:set var="indexCount" value="1" />
						<logic:iterate id="list" name="tcList" indexId="index">

							<tr>
								<td>${indexCount}</td>
								<td>${list.project.projectID}(${list.state.stateName}) - ${list.project.piaPrn.userName}</td>
								<td>${list.tcID}(${list.district.districtName})</td>
								<td>${list.address}</td>
								<td>${list.pinCode}</td>
								<td>${list.resiStatus}</td>
								<td><a href="#" onclick=" editDetail('${list.id}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
								<td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteDetail('${list.id}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
							</tr>

							<c:set var="indexCount" value="${indexCount + 1}" />
						</logic:iterate>
					</logic:present>
	</tbody>
				</table> <br />




			</td>
		</tr>
	</table>

</html:form>