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
	 var sanctionId=$("#sanctionIdCheck").val();
	
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	if(sanctionId==""||sanctionId==null||sanctionId=="null"||sanctionId=="0") 
		var strconfirm = confirm("Are you sure you want to Save ?");
	    if (strconfirm == true) {
	   
	    	document.forms[0].action="sanctionPost.do?methodName=save&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	    }
	  else{
		var strconfirm = confirm("Are you sure you want to Update ?");
	    if (strconfirm == true) {
	   
	    	document.forms[0].action="sanctionPost.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	    }
	}  
	}}
function editRecord(id){
	  document.forms[0].action="sanctionPost.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue+"&id="+id;
	  document.forms[0].submit();
}
function deleteRecord(id){
	/*   alert(id); */
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	  var strconfirm = confirm("Are you sure you want to delete ?");
	    if (strconfirm == true) {
	  document.forms[0].action="sanctionPost.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue+"&id="+id;
		document.forms[0].submit();
	    }
}}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   

   return true;
};




$(document).ready(function () {
	$('#example').DataTable();
	
});
</script>
  
</head>

<body>
<html:form action="login/sanctionPost">

            <table width="100%" class="pageHeaderTable" >
				<tr>
					<td align="center" class="pageHeader">Sanction Post</td>
			   </tr>
			</table>

       <table width="100%" align="center"  class="inputTBL" >

            <tr >
            <th >State Name <span class="text-error">*</span></th>
            <td >
            <html:select property="stateId" styleId="stateId"  styleClass="form-control"  value="${sanctionDeatil.stateId}" >
					<html:option value="0">-select-</html:option>
					<logic:present name="stateList">
						<logic:iterate id="stateList" name="stateList" >
							<html:option value="${stateList.stateId}">${stateList.stateName}</html:option>
						</logic:iterate>
					</logic:present>
		   </html:select>
             </td>
             </tr>
             <tr>
             <th >Designation <span class="text-error">*</span></th>
              <td >
            <html:select property="designationId" styleId="designationId"  styleClass="form-control"  value="${sanctionDeatil.designationId}" >
					<html:option value="0">-select-</html:option>
				    <logic:present name="designationMasterList">
					<logic:iterate id="designationMasterList" name="designationMasterList">
						<html:option value="${designationMasterList.designationId}">${designationMasterList.designationName}</html:option>
					</logic:iterate>
				</logic:present>
		   </html:select>
             </td>
            </tr> 
            <tr>
              <th>No of Post <span class="text-error">*</span></th>
              <td > <html:text onkeypress="return isNumberKey(event)" property="noofPost" styleId="noofPost"  value="${sanctionDeatil.noofPost}"/></td><html:hidden styleId="sanctionIdCheck" property="sanctionId"  value="${sanctionDeatil.sanctionId}"></html:hidden>
            </tr>
	       </table>     
	       
	     <div align="center">
		   <input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="saveRecord()"/> 
		   <%-- <input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/>  --%>
		 </div>    
  
     <table width="100%" border="1" id="example" class="display">
	        <thead>
			<tr>
			  <td> S.No     </td>
	          <td>State Name</td>
	          <td>Designation Name</td>
	          <td> No of Post</td>
	          <td>Edit     </td>
	          <td>Delete   </td>
			  </tr>
			</thead>
			
			<tbody>
			<logic:present name="sanctionPostList">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="sanctionPostList" name="sanctionPostList"> 
					<tr>
					 <td align="center">${indexCount}</td>  
					 <td> ${sanctionPostList.state.stateName} </td> 
					 <td> ${sanctionPostList.designationMaster.designationName} </td>
					 <td> ${sanctionPostList.noofPost} </td>
	                 <td><a href="#" onclick="editRecord(${sanctionPostList.sanctionId})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
					 <td><a href="#" class="checkPermissionForFormForInsert" onclick="deleteRecord(${sanctionPostList.sanctionId})"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
					</tr>
				  <c:set var="indexCount" value="${indexCount + 1}" />	
			   </logic:iterate>
		   </logic:present>
			</tbody>
	    </table> 
  
  
  
</html:form>
</body>
</html>