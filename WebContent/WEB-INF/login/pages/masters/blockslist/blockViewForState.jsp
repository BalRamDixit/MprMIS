<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>

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
 
function getDataList()
{
	var dis=document.getElementById("districtPanchayat").value;




	if((dis==null) || (dis==0)){

		alert("PLEASE SELECT DISTRICT");
		return false;
	}
	
	document.forms[0].action="block.do?methodName=showView"+"&"+tokenParameter+"="+tokenValue;
   	document.forms[0].submit();
} 
function closePage()
{
	var status=window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
	document.forms[0].action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
 	document.forms[0].submit();
	}
}

</script>
<!--Main form section starts here-->

<body>
<html:form action="/login/block">
 		 	
 		  <TABLE align="center" width="100%">
	      
		<tr>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
		</tr>
	    
	    <tr>
			<td align="center" class="actionstxt"><span class="actionstxt"> DISTRICT WISE BLOCK LIST REPORT </span>
			</td>
		</tr>
		

		</TABLE> 	
 		 
 		  <TABLE align="center" width="100%">
	    
	    <tr>
            <td class="formlabel"  align="center"  >
            <b><bean:message  key="login.districtPanchayat.label" /></b><span class="mandatory">*</span></td>
             
            <td   class="formlabel"  > <html:select property="districtCode" styleId="districtPanchayat" onchange="getDataList()">
			 <html:option value="0"><bean:message key="label.selectDistrict" /></html:option>
			
			<logic:present name="districtList">
			 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
			 </logic:present>
			 			 
			 </html:select>
            </td>
        </tr>

		</TABLE> 	
 		 		
 		 		
	 <table   border="0" cellspacing="0" cellpadding="0" align="center">
	     
		 
         
        
		<tr>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
		</tr>
		<tr>
            <td height="30" colspan="1" align="center" > 
            
            
          
            </td>
        </tr> 
        </table>
	 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	 <logic:present name="<%=Constants.COLLECTION_GETBLOCK%>">
	  <c:set var="cnt" value="0"></c:set>
		
	 
	    
	    <tr><td class="formlabel" align="center" colspan="5"><b><%=application.getAttribute("label.srno").toString()%></b></td>
	    <td class="formlabel" align="center"><b><%=application.getAttribute("label.blockName").toString()%></td></b>
		</tr>
	    <logic:iterate name="<%=Constants.COLLECTION_GETBLOCK%>" id="blockID">
	    <c:set var="cnt" value="${cnt+1}"></c:set>
	     <tr><td class="formlabel" align="center" colspan="5">${cnt}</td>
	      <td class="formlabel" align="center"> ${blockID.blockName}</td>
	    </tr>
	    
	    
	    
	    </logic:iterate>
	      
	     </logic:present>
	     </td></tr>
			
					
	    </table>
	    
	    <TABLE align="center" width="100%">
	      
		<tr>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
		</tr>
	    
	    <tr>
						<td width="40%" height="40" colspan="2" align="center">
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
					</tr></TABLE>
	    
</html:form>

</body>
<!--Main form section ends here-->