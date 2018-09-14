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
		var status=window.confirm("Do you want to Update this Record");
		if(status==true){
			document.parliamentaryConstituencyMasterBean.action = "parliamentaryConstituencyMaster.do?methodName=updateCtsa"+"&"+tokenParameter+"="+tokenValue;
			document.parliamentaryConstituencyMasterBean.submit();
		}
	}
	else{
		var name = document.parliamentaryConstituencyMasterBean.name.value;
		var code = document.parliamentaryConstituencyMasterBean.code.value;
		var url = "parliamentaryConstituencyMaster.do?methodName=checkRecord&name="+name+"&code="+code;
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
	     alert("Ctsa Name is already Present in Record");
	     return;
    }else{
    	 document.parliamentaryConstituencyMasterBean.name.focus();
	     save();
    	 }
  	}
}

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
	if(document.parliamentaryConstituencyMasterBean.name.value == "" ){
		alert("Please fill Ctsa Name");
  		document.parliamentaryConstituencyMasterBean.name.focus();
  		return false;
		} 
		
	var name=document.parliamentaryConstituencyMasterBean.name.value;
	
	alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
	if( !(alphnum.test(name)) ) {
		alert("This Format is not supported");
		document.parliamentaryConstituencyMasterBean.name.value ="";
		document.parliamentaryConstituencyMasterBean.name.focus();
		return false;
	}
		if( document.parliamentaryConstituencyMasterBean.code.value == "" )
		{
			alert("Please fill Ctsa Name");
  		 document.parliamentaryConstituencyMasterBean.code.focus();
  		 return false;
		}
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
			document.parliamentaryConstituencyMasterBean.action = "parliamentaryConstituencyMaster.do?methodName=saveCtsa"+"&"+tokenParameter+"="+tokenValue;
		    document.parliamentaryConstituencyMasterBean.submit();
	 	}
}
}
function editRecord(id){

	$('#IdForAction').val(id);
	document.parliamentaryConstituencyMasterBean.action = "parliamentaryConstituencyMaster.do?methodName=editCtsa"+"&"+tokenParameter+"="+tokenValue;
	document.parliamentaryConstituencyMasterBean.submit();

}

function deleteRecord(id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
	$('#IdForAction').val(id);
	var status=window.confirm("Do you want to delete this Record?");
	if(status==true){
		document.parliamentaryConstituencyMasterBean.action = "parliamentaryConstituencyMaster.do?methodName=deleteCtsa"+"&"+tokenParameter+"="+tokenValue;
		document.parliamentaryConstituencyMasterBean.submit();
	}
}
}
</script>


                                                                  <!--Main form section starts here-->
<html:form action="login/parliamentaryConstituencyMaster">
	<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Add Parliamentary Constituency Detail</td>
			   </tr>
	</table>
<table width="100%" >
		<tr>
			<td>
			 <input type="hidden" name="id" id="IdForAction"/>
			 <input type="hidden" name="editId" id="idForEdit" value="${ctsaMasterBean.id}"/>
			 
		
			
			<table width="100%" align="center" class="inputTBL">
				  <tr>
					 <th>Parliamentary Constituency Name <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="name" styleId="name"  onblur="this.value=this.value.toUpperCase();" value="${ctsaMasterBean.name}" /> </span></td>
				  </tr>
				  <tr>	
					 <th>Parliamentary Constituency Code <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="code" styleId="code"  onblur="this.value=this.value.toUpperCase();"  value="${ctsaMasterBean.code}" /> </span></td>
				  </tr>
				  <tr>
					 <td><font color="red" size="1">&nbsp;<html:errors property="name"/></font></td>
					 <td><font color="red" size="1">&nbsp;<html:errors property="code"/></font></td>
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
						<th>Sr NO</th>
						<th>Parliamentary Constituency Name</th>
						<th>Parliamentary Constituency Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Sr NO</th>
						<th>Parliamentary Constituency Name</th>
						<th>Parliamentary Constituency Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</tfoot>
				<tbody>
					<logic:present name="ctsaList">
						<c:set var="indexCount" value="1" />
						<logic:iterate id="list" name="ctsaList" indexId="index">
							<tr>
								<td>${indexCount}</td>
								<td>${list['name']}</td>
								<td>${list['code']}</td>	
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