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
				<td align="center" class="pageHeader">REPORT 3:DISTRICT-WISE PROGRESS OF EACH STATE FOR CURRENT FINANCIAL YEAR</td>
			  </tr>
			</table>
  
        <div style="overflow-x:scroll;overflow-y:scroll;" >	
		    
		<!--  For Second Report-->
		<table width="100%" border="1" id="example" class="display">
	        <thead>
			 <tr>
			  <th> S.No </th>
	          <th> District Name</th>
	          <th> Trained</th>
	          <th> Placed</th>
	          
			  </tr>
			</thead>
			
			<thead>
			<tr>   
			    <td></td>
			    <td></td>	
			    <td></td>
			    <td></td>
				
			  </tr>
			</thead>
			
			<tbody>
			<logic:present name="progressReportList3">
				 <c:set var="indexCount" value="1" />
				 <logic:iterate id="progressReportList3" name="progressReportList3">
					<tr>
					 <td align="center">${indexCount}</td>  
					 <td> ${progressReportList3[0]}</td> 
					 <td> ${progressReportList3[1]}</td> 
					 <td> ${progressReportList3[2]}</td> 
					
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



