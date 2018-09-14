<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
</head>
<script type="text/javascript">
	function hideShowField() {
		document.getElementById('loginId').value = "";
		document.getElementById('districtCode').value = "";
		document.getElementById('blockCode').value = "";
		var level = document.getElementById('levelCode').value;
		if (level == '2') {
			document.getElementById('districtRow').className = "hide";
			document.getElementById('blockRow').className = "hide";
			getUserByCode();
		}
		if (level == '3') {
			document.getElementById('districtRow').className = "";
			document.getElementById('blockRow').className = "hide";
			getDistrictByStateCode();
		}
		if (level == '4') {
			document.getElementById('districtRow').className = "";
			document.getElementById('blockRow').className = "";
			getDistrictByStateCode();
		}
	}

	function getUserByCode() {
		var level = document.getElementById('levelCode').value;
		var entity = "", state = "", district = "", block = "";
		if (level == "2") {
			entity = "<bean:write name="entityCode" scope = "session"/>";
		}
		if (level == "3") {
			// 				state = "<bean:write name="entityCode" scope = "session"/>";
			district = document.getElementById('districtCode').value;
			entity = district;
		}
		if (level == "4") {
			// 				state = "<bean:write name="entityCode" scope = "session"/>";
			district = document.getElementById('districtCode').value;
			block = document.getElementById('blockCode').value;
			entity = block;
		}
		if (entity != "") {
			var url = "manageRoleAction.do?methodName=getUserByCode&entity="
					+ entity + "&level=" + level;
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponseUser;
			req.send(null);
		} else {
			var selectBox = document.getElementById("loginId");
			var len = selectBox.length;
			for ( var k = len - 1; k > 0; k--) {
				selectBox.remove(k);
			}
		}
	}

	function handleHttpResponseUser() {
		var level = document.getElementById('levelCode').value;
		if (req.readyState == 4) {
			var xmlDoc = req.responseXML;
			var validate = xmlDoc.getElementsByTagName("UserList");
			try {
				if (validate[0] != null) {
					if (validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
						var selectBox = document.getElementById("loginId");
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
						//if(level == "0" || level == "1")
						// 			   				if(level == "2")
						// 			   				{
						// 								if((document.getElementById('hiddenLoginId')!= null) && (document.getElementById('hiddenLoginId').value!=""))
						// 								{
						// 									getUserByCodeOnLoad();
						// 								}
						// 							} 		
					} else {
						if (document.getElementById("loginId") != null) {
							var selectBoxL = document.getElementById("loginId");
							var lenL = selectBoxL.options.length;
							for ( var kL = lenL - 1; kL > 0; kL--) {
								selectBoxL.remove(kL);
							}
						}
						alert("<bean:message key="msg.noUserAvailable" />");
					}
				}
			} catch (err) {
				alert("<bean:message key="error.fetchingUser" />");
			}
		}
	}

	function getDistrictByStateCode() {
		var state = <bean:write name="entityCode" scope = "session"/>;
		if (state != "") {
			var districtCodeId = document.getElementById('districtCode').id;
			var url = "manageRoleAction.do?methodName=getDistrictByStateCode&state="
					+ state + "&districtCodeId=" + districtCodeId;
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponse;
			req.send(null);
		} else {
			var selectBox = document.getElementById("districtCode");
			var len = selectBox.length;
			for ( var k = len - 1; k > 0; k--) {
				selectBox.remove(k);
			}
		}
	}

	function handleHttpResponse() {
		if (req.readyState == 4) {
			try {
				if (document.getElementById("districtCode") != null) {
					var selectBox = document.getElementById("districtCode");
					var len = selectBox.options.length;
					for ( var k = len - 1; k > 0; k--) {
						selectBox.remove(k);
					}
				}
				if (req.responseText != '') {
					evalScript(req.responseText);
					//var stateUser = document.getElementById('stateCode').value;
					var state = <bean:write name="entityCode" scope = "session"/>;
					if (state.length = 1) {
						state = "0" + state;
					}
					// 			   		 	if(state != "")
					// 			   			{
					// 			   				getUserByCode();
					// 			   			}
				} else {
					alert("<bean:message key="msg.noDistrictsAvailable" />");
				}
			} catch (err) {
				alert("<bean:message key="error.fetchingDistrictForState" />");
			}
		}
	}

	function getBlockByDistrictCode() {
		var district = document.getElementById('districtCode').value;
		if (district != "") {
			var url = "manageRoleAction.do?methodName=getBlockByDistrictCode&district="
					+ district
					+ "&blockCodeId="
					+ document.getElementById("blockCode").id;
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponseBlock;
			req.send(null);
		} else {
			var selectBox = document.getElementById("blockCode");
			var len = selectBox.length;
			for ( var k = len - 1; k > 0; k--) {
				selectBox.remove(k);
			}
		}
	}

	function handleHttpResponseBlock() {
		if (req.readyState == 4) {
			try {
				if (req.responseText != '') {
					var selectBoxRole = document.getElementById("blockCode");
					var len = selectBoxRole.length;
					for ( var k = len - 1; k > 0; k--) {
						selectBoxRole.remove(k);
					}
					evalScript(req.responseText);
					var districtUser = document.getElementById('districtCode').value;
					if (districtUser != "") {
						getUserByCode();
					}
				} else {
					//alert("There seems to be no block available for This District");
					var selectBoxRole = document.getElementById("blockCode");
					var len = selectBoxRole.length;
					for ( var k = len - 1; k > 0; k--) {
						selectBoxRole.remove(k);
					}
				}
			} catch (err) {
				//alert("There seems to be an error fetching block for This District");
			}
		}
	}

	function searchUser(form) {
		var minlength = 8;
		var maxlength = 16;
		if (!(validateMask(form))) {
			return;
		}
		if (document.getElementById('levelCode').value == "") {
			alert("<bean:message key="msg.selectLevelOfUser" />");
			return;
		}
		var level = document.getElementById('levelCode').value;
		if (level == '3') {
			if (document.getElementById('districtCode').value == "") {
				alert("<bean:message key="msg.selectDistrict" />");
				return;
			}
		}
		if (level == '4') {
			if (document.getElementById('districtCode').value == "") {
				alert("<bean:message key="msg.selectDistrict" />");
				return;
			}
			if (document.getElementById('blockCode').value == "") {
				alert("<bean:message key="msg.selectBlock" />");
				return;
			}
		}
		if (document.getElementById('loginId').value == "") {
			var arg = '<bean:message key="lable.user.loginID" />';
			var mess = '<bean:message key="error.select"  arg0="'+arg+ '" />';
			alert(mess);
			return;
		}
		var status = true;
		if (status == true) {
			document.userForm.action = "adminChangePassword.do?methodName=ChangePassword"
					+ "&stCode="
					+ <bean:write name="entityCode" scope = "session"/>
					+ "&"
					+ tokenParameter + "=" + tokenValue;
			document.userForm.submit();
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

	function closePage() {
		var status = window.confirm('<bean:message key="alert.close.form" />');
		if (status == true) {
			document.userForm.action = "login.do?methodName=closePage&"
					+ tokenParameter + "=" + tokenValue;
			document.userForm.submit();
		}
	}
</script>

<html:form action="login/adminChangePassword">
	<table width="100%">
		<tr><td class="pageHeader" align="center">
			Reset Password by State MIS-SGSY Nodal Officer
		</td></tr>
		<tr><td align="center">
		<table align="center" class="inputTBL" width="90%">
		<tr>
			<th><bean:message
					key="lable.user.levelofuser" /><span class="mandatory">*</span>
			</th>
			<td><html:select
					property="levelCode" styleId="levelCode" onchange="hideShowField()">
					<html:option value="">
						<bean:message key="lable.manageRole.level" />
					</html:option>
					<html:options collection="levelList" property="propertyCode"
						labelProperty="propertyValue" />
				</html:select></td>
		</tr>
		<!--  				<tr id="stateRow" class="hide"> -->
		<%-- 					<td width="50%" ><bean:message key="lable.user.selectState"/><span class="mandatory">*</span></td> --%>
		<!-- 					<td > -->
		<%-- 					<html:select property="stateCode" styleId="stateCode" onchange="getDistrictByStateCode()"> --%>
		<%-- 					<html:option value=""><bean:message key="label.selectState" /></html:option> --%>
		<%-- 	 					<html:options collection="stateList" property="stateCode" labelProperty="stateName"/> --%>
		<%-- 					</html:select></td> --%>
		<!-- 				</tr> -->

		<!-- 				<tr id="districtRow" class="hide"> -->
		<%-- 					<td width="50%" ><bean:message key="lable.user.selectDistrict"/><span class="mandatory">*</span></td> --%>
		<!-- 					<td > -->
		<%-- 					<html:select property="districtCode" styleId="districtCode" onchange="getBlockByDistrictCode()"> --%>
		<%-- 					<html:option value=""><bean:message key="label.selectDistrict" /></html:option> --%>

		<%-- 					</html:select></td> --%>
		<!-- 				</tr> -->

		<!-- 				<tr id="blockRow" class="hide"> -->
		<%-- 					<td width="50%" ><bean:message key="lable.user.selectBlockPanchayat"/><span class="mandatory">*</span></td> --%>
		<!-- 					<td > -->
		<%-- 					<html:select property="blockCode" styleId="blockCode" onchange="getVillageByBlockCode()"> --%>
		<%-- 					<html:option value=""><bean:message key="label.selectBlock" /></html:option> --%>

		<%-- 					</html:select></td> --%>
		<!-- 				</tr> -->

		<!-- 				<tr> -->
		<%-- 					<td width="50%" ><bean:message key="lable.user.loginID"/><span class="mandatory">*</span></td> --%>
		<%-- 					<td ><html:select property="loginId" styleId="loginId"> --%>
		<%-- 					<html:option value=""><bean:message key="select.label" /></html:option>						 --%>
		<%-- 					</html:select></td> --%>
		<!-- 				</tr> -->

		<tr id="districtRow" class="hide">
			<th><bean:message
					key="lable.user.selectDistrict" /><span class="mandatory">*</span>
			</th>
			<td><html:select property="districtCode"
					styleId="districtCode"
					onchange="getUserByCode(),getBlockByDistrictCode()">
					<html:option value="">
						<bean:message key="label.selectDistrict" />
					</html:option>
				</html:select></td>
		</tr>


		<tr id="blockRow" class="hide">
			<th><bean:message
					key="lable.user.selectBlockPanchayat" /><span class="mandatory">*</span>
			</th>
			<td ><html:select property="blockCode"
					styleId="blockCode" onchange="getUserByCode()">
					<html:option value="">
						<bean:message key="label.selectBlock" />
					</html:option>
				</html:select></td>
		</tr>

		<tr>
			<th><bean:message
					key="lable.user.loginID" /><span class="mandatory">*</span>
			</th>
			<td ><html:select property="loginId"
					styleId="loginId">
					<html:option value="">
						<bean:message key="select.label" />
					</html:option>
				</html:select>
			</td>
		</tr>
		</table>
		</td></tr>
		<tr>
			<td height="40" colspan="2" align="center"><html:button
					styleClass="button" property="Search" value="Submit"
					onclick="searchUser(this.form)" /> <logic:notEqual
					name="searchSuccess" value="searchSuccess">
					<input name="Button" type="button" class="button" value="Close"
						onclick="closePage()" />
				</logic:notEqual>
			</td>
		</tr>
		<html:hidden property="hiddenStateCode"></html:hidden>
		<html:hidden property="hiddenDistrictCode"></html:hidden>
		<html:hidden property="hiddenBlockCode"></html:hidden>
		<html:hidden property="hiddenVillageCode"></html:hidden>
		<html:hidden property="hiddenLoginId" value=""></html:hidden>
	</table>
</html:form>
</html:html>