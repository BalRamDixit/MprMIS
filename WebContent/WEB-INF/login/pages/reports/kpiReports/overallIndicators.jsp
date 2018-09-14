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
			<h3 align="center">Overall indicators</h3>
			
			 
			 <div style="overflow-x:scroll;overflow-y:scroll;width:1090px;height:490px;" >	
			 <table id="exportTable" style="border-collapse:collapse;">	
			 <tr style="background: #b8d1f3;">
			 
				
				<th style="padding:7px;border: 1px solid black;background: #D6EAF8;">	
				<table width="100%" style="border-collapse:collapse;">
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;" >AP state</td>
					<td style="padding:7px;border: 1px solid black;">Yes</td>
					
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">AP status since</td>
					<td style="padding:7px;border: 1px solid black;">2012</td>
					
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">SRLM</td>
					<td style="padding:7px;border: 1px solid black;">ORMAS</td>
					
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Department </td>
					<td style="padding:7px;border: 1px solid black;">Panchayati Raj</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">CTSA </td>
					<td style="padding:7px;border: 1px solid black;">NABCONS </td>
					
				</tr> 
				</table>
				
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of posts vacant</td>
					<td style="padding:7px;border: 1px solid black;">0</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Whether COO is posted</td>
					<td style="padding:7px;border: 1px solid black;">Yes</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of SPMs posted</td>
					<td style="padding:7px;border: 1px solid black;">9</td>
				</tr>
				 
				</table>
				<br></br>
				
				<table width="100%" style="border-collapse:collapse;">
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Appraisal agency empaneled</td>
					<td style="padding:7px;border: 1px solid black;">No</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Name of Appraisal agency</td>
					<td style="padding:7px;border: 1px solid black;">NA</td>
				</tr> 
				</table>
				<br></br>
				<table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Whether state has MIS</td>
					<td style="padding:7px;border: 1px solid black;">Yes</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Whether MIS is SOP compliant</td>
					<td style="padding:7px;border: 1px solid black;">NO</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Whether MIS is connected to Central MIS</td>
					<td style="padding:7px;border: 1px solid black;">NO</td>
				</tr>
				
				 
				</table>
				
			 </th>
			 <th style="padding:7px;border: 1px solid black;background: #D6EAF8;">
			 <table width="100%" style="border-collapse:collapse;" >
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Whether State TSA is empaneled</td>
					<td style="padding:7px;border: 1px solid black;">Yes</td>
				</tr> 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">TSA Name</td>
					<td style="padding:7px;border: 1px solid black;">PWC</td>
				</tr> 
			 </table>
			<br></br>
				<table width="100%" style="border-collapse:collapse;">
				<tr style="background: #b8d1f3;"> 
					<td colspan="2" style="padding:7px;border: 1px solid black;">(in number of candidates)</td>
					
				</tr>										 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Action Plan target(FY 2016-FY 2019)</td>
					<td style="padding:7px;border: 1px solid black;">173,629</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Target sanctioned by sate, by EC July 2015</td>
					<td style="padding:7px;border: 1px solid black;">72,593</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Training Target achievement</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				</table>
			<br></br>
				<table width="100%" style="border-collapse:collapse;">
														 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">No of job fairs held in state</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of candidates placed from job fairs(cumulative)</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr> 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of candidates placed through industrial internships</td>
					<td style="padding:7px;border: 1px solid black;"> </td>
				</tr>
				</table>
				
			 </th>
			
			
			 <th style="padding:7px;border: 1px solid black;background: #D6EAF8;">
			 <table width="100%" style="border-collapse:collapse;">
														 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of ongoing projects</td>
					<td style="padding:7px;border: 1px solid black;">80</td>
				</tr> 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of projects yet to be registered on PFMS</td>
					<td style="padding:7px;border: 1px solid black;">3</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Number of projects for which expenditures are not yet recorded on PFMS</td>
					<td style="padding:7px;border: 1px solid black;">36</td>
				</tr>
			 </table>
				<br></br>
				<table width="100%" style="border-collapse:collapse;">
														 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Total Training Centres</td>
					<td style="padding:7px;border: 1px solid black;">201</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Active Training Centres</td>
					<td style="padding:7px;border: 1px solid black;">80</td>
				</tr> 
				</table>
				<br></br>
				<table width="100%" style="border-collapse:collapse;" >
														 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">PPWS Target*</td>
					<td style="padding:7px;border: 1px solid black;">13,022</td>
				</tr> 
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Active TC Capacity</td>
					<td style="padding:7px;border: 1px solid black;">9,038 </td>
				</tr> 
				 <tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">% Capacity utilization(under training/ Active TC capacity)</td>
					<td style="padding:7px;border: 1px solid black;"> %</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Under training</td>
					<td style="padding:7px;border: 1px solid black;"> %</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">Candidates with more than 75% attendance in month</td>
					<td style="padding:7px;border: 1px solid black;"> -</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">% Candidates with bank accounts</td>
					<td style="padding:7px;border: 1px solid black;"> -</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">% candidates with aadhaar</td>
					<td style="padding:7px;border: 1px solid black;"> -</td>
				</tr>
				<tr style="background: #b8d1f3;"> 
					<td style="padding:7px;border: 1px solid black;">% candidates with insurance</td>
					<td style="padding:7px;border: 1px solid black;"> -</td>
				</tr>
				</table>
				
			 </th>
			
			
			</tr>
					
			</table>
					
		</div>
				
				 
		<button id="btnExport" onclick="fnExcelReport('exportTable');"> EXPORT </button>		 
			 	 
	
</html:form>
</body>
