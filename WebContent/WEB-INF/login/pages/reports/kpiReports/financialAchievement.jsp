<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<!--script for menu-->
<script type="text/javascript">
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

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)){
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"FileName.xls");
    }  
    else{
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));
    }
    return (sa);
}
function closePage()
{
	document.getElementById("newRec").style.display="none";
	$('.CompareButton').show();
} 
  
</script>
<body>
 <html:form action="login/report">	
			<iframe id="txtArea1" style="display:none"></iframe>
			<h3 align="center">FINANCIAL ACHIEVEMENT(all figures in Rs. Crores)</h3>
			
			 
			 <div style="overflow-x:scroll;overflow-y:scroll;width:1090px;height:490px;" >	
			 <table id="exportTable" style="border-collapse:collapse;">	
			 <tr style="background: #b8d1f3;">
			 <th style="padding:7px;border: 1px solid black;background: #D6EAF8;">
					<table width="100%" style="border-collapse:collapse;"  >
						<tr>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;" align="left"></th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left" colspan="4" align="center">Project Cost</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left" colspan="3">Other Cost</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left" rowspan="2">Total</th>
													 							
 						</tr>								 
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;"width="20%">Cumulative and FY 16-17(Rs. Crores)</td>
					<td style="padding:7px;border: 1px solid black;"width="10%">Programme cost </td>
					<td style="padding:7px;border: 1px solid black;"width="10%">Support cost</td>
					<td style="padding:7px;border: 1px solid black;"width="10%">CTSA </td>
					<td style="padding:7px;border: 1px solid black;"width="10%">Total</td>
					<td style="padding:7px;border: 1px solid black;"width="10%">Admin cost </td>
					<td style="padding:7px;border: 1px solid black;"width="10%">Capacity Building</td>
					<td style="padding:7px;border: 1px solid black;"width="10%">Total Other </td>
					
				</tr> 
				
				<tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Total Amount Sanction</td>
				 <td style="padding:7px;border: 1px solid black;">1279.12</td>
				 <td style="padding:7px;border: 1px solid black;"></td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"></td>
				 <td style="padding:7px;border: 1px solid black;"></td>
				 <td style="padding:7px;border: 1px solid black;"></td>
				 <td style="padding:7px;border: 1px solid black;"></td>
				 </tr>
					
				<tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">Central</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>
				 <tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">State</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>
				<tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">Released to State-Central share</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>
				<tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">Total received by SRLM</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	
				<tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">Central share</td>
				 	<td style="padding:7px;border: 1px solid black;"> 65.60</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
			   </tr>
			  <tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">State share</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
			  </tr>
			  <tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">Total Utilized</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
			  </tr>
			  <tr style="background: #b8d1f3;">
				    <td style="padding:7px;border: 1px solid black;">Utilized %</td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
				    <td style="padding:7px;border: 1px solid black;"> </td>
				 	<td style="padding:7px;border: 1px solid black;"> </td>
			  </tr>
				
				 </table>
					</th>
				
				<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">	
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;" width="60%">Last UC submitted</td>
					<td style="padding:7px;border: 1px solid black;"width="40%"></td>
				</tr> 
				</table>
				
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"width="60%">Additional fund requirement for current year(till March 2017)</td>
					<td style="padding:7px;border: 1px solid black;"width="40%">-</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Fund requirement for next year(FY 17-18	)</td>
					<td style="padding:7px;border: 1px solid black;">-</td>
				</tr> 
				</table>
				
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;" width="60%">Current month overview</td>
					<td  style="padding:7px;border: 1px solid black;"width="40%">FY 16-17(Cumulative till month)</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Opening balance(FY 16-17)</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Opening balance(FY 16-17)</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Fund released- Centre</td>
					<td style="padding:7px;border: 1px solid black;">137.96</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Fund released- State</td>
					<td style="padding:7px;border: 1px solid black;"> 65.60</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Interest earned</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Recovery and penalties</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Fund utilized</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Closing balance</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Last Bank reconciliation(Cr)</td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				</table>
					
					</th>
					</tr>
					
			</table>
					
		</div>
				
				 
		<button id="btnExport" onclick="fnExcelReport('exportTable');"> EXPORT </button>		 
			 	 
	
</html:form>
</body>
