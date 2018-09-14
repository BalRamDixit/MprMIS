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
	var state_id=$("#stateIdId").val();
	
	var statecode=$("#stateCodeid").val();
	var statename=$("#statenameid").val();
	var stateshortname=$("#stateShortNameid").val();
	var centershare=$("#centerShareid").val();
	var stateshar=$("#stateShareid").val();
	var sc_st=$("#sc_stid").val();
	var miniority=$("#miniorityid").val();
	var women=$("#womenid").val();
	if(statecode=="" || statecode==null){
		alert("please enter state code");
		return flase;
	}
	if(statename=="" || statename==null){
		alert("please enter state name");
		return flase;
	}
	if(stateshortname=="" || stateshortname==null){
		alert("please enter state short name");
		return flase;
	}
	if(centershare=="" || centershare==null){
		alert("please enter center share");
		return flase;
	}
	if(stateshar=="" || stateshar==null){
		alert("please enter state share");
		return flase;
	}
	if(sc_st=="" || sc_st==null){
		alert("please enter sc st %");
		return flase;
	}
	if(miniority=="" || miniority==null){
		alert("please enter miniority %");
		return flase;
	}
	if(women=="" || women==null){
		alert("please enter women %");
		return flase;
	}
	
	
	
	if(state_id==""||state_id==null||state_id=="null"||state_id=="0"){
		var strconfirm = confirm("Are you sure you want to Save ?");
	    if (strconfirm == true) {
	   
	    	document.forms[0].action="stateMaster.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	    }
	}
	else{
		var strconfirm = confirm("Are you sure you want to Update ?");
	    if (strconfirm == true) {
	   
	    	document.forms[0].action="stateMaster.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	    }
	
	}
	}
	
}
function edit(stateId){
	//alert(stateId);
	document.forms[0].action="stateMaster.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue+"&id="+stateId;
	document.forms[0].submit();
}
function deleteState(stateId){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var strconfirm = confirm("want to delete ?");
    if (strconfirm == true) {
   
    	document.forms[0].action="stateMaster.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue+"&id="+stateId;
    	document.forms[0].submit();
    	
    	
    }
	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   

   return true;
}
$(document).ready(function(){
 	
	$('#example').DataTable();
	});
  
</script>

 <html:form action="login/stateMaster">
	<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">State Master</td>
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
			<th>State Code</th>
			<td><html:text property="stateCode" styleId="stateCodeid" value="${stateBean.stateCode}" ></html:text></td>
		</tr>
		<tr>

			<th>State Name</th>
			<td><html:text property="stateName" styleId="statenameid" value="${stateBean.stateName}"></html:text><html:hidden property="stateId" styleId="stateIdId" value="${stateBean.stateId}"/></td>

		</tr>
		<tr>
			<th>Short Name</th>
			<td><html:text property="stateShortName" styleId="stateShortNameid"  value="${stateBean.stateShortName}"></html:text></td>
		</tr>
		<tr>
			<th>Center Share %</th>
			<td><html:text property="centerShare" styleId="centerShareid" value="${stateBean.centerShare}" onkeypress="return isNumberKey(event)"></html:text></td>
		</tr>
		<tr>
			<th>State Share %</th>
			<td><html:text property="stateShare" styleId="stateShareid" value="${stateBean.stateShare}" onkeypress="return isNumberKey(event)"></html:text></td>
		</tr>
		<tr>
			<th>SC+ST %</th>
			<td><html:text property="sc_st"  styleId="sc_stid" value="${stateBean.sc_st}" onkeypress="return isNumberKey(event)"></html:text></td>
		</tr>
		<tr>
			<th>Minority %</th>
			<td><html:text property="miniority" styleId="miniorityid" value="${stateBean.miniority}" onkeypress="return isNumberKey(event)"></html:text></td>
		</tr>
		<tr>
			<th >Woman %</th>
			<td ><html:text property="women" styleId="womenid" value="${stateBean.women}" onkeypress="return isNumberKey(event)"></html:text></td>

			
		</tr>
	</table>
	<div align="center">
	<html:button property="" styleClass="button checkPermissionForFormForInsert" onclick="save()"
					value="save"></html:button>
	</div>
				
				<table width="100%" class="display" id="example">
				<thead>
				<tr>
				<th> S. No.</th>
				<th> State Code</th>
				<th> State Name</th>
				<th> Short Name </th>
				<th> Center Share % </th>
				<th> State Share % </th>
				<th> SC+ST % </th>
				<th> Minority % </th>
				<th> Woman % </th>
				<th>Edit</th>
				<th>Delete</th>
				</tr>
				</thead>
				<tbody>
				<logic:present name="stateList">
							<c:set var="indexCount" value="13" />
				<logic:iterate name="stateList" id="stateList"  scope="request">
				<tr>
				
				
				 <td>${indexCount}</td>
				<td>${stateList.stateCode}</td>
				<td>${stateList.stateName}</td>
				<td>${stateList.stateShortName}</td>
				<td>${stateList.centerShare}</td>
				<td>${stateList.stateShare}</td>
				<td>${stateList.sc_st}</td>
				<td>${stateList.miniority}</td>
				<td>${stateList.women}</td>
				<td><a href="#" onclick="edit('${stateList.stateId}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
				<td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteState('${stateList.stateId}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
<%-- 				<td><html:button property="" value="edit" onclick="edit('${stateList.stateId}')" /><html:button property="" value="delete" onclick="deleteState('${stateList.stateId}')" /></td> --%>
				 
				
				</tr>
				<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
				</tbody>
				<tfoot>
				<tr>
				<td> S. No.</td>
				<td> State Code</td>
				<td> State Name</td>
				<td> Short Name </td>
				<td> Center Share % </td>
				<td> State Share % </td>
				<td> SC+ST % </td>
				<td> Minority % </td>
				<td> Woman % </td>
				<td>Edit</td>
				<td>Delete</td>
				</tr>
				</tfoot>
				</table>
		
	
</html:form>
