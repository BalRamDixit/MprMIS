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
		var status=window.confirm("Do you want to Update this Record ?");
		if(status==true){
			document.assessmentBodyBean.action = "assessmentBodyMaster.do?methodName=updateAssessmentBody"+"&"+tokenParameter+"="+tokenValue;
			document.assessmentBodyBean.submit();
		}
	}
	else{
		var assBodyName = document.assessmentBodyBean.assBodyName.value;
		var assBodyCode = document.assessmentBodyBean.assBodyCode.value;
		var url = "assessmentBodyMaster.do?methodName=checkRecord&assBodyName="+assBodyName+"&assBodyCode="+assBodyCode;
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
	     alert("Assessment Body Name is already Present in Record");
	     return;
    }else{
    	 document.assessmentBodyBean.assBodyName.focus();
	     save();
    	 }
  	}
}

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	    if(document.assessmentBodyBean.assBodyName.value == "" ){
		  alert("Please fill Assessment Body Name");
  		  document.assessmentBodyBean.assBodyName.focus();
  		return false;
		} 
		
	var assBodyName=document.assessmentBodyBean.assBodyName.value;
	   alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
	if( !(alphnum.test(assBodyName)) ) {
		alert("This Format is not supported");
		document.assessmentBodyBean.assBodyName.value ="";
		document.assessmentBodyBean.assBodyName.focus();
		return false;
	}
		if( document.assessmentBodyBean.assBodyCode.value == "" )
		{
			alert("Please fill Assessment Body Code");
  		    document.assessmentBodyBean.assBodyCode.focus();
  		 return false;
		}
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
			document.assessmentBodyBean.action = "assessmentBodyMaster.do?methodName=saveAssessmentBody"+"&"+tokenParameter+"="+tokenValue;
		    document.assessmentBodyBean.submit();
	 	}
}
}
function editRecord(id){
	$('#IdForAction').val(id);
	document.assessmentBodyBean.action = "assessmentBodyMaster.do?methodName=editAssessmentBody"+"&"+tokenParameter+"="+tokenValue;
	document.assessmentBodyBean.submit();
}
function deleteRecord(id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	$('#IdForAction').val(id);
	var status=window.confirm("Do you want to delete this Record?");
	if(status==true){
		document.assessmentBodyBean.action = "assessmentBodyMaster.do?methodName=deleteAssessmentBody"+"&"+tokenParameter+"="+tokenValue;
		document.assessmentBodyBean.submit();
	}
}
}
</script>


                                                                  <!--Main form section starts here-->
<html:form action="login/assessmentBodyMaster">
<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Add Assessment Body Detail</td>
			   </tr>
			</table>
<table width="100%" >
		<tr>
			<td>
			 <input type="hidden" name="id" id="IdForAction"/>
			 <input type="hidden" name="editId" id="idForEdit" value="${assessmentBodyBean.id}"/>
			 
			
			
			<table width="100%" align="center" class="inputTBL">
				  <tr>
					 <th>Assessment Body Name <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="assBodyName" styleId="assBodyName"  onblur="this.value=this.value.toUpperCase();" value="${assessmentBodyBean.assBodyName}" /> </span></td>
				  </tr>
				  <tr>	
					 <th>Assessment Body Code <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="assBodyCode" styleId="assBodyCode"  onblur="this.value=this.value.toUpperCase();"  value="${assessmentBodyBean.assBodyCode}" /> </span></td>
				  </tr>
				  <tr>
					 <td><font color="red" size="1">&nbsp;<html:errors property="assBodyName"/></font></td>
					 <td><font color="red" size="1">&nbsp;<html:errors property="assBodyCode"/></font></td>
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
						<th>Assessment Body Name</th>
						<th>Assessment Body Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Sr no.</th>
						<th>Assessment Body Name</th>
						<th>Assessment Body Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</tfoot>
				<tbody>
					<logic:present name="assBodyList">
						<c:set var="indexCount" value="1" />
						<logic:iterate id="list" name="assBodyList" indexId="index">
							<tr>
								<td>${indexCount}</td>
								<td>${list['assBodyName']}</td>
								<td>${list['assBodyCode']}</td>	
								<td><a href="#" onclick="editRecord(${list['id']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
								<td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteRecord(${list['id']})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
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