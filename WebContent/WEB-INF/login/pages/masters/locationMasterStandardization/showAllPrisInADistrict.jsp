<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>

<head>
	
</head>
<body>
	<html:form action="login/locationMasterStandardization">
		<%int cnt=0; %>
		<table width="100%" border=1>
				<tr>
					<td colspan="3" class="actionstxt" align="center">
						<bean:message key="label.locationMasterStandardizaton"/>
					</td>
					<td class="actionstxt" align="center">
						Export to Pdf
					</td>
				</tr>
				<tr>
					<td class="actionstxt" align="center">
						Sl.No
					</td>
					<td class="actionstxt" align="center">
						Block Name
					</td>
					<td class="actionstxt" align="center">
						Grampanchayat Name
					</td>
					<td class="actionstxt" align="center">
						Village Name
					</td>
				</tr>
				<tr>
					<td>
						<logic:present name="allVillageList" scope="request">
							<logic:iterate id="pri" name="allVillageList" indexId="index">
								<tr>
									<td>
										<span style="color: #4169E1; font-weight: bold;">
											<%=++cnt%>
										</span>
									<td>
										<span style="color: #4169E1; font-weight: bold;">
											<bean:write name="pri" property="blockName"/>
										</span>
									</td>
									<td>
										<span style="color: #4169E1; font-weight: bold;">
											<bean:write name="pri" property="gramPanchayatName"/>
										</span>
									</td>
									<td>
										<span style="color: #4169E1; font-weight: bold;">
											<bean:write name="pri" property="villageName"/>
										</span>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</td>
				</tr>
		</table>
	</html:form>
</body>

