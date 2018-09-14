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
 
function getDataList()
{
	var dis=document.getElementById("districtPanchayat").value;
	if((dis==null) || (dis==0)){

	alert("please select district");
	return false;
          }
	var status=window.confirm("Do you want to view the SGSY Bank Reports for the selected level? ");
	if(status==true){
			if(document.forms[0].districtCode !=null && document.forms[0].districtCode.value!='0'){									
				var districtName=document.forms[0].districtCode[document.forms[0].districtCode.selectedIndex].text;					
				document.forms[0].action = "bank.do?methodName=showViewBank"+"&districtName="+districtName+"&"+tokenParameter+"="+tokenValue;                                       
				document.forms[0].submit();			   
				}
			
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

</script>
<!--Main form section starts here-->

<body>
<html:form action="/login/bank">
 					
	 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	   <tr>
	   <td>
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="actionstxt"><span class="actionstxt"> SGSY BANK REPORT </span>
				    </td>
			 	</tr>
				<tr>
					<td height="20" class="formlabel"><span class="mandatory">*</span>
					<span class="normaltext"><bean:message key="lable.field.manadatory"></bean:message> </span></td>
				</tr> 
			</table>
			</td>
	   </tr>
        <tr>
            <td class="formlabel"><bean:message  key="login.districtPanchayat.label" /><span class="mandatory">*</span></td>
            <td> <html:select property="districtCode" styleId="districtPanchayat" >
             <html:option value="0"><bean:message key="label.selectDistrict" />
             </html:option>&nbsp;<logic:present name="districtList">
			 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
			 </logic:present>
			 </html:select>
            </td>
        </tr>
         
        
		<tr>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
		</tr>
		<tr>
            <td  height="30" colspan="1" align="center" > 
            <html:button styleClass="button" property="next" value="Submit" onclick="getDataList()"></html:button>
            <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td> 
  
        </tr> 
	 </table>
	  
</html:form>
</body>
<!--Main form section ends here-->