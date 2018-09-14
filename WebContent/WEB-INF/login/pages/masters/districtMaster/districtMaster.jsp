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
function saveform(){
	//alert("aa");
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
var state_id=$("#districtIdId").val();
//alert(state_id);
var districtcode=$("#districtCodeid").val();
var districtname=$("#districtNameid").val();
var state=$("#state_idId").val();
var specialArea=$("#specialAreaid").val();
if(districtcode=="" || districtcode==null){
	alert("please enter district code");
	return false;
}
if(districtname==""||districtname==null){
	alert("please enter district name");
	return false;
}

if(state=="0"){
	alert("please select state");
	return false;
}if(specialArea=="Yes"){
	var typeOfSpecialArea=$("#typeOfSpecialAreaid").val();
	if(typeOfSpecialArea=="0"){
		alert("please select type special area");
		return false; 
	}
}



if(state_id==""||state_id==null||state_id=="null"||state_id=="0"){
	var strconfirm = confirm("Are you sure you want to Save ?");
    if (strconfirm == true) {
   
    	document.forms[0].action="districtMaster.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
    }
}else{
	var strconfirm = confirm("Are you sure you want to Update ?");
    if (strconfirm == true) {
   
    	document.forms[0].action="districtMaster.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
    }
}
	}
}
  function edit(id){
	  document.forms[0].action="districtMaster.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue+"&districtId="+id;
		document.forms[0].submit();
  }
  function deletedistrict(id){
	  var x=checkPermissionForFormForInsert();
		if(x=='true'){
	  var strconfirm = confirm("Are you sure you want to delete ?");
	    if (strconfirm == true) {
	  document.forms[0].action="districtMaster.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue+"&districtId="+id;
		document.forms[0].submit();
	    }
		}
  }
  $(document).ready(function(){
	 	
		$('#example').DataTable();
		});
		
</script>

 <html:form action="login/districtMaster">
	<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">District Master</td>
			   </tr>
			</table>			
	<table width="100%" align="center" class="inputTBL">
		<tr>
		<th>District Code</th>
		<td><html:text property="districtCode" styleId="districtCodeid" value="${districtDeatil.districtCode}"></html:text><html:hidden styleId="districtIdId" property="districtId"  value="${districtDeatil.districtId}"></html:hidden></td>
		</tr>
		<tr>
		<th>District Name</th>
		<td><html:text property="districtName" styleId="districtNameid" value="${districtDeatil.districtName}"></html:text></td>
		</tr>
		<tr>
		<th>State</th>
		<td>
		<html:select property="state_id" styleId="state_idId"  styleClass="form-control" value="${districtDeatil.state.stateId}">
								<html:option value="0">-select-</html:option>
								<logic:present name="statelist">
									<logic:iterate id="statelist" name="statelist">
										<html:option value="${statelist.stateId}">${statelist.stateName}</html:option>
									</logic:iterate>
								</logic:present>
		</html:select>
		
		</td>
		</tr>
		<tr>
		<th>Special Area</th>
		<td><html:select property="specialArea" styleId="specialAreaid"  value="${districtDeatil.specialArea}"><html:option value="No">No</html:option><html:option value="Yes">Yes</html:option></html:select>   </td>
		</tr>
		<tr>
			<th >Type of Special Area</th>
			<td >
			<html:select property="typeOfSpecialArea" styleId="typeOfSpecialAreaid"  styleClass="form-control" value="${districtDeatil.typeSpecialArea.specialAreaId}">
								<html:option value="0">-select-</html:option>
								<logic:present name="specialAreaList">
									<logic:iterate id="specialAreaList" name="specialAreaList">
										<html:option value="${specialAreaList.specialAreaId}">${specialAreaList.specialAreaName}</html:option>
									</logic:iterate>
								</logic:present>
		</html:select> 
		</td>

			
		</tr>
	</table>
	<div align="center">
	<html:button property="" styleClass="button checkPermissionForFormForInsert" onclick="saveform()"
					value="save"></html:button>
	</div>
				
				
				
				
				<table width="100%" class="display" id="example">
				<thead>
				<tr>
				<th>S. No.</th>
				<th>District Code</th>
				<th>District Name</th>
				<th>State Name</th>
				<th>Special Area</th>
				<th>Type of Special Area</th>
				<th>Edit</th>
				<th>Delete</th>
				</tr>
				</thead>
				<tbody>
				<logic:present name="districtList">
							<c:set var="indexCount" value="1" />
				<logic:iterate name="districtList" id="districtList"  scope="request">
				<tr>
				<td>${indexCount}</td>
				<td>${districtList.districtCode}</td>
				<td>${districtList.districtName}</td>
				<td>${districtList.state.stateName}</td>
				<td>${districtList.specialArea}</td>
				<td>${districtList.typeSpecialArea.specialAreaName}</td>
				
				<td><a href="#" onclick="edit('${districtList.districtId}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
				<td><a href="#" class="checkPermissionForFormForInsert" onclick="deletedistrict('${districtList.districtId}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
				<%-- <td><html:button property="" value="edit" onclick="edit('${districtList.districtId}')"></html:button> <html:button property="" value="delete" onclick="deletedistrict('${districtList.districtId}')"></html:button></td>
				 --%>
				</tr>
				<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
				</tbody>
				<tfoot>
				<tr>
				<td>S. No.</td>
				<td>District Code</td>
				<td>District Name</td>
				<td>State Name</td>
				<td>Special Area</td>
				<td>Type of Special Area</td>
				<td>Edit</td>
				<td>Delete</td>
				</tr>
				</tfoot>
				</table>
		
	
</html:form>
