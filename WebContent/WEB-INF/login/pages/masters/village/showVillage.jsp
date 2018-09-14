<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
</head>
<script language=javascript>

 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 
var levelName;


 
function clearUser(){ 

    var status=window.confirm("<bean:message key="msg.clear" />");
	if(status==true){
	document.forms[0].action = "sgsyCommittee.do?methodName=showModify&"+tokenParameter+"="+tokenValue;
  	//document.forms[0].submit();
	} 
}
function closePage()
{
	var status=window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
	document.forms[0].action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
 	document.forms[0].submit();
	}
}
 
   function viewReports(){
   

	if(document.getElementById('levelCode').value==""){
		alert("Please select the level where you want to view.");
		return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	
	if(lavel=='3'){
		if(document.getElementById('stateCode').value=="0"){
			alert("<bean:message key="msg.selectState" />");
		return;
		}
		if(document.getElementById('districtCode').value=="0"){
		alert("<bean:message key="msg.selectDistrict" />");
		return;
		}
	}
	if(lavel=='4'){
		if(document.getElementById('stateCode').value=="0"){
			alert("<bean:message key="msg.selectState" />");
		return;
		}
		if(document.getElementById('districtCode').value=="0"){
		alert("<bean:message key="msg.selectDistrict" />");
		return;
		}
		if(document.getElementById('blockCode').value=="0"){
		alert("<bean:message key="msg.selectBlock" />");
		return;
		}
	} 
    var blockName=null;
	var stateName=document.forms[0].stateCode[document.forms[0].stateCode.selectedIndex].text;
	var districtName=document.forms[0].districtCode[document.forms[0].districtCode.selectedIndex].text;
	                                                
	 blockName=document.forms[0].blockCode[document.forms[0].blockCode.selectedIndex].text;
	
	
    var status=window.confirm("Do you want to view the SGSY Village Reports for the selected level? ");
	if(status==true){
		document.forms[0].action = "village.do?methodName=showVillageList&"+tokenParameter+"="+tokenValue+"&stateName="+stateName+"&districtName="+districtName+"&blockName="+blockName;
	    document.forms[0].submit();
	} 
}
   function getDataSList(FLAG)
   {
   	
   	if(FLAG=='LEVEL'  ){
   		if(document.forms[0].stateCode!=null)
     				 document.forms[0].stateCode.value="";
   		if(document.forms[0].districtCode!=null)
			 document.forms[0].districtCode.value="";
   				//document.forms[0].blockCode.value="";
   	}
   	
   	document.forms[0].action="village.do?methodName=showVillage"+"&"+tokenParameter+"="+tokenValue;
      	document.forms[0].submit();
   	
   }
</script>


<body>
<html:form action="/login/village">

	
	<div class="mainpane">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="actionstxt"><span class="actionstxt"> SGSY VILLAGE REPORT </span>
				    </td>
			 	</tr>
				<tr>
					<td height="20" class="formlabel"><span class="mandatory">*</span>
					<span class="normaltext"><bean:message key="lable.field.manadatory"></bean:message> </span></td>
				</tr> 
			</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr >
					<td width="50%" class="formlabel">View As<span class="mandatory">*</span></td>
					<td class="formlabel">
					<html:select property="levelCode" styleId="levelCode" onchange="getDataSList('LEVEL')">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
					</html:select></td>
				</tr>	
				<logic:present name="levelList">
				<tr> 
				<logic:present name="stateList">
                    <td class="formlabel">State<span class="mandatory">*</span></td>
            <td><html:select property="stateCode" styleId="stateCode" onchange="getDataSList('K')">
			 <html:option value="0"><bean:message key="label.selectState" /></html:option>
			  	<logic:present name="stateList"> 
			 <html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
		 </logic:present>
			 </html:select>
             </td>
              </logic:present>
        </tr>
				 <tr> <logic:present name="districtList">
                    <td class="formlabel">District<span class="mandatory">*</span></td>
            <td><html:select property="districtCode" styleId="districtCode" onchange="getDataSList('K')">
			 <html:option value="0"><bean:message key="label.selectDistrict" /></html:option>
			  	<logic:present name="districtList"> 
			 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
		 </logic:present>
			 </html:select>
             </td>
              </logic:present>
        </tr>
				 <tr><logic:present name="blockslist">
                    <td class="formlabel">Block<span class="mandatory">*</span></td>
            <td><html:select property="blockCode" styleId="blockCode"  >
			 <html:option value="0"><bean:message key="label.selectBlock" /></html:option>
			  	<logic:present name="blockslist"> 
			 <html:options collection="blockslist" property="propertyCode" labelProperty="propertyValue"/>
		 </logic:present>
			 </html:select>
             </td>
       </logic:present>
		        
		   </logic:present>     
		      
				  
			</table>
			 </td></tr>
			
					<tr>
						<td height="40" colspan="2" align="center">
						<html:button styleClass="button" property="next" value="View Village" onclick="viewReports()"/>					
						<html:button styleClass="button" property="Clear" value="Clear" onclick="getDataSList('LEVEL')"/>
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
					</tr>
	</table>
  
	</div>
</html:form>
 
</body>
 
</html:html>
