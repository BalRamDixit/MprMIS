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
	function addDetail() {

		var x=checkPermissionForFormForInsert();
		if(x=='true'){	
				document.forms[0].action = "batchCompletion.do?methodName=addDetail"
				+ "&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();

	}		 
	}
	
	function editDetail(id) {	
  		document.forms[0].action = "batchCompletion.do?methodName=edit&id="+id+'&reqtrack=' + tokenValue; 		
		document.forms[0].submit();

	}
	function deleteDetail(id) {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
 		var status = window.confirm('Are You Sure You Want to Delete ?');
		if (status == true) {
			document.forms[0].action = "batchCompletion.do?methodName=deleteproject&id="
					+ id + '&reqtrack=' + tokenValue;
			document.forms[0].submit();
				}
			}
		}
</script>
<!--Main form section starts here-->
<html:form action="login/batchCompletion">



	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">BATCH COMPLETION</td>
		</tr>
	</table> 
	 
	<div align="right">
		<html:button styleClass="button checkPermissionForFormForInsert"  property="" styleId="addB"
			onclick="addDetail();">ADD NEW</html:button>
	</div>
	<table width="100%" align="center" class="display" id="example">
		<thead>
			<tr>

				<th>S.No.</th>	
				<th>BATCH Id</th>	
				<th>BATCH STATUS</th>	
				<th>COMPLETED PWD</th>			 
				<th>COMPLETED TOTAL</th> 						 
				<th>BATCH END DATE</th>
				<th>COMPLETED WOMEN</th>	
				<th>COMPLETED MINORITY</th>	
				<th>EDIT</th>
				<th>DELETE</th>				
				 
			</tr>
		</thead>
		<tfoot>
			<tr>

				<th>S.No.</th>
				<th>BATCH Id</th>	
			    <th>BATCH STATUS</th>	
				<th> COMPLETED PWD</th>			 
				<th>COMPLETED TOTAL</th> 						 
				<th>BATCH END DATE</th>
				<th>COMPLETED WOMEN</th>	
				<th>COMPLETED MINORITY</th>	
				<th>EDIT</th>
				<th>DELETE</th>	
			</tr>
		</tfoot>
<tbody>
		<logic:present name="ViewList">
			<c:set var="indexCount" value="1" />
			<logic:iterate id="list" name="ViewList" indexId="index">

				<tr>
					<td style="text-align: center;">${indexCount}</td>
					<td>${list.batchID.batchID}</td>
 					<td>${list.batchStatus}</td>					
 					<td>${list.complet_Pwd}</td>
					<td>${list.complet_Total}</td>
					<td>${list.batchEndDate}</td>
					<td>${list.complet_Women}</td>
					<td>${list.complet_Minority}</td>
				 
					<td><a href="#" onclick="editDetail(${list.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
					<td><a href="#" Class="checkPermissionForFormForInsert" onclick="deleteDetail(${list.id})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
<%-- 				  <td><a Class="CompareButton" property=""	onclick="editDetail(${list.tcID.id},${list.id})" ><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a> </td> --%>
<%-- 				  <td><a Class="CompareButton checkPermissionForFormForInsert"  property=""	onclick="deleteDetail(${list.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>   --%>
				</tr>
				<c:set var="indexCount" value="${indexCount + 1}" />
			</logic:iterate>
		</logic:present></tbody>
	</table>
	 
	
</html:form>
