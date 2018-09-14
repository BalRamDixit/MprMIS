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
				
				document.forms[0].action = "constituency.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
			}
		}
		else{		
			var x=checkPermissionForFormForInsert();
			if(x=='true'){
	 		document.forms[0].action = "constituency.do?methodName=save" + "&"+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit(); 
			}
		}
	}
	
}
	
	function edit(id){		
   		$('#IdForAction').val(id);		 
		document.forms[0].action = "constituency.do?methodName=edit"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	 
	}
	
	
	function deleteData(id){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
    		  $('#IdForAction').val(id);
		document.forms[0].action = "constituency.do?methodName=delete"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}	   
	}
	function clearField()
	{

	}
	function getXMLHttpRequest() {
		var xmlHttpReq = false;
		if (window.XMLHttpRequest) {
			xmlHttpReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			try {
				xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (exp1) {
				try {
					xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (exp2) {
					xmlHttpReq = false;
				}
			}
		}
		return xmlHttpReq;
	}
	function getDistrict(){
		var xmlHttpRequest = getXMLHttpRequest();
	  	xmlHttpRequest.onreadystatechange = function(){
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				if (xmlHttpRequest.responseText != "") {
					$('#districtId').html(xmlHttpRequest.responseText);
				} 
			}
		}
		xmlHttpRequest.open("POST","constituency.do?methodName=getDistrict", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("state="+$('#state').val());	
	}

	function back() {
		 
		document.forms[0].action = "constituency.do?methodName=show" + "&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
		 
	};
</script>
<!--Main form section starts here-->
<html:form action="login/constituency">

            <input type="hidden" name="constituencyId" id="IdForAction"/>
			<input type="hidden" name="editId" id="idForEdit" value="${constituencyMasterBean.constituencyId}"/>

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">Assembly Constituency Master</td>
		</tr>
	</table>
 

	<table width="100%" align="center" class="inputTBL">
		<tr>
			<th>Assembly Constituency Code<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="assemblyConstituencyCode"  value="${constituencyMasterBean.assemblyConstituencyCode}" styleId="assemblyConstituencyCode" />
			</span></td>
		</tr>
		
		<tr>
			<th>Assembly Constituency Name<span class="text-error"></span></th>
			<td><span class="text-error"> <html:text
						property="assemblyConstituencyName"   value="${constituencyMasterBean.assemblyConstituencyName}" styleId="assemblyConstituencyName" />
			</span></td>
		</tr>
		<tr>
			<th>State Name<span class="text-error"></span></th>
			<td>
				<span class="text-error"> 
					<html:select property="state" styleId="state" value="${constituencyMasterBean.state}" onchange="getDistrict()">
						<html:option value="0">Select State</html:option>
	            		<logic:present name="stateList">
							<logic:iterate id="project" name="stateList">
								<html:option value="${project['stateId']}">${project['stateName']}</html:option>
							</logic:iterate>
						</logic:present>
					</html:select>
				</span>
			</td>
		</tr>
		<tr>
			<th>District Name<span class="text-error"></span></th>
			<td>
				<span class="text-error"> 
					<html:select property="districtid" styleId="districtId" value="${constituencyMasterBean.districtid}" onchange="getAssignState()">
						<html:option value="0">Select District</html:option>
						<logic:present name="districtList">
							<logic:iterate id="project" name="districtList">
								<html:option value="${project['districtId']}">${project['districtName']}</html:option>
							</logic:iterate>
						</logic:present>
					</html:select> 
				</span>
			</td>
		</tr>
		<tr>
			<th>Parliamentary Constituency Name<span class="text-error"></span></th>
			<td><span class="text-error"> 
				<html:select property="parliamentaryConstituencyId" styleId="parliamentaryConstituencyName" value="${constituencyMasterBean.parliamentaryConstituencyId}">
						<html:option value="0">Select Parliamentary Constituency</html:option>
						<logic:present name="parliamentaryConstituencyList">
							<logic:iterate id="project" name="parliamentaryConstituencyList">
								<html:option value="${project['id']}">${project['name']}</html:option>
							</logic:iterate>
						</logic:present>
					</html:select> 
			
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
               		<th>Constituency Code </th>
               		<th>Constituency Name </th> 
               		<th>Parliamentary Constituency Name</th> 
               		<th>District Name</th>                		               		 
               		<th>Edit</th>
               		<th>Delete</th>
               </tr>
 		</thead>
 		<tfoot>
             <tr>
		 			<th>S.No </th>
               		<th>Constituency Code </th>
               		<th>Constituency Name </th>  
               		<th>Parliamentary Constituency Name</th> 
               		<th>District Name</th>             		               		 
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
						<td>${list['assemblyConstituencyCode']}</td>
					     <td>${list['assemblyConstituencyName']}</td> 
					     <td>${list['parliamentaryConstituencyName']['name']}</td> 	
					     <td>${list['district']['districtName']}</td>
					     <td><a href="#" onclick="edit(${list['constituencyId']})"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
						<td><a href="#" onclick="deleteData(${list['constituencyId']})" class="checkPermissionForFormForInsert"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>	 
					    
					</tr>
					<c:set var="indexCount" value="${indexCount + 1}" />
				</logic:iterate>
			</logic:present> 
		</tbody>	
				</table>
			   


</html:form>
