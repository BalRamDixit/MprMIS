<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;

function closeReport(){
	 window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
	
	</script>
<html:form action="/login/infrastructureAction">
<table width="100%" align="center">
	<tr><td align="center" class="pageHeader">Infrastructure</td></tr>
	<tr>
	<table width="99%"  align="center">
	<tr><th>Existing infrastructure capacity at training centers</th></tr>
	<tr>
	<th>District(s)</th>
	<th>Number of active training centers</th>
	<th>Total training space with the centers (sq ft)</th>
	<th>Number of practical labs with centers</th>
	<th>Action</th>
	</tr>
	<tr>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="Ernakulam">Ernakulam</html:option>
    <html:option value="Cherthala">Cherthala</html:option>
    <html:option value="Kollam">Kollam</html:option>
    <html:option value="Kottayam">Kottayam</html:option>
    <html:option value="Malappuram">Malappuram</html:option>	
	</html:select>
	<td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td></td>
	</tr>
	<tr>
	<td>Total</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td></td>
	<td><html:button  styleClass="button" property="" onclick="">Calculate</html:button></td>
	<td><html:button  styleClass="button" property="" onclick="">AddMore</html:button></td>
	</tr>

<tr><th>Existing infrastructure capacity at training centers</th></tr>
	<tr>
	<th>District(s)</th>
	<th>Number of active training centers</th>
	<th>Total training space with the centers (sq ft)</th>
	<th>Number of practical labs with centers</th>
	<th>Action</th>
	</tr>
	<tr>
	<td>
	<html:select property="stateCode" styleId="stateCode" >
	<html:option value="Ernakulam">Ernakulam</html:option>
    <html:option value="Cherthala">Cherthala</html:option>
    <html:option value="Kollam">Kollam</html:option>
    <html:option value="Kottayam">Kottayam</html:option>
    <html:option value="Malappuram">Malappuram</html:option>	
	</html:select>
	<td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td></td>
	</tr>
	<tr>
	<td>Total</td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td><html:text styleId="stateCode" property="stateCode" maxlength="10"  size="10"/></td>
	<td></td>
	<td><html:button  styleClass="button" property="" onclick="">Calculate</html:button></td>
	<td><html:button  styleClass="button" property="" onclick="">AddMore</html:button></td>
    </tr>
    <tr><html:button  styleClass="button" property="" onclick="">Submit</html:button></tr>
    </table>
    </tr>
    </table>
    </html:form>
		
	
	
	
	
	
	