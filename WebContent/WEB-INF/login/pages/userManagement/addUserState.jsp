<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<html:html>
<head>

</head>
<script language=javascript>
 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>' ; 
function userForm_mask () { 

	 var arg1='<bean:message key="userForm.userName" />';  
	 var arg2='<bean:message key="userForm.userDesignation" />';  
	 var arg3='<bean:message key="lable.user.loginID" />';  
	
     this.a0 = new Array("userName", '<bean:message key="error.correctvalue"  arg0="'+arg1+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a1 = new Array("userDesignation", '<bean:message key="error.correctvalue"  arg0="'+arg2+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a3 = new Array("loginId", '<bean:message key="error.correctvalue"  arg0="'+arg3+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[a-zA-Z0-9]*$/;  return this[varName];"));
     
}

function saveUser(form){
	
	if(!(validateMask(form))) 
   		 return;
	
	if(document.getElementById('userName').value==""){
	alert("<bean:message key="msg.enterUserName" />");
	return;
	}
	
	if(document.getElementById('userDesignation').value==""){
	alert("<bean:message key="msg.enterUserDesignation" />");
	return;
	}
	
	if(document.getElementById('levelCode').value==""){
	alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}	
	
	if((document.getElementById('districtRow').className !="hide") && (document.getElementById('districtCode').value=="")){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if((document.getElementById('blockRow').className !="hide") && (document.getElementById('blockCode').value=="")){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	 
	if(document.getElementById('loginId').value==""){
		var arg='<bean:message key="lable.user.loginID" />';  
		var mess='<bean:message key="error.enter"  arg0="'+arg+ '" />';  
		alert(mess);			
		return;
	} 	
    var emailID=document.getElementById('emailId')
	
	if (emailID.value != null){	
		if (echeck(emailID.value)==false){
		emailID.focus();
		return false;
	  }
	}
	
    document.forms[0].loginId.value=document.forms[0].userNameID.value+document.forms[0].loginId.value;
    var status=window.confirm('Login Id '+document.forms[0].loginId.value+ ' will be created. Are you sure you want to create this Login Id?');

	if(status==true){
		document.userForm.action="userAction.do?methodName=save&"+tokenParameter+"="+tokenValue;
		
		
		
		document.userForm.submit();
	}else{
	
		document.forms[0].loginId.value="";
	}  
}

var districtShortName="";
function getBlockByDistrictCode(){

	
  var district = document.getElementById('districtCode').value;
  
  if(district != ""){
    
		 getDistrictShortName(district,'GETDISTRICTSHORTNAME');
	 
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
		 	
	urlString = "<%=request.getContextPath()%>/DataPopulatorServlet?"+ "&primaryCode=" + primaryObjValue + "&funcParam=" + funcParam;

		http_request.open('POST', urlString, true);
		http_request.send(null);
		http_request.onreadystatechange = popDetails;
		function popDetails() //function to be called onchange
		{
			if (http_request.readyState == 4) {

				//evalScript(http_request.responseText);	
				if (http_request.responseText != '') {

					var districtShortName = '';

					districtShortName = http_request.responseText;

					document.forms[0].userNameID.value = "";

					document.forms[0].districtShortName.value = "";

					if (document.forms[0].stateShortName != null)
						document.forms[0].districtShortName.value = document.forms[0].stateShortName.value
								+ districtShortName;

					document.forms[0].userNameID.value = document.forms[0].districtShortName.value
							.toUpperCase();

					var url = "userAction.do?methodName=getBlockByDistrictCode&district="
							+ primaryObject;
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					} else if (window.ActiveXObject) {
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}
					req.open("GET", url, true);
					req.setRequestHeader(tokenParameter, tokenValue);
					req.onreadystatechange = handleHttpResponseBlock;
					req.send(null);

				}

			}
		}
	}

	function handleHttpResponseBlock() {
		if (req.readyState == 4) {
			var xmlDoc = req.responseXML;
			var validate = xmlDoc.getElementsByTagName("BlockList");
			try {

				if (validate[0] != null) {

					if (validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
						var selectBox = document.getElementById("blockCode");
						var len = selectBox.options.length;
						for ( var k = len - 1; k > 0; k--) {
							selectBox.remove(k);
						}
						for ( var i = 1; i < validate[0].childNodes.length; i++) {
							var oOption = document.createElement("OPTION");
							oOption.text = validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
							oOption.value = validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
							selectBox.options.add(oOption);
						}
					} else {
						alert("<bean:message key="msg.noBlockCodeAvailable" />");
					}

				}
			} catch (err) {
				alert("<bean:message key="error.fetchingBlockForDist" />");
			}
		}
	}

	function getVillageByBlockCode() {

		var block = document.getElementById('blockCode').value;
		if (block != "") {

			var blockShortName = document.forms[0].blockCode.options[document.forms[0].blockCode.selectedIndex].text;

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

			document.forms[0].userNameID.value = "";

			bshortname = blockShortName.substring(0, 1);

			bshortname = bshortname
					+ blockShortName.substring(blockShortName.length - 3,
							blockShortName.length);

			document.forms[0].blockShortName.value = document.forms[0].districtShortName.value
					+ bshortname;

			document.forms[0].userNameID.value = document.forms[0].blockShortName.value;

		}

	}

	function hideShowField() {

		var selectBox = document.getElementById("blockCode");
		var len = selectBox.length;
		if (selectBox.length > 0) {
			for ( var k = len - 1; k > 0; k--) {
				selectBox.remove(k);
			}
		}
		document.getElementById('districtCode').value = "";
		document.getElementById('blockCode').value = "";

		//alert(document.forms[0].userNameID)

		if (typeof (document.forms[0].userNameID) != 'undefined') {

			if (document.forms[0].stateShortName != null)
				document.forms[0].userNameID.value = document.forms[0].stateShortName.value;

		}
		var lavel = document.getElementById('levelCode').value;

		if (lavel == '3') {
			document.getElementById('districtRow').className = "";
			document.getElementById('blockRow').className = "hide";

		}
		if (lavel == '4') {
			document.getElementById('districtRow').className = "";
			document.getElementById('blockRow').className = "";

		}
		if (lavel == '5') {
			document.getElementById('districtRow').className = "";
			document.getElementById('blockRow').className = "";

		}
		if (lavel == '2' || lavel == "") {
			document.getElementById('districtRow').className = "hide";
			document.getElementById('blockRow').className = "hide";

		}

	}

	function echeck(str) {

		var at = "@";
		var dot = ".";
		var lat = str.indexOf(at);
		var lstr = str.length;
		var ldot = str.indexOf(dot);
		if (str.indexOf(at) == -1) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		if (str.indexOf(at) == -1 || str.indexOf(at) == 0
				|| str.indexOf(at) == lstr) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
				|| str.indexOf(dot) == lstr) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		if (str.indexOf(at, (lat + 1)) != -1) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		if (str.substring(lat - 1, lat) == dot
				|| str.substring(lat + 1, lat + 2) == dot) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		if (str.indexOf(dot, (lat + 2)) == -1) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		if (str.indexOf(" ") != -1) {
			alert("<bean:message key="error.invalidEmailId" />");
			return false;
		}

		return true;
	}

	function closePage() {
		var status = window.confirm("<bean:message key="alert.close.form" />");
		if (status == true) {
			document.userForm.action = "login.do?methodName=closePage&"
					+ tokenParameter + "=" + tokenValue;
			document.userForm.submit();
		}
	}
</script>
<body>
	<html:form action="login/userAction">
		<div class="mainpane">
			<table width="100%">
				<tr>
					<td align="center" class="pageHeader">
					<bean:message key="lable.add" /> <bean:message key="lable.user.user" />
					<input type="hidden" name="userNameID" /> 
					<html:hidden property="informationDialog" />
					<html:hidden property="informationDialogText" />
					<html:hidden property="informationDialogHeader" />
					</td>
				</tr>
				<tr>
					<td><div style="padding-left: 50px;" align="left"><font color="red">*</font> <strong>Are Mandatory Field</strong></div>	</td>
				</tr>
				<tr>
					<td align="center">
						<table width="90%" class="inputTBL">
							<tr>
								<th width="30%"><bean:message key="lable.user.userName" /><span class="mandatory">*</span></th>
								<td><html:text property="userName" styleId="userName" onblur="this.value=this.value.toUpperCase();" size="50" maxlength="70"/></td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.designation" /><span class="mandatory">*</span></th>
								<td><html:text property="userDesignation" styleId="userDesignation" onblur="this.value=this.value.toUpperCase();" size="50" maxlength="70"/></td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.levelofuser" /><span class="mandatory">*</span></th>
								<td><html:select property="levelCode" styleId="levelCode" onchange="hideShowField()">
										<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
										<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
									</html:select>
								</td>
							</tr>

							<tr id="districtRow" class="hide">
								<th><bean:message key="lable.user.selectDistrict" /><span class="mandatory">*</span></th>
								<td><html:select property="districtCode" styleId="districtCode" onchange="getBlockByDistrictCode()">
										<html:option value=""><bean:message key="label.selectDistrict" /></html:option>
										<html:options collection="districtList" property="districtCode" labelProperty="districtName" />
									</html:select>
								</td>
							</tr>

							<tr id="blockRow" class="hide">
								<th><bean:message key="lable.user.selectBlockPanchayat" /><span class="mandatory">*</span></th>
								<td><html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()">
										<html:option value=""><bean:message key="label.selectBlock" /></html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.loginID" /><span class="mandatory">*</span>
								</th>
								<td><html:text property="loginId" styleId="loginId" onblur="this.value=this.value.toUpperCase();" size="50" maxlength="70"/> <br />
								<font color="red" size="1"><html:errors property="loginId" /></font>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.emailID" /><span class="mandatory"></span>&nbsp;
								<font size="1" color="blue">(NIC mail id is preferable.Don't use yahoo mail id)</font></th>
								<td><html:text property="emailId" styleId="emailId" onblur="this.value=this.value.toUpperCase(),echeck(this.value);" size="50" maxlength="50"/>
									<br />
								<font color="red" size="1"><html:errors property="emailId" /></font>
								</td>
							</tr>
							<tr>
								<th>Mobile <font color="blue" size="1">(10 digit Only)</font>
								</th>
								<td>+91 - <html:text property="mobile" styleId="mobile" size="10" maxlength="10" /> <br />
								<font color="red" size="1"><html:errors property="mobile" /></font>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.user.accountStatus" /><span class="mandatory">*</span></th>
								<td><html:select property="activeFlag" size="1">
										<html:option value="Y">
											<bean:message key="lable.open" />
										</html:option>
										<!-- <html:option value="N"><bean:message key="lable.notauthorised" /></html:option> -->
									</html:select> 
									<html:hidden property="hiddenStateCode" styleId="hiddenStateCode" />
									<logic:present name="<%=SGSYConstants.SGSY_LOCATIONVO %>">
										<input type="hidden" name="stateShortName" value="<bean:write name="<%=SGSYConstants.SGSY_LOCATIONVO %>" 
														property="stateShortName"></bean:write>" /></logic:present> 
									<input type="hidden" name="districtShortName" /> 
									<input type="hidden" name="blockShortName" />
								</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td align="center"><html:button styleClass="button" property="next" value="Save" onclick="saveUser(this.form)"></html:button>
						<input name="Button" type="reset" class="button" value="Clear" />
						<input name="Button" type="button" class="button" value="Close"
						onclick="closePage()" />
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<script>
hideShowField();
</script>
</html:html>
