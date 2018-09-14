<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function quit(){
	var status = window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
		window.location = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
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


function getSanctionNo(){
	document.forms[0].action = "sanctionOrder.do?methodName=getSecNo&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
	}
function getPrnNo(){
	document.forms[0].action = "sanctionOrder.do?methodName=getPrnNo&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
	}
function getSanctionDetail(){
	document.forms[0].action = "sanctionOrder.do?methodName=getSanctionDetail&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
	}

function validate(){ 
	var validationCount = 0;	
			
	var event1 = new LiveValidation(document.forms[0].sanctionOrderDate,{onlyOnSubmit:true});
		event1.add( Validate.Presence );				
		
	var areAllValid = LiveValidation.massValidate( [ event1] );
	
	if(document.getElementById("sanctionFile").style.display != "none"){
	if(!checkFiles()){
		validationCount++;
	}
	}
	
	if(!areAllValid){
		validationCount++;
	}
			
	if(validationCount == 0)
		return true;	
	else
		return false;	
}

function showFiles(fileType) {
	document.forms[0].action = "sanctionOrder.do?methodName=showFiles&file="+fileType+"&" + tokenParameter + "=" + tokenValue;
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

function modifyPage(){		
	 var validationCheck = validate();
	if(validationCheck){ 
		var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	 	if(status==true){  
			document.forms[0].action = "sanctionOrder.do?methodName=modifySanctionOrder&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		  } 
	} 
}
</script>

<html:form action="/login/sanctionOrder" enctype="multipart/form-data">
	<table width="100%" align="center">
		<tr>
			<td align="center" class="pageHeader">Modify Sanction Order</td>
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
		<td align="center" class="text-error">
		 <logic:notEmpty name="docSanctionFileError">${docSanctionFileError }
									</logic:notEmpty> </td></tr>
		<tr>
			<td><label class="label-important">All
					fields marked with (*) are mandatory to fill.</label></td>
		</tr>
		<tr>
			<td align="center">
				
					<table width="100%" align="center" class="bordered">
					<tr>
						<td><strong>Name of the Applicant Organisation </strong><span class="text-error">*</span></td>
						<td><html:select property="piaCode" styleId="piaCode"
								onchange="getSanctionNo();">
								<html:option value="S">Select</html:option>
								<logic:present name="piaDetail">
									<html:options collection="piaDetail" property="piaCode"
										labelProperty="piaName" />
								</logic:present>
							</html:select>
						</td>
					</tr>
					<c:if test="${empty sanctionNumber}">
					<tr><td></td><td><span class="text-primary">No Sanction order Found</span></td></tr>
					</c:if>
					<c:if test="${not empty sanctionNumber}">
					<tr>					   
					   <td><strong>Sanction Order No.</strong><span class="text-error">*</span></td>
						<td><html:select property="sanctionOrderNo" styleId="sanctionOrderNo"
								onchange="getSanctionDetail();">
								<html:option value="">Select</html:option>
								<logic:present name="sanctionNumber">
									<html:options collection="sanctionNumber" property="sanctionOrderNo"
										labelProperty="sanctionOrderNo" />
								</logic:present>
							</html:select>
						</td>			
					</tr>
					
					</c:if>
					</table>					
					</td>
				</tr>
				<logic:present name="modifySanctionDetail">
				<logic:iterate id="sanctionDetail" name="modifySanctionDetail">
				<html:hidden property="createdOn" value="${sanctionDetail.createdOn}"></html:hidden>
				<html:hidden property="createdBy" value="${sanctionDetail.createdBy}"></html:hidden>
				<html:hidden property="sanctionFileName" value="${sanctionDetail.sanctionFileName}"></html:hidden>
				<tr>
			<td align="center">				
					<table width="100%" align="center" class="bordered">
					<tr>
					<th colspan="4">Sanction Order Details</th>
					</tr>
					<tr>
						<td><strong>Applicant Organisation Permanent Registration Number</strong></td>
						<td>
							<html:hidden property="piaRegistrationNumber" value="${sanctionDetail.piaRegistrationNumber}"></html:hidden>
							${sanctionDetail.piaRegistrationNumber}						
						</td>
					</tr>
					<tr>
						<td width="150px"><strong>Sanction Order Number</strong> <span class="text-error">*</span></td>
						<td><html:hidden property="sanctionOrderNo"  value="${sanctionDetail.sanctionOrderNo}"> </html:hidden>
								${sanctionDetail.sanctionOrderNo}</td>
					</tr>
					
					<tr>			
						<td><strong>Sanction Order Date</strong><span class="text-error">*</span></td>
						<td><html:text property="sanctionOrderDate" value="${sanctionDetail.sanctionOrderDate}"
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
					     <td>		
								<a href="javascript:showFiles('${sanctionDetail.sanctionFileName}');" >View</a>
									 <a href="javascript:fileStatus('sanctionFile', 'show');">Change</a>
										<html:file property="sanctionFile" style="display:none;" 
										styleId="sanctionFile" styleClass="infoBtn" onclick="javascript:removeError('docSanctionFileError','sError')" />	
									<a id="sanctionFileRemove" style="display:none;" href="javascript:fileStatus('sanctionFile', 'hide');" >Remove</a>
									<font id="docSanctionFileError" class="text-error"></font>
								</td> 
					</tr> 
					</table>
					</tr></logic:iterate>
					</logic:present>
			</table>

	<div align="center" style="padding-top: 15px; padding-bottom: 15px;">
		<html:button styleClass="primaryBtn" property="" onclick="modifyPage();"
			value="Modify" />
		<html:button styleClass="defaultBtn" property="" onclick="quit();"
			value="Exit" />
	</div>
</html:form>
