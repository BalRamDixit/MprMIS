 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
<head>
<script>

$(document).ready(function () {
	$('#example').DataTable();
		
});

</script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>





<body>
<html:form action="login/yearWiseProgressReport">

            <table width="100%" class="pageHeaderTable" >
			  <tr>
				<td align="center" class="pageHeader">REPORT 7:PROJECT-WISE CATEGORY-WISE ACHIEVEMENT</td>
			  </tr>
			</table>
  
        <div style="overflow-x:scroll;overflow-y:scroll;" >	
		    
		<!--  For Second Report-->
		<table width="100%" border="1" id="example" class="display">
	        <thead>
			<tr>
			  <th> S.No </th>
	          <th> PIA</th>
	          <th colspan="2"> TOTAL</th>
	          <th colspan="2">SC</th>
	          <th colspan="2">ST</th>
	          <th colspan="2">MINORITY</th>
	          <th colspan="2">WOMEN</th>
	          <th colspan="2">PWD</th>
		   </tr>
			</thead>
			
			<thead>
			<tr>    <td></td>
			        <td></td>	
					<th> T </th>
					<th> P </th>
					<th> T </th>
					<th> P </th>
					<th> T </th>
					<th> P </th>
					<th> T </th>
					<th> P </th>
					<th> T </th>
					<th> P </th>

					<th> T </th>
					<th> P </th>
					
			  </tr>
			</thead>
			
			<tbody>
			<logic:present name="progressReportList7">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="progressReportList7" name="progressReportList7">
					<tr>
					 <td align="center">${indexCount}</td>  
					 <td> ${1}</td> 
					 <td> ${2}</td>
	                 <td> ${3}</td>
					 <td> ${4} </td>
					 <td> ${5} </td>
					 <td> ${6} </td>
					 <td> ${7} </td>
					 <td> ${8} </td>
					</tr>
				  <c:set var="indexCount" value="${indexCount + 1}" />	
			   </logic:iterate>
		   </logic:present>
			</tbody>
			
			
		</table>
							
		</div>  
  
</html:form>
</body>

</html> 

<%-- <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
             height: 190,
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
             width: 280,
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
             width: 280,
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
             width: 280,
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
             width: 280,
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
 <html:form action="login/yearWiseProgressReport">	
 		<table width="100%" class="pageHeaderTable">
			<tr>
				<th align="center" class="pageHeader">DASHBOARD</th>
			</tr>
		</table>
		<table id="tableToExport" width="100%" >
		<tr><td>
<div>		
		<table width="100%" >
			<tr>
				<td><table border="1" width="100%"><tr><th style="background: #c5c4c8">Social Gender Training Completion</th></tr></table>
					<div id="pieChart"/>
					<table border="1" width="100%" ><tr><td></td><th>SC</th><th>ST</th><th>GENERAL</th><th>Total</th></tr>
							<tr><th>Achieved till May</th><td>32</td><td>325</td><td>65</td><td>422</td></tr>
							<tr><th>Total Target</th><td>397</td><td>877</td><td>564</td><td>2088</td></tr>
							<tr><td></td><td>8%</td><td>37%</td><td>12%</td><td>20%</td></tr></table>
				</td>
				<td><table border="1" width="100%"><tr><th style="background: #c5c4c8">Gender Training Completion</th></tr></table>
					<div id="pieChart1"/>
					<table border="1" width="100%"><tr><td></td><th>Women</th><th>Men</th><th>Total</th></tr>
					<tr><th>Achieved till May</th><td>118  </td><td>304</td><td>422</td></tr>
					<tr><th>Total Target</th><td>689  </td><td>1399</td><td>2088</td></tr>
					<tr><th> </th><td>17%  </td><td>22%</td><td>20%</td></tr></table>  
				</td>
				<td><table border="1" width="100%"><tr><th style="background: #c5c4c8">Social Gender Training Completion Minority Training Completion</th></tr></table>
					<div id="pieChart2"/>
					<table border="1" width="100%"><tr><td></td><th>Minority</th><th>Total</th></tr>
					<tr><th>Achieved till May</th><td>118</td><td>422</td></tr>
					<tr><th>Total Target</th><td>510</td><td>2088</td></tr>
					<tr><th> </th><td>35%</td><td>20%</td></tr></table>
				</td>
				</tr></table>
</div>
<div>		<table>
				<tr><td>
				<table border="1" width="100%"><tr><th style="background: #c5c4c8">Fund expenditure</th></tr></table>
				<div id="lineChart4"/>
			</td>
			<td><table border="1" width="100%"><tr><th style="background: #c5c4c8">Training Commencement</th></tr></table>
				<div id="lineChart1"/>
			</td>
			<td><table border="1" width="100%"><tr><th style="background: #c5c4c8">Training Completion</th></tr></table>
				<div id="lineChart2"/>
			</td>
			<td><table border="1" width="100%"><tr><th style="background: #c5c4c8">Placed</th></tr></table>
				<div id="lineChart3"/>
			</td>
			</tr>  
</table></div>
<div><table width="100%">			
			<tr>
			<td><table border="1" width="100%">
					<tr><th style="background: #c5c4c8" colspan="4">Placed</th></tr>
					<tr><th>Month</th><th >PPWS</th><th >Achieved</th><th >Percentage</th></tr>
					<tr><td >Sep-15</td><td >210</td><td >210</td><td >100%</td></tr>
					<tr><td >Oct-15</td><td >236</td><td >236</td><td >100%</td></tr>
					<tr><td >Nov-15</td><td >306</td><td >271</td><td >89%</td></tr>
					<tr><td >Dec-15</td><td >516</td><td >411</td><td >80%</td></tr>
					<tr><td >Jan-16</td><td >586</td><td >444</td><td >76%</td></tr>
					<tr><td >Feb-16</td><td >796</td><td >479</td><td >60%</td></tr>
					<tr><td >Mar-16</td><td >831</td><td >508</td><td >61%</td></tr>
					<tr><td >Apr-16</td><td >831</td><td > </td><td >0%</td></tr>
					<tr><td >May-16</td><td >831</td><td > </td><td >0%</td></tr>
			</table>
			</td>
			<td>
				<table  border="1" width="100%">
						<tr><th style="background: #c5c4c8"  colspan="4">Commencement</th></tr>
						<tr><th >Month</th><th >PPWS</th><th >Achieved</th><th >Percentage</th></tr>
						<tr><td >Sep-15</td><td >210</td><td >210</td><td >100%</td></tr>
						<tr><td >Oct-15</td><td >236</td><td >236</td><td >100%</td></tr>
						<tr><td >Nov-15</td><td >306</td><td >271</td><td >89%</td></tr>
						<tr><td >Dec-15</td><td >516</td><td >411</td><td >80%</td></tr>
						<tr><td >Jan-16</td><td >586</td><td >444</td><td >76%</td></tr>
						<tr><td >Feb-16</td><td >796</td><td >479</td><td >60%</td></tr>
						<tr><td >Mar-16</td><td >831</td><td >508</td><td >61%</td></tr>
						<tr><td >Apr-16</td><td >831</td><td > </td><td >0%</td></tr>
						<tr><td >May-16</td><td >831</td><td > </td><td >0%</td></tr>
					</table></td>
					<td>
					
				</td>
			</tr></table></div></td></tr></table>
		<button id="btnExport" onclick="fnExcelReport('tableToExport');" type="button"> EXPORT </button>	
			 
</html:form>
</body>
 --%>


