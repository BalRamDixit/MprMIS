<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<style type="text/css">
	.TFtable{
		 
		border-collapse:collapse; 
	}
	.TFtable th{
	padding:7px; background: #D6EAF8;
	}
	.TFtable td{ 
		padding:7px; 
	}
	.TFtable th, td {
    border: 1px solid black;
}
	 
	/* provide some minimal visual accomodation for IE8 and below */
	.TFtable tr{
		background: #b8d1f3;
	}
	/*  Define the background color for all the ODD background rows  */
	.TFtable tr:nth-child(odd){ 
		background: #b8d1f3;
	}
	/*  Define the background color for all the EVEN background rows  */
	.TFtable tr:nth-child(even){
		background: #dae5f4;
	}
</style>
<!--script for menu-->
<script type="text/javascript">

function closePage()
{
	document.getElementById("newRec").style.display="none";
	$('.CompareButton').show();
} 
  
</script>
 <html:form action="login/physicalAchieve">	
			
			<p align="center">PHYSICAL ACHIEVEMENT</p>
			
			 
			 <div style="overflow-x:scroll;overflow-y:scroll;width:1090px;height:490px;" >	
			 <table>	<tr>
			 <th>
					<table width="100%" class="TFtable" >
						<tr>
							<th align="left">Number of candidates (unless specified)</th>
							<th align="left">Action Plan Target</th>
							<th align="left">Cumulative achievement of ongoing projects</th>
 							<th align="left">% Achieved</th>
							<th align="left">Target (FY 2016-17)</th>
							<th align="left">Actual Achievement(FY 16-17)</th>
 							<th align="left">% Achieved</th>
							<th align="left">Achieved during this month</th> 							 							
 						</tr>								 
				<tr>	
					<td width="250px;">Training commenced</td>
					<td>173,629 </td>
					<td>59,135</td>
					<td> </td>
					<td>52,089</td>
					<td>53,390 </td>
					<td> </td>
					<td>1,853 </td>
				</tr> 
				 <tr>
				 <td>Training completed</td>
				 <td>173,629 </td>
				 <td>53,691</td>
				 <td> </td>
				 <td>52,089 </td>
				 <td>23,205</td>
				 <td>45% </td>
				 <td>1,248</td>
				 
				 </tr>	 
				 <tr>
				 <td>SC/ST</td>
				 <td> </td>
				 <td>- </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Women</td>
				 <td> </td>
				 <td>- </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Minority </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Pro-Life </td>
				 <td> </td>
				 <td>345 </td>
				 <td> </td>
				 <td> </td>
				 <td>345 </td>
				 <td> 5%</td>
				 <td>345 </td>
				 </tr>	 
				 <tr>
				 <td>Assessment completed </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td>52,089 </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Certified </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td>52,089 </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Appointed  </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td>23,205 </td>
				 <td>39,249 </td>
				 <td>169% </td>
				 <td>1,287</td>
				 </tr>	 
				 <tr>
				 <td>Placed (for 3 months)</td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td>23,205</td>
				 <td>35,348 </td>
				 <td>152% </td>
				 <td>1,186</td>
				 </tr>	 
				 <tr>
				 <td>Retained for 12 months</td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Foreign placements</td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Placements over Rs. 15000</td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>	 
				 <tr>
				 <td>Average person months of placement provided </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 <td> </td>
				 </tr>			 
					</table>
					</th>
					<th>
					<table width="100%" class="TFtable" >
						<tr>
							<th align="center" colspan="2">Champion Employers</th>
							 					 							
 						</tr>								 
				<tr> 
					<td>Sanctioned target</td>
					<td>Cumulative achievement</td>
				</tr> 
				<tr> 
					<td> </td>
					<td> 1853</td>
				</tr>
				<tr> 
					<td> </td>
					<td>1248 </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> 345</td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> 1287</td>
				</tr>
				<tr> 
					<td> </td>
					<td>1186 </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				<tr> 
					<td> </td>
					<td> </td>
				</tr>
				
					</table>
					</th>
					</tr>
					
					
					</table><p>*FY 16-17 Target is 30% of Action Plan Target, __% for 17-18 and __ for 18-19.
                    Coloring of cells will be based on business logic provided.</p>
					
				</div>
			 	 
	
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<!--Main form section ends here-->