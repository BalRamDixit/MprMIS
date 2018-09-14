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
					<td align="center" class="pageHeader">REPORT 1:STATE-WISE CUM YEAR-WISE PROGRESS</td>
			   </tr>
			</table>
  
             <!-- <div style="overflow-x:scroll;overflow-y:scroll;" >	 -->
		
					<!-- <table width="100%" class="inputTBL"  >
				<tr>
					<th> State Name</th>
					<th colspan="2">FY2012-13</th>
					<th colspan="2">FY2013-14</th>
					<th colspan="2">FY2014-15</th>
					<th colspan="2">FY2015-16</th>
 					<th colspan="2">FY2016-17</th>
 												 							
 			   </tr>								 
				<tr>
				    <td>  </td>	
				    <td>  </td>	
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
			
			    </table> -->
			    
			<!--   <div style="overflow-x:scroll;overflow-y:scroll;" >    -->
		    <table width="100%" border="1" id="example" class="display">
	        <thead>
			<tr>
			  <th> S.No </th>
	          <th> State Name</th>
	          <th colspan="2"> FY2012-13</th>
	          <th colspan="2">FY2013-14</th>
	          <th colspan="2">FY2014-15</th>
	          <th colspan="2">FY2015-16</th>
	          <th colspan="2">FY2016-17</th>
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
			  </tr>
			</thead>
			
			<tbody>
			<logic:present name="progressReportList">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="progressReportList" name="progressReportList">
					<tr>
					 <td align="center">${indexCount}</td>  
					 <td> ${progressReportList[1]}</td> 
					 <td> ${progressReportList[0]}</td> 
					 <td> ${progressReportList[2]}</td> 
					 <td> ${progressReportList[3]}</td> 
					 <td> ${progressReportList[4]}</td> 
					 <td> ${progressReportList[5]}</td> 
					 <td> ${progressReportList[6]}</td> 
					 <td> ${progressReportList[7]}</td> 
					 <td> ${progressReportList[8]}</td> 
					 <td> ${progressReportList[9]}</td> 
					 <td> ${progressReportList[10]}</td>  
		
					</tr>
				  <c:set var="indexCount" value="${indexCount + 1}" />	
			   </logic:iterate>
		   </logic:present>
			</tbody>
	      </table> 

		<!-- 	</div>	 -->	
		
  
</html:form>
</body>

</html>