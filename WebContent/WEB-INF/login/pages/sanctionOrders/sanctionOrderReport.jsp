<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function showFiles(fileType) 
{
	document.forms[0].action = "sanctionOrderReport.do?methodName=showFiles&file="+fileType+"&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

</script>

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
function AllTables(){
	//TestTable1();
	TestTable2();
	//TestTable3();
	LoadSelect2Script(MakeSelect2);
}
function MakeSelect2(){
	$('select').select2();
	$('.dataTables_filter').each(function(){
		$(this).find('label input[type=text]').attr('placeholder', 'Search');
	});
}
$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	LoadDataTablesScripts(AllTables);
	// Add Drag-n-Drop feature
	//WinMove();
});
</script>
<html:form action="/sanctionOrderReport">

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name" align="center">
					<i class="fa fa"></i>
					<span>Sanction Order Report</span>
				</div>
				
			</div>

	<div class="box-content no-padding table-responsive">
		<logic:present name="sanctionDetail">
				<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-2">
					<thead>
						<tr>
							 <th><label>S.&nbsp;No.</label></th> 
							<th><label><input type="text" name="search_rate" value="PIA Name" class="search_init" /></label></th>
							<th><label><input type="text" name="search_name" value="PIA Registration Number" class="search_init" /></label></th>
							<th><label><input type="text" name="search_votes" value="Sanction Order Number" class="search_init" /></label></th>
							<th><label><input type="text" name="search_homepage" value="SanctionOrderDate" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version" value="SanctionFile" class="search_init" /></label></th>
							
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="list" name="sanctionDetail" indexId="index">
							<tr>
								<td>${index + 1}</td>
								<td>${list[5]}</td>
								<td>${list[4]}</td>
								<td>${list[0]}</td>
								<td>${list[1]}</td>
								<td><a href="javascript:showFiles('${list[3]}')">View</a></td>
							</tr>
						</logic:iterate>
					</tbody>
					
				</table>
				</logic:present>
			</div>
		</div>
		</div>
	</div>

</html:form>