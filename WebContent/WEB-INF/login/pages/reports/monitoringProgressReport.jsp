<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<style>
	.hideDiv{
		display:none;
	}
</style>
<script>
	function show(elementToShow){
		if($('#hideShow'+elementToShow).hasClass('hideDiv')){
			$('#hideShow'+elementToShow).removeClass('hideDiv');
		}
		else{
			$('#hideShow'+elementToShow).addClass('hideDiv');
		}
		
	}
</script>
<html:form action="login/monitoringProgressReport">
	<body>

		<table width="100%" class="pageHeaderTable">
			<tr>
				<td align="center" class="pageHeader">MONITORING PROGRESS
					REPORT</td>
			</tr>
		</table>
		<div style="overflow-x: scroll; overflow-y: scroll;" id="Export">
			<table width="100%" align="center" id="tableId" class="display"	border="1">

				<tr>
					<th align="center">S.No.</th>
					<th align="center">State Name</th>
					<th align="center">No of Projects Detail</th>
					<th align="center">No of Project sanction Details</th>
					<th align="center">No of Project Sanction Modified</th>
					<th align="center">No of Project With Trade Target</th>
					<th align="center">No of Project with District Target</th>
					<th align="center">No of Project with PPWS</th>
					<th align="center">No Of Project With 1st Installment</th>
					<th align="center">No Of Project With 2nd Installment</th>
					<th align="center">No Of Project With 3rd Installment</th>
					<th align="center">No Of Project With 4th Installment</th>
					<th align="center">No of Training Center</th>
					<th align="center">No of Training Center those Due diligence entered</th>
					<th align="center">No of Training Center with Trade Capacity</th>
					<th align="center">No of Batch data those are completed before	31st March 2017</th>
					<th align="center">No of Batch Creation After 31st March 2017</th>
					<th align="center">No of Batches Batch Completion After 31st March 2017</th>
					<th align="center">No of Inspection</th>
					<th align="center">No of Batches whose Placement is marked</th>
					<th align="center">No of Candidates Trained</th>
					<th align="center">No of Candidate Placed</th>
				</tr>
				<logic:present name="formBean">
					<c:set var="TotalIndexCount" value="1" />
					<c:forEach items="${formBean}" var="completeData">
						<c:forEach items="${completeData.value}" var="formBeanMain">
							
							<%-- <div>
								<h3>${formBeanMain.key}</h3>
								==>
								<h4>${formBeanMain.value}</h4>
							</div> --%>
								<c:if test="${formBeanMain.key eq 'Total'}">
										<tr>
											<td>${TotalIndexCount}</td>
											<td><a href="#" onclick=show('${TotalIndexCount}') style="color:black">${formBeanMain.value.field1}</a></td>
											<td>${formBeanMain.value.field2}</td>
											<td>${formBeanMain.value.field3}</td>
											<td>${formBeanMain.value.field4}</td>
											<td>${formBeanMain.value.field5}</td>
											<td>${formBeanMain.value.field6}</td>
											<td>${formBeanMain.value.field7}</td>
											<td>${formBeanMain.value.field9}</td>
											<td>${formBeanMain.value.field10}</td>
											<td>${formBeanMain.value.field11}</td>
											<td>${formBeanMain.value.field12}</td>
											<td>${formBeanMain.value.field8}</td>
											<td>${formBeanMain.value.field13}</td>
											<td>${formBeanMain.value.field14}</td>
											<td>${formBeanMain.value.field15}</td>
											<td>${formBeanMain.value.field16}</td>
											<td>${formBeanMain.value.field17}</td>
											<td>${formBeanMain.value.field18}</td>
											<td>${formBeanMain.value.field19}</td>
											<td>${formBeanMain.value.field20}</td>
											<td>${formBeanMain.value.field21}</td>
											<c:set var="TotalIndexCount" value="${TotalIndexCount + 1}" />
										</tr>
								</c:if>
							</c:forEach>
							<c:set var="indexCount" value="1" />
							<tr class="hideDiv" id="hideShow${TotalIndexCount-1}">
									<td colspan="22">
										<table  width="100%" align="center"  id="tableId" class="display"  border="1">			
						 					 <tr>
											  	<th align="center">S.No.</th>
											     <th align="center">PIA PRN</th>
											     <th align="center">PIA Name</th>
								                 <th align="center">No of  Projects Detail</th> 
								                 <th align="center">No of Project sanction Details</th>
								                 <th align="center" >No of Project Sanction Modified</th>
								                 <th align="center">No of Project With Trade Target</th>
								                 <th align="center">No of Project with District Target</th>
								                 <th align="center" >No of Project with PPWS</th>
								                 <th align="center">No Of Project With 1st Installment</th>
								                 <th align="center" >No Of Project With 2nd Installment</th>
								                 <th align="center">No Of Project With 3rd Installment </th>
								                 <th align="center">No Of Project With 4th Installment</th>
								                 <th align="center" >No of Training Center</th> 
								                 <th align="center">No of Training Center those Due diligence entered</th>
								                 <th align="center" >No of Training Center with Trade Capacity</th>
								                 <th align="center">No of Batch data those are completed before 31st March 2017 </th>
								                 <th align="center">No of Batch Creation After 31st March 2017</th>
								                 <th align="center" >No of Batches Batch Completion After 31st March 2017</th>
								                 <th align="center" >No of Inspection</th> 
								                 <th align="center" >No of Batches whose Placement is marked</th>
								                 <th align="center" >No of Candidates Trained </th>
								                 <th align="center" > No of Candidate Placed </th>
								             </tr>
											<c:forEach items="${completeData.value}" var="formBeanMain">
											<c:if test="${formBeanMain.key ne 'Total'}">
												<tr>
													<td>${indexCount}</td>
													<td>${formBeanMain.value.field22}</td>
			  										<td>${formBeanMain.value.field23}</td>
													<td>${formBeanMain.value.field2}</td>
													<td>${formBeanMain.value.field3}</td>
													<td>${formBeanMain.value.field4}</td>
													<td>${formBeanMain.value.field5}</td>
													<td>${formBeanMain.value.field6}</td>
													<td>${formBeanMain.value.field7}</td>
													<td>${formBeanMain.value.field9}</td>
													<td>${formBeanMain.value.field10}</td>
													<td>${formBeanMain.value.field11}</td>
													<td>${formBeanMain.value.field12}</td>
													<td>${formBeanMain.value.field8}</td>
													<td>${formBeanMain.value.field13}</td>
													<td>${formBeanMain.value.field14}</td>
													<td>${formBeanMain.value.field15}</td>
													<td>${formBeanMain.value.field16}</td>
													<td>${formBeanMain.value.field17}</td>
													<td>${formBeanMain.value.field18}</td>
													<td>${formBeanMain.value.field19}</td>
													<td>${formBeanMain.value.field20}</td>
													<td>${formBeanMain.value.field21}</td>
													<c:set var="indexCount" value="${indexCount + 1}" />
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</td>
							</tr>
					</c:forEach>
				</logic:present>
			</table>
		</div>
		<div align="center">
			<!-- 		<input name="Button" type="button" class="button" value="Retrieve" onclick="Retrieve()" /> -->
			<button id="btnExport" class="button" type="button"
				onclick="fnExcelReport('tableId');">EXPORT To Excel</button>
			<button id="btnExport" class="button" type="button"
				onclick="createPdf('Export');">EXPORT To PDF</button>
		</div>
		
	</body>
</html:form>
