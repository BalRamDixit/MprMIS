<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

	function closePage() {
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if (status == true) {
			document.forms[0].action = "outerAction.do?methodName=showPage&"
					+ tokenParameter + "=" + tokenValue;
			;
			document.forms[0].submit();
		}
	}

	
	function showData() 
	{
		document.forms[0].action = "targetSummaryAction.do?methodName=showPage&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function filterDropDown()
	{
		document.forms[0].action = "targetSummaryAction.do?methodName=showDropDown";
		document.forms[0].submit();
	}
	function filterDropDown1()
	{
		document.forms[0].action = "targetSummaryAction.do?methodName=showDropDown1";
		document.forms[0].submit();
	}
	function resetFilters() 
	{
		elements = document.getElementsByTagName("select")
		for ( var i = 0; i < elements.length; i++) 
		{
			elements[i].selectedIndex = "";
		}
		document.forms[0].action = "targetSummaryAction.do?methodName=resetFilters&"
		document.forms[0].submit();
	}
</script>

<html:form action="/targetSummaryAction">
	<table width="100%" align="center">
		<tr>
			<td align="center" class="pageHeader">Report Of Target Summary</td>
		</tr>
		<tr>
			<td align="center" width="100%">
				<table class="borderedPresentation" width="100%">
					<tr>
						<th width="150px;">Pia Name</th>
						<td><html:select name="TargetSummaryForm" property="piaName"
								styleId="piaName" onchange="filterDropDown();">
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
			<td align="center" width="100%">

				<div style="width: 940px; height: 450px; overflow: auto;">
					<table class="borderedPresentation" width="100%">
						<tr>
							<th rowspan="2">S.No</th>
							<th rowspan="2">PIA Name</th>
							<th rowspan="2" align="center">Project Name</th>
							<th colspan="3" align="center">Target For Financial Year</th>
							<th rowspan="2">Project Target completed till 31.03.2012</th>
							<th rowspan="2">Target completed in the First quarter</th>
							<th colspan="9" align="center">Target To be completed In</th>
						</tr>
						<tr>
							<th>2012-13</th>
							<th>2013-14</th>
							<th>2014-15</th>
							<th>July 2012</th>
							<th>August 2012</th>
							<th>September 2012</th>
							<th>October 2012</th>
							<th>November 2012</th>
							<th>December 2012</th>
							<th>January 2013</th>
							<th>February 2013</th>
							<th>March 2013</th>
						</tr>
						<logic:present name="targetSummayLst">
							<logic:iterate id="list" name="targetSummayLst" indexId="index">
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
									<td>${list[10]}</td>
									<td>${list[11]}</td>
									<td>${list[12]}</td>
									<td>${list[13]}</td>
									<td>${list[14]}</td>
									<td>${list[15]}</td>
									<td>${list[16]}</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
				</div></td>
		</tr>
	</table>
</html:form>