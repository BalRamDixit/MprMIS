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
function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var state_id=$("#specialAreaIdId").val();
	//alert(state_id);
	var specialAreaCode=$("#specialAreaCodeid").val();
	var specialAreaName=$("#specialAreaNameid").val();
	if(specialAreaCode=="" || specialAreaCode==null){
		alert("please enter special area code");
		return false;
	}if(specialAreaName=="" || specialAreaName==null){
		alert("please enter special area name");
		return false;
	}
	
	
	
	if(state_id==""||state_id==null||state_id=="null"||state_id=="0"){
		var strconfirm = confirm("Are you sure you want to Save ?");
	    if (strconfirm == true) {
	   
	    	document.forms[0].action="specialAreaMaster.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	    }
	}
	else{
		var strconfirm = confirm("Are you sure you want to Update ?");
	    if (strconfirm == true) {
	   
	    	document.forms[0].action="specialAreaMaster.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	    }
	
	}
	}
	
}
function edit(Id){
	//alert(stateId);
	document.forms[0].action="specialAreaMaster.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue+"&specialAreaId="+Id;
	document.forms[0].submit();
}
function deletespecialArea(Id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var strconfirm = confirm("want to delete ?");
    if (strconfirm == true) {
   
    	document.forms[0].action="specialAreaMaster.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue+"&specialAreaId="+Id;
    	document.forms[0].submit();
    	
    	
    }
	}
	
}
$(document).ready(function(){
 	
	$('#example').DataTable();
	});
</script>

 <html:form action="login/specialAreaMaster">
	<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Special Area Master</td>
			   </tr>
			</table>
			<% 
			String value=(String) request.getAttribute("message");
			
			if(value==""||value==null||value=="null"){
				
			} else{
				out.print(request.getAttribute("message"));
			}%>		
				
	<table width="100%" align="center" class="inputTBL">
		
		<tr>
			<th>Special Area Code</th>
			<td><html:text property="specialAreaCode" value="${specialAreaBean.specialAreaCode}" styleId="specialAreaCodeid"/><html:hidden property="specialAreaId" styleId="specialAreaIdId" value="${specialAreaBean.specialAreaId}"/></td>
            
			
		</tr>
		<tr><th>Special Area Name</th>
			<td><html:text property="specialAreaName" value="${specialAreaBean.specialAreaName}" styleId="specialAreaNameid"/></td>
			</tr>
	</table>
	<div align="center">
	<html:button styleClass="button checkPermissionForFormForInsert" property="" onclick="save()" value="save"></html:button>
	</div>
				
				<table width="100%" class="display" id="example">
				<thead>
				<tr>
				<th>S.No.</th>
				<th>Special Area Code</th>
				<th>Special Area Name</th>
				<th>Edit</th>
				<th>Delete</th>
				</tr>
				</thead>
				<tbody>
				<logic:present name="specialAreaList">
							<c:set var="indexCount" value="1" />
				<logic:iterate name="specialAreaList" id="specialAreaList"  scope="request">
				<tr>
				<td>${indexCount}</td>
				<td>${specialAreaList.specialAreaCode}</td>
				<td>${specialAreaList.specialAreaName}</td>
				<td><a href="#" onclick="edit('${specialAreaList.specialAreaId}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
				<td><a href="#" class="checkPermissionForFormForInsert" onclick="deletespecialArea('${specialAreaList.specialAreaId}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
				
				<%-- <td><html:button property="" value="edit" onclick="edit('${specialAreaList.specialAreaId}')"></html:button> <html:button property="" value="delete" onclick="deletespecialArea('${specialAreaList.specialAreaId}')"></html:button>   </td>
				 --%></tr>
				<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
				</tbody>
				<tfoot>
				<tr>
				<td>S. No.</td>
				<td>Special Area Code</td>
				<td>Special Area Name</td>
				<td>Edit</td>
				<td>Delete</td>
				</tr></tfoot>
				</table>
		
	
</html:form>
