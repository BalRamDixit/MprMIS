<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">


function add() {

	var optionclone= $(".district").last().html();
//	var lastSelectedOption=$("#districtId"+i).val();
		i++;
	var row="<tr><td><select name='district' class='district' id='districtId' onchange='javascript:getSpecial(this)'>"+optionclone+"</select></td>"
	+"<td><input type='text' name='isSpecial' style='width: 95px;' id='isSpecialId' readonly ></td>"
	+"<td><input type='text' name='trainingTargetDist' style='width: 95px;' class='trainingTargetDist' id='trainingTargetDistId'></td>"
	+"<td><input name='Button' type='button' class='button'  value='Remove'  onclick='removeRow(this)'></td></tr>";
	$("#tablenew").append(row);
//	$("#districtId"+i+" option[value='"+lastSelectedOption+"']" ).remove();
}


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
function getSpecial(district){
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
					} else{
					}
					} else {
						document.getElementById("isSpecialId").innerHTML = 'No';
				}
				}
			}
	xmlHttpRequest.open("POST","districtTarget.do?methodName=getSpecialDetail", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("isSpecial="+district.value);	
}


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
function editDetail(projectId,district){
	 document.forms[0].action="districtTarget.do?methodName=edit&id="+projectId+'&reqtrack='+tokenValue; 
	 document.forms[0].submit();
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

function save(){
   	 var a=document.getElementById("projectIDId").value;
 	 var b=document.getElementById("trainingTargetDistId").value; 
 	 
 	 var result=checkTotal();
 	 if(!result){
 		 return false;
 	 }
 	/* +"&"+"projectidman"+"="+a+"&"+"target"+"="+b+"&"+tokenParameter+"="+tokenValue */
	var status=window.confirm('Are You Sure You Want to Save ?');
	if(status==true){
		var xmlHttpRequest = getXMLHttpRequest();
		  xmlHttpRequest.onreadystatechange = function(){
			  if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
					if (xmlHttpRequest.responseText != "") {
						var x=xmlHttpRequest.responseText;						 
						if(x=="true"){
							document.forms[0].action="districtTarget.do?methodName=save&"+tokenParameter+"="+tokenValue;;
							document.forms[0].submit();
						}
						else{
							alert("Enter Valid District target");

						}
					} 
				}
			}
		xmlHttpRequest.open("POST","districtTarget.do?methodName=checkForData", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("projectidman="+a+"&target="+b);	
	}
}
function back(){
	
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="districtTarget.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
} 
function removeRow(vv){
	var c=confirm("Do you want to Remove")
	if(c==true){
	var row_length=$('#tablenew tr').length;
	if(row_length<3){
		alert("unable to delete row");
		return false;
	}
	$(vv).parent().parent().remove();
} }
function checkdistrict(vv){
	var i=0;
	$(".district").each(function(){
		if($(this).val()==vv.value){
			i++;
		}
	});
	if(i>1){
		vv.value="0";
		$(vv).closest("td").next().find("input").val("");
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


</script>
<!--Main form section starts here-->
<html:form action="login/districtTarget">

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">DISTRICT TARGET</td>
		</tr>

	</table>

	<table width="100%" align="center" class="inputTBL">


		<tr>
			<th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
			<td> <html:select property="projectId" styleId="projectIDId"
					styleClass="form-control"
					onchange="javascript:getcc(this.value); getTradeTarget(this.value);" value="0"> 
					<html:option value="0">-select-</html:option>
					<logic:present name="projectList">
						<logic:iterate id="projectList" name="projectList">
							<html:option value="${projectList.id}">${projectList.projectID}</html:option>
						</logic:iterate>
					</logic:present>
				</html:select>  <input type="hidden" id="id" name="id"></input></td>
			
		</tr>

		<tr>
			<th>State Name<span class="text-error"></span></th>
			<td><span class="text-error"><html:text
						property="stateName" readonly="true" styleId="stateNameId" /></span></td>
		</tr>

		<tr>
			<th>Project Target</th>
			<td><input type="text" id="projectTargetId" readonly="true" /></td>
		</tr>
	</table>

	<div id="showTable">

		<div style="text-align: right;">
		<input name="Button" type="button" class="button checkPermissionForFormForInsert"
			value="<bean:message  key="button.addRow"/>" onclick="add()" />
		</div>

		<table width="100%" align="center" id="tablenew" class="inputTBL">

			<tr>
				<th>Name of District<span class="text-error"></span></th>
				<th><span class="input-group-addon" id="basic-addon1">Whether Special Area</span></th>
				<th>Training Target<span class="text-error">*</span></th>
				<th></th>
			</tr>

		 <tr>
				<td><span class="text-error"> <html:select
							property="district" styleId="districtId"
							styleClass="form-control district"
							onchange="javascript:getSpecial(this);">
							<html:option value="">--SELECT--</html:option>
						</html:select></span>
				</td>
				     <td><html:text property="isSpecial" styleId="isSpecialId"
						readonly="true" style='width: 95px;' styleClass="form-control" value="${dis.district}">
					</html:text></td>
			 <td>
				  <span class="text-error"><html:text
				  property="trainingTargetDist" styleClass="trainingTargetDist"
				  styleId="trainingTargetDistId" style='width: 95px;'
				  onkeypress="return isNumberKey(event)" value="${dis.trainingTargetDist}" />
				  </span>
		     </td>
		     <td></td>
			
			</tr> 
			
           



			<!-- <tr>
				<td>

			
			</td>
			</tr> -->

		</table>
	
		<div align="center">
			<tr >
			<td >				
					<input name="Button" type="button" class="button checkPermissionForFormForInsert"
						value="<bean:message  key="button.save"/>" onclick="save()" />&nbsp;&nbsp;&nbsp; <input
						name="Button" type="button" class="button"
						value="<bean:message  key="button.back"/>" onclick="back()" />
				</td>
				</tr>
			</div>	
			
	</div>

</html:form>
