 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<script type="text/javascript">

var tokenParameter = 'reqtrack';
var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';

function modifyPage(){		
	var validationCheck = validate();
	if(validationCheck){ 
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	 	if(status==true){ 
			document.forms[0].action = "modifyPiaAction.do?methodName=modifyPia&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		 } 
	}
}

function removeError(first, second){
	document.getElementById(first).innerHTML='';
	document.getElementById(second).innerHTML='';
}

 function backPage(){
	 document.forms[0].action = "modifyPiaAction.do?methodName=modifyPIADetails&"+tokenParameter+"="+tokenValue;
	 document.forms[0].submit();
	}

 function showFiles(fileType) {
		document.forms[0].action = "verifyPiaAction.do?methodName=showFiles&file="+fileType+"&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
 
 function fileStatus(id, work){
	 if(work == 'show'){
		 document.getElementById(id).style.display='';
		 document.getElementById(id.replace("File","FileRemove")).style.display = '';
		 document.getElementById(id.replace("File","Error")).innerHTML = "";
	 }else if(work == 'hide'){
		 document.getElementById(id).value='';
		 document.getElementById(id).style.display='none';
		 document.getElementById(id.replace("File","FileRemove")).style.display = 'none';
		 document.getElementById(id.replace("File","Error")).innerHTML = "";
	 }
 } 

/* used to check file format */
function checkFileFormat(fileId){
	var flag = false;
	var fileName = document.getElementById(fileId).value;
	var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
	if(ext == "pdf" || ext == "PDF" || ext == "JPEG" || ext == "jpeg" || ext == "jpg" || ext == "JPG" ){
		flag = true;
	}
	return flag;
}

function checkFiles(id){	
	 if(id == "all"){
		 var count = 0;
		 var fileErrorLocation = new Array();
		 var attachedFile = new Array();
		 var index = 0;	 
		 if(document.getElementById('regFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("regError");
			 attachedFile[index] = "regFile";
			 index++;
		 }
		 if(document.getElementById('panFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("panError");
			 attachedFile[index] = "panFile";
			 index++;
		 }
		 if(document.getElementById('tanFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("tanError");
			 attachedFile[index] = "tanFile";
			 index++;	 		 
		 }
		 if(document.getElementById('officePhotoFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("officePhotoError");
			 attachedFile[index]  = "officePhotoFile";
			 index++;
		 }	
		 if(document.getElementById('cvFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("cvError");
			 attachedFile[index]  = "cvFile";
			 index++;
		 }	
		 if(document.getElementById('photoFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("photoError");
			 attachedFile[index]  = "photoFile";
			 index++;
		 }	
		for(var i = 0; i < attachedFile.length; i++){
			if(!checkFileFormat(attachedFile[i])){
				fileErrorLocation[i].innerHTML = "Incorrect file format ";
				count++;
			}
		}
		if(count > 0){
			return false;
		}else{
			return true;
		}
	}else{	
		var ErrorID = document.getElementById(id.replace("FileName","Error"));
		ErrorID.innerHTML = "";
		if(!checkFileFormat(id)){			
			ErrorID.innerHTML = "Incorrect file format ";
		}
 	}
}

/* Function used to validate the form feilds */
 
 function validate(){ 
	var piaName = new LiveValidation(document.forms[0].piaName,{onlyOnSubmit:true});
	piaName.add( Validate.Presence );
	piaName.add( Validate.Format, { pattern: /^[a-z\d\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."});
		
	var address = new LiveValidation(document.forms[0].address,{onlyOnSubmit:true});
	address.add( Validate.Presence );	
	
	var pin = new LiveValidation(document.forms[0].pin,{onlyOnSubmit:true});
	pin.add( Validate.Presence );  	
		
	var stateCode = new LiveValidation(document.forms[0].stateCode,{onlyOnSubmit:true});	
	stateCode.add( Validate.Presence );
			
	var officePhone = new LiveValidation(document.forms[0].officePhone,{onlyOnSubmit:true});
	officePhone.add( Validate.Presence );
	officePhone.add( Validate.Numericality, { onlyInteger: true } );		
	officePhone.add( Validate.Length, { is: 11} );
		
	var officeFax = new LiveValidation(document.forms[0].officeFax,{onlyOnSubmit:true});
	officeFax.add( Validate.Presence );
	officeFax.add( Validate.Numericality, { onlyInteger: true } );
	officeFax.add( Validate.Length, { is: 11} );
			
	var email = new LiveValidation(document.forms[0].email,{onlyOnSubmit:true});
	email.add( Validate.Presence);	
	email.add( Validate.Email, {failureMessage: "Invalid email"} );	

  	var website = new LiveValidation(document.forms[0].website,{onlyOnSubmit:true});
  	website.add( Validate.Presence );		
  	
  	var panNo = new LiveValidation(document.forms[0].panNo, {onlyOnSubmit: true } );
  	panNo.add( Validate.Presence );
  	panNo.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
  	
  	var tanNo = new LiveValidation(document.forms[0].tanNo,{onlyOnSubmit:true});
  	tanNo.add( Validate.Presence );	
  	tanNo.add( Validate.Format, { pattern: /^([a-zA-Z]{4})(\d{5})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect TAN Format" } );

  	var pin = new LiveValidation(document.forms[0].pin,{onlyOnSubmit:true});
  	pin.add( Validate.Presence );
  	pin.add( Validate.Length, { is: 6} );
  	
  	var registrationNumber = new LiveValidation(document.forms[0].registrationNumber,{onlyOnSubmit:true});
  	registrationNumber.add( Validate.Presence );
  	
  	var registrationStateCode = new LiveValidation(document.forms[0].registrationStateCode,{onlyOnSubmit:true});
  	registrationStateCode.add( Validate.Presence );
  	
  	var dateOfRegistration = new LiveValidation(document.forms[0].dateOfRegistration,{onlyOnSubmit:true});
  	dateOfRegistration.add( Validate.Presence );
  	
 	 // member validation start
  /* 	var memberName = new LiveValidation(document.forms[0].memberName,{onlyOnSubmit:true});
  	memberName.add( Validate.Presence );
  	
  	var memberDesignation = new LiveValidation(document.forms[0].memberDesignation,{onlyOnSubmit:true});
  	memberDesignation.add( Validate.Presence );  
  	
  	var memberContact = new LiveValidation(document.forms[0].memberContact,{onlyOnSubmit:true});
  	memberContact.add( Validate.Presence );
  	
  	var memberPan = new LiveValidation(document.forms[0].memberPan, {onlyOnSubmit: true } );
  	memberPan.add( Validate.Presence );
  	memberPan.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
  	
  	var memberEmail = new LiveValidation(document.forms[0].memberEmail,{onlyOnSubmit:true});
  	memberEmail.add( Validate.Presence );	
  	memberEmail.add( Validate.Email, {failureMessage: "Invalid email"} );	
  	
  	var memberAadharVoterNo = new LiveValidation(document.forms[0].memberAadharVoterNo,{onlyOnSubmit:true});
  	memberAadharVoterNo.add( Validate.Presence );	
  	
  	var memberPassportDrivingNo = new LiveValidation(document.forms[0].memberPassportDrivingNo,{onlyOnSubmit:true});
  	memberPassportDrivingNo.add( Validate.Presence ); */	
  	// member validation end 
  	
  	// authorized persion validation end 
  	var authPersonName = new LiveValidation(document.forms[0].authPersonName,{onlyOnSubmit:true});
  	authPersonName.add( Validate.Presence );
  	//authPersonName.add( Validate.Format, { pattern: /^[a-z\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."});
  	
  	var authRelativeName = new LiveValidation(document.forms[0].authRelativeName,{onlyOnSubmit:true});
  	authRelativeName.add( Validate.Presence );
  	
  	var authAge = new LiveValidation(document.forms[0].authAge,{onlyOnSubmit:true});
  	authAge.add( Validate.Presence );
  	
	var authDesignation = new LiveValidation(document.forms[0].authDesignation,{onlyOnSubmit:true});
	authDesignation.add( Validate.Presence );
  	
	var authOccupation = new LiveValidation(document.forms[0].authOccupation,{onlyOnSubmit:true});
	authOccupation.add( Validate.Presence );
  	
	var authAddress = new LiveValidation(document.forms[0].authAddress,{onlyOnSubmit:true});
	authAddress.add( Validate.Presence );
	
	var authPersonContact = new LiveValidation(document.forms[0].authPersonContact,{onlyOnSubmit:true});
	authPersonContact.add( Validate.Presence );
	
	var authPersonEmail = new LiveValidation(document.forms[0].authPersonEmail,{onlyOnSubmit:true});
	authPersonEmail.add( Validate.Presence );
	authPersonEmail.add( Validate.Email, {failureMessage: "Invalid email"} );	
	
	var authPersonPassport = new LiveValidation(document.forms[0].authPersonPassport,{onlyOnSubmit:true});
	authPersonPassport.add( Validate.Presence );
	
	var authPersonAdhaar = new LiveValidation(document.forms[0].authPersonAdhaar,{onlyOnSubmit:true});
	authPersonAdhaar.add( Validate.Presence );
	
	var authPersonPan = new LiveValidation(document.forms[0].authPersonPan,{onlyOnSubmit:true});
	authPersonPan.add( Validate.Presence );
	authPersonPan.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
  	
	/* if(document.getElementById("memberLiability").value!=null){
		var memLiability=new LiveValidation(document.forms[0].memberLiability,{onlyOnSubmit:true});
		memLiability.add( Validate.Numericality, { onlyInteger: true });
		memLiability.add(Validate.Numericality,{maximum:100})
	} */
	
	var areAllValid = LiveValidation.massValidate(
			[piaName, address, pin, stateCode, officePhone, officeFax, email, website, panNo, tanNo, pin, registrationNumber,
			 registrationStateCode, dateOfRegistration, /*memberName,memberDesignation,   memberContact, memberPan, memberEmail, 
			 memberAadharVoterNo, memberPassportDrivingNo,*/  authPersonName, authRelativeName, authAge, authDesignation, authOccupation, 
			 authAddress, authPersonContact, authPersonEmail, authPersonPassport, authPersonAdhaar, authPersonPan/* ,memLiability */] );

	if(!checkFiles('all') || !checkPanTan() || !isPiaActivityChecked() || !validateAdditionalDetails()){
		areAllValid = false;
	}
	return areAllValid;	
}
 function isPiaActivityChecked(){
	  var i, count = 0, chks = document.getElementsByName('piaActivity');
	  for (i = 0; i < chks.length; i++){
	    if (chks[i].checked){
	    	count++;
	    }
	  }
	    if(count>0 )
	    	return true;
	    else{
	    	document.getElementById("piaActivityError").innerHTML = "No Applicant Organisation Activity is selected.";
	        return false;
	    }
	  }

 function validateAdditionalDetails(){
	 var first = document.getElementById('regNoSection12A').value;
	 var firstDate = document.getElementById('regDateSection12A').value;
	 
	 var second = document.getElementById('regNoSection80G').value;
	 var secondDate = document.getElementById('regDateSection80G').value;
	 
	 var fcra = document.getElementById('regNoFCRA').value;
	 var fcraDate = document.getElementById('regDateFCRA').value;
	 
	 var errorDiv = document.getElementById('taxErrorDiv');
	 var errCount =0;
	 
	 if(firstDate != '' && first == ''){
		 errorDiv.innerHTML = "Please fill complete detail of registration under section 12A";
		 errCount++;
	 }
	 if(secondDate != '' && second == ''){
		 errorDiv.innerHTML = "Please fill complete detail of registration under section 80G";
		 errCount++;
	 }
	 if(fcraDate != '' && fcra == ''){
		 errorDiv.innerHTML = "Please fill complete detail of registration under FCRA";
		 errCount++;
	 }
	 if(errCount > 0){
		 return false;
	 }
	 else{
		 return true;
	 }
 }
 function checkPanTan(){
	 var errCount = 0;
	 var errorDiv = document.getElementById('panTanError');
	if(document.getElementById('panNo').value != '<logic:present name="piaDetail"><bean:write property="panNo" name="piaDetail"/></logic:present>' && document.getElementById('panFile').value == '')	{
		//errorDiv.innerHTML = 'Please upload PAN file';
		var status=window.confirm("You have changed your PAN No. But didn't changed your PAN file.\n Do you want to continue.");
	 	if(status != true){ 
	 		errCount++;
	 	}
	}
	if(document.getElementById('tanNo').value != '<logic:present name="piaDetail"><bean:write property="tanNo" name="piaDetail"/></logic:present>' && document.getElementById('tanFile').value == '')	{
		//errorDiv.innerHTML = 'Please upload TAN file';
		var status=window.confirm("You have changed your TAN No. But didn't changed your TAN file.\n Do you want to continue.");
	 	if(status != true){ 
	 		errCount++;
	 	}
	}
	if(document.getElementById('registrationNumber').value != '<logic:present name="piaDetail"><bean:write property="registrationNumber" name="piaDetail"/></logic:present>' && document.getElementById('regFile').value == '')	{
		//errorDiv.innerHTML = 'Please upload Registration file';
		var status=window.confirm("You have changed your registration No. But didn't changed your registration file.\n Do you want to continue.");
	 	if(status != true){ 
	 		errCount++;
	 	}
	}
	if(errCount > 0){
		return false;
	}else{
		return true;
	}
 }
 
 function getDistrictList(){
	document.getElementById("districtCode").value = "";
	document.getElementById("blockCode").value = "";
 	clearMultiSelect('districtCode'); 
 	clearMultiSelect('blockCode'); 
 	addOption(document.forms[0].districtCode,'<bean:message key="label.select"/>','' );
 	addOption(document.forms[0].blockCode,'<bean:message key="label.select"/>','' );
 	getDetailsByAjax(document.getElementById("stateCode"),document.forms[0].districtCode.name,'GETDISTRICTLIST');
 }

 function getBlockList(){
	clearMultiSelect('blockCode');  
 	addOption(document.forms[0].blockCode,'<bean:message key="label.select"/>','' );
 	getDetailsByAjax(document.getElementById("districtCode"),document.forms[0].blockCode.name,'GETBLOCKLIST');
 }
 
 function getDetailsByAjax(primaryObject, dependantObject, funcParam){	
	controlName = dependantObject;
	http_request = createAjaxRequest();	
	primaryObjValue = primaryObject.value;					
	if(primaryObject.value !="<bean:message key="label.select"/>"){	
		if(primaryObject.value == "<bean:message key="label.select"/>,<bean:message key="label.select"/>"){						
			return;
		}
	}									
	urlString = "<%=request.getContextPath()%>/DataPopulatorServlet?"
				+"controlName="+controlName
				+"&primaryCode="+primaryObject.value
				+"&funcParam="+funcParam;			 
			 		
	http_request.open('POST',urlString, true);
	http_request.setRequestHeader(tokenParameter, tokenValue);
	http_request.send(null);
	http_request.onreadystatechange = popDetails;			
	
	function popDetails() {
			if (http_request.readyState == 4) {
				evalScript(http_request.responseText);
			}
		}
	}
 
 function showData(){
		document.forms[0].action = "modifyPiaAction.do?methodName=modifyPIADetails&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
 
 function showName(id) {
	 if(id==3 || id==4){
		    document.getElementById("NotChanged").style.display="none";
		    document.getElementById("Changed").style.display="";
		    document.getElementById("Designation").style.display="none";
		    document.getElementById("Liability").style.display="";
	 }
	 if(id!=3 && id!=4){
		 document.getElementById("NotChanged").style.display="none";
		    document.getElementById("Changed").style.display="";
		    document.getElementById("Designation").style.display="";
		    document.getElementById("Liability").style.display="none";
	 }	
}
 
 function handleChange(input) {
	    if (input.value < 0) input.value = 0;
	    if (input.value > 100) {
	    	alert("Liability Value should be less than 100 percent");	  
	    	input.value = '';
	    }
	  }
 
 
 document.getElementById('realtxt').onkeyup = searchSel;
 function searchSel() 
     {
       var input = document.getElementById('realtxt').value.toLowerCase();
        
           len = input.length;
           output = document.getElementById('piaCode').options;
       for(var i=0; i<output.length; i++)
           if (output[i].text.toLowerCase().indexOf(input) != -1 ){
           output[i].selected = true;
               break;
           }
       if (input == '')
         output[0].selected = true;
     }
 
 function hideDetail() {
	 document.getElementById("detail").style.display="none";
 }
 
</script>
<html:form action="/login/modifyPiaAction" method="post" enctype="multipart/form-data">
	<logic:present name="msg">
	<center>
	<font class="label-warning">${msg }</font>
	</center>
</logic:present>

<table width="100%" align="center" style="padding-down: 7px;" class="piaLogTbl">
		<tr>
			<th colspan="2" align="center" class="pageHeader">Applicant Organisation Modification</th>
		</tr>
		<tr>		
			<logic:notEmpty name="listOfVerifiedPIAs">			
				<th width="30%"><label class="label-info">Applicant Organisation List for Modification</label></th>
				<td align="center">
				<b><label class="label-info"><font color="#fff">SEARCH</font></label> </b> <input type="text" id="realtxt" onkeyup="javascript:searchSel();" onkeydown="hideDetail();"/><br/>
				<html:select property="piaCode" name="modifyPiaForm"
						styleId="piaCode" onchange="hideDetail();">
						<html:option value="">Select</html:option>
						<logic:present name="listOfVerifiedPIAs">
							<logic:iterate id="piaList" name="listOfVerifiedPIAs">
								<html:option value="${piaList.piaCode}">${piaList.piaCode}-${piaList.piaName}</html:option>
							</logic:iterate>
						</logic:present>
					</html:select>
				</td>
			</logic:notEmpty>
		</tr>
		<tr>
		<td colspan="2" align="center">
 	<html:button  styleClass="mybtn" property="" onclick="showData();">Show</html:button>
 	</td>     
	</tr>
	</table> 

<div id="detail">
<logic:present name="piaDetail" >
<table width="100%" align="center" style="padding-down: 7px;" class="bordered">
<html:hidden name="piaDetail" property="piaStatus"  />
	<tr>
		<th align="center" class="pageHeader">
		<small><c:if test="${piaDetail.piaStatus != 'V'}" >
			Applicant Organisation Temporary Reference Number:&nbsp;
 			    <bean:write name="piaDetail" property="piaCode" /><br/>
 			    <html:hidden name="piaDetail" property="piaCode" ></html:hidden>
  		</c:if>
	  	<c:if test="${piaDetail.piaStatus == 'V'}" >
	 	    	Applicant Organisation Permanent Registration Number:&nbsp; 
	 		    <bean:write name="piaDetail" property="piaRegistrationNo" /><br/>
	 		    <html:hidden name="piaDetail" property="piaRegistrationNo" ></html:hidden>
	  	</c:if></small>
	  	</th>
	  	</tr>
</table>
<table width="100%" align="center" style="padding-down: 7px;" class="bordered">	  	
	  	<tr><th>
	  	<html:hidden property="createdOn" name="piaDetail"></html:hidden>
	  	<html:hidden property="createdBy" name="piaDetail"></html:hidden>
	  	<html:hidden property="piaConfirmationDate" name="piaDetail"></html:hidden>
		<%-- <bean:write name="piaDetail" property="piaName"/> --%>
		<html:text property="piaName" styleId="piaName" onblur="this.value=this.value.toUpperCase();"  styleClass="smallInputText"
										name="piaDetail" size="70" maxlength="70" />
		<br/>(<c:if test="${piaDetail.piaStatus == 'P'}" ><span class="text-info">Pending</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'C'}" ><span class="text-primary">Submitted</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'S'}" ><span class="text-primary">Submitted</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'V'}" ><span class="text-success">Verified</span></c:if>
		<c:if test="${piaDetail.piaStatus == 'R'}" ><span class="text-error">Rejected</span></c:if>)
		
		<br/><small>OfficePhoto:&nbsp;
							<html:hidden property="officePhotoFileName" name="piaDetail" value="${piaDetail.officePhotoFileName}"></html:hidden>
								<c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.officePhotoFileName}');" >View</a>
									<a href="javascript:fileStatus('officePhotoFile', 'show');">Change</a>
										<html:file property="officePhotoFile"  style="display:none;" onchange="javascript:checkFiles('officePhotoFile');"
										styleId="officePhotoFile" styleClass="infoBtn"/>	
									<a id="officePhotoFileRemove" style="display:none;" href="javascript:fileStatus('officePhotoFile', 'hide');" >Remove</a>
								
										<small><font id="officePhotoError"  class="text-error">${officePhotoError }</font></small>
								</c:if>
			</small>
		<br/><small>Web Site:&nbsp;<html:text property="website" name="piaDetail" onblur="this.value=this.value.toUpperCase();"
										styleId="website" size="60" maxlength="150"></html:text></small><br/>						
	  	</th>	  	
	</tr>
	</table>
	<table width="100%" class="bordered">
<tr><th colspan="2">NITI Aayog Registration Details:</th></tr>
<tr>
<td>
	<strong>NITI Aayog allotted Unique Id:</strong>:&nbsp; 
	
	
	<html:hidden property="panFileName" name="piaDetail" value="${piaDetail.panFileName}"></html:hidden>
		<c:if test="${piaDetail.enrolmentNumber==null}">Not Available</c:if>
		<c:if test="${piaDetail.enrolmentNumber!=null}">${piaDetail.enrolmentNumber}</c:if>
		&emsp;
		<c:if test="${piaDetail.enrolmentNumber!=null}"><a href="javascript:showFiles('${piaDetail.enrolmentFileName}');" >View</a></c:if>
									
	
</tr>
</table>
	
	
	
	
	
	
	

<table width="100%" align="center" class="bordered">
	<tr>
		<th>Address</th>
		<th>Contact Detail</th>
	</tr>
	<tr>
	<td>
		<html:textarea property="address" onblur="this.value=this.value.toUpperCase();"
		name="piaDetail" styleId="address" cols="48" rows="2" /><br/>	  		  	
	  	
	  	<strong>State</strong>:&nbsp;<html:select property="stateCode"
										name="piaDetail" styleId="stateCode"
										onchange="getDistrictList();">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode"
												labelProperty="stateName" />
										</logic:present>
									</html:select><br/>
	  	<strong>District</strong>:&nbsp;<html:select property="districtCode"
										name="piaDetail" styleId="districtCode"
										onchange="getBlockList();">
										<html:option value="">Select</html:option>
										<logic:present name="districtList">
											<html:options collection="districtList"
												property="districtCode" labelProperty="districtName" />
										</logic:present>
									</html:select><br/>
	  <strong>Block</strong>:&nbsp;<html:select property="blockCode"
										name="piaDetail" styleId="blockCode">
										<html:option value="">Select</html:option>
										<logic:present name="blockList">
											<html:options collection="blockList" property="blockCode"
												labelProperty="blockName" />
										</logic:present>
									</html:select><br/>
	  	
	  	<strong>Pin</strong>:&nbsp;<html:text property="pin" name="piaDetail" onkeypress="return isNumberKey(event)"
										styleId="pin" size="6" maxlength="6" />
		</td>
		
		<td>
		    <strong>Office Phone</strong>:&nbsp;<html:text property="officePhone"
										name="piaDetail" size="11" maxlength="11" onkeypress="return isNumberKey(event)"
										styleId="officePhone" /><br/>
			<strong>Office Fax No</strong>:&nbsp;<html:text property="officeFax" name="piaDetail" onkeypress="return isNumberKey(event)"
										size="11" maxlength="11" styleId="officeFax" /><br/>
			<strong>Email</strong>:&nbsp;<html:text property="email" name="piaDetail" onblur="this.value=this.value.toUpperCase();"
										styleId="email" size="30" maxlength="150"></html:text><br/>		
		</td>
	</tr>
</table>


<table width="100%" align="center" style="padding-down: 7px;" class="bordered">	 
<tr>	

	<th>Category of Applicant</th>
	<td><logic:present name="piaCategory">
						<c:if test="${piaDetail.piaStatus == 'V' }">
							<logic:iterate id="category" name="piaCategory">
								<c:if test="${piaDetail.categoryCode == category.categoryId }">
									<html:radio property="categoryCode" name="piaDetail" styleId="categoryCode" value="${category.categoryId}" >
											&nbsp;<bean:write name="category" property="categoryName" />
									</html:radio>
								</c:if>

							</logic:iterate>
						</c:if>
						<c:if test="${piaDetail.piaStatus != 'V' }">						
							<logic:iterate id="category" name="piaCategory">	
							<br/>							
									<html:radio property="categoryCode" name="piaDetail" styleId="categoryCode" value="${category.categoryId}" onclick="javascript:showName(${category.categoryId});">
											&nbsp;<bean:write name="category" property="categoryName" />
									</html:radio>								
							</logic:iterate>
						</c:if>
					</logic:present></td>
</tr>
<tr>
	<th>Type of business/Activity of Applicant Organisation</th>

	<td><logic:present name="piaActivity">
							    <c:set var="selectActivity" value="${fn:split(piaDetail.activityCode,', ')}" /> 
								<logic:iterate id="activity" name="piaActivity" indexId="index">
								<c:set var="actFlag" value="0" />									
											<logic:iterate id="slact" name="selectActivity" >
												<c:if test="${activity.activityId == slact }" >
													<input type="checkbox" name="piaActivity" value="${activity.activityId}" checked id="piaActivity" onclick="javascript:document.getElementById('piaActivityError').innerHTML = '';">
													&nbsp;<bean:write name="activity" property="activityName" /><br/>
													<c:set var="actFlag" value="1" />							
												</c:if>										
											</logic:iterate>	
									<c:if test="${actFlag == 0 }" >
										<html:checkbox property="piaActivity" styleId="piaActivity"   value="${activity.activityId}" >
											&nbsp;<bean:write name="activity" property="activityName" /><br/>	
										</html:checkbox>
									</c:if>					
								</logic:iterate>	
								</logic:present>
								<font id="piaActivityError" color="red" ></font>
	</td>
</tr>
</table>
					
<table width="100%" class="bordered">
<tr><th colspan="2">Registration Detail</th></tr>
<tr>
<td>	
	<strong>State where registered</strong>:&nbsp;<html:select property="registrationStateCode"
										name="piaDetail" styleId="registrationStateCode">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode"
												labelProperty="stateName" />
										</logic:present>
									</html:select><br/>
         <c:if test="${piaDetail.registrationNumber != null}" >
         
	<strong>Registration number</strong>:&nbsp;
										<html:hidden property="regFileName" name="piaDetail" value="${piaDetail.regFileName}"></html:hidden>
										<html:text property="registrationNumber" onblur="this.value=this.value.toUpperCase();"
										name="piaDetail" size="25" maxlength="21"
										styleId="registrationNumber" /> 
								<c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.regFileName }');" >View</a>
									<a href="javascript:fileStatus('regFile', 'show');">Change</a>
										<html:file property="regFile" style="display:none;" onchange="javascript:checkFiles('regFile');"
										styleId="regFile" styleClass="infoBtn"/>	
									<a id="regFileRemove" style="display:none;" href="javascript:fileStatus('regFile', 'hide');" >Remove</a>
										<small><font id="regError"  class="text-error">${regError }</font></small>
								</c:if><br/>
         </c:if>
	<strong>Date of registration</strong>:&nbsp;<html:text property="dateOfRegistration" value="${piaDetail.dateOfRegistration}" styleId="dateOfRegistration" readonly="true" size="10" maxlength="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].dateOfRegistration,'dd-mm-yyyy',this)"/>

</td>
<td>
	<strong>PAN</strong>:&nbsp; <html:hidden property="panFileName" name="piaDetail" value="${piaDetail.panFileName}"></html:hidden>
								<html:text property="panNo" name="piaDetail" onblur="this.value=this.value.toUpperCase();"
										styleId="panNo" size="12" maxlength="10" /> <c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.panFileName }');" >View</a>
									<a href="javascript:fileStatus('panFile', 'show');">Change</a>
										<html:file property="panFile" style="display:none;"
										styleId="panFile" styleClass="infoBtn" onchange="javascript:checkFiles('panFile');"/>										
									<a id="panFileRemove" style="display:none;" href="javascript:fileStatus('panFile', 'hide');" >Remove</a>
									
										<small><font id="panError"  class="text-error">${panError }</font></small>
								</c:if><br/>
	<strong>TAN</strong>:&nbsp;
							<html:hidden property="tanFileName" name="piaDetail" value="${piaDetail.tanFileName}"></html:hidden>
							<html:text property="tanNo" name="piaDetail"
										styleId="tanNo" size="12" maxlength="10" onblur="this.value=this.value.toUpperCase();"/> 
									<c:if test="${piaDetail.piaCode != null}">
										<a href="javascript:showFiles('${piaDetail.tanFileName}');" >View</a>
										<a href="javascript:fileStatus('tanFile', 'show');">Change</a>
										<html:file property="tanFile"  style="display:none;"
										styleId="tanFile" styleClass="infoBtn" onchange="javascript:checkFiles('tanFile');"/>										
									<a id="tanFileRemove" style="display:none;" href="javascript:fileStatus('tanFile', 'hide');" >Remove</a>
									
										<small><font id="tanError"  class="text-error">${tanError }</font></small>
									
									</c:if></td>
</tr>
</table>

<table width="100%" align="center" class="bordered">	
	<tr><th colspan="3">Details of the Owners/ Directors </th></tr>	
	<tr>
		<td><strong>Name</strong></td>
		<td><strong>Contacts</strong></td>
		<td><strong>Other Details</strong></td>
		
	</tr>
	<logic:present name="piaMemberDetail">
	<logic:iterate id="member" name="piaMemberDetail">
	<c:if test="${(member.nriStatus != 'yes') && (member.authorizedPerson != 'yes')}">
	<tr>
		<html:hidden property="memberCode" name="member"></html:hidden>
		<td><strong>Name</strong>:&nbsp;
			<html:text name="member" property="memberName" styleId="memberName" onblur="this.value=this.value.toUpperCase();"/><br/>
		
		
		<div id="NotChanged">
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
	    	<strong>Designation</strong>:&nbsp;
	    		<html:select property="memberDesignation" value="${member.designation}" styleId="memberDesignation">
	    			<html:option value="">Select</html:option>
	    			<html:option value="Director">Director</html:option>
	    		</html:select>
	
	    </c:if>
	    <c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
	    	<strong>Liability</strong>:&nbsp;<html:text property="memberLiability" styleId="memberLiability"  value="${member.liability}" onblur="this.value=this.value.toUpperCase();handleChange(this);" onkeypress="return isNumberKey(event)" />
	    </c:if>
	    </div>
	    
	    <div id="Changed" style="display: none;">
		<div id="Designation" style="display: none;">
	    	<strong>Designation</strong>:&nbsp;
	    		<html:select property="memberDesignation" value="${member.designation}" styleId="memberDesignation">
	    			<html:option value="">Select</html:option>
	    			<html:option value="Director">Director</html:option>
	    		</html:select>
	     </div> 
	     <div id="Liability" style="display: none;">
	    	<strong>Liability(Range 1-100)</strong>:&nbsp;<html:text property="memberLiability" styleId="memberLiability"  value="${member.liability}" onblur="this.value=this.value.toUpperCase();handleChange(this);" onkeypress="return isNumberKey(event)"/>
	    </div>
	    </div>
	    
	    
	    </td>
		<td><strong>Contact</strong>:&nbsp;<html:text property="memberContact" styleId="memberContact" value="${member.contact}" maxlength="11" onkeypress="return isNumberKey(event)"/><br/>
			<strong>Email-Id</strong>:&nbsp;<html:text property="memberEmail" styleId="memberEmail" value="${member.email}" onblur="this.value=this.value.toUpperCase();"/></td>
		<td><strong>PAN</strong>:&nbsp;<html:text property="memberPan" styleId="memberPan" value="${member.pan}" onblur="this.value=this.value.toUpperCase();"/><br/>
			<strong>Aadhaar No. <br>/ Voter ID card No.</strong>:&nbsp;<html:text property="memberAadharVoterNo" styleId="memberAadharVoterNo" value="${member.aadharVoterNo}" onblur="this.value=this.value.toUpperCase();"/><br/>
			<strong>Passport No.<br>/ Driving Licence No.</strong>:&nbsp;<html:text property="memberPassportDrivingNo" styleId="memberPassportDrivingNo" value="${member.passportDrivingNo}" onblur="this.value=this.value.toUpperCase();"/></td>
 	</tr>	
	</c:if> 
	</logic:iterate>
	</logic:present>
</table>

<table width="100%" align="center" class="bordered" >	
	<tr><th colspan="3">Authorized Person Details</th></tr>
	<logic:present name="piaMemberDetail">
	<logic:iterate id="member" name="piaMemberDetail">
	<logic:equal value="yes" name="member" property="authorizedPerson">
		<html:hidden property="authPersonCode" value="${member.memberCode }"></html:hidden>
	    <tr>
	    	<td colspan="2"><strong>Personal Detail</strong></td>
	    	<td><strong>Communication / Other Details</strong></td>
	   		<!-- <td><strong>Photo and CV</strong></td> -->
	    </tr>
	    <tr>
	       <td>		
		  <%--   Uploaded Photo:<a href="javascript:showFiles('${member.photoFileName}');">View</a><br/> --%>
		   
		    Uploaded CV:
		    <html:hidden property="authCVFileName" name="member" value="${member.cvFileName}"></html:hidden>
		    <a href="javascript:showFiles('${member.cvFileName}');" >View</a>
			<a href="javascript:fileStatus('cvFile', 'show');">Change</a>
			<html:file property="cvFile"  style="display:none;" onchange="javascript:checkFiles('cvFile');"
					styleId="cvFile" styleClass="infoBtn"/>	
			<a id="cvFileRemove" style="display:none;" href="javascript:fileStatus('cvFile', 'hide');" >Remove</a>
			
				<small><font id="cvError"  class="text-error">${cvError }</font></small>
		   <br/><br/>	
		   <html:hidden property="authPhotoFileName" name="member" value="${member.photoFileName}"></html:hidden>
		     <img src="<%=request.getContextPath()%>/prn/Downloader?imageName=${member.photoFileName}" width="100" height="80" > <br/>
			<a href="javascript:fileStatus('photoFile', 'show');">Change Photo</a>
			<html:file property="photoFile"  style="display:none;" onchange="javascript:checkFiles('photoFile');"
					styleId="photoFile" styleClass="infoBtn"/>	
			<a id="photoFileRemove" style="display:none;" href="javascript:fileStatus('photoFile', 'hide');" >Remove</a>
			
				<small><font id="photoError"  class="text-error">${photoError }</font></small>
		    
	       </td>
	    	<td><strong>Name</strong>:&nbsp;<html:text property="authPersonName" styleId="authPersonName" value="${member.memberName }" onblur="this.value=this.value.toUpperCase();"/><br/>
			    <strong>S/o, D/o, W/o</strong>:&nbsp;<html:text property="authRelativeName" styleId="authRelativeName" value="${member.relativeName }" onblur="this.value=this.value.toUpperCase();"/><br/>
			    <strong>Age</strong>:&nbsp;<html:text property="authAge" styleId="authAge" value="${member.age}" maxlength="2" onkeypress="return isNumberKey(event)"/><br/>
			    <strong>Designation</strong>:&nbsp;<html:text property="authDesignation" value="${member.designation}" styleId="authDesignation" onblur="this.value=this.value.toUpperCase();"/>
				<br/>	
				<strong>Occupation</strong>:&nbsp;<html:text property="authOccupation" styleId="authOccupation" value="${member.occupation}" onblur="this.value=this.value.toUpperCase();"/>
			</td>	
		    <td>
		    		<strong>Residence Address</strong><br/>
		    		<html:textarea property="authAddress" styleId="authAddress" value="${member.address}" cols="35" rows="2"  onblur="this.value=this.value.toUpperCase();"/><br/>
					<strong>Contact</strong>:&nbsp;<html:text property="authPersonContact" styleId="authPersonContact" value="${member.contact}" maxlength="11" onkeypress="return isNumberKey(event)"/><br/>
					<strong>Email</strong>:&nbsp;<html:text property="authPersonEmail" styleId="authPersonEmail" value="${member.email}" onblur="this.value=this.value.toUpperCase();"/><br/>
					<strong>Passport No./Driving Licence No</strong>:&nbsp;<html:text property="authPersonPassport" styleId="authPersonPassport" value="${member.passportDrivingNo}" onblur="this.value=this.value.toUpperCase();"/><br/>
					<strong>Aadhaar No./Voter ID card No</strong>:&nbsp;<html:text property="authPersonAdhaar" styleId="authPersonAdhaar" value="${member.aadharVoterNo}" onblur="this.value=this.value.toUpperCase();"/><br/>
					<strong>PAN No:</strong><html:text property="authPersonPan" styleId="authPersonPan" value="${member.pan}" maxlength="10" size="13" onblur="this.value=this.value.toUpperCase();"/>
			</td>
	      </tr>
	
	</logic:equal>
	</logic:iterate>
	</logic:present>  
</table>		

			
<logic:equal value="1" name="piaDetail" property="categoryCode">				
<table width="100%"  align="center" class="bordered" >
	<tr><th colspan="2">Details of Land and Building owned by the Educational Institution</th></tr>
	<tr>	
		<td>
			<strong>Address of Land / Building :</strong><br/>
			<html:textarea name="piaDetail" property="addressLandBuilding" cols="48" rows="2" onblur="this.value=this.value.toUpperCase();"/>
		</td>
		<td>
			<strong>Whether Freehold / mortgaged :</strong><br/>
			<html:select  property="freeholdMortgaged" styleId="freeholdMortgaged" name="piaDetail" value="${piaDetail.freeholdMortgaged}">
				<html:option value="">Select</html:option>
				<html:option value="Freehold">Freehold</html:option>
				<html:option value="Mortgaged">Mortgaged</html:option>				
			</html:select>
		</td>
	</tr>		
</table>
</logic:equal>
<table width="100%" align="center" class="bordered">
	<tr>
		<th colspan="5">Additional Details
		<font color="red" id="taxErrorDiv"></font>
		</th>
	</tr>
	<tr><td width="5%"><strong>S.No.</strong></td>
		<td width="50%"></td>
		<td width="30%"><strong>Registration Number</strong></td>
		<td width="20%"><strong>Registration Date</strong></td>
	</tr>
	<tr><td>1</td>
		<td><strong>Details of registration under section 12A of Income Tax Act of 1956</strong></td>
		<td><html:text name="piaDetail" property="regNoSection12A" styleId="regNoSection12A" onclick="this.value=this.value.toUpperCase();document.getElementById('taxErrorDiv').innerHTML='';" size="10" maxlength="16"/></td>
		<td><html:text property="regDateSection12A" value="${piaDetail.regDateSection12A }" styleId="regDateSection12A" readonly="true"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateSection12A,'dd-mm-yyyy',this);document.getElementById('taxErrorDiv').innerHTML='';"/></td>
		<td><small style="cursor: pointer;color: blue;" onclick="javascript:document.getElementById('regDateSection12A').value='';document.getElementById('taxErrorDiv').innerHTML='';">Remove Date</small></td>
	</tr>
	<tr><td>2</td>
		<td><strong>Details of registration under section 80G of Income Tax Act of 1956</strong></td>
		<td><html:text name="piaDetail" property="regNoSection80G" styleId="regNoSection80G" onclick="this.value=this.value.toUpperCase();document.getElementById('taxErrorDiv').innerHTML='';" size="10" maxlength="16"/></td>
		<td><html:text property="regDateSection80G" value="${piaDetail.regDateSection80G }" styleId="regDateSection80G" readonly="true" 
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateSection80G,'dd-mm-yyyy',this);document.getElementById('taxErrorDiv').innerHTML='';"/></td>
		<td><small style="cursor: pointer;color: blue;" onclick="javascript:document.getElementById('regDateSection80G').value='';document.getElementById('taxErrorDiv').innerHTML='';">Remove Date</small></td>
	</tr>
	<tr><td>3</td>
		<td><strong>Details of registration under FCRA</strong></td>
		<td><html:text name="piaDetail" property="regNoFCRA" styleId="regNoFCRA" onclick="this.value=this.value.toUpperCase();document.getElementById('taxErrorDiv').innerHTML='';" size="10" maxlength="16"/></td>
		<td><html:text property="regDateFCRA" value="${piaDetail.regDateFCRA }" styleId="regDateFCRA" readonly="true"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateFCRA,'dd-mm-yyyy',this);document.getElementById('taxErrorDiv').innerHTML='';"/></td>
		<td><small style="cursor: pointer;color: blue;" onclick="javascript:document.getElementById('regDateFCRA').value='';document.getElementById('taxErrorDiv').innerHTML='';">Remove Date</small></td>
	</tr>
</table>
<div align="center" style="padding-top: 15px; padding-bottom: 10px;">
 	<html:button  styleClass="defaultBtn" property="" onclick="modifyPage()">Modify</html:button> 
    <html:button  styleClass="defaultBtn" property="" onclick="backPage()">Back</html:button> 
	</div>
</logic:present>	
</div>
</html:form>
