<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script>
function fnExcelReport(TableIdToExport)
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById(TableIdToExport); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
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
<body>
<html:form action="login/report">
<iframe id="txtArea1" style="display:none"></iframe>
	<div>
		<table width="100%" border="1" id="tableToExport">
			<tr>
				<th colspan="13"><b>7. CHAMPION EMPLOYERS</b></th>
			</tr>
			
			<tr>
				<th width="10%" style="background: #d9d9d9 ;border: 1px solid black;">Champion employers</th>
				<th width="7%" style="background: #d9d9d9 ;border: 1px solid black;">Date of MoU</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Target as per MoU</th>
				<th width="7%" style="background: #d9d9d9 ;border: 1px solid black;">Number of projects in state</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Total target of sanctioned projects</th>
				<th width="7%" style="background: #d9d9d9 ;border: 1px solid black;">Total Trained</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Trained as % of Target</th>
				<th width="7%" style="background: #d9d9d9 ;border: 1px solid black;">Total Placed</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Placed as % of Trained</th>
				<th width="7%" style="background: #d9d9d9 ;border: 1px solid black;">Average Salary</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Average months of retention</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Target covered by proposals submitted</th>
				<th width="8%" style="background: #d9d9d9 ;border: 1px solid black;">Target covered by sanctioned proposals</th>
			</tr>
			<tr>
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Apollo Medskills</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">10,000</td> 
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Cafe Coffee Day</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">10,000</td> 
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Manpower Group</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">10,000</td> 
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Surya Wires</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">10,000</td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Quess</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">5,000</td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Teamlease</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">5,000</td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Safeeducate</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">5,000</td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
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
				<th style="background: #d9d9d9 ;border: 1px solid black;"align="left">Shiv Sakti</th>
				<td style="border: 1px solid black;background: #d9d9d9"></td>
				<td style="border: 1px solid black;background: #d9d9d9">5,000</td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
				<td style="border: 1px solid black;background: #ffcc00"></td>
			</tr>
		</table>
	</div>
	<button id="btnExport" onclick="fnExcelReport('tableToExport');" type="button"> EXPORT </button>
</html:form>
</body>