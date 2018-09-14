<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function quit(){
	var status = window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
		window.location = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
	}
}

function save(){
	var validationCheck = validate();
	if(validationCheck == true){
		var status=window.confirm('<bean:message key="msg.saveForm" />');
	    if(status==true){
	    	document.forms[0].action = "sanctionOrder.do?methodName=save&" + tokenParameter + "=" + tokenValue;
	    	document.forms[0].submit();
	    }
	}	
}

function checkFileFormat(fileId){
	var flag = false;
	var fileName = document.getElementById(fileId).value;
	var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
	if(ext == "pdf" || ext == "PDF" || ext == "doc" || ext == "docx"){
		flag = true;
	}
	return flag;
}

function removeError(first, second){
	document.getElementById(first).innerHTML='';
	document.getElementById(second).innerHTML='';
}

function checkFiles(){
	var count = 0;
		 var fileErrorLocation = document.getElementById("docSanctionFileError");
		  var attachedFile = "sanctionFile";
	     if(!checkFileFormat(attachedFile)){
				fileErrorLocation.innerHTML = "Incorrect file format ";
				count++;
	     }
				
		if(count > 0){
			return false;
		}else{
			return true;
		}
	}




/* function getPrnNo(){
	document.getElementById("prnId").innerHTML="";
	 var piaCode = document.getElementById("piaCode").value;
	 var xmlHTTP;
		if(window.XMLHttpRequest) {
			xmlHTTP = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
		}
	    var url = 'UploadSanctionFile.do?methodName=getPrnNo';
	    xmlHTTP.onreadystatechange = getPrnNumber;
		xmlHTTP.open("POST",url, true);
		xmlHTTP.setRequestHeader(tokenParameter, tokenValue);
		xmlHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHTTP.send("piaCode="+piaCode+"");
		function getPrnNumber(){
			if(xmlHTTP.readyState==4 && xmlHTTP.status==200){
			document.getElementById("prnId").innerHTML= xmlHTTP.responseText;
		}
		};      
} */

function getPrnNo(){
	document.forms[0].action = "sanctionOrder.do?methodName=getPrnNo&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
	}

function validate(){ 
	var validationCount = 0;
	
	var event1 = new LiveValidation(document.forms[0].sanctionOrderNo,{onlyOnSubmit:true});
	event1.add( Validate.Presence );	
	event1.add( Validate.Format, { pattern: /^[a-zA-Z0-9\/\-()]+$/ , failureMessage: "Special Characters / ( ) - and alphanumeric are only allowed."});		
	var event2 = new LiveValidation(document.forms[0].piaCode,{onlyOnSubmit:true});
		event2.add( Validate.Presence );
		
	var event3 = new LiveValidation(document.forms[0].sanctionOrderDate,{onlyOnSubmit:true});
		event3.add( Validate.Presence );	
				
		
	var areAllValid = LiveValidation.massValidate( [ event1, event2, event3] );
	
	if(!checkFiles()){
		validationCount++;
	}
	if(!areAllValid){
		validationCount++;
	}
			
	if(validationCount == 0)
		return true;	
	else
		return false;	
}
</script>

<html:form action="/login/sanctionOrder" enctype="multipart/form-data">
	<table width="100%" align="center">
		<tr>
			<td align="center" class="pageHeader">Uploading Sanction Order</td>
		</tr>
		<tr>
			<td align="center" class="text-error"><logic:present
					name="sameSanctionNumber">${sameSanctionNumber}</logic:present>
			</td>
		</tr>
		<tr>
		<tr>
			<td align="center"><logic:present name="notification">${notification}</logic:present>
			</td>
		</tr>
		<tr>
			<td><label class="label-important">All
					fields marked with (*) are mandatory to fill.</label></td>
		</tr>
		<tr>
			<td align="center">
				
					<table width="100%" align="center" class="bordered">
					<tr>					
					<th colspan="4">Applicant Organisation  Details</th>
					</tr>
					<tr>
						<td><strong>Name of the Applicant Organisation </strong><span class="text-error">*</span></td>
						<td><html:select property="piaCode" styleId="piaCode"
								onchange="getPrnNo();">
								<html:option value="">Select</html:option>
								<logic:present name="piaDetail">
									<html:options collection="piaDetail" property="piaCode"
										labelProperty="piaName" />
								</logic:present>
							</html:select>
						</td>
					</tr>
					<tr>
						<td><strong>Applicant Organisation Permanent Registration Number</strong></td>
						<td>
						<logic:present name="prnIdValue">
							<html:hidden property="piaRegistrationNumber" value="${prnIdValue}"></html:hidden>
							${prnIdValue}
						</logic:present>
						</td>
					</tr>
					</table>
					<table width="100%" align="center" class="bordered">
					<tr>
					<th colspan="4">Sanction Order Details</th>
					</tr>
					<tr>
						<td width="150px"><strong>Sanction Order Number</strong> <span class="text-error">*</span></td>
						<td><html:text property="sanctionOrderNo"
								styleId="sanctionOrderNo" size="35" maxlength="35" /></td>
					</tr>
					<tr>			
						<td><strong>Sanction Order Date</strong><span class="text-error">*</span></td>
						<td><html:text property="sanctionOrderDate"
								styleId="sanctionOrderDate" size="10" maxlength="10"
								readonly="true" title="Click here to select date"
								onclick="javascript:displayCalendar(document.forms[0].sanctionOrderDate,'dd-mm-yyyy',this)" />
						</td>
					</tr>
					</table>
					<table width="100%" align="center" class="bordered">
					<tr>
						<th colspan="2"><strong>Upload File<span
								class="text-error">*</span>
							<small><span class="text-error">(upload supporting document in
									doc or pdf format, subjected to maximum size of 7 MB)</span></small>
						</strong> </th>
						<td><html:file property="sanctionFile" styleId="sanctionFile"
								styleClass="infoBtn"
								onclick="javascript:removeError('docSanctionFileError','sError')" />
							<small><font id="docSanctionFileError" class="text-error"></font>
						</small> <logic:notEmpty name="docSanctionFileError">
								<small><font id="sError" class="text-error">${docSanctionFileError}</font>
								</small>
							</logic:notEmpty></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<div align="center" style="padding-top: 15px; padding-bottom: 15px;">
		<html:button styleClass="primaryBtn" property="" onclick="save();"
			value="Submit" />
		<html:button styleClass="defaultBtn" property="" onclick="quit();"
			value="Exit" />
	</div>
</html:form>
