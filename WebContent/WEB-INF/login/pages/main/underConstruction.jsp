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
		<tr>
			<td >
				<img src="images/underConstruction.gif" />
		 	</td>
		 	<td>
		 	<span style="color: #96B566; font: 20px bold;">LINK IS UNDER CONSTRUCTION</span><br/><br/>
		 	<span>Sorry for any inconvenience</span>
		 	</td>
		</tr>
		<tr align="right">
			<td>
				<html:button styleClass="button" property="" onclick="closeReport()">
					<bean:message key="button.close" />
				</html:button>
			</td>
		</tr>
	</table> 
</body>

</html>
<!--Main form section ends here-->
