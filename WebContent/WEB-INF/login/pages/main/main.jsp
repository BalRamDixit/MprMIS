<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>' ; 
  
</script>
<head>
<meta http-equiv="refresh" content="0;URL=login/login.do?methodName=showMainPage&reqtrack=<%=request.getSession().getAttribute("TRACKERID") %>" />
 

<title>Blank Page</title>

</head>
 Please Wait till the Page is loaded or you can Refresh ( F5 ) the Link again.

    It may happen due to Network Traffic ...