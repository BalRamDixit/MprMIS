<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<script src="sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="sweetalert-master/dist/sweetalert.css">


<script type="text/javascript">
	function closePage() {
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if (status == true) {
			document.accessForm.action = "login.do?methodName=closePage" + "&"
					+ tokenParameter + "=" + tokenValue;
			document.accessForm.submit();
		}
	}

	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>

<style>
p.err {
	color: #E80003;
	background-color: #F88B41;
	box-sizing: !important;
}
</style>

<html>
<body>
	<html:form action="login/projectTargetAction">
		<table>
			<tr>
				<td>
					<table width="100%" class="pageHeaderTable">
						<tr>
							<td align="center" width="120%" class="pageHeader">Project
								Setup - Project Target</td>
						</tr>
						
					</table>

					<table width="100%" align="center" class="inputTBL">
						<tr>
							<th>${name}</th>
							<td><b>${nameValue}</b></td>
						</tr>
					</table> <br /> <br />
					<table width="120%" align="center" id="example" class="display">
						<thead>
							<tr>
								<th>S.NO</th>
								<th>Project-ID</th>
								<th>Total Training Target</th>
								<th>SC+ST</th>
								<th>General</th>
								<th>Minority</th>
								<th>Women</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>S.NO</th>
								<th>Project-ID</th>
								<th>Total Training Target</th>
								<th>SC+ST</th>
								<th>General</th>
								<th>Minority</th>
								<th>Women</th>
							</tr>
						</tfoot>
						<tbody>
							<logic:present name="DetailsList">
								<c:set var="indexCount" value="1" />
								<logic:iterate id="list" name="DetailsList" indexId="index">
									<tr>
										<td>${indexCount}</td>
										<td>${list[0]}</td>
										<td>${list[1]}</td>
										<td>${list[2]}</td>
										<td>${list[3]}</td>
										<td>${list[4]}</td>
										<td>${list[5]}</td>
										<td></td>
									</tr>
									<c:set var="indexCount" value="${indexCount + 1}" />
								</logic:iterate>
							</logic:present>
						</tbody>
					</table> <br /> <br /> <br />
					<div align="center">
						<input name="Button" type="button" class="button"
							value="<bean:message  key="button.close"/>" onclick="closePage()" />
					</div>
				</td>
			</tr>
		</table>
	</html:form>
</body>
<!--Main form section ends here-->