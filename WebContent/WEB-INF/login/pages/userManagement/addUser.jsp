<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.login.LoginVO"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
</head>
<script language=javascript>

 
function getDistrictByStateCode(){
  var state = document.getElementById('stateCode').value;
  if(state != ""){
		    var url = "userAction.do?methodName=getDistrictByStateCode&state="+state;
		    if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} 
			else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);			
			req.setRequestHeader(tokenParameter, tokenValue);
		 	req.onreadystatechange = handleHttpResponse;
			req.send(null);
	}
	else{
   		var selectBox = document.getElementById("districtCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function handleHttpResponse(){
	if (req.readyState == 4){	
		    var xmlDoc = req.responseXML; 
		    var validate = xmlDoc.getElementsByTagName("DistrictList");
		  	if(validate[0]!=null){
		   		if(validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
		    		var selectBox = document.getElementById("districtCode");
		    		var len = selectBox.options.length;
		   			for(var k=len-1;k>0;k--) {
		     		selectBox.remove(k);
		    		}
		   for(var i=1;i<validate[0].childNodes.length;i++){
		           var oOption = document.createElement("OPTION");
					oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
					oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
					selectBox.options.add(oOption);
		   		}
		   		var state = document.getElementById('stateCode').value;
		   		getStateShortName(state,'GETSTATESHORTNAME'); 		
		    } else {	  
		    	alert('<bean:message key="msg.noDistrictsAvailable" />');
		    }
		} 
     }
}



function getDataList() {
	
	//alert("FFF");
		var level = document.forms[0].levelCode.value;
		
		//alert(level);
		if(level ){
		document.userForm.action = "userAction.do?methodName=getDataList&form=1&type="+level+"&"
				+ tokenParameter+ "="+ tokenValue;
				

		document.userForm.submit();
		}
	}


function getStateShortName(primaryObject,funcParam){
	var state = document.getElementById('stateCode').value;
   // getStateShortName(state,'GETSTATESHORTNAME'); 
	var arg0="",arg1="",errorList="";   
	http_request = createAjaxRequest(); 	
	var primaryObjValue = state ;	 	
	urlString = "<%=request.getContextPath()%>/DataPopulatorServlet?" +"&stateCode="+primaryObjValue 
    +"&funcParam="+'GETSTATESHORTNAME';  
     	
	http_request.open('POST',urlString, true);
	http_request.setRequestHeader(tokenParameter, tokenValue);
	http_request.send(null);
	http_request.onreadystatechange = popDetails;
		function popDetails()		//function to be called onchange
		{
			if(http_request.readyState == 4){		
				if(http_request.responseText!='')	
				   if(document.getElementById("districtShortName")!=null && typeof(document.getElementById("districtShortName"))!='undefined')
					   document.getElementById("districtShortName").value="";
				   if(document.getElementById("stateShortName")!=null && typeof(document.getElementById("stateShortName"))!='undefined')
				 	{
					   document.getElementById("stateShortName").value="";
					 	document.getElementById("stateShortName").value=http_request.responseText;
				 	}
				 	 if(document.getElementById("userNameID")!=null && typeof(document.getElementById("userNameID"))!='undefined')
				 		document.getElementById("userNameID").value="";
				 	document.getElementById("userNameID").value=http_request.responseText;
				 					 	
				 	if(funcParam=='1' || funcParam=='2'){
				 				 var district = document.getElementById('districtCode').value;
				 				 getDistrictShortName(district,funcParam);
				 	}
			} 	
		}	
 }

 

var districtShortName="";

function getBlockByDistrictCode(){	
  var district = document.getElementById('districtCode').value;
  if(district != ""){
	  // setTimeout(function(){
	  		getDistrictShortName(district,'GETDISTRICTSHORTNAME');
		// },200);
	
	}
	else{
   		var selectBox = document.getElementById("blockCode");
   		var len = selectBox.length;
   		for(var k=len-1;k>0;k--){
     			selectBox.remove(k);
   		}	   		   		
	} 
}    

function getDistrictShortName(primaryObject,funcParam){
	var arg0="",arg1="",errorList="";  
	http_request = createAjaxRequest();
	var primaryObjValue = primaryObject ; 	
	urlString = "<%=request.getContextPath()%>/DataPopulatorServlet?"
				+ "&primaryCode=" + primaryObjValue + "&funcParam="
				+ 'GETDISTRICTSHORTNAME';

		http_request.open('POST', urlString, true);
		http_request.setRequestHeader(tokenParameter, tokenValue);
		http_request.send(null);
		http_request.onreadystatechange = popDetails;
		function popDetails() //function to be called onchange
		{
			if (http_request.readyState == 4) {

				//evalScript(http_request.responseText);	
				if (http_request.responseText != '') {

					var districtShortName = '';

					districtShortName = http_request.responseText;

					if (document.getElementById("userNameID") != null
							&& typeof (document.getElementById("userNameID")) != 'undefined')
						document.getElementById("userNameID").value = "";
					if (document.getElementById("districtShortName") != null
							&& typeof (document
									.getElementById("districtShortName")) != 'undefined')
						document.getElementById("districtShortName").value = "";

					document.getElementById("districtShortName").value = document
							.getElementById("stateShortName").value
							+ districtShortName;

					document.getElementById("userNameID").value = document
							.getElementById("districtShortName").value
							.toUpperCase();

					if (funcParam == '2') {

						getBlockUserId();
					}

				}

			}
		}
	}

	function getBlockUserId() {
		var block = document.getElementById('blockCode').value;
		if (block != "") {
			var blockShortName = document.getElementById('blockCode').options[document
					.getElementById('blockCode').selectedIndex].text;

			var intIndexOfMatch = blockShortName.indexOf("(");

			while (intIndexOfMatch != -1) {

				blockShortName = blockShortName.replace("(", "");
				intIndexOfMatch = blockShortName.indexOf("(");
			}

			intIndexOfMatch = blockShortName.indexOf(")");

			while (intIndexOfMatch != -1) {

				blockShortName = blockShortName.replace(")", "");
				intIndexOfMatch = blockShortName.indexOf(")");
			}
			blockShortName = blockShortName.split(' ');

			var bb2 = '', bshortname = '';

			if (typeof (blockShortName[1]) != 'undefined')
				bb2 = blockShortName[1];

			blockShortName = blockShortName[0] + bb2;

			blockShortName = blockShortName.toUpperCase();

			if (document.getElementById("userNameID") != null && typeof (document.getElementById("userNameID")) != 'undefined')
				document.getElementById("userNameID").value = "";
			bshortname = blockShortName.substring(0, 1);
			bshortname = bshortname+ blockShortName.substring(blockShortName.length - 3, blockShortName.length);
			document.getElementById("blockShortName").value = document.getElementById("districtShortName").value+ bshortname;
			document.getElementById("userNameID").value = document.getElementById("blockShortName").value;
			//	alert('document.getElementById("userNameID").value==='+document.getElementById("userNameID").value);
		}

	}

	function userForm_mask() {
		var arg1 = '<bean:message key="userForm.userName" />';
		var arg2 = '<bean:message key="userForm.userDesignation" />';
		var arg3 = '<bean:message key="lable.user.loginID" />';

		this.a0 = new Array(
				"userName",
				'<bean:message key="error.correctvalue"  arg0="'+arg1+'" />',
				new Function("varName",
						"this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
		this.a1 = new Array(
				"userDesignation",
				'<bean:message key="error.correctvalue"  arg0="'+arg2+'" />',
				new Function("varName",
						"this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
		this.a3 = new Array(
				"loginId",
				'<bean:message key="error.correctvalue"  arg0="'+arg3+'" />',
				new Function("varName",
						"this.mask=/^[a-zA-Z]{1}[a-zA-Z0-9]*$/;  return this[varName];"));

	}

	function saveUser(form) {

		if (!(validateMask(form)))
			return;

		if (document.getElementById('userName').value == "") {
			alert('<bean:message key="msg.usernameToBeEntered" />');
			return;
		}
		if (document.getElementById('userDesignation').value == "") {
			alert('<bean:message key="msg.designationToBeEntered" />');
			return;
		}
		if (document.getElementById('levelCode').value == "") {
			alert('<bean:message key="msg.levelOfUserToBeEntered" />');
			return;
		}

		var lavel = document.getElementById('levelCode').value;
		if (lavel == '2') {
			if (document.getElementById('stateCode').value == "") {
				alert('<bean:message key="msg.selectState" />');
				return;
			}
		}
		/*if (lavel == '3') {
			if (document.getElementById('stateCode').value == "") {
				alert('<bean:message key="msg.selectState" />');
				return;
			}
			if (document.getElementById('districtCode').value == "") {
				alert('<bean:message key="msg.selectDistrict" />');
				return;
			}
		}*/
		if (lavel == '4') {
			if (document.getElementById('stateCode').value == "") {
				alert('<bean:message key="msg.selectState" />');
				return;
			}
			if (document.getElementById('districtCode').value == "") {
				alert('<bean:message key="msg.selectDistrict" />');
				return;
			}
			if (document.getElementById('blockCode').value == "") {
				alert('<bean:message key="msg.selectBlock" />');
				return;
			}
		}

		if (lavel == '3') {
			if (document.getElementById('stateCode').value == "") {
				alert('<bean:message key="msg.selectState" />');
				return;
			}
			if (document.getElementById('districtCode').value == "") {
				alert('<bean:message key="msg.selectDistrict" />');
				return;
			}
			

		}
		if (document.getElementById('loginId').value == "") {
			var arg = '<bean:message key="lable.user.loginID" />';
			var mess = '<bean:message key="error.enter"  arg0="'+arg+ '" />';
			alert(mess);
			return;
		}

		var emailID = document.getElementById('emailId')
		if (emailID.value != null) {
			if (echeck(emailID.value) == false) {
				emailID.focus();
				return false;
			}
		}
		//alert("User Name ID:"+ document.getElementById("userNameID").value );
		document.getElementById("loginId").value = document.getElementById("userNameID").value + document.getElementById("loginId").value;
		//alert("User Name ID:"+ document.getElementById("loginId").value );
		var status = window
				.confirm('Login Id '
						+ document.getElementById("loginId").value
						+ ' will be created. Are you sure you want to create this Login Id?');
		if (status == true) {

			document.userForm.action = "userAction.do?methodName=save" + "&"
					+ tokenParameter + "=" + tokenValue;
			document.userForm.submit();
		} else {

			document.getElementById("loginId").value = "";
		}
	}

	function echeck(str) {

		var at = "@";
		var dot = ".";
		var lat = str.indexOf(at);
		var lstr = str.length;
		var ldot = str.indexOf(dot);
		if (str.indexOf(at) == -1) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		if (str.indexOf(at) == -1 || str.indexOf(at) == 0
				|| str.indexOf(at) == lstr) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
				|| str.indexOf(dot) == lstr) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		if (str.indexOf(at, (lat + 1)) != -1) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		if (str.substring(lat - 1, lat) == dot
				|| str.substring(lat + 1, lat + 2) == dot) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		if (str.indexOf(dot, (lat + 2)) == -1) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		if (str.indexOf(" ") != -1) {
			alert('<bean:message key="error.invalidEmailId" />');
			return false;
		}

		return true;
	}

	function closePage() {
		var status = window.confirm('<bean:message key="alert.close.form" />');
		if (status == true) {
			document.userForm.action = "login.do?methodName=closePage&" + tokenParameter + "=" + tokenValue;
			document.userForm.submit();
		}
	}

	function clearPage() {
		var status = window.confirm('<bean:message key="msg.clear" />');
		if (status == true) {
			document.userForm.action = "userAction.do?methodName=showAdd&"+ tokenParameter + "=" + tokenValue;
			document.userForm.submit();
		}
	}
	
</script>
<body>
	<html:form action="login/userAction">
		<div>
			<table width="100%">
				<tr>
					<td align="center">
						<% LoginVO loginvo = (LoginVO) request.getSession().getAttribute("loginVO"); %>
						<table width="100%" align="center">
							<tr>
								<td align="center" class="pageHeader">Add User</td>
								<html:hidden property="informationDialog" styleId="informationDialog" />
								<html:hidden property="informationDialogText" styleId="informationDialogText" />
								<html:hidden property="informationDialogHeader" styleId="informationDialogHeader" />

								<logic:present name="stateShortName">
									<input type="hidden" name="stateShortName" value="${stateShortName}" />
								</logic:present>
								<logic:present name="districtShortName">
									<input type="hidden" name="districtShortName" value="${districtShortName}" />
								</logic:present>
							</tr>
							<tr>
								<td> <label class="label-important">( * ) Are Mandatory Field</label></td>
							</tr>
						</table>
						<table width="98%" class="inputTBL">	
							<tr>
								<th width="35%"><bean:message key="lable.user.userName" /><span class="text-error">*</span>
								</th>
								<td><html:text property="userName" onblur="this.value=this.value.toUpperCase();" styleId="userName" size="50" maxlength="70"/> 
									<span class="text-error"><html:errors property="userName" /></span>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.designation" /><span class="text-error">*</span></th>
								<td><html:text property="userDesignation" onblur="this.value=this.value.toUpperCase();" styleId="userDesignation" size="50" maxlength="70"/> <br />
									<span class="text-error"><html:errors property="userDesignation" /></span>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.levelofuser" /><span class="text-error">*</span></th>
								<td><html:select property="levelCode" styleId="levelCode" onchange="getDataList();">
										<html:option value="">Select</html:option>
										<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
									</html:select><br />
									<span class="text-error"><html:errors property="levelCode" /></span>
								</td>
							</tr>
							<logic:present name="myas">
								<html:hidden property="entityCode"></html:hidden>
							</logic:present>
							<logic:present name="state">
								<tr>
									<th><bean:message key="lable.user.selectState" /><span class="text-error">*</span>
									</th>
									<td><html:select property="stateCode" styleId="stateCode" onchange="getStateShortName('','0')">
											<html:option value="">
												<bean:message key="label.selectState" />
											</html:option>
											<logic:present name="stateList">
												<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue" />
											</logic:present>
										</html:select>
									</td>
								</tr>
							</logic:present>	
							
							<logic:present name="district">
								<tr>
									<th><bean:message key="lable.user.selectState" /><span class="text-error">*</span>
									</th>
									<td><html:select property="stateCode" styleId="stateCode" onchange="getDataList();getStateShortName('','0')">
											<html:option value="">
												<bean:message key="label.selectState" />
											</html:option>
											<logic:present name="stateList">
												<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue" />
											</logic:present>
										</html:select>
									</td>
								</tr>
								<tr>
									<th><bean:message key="lable.user.selectDistrict" /><span class="text-error">*</span>
									</th>
									<td><html:select property="districtCode" styleId="districtCode" onchange="getStateShortName('','0')">
											<html:option value="">
												<bean:message key="label.selectDistrict" />
											</html:option>
											<logic:present name="districtList">
												<html:options collection="districtList" property="propertyCode" labelProperty="propertyValue" />
											</logic:present>
										</html:select>
									</td>
								</tr>
							</logic:present>		
													
							<tr>
								<th><bean:message key="lable.user.loginID" /><span class="text-error">*</span>
								</th>
								<td>
									<html:text property="loginId" onblur="this.value=this.value.toUpperCase();" styleId="loginId" size="50" maxlength="70"/> <br />
								<span class="text-error"><html:errors property="loginId" /></span>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.emailID" /><span class="text-error">*</span>&nbsp;
								<font size="1" color="blue">(NIC mail id is preferable.Don't use yahoo mail id)</font>
								</th>
								<td><html:text property="emailId" styleId="emailId" onblur="this.value=this.value.toUpperCase(),echeck(this.value);" size="50" maxlength="50"/>
									<span class="text-error"><html:errors property="emailId" /></span>
								</td>
							</tr>
							<tr>
								<th>Mobile <font color="blue" size="1">(10 digit Only)</font>
								</th>
								<td>+91 - <html:text property="mobile" styleId="mobile" size="10" maxlength="10" /> 
								<span class="text-error"><html:errors property="mobile" /></span>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.accountStatus" /><span class="text-error">*</span>
								</th>
								<td><html:select property="activeFlag" size="1">
										<html:option value="Y">
											<bean:message key="lable.open" />
										</html:option>
										<html:option value="B">
											<bean:message key="lable.blocked" />
										</html:option>
										<html:option value="N">
											<bean:message key="lable.notauthorised" />
										</html:option>
									</html:select>
								</td>
							</tr>
							<html:hidden property="hiddenStateCode" styleId="hiddenStateCode" />
						</table>
						<div align="center">						
							<html:button styleClass="primaryBtn" property="next" value="Save" onclick="saveUser(this.form)"></html:button>
							<input name="Button" type="button" class="defaultBtn" value="Clear" onclick="clearPage()" /> 
							<input name="Button" type="button" class="defaultBtn" value="Close" onclick="closePage()" />
						</div> 
						<input type="hidden" name="userNameID" id="userNameID" /> 
						<input type="hidden" name="stateShortName" id="stateShortName" /> 
						<input type="hidden" name="districtShortName" id="districtShortName" /> 
						<input type="hidden" name="blockShortName" id="blockShortName" />
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<script>
	function getDataList() {
		
	alert("FFF");
		var level = document.forms[0].levelCode.value;
		
		alert(level);
		if(level ){
		document.userForm.action = "userAction.do?methodName=getDataList&form=1&type="+level+"
				&"
				+ tokenParameter
				+ "="
				+ tokenValue
				+ "&param="
				+ param;

		document.userForm.submit();
		}
	}
	
	function getStateList(){
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
</html:html>
