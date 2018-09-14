<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<script type="text/javascript">

var tokenParameter = 'reqtrack';
var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';

function updateAndSubmit(){		
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
		    	document.forms[0].action = "registrationAction.do?methodName=updatePartB&" + tokenParameter + "=" + tokenValue;
		    	document.forms[0].submit();
		    }
		}
	
	}else{	
		
		if(validationCheck){
			var status=window.confirm("<bean:message key="alert.submit.confirm" />");
			if(status==true){
				document.forms[0].action = "registrationAction.do?methodName=updatePartB&" + tokenParameter + "=" + tokenValue;
				document.forms[0].submit();
			}
		}
	}
}

function removeError(first, second){
	document.getElementById(first).innerHTML='';
	document.getElementById(second).innerHTML='';
}

 /* used to quit from the registration page */
 function quit(){
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if(status==true){
			window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
		}
	}
 
 function checkCaptcha(){
		var flag = false;
		var div = document.getElementById("captchaError");
		div.innerHTML= "";
		var captcha = document.getElementById("captchaResponse").value;
		var xmlhttp;
		var captchaText = document.getElementById("captchaResponse").value;
		
		//var url =  location.protocol + '//' + location.host+"/prnlive/captchaCheck?captcha=" + captchaText;
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
	if(ext == "pdf" || ext == "PDF" || ext == "JPEG" || ext == "jpeg" || ext == "jpg" || ext == "JPG" || ext == "PNG" || ext == "png"){
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

function checkcvformat(imgId) {		
	var ext = imgId.split(".");
    ext = ext[ext.length-1].toLowerCase();      
    var arrayExtensions = ["pdf" , "jpg", "jpeg"];
    if (arrayExtensions.lastIndexOf(ext) == -1) {
        alert("Wrong extension type.");
        $("#cvFile").val("");
        }	    
    var fileUpload = document.getElementById("cvFile");	    
    var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2); 
   // alert("the file size is --"+size);
    if (size > 300) {
        alert("Image size should be less than 300 KB ");
        $("#cvFile").val("");
    }
}

function checkimgreg(imgId) {		
	var ext = imgId.split(".");
    ext = ext[ext.length-1].toLowerCase();      
    var arrayExtensions = ["jpg" , "jpeg", "png","pdf"];
    if (arrayExtensions.lastIndexOf(ext) == -1) {
        alert("Wrong extension type.");
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

function checkimg(imgId) {		
	var ext = imgId.split(".");
    ext = ext[ext.length-1].toLowerCase();      
    var arrayExtensions = ["jpg" , "jpeg", "png"];
    if (arrayExtensions.lastIndexOf(ext) == -1) {
        alert("Wrong extension type.");
        $("#photoFile").val("");
        }	    
    var fileUpload = document.getElementById("photoFile");	    
    var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2); 
   // alert("the file size is --"+size);
    if (size > 300) {
        alert("Image size should be less than 300 KB ");
        $("#photoFile").val("");
    }	    
/*    
	  // document.getElementById("uploadimg").style.display="none";
		var ext = imgId.split(".");
	    ext = ext[ext.length-1].toLowerCase();      
	    var arrayExtensions = ["jpg" , "jpeg", "png", "bmp"];
	    if (arrayExtensions.lastIndexOf(ext) == -1) {
	        alert("Wrong extension type.");
	        $("#photoFile").val("");
	        }	    
	    var fileUpload = document.getElementById("photoFile");	   
	    var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2); 
	   // alert("the file size is --"+size);
	    if (size > 300) {
	        alert("Image size should be less than 300 KB ");
	        $("#photoFile").val("");
	    }*/	    
	}

function checkFiles(id){	
	 if(id == "all"){
		 var count = 0;
		 var fileErrorLocation = new Array();
		 var attachedFile = new Array();
		 var index = 0;	 
		 
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
	email.add( Validate.Email );

  	var website = new LiveValidation(document.forms[0].website,{onlyOnSubmit:true});
  	website.add( Validate.Presence );		
	 		
	var areAllValid = LiveValidation.massValidate( [piaName, address, pin, stateCode, officePhone, officeFax, email, website ] );

	if(!validatePart2()|| !checkFiles('all') || !checkCaptcha()){
		areAllValid = false;
	}
	return areAllValid;	
}

 function validatePart2(){
		var errorCount = 0;
		var isValidate= false;
		var TALastRow = document.getElementById("memberTbl").rows.length;	
		var categoryCheck = document.getElementById("categoryCheck").value;
		
		// Validation for Members Start
		 for( var i = 0; i <= TALastRow-3; i++){	
			var memberName = new LiveValidation('memberName'+i, {onlyOnSubmit:true});
			memberName.add( Validate.Presence);
			memberName.add( Validate.Format, { pattern: /^[a-z\s]+$/i, failureMessage: "No Special Characters Allowed."});
			
			var memberDesgLibl;
			if(categoryCheck == 3 || categoryCheck == 4 ){
				memberDesgLibl = new LiveValidation('memberLiability'+i, {onlyOnSubmit:true});
				memberDesgLibl.add( Validate.Presence);
			}else{
				memberDesgLibl = new LiveValidation('memberDesignation'+i, {onlyOnSubmit:true});
				memberDesgLibl.add( Validate.Presence);
			}
			
			var memberContact= new LiveValidation('memberContact'+i, {onlyOnSubmit:true});
			memberContact.add( Validate.Presence);
			memberContact.add( Validate.Numericality, {onlyInteger: true});
			memberContact.add( Validate.Length, {is: 11} );
					
			var memberEmail = new LiveValidation('memberEmail'+i, {onlyOnSubmit:true});
			memberEmail.add( Validate.Presence);
			memberEmail.add( Validate.Email, {failureMessage: "Invalid email"} );		
			
			var memberPan = new LiveValidation('memberPan'+i, {onlyOnSubmit: true } );
			memberPan.add( Validate.Presence );
			memberPan.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
			
			var memberAadharVoterNo = new LiveValidation('memberAadharVoterNo' + i, {onlyOnSubmit:true});
			memberAadharVoterNo.add( Validate.Presence);
				
			var memberPassportDrivingNo = new LiveValidation('memberPassportDrivingNo' + i, {onlyOnSubmit:true});
			memberPassportDrivingNo.add( Validate.Presence);	
			
			var areAllValid = LiveValidation.massValidate( [memberName, memberDesgLibl, memberContact, memberEmail, memberPan, memberAadharVoterNo, memberPassportDrivingNo]);
			if(!areAllValid){
				errorCount++;
			}		
		} 
		// Validation for Members Start
		
		// Validation for NRI Members Start
		if(categoryCheck == 3 || categoryCheck == 4 ){
		var nriLastRow = document.getElementById("nriTbl").rows.length;
		for( var i = 0; i <= nriLastRow-3; i++){
			var nriCountry = new LiveValidation('nriCountry'+i, {onlyOnSubmit:true});
			nriCountry.add( Validate.Presence);
			
			var nriName = new LiveValidation('nriName'+i, {onlyOnSubmit:true});
			nriName.add( Validate.Presence);
			nriName.add( Validate.Format, { pattern: /^[a-z\s]+$/i, failureMessage: "No Special Characters Allowed."});
			
			var nriPassportNo = new LiveValidation('nriPassportNo'+i, {onlyOnSubmit:true});
			nriPassportNo.add( Validate.Presence);	
						
			var nriPassportValidDate = new LiveValidation('nriPassportValidDate'+i, {onlyOnSubmit:true});
			nriPassportValidDate.add( Validate.Presence);	
				
			var nriVisaValidDate = new LiveValidation('nriVisaValidDate'+i, {onlyOnSubmit:true});
			nriVisaValidDate.add( Validate.Presence);	
				
			var nriWorkPermit = new LiveValidation('nriWorkPermit'+i, {onlyOnSubmit:true});
			nriWorkPermit.add( Validate.Presence);	
				
			//var nriWorkingPermitDate = new LiveValidation('nriWorkingPermitDate'+i, {onlyOnSubmit:true});
			//nriWorkingPermitDate.add( Validate.Presence); 	
				
			var nriclearanceFRAMHA = new LiveValidation('nriclearanceFRAMHA'+i, {onlyOnSubmit:true});
			nriclearanceFRAMHA.add( Validate.Presence);		
				
				
			var areAllValid = LiveValidation.massValidate( [nriCountry, nriName, nriPassportNo, nriPassportValidDate, nriVisaValidDate, nriWorkPermit, nriclearanceFRAMHA]);
			if(!areAllValid){
				errorCount++;
			}		
		} 
		} 
		// Validation for NRI Members End
		
		// Validation for Authorized person Start
		var authPersonName = new LiveValidation('authPersonName', {onlyOnSubmit:true});
			authPersonName.add( Validate.Presence);	
		
		var authPersonContact = new LiveValidation('authPersonContact', {onlyOnSubmit: true } );
			authPersonContact.add( Validate.Presence );
			authPersonContact.add( Validate.Numericality, {onlyInteger: true});
			authPersonContact.add( Validate.Length, {is: 11} );
				
		var authPersonPan = new LiveValidation('authPersonPan', {onlyOnSubmit:true});
			authPersonPan.add( Validate.Presence);
			authPersonPan.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
		
		var authPersonEmail = new LiveValidation('authPersonEmail', {onlyOnSubmit:true});
			authPersonEmail.add( Validate.Presence);
			authPersonEmail.add( Validate.Email, {failureMessage: "Invalid email"} );
		
		var authPersonAdhaar = new LiveValidation('authPersonAdhaar', {onlyOnSubmit:true});
			authPersonAdhaar.add( Validate.Presence);
		
		var authPersonPassport = new LiveValidation('authPersonPassport', {onlyOnSubmit:true});
			authPersonPassport.add( Validate.Presence);
		
		var authRelativeName = new LiveValidation('authRelativeName', {onlyOnSubmit:true});
			authRelativeName.add( Validate.Presence);
		
		var authAge = new LiveValidation('authAge', {onlyOnSubmit:true});
			authAge.add( Validate.Presence);
			authAge.add( Validate.Numericality, {onlyInteger: true});
		
		var authOccupation = new LiveValidation('authOccupation', {onlyOnSubmit:true});
			authOccupation.add( Validate.Presence);
		
		var authDesignation = new LiveValidation('authDesignation', {onlyOnSubmit:true});
			authDesignation.add( Validate.Presence);
		
		var authAddress = new LiveValidation('authAddress', {onlyOnSubmit:true});
			authAddress.add( Validate.Presence);
		
		var authPostOffice = new LiveValidation('authPostOffice', {onlyOnSubmit:true});
			authPostOffice.add( Validate.Presence);
		
		var authPoliceStation = new LiveValidation('authPoliceStation', {onlyOnSubmit:true});
			authPoliceStation.add( Validate.Presence);
		
		var authStateCode = new LiveValidation('authStateCode', {onlyOnSubmit:true});
			authStateCode.add( Validate.Presence);
		areAllValid = LiveValidation.massValidate( [authPersonName, authPersonContact, authPersonPan, authPersonEmail, authPersonAdhaar, authPersonPassport, 
		                                            authRelativeName, authAge, authOccupation, authDesignation, authAddress, authPostOffice, 
		                                            authPoliceStation, authStateCode]);
		if(!areAllValid){
			errorCount++;
		}	
		// Validation for Authorized person End
		
		// Validation for Education Institutions Start
		if(categoryCheck == 1){
			var addressLandBuilding = new LiveValidation('addressLandBuilding', {onlyOnSubmit:true});
			addressLandBuilding.add( Validate.Presence);
		    
		    var freeholdMortgaged = new LiveValidation('freeholdMortgaged', {onlyOnSubmit:true});
		    freeholdMortgaged.add( Validate.Presence);	
		    
		    areAllValid = LiveValidation.massValidate( [addressLandBuilding, freeholdMortgaged]);
			if(!areAllValid){
				errorCount++;
		     }
		}
	 	// Validation for Education Institutions End
    
		if(document.getElementById("isNonProfitOrg").value=='Yes'){
			var regNoSection12A = new LiveValidation('regNoSection12A', {onlyOnSubmit:true});
			regNoSection12A.add( Validate.Presence);
		    
		    var regNoFCRA = new LiveValidation('regNoFCRA', {onlyOnSubmit:true});
		    regNoFCRA.add( Validate.Presence);	
		    
		    var regNoSection80G = new LiveValidation('regNoSection80G', {onlyOnSubmit:true});
		    regNoSection80G.add( Validate.Presence);
		    
		    var regDateSection12A = new LiveValidation('regDateSection12A', {onlyOnSubmit:true});
		    regDateSection12A.add( Validate.Presence);
		    
		    var regDateFCRA = new LiveValidation('regDateFCRA', {onlyOnSubmit:true});
		    regDateFCRA.add( Validate.Presence);	
		    
		    var regDateSection80G = new LiveValidation('regDateSection80G', {onlyOnSubmit:true});
		    regDateSection80G.add( Validate.Presence);
		    
		    areAllValid = LiveValidation.massValidate( [regNoSection12A, regNoFCRA,regNoSection80G,regDateSection12A,regDateFCRA,regDateSection80G]);
			if(!areAllValid){
				errorCount++;
		     }
		}
	 	
	 	if (errorCount == 0){
	 		isValidate = true;
	     }
		return isValidate;
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
 
 /* function used to create new row */
 function addRow(tableId){
 	var categoryCheck = document.getElementById("categoryCheck").value;
 	var tableValue=document.getElementById(tableId); 
 	var lastRow = tableValue.rows.length;
 	var rowCount = lastRow-2;
 	var index = lastRow-1;
 	var maxRow=22;
 		if(lastRow < maxRow){
 			var flag = true;
 			if(lastRow-3 >= 0){
 			if(document.getElementById("memberName".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(categoryCheck == 3 || categoryCheck == 4 ){
 				if(document.getElementById("memberLiability".concat(lastRow-3)).value==''){
 					flag=false;
 				}
 			}else{
 				if(document.getElementById("memberDesignation".concat(lastRow-3)).value==''){
 					flag=false;
 				}
 			}
 			if(document.getElementById("memberContact".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("memberEmail".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("memberPan".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("memberAadharVoterNo".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("memberPassportDrivingNo".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			}
 			if(flag){
 				createRow('TA', lastRow, rowCount, tableValue, index);
 			}
 			else{
 				alert("Fill the all detail first.");
 			}
 		}
 		else{
 		alert("Maximum 20 member allowed.");
 	}
 }

 function createRow(tblName, lastRow, cou, tableValue, index){
 	var row = tableValue.insertRow(lastRow);
 	var categoryCheck = document.getElementById("categoryCheck").value;
 	row.setAttribute('id',tblName+'rowId'+cou);	
 	
 	cellLeft = row.insertCell(0);
 	cellLeft.innerHTML="<input type='text' name='memberName' id='memberName"+cou+"' size='14' maxlength='50' onblur='this.value=this.value.toUpperCase();'/><input type='hidden' name='memberCode' id='memberCode"+cou+"' value='0'/>";
 	cellLeft.align="center";

 	if(categoryCheck == 3 || categoryCheck == 4 ){
 		cellLeft = row.insertCell(1); 
 		cellLeft.innerHTML='<input type="text" name="memberLiability"  value="" id="memberLiability'+ cou +'" size="3" maxlength="3" >';
 	}else{
 		cellLeft = row.insertCell(1); 
 		cellLeft.innerHTML='<select name="memberDesignation" id="memberDesignation'+ cou +'">' +
 		'<option value="">Select</option><option value="Director">Director</option></select>';
 	}

 	cellLeft = row.insertCell(2);  	
 	cellLeft.innerHTML='<input type="text" name="memberContact" value="" id="memberContact'+ cou +'" size="11" maxlength="11" onkeypress="return isNumberKey(event)" >';
 	
 	cellLeft = row.insertCell(3); 
 	cellLeft.innerHTML='<input type="text" name="memberEmail"  value="" id="memberEmail'+ cou +'"  size="14" maxlength="50" onblur="this.value=this.value.toUpperCase();">';
 	
 	cellLeft = row.insertCell(4); 
 	cellLeft.innerHTML='<input type="text" name="memberPan"  value="" id="memberPan'+ cou +'" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();">';		
 	
 	cellLeft = row.insertCell(5);  
 	cellLeft.innerHTML='<input type="text" name="memberAadharVoterNo"  value="" id="memberAadharVoterNo'+ cou +'" size="12" maxlength="12" onblur="this.value=this.value.toUpperCase();">';
 	
 	cellLeft = row.insertCell(6);  
 	cellLeft.innerHTML='<input type="text" name="memberPassportDrivingNo"  value="" id="memberPassportDrivingNo'+ cou +'" size="16" maxlength="16" onblur="this.value=this.value.toUpperCase();">';	
 }

 function removeRow(tableId){
 	try {            
 		var table = document.getElementById(tableId);
 			maxRow = 3;
 		var rowCount = table.rows.length;
 			if(rowCount > maxRow)	
 				table.deleteRow(rowCount-1);
 			else
 				alert("No fields allow to delete.");
 		}catch(e) {
 			alert(e+"---"+rowCount);	
 		}
 }

 /* function used to add row in nri member table */
 function addRowNRI(tableId){
 	var tableValue=document.getElementById(tableId); 
 	var lastRow = tableValue.rows.length;
 	var rowCount = lastRow-2;
 	var index = lastRow-1;
 	var maxRow=22;
 		if(lastRow < maxRow){
 			var flag = true;
 			if(lastRow-3 >= 0){
 			if(document.getElementById("nriName".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriCountry".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriPassportNo".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriPassportValidDate".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriVisaValidDate".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriWorkPermit".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriWorkingPermitDate".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			if(document.getElementById("nriclearanceFRAMHA".concat(lastRow-3)).value==''){
 				flag=false;
 			}
 			}
 			if(flag){
 				createRowNRI('SA', lastRow, rowCount, tableValue, index);
 			}
 			else{
 				alert("Fill the all detail first.");
 			}
 		}
 		else{
 		alert("Maximum 20 member allowed.");
 	}
 }

 function createRowNRI(tblName, lastRow, cou, tableValue, index){
 	var row = tableValue.insertRow(lastRow);
 	row.setAttribute('id',tblName+'rowId'+cou);
 	
 	cellLeft = row.insertCell(0);
 	cellLeft.innerHTML="<input type='text' name='nriName' id='nriName"+cou+"' size='10' onblur='this.value=this.value.toUpperCase();'/><input type='hidden' name='nriCode' id='nriCode"+cou+"' value='0'/>";
 	cellLeft.align="center";

 	cellLeft = row.insertCell(1); 
 	cellLeft.innerHTML='<input type="text" name="nriCountry"  value="" id="nriCountry'+ cou +'" size="10" onblur="this.value=this.value.toUpperCase();" >';

 	cellLeft = row.insertCell(2);  	
 	cellLeft.innerHTML='<input type="text" name="nriPassportNo" maxlength="10" value="" id="nriPassportNo'+ cou +'" size="10" onblur="this.value=this.value.toUpperCase();" >';
 	
 	cellLeft = row.insertCell(3); 
 	cellLeft.innerHTML='<input type="text" name="nriPassportValidDate" size="10" value="" '+
 					   'onclick="javascript:displayCalendar(document.forms[0].nriPassportValidDate'+ cou +',\'dd-mm-yyyy\',this)"'+
 					   ' readonly="readonly" id="nriPassportValidDate'+ cou +'" title="Click here to select date">';
 	
 	cellLeft = row.insertCell(4); 
 	cellLeft.innerHTML='<input type="text" name="nriVisaValidDate" size="10" value="" '+
 					   'onclick="javascript:displayCalendar(document.forms[0].nriVisaValidDate'+ cou +',\'dd-mm-yyyy\',this)"'+
 					   ' readonly="readonly" id="nriVisaValidDate'+ cou +'" title="Click here to select date">';		
 	
 	cellLeft = row.insertCell(5);  
 	cellLeft.innerHTML='<select name="nriWorkPermit" id="nriWorkPermit'+ cou +'"><option value="">Select</option>' +
 	   					'<option value="Yes">Yes</option><option value="No">No</option></select>';
 	
 	cellLeft = row.insertCell(6);  
 	cellLeft.innerHTML='<input type="text" name="nriWorkingPermitDate" size="10" value="" '+
 					   'onclick="javascript:displayCalendar(document.forms[0].nriWorkingPermitDate'+ cou +',\'dd-mm-yyyy\',this)"'+
 					   ' readonly="readonly" id="nriWorkingPermitDate'+ cou +'" title="Click here to select date">';
 	
 	cellLeft = row.insertCell(7);  
 	cellLeft.innerHTML = '<select name="nriclearanceFRAMHA" id="nriclearanceFRAMHA'+ cou +'"><option value="">Select</option>' +
 					     '<option value="Yes">Yes</option><option value="No">No</option></select>';
 }

 function deleteRowNRI(tableId){
 	try {            
 		var table = document.getElementById(tableId);
 			maxRow=3;
 		var rowCount = table.rows.length;
 			if(rowCount > maxRow)	
 				table.deleteRow(rowCount-1);
 			else
 				alert("No fields allow to delete.");
 		}catch(e) {
 			alert(e+"---"+rowCount);
 			}
 }
 
 function createPdf(){
		document.forms[0].action = "registrationAction.do?methodName=createPdf&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
 
 function change(){
		if(document.getElementById("isNonProfitOrg").value=='Yes'){
			document.getElementById("additionalDetail").style.display='';
		}
		if(document.getElementById("isNonProfitOrg").value=='No' || document.getElementById("isNonProfitOrg").value=='Select' ){
			document.getElementById("additionalDetail").style.display='none';
		}
	}
 function loadWhetherApplicant()
 {
 if(document.getElementById("isNonProfitOrg").value=='Yes'){
		document.getElementById("additionalDetail").style.display='';
	}
	if(document.getElementById("isNonProfitOrg").value=='No' || document.getElementById("isNonProfitOrg").value=='Select' ){
		document.getElementById("additionalDetail").style.display='none';
	}
 }
</script>

<body  onload="loadWhetherApplicant()">
	<!-- Rahul -->
	<!-- <h3>registration.jsp</h3> -->
	<!-- To display the file name -->
	<%-- <h3><%= pageContext.getPage().getClass().getName().substring(pageContext.getPage().getClass().getName().lastIndexOf('.')+1).replace('_','.') %></h3> --%>

	<html:form action="registrationAction" method="post" enctype="multipart/form-data">
	<div class="outerTBL">
	<!-- Used to identify the category -->
	<html:hidden property="" value="${piaDetail.categoryCode}" styleId="categoryCheck" />
		<table width="100%">
		<tr>
			<td class="pageHeader" align="center">Applicant Organisation <small>Registration Form</small></td>
		</tr>
		<tr>
		<td align="center">
						<table width="98%" class="inputTBL">
							<c:if test="${piaDetail.piaCode != null }">
							<tr align="center">															
								<td style="padding-top: 10px;" colspan="2">
									<p><label class="label-info" style="font: 18px;">Temporary Reference Number <span class="label-important">( Please note this number for future references ): </span> 
									<span class="label-warning"><bean:write name="piaDetail" property="piaCode"/></span></label></p>
									<html:hidden name="piaDetail" property="piaCode"/>
 								</td>										
							</tr>
							</c:if>
							<tr>
								<td style="padding-top: 10px;" colspan="2"><label class="label-important">All fields
										marked with (*) are mandatory to fill.</label></td>									
							</tr>
							
							<tr>
								<th colspan=2"><span class="text-primary">Category of Applicant <span class="text-error">*</span>
								</span>
								</th>
							</tr>
							<tr>							
								<td colspan="2"><logic:present name="piaCategory">
										<logic:iterate id="category" name="piaCategory">
											<c:if test="${piaDetail.categoryCode == category.categoryId }">
											<html:radio property="categoryCode" name="piaDetail"
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
										business/activity of Applicant Organisation</span> <span class="text-error">*</span> <small><span
										class="text-error">(Please tick mark options given
											below, multiple options permitted)</span>
								</small></th>
							</tr>
							<tr>
								<td colspan="2">
								<logic:present name="piaActivity">
							    <c:set var="selectActivity" value="${fn:split(piaDetail.activityCode,', ')}" /> 
								<logic:iterate id="activity" name="piaActivity" indexId="index">
								<c:set var="actFlag" value="0" />									
											<logic:iterate id="slact" name="selectActivity" >
												<c:if test="${activity.activityId == slact }" >
													<input type="checkbox" name="piaActivity" value="${activity.activityId}" checked id="piaActivity">
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
								<tr><th colspan="3"><span class="text-primary">NITI Aayog Registration Details:</span></th></tr>
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
								<td width="20%">NITI Aayog Alloted Unique Id:<span
									class="text-error"></span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 300kb)</span></small>
								</td>
								<td>
								<html:text property="enrolmentNumber" onblur="this.value=this.value.toUpperCase();"
										name="piaDetail" size="21" maxlength="21" 
										styleId="enrolmentNumber" /> 
								<c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.enrolmentFileName }');" >View</a>
									<a href="javascript:fileStatus('enrolmentFile', 'show');">Change</a>
										<html:file property="enrolmentFile" style="display:none;" onchange="javascript:checkimgreg(this.value);"
										styleId="enrolmentFile" styleClass="infoBtn"/>	
									<a id="enrolmentFileRemove" style="display:none;" href="javascript:fileStatus('enrolmentFile', 'hide');" >Remove</a>
									<small><font id="enrolError" class="text-error"></font></small>
									<logic:notEmpty name="enrolError">
										<small><font class="text-error">${enrolError}</font></small>
									</logic:notEmpty>
								</c:if>	
								</td>							
							</tr>												
						</table>
					
						<table style="padding-top: 10px;" width="98%" class="inputTBL">
							<tr>
								<td id="catName" width="20%">Name of the Applicant Organisation <span class="text-error">*</span>
								</td>
								<td><html:text property="piaName" styleId="piaName" onblur="this.value=this.value.toUpperCase();"
										name="piaDetail" size="100" maxlength="1000" />
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
								<td colspan="2"><html:textarea property="address" onblur="this.value=this.value.toUpperCase();"
										name="piaDetail" styleId="address" cols="52" rows="3" />
								</td>
							</tr>
							<tr>
								<td width="20%">Pin <span class="text-error">*</span></td>
								<td><html:text property="pin" name="piaDetail" onkeypress="return isNumberKey(event)"
										styleId="pin" size="6" maxlength="6" /></td>
							</tr>
							<tr>
								<td width="20%">Office Photo <span class="text-error">*</span><br/>
								<small><span class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 200kb)</span></small>
								</td>
								<td>		
									<c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.officePhotoFileName}');" >View</a>
									<a href="javascript:fileStatus('officePhotoFile', 'show');">Change</a>
										<html:file property="officePhotoFile"  style="display:none;" onchange="javascript:checkFiles('officePhotoFile');"
										styleId="officePhotoFile" styleClass="infoBtn"/>	
									<a id="officePhotoFileRemove" style="display:none;" href="javascript:fileStatus('officePhotoFile', 'hide');" >Remove</a>
									<small><font id="officePhotoError" class="text-error"></font></small>
									<logic:notEmpty name="officePhotoError">
										<small><font class="text-error">${officePhotoError }</font></small>
									</logic:notEmpty>
								</c:if>
								
								</td>
							</tr>
							<tr>
								<td>State <span class="text-error">*</span>
								</td>
								<td colspan="2"><html:select property="stateCode"
										name="piaDetail" styleId="stateCode"
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
										name="piaDetail" styleId="districtCode"
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
										name="piaDetail" styleId="blockCode">
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
										name="piaDetail" size="11" maxlength="11" onkeypress="return isNumberKey(event)"
										styleId="officePhone" />&emsp;
										<span class="text-error"><small>(In case of mobile add 0 in the beginning)</small></span>
								</td>
							</tr>
							<tr>
								<td>Office Fax No <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="officeFax" name="piaDetail" onkeypress="return isNumberKey(event)"
										size="11" maxlength="11" styleId="officeFax" />
								</td>
							</tr>
							<tr>
								<td>Email<span class="text-error">*<small><br/>(Yahoo Email Id is not preffered)</small></span></td>
								<td colspan="2"><html:text property="email" name="piaDetail" onblur="this.value=this.value.toUpperCase();"
										styleId="email" size="35" maxlength="150"></html:text>
								</td>
							</tr>
							<tr>
								<td>Website <span class="text-error">*</span></td>
								<td colspan="2"><html:text property="website" name="piaDetail" onblur="this.value=this.value.toUpperCase();"
										styleId="website" size="60" maxlength="150"></html:text>
								</td>										
							</tr>
						</table>
						<table style="padding-top: 15px;" width="98%" class="inputTBL">
							<tr>
								<th colspan="2"><span class="text-primary">
										Registration Detail of the Applicant Organisation (as per the Registartion Certificate)</span>
								</th>
							</tr>
							<tr>
								<td width="20%">Registration Number <span
									class="text-error">*</span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 200kb)</span></small>
								</td>
								<td>
								<html:text property="registrationNumber" onblur="this.value=this.value.toUpperCase();"
										name="piaDetail" size="21" maxlength="21" 
										styleId="registrationNumber" /> 
								<c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.regFileName }');" >View</a>
									<a href="javascript:fileStatus('regFile', 'show');">Change</a>
										<html:file property="regFile" style="display:none;" onchange="javascript:checkFiles('regFile');"
										styleId="regFile" styleClass="infoBtn"/>	
									<a id="regFileRemove" style="display:none;" href="javascript:fileStatus('regFile', 'hide');" >Remove</a>
									<small><font id="regError" class="text-error"></font></small>
									<logic:notEmpty name="regError">
										<small><font class="text-error">${regError }</font></small>
									</logic:notEmpty>
								</c:if>
								</td>
							</tr>
							<tr>
								<td>State where registered <span class="text-error">*</span>
								</td>
								<td><html:select property="registrationStateCode"  
										name="piaDetail" styleId="registrationStateCode">
										<html:option value="">Select</html:option>
										<logic:present name="stateList">
											<html:options collection="stateList" property="stateCode"
												labelProperty="stateName" />
										</logic:present>
									</html:select>
									<html:hidden property="registrationStateCode" name="piaDetail"  />
									<html:hidden property="panFileName" name="piaDetail"  />
									<html:hidden property="tanFileName" name="piaDetail"  />
									<html:hidden property="regFileName" name="piaDetail"  />
									<html:hidden property="officePhotoFileName" name="piaDetail"  />		
									<html:hidden property="createdOn" name="piaDetail"  />
									<html:hidden property="createdBy" name="piaDetail"  />									
									</td>
							</tr>
							<tr>
								<td>Date of registration <span class="text-error">*</span>
								</td>
								<td><html:text property="dateOfRegistration" readonly="true"
										name="piaDetail" styleId="dateOfRegistration" size="10"
										maxlength="10" title="Click here to select date"
										onclick="javascript:displayCalendar(document.forms[0].dateOfRegistration,'dd-mm-yyyy',this)" />
								</td>
							</tr>
							<tr>
								<td>PAN <span class="text-error">*</span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 200kb)</span></small>
								</td>
								<td><html:text property="panNo" name="piaDetail"  onblur="this.value=this.value.toUpperCase();"
										styleId="panNo" size="10" maxlength="10" /> 
								<c:if test="${piaDetail.piaCode != null}">
									<a href="javascript:showFiles('${piaDetail.panFileName }');" >View</a>
									<a href="javascript:fileStatus('panFile', 'show');">Change</a>
										<html:file property="panFile" style="display:none;"
										styleId="panFile" styleClass="infoBtn" onchange="javascript:checkFiles('panFile');"/>										
									<a id="panFileRemove" style="display:none;" href="javascript:fileStatus('panFile', 'hide');" >Remove</a>
									<small><font id="panError" class="text-error">${panError }</font></small>
									<logic:notEmpty name="panError">
										<small><font class="text-error">${panError }</font></small>
									</logic:notEmpty>
								</c:if>
								</td>
							</tr>
							<tr>
								<td>TAN <span class="text-error">*</span><br/><small><span
										class="text-error">(upload supporting document in jpg or pdf format, subjected to maximum size of 200kb)</span></small>
								</td>
								
								<td><html:text property="tanNo" name="piaDetail" 
										styleId="tanNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /> 
									<c:if test="${piaDetail.piaCode != null}">
										<a href="javascript:showFiles('${piaDetail.tanFileName}');" >View</a>
										<a href="javascript:fileStatus('tanFile', 'show');">Change</a>
										<html:file property="tanFile"  style="display:none;"
										styleId="tanFile" styleClass="infoBtn" onchange="javascript:checkFiles('tanFile');"/>										
									<a id="tanFileRemove" style="display:none;" href="javascript:fileStatus('tanFile', 'hide');" >Remove</a>
									<small><font id="tanError" class="text-error">${tanError }</font></small>
									<logic:notEmpty name="tanError">
										<small><font class="text-error">${tanError }</font></small>
									</logic:notEmpty>
									</c:if>
								</td>
							</tr>
						</table>
						
<!-- PART 2 Entry -->					
<table style="padding-top: 15px;" width="98%"  align="center" class="inputTBL" id="memberTbl" >	
	<tr><td colspan="9"><label class="label-inverse">Details of the Owners/ Directors </label></td></tr>	
	<tr>
		<th>Name<span class="text-error">*</span></th>	
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
			<th>Designation<span class="text-error">*</span></th>
		</c:if>
		
		<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
			<th>Liability<span class="text-error">*</span></th>
			<html:hidden property="categoryCode" name="piaDetail" styleId=""></html:hidden>
		</c:if>	
		<th>Contact<span class="text-error">*</span></th>
		<th>Email<span class="text-error">*</span></th>
		<th>PAN No.<span class="text-error">*</span></th>		
		<th>Aadhaar No./<br/>Voter ID card No.<span class="text-error">*</span></th>
		<th>Passport No./<br/>Driving Licence No.<span class="text-error">*</span></th>
		<td><input type="button"  class="infoBtn"  value="Add" onclick="return addRow('memberTbl');"/><br/>
		<input type="button"  class="infoBtn"  value="Delete" onclick="return removeRow('memberTbl');"/></td>
		

	<logic:present name="piaMemberDetail" >
	<logic:empty name="piaMemberDetail"> 
	<tr id="TArowId0">
		<td><html:hidden property="memberCode" styleId="memberCode0" value="0" />
		<html:text  property="memberName" styleId="memberName0" size="14" onblur="this.value=this.value.toUpperCase();" /></td>
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
			<td><html:select  property="memberDesignation" styleId="memberDesignation0">
				<html:option value="">Select</html:option>
				<html:option value="Director">Director</html:option>
			</html:select></td>
		</c:if>
		<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
			<td><html:text  property="memberLiability" styleId="memberLiability0" size="3" maxlength="3"/></td>
		</c:if>
		<td><html:text  property="memberContact" styleId="memberContact0"  size="11" maxlength="11" onkeypress="return isNumberKey(event)"/></td>
		<td><html:text  property="memberEmail" styleId="memberEmail0" size="14" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text  property="memberPan" styleId="memberPan0" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text  property="memberAadharVoterNo" styleId="memberAadharVoterNo0" size="12"  maxlength="12" onblur="this.value=this.value.toUpperCase();" /></td>	
		<td><html:text  property="memberPassportDrivingNo" styleId="memberPassportDrivingNo0" size="16" maxlength="16" onblur="this.value=this.value.toUpperCase();" /></td>	
	</tr>
	</logic:empty>
	<c:set var="indexCount" value="0"/>
	<logic:iterate id="member" name="piaMemberDetail">
	<c:if test="${member.nriStatus == null && member.authorizedPerson == null}">
	<html:hidden property="memberCode" styleId="memberCode${indexCount}" value="${member.memberCode}" />
	<tr id="TArowId${indexCount}">
		<td><html:text property="memberName" styleId="memberName${indexCount}" size="14" value="${member.memberName}" onblur="this.value=this.value.toUpperCase();"  /></td>
		<c:if test="${(piaDetail.categoryCode != 3) && (piaDetail.categoryCode != 4)}">
			<td><html:select  property="memberDesignation" styleId="memberDesignation${indexCount}" value="${member.designation}" >
				<html:option value="">Select</html:option>
				<html:option value="Director">Director</html:option>
			</html:select></td>
		</c:if>
		<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
			<td><html:text  property="memberLiability" styleId="memberLiability${indexCount}" size="3" maxlength="3" value="${member.liability}" /></td>
		</c:if>
		<td><html:text property="memberContact" styleId="memberContact${indexCount}" value="${member.contact}" size="11" maxlength="11" onkeypress="return isNumberKey(event)"/></td>
		<td><html:text property="memberEmail" styleId="memberEmail${indexCount}" value="${member.email}" size="14" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="memberPan" styleId="memberPan${indexCount}" size="10" value="${member.pan}" maxlength="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="memberAadharVoterNo" styleId="memberAadharVoterNo${indexCount}" size="12" value="${member.aadharVoterNo}"  maxlength="12" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="memberPassportDrivingNo" styleId="memberPassportDrivingNo${indexCount}" value="${member.passportDrivingNo}" size="16" maxlength="16" onblur="this.value=this.value.toUpperCase();" /></td>		
	</tr>
	<c:set var="indexCount" value="${indexCount+1}"/>
	</c:if>	
	</logic:iterate>
	</logic:present> 
</table>
</td></tr>








<!-- In case of an Educational institution with land and building -->
<logic:equal value="1" property="categoryCode" name="piaDetail" >
<tr><td align="center" style="padding-top: 7px;">
	<table width="98%"  align="center" class="inputTBL">
	<tr><td><label class="label-inverse">Details of Land and Building owned by the Educational Institution</label></td></tr>
	<tr>
		<th>Address of Land / Building</th>
		<th>Whether Freehold / mortgaged</th>
	</tr>
<tr>
	<td><html:text property="addressLandBuilding" styleId="addressLandBuilding" name="piaDetail" /></td>
	<td><html:select  property="freeholdMortgaged" styleId="freeholdMortgaged" name="piaDetail" >
				<html:option value="">Select</html:option>
				<html:option value="Freehold">Freehold</html:option>
				<html:option value="Mortgaged">Mortgaged</html:option>				
			</html:select>
	</td>
</tr>		
</table></td>	
</tr>
</logic:equal>
<!-- In case of an Educational institution with land and building -->
<!-- In case a Partner in the Firm is a not an Indian Citizen, details of such Partner -->
<c:if test="${(piaDetail.categoryCode == 3) || (piaDetail.categoryCode == 4)}">
<tr><td align="center" style="padding-top: 7px">
	<table width="98%" align="center" class="inputTBL" id="nriTbl">	
	<tr><td colspan="7"><label class="label-inverse">In case a Partner in the Firm is a not an Indian Citizen, details of such Partner</label></td></tr>
	<tr>
		<th align="left">Name</th>				
		<th align="left">Country</th>
		<th align="left">Passport No</th>				
		<th align="left">Valid till</th>
		<th align="left">Visa valid till</th>
		<th align="left">Whether<br/>a valid<br/>work permit<br/> held in<br/>India </th>				
		<th align="left">If yes,<br/>Work permit<br/>valid till</th>
		<th align="left">Whether<br/>clearance from<br/>FRA, MHA<br/>obtained.</th>
		<td><input type="button"  class="infoBtn"  value="Add" onclick="return addRowNRI('nriTbl');"/><br/>
		<input type="button"  class="infoBtn"  value="Delete" onclick="return deleteRowNRI('nriTbl');"/></td>
	</tr>
	<logic:present name="piaMemberDetail">
	<c:set var="indexCount" value="0"/>
	<logic:iterate id="member" name="piaMemberDetail" indexId="index">
	<c:if test="${member.nriStatus == 'yes'}">
	<html:hidden property="nriCode" styleId="nriCode${indexCount}" value="${member.memberCode}" />
	<tr id="nriTbl${indexCount}">
		<td><html:text name="RegistrationForm" property="nriName" styleId="nriName${indexCount}" value="${member.memberName}" size="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="nriCountry" styleId="nriCountry${indexCount}" value="${member.country}" size="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="nriPassportNo" styleId="nriPassportNo${indexCount}" value="${member.passportDrivingNo}" size="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="nriPassportValidDate" styleId="nriPassportValidDate${indexCount}" value="${member.passportValidDate}" readonly="true" size="10" 
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].nriPassportValidDate${indexCount},'dd-mm-yyyy',this)"/></td>
		<td><html:text property="nriVisaValidDate" styleId="nriVisaValidDate${indexCount}" value="${member.visaValidDate}" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].nriVisaValidDate${indexCount},'dd-mm-yyyy',this)"/></td>
		<td><html:select property="nriWorkPermit" styleId="nriWorkPermit${indexCount}" value="${member.workPermit}" >
				<html:option value="">Select</html:option>
				<html:option value="Yes">Yes</html:option>
				<html:option value="No">No</html:option>				
			</html:select></td>
		<td><html:text  property="nriWorkingPermitDate" styleId="nriWorkingPermitDate${indexCount}" value="${member.workingPermitDate}" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].nriWorkingPermitDate${indexCount},'dd-mm-yyyy',this)"/></td>
		<td><html:select  property="nriclearanceFRAMHA" styleId="nriclearanceFRAMHA${indexCount}" value="${member.clearanceFRAMHA}" >
				<html:option value="">Select</html:option>
				<html:option value="Yes">Yes</html:option>
				<html:option value="No">No</html:option>				
			</html:select>
		</td>
		<td>
		</td>
	</tr> 
	<c:set var="indexCount" value="${indexCount+1}"/>
	</c:if>
	</logic:iterate>
	</logic:present> 
</table>
</td>	
</tr>
</c:if>

<tr><td align="center" style="padding-top: 7px;">
<c:set var="authorizedMember" value="false" />
<logic:iterate id="member" name="piaMemberDetail" indexId="index">
<c:if test="${member.authorizedPerson == 'yes'}">
<c:set var="authorizedMember" value="true" />
	<html:hidden property="authPersonCode" styleId="authPersonCode" value="${member.memberCode}" />
	<table width="98%" align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="9"><label class="label-inverse">Authorized Person Details</label>
	&emsp;(<span class="text-info">Only authorised person need to fill the below detail</span>)</td></tr>	
	<tr>
		<th width="20%">Name<span class="text-error">*</span></th>
		<td><html:text  property="authPersonName" styleId="authPersonName" value="${member.memberName}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
		<th width="20%">S/o, D/o, W/o<span class="text-error">*</span></th>
		<td><html:text  property="authRelativeName" styleId="authRelativeName" value="${member.relativeName}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>
	<tr>
		<th>Residence Address<span class="text-error">*</span></th>
		<td><html:textarea property="authAddress" styleId="authAddress" value="${member.address}" rows="3" onblur="this.value=this.value.toUpperCase();" /></td>
		<th>Age<span class="text-error">*</span></th>
		<td><html:text  property="authAge" styleId="authAge"  size="3" value="${member.age}" maxlength="3" onkeypress="return isNumberKey(event)" /></td>
	</tr>
	<tr>
		<th>Designation<span class="text-error">*</span></th>
		<td><html:text  property="authDesignation" styleId="authDesignation" value="${member.designation}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
		<th>Occupation<span class="text-error">*</span></th>
		<td><html:text  property="authOccupation" styleId="authOccupation" value="${member.occupation}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>			
	</tr>

	<tr>
		<th>Contact<span class="text-error">*</span></th>
		<td><html:text  property="authPersonContact" styleId="authPersonContact" value="${member.contact}" size="11" maxlength="11" onkeypress="return isNumberKey(event)" /></td>
		<th>Email<span class="text-error">*</span></th>
		<td><html:text  property="authPersonEmail" styleId="authPersonEmail" value="${member.email}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>
	<tr>
		<th>Pan No<span class="text-error">*</span></th>
		<td><html:text  property="authPersonPan" styleId="authPersonPan" value="${member.pan}" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<th>Aadhaar No./<br/>Voter-ID card No.<span class="text-error">*</span></th>
		<td><html:text  property="authPersonAdhaar" styleId="authPersonAdhaar" value="${member.aadharVoterNo}" size="12" maxlength="12" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>	
	<tr>		
		<th>State<span class="text-error">*</span></th>
		<td><html:select property="authStateCode" styleId="authStateCode"  value="${member.stateCode}">
			<html:option value="">Select</html:option>
				<logic:present name="stateList">
					<html:options collection="stateList" property="stateCode" labelProperty="stateName" />
				</logic:present>
			</html:select></td>				
        <th>Passport No./<br/>Driving Licence No.<span class="text-error">*</span></th>
		<td><html:text  property="authPersonPassport" styleId="authPersonPassport" value="${member.passportDrivingNo}" size="16" maxlength="16" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>
	
	<tr>	
		<th>Post Office<span class="text-error">*</span></th>
		<td><html:text  property="authPostOffice" styleId="authPostOffice" value="${member.postOffice}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>	
		<th>Police Station<span class="text-error">*</span></th>
		<td><html:text  property="authPoliceStation" styleId="authPoliceStation" value="${member.policeStation}" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>	
	<tr>	
			<th>Upload CV<span class="text-error">*</span></th>
			<td>
				<a href="javascript:showFiles('${member.cvFileName}');" >View</a>
				<a href="javascript:fileStatus('cvFile', 'show');">Change</a>
				<html:file property="cvFile" name="registrationForm" style="display:none;" onchange="javascript:checkFiles('cvFile');checkcvformat(this.value);"
					styleId="cvFile" styleClass="infoBtn"/>	
				<a id="cvFileRemove" style="display:none;" href="javascript:fileStatus('cvFile', 'hide');" >Remove</a>
				<small><font id="cvError" class="text-error"></font></small>
				<logic:notEmpty name="cvError">
						<small><font class="text-error">${cvError }</font></small>
				</logic:notEmpty>
			</td>
				<th>Upload Authorized Person Photo<span class="text-error">*</span></th>
			<td>
				<a href="javascript:showFiles('${member.photoFileName}');" >View</a><!-- onchange="javascript:checkFiles('photoFile');" -->
				<a href="javascript:fileStatus('photoFile', 'show');">Change</a>
				<html:file property="photoFile" name="registrationForm" style="display:none;" onchange="checkimg(this.value)"
						styleId="photoFile" styleClass="infoBtn"/>	
				<a id="photoFileRemove" style="display:none;" href="javascript:fileStatus('photoFile', 'hide');" >Remove</a>
				<small><font id="photoError" class="text-error"></font></small>
				<logic:notEmpty name="photoError">
						<small><font class="text-error">${photoError}</font></small>
				</logic:notEmpty>
				<html:hidden property="authPhotoFileName" name="member"  value="${member.photoFileName }"/>
				<html:hidden property="authCVFileName" name="member"  value="${member.cvFileName }" />
			</td>
	</tr>		
 </table>
</c:if>
</logic:iterate>
<c:if test="${authorizedMember == 'false' }">
<table width="98%" align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="9"><label class="label-inverse">Authorized Person Details</label>
	&emsp;(<span class="text-info">Only authorised person need to fill the below detail</span>)</td></tr>	
	<tr>
		<th width="20%">Name<span class="text-error">*</span></th>
		<td><html:text  property="authPersonName" styleId="authPersonName" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
		<th width="20%">S/o, D/o, W/o<span class="text-error">*</span></th>
		<td><html:text  property="authRelativeName" styleId="authRelativeName" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>
	<tr>
		<th>Residence Address<span class="text-error">*</span></th>
		<td><html:textarea property="authAddress" styleId="authAddress" rows="3" onblur="this.value=this.value.toUpperCase();" /></td>
		<th>Age<span class="text-error">*</span></th>
		<td><html:text  property="authAge" styleId="authAge"  size="3" maxlength="3" onkeypress="return isNumberKey(event)" /></td>
	</tr>
	<tr>
		<th>Designation<span class="text-error">*</span></th>
		<td><html:text  property="authDesignation" styleId="authDesignation" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
		<th>Occupation<span class="text-error">*</span></th>
		<td><html:text  property="authOccupation" styleId="authOccupation" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>			
	</tr>

	<tr>
		<th>Contact<span class="text-error">*</span></th>
		<td><html:text  property="authPersonContact" styleId="authPersonContact" size="11" maxlength="11" onkeypress="return isNumberKey(event)" /></td>
		<th>Email<span class="text-error">*</span></th>
		<td><html:text  property="authPersonEmail" styleId="authPersonEmail" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>
	<tr>
		<th>Pan No<span class="text-error">*</span></th>
		<td><html:text  property="authPersonPan" styleId="authPersonPan" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /></td>
		<th>Aadhaar No./<br/>Voter-ID card No.<span class="text-error">*</span></th>
		<td><html:text  property="authPersonAdhaar" styleId="authPersonAdhaar" size="12" maxlength="12" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>	
	<tr>
			<th>State<span class="text-error">*</span></th>
				<td><html:select property="authStateCode" styleId="authStateCode">
				<html:option value="">Select</html:option>
				<logic:present name="stateList">
					<html:options collection="stateList" property="stateCode" labelProperty="stateName" />
				</logic:present>
			</html:select></td>				
        <th>Passport No./<br/>Driving Licence No.<span class="text-error">*</span></th>
		<td><html:text  property="authPersonPassport" styleId="authPersonPassport" size="16" maxlength="16" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>	
	<tr>	
		<th>Post Office<span class="text-error">*</span></th>
		<td><html:text  property="authPostOffice" styleId="authPostOffice" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>	
		<th>Police Station<span class="text-error">*</span></th>
		<td><html:text  property="authPoliceStation" styleId="authPoliceStation" maxlength="50" onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>	
	<tr>	
		<th>Upload CV<span class="text-error">*</span></th>
		<td><html:file style="display:bold;" property="cvFile" name="registrationForm" styleId="cvFile" styleClass="infoBtn" onchange="checkcvformat(this.value)"/>
		<font id="cvError" class="text-error"></font>
		<logic:notEmpty name="cvError">
			<small><font id="cError" class="text-error">${cvError }</font></small>
		</logic:notEmpty>
		</td>		
		<th>Upload Authorized Person Photo<span class="text-error">*</span></th>
		<td><html:file style="display:bold;" property="photoFile" name="registrationForm" styleId="photoFile" styleClass="infoBtn" onchange="checkimg(this.value)"/>
		<font id="photoError" class="text-error"></font>
		<logic:notEmpty name="photoError">
			<small><font id="pError" class="text-error">${photoError }</font></small>
		</logic:notEmpty>		
		</td>
	</tr>		
 </table>
</c:if>
</td></tr>

	
<tr><td style="padding-top: 7px;">
<table width="100%" align="center" class="inputTBL" >
<tr>
<th>Whether the Applicant is a Non-Profit Organization</th>
<td>
<html:select  property="isNonProfitOrg" onchange="change()" styleId="isNonProfitOrg" >
	    <html:option value="No">No</html:option>
	     <html:option value="Yes">Yes</html:option>
	     
		 </html:select>
</td>
</tr>
</table>
</td>
</tr>

<tr><td style="padding-top: 7px;">
<table width="100%" align="center" class="inputTBL" id="additionalDetail"  style="display: none">
	<tr>
		<td colspan="4"><label class="label-inverse">Additional Details</label></td>
	</tr>
	<tr><th width="5%">S.No.</th>
		<th width="55%"></th>
		<th width="20%">Registration Number</th>
		<th width="20%">Registration Date</th>
	</tr>
	<tr><th>1</th>
		<th>Details of registration under section 12A of Income Tax Act of 1956</th>
		<td><html:text property="regNoSection12A" styleId="regNoSection12A" name="piaDetail" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="regDateSection12A" styleId="regDateSection12A" name="piaDetail" readonly="true" size="10" maxlength="16"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateSection12A,'dd-mm-yyyy',this)"/></td>
	</tr>
	<tr><th>2</th>
		<th>Details of registration under section 80G of Income Tax Act of 1956</th>
		<td><html:text property="regNoSection80G" styleId="regNoSection80G" name="piaDetail" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="regDateSection80G" styleId="regDateSection80G" name="piaDetail"  readonly="true" size="10" maxlength="16"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateSection80G,'dd-mm-yyyy',this)"/></td>
		
	</tr>
	<tr><th>3</th>
		<th>Details of registration under FCRA</th>
		<td><html:text property="regNoFCRA" styleId="regNoFCRA" name="piaDetail" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="regDateFCRA" styleId="regDateFCRA" name="piaDetail"  readonly="true" size="10" maxlength="16"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateFCRA,'dd-mm-yyyy',this)"/></td>
	</tr>
</table>
</td>
</tr>
	<tr id="captcha" >
		<td align="center">
			<img id="captchaImg" src="${pageContext.request.contextPath}/captcha" alt="Captcha Image" height="45" />
			<img src="../images/refresh.png" alt="Reload" onclick="document.forms[0].captchaImg.src='${pageContext.request.contextPath}/captcha?id='+Math.random();" style="cursor:pointer"/>
			<br/>Type above text: <html:errors property="error1"/>
			<html:text property="captchaResponse" styleId="captchaResponse" size="7"  maxlength="7" onchange="checkCaptcha()"/><font id="captchaError" style="color:red;"></font>
		</td>
	</tr>
</table>

	<div align="center" style="padding-top: 15px; padding-bottom: 15px;">
		<%-- <html:button styleClass="inverseBtn" property="" onclick="createPdf();" value="Download the registration details entered in pdf format" /> --%>
		<html:button styleClass="primaryBtn" property="" onclick="updateAndSubmit();" value="Update & Submit" />
		<html:button styleClass="defaultBtn" property="" onclick="quit()" value="Exit" />
	</div>
</div>
</html:form>
</body>
