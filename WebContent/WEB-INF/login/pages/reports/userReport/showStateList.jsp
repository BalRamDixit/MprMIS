<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="com.infotech.sgsy.util.Constants" %>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />


<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="javascript" src="javaScript/common.js"></script>
<script type="text/javascript">
<!-- 


var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;

function getStateDetails(){
	
	var allReport='';
	
	if(document.forms[0].reportType[0].checked == true){		
		 allReport = document.forms[0].reportType[0].value;
		 
	}
	
	if(document.forms[0].reportType[1].checked== true){
		 allReport = document.forms[0].reportType[1].value;
	}
	
	if(document.forms[0].reportType[2].checked== true){
		 allReport = document.forms[0].reportType[2].value;
	}
	
	if(document.forms[0].reportType[3].checked== true){
		 allReport = document.forms[0].reportType[3].value;
	}	
	
	if(allReport == "all"){
		document.getElementById('yearName').value =document.getElementById('finYr').options[document.getElementById('finYr').selectedIndex].text;
		document.forms[0].action="userAction1.do?methodName=getStateDetails";
		document.forms[0].submit();
		document.getElementById('yearId').className="";
		document.getElementById('previousReportFor').value=allReport;
	}	
	
	
	
}


function getPreviousReportType(){
	
	var allReport='';
	
	if(document.forms[0].reportType[0].checked == true){		
		 allReport = document.forms[0].reportType[0].value;
		 
	}
	
	if(document.forms[0].reportType[1].checked== true){
		 allReport = document.forms[0].reportType[1].value;
	}
	
	if(document.forms[0].reportType[2].checked== true){
		 allReport = document.forms[0].reportType[2].value;
	}
	
	if(document.forms[0].reportType[3].checked== true){
		 allReport = document.forms[0].reportType[3].value;
	}	
	
	if(allReport != document.getElementById('previousReportFor').value){
		alert("allReport :" +allReport );
		alert("previousReportFor  :" +document.getElementById('previousReportFor').value)
	 	alert("Please check report");
	 	return false;
	}
	
}

function closeReport(){
	  var status=window.confirm("Are You Sure You want to close the form ?");
	  if(status==true){
		document.forms[0].action = "userAction1.do?methodName=showUsrView"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}
}

function checkReport(obj){

 if(obj.value == "all"){
 	 document.getElementById('yearId').className=""; 	 
 	 document.getElementById('stateTrId').className="hide";
 	 document.getElementById('districtTrId').className="hide";	  
	 document.getElementById('blockTrId').className="hide";
 }
 
 if(obj.value == "stateWise"){
 	 document.getElementById('yearId').className=""; 	 
 	 document.getElementById('stateTrId').className="";
 	 document.getElementById('districtTrId').className="hide";	  
	 document.getElementById('blockTrId').className="hide";
 }
 
 if(obj.value == "districtWise"){
 	 document.getElementById('yearId').className=""; 	 
 	 document.getElementById('stateTrId').className="";
 	 document.getElementById('districtTrId').className="";	  
	 document.getElementById('blockTrId').className="hide";
 }
 
 if(obj.value == "blockWise"){
  	 document.getElementById('yearId').className="";
 	 document.getElementById('stateTrId').className="";
 	 document.getElementById('districtTrId').className="";
 	 document.getElementById('blockTrId').className="";
 	 
 }
 	document.getElementById('stateCode').value="";
	document.getElementById('finYr').value="";
	document.getElementById('districtCode').value="";
	document.getElementById('blockCode').value="";
	
	
		
}

function getDistrict(val){ 

	var allReport='';
	
	if(document.forms[0].reportType[1].checked == true){	
		 allReport = document.forms[0].reportType[1].value;		 
	}
	
	if(allReport == "stateWise"){
		var stateName =document.getElementById('stateCode').options[document.getElementById('stateCode').selectedIndex].text;
		var yearName = document.getElementById('finYr').options[document.getElementById('finYr').selectedIndex].text;
		
		document.forms[0].action="userAction1.do?methodName=getStateWiseDetails&stateName="+stateName+"&yearName="+yearName;
		document.forms[0].submit();
		
	}else{
		clearMultiSelect('districtCode');
		addOption(document.getElementById('districtCode'),'Select','' );			     
		getDetailsByAjax(document.getElementById('stateCode'),document.getElementById('districtCode'),'GETDISTRICTSBYSTATE');
	}
	 
	
}

function getBlock(val){ 
	
	var allReport='';
	
	if(document.forms[0].reportType[2].checked == true){	
		 allReport = document.forms[0].reportType[2].value;		 
	}
	
	if(allReport == "districtWise"){
		var stateName =document.getElementById('stateCode').options[document.getElementById('stateCode').selectedIndex].text;
		var yearName = document.getElementById('finYr').options[document.getElementById('finYr').selectedIndex].text;
		var districtName = document.getElementById('districtCode').options[document.getElementById('districtCode').selectedIndex].text;
		
		document.forms[0].action="userAction1.do?methodName=getDistrictWiseDetails&stateName="+stateName+"&yearName="+yearName+"&districtName="+districtName;
		document.forms[0].submit();
		
	}else{
		clearMultiSelect('blockCode');
		addOption(document.getElementById('blockCode'),'Select','' );			     
		getDetailsByAjax(document.getElementById('districtCode'),document.getElementById('blockCode'),'GETBLOCKBYDISTRICT');
	}

}

function getBlock(val){ 
	
	var allReport='';
	
	if(document.forms[0].reportType[2].checked == true){	
		 allReport = document.forms[0].reportType[2].value;		 
	}
	
	if(allReport == "districtWise"){
		var stateName =document.getElementById('stateCode').options[document.getElementById('stateCode').selectedIndex].text;
		var yearName = document.getElementById('finYr').options[document.getElementById('finYr').selectedIndex].text;
		var districtName = document.getElementById('districtCode').options[document.getElementById('districtCode').selectedIndex].text;
		
		document.forms[0].action="userAction1.do?methodName=getDistrictWiseDetails&stateName="+stateName+"&yearName="+yearName+"&districtName="+districtName;
		document.forms[0].submit();
		
	}else{
		clearMultiSelect('blockCode');
		addOption(document.getElementById('blockCode'),'Select','' );			     
		getDetailsByAjax(document.getElementById('districtCode'),document.getElementById('blockCode'),'GETBLOCKBYDISTRICT');
	}

}

function getGroup(){ 
	
	var allReport='';
	
	if(document.forms[0].reportType[3].checked == true){	
		 allReport = document.forms[0].reportType[3].value;		 
	}
	
	if(allReport == "blockWise"){
	
		var stateName =document.getElementById('stateCode').options[document.getElementById('stateCode').selectedIndex].text;
		var yearName = document.getElementById('finYr').options[document.getElementById('finYr').selectedIndex].text;
		var districtName = document.getElementById('districtCode').options[document.getElementById('districtCode').selectedIndex].text;
		var blockName = document.getElementById('blockCode').options[document.getElementById('blockCode').selectedIndex].text;
		
		document.forms[0].action="userAction1.do?methodName=getBlockWiseDetails&stateName="+stateName+"&yearName="+yearName+"&districtName="+districtName+"&blockName="+blockName;
		document.forms[0].submit();
		
	}

}



function getDetailsByAjax(primaryObject,dependantObject,funcParam){   	 
   	 
	 controlName=dependantObject.name;	
	 	 
	 http_request = createAjaxRequest();
	
	 primaryObjValue = primaryObject.value;
	
	 if(primaryObjValue!="Select"){
		if(primaryObjValue== "Select,Select"){
			return;
		}
	}								
	urlString = "<%=request.getContextPath()%>/DataPopulatorServlet?"
	+"controlName="+controlName
	+"&primaryCode="+primaryObjValue				
	+"&funcParam="+funcParam	
	+"&stateCode="+document.getElementById('stateCode').value;		
	
	http_request.open('POST',urlString, true);
	http_request.send(null);
	http_request.onreadystatechange = popDetails;		
			
	function popDetails(){			//function to be called onchange
		 
		if(http_request.readyState == 4){				
		 	evalScript(http_request.responseText);		
		}					 
	}	
}


function getStateUserList(stateCode,stateShortName,stateName){
 

//alert(stateCode+' ss '+stateShortName+' sn '+stateName); 
 
document.forms[0].action="userAction1.do?methodName=getUserListForReport&level=2&entityCode="+stateCode+"&stateShortName="+stateShortName+"&stateName="+stateName+"&"+tokenParameter+"="+tokenValue;
document.forms[0].submit();

}









//-->
</script>
<body>

<html:form action="login/userAction1">

<table width="100%">
	<tr>
		<td align="center" class="pageHeader">State User Information Report</td> 		 
	</tr>
	<tr><td align="center">
	<logic:present name="stateList">
	<table  width="80%" class="inputTBL">	
		<tr>
			<th>Sl.No.</th>
			<th><bean:message  key="label.state" /></th>
			<th>Number Of State Users</th>		  
	   </tr>
		 
		<logic:iterate  id="stateDetails"  name="stateList"  indexId="index">			
		<tr class="row1">
			<td>${index + 1}</td>
			<td align="left">
			 	<bean:write name="stateDetails"  property="stateName" />
		 	</td>			
			 <td align="center">
			 <a href="javascript:getStateUserList('<bean:write name="stateDetails" property="stateCode"/>','<bean:write name="stateDetails" property="stateShortName"/>','<bean:write name="stateDetails" property="stateName"/>') ">
		 		 <bean:write name="stateDetails"  property="noOfUsers" />
			 </a>			 
			 </td>
	   </tr>			
		</logic:iterate>	
		</table>	
		</logic:present>
	  <table width="100%">			
			<tr>			
				<td align="center">
					<html:button styleClass="button" property="" onclick="closeReport()"><bean:message  key="button.close" /></html:button>
			</tr>	
		</table>
 </td></tr>
</table>	  
</html:form> 

</body>
</html>
