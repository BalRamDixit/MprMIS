<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>DASHBOARD</title>
   <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/charts.js"></script>
   <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/classic/resources/charts-all.css" rel="stylesheet" />
<script type="text/javascript">
      Ext.onReady(function() {
         Ext.create('Ext.chart.PolarChart', {
             renderTo: Ext.getElementById('pieChart'),
             width: 300,
             height: 200,
             store: {
                 fields: ['name', 'g1'],
                 data: [ {"name": "SC", "g1": 60} ,
                     {"name": "ST","g1": 25},
                     {"name": "GENERAL","g1": 67}
                 ]
             },//#67b7dc
             legend: { docked: 'left' },
             series: [{
                 type: 'pie', style: { colors : ['#f00', '#0f0', '#00f'] }, xField: 'g1', label: { field: 'name' }, donut: 30 // increase or decrease for increasing or decreasing donut area in middle.
             }]
      });
         
         Ext.create('Ext.chart.PolarChart', {
             renderTo: Ext.getElementById('pieChart1'),
             width: 300,
             height: 200,
             store: {
                 fields: ['name', 'g1'],
                 data: [ {"name": "Men", "g1": 60} ,
                     {"name": "Women","g1": 25}
                 ]
             },//#67b7dc
             legend: { docked: 'left' },
             series: [{
                 type: 'pie', xField: 'g1', label: { field: 'name' }, donut: 30 // increase or decrease for increasing or decreasing donut area in middle.
             }]
      });
         
         Ext.create('Ext.chart.PolarChart', {
             renderTo: Ext.getElementById('pieChart2'),
             width: 300,
             height:200,
             store: {
                 fields: ['name', 'g1'],
                 data: [ {"name": "Minority", "g1": 20} ,
                     {"name": "Others","g1": 60}
                 ]
             },//#67b7dc
             legend: { docked: 'left' },
             series: [{
                 type: 'pie', xField: 'g1', label: { field: 'name' }, donut: 30 // increase or decrease for increasing or decreasing donut area in middle.
             }]
      });
         
         Ext.create('Ext.chart.CartesianChart', {
             renderTo:  Ext.getElementById('lineChart1'),
             width: 295,
             height: 200,
             store: {
                 fields: ['name', 'g1', 'g2'],
                 data: [
                	 {"name": "Sep-15", "g1": 57, "g2": 59},
                     {"name": "Oct-15", "g1": 45, "g2": 50},
                     {"name": "Nov-15", "g1": 67, "g2": 43},
                     {"name": "Dec-15", "g1": 45, "g2": 18},
                     {"name": "Jan-16", "g1": 30, "g2": 90},
                     {"name": "Feb-16", "g1": 30, "g2": 90},
                     {"name": "Mar-16", "g1": 30, "g2": 90}
                 ]
             },

             legend: {
                 docked: 'bottom'
             },

             //define x and y axis.
             axes: [{
                 type: 'numeric',
                 position: 'left'
             }, {
                 type: 'category',
                 visibleRange: [0, 1],
                 position: 'bottom'
             }],

             //define the actual series
             series: [{
                 type: 'line',
                 xField: 'name',
                 yField: 'g1',
                 title: 'PPWS'
             }, {
                 type: 'line',
                 xField: 'name',
                 yField: 'g2',
                 title: 'Achieved'
             }]
         });   
         
         Ext.create('Ext.chart.CartesianChart', {
             renderTo:  Ext.getElementById('lineChart2'),
             width: 295,
             height: 200,
             store: {
                 fields: ['name', 'g1', 'g2'],
                 data: [
	                	 {"name": "Sep-15", "g1": 57, "g2": 59},
	                     {"name": "Oct-15", "g1": 45, "g2": 68},
	                     {"name": "Nov-15", "g1": 67, "g2": 43},
	                     {"name": "Dec-15", "g1": 45, "g2": 18},
	                     {"name": "Jan-16", "g1": 30, "g2": 90},
	                     {"name": "Feb-16", "g1": 30, "g2": 15},
	                     {"name": "Mar-16", "g1": 30, "g2": 90}
                 ]
             },

             legend: {
                 docked: 'bottom'
             },

             //define x and y axis.
             axes: [{
                 type: 'numeric',
                 position: 'left'
             }, {
                 type: 'category',
                 visibleRange: [0, 1],
                 position: 'bottom'
             }],

             //define the actual series
             series: [{
                 type: 'line',
                 xField: 'name',
                 yField: 'g1',
                 title: 'PPWS'
             }, {
                 type: 'line',
                 xField: 'name',
                 yField: 'g2',
                 title: 'Achieved'
             }]
         });   
         
         Ext.create('Ext.chart.CartesianChart', {
             renderTo:  Ext.getElementById('lineChart3'),
             width: 295,
             height: 200,
             store: {
                 fields: ['name', 'g1', 'g2'],
                 data: [
                        {"name": "Sep-15", "g1": 57, "g2": 26},
                        {"name": "Oct-15", "g1": 45, "g2": 50},
                        {"name": "Nov-15", "g1": 67, "g2": 43},
                        {"name": "Dec-15", "g1": 45, "g2": 54},
                        {"name": "Jan-16", "g1": 30, "g2": 90},
                        {"name": "Feb-16", "g1": 30, "g2": 51},
                        {"name": "Mar-16", "g1": 30, "g2": 60}
                 ]
             },

             legend: {
                 docked: 'bottom'
             },

             //define x and y axis.
             axes: [{
                 type: 'numeric',
                 position: 'left'
             }, {
                 type: 'category',
                 visibleRange: [0, 1],
                 position: 'bottom'
             }],

             //define the actual series
             series: [{
                 type: 'line',
                 xField: 'name',
                 yField: 'g1',
                 title: 'PPWS'
             }, {
                 type: 'line',
                 xField: 'name',
                 yField: 'g2',
                 title: 'Achieved'
             }]
         });
         
         
         Ext.create('Ext.chart.CartesianChart', {
             renderTo:  Ext.getElementById('lineChart4'),
             width: 295,
             height: 200,
             store: {
                 fields: ['name', 'g1', 'g2'],
                 data: [
                        {"name": "Item-0", "g1": 57, "g2": 35},
                        {"name": "Item-1", "g1": 45, "g2": 15},
                        {"name": "Item-2", "g1": 67, "g2": 20},
                        {"name": "Item-3", "g1": 45, "g2": 18},
                        {"name": "Item-4", "g1": 30, "g2": 56}
                 ]
             },

             legend: {
                 docked: 'bottom'
             },

             //define x and y axis.
             axes: [{
                 type: 'numeric',
                 position: 'left'
             }, {
                 type: 'category',
                 visibleRange: [0, 1],
                 position: 'bottom'
             }],

             //define the actual series
             series: [{
                 type: 'line',
                 xField: 'name',
                 yField: 'g1',
                 title: 'Expditure'
             }, {
                 type: 'line',
                 xField: 'name',
                 yField: 'g2',
                 title: 'Milestone 60%'
             }]
         });   
    });
      
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
  	
  	function handleHttpResponse(){
  		if (req.readyState == 4 && req.responseCode==200){	
  	    	var response = req.responseText; 
  	    	alert("Document Created");
  	  	}
  	}      
      
      
</script>
<body>
 <html:form action="login/report">	
 		<table width="100%" class="pageHeaderTable">
			<tr>
				<th align="center" class="pageHeader">DASHBOARD</th>
			</tr>
		</table>
<table id="tableToExport" width="100%" >
<tr><td>
<div>	 <table width="100%" > 
			<tr>
				<td><logic:present name="formBean">
	            		 	<logic:iterate id="formBean" name="formBean" >
								<table border="1" width="100%"><tr><th style="background: #c5c4c8">Social Gender Training Completion</th></tr></table>
								<table border="1" width="100%"><tr><td></td><th>SC</th><th>ST</th><th>GENERAL</th><th>Total</th></tr>
									<tr><th>Achieved till May</th><td>${formBean.sc}</td><td>${formBean.st}</td><td>${formBean2.general}</td><td>${formBean.scStGeneralTotal}</td></tr>
									<tr><th>Total Target</th><td>${formBean.scTotal }</td><td>${formBean.stTotal}</td><td>${formBean.generalTotal}</td><td>${formBean.scStGeneralTotalTarget}</td></tr>
							<tr><td></td><td>${formBean.scPercentage}</td><td>${formBean.stPercentage}</td><td>${formBean.generalPercentage}</td><td>${formBean.scStGeneralTotalPercentage}</td></tr></table>
							</logic:iterate>
						</logic:present>  
					<div id="pieChart"/> 
				</td>
				<td><logic:present name="formBean1">
	            		 	<logic:iterate id="formBean1" name="formBean1" >
	            		 	<table border="1" width="100%"><tr><th style="background: #c5c4c8">Gender Training Completion</th></tr></table>
							<table border="1" width="100%"><tr><td></td><th>Women</th><th>Men</th><th>Total</th></tr>
								<tr><th>Achieved till May</th><td>${formBean1.women}  </td><td>${formBean1.men}</td><td>${formBean1.Gendermentotal}</td></tr>
								<tr><th>Total Target</th><td>${formBean1.womenTotal}  </td><td>${formBean1.menTotal}</td><td>${formBean1.Genderwomentotal}</td></tr>
								<tr><th> </th><td>${formBean1.womenPercentage}  </td><td>${formBean1.menPercentage}</td><td>${formBean1.GendertotalPercentage}</td></tr></table>
							</logic:iterate>
						</logic:present>  
						<div id="pieChart1"/>
				</td>
				<td><logic:present name="formBean2">
	            		 	<logic:iterate id="formBean2" name="formBean2" >
	            		 		<table border="1" width="100%"><tr><th style="background: #c5c4c8;" >Social Gender Training Completion Minority Training Completion</th></tr></table>
								<table border="1" width="100%"><tr><td></td><th>Minority</th><th>Total</th></tr>
									<tr><th>Achieved till May</th><td>${formBean2.minority}</td><td>${formBean2.minoritytotal}</td></tr>
									<tr><th>Total Target</th><td>${formBean2.minorityTarget}</td><td>${formBean2.minorityTargettotal}</td></tr>
									<tr><th> </th><td>${formBean2.minorityPercentage}</td><td>${formBean2.minoritytotalPercentage}</td></tr></table>
							</logic:iterate>
						</logic:present>
						<div id="pieChart2"/>
				</td>
				</tr>
			</table>
	</div>
	<div>	<table>
				<tr>
					<td>
						<table border="1" width="100%"><tr><th style="background: #c5c4c8">Fund expenditure</th></tr></table>
						<div id="lineChart4"/>
					</td>
					<td>
						<table border="1" width="100%"><tr><th style="background: #c5c4c8">Training Commencement</th></tr></table>
						<div id="lineChart1"/>
					</td>
					<td>
						<table border="1" width="100%"><tr><th style="background: #c5c4c8">Training Completion</th></tr></table>
						<div id="lineChart2"/>
					</td>
					<td>
						<table border="1" width="100%"><tr><th style="background: #c5c4c8">Placed</th></tr></table>
						<div id="lineChart3"/>
					</td>
				</tr>  
			</table>
	</div>
	<div> <table width="100%">			
			<tr>
			<td><table border="1" width="100%">
						<logic:present name="formBean3">
	            		 	<logic:iterate id="formBean3" name="formBean3" >
								<tr><th style=" background: #c5c4c8" colspan="4">Placed</th></tr>
								<tr><th>Month</th><th >PPWS</th><th >Achieved</th><th >Percentage</th></tr>
								<tr><td >${formBean3.monthPlaced}</td><td >${formBean3.ppwsPlaced}</td><td >${formBean3.achievedPlaced}</td><td >${formBean3.percentagePlaced}</td></tr>
								<!-- <tr><td >Oct-15</td><td >236</td><td >236</td><td >100%</td></tr>
								<tr><td >Nov-15</td><td >306</td><td >271</td><td >89%</td></tr>
								<tr><td >Dec-15</td><td >516</td><td >411</td><td >80%</td></tr>
								<tr><td >Jan-16</td><td >586</td><td >444</td><td >76%</td></tr>
								<tr><td >Feb-16</td><td >796</td><td >479</td><td >60%</td></tr>
								<tr><td >Mar-16</td><td >831</td><td >508</td><td >61%</td></tr>
								<tr><td >Apr-16</td><td >831</td><td > </td><td >0%</td></tr>
								<tr><td >May-16</td><td >831</td><td > </td><td >0%</td></tr> -->
							</logic:iterate>
						</logic:present>
			</table>
			</td>
			<td>
				<table  border="1" width="100%">
						<logic:present name="formBean4">
	            		 	<logic:iterate id="formBean4" name="formBean4" >
								<tr><th style="background: #c5c4c8"  colspan="4">Commencement</th></tr>
								<tr><th >Month</th><th >PPWS</th><th >Achieved</th><th >Percentage</th></tr>
								<tr><td >${formBean4.monthCommencement}</td><td >${formBean4.ppwsCommencement}</td><td >${formBean4.achievedCommencement}</td><td >${formBean4.percentageCommencement}</td></tr>
								<!-- <tr><td >Oct-15</td><td >236</td><td >236</td><td >100%</td></tr>
								<tr><td >Nov-15</td><td >306</td><td >271</td><td >89%</td></tr>
								<tr><td >Dec-15</td><td >516</td><td >411</td><td >80%</td></tr>
								<tr><td >Jan-16</td><td >586</td><td >444</td><td >76%</td></tr>
								<tr><td >Feb-16</td><td >796</td><td >479</td><td >60%</td></tr>
								<tr><td >Mar-16</td><td >831</td><td >508</td><td >61%</td></tr>
								<tr><td >Apr-16</td><td >831</td><td > </td><td >0%</td></tr>
								<tr><td >May-16</td><td >831</td><td > </td><td >0%</td></tr> -->
							</logic:iterate>
						</logic:present>
					</table></td>
					<td>
					<table  border="1" width="100%">
						<logic:present name="formBean5">
	            		 	<logic:iterate id="formBean5" name="formBean5" >
								<tr><th style="background: #c5c4c8"  colspan="4">Completion</th></tr>
								<tr><th >Month</th><th >PPWS</th><th >Achieved</th><th >Percentage</th></tr>
								<tr><td >${formBean5.monthCompletion}</td><td >${formBean5.ppwsCompletion}</td><td >${formBean5.achievedCompletion}</td><td >${formBean5.percentageCompletion}</td></tr>
								<!-- <tr><td >Oct-15</td><td >236</td><td >236</td><td >100%</td></tr>
								<tr><td >Nov-15</td><td >306</td><td >271</td><td >89%</td></tr>
								<tr><td >Dec-15</td><td >516</td><td >411</td><td >80%</td></tr>
								<tr><td >Jan-16</td><td >586</td><td >444</td><td >76%</td></tr>
								<tr><td >Feb-16</td><td >796</td><td >479</td><td >60%</td></tr>
								<tr><td >Mar-16</td><td >831</td><td >508</td><td >61%</td></tr>
								<tr><td >Apr-16</td><td >831</td><td > </td><td >0%</td></tr>
								<tr><td >May-16</td><td >831</td><td > </td><td >0%</td></tr> -->
							</logic:iterate>
						</logic:present>
					 </table>
					</td>
			</tr></table>
	</div>
	<div><table>
			<tr><td>
					<table border="1" width="100%">
						<logic:present name="formBean6">
	            		 	<logic:iterate id="formBean6" name="formBean6" >
								<tr> <th colspan="3" style="background: #c5c4c8">General</th> </tr>
								<tr> <th >File No.</th> <td >${formBean6.fileNo}</td></tr>
								<tr> <th >CTSA</th> <td >${formBean6.ctsa}</td></tr>
								<tr> <th >Total target</th> <td class="amount">${formBean6.totalTarget}</td></tr>
								<tr> <th >Total Cost  Rs.</th> <td class="amount"> ${formBean6.totalCost}</td> </tr>
								<tr> <th >Fund released  Rs.</th> <td class="amount"> ${formBean6.fundReleased}</td> </tr>
							</logic:iterate>
						</logic:present>
					</table>
					<table border="1" width="100%">
						<logic:present name="formBean12">
	            		 	<logic:iterate id="formBean12" name="formBean12" >
								<tr><th style="background: #c5c4c8">Residential / Non-Residential</th><td>${formBean12.residential}</td> </tr>
								<tr><th >TC In-Charge Name</th><td >${formBean12.inchargeName}</td></tr>
								<tr><th >TC In-Charge Mobile no.</th><td >${formBean12.inchargemobile}</td></tr>
								<tr><th >Status of TC</th><td >${formBean12.statusTC}</td></tr>
							</logic:iterate>
						</logic:present>
					</table>
				</td>
	 			<td>
					<table border="1">
						<logic:present name="formBean7">
	            		 	<logic:iterate id="formBean7" name="formBean7" >
								<tr> <th style="background: #c5c4c8" colspan="4">Key Dates</th> </tr>
								<tr><td  colspan="2"></td> <th colspan="2">Standards Actual</th></tr>
								<tr> <th >Project Sanction (I2)</th> <td >${formBean7.projectSanction}</td><td  colspan="2"></td></tr>
								<tr><th >MoU Signing (I3)</th> <td >${formBean7.mouSig}</td><td >20 days from I2</td><td >${formBean7.days20}</td></tr>
								<tr> <th >1st Installment release (I4)</th> <td >${formBean7.firstInstallment}</td><td >10 days from I2)</td><td >${formBean7.days10}</td></tr>
								<tr> <th >Project Commencement (E1)</th> <td >${formBean7.ProjectCommencement}</td><td >30 days from I4</td><td >${formBean7.days30}</td></tr>
								<tr> <th >PER approval (I5)</th> <td >${formBean7.perApproval}</td><td >15 days form I4</td><td >${formBean7.days15}</td></tr>
								<tr> <th >Training Commencement (E1a)</th> <td >${formBean7.trainingCommencement}</td><td >90 days from E1</td><td >${formBean7.days90}</td></tr>
							</logic:iterate>
						</logic:present>
					</table>
				</td>
				<td>
					<table width="100%"  border="1">
						<logic:present name="formBean8">
	            		 	<logic:iterate id="formBean8" name="formBean8" >
								<tr><th style="background: #c5c4c8" width="50%">Trade Sanctioned</th><td >${formBean8.tradeSanctioned}</td></tr>
							</logic:iterate>
						</logic:present>
					</table>	
					<table width="100%" border="1">
						<logic:present name="formBean13">
	            		 	<logic:iterate id="formBean13" name="formBean13" >
								<tr><th style="background: #c5c4c8"  rowspan="3" width="50%">Districts Covered</th><td >Saraikela-Kharsawan</td></tr>
								<tr><td >${formBean13.districtsCovered}</td></tr>
							</logic:iterate>
						</logic:present>
					</table>
					<table width="100%" border="1">
						<logic:present name="formBean11">
	            		 	<logic:iterate id="formBean11" name="formBean11" >
								<tr><th style="background: #c5c4c8"  colspan="4">Financials from PFMS</th></tr>
								<tr><th >Fund released</th><td >${formBean11.FundreleasedPfms}</td></tr>
								<tr><th >Year</th><th >Expenditure</th><th >Cum Exp</th><th >Expditure</th></tr>
								<tr><td >${formBean11.Year}</td><td >${formBean11.Expenditure}</td><td >${formBean11.cumExp}</td><td >${formBean11.Expditure}</td></tr>
								<%-- <tr><td >${formBean11.field}</td><td >${formBean11.field}</td><td >${formBean11.field}</td><td >${formBean11.field}</td></tr>
								<tr><td >${formBean11.field}</td><td >${formBean11.field}</td><td >${formBean11.field}</td><td >${formBean11.field}</td></tr> --%>
							</logic:iterate>
						</logic:present>
				</table>
				</td>
				<td>
					<table border="1">
						<logic:present name="formBean9">
	            		 	<logic:iterate id="formBean9" name="formBean9" >
								<tr><th style="background: #c5c4c8"  colspan="2">Alerts and Advisories</th></tr>
								<tr><th >Type of alert/SCN</th><td >${formBean9.alertType}</td></tr>
								<tr><th >Date of Issue of Alert /</th><td >${formBean9.alertIssueDate}</td></tr>
								<tr><th >Reason</th><td >${formBean9.alertReason}</td></tr>
								<tr><th >No. Advisories issued by CTSA</th><td>${formBean9.advisoriesCTSA}</td></tr>
							</logic:iterate>
						</logic:present>
					</table>
					<table  width="100%" border="1">
						<logic:present name="formBean10">
	            		 	<logic:iterate id="formBean10" name="formBean10" >
								<tr><th colspan="2" style="background: #c5c4c8" >Achievement till May 2016</th></tr>
								<tr><th >Total Trained</th><td >${formBean10.totalTrained}</td></tr>
								<tr><th >Total Placed</th><td >${formBean10.totalPlaced}</td></tr>
							</logic:iterate>
						</logic:present>
					</table>
				</td>
			</tr></table></div></td></tr></table>
		<button id="btnExport" onclick="fnExcelReport('tableToExport');" type="button"> EXPORT </button>	
<!-- 		<button id="btnExport" onclick="createPdf()" type="button">EXPORT To PDF</button>	  -->
</html:form>
</body>
