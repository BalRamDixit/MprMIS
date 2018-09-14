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
		//document.forms[0].action = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
		document.userForm.action = "userAction1.do?methodName=showUsrView&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	   }
}

function getDistrictDetails(){

	var stateCode=document.forms[0].stateCode.value;
	document.forms[0].action="userAction1.do?methodName=getDistrictDetails&stateCode="+stateCode+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();

}
function getDistrictUserList(distCode,distName){
 

	// alert(distCode+'== dn== '+distName); 
	 
	var stateName=document.forms[0].stateCode[document.forms[0].stateCode.selectedIndex].text;
	
	//alert(stateName); 
	 
	document.forms[0].action="userAction1.do?methodName=getUserListForReport&level=3&entityCode="+distCode+"&stateName="+stateName+"&"+tokenParameter+"="+tokenValue+"&districtName="+distName;
	document.forms[0].submit();

}

//-->
</script>
<body>

<html:form action="login/userAction1">

<table width="100%">
	<tr>
		<td class="pageHeader" align="center" >District User List</td> 		 
	</tr>
	<tr><td align="center">
	   <table width="100%">	
	   	<tr >
					<td  align="right"><bean:message key="lable.user.selectState"/><span class="mandatory">*</span></td>
					<td >
						<html:select property="stateCode" styleId="stateCode" onchange="getDistrictDetails();">
							<html:option value=""><bean:message key="label.selectState" /></html:option>
								<logic:present name="stateList">
									<html:options collection="stateList" property="stateCode" labelProperty="stateName"/>
								</logic:present>
					</html:select></td>
		</tr>						 
		</table>
		 <logic:present name="<%=Constants.DISTRICT_LIST %>">		
		<table width="80%" class="inputTBL">	
		<tr>		
			<th>Sl.No.</th>
			<th><bean:message  key="label.district" /></th>
			<th>Number Of District User</th>
	   </tr>
		<logic:iterate  id="districtDetails"  name="<%= Constants.DISTRICT_LIST %>" indexId="index" >	
		<tr>
			<td>${index+1}</td> 
			<td align="left">
			<bean:write name="districtDetails"  property="districtName" />
			</td>
		    <td align="center">		    
		    <a href="javascript:getDistrictUserList('<bean:write name='districtDetails' property='districtCode'/>','<bean:write name='districtDetails' property='districtName'/>' ) ">
		    	<bean:write name="districtDetails"  property="noOfUsers" />
		    </a>
		    </td>
	   </tr>			
		</logic:iterate>		 
 	</table>	
  	</logic:present>
	  <table width="100%">			
			<tr>
				<td align="center"><html:button styleClass="button" property="" onclick="closeReport()"><bean:message  key="button.close" /></html:button></td>
			</tr>	
		</table>
	</td></tr>
</table>  
</html:form> 

