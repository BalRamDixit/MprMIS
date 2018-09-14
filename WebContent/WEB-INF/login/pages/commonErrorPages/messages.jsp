<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>

<html:html> 
		<html:messages id="message" name="<%=SGSYConstants.MSG %>" message="true">				
<script type="text/javascript">			
 
				//if(document.getElementById('messageId').value != null && document.getElementById('messageId').value != ""){
				
	alert('<bean:write name="message"/>');
				
				//}
        	</script>
	 	</html:messages>			 
</html:html> 







