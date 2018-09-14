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
			
			document.forms[0].action = "trade.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
	}
	else{		
		save();	 
	}
}
	function save() {
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
 		document.forms[0].action = "trade.do?methodName=save" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
		}
	};
	
	function edit(id){		
  		$('#IdForAction').val(id);		 
		document.forms[0].action = "trade.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	 
	}
	
	
	function deleteData(id){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
   		  $('#IdForAction').val(id);
		document.forms[0].action = "trade.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}
	}
	function clearField()
	{

	}

	function back() {
		document.forms[0].action = "trade.do?methodName=show" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	};
</script>
<!--Main form section starts here-->
<html:form action="login/trade">

            <input type="hidden" name="tradeId" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${Bean.tradeId}"/>

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Trade Master</td>
		</tr>
	</table>
 

	<table width="100%" align="center" class="inputTBL">
		<tr>
			<th>Trade Code<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="tradeCode"  value="${Bean.tradeCode}" styleId="tradeCodeId" />
			</span></td>
		</tr>

		<tr>
			<th>Trade Name<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="tradeName"   value="${Bean.tradeName}" styleId="tradeNameId" />
				</span></td>
		</tr>
			<tr>
			<%--<th>Sector Name<span class="text-error"></span></th>
			 <td><span class="text-error"> <html:text
						property="sectorCode"   value="${Bean.sectorCode}" styleId="sectorCodeId" />
				</span></td> --%>
				
				<th width="25%">Sector Name</th>
			<td width="25%">
			<html:select property="sectorId" styleId="sectorCodeId"  styleClass="form-control" value="${Bean.sectorId}">
				<html:option value="0">-select-</html:option>
				<logic:present name="sectorList">
					<logic:iterate id="sectorList" name="sectorList">
						<html:option value="${sectorList.sectorId}">${sectorList.sectorName}</html:option>
					</logic:iterate>
				</logic:present>
			</html:select> 
			</td>
		</tr>
		<tr>				
			<th width="25%">Assessment Body</th>
			<td width="25%">
			<html:select property="assessmentBodyId" styleId="assessmentBodyId"  styleClass="form-control" value="${Bean.assessmentBodyId}" >
								<html:option value="0">-select-</html:option>
								<logic:present name="assBodyList">
									<logic:iterate id="assBodyList" name="assBodyList">
										<html:option value="${assBodyList.id}">${assBodyList.assBodyName}</html:option>
									</logic:iterate>
								</logic:present>
		</html:select> 
		</td>
			
				
		</tr> 
		
		
			<tr>
			<th>Course Duration(in no. of Hours)<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="courseDuration"   value="${Bean.courseDuration}" styleId="courseDurationId" />
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
               		<th>Trade Code </th>
               		<th>Trade Name </th> 
               		<th>Sector Name </th>
               		<th>Assessment Body </th> 
               		<th>Course Duration(in no. of Hours) </th>
                	<th>Edit</th>
					<th>Delete</th>
               </tr>
 		</thead>
 		<tfoot>
             <tr>
		 			<th>S.No </th>
               		<th>Trade Code </th>
               		<th>Trade Name </th> 
               		<th>Sector Name </th>
               		<th>Assessment Body </th> 
               		<th>Course Duration(in no. of Hours) </th>
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
						<td>${list['tradeCode']}</td>
						<td>${list['tradeName']}</td>	
						<td>${list.sectorId.sectorName}</td>
						<td>${list.assessmentBodyId.assBodyName}</td>	
						<td>${list['courseDuration']}</td>									 
					    <td><a href="#" onclick="editRecord(${list['tradeId']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteRecord(${list['tradeId']})" class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present>
		</tbody>	
				</table>
			   


</html:form>
