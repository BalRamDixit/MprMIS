<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';


</script>

<script type="text/javascript">
	// Run Datables plugin and create 3 variants of settings
	function AllTables() {
		//TestTable1();
		TestTable2();
		//TestTable3();
		LoadSelect2Script(MakeSelect2);
	}
	function MakeSelect2() {
		$('select').select2();
		$('.dataTables_filter').each(
				function() {
					$(this).find('label input[type=text]').attr('placeholder',
							'Search');
				});
	}

	function getDetail(encd) {
			document.forms[0].action = "sanctionOrderReport.do?methodName=showSanctionReport&encd="
					+ encd + "&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
	}


	function backbutton() {
		var encd = document.getElementById("encd").value;

		if (encd.length == 2) {
			encd = "n";
			document.forms[0].action = "sanctionOrderReport.do?methodName=showSanctionReport&encd="
					+ encd + "&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
		if (encd.length == 4) {
			encd = encd.substr(0, 2);
			document.forms[0].action = "sanctionOrderReport.do?methodName=showSanctionReport&encd="
				+ encd + "&" + tokenParameter + "=" + tokenValue; 
			document.forms[0].submit();
		}
	}

	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		//WinMove();
	});
</script>
<html:form action="/sanctionOrderReport">
<bean:parameter id="encdParam" name="encd" value="" />
<html:hidden property="" value="<%= encdParam%>" styleId="encd" />

	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<div class="box-name" align="center">
						<i class="fa fa"></i> <span>DDU-GKY Approved Projects </span>
					</div>

				</div>

				<div class="box-content no-padding table-responsive">
					<table
						class="table table-bordered table-striped table-hover table-heading table-datatable"
						id="datatable-2">
						<tr>
							<td colspan="2">
							 <logic:notEqual name="encdParam" value="n">
									<logic:notEqual name="encdParam" value="">
										<html:button styleClass="mprButton" property=""
											onclick="backbutton()">
											<bean:message key="button.back" />
										</html:button>
									</logic:notEqual>
								</logic:notEqual> 
								<html:button styleClass="mprButton" property=""
									onclick="closeReport();">
									<bean:message key="button.close" />
								</html:button></td>
						</tr>

					</table>

					<logic:present name="stateList">
						<table
							class="table table-bordered table-striped table-hover table-heading table-datatable"
							id="datatable-2">
							<thead>
								<tr>
									<th><label>S.&nbsp;No.</label></th>
									<th><label>State Name</label></th>
									<th><label>Total Project</label></th>
									<th><label>Total Target Assigned</label></th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="list" name="stateList" indexId="index">
									<tr>
										<td>${index + 1}</td>
										<td><a href="javascript:getDetail('${list[0]}')">${list[1]}</a></td>
										<td>${list[2]}</td>
										<td>${list[3]}</td>
									</tr>
								</logic:iterate>
							</tbody>

						</table>
					</logic:present>
					
					<logic:present name="districtList">
						<table
							class="table table-bordered table-striped table-hover table-heading table-datatable"
							id="datatable-2">
							<thead>
								<tr>
									<th><label>S.&nbsp;No.</label></th>
									<th><label>State Name</label></th>
									<th><label>District Name</label></th>
									<th><label>Total Project</label></th>
									<th><label>Total Target Assigned</label></th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="list" name="districtList" indexId="index">
									<tr>
										<td>${index + 1}</td>
										<td>${list[1]}</td>
										<td><a href="javascript:getDetail('${list[0]}')">${list[2]}</a></td>
										<td>${list[3]}</td>
										<td>${list[4]}</td>
									</tr>
								</logic:iterate>
							</tbody>

						</table>
					</logic:present>
					
					<logic:present name="approvedPIAList">
				<div>
						<table
							class="table table-bordered table-striped table-hover table-heading table-datatable" width="90%"
							id="datatable-2" >
							<thead>
								<tr>
									<th><label>S.&nbsp;No.</label></th>
									<th><label>State Name</label></th>
									<th><label>District Name</label></th>
									<th><label>Scheme Type</label></th>
									<th><label>CTSA</label></th>
									<th><label>PIA Name</label></th>
									<th><label>Overall Target</label></th>
									<th><label>District Target</label></th>
									<th><label>Contact Person</label></th>
									<th><label>Contact No.</label></th>
								</tr>
							</thead>
							<tbody style="padding-right:2px;">
								<logic:iterate id="list" name="approvedPIAList" indexId="index">
									<tr>
										<td>${index + 1}</td>
										<td>${list[0]}</td>
										<td>${list[1]}</td>
										<td>${list[3]}</td>
										<td>${list[4]}</td>
										<td>${list[5]}</td>
										<td>${list[6]}</td>
										<td align="center">${list[7]}</td>
										<td>${list[8]}</td>
										<td>${list[9]}</td>
									</tr>
								</logic:iterate>
							</tbody>

						</table>
						</div>
					</logic:present>


					<logic:present name="UnderConstruction">
						<%-- <jsp:include page="/WEB-INF/login/pages/main/underConstruction.jsp"></jsp:include> --%>

						<p>Under Construction</p>

					</logic:present>
				</div>
			</div>
		</div>
	</div>

</html:form>