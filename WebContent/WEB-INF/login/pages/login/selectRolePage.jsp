<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.infotech.sgsy.util.Constants"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="title.selectRolePage" /></title>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
</head>
<!--script for menu-->
<script language=javascript>

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 
function getDataList(type)
{
	document.loginForm.action="login.do?methodName=getDataList&type="+type+"&"+tokenParameter+"="+tokenValue;
   	document.loginForm.submit();
} 
  

function submitPanchayat(){

	
	if(document.loginForm.panchayatRole.value =="0")
	{
		alert("<bean:message key="msg.selectRole" />");
		return false;
	}	
		
	<logic:present name="village">
	  
	if(document.loginForm.statePanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectState" />");
		return false;
	}
	if(document.loginForm.districtPanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectDistrict" />");
		return false;
	}
	if(document.loginForm.blockPanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectBlock" />");
		return false;
	}
	if(document.loginForm.villagePanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectVillage" />");
		return false;
	}
	
	if((document.loginForm.statePanchayat.value!="0" && document.loginForm.statePanchayat.value!=null))
	{
	
	var code = document.loginForm.statePanchayat.value;
	
	}
	if((document.loginForm.districtPanchayat.value!="0" && document.loginForm.districtPanchayat.value!=null))
	{
	
	var code = document.loginForm.districtPanchayat.value;
	
	}	
	if((document.loginForm.blockPanchayat.value!="0" && document.loginForm.blockPanchayat.value!=null))
	{
	
	var code = document.loginForm.blockPanchayat.value;
	
	}
	if((document.loginForm.villagePanchayat.value!="0" && document.loginForm.villagePanchayat.value!=null))
	{
	
	var code = document.loginForm.villagePanchayat.value;
	
	}
	</logic:present>
	
		
	 <logic:present name="block">
	 
	if(document.loginForm.statePanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectState" />");
		return false;
	}
	if(document.loginForm.districtPanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectDistrict" />");
		return false;
	}
	if(document.loginForm.blockPanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectBlock" />");
		return false;
	}
		
	if((document.loginForm.statePanchayat.value!="0" && document.loginForm.statePanchayat.value!=null))
	{
	
	var code = document.loginForm.statePanchayat.value;
	
	}
	if((document.loginForm.districtPanchayat.value!="0" && document.loginForm.districtPanchayat.value!=null))
	{
	
	var code = document.loginForm.districtPanchayat.value;
	
	}	
	if((document.loginForm.blockPanchayat.value!="0" && document.loginForm.blockPanchayat.value!=null))
	{
	
	var code = document.loginForm.blockPanchayat.value;
	
	}
	
	</logic:present>
	
	<logic:present name="district">
	
	if(document.loginForm.statePanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectState" />");
		return false;
	}
	if(document.loginForm.districtPanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectDistrict" />");
		return false;
	}
		
	if((document.loginForm.statePanchayat.value!="0" && document.loginForm.statePanchayat.value!=null))
	{
	
	var code = document.loginForm.statePanchayat.value;
	
	}
	if((document.loginForm.districtPanchayat.value!="0" && document.loginForm.districtPanchayat.value!=null))
	{
	
	var code = document.loginForm.districtPanchayat.value;
	
	}	
	
	</logic:present>
	
	 <logic:present name="state">
	
	if(document.loginForm.statePanchayat.value =="0")
	{
		alert("<bean:message key="msg.selectState" />");
		return false;
	}
		
	if((document.loginForm.statePanchayat.value!="0" && document.loginForm.statePanchayat.value!=null))
	{
	
	var code = document.loginForm.statePanchayat.value;
	
	}	
	
	</logic:present>
	 
	 <logic:present name="myas">
	
	var code = document.loginForm.entityCode.value;
		 
	 </logic:present>
	
    var status=true;
    //window.confirm("Are you sure you want to submit ");
	if(status==true){
	document.loginForm.action="login.do?methodName=loginWithSRL&panchayatEntity="+code+"&"+tokenParameter+"="+tokenValue;
	document.loginForm.submit();
	} 
	
}

</script>
<!--Main form section starts here-->

<body>
<html:form action="login/login">
<table>
<tr>
	<td class="mainpaneheading"><bean:message  key="login.selectRole.label" /></td>
</tr>
</table>
<table align="center">
<tr>
<td class="mainpanebgcolor" align="center" valign="top">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
						
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
					
		<tr>
            <td class="formtitle"><bean:message  key="login.panchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="panchayatRole" styleId="panchayatRole" onchange="getDataList('N')">
			 <html:option value="0"><bean:message key="label.selectRole" /></html:option>
			 <html:options collection="getRoleList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <logic:present name="myas">
             <html:hidden property="entityCode"></html:hidden>
        </logic:present>
        
        <logic:present name="state">
        <tr>
            <td class="formtitle"><bean:message  key="login.statePanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="statePanchayat" styleId="statePanchayat" >
			 <html:option value="0"><bean:message key="label.selectState" /></html:option>
			 <html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
        </logic:present>
        
        <logic:present name="district">
        <tr>
            <td class="formtitle"><bean:message  key="login.statePanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="statePanchayat" styleId="statePanchayat" onchange="getDataList('DT')">
			 <html:option value="0"><bean:message key="label.selectState" /></html:option>
			 <html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
          <tr><td>&nbsp;</td></tr>
        <tr>
            <td class="formtitle"><bean:message  key="login.districtPanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="districtPanchayat" styleId="districtPanchayat" >
			 <html:option value="0"><bean:message key="label.selectDistrict" /></html:option>
			 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
        </logic:present>
               
        <logic:present name="block">
        <tr>
            <td class="formtitle"><bean:message  key="login.statePanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="statePanchayat" styleId="statePanchayat" onchange="getDataList('ZP')">
			 <html:option value="0"><bean:message key="label.selectState" /></html:option>
			 <html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
          <tr><td>&nbsp;</td></tr>
         <tr>
            <td class="formtitle"><bean:message  key="login.districtPanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="districtPanchayat" styleId="districtPanchayat" onchange="getDataList('BP')">
			 <html:option value="0"><bean:message key="label.selectDistrict" /></html:option>
			 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
         <tr><td>&nbsp;</td></tr>
        <tr>
            <td class="formtitle"><bean:message  key="login.blockPanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="blockPanchayat" styleId="blockPanchayat" >
			 <html:option value="0"><bean:message key="label.selectBlock" /></html:option>
			  <html:options collection="blockList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
        </logic:present>
        
        <logic:present name="village">
        <tr>
            <td class="formtitle"><bean:message  key="login.statePanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="statePanchayat" styleId="statePanchayat" onchange="getDataList('ZP')">
			 <html:option value="0"><bean:message key="label.selectState" /></html:option>
			 <html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
          <tr><td>&nbsp;</td></tr>
        <tr>
            <td class="formtitle"><bean:message  key="login.districtPanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="districtPanchayat" styleId="districtPanchayat" onchange="getDataList('BP')">
			 <html:option value="0"><bean:message key="label.selectDistrict" /></html:option>
			 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
         <tr><td>&nbsp;</td></tr>
        <tr>
            <td class="formtitle"><bean:message  key="login.blockPanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="blockPanchayat" styleId="blockPanchayat" onchange="getDataList('VP')" >
             <html:option value="0"><bean:message key="label.selectBlock" /></html:option>
             <html:options collection="blockList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
         <tr><td>&nbsp;</td></tr>
        <tr>
            <td class="formtitle"><bean:message  key="login.villagePanchayat.label" /><span class="mandatory">*</span></td>
            <td><html:select property="villagePanchayat" styleId="villagePanchayat" >
			 <html:option value="0"><bean:message key="label.selectVillage" /></html:option>
			 <html:options collection="villageList" property="propertyCode" labelProperty="propertyValue"/>
			 </html:select>
             </td>
        </tr>
        </logic:present>
        
		<tr>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
		</tr>
		
		 <tr>
            <td height="40" colspan="2" align="center" > 
            <html:button styleClass="button" property="next" value="Submit" onclick="submitPanchayat()"></html:button>
            </td>
        </tr>
			</table>
						
			
			</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
</html:form>
</body>
<!--Main form section ends here-->