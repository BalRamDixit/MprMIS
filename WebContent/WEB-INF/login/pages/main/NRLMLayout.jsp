<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" ignore="true" /></title>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pagelayout">
<table width="960px" border="0" align="center" cellpadding="0" cellspacing="0" class="masthead">
    <tr>
      <td align="right" valign="middle"><img src="images/nationalemblem.jpg" alt="National emblem of India" width="80" height="80" /></td>
      <td align="center" valign="bottom"><img src="images/NRLM.jpg" alt="NRLM Logo" width="500" height="80"/></td>
    	<td align="left" valign="bottom"><img src="images/NRLMLogo.jpg" alt="NRLM Logo" width="80" height="80"/></td>
    </tr>
    
  </table>
  <table> 
	<tr>
		<td >
		<tiles:insert attribute="header" ignore="true">
			<tiles:put name="title" beanName="title" beanScope="tile" />
		</tiles:insert>
		</td>
	</tr>
	</table>
	<table align="center" width="100%">
	<tr>
		<td align="center"><div align="center"><tiles:insert attribute="body" /></div></td>
	</tr>
	</table>
	<table>
	<tr>
		<td><tiles:insert attribute="footer" ignore="true" /></td>
	</tr>
	</table>
</div>
</body>
</html>
