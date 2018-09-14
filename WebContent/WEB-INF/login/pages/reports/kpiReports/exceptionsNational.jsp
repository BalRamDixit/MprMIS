<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script>
	function fnExcelReport(TableIdToExport) {
		var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
		var textRange;
		var j = 0;
		tab = document.getElementById(TableIdToExport); // id of table

		for (j = 0; j < tab.rows.length; j++) {
			tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
			//tab_text=tab_text+"</tr>";
		}

		tab_text = tab_text + "</table>";
		var ua = window.navigator.userAgent;
		var msie = ua.indexOf("MSIE ");

		if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
			txtArea1.document.open("txt/html", "replace");
			txtArea1.document.write(tab_text);
			txtArea1.document.close();
			txtArea1.focus();
			sa = txtArea1.document.execCommand("SaveAs", true, "FileName.xls");
		} else
			sa = window.open('data:application/vnd.ms-excel,'
					+ encodeURIComponent(tab_text));

		return (sa);
	}
</script>
<html:form action="login/report">
	<body>
		<div style="overflow-x: scroll; overflow-y: scroll;">
			<table width="100%" cellspacing="10" id="tableToExport">
				 <tr> 
					<td colspan="3"><b>6 B. INSPECTIONS AND DEFAULTS (CONTINUED..)</b></td>
				 </tr> 
				<tr>
					<td>
						<table border="1">
							<tr>
								<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="left">Training Center Inspections(NABCONS) FY15-16</th>
								<th colspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="center">CTSA</th>
								<th colspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="center">SRLM</th>
								<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="left">Active Training Center this month</th>
								<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="left">Training Center Active (Cumulative)</th>
							</tr>
							<tr>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(cumulative)</th>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(this month)</th>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(cumulative)</th>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(this month)</th>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Maharasthra</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Odisha</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Tripura</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Karnataka</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Total</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
						</table>
					</td>
					<td><br /></td>
					<td>
						<table border="1">
							<tr>
								<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;" align="left">Training Center Inspections(NIRD) FY15-16</th>
								<th colspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="center">CTSA</th>
								<th colspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="center">SRLM</th>
								<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="left">Active Training Center this month</th>
								<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"align="left">Training Center Active (Cumulative)</th>
							</tr>
							<tr>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(cumulative)</th>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(this month)</th>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(cumulative)</th>
								<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total Inspections Completed(this month)</th>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Gujrat</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Bihar</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Haryana</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Jharkhand</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
							<tr>
								<td align="left" style="border: 1px solid black;">Total</td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
								<td style="border: 1px solid black;background: #ffcc00"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<button id="btnExport" onclick="fnExcelReport('tableToExport');">
			EXPORT</button>
	</body>
</html:form>