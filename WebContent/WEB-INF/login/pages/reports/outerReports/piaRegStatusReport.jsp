<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function closePage(){
	document.forms[0].action = "outerAction.do?methodName=showIndex";
	document.forms[0].submit();
}
</script>
<html:form action="/outerAction"> 
<div   class="outerTBL">
<table width="100%" align="center" style="padding-down: 7px;">
	<tr>
		<th colspan="2" align="center" class="pageHeader">${title }</th>
	</tr>
<tr>
<td align="center">
<table width="99%" class="bordered">

						<!-- All -->
						<logic:present name="PSV">
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
								<th>Submission Date</th>
							</tr>

							<logic:iterate id="list" name="PSV" indexId="index">
								<tr>
									<td>${index+1 }</td>
									<td>${list.piaName }</td>
									<td>${list.address }</td>
									<td>${list.modifyOn }</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<!-- All END -->

						<!-- PRN Allotted -->
						<logic:present name="V">
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
								<th>PRN Allotment Date</th>
							</tr>

							<logic:iterate id="list" name="V" indexId="index">
								<tr>
									<td>${index+1 }</td>
									<td>${list.piaName }</td>
									<td>${list.address }</td>
									<td>${list.piaConfirmationDate}</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<!-- PRN Allotted END-->

						<!-- Rejected -->
						<logic:present name="R">
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
							</tr>

							<logic:iterate id="list" name="R" indexId="index">
								<tr>
									<td>${index+1 }</td>
									<td>${list.piaName }</td>
									<td>${list.address }</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<!-- Rejected END-->


						<!-- Submitted -->
						<logic:present name="S">

							<!-- <tr><th width="100%">Applications pending less than 2 days</th></tr>  -->
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
								<th width="100px;">Pending Status</th>
							</tr>
							<c:set var="index" value="1" />
							<logic:iterate id="list1" name="piaList1">
								<tr>
									<td>${index }</td>
									<td>${list1.piaName }</td>
									<td>${list1.address }</td>
									<td>${list1.piaConfirmationDate}</td>
								</tr>
								<c:set var="index" value="${index +1}" />
							</logic:iterate>
							<logic:iterate id="list2" name="piaList2">
								<tr>
									<td>${index }</td>
									<td>${list2.piaName }</td>
									<td>${list2.address }</td>
									<td>${list2.piaConfirmationDate}</td>
								</tr>
								<c:set var="index" value="${index +1}" />
							</logic:iterate>
						</logic:present>
						<!-- Submitted END-->

						<!-- PIA WITHDRAWN -->
						<logic:present name="PW">
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
							</tr>

							<logic:iterate id="list" name="PW" indexId="index">
								<tr>
									<td>${index+1 }</td>
									<td>${list.piaName }</td>
									<td>${list.address }</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<!-- PIA WITHDRAWN END -->

						<!-- PIA DEBARRED -->
						<logic:present name="PD">
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
							</tr>

							<logic:iterate id="list" name="PD" indexId="index">
								<tr>
									<td>${index+1 }</td>
									<td>${list.piaName }</td>
									<td>${list.address }</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<!-- PIA DEBARRED END -->

						<!-- PIA BLACKLISTED -->
						<logic:present name="PB">
							<tr>
								<th width="40px;">Sr. No.</th>
								<th>Name</th>
								<th>Address</th>
							</tr>

							<logic:iterate id="list" name="PB" indexId="index">
								<tr>
									<td>${index+1 }</td>
									<td>${list.piaName }</td>
									<td>${list.address }</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<!-- PIA BLACKLISTED END-->


					</table>
</td>
</tr>	
</table>
<div align="center" style="padding-top: 15px; padding-bottom: 10px;"> 
	<html:button  styleClass="defaultBtn" property="" onclick="closePage()">Back</html:button>
</div>
</div>
</html:form>