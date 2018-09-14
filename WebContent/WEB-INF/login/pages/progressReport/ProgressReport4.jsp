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
				<td align="center" class="pageHeader">REPORT 4: STATE-WISE CATEGORY-WISE ACHIEVEMENT</td>
			  </tr>
			</table>
  
        <div style="overflow-x:scroll;overflow-y:scroll;" >	
		    
		<!--  For Second Report-->
		<table width="100%" border="1" id="example" class="display">
	        <thead>
			<tr>
			  <th> S.No </th>
	          <th> State Name</th>
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
			<logic:present name="progressReportList4">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="progressReportList4" name="progressReportList4">
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