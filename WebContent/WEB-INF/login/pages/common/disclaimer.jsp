<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%String loggedFlag="";

if(request.getAttribute("logFlag")!=null){
	
	loggedFlag=(String)request.getAttribute("logFlag");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javaScript/md5.js"></script> 
<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;
function closeReport(){
	 window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
</script>
</head>

<!--Main form section starts here-->

<body>

<div class="footer">
    	<table width="945" border="0" align="center" cellpadding="0" >
      		<tr>
        		<td align="center"> </td>
      		</tr>
      		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
    	</table>
  	</div>

<table width="945" border="0" cellpadding="0" cellspacing="0">
		<tr> <td align="center" class="pageHeader"> Disclaimer <br></br></td>
		</tr>
		</table>	
<table>		
<tr>
<td><b>While every care has been taken to provide correct and updated information, Ministry of Rural Development is not responsible for any error / omission or delay in updating of data / information etc. 
The visitors to the site are therefore, requested to contact the Ministry for details and latest information. 
Links to other websites / webpages of Government Departments / Organisations have also been provided. 
The content of these websites are owned by the respective organisations and they may be contacted for any further information or suggestion.<br></br>
</b>
</td>
</tr>
</table>

<div align="center">
<table>
<tr>
<td>

				<html:button styleClass="button" property="" onclick="closeReport()">
				<bean:message key="button.close" />
				</html:button>
				
			</td>
		</tr>
</table>
</div>

</body>

</html>

