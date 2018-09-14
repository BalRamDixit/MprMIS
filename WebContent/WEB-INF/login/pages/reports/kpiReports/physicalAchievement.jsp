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
			<h3 align="center">PHYSICAL ACHIEVEMENT </h3>
			
			 
			 <div style="overflow-x:scroll;overflow-y:scroll;width:1090px;height:490px;" >	
			 <table id="exportTable" style="border-collapse:collapse;">	
			 <tr style="background: #b8d1f3;">
			
			 <th style="padding:7px;border: 1px solid black;background: #D6EAF8;">
					<table width="100%" style="border-collapse:collapse;" >
						<tr style="background: #b8d1f3;">
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Number of candidates (unless specified)</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Action Plan Target</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Cumulative achievement of ongoing projects</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">% Achieved</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Target (FY 2016-17)</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Actual Achievement(FY 16-17)</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">% Achieved</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Achieved during this month</th> 							 							
 						</tr>								 
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">Training commenced</td>
					<td style="padding:7px;border: 1px solid black;">173,629 </td>
					<td style="padding:7px;border: 1px solid black;">59,135</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">52,089</td>
					<td style="padding:7px;border: 1px solid black;">53,390 </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">1,853 </td>
				</tr> 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Training completed</td>
				 <td style="padding:7px;border: 1px solid black;">173,629 </td>
				 <td style="padding:7px;border: 1px solid black;">53,691</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">52,089 </td>
				 <td style="padding:7px;border: 1px solid black;">23,205</td>
				 <td style="padding:7px;border: 1px solid black;">45% </td>
				 <td style="padding:7px;border: 1px solid black;">1,248</td>
				 </tr>	 
				 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">SC/ST</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">- </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Women</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">- </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Minority </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Pro-Life </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">345 </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">345 </td>
				 <td style="padding:7px;border: 1px solid black;"> 5%</td>
				 <td style="padding:7px;border: 1px solid black;">345 </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Assessment completed </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">52,089 </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Certified </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">52,089 </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Appointed  </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">23,205 </td>
				 <td style="padding:7px;border: 1px solid black;">39,249 </td>
				 <td style="padding:7px;border: 1px solid black;">169% </td>
				 <td style="padding:7px;border: 1px solid black;">1,287</td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Placed (for 3 months)</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;">23,205</td>
				 <td style="padding:7px;border: 1px solid black;">35,348 </td>
				 <td style="padding:7px;border: 1px solid black;">152% </td>
				 <td style="padding:7px;border: 1px solid black;">1,186</td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Retained for 12 months</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Foreign placements</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td>Placements over Rs. 15000</td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 <td style="padding:7px;border: 1px solid black;"> </td>
				 </tr>	 
				 <tr style="background: #b8d1f3;">
				 <td style="padding:7px;border: 1px solid black;">Average person months of placement provided </td>
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
						<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="center" width="50%" colspan="2">Champion Employers</th>
							 					 							
 					</tr>								 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Sanctioned target</td>
					<td style="padding:7px;border: 1px solid black;">Cumulative achievement</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> 1853</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">1248 </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> 345</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> 1287</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">1186 </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				
					</table>
					</th>
					</tr>
					
					
					</table><p>*FY 16-17 Target is 30% of Action Plan Target, __% for 17-18 and __ for 18-19.
                    Coloring of cells will be based on business logic provided.</p>
					
				</div>
		<button id="btnExport" onclick="fnExcelReport('exportTable');"> EXPORT </button>	 	 
	
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<!--Main form section ends here-->