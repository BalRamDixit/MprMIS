<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="css/main_style.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />   
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javaScript/jquery.js"></script>
<script type="text/javascript" src="javaScript/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="javaScript/bootstrap.min.js"></script>
<script type="text/javascript" src="javaScript/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function () {
		$('#reportLinksTable').dataTable();
	});
</script>
<style>
#reportLinksTable {
	font-size: small;
}
.innerDivStyle {
	width: 100%;
	padding: 5px;
}
.form-control {
	height: auto;
}
body {
	background-color: #DFDFDF;	
}
</style>
<script type="text/javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

/*  function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.forms[0].action = "outerAction.do?methodName=showPage&" + tokenParameter + "=" + tokenValue;;
	document.forms[0].submit();
	}
} 
 */
 

 
 
function showData(){
	document.forms[0].action = "outerAction.do?methodName=piaRegStatus&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}
 function searchPIAData(){
	 
	 document.getElementById("sub").checked = false;
	 document.getElementById("Checked").checked = false;
	 
	 document.getElementById("Verified").checked = false;
	 document.getElementById("Rejected").checked = false;
	 document.getElementById("Incomplete").checked = false;
	 document.getElementById("Withdrawn").checked = false;
	 
	 
	 document.getElementById("Debarred").checked = false;
	 document.getElementById("Blacklisted").checked = false;
	 document.getElementById("all").checked = false;
	 
	 	var piaSearch = document.getElementById("piaSearch").value;
		document.forms[0].action = "outerAction.do?methodName=searchPIAData&" + tokenParameter + "=" + tokenValue +"&piaSearch="+piaSearch;
		document.forms[0].submit();
	}

function getDetail(pid){
	document.forms[0].action = "outerAction.do?methodName=getPiaDetail&pid="+pid+"&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
} 
</script>
</head>
<body>
	<html:form action="/outerAction">
	<div class="panel panel-default" id="panelfilter">
			<!-- Default panel contents -->
			<div class="panel-heading" id="panel" align="center">
				<b>Applicant Organization Verification Detailed Report</b>
			</div>
			<!-- List group -->
			<ul class="list-group">
				<li class="list-group-item"><span class="input-group-addon"
					id="basic-addon5"> Select PIA's </span> <br />
					<div class="row" style="margin-top: 5px;">
						<div class="col-md-12">
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> 
									<html:radio property="piaReportStatus" value="A" styleId="all" onclick="javascript:showData();"/></span> <span
										class="btn-default form-control " id="typeLabel">All</span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="S" styleId="sub"
											onclick="javascript:showData();" /></span> <span
										class="btn-default form-control " id="typeLabel">Submitted</span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="C" styleId="Checked"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">Checked
									</span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="V" styleId="Verified"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">Verified
									</span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="R" styleId="Rejected"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">Rejected
									</span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="P" styleId="Incomplete"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">
										Incomplete </span>
								</div>
								<!-- /input-group -->
							</div>
						</div>
						<br /> <br />
						<div class="col-md-12">
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="PW" styleId="Withdrawn"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">
										Withdrawn </span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="PD" styleId="Debarred"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">
										Debarred </span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> <html:radio
											property="piaReportStatus" value="PB" styleId="Blacklisted"
											onclick="javascript:showData();" />
									</span><span class="btn-default form-control " id="typeLabel">
										Blacklisted </span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-2">
							<div  class="input-group-addon">														 
							  <input type="search" name="piaSearch" id="piaSearch"  placeholder="TRN/PRN">
							  <button onclick="searchPIAData()">GO</button>						  
							</div>
							</div>

						</div>
						
					</div>
					
			</ul>
		</div>		
		<table id="reportLinksTable" width="99%" align="center"class="table table-striped table-bordered" >
			<logic:present name="piaList">
				<!-- VERIFIED -->
				<thead>
					<tr>
						<th>Sr. No.</th>
						<th>Applicant Organization</th>
						<th>Authorized Person</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="list" name="piaList" indexId="index">
						<tr>
							<td>${index+1 }</td>
							<td>
								<div class="innerDivStyle">
									<div class="input-group">
										<span class="input-group-addon">Organization Name</span> <span
											class="form-control">${list[1]} (<a
											href="javascript:getDetail('${list[0]}');">View Complete
												Details</a>)
										</span>
									</div>
								</div>
								<div class="innerDivStyle">
									<div class="input-group">
										<span class="input-group-addon">Organization Contact:
										</span> <span class="form-control"> <strong>Address:</strong>
											${list[2] }<br /> <strong>Contact No:</strong> ${list[3] }<br />
											<strong>email-id:</strong> <span
											style="text-transform: lowercase; color: #2673B4">${list[4] }</span>
										</span>
									</div>
								</div>
							</td>
									<td>
										<c:if test="${list[5] !='' }">
										<span class="text-info">Authorized Person Name</span>: ${list[5] }<br />
										</c:if> <c:if test="${list[5]=='' }">
										<span class="text-info">No Authorized Person Found</span>
										</c:if>
									</td>
						</tr>
					</logic:iterate>

				</tbody>
			</logic:present>
		</table>
</html:form>
	</body>

<!--State Level HR Status Report -->