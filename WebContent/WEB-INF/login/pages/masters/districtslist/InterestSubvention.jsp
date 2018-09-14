<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

	function closePage() {
		var status = window.confirm('<bean:message key="alert.close.form" />');
		if (status == true) {
			document.forms[0].action = "login.do?methodName=closePage" + "&"
					+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
	}
	function save(){
		var status = window.confirm('<bean:message key="msg.saveForm" />');
		if (status == true) {
         document.forms[0].action = "district.do?methodName=save&"+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
	}
</script>

<html:form action="/login/district">
	<table width="100%" align="center">
		<tr>
			<td align="center" class="pageHeader">Interest Subvention</td>
		</tr>
		<tr>
			<td>
				<table width="90%" class="reportTBL" align="center">
					<tr>
						<th>S.No.</th>
						<th>District Name</th>
						<th>Interest % above which the Interest Subsidy is applicable</th>
					</tr>
					<logic:present name="districtform">
						<logic:iterate id="district" name="districtform" property="districtList"
							indexId="index">
							<tr>
								<td>${index +1}</td>
								<td><bean:write name="district"  property="districtName" />
				                <html:hidden name="district" property="entityCode"  indexed="true" />
								</td>

								<td><html:select name="district" property="interestSubventionRate"
										styleId="interestSubventionRate" indexed="true">
										<html:option value="7">7%</html:option>
										<html:option value="4">4%</html:option>
									</html:select>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</table></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><html:button styleClass="button"
					property="" onclick="save();">Save</html:button> 
					<html:button
					styleClass="button" property="" onclick="closePage();">
					<bean:message key="button.close" />
				</html:button>
			</td>
		</tr>
	</table>
</html:form>


