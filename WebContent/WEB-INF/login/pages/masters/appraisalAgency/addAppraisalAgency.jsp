<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>  
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link type="text/css" href="css/displayTag.css" rel="stylesheet"  />

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
		var status=window.confirm("Do you want to Update this Record?");
		if(status==true){
			document.appraisalAgencyBean.action = "appraisalAgencyMaster.do?methodName=updateAppraisalAgency"+"&"+tokenParameter+"="+tokenValue;
			document.appraisalAgencyBean.submit();
		}
	}
	else{
		var appraisalName = document.appraisalAgencyBean.appraisalName.value;
		var appraisalCode = document.appraisalAgencyBean.appraisalCode.value;
		var url = "appraisalAgencyMaster.do?methodName=checkRecord&appraisalName="+appraisalName+"&appraisalCode="+appraisalCode;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("GET", url, true);
		req.setRequestHeader(tokenParameter, tokenValue);
		req.onreadystatechange = handleHttpResponse;
		req.send(null);
	}
}
}
function handleHttpResponse(){
	
	if (req.readyState == 4){	
	var response = req.responseText; 
    if(response == 'true'){
	     alert("Appraisal Agency Name is already Present in Record");
	     return;
    }else{
    	 document.appraisalAgencyBean.appraisalName.focus();
	     save();
    	 }
  	}
}

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	if(document.appraisalAgencyBean.appraisalName.value == "" ){
  		alert("Please fill Appraisal Agency Name");
  		document.appraisalAgencyBean.appraisalName.focus();
  		return false;
		} 
		
	var appraisalName=document.appraisalAgencyBean.appraisalName.value;
	
	alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
	if( !(alphnum.test(appraisalName)) ) {
		alert("This Format is not supported");
		document.appraisalAgencyBean.appraisalName.value ="";
		document.appraisalAgencyBean.appraisalName.focus();
		return false;
	}
		if( document.appraisalAgencyBean.appraisalCode.value == "" )
		{
		 alert("Please fill Appraisal Agency Code");
  		 document.appraisalAgencyBean.appraisalCode.focus();
  		 return false;
		}
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
			document.appraisalAgencyBean.action = "appraisalAgencyMaster.do?methodName=saveAppraisalAgency"+"&"+tokenParameter+"="+tokenValue;
		    document.appraisalAgencyBean.submit();
	 	}
}
}

function editRecord(id){
	$('#IdForAction').val(id);
	document.appraisalAgencyBean.action = "appraisalAgencyMaster.do?methodName=editAppraisalAgency"+"&"+tokenParameter+"="+tokenValue;
	document.appraisalAgencyBean.submit();
}
function deleteRecord(id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	$('#IdForAction').val(id);
	var status=window.confirm("Do you want to delete this Record?");
	if(status==true){
		document.appraisalAgencyBean.action = "appraisalAgencyMaster.do?methodName=deleteAppraisalAgency"+"&"+tokenParameter+"="+tokenValue;
		document.appraisalAgencyBean.submit();
	}
}
}
</script>


                                                                  <!--Main form section starts here-->
<html:form action="login/appraisalAgencyMaster">
<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Add Appraisal Detail</td>
			   </tr>
			</table>
<table width="100%" >
		<tr>
			<td>
			 <input type="hidden" name="id" id="IdForAction"/>
			 <input type="hidden" name="editId" id="idForEdit" value="${appraisalAgencyBean.id}"/>
			 
			
			
			<table width="100%" align="center" class="inputTBL">
				  <tr>
					 <th>Appraisal Agency Name <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="appraisalName" styleId="appraisalName"  onblur="this.value=this.value.toUpperCase();" value="${appraisalAgencyBean.appraisalName}" /> </span></td>
				  </tr>
				  <tr>	
					 <th>Appraisal Agency Code <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="appraisalCode" styleId="appraisalCode"  onblur="this.value=this.value.toUpperCase();"  value="${appraisalAgencyBean.appraisalCode}" /> </span></td>
				  </tr>
				  <tr>
					 <td><font color="red" size="1">&nbsp;<html:errors property="appraisalName"/></font></td>
					 <td><font color="red" size="1">&nbsp;<html:errors property="appraisalCode"/></font></td>
					 <td></td>
				  </tr>
			</table>
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
			</div>
			</td>
			
		</tr>
		
	 	<tr>
			<td>
				<table  class="display" id="example">
				<thead>
					<tr>	
						<th>Sr no.</th>
						<th>Appraisal Agency Name</th>
						<th>Appraisal Agency Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Sr no.</th>
						<th>Appraisal Agency Name</th>
						<th>Appraisal Agency Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</tfoot>
				<tbody>
					<logic:present name="appAgencyList">
						<c:set var="indexCount" value="1" />
						<logic:iterate id="list" name="appAgencyList" indexId="index">
							<tr>
								<td>${indexCount}</td>
								<td>${list['appraisalName']}</td>
								<td>${list['appraisalCode']}</td>	
								<td><a href="#" onclick="editRecord(${list['id']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
								<td><a href="#" class="checkPermissionForFormForInsert"  onclick="deleteRecord(${list['id']})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
							</tr>
						<c:set var="indexCount" value="${indexCount + 1}" />
					</logic:iterate>
				 </logic:present>
				</tbody>					
			</table>
			
			
			</td>
			
		</tr> 
	</table>


</html:form>
                                                                  <!--Main form section ends here-->