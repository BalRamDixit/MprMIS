
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>
	';

	function closePage() {
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if (status == true) {
			document.forms[0].action = "outerAction.do?methodName=showPage"
			document.forms[0].submit();
		}
	}

	function showData() {
		document.forms[0].action = "districtSummaryAction.do?methodName=showPage1";
		document.forms[0].submit();
	}

	function filterDropDown() {
		document.forms[0].action = "districtSummaryAction.do?methodName=showDropDown";
		document.forms[0].submit();
	}
	function filterDropDown1() {
		document.forms[0].action = "districtSummaryAction.do?methodName=showDropDown1";
		document.forms[0].submit();
	}

	function resetFilters() {
		elements = document.getElementsByTagName("select");
		for ( var i = 0; i < elements.length; i++) {
			elements[i].selectedIndex = "";
		}
		document.forms[0].action = "districtSummaryAction.do?methodName=resetDropDownFilter";
		document.forms[0].submit();
	}

	function filterDropDown2() {
		document.forms[0].action = "districtSummaryAction.do?methodName=showDropDown2";
		document.forms[0].submit();
	}
</script>
<html:form action="/districtSummaryAction">

	<table width="100%" align="center">

		<tr>
			<td align="center" class="pageHeader">Report of District Wise
				PIA</td>
		</tr>

		<tr>
			<td width="100%" align="center">
				<table width="100%" class="borderedPresentation">
					<tr>
						<th width="150px">PIA Name</th>
						<td><html:select property="piaName" styleId="piaNameId"
								onchange="filterDropDown();">
								<html:option value="">Select</html:option>

								<logic:present name="piaNameLst">
									<logic:iterate id="list" name="piaNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>
					<tr>
						<th>Project Name</th>
						<td><html:select property="projectName"
								styleId="projectNameId" onchange="filterDropDown1();">
								<html:option value="">Select</html:option>

								<logic:present name="projectNameLst">
									<logic:iterate id="list" name="projectNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>
					<tr>
						<th>State Name</th>
						<td><html:select property="stateName" styleId="stateNameId"
								onchange="filterDropDown2();">
								<html:option value="">Select</html:option>

								<logic:present name="stateNameLst">
									<logic:iterate id="list" name="stateNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>
					<tr>
						<th>District Name</th>
						<td><html:select property="districtName"
								styleId="districtNameId">
								<html:option value="">Select</html:option>

								<logic:present name="districtNameLst">

									<logic:iterate id="list" name="districtNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>
					<tr>
						<td colspan="2"><html:button styleClass="button" property=""
								onclick="showData();">
								Search
							</html:button> <html:button styleClass="button" property=""
								onclick="resetFilters();">
								<bean:message key="button.reset" />
							</html:button> <html:button styleClass="button" property=""
								onclick="closePage();">
								<bean:message key="button.close" />
							</html:button>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<logic:present name="districtSummayLst">
			<tr>
				<td width="100%" align="center">
					<div style="width: 940px; height: 450px; overflow: auto;">
						<table width="100%" align="center" class="bordered"
							style="overflow: scroll";>

							<tr>
								<th>S.No.</th>
								<th>PIA Name</th>
								<th>Project Name</th>
								<th>State Name</th>
								<th>District Name</th>
								<th>Target Trainees</th>
								<th>Target Centres</th>
							</tr>
							<logic:iterate id="list" name="districtSummayLst" indexId="index">
								<tr>
									<td>${index+1}</td>
									<td>${list[1]}</td>
									<td>${list[2]}</td>
									<td>${list[3]}</td>
									<td>${list[4]}</td>
									<td>${list[5]}</td>
									<td>${list[6]}</td>
								</tr>
							</logic:iterate>

						</table>
					</div></td>
			</tr>
		</logic:present>
	</table>

	<div align="center"></div>



</html:form>