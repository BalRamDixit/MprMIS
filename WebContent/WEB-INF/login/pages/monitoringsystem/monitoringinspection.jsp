<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">

$(document).ready(function(){
 	
	$('#inspection').DataTable();
	});
	
function addDetail() {

	var x=checkPermissionForFormForInsert();
	if(x=='true'){	
			document.forms[0].action = "monitoringInspection.do?methodName=addDetail" + "&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
	}		 
 }


function getDateDetails(trainingId){
	var TrainingId = trainingId;
	if(TrainingId!= null &&TrainingId!= 0){
		var url="monitoringInspection.do?methodName=getDateDetails&TrainingId="+TrainingId;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	req.open("POST", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponse;
	req.send();
}

function handleHttpResponse(){
	
	$('#tbody').html("");
	
	if (req.readyState == 4){	
    	var response = req.responseText; 
//     	alert("response"+response);
    	if(response !="" && response != null){
    		
      	var aa=JSON.parse(response);
    	var dynamic="";
    	for(var i=0;i<aa.length;i++){
    		
    		if(aa[i].conductedBySRLMdate =="null")
	    		{
		    		aa[i].conductedBySRLMdate="";
	    		}
    		if(aa[i].conductedByCTSAdate =="null")
				{
		    		aa[i].conductedByCTSAdate="";
				}
    		if(aa[i].conductedByQAdate =="null")
				{ 
		    		aa[i].conductedByQAdate="";
				}
    		dynamic+="<tr>";
    		dynamic+="<td  style='vertical-align:top'><input type='text'  readonly='true' value='"+aa[i].batchId+"'/><input type='hidden' name='batchId' value='"+aa[i].tcId+"'><input type='hidden' name='id' value='"+aa[i].id+"'></td>";
    		dynamic+="<td><input type='text' style='text-align:center' class='calendar input' readonly='true' name='conductedByQAdate' value='"+aa[i].conductedByQAdate+"'/></td>";
    		dynamic+="<td><input type='text'  style='text-align:center' class='calendar input' readonly='true' name='conductedByCTSAdate' value='"+aa[i].conductedByCTSAdate+"'/></td>";
    		dynamic+="<td><input type='text'  style='text-align:center' class='calendar input' readonly='true' name='conductedBySRLMdate' value='"+aa[i].conductedBySRLMdate+"'/></td>";
     		dynamic+="<td><a href='#' onclick=editDetail('"+aa[i].id+"')><img src='images/EditIcon.png' alt='' height='14px' width='17px' /></a></td>";
    		dynamic+="</tr>";
    		}
    	
    $("#tbody").append(dynamic);
	}
	}
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

function getTrainingList(prId){
// 	alert("getting training list");
	var element=document.getElementById("projectID");
	var a= document.getElementById("projectID").value 
	element.options[ element.selectedIndex ].text;
	var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = function(){
			if (xmlHttpRequest.readyState == 4) 
			{
				if (xmlHttpRequest.status == 200) 
					{
							if (xmlHttpRequest.responseText != "") {
								document.getElementById("TrainingId").innerHTML = '<option value="0"> --SELECT-- </option>';
								document.getElementById("TrainingId").innerHTML = xmlHttpRequest.responseText;
							} 
							else{
								 document.getElementById("TrainingId").innerHTML = '<option value="0"> --SELECT-- </option>'; 
								 }
					}
				else 
					{
							document.getElementById("TrainingId").innerHTML = '<option value="0"> --SELECT-- </option>';
					}
			}
		}
	xmlHttpRequest.open("POST","monitoringInspection.do?methodName=getTrain&trainCenter=" + a, true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttpRequest.send("prId="+prId);	
 }

function back(){
	var status=window.confirm('Are you Sure you Want to go back ?');
	if(status==true){
		document.forms[0].action="login.do?methodName=login"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}
	};
	
function editDetail(id) {	
// 		alert("in edit details"+id)	;
		document.forms[0].action="monitoringInspection.do?methodName=editDetail"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	};

</script>

<html:form action="login/monitoringInspection">
	<div>
		<table width="100%" class="pageHeaderTable">
			<tr>
				<th align="center" class="pageHeader">MONITORING INSPECTION</th>
			</tr>
		</table>
		
		<div align="right">
		<html:button styleClass="button checkPermissionForFormForInsert"  property="" styleId="addB" onclick="addDetail();">ADD NEW</html:button>
		</div> 
		
		<table width="100%" class="inputTBL">
			<tr>
				<th>Project ID<span class="text-error"></span></th>
				<td><html:select property="prId" styleId="projectID" styleClass="form-control" onchange="getTrainingList(this.value);" >
						<html:option value="0">--SELECT-- </html:option>
						<logic:present name="projectList">
							<logic:iterate id="project" name="projectList">
  								<html:option value="${project.id}">${project.projectID}</html:option>  
							</logic:iterate>
						</logic:present>
					</html:select></td>
				<th>Training Center ID <span class="text-error"></span></th>
				<td><html:select property="tcId" styleId="TrainingId" styleClass="form-control" onchange="getDateDetails(this.value);">
							<html:option value="0">--SELECT-- </html:option>
					</html:select></td>	
			</tr>
		</table>
	</div>
	
	<table width="100%" align="center" id="inspection" class="display">
				<thead>
					<tr width="100%">
						<th>Batch ID</th>
						<th>Date of Inspections Conducted By Q Team  </th>
						<th>Date of Inspections Conducted By CTSA  </th>
						<th>Date of Inspections Conducted By SRLM  </th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody id="tbody">
				</tbody>
			</table>
		<div align="center">
			<input name="Button" type="button" class="button" value="Back" onclick="back()" />
		</div>
	
</html:form>
