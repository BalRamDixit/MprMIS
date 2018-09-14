<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<!--script for menu-->
<style>

</style>

<script type="text/javascript">
var bullEye=null;

$(document).ready(function()
{
	document.getElementById("projectIDD").value="";
  $("#tcIdADD").change();
  
});

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

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
function isGraterThen35(element){
	if($(element).val()>35){
		alert("Value Should not be grater then 35");
		$(element).focus();
	}
}
function calculateSum(){
 
	
	  var sc= document.getElementById("commencedScId").value;
	  var st= document.getElementById("commencedStId").value;
	  var oth= document.getElementById("commencedOthersId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(oth==null || oth==''){
		  oth="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(oth);
	  document.getElementById("sumCatId").value=sum;
	  
}

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
		xmlHttpRequest.open("POST","ppwsAchievePlace.do?methodName=getListOfTCId", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("projectid="+projectid);	
}



function getTrainList(){
	var  prID=$("#projectIDD").val();
	var tcId=$("#tcIdADD").val();
	
	if(prID!=0 && tcId!=0){
	var url="ppwsAchievePlace.do?methodName=getTCList&prID="+prID+"&tcId="+tcId;
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

 function handleHttpResponse(){
	
	 $('#tbody').html("");

	if (req.readyState == 4){	
    	var response = req.responseText; 
    	
    	var aa=JSON.parse(response);
    	var dynamic="";
    	for(var i=0;i<aa.length;i++){
    		
    		dynamic+="<tr>";
    		dynamic+="<td><input type='hidden' name='batchId' value='"+aa[i].bId+"'><input type='text'  readOnly='true' id='batchId' value='"+aa[i].batchId+"'><input type='hidden' name='id'  value='"+aa[i].id+"'></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='appointed'  id='appointedId' value='"+aa[i].appointed+"' onblur='isGraterThen35(this)' ></td>";
    		
    		dynamic+="<td><input type='text' style='width:70px;' readOnly='true' id='sumCatId' class='amount'  name='commenced_Total'  value='"+aa[i].commenced_Total+"' ></td>";
    	
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='commenced_Sc' id='commencedScId'  value='"+aa[i].commenced_Sc+"' onblur='isGraterThen35(this);calculateSum();'></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='commenced_St'  id='commencedStId'  value='"+aa[i].commenced_St+"' onblur='isGraterThen35(this);calculateSum();' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='commenced_Others' id='commencedOthersId'  value='"+aa[i].commenced_Others+"' onblur='isGraterThen35(this);calculateSum();' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='commenced_Women'  id='commencedWomenId'  value='"+aa[i].commenced_Women+"' onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='commenced_Minority'  id='commencedMinorityId'  value='"+aa[i].commenced_Minority+"' onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='commenced_Pwd'  id='commencedPwdId'  value='"+aa[i].commenced_Pwd+"' onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount'  name='candidate_Assessed'  id='candidateAssessedId'  value='"+aa[i].candidate_Assessed+"' onblur='isGraterThen35(this)' ></td>";
    		dynamic+="<td><input type='text' style='width:70px;' class='amount' name='candidate_Certified'   id='candidateCertifiedId'  value='"+aa[i].candidate_Certified+"' onblur='isGraterThen35(this)' ></td>";
    		dynamic+="</tr>";
    	}
    	$('#tbody').append(dynamic);
    }
}

 function checkValidation(train){
		
	  var url="ppwsAchievePlace.do?methodName=getValDone&train="+train;  
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
						   var app2 =bullEye[i].commencedTotal;
						   var batchId3 =bullEye[i].batchId;
						 						 
						   $("#abcd > tbody > tr").each(function()
							{
							 var batchid=$(this).find('td').eq(0).find("input[name='batchId']"); 
							
							if(batchid.val()==bullEye[i].batchId){
								
							    var appoint11 = $(this).find("td").eq(1).find("input");
							    var totalScStOth = $(this).find("td").eq(2).find("input");
							    var candidateAss11 = $(this).find("td").eq(9).find("input");
							    var CandCertified11 = $(this).find("td").eq(10).find("input");
							   
							    				    
							    
							   if(app2< appoint11.val()){
								  alert("Should be less than or equal to commencement total");
								   checkforerror++;
								   $(appoint11).focus();
								   return false;
								}
							   if(app2< totalScStOth.val()){
								   alert("Should be less than or equal to commencement total");
									   checkforerror++;
									   $(totalScStOth).focus();
									   return false;
									}	 
							   if(app2< candidateAss11.val()){
								   alert("Should be less than or equal to commencement total");
									   checkforerror++;
									   $(candidateAss11).focus();
									   return false;
									}	
							   if(app2< CandCertified11.val()){
								   alert("Should be less than or equal to commencement total");
									   checkforerror++;
									   $(CandCertified11).focus();
									   return false;
									}	  	  
							}
							});
						   if(checkforerror > 0){
					    		saveDataOrNotFlag++;
								break;
							}  
					   }
				 }
		   
		      if(saveDataOrNotFlag==0){
		       	var status=window.confirm('Are You Sure You Want tO Save ?');
		   		if(status==true){
		       		document.forms[0].action="ppwsAchievePlace.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
					document.forms[0].submit();
		   		 }
		       	}
		    }	
		
  
function save(){
 var x=checkPermissionForFormForInsert();
 if(x=='true'){
	var train=document.getElementById("tcIdADD").value;
	checkValidation(train); 
	}
}
</script>

<!--Main form section starts here-->
<html:form action="login/ppwsAchievePlace">

	<table width="100%" class="pageHeaderTable">
	<tr>
		<td align="center" class="pageHeader">MONITORING - PLACED</td>
	</tr>
	</table>
			
	<table width="100%" align="center" class="inputTBL">
<tr>
	
	<th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
			<td>	<html:select property="projectId" styleId="projectIDD"  styleClass="form-control"  onchange="getTrainingCenterList(this.value);getTrainList();">
								<html:option value="">-select-</html:option>
								<logic:present name="projectList">
									<logic:iterate id="project" name="projectList">
										<html:option value="${project.id}">${project.projectID}</html:option>
									</logic:iterate>
							</logic:present>
					</html:select></td>
	
	    <th><span class="input-group-addon" id="basic-addon1">Training Center ID</span></th>
		<td><html:select property="tcId" styleId="tcIdADD"  styleClass="form-control"  onchange="getTrainList(this.value);" >
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
			<table width="100%" align="center" id="abcd"  border="1"  style="border: 1px solid black;" >	
				<thead>	
				   <tr width="100%"   bgcolor="#dfdfdf">
                         
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Batch ID  <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Appointed<span class="text-error"></span></th>
                         <th  colspan="7"  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Candidate Placed <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Candidate Assessed<span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Candidate Certified<span class="text-error"></span></th>
                  </tr>
                  <tr >
                         <th><span class="text-error"></span></th>
                         <th><span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Total<span class="text-error">(SC+ST+OTH)</span> <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">SC <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">ST <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Others <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Women <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">Minority <span class="text-error"></span></th>
                         <th  style="border-left: thin solid; border-right: thin solid; border-top: thin solid; border-bottom: thin solid;">PWD <span class="text-error"></span></th>
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