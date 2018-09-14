<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<script type="text/javascript">

var tokenParameter = 'reqtrack';
var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';
 
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
		var message = "Incorrect captcha value";
		div.innerHTML= message;
	}else {
		flag = true;
	}
	return flag
}

function removeError(first, second){
	document.getElementById(first).innerHTML='';
	document.getElementById(second).innerHTML='';
}

function showName(id){
	var trName = document.getElementById("catName");
	if(id == '1'){
		trName.innerHTML = 'Name of the Educational Institution';
	}
	if(id == '2'){
		trName.innerHTML = 'Name of the Company';
	}
	if(id == '3'){
		trName.innerHTML = 'Name of the Partnership Firm/ LLP Firm';
	}
	if(id == '4'){
		trName.innerHTML = 'Name of the Partnership Firm/ LLP Firm';
	}
	if(id == '5'){
		trName.innerHTML = 'Name of the Institution';
	}
	if(id == '6'){
		trName.innerHTML = 'Name of the Trust/ Society/ Cooperative / Federation';
	}
	if(id == '7'){
		trName.innerHTML = 'Name of the Trust/ Society/ Cooperative / Federation';
	}
	if(id == '8'){
		trName.innerHTML = 'Name of the Trust/ Society/ Cooperative / Federation';
	}
	if(id == '9'){
		trName.innerHTML = 'Name of the SME';
	}
	if(id == '10'){
		trName.innerHTML = 'Name of the Institution';
	}
	if(id == '11'){
		trName.innerHTML = 'Name of the Venture/ Consortium';
	}
}

function savePartA(){
	
	var validationCheck = validate();
	
	var catvalue = document.querySelector('input[name = "categoryCode"]:checked').value;	
	
	if(catvalue==6 || catvalue==7 || catvalue==8){		
		
		var enrolmentNumber=document.getElementById("enrolmentNumber").value;
		
		var enrolmentFile =document.getElementById("enrolmentFile").value;
		
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
		    	document.forms[0].action = "registrationAction.do?methodName=savePartA&" + tokenParameter + "=" + tokenValue;
		    	document.forms[0].submit();
		    }
		}
	
	}else{
		
		if(validationCheck == true){
			var status=window.confirm('<bean:message key="msg.saveForm" />');
		    if(status==true){
		    	document.forms[0].action = "registrationAction.do?methodName=savePartA&" + tokenParameter + "=" + tokenValue;
		    	document.forms[0].submit();
		    }
		}
	}
	
		
}


function quit(){
	var status = window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
		window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
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
			fileErrorLocation[0] = document.getElementById("regError");
			fileErrorLocation[1] = document.getElementById("panError");
			fileErrorLocation[2] = document.getElementById("tanError");
			fileErrorLocation[3] = document.getElementById("officePhotoError");
			//fileErrorLocation[4] = document.getElementById("enrolError");
		var attachedFile = new Array();		
		attachedFile[0] = "regFile";
		attachedFile[1] = "panFile";
		attachedFile[2] = "tanFile";
		attachedFile[3]  = "officePhotoFile";
		//attachedFile[4]  = "enrolmentFile";
		for(i = 0; i < 4; i++){
			if(!checkFileFormat(attachedFile[i])){
				fileErrorLocation[i].innerHTML = "Incorrect file format ";
				count++;
			}/* else if(!checkFileSize(attachedFile[i])){
				fileErrorLocation[i].innerHTML = "File Size should be less than 200 Kb";
				flagFileCount = flagFileCount + 1;
				count++;
			} */
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
		}/* else if(!checkFileSize(id)){
			ErrorID.innerHTML = "File Size should be less than 200 Kb";
		} */
 	}
} 

/* Function used to validate the form feilds */
 
 function validate(){ 
	var validationCount = 0;
	document.getElementById("piaActivityError").innerHTML = "";
	document.getElementById("piaCategoryError").innerHTML = "";
	
	if(!isPiaActivityChecked()){
		validationCount++;
	}
	
	/* if(!document.getElementById("categoryCode").checked ){
		document.getElementById("piaCategoryError").innerHTML = "No Applicant Organisation  Category is selected.";
		validationCount++;
	} */
	 
	var event1 = new LiveValidation(document.forms[0].piaName,{onlyOnSubmit:true});
		event1.add( Validate.Presence );
		event1.add( Validate.Format, { pattern: /^[a-z\d\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."});
		
	var event2 = new LiveValidation(document.forms[0].registrationNumber,{onlyOnSubmit:true});	
		event2.add( Validate.Presence );
		
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
		
	var event9 = new LiveValidation(document.forms[0].registrationStateCode,{onlyOnSubmit:true});	
		event9.add( Validate.Presence );
	
	var event10 = new LiveValidation(document.forms[0].dateOfRegistration,{onlyOnSubmit:true});
		event10.add( Validate.Presence );
	
	var event11 = new LiveValidation(document.forms[0].regFile,{onlyOnSubmit:true});
	 	event11.add( Validate.Presence );	
		
	var event12 = new LiveValidation(document.forms[0].panFile,{onlyOnSubmit:true});
	  	event12.add( Validate.Presence );	
		
	var event13 = new LiveValidation(document.forms[0].tanFile,{onlyOnSubmit:true});
		event13.add( Validate.Presence );
	
	var event14 = new LiveValidation(document.forms[0].panNo,{onlyOnSubmit:true});
		event14.add( Validate.Presence );	
		event14.add( Validate.Length, { is: 10} );
		event14.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
	
  	var event15 = new LiveValidation(document.forms[0].tanNo,{onlyOnSubmit:true});
		event15.add( Validate.Presence );	
		event15.add( Validate.Format, { pattern: /^([a-zA-Z]{4})(\d{5})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect TAN Format" } );

  	var event16 = new LiveValidation(document.forms[0].website,{onlyOnSubmit:true});
	  	event16.add( Validate.Presence );	
	  	
	var event17 = new LiveValidation(document.forms[0].captchaResponse,{onlyOnSubmit:true});
	  	event17.add( Validate.Presence );	
	  	
	var event18 = new LiveValidation(document.forms[0].officePhotoFile,{onlyOnSubmit:true});
	  	event18.add( Validate.Presence );
	  
	var event19 = new LiveValidation(document.forms[0].pin,{onlyOnSubmit:true});
	  	event19.add( Validate.Presence );	  
	  	
     
										
	var areAllValid = LiveValidation.massValidate( [ event1, event2, event4, event5, event6, event7, event8, event9,event10,event11,event12,event13,event14,event15,event16,event17,event18, event19] );
	
	if(!areAllValid){
		validationCount++;
	}
	
	if(checkPanAndTan() == "true"){
		validationCount++;
	}  
	
	if(!checkFiles("all") || !checkCaptcha() ){
		validationCount++;
	}
	
	if(validationCount == 0)
		return true;	
	else
		return false;	
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
	    	document.getElementById("piaActivityError").innerHTML = "No Applicant Organisation  Activity is selected.";
	        return false;
	    }
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
	
	var pan = new LiveValidation('panNo', {onlyOnBlur: true } );
		pan.add( Validate.Presence );
		pan.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
	
	var tan = new LiveValidation('tanNo', {onlyOnBlur: true } );
		tan.add( Validate.Presence );
		tan.add( Validate.Format, { pattern: /^([a-zA-Z]{4})(\d{5})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect TAN Format" } );
	
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
 
 function getNGOFields(categ){
	//var categ= document.getElementById("categoryCode").value ;
	document.getElementById("ngoFields").style.display="none";
	if(categ!=null){
	if(categ=="6" || categ=="7" || categ=="8"  ){
		
		document.getElementById("ngoFields").style.display="";
		
	}else{
		document.getElementById("ngoFields").style.display="none";
	}
	}else{
		document.getElementById("ngoFields").style.display="none";
	}
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
	
function checkPanAndTan(){
	document.getElementById("myDiv").innerHTML="";
	 var PAN = document.getElementById("panNo").value;
	 var TAN = document.getElementById("tanNo").value;
	 
	 var xmlHTTP;
		if(window.XMLHttpRequest) {
			xmlHTTP = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
		}
	    var url = 'registrationAction.do?methodName=checkPiaDetail';
	    xmlHTTP.onreadystatechange = piaPanTan;
		
		xmlHTTP.open("POST",url, false);
		xmlHTTP.setRequestHeader(tokenParameter, tokenValue);
		xmlHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHTTP.send("PAN=" + PAN + "&TAN=" + TAN);
		
		function piaPanTan(){
			if(xmlHTTP.readyState==4 && xmlHTTP.status==200){
				var flag = xmlHTTP.responseText;
				var textval = "Applicant Organisation having <span class='text-error'> PAN: " + PAN 
								+ " </span> or <span class='text-error'>TAN: " + TAN + "</span> already registered.";
				if(flag == "true") {
					document.getElementById("myDiv").innerHTML= textval;
				}
				return flag;
			}
		};
		return xmlHTTP.responseText;
} 

	function checkimg(imgId) {	
		//alert("hhhhhhh");
		
			var ext = imgId.split(".");
		    ext = ext[ext.length-1].toLowerCase();      
		    var arrayExtensions = ["jpg" , "jpeg", "png", "pdf"];
		    if (arrayExtensions.lastIndexOf(ext) == -1) {
		        alert("File Type not supported.");
		        $("#enrolmentFile").val("");
		        }	    
		    var fileUpload = document.getElementById("enrolmentFile");	    
		    var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2); 
		   // alert("the file size is --"+size);
		    if (size > 300) {
		        alert("Image size should be less than 300 KB ");
		        $("#enrolmentFile").val("");
		    }	    
		}
//sumit

</script>
<!-- getNGOFields('') -->
<body onload="validateEntireForm();">
	<!-- Rahul -->
	<!-- <h3>registration.jsp</h3> -->
	<!-- To display the file name -->
	<%-- <h3><%= pageContext.getPage().getClass().getName().substring(pageContext.getPage().getClass().getName().lastIndexOf('.')+1).replace('_','.') %></h3> --%>

	<html:form action="registrationAction" method="post"
		enctype="multipart/form-data">
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
									<td style="padding-top: 10px;" colspan="2">
										<p>
											<label class="label-info" style="font: 18px;">Temporary	Reference Number 
												<span class="label-important">(Please note this number for future references ): </span> 
												<span class="label-warning"><bean:write	name="registrationForm" property="piaCode" /></span>
											</label>
										</p>
									</td>
								</tr>
							</c:if>
							<tr>
								<td style="padding-top: 10px;" colspan="2">
									<label class="label-important">All fields marked with (*) are mandatory to fill.</label>
								</td>
							</tr>
							<tr>
								<th colspan=2"><span class="text-primary">Category of Applicant Organisation<span class="text-error">*</span></span></th>
							</tr>
							<tr>
								<td colspan="2"><span id="piaCategoryError" class="text-error"></span><br /> 
									<logic:present name="piaCategory" >
										<logic:iterate id="category" name="piaCategory">
											<html:radio property="categoryCode" name="registrationForm" styleId="categoryCode" 
												value="${category.categoryId}" onclick="javascript:showName(${category.categoryId});">
												&nbsp;<bean:write name="category" property="categoryName" />
											</html:radio>
											<br />
										</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr>
								<th colspan="2">
									<span class="text-primary">Type of business/activity of Applicant Organisation </span>
									<span class="text-error">*</span>
									<small><span class="text-error">(Please tick mark options given below, multiple options permitted)</span></small>
								</th>
							</tr>
							<tr>
								<th colspan="2">
									<span><small><b><em>"Note:- Activity as per the statutory mandate in case of a
										Government or a Semi-Government Organization "</em></b></small></span>
								</th>
							</tr>
							<tr>
								<td colspan="2"><span id="piaActivityError" class="text-error"></span><br />
									<logic:present	name="piaActivity">
										<logic:present name="piaActivity">
											<logic:iterate id="activity" name="piaActivity" indexId="index">
												<html:checkbox property="piaActivity" name="registrationForm" value="${activity.activityId}">
													&nbsp;<bean:write name="activity" property="activityName" />
												</html:checkbox><br/>
											</logic:iterate>
										</logic:present>
									</logic:present>
								</td>
							</tr>
						</table>
						<table style="padding-top: 10px;" width="98%" class="inputTBL">
							<tr>
								<td id="catName" width="20%">Name of the Applicant Organisation<span class="text-error">*</span></td>
								<td><html:text property="piaName" styleId="piaName" name="registrationForm" size="100" maxlength="100" onblur="this.value=this.value.toUpperCase();" /></td>
							</tr>
						</table>

						<div id="ngoFields">
							<table style="padding-top: 10px;" width="98%" class="inputTBL">
								<tr><th colspan="3"><span class="text-primary">NITI Aayog Registration Details:</span></th></tr>
								<tr>
									<th colspan="2"><span><small><b><em>								
										"Note:- In case of NGOs (Society, Trust and Cooperative Society), please provide the below details. 
										As per NITI Aayog, GoI, All NGOs must be registered on NITI Aayog Portal (NGO Darpan Portal) and should 
										obtain unique identifiers before submitting their application for grants from any Ministry "</em>
										</b></small></span>
									</th>
								</tr>
								<tr>
									<td width="20%">NITI Aayog Alloted Unique Id: <span class="text-error"></span>
										<small><br/><span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span></small>
									</td>
									<td>
										<html:text property="enrolmentNumber" name="registrationForm" size="21" maxlength="21" styleId="enrolmentNumber" onblur="this.value=this.value.toUpperCase();" /> 
										<html:file	property="enrolmentFile" name="registrationForm"  styleId="enrolmentFile" styleClass="infoBtn" onchange="javascript:checkimg(this.value);" /> 
										<small><font  id="enrolError" class="text-error"></font></small>
										<logic:notEmpty  name="enrolError">
											<small><font id="enError" class="text-error">${enrolError}</font></small>
										</logic:notEmpty>
									</td>
								</tr>
							</table>
						</div>

						<table style="padding-top: 15px;" width="98%" class="inputTBL">
							<tr>
								<th colspan="3"><span class="text-primary">Address and Contact Detail</span></th>
							</tr>
							<tr>
								<td width="20%">Address <span class="text-error">*</span></td>
								<td colspan="2"><html:textarea property="address" name="registrationForm" styleId="address" cols="52" rows="3" onblur="this.value=this.value.toUpperCase();" /></td>
							</tr>
							<tr>
								<td width="20%">Pin <span class="text-error">*</span></td>
								<td><html:text property="pin" name="registrationForm" styleId="pin" size="6" maxlength="6" onkeypress="return isNumberKey(event)" /></td>
							</tr>
							<tr>
								<td width="20%">Office Photo <span class="text-error">*</span><small><br />
									<span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span> </small></td>
								<td><html:file property="officePhotoFile" name="registrationForm" styleId="officePhotoFile" styleClass="infoBtn" onclick="javascript:removeError('officePhotoError','oError')" />
									<small><font id="officePhotoError" class="text-error"></font></small>
									<logic:notEmpty name="officePhotoError">
										<small><font id="oError" class="text-error">${officePhotoError }</font></small>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td>State<span class="text-error">*</span></td>
								<td colspan="2">
									<html:select property="stateCode" name="registrationForm" styleId="stateCode" onchange="getDistrictList();">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode" labelProperty="stateName" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>District</td>
								<td colspan="2">
									<html:select property="districtCode" name="registrationForm" styleId="districtCode" onchange="getBlockList();">
										<html:option value="">Select</html:option>
										<logic:present name="districtList">
											<html:options collection="districtList"
												property="districtCode" labelProperty="districtName" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>Block</td>
								<td colspan="2"><html:select property="blockCode" name="registrationForm" styleId="blockCode">
									<html:option value="">Select</html:option>
										<logic:present name="blockList">
											<html:options collection="blockList" property="blockCode"
												labelProperty="blockName" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>Office Phone <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="officePhone" name="registrationForm" size="11" maxlength="11" onkeypress="return isNumberKey(event)" styleId="officePhone" />
									<span class="text-error"><small>(In case of mobile add 0 in the beginning)</small></span>
								</td>
							</tr>
							<tr>
								<td>Office Fax No <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="officeFax" name="registrationForm" onkeypress="return isNumberKey(event)" size="11" maxlength="11" styleId="officeFax" /></td>
							</tr>

							<tr>
								<td>Email<span class="text-error">*<small><br />(Yahoo Email Id is not preffered)</small></span></td>
								<td colspan="2">
									<html:text property="email" name="registrationForm" styleId="email" size="35" maxlength="70" onblur="this.value=this.value.toUpperCase();"></html:text>
								</td>
							</tr>

							<tr>
								<td>Website <span class="text-error">*</span></td>
								<td colspan="2">
									<html:text property="website" name="registrationForm" styleId="website" size="60" maxlength="150" onblur="this.value=this.value.toUpperCase();"></html:text>
								</td>
							</tr>
						</table>
						<table style="padding-top: 15px;" width="98%" class="inputTBL">
							<tr>
								<th colspan="2">
								<span class="text-primary">Registration Detail of the Applicant Organisation (as per the Registartion Certificate)</span> 
								<font id="allError" style="color: red;"></font>
								<div id="myDiv"></div>
								</th>
							</tr>
							<tr>
								<th colspan="2"><span><small><b><em>
													" Note:- In case of Statutory Bodies set by Central/State
													Government or Departments under Central/State Government, a
													letter from an authorized signatory of Statutory body,
													attaching a copy of the page of Gazette notification of the
													Act which clearly indicates the Act number, the date of
													notification and the name of the organization. The letter
													must clearly note address of the Head Office of the
													organization as also evidenced from the organization's
													website. Please upload these two documents in one
													attachment. "</em></b></small></span></th>
							</tr>
							<tr></tr>
							<tr>
								<td width="20%">Registration Number <span class="text-error">*</span> <small><br />
									<span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span> </small>
								</td>
								<td>
									<html:text property="registrationNumber" name="registrationForm" size="21" maxlength="21" styleId="registrationNumber" onblur="this.value=this.value.toUpperCase();" />
									<html:file property="regFile" name="registrationForm" styleId="regFile" styleClass="infoBtn" onclick="javascript:removeError('regError','rError')" /> 
									<small><font id="regError" class="text-error"></font></small> 
									<logic:notEmpty	name="regError">
										<small><font id="rError" class="text-error">${regError }</font></small>
									</logic:notEmpty></td>
							</tr>
							<tr>
								<td>State where registered <span class="text-error">*</span></td>
								<td>
									<html:select property="registrationStateCode"
										name="registrationForm" styleId="registrationStateCode">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode"
												labelProperty="stateName" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>Date of registration <span class="text-error">*</span></td>
								<td>
									<html:text property="dateOfRegistration" name="registrationForm" styleId="dateOfRegistration" size="10"
										maxlength="10" readonly="true" title="Click here to select date" 
										onclick="javascript:displayCalendar(document.forms[0].dateOfRegistration,'dd-mm-yyyy',this)" />
								</td>
							</tr>
							<tr>
								<td>PAN<span class="text-error">*</span><small><br />
									<span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span> </small>
								</td>
								<td>
									<html:text property="panNo" name="registrationForm" styleId="panNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" />
									<html:file property="panFile" name="registrationForm" styleId="panFile" styleClass="infoBtn" onclick="javascript:removeError('panError','pError')" /> 
									<small><font id="panError" class="text-error"></font></small> 
									<logic:notEmpty	name="panError">
										<small><font id="pError" class="text-error">${panError}</font></small>
									</logic:notEmpty></td>
							</tr>
							<tr>
								<td>TAN <span class="text-error">*</span>
									<small><br/><span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span></small>
								</td>
								<td><html:text property="tanNo" name="registrationForm" styleId="tanNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /> 
									<html:file property="tanFile" name="registrationForm" styleId="tanFile" styleClass="infoBtn" onclick="javascript:removeError('tanError','tError')" /> 
									<small><font id="tanError" class="text-error"></font></small> 
									<logic:notEmpty	name="tanError">
										<small><font id="tError" class="text-error">${tanError }</font></small>
									</logic:notEmpty>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr id="captcha">
					<td style="padding-left: 10px;">
						<img id="captchaImg" src="${pageContext.request.contextPath}/captcha" alt="Captcha Image" height="45"> 
						<img src="../images/refresh.png" alt="Reload" 
						onclick="document.forms[0].captchaImg.src='${pageContext.request.contextPath}/captcha?id='+Math.random();" 
						style="cursor: pointer" /> <br />Type above text<br /> 
						<html:errors property="error1" /> 
						<html:text property="captchaResponse" styleId="captchaResponse" maxlength="7" size="7" onchange="checkCaptcha()" /> 
						<font id="captchaError"	style="color: red;"></font>
					</td>
				</tr>
			</table>

			<div align="center" style="padding-top: 15px; padding-bottom: 15px;">
				<html:button styleClass="primaryBtn" property=""
					onclick="savePartA();" value="Save" />
				<html:button styleClass="defaultBtn" property="" onclick="quit();"
					value="Exit" />
			</div>
		</div>
	</html:form>
</body>

