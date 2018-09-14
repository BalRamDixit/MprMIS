<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<!--script for menu-->
<script type="text/javascript">

	function saveform(){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var projectid=document.getElementById("projectIdId").value;
		if(projectid=="0"){
			alert("Please select project Id");
			return false;
		}
		var rr=false;
		$("input[data-test]").each(function(){
			var target=$(this).data('test');
			var abc=$("#expTrainComn"+target).val();
			var efg=$("#expTrainComp"+target).val();
			var jkl=$("#placeExp"+target).val();
			
			if(abc==null||abc==""){
				abc=0;
			}if(efg==null||efg==""){
				efg=0;
			}if(jkl==null||jkl==""){
				jkl=0;
			}
			var result=0;
			
			if(parseInt(abc)<parseInt(efg)+parseInt(jkl)){
				alert("total of Expected Training Completion and Expected Placement should be greater or equal to Expected Training Commencement of "+target.substring(0, 3) + "," + target.substring(3));
				$("#expTrainComn"+target).focus();
				
				rr=true;
				return false;
			}
		});
		
		if(rr){
			return false;
		}
	
		var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="ppwsSetup.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		} 
		}
	}
	function editform(){
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
		var projectid=document.getElementById("projectIdId").value;
		if(projectid=="0"){
			alert("Please select project Id");
			return false;
		}
		var rr=false;
		$("input[data-test]").each(function(){
			
			var target=$(this).data('test');
			var abc=$("#expTrainComn"+target).val();
			var efg=$("#expTrainComp"+target).val();
			var jkl=$("#placeExp"+target).val();
			
			if(abc==null||abc==""){
				abc=0;
			}if(efg==null||efg==""){
				efg=0;
			}if(jkl==null||jkl==""){
				jkl=0;
			}
			var result=0;
			
			if(parseInt(abc)<parseInt(efg)+parseInt(jkl)){
				alert("total of Expected Training Completion and Expected Placement should be greater or equal to Expected Training Commencement of "+target.substring(0, 3) + "," + target.substring(3));
				$("#expTrainComn"+target).focus();
				
				rr=true;
				return false;
			}
		});
		
		if(rr){
			return false;
		}
		var status=window.confirm('Are You Sure You Want to update ?');
		if(status==true){
			document.forms[0].action="ppwsSetup.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		} 
	}
	}
	function backform(){
		  var status=window.confirm('Are You Sure You Want to go back ?');
			if(status==true){
				document.forms[0].action="ppwsSetup.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
			}  
	  }

function showRemark(str){
	if (str!='0')
	{
		//document.getElementById("showTable").style.display = "";
  			
	} else{
		//document.getElementById("showTable").style.display = "none";
 	}
}
$(document).ready(function()
{
	document.getElementById("projectIdId").value="0";
	showRemark("0");
});

     
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
		
		function showTableFn(value){
			if(value!=0){
			
			var xmlHttpRequest = getXMLHttpRequest();
		        xmlHttpRequest.onreadystatechange = function(){
				 if (xmlHttpRequest.readyState == 4) {
						if (xmlHttpRequest.status == 200) {
						 if (xmlHttpRequest.responseText != "") {
								var response=xmlHttpRequest.responseText.split("^^^");
								
								document.getElementById("showtable").innerHTML = response[0];
								var dd=response[1].split("<--->");
								var month=dd[0].substring(5, 7);
								var year=dd[0].substring(0,4);
								var date=dd[0].substring(8,10); 
								var month1=dd[1].substring(5, 7);
								var year1=dd[1].substring(0,4);
								var date1=dd[1].substring(8,10); 
								$("#commencement").text(date+"-"+month+"-"+year);
								$("#enddate").text(date1+"-"+month1+"-"+year1);
							} else{
								
								
						}
						} else {
							alert("error");
							
					}
					}
				}
			  
		xmlHttpRequest.open("POST","ppwsSetup.do?methodName=durationMonths", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("projectId="+value);	
			}else{
				document.getElementById("showtable").innerHTML = "Please select a project";
				$("#commencement").text("");
				$("#enddate").text("");
			}

		}
   
</script>
<!--Main form section starts here-->
<html:form action="login/ppwsSetup" styleId="ppwsForm">

	 
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">PPWS Setup</td>					
               </tr>
			</table> 
				<table width="100%" align="center" class="inputTBL">
			 
			 	 <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
						<td>	<html:select property="projectId" styleId="projectIdId"  styleClass="form-control"  onchange="javascript:showTableFn(this.value);" >
								<html:option value="0">-Select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr>
						   <tr>
						   <th width="25%">Date of commencement</th>
						   <td width="25%" id="commencement"></td>
						   <th width="25%" >End Date</th>
						   <td width="25%" id="enddate"></td>
						   </tr>
			 </table>
				<div id="showtable">Please select a project</div>
				
			
			<br></br>
		 		 	
</html:form>
 