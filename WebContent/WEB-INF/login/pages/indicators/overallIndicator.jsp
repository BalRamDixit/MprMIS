<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<html>
<head>
    <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=2">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="sweetalert-master/dist/sweetalert.min.js"></script>
  <link rel="stylesheet" type="text/css" href="sweetalert-master/dist/sweetalert.css">
  

<script type="text/javascript">
function showRemark(str){
if (str=="2016-2017")
{
	document.getElementById("remarkRow").style.display = "";	
} else{
   document.getElementById("remarkRow").style.display = "none";
}
}
$(document).ready(function()
{
	showRemark("0");
});
  
</script>
  
</head>

<body>
<html:form action="login/kpiFormAction">

 <table width="100%" align="center"  class="inputTBL" >
            <tr >
            <th >State Name <span class="text-error">*</span></th>
            <td span class="text-error"> <html:text size="25" maxlength="10" property="stateName" disabled="true" styleId="stateNameId" /></td>
            </tr> 
            
             <tr>
             <th >Month<span class="text-error"></span></th>
             <td ><span class="text-error">
                <html:select property="month" >
                    <html:option value="0">SELECT</html:option>
                    <html:option value="january">January</html:option>
                    <html:option value="february">February</html:option>
                    <html:option value="march">March</html:option>
			    	<html:option value="april">April</html:option>
			    	<html:option value="may">May</html:option>
			    	<html:option value="june">June</html:option>
			    	<html:option value="july">July</html:option>
			    	<html:option value="august">August</html:option>
			    	<html:option value="september">September</html:option>
			    	<html:option value="october">October</html:option>
			    	<html:option value="november">November</html:option>
			    	<html:option value="december">December</html:option>
			    </html:select></span>   
	         </td>
	         </tr>
	         
	         <tr>
            <th>Year<span class="text-error"></span></th>
             <td><span class="text-error">
                <html:select property="year" styleId="yearId" onchange="javascript:showRemark(this.value);">
                    <html:option value="0">SELECT</html:option>
                    <html:option value="2016-2017">2016-2017</html:option>
                   <%--  <html:option value="2017">2017</html:option> --%>
                 </html:select></span>
	         </td>
	      </tr>
   </table>         
  
  <div id="remarkRow">
  
<div  class="container-fluid bg-3 text-center">    
   <h1> Over All Indicators</h1> <br>
  </div>
 
  <div class="row">
    <div class="col-sm-4">
       AP State <span class="badge">Yes</span><br>
       AP State since <span class="badge">2012</span><br>
       SRLM <span class="badge">ORMAS</span><br>
       Department <span class="badge">Panchayati Raj</span><br>
       CTSA <span class="badge">NABCONS</span><br>
    </div>
    
    <div class="col-sm-4"> 
      Whether State TSA is empaneled<span class="badge">Yes</span><br>
      TSA name<span class="badge">PWC</span><br>
    </div>
    
    <div class="col-sm-4"> 
      No of Ongoing Projects<span class="badge">80</span><br>
      No of Projects yet to be registered on PFMS<span class="badge">3</span><br>
      Number of projects for which expenditures are not yet recorded on PFMS<span class="badge">36</span><br>
    </div>
 </div>
<br>


  <div class="row">
      
     <div class="col-sm-4"> 
      Number of Post Vacant<span class="badge">0</span><br>
      Whether COO is posted<span class="badge">Yes</span><br>
      Number of SPMs posted<span class="badge">9</span><br>
    </div>
    
     <div class="col-sm-4"> 
      (in numbers of candidates)<span class="badge"></span><br>
      Action Plan Target(FY 2016- FY 2019)<span class="badge">173,629</span><br>
      Target Sanctioned by state, by EC July 2015<span class="badge">72,593</span><br>
      Training Target achievement<span class="badge"></span><br>
     </div>
    
     <div class="col-sm-4"> 
      Total Training Centres<span class="badge">201</span><br>
      Active Training Centres<span class="badge">80</span><br>
    </div>

</div><br><br>
  
  <div class="row">
      
     <div class="col-sm-4"> 
      Appraisal Agency empaneled<span class="badge">No</span><br>
      Name of Appraisal Agency<span class="badge">NA</span><br>
     </div>
    
     <div class="col-sm-4"> 
      No of job Fairs held in state<span class="badge"></span><br>
      No of candidates placed from job fairs (cumulative)<span class="badge"></span><br>
      No of candidates placed through industrial internship<span class="badge"></span><br>
     </div>
    
     <div class="col-sm-4"> 
      PPWS Target<span class="badge">13,022</span><br>
      Active TC Capacity<span class="badge">9,038</span><br>
      % Capacity utilization(under training/ Active TC Capacity)<span class="badge">%</span><br>
      Under training<span class="badge"></span><br>
      Candidate with more than 75 % attendance in a month<span class="badge"></span><br>
      % Candidate with bank accounts<span class="badge"></span><br>
      % Candidate with aadhaar<span class="badge"></span><br>
      % Candidate with insurance<span class="badge"></span><br>
    </div>
 
</div><br><br>

   
  <div class="row">
      
     <div class="col-sm-4"> 
      Whether state has a MIS<span class="badge">Yes</span><br>
      Whether MIS is SOP compliant<span class="badge">No</span><br>
      Whether MIS is connected to Central MIS<span class="badge">No</span><br>
     </div>

</div>
</div>
</html:form>
</body>
</html>