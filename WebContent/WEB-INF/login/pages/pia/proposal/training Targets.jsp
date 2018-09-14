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
	
function save(){	
		var status=window.confirm('<bean:message key="msg.saveForm" />');
	    if(status==true){
			document.forms[0].action="trainingTargetsAction.do?methodName=save&"+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
	}	
</script>

<html:form action="/login/trainingTargetsAction">
	<table width="100%" align="center">
		<tr>
			<td align="center" class="pageHeader">Training Targets</td>
		</tr>
		<tr>
			<td>
				<table width="99%" align="center">
					<tr>
					    <th>Year</th> 
						<th>Month</th>
						<th>Batch</th>
						<th>Trade</th>
						<th>No of Candidates</th>
					</tr>

					<logic:present name="monthList">
						<logic:iterate id="list" name="monthList">
							<tr>
								<td>${list[1]}</td>
								<td><html:text styleId="batch" property="batch"
										maxlength="10" size="10" />
								</td>
								<td><html:text styleId="trade" property="trade"
										maxlength="10" size="10" />
								</td>
								<td><html:text styleId="noOfCandidates"
										property="noOfCandidates" maxlength="10" size="10" />
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</table></td>
		</tr>
		<tr>
			<td><html:button styleClass="button" property="" onclick="">AddYear</html:button>
			<html:button styleClass="button" property=""
onclick="save()">Submit</html:button>
			</td>
		</tr>
	</table>
</html:form>
