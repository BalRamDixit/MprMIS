<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.forms[0].action = "outerAction.do?methodName=showPage&" + tokenParameter + "=" + tokenValue;;
	document.forms[0].submit();
	}
} 

	function showData()
	{
		document.forms[0].action = "traininginformationAction.do?methodName=showPage1&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}

	function filterDropDown()
	{
		document.forms[0].action = "traininginformationAction.do?methodName=showDropDown&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function filterDropDown1()
	{
		document.forms[0].action = "traininginformationAction.do?methodName=showDropDown1&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function filterDropDown2()
	{
		document.forms[0].action = "traininginformationAction.do?methodName=showDropDown2&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function resetFilters()
	{
		elements = document.getElementsByTagName("select")
		for(var i=0; i < elements.length ; i++)
		{
		elements[i].selectedIndex= "";
		}
		document.forms[0].action = "traininginformationAction.do?methodName=resetDropDownFilter&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
</script>
<html:form action="/traininginformationAction">
	<table width="100%" align="center">

		<tr>
			<td align="center" class="pageHeader">Report of Training Centres
				</td>
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
						<th>Training Centre Name</th>
						<td><html:select property="trainingcentername"
								styleId="trainingCenterNameId" onchange="filterDropDown2();">
								<html:option value="">Select</html:option>
								<logic:present name="trainingCenterNameLst">
									<logic:iterate id="list" name="trainingCenterNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>

					<tr>
						<th>Training Centre Code</th>
						<td><html:select property="trainingcode"
								styleId="trainingCenterCodeId">
								<html:option value="">Select</html:option>
								<logic:present name="trainingCenterCodeLst">
									<logic:iterate id="list" name="trainingCenterCodeLst">
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
							</html:button></td>
					</tr>

				</table>
			</td>
		</tr>
		<logic:present name="trainingSummayLst">
			<tr>
				<td align="center">
					<div
						style="width: 940px; height: 450px; overflow: auto; border-bottom-style: solid;">
						<table width="100%" align="center" class="bordered">
							<tr>
								<th>S.No.</th>
								<th>PIA Name</th>
								<th>Project Name</th>
								<th>Training Centre Code</th>
								<th>Training Centre Name</th>
								<th>State Name</th>
								<th>District Name</th>
								<th>Address</th>
								<th>Centre Head Name</th>
								<th>Centre Head Email</th>
								<th>Centre Head Mobile</th>
								<th>Start Date</th>
								<th>District Target</th>
								<th>Training Capacity</th>
								<th>Residential</th>
								<th>Franchised</th>
								<th>Status <br/>(Operational,<br/>Non-operational,<br/>Closed)</th>
							</tr>

							<logic:iterate id="list" name="trainingSummayLst" indexId="index">
								<tr>
									<td>${index+1}</td>
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

						</table>
					</div>
				</td>
			</tr>
		</logic:present>
	</table>



</html:form>