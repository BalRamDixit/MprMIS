<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<style>
.input{
width: 74px!important;
}
</style>
<!--script for menu-->
<script type="text/javascript">

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
	
	function getDetails(){
		var month=$("#month").val();
		var year=$("#year").val();	
		var currentmonth=$("#currentmonth").val();
		var currentyear=$("#currnetyear").val();
		if(month.length<2 || year.length <2){
			alert("please select Both month and year..!!");
			return false;
		}
	
		
		if(currentmonth == month && currentyear == year){
			document.forms[0].action="candidateAction.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
	}
		else{
			if(month.length>0 && year.length > 0){
			var xmlHttpRequest = getXMLHttpRequest();
			
			
			  xmlHttpRequest.onreadystatechange = function(){
				 
				  
					if (xmlHttpRequest.readyState == 4) {
						
						if (xmlHttpRequest.status == 200) {
							
							if (xmlHttpRequest.responseText != "") {
								
								var aa=JSON.parse(xmlHttpRequest.responseText);
								var dynamicRow="";
								for(var i=0;i<aa.length;i++){
							dynamicRow+="<tr>";		
							dynamicRow+="<td><input type='text' style='width: 150px;' readonly value='"+aa[i].projectId+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].total_no_of_can+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].total_no_of_can_with_Adhar_no+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].total_no_of_can_Bank_acc+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].total_no_of_can_Mobile_no+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].total_no_can_aadhar_lin_bank_acc+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].no_of_can_insurance+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].pro_life_trained_can+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].foreign_placed+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].can_place_earn_more+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].can_retained+"' /></td>";
							dynamicRow+="<td><input type='text' class='input' readonly value='"+aa[i].placed_documented_sub_upload+"' /></td>";
							dynamicRow+="</tr>";		
								}
								$("#candidateStatTable tr").not(':first').remove();
								$("#candidateStatTable").append(dynamicRow);
								document.getElementById("saveButton").disabled=true;
								$("#saveButton").css("background-color", "gray");
								
							} else{
								
						}
						} else {
							alert("error...!!!!");
					}
					}
				}
			
		xmlHttpRequest.open("POST","candidateAction.do?methodName=getDeatils", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("month="+month+"&year="+year);	
			}
			
		}
		
		
	}
	



function saveform(){
	 var x=checkPermissionForFormForInsert();
		if(x=='true'){
	var status=window.confirm('Are You Sure You Want tO Save ?');
	if(status==true){
		document.forms[0].action="candidateAction.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	}
		}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
$(document).ready(function(){
			window.setTimeout(function (){
				$("#month").val($("#currentmonth").val());
				$("#year").val($("#currnetyear").val());
	    }, 100);
		});
function checkvalue(vv){
	
    var total_no_of_can=$(vv).closest('tr').find('td').eq(1).find('input').val();
    var no_of_can=$(vv).val();
	if( parseInt(no_of_can) >  parseInt(total_no_of_can)){
		alert("Please enter less or equal number of candidate to total number of candidate ie "+total_no_of_can);
		vv.value=no_of_can.substring(0, no_of_can.length-1);
		return false;
	}
}

function fnExcelReport(TableIdToExport)
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById(TableIdToExport); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)){
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"FileName.xls");
    }  
    else{
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));
    }
    return (sa);
}


</script>

 <html:form action="login/candidateAction">
			<iframe id="txtArea1" style="display:none"></iframe>
			<table width="100%" class="pageHeaderTable" >
				<tr>
					<td align="center" class="pageHeader">CANDIDATE STATISTICS</td>					
               </tr>
				
			</table>			
			
				
		
		<input type="hidden" value="${currentmonth}" id="currentmonth"/>
		<input type="hidden" value="${currentyear}" id="currnetyear"/>
	
	    <table id="exportTable">	
		 <tr>
		 <th>
	
	   <table width="100%" align="center" class="inputTBL">
		<tr>
			<th><span class="input-group-addon" id="basic-addon1">
					Month</span></th>
			<td><%-- <html:text property="month" value='February' readonly="true"></html:text> --%><html:select property="month" styleId="month" onchange="getDetails()">
			        <html:option value="0">--SELECT--</html:option>
					<html:option value="January">January</html:option>
					<html:option value="February">February</html:option>
					<html:option value="March">March</html:option>
					<html:option value="April">April</html:option>
					<html:option value="May">May</html:option>
					<html:option value="June">June</html:option>
					<html:option value="July">July</html:option>
					<html:option value="August">August</html:option>
					<html:option value="September">September</html:option>
					<html:option value="October">October</html:option>
					<html:option value="November">November</html:option>
					<html:option value="December">December</html:option>
				</html:select></td>
		
			<th><span class="input-group-addon" id="basic-addon1">
					Year</span></th>
			<td><html:select property="year" styleId="year" onchange="getDetails()">
			 <html:option value="0">--SELECT--</html:option>
					<html:option value="2017">2017</html:option>
				</html:select></td><td></td>
		</tr>
	</table>
	
	<table width="100%" align="center" class="inputTBL" id="candidateStatTable">
		<tr>
			<th>Project Id</th>
			<th>Total No of candidate<br/>Under training+Trained</th>
			<th>No. of Candidate<br/>With Aadhaar Card</th>
			<th>No. of candidate<br/>Bank Account</th>
			<th>No. of Candidate<br/>Mobile Number</th>
			<th>No. of Candidate<br/>Aadhar linkage<br/>with Bank Account</th>
			<th>No. of Candidate<br/>with insurance</th>
			<th>No. of Pro-Life<br/>Trained Candidate</th>
			<th>No. of Candidate<br/>Foreign Placed</th>
			<th>No. of Candidate<br/>placed earning >=15,000PM</th>
			<th>No. of Candidate<br/>Retained For > 12 Months</th>
			<th>Placement Document submitted<br/>/uploaded for No.<br/>of Candidate</th>
		</tr>
		<logic:iterate name="candidateList" id="candidateList"  scope="request">
		<tr>
		<td><input type="text" readonly="true" value="${candidateList.project.projectID}" style='width: 150px;'/><input type="hidden" name="projectId" value="${candidateList.project.id}"/><html:hidden property="id" value='${candidateList.id}'></html:hidden></td>
		<td><html:text property="total_no_of_can" readonly="true" value="${candidateList.total_no_of_can}" styleClass="input"></html:text></td>
		<td><html:text property="total_no_of_can_with_Adhar_no" value="${candidateList.total_no_of_can_with_Adhar_no}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)" ></html:text></td>
		<td><html:text property="total_no_of_can_Bank_acc" value="${candidateList.total_no_of_can_Bank_acc}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="total_no_of_can_Mobile_no" value="${candidateList.total_no_of_can_Mobile_no}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="total_no_can_aadhar_lin_bank_acc" value="${candidateList.total_no_can_aadhar_lin_bank_acc}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="no_of_can_insurance" value="${candidateList.no_of_can_insurance}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="pro_life_trained_can" value="${candidateList.pro_life_trained_can}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="foreign_placed" value="${candidateList.foreign_placed}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="can_place_earn_more" value="${candidateList.can_place_earn_more}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="can_retained" value="${candidateList.can_retained}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		<td><html:text property="placed_documented_sub_upload" value="${candidateList.placed_documented_sub_upload}" styleClass="input" onkeypress="return isNumberKey(event)"  onkeyup="checkvalue(this)"></html:text></td>
		</tr>
		</logic:iterate>
	</table>	  
			  
		</th>
		</tr>
		</table>	  
			  

			 
	<div align="center">
		<input name="Button" type="button" class="button checkPermissionForFormForInsert" id="saveButton" value="Save" onclick="saveform()"/> 
		<button id="btnExport" class="button"  onclick="fnExcelReport('exportTable');"> EXPORT </button>		  
			   <%--  <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/>  --%>
			</div>		
	
</html:form>
 