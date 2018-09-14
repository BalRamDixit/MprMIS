
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function checkCaptcha(){
	var flag = false;
	var div = document.getElementById("captchaError");
	div.innerHTML= "";
	var captcha = document.getElementById("captchaResponse").value;
	var xmlhttp;
	var captchaText = document.getElementById("captchaResponse").value;
//	var url =  location.protocol + '//' + location.host+"/prnlive/captchaCheck?captcha="+captchaText;
	var projName = '${pageContext.request.contextPath}';
	var url =  location.protocol + '//' + location.host+"/"+projName.substr(1)+"/captchaCheck?captcha=" + captchaText;


	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
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
	return flag;
}

function save(){
	var validationCheck = validate();
	if(validationCheck == true){
		if(checkCaptcha() == true){
			var status=window.confirm("<bean:message key="alert.submit.confirm" />");
			if(status==true){
				document.forms[0].action = "registrationAction.do?methodName=savePartB&" + tokenParameter + "=" + tokenValue;
				document.forms[0].submit();
			}
		}
	}
}	

function finalsubmit(){
	if(checkCaptcha() == true){
		var status=window.confirm('You are going to sumbit the Applicant Organisation Registration to MoRD.');
		if(status==true){
			document.forms[0].action = "registrationAction.do?methodName=submit&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
	}	
}	

function quit(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
		window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
}

function createPdf(){
	document.forms[0].action = "registrationAction.do?methodName=createPdf&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
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
	row.setAttribute('id',tblName+'rowId'+index);	
	
	cellLeft = row.insertCell(0);
	cellLeft.innerHTML="<input type='text' name='memberName' id='memberName"+cou+"' size='14' maxlength='50'/>";
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
	cellLeft.innerHTML='<input type="text" name="memberContact" value="" id="memberContact'+ cou +'" size="11" maxlength="11"  >';
	
	cellLeft = row.insertCell(3); 
	cellLeft.innerHTML='<input type="text" name="memberEmail"  value="" id="memberEmail'+ cou +'"  size="14" maxlength="50">';
	
	cellLeft = row.insertCell(4); 
	cellLeft.innerHTML='<input type="text" name="memberPan"  value="" id="memberPan'+ cou +'" size="10" maxlength="10">';		
	
	cellLeft = row.insertCell(5);  
	cellLeft.innerHTML='<input type="text" name="memberAadharVoterNo"  value="" id="memberAadharVoterNo'+ cou +'" size="12" maxlength="12">';
	
	cellLeft = row.insertCell(6);  
	cellLeft.innerHTML='<input type="text" name="memberPassportDrivingNo"  value="" id="memberPassportDrivingNo'+ cou +'" size="16" maxlength="16">';	
}

function removeRow(tableId){
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

function tableShow(){
	document.getElementById("NRItable").style.visibility = "";
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
	row.setAttribute('id',tblName+'rowId'+index);
	
	cellLeft = row.insertCell(0);
	cellLeft.innerHTML="<input type='text' name='nriName' id='nriName"+cou+"' size='14' />";
	cellLeft.align="center";

	cellLeft = row.insertCell(1); 
	cellLeft.innerHTML='<input type="text" name="nriCountry"  value="" id="nriCountry'+ cou +'" size="10" >';

	cellLeft = row.insertCell(2);  	
	cellLeft.innerHTML='<input type="text" name="nriPassportNo" maxlength="10" value="" id="nriPassportNo'+ cou +'" size="10"  >';
	
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

function validate(){
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
			
		var nriWorkingPermitDate = new LiveValidation('nriWorkingPermitDate'+i, {onlyOnSubmit:true});
		nriWorkingPermitDate.add( Validate.Presence);	
			
		var nriclearanceFRAMHA = new LiveValidation('nriclearanceFRAMHA'+i, {onlyOnSubmit:true});
		nriclearanceFRAMHA.add( Validate.Presence);		
			
			
		var areAllValid = LiveValidation.massValidate( [nriCountry, nriName, nriPassportNo, nriPassportValidDate, nriVisaValidDate, nriWorkPermit ,nriWorkingPermitDate , nriclearanceFRAMHA]);
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
			
	var authPersonPan = new LiveValidation('authPersonPan', {onlyOnSubmit:true});
		authPersonPan.add( Validate.Presence);
		authPersonPan.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
	
	var authPersonEmail = new LiveValidation('authPersonEmail', {onlyOnSubmit:true});
		authPersonEmail.add( Validate.Presence);
	
	var authPersonAdhaar = new LiveValidation('authPersonAdhaar', {onlyOnSubmit:true});
		authPersonAdhaar.add( Validate.Presence);
	
	var authPersonPassport = new LiveValidation('authPersonPassport', {onlyOnSubmit:true});
		authPersonPassport.add( Validate.Presence);
	
	var authRelativeName = new LiveValidation('authRelativeName', {onlyOnSubmit:true});
		authRelativeName.add( Validate.Presence);
	
	var authAge = new LiveValidation('authAge', {onlyOnSubmit:true});
		authAge.add( Validate.Presence);
	
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
	
	
 	// Validation for Education Institutions End
	
 	// Validation for Addtion Registration detail Start
 	/* var regNoSection12A = new LiveValidation('regNoSection12A', {onlyOnSubmit:true});
    regNoSection12A.add( Validate.Presence);	
	
    var regDateSection12A = new LiveValidation('regDateSection12A', {onlyOnSubmit:true});
    regDateSection12A.add( Validate.Presence);	
	
    var regNoSection80G = new LiveValidation('regNoSection80G', {onlyOnSubmit:true});
    regNoSection80G.add( Validate.Presence);	
	
    var regDateSection80G = new LiveValidation('regDateSection80G', {onlyOnSubmit:true});
    regDateSection80G.add( Validate.Presence);	
	
    var regNoFCRA = new LiveValidation('regNoFCRA', {onlyOnSubmit:true});
    regNoFCRA.add( Validate.Presence);	
	
	var regDateFCRA = new LiveValidation('regDateFCRA', {onlyOnSubmit:true});
	regDateFCRA.add( Validate.Presence);	
	// Validation for Addtion Registration detail End
	
	areAllValid = LiveValidation.massValidate( [regNoSection12A, regDateSection12A, regNoSection80G, regDateSection80G, regNoFCRA, regDateFCRA]);
	if(!areAllValid){
		errorCount++;
     } */

	if(errorCount == 0)
		isValidate = true;
	return isValidate;
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
function checkFileSize(fileId){
	var flag = false;
	var sizeInBytes = document.getElementById(fileId).files[0].size;
	var fileSize = (sizeInBytes/1024);
	if(200 > fileSize){
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
		 if(document.getElementById('photoFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("photoError");
			 attachedFile[index] = "photoFile";
			 index++;
		 }
		 if(document.getElementById('cvFile').style.display != 'none'){
			 fileErrorLocation[index] = document.getElementById("cvError");
			 attachedFile[index] = "cvFile";
			 index++;
		 }
		for(i = 0; i < attachedFile.length; i++){
			if(!checkFileFormat(attachedFile[i])){
				fileErrorLocation[i].innerHTML = "Incorrect file format ";
				count++;
			}else if(!checkFileSize(attachedFile[i])){
				fileErrorLocation[i].innerHTML = "File Size should be less than 300 Kb";
				flagFileCount = flagFileCount + 1;
				count++;
			}
		}
		if(count > 0){
			return false;
		}else{
			return true;
		}
	}else{	
		var ErrorID = document.getElementById(id.replace("File","Error"));
		ErrorID.innerHTML = "";
		if(!checkFileFormat(id)){			
			ErrorID.innerHTML = "Incorrect file format ";
		}else if(!checkFileSize(id)){
			ErrorID.innerHTML = "File Size should be less than 300 Kb";
		}
	}
}

function showFiles(fileType) {
	document.forms[0].action = "registrationAction.do?methodName=showFiles&file="+fileType+"&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

function fileStatus(id, work){
	 if(work == 'show'){
		 document.getElementById(id).style.display='';
		 document.getElementById(id.replace("File","Remove")).style.display = '';
		 document.getElementById(id.replace("File","Error")).innerHTML = "";
	 }else if(work == 'hide'){
		 document.getElementById(id).style.display='none';
		 document.getElementById(id).value = '';
		 document.getElementById(id.replace("File","Remove")).style.display = 'none';
		 document.getElementById(id.replace("File","Error")).innerHTML = "";
	 }
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
function checkimg(imgId) {	
	  // document.getElementById("uploadimg").style.display="none";
		var ext = imgId.split(".");
	    ext = ext[ext.length-1].toLowerCase();      
	    var arrayExtensions = ["jpg" , "jpeg", "png", "bmp", "gif"];
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
	}
</script>

<body onload="loadWhetherApplicant()">
	<!-- Rahul -->
	<!-- <h3>registration.jsp</h3> -->
	<!-- To display the file name -->
	<%-- <h3><%= pageContext.getPage().getClass().getName().substring(pageContext.getPage().getClass().getName().lastIndexOf('.')+1).replace('_','.') %></h3> --%>

<html:form action="registrationAction"  method="post" enctype="multipart/form-data">
<div class="outerTBL">

<!-- Used to identify the category -->
<html:hidden property="" value="${registrationForm.categoryCode}" styleId="categoryCheck" />

<table width="100%" align="center">
	<tr>
		<td class="pageHeader" align="center">Applicant Organisation <small>Registration Form</small></td>
	</tr>
	<tr>
		<td><label class="label-important">All fields marked with (*) are mandatory to fill.</label></td>
	</tr>
	<tr>
	<td align="center" >
	<table width="100%"  align="center" class="inputTBL" id="memberTbl" style="padding-top: 7px;">	
	<tr><td colspan="9"><label class="label-inverse">Details of the Owners/ Directors </label></td></tr>	
	<tr>
		<th>Name<span class="text-error">*</span></th>	
		<c:if test="${(registrationForm.categoryCode != 3) && (registrationForm.categoryCode != 4)}">
			<th>Designation<span class="text-error">*</span></th>
		</c:if>
		
		<c:if test="${(registrationForm.categoryCode == 3) || (registrationForm.categoryCode == 4)}">
			<th>Liability<span class="text-error">*</span></th>
			<html:hidden property="categoryCode" name="registrationForm" styleId=""></html:hidden>
		</c:if>	
		<th>Contact<span class="text-error">*</span></th>
		<th>Email<span class="text-error">*</span></th>
		<th>PAN No.<span class="text-error">*</span></th>
		<th>Aadhaar No./<br/>Voter’s ID card No.<span class="text-error">*</span></th>
		<th>Passport No./<br/>Driving Licence No.<span class="text-error">*</span></th>
		<td><input type="button"  class="infoBtn"  value="Add" onclick="return addRow('memberTbl');"/></td>
	</tr>
	<logic:present name="registrationForm" >
	<logic:iterate id="member" name="registrationForm" property="memberName" indexId="index">
	<tr id="TArowId${index}">
		<td><html:text  property="memberName" styleId="memberName${index}" value="${registrationForm.memberName[index]}" size="14" /></td>
		<c:if test="${(registrationForm.categoryCode != 3) && (registrationForm.categoryCode != 4)}">
			<td><html:select  property="memberDesignation" styleId="memberDesignation${index}" value="${registrationForm.memberDesignation[index]}">
				<html:option value="">Select</html:option>
				<html:option value="Director">Director</html:option>
			</html:select></td>
		</c:if>
		<c:if test="${(registrationForm.categoryCode == 3) || (registrationForm.categoryCode == 4)}">
			<td><html:text  property="memberLiability" styleId="memberLiability${index}" value="${registrationForm.memberLiability[index]}" size="3" maxlength="3"/></td>
		</c:if>
		<td><html:text  property="memberContact" styleId="memberContact${index}" value="${registrationForm.memberContact[index]}" size="11" maxlength="11"/></td>
		<td><html:text  property="memberEmail" styleId="memberEmail${index}" value="${registrationForm.memberEmail[index]}" size="14" maxlength="50"/></td>
		<td><html:text  property="memberPan" styleId="memberPan${index}" value="${registrationForm.memberPan[index]}" size="10" maxlength="10"/></td>
		<td><html:text  property="memberAadharVoterNo" styleId="memberAadharVoterNo${index}" value="${registrationForm.memberAadharVoterNo[index]}" size="12"  maxlength="12"/></td>
		<td><html:text  property="memberPassportDrivingNo" styleId="memberPassportDrivingNo${index}" value="${registrationForm.memberPassportDrivingNo[index]}" size="16" maxlength="16" /></td>		
	</tr>	
	</logic:iterate>
	</logic:present> 
</table>

</td></tr>

<!-- In case of an Educational institution with land and building -->
<logic:equal value="1" property="categoryCode" name="registrationForm" >
<tr><td align="center" style="padding-top: 7px;">
	<table width="100%"  align="center" class="inputTBL">
	<tr><td><label class="label-inverse">Details of Land and Building owned by the Educational Institution</label></td></tr>
	<tr>
		<th>Address of Land / Building</th>
		<th>Whether Freehold / mortgaged</th>
	</tr>
<tr>
	<td><html:text property="addressLandBuilding" styleId="addressLandBuilding" /></td>
	<td><html:select  property="freeholdMortgaged" styleId="freeholdMortgaged" >
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
<c:if test="${(registrationForm.categoryCode == 3) || (registrationForm.categoryCode == 4)}">
<tr><td align="center" style="padding-top: 7px">
	<table width="100%"  align="center" class="inputTBL" id="nriTbl">	
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
		<td><input type="button"  class="infoBtn"  value="Add" onclick="return addRowNRI('nriTbl');"/></td>
	</tr>
	<logic:present name="registrationForm" >
	<logic:notEmpty name="registrationForm" property="nriName">
	<logic:iterate id="member" name="registrationForm" property="nriName" indexId="index">
	<tr id="nriTbl${index}">
		<td><html:text property="nriName" styleId="nriName${index}" value="${registrationForm.nriName[index]}" size="10" /></td>
		<td><html:text property="nriCountry" styleId="nriCountry${index}" value="${registrationForm.nriCountry[index]}" size="10" /></td>
		<td><html:text property="nriPassportNo" styleId="nriPassportNo${index}" value="${registrationForm.nriPassportNo[index]}" size="10" /></td>
		<td><html:text property="nriPassportValidDate" styleId="nriPassportValidDate${index}" value="${registrationForm.nriPassportValidDate[index]}" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].nriPassportValidDate${index},'dd-mm-yyyy',this)"/></td>
		<td><html:text property="nriVisaValidDate" styleId="nriVisaValidDate${index}" value="${registrationForm.nriVisaValidDate[index]}" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].nriVisaValidDate${index},'dd-mm-yyyy',this)"/></td>
		<td><html:select  property="nriWorkPermit" styleId="nriWorkPermit${index}" value="${registrationForm.nriWorkPermit[index]}">
				<html:option value="">Select</html:option>
				<html:option value="Yes">Yes</html:option>
				<html:option value="No">No</html:option>				
			</html:select></td>
		<td><html:text  property="nriWorkingPermitDate" styleId="nriWorkingPermitDate${index}" value="${registrationForm.nriWorkingPermitDate[index]}" readonly="true" size="10"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].nriWorkingPermitDate${index},'dd-mm-yyyy',this)"/></td>
		<td><html:select  property="nriclearanceFRAMHA" styleId="nriclearanceFRAMHA${index}" value="${registrationForm.nriclearanceFRAMHA[index]}">
				<html:option value="">Select</html:option>
				<html:option value="Yes">Yes</html:option>
				<html:option value="No">No</html:option>				
			</html:select>
		</td>
		<td>
		</td>
	</tr> 
	</logic:iterate>
	</logic:notEmpty> 
	</logic:present>
</table>
</td>	
</tr>
</c:if>
<tr><td>
<table width="100%"  align="center" class="inputTBL" style="padding-top: 7px;">	
	<tr><td colspan="9"><label class="label-inverse">Authorized Person Details</label>
	&emsp;(<span class="text-info">Only authorised person need to fill the below detail</span>)</td></tr>	
	<tr>
		<th width="20%">Name<span class="text-error">*</span></th>
		<td><html:text  property="authPersonName" styleId="authPersonName" maxlength="50" /></td>
		<th width="20%">S/o, D/o, W/o<span class="text-error">*</span></th>
		<td><html:text  property="authRelativeName" styleId="authRelativeName" maxlength="50" /></td>
	</tr>
	<tr>
		<th>Residence Address<span class="text-error">*</span></th>
		<td><html:textarea property="authAddress" styleId="authAddress" rows="3" /></td>
		<th>Age<span class="text-error">*</span></th>
		<td><html:text  property="authAge" styleId="authAge"  size="3" maxlength="3" /></td>
	</tr>
	<tr>
		<th>Designation<span class="text-error">*</span></th>
		<td><html:text  property="authDesignation" styleId="authDesignation" maxlength="50" /></td>
		<th>Occupation<span class="text-error">*</span></th>
		<td><html:text  property="authOccupation" styleId="authOccupation" maxlength="50" /></td>			
	</tr>

	<tr>
		<th>Contact<span class="text-error">*</span></th>
		<td><html:text  property="authPersonContact" styleId="authPersonContact" size="11" maxlength="11" /></td>
		<th>Email<span class="text-error">*</span></th>
		<td><html:text  property="authPersonEmail" styleId="authPersonEmail" maxlength="50" /></td>
	</tr>
	<tr>
		<th>Pan No<span class="text-error">*</span></th>
		<td><html:text  property="authPersonPan" styleId="authPersonPan" size="10" maxlength="10" /></td>
		<th>Aadhaar No./<br/>Voter-ID card No.<span class="text-error">*</span></th>
		<td><html:text  property="authPersonAdhaar" styleId="authPersonAdhaar" size="12" maxlength="12" /></td>
	</tr>	
	<tr>			
        <th>Passport No./<br/>Driving Licence No.<span class="text-error">*</span></th>
		<td><html:text  property="authPersonPassport" styleId="authPersonPassport" size="16" maxlength="16" /></td>
	</tr>
	<tr>	
		<th>State<span class="text-error">*</span></th>
		<td><html:select property="authStateCode" styleId="authStateCode" >
			<html:option value="">Select</html:option>
				<logic:present name="stateList">
					<html:options collection="stateList" property="stateCode" labelProperty="stateName" />
				</logic:present>
			</html:select></td>	
	</tr>
	<tr>	
		<th>Post Office<span class="text-error">*</span></th>
		<td><html:text  property="authPostOffice" styleId="authPostOffice" maxlength="50" /></td>	
		<th>Police Station<span class="text-error">*</span></th>
		<td><html:text  property="authPoliceStation" styleId="authPoliceStation" maxlength="50"  /></td>
	</tr>	
	<tr>	
			<th>Upload CV<span class="text-error">*</span></th>
			<td>
				<a href="javascript:showFiles('${cv}');" >View</a>
				<a href="javascript:fileStatus('cvFile', 'show');">Change</a>
				<html:file property="cvFile" name="registrationForm" style="display:none;" onchange="javascript:checkFiles('cvFile');"
					styleId="cvFile" styleClass="infoBtn"/>	
				<a id="cvRemove" style="display:none;" href="javascript:fileStatus('cvFile', 'hide');" >Remove</a>
				<small><font id="cvError" class="text-error"></font></small>
			</td>
				<th>Upload Photo<span class="text-error">*</span></th>
			<td>
				<a href="javascript:showFiles('${photo}');" >View</a>
				<a href="javascript:fileStatus('photoFile', 'show');">Change</a>
				<html:file property="photoFile" name="registrationForm" style="display:none;" onchange="checkimg(this.value);"
						styleId="photoFile" styleClass="infoBtn"/>	
				<a id="photoRemove" style="display:none;" href="javascript:fileStatus('photoFile', 'hide');" >Remove</a>
				<small><font id="photoError" class="text-error"></font></small>
			</td>
	</tr>		
</table>
</td></tr>


<tr><td style="padding-top: 7px;">
<table width="100%" align="center" class="inputTBL" >
<tr>
<th>Whether the Applicant is a Non-Profit Organization</th>
<td>

<html:select  property="isNonProfitOrg" name="registrationForm" onchange="change()"  styleId="isNonProfitOrg" >
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
		<td><html:text property="regDateSection12A" styleId="regDateSection12A" name="piaDetail" size="10" maxlength="16"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateSection12A,'dd-mm-yyyy',this)"/></td>
	</tr>
	<tr><th>2</th>
		<th>Details of registration under section 80G of Income Tax Act of 1956</th>
		<td><html:text property="regNoSection80G" styleId="regNoSection80G" name="piaDetail" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="regDateSection80G" styleId="regDateSection80G" name="piaDetail" size="10" maxlength="16"
						title="Click here to select date" onclick="javascript:displayCalendar(document.forms[0].regDateSection80G,'dd-mm-yyyy',this)"/></td>
		
	</tr>
	<tr><th>3</th>
		<th>Details of registration under FCRA</th>
		<td><html:text property="regNoFCRA" styleId="regNoFCRA" name="piaDetail" onblur="this.value=this.value.toUpperCase();" /></td>
		<td><html:text property="regDateFCRA" styleId="regDateFCRA" name="piaDetail" size="10" maxlength="16"
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
	<html:button styleClass="inverseBtn" property="" onclick="createPdf();" value="Download the registration details entered in pdf format" />
	<%-- <html:button styleClass="primaryBtn" property="" onclick="update();" value="Update" />	 --%>
	<html:button styleClass="primaryBtn" property="" onclick="finalsubmit();" value="final Submit" />
	<html:button styleClass="defaultBtn" property="" onclick="quit();" value="Cancel" />
</div>		
</div>
</html:form>
</body>