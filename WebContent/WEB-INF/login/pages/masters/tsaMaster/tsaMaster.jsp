<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE>
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
			document.tsaMasterBean.action = "tsaMaster.do?methodName=updateTsa&"+tokenParameter+"="+tokenValue;
			document.tsaMasterBean.submit();
		}
	}
	else{
		var tsaName = document.tsaMasterBean.tsaName.value;
		var tsaCode = document.tsaMasterBean.tsaCode.value;
		var url = "tsaMaster.do?methodName=checkRecord&tsaName="+tsaName+"&tsaCode="+tsaCode;
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
	     alert("Tsa Name is already Present in Record");
	     return;
    }else{
    	 document.tsaMasterBean.tsaName.focus();
	     save();
    	 }
  	}
}

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
	
	if(document.tsaMasterBean.tsaName.value == "" ){
		alert("Please fill Tsa Name");
  		document.tsaMasterBean.tsaName.focus();
  		return false;
		} 
		
	var tsaName=document.tsaMasterBean.tsaName.value;
	
	alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
	if( !(alphnum.test(tsaName)) ) {
		alert("This Format is not supported");
		document.tsaMasterBean.tsaName.value ="";
		document.tsaMasterBean.tsaName.focus();
		return false;
	}
		if( document.tsaMasterBean.tsaCode.value == "" )
		{
			alert("Please fill Tsa Name");
  		 document.tsaMasterBean.tsaCode.focus();
  		 return false;
		}
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	if(status==true){
			document.tsaMasterBean.action = "tsaMaster.do?methodName=saveTsa"+"&"+tokenParameter+"="+tokenValue;
		    document.tsaMasterBean.submit();
	 	}
}
}
function editRecord(id){
	$('#IdForAction').val(id);
	document.tsaMasterBean.action = "tsaMaster.do?methodName=editTsa"+"&"+tokenParameter+"="+tokenValue;
	document.tsaMasterBean.submit();
}
function deleteRecord(id){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
	$('#IdForAction').val(id);
	var status=window.confirm("Do you want to delete this Record ?");
	if(status==true){
		document.tsaMasterBean.action = "tsaMaster.do?methodName=deleteTsa"+"&"+tokenParameter+"="+tokenValue;
		document.tsaMasterBean.submit();
	}
}
}
</script>


                                                                  <!--Main form section starts here-->
<html:form action="login/tsaMaster">
<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Add TSA Detail</td>
			   </tr>
			</table>
<table width="100%" >
		<tr>
			<td>
			 <input type="hidden" name="id" id="IdForAction"/>
			 <input type="hidden" name="editId" id="idForEdit" value="${tsaMasterBean.id}"/>
			 
			
			
			<table width="100%" align="center" class="inputTBL">
				  <tr>
					 <th>Tsa Name <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="tsaName" styleId="tsaName"  onblur="this.value=this.value.toUpperCase();" value="${tsaMasterBean.tsaName}" /> </span></td>
				  </tr>
				  <tr>	
					 <th>Tsa Code <span class="text-error">*</span></th>
					 <td><span class="text-error"> <html:text property="tsaCode" styleId="tsaCode"  onblur="this.value=this.value.toUpperCase();"  value="${tsaMasterBean.tsaCode}" /> </span></td>
				  </tr>
				  <tr>
					 <td><font color="red" size="1">&nbsp;<html:errors property="tsaName"/></font></td>
					 <td><font color="red" size="1">&nbsp;<html:errors property="tsaCode"/></font></td>
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
						<th>Tsa Name</th>
						<th>Tsa Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Sr no.</th>
						<th>Tsa Name</th>
						<th>Tsa Code</th>
						<th>Edit</th>
						<th>Delete</th>
						
					</tr>
				</tfoot>
				<tbody>
					<logic:present name="tsaList">
						<c:set var="indexCount" value="1" />
						<logic:iterate id="list" name="tsaList" indexId="index">
							<tr>
								<td>${indexCount}</td>
								<td>${list['tsaName']}</td>
								<td>${list['tsaCode']}</td>	
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