<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable();
	});
	function editDetail(id) {
		/* var x=checkPermissionForFormForInsert();
		if(x=='true'){ */
			
		/* } */
		document.forms[0].action="stateFinancialForm.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue+"&id="+id;
		document.forms[0].submit();
		
		
	}
	function deleteDetail() {
		/* var x=checkPermissionForFormForInsert();
		if(x=='true'){
			
		} */
	}
	/*  class="checkPermissionForFormForInsert"  */
</script>
<html:form action="login/stateFinancialForm">

	<table class="display" id="example">
		<thead>
			<tr>
				<th>S. No.</th>
				<th>State Name</th>
				<th>central Program Cost</th>
				<th>Show details</th>
				<!-- <th>Delete</th> -->
			</tr>

		</thead>
		<tbody><logic:present name="statefinanceList">
			<c:set var="indexCount" value="1" />
			<logic:iterate id="statefinanceList" name="statefinanceList">
				<tr>
					<td>${indexCount}</td>
					<td>${statefinanceList.sanctionDetailId.state.stateName}</td>
					<td>${statefinanceList.centralProgramCost}</td>
					<td><a href="#"  onclick="editDetail(${statefinanceList.id})"><img
							src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
				</tr>
				<c:set var="indexCount" value="${indexCount + 1}" />
			</logic:iterate>
		</logic:present></tbody>
		
		<tfoot>
			<tr>
				<td>S. No.</td>
				<td>State Name</td>
				<td>central Program Cost</td>
				<td>Show details</td>
				<!-- <td>Delete</td> -->
			</tr>
		</tfoot>
	</table>
	<div align="center">
<html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message  key="button.close" /></html:button>
</div>
</html:form>