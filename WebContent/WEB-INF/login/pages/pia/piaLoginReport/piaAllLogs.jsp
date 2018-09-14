<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function showData(){
	document.forms[0].action = "PiaLogAction.do?methodName=showPiaLog&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

function genrateExcel(){
	document.forms[0].action = "PiaLogAction.do?methodName=createExcel&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}
</script>

<html:form action="/login/PiaLogAction">

<!-- <tr>
<td>
<table width="100%" align="center" style="padding-down: 7px;">
<tr>
					<td align="right"><a href="javascript:genrateExcel()"
						title="Download as Excel"><img alt="EXCEL" src="images/Excel-icon.png" width="10px;" height="10px;" /> Excel </a></td>
				</tr>
				</table>
				</td>
				</tr> -->

	<table width="100%" align="center" style="padding-down: 7px;" class="piaLogTbl">
		<tr>
			<th colspan="2" align="center" class="pageHeader">Applicant Organisation Logs</th>
		</tr>
		<tr>
			<td colspan="2">
				<div style="overflow: auto; max-width: 700px;">
					<table width="100%" class="piaLogTbl">
					<tr><th>Applicant Organisation Current Status</th></tr>
					<tr>
						<td>
							<table width="100%">
							<tr><th width="30%">Status</th>
							<td>
							<c:if test="${piaCurrentStatus.piaStatus == 'V'}"><span class="text-error">Verified</span></c:if> 
							<c:if test="${piaCurrentStatus.piaStatus == 'S'}"><span class="text-error">Submitted</span></c:if> 
							<c:if test="${piaCurrentStatus.piaStatus == 'C'}"><span class="text-error">Checked</span></c:if> 
							<c:if test="${piaCurrentStatus.piaStatus == 'I' || piaCurrentStatus.piaStatus == 'P'}"><span class="text-error">Incomplete</span></c:if> 
							<c:if test="${piaCurrentStatus.piaStatus == 'R'}"><span class="text-error">Rejected</span></c:if>
							<c:if test="${piaCurrentStatus.piaStatus == 'PW'}" ><span class="text-error">Withdrawn</span></c:if>
		           			<c:if test="${piaCurrentStatus.piaStatus == 'PD'}" ><span class="text-error">Debarred</span></c:if>
							<c:if test="${piaCurrentStatus.piaStatus == 'PB'}" ><span class="text-error">Blacklisted</span></c:if>
							</td></tr>
							<tr><th>Last Modification</th><td>${piaCurrentStatus.modifyOn}</td></tr>							
							</table>
														
							<table width="100%">
							<tr><th width="30%" >Applicant Organisation Name</th><td>${piaCurrentStatus.piaName}</td></tr>
							<tr><th>Temp. Ref. No.</th><td>${piaCurrentStatus.piaCode}</td></tr>
							<tr><th>Permanent Registration Number</th><td>${piaCurrentStatus.piaRegistrationNo}</td></tr>
							<tr><th>Verification Date</th><td>${piaCurrentStatus.piaConfirmationDate}</td></tr>
							</table>
																					
							<table width="100%">
							<tr><th width="30%">Registration No</th><td>${piaCurrentStatus.registrationNumber}</td></tr>
							<tr><th>PAN</th><td>${piaCurrentStatus.panNo}</td></tr>
							<tr><th>TAN</th><td>${piaCurrentStatus.tanNo}</td></tr>							
							</table>
						</td>
					</tr>
					</table>
				  </div>

				<%-- div style="overflow: auto; max-width: 700px;">
					<table width="100%" class="bordered">	
					<tr><th colspan="3">Applicant Organisation Old Status</th></tr>
					<tr>
						<th width="30%">Date</th>
						<th width="30%">Status</th>
						<th width="30%">Remark</th>
					</tr>					
					<logic:iterate id="piaOldLog" name="piaOldStatus">
					<tr>					
						<td>${piaOldLog[0]}</td>
						<td><c:if test="${piaOldLog[1] == 'V'}">Verified</c:if> 
							<c:if test="${piaOldLog[1] == 'S'}">Submitted</c:if> 
							<c:if test="${piaOldLog[1] == 'C'}">Checked</c:if> 
							<c:if test="${piaOldLog[1] == 'I' || piaOldLog[1] == 'P'}">Incomplete</c:if> 
						    <c:if test="${piaOldLog[1] == 'R'}">Rejected</c:if></td>
						<td>${piaOldLog[2]}</td>
					</tr>
						</logic:iterate>
					</table>
				</div> --%>
				<logic:empty name="piaOldStatus">
					<div style="width: 80%; padding-left: 75px;border: solid #ccc 1px; -moz-border-radius: 6px; -webkit-border-radius: 6px; border-radius: 6px; -webkit-box-shadow: 0 1px 1px #ccc; -moz-box-shadow: 0 1px 1px #ccc; box-shadow: 0 1px 1px #ccc; margin: 10px 50px 5px 50px; padding: 15px 10px 15px 50px; background-repeat: no-repeat;  background-position: 10px center; color: #D8000C; background-color: #FFBABA; text-transform: uppercase; background-image: url(../images/warning.png);">No Previous History Found </div>
					</logic:empty>	
				<logic:notEmpty name="piaOldStatus" >
				<div style="overflow: auto; max-width: 700px;">
					<table width="100%" class="piaLogTbl">	
					<tr><th colspan="7">Applicant Organisation Old Status</th></tr>
					<tr>
						<th width="20%">Applicant Organisation</th>						
						<th width="5%">Status</th>
						<th width="35%">Status Remark</th>
						<th width="10%">Created By</th>
						<th width="10%">Created On</th>
						<th width="10%">Updated By</th>
						<th width="10%">Updated On</th>						
					</tr>
							
					<logic:iterate id="piaOldLog" name="piaOldStatus">
					<tr>					
						<td>${piaOldLog[1]}</td>						
						<td>
						<c:if test="${piaOldLog[3] == 'V'}"><span class="text-error">Verified</span></c:if> 
						<c:if test="${piaOldLog[3] == 'S'}"><span class="text-error">Submitted</span></c:if> 
						<c:if test="${piaOldLog[3] == 'C'}"><span class="text-error">Checked</span></c:if> 
						<c:if test="${piaOldLog[3] == 'I' || piaOldLog[3] == 'P'}"><span class="text-error">Incomplete</span></c:if> 
						<c:if test="${piaOldLog[3] == 'R'}"><span class="text-error">Rejected</span></c:if>
						<c:if test="${piaOldLog[3] == 'PW'}"><span class="text-error">Withdrawn</span></c:if>
						<c:if test="${piaOldLog[3] == 'PD'}"><span class="text-error">Debarred</span></c:if>
						<c:if test="${piaOldLog[3] == 'PB'}"><span class="text-error">Blacklisted</span></c:if>
						</td>						
						<td>${piaOldLog[2]}</td>
						<td>${piaOldLog[4]}</td>
						<td>${piaOldLog[5]}</td>
						<td>${piaOldLog[6]}</td>
						<td>${piaOldLog[7]}</td>
					</tr>
						</logic:iterate>
					</table>
				</div>
				</logic:notEmpty>
			</td>
		</tr>
	</table>
	<div align="center" style="padding-top: 15px; padding-bottom: 10px;">
		<html:button styleClass="mybtn" property="" onclick="showData()">Close</html:button>		
	</div>
</html:form>