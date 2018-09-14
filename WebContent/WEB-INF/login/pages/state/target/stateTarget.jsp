<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"> 
// this is done to display date in correct format
$(document).ready(function () {
    $(".nndate").each(function() {
    	$(this).find("input:text").each(function() {
    	var dbDate=$(this).val();
    	if(dbDate!=null && dbDate!=""){
    	var date2 = new Date(dbDate);
    	$(this).datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
    }
    });
    });
  });
 

//window.onbeforeunload=confirmExit;
 function confirmExit(){		
 	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
 	if(window.event.clientY < 0 ){
 		var xmlhttp;
 		if (window.ActiveXObject) { // code for IE6, IE5
 		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
 		xmlhttp.open("GET",URL,false);
 		xmlhttp.send();
 	}
 	if (window.XMLHttpRequest){
 		// code for IE7+, Firefox, Chrome, Opera, Safari
 		xmlhttp = new XMLHttpRequest();
 		xmlhttp.open("GET",URL,false);
 		xmlhttp.send();
 	}
   }
 } 
 
 
 
  $(function() {
    $( "#annualPlanId" ).datepicker({ dateFormat: 'dd-mm-yy' });
    $("#annualPlanId").datepicker('option', { maxDate:new Date()});
});  


$(document).ready(function () {	
	$('#example').DataTable();
	var sizea=${size};
	if(sizea=="0"){
		
	
	
  var id=$("#idUU").val();
	if(id<1){
		$("#button").val("save");
	   }
	else  $("#button").val("Update");  
	
	 var others = $('select[name="stateMis"]').val()
	 if(others == "None"){
		     document.getElementById("applicationNameId").readOnly=true; 
		     document.getElementById("sopCompliantId").disabled = true;  
		     document.getElementById("centralMisId").disabled = true; 
		     
		     document.getElementById("applicationNameId").style.backgroundColor = "#D3D3D3";
		     document.getElementById("sopCompliantId").style.backgroundColor = "#D3D3D3";
		     document.getElementById("centralMisId").style.backgroundColor = "#D3D3D3";
		     
	        }else{
	        	 //document.getElementById("applicationNameId").readOnly=false; 
	        	 document.getElementById('applicationNameId').readOnly = false;
	        	 
	        	 document.getElementById("sopCompliantId").disabled = false; 
			     document.getElementById("centralMisId").disabled = false;  
			     
	        	 document.getElementById("applicationNameId").style.backgroundColor = "white";
	        	 document.getElementById("sopCompliantId").style.backgroundColor = "white";
			     document.getElementById("centralMisId").style.backgroundColor = "white";
	        }
	 
	 var schemeVal = $('select[name="scheme"]').val()
	 if(schemeVal == "YP")
	 {
		    document.getElementById("annualPlanId").disabled = true; 
	        document.getElementById("annualPlanId").value = "";
		    document.getElementById("annualPlanId").style.backgroundColor = "#D3D3D3";
	        }else{
	        	
	        	 document.getElementById("annualPlanId").disabled=false; 
	        	 document.getElementById("annualPlanId").style.backgroundColor = "white";
	        }	
	}
	
	
});

/* function changedateFormat(){
	$(".dateformate").each(function(){
		var text=$(this).text();
	//	alert(text);
		if(text!="" && text!=null){
		var month=text.substring(5, 7);
		var year=text.substring(0,4);
		var date=text.substring(8,10);   
		$(this).text(date+"-"+month+"-"+year);
		}
 });
} */

function change(){
	var other = document.getElementById("stateMisId").value; 
   if(other == "None" )
	 {
	     document.getElementById("applicationNameId").readOnly=true; 
	     document.getElementById("applicationNameId").value = "";
	     
	     document.getElementById("sopCompliantId").disabled = true;  
	     document.getElementById("sopCompliantId").value = "";
	     
	     document.getElementById("centralMisId").disabled = true;  
	     document.getElementById("centralMisId").value = "";
	     
		 document.getElementById("applicationNameId").style.backgroundColor = "#D3D3D3";
		 document.getElementById("sopCompliantId").style.backgroundColor = "#D3D3D3";
		 document.getElementById("centralMisId").style.backgroundColor = "#D3D3D3";
	   }else{
	      	 document.getElementById("applicationNameId").readOnly=false; 
	      	document.getElementById("sopCompliantId").disabled = false; 
	      	document.getElementById("centralMisId").disabled = false; 
	      	
	      	 document.getElementById("applicationNameId").style.backgroundColor = "white";
	      	 document.getElementById("sopCompliantId").style.backgroundColor = "white";
	      	 document.getElementById("centralMisId").style.backgroundColor = "white";
	      }
}

function changeScheme(){
	var scheme = document.getElementById("schemeId").value; 
	   if(scheme == "YP" )
		 {  
		        document.getElementById("annualPlanId").disabled = true; 
		        document.getElementById("annualPlanId").value = "";
		        document.getElementById("annualPlanId").style.backgroundColor = "#D3D3D3";
		   }else{
		      	 document.getElementById("annualPlanId").disabled=false; 
		      	 document.getElementById("annualPlanId").style.backgroundColor = "white";
		      }
}     
function hidebodercolor(fieldid)
{
    document.getElementById(fieldid.id).style.border="";	
}

function save(){
	
 var id=$("#idUU").val();
 //alert(id);
if(id<1){
	
	var status=window.confirm('ARE YOU SURE YOU WANT TO SAVE ?');
	if(status==true){
	    document.forms[0].action="stateTargetFormAction.do?methodName=save&id="+id+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	  }
	}
	else {
				 
    var status=window.confirm('ARE YOU SURE YOU WANT TO UPDATE ?');
	if(status==true){ 
		document.forms[0].action="stateTargetFormAction.do?methodName=update"+"&"+tokenParameter+"="+tokenValue; 
		document.forms[0].submit();
	}
}
}

</script>
 
 <html>
 <body >
 <html:form action="login/stateTargetFormAction">
 <table width="100%" class="pageHeaderTable">
            <tr>
            <td align="center" width="100%" class="pageHeader">State Details</td>
            </tr>
                   
         
        </table> 
    <c:set var="size" scope="request" value="${size}"/> 
    <c:choose>
    <c:when test="${size <= 0}">
      <table width="100%" >
  <tr>
	<td>
		
                   
       <table width="100%" align="center"  class="inputTBL" >
         <jsp:useBean id="statetargetdetails" class="com.infotech.sgsy.stateSetupTarget.StateTargetVO" scope="request" />
    
			<tr>
            <th>State Name <span class="text-error"></span></th>
             <td><span class="text-error"><html:text   readonly="true"  property="stateName"  value="${stateName }"  
                             styleId="stateNameId" onchange="hidebodercolor(this);" /></span></td>
            </tr> 
            
            <tr>
            <th>Scheme <span class="text-error"></span></th>
            <td><span class="text-error rree">
            <html:select property="scheme"   styleId="schemeId" onchange="javascript:changeScheme();hidebodercolor(this);"  value="${statetargetdetails.scheme}">
				<html:option value="AP">AP</html:option>							
				<html:option value="YP">YP</html:option>
			</html:select> </span>
			</td>
            </tr> 
           
            <tr id="remarkRow" >	
			<th align="left" width="50%" >Since when Annual Plan<span class="text-error"></span></th>
			<td align="right" width="50%"><span class="text-error nndate"><html:text  property="annualPlan"  styleId="annualPlanId"
					    onchange="hidebodercolor(this);" value="${statetargetdetails.annualPlan}"/></span></td>
		    </tr> 
            
            <tr>
            <th>Central Technical Support Agency <span class="text-error"></span></th>
            <td><span class="text-error">
               <html:select property="ctsa" styleId="ctsaId" onchange="hidebodercolor(this);" value="${statetargetdetails.ctsa.id}">
               <html:option value="0">--SELECT--</html:option>
               <logic:present name="ctsaList">
					 <logic:iterate id="ctsaList" name="ctsaList">
					   <html:option value="${ctsaList.id}">${ctsaList.ctsaName}</html:option>
					 </logic:iterate>
				  </logic:present>
							
							<%-- <html:option value="NIRD">NIRD</html:option>
							<html:option value="NABCON">NABCON</html:option> --%>
		       </html:select></span>
			</td>
			<%-- <td>${statetargetdetails.ctsa.id}</td> --%>
            </tr> 
            
            <tr>
            <th>Appraisal Agency <span class="text-error"></span></th>
            <td> <span class="text-error">
              <html:select property="appraisalAgency" styleId="appraisalAgencyId"  onchange="hidebodercolor(this);" value="${statetargetdetails.appraisalAgency.id}">
			    <html:option value="0">--SELECT--</html:option>
				  <logic:present name="appraisalAgencyList">
					 <logic:iterate id="APPRAISALAGENCY" name="appraisalAgencyList">
					   <html:option value="${APPRAISALAGENCY.id}">${APPRAISALAGENCY.appraisalName}</html:option>
					 </logic:iterate>
				  </logic:present>
			  </html:select></span>
			</td>
            </tr> 
            
            <tr>
            <th>Technical Support Agency Name <span class="text-error"></span></th>
            <td> <span class="text-error">
               <html:select property="tsaName" styleId="tsaNameId"  onchange="hidebodercolor(this);" value="${statetargetdetails.tsaName.id}">
				 <html:option value="0">--SELECT--</html:option>
				   <logic:present name="tsaList">
					 <logic:iterate id="TSA" name="tsaList">
					   <html:option value="${TSA.id}">${TSA.tsaName}</html:option>
				 	 </logic:iterate>
				   </logic:present>
			    </html:select></span>
			</td>
            </tr> 
           
           <tr>
            <th>Status of MIS in State<span class="text-error"></span></th>
           <td><span class="text-error">
            <html:select property="stateMis" styleId="stateMisId"  onchange="hidebodercolor(this);change();" value="${statetargetdetails.stateMis}">
							<html:option value="ERP">ERP</html:option>
							<html:option value="MPR">MPR</html:option>
							<html:option value="Excel">Excel</html:option>
							<html:option value="Procurement in Progress">Procurement in Progress</html:option>
							<html:option value="Development in Progress">Development in Progress</html:option>
							<html:option value="None">None</html:option>
			</html:select></span>
			</td></tr> 
			
            <tr id="row12">
            <th>Name of application/Package</th>
            <td><span class="text-error"><html:text   property="applicationName"  styleId="applicationNameId" 
                             onchange="hidebodercolor(this);" value="${statetargetdetails.applicationName}"/></span></td>
            </tr> 
            
            <tr id="row12">
            <th>Whether MIS is SOP compliant <span class="text-error"></span></th>
            <td><span class="text-error">
              <html:select property="sopCompliant" styleId="sopCompliantId" onchange="hidebodercolor(this);" value="${statetargetdetails.sopCompliant}" >
                            <html:option value="">Select</html:option>
							<html:option value="Yes">Yes</html:option>
							<html:option value="No">No</html:option>
			  </html:select></span>
			</td></tr> 
            
            <tr id="row12">
            <th>Whether MIS is connected to Central MIS <span class="text-error"></span></th>
            <td><span class="text-error">
               <html:select property="centralMis" styleId="centralMisId" onchange="hidebodercolor(this);" value="${statetargetdetails.centralMis}">
                            <html:option value="">Select</html:option>
							<html:option value="Yes">Yes</html:option>
							<html:option value="No">No</html:option>
			   </html:select></span>
			</td>
			</tr> 
          <tr>   
			 <td><span class="text-error"> <html:hidden property="id"    value="${id}" styleId="idUU" /></span></td>
             </tr> 
            </table>
          
          <div align="center">
        	  <input name="Button" id="button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="save()"/>
        	  <input name="Button" id="button" type="button" class="button" onclick="closePage(tokenParameter,tokenValue)" value="Close"/>
        	 
		  </div>	
							
			</td>
		</tr>
	
  </table>
      
      
      
      
    </c:when>
			<c:when test="${size > 0}">
				<table class="display" id="example">
					<thead>
						<tr>
						    <th>S.No</th>
							<th>State Name</th>
							<th>Scheme</th>
							<th>Annual Plan</th>
							<th>CTSA</th>
							<th>Appraisal Agency</th>
							<th>Tsa Name</th>
							<th>State Mis</th>
							<th>Application Name</th>
							<th>Sop Compliant</th>
							<th>Central Mis</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="stateList">
						<c:set var="indexCount" value="1" />
							<logic:iterate id="stateList" name="stateList">
								<tr>
								<td>${indexCount}</td>
									<td>${stateList.state.stateName}</td>
									<td>${stateList.scheme}</td>
									<td >${stateList.dateformat}</td>
									<td>${stateList.ctsa.ctsaName}</td>
									<td>${stateList.appraisalAgency.appraisalName}</td>
									<td>${stateList.tsaName.tsaName}</td>
									<td>${stateList.stateMis}</td>
									<td>${stateList.applicationName}</td>
									<td>${stateList.sopCompliant}</td>
									<td>${stateList.centralMis}</td>
								</tr>
								<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
					</tbody>
					<tfoot>
						<tr>
						<td>S.No</td>
							<td>State Name</td>
							<td>Scheme</td>
							<td>Annual Plan</td>
							<td>CTSA</td>
							<td>Appraisal Agency</td>
							<td>Tsa Name</td>
							<td>State Mis</td>
							<td>Application Name</td>
							<td>Sop Compliant</td>
							<td>Central Mis</td>

						</tr>


					</tfoot>
				</table>





			</c:when>
			<c:otherwise>
       
    </c:otherwise>
    
</c:choose>   
        
        
 
</html:form>
</body>
<!--Main form section ends here-->
 