<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<!--script for menu-->
<script type="text/javascript">
 
$(document).ready(function() {
	
	$('#example').DataTable();
	
	});
 
	 function editDetail(tcid,projectId){

	 	 document.forms[0].action="tcSetupTrade.do?methodName=edit&tcId="+tcid+"&projectid="+projectId+'&reqtrack='+tokenValue;
	 	 document.forms[0].submit();
	 	  
	 }
	 function deleteDetail(tcid){

	 	 document.forms[0].action="tcSetupTrade.do?methodName=delete&tcId="+tcid+'&reqtrack='+tokenValue;
	 	 document.forms[0].submit();
	 	  
	 }
	 
	 function addDetail(){
	 	  
	 	 document.forms[0].action="tcSetupTrade.do?methodName=addDetail"+"&"+tokenParameter+"="+tokenValue;;
	 	 document.forms[0].submit(); 

	 }
  
</script>
<!--Main form section starts here-->
<html:form action="login/tcSetupTrade">

			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Training Center-Trade Target</td>					
               </tr>
			</table>
			 
			<table width="100%" align="center" class="display" id="example">
			 	<thead>
				<tr>
					<th>S.No.</th> 
					<th>Project Id</th>
					<th>Training Centre</th> 
					<th>Approved Trade Target</th>
					<th>Edit</th>
					<th>Delete</th>
					</tr>
				</thead>
				<tfoot>
				 <tr>	 
					<th>S.No.</th> 
					<th>Project Id</th>
					<th>Training Centre</th> 
					<th>Approved Trade Target</th>
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
									<td >${list[0]}</td>
									<td>${list[1]}</td>
									<td>${list[2]}</td>
 									<td><a href="#" onclick="editDetail('${list[1]}','${list[0]}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
									<td><a href="#" onclick="deleteDetail('${list[1]}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
								</tr>
								<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
					</tbody>	
				</table>
			   <div align="center"><html:button styleClass="CompareButton" property="" styleId="addB" onclick="addDetail();">ADD NEW</html:button>
			 	</div>
			 
			 
			 
</html:form>
 