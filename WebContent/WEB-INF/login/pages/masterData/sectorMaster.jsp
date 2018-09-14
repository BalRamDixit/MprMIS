<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link type="text/css" href="css/displayTag.css" rel="stylesheet"  />
<style type="text/css">
 
</style>

<!--script for menu-->
<script type="text/javascript">


$(document).ready(function()
		{
 			$('#example').DataTable();
		});


function saveRecord(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	
	var idForEdit=$('#idForEdit').val()+"abc";
  	if(idForEdit!="abc"){
		var status=window.confirm("Do you realy want to Update  ");
		if(status==true){
			
			document.forms[0].action = "sector.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
	else{		
		save();	 
	}
	}
}
	function save() {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
 		document.forms[0].action = "sector.do?methodName=save" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
		}

	};
	
	function edit(id){		
  		$('#IdForAction').val(id);		 
		document.forms[0].action = "sector.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	 
	}
	
	
	function deleteData(id){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
   		  $('#IdForAction').val(id);
		document.forms[0].action = "sector.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		} 
	}
	function clearField()
	{

	}

	function back() {
		document.forms[0].action = "sector.do?methodName=show" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	};
</script>
<!--Main form section starts here-->
<html:form action="login/sector">

            <input type="hidden" name="sectorId" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${Bean.sectorId}"/>

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Sector Master</td>
		</tr>
	</table>
 

	<table width="100%" align="center" class="inputTBL">
		<tr>
			<th>Sector Code<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="sectorCode"  value="${Bean.sectorCode}" styleId="sectorCodeId" />
			</span></td>
		</tr>

		<tr>
			<th>Sector Name<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="sectorName"   value="${Bean.sectorName}" styleId="sectorNameId" />
			</span></td>
		</tr>

	</table>
	<div align="center">
		<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="saveRecord()" />
 	</div>
	 <br/>	 
	
	<table width="100%"  class="display" id="example">
		<thead>
              <tr>
		 			<th>S.No </th>
               		<th>Sector Code </th>
               		<th>Sector Name </th>               		               		 
               		<th>Edit</th>
               		<th>Delete</th>
               </tr>
 		</thead>
 		<tfoot>
             <tr>
		 			<th>S.No </th>
               		<th>Sector Code </th>
               		<th>Sector Name </th>               		               		 
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
						<td>${list['sectorCode']}</td>
						<td>${list['sectorName']}</td>		 
					    <td><a href="#" onclick="editRecord(${list['sectorId']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteRecord(${list['sectorId']})" class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>	
				</table>
			   


</html:form>
