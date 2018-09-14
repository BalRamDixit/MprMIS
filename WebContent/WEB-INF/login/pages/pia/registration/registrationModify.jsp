<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<script type="text/javascript">

var tokenParameter = 'reqtrack';
var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';

function updatePartA(){
	var validationCheck = validate();
	var catvalue = document.querySelector('input[name = "categoryCode"]:checked').value;	
	
	if(catvalue==6 || catvalue==7 || catvalue==8){	
		
		var enrolmentNumber=document.getElementById("enrolmentNumber").value;
		var enrolmentFile ='${piaDetail.enrolmentFileName}';
		if(enrolmentNumber=="" )
		{
			alert("Unique Id can not be empty in case of NGOs");
			document.getElementById("enrolmentNumber").focus();
			return;
		}
		if(enrolmentFile=="")
		{
			alert("Unique File can not be empty in case of NGOs");
			document.getElementById("enrolmentNumber").focus();
			return;
		}
	
		if(validationCheck == true){
			var status=window.confirm('<bean:message key="msg.saveForm" />');
		    if(status==true){
		    	document.forms[0].action = "registrationAction.do?methodName=updatePartA&" + tokenParameter + "=" + tokenValue;
		    	document.forms[0].submit();
		    }
		}
	
	}else{	
		if(validationCheck){
			var status=window.confirm('<bean:message key="msg.saveForm" />');
		    if(status){
		    	document.forms[0].action = "registrationAction.do?methodName=updatePartA&" + tokenParameter + "=" + tokenValue;
		    	document.forms[0].submit();
		    }
		}	
	}
}

 /* used to continue with the next page */
 function continueWithPartB(){
		var captcha = checkCaptcha();
		if(captcha){
		var status=window.confirm('Clicking on continue button will take you to next page without saving/updating details of the page.');
		if(status){
		    	document.forms[0].action = "registrationAction.do?methodName=continueWithPartB&" + tokenParameter + "=" + tokenValue;
		    	document.forms[0].submit(); 
		}
		}
 }
 
 /* used to quit from the registration page */
 function quit(){
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if(status==true){
			window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
		}
	}
 
 function removeError(first,second){
	 document.getElementById(first).innerHTML='';
	 document.getElementById(second).innerHTML='';
 }
 
 function checkCaptcha(){
		var flag = false;
		var div = document.getElementById("captchaError");
		div.innerHTML= "";
		var captcha = document.getElementById("captchaResponse").value;
		var xmlhttp;
		var captchaText = document.getElementById("captchaResponse").value;
		//var url =  location.protocol + '//' + location.host+"/prnlive/captchaCheck?captcha="+captchaText;

		var projName = '${pageContext.request.contextPath}';
		var url =  location.protocol + '//' + location.host+"/"+projName.substr(1)+"/captchaCheck?captcha=" + captchaText;

		if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.open("POST",url,false);
		xmlhttp.send();
		if(xmlhttp.responseText == "false"){
			var message = "Incorrect captcha";
			div.innerHTML= message;
		}else {
			flag = true;
		}
		return flag;
	}

 function showFiles(fileType) {
		document.forms[0].action = "registrationAction.do?methodName=showFiles&file="+fileType+"&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
 
 function fileStatus(id, work){
	 if(work == 'show'){
		 document.getElementById(id).style.display='';
		 document.getElementById(id.replace("File","FileRemove")).style.display = '';
		 document.getElementById(id.replace("File","Error")).innerHTML = "";
	 }else if(work == 'hide'){
		 document.getElementById(id).style.display='none';
		 document.getElementById(id).value = '';
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

/* used to check file size */
/* function checkFileSize(fileId){
	var flag = false;
	var sizeInBytes = document.getElementById(fileId).files[0].size;
	var fileSize = (sizeInBytes/1024);
	if(200 > fileSize){
		flag = true;
	 }
	return flag;
} */

function checkFiles(id){	
	 if(id == "all"){
		 var count = 0;
		 var fileErrorLocation = new Array();
		 var attachedFile = new Array();
		 var index = 0;	 
		 
			/////////////
		
			if(document.getElementById('enrolmentFile').style.display != 'none'){
				 fileErrorLocation[index] = document.getElementById("enrolError");
				 attachedFile[index] = "enrolmentFile";
				 index++;
			 }
		 
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
		for(i = 0; i < attachedFile.length; i++){
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
	var event1 = new LiveValidation(document.forms[0].piaName,{onlyOnSubmit:true});
		event1.add( Validate.Presence );
		event1.add( Validate.Format, { pattern: /^[a-z\d\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."});
		
	/* var event2 = new LiveValidation(document.forms[0].registrationNumber,{onlyOnSubmit:true});	
		event2.add( Validate.Presence ); */
		
	var event4 = new LiveValidation(document.forms[0].address,{onlyOnSubmit:true});
		event4.add( Validate.Presence );	
		
	var event5 = new LiveValidation(document.forms[0].stateCode,{onlyOnSubmit:true});	
		event5.add( Validate.Presence );
			
	var event6 = new LiveValidation(document.forms[0].officePhone,{onlyOnSubmit:true});
		event6.add( Validate.Presence );
		event6.add( Validate.Numericality, { onlyInteger: true } );		
		event6.add( Validate.Length, { is: 11} );
		
	var event7 = new LiveValidation(document.forms[0].officeFax,{onlyOnSubmit:true});
		event7.add( Validate.Presence );
		event7.add( Validate.Numericality, { onlyInteger: true } );
		event7.add( Validate.Length, { is: 11} );
			
	var event8= new LiveValidation(document.forms[0].email,{onlyOnSubmit:true});
		event8.add( Validate.Presence);	
		event8.add( Validate.Email );

  	var event16 = new LiveValidation(document.forms[0].website,{onlyOnSubmit:true});
	  	event16.add( Validate.Presence );	
	  	
	var event17 = new LiveValidation(document.forms[0].captchaResponse,{onlyOnSubmit:true});
	  	event17.add( Validate.Presence );	
	 
	var event18 = new LiveValidation(document.forms[0].pin,{onlyOnSubmit:true});
	  	event18.add( Validate.Presence );
	  	
 
	var areAllValid = LiveValidation.massValidate( [ event1, event4, event5, event6, event7, event8, event16, event17, event18] );
	/* if(!checkFiles('all') || !checkCaptcha()){ */
	if( !checkCaptcha()  || !checkFiles('all')){
		areAllValid = false;
	}
	return areAllValid;	
}

 function validateEntireForm(){	
	var piaName = new LiveValidation('piaName', {onlyOnBlur: true } );
		piaName.add( Validate.Format, { pattern: /^[a-z\d\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed." } );
		
	var email = new LiveValidation('email', {onlyOnBlur: true } );
		email.add( Validate.Email );
			
	var officePhone = new LiveValidation('officePhone', {onlyOnBlur: true } );
		officePhone.add( Validate.Numericality, { onlyInteger: true } );
		officePhone.add( Validate.Length, { is: 11} );
			
	var officeFax = new LiveValidation('officeFax', {onlyOnBlur: true } );
		officeFax.add( Validate.Numericality, { onlyInteger: true } );
		officeFax.add( Validate.Length, { is: 11} );
	
	var captcha = new LiveValidation('captchaResponse',{onlyOnBlur: true});
		captcha.add(Validate.Presence);

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
</script>

<body onload="validateEntireForm()">
	<!-- Rahul -->
	<!-- <h3>registration.jsp</h3> -->
	<!-- To display the file name -->
	<%-- <h3><%= pageContext.getPage().getClass().getName().substring(pageContext.getPage().getClass().getName().lastIndexOf('.')+1).replace('_','.') %></h3> --%>
	
	<html:form action="registrationAction" method="post" enctype="multipart/form-data">
	<div class="outerTBL">
		<table width="100%">
		<tr>
			<td class="pageHeader" align="center">Applicant Organisation <small>Registration Form</small></td>
		</tr>
				<tr>
					<td align="center">
						<table width="98%" class="inputTBL">
							<c:if test="${registrationForm.piaCode != null }">
							<tr align="center">															
								<td  style="padding-top: 10px;" colspan="2">
									<p><label class="label-info" style="font: 18px;">Temporary Reference Number <span class="label-important">( Please note this number for future references ): </span> 
									<span class="label-warning"><bean:write name="registrationForm" property="piaCode"/></span></label></p>
 								</td>										
							</tr>
							</c:if>
							<tr>
								<td style="padding-top: 10px;" colspan="2"><label class="label-important">All fields
										marked with (*) are mandatory to fill.</label></td>									
							</tr>
							
							<tr>
								<th colspan=2"><span class="text-primary">Category of Applicant Organisation <span class="text-error">*</span>
								</span>
								</th>
							</tr>
							<tr>							
								<td colspan="2"><logic:present name="piaCategory">
										<logic:iterate id="category" name="piaCategory">
											<c:if test="${registrationForm.categoryCode == category.categoryId }">
											<html:radio property="categoryCode" name="registrationForm"
												styleId="categoryCode" value="${category.categoryId}"
												onclick="javascript:showName(${category.categoryId});">
											&nbsp;<bean:write name="category" property="categoryName" />
											</html:radio>
											</c:if>
										</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr>
								<th colspan="2"><span class="text-primary">Type of
										business/activity of Applicant Organisation </span> <span class="text-error">*</span> <small><span
										class="text-error">(Please tick mark options given
											below, multiple options permitted)</span>
								</small></th>
							</tr>
							<tr>
								<td colspan="2">
								<logic:present name="piaActivity">
								<%-- <c:set var="selectActivity" value="${fn:split(piaDetail.activityCode,', ')}" /> --%>
								<logic:iterate id="activity" name="piaActivity" indexId="index">
								<c:set var="actFlag" value="0" />									
											<logic:iterate id="slact" name="registrationForm"  property="piaActivity">
												<c:if test="${activity.activityId == slact }" >
													<input type="checkbox" name="piaActivity" readonly="true" value="${activity.activityId}" checked id="piaActivity">
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
								</td>								
							</tr>
						</table>

						<table style="padding-top: 10px;" width="98%" class="inputTBL">
							<tr>
								<td id="catName" width="20%">Name of the Applicant Organisation <span class="text-error">*</span>
								</td>
								<td><html:text property="piaName" styleId="piaName"
										name="registrationForm" size="100" maxlength="100" onblur="this.value=this.value.toUpperCase();" />
								</td>
							</tr>
						</table>
						<table style="padding-top: 10px;" width="98%" class="inputTBL">
								<tr>
									<th colspan="3"><span class="text-primary">NITI Aayog Registration Details:</span></th>									
								</tr>
								<tr>
								<th colspan="2"><span><small><b><em>
													" Note:- In case of NGOs (Society, Trust and Cooperative
													Society), please provide the below details. As per NITI
													Aayog, GoI, All NGOs must be registered on NITI Aayog
													Portal (NGO Darpan Portal) and should obtain unique
													identifiers before submitting their application for grants
													from any Ministry "</em> </b></small></span></th>
								</tr>
							<tr>
								<td width="20%">NITI Aayog Alloted Unique Id: <span
									class="text-error"></span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span></small>
								</td>
								<td>
								<html:text property="enrolmentNumber" name="registrationForm" 
								size="21" maxlength="21" onblur="this.value=this.value.toUpperCase();"  styleId="enrolmentNumber" /> 								
								<c:if test="${registrationForm.piaCode != null}" >
									<a href="javascript:showFiles('${piaDetail.enrolmentFileName }');" >View</a>
									<a href="javascript:fileStatus('enrolmentFile', 'show');">Change</a>
									
										<html:file property="enrolmentFile" name="registrationForm" style="display:none;" 
										styleId="enrolmentFile" styleClass="infoBtn" onclick="javascript:removeError('enrolError','enError');javascript:checkimg(this.value);"/>							
										
										
										
										
									<a id="enrolFile" style="display:none;" href="javascript:fileStatus('enrolmentFile', 'hide');" >Remove</a>
									
									<small><font id="enrolError" class="text-error"></font></small>
									<logic:notEmpty name="enrolError">
										<small><font id="enError" class="text-error">${enrolError }</font></small>
									</logic:notEmpty>
								</c:if>
								</td>
							</tr>
						</table>
						
						<table style="padding-top: 15px;" width="98%" class="inputTBL">
							<tr>
								<th colspan="3"><span class="text-primary">Address
										and Contact Detail</span>
								</th>
							</tr>
							<tr>
								<td width="20%">Address <span class="text-error">*</span>
								</td>
								<td colspan="2"><html:textarea property="address"
										name="registrationForm" onblur="this.value=this.value.toUpperCase();" styleId="address" cols="52" rows="3" />
								</td>
							</tr>
							<tr>
								<td width="20%">Pin <span class="text-error">*</span></td>
								<td><html:text property="pin" name="registrationForm" 
										styleId="pin" size="6" maxlength="6" /></td>
							</tr>
							<tr>
								<td width="20%">Office Photo <span class="text-error">*</span><br/>
								<small><span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 200kb)</span></small>
								</td>
								<td>		
									<c:if test="${registrationForm.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.officePhotoFileName}');" >View</a>
									<a href="javascript:fileStatus('officePhotoFile', 'show');">Change</a>
										<html:file property="officePhotoFile" name="registrationForm" style="display:none;" 
										styleId="officePhotoFile" styleClass="infoBtn" onclick="javascript:removeError('officePhotoError','oError')"/>	
									<a id="officePhotoFileRemove" style="display:none;" href="javascript:fileStatus('officePhotoFile', 'hide');" >Remove</a>
									<small><font id="officePhotoError" class="text-error"></font></small>
									<logic:notEmpty name="officePhotoError">
										<small><font id="oError" class="text-error">${officePhotoError }</font></small>
									</logic:notEmpty>
								</c:if>
								
								</td>
							</tr>
							<tr>
								<td>State <span class="text-error">*</span>
								</td>
								<td colspan="2"><html:select property="stateCode"
										name="registrationForm" styleId="stateCode" 
										onchange="getDistrictList();">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode"
												labelProperty="stateName" />
										</logic:present>
									</html:select></td>
							</tr>
							<tr>
								<td>District</td>
								<td colspan="2"><html:select property="districtCode"
										name="registrationForm" styleId="districtCode" 
										onchange="getBlockList();">
										<html:option value="">Select</html:option>
										<logic:present name="districtList">
											<html:options collection="districtList"
												property="districtCode" labelProperty="districtName" />
										</logic:present>
									</html:select></td>
							</tr>
							<tr>
								<td>Block</td>
								<td colspan="2"><html:select property="blockCode" 
										name="registrationForm" styleId="blockCode">
										<html:option value="">Select</html:option>
										<logic:present name="blockList">
											<html:options collection="blockList" property="blockCode"
												labelProperty="blockName" />
										</logic:present>
									</html:select></td>
							</tr>
							<tr>
								<td>Office Phone <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="officePhone"
										name="registrationForm"  size="11" maxlength="11"
										styleId="officePhone" />
										<span class="text-error"><small>(In case of mobile add 0 in the beginning)</small></span>
								</td>
							</tr>
							<tr>
								<td>Office Fax No <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="officeFax" name="registrationForm" 
										size="11" maxlength="11" styleId="officeFax" />
								</td>
							</tr>

							<tr>
								<td>Email<span class="text-error">*<small><br/>(Yahoo Email Id is not preffered)</small></span></td>
								<td colspan="2"><html:text property="email" name="registrationForm" onblur="this.value=this.value.toUpperCase();"
										styleId="email" size="35" maxlength="150"></html:text>
								</td>
							</tr>

							<tr>
								<td>Website <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="website" name="registrationForm" 
										styleId="website" size="60" onblur="this.value=this.value.toUpperCase();" maxlength="150"></html:text>
								</td>
										
							</tr>
						</table>
						<table style="padding-top: 15px;" width="98%" class="inputTBL">
							<tr>
								<th colspan="2"><span class="text-primary">
										Registration Detail of the Applicant Organisation  (as per the Registartion Certificate)</span>
								</th>
							</tr>
							<tr>
								<td width="20%">Registration Number <span
									class="text-error">*</span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span></small>
								</td>
								<td>
								<html:text property="registrationNumber"
										name="registrationForm" size="21"  maxlength="21" onblur="this.value=this.value.toUpperCase();"
										styleId="registrationNumber" /> 
								<c:if test="${registrationForm.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.regFileName }');" >View</a>
									<a href="javascript:fileStatus('regFile', 'show');">Change</a>
										<html:file property="regFile" name="registrationForm" style="display:none;" 
										styleId="regFile" styleClass="infoBtn" onclick="javascript:removeError('regError','rError')"/>	
									<a id="regFileRemove" style="display:none;" href="javascript:fileStatus('regFile', 'hide');" >Remove</a>
									<small><font id="regError" class="text-error"></font></small>
									<logic:notEmpty name="regError">
										<small><font id="rError" class="text-error">${regError }</font></small>
									</logic:notEmpty>
								</c:if>
								</td>
							</tr>
							<tr>
								<td>State where registered <span class="text-error">*</span>
								</td>
								<td><html:select property="registrationStateCode"   
										name="registrationForm" styleId="registrationStateCode">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode"
												labelProperty="stateName" />
										</logic:present>
									</html:select></td>
							</tr>
							<tr>
								<td>Date of registration <span class="text-error">*</span>
								</td>
								<td><html:text property="dateOfRegistration" 
										name="registrationForm" styleId="dateOfRegistration" size="10"
										maxlength="10" title="Click here to select date"
										onclick="javascript:displayCalendar(document.forms[0].dateOfRegistration,'dd-mm-yyyy',this)" />

								</td>
							</tr>
							<tr>
								<td>PAN <span class="text-error">*</span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span></small>
								</td>
								<td><html:text property="panNo" name="registrationForm" 
										styleId="panNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /> 
								<c:if test="${registrationForm.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.panFileName }');" >View</a>
									<a href="javascript:fileStatus('panFile', 'show');">Change</a>
										<html:file property="panFile" name="registrationForm" style="display:none;"
										styleId="panFile" styleClass="infoBtn" onclick="javascript:removeError('panError','pError')"/>										
									<a id="panFileRemove" style="display:none;" href="javascript:fileStatus('panFile', 'hide');" >Remove</a>
									<small><font id="panError" class="text-error"></font></small>
									<logic:notEmpty name="panError">
										<small><font id="pError" class="text-error">${panError }</font></small>
									</logic:notEmpty>
								</c:if>
								</td>
							</tr>
							<tr>
								<td>TAN <span class="text-error">*</span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 200kb)</span></small>
								</td>
								
								<td><html:text property="tanNo" name="registrationForm" 
										styleId="tanNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /> 
									<c:if test="${registrationForm.piaCode != null}">
										<a href="javascript:showFiles('${piaDetail.tanFileName}');" >View</a>
										<a href="javascript:fileStatus('tanFile', 'show');">Change</a>
										<html:file property="tanFile" name="registrationForm" style="display:none;"
										styleId="tanFile" styleClass="infoBtn" onclick="javascript:removeError('tanError','tError')"/>										
									<a id="tanFileRemove" style="display:none;" href="javascript:fileStatus('tanFile', 'hide');" >Remove</a>
									<small><font id="tanError" class="text-error"></font></small>
									<logic:notEmpty name="tanError">
										<small><font id="tError" class="text-error">${tanError }</font></small>
									</logic:notEmpty>
									</c:if>
								</td>
							</tr>
						</table></td>
				</tr>
				<tr id="captcha">
					<td style="padding-left: 10px;">
						<img id="captchaImg" src="${pageContext.request.contextPath}/captcha" alt="Captcha Image" height="45">
						<img src="../images/refresh.png" alt="Reload" onclick="document.forms[0].captchaImg.src='${pageContext.request.contextPath}/captcha?id='+Math.random();" style="cursor: pointer" /> <br />Type above text<br />
						<html:errors property="error1" /> 
						<html:text property="captchaResponse" styleId="captchaResponse" maxlength="7" size="7" onchange="checkCaptcha()" />
						<font id="captchaError" style="color: red;"></font></td>
				</tr>
			</table>

			<div align="center" style="padding-top: 15px; padding-bottom: 15px;">
				<c:if test="${registrationForm.piaCode != null }">
				<html:button styleClass="primaryBtn" property="" onclick="updatePartA();" value="Save & Continue" />
					<%-- <html:button styleClass="primaryBtn" property="" onclick="continueWithPartB();" value="Continue" /> --%>
				</c:if>
				<html:button styleClass="defaultBtn" property="" onclick="quit()" value="Exit" />
			</div>
		</div>
	</html:form>
</body>


