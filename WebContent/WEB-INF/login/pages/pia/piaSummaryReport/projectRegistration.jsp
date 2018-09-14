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
			document.forms[0].submit();
		}
	}

	function showData() {
		document.forms[0].action = "projectRegistrationAction.do?methodName=showPage1&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function filterDropDown() {
		document.forms[0].action = "projectRegistrationAction.do?methodName=showDropDown&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function filterDropDown1() {
		document.forms[0].action = "projectRegistrationAction.do?methodName=showDropDown1&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	function resetFilters() {
		elements = document.getElementsByTagName("select")
		for ( var i = 0; i < elements.length; i++) {
			elements[i].selectedIndex = "";
		}
		document.forms[0].action = "projectRegistrationAction.do?methodName=resetDropDownFilter&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
</script>
<html:form action="/projectRegistrationAction">
	<table width="100%" align="center">

		<tr>
			<td align="center" class="pageHeader">Report of PIA Projects</td>
		</tr>

		<tr>
			<td width="100%" align="center" >
				<table width="100%"  class="borderedPresentation">
					<tr>
						<th width="150px;">PIA Name</th>
						<td><html:select property="piaName" styleId="piaNameId" onchange="filterDropDown();">
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
								styleId="projectName"	onchange="filterDropDown1();">
								<html:option value="">Select</html:option>
								<logic:present name="projectNameLst">
									<logic:iterate id="list" name="projectNameLst">
										<html:option value="${list}">${list}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
					</tr>
					<tr>
					<td colspan="2">
					<html:button styleClass="button" property="" onclick="showData();">
								Search
							</html:button>
							<html:button styleClass="button" property="" onclick="resetFilters();">
								<bean:message key="button.reset" />
							</html:button>
					<html:button styleClass="button" property="" onclick="closePage();">
								<bean:message key="button.close" />
							</html:button>
								
					</td>
					</tr>

				</table>
			</td>
		</tr>
		<logic:present name="projectSummayLst">
			<tr>
				<td align="center">
					<div style="width: 940px; height:450px; overflow: auto; border-bottom-style: solid;">
						<table width="100%"  align="center" class="bordered">
							<tr>
								<th>S.No.</th>
								<th>PIA Name</th>
								<th>Project Name</th>
								<th>Monitoring Agency</th>
								<th>Beneficiaries</th>
								<th>Start Date as per Sanction Letter</th>
								<th>End Date as per Sanction Letter</th>
								<th>Actual Start Date</th>
								<th>Actual End Date</th>
								<th>Project Type</th>
								<th>Duration in years.months</th>
								<th>Total Cost of the Project(in Lakhs)*</th>
								<th>MORD Share(in Lakhs)</th>
								<th>Percentage of Residential Training Centres</th>
								<th>Project in-charge(name)</th>
								<th>Project in-charge(Designation)</th>
								<th>Project in-charge(mobile)</th>
								<th>Project in-charge(Email)</th>
								<th>Project Wedsite</th>
								<th>MIS Incharge</th>
								<th>MIS Designation</th>
								<th>MIS Email</th>
								<th>MIS Mobile</th>
								<th>Have you conducted a scan for this project ?</th>
								<th>Scan Document Date</th>
								<th>Scan Document File Name</th>
								<th>Sanction Letter</th>
								<th>Sanction Date</th>
								<th>Installment Date</th>
								<th>Installment Amount</th>
								<th>Sanction Letter File Name</th>
								<th>Second Option</th>
								<th>Second Installment Date</th>
								<th>Second Installment Amount</th>
								<th>Third Option</th>
								<th>Third Installment Date</th>
								<th>Third Installment Amount</th>
								<th>Extension</th>
								<th>Training hours</th>
								<th>Centres Owned</th>
								<th>Cost per Trainee</th>
								<th>Person Responsible for Approving Submitted Data</th>
								<th>Centres Franchised</th>
								<th>Sanction Letter Scan Second FileName</th>
								<th>Sanction Letter Scan Third FileName</th>
								<th>Is project closed</th>
							</tr>
							<logic:iterate id="list" name="projectSummayLst" indexId="index">
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
									<td>${list[17]}</td>
									<td>${list[18]}</td>
									<td>${list[19]}</td>
									<td>${list[20]}</td>
									<td>${list[21]}</td>
									<td>${list[22]}</td>
									<td>${list[23]}</td>
									<td>${list[24]}</td>
									<td>${list[25]}</td>
									<td>${list[26]}</td>
									<td>${list[27]}</td>
									<td>${list[28]}</td>
									<td>${list[29]}</td>
									<td>${list[30]}</td>
									<td>${list[31]}</td>
									<td>${list[32]}</td>
									<td>${list[33]}</td>
									<td>${list[34]}</td>
									<td>${list[35]}</td>
									<td>${list[36]}</td>
									<td>${list[37]}</td>
									<td>${list[38]}</td>
									<td>${list[39]}</td>
                                    <td>${list[40]}</td>
                                    <td>${list[41]}</td>
                                    <td>${list[42]}</td>
                                    <td>${list[43]}</td>
                                    <td>${list[44]}</td>
                                    <td>${list[45]}</td>
								</tr>
							</logic:iterate>

						</table>
					</div>
				</td>
			</tr>
		</logic:present>
	</table>
</html:form>