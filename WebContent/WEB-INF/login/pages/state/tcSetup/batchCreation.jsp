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
				document.forms[0].action = "batchCreation.do?methodName=addDetail"
				+ "&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();

	}		 
	}
		
	function editDetail(tcid,id) {	
  		document.forms[0].action = "batchCreation.do?methodName=edit&id="+id+'&tcid='+tcid+ '&reqtrack=' + tokenValue; 		
		document.forms[0].submit();

	}
	function deleteDetail(id) {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
 		var status = window.confirm('Are You Sure You Want to Delete ?');
		if (status == true) {
			document.forms[0].action = "batchCreation.do?methodName=deleteproject&id="
					+ id + '&reqtrack=' + tokenValue;
			document.forms[0].submit();
				}
			}
		}
</script>
<!--Main form section starts here-->
<html:form action="login/batchCreation">



	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">BATCH CREATION</td>
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
				<th>TRAINING CENTER</th>	
				<th>SECTOR</th>			 
				<th>TRADE</th> 						 
				<th>BATCH SIZE</th>
				<th>BATCH START DATE</th>	
				<th>BATCH FREEZE DATE</th>	
				<th>EDIT</th>
				<th>DELETE</th>				
				 
			</tr>
		</thead>
		<tfoot>
			<tr>

				<th>S.No.</th>
				<th>BATCH Id</th>
			    <th>TRAINING CENTER</th>
				<th>SECTOR</th>			 
				<th>TRADE</th> 						 
				<th>BATCH SIZE</th>
				<th>BATCH START DATE</th>	
				<th>BATCH FREEZE DATE</th>	
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
					<td>${list.batchID}</td>
 					<td>${list.tcID.trainningCenter.tcID}(${list.tcID.trainningCenter.district.districtName})</td>					
 					<td>${list.tcID.sector.sectorName}</td>
					<td>${list.tcID.trade.tradeName}-${list.tcID.trade.tradeCode}</td>
					<td>${list.batchSize}</td>
					<td>${list.batchStartDate}</td>
					<td>${list.batchFreezeDate}</td>
				 
					<td><a href="#" onclick="editDetail(${list.tcID.id},${list.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
					<td><a href="#" Class="checkPermissionForFormForInsert" onclick="deleteDetail(${list.id})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
<%-- 				  <td><a Class="CompareButton" property=""	onclick="editDetail(${list.tcID.id},${list.id})" ><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a> </td> --%>
<%-- 				  <td><a Class="CompareButton checkPermissionForFormForInsert"  property=""	onclick="deleteDetail(${list.id})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>   --%>
				</tr>
				<c:set var="indexCount" value="${indexCount + 1}" />
			</logic:iterate>
		</logic:present></tbody>
	</table>
	 
	
</html:form>
