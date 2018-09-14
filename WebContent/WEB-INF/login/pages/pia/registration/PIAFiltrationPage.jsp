<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<script type="text/javascript">

var tokenParameter = 'reqtrack';
var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';
 
function FilterPIA(){
	if(validate()){
	    	document.forms[0].action = "registrationAction.do?methodName=checkAuthenticatePIA&" + tokenParameter + "=" + tokenValue;
	    	document.forms[0].submit();
	}
}

function quit(){	
	var status = window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){	
		window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;	
	}
}

function validate(){ 
	var piaCode = new LiveValidation(document.forms[0].piaCode,{onlyOnSubmit:true});
		piaCode.add( Validate.Presence );
		piaCode.add( Validate.Numericality, { onlyInteger: true } );	
		
	var registrationNumber = new LiveValidation(document.forms[0].registrationNumber,{onlyOnSubmit:true});	
		registrationNumber.add( Validate.Presence );
		
	var panNo = new LiveValidation(document.forms[0].panNo,{onlyOnSubmit:true});
		panNo.add( Validate.Presence );	
		panNo.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
		
	var tanNo = new LiveValidation(document.forms[0].tanNo,{onlyOnSubmit:true});	
		tanNo.add( Validate.Presence );	
		tanNo.add( Validate.Format, { pattern: /^([a-zA-Z]{4})(\d{5})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect TAN Format" } );
										
	var areAllValid = LiveValidation.massValidate( [ piaCode, registrationNumber, panNo, tanNo] );
	return areAllValid;	
}

function validateEntireForm(){	
	var piaCode = new LiveValidation(document.forms[0].piaCode, {onlyOnBlur: true } );
		piaCode.add( Validate.Numericality, { onlyInteger: true } );
		
	var panNo = new LiveValidation(document.forms[0].panNo, {onlyOnBlur: true } );
		panNo.add( Validate.Format, { pattern: /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect PAN Format" } );
			
	var tanNo = new LiveValidation(document.forms[0].tanNo, {onlyOnBlur: true } );
		tanNo.add( Validate.Format, { pattern: /^([a-zA-Z]{4})(\d{5})([a-zA-Z]{1})+$/i, failureMessage: "Incorrect TAN Format" } );
			
 }	

</script>
	<body onload="validateEntireForm()">
	<html:form action="registrationAction" >	
		<div class="outerTBL">
			<table width="100%">
				<tr>
					<td class="pageHeader" align="center">
					Applicant Organisation Details</td>
				</tr>
				
			<logic:present name="ErrorMsgPIA">
			<tr >															
				<td  align="center" style="padding-top: 10px;" colspan="2">
				<p><label class="label-warning" style="font: 18px;">${ErrorMsgPIA}</label></p>
 				</td>										
			</tr>
			</logic:present>
			<logic:present name="PIARegistraionSubmitted">
			<tr >															
				<td  align="center" style="padding-top: 10px;" colspan="2">
				<p><label class="label-warning" style="font: 18px;">${ErrorMsgPIA}</label></p>
 				</td>										
			</tr>
			</logic:present>
				<tr>
					<td align="center">
						<table style="padding-top: 15px;" width="90%" class="inputTBL">
							<tr>
								<td width="10px;">1.</td>
								<td width="200px;">Temporary Reference Number<span class="text-error">*</span></td>
								<td><html:text property="piaCode" name="registrationForm" size="11" maxlength="11" styleId="piaCode" onkeypress="return isNumberKey(event)"/></td>
							</tr>
							<tr>
								<td>2.</td>
								<td>Registration Number<span class="text-error">*</span></td>
								<td><html:text property="registrationNumber" name="registrationForm" size="21" maxlength="21" styleId="registrationNumber" onblur="this.value=this.value.toUpperCase();" /></td>
							</tr>

							<tr>
								<td>3.</td>
								<td>PAN Number<span class="text-error">*</span></td>
								<td><html:text property="panNo" name="registrationForm" styleId="panNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /></td>
							</tr>

							<tr><td>4.</td>
								<td>TAN Number<span class="text-error">*</span></td>
								<td><html:text property="tanNo" name="registrationForm" styleId="tanNo" size="10" maxlength="10" onblur="this.value=this.value.toUpperCase();" /></td>
							</tr>
						</table></td>
				</tr>
			</table>

			<div align="center" style="padding-top: 15px; padding-bottom: 15px;">
				<html:button styleClass="primaryBtn" property="" onclick="FilterPIA()" value="Submit" />
				<html:button styleClass="defaultBtn" property="" onclick="quit()" value="Exit" />
			</div>
		</div>
	</html:form>
	</body>