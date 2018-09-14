<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function showData(){
	document.forms[0].action = "PiaStatusAction.do?methodName=showPiaStatus&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

function genrateExcel(){
	document.forms[0].action = "PiaStatusAction.do?methodName=createExcel&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}


</script>

<html:form action="/login/PiaStatusAction">

<tr>
		<td >
			<table width="100%" class="mprMenu table-bordered" align="center">
				<tr>
					<td align="right"><a href="javascript:genrateExcel()"
						title="Download as Excel"><img alt="EXCEL" src="images/Excel-icon.png" width="10px;" height="10px;" /> Excel </a></td>
				</tr>
			</table></td>
	</tr>
	
	<table width="100%" align="center" style="padding-down: 7px;">
		<tr>
			<th colspan="2" align="center" class="pageHeader">Project
				Implementation Agency (PIAs) Status</th>
		</tr>
		<tr>
			<td colspan="2">

				<table width="100%" class="bordered">
					<tr>
						<th>Applicant Organisation Current Status</th>
					</tr>
					<tr>
						<td>
							<table width="100%">
								<tr>
									<th width="30%">Applicant Organisation Status</th>
									<td><html:select property="piaReportStatus" onchange="showData();">
											<html:option value="A">All</html:option>
											<html:option value="R">Rejected</html:option>
											<html:option value="V">Verified</html:option>
											<html:option value="S">Submitted</html:option>
											<html:option value="P">Incomplete</html:option>
										</html:select></td>
								</tr>
							</table></td>
					</tr>
				</table>


				<div style="overflow: auto; max-width: 700px; max-height: 400px;">
					<table width="100%" class="bordered">
						<tr>
								<th rowspan="2">S.No.</th> 
								<th rowspan="2">Applicant Organisation Code(temporary)</th>
								<th rowspan="2">Applicant Organisation Status</th>	
								<th rowspan="2">Applicant Organisation Confirmation Date(in case of verified PIAs)</th>	
								<th rowspan="2">Applicant Organisation Application Submission Date</th>	
								<th rowspan="2">Applicant Organisation Name</th>
								<th rowspan="2">Applicant Organisation Permanent Number(in case of verified PIAs)</th>	
								<th rowspan="2">Category of Applicant</th>	
								<th rowspan="2">Type of business/Activity</th>	
								<th colspan="5">Address</th>												
								<th colspan="4">Contact Detail </th>															
								<th rowspan="2">State where registered</th>
								<th rowspan="2">Registration number</th>
								<th rowspan="2">Date of registration</th>	
								<th rowspan="2">PAN</th>	
								<th rowspan="2">TAN</th>	
								<th rowspan="2">TIN</th>	
								<th colspan="2">Details of registration under section 12A</th>		
								<th colspan="2">Details of registration under section 80G</th>		
								<th colspan="2">Details of registration under FCRA</th>
								<th  rowspan="2">Address of Land/Building</th>
								<th  rowspan="2">Freehold/Mortgaged</th>														
								<th  rowspan="2">Applicant Organisation Application Rejection Reason</th>												
							</tr>
							<tr>
								<th>Address</th>			
								<th>State</th> 
								<th>District</th> 
								<th>Block </th>
								<th>PIN</th>
								<th>Office Phone </th>
								<th>Office Fax </th>	
								<th>Email</th>	
								<th>Website</th>	
								<th>Registration Number</th>	
								<th>Registration Date</th>
								<th>Registration Number</th>	
								<th>Registration Date</th>
								<th>Registration Number</th>	
								<th>Registration Date</th>	
							</tr>	
							<logic:iterate id="list" name="piaStatusList" indexId="index">
							<tr>
							<td>${index + 1 }</td>
							<td>${list[0]}</td>
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
							</tr>						
							
							</logic:iterate>
						
					</table>
				</div></td>
		</tr>
	</table>
	<div align="center" style="padding-top: 15px; padding-bottom: 10px;">
		<html:button styleClass="defaultBtn" property="" onclick="showData()">Close</html:button>
	</div>
</html:form>