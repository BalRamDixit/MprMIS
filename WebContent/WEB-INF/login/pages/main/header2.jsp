 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<% 

ServletContext context = getServletContext(); 


%>
  <script language="javascript">
  
  var tokenParameter='reqtrack';
  var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 


 
// disable the mouse right button
   
   	var message="Function Disabled!";

	function clickIE4()
	{
		if (event.button==2)
		{
			//alert(message);
			return false;
		}
	}

	function clickNS4(e)
	{
		if (document.layers||document.getElementById&&!document.all)
		{
			if (e.which==2||e.which==3)
			{
				//alert(message);
				return false;
			}
		}
	}

	if (document.layers)
	{
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown=clickNS4;
	}
	else if (document.all&&!document.getElementById)
	{
		document.onmousedown=clickIE4;
	}

	document.oncontextmenu=new Function("return false")

</script>
  
<body  >
<html:form action="login/language">
<!-- <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="globalnav">
    <tr>
      <td width="760"></td>
    </tr>
    <tr>
       <td> 
      </td>
    </tr>
  </table> -->
  </html:form>
</body>  
  