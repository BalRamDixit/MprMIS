<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

	function closePage() 
	{
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if (status == true) 
		{
			document.forms[0].action = "outerAction.do?methodName=showPage&"
					+ tokenParameter + "=" + tokenValue;
			;
			document.forms[0].submit();
		}
	}
	function filterDropDown() 
	{
		document.forms[0].action = "expSummaryAction.do?methodName=showDropDown";
		document.forms[0].submit();
	}
	function filterDropDown1() 
	{
		document.forms[0].action = "expSummaryAction.do?methodName=showDropDown1";
		document.forms[0].submit();
	}
	function filterDropDown2()
	{
		document.forms[0].action = "expSummaryAction.do?methodName=showDropDown2";
		document.forms[0].submit();
	}
	function filterDropDown3()
	{
		document.forms[0].action = "expSummaryAction.do?methodName=showDropDown3";
		document.forms[0].submit();
	}
	function showData()
	{
		document.forms[0].action = "expSummaryAction.do?methodName=showPage&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}

	function resetFilters() 
	{
		elements = document.getElementsByTagName("select")
		for ( var i = 0; i < elements.length; i++) 
		{
			elements[i].selectedIndex = "";
		}
		document.forms[0].action = "expSummaryAction.do?methodName=resetFilters&"
		document.forms[0].submit();
	}
</script>
<html:form action="/expSummaryAction">
	<table width="100%" align="center">
		<tr>
			<td align="center" class="pageHeader">Report Of Expenditure
				Summary</td>
		</tr>

		<tr>
			<td align="center" width="100%">
				<table class="borderedPresentation" width="100%">
					<tr>
						<th width="150px;">Pia Name</th>
						<td><html:select property="piaName" styleId="piaName"
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
						<td><html:select property="projectName" styleId="projectName"
								onchange="filterDropDown1();">
								<html:option value="">Select</html:option>
								<logic:present name="projectNameLst">
									<logic:iterate id="list" name="projectNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>

					<tr>
						<th>Sector Name</th>
						<td><html:select property="sectorName" styleId="sectorName"
								onchange="filterDropDown2();">
								<html:option value="">Select</html:option>
								<logic:present name="sectorNameLst">
									<logic:iterate id="list" name="sectorNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>

					</tr>

					<tr>
						<th>Trade Name</th>
						<td><html:select property="tradeName" styleId="tradeName"
								onchange="filterDropDown3();">
								<html:option value="">Select</html:option>
								<logic:present name="tradeNameLst">
									<logic:iterate id="list" name="tradeNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>

					</tr>
					<tr>
						<th>Certifying Agency</th>
						<td><html:select property="certifyingAgencyName"
								styleId="certifyingAgencyName" onchange="filterDropDown();">
								<html:option value="">Select</html:option>
								<logic:present name="certifyingAgencyLst">
									<logic:iterate id="list" name="certifyingAgencyLst">
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
								Reset
							</html:button> <html:button styleClass="button" property=""
								onclick="closePage();">
								<bean:message key="button.close" />
							</html:button></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td align="center" width="100%">
				<table class="borderedPresentation" width="100%">
					<tr>
						<th>Sl.No.</th>
						<th>PIA Name</th>
						<th>Project Name</th>
						<th>Sector Name</th>
						<th>Trade Name</th>
						<th>Certifying Agency</th>
						<th>Duration Training In Months</th>
						<th>Duration Training in Days</th>
						<th>On Job Training in Days</th>
						<th>Training Duration <br />per day in hours</th>
					</tr>
					<logic:present name="expenditureSummayLst">
						<logic:iterate id="list" name="expenditureSummayLst"
							indexId="index">
							<tr>
								<td>${index + 1}</td>
								<td>${list[1]}</td>
								<td>${list[2]}</td>
								<td>${list[3]}</td>
								<td>${list[4]}</td>
								<td>${list[5]}</td>
								<td>${list[6]}</td>
								<td>${list[7]}</td>
								<td>${list[8]}</td>
								<td>${list[9]}</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</table>
			</td>
		</tr>
	</table>


</html:form>