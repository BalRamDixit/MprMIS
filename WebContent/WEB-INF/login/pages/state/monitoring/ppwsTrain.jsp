<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title></title>


<!--script for menu-->
<script type="text/javascript">

var bullEye=null;
$(document).ready(function()
{
	document.getElementById("projectIDD").value="";
	$("#tcIdADD").change();
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
};

function getTrainingCenterList(projectid){
	var xmlHttpRequest = getXMLHttpRequest();
		  xmlHttpRequest.onreadystatechange = function(){
			  if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					if (xmlHttpRequest.responseText != "") {
						     document.getElementById("tcIdADD").innerHTML = xmlHttpRequest.responseText;
					} else{
					}
					} else {
						     document.getElementById("tcIdADD").innerHTML = '';
				}
				}
			}
		xmlHttpRequest.open("POST","ppwsAchieveTrain.do?methodName=getListOfTCId", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("projectid="+projectid);	
}


function getTrainList(){
	
	var  prID=$("#projectIDD").val();
	var month=$("#month").val();
	var year=$("#year").val();
	var tcId=$("#tcIdADD").val();
	
	if(prID!=0 && month!=0  && year!=0 && tcId!=0){
		
	
   var url="ppwsAchieveTrain.do?methodName=getTCList&prID="+prID+"&month="+month+"&year="+year+"&tcId="+tcId;
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
}
function isGraterThen35(element){
	if($(element).val()>35){
		alert("Value Should not be grater then 35");
		$(element).focus();
	}
}
 function handleHttpResponse(){
	 $('#tbody').html("");
    	if (req.readyState == 4){	
    	var response = req.responseText; 
    	var aa=JSON.parse(response);
    	var dynamic="";
    	for(var i=0;i<aa.length;i++){
    		
    		dynamic+="<tr>";
    		dynamic+="<td><input type='hidden' name='batchId' value='"+aa[i].bId+"'><input type='text'  readOnly='true' id='batchId' value='"+aa[i].batchId+"'><input type='hidden' name='id'  value='"+aa[i].id+"'></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_Total'  id='commenced_TotalId'  value='"+aa[i].commenced_Total+"' onblur='isGraterThen35(this)'></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_Sc'  id='commenced_ScId'  value='"+aa[i].commenced_Sc+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_St'  id='commenced_StId'  value='"+aa[i].commenced_St+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_Others'  id='commenced_OthersId'  value='"+aa[i].commenced_Others+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_Women'  id='commenced_WomenId'  value='"+aa[i].commenced_Women+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_Minority'  id='commenced_MinorityId'  value='"+aa[i].commenced_Minority+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='commenced_Pwd'  id='commenced_PwdId'  value='"+aa[i].commenced_Pwd+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_Total'  id='completed_TotalId'  value='"+aa[i].completed_Total+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_Sc'  id='completed_ScId'  value='"+aa[i].completed_Sc+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_St'  id='completed_StId'  value='"+aa[i].completed_St+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_Others'  id='completed_OthersId'  value='"+aa[i].completed_Others+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_Women'  id='completed_WomenId'  value='"+aa[i].completed_Women+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_Minority'  id='completed_MinorityId'  value='"+aa[i].completed_Minority+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='completed_Pwd'  id='completed_PwdId'  value='"+aa[i].completed_Pwd+"'onblur='isGraterThen35(this)' ></td>";
    		dynamic+="</tr>";
    		
    	}
    	$('#tbody').append(dynamic);
    }
}
function checkValidation(month,year,train){
		
	  var url="ppwsAchieveTrain.do?methodName=getValDone&month="+month+"&year="+year+"&train="+train;  
	  if (window.XMLHttpRequest)
		{
		  req = new XMLHttpRequest();
		} else if (window.ActiveXObject)
		{
		  req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("get", url, true);
		req.setRequestHeader(tokenParameter, tokenValue);
		req.onreadystatechange = handleHttpResponseNew;
		req.send(); 
		} 
	
	function handleHttpResponseNew(){
		
	  if (req.readyState == 4)
	  {	
		var saveDataOrNotFlag=0;
	    var response = req.responseText;
	    bullEye=JSON.parse(response);
	    var checkforerror=0;	
	   
	   	for(i=0;i<bullEye.length;i++)
	     {
	       	
	        var comSc =bullEye[i].commenced_Sc;
	        var comSt =bullEye[i].commenced_St;	
	       	var comOthers =bullEye[i].commenced_Others;
	    	var comWomen =bullEye[i].commenced_Women;
	    	var comMinority =bullEye[i].commenced_Minority;
	    	var comPwd =bullEye[i].commenced_Pwd;
	    	var compSc =bullEye[i].completed_Sc;
	    	var compSt =bullEye[i].completed_St;
	    	var compOthers =bullEye[i].completed_Others;
	    	var compWomen =bullEye[i].completed_Women;
	    	var compMinority =bullEye[i].completed_Minority;
	    	var compPwd =bullEye[i].completed_Pwd;
	    	var batchId =bullEye[i].batchId;
	    	
	    	$("#abcd > tbody > tr").each(function()
	        {
	    	 var batchid=$(this).find('td').eq(0).find("input[name='batchId']");
	    	
	    	 if(batchid.val()==bullEye[i].bId){
	    		    var comSc1= $(this).find("td").eq(1).find("input");
	    			var comSt1 =$(this).find("td").eq(2).find("input");
	    			var comOthers1 =$(this).find("td").eq(3).find("input");
	    			var comWomen1 =$(this).find("td").eq(4).find("input");
	    			var comMinority1 =$(this).find("td").eq(5).find("input");
	    			var comPwd1 =$(this).find("td").eq(6).find("input");
	    			var compSc1 =$(this).find("td").eq(7).find("input");
	    			var compSt1 =$(this).find("td").eq(8).find("input");
	    			var compOthers1 =$(this).find("td").eq(9).find("input");
	    			var compWomen1=$(this).find("td").eq(10).find("input");
	    			var compMinority1 =$(this).find("td").eq(11).find("input");
	    			var compPwd1 =$(this).find("td").eq(12).find("input");
	    			
	    			if(comSc>comSc1.val()){
		    			alert("Should be more than or equal to previous month");
		    			checkforerror++;
		    			$(comSc1).focus();
		    			 return false;
		    		}	
	    			 if(comSt>comSt1.val()){
	 	    			alert("Should be more than or equal to previous month");
	 	    			checkforerror++;
	 	    			$(comSt1).focus();
	 	    			 return false;
	 	    		}
	    			 if(comOthers>comOthers1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(comOthers1).focus();
		 	    			 return false;
		 	    		}
	    			 if(comWomen>comWomen1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(comWomen1).focus();
		 	    			 return false;
		 	    		}
	    			 if(comMinority>comMinority1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(comMinority1).focus();
		 	    			 return false;
		 	    		}
	    			 if(comPwd>comPwd1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(comPwd1).focus();
		 	    			 return false;
		 	    		}
	    			 if(compSc>compSc1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(compSc1).focus();
		 	    			 return false;
		 	    		}
	    			 if(compSt>compSt1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(compSt1).focus();
		 	    			 return false;
		 	    		}
	    			 if(compOthers>compOthers1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(compOthers1).focus();
		 	    			 return false;
		 	    		}
	    			 if(compWomen>compWomen1.val()){
	    				 alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(compWomen1).focus();
		 	    			 return false;
	    			 }
	    			 if(compMinority>compMinority1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(compMinority1).focus();
		 	    			 return false;
		 	    		}
	    			 if(compPwd>compPwd1.val()){
		 	    			alert("Should be more than or equal to previous month");
		 	    			checkforerror++;
		 	    			$(compPwd1).focus();
		 	    			 return false;
		 	    		}
	    			 
	    			 
	    			 
	    		//...secound validation code	 
	    			 
	    			 
	    			 if(compSc1.val() > comSc1.val()){
	    				 alert("Should be less than or equal to respective Training Commencement Achieved");
		 	    			checkforerror++;
		 	    			$(compSc1).focus();
		 	    			 return false; 
	    			 }
	    			 if(compSt1.val() > comSt1.val()){
	    				 alert("Should be less than or equal to respective Training Commencement Achieved");
		 	    			checkforerror++;
		 	    			$(compSt1).focus();
		 	    			 return false; 
	    			 }
	    			 
	    			 if(compOthers1.val() > comOthers1.val()){
	    				 alert("Should be less than or equal to respective Training Commencement Achieved");
		 	    			checkforerror++;
		 	    			$(compOthers1).focus();
		 	    			 return false; 
	    			 }
	    			 if(compWomen1.val() > comWomen1.val()){
	    				 alert("Should be less than or equal to respective Training Commencement Achieved");
		 	    			checkforerror++;
		 	    			$(compWomen1).focus();
		 	    			 return false; 
	    			 }
	    			 if(compMinority1.val() > comMinority1.val()){
	    				 alert("Should be less than or equal to respective Training Commencement Achieved");
		 	    			checkforerror++;
		 	    			$(compMinority1).focus();
		 	    			 return false; 
	    			 }
	    			 
	    			 if(compPwd1.val() > comPwd1.val()){
	    				 alert("Should be less than or equal to respective Training Commencement Achieved");
		 	    			checkforerror++;
		 	    			$(compPwd1).focus();
		 	    			 return false; 
	    			 }
	    		}
	    	});
	    	
	    	if(checkforerror > 0){
	    		saveDataOrNotFlag++;
	    		break;
			 }    		
	    	} 
	       	
	       	if(saveDataOrNotFlag<1){
	       	var status=window.confirm('Are You Sure You Want tO Save ?');
	   		if(status==true){
	       		document.forms[0].action="ppwsAchieveTrain.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
	   		 }
	       	}
	    	
	    	}	
				
	}
	
 function save(){
	 var x=checkPermissionForFormForInsert();
	if(x=='true'){	
	 var month=document.getElementById("month").value;
	 var year=document.getElementById("year").value;
	// var proj=document.getElementById("projectIDD").value;
	 var train=document.getElementById("tcIdADD").value;
	 checkValidation(month,year,train); 
	} 
}
 
</script>
<!--Main form section starts here-->
<html:form action="login/ppwsAchieveTrain">

	
			
	<table width="100%" class="pageHeaderTable">
	<tr>
		<td align="center" class="pageHeader">MONITORING - PPWS ACHIEVEMENT(TRAINING)</td>
	</tr>
	</table>
			
	<table width="100%" align="center" class="inputTBL">
	
   
    <tr>
	<th><span class="input-group-addon" id="basic-addon1">Year</span></th>
	<td><html:select property="year" styleId="year"  onchange="javascript:getTrainList();">
		<html:option value="0">--SELECT--</html:option>
				<html:option value="2017">2017</html:option>
		</html:select></td>
	
	<th><span class="input-group-addon" id="basic-addon1">Month</span></th>
		<td><html:select property="month" styleId="month"   onchange="javascript:getTrainList();" >
			        <html:option value="0">--SELECT--</html:option>
					<html:option value="1">January</html:option>
					<html:option value="2">February</html:option>
					<html:option value="3">March</html:option>
					<html:option value="4">April</html:option>
					<html:option value="5">May</html:option>
					<html:option value="6">June</html:option>
					<html:option value="7">July</html:option>
					<html:option value="8">August</html:option>
					<html:option value="9">September</html:option>
					<html:option value="10">October</html:option>
					<html:option value="11">November</html:option>
					<html:option value="12">December</html:option>
			</html:select></td>
	</tr>
	
		  
	    <tr>
	    <th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
		<td>	<html:select property="projectId" styleId="projectIDD"  styleClass="form-control"  onchange="getTrainingCenterList(this.value);getTrainList();">
								<html:option value="">-select-</html:option>
								<logic:present name="projectList">
									<logic:iterate id="project" name="projectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
								</logic:present>
				</html:select>
		</td>
						  	
	 
	    <th><span class="input-group-addon" id="basic-addon1">Training Id</span></th>
		<td>
		   <html:select property="tcId" styleId="tcIdADD"  styleClass="form-control"  onchange="getTrainList(this.value);" >
				<html:option value="0">-select-</html:option>
				       <logic:present name="TrainingList">
				        	<logic:iterate id="training" name="TrainingList">
							   <html:option value="${training}">${training}</html:option>
				        	</logic:iterate>
					</logic:present>
			</html:select>
		</td>
	 </tr>
</table>
	
			<br/>
			<div>		
			<div style="overflow-x:scroll;overflow-y:hidden;width:100%;">
			<table width="100%" align="center" id="abcd" >	
			<thead>		
						 <tr  width="100%"   bgcolor="#dfdfdf">
			                 
						          	 
						          	 <th style="text-align:center">Batch ID <span class="text-error"></span></th>
						   			 <th style="text-align:center">Total<span class="text-error"></span></th>
					                 <th style="text-align:center">SC<span class="text-error"></span></th>
					                 <th style="text-align:center">ST<span class="text-error"></span></th>
					                 <th style="text-align:center">Others<span class="text-error"></span></th> 
					                 <th style="text-align:center">Women<span class="text-error"></span></th>
					                 <th style="text-align:center">Minority<span class="text-error"></span></th>
					                 <th style="text-align:center">PWD<span class="text-error"></span></th>
					                
					                 <th style="text-align:center">Total<span class="text-error"></span></th>    
					                 <th style="text-align:center">SC<span class="text-error"></span></th>
					                 <th style="text-align:center">ST<span class="text-error"></span></th>
					                 <th style="text-align:center">Others<span class="text-error"></span></th> 
					                 <th style="text-align:center">Women<span class="text-error"></span></th>
					                 <th style="text-align:center">Minority<span class="text-error"></span></th>
					                 <th style="text-align:center">PWD<span class="text-error"></span></th>
					                                
			               			
			                </tr>
			                </thead>
			               
			                <tbody id="tbody">
			                </tbody>
			            	
	             	</table>
			</div>
			
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="Save" onclick="save()"/> 
				<input name="Button" type="button" class="button" value="back" onclick="back()" />
			</div>	
		</div>	
			
</html:form>
</body>
<!--Main form section ends here-->