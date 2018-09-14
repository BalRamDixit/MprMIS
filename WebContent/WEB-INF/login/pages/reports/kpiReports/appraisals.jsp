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
			<h3 align="center">Appraisals</h3>
			
			 
			 <div style="overflow-x:scroll;overflow-y:scroll;width:1090px;height:490px;" >	
			 <table id="exportTable" style="border-collapse:collapse;">	
			 <tr style="background: #b8d1f3;">
			 
				
				<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">	
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">(in numbers of candidates)</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Total</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Long term(>= 1152 hours)</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Short term(<=1152 hours)</th>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">target remaining to be covered by fresh project sanctions(16-19)- as of FY 16 beginning</td>
					<td style="padding:7px;border: 1px solid black;">101,036</td>
					<td style="padding:7px;border: 1px solid black;">40,129</td>
					<td style="padding:7px;border: 1px solid black;">60,907</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Target covered by fresh proposals submitted online </td>
					<td style="padding:7px;border: 1px solid black;">81,450</td>
					<td style="padding:7px;border: 1px solid black;">780</td>
					<td style="padding:7px;border: 1px solid black;">80,670</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Target covered by approved/sanctioned projects in FY 16 </td>
					<td style="padding:7px;border: 1px solid black;">7,480</td>
					<td style="padding:7px;border: 1px solid black;">-</td>
					<td style="padding:7px;border: 1px solid black;">7480</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Target remaining to be sanctioned </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr> 
				</table>
				<br />
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Champion Employers Figures in number of projects-FY 16-17(Cumulative)</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"># of projects</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Cleared for next stage</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Rejected</th>
					<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">Pending</th>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Total proposals received</td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">CTSA feedback received/State Govt recommended </td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Considered by PAC</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">First Installment released </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"></td>
				</tr> 
				</table>
				
			 </th>
			 <th>
					<table width="100%" style="border-collapse:collapse;" >
						<tr style="background: #b8d1f3;">
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">FY 16-17 (Cumulative)(Figures in number of projects)</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left"  align="center"># of projects </th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Cleared for next stage</th>
							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left" >Rejected</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">Pending</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">0-2 month</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">2-4 months</th>
 							<th style="padding:7px;border: 1px solid black;background: #D6EAF8;"align="left">4+ months</th>
 							
													 							
 						</tr>								 
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">Total propsals received</td>
					<td style="padding:7px;border: 1px solid black;"> 111 </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">  </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">25</td>
					<td style="padding:7px;border: 1px solid black;">20</td>
					<td style="padding:7px;border: 1px solid black;">66 </td>
					
				</tr> 
				
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">Initial screening completed</td>
					<td style="padding:7px;border: 1px solid black;">  </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">  </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr> 
					
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">PIA has paid appraisal fee</td>
					<td style="padding:7px;border: 1px solid black;"> 18 </td>
					<td style="padding:7px;border: 1px solid black;">15 </td>
					<td style="padding:7px;border: 1px solid black;"> 3 </td>
					<td style="padding:7px;border: 1px solid black;"> 93</td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr> 
				 <tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">Quality appraisal completed</td>
					<td style="padding:7px;border: 1px solid black;"> 11 </td>
					<td style="padding:7px;border: 1px solid black;"> 10</td>
					<td style="padding:7px;border: 1px solid black;"> 1</td>
					<td style="padding:7px;border: 1px solid black;"> 4</td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr> 
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">CTSA feedback received/State Govt recommended</td>
					<td style="padding:7px;border: 1px solid black;"> 10 </td>
					<td style="padding:7px;border: 1px solid black;"> 10</td>
					<td style="padding:7px;border: 1px solid black;"> 0</td>
					<td style="padding:7px;border: 1px solid black;"> 0</td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr>
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">Considered by PAC</td>
					<td style="padding:7px;border: 1px solid black;"> 10 </td>
					<td style="padding:7px;border: 1px solid black;">9 </td>
					<td style="padding:7px;border: 1px solid black;"> 1 </td>
					<td style="padding:7px;border: 1px solid black;"> 0</td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr> 
				<tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">Sanctioned</td>
					<td style="padding:7px;border: 1px solid black;"> 6 </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">  </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr> 
			  <tr style="background: #b8d1f3;">	
					<td style="padding:7px;border: 1px solid black;">First Installment Released</td>
					<td style="padding:7px;border: 1px solid black;"> 0 </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;">  </td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"></td>
					<td style="padding:7px;border: 1px solid black;"> </td>
					
				</tr> 
			    </table>
				</th>
			</tr>
					
			</table>
			<ul>
			  <li>Pending = Cleared in previous stage - completed in this stage </li>
			  <li>Rejected = Completed in this stage - cleared in this stage</li>
			  <li>Design SRLM level input sheet - Master table for all existing proposals(we upload this)</li>
			</ul>
					
		</div>
				
				 
		<button id="btnExport" onclick="fnExcelReport('exportTable');"> EXPORT </button>		 
			 	 
	
</html:form>
</body>
