<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">
    
	
$(function() {
    $("#dateOfEcId").datepicker({ dateFormat: 'dd-mm-yy' });
	$("#dateOfEcId").datepicker('option', { maxDate:new Date()});
 
});





function getXMLHttpRequest() {
	var xmlHttpReq = false;
	// to create XMLHttpRequest object in non-Microsoft browsers
	
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	} else if (window.ActiveXObject) {
		try {
			// to create XMLHttpRequest object in later versions
			// of Internet Explorer
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				// to create XMLHttpRequest object in older versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}
function getPia(){
	
	var xmlHttpRequest = getXMLHttpRequest();
	var prn=document.getElementById("piaPrnId").value;
	
		/* var sector=document.getElementById("sectorId").value; */
	  xmlHttpRequest.onreadystatechange = function(){
		 
		  
			if (xmlHttpRequest.readyState == 4) {
				
				if (xmlHttpRequest.status == 200) {
					
					if (xmlHttpRequest.responseText != "") {
						
						document.getElementById("piaNameId").value = xmlHttpRequest.responseText;
						document.getElementById("detail").value="hh";
					} else{
						alert("Not Found");
						 document.getElementById("piaNameId").value = ''; 
						 document.getElementById("detail").value="nn";
				}
				} else {
					
					document.getElementById("piaNameId").value = '';
					document.getElementById("detail").value="nn";
			}
			}
		}
	
xmlHttpRequest.open("POST","projectDetails.do?methodName=getPia", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("prn="+prn);	

}



function checkUniqueFileNumber(filenumber){
	
	var xmlHttpRequest = getXMLHttpRequest();
	
	
		/* var sector=document.getElementById("sectorId").value; */
	  xmlHttpRequest.onreadystatechange = function(){
		 
		  
			if (xmlHttpRequest.readyState == 4) {
				
				if (xmlHttpRequest.status == 200) {
					
					if (xmlHttpRequest.responseText != "") {
						
						if(xmlHttpRequest.responseText=="already exist"){
							alert("File number already exist");
							 window.setTimeout(function ()
									    {
									        document.getElementById('fileNumberId').focus();
									    },10);
							
						}
					} else{
						
						// document.getElementById("fileNumberId").value = ''; 
				}
				} else {
					
					//document.getElementById("fileNumberId").value = '';
			}
			}
		}
	
xmlHttpRequest.open("POST","projectDetails.do?methodName=getUniquesFileNumber", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("filenumber="+filenumber);	

}


function save(){
	 var x=checkPermissionForFormForInsert();
		if(x=='true'){
	var filenumber=document.getElementById("fileNumberId").value;
	if(filenumber==null||filenumber==""){
		alert("Please enter file number");
		return false;
	}
	var piaPrnId=document.getElementById("piaPrnId").value;
	if(piaPrnId==""||piaPrnId==null){
		alert("Please Enter PIA PRN");
		return false;
	}
	var validate1=document.getElementById("detail").value;
	if(validate1=="nn"){
		alert("Please validate PiaPrn");
		return false;
	}
	
	var projectScheme=document.getElementById("projectSchemeId").value;
	if(projectScheme=="0"){
		alert("Please select Project Scheme");
		return false;
	}
	
	
	
	var totalcost=document.getElementById("totalProjectCostId").value;
	var programcost=document.getElementById("programCostId").value;
	
	
	if(totalcost==null||totalcost==""){
		alert("Total cost cannot be null");
		return false;
	}
	
	/* if(programcost==null||programcost==""){
		alert("Please enter Program cost");
		return false;
	} */
	if(parseInt(totalcost)<parseInt(programcost)){
		alert("Total cost of project can not be less than program cost");
		return false;
	}
	
	var projectTypeId=document.getElementById("projectTypeId").value;
	if(projectTypeId=="0"){
		alert("Please select Project Type");
		return false;
	}
	
	/* var welfareCostId=document.getElementById("welfareCostId").value;
	if(welfareCostId=="0"||welfareCostId<0||welfareCostId==null){
		alert("welfare cost cannot be 0 or less than 0");
		return false;
	} */
	var consortiumId=document.getElementById("consortiumId").value;
	if(consortiumId=="Yes"){
		var prnOfConsortiumPartnerId=document.getElementById("prnOfConsortiumPartnerId").value;
		if(prnOfConsortiumPartnerId==""||prnOfConsortiumPartnerId==null){
			alert("Please Enter Prn of Consortium partner");
			return false;
		}
		var partnerApplicantPiaSameId=document.getElementById("partnerApplicantPiaSameId").value;
		if(partnerApplicantPiaSameId=="Yes"){
			//alert(partnerApplicantPiaSameId+"-------------"+piaPrnId);
			if(prnOfConsortiumPartnerId!=piaPrnId){
				alert("partner Applicant Pia and PIA PRN is not same");
				return false;
			}
			
		}else{
			if(partnerApplicantPiaSameId==piaPrnId){
				alert("partner Applicant Pia and PIA PRN is same");
				return false;
			}
		}
		if(partnerApplicantPiaSameId=="0"){
			alert("Please select Wheather lead partner & applicant PIA same");
			return false;
		}
		
		
	}
	var validate2=document.getElementById("detail2").value;
	if(validate2=="nn"){
		alert("Please validate Prn Consortium Partner");
		return false;
		
	}
	var sanctionedId=document.getElementById("sanctionedId").value;
	if(sanctionedId=="0"){
		alert("Please select Wheather Sanctioned");
		return false;
	}
	var dateOfEcId=document.getElementById("dateOfEcId").value;
	if(dateOfEcId==""||dateOfEcId==null){
		alert("Please select date of EC/PAC approval ");
		return false;
	}
	
	
	
	
	var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="projectDetails.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
}

function back(){
	
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="projectDetails.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
 
   
function getDropDownList(prn){
		  
	        document.forms[0].action="projectDetails.do?methodName=addDetail&prn="+prn+'&reqtrack='+tokenValue;
 			document.forms[0].submit();		 
	}
   
function getid(){
	 
	   var i=0;
	    var piaPrn=document.getElementById("piaPrnId").value;   
	    if(piaPrn==""||piaPrn==null){
	    	alert("Please enter pia prn");
	    	i++;
	    	
	    }else{
	    	var vv=document.getElementById("detail").value; 
	 // alert(vv);
	    	if(vv=="nn"){
	    		alert("Please valiate Pia Prn");
	    		i++
	    	}
	    }
	    
	    var projectScheme=document.getElementById("projectSchemeId").value;
	    if(projectScheme=="0"){
	    	alert("Please select project scheme");
	    	i++;
	    }
	    if(i>0){
	    	document.getElementById("projectSchemeId").value="0";
	    	return false;
	    }
	    var scheme = projectScheme.substring(0, 1);
	    /* if(scheme=="D"){
	    	scheme="Y";
	    }
	    else if(scheme=="S"){
	    	scheme="A";
	    } */
	  //  var prn = piaPrn.substring(0, 6);	
	    var prn=piaPrn.slice(-6);
	   // alert("prn-->"+piaPrn+"    last 6 digit-->  "+prn);
	    var srno=document.getElementById("srno").value;
		var sr=parseInt(srno)+1;
		var statecode=document.getElementById("statecode").value; 
		if(statecode.length==2){
			
			statecode="0"+statecode;
		}else if(statecode.length==1){
			statecode="00"+statecode;
		}
	    var id=scheme+statecode+prn.toUpperCase()+sr;
	   	
	    document.getElementById("projectIDId").value=id;
	    if(projectScheme=="YP"){
	    	document.getElementById("programCostId").disabled = false; 
	    	document.getElementById("maskedAmountprogramCostId").disabled = false;
	    	
	    }else{
	    	 document.getElementById("programCostId").disabled = true; 
	    	 document.getElementById("maskedAmountprogramCostId").disabled = true; 
	    }
	}
    
     function getcost(cost1){
    	 var cost=cost1.value;
    	// alert(cost)
    	 var centerShare= document.getElementById("centerShare").value;
    	 var stateshare= document.getElementById("stateShare").value;
	     var centralCost= (cost*centerShare)/100;
	     var stateCost=(cost*stateshare)/100;   	  	
	     document.getElementById("centralShareId").value=Math.round(centralCost);
	     document.getElementById("stateShareId").value=Math.round(stateCost);
	     document.getElementById("programCostId").value="";
	    // document.getElementById("welfareCostId").value="";
	     changeTextToBack(document.getElementById("centralShareId"),"maskedAmountcentralShareId");
	     changeTextToBack(document.getElementById("stateShareId"),"maskedAmountstateShareId");
	    // Math.round
	}
     function changeTextToBack(element,x){
    		//alert("x-->"+x);
    		var getInput = $(element).val();
    		//alert(getInput);
    		$("#"+x).autoNumeric('set', getInput);
    		
    	}
    /* function getWelfarecost(programcost){
    	var ppcost=programcost.value;
    	var projectcost= document.getElementById("totalProjectCostId").value;
    	if(ppcost==null||ppcost==""){
    		ppcost=0;
    		document.getElementById("welfareCostId").value="";
    	}
    	var welfarecost=parseInt(projectcost)-parseInt(ppcost);
    	if(welfarecost<0){
    		document.getElementById("welfareCostId").value="";
    	}
    	else
    	document.getElementById("welfareCostId").value=welfarecost;
    } */
     
     function getcons(vv){	 
 	 if(vv=="Yes"){
 		document.getElementById("prnOfConsortiumPartnerId").disabled = false;
 		document.getElementById("prnConsortiumPartnerNameId").disabled = false;
 		document.getElementById("partnerApplicantPiaSameId").disabled = false;
 		document.getElementById("validatebutton").disabled = false;
 		$("#detail2").val("nn");
 		 
 	 }else{
 		document.getElementById("prnOfConsortiumPartnerId").value="";
 		document.getElementById("prnConsortiumPartnerNameId").value="";
 		document.getElementById("partnerApplicantPiaSameId").value="0";
 		document.getElementById("prnOfConsortiumPartnerId").disabled = true;
 		document.getElementById("prnConsortiumPartnerNameId").disabled = true;
 		document.getElementById("partnerApplicantPiaSameId").disabled = true;
 		document.getElementById("validatebutton").disabled = true; 
 		$("#detail2").val("hh");
 	 }
 	 
 	}
      
     function isNumberKey(evt)
 	{
 	   var charCode = (evt.which) ? evt.which : event.keyCode
 	   if (charCode > 31 && (charCode < 48 || charCode > 57))
 	      return false;
 	   

 	   return true;
 	} 
     function checkpia(){
    	 var vv=document.getElementById("prnOfConsortiumPartnerId");
    	var piaprn=document.getElementById("piaPrnId").value;
    	if(piaprn==vv.value){
    		document.getElementById("partnerApplicantPiaSameId").value="Yes";
    		getPiaforPar(vv.value);
    		/* var pianame=document.getElementById("piaNameId").value;
    		document.getElementById("prnConsortiumPartnerNameId").value=pianame; */
    		//document.getElementById("detail2").value="hh";
    	}else{
    		getPiaforPar(vv.value);
    		document.getElementById("partnerApplicantPiaSameId").value="No";
    	}
     }
     function getPiaforPar(prn){
    		
    		var xmlHttpRequest = getXMLHttpRequest();
    		
    		
    			/* var sector=document.getElementById("sectorId").value; */
    		  xmlHttpRequest.onreadystatechange = function(){
    			 
    			  
    				if (xmlHttpRequest.readyState == 4) {
    					
    					if (xmlHttpRequest.status == 200) {
    						
    						if (xmlHttpRequest.responseText != "") {
    							
    							document.getElementById("prnConsortiumPartnerNameId").value = xmlHttpRequest.responseText;
    							document.getElementById("detail2").value="hh";
    							
    						} else{
    							document.getElementById("prnConsortiumPartnerNameId").value = ""; 
    							document.getElementById("detail2").value="nn";
    							// document.getElementById("prnConsortiumPartnerNameId").value = ''; 
    					}
    					} else {
    						document.getElementById("prnConsortiumPartnerNameId").value = "";
    						 document.getElementById("detail2").value="nn";
    						//document.getElementById("prnConsortiumPartnerNameId").value = '';
    				}
    				}
    			}
    		
    	xmlHttpRequest.open("POST","projectDetails.do?methodName=getPia", true);
    	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
    	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
    	xmlHttpRequest.send("prn="+prn);	

    	}
     function setdetail(){
    	 document.getElementById("piaNameId").value = ''; 
		 document.getElementById("detail").value="nn";
     }
     $(document).ready(function()
    		 {
    	 $("#maskedAmountcentralShareId").attr('readonly', 'readonly');
    	 $("#maskedAmountstateShareId").attr('readonly', 'readonly');
    	
    		 });
    		 
</script>
<!--Main form section starts here-->
<html:form action="login/projectDetails">
<jsp:useBean id="statedetail" class="com.infotech.sgsy.master.state.StateVO" scope="request"/>
	
			<input type="hidden" value="${srNo}" id="srno"/>
			
			<input type="hidden" value="${statedetail.stateCode}" id="statecode"/>
			<input type="hidden" value="${statedetail.centerShare}" id="centerShare"/>
			<input type="hidden" value="${statedetail.stateShare}" id="stateShare"/>
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Project Details </td>
					
               </tr>
				
			</table>
			
			
				<%-- <table width="100%" align="center" class="inputTBL">
				<tr>
					<th width="50%">${name}</th>
					<td width="50%"><b>${nameValue}</b></td>
				</tr>
				</table> --%>
		<input type="hidden" id="detail" value="nn"/>
		<input type="hidden" id="detail2" value="nn"/>
					  
					<table width="100%" align="center" class="inputTBL">
					 <tr>
					<th width="50%">File Number/sanction order no.<span class="text-error"></span></th>
					<td  width="50%" ><span class="text-error"> <html:text property="fileNumber"   styleId="fileNumberId" /> </span>
					<!-- onblur="javascript:checkUniqueFileNumber(this.value)" -->
					</td>
				</tr> 
				 
					 
					  
						
					 <tr><th width="50%"><span class="input-group-addon" id="basic-addon1">PIA-PRN</span></th>
						<td width="50%">
						<html:text property="piaPrn" styleId="piaPrnId" style="text-transform: uppercase" styleClass="form-control" onkeypress="javascript:setdetail();"></html:text>
						<input type="button" class="button" value="validate" onclick="javascript:getPia()"/>
							<%-- <html:select property="piaPrn" styleId="piaPrnId" onchange="javascript:getPia(this.value)"  styleClass="form-control" >
								<html:option value="0">-select-</html:option>
								<logic:present name="PIAList">
									<logic:iterate id="piaPrn" name="PIAList">
										<html:option value="${piaPrn[1]}">${piaPrn[1]}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select> --%></td>
						   </tr>
						   
						   <tr>
							<th width="50%"><span class="input-group-addon" id="basic-addon1">PIA-NAME</span></th>
							<td width="50%" style="vertical-align: top; text-align: left;"><html:textarea property="piaName" styleId="piaNameId" readonly="true"  styleClass="form-control" />								 
								
							</td>						 
						 </tr>
						 <tr>	
					<th width="50%">Project Scheme<span class="text-error"></span></th>
					<td width="50%"><span class="text-error"> <html:select property="projectScheme"  onchange="javascript:getid()" styleId="projectSchemeId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="DDUGKY">DDUGKY</html:option>
					<html:option value="Roshni">Roshni</html:option>
					<html:option value="Himayat">Himayat</html:option>
					<html:option value="SagarMala">SagarMala</html:option>
					<html:option value="AP">AP</html:option>
					<html:option value="YP">YP</html:option>
 					</html:select> </span></td>					 
				</tr>		 
						   <tr>
						 <th width="50%">
							<span class="input-group-addon" id="basic-addon1">CTSA Name</span></th>
							<td width="50%">
							<html:select property="projectType" styleId="projectTypeId"   styleClass="form-control" >
								<html:option value="0">-select-</html:option>
								<logic:present name="ProjectTypeList">
									<logic:iterate id="projectType" name="ProjectTypeList">
										<html:option value="${projectType.id}">${projectType.ctsaName}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						 </tr>
				
			  <tr>
					<th width="50%">Project ID<span class="text-error"></span></th>
					<td width="50%"><span class="text-error"> <html:text property="projectID" readonly="true" styleId="projectIDId" /> </span></td>
				</tr>  
				
			<tr>
					<th width="50%">Total Project Cost<span class="text-error"> (in Rs.)</span></th>
					<td width="50%"><span class="text-error"> <html:text property="totalProjectCost" onblur="getcost(totalProjectCost)"  styleClass="amount"   styleId="totalProjectCostId" /> </span></td>
				</tr> 
				
				<tr>
					<th width="50%">Project Cost-Central Share<span class="text-error"> (in Rs.) </span>- ${statedetail.centerShare} % of Total project cost</th>
					<td width="50%"><span class="text-error"> <html:text property="centralShare"  styleClass="amount" readonly="true"   styleId="centralShareId"  /> </span></td>
				</tr> 
				<tr>
					<th width="50%">Project Cost-State Share<span class="text-error"> (in Rs.) </span>- ${statedetail.stateShare} % of Total project cost</th>
					<td width="50%"><span class="text-error"> <html:text property="stateShare" styleClass="amount"  readonly="true"   styleId="stateShareId" /> </span></td>
				</tr> 
				
				<tr>
					<th width="50%">Project Cost-Program Cost<span class="text-error"> (in Rs.)</span></th>
					<td width="50%"><span class="text-error"> <html:text property="programCost" styleClass="amount"   styleId="programCostId" /> </span></td>
				</tr> 
					 
					 
				<%-- <tr>
					<th width="50%">Project Cost-Welfare Cost  <span class="text-error"> (in Rs.)</span></th>
					<td width="50%"><span class="text-error"> <html:text property="welfareCost" styleClass="amount"  styleId="welfareCostId" readonly="true"/> </span></td>
				</tr>  --%>	 
					 
					
					 	<tr>	
					<th width="50%">Whether Consortium<span class="text-error"></span></th>
					<td width="50%"><span class="text-error"> <html:select property="consortium" onchange="getcons(this.value)"  styleId="consortiumId" >
				<%-- 	<html:option value="0">- Select -</html:option> --%>
					<html:option value="Yes">Yes</html:option>
					<html:option value="No">NO</html:option>
					 </html:select></span></td>
				</tr>	
				</table>
					 <div id="show">
					 <table width="100%" align="center" class="inputTBL">		 
					  <tr><th width="50%"><span class="input-group-addon" id="basic-addon1">PRN of Consortium Partner</span></th>
						<td width="50%">
						<html:text property="prnOfConsortiumPartner" styleId="prnOfConsortiumPartnerId"   styleClass="form-control"/>
						<input type="button" id="validatebutton" class="button" value="validate" onclick="javascript:checkpia()"/>
							<%-- <html:select property="prnOfConsortiumPartner" styleId="prnOfConsortiumPartnerId"   styleClass="form-control"  onchange="javascript:checkpia(this)">
								<html:option value="0">-select-</html:option>
								<logic:present name="PIAList">
									<logic:iterate id="piaPrn" name="PIAList">
										<html:option value="${piaPrn[1]}">${piaPrn[1]}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select> --%></td>
						   </tr>
				<!-- </tr> -->
					   	<tr>	
					<th width="50%">Consortium Partner Name<span class="text-error"></span></th>
				 <td width="50%" style="vertical-align: top; text-align: left;" ><span class="text-error"> <html:textarea property="prnConsortiumPartnerName"  readonly="true"   styleId="prnConsortiumPartnerNameId" /> </span></td>
					 
				</tr></table>
					 </div>
					 <div>
					<table width="100%" align="center" class="inputTBL">
					 
					 	 	<tr>	
					<th width="50%">Whether Lead Partner and applicant Pia are same<span class="text-error"></span></th>
					<td width="50%"><span class="text-error"> <html:select property="partnerApplicantPiaSame" styleId="partnerApplicantPiaSameId">
					<html:option value="0">- Select -</html:option>
					<html:option value="Yes">Yes</html:option>
					<html:option value="No">NO</html:option>
					 </html:select></span></td>
				</tr>
					 
					 	<tr>	
					<th width="50%">Date of EC/PAC approval minutes uploaded<span class="text-error"></span></th>
				 <td width="50%"><span class="text-error"> <html:text property="dateOfEc" readonly="true"    styleId="dateOfEcId" /> </span></td>
					 
				</tr>
					 
					 	 	<tr>	
					<th width="50%">Whether Sanctioned<span class="text-error"></span></th>
					<td width="50%"><span class="text-error"> <html:select property="sanctioned" styleId="sanctionedId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="Yes">Yes</html:option>
					<html:option value="No">NO</html:option>
					 </html:select></span></td>
				</tr>
					 
					 </table>
			 	</div>
			<br/>
		 
				<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>		
			
</html:form>
 