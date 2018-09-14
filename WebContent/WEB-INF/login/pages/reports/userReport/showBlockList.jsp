<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@ page import="com.infotech.sgsy.util.Constants" %>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />

<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css"></link>
<script language="javascript" type="text/javascript" src="javaScript/dhtmlgoodies_calendar.js"></script>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
<!-- 

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 

function closeReport(){
	  var status=window.confirm("Are You Sure You want to close the form ?");
	  if(status==true){
	  
	   document.forms[0].action = "userAction1.do?methodName=showUsrView"+"&"+tokenParameter+"="+tokenValue;
	   document.forms[0].submit();
		 
	   }
}

function getDistrictDetails(){

	var stateCode=document.forms[0].stateCode.value;
	document.forms[0].action="userAction1.do?methodName=getDistrictDetails&flag=1&stateCode="+stateCode+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();

}

function getBlockDetails(){

	var stateCode=document.forms[0].stateCode.value;
	var distCode=document.forms[0].districtCode.value;
	document.forms[0].action="userAction1.do?methodName=getBlockDetails&stateCode="+stateCode+"&"+tokenParameter+"="+tokenValue+"&districtCode="+distCode;
	document.forms[0].submit();

}


function getDistrictUserList(blockCode,blockName){
 

	// alert(distCode+'== dn== '+distName); 
	 
	var stateName=document.forms[0].stateCode[document.forms[0].stateCode.selectedIndex].text;
	var distName=document.forms[0].districtCode[document.forms[0].districtCode.selectedIndex].text;
	//alert(stateName); 
	 
	document.forms[0].action="userAction1.do?methodName=getUserListForReport&level=4&entityCode="+blockCode+"&stateName="+stateName+"&"+tokenParameter+"="+tokenValue+"&districtName="+distName+"&blockName="+blockName;
	document.forms[0].submit();

}

//-->
</script>
<body>
<html:form action="login/userAction1">
<table width="100%">
<tr>
	<td class="pageHeader" width="100%" align="center" >Block User List</td> 		 
</tr>
<tr><td align="center">	
	<table width="90%" id="table2" class="inputTBL">
		<tr>
			<th><bean:message key="lable.user.selectState"/><span class="mandatory">*</span></th>
			<td>
				<html:select property="stateCode" styleId="stateCode" onchange="getDistrictDetails();">
					<html:option value=""><bean:message key="label.selectState" /></html:option>
					<logic:present name="stateList">
						<html:options collection="stateList" property="stateCode" labelProperty="stateName"/>
					</logic:present>
				</html:select></td>
		</tr>		
		 <tr>
			<th><bean:message key="lable.user.selectDistrict"/><span class="mandatory">*</span></th>
			<td>
				<html:select property="districtCode" styleId="districtCode" onchange="getBlockDetails()" >
					<html:option value=""><bean:message key="label.selectDistrict" /></html:option>
					<logic:present name="districtList">
					 <html:options collection="districtList" property="districtCode" labelProperty="districtName"/>
					 </logic:present>
					</html:select></td>
		</tr>
	</table>
			
	<logic:present name="<%=Constants.BLOCKLIST %>">		
	<table width="90%" id="table2" class="inputTBL" >	
		<tr>		
			<th align="left"><bean:message  key="label.block" /></th>
			<th>Number Of Block User</th>
	   </tr>
		<logic:iterate  id="districtDetails"  name="<%= Constants.BLOCKLIST %>" >
		<tr class="row1">
			<td align="left">
			<bean:write name="districtDetails"  property="blockName" />
			</td>
		    <td align="center">
		    
		    <a href="javascript:getDistrictUserList('<bean:write name='districtDetails' property='blockCode'/>','<bean:write name='districtDetails' property='blockName'/>' ) ">
		    	<bean:write name="districtDetails"  property="noOfUsers" />
		    </a>
		    </td> 
	   </tr>			
		</logic:iterate>	
	 
 	</table>	
  	</logic:present>
 </td>
 </tr>
 <tr>
 	<td> 					
		<html:button styleClass="button" property="" onclick="closeReport()"><bean:message  key="button.close" /></html:button>
	</td>	
</tr>	
		</table>
</html:form> 
</body>
