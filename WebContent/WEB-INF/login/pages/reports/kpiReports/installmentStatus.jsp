<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script>
	
	
	function handleHttpResponse(){
		if (req.readyState == 4 && req.responseCode==200){	
	    	var response = req.responseText; 
	    	alert("Document Created");
	  	}
	}
</script>
<html:form action="login/report" >
	<body>
	<div id="Export">
		<table width="100%" cellspacing="10" cellpadding="10"
			id="tableToExport">
			<tr>
				<th colspan="3"><b>5. INSTALLMENT STATUS</b></th>
			</tr>
			<tr>
				<td>
				<table border="1" width="100%">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"></th>
							<th style="background: #d9d9d9 ;border: 1px solid black;"></th>
							<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Target</th>
							<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Actual</th>
							<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Pending</th>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">1</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Total Projects that are Sanctioned</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">-</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">86</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">-</td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">2</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Total projects that have received 1 installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">86</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">80</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">6</td>
						</tr>
					</table>
					
			
				<table border="1" width="100%">
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">3</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Total Project that have achieved 2 installment
								milestones</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">61</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">58</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">3</td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">4</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that have submitted data and documents for 2
								installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">58</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">34</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">24</td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">5</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that are recommended for 2 installment (E2)</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">34</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">24</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">10</td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">6</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that have received 2 installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">24</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">23</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00">1</td>
						</tr>
					</table>
					
				
				<table border="1" width="100%">
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">7</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Total Project that have achieved 3 installment
								milestones</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">8</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that have submitted data and documents for 3
								installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">9</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that are recommended for 3 installment (E4)</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">10</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that have received 3 installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table>
					
		
				<table border="1" width="100%">
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">11</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Total Project that have achieved 4 installment
								milestones</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">12</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that have submitted data and documents for 4
								installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">13</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that are recommended for 4 installment (E7)</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">14</td>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Projects that have received 4 installment</td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="8%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table>
				</td>
				<td>
				
				</td>
				<td><table border="1" width="90%">
						<tr>
							<th style="background: #d9d9d9 ;border: 1px solid black;"></th>
							<th width="10%" style="background: #d9d9d9 ;border: 1px solid black;">Target</th>
							<th width="10%" style="background: #d9d9d9 ;border: 1px solid black;">Actual</th>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Average number of days from project sanction to release of first installment</td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Average number of days from E2 to release of second installment</td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Average number of days from E4 to release of third installment</td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
						<tr>
							<td style="border: 1px solid black;background: #d9d9d9 ;">Average number of days from E7 to release of fourth installment</td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
							<td width="10%" style="border: 1px solid black;background: #ffcc00"></td>
						</tr>
					</table></td>
			</tr>
		</table>
		</div>
		<button id="btnExport" onclick="fnExcelReport('tableToExport');" type="button">EXPORT To Excel</button>
			<button id="btnExport" onclick="createPdf()" type="button">EXPORT To PDF</button>
	</body>
</html:form>