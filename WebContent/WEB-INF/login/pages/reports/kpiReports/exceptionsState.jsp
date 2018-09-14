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
		<iframe id="txtArea1" style="display: none"></iframe>
		<table width="100%" cellspacing="10" cellpadding="5"
			id="tableToExport">
			<tr>
				<th colspan="5"><b>6 A. INSPECTION AND DEFAULTS</b></th>
			</tr>
			<tr>
				<td><table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left" colspan="3"><u>Training Center Due- Diligence</u></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total TCs for which DD requests received</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total TCs approved</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Total TCs for which DD pending</th>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #ffcc00">23</td>
							<td style="border: 1px solid black;background: #ffcc00">12</td>
							<td style="border: 1px solid black;background: #ffcc00">11</td>
						</tr>
					</table> <br />
					<table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left" colspan="5"><u>Due Diligence Requests</u></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">(Date of reference is DD Request by PIA)</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Total Pending</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">10 Days old</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">10-20 Days old</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">20+ Days old</th>
						</tr>
						<tr>
							<th  style="background: #d9d9d9 ;border: 1px solid black;"align="left">Initial Due -Diligence Inspection visit conducted by
								SRLM/TSA</th>
							<td style="border: 1px solid black;background: #ffcc00">8</td>
							<td style="border: 1px solid black;background: #ffcc00">4</td>
							<td style="border: 1px solid black;background: #ffcc00">4</td>
							<td style="border: 1px solid black;background: #ffcc00">0</td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Awaiting PIA compliance to DD inspection report</th>
							<td style="border: 1px solid black;background: #ffcc00">3</td>
							<td style="border: 1px solid black;background: #ffcc00">0</td>
							<td style="border: 1px solid black;background: #ffcc00">1</td>
							<td style="border: 1px solid black;background: #ffcc00">2</td>
						</tr>
					</table> <br /> 
					<table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left" colspan="2"><u>Complaints</u></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of complaints received</th>
							<td width="40%" style="border: 1px solid black;background: #ffcc00">3</td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of complaints closed</th>
							<td width="40%" style="border: 1px solid black;background: #ffcc00">-</td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of complaints open</th>
							<td width="40%" style="border: 1px solid black;background: #ffcc00">3</td>
						</tr>
					</table></td><td><br /></td>
				<td>
					<table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;" colspan="4" align="left"><u>Inspections</u></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"></th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Total Inspections completes (cumulative)</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Active Training Center this month</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Total Inspections completed(this month)</th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Q- Team</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">SRLM</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">CTSA</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table> <br /> 
					<table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left" colspan="2"><u>Exceptions</u></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of active projects that are :</th>
							<th width="37%" style="border: 1px solid black;background: #ffcc00"></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">PPWS non complaint</th>
							<td width="37%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Not commenced training within 3 months</th>
							<td width="37%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>

						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Not submitted data and documents for concurrent monitoring
								till last month</th>
							<td width="37%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table>
				</td><td><br /></td>
				<td><table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left" colspan="9"><u>Defaults</u></th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;" rowspan="2"></th>
							<th style="background: #d9d9d9 ;border: 1px solid black;" rowspan="2"># of active projects</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;" rowspan="2"># of alerts</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;" colspan="4">Reason</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;" colspan="2">Status</th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;">PPWS Related</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">TC Inspection Related</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Financial related</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Others</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Open</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Closed</th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of yellow alerts</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00">25</td>
							<td style="border: 1px solid black;background: #ffcc00">2</td>
							<td style="border: 1px solid black;background: #ffcc00">10</td>
							<td style="border: 1px solid black;background: #ffcc00">4</td>
							<td style="border: 1px solid black;background: #ffcc00">9</td>
							<td style="border: 1px solid black;background: #ffcc00">10</td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of Red alerts</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of SCN</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table> <br /> 
					<table border="1">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left" colspan="6"><u>Penalties</u></th>
						</tr>
						<tr>
							<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"></th>
							<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;">Total of active projects</th>
							<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;"># of Penalties</th>
							<th rowspan="2" style="background: #d9d9d9 ;border: 1px solid black;">closed</th>
							<th colspan="2" style="background: #d9d9d9 ;border: 1px solid black;">Open</th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Phase for PIA to appeal</th>
							<th style="background: #d9d9d9 ;border: 1px solid black;">Decision Pending at appellate authority</th>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of Minor Penalties</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Number of Major Penalties</th>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
							<td style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table></td>
			</tr>
		</table>
		<button id="btnExport" onclick="fnExcelReport('tableToExport');">
			EXPORT</button>
	</body>
</html:form>