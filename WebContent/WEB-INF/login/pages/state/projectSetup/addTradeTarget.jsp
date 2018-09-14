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
	// to create XMLHttpRequest object in non-Microsoft browsers
	
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	} else if (window.ActiveXObject) {
		try {
			// to create XMLHttpRequest object in later versions
			// of Internet Explorer
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				// to create XMLHttpRequest object in older versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}
	
	function getTrade(sector){
		
		var xmlHttpRequest = getXMLHttpRequest();
		
		
			/* var sector=document.getElementById("sectorId").value; */
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							
							//document.getElementById("tradeId").innerHTML = xmlHttpRequest.responseText;
							$(sector).closest("td").next().find("select").html(xmlHttpRequest.responseText);
						} else{
							$(sector).closest("td").next().find("select").html('<option value="0"> --SELECT-- </option>');
							// document.getElementById("tradeId").innerHTML = '<option value="0"> --SELECT-- </option>'; 
					}
					} else {
						$(sector).closest("td").next().find("select").html('<option value="0"> --SELECT-- </option>');
					//	document.getElementById("tradeId").innerHTML = '<option value="0"> --SELECT-- </option>';
				}
				}
			}
		
	xmlHttpRequest.open("POST","tradeTarget.do?methodName=getTargetDetail", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("sector="+sector.value);	

	}


function showRemark(str){
	
		getTotalTrainingTarget(str);
	
}
 $(document).ready(function()
{
	$("#projectIDId").val("0");//showRemark("0");
});
 

function getTotalTrainingTarget(projectId){
	if(projectId!="0"){
	var xmlHttpRequest = getXMLHttpRequest();
	
	
	/* var sector=document.getElementById("sectorId").value; */
  xmlHttpRequest.onreadystatechange = function(){
	 
	  
		if (xmlHttpRequest.readyState == 4) {
			
			if (xmlHttpRequest.status == 200) {
				
				if (xmlHttpRequest.responseText != "") {
					var response=xmlHttpRequest.responseText.split("<--->");
					$("#tradetargetTable tr").not(':first').remove();
					$("#tradetargetTable").append(response[0]);
					if(response[1]=="null"||response[1]==null||response[1]<1){
						document.getElementById("projecttrainingTarget").value ="";
					}else{
						document.getElementById("projecttrainingTarget").value =response[1];
					}
					
				} else{
					$("#tradetargetTable tr").not(':first').remove();
					document.getElementById("projecttrainingTarget").value ="";
					 document.getElementById("").innerHTML = ''; 
			}
			} else {
				$("#tradetargetTable tr").not(':first').remove();
				document.getElementById("projecttrainingTarget").value ="";
				document.getElementById("").innerHTML = '';
		}
		}
	}

xmlHttpRequest.open("POST","tradeTarget.do?methodName=getTotalTrainingTarget", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("projectId="+projectId);
	}else{
		$("#tradetargetTable tr").not(':first').remove();
	}
}

function gettradeduration(vv){

	if(vv.value=="NO"){
		var tradeid=$(vv).closest("td").prev().find("select").val();
		//alert(tradeid);
		//var tradeid=document.getElementById("tradeId").value;
var xmlHttpRequest = getXMLHttpRequest();
	
	
	/* var sector=document.getElementById("sectorId").value; */
  xmlHttpRequest.onreadystatechange = function(){
	 
	  
		if (xmlHttpRequest.readyState == 4) {
			
			if (xmlHttpRequest.status == 200) {
				
				if (xmlHttpRequest.responseText != "") {
					
					$(vv).closest("td").next().find("input").prop("readonly", true);
					$(vv).closest("td").next().find("input").val( xmlHttpRequest.responseText);
				//	document.getElementById("totalTradeDurationId").readOnly = true;
				//	document.getElementById("totalTradeDurationId").value = xmlHttpRequest.responseText;
				} else{
					/* document.getElementById("totalTradeDurationId").readOnly = true;
					 document.getElementById("totalTradeDurationId").value ='';  */
			}
			} else {
			/* 	$(vv).closest("td").next().find("input").prop("readonly", false);
				 */
				 $(vv).closest("td").next().find("input").val('');
				/* document.getElementById("totalTradeDurationId").value = ''; */
		}
		}
	}

xmlHttpRequest.open("POST","tradeTarget.do?methodName=gettradeDuration", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("tradeid="+tradeid);
}else{

	$(vv).closest("td").next().find("input").prop("readonly", false);
	$(vv).closest("td").next().find("input").val("");
}
}
function addoption(tradeval){
var result=checkTrade(tradeval);
	if(result){
		alert("This trade is already selected....!!");
		tradeval.value="0";
		return false;
	}
	
	
	var abc=null;
	if(tradeval.value=="0"){
		abc="<option value='0'>--SELECT--</option>";
	}else{
		abc="<option value='0'>--SELECT--</option><option value='YES'>YES</option><option value='NO'>NO</option>";
	}
	$(tradeval).closest("td").next().find("select").html(abc);
	
	//document.getElementById("otherTradeId").innerHTML =abc;
	//document.getElementById("totalTradeDurationId").value = '';
}


function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
var projectIDId=$("#projectIDId").val();  
if(projectIDId=="0"){
	alert("Please select a project");
	return false;
}
	var result=validation();
	//alert(result);
			
	if(!result){
		return false;
	}		
	var result2=checktotaltrainingTarget("save");	
	if(!result2){
		return false;
	}
	//alert("aaa");
	var status=window.confirm('Are You Sure You Want to Save ?');
		if(status==true){
			document.forms[0].action="tradeTarget.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
}
  
  
function back(){
	
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="tradeTarget.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
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
function insRow()
{
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
    //   var optionclone=$("#sectorId").html();  
    var projectid=$("#projectIDId").val();
    if(projectid!="0"){
     var optionclone= $(".sector").first().html();
     var option= optionclone.replace("selected", "");
var tablerow="<tr><td><select name='sector' class='sector'  style='width: 95px;' onchange='javascript:getTrade(this);'>"+option+"</select><input type='hidden' name='id' value='0'></td>"+
              "<td> <select name='trade' class='trade' onchange='javascript:addoption(this)' style='width: 95px;'></select></td>"+
              "<td><select name='otherTrade' onchange='javascript:gettradeduration(this);' style='width: 95px;'></select></td>"+
              "<td><input name='totalTradeDuration' type='text' style='width: 50px;' readonly onkeypress='return isNumberKey(event)'></td>"+
              "<td><input name='ojt' type='text' style='width: 50px;'  onkeypress='return isNumberKey(event)'></td>"+
              "<td><input name='trainingTarget' type='text' style='width: 50px;' onblur='javascript:checktotaltrainingTarget('blur')' ></td>"+
            /*   "<td><input name='nonResiFull' type='text' style='width: 50px;' onkeypress='return isNumberKey(event)' ></td>"+ */
             /*  "<td><input name='nonResiPart' type='text' style='width: 50px;' onkeypress='return isNumberKey(event)' ></td>"+
              "<td><input name='nonResiWeekend' type='text' style='width: 50px;' onkeypress='return isNumberKey(event)' ></td>"+ */
             /*  "<td><input name='resiFull' type='text' style='width: 50px;' onkeypress='return isNumberKey(event)' ></td>"+ */
              "<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove Row'  onclick='removeRow(this)'></td></tr>";
       $("#tradetargetTable").append(tablerow);
    }else{
    	alert("please select project..!!");
    }
	}
   
}
function removeRow(vv){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
		var status=window.confirm('Are You Sure You Want tO Delete ?');
		if(status==true){
	var row_length=$('#tradetargetTable tr').length;
	if(row_length<3){
		alert("unable to delete row");
		return false;
	}
	
	$(vv).parent().parent().remove();
		}
	}
}
function validation(){
	//var i=1;
	var result=false;
	$("#tradetargetTable tr").not(':first').each(function(){
		
		var sector =$(this).find('td').eq(0).find('select').val();
		var trade=$(this).find('td').eq(1).find('select').val();
		var otherTrade=$(this).find('td').eq(2).find('select').val();
		var totalTradeDuration=$(this).find('td').eq(3).find('input').val();
		var ojt=$(this).find('td').eq(4).find('input').val();
		var trainingTarget=$(this).find('td').eq(5).find('input').val();
		/* var nonResiFull=$(this).find('td').eq(6).find('input').val(); */
		/* var nonResiPart=$(this).find('td').eq(7).find('input').val();
		var nonResiWeekend=$(this).find('td').eq(8).find('input').val(); */
		/* var resiFull=$(this).find('td').eq(7).find('input').val(); */
		
		
		if(sector=="0"||sector==null||sector==""){
			alert("Please select Sector ");
			result=true;
			return false;
		}
		if(trade=="0"||trade==null||trade==""){
			alert("Please select Trade");
			result=true;
			return false;
		}
		if(totalTradeDuration==null||totalTradeDuration==""){
			totalTradeDurationtotalTradeDuration=0;
		}
		if(ojt==null||ojt==""){
			ojt=0;
		}
		
		if(parseInt(totalTradeDuration)<=parseInt(ojt)){
			alert("OJT(hr) must be less than total trade duration !!");
			result=true;
			return false;
		}                                                                                                                                                                      
		/* if(nonResiFull==null||nonResiFull==""){
			nonResiFull=0;
		} */
		/* if(nonResiPart==null||nonResiPart==""){
			nonResiPart=0;
		}if(nonResiWeekend==null||nonResiWeekend==""){
			nonResiWeekend=0;
		} */
		
		/* if(resiFull==null||resiFull==""){
			resiFull=0;
		} */
		if(trainingTarget==""||trainingTarget==null){
			alert("Please Enter Training Target..!!");
			result=true;
			return false;
		}
// 		/*  */if(parseInt(trainingTarget)!=parseInt(nonResiFull)/* +parseInt(nonResiPart)+parseInt(nonResiWeekend) */+parseInt(resiFull)){
			//alert(trainingTarget+"---"+nonResiFull+" + "+resiFull);
		/* if(parseInt(trainingTarget)!=parseInt(parseInt(nonResiFull)+parseInt(resiFull))){
			
			alert("Training Target must be equal to sum of (Non Residential Full Time ,  Residential Full Time)");
			alert(parseInt(nonResiFull)+parseInt(resiFull);                                                                                                 
			result=true;
			return false;
		} */
		
	});
	if(result){
		return false;
	}else
		return true;
	
	
	
}
function checktotaltrainingTarget(action){
	
	//alert("ooo");
	var project_total_training_target=$("#projecttrainingTarget").val();
	if(project_total_training_target==""||project_total_training_target==null){
	//	alert("Training target should be greater than 0");
		return false;
	}
	var total=0;
	$("#tradetargetTable tr").not(':first').each(function(){
		var trainingTarget=$(this).find('td').eq(5).find('input').val();
		if(trainingTarget==""||trainingTarget==null){
			trainingTarget=0;
		}
		total=parseInt(total)+parseInt(trainingTarget);
	});
	var result=true;
	if(action=="save"){
		if(project_total_training_target!=total){
			alert("Training Target will be equal Project's Total Trainig target..!!");
			result=false;
		}
	}else{ 
		//alert("total");
		if(project_total_training_target<total){
			alert("Training Target Should be less than Project's Total Trainig target..!!");
			result=false;
		}
	}
	
	
	
	if(!result){
		return false;
	}else
		return true;
	
	
}
function checkTrade(vv){
	var result=false;
	var i=0;
	if(vv.value=="0"){
		
	}else{
		$(".trade").each(function(){
			if($(this).val()==vv.value){
				i++;
			}
		});
		
	}
	if(i>1){
		result=true;
	}
	return result;
	
	
}
  
/* $(vv).closest("td").next().find("input").val($(vv).val());  */
</script>
<!--Main form section starts here-->
<html:form action="login/tradeTarget">
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">TRADE TARGET</td>					
               </tr>
				
			</table>			
		
			  
			<table width="100%" align="center"  class="inputTBL">
			 
			 	 <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
						<td>	<html:select property="projectID" styleId="projectIDId"  styleClass="form-control"  onchange="javascript:showRemark(this.value);" >
								<html:option value="0">-select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr>
						  <tr>
						  <th><span class="input-group-addon" id="basic-addon1">Training Target</span></th>
						  <td><input type="text" id="projecttrainingTarget" readonly /></td>
						  </tr>
			 </table>
			<input type="button" value="Add Row" class="button checkPermissionForFormForInsert" style="float: right;" onclick="insRow()"/>
			 
			 <div id="showTable">
			<table width="100%" align="center" id="tradetargetTable"  class="inputTBL">
			
			 <tr><th><span class="input-group-addon" id="basic-addon1">Sector</span></th>
				<th><span class="input-group-addon" id="basic-addon1">Trade</span></th>
				<th>Is Other Trade Included <span class="text-error">*</span></th>		
				<th>Total Trade Duration(in hours) <span class="text-error">*</span></th>	
				<th>OJT(in hours) <span class="text-error">*</span></th>	
				<th>Training Target <span class="text-error">*</span></th>
				<!-- <th>Non Residential Full Time<span class="text-error"></span></th> -->
				<!-- <th>Non Residential Part Time<span class="text-error"></span></th>
				<th>Non Residential Weekend<span class="text-error"></span></th> -->
				<!-- <th>Residential Full Time<span class="text-error"></span></th> -->
				<th></th>
				</tr>
			
			
				
			</table>
			
			</div>
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>			
			
			 
			
	
</html:form>
 