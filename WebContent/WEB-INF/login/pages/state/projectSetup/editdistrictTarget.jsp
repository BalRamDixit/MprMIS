<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>


<!--script for menu-->
<script type="text/javascript">
function getXMLHttpRequest() {
	var xmlHttpReq = false;
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
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
function getSpecial(district,idForChange){
	var result=checkdistrict(district);
	if(!result){
		return false;
	}
	
	var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = function(){
		  if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200)
			{
			if (xmlHttpRequest.responseText != "") {
				
				$(district).closest("td").next().find("input").val(xmlHttpRequest.responseText);
				}
				} else {
					document.getElementById("isSpecialId"+idForChange).innerHTML = 'No';
			}
			}
		}
xmlHttpRequest.open("POST","districtTarget.do?methodName=getSpecialDetail", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("isSpecial="+district.value);	
}

function back(){
	
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="districtTarget.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
 	

var i = 1;
function deleteDetail(id){
	     
		 $('.remove').live('click',function(){console.log($(this).parent().parent().remove())});
}

function insRow()
{ 
	var optionclone= $(".district").first().html();
	var option=optionclone.replace("selected","");
	/* var lastSelectedOption=$("#districtId"+i).val(); */
var tablerow="<tr><td><select name='district' class='district' id='districtId' onchange='javascript:getSpecial(this,1)' >"+option+"</select><input type='hidden' name='id' value='0'></td>"+
                "<td><input type='text' style='width: 95px;' readOnly='true 'name='isSpecial' id='isSpecialId'  ></td>"+
               "<td><input type='text' name='trainingTargetDist' style='width: 95px;'  onblur='checktotaltrainingTarget()' class='trainingTargetDist' id='trainingTargetDistId'></td>"+
               "<td><input type='hidden' ></td>"+
                "<td><input name='Button' type='button' class='button checkPermissionForFormForInsert' value='Remove'  onclick='removeRow(this)'></td>"
                "</tr>";      
                        
          // alert(tablerow); 
           $("#tablenew").append(tablerow);
          /*  $("#districtId"+i+" option[value='"+lastSelectedOption+"']" ).remove(); */
}

function updateform(){
	
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	 var result=checkTotal();
 	 if(!result){
 		 return false;
 	 }	 
	var status=window.confirm('Are You Sure You Want tO Update ?');
	if(status==true){
		document.forms[0].action="districtTarget.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	}
}}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

$(document).ready(function()
{ 
  var str =  document.getElementById("stateNameId").value;
	
});
function removeRow(vv){
	var c=confirm("Do you want to Remove")
	if(c==true){
	var row_length=$('#tablenew tr').length;
	if(row_length<3){
		alert("unable to delete row");
		return false;
	}
	$(vv).parent().parent().remove();
}
}
function checkdistrict(vv){
	var i=0;
	$(".district").each(function(){
		if($(this).val()==vv.value){
			i++;
		}
	});
	if(i>1){
		vv.value="0";
		alert("district is already selected");
		return false;
	}
	else{
		return true;
	}
}

function checkTotal(){
	var total=$("#projectTargetId").val();
	var training=0;
	$(".trainingTargetDist").each(function(){
		training=parseInt(training)+parseInt($(this).val());
	});
	var result=true;
	if(total!=training){
		alert("Training Target should be equal to Project Target..!!");
		result=false;
		return result;
	}else{
		return result;
	}
}

/* vinay */
function getTradeTarget(str){
	var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = function(){
		 
		  if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200) {
				
				if (xmlHttpRequest.responseText != "") {
					//alert(xmlHttpRequest.responseText);
				     document.getElementById("projectTargetId").value = xmlHttpRequest.responseText;
				} 
				} else {
					document.getElementById("projectTargetId").value = '';
			}
			}
		}
	xmlHttpRequest.open("POST","districtTarget.do?methodName=getSanctionTarget", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("project_id="+str);	
}

function getState(str){
	var element=document.getElementById("projectIDId");
	 
	document.getElementById("stateNameId").value = str;
	document.getElementById("projectid").value =element.options[ element.selectedIndex ].text;
	
	var proj=document.getElementById("projectid").value;
	getValueForEdit(proj);
	  
}
function getcc(str){
	
	var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = function(){
		 
		  if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200) {
				if (xmlHttpRequest.responseText != "") {
					   document.getElementById("stateNameId").value = xmlHttpRequest.responseText;
					   var proj=document.getElementById("projectIDId").value;
						getValueForEdit(proj);
				} else{
				}
				} else {
					document.getElementById("stateNameId").innerHTML = '';
			}
			}
		}
	xmlHttpRequest.open("POST","projectSanctionAction.do?methodName=getStateName", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("project_id="+str);	
}
function getValueForEdit(proj){
	
 	var url="districtTarget.do?methodName=getEdit&proj="+proj;
	if (window.XMLHttpRequest)
	{
	  req = new XMLHttpRequest();
	} else if (window.ActiveXObject)
	{
	  req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("get", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponse;
	req.send();
}
function editDetail(projectId){
	 document.forms[0].action="districtTarget.do?methodName=edit&id="+projectId+'&reqtrack='+tokenValue; 
	 document.forms[0].submit();
}
function handleHttpResponse(){
   	if (req.readyState == 4){	
   		var response = req.responseText;
   		/* alert(response); */
   		if(response>=1){
   			editDetail(document.getElementById("projectIDId").value)
   		}
   		else{
   			 getDist(document.getElementById("projectIDId").value); 
   		}
   }
}

function getDist(str){
     var xmlHttpRequest = getXMLHttpRequest();
         xmlHttpRequest.onreadystatechange = function(){
	 
	    if (xmlHttpRequest.readyState == 4) {
	    	if (xmlHttpRequest.status == 200) {
			
			if (xmlHttpRequest.responseText != "") {
			     document.getElementById("districtId").innerHTML = xmlHttpRequest.responseText;
			} else{
			}
			} else {
				document.getElementById("districtId").innerHTML = '<option value="0"> --SELECT-- </option>';
		}
		}
	}
	xmlHttpRequest.open("POST","districtTarget.do?methodName=getDistrictDetail", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("project_id="+str);	
}

$(document).ready(function()
{ 
	var str =  document.getElementById("stateNameId").value;
	
 });
/* vinay end */

</script>

 <html:form action="login/districtTarget">
				
		<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">DISTRICT TARGET</td>					
                </tr>
		</table>	
			
			  
			<table width="100%" align="center"  class="inputTBL">
			 
			 	  <%-- <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
						<td><html:text property="projectIdID" styleId="projectIDId" value="${projectId}"  styleClass="form-control" readonly="true" />
							<html:hidden property="projectId"  value="${projectID}" />
							</td>
			     </tr> --%> 
			     <!-- Update Vinay Check -->
      <tr>
			<th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
			<td><html:select property="projectId" styleId="projectIDId"
					styleClass="form-control"
					onchange="javascript:getcc(this.value); getTradeTarget(this.value);" value="${projectID}">
					<html:option value="0">-select-</html:option>
					<logic:present name="projectList">
						<logic:iterate id="projectList" name="projectList">
							<html:option value="${projectList.id}">${projectList.projectID}</html:option>
						</logic:iterate>
					</logic:present>
				</html:select> <!-- <input type="hidden" id="id" name="id"></input> --></td>
			
		</tr>

			     
			     <tr>
			    <th>State Name<span class="text-error"></span></th>
		     	<td><span class="text-error"><html:text
						property="stateName" readonly="true" styleId="stateNameId" /></span></td>
		          </tr>
			     
			     
				         <tr>
						  <th><span class="input-group-addon" id="basic-addon1">Project Target</span></th>
						  <td><input type="text" id="projectTargetId" readonly value="${projecttarget}" /></td>
						</tr>
			</table>
			  
			
			  
			  <input type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.addRow"/>" style="float: right;" onclick="insRow()"/>
			  
			  <table width="100%" align="center" id="tablenew"  class="inputTBL">
			  
			  <tr>
			    <th><span class="input-group-addon" id="basic-addon1">Name of District</span></th>
				<th>Whether Special Area<span class="text-error"></span></th>		
				<th>Training Target<span class="text-error"></span></th>	
				<th></th>
				<th></th>
				</tr>
			  <%  out.println(request.getAttribute("rows")); %>
			 	 
				
			 </table>
			
			<div align="center">
			 	<tr>
				<td>
					<input name='Button' type='button' class='button checkPermissionForFormForInsert' value='Update' onclick='javascript:updateform()'/>&nbsp;&nbsp;&nbsp;&nbsp;
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/>
				</td>
				</tr> 
			</div>	
			 
</html:form>
 