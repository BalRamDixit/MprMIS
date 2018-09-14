<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
<head>
 <script type="text/javascript" src="/assets/script/canvasjs.min.js"></script>
<script>

$(document).ready(function () {
	$('#example').DataTable();
		
});

/*     /////////////////////////////    */
window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
      text: "Adding dataPoints"  
      },
      data: [
      {        
        type: "column",
        dataPoints: [
        { y: 71 },
        { y: 55},
        { y: 50 },
        { y: 65 },
        { y: 95 },
        { y: 68 },
        { y: 28 },
        { y: 34 },
        { y: 14}
      
        ]
      }
      ]
    });

    chart.render();
  }
</script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>





<body>
<html:form action="login/yearWiseProgressReport">

            <table width="100%" class="pageHeaderTable" >
			  <tr>
				<td align="center" class="pageHeader">REPORT 2:STATE-WISE CUM MONTH-WISE PROGRESS</td>
			  </tr>
			</table>
  
  
<!--   <div id="chartContainer" style="height: 300px; width: 100%;">
  </div> -->
  
        <div style="overflow-x:scroll;overflow-y:scroll;" >	
		    
		<!--  For Second Report-->
		<table width="100%" border="1" id="example" class="display">
	        <thead>
			<tr>
			  <th> S.No </th>
	          <th> State Name</th>
	          <th colspan="2"> APR</th>
	          <th colspan="2">MAY</th>
	          <th colspan="2">JUN</th>
	          <th colspan="2">JUL</th>
	          <th colspan="2">AUG</th>
	          <th colspan="2">SEP</th>
	          <th colspan="2">OCT</th>
	          <th colspan="2">NOV</th>
	          <th colspan="2">DEC</th>
	          <th colspan="2">JAN</th>
	          <th colspan="2">FEB</th>
	          <th colspan="2">MAR</th>
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
			<logic:present name="progressReportList2">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="progressReportList2" name="progressReportList2">
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
					 <td> ${9} </td>
					 <td> ${10} </td>
					 
					 <td> ${11} </td>
					 <td> ${12} </td>
					 <td> ${13} </td>
					 <td> ${14} </td>
					 <td> ${15} </td>
					 <td> ${16} </td>
					 <td> ${17} </td>
					 <td> ${18} </td>
					 <td> ${19} </td>
					 <td> ${20} </td>
					 <td> ${21} </td>
					 <td> ${22} </td>
					 <td> ${23} </td>
					 <td> ${24} </td>
					 <td> ${25} </td>
					 
					 
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

<%-- 
<!DOCTYPE HTML>
<html>
<head>  
  <script type="text/javascript">
  window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
      text: "Adding dataPoints"  
      },
      data: [
      {        
        type: "column",
        dataPoints: [
        { y: 71 },
        { y: 55},
        { y: 50 },
        { y: 65 },
        { y: 95 },
        { y: 68 },
        { y: 28 },
        { y: 34 },
        { y: 14}
      
        ]
      }
      ]
    });

    chart.render();
  }
  </script>
 <script type="text/javascript" src="/assets/script/canvasjs.min.js"></script></head>
<body>
  <div id="chartContainer" style="height: 300px; width: 100%;">
  </div>
</body>
</html> --%>