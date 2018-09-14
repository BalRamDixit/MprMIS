<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<html>
<head>
    <meta charset="utf-8">

  

<script type="text/javascript">
function saveRecord(){
	
	var idForEdit=$('#idForEdit').val()+"abc";
	/* alert(idForEdit); */
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	if(idForEdit!="abc"){
		var status=window.confirm("Do you realy want to Update this Role Name and Description?");
		if(status==true){
			document.CertifyingAgencyForm.action = "certifyingAgency.do?methodName=updateRole"+"&"+tokenParameter+"="+tokenValue;
			document.CertifyingAgencyForm.submit();
		}
	}
	else{
		var certifyingAgencyName = document.CertifyingAgencyForm.certifyingAgencyName.value;
		var certifyingAgencyCode = document.CertifyingAgencyForm.certifyingAgencyCode.value;
		var url = "certifyingAgency.do?methodName=checkRecord&certifyingAgencyName="+certifyingAgencyName+"&certifyingAgencyCode="+certifyingAgencyCode;
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
   }}
   
function handleHttpResponse(){
	if (req.readyState == 4){	
    	var response = req.responseText; 
    	/* alert(response); */
    	if(response == 'true'){
	     	alert("<bean:message key="error.roleExist" />"); 
	     	return;
    	 }else{
	         document.CertifyingAgencyForm.certifyingAgencyName.focus();
		     save();
    	  }
     	}
    }
    
function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	if(document.CertifyingAgencyForm.certifyingAgencyName.value == "" ){
  		/* var arg='<bean:message key="label.roleName" />';  
		var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />'; 
		
		alert(mess); */
		alert("Please fill Certifying Agency Name");
  		document.CertifyingAgencyForm.roleName.focus();
  		return false;
		} 
		var certifyingAgencyName=document.CertifyingAgencyForm.certifyingAgencyName.value;
	alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
	if( !(alphnum.test(certifyingAgencyName)) ) {
		/* var arg='<bean:message key="label.roleName" />';  
		var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
		alert(mess); */
		alert("This Format is not supported");
		document.CertifyingAgencyForm.certifyingAgencyName.value ="";
		document.CertifyingAgencyForm.certifyingAgencyName.focus();
		return false;
	}
		if( document.CertifyingAgencyForm.certifyingAgencyCode.value == "" ){
  		/* var arg='<bean:message key="access.addRole.roleDesc.label" />';  
		var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
		alert(mess); */
		alert("Please fill Certifying Agency Code")
  		document.CertifyingAgencyForm.certifyingAgencyCode.focus();
  		return false;
		}
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
		document.CertifyingAgencyForm.action = "certifyingAgency.do?methodName=saveRole"+"&"+tokenParameter+"="+tokenValue;
		document.CertifyingAgencyForm.submit();
	 	}
 }}


function editRecord(certifyingAgencyId){
	/* alert(certifyingAgencyId); */
	
	$('#IdForAction').val(certifyingAgencyId);
	document.CertifyingAgencyForm.action = "certifyingAgency.do?methodName=editRole"+"&"+tokenParameter+"="+tokenValue;
	document.CertifyingAgencyForm.submit();
}

function deleteRecord(certifyingAgencyId){
	
	$('#IdForAction').val(certifyingAgencyId);
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm("Do you realy want to delete this role?");
	if(status==true){
		document.CertifyingAgencyForm.action = "certifyingAgency.do?methodName=deleteRole"+"&"+tokenParameter+"="+tokenValue;
		document.CertifyingAgencyForm.submit();
	}
}}

	
$(document).ready(function () {
	$('#example').DataTable();
	
});

</script>
  
</head>

<body>
<html:form action="login/certifyingAgency">

<input type="hidden" name="certifyingAgencyId" id="IdForAction"/>
<input type="hidden" name="editId" id="idForEdit" value="${certifyingAgencyForm.certifyingAgencyId}"/>
             <table width="100%" class="pageHeaderTable" >
				<tr>
					<td align="center" class="pageHeader">Certifying Agency</td>
			   </tr>
			</table>
     <table width="100%" align="center"  class="inputTBL" >
      <tr>
          <td>
           
             <table width="100%" class="inputTBL" >
				<tr>
                   <th colspan="2">Certifying Agency Name <span class="text-error">*</span></th>
                   <td > <html:text  maxlength="100" property="certifyingAgencyName" onblur="this.value=this.value.toUpperCase();" styleId="" value="${certifyingAgencyForm.certifyingAgencyName}"/></td>
                </tr>
				<tr>
                   <th colspan="2">Certifying Agency Code <span class="text-error">*</span></th>
                   <td > <html:text  maxlength="100" property="certifyingAgencyCode" onblur="this.value=this.value.toUpperCase();" styleId="" value="${certifyingAgencyForm.certifyingAgencyCode}"/></td>
               </tr>
			  </table>
         
                <div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
				<%-- <input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> --%> 
			   </div>
		  
	      <table width="100%" border="1" id="example" class="display">
	        <thead>
			<tr>
			<td> S.No </td>
	          <td> Certifying Agency Name</td>
	          <td> Certifying Agency Code</td>
	          <td>Edit</td>
	          <td>Delete</td>
			  </tr>
			</thead>
			
			<tbody>
			<logic:present name="roleList">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="roleList" name="roleList">
					<tr>
					 <td align="center">${indexCount}</td>  
					 <td> ${roleList.certifyingAgencyName} </td> 
					 <td> ${roleList.certifyingAgencyCode} </td>
	                 <td><a href="#" onclick="editRecord(${roleList.certifyingAgencyId})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
					 <td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteRecord(${roleList.certifyingAgencyId})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
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
</body>
</html>