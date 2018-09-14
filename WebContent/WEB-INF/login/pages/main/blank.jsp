<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Blank Page</title>

<!--script for menu-->

<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
  
</script>

<!--Main form section starts here-->
<html:form action="login/login">
<table >
<tr>
<td class="mainpanebgcolor" align="left" valign="top">

</td>
</tr>
</table>
</html:form>
<!--Main form section ends here-->