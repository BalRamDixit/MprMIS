
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
.fif1{
     
      margin-right:8%;
     float:left;
 }


</style>
<script type="text/javascript">

$(document).ready(function()
{
//	$("#projectID").change();
	var x=$("#classToReadOnly").html();
	$('.fif1 input').attr('readonly', 'readonly');
	$('.fif1 textarea').attr('readonly', 'readonly');
	$('.fif2 input').attr('readonly', 'readonly');
	$('.fif2 textarea').attr('readonly', 'readonly');
	$('.fif3 input').attr('readonly', 'readonly');
	$('.fif3 textarea').attr('readonly', 'readonly');
	if(x=='fif1'){
		$( "#conductedBySRLMdateId" ).datepicker({ dateFormat: 'dd-mm-yy',stepMonths: 0 });
	}
	if(x=='fif2'){
		$( "#conductedByCTSAdateId" ).datepicker({ dateFormat: 'dd-mm-yy',stepMonths: 0 });
	}
	if(x=='fif3'){
		$( "#conductedByQAdateId" ).datepicker({ dateFormat: 'dd-mm-yy',stepMonths: 0 });
	}
	$("."+x+" input").removeAttr('readonly');
	$("."+x+" textarea").removeAttr('readonly');
	
});


function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
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


	function getBatchList(){
		var TrainingId=$("#TrainingId").val();
		if(TrainingId!= null &&TrainingId!= 0){
			var url="monitoringInspection.do?methodName=getBatchList&TrainingId="+TrainingId;
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

function getTrainingList(prId){
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

function abc(){
	$(".calendar").each(function(){
		var da=$(this).val();
		if(da=="null"||da==null){
			$(this).val("");
			} else{
				var date2 = new Date(da);
				$(this).datepicker({
			        dateFormat: 'dd-mm-yy'
			    }).datepicker('setDate', date2)
			}
	});
}

function dd(vv){
	var tt=vv.value;
	if(tt.length<10){
	$(vv).closest('td').next('td').find('input').prop( "disabled", true );
	$(vv).closest('td').next('td').next('td').find('input').prop( "disabled", true );
	}else{
		$(vv).closest('td').next('td').find('input').prop( "disabled", false );
		$(vv).closest('td').next('td').next('td').find('input').prop( "disabled", false );
	}
	}

function handleHttpResponse(){
	
	$('#tbody').html("");
	if (req.readyState == 4){	
    	var response = req.responseText; 
//     	alert("tets"+response);
      	var aa=JSON.parse(response);
    	var dynamic="";
    	for(var i=0;i<aa.length;i++){
    		dynamic+="<tr>";
    		dynamic+="<td  style='vertical-align:top'><input type='text'  readonly='true' value='"+aa[i].batchId+"'/><input type='hidden' name='batchId' value='"+aa[i].tcId+"'><input type='hidden' name='id' value='"+aa[i].id+"'></td>";
	    	dynamic+="<td class='fif3'><textarea rows='1' name='remarksQa' >"+aa[i].remarksQa+"</textarea></td>";
		    dynamic+="<td class='fif1'><textarea rows='1' name='remarksSRLM'>"+aa[i].remarksSRLM+"</textarea></td>";
	    	dynamic+="<td class='fif2'><textarea rows='1' name='remarksCtsa'>"+aa[i].remarksCtsa+"</textarea></td>";
	    	dynamic+="</tr>";
    	}
    	$("#tbody").append(dynamic);
    	var x=$("#classToReadOnly").html();
    	$('.fif1 input').attr('readonly', 'readonly');
    	$('.fif1 textarea').attr('readonly', 'readonly');
    	$('.fif2 input').attr('readonly', 'readonly');
    	$('.fif2 textarea').attr('readonly', 'readonly');
    	$('.fif3 input').attr('readonly', 'readonly');
    	$('.fif3 textarea').attr('readonly', 'readonly');
    	$("."+x+" input").removeAttr('readonly');
    	$("."+x+" textarea").removeAttr('readonly');
  	}
} 

function save(){
	var QtDate=document.getElementById("conductedByQAdateId");
	var ctsatDate=document.getElementById("conductedByCTSAdateId");
	var srlmDate=document.getElementById("conductedBySRLMdateId");
	
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm('Are You Sure You want to Save?');
		if(status==true){
			document.forms[0].action="monitoringInspection.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
}
function back(){
	var status=window.confirm('Are you Sure you Want to go back ?');
	if(status==true){
	//	document.forms[0].action="login.do?methodName=login"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].action="monitoringInspection.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}
};
 

</script>
<html:form action="login/monitoringInspection">
		<table width="100%" class="pageHeaderTable">
			<tr>
				<th align="center" class="pageHeader">MONITORING INSPECTION</th>
			</tr>
		</table>
		<table width="100%" class="inputTBL">
			<tr>
				<th>Project ID<span class="text-error"></span></th>
				<td><html:select property="prId" styleId="projectID" styleClass="form-control" value="${Bean.projectId.projectID}" onchange="getTrainingList(this.value);">
						<html:option value="0">--SELECT-- </html:option>
						<logic:present name="projectList">
							<logic:iterate id="project" name="projectList">
								<html:option value="${project.id}">${project.projectID}</html:option>
							</logic:iterate>
						</logic:present>
					</html:select>
				</td>
				<th>Training Center ID <span class="text-error"></span></th>
				<td><html:select property="tcId" styleId="TrainingId" styleClass="form-control" value="${Bean.trainingCenterId.trainingCenterId.tcID}" onchange="getBatchList(this.value);">
					<html:option value="0">--SELECT-- </html:option>
					</html:select>
				</td>	
			</tr>
			<c:if test="${RoleId eq '45'||RoleId eq '49'}">
				<span style="display: none" id="classToReadOnly">fif1</span>
			</c:if>
			<c:if test="${RoleId eq '5001'||RoleId eq '10001'}">
				<span style="display: none" id="classToReadOnly">fif2</span>
			</c:if>
			<c:if test="${RoleId eq '4'||RoleId eq '10002'}">
				<span style="display: none" id="classToReadOnly">fif3</span>
			</c:if>
		</table>
	
	<div>
			<div class="fif3"> 
					<table  class="inputTBL" width="100%">
						<tr><th colspan="2" >Will Only be Filled By Q Team</th></tr>
						<tr><td>Date of Inspections Conducted By Q Team  </th>
						<td><html:text property="conductedByQAdate" readonly="true" styleId="conductedByQAdateId" value="${Bean.conductedByQAdate}"/></td></tr>
						<tr><td style="vertical-align: top; text-align: left;">Q Team Remarks </td>
						<td><html:textarea property="remarkstcQa" styleId="remarkstcQaId" rows="3" cols="2" value="${Bean.remarkstcQa}"></html:textarea></td></tr>
					</table>
			</div>
			<div class="fif1">
					<table class="inputTBL"  width="120%">
						<tr><th colspan="2">Will Only be Filled By SRLM</th></tr>
						<tr><td>Date of Inspections Conducted By SRLM  </td>
						<td><html:text property="conductedBySRLMdate" readonly="true" styleId="conductedBySRLMdateId" value="${Bean.conductedBySRLMdate}"/></td>
						<tr><td style="vertical-align: top; text-align: left;">SRLM Remarks </td>
						<td><html:textarea property="remarkstcSRLM" styleId="remarkstcSRLMId" rows="2" cols="2" value="${Bean.remarkstcSRLM}"></html:textarea></td></tr>
						<tr><td>No of Advisories Raised by SRLM  </td>
						<td><html:text property="advisoryRaisedBySrlm" styleId="advisoryRaisedBySrlmId" value="${Bean.advisoryRaisedBySrlm}" onkeypress="return isNumberKey(event)"/></tr>
						<tr><td>No of Advisories Closed</td>
						<td><html:text property="advisoryClosedBySrlm" styleId="advisoryClosedBySrlmId" value="${Bean.advisoryClosedBySrlm}" onkeypress="return isNumberKey(event)"/></tr>
					</table>
			</div>
			<div class="fif2">
				
					<table  class="inputTBL" >
						<tr><th colspan="2" ><b>Will Only be Filled By CTSA</b></th></tr>
						<tr><td width="100%"> Date of Inspections Conducted By CTSA  </td>
						<td ><html:text property="conductedByCTSAdate" readonly="true"  styleId="conductedByCTSAdateId" value="${Bean.conductedByCTSAdate}"/></td>
						<tr><td style="vertical-align: top; text-align: left;"> CTSA Remarks </td>
						<td><html:textarea property="remarkstcCtsa" styleId="remarkstcCtsaId" rows="2" cols="2" value="${Bean.remarkstcCtsa}"></html:textarea></td></tr>
						<tr><td> No of Advisories Raised by CTSA  </td>
						<td><html:text property="advisoryRaisedByCtsa" styleId="advisoryRaisedByCtsaId" value="${Bean.advisoryRaisedByCtsa}" onkeypress="return isNumberKey(event)"/></tr>
						<tr><td> No of Advisories Closed  </td>
						<td><html:text property="advisoryClosedByCtsa" styleId="advisoryClosedByCtsaId" value="${Bean.advisoryClosedByCtsa}" onkeypress="return isNumberKey(event)"/></tr>
					</table>
			</div>
			
	</div>
	<div>
		<div style="overflow-x: scroll; overflow-y: hidden; width: 100%;">
			<table width="100%" align="center" id="inspection"  >
				<thead>
					<tr width="100%">
						<th>Batch ID </th>
						<th>Q Team Remarks</th>
						<th>SRLM Remarks </th>
						<th>CTSA Remarks</th>
					</tr>
				</thead>
				<tbody id="tbody">
				</tbody>
			</table>
		</div>
		<div align="center">
			<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="Save" onclick="save()" />&nbsp;&nbsp; 
			<input name="Button" type="button" class="button" value="Back" onclick="back()" />
		</div>
	</div>
</html:form>
</body>
<!--Main form section ends here-->