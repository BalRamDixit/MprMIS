<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
<link href="css/pykka.css" rel="stylesheet">
</head>
<script>
function success(path){
 document.successForm.action=path;
 document.successForm.submit();
}

function closePage(){
	var status=window.confirm("Are you sure you want to Close ");
	if(status==true){
	document.successForm.action = "";
	document.successForm.submit();
	}
}

function showAdd(){	
	var showAddPage = "showAddPage";
	document.successForm.action ="bankAccountAction.do?methodName=showAdd&showAddPage="+showAddPage;
	document.successForm.submit();
}

function goToUrl(path){
		document.successForm.action=path;
		document.successForm.submit();
}


</script>
<body>

<form name="successForm" action="" method="post">
<table align="center">
<tr><td>&nbsp;</td></tr><p>&nbsp;</p><p>&nbsp;</p>
</table>
<table align="center" class="tableborder">

<tr valign="top">
<td align="center" class="oddrow">
<% if(request.getAttribute("msg") != null){ %>
<%=request.getAttribute("msg") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("proposalSave") != null){ %>
<%=request.getAttribute("proposalSave") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("populationNotAvailable") != null){ %>
<%=request.getAttribute("populationNotAvailable") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("pykkaCenterNotAvailable") != null){ %>
<%=request.getAttribute("pykkaCenterNotAvailable") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("proposalForward") != null){ %>
<%=request.getAttribute("proposalForward") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("proposalNotSubmited") != null){ %>
<%=request.getAttribute("proposalNotSubmited") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("proDelete") != null){ %>
<%=request.getAttribute("proDelete") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("proposalAlreadyForward") != null){ %>
<%=request.getAttribute("proposalAlreadyForward") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("proposalAlreadyforDeleteFroward") != null){ %>
<%=request.getAttribute("proposalAlreadyforDeleteFroward") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("proposalUpdate") != null){ %>
<%=request.getAttribute("proposalUpdate") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("clusterSave") != null){ %>
<%=request.getAttribute("clusterSave") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("msgForMinorPanchayat") != null){ %>
<%=request.getAttribute("msgForMinorPanchayat") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("msgForBlockPanchayat") != null){ %>
<%=request.getAttribute("msgForBlockPanchayat") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("updateAssignRole") != null){ %>
<%=request.getAttribute("updateAssignRole") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("assignRole") != null){ %>
<%=request.getAttribute("assignRole") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("msgForMaxPykkaCenter") != null){ %>
<%=request.getAttribute("msgForMaxPykkaCenter") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("pykkaCenterCreateMessage") != null){ %>
<%=request.getAttribute("pykkaCenterCreateMessage") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("msgForMinorPanchayatKridashree") != null){ %>
<%=request.getAttribute("msgForMinorPanchayatKridashree") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("noUpdateAssignRole") != null){ %>
<%=request.getAttribute("noUpdateAssignRole") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("saveBankAccount") != null){ %>
<%=request.getAttribute("saveBankAccount") %><br><tr><td align="center">
</td></tr>
<%} %>
<% if(request.getAttribute("bankAccountExist") != null){ %>
<%=request.getAttribute("bankAccountExist") %><br><tr><td align="center">
</td></tr>
	<br><tr><td align="center">
	<input name="Button" type="button" value="Yes" onclick="showAdd()">
	<input name="Button" type="button" value="No " onclick="closePage()">
	</td></tr>
<%} %>

<% if(request.getAttribute("isAccountAlreadyExist") != null){ %>
<%=request.getAttribute("isAccountAlreadyExist") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("isProposalApproved") != null){ %>
<%=request.getAttribute("isProposalApproved") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("bankAccountModify") != null){ %>
<%=request.getAttribute("bankAccountModify") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("transferAndRecieveExist") != null){ %>
<%=request.getAttribute("transferAndRecieveExist") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("msgForApproveOrReviewProposal") != null){ %>
<%=request.getAttribute("msgForApproveOrReviewProposal") %><br><tr><td align="center">
</td></tr>
<%} %>

<% if(request.getAttribute("requestMSg") != null){ %>
<%=request.getAttribute("requestMSg") %><br><tr><td align="center">
<%String url =  (String)request.getAttribute("path");%>
<input type="button" class="button" onclick="goToUrl('<%=url%>')">
<input type="button" class="button" onclick="closePage()">
</td></tr>
<%} %>

</table>
<table height="300">
<tr><td>&nbsp;</td></tr>
</table>


</form>
</body>
</html>