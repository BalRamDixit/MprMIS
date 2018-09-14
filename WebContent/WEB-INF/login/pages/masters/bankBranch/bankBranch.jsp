 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="Javascript1.1" src="javaScript/common.js"></script>
<!-- <script language="javascript" src="javaScript/AjaxScript.js"></script>

 -->
<script language="javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>' ; 
 

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
function getIFSCCheck(ifscCode){
	 var ifscCode = document.getElementById("ifscCode").value;	
	 var xmlHTTP;
		if(window.XMLHttpRequest) {
			xmlHTTP = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
		}
	    var url = 'bankBranch.do?methodName=checkIfsc';
	    xmlHTTP.onreadystatechange = IfscCode;
		
		xmlHTTP.open("POST",url, true);
		xmlHTTP.setRequestHeader(tokenParameter, tokenValue);
		xmlHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHTTP.send("ifscCode="+ifscCode+"");
		function IfscCode(){
			if(xmlHTTP .readyState==4 && xmlHTTP.status==200){
				var flag = xmlHTTP.responseText;
				var textval = "IFSC Code Already Exist!";
				if(flag == "true") 
				{
					document.getElementById("myDiv").innerHTML= textval;
				}
			}
		};      
}
</script>


<html:form action="login/bankBranch">
<table width="100%">	
<tr>
	<td class="pageHeader" align="center" ><bean:message key="title.addbankbranch"></bean:message></td> 		 
</tr>	
<tr><th style="padding-left: 50px;" align="left"><font color="red">*</font> Are Mandatory Field</th></tr>	
<tr>
<td align="center">	
	<table width="90%" align="center" class="inputTBL">
	<!-- FOR STATE LOGIN -->	
	<logic:equal name="BRANCH_ENTRY_LEVEL" value="STATE_BRANCH" >
	<tr>
		<th width="300px;"><bean:message key="label.state"/><span class="mandatory">*</span></th>
		<td><label><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></label></td>
	</tr>
	</logic:equal>
	
	<!-- FOR DISTRICT LOGIN -->
	<logic:equal name="BRANCH_ENTRY_LEVEL" value="DISTRICT_BRANCH" >
	<tr>
		<th width="300px;"><bean:message key="label.state"/><span class="mandatory">*</span></th>
		<td><label><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="stateName"></bean:write></label></td>
	</tr>
	<tr>
		<th><bean:message key="label.district"/><span class="mandatory">*</span></th>
		<td><label><bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" property="districtName"></bean:write></label></td>
	</tr>
	</logic:equal>
	
	<tr>
		<th><bean:message key="label.bankname"/><span class="mandatory">*</span></th>
		<td><html:select property="bankCode" styleId="bankCode" onchange="getDetailsByAjax(this,'','banktypes')"> 
			<html:option value=""><bean:message key="label.select" /></html:option>
			<html:options collection="bankscoll" property="propertyCode" labelProperty="propertyValue" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th><bean:message key="label.banktype"/></th>
		<td><div id='div1'> </div></td>
	</tr>
	<tr>
		<th><bean:message key="label.branchname"/><span class="mandatory">*</span></th>
		<td><html:text property="bankBranchName" styleId="bankBranchName" size="75" maxlength="75" onblur="this.value=this.value.toUpperCase();" onkeypress="return checkAlNum(this);"></html:text></td>
	</tr>
	<tr>
		<th><bean:message key="label.branchabbreviation"/><span class="mandatory">*</span></th>
	<td><html:text property="ifscCode"  styleId="ifscCode" size="11" maxlength="11" onblur="javascript:getIFSCCheck();this.value=this.value.toUpperCase();" ></html:text>
	<font  color="red"><div id="myDiv">  </div></font></td>
	</tr>
	
	<tr>
		<th><bean:message key="bankForm.address"/><span class="mandatory">*</span></th>
		<td><html:textarea property="address" styleId="address" style="width:75%;"  onblur="this.value=this.value.toUpperCase();" rows="3" /></td>
	</tr>			
</table>
</td></tr>	
<tr><td align="center">
	 <html:button styleClass="button" property="" onclick="saveBranch()"><bean:message  key="button.save" /></html:button>  
	 <html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message  key="button.close" /></html:button>  
	<html:button styleClass="button" property="" onclick="resetAll()"><bean:message  key="button.clear" /></html:button>					
</td></tr>
</table>
</html:form>

<script type="text/javascript">

function resetAll(){    
	var status=window.confirm('<bean:message key="msg.clear" />');
	if(status==true){
	document.forms[0].action = "bankBranch.do?methodName=showAdd"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
	document.forms[0].bankCode="";
	document.forms[0].bankTypeCode="";
	document.forms[0].bankBranchName="";
	document.forms[0].ifscCode="";
	document.forms[0].villageCode=""; 	
	document.forms[0].address="";	
	}	
   }
   
function validate(){
	var bankCode = new LiveValidation(document.forms[0].bankCode,{onlyOnSubmit:true});
		bankCode.add( Validate.Presence );		
	
	var bankBranchName = new LiveValidation(document.forms[0].bankBranchName,{onlyOnSubmit:true});
		bankBranchName.add( Validate.Presence );
		bankBranchName.add( Validate.Length, { maximum: 100} );
		bankBranchName.add( Validate.Format, { pattern: /^[a-z\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."});
	
	var ifscCode = new LiveValidation(document.forms[0].ifscCode,{onlyOnSubmit:true});	
		ifscCode.add( Validate.Presence );
		ifscCode.add( Validate.Length, { is: 11} );
		ifscCode.add( Validate.Format, { pattern: /^[a-z\d]+$/i, failureMessage: "Only AlphaNumericValue are Allowed."});
				
	var address = new LiveValidation(document.forms[0].address,{onlyOnSubmit:true});
		address.add( Validate.Presence ); 
		address.add( Validate.Length, { maximum: 250 } );
	/* 	address.add( Validate.Format, { pattern: /^[a-z\d\s]+$/i, failureMessage: "Special Characters Other Than (Space) are Not Allowed."}); */
		
	var areAllValid = LiveValidation.massValidate( [bankCode, bankBranchName, ifscCode, address ] );
	return areAllValid;
}  
	
function saveBranch(){	
	if(validate()){
		var status=window.confirm('<bean:message key="msg.saveForm" />');
		if(status==true){
			document.forms[0].action="bankBranch.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
 			document.forms[0].submit();
		}	
	}
}
	
// USED TO GET THE BANK DETAIL	
function getDetailsByAjax(primaryObject,dependantObject,funcParam){
	controlName=dependantObject;
	http_request = createAjaxRequest();
	primaryObjValue = primaryObject.value;	
		
	urlString = "<%=request.getContextPath()%>/DataPopulatorServlet?controlName="+controlName
				+"&primaryCode="+primaryObject.value
				+"&funcParam="+funcParam;			
 			 
	http_request.open('POST',urlString, true);
	http_request.setRequestHeader(tokenParameter, tokenValue);
	http_request.send(null);
	http_request.onreadystatechange = popDetails;
}	 
//function to be called onchange
function popDetails(){
	if(http_request.readyState == 4){
 		if( http_request.responseText == 'null' )
 			document.getElementById("div1").innerText = "" ;
 		else	 
 			document.getElementById("div1").innerText = http_request.responseText;
	}
} 

</script>