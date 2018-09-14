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
		
		var projectId=$("#projectIDId").val();
			/* var sector=document.getElementById("sectorId").value; */
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							$(sector).closest("td").next().find("select").html(xmlHttpRequest.responseText);
							
							//document.getElementById("tradeId").innerHTML = xmlHttpRequest.responseText;
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
		
	xmlHttpRequest.open("POST","tcSetupTrade.do?methodName=getTargetDetail", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("sector="+sector.value+"&projectId="+projectId);	

	}
	
function getSectorofproject(projectid){
		
		var xmlHttpRequest = getXMLHttpRequest();
		
		
			/* var sector=document.getElementById("sectorId").value; */
		  xmlHttpRequest.onreadystatechange = function(){
			 
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							var str=xmlHttpRequest.responseText;
							//var str_arr=str.split('<~~~>');
							
							//document.getElementById("sectorCode").innerHTML =str_arr[0];
							
							/* $(".sector").html(str_arr[0]);
							$(".trade").html(""); */
							document.getElementById("tcId").innerHTML =str;
							$("#dynamicTable tr").not(':first').remove();
							document.getElementById("total_approved_capasity").value="";
						} 
						else {
						/* 	$(".sector").html("<option value='0'> --SELECT-- </option>"); */
							document.getElementById("tcId").innerHTML ="<option value='0'> --SELECT-- </option>";
							document.getElementById("total_approved_capasity").value="";
							$("#dynamicTable tr").not(':first').remove();
							//document.getElementById("sectorCode").innerHTML = '<option value="0"> --SELECT-- </option>';
					}
					} else {
						/* $(".sector").html("<option value='0'> --SELECT-- </option>"); */
						document.getElementById("tcId").innerHTML ="<option value='0'> --SELECT-- </option>";
						document.getElementById("total_approved_capasity").value="";
						$("#dynamicTable tr").not(':first').remove();
						//document.getElementById("sectorCode").innerHTML = '<option value="0"> --SELECT-- </option>';
				}
				}
			}
		
	xmlHttpRequest.open("POST","tcSetupTrade.do?methodName=getsector", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectid="+projectid);	

	}
 
 function getApprovedCapacity(tcidobj){
	 var xmlHttpRequest = getXMLHttpRequest();
		
		var projectId=document.getElementById("projectIDId").value;
		/* var sector=document.getElementById("sectorId").value; */
	  xmlHttpRequest.onreadystatechange = function(){
		 
		  
			if (xmlHttpRequest.readyState == 4) {
				
				if (xmlHttpRequest.status == 200) {
					
					if (xmlHttpRequest.responseText != "") {
						var response = xmlHttpRequest.responseText;
						var res=response.split("<--->");
						//alert(res[0]);
						//alert(res[1]);
						
					$("#dynamicTable tr").not(':first').remove();
					$("#dynamicTable").append(res[0]);
						if(res[1]=="null" ||res[1]< 1){
							alert("Wait for capacity approved");
							document.getElementById("total_approved_capasity").value="";
						}else{
							document.getElementById("total_approved_capasity").value=res[1];
						}
						
						/* if(xmlHttpRequest.responseText>0){
							document.getElementById("total_approved_capasity").value=xmlHttpRequest.responseText;
							
							
							
							
							
						}else{
							alert("Wait for capacity approved");
							document.getElementById("total_approved_capasity").value="";
						} */
						
						
					} 
				} else {
					
					document.getElementById("total_approved_capasity").value = '';
			}
			}
		}
	
xmlHttpRequest.open("POST","tcSetupTrade.do?methodName=getApprovedCapacity", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("tcid="+tcidobj.value+"&&projectId="+projectId);
	 
 }
  
 	 
/*  function showRemark(str){
	 	 
		 if (str!='0')
			{
				document.getElementById("showtable").style.display = "";	
				getSectorofproject(str);
		 			
			} else{
				document.getElementById("showtable").style.display = "none";
		 	}
		 
		 
	 } 
	  */
	 $(document).ready(function()
			 {
		  $("#projectIDId").val("0");
		// showRemark("0");
			 });

	 function editDetail(projectId){
	 	 
		
	 	 document.forms[0].action="tcSetupTrade.do?methodName=edit&projectId="+projectId+'&reqtrack='+tokenValue;
	 	 document.forms[0].submit();
			
	 }

	  	 
	 function addDetail(){
		 var x=checkPermissionForFormForInsert();
			if(x=='true'){
	 	 document.forms[0].action="tcSetupTrade.do?methodName=addDetail"+"&"+tokenParameter+"="+tokenValue;;
	 	 document.forms[0].submit(); 
			}
	 }

	 function save(){
		 var x=checkPermissionForFormForInsert();
			if(x=='true'){
		var projectid= $("#projectIDId").val();
		var tcid= $("#tcId").val();
		var total_cap=$("#total_approved_capasity").val();
		if(projectid=="0"){
			alert("Please Select Project..!");
			return false;
		}
		if(tcid=="0"){
			alert("Please Select Training Center id..!");
			return false;
		}
		
		
		
		 var result=validateTable();
			if(!result){
				return false;
			}
			/* var result1= calculateApprovedCapacity("save");
			if(!result1){
				return false;
			} */
				
			var status=window.confirm('Are You Sure You Want to Save ?');
				if(status==true){
					document.forms[0].action="tcSetupTrade.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
					document.forms[0].submit();
				}
			}
	 }

	 
	 function back(){
	 	
	 	var status=window.confirm('Are You Sure You Want to go back ?');
	 		if(status==true){
	 			document.forms[0].action="tcSetupTrade.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
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
	function insRow(){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var tcid=$("#tcId").val();
	    var projectId=$("#projectIDId").val();
	    if(projectId=="0"){
			alert("Please Select a Project id..!!");
			return false;
		}
		 if(tcid=="0"){
			alert("Please Select a Training Center id..!!");
			return false;
		}
		 var optionclone=$(".sector").first().html();
		 var option= optionclone.replace("selected", "");
		var tableRow="<tr><td><select class='sector' name='sectorCode' onchange='javascript:getTrade(this);'>"+option+"</select><input type='hidden' name='id' value='0'></td><td><select name='tradeCode' class='trade' onchange='checkTradeTarget(this)'></select></td>"+
		"<td><input type='text' name='appTradeCapacity' class='apptrade' onblur='calculateApprovedCapacity('blur')' onkeypress='return isNumberKey(event)'></td>"+
		"<td><input type='button' class='button checkPermissionForFormForInsert' value='Remove' onclick='removeRow(this)'/></td></tr>";
	    $("#dynamicTable").append(tableRow);
		}
	} 
	function removeRow(vv){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
			var status=window.confirm('Are You Sure You Want tO Delete ?');
			if(status==true){
		var row_length=$('#dynamicTable tr').length;
		if(row_length<3){
			alert("unable to delete row");
			return false;
		}
		$(vv).parent().parent().remove();
			}
		}
	}
	function validateTable(){
		var i=0;
		$("#dynamicTable tr").not(':first').each(function(){
			var sector =$(this).find('td').eq(0).find('select').val();
			var trade=$(this).find('td').eq(1).find('select').val();
			var appTradeCap=$(this).find('td').eq(2).find('input').val();	
			if(sector=="0"){
				alert("Please select Sector..!");
				i++;
			}
          if(trade=="0"){
        	  alert("Please select Trade..!");
        	  i++;
			}
			if(appTradeCap==""||appTradeCap==null){
				 alert("Please enter Trade capacity..!");
	        	  i++;
			}
			
		});
		if(i>0){
			return false;
		}else{
			return true;
		}
	}
	function calculateApprovedCapacity(action){
		/* var totalApprovedCapacity=$("#total_approved_capasity").val();
		var total=0;
		$(".apptrade").each(function(){
			var capacity=$(this).val();
			total=parseInt(total)+parseInt(capacity);
			
		});
		var result=true;
		if(action=="save"){
			if(totalApprovedCapacity != total){
				alert("Approved capacity must be equal to Total Approved capacity ..!!");
			     result=false;
			}
		}else{
			if(totalApprovedCapacity < total){
				alert("Approved capacity not greater than Total Approved capacity ..!!");
			     result=false;
			}
		}
		
		return result; */
		}
	function checkTradeTarget(vv){
		
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
			vv.value="0";
			alert("This Trade is already selected..!");
			return false;
		}else{
			return true;
		}
		
	}
  
</script>
<!--Main form section starts here-->
<html:form action="login/tcSetupTrade">

	
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Trade Capacity</td>
					
               </tr>
				
			</table>
			
				
			
			
			<table width="100%" align="center" class="inputTBL">
				<%-- <tr>
					<th>${name}</th>
					<td><b>${nameValue}</b></td>
				</tr> --%>
				<tr>
				<th>Project ID</th><td>
				<html:select property="projectID" styleId="projectIDId"  styleClass="form-control"  onchange="javascript:getSectorofproject(this.value);" >
								<html:option value="0">-select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
				</tr>
				<tr>
				<th>Training Center ID</th><td>
				<html:select property="tcId"  styleId="tcId" onchange="javascript:getApprovedCapacity(this)">
				<html:option value="0">- Select -</html:option>
				
				
				</html:select></td>
				</tr>
			<!-- <tr>
			<th>Training Center Approved Target</th>
			<td></td>
			</tr> -->
					
				
				
				</table>
				<input type="hidden" id="total_approved_capasity" readonly/>
			
			
			 <input type="button" value="Add Row" class="button checkPermissionForFormForInsert" style="float: right;" onclick="insRow()"/>
			<div id="showtable">
			<table width="100%" align="center" id="dynamicTable" class="inputTBL">
			<tr>
			
					<th>Sector<span class="text-error"></span></th>
					<th>Trade Code & Name<span class="text-error"></span></th>
					<th>Approved Trade Capacity<span class="text-error"></span></th>
					<th></th>
					
			</tr>
				<%-- <tr>	
				<td><span class="text-error">  
					<html:select property="sectorCode" styleId="sectorCode"   styleClass="form-control sector"  onchange="javascript:getTrade(this);">
								<html:option value="">-select-</html:option>
								
							</html:select>	
					</span></td>
					
					<td><span class="text-error">  
					<html:select property="tradeCode" styleClass="trade" onchange="checkTradeTarget(this)" styleId="tradeId">
					<html:option value="0">--SELECT--</html:option>
					
					</html:select>	
					</span></td>
					<td><span class="text-error"><html:text  property="appTradeCapacity" styleClass="apptrade" onblur="calculateApprovedCapacity('blur')" styleId="appTradeCapacityId" onkeypress="return isNumberKey(event)"/>  </span></td>
					<td><input type="button" value="Remove" onclick="removeRow(this)"/></td>
				</tr> --%>
				
				
			</table>
			
			
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>	
			 	</div>
			
</html:form>
 