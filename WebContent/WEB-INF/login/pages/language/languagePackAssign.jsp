<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants;"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="Javascript1.1" src="javaScript/common.js"></script>
<title></title>
</head>
<script type="text/javascript">
 
 window.onbeforeunload=confirmExit;
function confirmExit(){
		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 
</script>
<body>
<html:form action="login/language">

	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td align="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr align="center">
	                <td class="mainpaneheading" colspan="2">
	                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 
							
							<tr align="center">
								<td align="center" class="actionstxt">
								<span class="actionstxt"><bean:message  key="label.assign"/></span>
								<bean:message  key="label.languagePack" /></td>
								<html:hidden property="informationDialog"></html:hidden>
								<html:hidden property="informationDialogText"></html:hidden>
								<html:hidden property="informationDialogHeader"></html:hidden>
							</tr>
							
							<tr>
								<td height="20" class="formlabel"><span class="mandatory">*</span>
								<span class="normaltext"><bean:message key="label.field.mandatory"></bean:message></span></td>
							</tr>
						</table>
	                </td>
             	 </tr>
           
                <tr>
                  <td  class="formsubheading"><bean:message key="label.enterLangageDetail"/></td>
                </tr>
                 
                 <tr>
                  <td class="formsubheading" width="50%"><bean:message key="label.languageName"/></td>
                  <td class="formlabel" width="50%">
                  	<html:select property="language" onchange="getDetails();">
                  		<html:option value=""><bean:message key="label.select"/></html:option>
                  		<logic:present name="allLanguageList">
								<html:options collection="allLanguageList" property="languageshortname" labelProperty="language"/>
						</logic:present>
                  	</html:select>
                  </td>
                </tr>
                    
                 <tr>
                 	<td  class="formsubheading" align="center" colspan="2"><bean:message key="label.languageShortName"/></td>
                 </tr>
                 
                  <tr>
                		<td class="formlabel" width="50%" colspan="2" >
                  		 	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	                   	 		<logic:present name="<%=Constants.STATE_LIST %>">
	                   	 			<logic:iterate id="stList" name="<%=Constants.STATE_LIST %>">
	                   	 			<%boolean bool=false; %>
	                    		 		<tr>
	                    		 			<td class="formlabel" width="50%" align="center">
	                    		 				<bean:write name="stList" property="stateName"/>
	                    		 			</td>
	                    		 			
	                    		 			<td class="formlabel" width="50%" align="center">
	                    		 				<logic:notEmpty name="SETSTATELIST">
		                    		 				<logic:iterate id="selStList" name="SETSTATELIST">
		                    		 					<logic:equal value="${stList.stateShortName}" name="selStList" property="propertyValue">
		                    		 						<%
		                    		 						bool=true; 
		                    		 						%>
		                    		 					</logic:equal >
		                    		 				</logic:iterate>
		                    		 			</logic:notEmpty>
		                    		 			<%if(bool){ %>
		                    		 				<input type="checkbox"  name="writeAccess" checked="checked"  value="<bean:write name="stList" property="stateShortName"/>"/>
		                    		 			<%}else{ %>
		                    		 				<input type="checkbox"  name="writeAccess"  value="<bean:write name="stList" property="stateShortName"/>"/>
		                    		 			<%} %>
	                    		 				
	                    		 			</td>
	                    		 		</tr>
			                     	 </logic:iterate>
			                      </logic:present>
		                      </table>                     		 
                  		 </td>
                 </tr>
                  <tr>
                    <td height="40" colspan="2" align="center" >
                     <html:button styleClass="button" property="saveButton" onclick="save()"><bean:message key="button.save" /></html:button>
                     <html:button styleClass="button" property="clearButton" onclick="clearField()"><bean:message  key="button.clear" /></html:button>
                     <html:button styleClass="button" property="closeButton" onclick="closePage(tokenParameter,tokenValue)" ><bean:message  key="button.close" /></html:button>
                 	</td>
                  </tr>
			</table>
			
			</td>
		</tr>
	</table>
	
</html:form>
</body>
</html>
<script type="text/javascript">
	function getDetails(){
	
		submitForm("language.do?methodName=showAdd"+"&"+tokenParameter+"="+tokenValue);
	}
	function save(){
	
		submitForm("language.do?methodName=save"+"&"+tokenParameter+"="+tokenValue);
	}
</script>